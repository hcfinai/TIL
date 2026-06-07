package com.likelion.pbl.assignment.controller;

import com.likelion.pbl.assignment.dto.*;
import com.likelion.pbl.assignment.service.AssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    // 과제 등록
    @PostMapping("/members/{memberId}/assignments")
    public ResponseEntity<AssignmentResponse> create(@PathVariable Long memberId,
                                                      @RequestBody AssignmentCreateRequest request) {
        AssignmentResponse response = assignmentService.create(memberId, request);
        if (response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 멤버별 과제 목록 조회
    @GetMapping("/members/{memberId}/assignments")
    public ResponseEntity<List<AssignmentResponse>> findByMemberId(@PathVariable Long memberId) {
        return ResponseEntity.ok(assignmentService.findByMemberId(memberId));
    }

    // 과제 단건 조회
    @GetMapping("/assignments/{id}")
    public ResponseEntity<AssignmentResponse> findById(@PathVariable Long id) {
        AssignmentResponse response = assignmentService.findById(id);
        if (response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(response);
    }

    // 과제 수정
    @PutMapping("/assignments/{id}")
    public ResponseEntity<AssignmentResponse> update(@PathVariable Long id,
                                                      @RequestBody AssignmentUpdateRequest request) {
        AssignmentResponse response = assignmentService.update(id, request);
        if (response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(response);
    }

    // 과제 삭제
    @DeleteMapping("/assignments/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!assignmentService.delete(id)) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.noContent().build();
    }
}
