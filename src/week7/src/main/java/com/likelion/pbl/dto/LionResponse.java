package com.likelion.pbl.dto;

import com.likelion.pbl.domain.role.Lion;

public class LionResponse {

    private String name;
    private String major;
    private int generation;
    private String part;
    private String roleName;
    private String studentId;

    private LionResponse(Lion lion) {
        this.name = lion.getName();
        this.major = lion.getMajor();
        this.generation = lion.getGeneration();
        this.part = lion.getPart();
        this.roleName = lion.getRoleName();
        this.studentId = lion.getStudentId();
    }

    public static LionResponse from(Lion lion) {
        return new LionResponse(lion);
    }

    public String getName() { return name; }
    public String getMajor() { return major; }
    public int getGeneration() { return generation; }
    public String getPart() { return part; }
    public String getRoleName() { return roleName; }
    public String getStudentId() { return studentId; }
}
