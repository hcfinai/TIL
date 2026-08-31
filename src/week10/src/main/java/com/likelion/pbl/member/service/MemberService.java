package com.likelion.pbl.member.service;

import com.likelion.pbl.global.exception.DuplicateMemberNameException;
import com.likelion.pbl.global.exception.MemberNotFoundException;
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
            throw new DuplicateMemberNameException("이미 존재하는 이름입니다: " + request.getName());
        }
        Member member = new Member(
                request.getName(), request.getMajor(), request.getGeneration(),
                request.getPart(), RoleType.LION, request.getStudentId(), null);
        return MemberResponse.from(memberRepository.save(member));
    }

    @Transactional
    public MemberResponse createStaff(StaffCreateRequest request) {
        if (memberRepository.existsByName(request.getName())) {
            throw new DuplicateMemberNameException("이미 존재하는 이름입니다: " + request.getName());
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

    public List<MemberResponse> findByPart(String part) {
        return memberRepository.findByPart(part).stream()
                .map(MemberResponse::from)
                .collect(Collectors.toList());
    }

    public MemberResponse findById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("멤버를 찾을 수 없습니다. id=" + id));
        return MemberResponse.from(member);
    }

    @Transactional
    public MemberResponse updateLion(Long id, LionUpdateRequest request) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("멤버를 찾을 수 없습니다. id=" + id));
        member.updateInfo(request.getMajor(), request.getGeneration(), request.getPart());
        member.updateStudentId(request.getStudentId());
        return MemberResponse.from(memberRepository.save(member));
    }

    @Transactional
    public MemberResponse updateStaff(Long id, StaffUpdateRequest request) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("멤버를 찾을 수 없습니다. id=" + id));
        member.updateInfo(request.getMajor(), request.getGeneration(), request.getPart());
        member.updatePosition(request.getPosition());
        return MemberResponse.from(memberRepository.save(member));
    }

    @Transactional
    public void deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new MemberNotFoundException("멤버를 찾을 수 없습니다. id=" + id);
        }
        memberRepository.deleteById(id);
    }
}
