package net.syscon.s4.inmate.trust.trustaccounts.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.OffenderTrustAccounts;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inmate.beans.OffenderTrustAccountsCommitBean;
import net.syscon.s4.inmate.trust.trustaccounts.OtdopctaRepository;
import net.syscon.s4.inmate.trust.trustaccounts.OtdopctaService;
import net.syscon.s4.pkgs.deductions.DeductionsService;
import net.syscon.s4.pkgs.trust.TrustService;

/**
 * Class OtdopctaServiceImpl
 */
@Service
public class OtdopctaServiceImpl extends BaseBusiness implements OtdopctaService {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdopctaServiceImpl.class.getName());

	@Autowired
	private OtdopctaRepository otdopctaRepository;

	@Autowired
	private EliteDateService dateService;

	@Autowired
	private TrustService trustService;

	@Autowired
	private DeductionsService deductionsService;

	/**
	 * Creates new OtdopctaServiceImpl class Object
	 */
	public OtdopctaServiceImpl() {
		// OtdopctaServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Object> cgwhenNewFormInstance() {
		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<OffenderSubAccounts> cgrichkOffenderTrustAccoun(final OffenderSubAccounts paramBean) {
		return otdopctaRepository.cgrichkOffenderTrustAccoun(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderTrustAccounts> offTaExecuteQuery(final OffenderTrustAccounts searchRecord) {
		return otdopctaRepository.offTaExecuteQuery(searchRecord);

	}

	@Transactional(rollbackFor = Exception.class)
	public Integer offTaCommit(final OffenderTrustAccountsCommitBean commitBean) {
		int liReturn = 0;
		OffenderTransactions objTrans = null;
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			objTrans = new OffenderTransactions();
			try {
				for (final OffenderTrustAccounts objTrustAcc : commitBean.getUpdateList()) {
					objTrans.setOffenderId(objTrustAcc.getOffenderId());
					objTrans.setCaseloadId(objTrustAcc.getCaseloadId());
					objTrans.setCaseloadType(objTrustAcc.getCaseloadType());
					//final String pOpenAcc = otdopctaRepository.checkAccountSatus(objTrans);
					BigDecimal offenderId = new BigDecimal(objTrustAcc.getOffenderId());
					 Map<String, Object> map = trustService.chkAccountStatus(objTrustAcc.getCaseloadId(),offenderId);
					  Set<Entry<String, Object>> entrySet = map.entrySet();
					  String pOpenAcc = null;
					for (Entry<String, Object> entry : entrySet) {
						 pOpenAcc = entry.getValue().toString();
					}
					if (pOpenAcc != null && "Y".equals(pOpenAcc) && "N".equals(objTrustAcc.getAccountClosedFlag())) {
						objTrustAcc.setAccountClosedFlag("N");
						final String txnType = otdopctaRepository.getTransactionType(objTrustAcc.getCaseloadId());
						if (txnType != null) {
							objTrans.setTxnType(txnType);
							final AccountCodes lstAccCodes = otdopctaRepository.getSubAccountType(objTrans);
							if (lstAccCodes != null && lstAccCodes.getSubAccountType() != null
									&& lstAccCodes.getTxnPostingType() != null) {
								objTrans.setSubAccountType(lstAccCodes.getSubAccountType());
								objTrans.setTxnPostingType(lstAccCodes.getTxnPostingType());
							} else {
								return 101;
							}
							objTrans.setTxnId(objTrustAcc.getNbtTxnId());
							objTrans.setTxnEntrySeq(1);
							objTrans.setOffenderBookId(objTrustAcc.getOffenderBookId());
							objTrans.setTxnEntryDesc("Re-Open Closed Account");
							objTrans.setTxnEntryDate(dateService.getDBTime());
							objTrans.setTxnEntryAmount(0.0);
							objTrans.setSlipPrintedFlag("N");
							objTrans.setCaseloadId(objTrustAcc.getCaseloadId());
							objTrans.setOffenderId(objTrustAcc.getOffenderId());
							objTrans.setCreateUserId(objTrustAcc.getCreateUserId());
							// liReturn = otdopctaRepository.insertIntoOffenderTransaction(objTrans);
							liReturn = trustService.insertIntoOffenderTrans(objTrans);
							if (liReturn == 1) {
								// liReturn = otdopctaRepository.processGlTransNew(objTrans);								
								liReturn = trustService.processGlTransNew(objTrans.getCaseloadId(),
										objTrans.getTxnType(), null, 0.0, objTrans.getTxnId(),
										objTrans.getTxnEntryDate(), objTrans.getTxnEntryDesc(),
										objTrans.getTxnEntrySeq(), "OTDOPCTA", objTrans.getOffenderId().intValue(),
										objTrans.getOffenderBookId(), null, objTrans.getSubAccountType(), null, null,
										"", 0, null, objTrans.getCreateUserId());
								if (liReturn >= 1) {
									liReturn = otdopctaRepository.updateOffenderSubAccounts(objTrans);
									// otdopctaRepository.getAcAndSetIndDate(objTrans);
									deductionsService.getAcAndSetIndDate(objTrustAcc.getOffenderId(),
											objTrustAcc.getCaseloadId(), objTrustAcc.getCreateUserId());
									liReturn = otdopctaRepository
											.offTaUpdateOffenderTrustAccounts(commitBean.getUpdateList());
								} else {
									throw new RuntimeException("In offTaCommit method from processGlTransNew :");
								}
							}
						} else {
							return 100;
						}
					}
				}

			} catch (Exception e) {
				logger.error("In offTxnCommit method : ", e);
				throw new RuntimeException("In offTxnCommit method from processGlTransNew :");
			}
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = otdopctaRepository.offTaDeleteOffenderTrustAccounts(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return otdopctaRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 */
	@Transactional
	public Integer sysPflCommit(final SystemProfilesCommitBean commitBean) {
		return 0;
	}

	public Integer preInsert() {
		return otdopctaRepository.preInsert();
	}

}