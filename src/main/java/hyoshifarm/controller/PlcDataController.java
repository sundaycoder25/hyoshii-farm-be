package hyoshifarm.controller;

import hyoshifarm.entity.PlcData;
import hyoshifarm.services.PlcDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/plc-data")
public class PlcDataController {

    @Autowired
    private PlcDataService plcDataService;

    @GetMapping("/history")
    public ResponseEntity<List<PlcData>> getHistory() {
        return ResponseEntity.ok(plcDataService.getRecentHistory());
    }
}