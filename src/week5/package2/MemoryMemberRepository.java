package week5.package2;

import week5.role.Role;

import java.util.ArrayList;
import java.util.List;

public class MemoryMemberRepository implements MemberRepository {

    private List<Role> members = new ArrayList<>();

    @Override
    public void save(Role role) {
        members.add(role);
    }

    @Override
    public Role findByName(String name) {
        for (Role member : members) {
            if (member.getName().equals(name)) {
                return member;
            }
        }
        return null;
    }

    @Override
    public List<Role> findAll() {
        return members;
    }

    @Override
    public boolean existsByName(String name) {
        return findByName(name) != null;
    }
}
