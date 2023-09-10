/**
 * created by: Subhradip Biswas 
 * 			   
 * 
 * Note: I have used thymeleaf template engine to serve html along with css and img 
 * (and will be serving js in future)
 * 
 */

package com.example.demo.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.bean.userBean;
import com.example.demo.commons.commonUtils;
import com.example.demo.dao.CustomerAccBalance;
import com.example.demo.dao.CustomerRdDetailsRepo;
import com.example.demo.dao.PayeeDetailsRepo;
import com.example.demo.dao.TransactionRepo;
import com.example.demo.dao.UserAccTypeRepo;
import com.example.demo.dao.UserLoginRepo;
import com.example.demo.dao.UserLoginUpdateRepo;
import com.example.demo.dao.UserRepo;
import com.example.demo.dao.lastUpdateAccNumber;
import com.example.demo.model.AccountBalance;
import com.example.demo.model.CustomerAccTypeDetails;
import com.example.demo.model.CustomerAccountDetails;
import com.example.demo.model.CustomerRecurringAccount;
import com.example.demo.model.TransactionDetails;
import com.example.demo.model.UserCredentials;
import com.example.demo.model.UserPayeeDetails;
import com.example.demo.model.lastAccountNumberCount;
import com.example.demo.model.loginStatus;



@Controller
public class MainController {
	
	@Autowired
	UserLoginRepo loginRepo;	//This repo is for using UserCredentials table.
	
	@Autowired
	UserRepo repo;				//This repo is for using CustomerAccountDetails table.
	
	@Autowired
	UserLoginUpdateRepo updateRepo;	//This repo is for using loginStatus table.
	
	@Autowired
	PayeeDetailsRepo pdRepo;	//This repo is for using  UserPayeeDetails table.
	
	@Autowired
	lastUpdateAccNumber lrepo;	//This repo is for using lastAccountNumberCount table.
	
	@Autowired
	UserAccTypeRepo utRepo;		//This repo is for using CustomerAccTypeDetails table.
	
	@Autowired
	CustomerAccBalance custAccBalrepo;	//This repo is for using AccountBalance table.
	
	@Autowired
	TransactionRepo tRepo;			//This repo is for using TransactionDetails table.
	
	@Autowired
	CustomerRdDetailsRepo ctRdRepo;
	
	
//	@Autowired
//    private BankAccountDAO bankAccountDAO;
//	
	//variables
	userBean ubean;
	commonUtils ccUt;
	String bankId="30";
	String senderAccNo;
	String receiverAccNo;
	String TransactionID;
	Date date;
	CustomerRecurringAccount ctRdAcc;
	String RDPaymentStat;
	String RDInvestedAmt;
	
	/**
	 * The functionality of the method is to return the common login page whenever the url, ending
	 * with "/" is entered.
	 * 
	 * @param: null
	 * @return: common login page for both Admin and Customer.
	 * 
	 * Developed by: Sayyed Fahad.
	 */
	@RequestMapping("/")
	public String home() {
		
		return "login";
	}
	
	
	/**
	 * The functionality of the method is to return the Dashboard page according to the User type
	 * If the user is Admin then it will return AdminDashboard, or else it will return 
	 * CustomerDashboard page, after successfully verified with login credentials.
	 * 
	 * 
	 * @param: UserCredentials object, Model object.
	 * @return: Dashboard page according to the User type.
	 * 
	 * Developed by: Sayeed Fahad.
	 */
	@PostMapping("/login")
	public String verifyLogin(UserCredentials uc, Model model) {
		loginStatus um=new loginStatus();
		String url="login";
		if(loginRepo.existsById(uc.getUserID())) {
			
			UserCredentials userDetail = loginRepo.findById(uc.getUserID()).orElse(new UserCredentials());
			if(userDetail.getUserType().equalsIgnoreCase("ADMIN")) { 
				um.setUserID(uc.getUserID());
				um.setLoginStatus("Y");
				updateRepo.save(um);
				url="adminDashboard";
			}
			else if(userDetail.getUserType().equalsIgnoreCase("CUSTOMER")) {
				um.setUserID(uc.getUserID());
				um.setLoginStatus("Y");
				ubean = new userBean();
				ubean.setCustomerID(userDetail.getUserID());
				//this.customerID = userDetail.getUserID();
				
				model.addAttribute("AccList", accBalance());
				updateRepo.save(um);
				url= "customerDashboard";
			}
		}
		return url;
	}
	
