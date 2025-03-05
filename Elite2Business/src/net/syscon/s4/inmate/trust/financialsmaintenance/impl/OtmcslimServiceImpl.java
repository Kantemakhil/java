package net.syscon.s4.inmate.trust.financialsmaintenance.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.CaseloadLimits;
import net.syscon.s4.im.beans.CaseloadLimitsCommitBean;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.trust.financialsmaintenance.OtmcslimRepository;
import net.syscon.s4.inmate.trust.financialsmaintenance.OtmcslimService;

/**
 * Class OtmcslimServiceImpl
 * 
 */
@Service
public class OtmcslimServiceImpl extends BaseBusiness implements OtmcslimService {

	@Autowired
	private OtmcslimRepository otmcslimRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<CaseloadLimits> csldLimExecuteQuery(CaseloadLimits searchRecord) {
		List<CaseloadLimits> returnList = otmcslimRepository.csldLimExecuteQuery(searchRecord);

		for(CaseloadLimits obj: returnList){
			String desc = otmcslimRepository.getCaseloadIdDesc(obj.getCaseloadId());
			if (desc!=null){
				obj.setDesciption(desc);
				
			}
			
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCSLD_LIM
	 *
	 * 
	 */
	@Transactional
	public String csldLimCommit(CaseloadLimitsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			liReturn = otmcslimRepository.csldLimInsertCaseloadLimits(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			liReturn = otmcslimRepository.csldLimUpdateCaseloadLimits(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = otmcslimRepository.csldLimDeleteCaseloadLimits(commitBean.getDeleteList());
		}
		return String.valueOf(liReturn);
	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<Caseloads> cgfkCsldLimCaseloadIdRecordGroup() {
		List<Caseloads> returnList = otmcslimRepository.cgfkCsldLimCaseloadIdRecordGroup();
		returnList.forEach(result ->{
			result.setCode(result.getCaseloadId());
			result.setDescription(result.getDescription());
		});
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> cgfkCsldLimLimitTypeRecordGroup() {
		return otmcslimRepository.cgfkCsldLimLimitTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> cgfkCsldLimPeriodTypeRecordGroup() {
		return otmcslimRepository.cgfkCsldLimPeriodTypeRecordGroup();

	}

	public List<ReferenceCodes> cgfkchkCsldLimCsldLimTyp(ReferenceCodes paramBean) {
		return otmcslimRepository.cgfkchkCsldLimCsldLimTyp(paramBean);
	}

	public List<Caseloads> cgfkchkCsldLimCsldLimCsl(Caseloads paramBean) {
		return otmcslimRepository.cgfkchkCsldLimCsldLimCsl(paramBean);
	}

	@Override
	public List<ReferenceCodes> cgfkchkCsldLimCsldLimPer(ReferenceCodes paramBean) {
		return otmcslimRepository.cgfkchkCsldLimCsldLimPer(paramBean);
	}

}