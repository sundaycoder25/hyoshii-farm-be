package hyoshifarm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class PlcDataDto {
    @JsonProperty("ID")
    private List<Integer> ID;

    @JsonProperty("Pack A")
    private List<Integer> PackA;

    @JsonProperty("Pack B")
    private List<Integer> PackB;

    @JsonProperty("Pack C")
    private List<Integer> PackC;

    @JsonProperty("Gross Weight")
    private List<Double> GrossWeight;

    @JsonProperty("Reject Weight")
    private List<Double> RejectWeight;

    private String ts;

    // Getters and Setters
    public List<Integer> getID() {
        return ID;
    }

    public void setID(List<Integer> ID) {
        this.ID = ID;
    }

    public List<Integer> getPackA() {
        return PackA;
    }

    public void setPackA(List<Integer> PackA) {
        this.PackA = PackA;
    }

    public List<Integer> getPackB() {
        return PackB;
    }

    public void setPackB(List<Integer> PackB) {
        this.PackB = PackB;
    }

    public List<Integer> getPackC() {
        return PackC;
    }

    public void setPackC(List<Integer> PackC) {
        this.PackC = PackC;
    }

    public List<Double> getGrossWeight() {
        return GrossWeight;
    }

    public void setGrossWeight(List<Double> GrossWeight) {
        this.GrossWeight = GrossWeight;
    }

    public List<Double> getRejectWeight() {
        return RejectWeight;
    }

    public void setRejectWeight(List<Double> RejectWeight) {
        this.RejectWeight = RejectWeight;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }
}