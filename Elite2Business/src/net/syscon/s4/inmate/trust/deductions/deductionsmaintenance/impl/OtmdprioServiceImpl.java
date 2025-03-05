package net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfilesCommitBean;
import net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.OtmdprioRepository;
import net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.OtmdprioService;

/**
 * Creates new OtmdprioServiceImpl class Object
 */
@Service
public class OtmdprioServiceImpl implements OtmdprioService {

	@Autowired
	public OtmdprioRepository otmdprioRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<CaseloadDeductionProfiles> csldDpExecuteQuery(CaseloadDeductionProfiles searchRecord) {
		List<CaseloadDeductionProfiles>  returnList = otmdprioRepository.csldDpExecuteQuery(searchRecord);
		for(CaseloadDeductionProfiles obj: returnList){
			List<CaseloadDeductionProfiles> dataList =otmdprioRepository.getBalTypeDesc(obj.getDeductionType());
			for(CaseloadDeductionProfiles objdata:dataList){
				obj.setDeductionTypeDesc(objdata.getDeductionTypeDesc());
				obj.setFromBalType(objdata.getFromBalType());

			}

		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCSLD_DP
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer csldDpCommit(CaseloadDeductionProfilesCommitBean commitBean) {
		int liReturn = 0;
		// updateRecords
		List<CaseloadDeductionProfiles> caseloadDeductionProfiles=	new ArrayList<CaseloadDeductionProfiles>();
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for(CaseloadDeductionProfiles obj:commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				caseloadDeductionProfiles.add(obj);
			}
			liReturn = otmdprioRepository.csldDpUpdateCaseloadDeductionProfiles(caseloadDeductionProfiles);
		}
		return liReturn;
	}


}