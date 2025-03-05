package net.syscon.s4.inst.classification.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.classification.OiiclassRepository;
import net.syscon.s4.inst.classification.OiiclassService;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.classification.beans.OiiclassClassInquiry;
import net.syscon.s4.inst.classification.beans.OiiclassClassInquiryCommitBean;
import net.syscon.s4.pkgs.common.CommonService;

@Service
public class OiiclassServiceImpl extends BaseBusiness implements OiiclassService {

	@Autowired
	private OiiclassRepository oiiclassRepository;

	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiiclassServiceImpl.class.getName());

	/**
	 * Creates new OiiclassServiceImpl class Object
	 */
	public OiiclassServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public AgencyLocations DefaultAgyLoc(AgencyLocations paramBean) {

		AgencyLocations agencyLocations = oiiclassRepository.defaultAgyLocDefaultAgyLoc(paramBean);
		return agencyLocations;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<SystemProfiles> sysProfExecuteQuery(SystemProfiles searchRecord) {
		return oiiclassRepository.sysProfExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PROF
	 *
	 * 
	 */
	@Transactional
	public Integer sysProfCommit(final SystemProfiles CommitBean) {
		int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<LivingUnits> livUnitExecuteQuery(final LivingUnits searchRecord) {
		return oiiclassRepository.livUnitExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstLIV_UNIT
	 *
	 * 
	 */
	@Transactional
	public Integer livUnitCommit(final LivingUnits CommitBean) {
		int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */

	public List<OiiclassClassInquiry> oiiclassExecuteQuery(final OiiclassClassInquiry searchRecord) {
		return commonService.oiiclassClassInquiry(searchRecord);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOIICLASS
	 *
	 * 
	 */
	@Transactional
	public Integer oiiclassCommit(final OiiclassClassInquiryCommitBean CommitBean) {
		int liReturn = 0;
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<Assessments> cgfkAssessmentTypeRecordGroup(final String userId) {
		List<Assessments> resultList = oiiclassRepository.cgfkAssessmentTypeRecordGroup(userId);
		resultList.forEach(result -> {
			result.setCode(result.getAssessmentId().toString());
			result.setDescription(result.getAssessmentType());
		});
		return resultList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<AgencyLocations> cgfkAgyLocIdRecordGroup(final String userId) {
		List<AgencyLocations> resultList = oiiclassRepository.cgfkAgyLocIdRecordGroup(userId);
		resultList.forEach(result -> {
			result.setContactName(result.getDescription());
			result.setDescription(result.getAgyLocId());
			result.setCode(result.getAgyLocId());
		});
		return resultList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<LivingUnits> cgfkHousingLevel1RecordGroup(final String agyLocId) {
		List<LivingUnits> resultList = oiiclassRepository.cgfkHousingLevel1RecordGroup(agyLocId);
		resultList.forEach(result -> {
			result.setDescription(result.getLivingUnitCode());
			result.setCode(result.getLivingUnitCode());
		});
		return resultList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<LivingUnits> cgfkHousingLevel2RecordGroup(final String agyLocId, final String livingUnitId) {
		List<LivingUnits> resultList = oiiclassRepository.cgfkHousingLevel2RecordGroup(agyLocId, livingUnitId);
		resultList.forEach(result -> {
			result.setDescription(result.getLivingUnitCode());
			result.setCode(result.getLivingUnitCode());
		});
		return resultList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<LivingUnits> cgfkHousingLevel3RecordGroup() {
		return oiiclassRepository.cgfkHousingLevel3RecordGroup();

	}

	@Override
	public List<ReferenceCodes> cgfkSearchTypeRecordGroup() {
		ReferenceCodes rCode = new ReferenceCodes();
		ReferenceCodes rCodeOne = new ReferenceCodes();
		ReferenceCodes rCodeTwo = new ReferenceCodes();
		List<ReferenceCodes> rCodeList = new ArrayList<ReferenceCodes>();
		try {
			rCode.setCode("OVERDUE");
			rCode.setDescription(rCode.getCode());
			rCodeList.add(rCode);

			rCodeOne.setCode("DUE FOR INITIAL");
			rCodeOne.setDescription(rCodeOne.getCode());
			rCodeList.add(rCodeOne);

			rCodeTwo.setCode("DUE FOR REVIEW");
			rCodeTwo.setDescription(rCodeTwo.getCode());
			rCodeList.add(rCodeTwo);
		} catch (Exception e) {
			logger.error("cgfkSearchTypeRecordGroup", e);
		}
		return rCodeList;
	}

}