package net.syscon.s4.inmate.trust.statements.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inmate.trust.statements.OturnumbRepository;
import net.syscon.s4.inmate.trust.statements.OturnumbService;

/**
 * Creates new OturnumbServiceImpl class Object
 */
@Service
public class OturnumbServiceImpl implements OturnumbService {

	@Autowired
	private OturnumbRepository oturnumbRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<OffenderTransactions> offTxnExecuteQuery(final OffenderTransactions searchRecord) {
		List<OffenderTransactions> resultList = oturnumbRepository.offTxnExecuteQuery(searchRecord);
		if (resultList != null) {
			resultList.forEach(data -> {
				if (data.getOffenderBookId() != null) {
					Offenders offender = oturnumbRepository.getOffenderDetails(data.getOffenderBookId());
					if (offender != null) {
						data.setPayeeName(offender.getLastName());
						data.setOffenderIdDisplay(offender.getOffenderIdDisplay());
						String receiptNum = StringUtils.leftPad(data.getReceiptNumber().substring(
								data.getReceiptNumber().length() - 6, data.getReceiptNumber().length()), 6, "0");
						data.setReceiptNumber(receiptNum);
					}
				}
			});
		}
		return resultList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_TXN
	 *
	 */
	@Transactional
	public Integer offTxnCommit(final OffenderTransactionsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			liReturn = oturnumbRepository.offTxnInsertOffenderTransactions(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			liReturn = oturnumbRepository.offTxnUpdateOffenderTransactions(commitBean.getUpdateList());
		}
		return liReturn;
	}

}