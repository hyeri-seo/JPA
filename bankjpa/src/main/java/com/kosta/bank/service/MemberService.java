package com.kosta.bank.service;

import javax.servlet.http.HttpSession;

import com.kosta.bank.dto.MemberDto;
import com.kosta.bank.entity.Member;

public interface MemberService {
	MemberDto login(String id, String password) throws Exception;
	void join(MemberDto member) throws Exception;
	void logout(HttpSession session) throws Exception;
}
