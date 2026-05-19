package week5.package1;

import week5.role.Role;

import java.util.List;

public class MemberService {

    // Step 1: Repository를 직접 생성
    private MemberRepository repository = new MemberRepository();

    public boolean register(Role role) {
        if (repository.existsByName(role.getName())) {
            return false; // 중복
        }
        repository.save(role);
        return true;
    }

    public List<Role> findAll() {
        return repository.findAll();
    }

    public Role findByName(String name) {
        return repository.findByName(name);
    }
}