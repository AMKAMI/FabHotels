package com.fabhotels.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabhotels.entity.TransactionInfo;
import com.fabhotels.repository.TransactionRepository;
import com.fabhotels.request.AddMoneyRequest;

@Service
public class TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	final static String MONEY_ADDED_TO_WAALET = "Money added to Wallet";

	final static String MONEY_DEBITTED = "Money tranfered to ";

	final static String MONEY_CREDITTED = "Money tranfered from ";

	public TransactionInfo logTransaction(String email, AddMoneyRequest addmoney, double balance) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String stringDate = dateFormat.format(new Date());

		TransactionInfo transaction = new TransactionInfo(email, stringDate, MONEY_ADDED_TO_WAALET, 0,
				addmoney.getMoney(), balance);
		return transactionRepository.save(transaction);
	}

	public TransactionInfo logTransaction(String email, Double amount, double balance, String otherEmail,
			Boolean isCredit) {
		TransactionInfo transaction;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String stringDate = dateFormat.format(new Date());
		
		if (isCredit)
			transaction = new TransactionInfo(email, stringDate, MONEY_CREDITTED + otherEmail, 0, amount, balance);
		else
			transaction = new TransactionInfo(email, stringDate, MONEY_DEBITTED + otherEmail, amount, 0, balance);

		return transactionRepository.save(transaction);
	}

	public List<TransactionInfo> getAllTransactions(String email) {
		return transactionRepository.findByEmail(email);

	}

}
