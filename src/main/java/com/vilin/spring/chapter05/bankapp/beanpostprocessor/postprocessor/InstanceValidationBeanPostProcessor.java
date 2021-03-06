package com.vilin.spring.chapter05.bankapp.beanpostprocessor.postprocessor;

import com.vilin.spring.chapter05.bankapp.beanpostprocessor.common.InstanceValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

public class InstanceValidationBeanPostProcessor implements BeanPostProcessor,
		Ordered {
	private static final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
	private int order;

	public InstanceValidationBeanPostProcessor() {
		logger.info("Created InstanceValidationBeanPostProcessor instance");
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		logger.info("InstanceValidationBeanPostProcessor's postProcessBeforeInitialization method invoked for bean " + beanName + " of type " + bean.getClass());
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		logger.info("InstanceValidationBeanPostProcessor's postProcessAfterInitialization method invoked for bean " + beanName + " of type " + bean.getClass());
		if (bean instanceof InstanceValidator) {
			((InstanceValidator) bean).validateInstance();
		}
		return bean;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public int getOrder() {
		return order;
	}
}