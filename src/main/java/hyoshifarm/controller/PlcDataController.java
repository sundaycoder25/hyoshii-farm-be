package hyoshifarm.controller;

import hyoshifarm.services.PlcDataService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/plc-data")
public class PlcDataController {

    private PlcDataService plcDataService;

//    @GetMapping("/productivity")
//    public ResponseEntity<Map<Integer, BigDecimal>> getProductivity(
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
//        return ResponseEntity.ok(plcDataService.calculateProductivityPerPic(date));
//    }
//
//    @GetMapping("/pack-ratios")
//    public ResponseEntity<Map<String, BigDecimal>> getPackRatios(
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
//        return ResponseEntity.ok(plcDataService.calculatePackRatios(date));
//    }
}
