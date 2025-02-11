package hyoshifarm.controller;

import hyoshifarm.dto.PlcMonitoringData;
import hyoshifarm.services.PlcDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/monitoring")
public class MonitoringController {

    @Autowired
    private PlcDataService plcDataService;

    @GetMapping
    public ResponseEntity<PlcMonitoringData> getMonitoringData() {
        return ResponseEntity.ok(plcDataService.getMonitoringData());
    }
}