	/**
	 * The functionality of the method is to return the create new customer account page,
	 * only applicable for Admin.
	 * 
	 * @param: null.
	 * @return: createNewCustomer page.
	 * 
	 * Developed by: Subhradip Biswas.
	 */
	@RequestMapping("/newCustomer")
	public String newCusrtomer() {
		 return "createNewCustomer";
	}
	
	/**
	 * The functionality of the method is to add new customer detail into the database,
	 * only applicable for Admin.
	 * 
	 * @param: CustomerAccountDetails object.
	 * @return: adminDashboard page.
	 * 
	 * Developed by: Subhradip Biswas.
	 */
	@PostMapping("/enterNewCustomer")
	public String enterNewCustomer(CustomerAccountDetails cd) {
		UserCredentials uc1 = new UserCredentials();
		ccUt=new commonUtils();
		lastAccountNumberCount lastNo=lrepo.findById(1).orElse(null);
		System.out.println("------>"+lastNo.getLastUpdatedNumber());
		cd.setAccNo(ccUt.getNewAccountNumber(lastNo.getLastUpdatedNumber(), bankId));
		uc1.setUserID(cd.getCustomerId());
		uc1.setPassword("Welcome@1234");
		uc1.setUserType("CUSTOMER");
		loginRepo.save(uc1);
		repo.save(cd);
		String aVal=lastNo.getLastUpdatedNumber();
		//
		lrepo.setLastAccNumberById(String.valueOf(((Integer.parseInt(aVal))+1)), 1);
		
		return "adminDashboard";
	}
	
	/**
	 * The functionality of the method is to return changeAdminPassword page,
	 * only applicable for Admin.
	 * 
	 * @param: null.
	 * @return: changeAdminPassword page.
	 * 
	 * Developed by: 
	 */
	@RequestMapping("/changeAdminPassword")
	public String changeAdminPass() {
		 return "changeAdminPassword";
	}
	
	
	/**
	 * The functionality of the method is to update Admin Password of the corresponding UserID,
	 * only applicable for Admin.
	 * 
	 * @param: UserCredentials object.
	 * @return: adminDashboard page.
	 * 
	 * Developed by: 
	 */
	@PostMapping("/changeAdminPass")
	public String adminPassChange(UserCredentials uc2) {
		uc2.setUserType("ADMIN");
		uc2.setLoginStatus("logout");
		loginRepo.setUserInfoById(uc2.getPassword(), uc2.getUserType(), uc2.getLoginStatus(), uc2.getUserID());
		return "adminDashboard";
	}
	
	
	/**
	 * The functionality of the method is to return addPayee page, containing new payee form,
	 * only applicable for Customer.
	 * 
	 * @param: null.
	 * @return: addPayee page.
	 * 
	 * Developed by: 
	 */
	@RequestMapping("/viewPayeeForm")
	public String payeeForm() {
		 return "addPayee";
	}
	
	
	/**
	 * The functionality of the method is to add a new payee details with status "Pending",
	 * only applicable for Customer.
	 * 
	 * @param: UserPayeeDetails object.
	 * @return: customerDashboard page.
	 * 
	 * Developed by: 
	 */
	@PostMapping("/addPayee")
	public String addPayeeDetails(UserPayeeDetails up) {
			up.setCustomerId(ubean.getCustomerID());
			up.setStatus("Pending");
			pdRepo.save(up);
			
			return "customerDashboard";
		}
	
