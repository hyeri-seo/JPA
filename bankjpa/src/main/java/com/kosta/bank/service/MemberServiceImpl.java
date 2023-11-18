package com.kosta.bank.service;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.bank.dto.MemberDto;
import com.kosta.bank.entity.Member;
import com.kosta.bank.repository.MemberRepository;

import lombok.Getter;
import lombok.Setter;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Getter
	@Setter
	@Autowired
	private MemberRepository memberRepository;

	@Override
	public MemberDto login(String id, String password) throws Exception {
		Optional<Member> omember = memberRepository.findById(id);
		if(omember.isEmpty()) throw new Exception("아이디 오류");
		Member member = omember.get();
		if(!omember.get().getPassword().equals(password)) throw new Exception("비밀번호 오류");
		return MemberDto.toDto(member);
	}

	@Override
	public void join(MemberDto member) throws Exception {
		Optional<Member> omember = memberRepository.findById(member.getId());
		if(omember.isPresent()) throw new Exception("아이디 중복 오류");
		memberRepository.save(Member.toEntity(member));
	}

	@Override
	public void logout(HttpSession session) throws Exception {
		session.removeAttribute("member");
	}

}
