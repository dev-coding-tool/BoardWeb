package com.codingtool.board.persistence;

import org.springframework.data.repository.CrudRepository;

import com.codingtool.board.domain.Member;

public interface MemberRepository extends CrudRepository<Member, String> {
	
}
