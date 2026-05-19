package com.likelion.pbl.repository;

import com.likelion.pbl.role.Role;

import java.util.List;

public interface MemberRepository {
    void save(Role role);
    Role findByName(String name);
    List<Role> findAll();
    boolean existsByName(String name);
}
