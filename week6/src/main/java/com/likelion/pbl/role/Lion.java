package com.likelion.pbl.role;

import com.likelion.pbl.policy.LionPolicy;
import com.likelion.pbl.policy.SubmissionPolicy;

public class Lion extends Role {
    private String studentId;

    public Lion(String name, String major, int memberCount, String part, String studentId) {
        super(name, major, memberCount, part);
        this.studentId = studentId;
    }

    public String getStudentId() { return studentId; }

    @Override
    public SubmissionPolicy getPolicy() {
        return new LionPolicy();
    }

    @Override
    public String getInfo() {
        return "🦁 역할: 아기사자\n" +
                "👤 이름: " + getName() + " | 🏷 전공: " + getMajor() +
                " | 🚀 기수: " + getMemberCount() + " | 🖥 파트: " + getPart() + "\n" +
                "🆔 학번: " + studentId;
    }
}
