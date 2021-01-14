package com.yhf.config;

import com.alibaba.fastjson.JSON;
import com.yhf.pojo.*;
import com.yhf.service.*;
import com.yhf.service.impl.SensorServiceImpl;
import com.yhf.service.impl.WaterQualityServiceImpl;
import com.yhf.service.impl.WeatherStationServiceImpl;
import com.yhf.util.*;
import com.yhf.util.impl.WaterQualityUtilImpl;
import com.yhf.util.impl.WeatherStationUtilImpl;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

import static javax.xml.bind.DatatypeConverter.printHexBinary;

/**
 * MQTT配置，消费者
 * @author
 */
@Configuration
public class MqttReceiverConfig {

    @Autowired
    private SensorService sensorUtil;
    @Autowired
    private WaterQualityService waterQualityService;
    @Autowired
    private WeatherStationService weatherStationService;
    @Autowired
    private TempHumService tempHumService;
    @Autowired
    private IlluService illuService;
    @Autowired
    private WaterQualityUtil waterQualityUtil;
    @Autowired
    private WeatherStationUtil weatherStationUtil;
    @Autowired
    private TempHumUtil tempHumUtil;
    @Autowired
    private IlluUtil illuUtil;
    @Autowired
    private EquipUtil equipUtil;

    /**
     * 订阅的bean名称
     */
    public static final String CHANNEL_NAME_IN = "mqttInboundChannel";

    // 客户端与服务器之间的连接意外中断，服务器将发布客户端的“遗嘱”消息
    private static final byte[] WILL_DATA;
    static {
        WILL_DATA = "offline".getBytes();
    }

    @Value("${mqtt.username}")
    private String username;

    @Value("${mqtt.password}")
    private String password;

    @Value("${mqtt.url}")
    private String url;

    @Value("${mqtt.receiver.clientId}")
    private String clientId;

    @Value("${mqtt.receiver.defaultTopic}")
    private String defaultTopic;


    /**
     * MQTT连接器选项
     */
    @Bean
    public MqttConnectOptions getReceiverMqttConnectOptions(){
        MqttConnectOptions options = new MqttConnectOptions();
        // 设置连接的用户名
        if(!username.trim().equals("")){
            options.setUserName(username);
        }
        // 设置连接的密码
        options.setPassword(password.toCharArray());
        // 设置连接的地址
        options.setServerURIs(StringUtils.split(url, ","));
        // 设置超时时间 单位为秒
        options.setConnectionTimeout(10);
        // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送心跳判断客户端是否在线
        // 但这个方法并没有重连的机制
        options.setKeepAliveInterval(20);
        return options;
    }

    /**
     * MQTT客户端
     */
    @Bean
    public MqttPahoClientFactory receiverMqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getReceiverMqttConnectOptions());
        return factory;
    }

    /**
     * MQTT信息通道（消费者）
     */
    @Bean(name = CHANNEL_NAME_IN)
    public MessageChannel mqttInboundChannel() {
        return new DirectChannel();
    }


    /**
     * MQTT消息订阅绑定（消费者）
     */
    @Bean
    public MessageProducer inbound() {
//        defaultTopic = sensorUtil.getThemes("111.229.163.181", "1883");
//        System.out.println(defaultTopic);
        // 可以同时消费（订阅）多个Topic
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(
                        clientId, receiverMqttClientFactory(),
                        StringUtils.split(defaultTopic, ","));
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        // 设置订阅通道
        adapter.setOutputChannel(mqttInboundChannel());

        return adapter;
    }

    /**
     * MQTT消息处理器（消费者）
     */
    @Bean
    @ServiceActivator(inputChannel = CHANNEL_NAME_IN)
    public MessageHandler handler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                String topic = message.getHeaders().get("mqtt_receivedTopic").toString();
                String msg = message.getPayload().toString();
                JsonData jd = (JsonData) JSON.parseObject(msg, JsonData.class);
                System.out.println(jd.toString());
                byte[] bytes = Base64.getDecoder().decode(jd.getData());

                System.out.println(printHexBinary(bytes));


                if(topic.startsWith("application/6/device")){
                    /**
                     * 气象站数据处理
                     */
                    WeatherStation weatherStation = weatherStationUtil.setBytes(bytes);
                    if(weatherStation != null){
                        weatherStation.setSe_id(sensorUtil.getSid("application/6/device/+/rx"));
                        weatherStationService.addMsg(weatherStation);
                    }
                }else if(topic.startsWith("application/3/device")){
                    /**
                     * 水质终端数据处理
                     */
                    WaterQuality waterQuality = waterQualityUtil.setBytes(bytes);
                    if(waterQuality != null){
                        waterQuality.setSe_id(sensorUtil.getSid("application/3/device/+/rx"));
                        waterQualityService.addMsg(waterQuality);
                    }
                }else if(topic.startsWith("application/1/device/2cf7f12212100030")){
                    /**
                     * 实验室温湿度数据处理
                     */
                    TempHum tempHum = tempHumUtil.setBytes(bytes);
                    if(tempHum != null){
//                        System.out.println("温度::"+tempHum.getTh_temp());
//                        System.out.println("湿度::"+tempHum.getTh_hum());
                        tempHum.setSe_id(sensorUtil.getSid("application/1/device/2cf7f12212100030/rx"));
                        tempHumService.addMsg(tempHum);
                    }
                }else if(topic.startsWith("application/5/device/8d0000010200000d")){
                    System.out.println(bytes);
                    String s = Base64.getEncoder().encodeToString(bytes);
//                    System.out.println("s::::"+s);
                    equipUtil.setBytes(bytes);
                }else if(topic.startsWith("application/1/device/2cf7f1221210007e")){
                    Illu illu = illuUtil.setBytes(bytes);
                    if(illu!=null){
//                        System.out.println("光照::"+illu.getIllu());
                        illu.setSe_id(sensorUtil.getSid("application/1/device/2cf7f1221210007e/rx"));
                        illuService.addMsg(illu);
                    }
                }
                System.out.println("\n--------------------START-------------------\n" +
                        "接收到订阅消息:\ntopic:" + topic + "\nmessage:" + msg +
                        "\n---------------------END--------------------");
            }
        };
    }
}