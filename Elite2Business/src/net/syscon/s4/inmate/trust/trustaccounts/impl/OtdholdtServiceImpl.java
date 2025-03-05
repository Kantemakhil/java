package net.syscon.s4.inmate.trust.trustaccounts.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inmate.trust.trustaccounts.OtdholdtRepository;
import net.syscon.s4.inmate.trust.trustaccounts.OtdholdtService;
import net.syscon.s4.pkgs.deductions.DeductionsService;
import net.syscon.s4.pkgs.trust.TrustService;

/**
 * Class OtdholdtServiceImpl
 */
@Service
public class OtdholdtServiceImpl extends BaseBusiness implements OtdholdtService {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdholdtServiceImpl.class.getName());

	
	@Autowired
	private OtdholdtRepository otdholdtRepository;

	@Autowired
	private EliteDateService dateService;
	@Autowired
	private TrustService trustService;
	@Autowired
	private DeductionsService deductionsService;

	/**
	 * Creates new OtdholdtServiceImpl class Object
	 */
	public OtdholdtServiceImpl() {
		// OtdholdtServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<OffenderTransactions> offBkgOnCheckDeleteMaster(final OffenderTransactions paramBean) {
		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public ReferenceCodes cgfkchkOffTxnOffTxnRef(final ReferenceCodes paramBean) {
		return  otdholdtRepository.cgfkchkOffTxnOffTxnRef(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<Object> cgwhenNewFormInstance() {
		return null;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public BigDecimal offTxnExecuteQuery(final Offenders searchRecord) {
		return otdholdtRepository.offTxnExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_TXN
	 *
	 * 
	 */
	@Transactional(rollbackFor= Exception.class)
	public Map<String,Object> offTxnCommit(final OffenderTransactionsCommitBean commitBean) {
		Map<String,Object> returnMap = new HashMap<>();
	    Integer liReturn = 0;
		// insertRecords
		List<OffenderTransactions> lstOffTrans = new ArrayList<OffenderTransactions>();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			try {
				for (final OffenderTransactions objTrans : commitBean.getInsertList()) {
					if (objTrans.getOffenderId() != null) {
						liReturn = 0;
						lstOffTrans = new ArrayList<OffenderTransactions>();
						objTrans.setCreateUserId(commitBean.getCreateUserId());
						logger.info(this.getClass().getName()+" preInsert ");
						objTrans.setTxnId(otdholdtRepository.preInsert());
						returnMap.put("txnId", objTrans.getTxnId());
						objTrans.setTxnEntrySeq(1);
						objTrans.setTxnEntryDate(dateService.getDBTime());
						objTrans.setTxnType("HOA");
						objTrans.setTxnAdjustedFlag("N");
						objTrans.setHoldClearFlag("N");
						objTrans.setSlipPrintedFlag("N");
						logger.info(this.getClass().getName()+" getMaxOffenderBookId ");
						objTrans.setOffenderBookId(Long.valueOf(otdholdtRepository.getMaxOffenderBookId(objTrans.getOffenderId())));
						objTrans.setTxnPostingType("DR");
						objTrans.setModifyDate(dateService.getDBTime());
						logger.info(this.getClass().getName()+" preInsertHoldNumber ");
						objTrans.setHoldNumber(otdholdtRepository.preInsertHoldNumber());
						returnMap.put("holdNumber", objTrans.getHoldNumber());
						lstOffTrans.add(objTrans);
						logger.info(this.getClass().getName()+" offTxnInsertOffenderTransactions ");
						liReturn = otdholdtRepository.offTxnInsertOffenderTransactions(lstOffTrans);
						if (liReturn == 1) {
							liReturn =	trustService.updateOffenderBalance(objTrans,commitBean.getCreateUserId());
							if (liReturn == 1) {
								liReturn=trustService.processGlTransNew(objTrans.getCaseloadId(), objTrans.getTxnType(), null, objTrans.getTxnEntryAmount().doubleValue(), objTrans.getTxnId(), objTrans.getTxnEntryDate(), objTrans.getTxnEntryDesc(), objTrans.getTxnEntrySeq(), "OTDHOLDT", objTrans.getOffenderId().intValue(), objTrans.getOffenderBookId(), objTrans.getSubAccountType(), null, 0, 0, null, 0, objTrans.getRemitterId(),objTrans.getCreateUserId());	                                      	
								if (liReturn >= 1  ) {
									liReturn = otdholdtRepository.updateOffTrustAccounts(objTrans);
								} else {
										 throw new RuntimeException("In offTxnCommit method from processGlTransNew :");
									}
								if (liReturn >= 1) {
								deductionsService.getAcAndSetIndDate(objTrans.getOffenderId(), objTrans.getCaseloadId(),commitBean.getCreateUserId());
									liReturn = updateSubacHoldbalance(objTrans);
									
								}else {
									throw new RuntimeException("In offTxnCommit method from processGlTransNew :");
								}
								}
							}
						}
						if (liReturn == 100) {
							returnMap.put("returnVal", liReturn);
							return returnMap;
						}

				}
			} catch (Exception e) {
				logger.error(this.getClass().getName()+" In offTxnCommit method : ", e);
				 throw new RuntimeException("In offTxnCommit method from processGlTransNew :");
			}
		}
		returnMap.put("returnVal", liReturn);
		return returnMap;
	}

	private void getAcAndSetIndDate(Long offenderId, String caseloadId) {
		otdholdtRepository.getAcAndSetIndDate(offenderId, caseloadId);
		
	}

	private Integer updateSubacHoldbalance(OffenderTransactions objTrans) {
		BigDecimal holdBal = otdholdtRepository.otdholdGetSubacHoldbalance(objTrans);
		BigDecimal vHoldBal = new BigDecimal(objTrans.getTxnEntryAmount());
		holdBal = holdBal.add(vHoldBal);
		Integer resultVal = otdholdtRepository.otdholdUpdateSubacHoldbalance(holdBal, objTrans.getCaseloadId(), objTrans.getOffenderId(), objTrans.getSubAccountType(),objTrans.getCreateUserId() );
		return resultVal;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return otdholdtRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 * 
	 */
	@Transactional
	public Integer sysPflCommit(final SystemProfilesCommitBean commitBean) {
		return 0;
	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> cgfkOffTxnSubAccountTypeRecordGroup(final String caseLoadId) {
		return otdholdtRepository.cgfkOffTxnSubAccountTypeRecordGroup(caseLoadId);

	}

	/**
	 * 
	 * @param paramBean
	 * @return
	 */
	public BigDecimal getExistingHoldAmount(final Offenders paramBean) {
		return otdholdtRepository.getExistingHoldAmount(paramBean);
	}

	/**
	 * 
	 * @param paramBean
	 * @return
	 */
	public BigDecimal getSubAccountBalance(final Offenders paramBean) {
		return otdholdtRepository.getSubAccountBalance(paramBean);
	}

}