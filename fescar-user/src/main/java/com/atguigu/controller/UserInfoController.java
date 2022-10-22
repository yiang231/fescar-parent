package com.atguigu.controller;

import com.atguigu.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userInfo")
@CrossOrigin
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;

	/***
	 * 账户余额递减
	 * @param username
	 * @param money
	 */
	@PostMapping(value = "/add")
	public String decrMoney(@RequestParam(value = "username") String username, @RequestParam(value = "money") int money) {
		userInfoService.decrMoney(username, money);
		return "success";
	}

}
