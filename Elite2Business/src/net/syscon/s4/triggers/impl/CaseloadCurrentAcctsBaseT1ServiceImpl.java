package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.triggers.CaseloadCurrentAcctsBaseT1Repository;
import net.syscon.s4.triggers.CaseloadCurrentAcctsBaseT1Service;

@Service
public class CaseloadCurrentAcctsBaseT1ServiceImpl implements CaseloadCurrentAcctsBaseT1Service {
	
     @Autowired
	private CaseloadCurrentAcctsBaseT1RepositoryImpl caseloadCurrentAcctsBaseT1Repository;
     
	@Override
	public void caseloadCurrentAcctBaseT1Trigger(String caseloadId, Long accountCode,String bankAccountNumber) {
		
		String chqBooksExistsFlag=caseloadCurrentAcctsBaseT1Repository.gettingCheckBookExistFlag(caseloadId,accountCode) ;
		
		if (chqBooksExistsFlag.equals("Y")) {
			caseloadCurrentAcctsBaseT1Repository.updatingBankChequeBooks(caseloadId,accountCode,bankAccountNumber);
		}
	}

}
