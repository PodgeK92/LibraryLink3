package com.example.librarylink3.LibraryLink3;


import jakarta.persistence.*;

@Entity
@Table(name = "Equipment")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long equipmentId;

    @Column(name = "time_slot", nullable = false)
    private String timeSlot;

    @Column(name = "status")
    private String status;

    @Column(name = "branch_id")
    private Long branchId;

    // Constructors
    public Equipment() {}

    public Equipment(String timeSlot, String status, Long branchId) {
        this.timeSlot = timeSlot;
        this.status = status;
        this.branchId = branchId;
    }

    // Getters and Setters
    public Long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }
}

