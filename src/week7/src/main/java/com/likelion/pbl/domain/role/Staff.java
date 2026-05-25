package com.likelion.pbl.domain.role;

public class Staff extends Role {

    private String position;

    public Staff(String name, String major, int generation, String part, String position) {
        super(name, major, generation, part);
        this.position = position;
    }

    public String getPosition() { return position; }

    public void setMajor(String major) { this.major = major; }
    public void setGeneration(int generation) { this.generation = generation; }
    public void setPart(String part) { this.part = part; }
    public void setPosition(String position) { this.position = position; }

    @Override
    public String getRoleName() { return "운영진"; }
}
