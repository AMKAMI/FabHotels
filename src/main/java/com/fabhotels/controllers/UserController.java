package com.fabhotels.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fabhotels.entity.TransactionInfo;
import com.fabhotels.messages.Message;
import com.fabhotels.request.AddMoneyRequest;
import com.fabhotels.request.TransferMoneyRequest;
import com.fabhotels.request.UpdateProfileRequest;
import com.fabhotels.request.UserLoginRequest;
import com.fabhotels.request.UserRegistrationRequest;
import com.fabhotels.response.PassbookResponse;
import com.fabhotels.response.UserResponse;
import com.fabhotels.response.WalletResponse;
import com.fabhotels.service.TransactionService;
import com.fabhotels.service.UserService;
import com.fabhotels.service.WalletService;

@RestController
public class UserController {

	static final String EMAIL = "email";

	static Logger log = Logger.getLogger(UserController.class.getName());

	@Autowired
	UserService userService;

	@Autowired
	WalletService walletService;

	@Autowired
	TransactionService transactionService;

	@GetMapping("/viewProfile")
	public UserResponse getUser(HttpSession session) {
		UserResponse response = new UserResponse();
		try {
			response = userService.getUser((String) session.getAttribute(EMAIL));
			log.info("View Profile " + EMAIL);
			return response;

		} catch (Exception ex) {
			response.setStatus(Message.TRY_LOGIN);
			log.info("Exception occured " + ex);
			return response;

		}
	}

	@PostMapping("/registration")
	public UserResponse userRegistration(@RequestBody UserRegistrationRequest userRegistration) {
		UserResponse response = new UserResponse();
		try {
			response = userService.registerUser(userRegistration);
			walletService.createWallet(userRegistration.getEmail());
			log.info(Message.REGISTRATION_SUCCESS);
			return response;

		} catch (Exception e) {
			response.setStatus(Message.REGISTRATION_FAILED);
			log.info("Exception is " + e);
			return null;

		}
	}

	@PostMapping("/login")
	public String userLogin(@RequestBody UserLoginRequest userLogin, HttpSession session) {

		try {
			if (userService.isUser(userLogin.getEmail(), userLogin.getPassword())) {
				session.setAttribute(EMAIL, userLogin.getEmail());
				log.info((String) session.getAttribute(EMAIL));
				return Message.SUCCESS;
			}

			else
				return Message.INVALID_CREDENTIALS;
		} catch (NullPointerException e) {
			return Message.INVALID_CREDENTIALS;
		}
	}

	@PostMapping("/addMoney")
	public WalletResponse addMoney(@RequestBody AddMoneyRequest addMoney, HttpSession session) {
		WalletResponse response = new WalletResponse();

		try {
			response = walletService.updateWallet((String) session.getAttribute(EMAIL), addMoney);
			log.info("Money added successfully to " + EMAIL);
			return response;

		} catch (NullPointerException e) {
			log.info("Exeception Occured" + e);
			response.setStatus(Message.FAILED);
			response.setError(e.toString());
			return response;
		}

	}

	@GetMapping("/signOut")
	public String userLogin(HttpSession session) {
		if (session.getAttribute(EMAIL) != null) {
			session.setAttribute(EMAIL, null);
			log.info(EMAIL + " LOGGED OUT");
			return Message.LOGGED_OUT;
		} else
			return Message.TRY_LOGIN;
	}

	@PutMapping("/updateProfile")
	public UserResponse userProfileUpdate(@RequestBody UpdateProfileRequest updateProfileRequest, HttpSession session) {
		UserResponse response = new UserResponse();
		try {
			response = userService.updateUserProfile(updateProfileRequest, (String) session.getAttribute(EMAIL));
			log.info("Update profile completed");
			return response;

		} catch (Exception e) {
			response.setStatus(Message.FAILED);
			log.info("Exception Occured" + e);
			return response;

		}
	}

	@GetMapping("/viewPassbook")
	public List<PassbookResponse> viewPassbok(HttpSession session) {
		List<TransactionInfo> transactionsInfo = transactionService
				.getAllTransactions((String) session.getAttribute(EMAIL));
		List<PassbookResponse> passbookResponse = new ArrayList<PassbookResponse>();
		transactionsInfo.stream().forEach((e) -> passbookResponse.add(new PassbookResponse(e)));
		return passbookResponse;
	}

	@PostMapping("/transferMoney")
	public WalletResponse transferMoney(@RequestBody TransferMoneyRequest transferMoneyRequest, HttpSession session) {
		WalletResponse response = new WalletResponse();
		try {
			if (session.getAttribute(EMAIL) == null)
				throw new Exception(Message.TRY_LOGIN);
			String toWallet = userService.getUser(transferMoneyRequest.getTo()).getEmail();
			walletService.debitWallet((String) session.getAttribute(EMAIL), transferMoneyRequest.getMoney(),
					transferMoneyRequest.getTo());
			response = walletService.creditWallet(toWallet, transferMoneyRequest.getMoney(),
					(String) session.getAttribute(EMAIL));
			return response;
		}

		catch (NullPointerException ex) {
			response.setError(Message.NO_USER);
			response.setStatus(Message.FAILED);
			log.info("Exception is " + ex);
			return response;
		}

		catch (Exception e) {
			response.setError(e.toString());
			response.setStatus(Message.FAILED);
			log.info("Exception in Transfer money is " + e);
			return response;
		}
	}
}
