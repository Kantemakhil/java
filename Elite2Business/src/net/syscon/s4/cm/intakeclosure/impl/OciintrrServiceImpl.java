package net.syscon.s4.cm.intakeclosure.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.intakeclosure.OcdsupstService;
import net.syscon.s4.cm.intakeclosure.OciintrrRepository;
import net.syscon.s4.cm.intakeclosure.OciintrrService;
import net.syscon.s4.common.beans.OffIntakeReviewQueue;
import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.trust.financialsmaintenance.OtdcntacService;
import net.syscon.s4.pkgs.trust.TrustService;

@Service
public class OciintrrServiceImpl extends BaseBusiness implements OciintrrService {
	@Autowired
	private OciintrrRepository ociintrrRepository;
	@Autowired
	private OcdsupstService ocdsupstService;
	@Autowired
	private EliteDateService eliteDateService;
	@Autowired
	private OtdcntacService otdcntacService;
	
	@Autowired
	private TrustService trustService;

	@Override
	public List<OffIntakeReviewQueue> offIntakeReiewQuExecuteQuery(final OffIntakeReviewQueue searchBean) {
		return ociintrrRepository.offIntakeReiewQuExecuteQuery(searchBean);
	}

	@Transactional
	public Integer offIntakeRevAccept(final OffIntakeReviewQueue bean) {
		Integer liReturn = 0;
		List<OffenderTransactions> offTxnInsertList = new ArrayList<>();
		OffenderTransactionsCommitBean commitBean = new OffenderTransactionsCommitBean();
		OffenderTransactions offtxnModel = new OffenderTransactions();
		bean.setModifyUserId(bean.getCreateUserId());
		liReturn = ociintrrRepository.offIntakeRevAccept(bean);
		if (liReturn == 1 && "Y".equals(bean.getBillableFlag())) {
//			String trustAccStatus = ociintrrRepository.checkAccountSatus(bean);
			Map<String, Object> trustAccStatusMap = trustService.chkAccountStatus(bean.getCaseloadId(),new BigDecimal(bean.getOffenderId()));
			String trustAccStatus = (String) trustAccStatusMap.get("P_OPEN_AN_ACCOUNT");
			if ("X".equals(trustAccStatus)) {
				offtxnModel.setOffenderId(bean.getOffenderId());
				offtxnModel.setOffenderBookId(Long.valueOf(bean.getOffenderBookId()));
				offtxnModel.setCaseloadId(bean.getCaseloadId());
				offtxnModel.setTxnPostingType("CR");
				offtxnModel.setSlipPrintedFlag("N");
				offtxnModel.setReceiptPrintedFlag("Y");
				offtxnModel.setDeductionFlag("Y");
				offtxnModel.setTxnAdjustedFlag("Y");
				offtxnModel.setHoldClearFlag("Y");
				offtxnModel.setAdjustTxnEntryId(99);
				offtxnModel.setTxnEntryDate(eliteDateService.getDBTime());
				offtxnModel.setModifyDate(eliteDateService.getDBTime());
				offtxnModel.setCreateDatetime(eliteDateService.getDBTime());
				offtxnModel.setCreateUserId(bean.getModifyUserId());
				offtxnModel.setModifyUserId(bean.getModifyUserId());
				offtxnModel.setTxnEntryAmount(0.00);
				offtxnModel.setCreateUserId(bean.getCreateUserId());
				offTxnInsertList.add(offtxnModel);
				commitBean.setInsertList(offTxnInsertList);
				commitBean.setCreateUserId(bean.getCreateUserId());
				otdcntacService.offTxnCommit(commitBean);
				ocdsupstService.autoCreateFeeAccounts(bean.getOffenderBookId(), bean.getCaseloadId(),
						bean.getSupStatusDatetime(), bean.getCreateUserId());
			} else {
				ocdsupstService.autoCreateFeeAccounts(bean.getOffenderBookId(), bean.getCaseloadId(),
						bean.getSupStatusDatetime(), bean.getCreateUserId());
			}
		}
		return liReturn;
	}

}
