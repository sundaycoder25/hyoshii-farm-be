package hyoshifarm.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "plc_data")
public class PlcData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer id;


    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP")
    private OffsetDateTime createdAt;

    @Column(name = "timestamp", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime timestamp;

    @Column(name = "pic_id")
    private Integer picId;

    @Column(name = "gross_weight", precision = 10, scale = 3)
    private BigDecimal grossWeight;

    @Column(name = "pack_a")
    private Integer packA;

    @Column(name = "pack_b")
    private Integer packB;

    @Column(name = "pack_c")
    private Integer packC;

    @Column(name = "reject_weight", precision = 10, scale = 3)
    private BigDecimal rejectWeight;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getPicId() {
        return picId;
    }

    public void setPicId(Integer picId) {
        this.picId = picId;
    }

    public BigDecimal getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(BigDecimal grossWeight) {
        this.grossWeight = grossWeight;
    }

    public Integer getPackA() {
        return packA;
    }

    public void setPackA(Integer packA) {
        this.packA = packA;
    }

    public Integer getPackB() {
        return packB;
    }

    public void setPackB(Integer packB) {
        this.packB = packB;
    }

    public Integer getPackC() {
        return packC;
    }

    public void setPackC(Integer packC) {
        this.packC = packC;
    }

    public BigDecimal getRejectWeight() {
        return rejectWeight;
    }

    public void setRejectWeight(BigDecimal rejectWeight) {
        this.rejectWeight = rejectWeight;
    }
}