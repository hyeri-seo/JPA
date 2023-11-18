package com.kosta.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.board.entity.Board;
import com.kosta.board.entity.Member;
import com.kosta.board.repository.BoardRepository;
import com.kosta.board.repository.MemberRepository;

@SpringBootTest
class BoardjpaApplicationTests {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Test
	void contextLoads() {
	}

	@Test
	void selectBoard() {
		Board board = boardRepository.findById(33).get();
		System.out.println(board);
		System.out.println(board.getMember());
	}
	
	@Test
	void selectMember() {
		Member member = memberRepository.findById("1001").get();
		System.out.println(member);
		for(Board board : member.getBoardList()) {
			System.out.println(board);
		}
	}
}
