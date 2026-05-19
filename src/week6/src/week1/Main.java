import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = 0;

        while (true) {
            System.out.print("저장할 아기사자 수를 5이상 입력하세요: ");
            count = scanner.nextInt();
            scanner.nextLine();

            if (count >= 5) {
                break;
            } else {
                System.out.println("[오류]아기 사자 수는 5 이상이어야 합니다.");
            }
        }

        String[] lions = new String[count];
        System.out.println("아기사자 이름을 입력하세요: ");
        for (int i = 0; i < count; i++) {
            lions[i] = scanner.nextLine();
        }

        System.out.println("아기사자 명단을 최종적으로 출력합니다.");
        for (int i = 0; i < lions.length; i++) {
            System.out.println((i + 1) + ". " + lions[i]);
        }

        scanner.close();
    }
}