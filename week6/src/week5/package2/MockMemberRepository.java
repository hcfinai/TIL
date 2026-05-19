package week5.package2;

import week5.role.Lion;
import week5.role.Role;

import java.util.ArrayList;
import java.util.List;

public class MockMemberRepository implements MemberRepository {

    // 미리 정의된 더미 데이터
    private List<Role> mockData = new ArrayList<>(List.of(
            new Lion("김사자", "컴퓨터공학과", 14, "백엔드", "202020202"),
            new Lion("이사자", "전자공학과", 13, "프론트엔드", "202020303")
    ));

    @Override
    public void save(Role role) {
        // Mock이라 실제 저장 안 함
        System.out.println("[Mock] save 호출됨 (실제 저장 안 됨)");
    }

    @Override
    public Role findByName(String name) {
        for (Role member : mockData) {
            if (member.getName().equals(name)) {
                return member;
            }
        }
        return null;
    }

    @Override
    public List<Role> findAll() {
        return mockData;
    }

    @Override
    public boolean existsByName(String name) {
        return findByName(name) != null;
    }
}