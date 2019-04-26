package com.vilin.spring.chapter05.bankapp.localoverrideconfigurer.main;

import com.vilin.spring.chapter05.bankapp.localoverrideconfigurer.domain.DataSource;
import com.vilin.spring.chapter05.bankapp.localoverrideconfigurer.domain.WebServiceConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SampleApp {
	private static final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
	
	public static void main(String args[]) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:com/vilin/spring/chapter05/bankapp/localoverrideconfigurer/applicationContext.xml");
		logger.info(context.getBean(DataSource.class));
		logger.info(context.getBean(WebServiceConfiguration.class));
	}
}
