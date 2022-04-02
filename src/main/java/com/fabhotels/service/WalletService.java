package com.fabhotels.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabhotels.entity.Wallet;
import com.fabhotels.messages.Message;
import com.fabhotels.repository.WalletRepository;
import com.fabhotels.request.AddMoneyRequest;
import com.fabhotels.response.WalletResponse;

@Service
public class WalletService {

	@Autowired
	WalletRepository walletRepository;

	@Autowired
	TransactionService transactionService;

	public Wallet createWallet(String email) {
		Wallet wallet = new Wallet(email);
		return walletRepository.save(wallet);
	}

	public WalletResponse updateWallet(String email, AddMoneyRequest addMoneyRequest) {

		Wallet wallet = walletRepository.findByEmail(email);
		wallet.setAmount(wallet.getAmount() + addMoneyRequest.getMoney());
		walletRepository.save(wallet);
		transactionService.logTransaction(email, addMoneyRequest, wallet.getAmount());
		
		WalletResponse response = new WalletResponse();
		response.setStatus(Message.SUCCESS);
		
		return response;

	}

	public void debitWallet(String email, Double money, String to) throws Exception {
		Wallet wallet = walletRepository.findByEmail(email);

		if (wallet.getAmount() - money >= 0) {
			wallet.setAmount(wallet.getAmount() - money);
			walletRepository.save(wallet);
			transactionService.logTransaction(email, money, wallet.getAmount(), to, false);

		} else {
			throw new Exception("Insufficient Balance");
		}

	}

	public WalletResponse creditWallet(String email, Double money, String from) {

		Wallet wallet = walletRepository.findByEmail(email);
		WalletResponse response = new WalletResponse();
		try {
			wallet.setAmount(wallet.getAmount() + money);
			walletRepository.save(wallet);
			response.setError("");
			response.setStatus(Message.SUCCESS);
			transactionService.logTransaction(email, money, wallet.getAmount(), from, true);
		} catch (Exception e) {
			response.setError(e.toString());
			response.setStatus(Message.FAILED);
		}

		return response;
	}

}
