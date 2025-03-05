package net.syscon.s4.inmate.trust.financialsmaintenance.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.beans.FreezeDisbursements;
import net.syscon.s4.inmate.beans.FreezeDisbursementsCommitBean;
import net.syscon.s4.inmate.trust.financialsmaintenance.OtmfreezRepository;
import net.syscon.s4.inmate.trust.financialsmaintenance.OtmfreezService;

/**
 * Class OtmfreezServiceImpl
 * 
 * @author Arkin Software Technologies
 * @version 1.0
 */
@Service
public class OtmfreezServiceImpl extends BaseBusiness implements OtmfreezService {

	@Autowired
	private OtmfreezRepository otmfreezRepository;

	/**
	 * Logger object used to print the log in the file
	 */

	/**
	 * Creates new OtmfreezServiceImpl class Object
	 */
	public OtmfreezServiceImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<FreezeDisbursements> freDisExecuteQuery(final FreezeDisbursements searchRecord) {
		List<FreezeDisbursements> beanList = null;
		if(searchRecord.getTxnType()!=null){
		final String code= otmfreezRepository.getCode(searchRecord.getTxnType(),searchRecord.getCaseloadType());
		searchRecord.setTxnType(code);
		}
		
		final List<FreezeDisbursements> returnList = otmfreezRepository.freDisExecuteQuery(searchRecord);
		for (final FreezeDisbursements obj : returnList) {
			beanList = otmfreezRepository.getRegDescription(obj.getAccountCode(), obj.getCaseloadType());
			for (final FreezeDisbursements bean : beanList) {
				if (bean.getSubAccountType() != null) {
					obj.setReg(bean.getSubAccountType());
				}
				if (bean.getAccountName() != null) {
					obj.setAccountName(bean.getAccountName());
				}
			}
			if (obj.getTxnType() != null) {
				final String description = otmfreezRepository.getTxnDescription(obj.getCaseloadType(),
						obj.getTxnType());
				obj.setTxnDescription(description);
				if (obj.getCaseloadType() !=null) {
					final String txnUsage = otmfreezRepository.getTxnUsage(obj.getCaseloadType(), obj.getTxnType());
					obj.setTxnUsage(txnUsage);
				}
			}
		}

		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstFRE_DIS
	 *
	 */
	@Transactional
	public Integer freDisCommit(final FreezeDisbursementsCommitBean commitBean) {
		Integer liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (FreezeDisbursements obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = freDisInsertFreezeDisbursements(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (FreezeDisbursements obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = otmfreezRepository.freDisUpdateFreezeDisbursements(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (FreezeDisbursements obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = otmfreezRepository.freDisDeleteFreezeDisbursements(commitBean.getDeleteList());
		}
		return liReturn;
	}

	private Integer freDisInsertFreezeDisbursements(final List<FreezeDisbursements> insertList) {
		for(final FreezeDisbursements obj: insertList) {
		final String code= otmfreezRepository.getCode(obj.getTxnDescription(),obj.getCaseloadType());
		obj.setTxnType(code);
		}
		return otmfreezRepository.freDisInsertFreezeDisbursements(insertList);
	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<TransactionTypes> cgfkFreDisTxnTypeRecordGroup(final String caseloadType) {
		final List<TransactionTypes> returnList= otmfreezRepository.cgfkFreDisTxnTypeRecordGroup(caseloadType);	
		returnList.forEach(result->{
			result.setCode(result.getDescription());
			result.setDescription(result.getTxnType());
		});
		return returnList;
		

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<AccountCodes> cgfkFreDisAccountCodeRecordGroup(final String caseloadType) {
		final List<AccountCodes> returnList= otmfreezRepository.cgfkFreDisAccountCodeRecordGroup(caseloadType);
		
		returnList.forEach(result->{
			result.setCode(result.getAccountName());
			result.setDescription(result.getAccountCode().toString()); 
			result.setSubAccountType(result.getSubAccountType());
		});
		return returnList;

	}

	@Override
	public String getTxnUage(final String txnType, final String caseloadType) {
		String code =null;
		if(txnType !=null){
		 code= otmfreezRepository.getCode(txnType,caseloadType);
		}
		return otmfreezRepository.getTxnUage(code,caseloadType);
	}

}