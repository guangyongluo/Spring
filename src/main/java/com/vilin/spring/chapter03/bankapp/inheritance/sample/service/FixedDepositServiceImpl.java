package com.vilin.spring.chapter03.bankapp.inheritance.sample.service;

import com.vilin.spring.chapter03.bankapp.inheritance.sample.base.ServiceTemplate;
import com.vilin.spring.chapter03.bankapp.inheritance.sample.dao.FixedDepositDao;
import com.vilin.spring.chapter03.bankapp.inheritance.sample.domain.FixedDepositDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FixedDepositServiceImpl extends ServiceTemplate implements FixedDepositService {
	private static final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

	private FixedDepositDao fixedDepositDao;

	public FixedDepositServiceImpl() {
		logger.info("initializing");
	}

	public FixedDepositDao getFixedDepositDao() {
		return fixedDepositDao;
	}

	public void setFixedDepositDao(FixedDepositDao fixedDepositDao) {
		logger.info("Setting fixedDepositDao property");
		this.fixedDepositDao = fixedDepositDao;
	}

	public FixedDepositDetails getFixedDepositDetails(long id) {
		return fixedDepositDao.getFixedDepositDetails(id);
	}

	public boolean createFixedDeposit(FixedDepositDetails fdd) {
		return fixedDepositDao.createFixedDeposit(fdd);
	}
}
