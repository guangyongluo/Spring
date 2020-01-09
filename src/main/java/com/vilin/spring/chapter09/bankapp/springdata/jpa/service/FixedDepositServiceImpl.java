package com.vilin.spring.chapter09.bankapp.springdata.jpa.service;

import com.vilin.spring.chapter09.bankapp.springdata.jpa.domain.FixedDepositDetails;
import com.vilin.spring.chapter09.bankapp.springdata.jpa.exceptions.NoFixedDepositFoundException;
import com.vilin.spring.chapter09.bankapp.springdata.jpa.repository.BankAccountRepository;
import com.vilin.spring.chapter09.bankapp.springdata.jpa.repository.FixedDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@Service
public class FixedDepositServiceImpl implements FixedDepositService {

	@Autowired
	private FixedDepositRepository fixedDepositRepository;

	@Autowired
	private BankAccountRepository bankAccountRepository;

	@Override
	@Transactional
	public int createFixedDeposit(FixedDepositDetails fdd) throws Exception {
		bankAccountRepository.subtractFromAccount(fdd.getBankAccountId().getAccountId(), fdd.getFdAmount());
		return fixedDepositRepository.save(fdd).getFixedDepositId();
	}

	@Override
	@Transactional
	public FixedDepositDetails getFixedDeposit(int fixedDepositId) {
		return fixedDepositRepository.findOne(fixedDepositId);
	}

	public long count() {
		return fixedDepositRepository.count();
	}

	public long countByTenure(int tenure) {
		return fixedDepositRepository.countByTenure(tenure);
	}

	@Transactional
	public List<FixedDepositDetails> removeByTenure(int tenure) {
		return fixedDepositRepository.removeByTenure(tenure);
	}

	@Override
	public List<FixedDepositDetails> findByTenure(int tenure) {
		try {
			return fixedDepositRepository.findByTenure(tenure);
		} catch (NoFixedDepositFoundException e) {
			return new ArrayList<FixedDepositDetails>();
		}
	}

	@Override
	public List<FixedDepositDetails> findByTenureLessThan(int tenure) {
		return fixedDepositRepository.findByTenureLessThan(tenure);
	}

	@Override
	public List<FixedDepositDetails> findByFdAmountGreaterThan(int fdAmount) {
		return fixedDepositRepository.findByFdAmountGreaterThan(fdAmount);
	}

	@Override
	public List<FixedDepositDetails> findByCustomQuery(int tenure, int fdAmount, String active) {
		return fixedDepositRepository.findByCustomQuery(tenure, fdAmount, active);
	}

	public List<FixedDepositDetails> findByTenure(int tenure, Pageable pageable) {
		return fixedDepositRepository.findByTenure(tenure, pageable);
	}

	public List<FixedDepositDetails> findByTenure(int tenure, Sort sort) {
		return fixedDepositRepository.findByTenure(tenure, sort);
	}

	@Override
	public Slice<FixedDepositDetails> findByFdAmount(int amount, Pageable pageable) {
		return fixedDepositRepository.findByFdAmount(amount, pageable);
	}

	@Override
	public List<FixedDepositDetails> findTop2ByTenure(int tenure) {
		return fixedDepositRepository.findTop2ByTenure(tenure);
	}

	@Override
	public List<FixedDepositDetails> findTop2ByOrderByFdCreationDateDesc() {
		return fixedDepositRepository.findTop2ByOrderByFdCreationDateDesc();
	}

	@Override
	public List<FixedDepositDetails> findByTenureAndFdAmount(int tenure, int fdAmount) {
		return fixedDepositRepository.findByTenureAndFdAmount(tenure, fdAmount);
	}

	@Override
	public Stream<FixedDepositDetails> findAllByTenure(int tenure) {
		return fixedDepositRepository.findAllByTenure(tenure);
	}

	@Override
	public CompletableFuture<List<FixedDepositDetails>> findAllByFdAmount(int fdAmount) {
		return fixedDepositRepository.findAllByFdAmount(fdAmount);
	}
	
	//-- QuerDsl example
//	@Override
//	public Iterable<FixedDepositDetails> getHighValueFds() {
//		Predicate whereClause = QFixedDepositDetails.fixedDepositDetails.active.eq("Y")
//				.and(QFixedDepositDetails.fixedDepositDetails.fdAmount.gt(1000))
//				.and(QFixedDepositDetails.fixedDepositDetails.tenure.between(6, 12));
//		return fixedDepositRepository.findAll(whereClause);
//		return null;
//	}

	//-- Query by Example
	@Override
	public Iterable<FixedDepositDetails> getAllFds() {
		FixedDepositDetails fd = new FixedDepositDetails();
		fd.setActive("Y");
		fd.setFdAmount(500);
		fd.setTenure(6);
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("fixedDepositId");
		Example<FixedDepositDetails> fdExample = Example.of(fd, matcher);
		return fixedDepositRepository.findAll(fdExample);
	}
}
