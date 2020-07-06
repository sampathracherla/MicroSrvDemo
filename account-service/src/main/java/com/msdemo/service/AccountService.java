package com.msdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msdemo.dao.AccountsDao;
import com.msdemo.model.Account;

@Service
public class AccountService {

	@Autowired
	private AccountsDao accountsDao;
	
	public List<Account> getAll() {
		return accountsDao.findAll();
	}
	
	public List<Account> getAllByProduct(String product) {
		return accountsDao.findByProductsIn(product);
	}
	
	public Account addOne(Account account) {
		Account ac = accountsDao.insert(account);
		return ac;
	}
	
	public void deleteAccount(Long accountId) {
		Account ac = accountsDao.findByAccountId(accountId);
		accountsDao.deleteById(ac.get_id());
	}
}
