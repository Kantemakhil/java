package net.syscon.s4.inst.automatedcounts.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.automatedcounts.OidcountRepository;
import net.syscon.s4.inst.automatedcounts.OidrecorRepository;
import net.syscon.s4.inst.automatedcounts.OidrecorService;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypes;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCounts;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountsCommitBean;
import net.syscon.s4.inst.automatedcounts.beans.AgencyLocationCounts;
import net.syscon.s4.pkgs.oidcount.OidcountPkgService;

@Service
public class OidrecorServiceImpl extends BaseBusiness implements OidrecorService {

	@Autowired
	private OidrecorRepository oidrecorRepository;

	@Autowired
	private OidcountRepository oidcountRepos;
	
	@Autowired
	private OidcountPkgService oidcountPkgService;

	/**
	 * Logger object used to print the log in the file
	 */

	/**
	 * Creates new OidrecorServiceImpl class Object
	 */
	public OidrecorServiceImpl() {
//	OidrecorServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public OmsModules CreateFormGlobals(OmsModules paramBean) {
		OmsModules omsModules = oidrecorRepository.createFormGlobalscreateFormGlobals(paramBean);
		return omsModules;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public SystemProfiles PrintList(SystemProfiles paramBean) {
		SystemProfiles systemProfiles = oidrecorRepository.printList(paramBean);
		return systemProfiles;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public AgencyLocations DefaultAgyLoc(AgencyLocations paramBean) {
		AgencyLocations agencyLocations = oidrecorRepository.defaultAgyLocDEFAULT_AGY_LOC(paramBean);
		return agencyLocations;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<AgencyCounts> agencyCountsExecuteQuery(AgencyCounts searchRecord) {
		return oidrecorRepository.agencyCountsExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstAGENCY_COUNTS
	 *
	 * 
	 */
	@Transactional
	public Integer agencyCountsCommit(AgencyCountsCommitBean commitBean) {
		int liReturn = 0;
		final AgencyCounts agencyCountsbean = new AgencyCounts();
		final AgencyLocationCounts alcBean = new AgencyLocationCounts();
		Integer globalReportingLocId = liReturn;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(data -> data.setCreateUserId(commitBean.getCreateUserId()));
			liReturn = oidrecorRepository.agencyCountsInsertAgencyCounts(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			final Integer reportingLocId = oidcountRepos.gettingMaxValueOfReportinglocidInAgenctLocations();
			commitBean.getUpdateList().forEach(data -> {
			data.setModifyUserId(commitBean.getCreateUserId());
			data.setCreateUserId(commitBean.getCreateUserId());
			});
			liReturn = oidrecorRepository.agencyCountsUpdateAgencyCounts(commitBean.getUpdateList());
			liReturn = oidrecorRepository.agencyCountsDeleteAgencyCounts(commitBean.getUpdateList());
			AgencyCountTypes deleteParamBean = new AgencyCountTypes();
			deleteParamBean.setSessionId(commitBean.getUpdateList().get(0).getSessionId().longValue());
			oidcountRepos.deleteInitiateRecordsOfLockedModules(deleteParamBean);

			AgencyCountTypes insertParamBean = new AgencyCountTypes();
			insertParamBean.setSessionId(commitBean.getUpdateList().get(0).getReCountSessionId().longValue());
			insertParamBean.setAgyLocId(commitBean.getUpdateList().get(0).getAgylocId());
			insertParamBean.setCaseLoadId(commitBean.getUpdateList().get(0).getCaseloadId());
			insertParamBean.setCreateUserId(commitBean.getCreateUserId());
			oidcountRepos.insertTheDataInLockedModules(insertParamBean);

			commitBean.getUpdateList().get(0).setParentReportingLocId(commitBean.getUpdateList().get(0).getReportingLocId());
			commitBean.getUpdateList().get(0).setReportingLocId(reportingLocId);
			commitBean.getUpdateList().get(0).setCreateUserId(commitBean.getCreateUserId());
			liReturn = oidrecorRepository.agencyCountsInsertAgencyCounts(commitBean.getUpdateList());
			AgencyCountTypes paramBean = new AgencyCountTypes();
			Long sessionIdTemp = Long.valueOf(commitBean.getUpdateList().get(0).getSessionId() + "");
			paramBean.setSessionId(sessionIdTemp);
			oidcountPkgService.cancelCount(sessionIdTemp,commitBean.getCreateUserId());

			agencyCountsbean.setReportingLocId(reportingLocId);
			agencyCountsbean.setCountTypeId(commitBean.getUpdateList().get(0).getCountTypeId());
			agencyCountsbean.setCreateUserId(commitBean.getCreateUserId());
			final List<Integer> agySeq = oidcountRepos.preInsertOfAagencyLocationCounts(agencyCountsbean);
			alcBean.setCountTypeId(Long.valueOf(commitBean.getUpdateList().get(0).getCountTypeId().toString()));
			alcBean.setModifyUserId(commitBean.getCreateUserId());
//			Integer count = oidrecorRepository.agencyLocationCountsDeleteAgencyCounts(alcBean);//S4-24972
			alcBean.setReportingLocId(Long.valueOf(reportingLocId.toString()));
			alcBean.setCreateUserId(commitBean.getCreateUserId());
			for (int i = 0; i < agySeq.size(); i++) {
				alcBean.setAgySeq(Long.valueOf(agySeq.get(i).toString()));
				oidcountRepos.insertTheDataInAgencyLocationCounts(alcBean);
			}
			globalReportingLocId = reportingLocId;
		}
		return globalReportingLocId;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> cgfkRecountRsnRecordGroup() {
		return oidrecorRepository.cgfkRecountRsnRecordGroup();

	}

}