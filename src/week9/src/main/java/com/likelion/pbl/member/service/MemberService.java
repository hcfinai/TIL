package com.likelion.pbl.member.service;

import com.likelion.pbl.member.domain.Member;
import com.likelion.pbl.member.domain.RoleType;
import com.likelion.pbl.member.dto.*;
import com.likelion.pbl.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public MemberResponse createLion(LionCreateRequest request) {
        if (memberRepository.existsByName(request.getName())) {
            return null;
        }
        Member member = new Member(
                request.getName(), request.getMajor(), request.getGeneration(),
                request.getPart(), RoleType.LION, request.getStudentId(), null);
        return MemberResponse.from(memberRepository.save(member));
    }

    @Transactional
    public MemberResponse createStaff(StaffCreateRequest request) {
        if (memberRepository.existsByName(request.getName())) {
            return null;
        }
        Member member = new Member(
                request.getName(), request.getMajor(), request.getGeneration(),
                request.getPart(), RoleType.STAFF, null, request.getPosition());
        return MemberResponse.from(memberRepository.save(member));
    }

    public List<MemberResponse> findAll() {
        return memberRepository.findAll().stream()
                .map(MemberResponse::from)
                .collect(Collectors.toList());
    }

    public MemberResponse findById(Long id) {
        Member member = memberRepository.findById(id).orElse(null);
        if (member == null) return null;
        return MemberResponse.from(member);
    }

    @Transactional
    public MemberResponse updateLion(Long id, LionUpdateRequest request) {
        Member member = memberRepository.findById(id).orElse(null);
        if (member == null) return null;
        member.updateInfo(request.getMajor(), request.getGeneration(), request.getPart());
        member.updateStudentId(request.getStudentId());
        return MemberResponse.from(memberRepository.save(member));
    }

    @Transactional
    public MemberResponse updateStaff(Long id, StaffUpdateRequest request) {
        Member member = memberRepository.findById(id).orElse(null);
        if (member == null) return null;
        member.updateInfo(request.getMajor(), request.getGeneration(), request.getPart());
        member.updatePosition(request.getPosition());
        return MemberResponse.from(memberRepository.save(member));
    }

    @Transactional
    public boolean deleteMember(Long id) {
        if (!memberRepository.existsById(id)) return false;
        memberRepository.deleteById(id);
        return true;
    }
}