	/**
	 * Service level method for getting all User payee details by customer ID.
	 * @param: null.
	 * @return: List<UserPayeeDetails> 
	 */
	public List<UserPayeeDetails> viewPayee() {
		
		return pdRepo.findAllByCustomerID(ubean.getCustomerID());
	}
	
	/**
	 * The functionality of the method is to view all existing payee details with status "Pending"
	 * as well as "Confirmed",
	 * only applicable for Customer.
	 * 
	 * @param: Model object.
	 * @return: viewPayee page.
	 * 
	 * Developed by: 
	 */
	@RequestMapping("/viewpayee")
	public String getAllPayee(Model model) {
	    model.addAttribute("PayeeList",viewPayee());
	    return "viewPayee";
	}
	
	/**
	 * Service level method for deleting particular User payee details by payee acc no.
	 * @param String accNo
	 * @return List<UserPayeeDetails>
	 */
	public List<UserPayeeDetails> deletePayee(String accNo) {
		pdRepo.deletePayeeByAccount_number(accNo);
		return pdRepo.findAllByCustomerID(ubean.getCustomerID());
	}
	
	/**
	 * The functionality of the method is to delete a particular payee details and 
	 * view all existing payee details with status "Pending" as well as "Confirmed",
	 * only applicable for Customer.
	 * 
	 * @param: UserPayeeDetails object, Model object.
	 * @return: viewPayee page.
	 * 
	 * Developed by: 
	 */
	@PostMapping("/deletePayee")
	public String deleteAndViewPayee(UserPayeeDetails uPayee, Model model) {
	
	    model.addAttribute("PayeeList",deletePayee(uPayee.getAccount_number()));
	    return "viewPayee";
	}
	
	/**
	 * Service level method for fetching User payee details by status "Pending".
	 * @param null
	 * @return List<UserPayeeDetails>
	 */
	@RequestMapping("/getPayeeStat")
	@ResponseBody
	public List<UserPayeeDetails> PayeeNotification() {
		return pdRepo.findAllByStatus("Pending");
	}
	
	/**
	 * The functionality of the method is to display particular payee details with status 
	 * "Pending"
	 * only applicable for Admin.
	 * 
	 * @param: Model object.
	 * @return: displayAdminPayee page.
	 * 
	 * Developed by: 
	 */
	@RequestMapping("/viewPayeeInAdminDash")
	public String displayPayeeStats(Model model) {
		model.addAttribute("PayeeList", PayeeNotification());
		return "displayAdminPayee";
	}
	
	/**
	 * The functionality of the method is to update particular payee status from
	 * "Pending" to "Confirmed"
	 * only applicable for Admin.
	 * 
	 * @param: null.
	 * @return: adminDashboard page.
	 * 
	 * Developed by: 
	 */
	@RequestMapping("/activatePayee")
	public String activatePayeeByAdmin() {
		//have to change here
		pdRepo.setPayeeInfoByStatus("Confirmed", "Pending");
		return "adminDashboard";
	}
	
	
	/**
	 * Service level method for fetching User Account Balance details by CustomerID.
	 * @param null
	 * @return List<AccountBalance>
	 */
	public List<AccountBalance> accBalance() {
		
		return custAccBalrepo.findAllByCustomerID(ubean.getCustomerID());
	}
	
	/**
	 * The functionality of the method is to return userTransaction page for making transaction
	 * 
	 * only applicable for Customer.
	 * 
	 * @param: null.
	 * @return: userTransaction page.
	 * 
	 * Developed by: 
	 */
	@RequestMapping("/transaction")
	public String viewTransactionForm() {
		return "userTransaction";
	}
	
