package com.msdemo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "accounts")
public class Account {

	@Id
	private String _id;
	
	private long account_id;
	private long limit;
	private List<String> products;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public long getAccount_id() {
		return account_id;
	}
	public void setAccount_id(long account_id) {
		this.account_id = account_id;
	}
	public long getLimit() {
		return limit;
	}
	public void setLimit(long limit) {
		this.limit = limit;
	}
	public List<String> getProducts() {
		return products;
	}
	public void setProducts(List<String> products) {
		this.products = products;
	}
}
