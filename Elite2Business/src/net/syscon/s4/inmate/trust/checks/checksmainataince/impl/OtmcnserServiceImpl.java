package net.syscon.s4.inmate.trust.checks.checksmainataince.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.sym.Name;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.im.beans.BankChequeBooks;
import net.syscon.s4.im.beans.BankChequeBooksCommitBean;
import net.syscon.s4.im.beans.CaseloadCurrentAccounts;
import net.syscon.s4.im.beans.CaseloadCurrentAccountsCommitBean;
import net.syscon.s4.inmate.trust.checks.checksmainataince.OtmcnserRepository;
import net.syscon.s4.inmate.trust.checks.checksmainataince.OtmcnserService;

/**
 * class OtmcnserServiceImpl
 * 
 * @author Ramesh
 */
@Service
public class OtmcnserServiceImpl implements OtmcnserService {

	@Autowired
	OtmcnserRepository otmcnserRepository;

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	// public List<AccountCodes> CgfkchkCsldCaCsldAcAcCo(AccountCodes paramBean)
	// {
	/// * CGFK$CHK_CSLD_CA_CSLD_AC_AC_CO */
	// //TODO p_field_level in boolean) is /* is the trigger item level? */
	/// * Validate foreign key value/query lookup data. */
	// List<AccountCodes> accountCodesList =
	// otmcnserRepository.cgfkchkCsldCaCsldAcAcCoc(paramBean);
	// //TODO fetch c
	// //TODO into csld_ca.dsp_account_name;
	// if (c%!found ){
	// throw new Error('form_trigger_failure');
	// }
	// return accountCodesList;
	// }

	/**
	 * Fetch the records from database table method csldCaExecuteQuery
	 * 
	 * @param searchRecord
	 */
	public List<CaseloadCurrentAccounts> csldCaExecuteQuery(CaseloadCurrentAccounts searchRecord) {
		List<CaseloadCurrentAccounts> returnList = otmcnserRepository.csldCaExecuteQuery(searchRecord);
		for (CaseloadCurrentAccounts obj : returnList) {
			String accountName = otmcnserRepository.accountName(obj.getAccountCode(),obj.getCreateUserId());
			obj.setAccountName(accountName);
		}
		return returnList;

	}

	/**
	 * method csldCaCommit return liReturn
	 * 
	 * @param commitBean
	 */
	@Transactional
	public Integer csldCaCommit(CaseloadCurrentAccountsCommitBean commitBean) {
		int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetch the records from database table method bankCbExecuteQuery
	 * 
	 * @param searchRecord
	 */
	public List<BankChequeBooks> bankCbExecuteQuery(BankChequeBooks searchRecord) {
		return otmcnserRepository.bankCbExecuteQuery(searchRecord);

	}

	/**
	 * method bankCbCommit
	 * 
	 * @param commitBean return liReturn
	 * @throws Exception
	 */
	@Transactional(rollbackFor = { Exception.class })
	public Integer bankCbCommit(final BankChequeBooksCommitBean commitBean) throws Exception {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(obj -> {
				obj.setCreateUserId(commitBean.getCreateUserId());
			});
			liReturn = bankcbInsertBankChequeBooks(commitBean.getInsertList(), commitBean.getCreateUserId());
			if (liReturn == 3) {
				throw new Exception("SERIES ALREADY EXIST");
			}
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(obj -> {
				obj.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = otmcnserRepository.bankCbDeleteBankChequeBooks(commitBean.getDeleteList());
		}
		return liReturn;
	}

	private Integer bankcbInsertBankChequeBooks(List<BankChequeBooks> insertList, String userName) {
		Integer retVal = 0;
		for (BankChequeBooks obj : insertList) {
			List<BankChequeBooks> list = new ArrayList<BankChequeBooks>();
			Long bankSeq = otmcnserRepository.getChequeBookSeq();
			obj.setChequeBookSeq(bankSeq);

			String acctNum = otmcnserRepository.getaccountNumber(obj.getCaseloadId(), obj.getAccountCode(), userName);
			if (acctNum != null) {
				obj.setAccountNumber(acctNum);
			}
			String returnListNew = otmcnserRepository.checkifNewSeries(obj.getFirstCheckNumber().longValue(),
					obj.getAccountCode().longValue(), obj.getCaseloadId());
			if (ApplicationConstants.YFLAG.equals(returnListNew)) {
				retVal = 3;
				return retVal;
			}
			list.add(obj);
			retVal = otmcnserRepository.bankcbInsertBankChequeBooks(list);
		}
		return retVal;
	}

	public String checkChecqueBooks(final Long firstChecknum, final Long accountCode,
			final String caseloadId) {
		String returnListNew = otmcnserRepository.checkifNewSeries(firstChecknum, accountCode, caseloadId);
//		if (returnListNew.size() >= 0) {
//			returnListNew.add("Y");
//		} else {
//
//			List<String> returnList = otmcnserRepository.checkChecqueBooks(firstChecknum, lastCheckNum, accountCode,
//					caseloadId);
//			if (returnList.size() >= 0) {
//				returnListNew.add("X");
//			}
		//}

		return returnListNew;
	}

	@Override
	public String checkChecqueBooksLastCheck(Long lastCheckNumLong, Long firstChecknumLong, Long accountCode,
			String caseloadId) {
		return otmcnserRepository.checkChecqueBooksLastCheck(lastCheckNumLong,firstChecknumLong,accountCode,caseloadId);
	}

}