package net.syscon.s4.inmate.trust.trustaccounts.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.trust.trustaccounts.OtupayinRepository;
import net.syscon.s4.inmate.trust.trustaccounts.OtupayinService;

/**
 * Class OtupayinServiceImpl
 */
@Service
public class OtupayinServiceImpl extends BaseBusiness implements OtupayinService {

	@Autowired
	private OtupayinRepository otupayinRepo;

	/**
	 * Creates new OtupayinServiceImpl class Object
	 */
	public OtupayinServiceImpl() {
		// OtupayinServiceImpl
	}


	/**
	 * Fetch the records from database table
	 * Method offDedExecuteQuery
	 * @param searchRecord
	 * Return OffenderDeductions
	 * 
	 */
	public OffenderDeductions offDedExecuteQuery(final OffenderDeductions searchRecord) {
		OffenderDeductions returnBean = new OffenderDeductions();
		returnBean = otupayinRepo.offDedExecuteQuery(searchRecord);
		if (returnBean.getDeductionType() != null) {
			final String deductionDesc = otupayinRepo.offDedPostQuery(returnBean);
			returnBean.setDeductionDesc(deductionDesc);
		}
		final List<OffenderTransactions> offTransList = otupayinRepo.offTxnExecuteQuery(searchRecord);
		for (final OffenderTransactions obj : offTransList) {
			if (!"UNLIMITED".equals(searchRecord.getTotOwing().toString()) && searchRecord.getTotOwing() != 786.0) {
				if ("DR".equals(obj.getTxnPostingType())) {
					obj.setTotalOwed(searchRecord.getTotOwing());
					searchRecord.setTotOwing(searchRecord.getTotOwing() + (obj.getTxnEntryAmount()));
					returnBean.setTotOwing(searchRecord.getTotOwing() +(obj.getTxnEntryAmount()));
				} else if("CR".equals(obj.getTxnPostingType())) {
					obj.setTotalOwed(searchRecord.getTotOwing());
					searchRecord.setTotOwing(searchRecord.getTotOwing() - (obj.getTxnEntryAmount()));
					returnBean.setTotOwing(searchRecord.getTotOwing() -(obj.getTxnEntryAmount()));
				}
				
			} else {
				obj.setTotalOwed(786.0);
			}
			obj.setTotalPaid(searchRecord.getTotPaid());
			if ("DED".equals(obj.getTxnType())) {
				if ("DR".equals(obj.getTxnPostingType())) {
					searchRecord.setTotPaid(searchRecord.getTotPaid() - (obj.getTxnEntryAmount()));
					returnBean.setTotPaid(searchRecord.getTotPaid() -(obj.getTxnEntryAmount()));
				} else {
					searchRecord.setTotPaid(searchRecord.getTotPaid() + (obj.getTxnEntryAmount()));
					returnBean.setTotPaid(searchRecord.getTotPaid() + (obj.getTxnEntryAmount()));
				}
				
			}
			
		}
		returnBean.setOffTransList(offTransList);
		return returnBean;

	}

	/**
	 * Fetch the records from database table
	 * Method offTxnExecuteQuery
	 * @param searchRecord
	 * Return List<OffenderTransactions>
	 * 
	 */
	public List<OffenderTransactions> offTxnExecuteQuery(final OffenderDeductions searchRecord) {
		return otupayinRepo.offTxnExecuteQuery(searchRecord);

	}

}