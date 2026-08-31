package com.likelion.pbl.assignment.service;

import com.likelion.pbl.assignment.domain.Assignment;
import com.likelion.pbl.assignment.dto.*;
import com.likelion.pbl.assignment.repository.AssignmentRepository;
import com.likelion.pbl.global.exception.AssignmentNotFoundException;
import com.likelion.pbl.global.exception.MemberNotFoundException;
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
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("멤버를 찾을 수 없습니다. id=" + memberId));
        Assignment assignment = new Assignment(request.getTitle(), request.getDescription(), member);
        return AssignmentResponse.from(assignmentRepository.save(assignment));
    }

    public List<AssignmentResponse> findByMemberId(Long memberId) {
        return assignmentRepository.findByMemberId(memberId).stream()
                .map(AssignmentResponse::from)
                .collect(Collectors.toList());
    }

    public List<AssignmentResponse> findAll() {
        return assignmentRepository.findAll().stream()
                .map(AssignmentResponse::from)
                .collect(Collectors.toList());
    }

    public List<AssignmentResponse> searchByTitle(String keyword) {
        return assignmentRepository.findByTitleContaining(keyword).stream()
                .map(AssignmentResponse::from)
                .collect(Collectors.toList());
    }

    public AssignmentResponse findById(Long id) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new AssignmentNotFoundException("과제를 찾을 수 없습니다. id=" + id));
        return AssignmentResponse.from(assignment);
    }

    @Transactional
    public AssignmentResponse update(Long id, AssignmentUpdateRequest request) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new AssignmentNotFoundException("과제를 찾을 수 없습니다. id=" + id));
        assignment.updateInfo(request.getTitle(), request.getDescription());
        return AssignmentResponse.from(assignmentRepository.save(assignment));
    }

    @Transactional
    public void delete(Long id) {
        if (!assignmentRepository.existsById(id)) {
            throw new AssignmentNotFoundException("과제를 찾을 수 없습니다. id=" + id);
        }
        assignmentRepository.deleteById(id);
    }
}
