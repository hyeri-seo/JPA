package com.kosta.bank.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.bank.dto.AccountDto;
import com.kosta.bank.entity.Account;
import com.kosta.bank.service.AccountService;

@RestController
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping("/makeaccount")
	public ResponseEntity<String> makeAccount(@RequestBody AccountDto acc) {
		try {
			accountService.makeAccount(acc);
			return new ResponseEntity<String>(acc.getId()+"계좌가 개설되었습니다", HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/accountinfo/{id}")
	public ResponseEntity<Object> accountInfo(@PathVariable String id) {
		try {
			AccountDto acc = accountService.accountInfo(id);
			return new ResponseEntity<Object>(acc, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/allaccountinfo")
	public ResponseEntity<Object> allAccountInfo() {
//	public ResponseEntity<List<Account>> allAccountInfo() {
		try {
			List<Account> accs = accountService.allAccountInfo();
			return new ResponseEntity<Object>(accs, HttpStatus.OK);
//			return new ResponseEntity<List<Account>>(accs, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("계좌 목록 조회 실패", HttpStatus.BAD_REQUEST);
//			return new ResponseEntity<List<Account>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/deposit/{id}")
	public ResponseEntity<Object> deposit(@PathVariable String id, @RequestBody Map<String, Integer> param) {
		try {
			Integer balance = accountService.deposit(id, param.get("money"));
			Map<String, Object> res = new HashMap<>();
			res.put("id", id);
			res.put("balance", balance);
			return new ResponseEntity<Object>(res, HttpStatus.OK);
			//데이터가 어떤 타입인지를 generic<>에 씀
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/withdraw/{id}")
	public ResponseEntity<Object> withdraw(@PathVariable String id, @RequestBody Map<String, Integer> param) {
		try {
			Integer balance = accountService.withdraw(id, param.get("money"));
			Map<String, Object> res = new HashMap<>();
			res.put("id", id);
			res.put("balance", balance);
			return new ResponseEntity<Object>(res, HttpStatus.OK);

		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
//	강사님 코드 post 버전
//	@PostMapping("/withdraw")
//	public ResponseEntity<Object> withdraw(@RequestBody Map<String, Object> param) {
//		try {
//			Integer balance = accountService.withdraw((String)param.get("id"), (Integer)param.get("money"));
//			Map<String, Object> res = new HashMap<>();
//			res.put("id", id);
//			res.put("balance", balance);
//			return new ResponseEntity<Object>(res, HttpStatus.OK);
//
//		} catch(Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
//		}
//	}
}

