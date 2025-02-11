package hyoshifarm.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import hyoshifarm.dto.PlcDataDto;
import hyoshifarm.dto.PlcMonitoringData;
import hyoshifarm.entity.PlcData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Service
public class MqttMessageHandler {
    private static final Logger logger = LoggerFactory.getLogger(MqttMessageHandler.class);
    private static final String MONITORING_TOPIC = "/topic/plc-monitoring";

    @Autowired
    private PlcDataService plcDataService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void handleMessage(String topic, String payload) {
        try {
            // 1. Parse MQTT message
            PlcDataDto dto = objectMapper.readValue(payload, PlcDataDto.class);

            // 2. Convert and save to database
            PlcData plcData = convertToEntity(dto);
            plcDataService.savePlcData(plcData);

            // 3. Broadcast original MQTT payload to WebSocket clients
            messagingTemplate.convertAndSend(MONITORING_TOPIC, payload);

            logger.info("Successfully processed and broadcast PLC data for PIC ID: {} from topic: {}",
                    dto.getID().get(0), topic);
        } catch (Exception e) {
            logger.error("Error processing MQTT message from topic {}: {}",
                    topic, e.getMessage(), e);
        }
    }

    private PlcData convertToEntity(PlcDataDto dto) {
        PlcData entity = new PlcData();
        ZoneId jakartaZone = ZoneId.of("Asia/Jakarta");

        // Parse timestamp dan tambahkan zone info
        LocalDateTime localDateTime = LocalDateTime.parse(dto.getTs());
        OffsetDateTime offsetDateTime = localDateTime.atZone(jakartaZone).toOffsetDateTime();

        entity.setTimestamp(offsetDateTime);
        entity.setCreatedAt(OffsetDateTime.now(jakartaZone));

        // Updated getter method names to match the new field names
        entity.setPicId(dto.getID().get(0));
        entity.setPackA(dto.getPackA().get(0));
        entity.setPackB(dto.getPackB().get(0));
        entity.setPackC(dto.getPackC().get(0));
        entity.setGrossWeight(BigDecimal.valueOf(dto.getGrossWeight().get(0)));
        entity.setRejectWeight(BigDecimal.valueOf(dto.getRejectWeight().get(0)));

        return entity;
    }
}