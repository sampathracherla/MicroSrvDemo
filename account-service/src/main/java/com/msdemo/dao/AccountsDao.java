package com.msdemo.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.msdemo.model.Account;

@Repository
public interface AccountsDao extends MongoRepository<Account, String> {

	public List<Account> findByProductsIn(String product);
	
	@Query("{account_id: ?0}")
	public Account findByAccountId(Long accountId);
}
