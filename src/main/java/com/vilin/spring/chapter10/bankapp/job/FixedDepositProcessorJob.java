package com.vilin.spring.chapter10.bankapp.job;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;

import com.vilin.spring.chapter10.bankapp.dao.FixedDepositDao;
import com.vilin.spring.chapter10.bankapp.domain.FixedDepositDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.ConfigurableMimeFileTypeMap;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.util.List;

public class FixedDepositProcessorJob {
	@Autowired
	@Qualifier(value = "fixedDepositDao")
	private FixedDepositDao myFixedDepositDao;

	@Autowired
	private transient JavaMailSender mailSender;

	@Autowired
	@Qualifier("requestProcessedTemplate")
	private transient SimpleMailMessage simpleMailMessage;

	private List<FixedDepositDetails> getInactiveFixedDeposits() {
		return myFixedDepositDao.getInactiveFixedDeposits();
	}

	public void sendEmail() throws AddressException, MessagingException {
		List<FixedDepositDetails> inactiveFds = getInactiveFixedDeposits();
		for (FixedDepositDetails fd : inactiveFds) {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
			mimeMessageHelper.setTo(fd.getEmail());
			mimeMessageHelper.setSubject(simpleMailMessage.getSubject());
			mimeMessageHelper.setText(simpleMailMessage.getText());
			mimeMessageHelper.setFrom(simpleMailMessage.getFrom());
			mailSender.send(mimeMessage);
		}
		myFixedDepositDao.setFixedDepositsAsActive(inactiveFds);
	}
}
