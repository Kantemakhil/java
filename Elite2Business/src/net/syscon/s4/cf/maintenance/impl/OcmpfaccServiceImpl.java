package net.syscon.s4.cf.maintenance.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cf.maintenance.OcmpfaccRepository;
import net.syscon.s4.cf.maintenance.OcmpfaccService;
import net.syscon.s4.cf.maintenance.beans.FeeAccounts;
import net.syscon.s4.cf.maintenance.beans.FeeAccountsCommitBean;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.beans.DeductionTypes;
@Service
public class OcmpfaccServiceImpl implements OcmpfaccService {
	@Autowired
	private OcmpfaccRepository ocmpfaccRepository;
	
	public static final String DUPLICATEDATA = "DUPLICATEDATA";
	public static final String DUPLICATESEQ = "DUPLICATESEQ";

	@Override
	public List<FeeAccounts> feeAccountsExecuteQuery() {
		return ocmpfaccRepository.feeAccountsExecuteQuery();
	}

	@Override
	public FeeAccounts feeAccountCommit(final FeeAccountsCommitBean commitBean) {
		int liReturn = 0;
		FeeAccounts returnData= new FeeAccounts();
//		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (FeeAccounts bean : commitBean.getInsertList()) {
				Integer dupcount = ocmpfaccRepository.preInsert(bean);
				Integer seqCount = ocmpfaccRepository.preInsertListSeq(bean);
				if (dupcount > 0) {
					returnData.setSealFlag(DUPLICATEDATA);
					return returnData;
				}
				if (seqCount > 0) {
					returnData.setSealFlag(DUPLICATESEQ);
					return returnData;
				}
			}
			returnData = ocmpfaccRepository.insertFeeAccounts(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (FeeAccounts bean : commitBean.getUpdateList()) {
				Integer seqCount = ocmpfaccRepository.preUpdateListSeq(bean);
				if (seqCount > 0) {
					returnData.setSealFlag(DUPLICATESEQ);
					return returnData;
				}
			}
			returnData = ocmpfaccRepository.updateFeeAccounts(commitBean.getUpdateList());
		}
		return returnData;
	}

	@Override
	public List<AccountCodes> getAccCodes() {
		return ocmpfaccRepository.getAccCodes();
	}

	@Override
	public List<DeductionTypes> getFeeCodes() {
		return ocmpfaccRepository.getFeeCodes();
	}

}
