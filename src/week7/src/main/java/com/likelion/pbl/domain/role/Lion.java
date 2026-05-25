package com.likelion.pbl.domain.role;

public class Lion extends Role {

    private String studentId;

    public Lion(String name, String major, int generation, String part, String studentId) {
        super(name, major, generation, part);
        this.studentId = studentId;
    }

    public String getStudentId() { return studentId; }

    public void setMajor(String major) { this.major = major; }
    public void setGeneration(int generation) { this.generation = generation; }
    public void setPart(String part) { this.part = part; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    @Override
    public String getRoleName() { return "아기사자"; }
}
