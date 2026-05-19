package week3.class3.role;

import week3.class3.policy.SubmissionPolicy;

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

    // 다형성: 하위 클래스가 자신에 맞는 정책 객체 반환
    public abstract SubmissionPolicy getPolicy();

    // 다형성: 하위 클래스가 자신의 정보를 문자열로 반환
    public abstract String getInfo();

    // 정책 객체에 위임
    public boolean canSubmit() {
        return getPolicy().canSubmit();
    }
}