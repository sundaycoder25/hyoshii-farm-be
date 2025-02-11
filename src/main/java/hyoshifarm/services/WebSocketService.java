//package hyoshifarm.services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class WebSocketService {
//
//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;
//
//    public void broadcastPlcData(Object data) {
//        messagingTemplate.convertAndSend("/topic/plc-data", data);
//    }
//}