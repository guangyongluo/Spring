package com.vilin.spring.chapter04.bankapp.replaced.method.dao;


import com.vilin.spring.chapter04.bankapp.replaced.method.domain.CustomerRegistrationDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomerRegistrationDaoImpl implements CustomerRegistrationDao {
	private static final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

	public CustomerRegistrationDaoImpl() {
		logger.info("Created CustomerRegistrationDaoImpl instance");
	}

	@Override
	public void registerCustomer(CustomerRegistrationDetails customerRegistrationDetails) {
	}

}
