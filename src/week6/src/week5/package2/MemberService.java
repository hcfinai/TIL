package week5.package2;

import week5.role.Role;

import java.util.List;

public class MemberService {

    // Step 2: Repository를 직접 생성하지 않고 외부에서 주입받음
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public boolean register(Role role) {
        if (repository.existsByName(role.getName())) {
            return false;
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