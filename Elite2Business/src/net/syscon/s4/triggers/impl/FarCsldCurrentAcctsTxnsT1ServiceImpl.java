package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.triggers.FarCsldCurrentAcctsTxnsT1Repository;
import net.syscon.s4.triggers.FarCsldCurrentAcctsTxnsT1Service;

@Service
public class FarCsldCurrentAcctsTxnsT1ServiceImpl implements FarCsldCurrentAcctsTxnsT1Service {

	@Autowired
	private FarCsldCurrentAcctsTxnsT1Repository farCsldCurrentAcctsTxnsT1Repository;

	@Override
	public void farCsldCurrentAcctsTxnsT1Trigger(final AccountCodes txns) {
		String vModuleName = null;
		vModuleName = farCsldCurrentAcctsTxnsT1Repository.getModuleName(txns.getTxnId());
		farCsldCurrentAcctsTxnsT1Repository.insertFarCsldCurrentAcctsTxns(txns, vModuleName);

	}

}