	/**
	 * The functionality of the method is to make transaction
	 * 
	 * only applicable for Customer.
	 * 
	 * @param: TransactionDetails object, Model object.
	 * @return: transacStat page.
	 * 
	 * Developed by: 
	 */
	@PostMapping("/sendMoney")
	public String transferMoney(TransactionDetails td, Model model) {
		ccUt = new commonUtils();
		td.setTransactionID(ccUt.getAlphaNumericString());
		td.setTransactionStatus("Confirmed");
		senderAccNo = td.getSenderAccNo();
		receiverAccNo = td.getReceiverAccNo();
		TransactionID = td.getTransactionID();
		tRepo.save(td);
		
		/////////
		
		AccountBalance amn=new AccountBalance();
		amn= custAccBalrepo.findByAccNo(td.getSenderAccNo());
		int newbal=Integer.valueOf(amn.getAccountBalance())-Integer.valueOf(td.getAmount());
		System.out.println("acc No  : "+td.getSenderAccNo());
		System.out.println("transaction amount : "+amn.getAccountBalance());
		System.out.println("newbal : "+newbal);
		custAccBalrepo.setAccountBalanceInfoByAccountBalance(String.valueOf(newbal), td.getSenderAccNo());
		
		
		AccountBalance amn1=new AccountBalance();
		amn1= custAccBalrepo.findByAccNo(td.getReceiverAccNo());
		int newbal1=Integer.valueOf(amn1.getAccountBalance())+Integer.valueOf(td.getAmount());
		custAccBalrepo.setAccountBalanceInfoByAccountBalance(String.valueOf(newbal1), td.getReceiverAccNo());
		/////////////////////////
		
		model.addAttribute("TransactionID", td.getTransactionID());
		return "transacStat";
	}
	
	
	/**
	 * Service level method for fetching User Transaction details from the bottom of the database table.
	 * @param null
	 * @return List<TransactionDetails>
	 */
	public List<TransactionDetails> transacDetails() {
		
		return tRepo.findTopByOrderByIdDesc();
	}
	
	/**
	 * The functionality of the method is to return customerDashboard with last transaction detail and 
	 * account balance.
	 * only applicable for Customer.
	 * 
	 * @param: Model object.
	 * @return: customerDashboard page.
	 * 
	 * Developed by: 
	 */
	@RequestMapping("/backCustomerDash")
	public String returnHome(Model model) {
		model.addAttribute("transacList", transacDetails());
		model.addAttribute("AccList", accBalance());
		return "customerDashboard";
	}
	
	/**
	 * The functionality of the method is to return userAccount page for creating 
	 * new FD or RD account.
	 * only applicable for Customer.
	 * 
	 * @param: null.
	 * @return: userAccount page.
	 * 
	 * Developed by: 
	 */
	@RequestMapping("/createFDRD")
	public String FDAcc() {
		return "userAccount";
	}

	/**
	 * The functionality of the method is to create new FD or RD account.
	 * only applicable for Customer.
	 * 
	 * @param: CustomerAccTypeDetails object, Model object.
	 * @return: userAccount page.
	 * @throws: ParseException
	 * 
	 * Developed by: 
	 */
	@PostMapping("/addFDRDAcc")
	public String addFDRDAcc(CustomerAccTypeDetails cAd , Model model) throws ParseException {
		ccUt = new commonUtils();
		lastAccountNumberCount lastNo=lrepo.findById(1).orElse(null);
		cAd.setFD_RD_AccountNo(ccUt.getNewAccountNumber(lastNo.getLastUpdatedNumber(), bankId));
		
		//******************** For Recurring ************************//
		
		if(cAd.getAccountType().equalsIgnoreCase("Recurring")) {
			CustomerRecurringAccount cusRecur = new CustomerRecurringAccount();
			//setting basic details
			cusRecur.setCustomerId(cAd.getCustomerId());
			cusRecur.setFullName(cAd.getFullName());
			cusRecur.setPanNumber(cAd.getPanNumber());
			cusRecur.setAccount_number(cAd.getAccount_number());
			cusRecur.setBranch(cAd.getBranch());
			cusRecur.setAccountType(cAd.getAccountType());
			cusRecur.setRD_AccountNo(ccUt.getNewAccountNumber(lastNo.getLastUpdatedNumber(), bankId));
			cusRecur.setNominee(cAd.getNominee());
			cusRecur.setInvestedAmount(cAd.getInvestedAmount());
			cusRecur.setStartDate(cAd.getStartDate());
			
			cusRecur.setTenure("36 Months");
			cusRecur.setEndDate(ccUt.getFinalYearForRD(cAd.getStartDate()));
			double rate=6.5;
			cusRecur.setPaymentStatus("N");
			RDPaymentStat = cusRecur.getPaymentStatus();
			RDInvestedAmt = cusRecur.getInvestedAmount();
			ctRdRepo.save(cusRecur);
			
		}else if(cAd.getAccountType().equalsIgnoreCase("Fixed")) {
			cAd.setTenure("5 Years");
			double rate=6.5;
			cAd.setEndDate(ccUt.getFinalYearForFD(cAd.getStartDate()));
			ccUt=new commonUtils();
			cAd.setPresentDate(ccUt.getCurrentDate());
			String k=ccUt.calcFinalAmountForFD(cAd.getInvestedAmount(), ccUt.getTimeDifference(cAd.getStartDate()),rate );
			cAd.setFinalAmount(k);
			System.out.println("-------->"+k);
			double returnAm=Double.parseDouble(k)-Double.parseDouble(cAd.getInvestedAmount());
			cAd.setReturnAmount(Integer.toString((int) returnAm));
			utRepo.save(cAd);
		}
		model.addAttribute("AccList", accBalance());
		return "customerDashboard";
	}
	

