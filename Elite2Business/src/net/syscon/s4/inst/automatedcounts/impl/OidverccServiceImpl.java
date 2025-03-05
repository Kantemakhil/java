package net.syscon.s4.inst.automatedcounts.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.automatedcounts.OidverccRepository;
import net.syscon.s4.inst.automatedcounts.OidverccService;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypes;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypesCommitBean;
import net.syscon.s4.inst.automatedcounts.beans.AgencyLocationCounts;
import net.syscon.s4.inst.automatedcounts.beans.TempOidcount;
import net.syscon.s4.inst.automatedcounts.beans.VReportingLocations;
import net.syscon.s4.inst.automatedcounts.beans.VReportingLocationsCommitBean;
import net.syscon.s4.inst.demographicsbiometrics.OcuverifRepository;
import net.syscon.s4.triggers.AgencyLocationCountsT1Service;

/**
 * Class OidverccServiceImpl
 */
@Service
public class OidverccServiceImpl extends BaseBusiness implements OidverccService {

	@Autowired
	private OidverccRepository oidverccRepo;
	@Autowired
	private AgencyLocationCountsT1Service agencyLocationCountsT1Service;
	@Autowired
	private OcuverifRepository ocuverifRepo;

	/**
	 * Creates new OidverccServiceImpl class Object
	 */
	public OidverccServiceImpl() {

	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<AgencyLocations> defaultAgyLoc(final AgencyLocations paramBean) {
		return oidverccRepo.defaultAgyLocdefaultAgyLoc(paramBean);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<AgencyCountTypes> agencyCountTypesExecuteQuery(final AgencyCountTypes searchRecord) {
		return oidverccRepo.agencyCountTypesExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstAGENCY_COUNT_TYPES
	 *
	 */
	@Transactional
	public Integer agencyCountTypesCommit(final AgencyCountTypesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
				for (AgencyCountTypes obj : commitBean.getInsertList()) {
					obj.setCreateUserId(commitBean.getCreateUserId());
				}
				liReturn = oidverccRepo.agencyCountTypesInsertAgencyCountTypes(commitBean.getInsertList());
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
					for (AgencyCountTypes obj : commitBean.getUpdateList()) {
						obj.setModifyUserId(commitBean.getCreateUserId());
					}
					liReturn = oidverccRepo.agencyCountTypesUpdateAgencyCountTypes(commitBean.getUpdateList());
				}
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = oidverccRepo.agencyCountTypesDeleteAgencyCountTypes(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<VReportingLocations> reportingLocationsExecuteQuery(final VReportingLocations searchRecord) {
		return oidverccRepo.reportingLocationsExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstREPORTING_LOCATIONS
	 *
	 */
	@Transactional
	public Integer reportingLocationsCommit(final VReportingLocationsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = oidverccRepo.reportingLocationsDeleteVReportingLocations(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> cgfkAgyLocIdRecordGroup(final String caseLoadId) {
		return oidverccRepo.cgfkAgyLocIdRecordGroup(caseLoadId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> cgfkCountTypesRecordGroup(final String agyLocId) {
		return oidverccRepo.cgfkCountTypesRecordGroup(agyLocId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<AgencyCountTypes> cgfkScheduledTimeRecordGroup(final String agyLocId, final String countTypeCode) {
		final List<AgencyCountTypes> returnList = oidverccRepo.cgfkScheduledTimeRecordGroup(agyLocId, countTypeCode);
		for (final AgencyCountTypes obj : returnList) {
			obj.setCode(obj.getCountTypeId().toString());
			obj.setDescription(obj.getScheduledTime());
			if (obj.getDescription() == null) {
				obj.setDescription("");
			}
		}
		return returnList;

	}

	/**
	 * Used to update the record in the data base
	 * 
	 * @param bean
	 * @return Integer
	 */
	@Transactional
	public Integer updateAgencyLocationCounts(final AgencyLocationCounts bean) {
		Integer liReturn = 0;
		liReturn = oidverccRepo.updateAgencyLocationCounts(bean);
		TempOidcount tempOidcoun = new TempOidcount();
		tempOidcoun.setAgySeq(bean.getAgySeq() != null ? bean.getAgySeq().intValue() : null);
		tempOidcoun.setCountTypeId(bean.getCountTypeId() != null ? bean.getCountTypeId().intValue() : null);
		tempOidcoun.setReportingLocId(bean.getReportingLocId() != null ? bean.getReportingLocId().intValue() : null);
		tempOidcoun.setReportedCount(bean.getReportedCount() != null ? bean.getReportedCount().intValue() : null);
		tempOidcoun.setDateSubmitted(bean.getDateSubmitted());
		tempOidcoun.setEnteredByUserid(bean.getModifyUserId());
		agencyLocationCountsT1Service.agencyLocationCountsT1Trigger(tempOidcoun, bean.getRcntInProgressFlag());
		return liReturn;
	}

	public String getUserName(String userId) {
		return ocuverifRepo.getUserName(userId);
	}
}