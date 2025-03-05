package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.triggers.OffFeeBillTransactionsT2Repository;
import net.syscon.s4.triggers.OffFeeBillTransactionsT2Service;

@Service
public class OffFeeBillTransactionsT2ServiceImpl implements OffFeeBillTransactionsT2Service {

	@Autowired
	private OffFeeBillTransactionsT2Repository offFeeBillTransactionsT2Repository;

	final private static String R = "R";
	final private static String A = "A";
	@Override
	public void offFeeBillTransactionsT2(OffFeeBillTransactions newBean) throws Exception {

		if (newBean.getBillTxnType() == null && newBean.getBillTxnAmount() != null) {
			throw new Exception("Bill status escalation/de-escalation transactions do not have a transaction amount");
		}
		if (newBean.getBillTxnType() != null && newBean.getBillTxnAmount() == null) {
			throw new Exception(
					"All bill transactions except bill status escalation/de-escalation transactions require a transaction amount");
		}
		if (newBean.getBillTxnType() != null) {
			String lvTxnUsage = offFeeBillTransactionsT2Repository
					.getoffFeeBillTransactionsT2(newBean.getBillTxnType());

			if (R.equalsIgnoreCase(lvTxnUsage) && newBean.getTrustTxnId() == null
					|| newBean.getTrustTxnEntrySeq() == null) {
				throw new Exception("Bill payment and payment reversal transactions are required to persist the offender trust transaction id");
			}
			if(!R.equalsIgnoreCase(lvTxnUsage) && newBean.getTrustTxnId() != null || newBean.getTrustTxnEntrySeq() != null) {
				throw new Exception("Bill transactions that are not payments or payment reversals do not have an offender trust transaction");
			}
			if(A.equalsIgnoreCase(lvTxnUsage) && newBean.getOffAdjTxnId() == null) {
				throw new Exception("Bill credits/offender adjustments and their reversals require a credit adjustment transaction id");
			}
			if(!A.equalsIgnoreCase(lvTxnUsage) && newBean.getOffAdjTxnId() != null) {
				throw new Exception("Only bill credits/offender adjustments and their reversals require a credit adjustment transaction id");
			}
			if(A.equalsIgnoreCase(lvTxnUsage) && newBean.getOriginalBillId() != null || newBean.getOffAdjRevRsn() == null){
				throw new Exception("Bill credits/offender adjustment reversals require an adjustment reversal reason");
			}
			if(A.equalsIgnoreCase(lvTxnUsage) && newBean.getOffAdjRevRsn() != null){
				throw new Exception("Only bill credits/offender adjustment reversals require an adjustment reversal reason");
			}
			if(A.equalsIgnoreCase(lvTxnUsage) && newBean.getOriginalBillId() != null || newBean.getOriginalOffAdjTxnId() == null) {
				throw new Exception("Only bill credits/offender adjustment reversals can populate an original adjustment transaction id");
			}
		}
	}

}
