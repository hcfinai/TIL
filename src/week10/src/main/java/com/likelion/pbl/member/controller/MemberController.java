package com.likelion.pbl.member.controller;

import com.likelion.pbl.member.dto.*;
import com.likelion.pbl.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/lions")
    public ResponseEntity<MemberResponse> createLion(@RequestBody LionCreateRequest request) {
        MemberResponse response = memberService.createLion(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/staffs")
    public ResponseEntity<MemberResponse> createStaff(@RequestBody StaffCreateRequest request) {
        MemberResponse response = memberService.createStaff(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> findAll(@RequestParam(required = false) String part) {
        if (part != null) {
            return ResponseEntity.ok(memberService.findByPart(part));
        }
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> findById(@PathVariable Long id) {
        MemberResponse response = memberService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/lions/{id}")
    public ResponseEntity<MemberResponse> updateLion(@PathVariable Long id,
                                                      @RequestBody LionUpdateRequest request) {
        MemberResponse response = memberService.updateLion(id, request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/staffs/{id}")
    public ResponseEntity<MemberResponse> updateStaff(@PathVariable Long id,
                                                       @RequestBody StaffUpdateRequest request) {
        MemberResponse response = memberService.updateStaff(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}
