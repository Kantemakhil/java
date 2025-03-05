package net.syscon.s4.inmate.trust.deductions.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderDeductionsCommitBean;
import net.syscon.s4.inmate.trust.deductions.OtiopinqRepository;
import net.syscon.s4.inmate.trust.deductions.OtiopinqService;

@Service
public class OtiopinqServiceImpl extends BaseBusiness implements OtiopinqService {

	@Autowired
	private OtiopinqRepository otiopinqRepository;


	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public Object offBkgPreDelete() {
		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Object> CgwhenNewFormInstance() {
		return null;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderDeductions> offDedExecuteQuery(final OffenderDeductions searchRecord) {
		BigDecimal temp10 = null;
		Long tempOff = null;
		String tempCl = null;
		String tempDedType = null;
		BigDecimal tempMaxMonAmt = null;
		BigDecimal temp11 = null;
		BigDecimal temp12 = null;
		BigDecimal tempVar = null;
		BigDecimal tempPrior = null;
		Long tempOffDedId = null;
		BigDecimal vWrtAmt = null;
		BigDecimal lvAdjAmount = null;
		BigDecimal lvAmount = null;
		BigDecimal lvRecAmount = null;
		List<OffenderDeductions> resultBean = otiopinqRepository.offDedExecuteQuery(searchRecord);
		for (OffenderDeductions bean : resultBean) {
			bean = populateWriteOff(bean);
			temp10 = bean.getMaxTotalAmount();
			tempMaxMonAmt = bean.getMaxMonthlyAmount();
			tempOff = bean.getOffenderId();
			tempCl = bean.getCaseloadId();
			tempDedType = bean.getDeductionType();
			tempPrior = bean.getDeductionPriority();
			tempOffDedId = bean.getOffenderDeductionId();
			if (bean.getNbtWriteOffAmount() != null) {
				vWrtAmt = bean.getNbtWriteOffAmount();
			} else {
				vWrtAmt = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
			}
			if (temp10 != null) {
				temp11 = otiopinqRepository.otiopinqSubMaxTotDedctAmt(tempOff, tempCl, tempDedType, tempPrior);
				if (temp11 == null) {
					temp11 = BigDecimal.ZERO;
				}
				bean.setNbtDeductionAmount((temp11.subtract(vWrtAmt)).setScale(2, BigDecimal.ROUND_HALF_UP).toString());

			} else if (temp10 == null && tempMaxMonAmt != null) {
				try {
					tempVar = otiopinqRepository.getDedAmtOnDate(tempOffDedId);
					temp12 = tempMaxMonAmt.subtract(tempVar);
					bean.setNbtDeductionAmount((temp12.setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
				} catch (Exception e) {
					tempMaxMonAmt = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
				}

			} else if (temp10 == null && tempMaxMonAmt == null) {
				lvAdjAmount = otiopinqRepository.adjustmentAmountC(tempOffDedId);
				Map<String, Object> lvTotalAmount = otiopinqRepository.lvTotalAmount(tempOffDedId);
				if (lvTotalAmount != null) {
					if (lvTotalAmount.get("MAX_TOTAL_AMOUNT") != null) {
						lvAmount = new BigDecimal(lvTotalAmount.get("MAX_TOTAL_AMOUNT").toString());
					}
					if (lvTotalAmount.get("DEDUCTION_AMOUNT") != null) {
						lvRecAmount = new BigDecimal(lvTotalAmount.get("DEDUCTION_AMOUNT").toString());
					}
				}
				if (lvAmount != null) {
					bean.setNbtDeductionAmount(lvAmount.subtract(lvRecAmount.subtract(lvAdjAmount))
							.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				} else {
					bean.setNbtDeductionAmount("UNLIMITED");
				}
			}

		}
		return resultBean;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_DED
	 *
	 * 
	 */
	@Transactional
	public Integer offDedCommit(final OffenderDeductionsCommitBean CommitBean) {
		int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<OffenderTransactions> offTxnExecuteQuery(final OffenderTransactions searchRecord) {
		List<OffenderTransactions> resultBean = otiopinqRepository.offTxnExecuteQuery(searchRecord);
		Double tempTxnEntryAmount = null;
		String tempTxnPostingType = null;
		for (OffenderTransactions bean : resultBean) {
			tempTxnEntryAmount = bean.getTxnEntryAmount();
			tempTxnPostingType = bean.getTxnPostingType();
			if (tempTxnEntryAmount != null) {
				if ("CR".equals(tempTxnPostingType)) {
					tempTxnEntryAmount = tempTxnEntryAmount * -1;
					bean.setNbtTxnEntryAmount2(tempTxnEntryAmount);
				} else if ("DR".equals(tempTxnPostingType)) {
					bean.setNbtTxnEntryAmount2(tempTxnEntryAmount);
				}
			}
		}
		return resultBean;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_TXN
	 *
	 */
	@Transactional
	public Integer offTxnCommit(final OffenderTransactionsCommitBean CommitBean) {
		int liReturn = 0;
		return liReturn;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return otiopinqRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 * 
	 */
	@Transactional
	public Integer sysPflCommit(final SystemProfilesCommitBean CommitBean) {
		int liReturn = 0;
		return liReturn;
	}

	@Override
	public List<DeductionTypes> cgfkchkOffDedOffDedDed() {
		return otiopinqRepository.cgfkchkOffDedOffDedDed();
	}

	@Override
	public String chkDedCat(final String deductionType) {
		return otiopinqRepository.chkDedCat(deductionType);
	}

	private OffenderDeductions populateWriteOff(final OffenderDeductions bean) {
		BigDecimal vAdjAmt = bean.getAdjustmentAmount();
		BigDecimal vDedAmt = bean.getDeductionAmount();
		String vDedType = bean.getDeductionType();
		if (vDedAmt == null) {
			vDedAmt = BigDecimal.ZERO;
		}
		if (vAdjAmt == null) {
			vAdjAmt = BigDecimal.ZERO;
		}
		if ("CROB".equals(chkDedCat(vDedType))) {
			if (bean.getAdjustmentReasonCode() != null) {
				if (bean.getMaxTotalAmount().equals(vAdjAmt.add(vDedAmt))) {
					bean.setNbtAdjustmentReasonCode("F");
				} else {
					bean.setNbtAdjustmentReasonCode("P");
				}
				bean.setNbtWriteOffAmount(vAdjAmt.setScale(2, BigDecimal.ROUND_HALF_UP));
			}
		} else {
			bean.setNbtWriteOffAmount(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
		}
		return bean;

	}

}