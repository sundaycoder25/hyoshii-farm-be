package hyoshifarm.dto;

import java.util.List;
import java.util.Map;

public class PlcMonitoringData {
    private Map<Integer, PicMetrics> picMetrics;
    private List<HourlyData> hourlyData;

    public static class PicMetrics {
        private int totalPackA;
        private int totalPackB;
        private int totalPackC;
        private double totalGrossWeight;
        private double totalRejectWeight;

        // Getters and Setters
        public int getTotalPackA() { return totalPackA; }
        public void setTotalPackA(int totalPackA) { this.totalPackA = totalPackA; }

        public int getTotalPackB() { return totalPackB; }
        public void setTotalPackB(int totalPackB) { this.totalPackB = totalPackB; }

        public int getTotalPackC() { return totalPackC; }
        public void setTotalPackC(int totalPackC) { this.totalPackC = totalPackC; }

        public double getTotalGrossWeight() { return totalGrossWeight; }
        public void setTotalGrossWeight(double totalGrossWeight) { this.totalGrossWeight = totalGrossWeight; }

        public double getTotalRejectWeight() { return totalRejectWeight; }
        public void setTotalRejectWeight(double totalRejectWeight) { this.totalRejectWeight = totalRejectWeight; }

    }

    public static class HourlyData {
        private String hour;
        private Map<String, Integer> picTotals;

        // Getters and Setters
        public String getHour() { return hour; }
        public void setHour(String hour) { this.hour = hour; }

        public Map<String, Integer> getPicTotals() { return picTotals; }
        public void setPicTotals(Map<String, Integer> picTotals) { this.picTotals = picTotals; }
    }

    // Getters and Setters for main class
    public Map<Integer, PicMetrics> getPicMetrics() { return picMetrics; }
    public void setPicMetrics(Map<Integer, PicMetrics> picMetrics) { this.picMetrics = picMetrics; }

    public List<HourlyData> getHourlyData() { return hourlyData; }
    public void setHourlyData(List<HourlyData> hourlyData) { this.hourlyData = hourlyData; }
}