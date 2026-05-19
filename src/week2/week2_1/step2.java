package week2.week2_1;

import java.util.Scanner;

public class step2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력 단계: 유효성 검증 없이 순차적으로 입력받기
        System.out.print("아기사자 이름을 입력하세요: ");
        String name = sc.nextLine();

        System.out.print("전공을 입력하세요: ");
        String major = sc.nextLine();

        System.out.print("기수를 입력하세요: ");
        int generation = Integer.parseInt(sc.nextLine());

        sc.close();

        // Step 2: 입력값과 관계없이 Lion 객체를 먼저 생성
        Lion lion = new Lion(name, major, generation);
        System.out.println("\n객체 생성이 완료되었습니다. 아기사자 객체의 상태를 확인합니다.");

        // 객체 생성 이후 객체 스스로 상태를 검증
        if (!lion.isValid()) {
            System.out.println("잘못된 아기사자 정보입니다.");
            return;
        }

        // 검증 통과 시 정보 출력
        System.out.println("아기사자 객체가 자신의 상태를 정상으로 판단했습니다.");
        System.out.println("아기사자 정보를 출력합니다.");
        lion.printInfo();
    }
}
