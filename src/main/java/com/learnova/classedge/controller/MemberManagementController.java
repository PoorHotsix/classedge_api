package com.learnova.classedge.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learnova.classedge.dto.MemberDto;
import com.learnova.classedge.service.MemberManagementService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class MemberManagementController {
private final MemberManagementService memberManagementService;

    @PostMapping("/members")
    // @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<String> activateMember(@RequestBody MemberDto memberDto, @RequestParam boolean activate){
        memberManagementService.ActivateMember(memberDto, activate);
        return new ResponseEntity<>(memberDto.getId(), HttpStatus.OK);
    }
}
