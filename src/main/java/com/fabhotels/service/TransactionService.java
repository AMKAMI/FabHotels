package com.fabhotels.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabhotels.entity.TransactionInfo;
import com.fabhotels.messages.Message;
import com.fabhotels.repository.TransactionRepository;
import com.fabhotels.request.AddMoneyRequest;

@Service
public class TransactionService {

	@Autowired
	TransactionRepository transactionRepository;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public TransactionInfo logTransaction(String email, AddMoneyRequest addmoney, double balance) {

		String stringDate = dateFormat.format(new Date());

		TransactionInfo transaction = new TransactionInfo(email, stringDate, Message.MONEY_ADDED_TO_WAALET, 0,
				addmoney.getMoney(), balance);
		return transactionRepository.save(transaction);
	}

	public TransactionInfo logTransaction(String email, Double amount, double balance, String otherEmail,
			Boolean isCredit) {
		TransactionInfo transaction;
		String stringDate = dateFormat.format(new Date());

		if (isCredit)
			transaction = new TransactionInfo(email, stringDate, Message.MONEY_CREDITTED + otherEmail, 0, amount,
					balance);
		else
			transaction = new TransactionInfo(email, stringDate, Message.MONEY_DEBITTED + otherEmail, amount, 0,
					balance);

		return transactionRepository.save(transaction);
	}

	public List<TransactionInfo> getAllTransactions(String email) {
		return transactionRepository.findByEmail(email);

	}

}
