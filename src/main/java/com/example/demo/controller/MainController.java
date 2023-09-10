/**
 * created by: Subhradip Biswas (ADID : SU40109923)
 * 
 * Note: I have used thymeleaf template engine to serve html along with css and img 
 * (and will be serving js in future)
 * 
 */

package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.UserLoginRepo;
import com.example.demo.dao.UserLoginUpdateRepo;
import com.example.demo.dao.UserRepo;
import com.example.demo.model.CustomerAccountDetails;
import com.example.demo.model.UserCredentials;
import com.example.demo.model.loginStatus;

@Controller
public class MainController {
	
	@Autowired
	UserLoginRepo loginRepo;
	
	@Autowired
	UserRepo repo;
	
	@Autowired
	UserLoginUpdateRepo updateRepo;

	
	@RequestMapping("/")
	public String home() {
		return "login";
	}
	
	@PostMapping("/login")
	public String verifyLogin(UserCredentials uc) {
		loginStatus um=new loginStatus();
		String url="login";
		if(loginRepo.existsById(uc.getUserID())) {
			
			UserCredentials userDetail = loginRepo.findById(uc.getUserID()).orElse(new UserCredentials());
			if(userDetail.getUserType().equalsIgnoreCase("ADMIN")) { 
				// updateLoginStatus(uc.getUserID()))
				um.setUserID(uc.getUserID());
				um.setLoginStatus("Y");
				updateRepo.save(um);
				url="adminDashboard";
			}
			else if(userDetail.getUserType().equalsIgnoreCase("CUSTOMER")) {
				um.setUserID(uc.getUserID());
				um.setLoginStatus("Y");
				//this.customerID = userDetail.getUserID();
				updateRepo.save(um);
				url= "customerDashboard";
			}
		}
		System.out.println(uc.toString());
		return url;
	}
	
	@RequestMapping("/newCustomer")
	public String newCusrtomer() {
		 return "createNewCustomer";
	}
	
	
	@PostMapping("/enterNewCustomer")
	public String enterNewCustomer(CustomerAccountDetails cd) {
		//cd.setCustomerId("abc1");
		UserCredentials uc1 = new UserCredentials();
		uc1.setUserID(cd.getCustomerId());
		uc1.setPassword("Welcome@1234");
		uc1.setUserType("CUSTOMER");
		loginRepo.save(uc1);
		repo.save(cd);
		
		return "adminDashboard";
	}
	
	@RequestMapping("/changeAdminPassword")
	public String changeAdminPass() {
		 return "changeAdminPassword";
	}
	
	@PostMapping("/changeAdminPass")
	public String adminPassChange(UserCredentials uc2) {
		uc2.setUserType("ADMIN");
		uc2.setLoginStatus("logout");
		loginRepo.setUserInfoById(uc2.getPassword(), uc2.getUserType(), uc2.getLoginStatus(), uc2.getUserID());
		return "adminDashboard";
	}
	
	
}
