package com.msdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msdemo.model.Account;
import com.msdemo.service.AccountService;

import reactor.core.publisher.Mono;

@Controller
public class AccountsController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/getAllAccounts")
	@ResponseBody
	public Mono<List<Account>> hello() {
		return Mono.just(accountService.getAll());
	}
	
	@GetMapping("/getAllByProducts/{product}")
	@ResponseBody
	public Mono<List<Account>> getByProduct(@PathVariable("product") String product) {
		return Mono.just(accountService.getAllByProduct(product));
	}
	
	@PostMapping("/addAccount")
	@ResponseBody
	public Mono<Account> addAccount(@RequestBody Account account) {
		return Mono.just(accountService.addOne(account));
	}
	
	@DeleteMapping("/deleteAccount/{accountId}")
	@ResponseBody
	public void deleteAccount(@PathVariable("accountId") Long accountId) {
		accountService.deleteAccount(accountId);
	}

	
}
