package net.syscon.s4.triggers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.TransactionPayees;
import net.syscon.s4.triggers.TransactionPayeesTjnRepository;
import net.syscon.s4.triggers.TransactionPayeesTjnService;
@Service
public class TransactionPayeesTjnServiceImpl implements TransactionPayeesTjnService{
@Autowired
private TransactionPayeesTjnRepository transactionPayeesTjnRepository;
	@Override
	public Integer transactionPayessTJNTrigger(List<TransactionPayees> lstTransactionPayess) {
		
		return transactionPayeesTjnRepository.transactionPayessTJNTrigger(lstTransactionPayess);
		}
	}
	

