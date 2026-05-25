package com.likelion.pbl.domain.role;

public abstract class Role {

    protected String name;
    protected String major;
    protected int generation;
    protected String part;

    public Role(String name, String major, int generation, String part) {
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
    }

    public String getName() { return name; }
    public String getMajor() { return major; }
    public int getGeneration() { return generation; }
    public String getPart() { return part; }

    public abstract String getRoleName();
}
