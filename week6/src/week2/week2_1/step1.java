package week2.week2_1;

import java.util.Scanner;

public class step1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("아기사자 이름을 입력하세요: ");
        String name = sc.nextLine();

        System.out.print("전공을 입력하세요: ");
        String major = sc.nextLine();

        System.out.print("기수를 입력하세요: ");
        int generation = Integer.parseInt(sc.nextLine());

        sc.close();

        System.out.println("\n입력값 검증을 진행합니다.");

        if (name == null || name.isBlank()) {
            System.out.println("[오류] 이름은 비어 있을 수 없습니다.");
            return;
        }
        if (major == null || major.isBlank()) {
            System.out.println("[오류] 전공은 비어 있을 수 없습니다.");
            return;
        }
        if (generation < 1) {
            System.out.println("[오류] 기수는 1 이상이어야 합니다.");
            return;
        }

        System.out.println("입력값 검증을 통과하여 아기사자 객체 생성을 진행합니다.");
        Lion lion = new Lion(name, major, generation);
        System.out.println("아기사자 객체를 성공적으로 생성했습니다.");
        System.out.println("아기사자 정보를 출력합니다.");
        lion.printInfo();
    }
}
