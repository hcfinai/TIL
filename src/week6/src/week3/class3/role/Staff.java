package week3.class3.role;

import week3.class3.policy.StaffPolicy;
import week3.class3.policy.SubmissionPolicy;

public class Staff extends Role {
    private String position;

    public Staff(String name, String major, int memberCount, String part, String position) {
        super(name, major, memberCount, part);
        this.position = position;
    }

    public String getPosition() { return position; }

    @Override
    public SubmissionPolicy getPolicy() {
        return new StaffPolicy();
    }

    @Override
    public String getInfo() {
        return "🦁 역할: 운영진\n" +
                "👤 이름: " + getName() + " | 🏷 전공: " + getMajor() +
                " | 🚀 기수: " + getMemberCount() + " | 🖥 파트: " + getPart() + "\n" +
                "⭐ 직책: " + position;
    }
}
