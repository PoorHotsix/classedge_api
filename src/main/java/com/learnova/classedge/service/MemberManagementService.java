package com.learnova.classedge.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learnova.classedge.domain.Member;
import com.learnova.classedge.dto.MemberDto;
import com.learnova.classedge.repository.MemberManagementRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberManagementService {
    private final MemberManagementRepository memberManagementRepository;
    private final PasswordEncoder passwordEncoder;

    public void ActivateMember(MemberDto dto, boolean isWithdraw){
        Member member = dtoToMember(dto);
        member.setIsWithdraw(isWithdraw);
        memberManagementRepository.save(member);
    }


     MemberDto memberToDto(Member member){
        MemberDto dto = new MemberDto(member.getEmail(), member.getId(), member.getMemberName()
                                    , member.getPassword(), member.getIsWithdraw(), member.getRole()
                                    , member.getNickname(), member.getLoginType());

        return dto;
    }
     Member dtoToMember(MemberDto dto){
        Member member = new Member(dto.getEmail(), dto.getId(), dto.getMemberName()
                                ,passwordEncoder.encode(dto.getPassword()) , dto.getIsWithdraw(), dto.getRole()
                                , dto.getNickname(), dto.getLoginType());
        return member;
    }
}
