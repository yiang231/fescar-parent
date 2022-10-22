package com.atguigu.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "user_info")
public class UserInfo implements Serializable {

	@Id
	@Column(name = "account")
	private String account;

	@Column(name = "money")
	private Integer money;

	@Column(name = "name")
	private String name;

	//get方法
	public String getAccount() {
		return account;
	}

	//set方法
	public void setAccount(String account) {
		this.account = account;
	}

	//get方法
	public Integer getMoney() {
		return money;
	}

	//set方法
	public void setMoney(Integer money) {
		this.money = money;
	}

	//get方法
	public String getName() {
		return name;
	}

	//set方法
	public void setName(String name) {
		this.name = name;
	}
}
