package hyoshifarm.config;

import hyoshifarm.services.MqttMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class MqttConfig {
    private static final Logger logger = LoggerFactory.getLogger(MqttConfig.class);

    @Value("${mqtt.broker.url}")
    private String brokerUrl;

    @Value("${mqtt.client.id}")
    private String clientId;

    @Value("${mqtt.topics}")
    private String[] topics;

    @Autowired
    private MqttMessageHandler messageHandler;

    private MqttClient mqttClient;

    @Bean
    public MqttClient mqttClient() throws MqttException {
        mqttClient = new MqttClient(brokerUrl, clientId);
        connectToMqtt();
        return mqttClient;
    }

    private void connectToMqtt() {
        try {
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(30);
            options.setKeepAliveInterval(60);

            mqttClient.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    logger.error("Connection to MQTT broker lost: {}", cause.getMessage());
                    // Try to reconnect
                    tryReconnect();
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) {
                    String payload = new String(message.getPayload());
                    messageHandler.handleMessage(topic, payload);
                    logger.debug("Received message from topic: {}", topic);
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    // Not used for subscriber
                }
            });

            mqttClient.connect(options);

            // Subscribe to topics after connection
            for (String topic : topics) {
                mqttClient.subscribe(topic);
                logger.info("Subscribed to topic: {}", topic);
            }

        } catch (MqttException e) {
            logger.error("Error connecting to MQTT broker: {}", e.getMessage());
        }
    }

    private void tryReconnect() {
        try {
            if (!mqttClient.isConnected()) {
                logger.info("Attempting to reconnect to MQTT broker...");
                connectToMqtt();
            }
        } catch (Exception e) {
            logger.error("Error during reconnection attempt: {}", e.getMessage());
        }
    }

    // Scheduled task to check connection every minute
    @Scheduled(fixedRate = 60000)
    public void checkConnection() {
        if (mqttClient != null && !mqttClient.isConnected()) {
            logger.info("Connection check: MQTT client disconnected. Attempting to reconnect...");
            tryReconnect();
        }
    }
}