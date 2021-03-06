package com.vilin.spring.chapter04.bankapp.dependencies.service;

import java.beans.ConstructorProperties;

import com.vilin.spring.chapter04.bankapp.dependencies.dao.CustomerRegistrationDao;
import com.vilin.spring.chapter04.bankapp.dependencies.domain.CustomerRegistrationDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomerRegistrationServiceImpl implements
		CustomerRegistrationService {
	private static final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

	private CustomerRegistrationDetails customerRegistrationDetails;
	private CustomerRegistrationDao customerRegistrationDao;

	@ConstructorProperties({ "customerRegistrationDetails",
			"customerRegistrationDao" })
	public CustomerRegistrationServiceImpl(
			CustomerRegistrationDetails customerRegistrationDetails,
			CustomerRegistrationDao customerRegistrationDao) {
		this.customerRegistrationDetails = customerRegistrationDetails;
		this.customerRegistrationDao = customerRegistrationDao;
		logger.info("Created CustomerRegistrationServiceImpl instance");
	}

	@Override
	public void setAccountNumber(String accountNumber) {
		customerRegistrationDetails.setAccountNumber(accountNumber);
	}

	@Override
	public void setAddress(String address) {
		customerRegistrationDetails.setAddress(address);
	}

	@Override
	public void setDebitCardNumber(String cardNumber) {
		customerRegistrationDetails.setCardNumber(cardNumber);
	}

	@Override
	public void register() {
		customerRegistrationDao.registerCustomer(customerRegistrationDetails);
	}

}
