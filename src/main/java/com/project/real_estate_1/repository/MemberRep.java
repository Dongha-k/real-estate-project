package com.project.real_estate_1.repository;

import com.project.real_estate_1.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRep extends JpaRepository<Member, Long> {
}