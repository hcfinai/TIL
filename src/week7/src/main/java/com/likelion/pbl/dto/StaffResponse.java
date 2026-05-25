package com.likelion.pbl.dto;

import com.likelion.pbl.domain.role.Staff;

public class StaffResponse {

    private String name;
    private String major;
    private int generation;
    private String part;
    private String roleName;
    private String position;

    private StaffResponse(Staff staff) {
        this.name = staff.getName();
        this.major = staff.getMajor();
        this.generation = staff.getGeneration();
        this.part = staff.getPart();
        this.roleName = staff.getRoleName();
        this.position = staff.getPosition();
    }

    public static StaffResponse from(Staff staff) {
        return new StaffResponse(staff);
    }

    public String getName() { return name; }
    public String getMajor() { return major; }
    public int getGeneration() { return generation; }
    public String getPart() { return part; }
    public String getRoleName() { return roleName; }
    public String getPosition() { return position; }
}
