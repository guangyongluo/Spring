package com.vilin.spring.chapter11.bankapp.proxy.service;

import com.vilin.spring.chapter11.bankapp.proxy.dao.BankAccountDao;
import com.vilin.spring.chapter11.bankapp.proxy.domain.BankAccountDetails;
import com.vilin.spring.chapter11.bankapp.proxy.exception.BankAccountAlreadyExistsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "bankAccountService")
public class BankAccountServiceImpl implements BankAccountService {
	private static Logger logger = LogManager.getLogger(BankAccountServiceImpl.class);
	@Autowired
	private BankAccountDao bankAccountDao;

	@Override
	public int createBankAccount(BankAccountDetails bankAccountDetails) {
		logger.info("createBankAccount method invoked");
		//-- obtain the proxy and invoke the isDuplicateAccount method via proxy
		boolean isDuplicateAccount = ((BankAccountService)AopContext.currentProxy()).isDuplicateAccount(bankAccountDetails);
//		boolean isDuplicateAccount = isDuplicateAccount(bankAccountDetails);
		if(!isDuplicateAccount) {
			return bankAccountDao.createBankAccount(bankAccountDetails);
		} else {
			throw new BankAccountAlreadyExistsException("Bank account already exists");
		}
	}
	
	public boolean isDuplicateAccount(BankAccountDetails bankAccountDetails) {
		//--check if the account already exists
		return false;
	}
}