	/**
	 * The functionality of the method is to return Admin Dashboard page.
	 * only applicable for Admin.
	 * 
	 * @param: null.
	 * @return: adminDashboard page.
	 * 
	 * Developed by: 
	 */
	@RequestMapping("/backHome")
	public String returnAdminHome() {
		return "adminDashboard";
	}
	
	/**
	 * The functionality of the method is to return Customer Dashboard page.
	 * only applicable for Customer.
	 * 
	 * @param: null.
	 * @return: customerDashboard page.
	 * 
	 * Developed by: 
	 */
	@RequestMapping("/backCustmerHome")
	public String returnCustomerHome() {
		return "customerDashboard";
	}
	
	/**
	 * The functionality of the method is to logout and and redirect to common login page.
	 * applicable for both Admin and Customer.
	 * 
	 * @param: null.
	 * @return: Common login page.
	 * 
	 * Developed by: 
	 */
	@RequestMapping("/logout")
	public String logout() {
		return "redirect:/";
	}
	
	public List<CustomerAccTypeDetails> investmentList() {
		
		return utRepo.findAllByCustomerID(ubean.getCustomerID());
	}
	
	@RequestMapping("/myInvestment")
	public String myInvestment(Model model) {
		
		model.addAttribute("InvestmentList", investmentList());
		return "myInvestments";
	}
	
	
	public List<CustomerRecurringAccount> RDinvestmentList() {
		CustomerRecurringAccount mmt=new CustomerRecurringAccount();
		ccUt=new commonUtils();
		mmt=ctRdRepo.findByCustomerID(ubean.getCustomerID());
		System.out.println("------------>"+ubean.getCustomerID());
		System.out.println("------------>"+mmt.getPaymentStatus());
		if(mmt.getPaymentStatus().equalsIgnoreCase("N")) {
			String invAmt=mmt.getInvestedAmount();
			String returnAmount=ccUt.rdReturn(invAmt, "1");
			String totalAmount=String.valueOf(Double.parseDouble(invAmt)+Double.parseDouble(returnAmount));
			
			ctRdRepo.setCustomerRecurringAccountInfoBycustomerId(ubean.getCustomerID(), totalAmount, returnAmount, "Y");
		}
		
		return ctRdRepo.findAllByCustomerID(ubean.getCustomerID());
	}

	@RequestMapping("/viewRDdeposit")
	public String myRDInvestment(Model model) {
		List<CustomerRecurringAccount> findAllByCustomerID=ctRdRepo.findAllByCustomerID(ubean.getCustomerID());
	
		model.addAttribute("RDInvestmentList", findAllByCustomerID);
		return "UserRDAccounts";
	}	
}
