package hyoshifarm.services;

import hyoshifarm.dto.PlcMonitoringData;
import hyoshifarm.dto.PlcMonitoringData.PicMetrics;
import hyoshifarm.dto.PlcMonitoringData.HourlyData;
import hyoshifarm.entity.PlcData;
import hyoshifarm.repository.PlcDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class PlcDataService {

    @Autowired
    private PlcDataRepository plcDataRepository;

    public PlcMonitoringData getMonitoringData() {
        List<PlcData> recentData = plcDataRepository.findTop10ByOrderByTimestampDesc();
        PlcMonitoringData monitoringData = new PlcMonitoringData();

        // Calculate PIC metrics
        Map<Integer, PicMetrics> picMetrics = new HashMap<>();
        for (PlcData data : recentData) {
            if (!picMetrics.containsKey(data.getPicId())) {
                PicMetrics metrics = new PicMetrics();
                metrics.setTotalPackA(0);
                metrics.setTotalPackB(0);
                metrics.setTotalPackC(0);
                metrics.setTotalGrossWeight(0);
                metrics.setTotalRejectWeight(0);
                picMetrics.put(data.getPicId(), metrics);
            }

            PicMetrics metrics = picMetrics.get(data.getPicId());
            metrics.setTotalPackA(metrics.getTotalPackA() + data.getPackA());
            metrics.setTotalPackB(metrics.getTotalPackB() + data.getPackB());
            metrics.setTotalPackC(metrics.getTotalPackC() + data.getPackC());
            metrics.setTotalGrossWeight(metrics.getTotalGrossWeight() + data.getGrossWeight().doubleValue());
            metrics.setTotalRejectWeight(metrics.getTotalRejectWeight() + data.getRejectWeight().doubleValue());

        }
        monitoringData.setPicMetrics(picMetrics);

        // Create hourly data
        List<PlcData> chronologicalData = new ArrayList<>(recentData);
        Collections.reverse(chronologicalData);

        List<HourlyData> hourlyDataList = new ArrayList<>();
        Map<String, Map<String, Integer>> timeBasedTotals = new LinkedHashMap<>();

        for (PlcData data : chronologicalData) {
            String timePoint = data.getTimestamp().format(DateTimeFormatter.ofPattern("HH:mm"));
            if (!timeBasedTotals.containsKey(timePoint)) {
                timeBasedTotals.put(timePoint, new HashMap<>());
            }

            int total = data.getPackA() + data.getPackB() + data.getPackC();
            timeBasedTotals.get(timePoint).put("PIC " + data.getPicId(), total);
        }

        for (Map.Entry<String, Map<String, Integer>> entry : timeBasedTotals.entrySet()) {
            HourlyData hourData = new HourlyData();
            hourData.setHour(entry.getKey());
            hourData.setPicTotals(entry.getValue());
            hourlyDataList.add(hourData);
        }

        monitoringData.setHourlyData(hourlyDataList);
        return monitoringData;
    }

    public PlcData savePlcData(PlcData data) {
        return plcDataRepository.save(data);
    }

    public List<PlcData> getRecentHistory() {
        return plcDataRepository.findLast20Records();
    }
}