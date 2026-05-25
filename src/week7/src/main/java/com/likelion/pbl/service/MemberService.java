package com.likelion.pbl.service;

import com.likelion.pbl.domain.role.Lion;
import com.likelion.pbl.domain.role.Role;
import com.likelion.pbl.domain.role.Staff;
import com.likelion.pbl.dto.*;
import com.likelion.pbl.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 생성 메서드
    public LionResponse createLion(LionCreateRequest request) {
        if (memberRepository.existsByName(request.getName())) {
            return null;
        }
        Lion lion = new Lion(
                request.getName(),
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                request.getStudentId()
        );
        memberRepository.save(lion);
        return LionResponse.from(lion);
    }

    public StaffResponse createStaff(StaffCreateRequest request) {
        if (memberRepository.existsByName(request.getName())) {
            return null;
        }
        Staff staff = new Staff(
                request.getName(),
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                request.getPosition()
        );
        memberRepository.save(staff);
        return StaffResponse.from(staff);
    }

    // 조회 메서드
    public Role findByName(String name) {
        return memberRepository.findByName(name);
    }

    // 수정 메서드
    public LionResponse updateLion(String name, LionUpdateRequest request) {
        Role existing = memberRepository.findByName(name);
        if (existing == null || !(existing instanceof Lion)) {
            return null;
        }
        Lion lion = new Lion(
                name,
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                request.getStudentId()
        );
        memberRepository.updateByName(name, lion);
        return LionResponse.from(lion);
    }

    public StaffResponse updateStaff(String name, StaffUpdateRequest request) {
        Role existing = memberRepository.findByName(name);
        if (existing == null || !(existing instanceof Staff)) {
            return null;
        }
        Staff staff = new Staff(
                name,
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                request.getPosition()
        );
        memberRepository.updateByName(name, staff);
        return StaffResponse.from(staff);
    }

    // 삭제 메서드
    public boolean deleteMember(String name) {
        return memberRepository.deleteByName(name);
    }
}
