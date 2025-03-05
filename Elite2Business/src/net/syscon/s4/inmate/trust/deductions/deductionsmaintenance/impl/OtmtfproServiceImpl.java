package net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OtmtfproFmbBean;
import net.syscon.s4.common.beans.OtmtfproFmbBeanCommitBean;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.SystemProfiles;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.OtmtfproRepository;
import net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.OtmtfproService;

@Service
public class OtmtfproServiceImpl extends BaseBusiness implements OtmtfproService {

	@Autowired
	private OtmtfproRepository otmtfproRepository;
	/**
	 * Logger object used to print the log in the file
	 */
//private static Logger logger = LogManager.getLogger(OtmtfproServiceImpl.class.getName());

	/**
	 * Creates new OtmtfproServiceImpl class Object
	 */
	public OtmtfproServiceImpl() {
//	OtmtfproServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Corporates> CgfkchkCsldDpDedprofCorp(final Corporates paramBean) {
		/* CGFK$CHK_CSLD_DP_DEDPROF_CORP_ */
		// TODO p_field_level in boolean) is /* is the trigger item level? */
		/* Validate foreign key value/query lookup data. */
//		if ((csldDpModel.payeeCorporateId !== null) ){
		List<Corporates> corporates = otmtfproRepository.cgfkchkCsldDpDedprofCorp(paramBean);
		// TODO fetch c
		// TODO into csld_dp.dsp_corporate_name;
//		if (c%!found ){
//		   throw new Error('form_trigger_failure');
//		}
//		}
		return corporates;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<AccountCodes> CgfkchkCsldDpDedprofAcCo(AccountCodes paramBean) {
		/* CGFK$CHK_CSLD_DP_DEDPROF_AC_CO */
		// TODO p_field_level in boolean) is /* is the trigger item level? */
		/* Validate foreign key value/query lookup data. */
		List<AccountCodes> accountCodesList = otmtfproRepository.cgfkchkCsldDpDedprofAcCo(paramBean);
		// TODO fetch c
		// TODO into csld_dp.dsp_account_name;
//		if (c%!found ){
//		   throw new Error('form_trigger_failure');
//		}
		return accountCodesList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<DeductionTypes> CgfkchkCsldDpDedprofDedty(DeductionTypes paramBean) {
		/* CGFK$CHK_CSLD_DP_DEDPROF_DEDTY */
		// TODO p_field_level in boolean) is /* is the trigger item level? */
		/* Validate foreign key value/query lookup data. */
		List<DeductionTypes> deductionTypesList = otmtfproRepository.cgfkchkCsldDpDedprofDedty(paramBean);
		// TODO fetch c
		// TODO into csld_dp.dsp_deduction_desc;
//		if (c%!found ){
//		   throw new Error('form_trigger_failure');
//		}
		return deductionTypesList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Object> CgwhenNewFormInstance() {
		/* CG$WHEN_NEW_FORM_INSTANCE */
		/* CGGN$GET_DATE_AND_USER */
		/* Get the current user and date; store in items for general use */
		// TODO fetch c
		// TODO into cg$ctrl.cgu$sysdate, cg$ctrl.cgu$user;
//		if (c%!found ){
//		  //TODO message('internal error no row in table sys.dual');
//		   throw new Error('form_trigger_failure');
//		}
		// TODO
		/* CGLY$COPY_USER_DATE */
		/* Values from USER and DATE items on page 0 in the control */
		/* block will be "fired" to all other USER and DATE items */
		/* on other pages. */
		// TODO cg$ctrl.cg$dt = cgu$sysdate;
		// TODO cg$ctrl.cg$us = cgu$user;
		// TODO
		/* CGLY$INIT_CANVASES */
		/* Call procedure to ensure correct canvases are visible */
		;
		// TODO
		/* CGBS$STARTUP */
		/* Perform start-up processing for Block Synchronisation */
		/* Set the defaults for the globals */
		// TODO default_value('a', 'global.cg$query_mode');
		/* Set the relations to the default query mode */
		// TODO cgbs$.set_relations( global.cg$query_mode );
		/* Set the query mode items */
		// TODO cgbs$set_query_mode( global.cg$query_mode );
		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<TransactionTypes> CgfkchkCsldDdCsldDdTxnty(TransactionTypes paramBean) {
		/* CGFK$CHK_CSLD_DD_CSLD_DD_TXNTY */
		// TODO p_field_level in boolean) is /* is the trigger item level? */
		/* Validate foreign key value/query lookup data. */
		List<TransactionTypes> transactionTypesList = otmtfproRepository.cgfkchkCsldDdCsldDdTxnty(paramBean);
		// TODO fetch c
		// TODO into csld_dd.dsp_description;
//		if (c%!found ){
//		   throw new Error('form_trigger_failure');
//		}
		return transactionTypesList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OtmtfproFmbBean> csldDpExecuteQuery(OtmtfproFmbBean searchRecord) {
//	csldDpaccountCode2
		List<OtmtfproFmbBean> resultList = otmtfproRepository.csldDpExecuteQuery(searchRecord);
		Corporates crop = new Corporates();
		List<Corporates> cropList = new ArrayList<Corporates>();

		for (final OtmtfproFmbBean ele : resultList) {
			if (ele.getCsldDpaccountCode() != null) {
				ele.setCsldDpaccountCode2(ele.getCsldDpaccountCode().toString());
				if (ele.getCsldDppayeeCorporateId() != null) {
					crop.setCorporateId(ele.getCsldDppayeeCorporateId());
					cropList = otmtfproRepository.cgfkchkCsldDpDedprofCorp(crop);
					if (cropList != null && cropList.size() > 0) {
						ele.setCsldDpdspCorporateName(cropList.get(0).getCorporateName());
					}
				}

			}
		}
		return resultList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCSLD_DP
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer csldDpCommit(OtmtfproFmbBeanCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final OtmtfproFmbBean obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = csldDpPreInsert(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final OtmtfproFmbBean obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = otmtfproRepository.csldDpUpdateCaseloadDeductionProfiles(commitBean.getUpdateList());
		}
		// DeleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (final OtmtfproFmbBean obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = otmtfproRepository.csldDpDeleteCaseloadDeductionProfiles(commitBean.getDeleteList());
		}
		return liReturn;
	}

	private int csldDpPreInsert(List<OtmtfproFmbBean> insertList) {
		int liReturn = 0;
//		insertList.forEach(element -> {
//			BigDecimal extPrt = BigDecimal.ZERO;
//			element.setCsldDpfifoFlag("Y");
//			element.setCsldDppercentage(BigDecimal.ZERO);
//			try {
//				extPrt = otmtfproRepository.getExternalPriorityNo(element.getCsldDpcaseloadId());
//			} catch (Exception e) {
//				extPrt = BigDecimal.ONE;
//			}
//			element.setCsldDpinternalPriorityNo(BigDecimal.ONE);
//			element.setCsldDpexternalPriorityNo(extPrt);
//			
//		});

		for (OtmtfproFmbBean element : insertList) {
			BigDecimal extPrt = BigDecimal.ZERO;
			element.setCsldDpfifoFlag("Y");
			element.setCsldDppercentage(BigDecimal.ZERO);
			try {
				extPrt = otmtfproRepository.getExternalPriorityNo(element.getCsldDpcaseloadId());
			} catch (Exception e) {
				extPrt = BigDecimal.ONE;
			}
			element.setCsldDpinternalPriorityNo(BigDecimal.ONE);
			element.setCsldDpexternalPriorityNo(extPrt);
			liReturn = otmtfproRepository.csldDpInsertCaseloadDeductionProfiles(element);
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OtmtfproFmbBean> csldDdExecuteQuery(OtmtfproFmbBean searchRecord) {
		return otmtfproRepository.csldDdExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCSLD_DD
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer csldDdCommit(OtmtfproFmbBeanCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final OtmtfproFmbBean obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = otmtfproRepository.csldDdInsertCaseloadDeductionDetails(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final OtmtfproFmbBean obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = otmtfproRepository.csldDdUpdateCaseloadDeductionDetails(commitBean.getUpdateList());
		}

		liReturn = csldDPostCommit(commitBean);

		// delete Records
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (final OtmtfproFmbBean obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = otmtfproRepository.csldDdDeleteCaseloadDeductionDetails(commitBean.getDeleteList());
		}

		return liReturn;
	}

	private Integer csldDPostCommit(OtmtfproFmbBeanCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			liReturn = csldDPostFormCommit(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			liReturn = csldDPostFormCommit(commitBean.getUpdateList());
		}

		return liReturn;
	}

	private int csldDPostFormCommit(List<OtmtfproFmbBean> commitList) {
		int liReturn = 0;
		for (OtmtfproFmbBean element : commitList) {
			if (null != element.getCsldDdreceiptTxnType()) {
				BigDecimal receiptPercentage = otmtfproRepository.getCsldddMaxPercentage(element.getCsldDdcaseloadId(),
						element.getCsldDddeductionType());
				liReturn = otmtfproRepository.updateCsldDdCaseloadDeductionProfiles(receiptPercentage,
						element.getCsldDdcaseloadId(), element.getCsldDddeductionType(), element.getCreateUserId());
			} else {
				liReturn = otmtfproRepository.updateCsldDdCaseloadDeductionProfiles(BigDecimal.ZERO,
						element.getCsldDdcaseloadId(), element.getCsldDddeductionType(), element.getCreateUserId());
			}

		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<SystemProfiles> sysPflExecuteQuery(SystemProfiles searchRecord) {
		return otmtfproRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer sysPflCommit(SystemProfilesCommitBean CommitBean) {
		return null;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<Corporates> cgfkCsldDpPayeeCorporateIRecordGroup() {
		return otmtfproRepository.cgfkCsldDpPayeeCorporateIRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<TransactionTypes> cgfkCsldDdReceiptTxnTypeRecordGroup(final String caseloadType) {
		return otmtfproRepository.cgfkCsldDdReceiptTxnTypeRecordGroup(caseloadType);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<DeductionTypes> cgfkCsldDpDeductionTypeRecordGroup(final String caseloadType) {
		return otmtfproRepository.cgfkCsldDpDeductionTypeRecordGroup(caseloadType);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<AccountCodes> cgfkCsldDpAccountCodeRecordGroup(final String caseloadType) {
		return otmtfproRepository.cgfkCsldDpAccountCodeRecordGroup(caseloadType);

	}

	@Override
	public String omsUtilsDisplayUserMessage(final BigDecimal msgNo, final String applnCode) {
		try {
			return otmtfproRepository.omsUtilsDisplayUserMessage(msgNo, applnCode);
		} catch (Exception e) {
			return "Message number " + msgNo + " not found in SYSTEM_MESSAGES table. Call support";
		}
	}

	@Override
	public List<CaseloadDeductionProfiles> chkDuplicateDedType(final String caseloadId, final String deductionType) {
		return otmtfproRepository.chkDuplicateDedType(caseloadId, deductionType);
	}

}