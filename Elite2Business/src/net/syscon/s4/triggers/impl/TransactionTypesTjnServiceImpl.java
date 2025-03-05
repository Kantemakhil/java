package net.syscon.s4.triggers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.triggers.TransactionTypesTjnRepository;
import net.syscon.s4.triggers.TransactionTypesTjnService;
@Service
public class TransactionTypesTjnServiceImpl implements  TransactionTypesTjnService{
@Autowired
private TransactionTypesTjnRepository transactionTypesTjnRepository;
	@Override
	public Integer transactiontypesTJNTrigger(List<TransactionTypes> lstTransactionPayess) {
		return transactionTypesTjnRepository.transactiontypesTJNTrigger(lstTransactionPayess);
	}

}
