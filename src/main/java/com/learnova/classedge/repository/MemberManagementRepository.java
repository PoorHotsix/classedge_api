package com.learnova.classedge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.learnova.classedge.domain.Member;

public interface MemberManagementRepository extends JpaRepository<Member, String> { // <Entity, PK>
    
    @Query("SELECT m FROM Member m WHERE m.email = :email")
    Member getMemberByEmail(@Param("email") String email);

    @Query("SELECT m FROM Member m WHERE m.id = :id")
    Member getMemberById(@Param("id") String id);

    @Query("SELECT m FROM Member m WHERE m.nickname = :nickname")
    Member getMemberByNickname(@Param("nickname") String nickname);

    // id로 회원 조회
    Optional<Member> findById(String id);

    // 카카오 nickname 조회
    Optional<Member> findByNickname(String nickname);

}
