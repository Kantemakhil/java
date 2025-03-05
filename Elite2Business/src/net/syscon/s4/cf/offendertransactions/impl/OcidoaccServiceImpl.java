package net.syscon.s4.cf.offendertransactions.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cf.offendertransactions.OcidoaccRepository;
import net.syscon.s4.cf.offendertransactions.OcidoaccService;
import net.syscon.s4.cf.offendertransactions.beans.VOffGroupedPaymentPlans;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;

/**
 * Class OcidoaccServiceImpl
 */
@Service
public class OcidoaccServiceImpl extends BaseBusiness implements OcidoaccService {

	@Autowired
	private OcidoaccRepository ocidoaccRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderSubAccounts> offSubaExecuteQuery(OffenderSubAccounts searchRecord) {

		List<OffenderSubAccounts> returnList = ocidoaccRepository.offSubaExecuteQuery(searchRecord);
		if (returnList != null && !returnList.isEmpty()) {
			for (OffenderSubAccounts obj : returnList) {
				AccountCodes returnBean = ocidoaccRepository.getAccountCodesOfTypeAndName(obj.getTrustAccountCode());
				obj.setSubAccountType(returnBean.getSubAccountType());
				obj.setDescription(returnBean.getAccountName());
			}

		}
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<VOffGroupedPaymentPlans> paySchExecuteQuery(VOffGroupedPaymentPlans searchRecord) {
		List<VOffGroupedPaymentPlans> returnList = ocidoaccRepository.paySchExecuteQuery(searchRecord);
		for (VOffGroupedPaymentPlans bean : returnList) {
			if (bean.getPaymentPlanId() != null) {
				Integer lvNonCompletedSch = ocidoaccRepository.lvNonCompletedSch(bean);
				if (lvNonCompletedSch == 0) {
					bean.setDueDate(null);
					bean.setAmount(BigDecimal.ZERO);
					bean.setBalOwing(BigDecimal.ZERO);
					bean.setArrears(BigDecimal.ZERO);
					bean.setDaysLate(null);
					bean.setReason("COMPLETED");
					continue;
				}
			}
			if (bean.getMinNextPaymentDate() == null) {
				bean.setDueDate(bean.getMaxNextPaymentDate());
			} else {
				bean.setDueDate(bean.getMinNextPaymentDate());
			}
			bean.setAmount(ocidoaccRepository.getAmount(bean));
			if (bean.getMinNextPaymentDateTwo() == null) {
				if (bean.getMaxNextPaymentDate() != null) {
					Date tempDate = ocidoaccRepository.getDaysLate(bean.getPaymentPlanId(),
							bean.getMaxNextPaymentDate());
					if (tempDate != null) {
						bean.setTempDate(tempDate);
					}
					bean.setDaysLate(getDaysLateQuery(tempDate));
				}
			} else {
				if (bean.getMinNextPaymentDate() != null) {
					Date tempDate = ocidoaccRepository.getDaysLate(bean.getPaymentPlanId(),
							bean.getMinNextPaymentDate());
					bean.setDaysLate(getDaysLateQuery(tempDate));
					if (tempDate != null) {
						bean.setTempDate(tempDate);
					}
				}
			}
			if (bean.getDaysLate() != null && bean.getDaysLate().intValue() > 0) {
				bean.setArrears(ocidoaccRepository.getArrears(bean));
				Integer ncount = ocidoaccRepository.getReason(bean);
				if (ncount == 1) {
					bean.setReason("LATE");
				} else {
					bean.setReason("MISSED");
				}
			}
		}
		return returnList;

	}

	public BigDecimal getDaysLateQuery(Date tempDate) {
		BigDecimal dayslate = BigDecimal.ZERO;
		if (tempDate != null) {
			return ocidoaccRepository.getDaysLateQuery(tempDate);
		}
		return dayslate;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderDeductions> offBncExecuteQuery(OffenderDeductions searchRecord) {
		List<OffenderDeductions> returnList = ocidoaccRepository.offBncExecuteQuery(searchRecord);
		for (OffenderDeductions bean : returnList) {
			if ("Y".equals(bean.getDedFlag())) {
				if (bean.getMaxTotalAmount() != null) {
					bean.setOrgAmount(bean.getMaxTotalAmount());
					bean.setTotCollected(bean.getDeductionAmount());
					bean.setTotalOwing(bean.getMaxTotalAmount().subtract(bean.getDeductionAmount()));
				} else if (bean.getMaxMonthlyAmount() != null) {
					bean.setOrgAmount(bean.getMaxMonthlyAmount());
					BigDecimal monAmt = ocidoaccRepository.getMonAmt(bean);
					bean.setTotCollected(monAmt);
					bean.setTotalOwing(bean.getMaxMonthlyAmount().subtract(monAmt));
				} else if (bean.getMaxRecursiveAmount() != null) {
					BigDecimal recMonth = ocidoaccRepository.getRecMonth(bean);
					bean.setOrgAmount(bean.getMaxRecursiveAmount().multiply(recMonth));
					bean.setTotCollected(bean.getDeductionAmount());
					bean.setTotalOwing(
							(bean.getMaxRecursiveAmount().multiply(recMonth)).subtract(bean.getDeductionAmount()));
				} else {
					bean.setTotCollected(bean.getDeductionAmount());
					bean.setUnlimited("Unlimited");
				}
			} else {
				bean.setTotCollected(bean.getDeductionAmount());
				bean.setUnlimited("Unlimited");
			}
		}
		return returnList;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public Integer sysPflExecuteQuery(final Integer rootOffenderid) {
		return ocidoaccRepository.sysPflExecuteQuery(rootOffenderid);

	}

	public String getActClosedFlag(final Integer offenderId, final String caseloadid) {
		return ocidoaccRepository.getActClosedFlag(offenderId, caseloadid);
	}
}