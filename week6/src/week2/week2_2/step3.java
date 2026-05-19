package week2.week2_2;

import week2.week2_1.Lion;

public class step3 {

    public static void main(String[] args) {
        System.out.println("아기사자 객체를 생성합니다.");
        Lion lion = new Lion("이현철", "Finance&Ai", 14);
        System.out.println("아기사자 정보를 출력합니다.");
        lion.printInfo();
        //public 필드: 다른 패키지에서도 접근 및 수정 가능
        System.out.println("step3-1. public 필드 접근을 시도합니다.");
        System.out.println("name 필드값을 변경합니다.");
        lion.name = "철현이";
        System.out.println("public 필드 접근 성공");
        System.out.println("아기사자 정보를 출력합니다.");
        lion.printInfo();
        //default 필드: 다른 패키지에서 접근 불가 → 컴파일 오류 발생
        //System.out.println("step3-2. default 필드 접근을 시도합니다.");
        //lion.major = "컴퓨터공학";

        //private 필드: 클래스 외부에서 접근 불가 → 컴파일 오류 발생
        //System.out.println("step3-3. private 필드 접근을 시도합니다.");
        //lion.generation = 13;

    }
}
