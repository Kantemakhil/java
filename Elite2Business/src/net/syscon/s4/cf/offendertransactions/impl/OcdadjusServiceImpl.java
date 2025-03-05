package net.syscon.s4.cf.offendertransactions.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactionsCommitBean;
import net.syscon.s4.cf.offendertransactions.OcdadjusRepository;
import net.syscon.s4.cf.offendertransactions.OcdadjusService;
import net.syscon.s4.cm.intakeclosure.OcdsupstRepository;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.VTrustHeader;
import net.syscon.s4.triggers.OffFeeBillTransactionsT1Service;
import net.syscon.s4.triggers.OffFeeBillTransactionsT2Service;

@Service
public class OcdadjusServiceImpl extends BaseBusiness implements OcdadjusService {
	@Autowired
	private OcdadjusRepository ocdbadjusRepository;
	@Autowired
	private OcdsupstRepository ocdsupstRepository;
	
	@Autowired
	private OffFeeBillTransactionsT1Service offFeeBillTransactionsT1Service;
	@Autowired
	private OffFeeBillTransactionsT2Service offFeeBillTransactionsT2Service;
	
	@Override
	public List<OffFeeBillTransactions> offFeeExecuteQuery(final BigDecimal offenderBookId) {
		List<OffFeeBillTransactions> listObj= ocdbadjusRepository.offFeeExecuteQuery(offenderBookId);
		return postExecuteQuery(listObj);
	}
	@Override
	public List<OffFeeBillTransactions> postExecuteQuery(final List<OffFeeBillTransactions> returnList) {
		for(OffFeeBillTransactions bean : returnList) {
			BigDecimal owingAmount = ocdbadjusRepository.getPostQuery(bean.getBillId());
			if(owingAmount!=null) {
				bean.setBalanceOwingAmount(owingAmount);
			}
		}
		return returnList;
	}
	@Override
	public List<TransactionTypes> rgAdjustType() {
		return ocdbadjusRepository.rgAdjustType();
	}

	@Override
	@Transactional
	public Integer offTxnCommit(OffFeeBillTransactionsCommitBean commitBean) {
		Integer liReturn = 0;
//		Map<String,Integer> ofbiltxnMap = new HashMap<String,Integer>();     
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			Integer staffId = ocdsupstRepository.getstaffId(commitBean.getInsertList().get(0).getBillTxnUser());
			Integer offAdjTxn = ocdbadjusRepository.getOffAdjustTxnId();
			for (OffFeeBillTransactions bean : commitBean.getInsertList()) {
				Integer txnNo = ocdbadjusRepository.getBillTranId(bean.getBillId());
				
//				ofbiltxnMap.put(bean.getBillTxnNo() + bean.getBillId(), txnNo);
				bean.setBillTxnNo(txnNo);
				bean.setBillTxnStaffId(staffId);
				bean.setOffAdjTxnId(offAdjTxn);
			}
				
				liReturn = ocdbadjusRepository.offTxnCommit(commitBean.getInsertList());
				if(liReturn ==1) {
					commitBean.getInsertList().forEach(bean->{				
						try {
							offFeeBillTransactionsT1Service.offFeeBillTransactionsT1(bean);
							offFeeBillTransactionsT2Service.offFeeBillTransactionsT2(bean);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					});

	}
	}
		return liReturn;
	}

	public String getfeeBillCaslSeq(final String caseloadId) {
		Integer caseloadSeq = ocdsupstRepository.getFeeBillSeq("SEQUENCE_FEE_BILL_" + caseloadId);
		if (caseloadSeq != 0) {
			String num = caseloadSeq.toString();
			while (num.length() < 10) {
				num = "0" + num;
			}
			return caseloadId + num;
		}
		return null;
	}

	@Override
	public String getbillEndDayPfVal() {
		return ocdbadjusRepository.getbillEndDayPfVal();
	}

	@Override
	public Integer getCasePlanId(VTrustHeader bean) {
		return ocdbadjusRepository.getCasePlanId(bean);
	}
}
