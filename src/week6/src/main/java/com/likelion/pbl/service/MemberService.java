package com.likelion.pbl.service;

import com.likelion.pbl.repository.MemberRepository;
import com.likelion.pbl.role.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    // 생성자 주입 (생성자 1개이므로 @Autowired 생략 가능)
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

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
