package com.likelion.pbl.role;

import com.likelion.pbl.policy.SubmissionPolicy;

public abstract class Role {
    private String name;
    private String major;
    private int memberCount;
    private String part;

    public Role(String name, String major, int memberCount, String part) {
        this.name = name;
        this.major = major;
        this.memberCount = memberCount;
        this.part = part;
    }

    public String getName() { return name; }
    public String getMajor() { return major; }
    public int getMemberCount() { return memberCount; }
    public String getPart() { return part; }

    public abstract SubmissionPolicy getPolicy();
    public abstract String getInfo();

    public boolean canSubmit() {
        return getPolicy().canSubmit();
    }
}
