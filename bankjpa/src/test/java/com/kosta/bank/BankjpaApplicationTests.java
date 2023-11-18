package com.kosta.bank;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kosta.bank.dto.AccountDto;
import com.kosta.bank.entity.Account;
import com.kosta.bank.entity.Member;
import com.kosta.bank.repository.AccountRepository;
import com.kosta.bank.repository.MemberRepository;
import com.kosta.bank.service.AccountService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BankjpaApplicationTests {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void selectAccountTest() {
		Optional<Account> oacc = accountRepository.findById("1001");
		if(oacc.isPresent()) {
			System.out.println(oacc.get());
		}
	}
	
	@Test
	void insertAccountTest() {
		AccountDto acc = new AccountDto("100020", "금요일", 300000, "Special", "VIP");
//		accountRepository.save(acc);
		try {
			accountService.makeAccount(acc);			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void updateAccountTest() {
		Account acc = new Account("100020", "금요일", 400000, "Special", "VIP");
		accountRepository.save(acc);
	}
	
	@Test
	void deleteAccountTest() {
		accountRepository.deleteById("10020");;
	}
	
	@Test
	void selectAllAcocuntTest() {
		List<Account> accList = accountRepository.findAll();
		for(Account acc : accList) {
			System.out.println(acc);
		}
	}
	
	@Test
	void insertMember() {
		Member member = Member.builder().id("coco").name("코코").password("1234")
			.email("coco@kosta.com").address("경기도 광명시").build();
		memberRepository.save(member);
	}
	
}
