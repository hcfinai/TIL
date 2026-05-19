package week5.package2;

import week5.role.Lion;
import week5.role.Role;
import week5.role.Staff;

import java.util.List;
import java.util.Scanner;

public class main {

    private static MemberService service;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // 저장소 선택 (의존성 주입)
        System.out.println("저장소를 선택하세요:");
        System.out.println("1. MemoryMemberRepository (실제 저장)");
        System.out.println("2. MockMemberRepository (더미 데이터)");
        System.out.print("선택: ");
        String repoChoice = scanner.nextLine().trim();

        MemberRepository repository;
        if (repoChoice.equals("1")) {
            repository = new MemoryMemberRepository();
        } else {
            repository = new MockMemberRepository();
        }

        // Service에 Repository 주입
        service = new MemberService(repository);

        while (true) {
            printMenu();
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1": registerMember(); break;
                case "2": showAllMembers(); break;
                case "3": searchByName(); break;
                case "4":
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("올바른 번호를 선택해주세요.");
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("🦁 ===== 멋사 멤버 관리 시스템 (Step 2: DI 적용) ===== 🦁");
        System.out.println("1. ➕ 멤버 등록");
        System.out.println("2. 📋 전체 멤버 조회");
        System.out.println("3. 🔍 이름으로 검색");
        System.out.println("4. 📕 종료");
        System.out.print("선택: ");
    }

    private static void registerMember() {
        System.out.println();
        System.out.print("👤 역할 선택 (1: 아기사자, 2: 운영진): ");
        String roleChoice = scanner.nextLine().trim();

        System.out.println("📝 정보 입력");
        System.out.print("이름: ");
        String name = scanner.nextLine().trim();
        System.out.print("전공: ");
        String major = scanner.nextLine().trim();
        System.out.print("기수: ");
        int memberCount = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("파트: ");
        String part = scanner.nextLine().trim();

        Role newMember;

        if (roleChoice.equals("1")) {
            System.out.print("학번: ");
            String studentId = scanner.nextLine().trim();
            newMember = new Lion(name, major, memberCount, part, studentId);
        } else if (roleChoice.equals("2")) {
            System.out.print("직책: ");
            String position = scanner.nextLine().trim();
            newMember = new Staff(name, major, memberCount, part, position);
        } else {
            System.out.println("올바른 역할을 선택해주세요.");
            return;
        }

        if (service.register(newMember)) {
            System.out.println("✅ 등록 완료: " + name);
        } else {
            System.out.println("❌ 등록 실패: 이미 존재하는 이름입니다.");
        }
    }

    private static void showAllMembers() {
        System.out.println();
        List<Role> members = service.findAll();

        if (members.isEmpty()) {
            System.out.println("등록된 멤버가 없습니다.");
            return;
        }

        System.out.println("===== 전체 멤버 목록 =====");
        for (int i = 0; i < members.size(); i++) {
            Role r = members.get(i);
            String roleType = (r instanceof Lion) ? "아기사자" : "운영진";
            System.out.println((i + 1) + ". [" + roleType + "] "
                    + r.getName() + " - " + r.getMemberCount() + "기");
        }
        System.out.println("📊 총 " + members.size() + "명");
    }

    private static void searchByName() {
        System.out.println();
        System.out.print("🔍 검색할 이름: ");
        String name = scanner.nextLine().trim();

        Role member = service.findByName(name);

        if (member == null) {
            System.out.println("해당 이름의 멤버를 찾을 수 없습니다.");
            return;
        }

        System.out.println("===== 검색 결과 =====");
        System.out.println(member.getInfo());
        System.out.println("📋 과제 제출 가능 여부: "
                + (member.canSubmit() ? "✅ 가능" : "❌ 불가"));
    }
}