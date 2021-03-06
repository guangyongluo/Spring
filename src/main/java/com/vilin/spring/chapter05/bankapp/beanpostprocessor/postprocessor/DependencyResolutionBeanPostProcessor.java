package com.vilin.spring.chapter05.bankapp.beanpostprocessor.postprocessor;

import com.vilin.spring.chapter05.bankapp.beanpostprocessor.common.DependencyResolver;
import com.vilin.spring.chapter05.bankapp.beanpostprocessor.common.MyApplicationContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

public class DependencyResolutionBeanPostProcessor implements BeanPostProcessor,
		Ordered {
	private MyApplicationContext myApplicationContext;
	private int order;
	private static final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
	
	public DependencyResolutionBeanPostProcessor() {
		logger.info("Created DependencyResolutionBeanPostProcessor instance");
	}
	
	public void setOrder(int order) {
		this.order = order;
	}
	
	@Override
	public int getOrder() {
		return order;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		logger.info("DependencyResolutionBeanPostProcessor's postProcessBeforeInitialization method invoked for bean " + beanName + " of type " + bean.getClass());
		if (bean instanceof DependencyResolver) {
			((DependencyResolver) bean).resolveDependency(myApplicationContext);
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		logger.info("DependencyResolutionBeanPostProcessor's postProcessAfterInitialization method invoked for bean " + beanName + " of type " + bean.getClass());
		return bean;
	}

	public void setMyApplicationContext(
			MyApplicationContext myApplicationContext) {
		this.myApplicationContext = myApplicationContext;
	}

}
