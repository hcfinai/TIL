package com.likelion.pbl.repository;

import com.likelion.pbl.domain.role.Role;

import java.util.List;

public interface MemberRepository {

    void save(Role role);

    Role findByName(String name);

    List<Role> findAll();

    void updateByName(String name, Role member);

    boolean deleteByName(String name);

    boolean existsByName(String name);
}
