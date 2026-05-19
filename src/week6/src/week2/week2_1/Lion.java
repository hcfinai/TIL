package week2.week2_1;

public class Lion {
    public String name;
    String major;
    private int generation;
    public Lion(String name, String major, int generation) {
        this.name = name;
        this.major = major;
        this.generation = generation;
    }
    public boolean isValid() {
        if (name == null || name.isBlank()) {
            System.out.println("[오류] 이름은 비어 있을 수 없습니다.");
            return false;
        }
        if (major == null || major.isBlank()) {
            System.out.println("[오류] 전공은 비어 있을 수 없습니다.");
            return false;
        }
        if (generation < 1) {
            System.out.println("[오류] 기수는 1 이상이어야 합니다.");
            return false;
        }
        return true;
    }
    public void printInfo() {
        System.out.println("이름 : " + name);
        System.out.println("전공 : " + major);
        System.out.println("기수 : " + generation);
    }
}
