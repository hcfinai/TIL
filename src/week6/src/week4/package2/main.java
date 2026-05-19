package week4.package2;

import week4.role.Lion;
import week4.role.Role;
import week4.role.Staff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class main {

    private static List<Role> members = new ArrayList<>();
    private static Map<String, List<Role>> partMap = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1": registerMember(); break;
                case "2": showAllMembers(); break;
                case "3": searchByName(); break;
                case "4": showByPart(); break;
                case "5":
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("올바른 번호를 선택해주세요.");
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("======== 🦁 멤버 관리 시스템 ========");
        System.out.println("1. 멤버 등록");
        System.out.println("2. 전체 멤버 조회");
        System.out.println("3. 이름으로 검색");
        System.out.println("4. 파트별 조회");
        System.out.println("5. 종료");
        System.out.print("선택: ");
    }

    private static void registerMember() {
        System.out.println();
        System.out.println("—— 📝 멤버 등록 ——");
        System.out.print("역할 선택 (1: 아기사자, 2: 운영진): ");
        String roleChoice = scanner.nextLine().trim();

        System.out.print("👤 이름: ");
        String name = scanner.nextLine().trim();

        // 중복 이름 체크
        for (Role m : members) {
            if (m.getName().equals(name)) {
                System.out.println("❌ 등록 실패: 이미 존재하는 이름입니다.");
                return;
            }
        }

        System.out.print("🎓 전공: ");
        String major = scanner.nextLine().trim();
        System.out.print("🚀 기수: ");
        int memberCount = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("💼 파트 (백엔드/프론트엔드/기획/디자인): ");
        String part = scanner.nextLine().trim();

        Role newMember;

        if (roleChoice.equals("1")) {
            System.out.print("🪪 학번: ");
            String studentId = scanner.nextLine().trim();
            newMember = new Lion(name, major, memberCount, part, studentId);

        } else if (roleChoice.equals("2")) {
            System.out.print("⭐ 직책: ");
            String position = scanner.nextLine().trim();
            newMember = new Staff(name, major, memberCount, part, position);

        } else {
            System.out.println("올바른 역할을 선택해주세요.");
            return;
        }

        // List에 추가
        members.add(newMember);

        // Map에도 추가 (파트 기준)
        partMap.computeIfAbsent(part, k -> new ArrayList<>()).add(newMember);

        System.out.println("✅ 등록 완료: " + name);
    }

    private static void showAllMembers() {
        System.out.println();
        System.out.println("—— 📋 전체 멤버 목록 ——");

        if (members.isEmpty()) {
            System.out.println("등록된 멤버가 없습니다.");
            return;
        }

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
        System.out.println("—— 🔍 이름으로 검색 ——");
        System.out.print("검색할 이름: ");
        String name = scanner.nextLine().trim();

        for (Role member : members) {
            if (member.getName().equals(name)) {
                System.out.println();
                System.out.println("✨ [검색 결과]");
                System.out.println(member.getInfo());
                System.out.println("📋 과제 제출 가능 여부: "
                        + (member.canSubmit() ? "✅ 가능" : "❌ 불가"));
                return;
            }
        }

        System.out.println("해당 이름의 멤버를 찾을 수 없습니다.");
    }

    private static void showByPart() {
        System.out.println();
        System.out.println("—— 💼 파트별 조회 ——");

        if (partMap.isEmpty()) {
            System.out.println("등록된 파트가 없습니다.");
            return;
        }

        System.out.println("📁 등록된 파트: " + partMap.keySet());
        System.out.print("조회할 파트: ");
        String part = scanner.nextLine().trim();

        List<Role> partMembers = partMap.get(part);

        if (partMembers == null || partMembers.isEmpty()) {
            System.out.println("해당 파트에 멤버가 없습니다.");
            return;
        }

        System.out.println();
        System.out.println("✨ [" + part + " 파트 멤버]");
        for (int i = 0; i < partMembers.size(); i++) {
            Role r = partMembers.get(i);
            String roleType = (r instanceof Lion) ? "아기사자" : "운영진";
            System.out.println((i + 1) + ". " + r.getName()
                    + " (" + roleType + ") - " + r.getMemberCount() + "기");
        }
    }
}
