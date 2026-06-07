package com.likelion.pbl.assignment.service;

import com.likelion.pbl.assignment.domain.Assignment;
import com.likelion.pbl.assignment.dto.*;
import com.likelion.pbl.assignment.repository.AssignmentRepository;
import com.likelion.pbl.member.domain.Member;
import com.likelion.pbl.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final MemberRepository memberRepository;

    public AssignmentService(AssignmentRepository assignmentRepository,
                             MemberRepository memberRepository) {
        this.assignmentRepository = assignmentRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public AssignmentResponse create(Long memberId, AssignmentCreateRequest request) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member == null) return null;
        Assignment assignment = new Assignment(request.getTitle(), request.getDescription(), member);
        return AssignmentResponse.from(assignmentRepository.save(assignment));
    }

    public List<AssignmentResponse> findByMemberId(Long memberId) {
        return assignmentRepository.findByMemberId(memberId).stream()
                .map(AssignmentResponse::from)
                .collect(Collectors.toList());
    }

    public AssignmentResponse findById(Long id) {
        Assignment assignment = assignmentRepository.findById(id).orElse(null);
        if (assignment == null) return null;
        return AssignmentResponse.from(assignment);
    }

    @Transactional
    public AssignmentResponse update(Long id, AssignmentUpdateRequest request) {
        Assignment assignment = assignmentRepository.findById(id).orElse(null);
        if (assignment == null) return null;
        assignment.updateInfo(request.getTitle(), request.getDescription());
        return AssignmentResponse.from(assignmentRepository.save(assignment));
    }

    @Transactional
    public boolean delete(Long id) {
        if (!assignmentRepository.existsById(id)) return false;
        assignmentRepository.deleteById(id);
        return true;
    }
}
