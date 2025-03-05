package net.syscon.s4.cm.programsservices.maintenance.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.programsservices.maintenance.OimworkrRepository;
import net.syscon.s4.cm.programsservices.maintenance.OimworkrService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.CourseActivitiesCommitBean;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRules;
import net.syscon.s4.pkgs.ocdoapop.OcdoapopPkgService;
import net.syscon.s4.triggers.CourseActivitiesT1Service;
import net.syscon.s4.triggers.CourseActivitiesT2Service;
import net.syscon.s4.triggers.CourseActivitiesT3Service;

/**
 * Creates new OimworkrServiceImpl class Object
 * 
 */

@Service
public class OimworkrServiceImpl extends BaseBusiness implements OimworkrService {

	@Autowired
	private OimworkrRepository oimworkrRepository;

	@Autowired
	private CourseActivitiesT3Service courseActivitiesT3Service;

	@Autowired
	private CourseActivitiesT1Service courseActivitiesT1Service;

	@Autowired
	private CourseActivitiesT2Service courseActivitiesT2Service;

	@Autowired
	private OcdoapopPkgService ocdoapopPkgService;

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<ProgramServices> crsActyPostQuery(ProgramServices paramBean) {

		return null;
	}

	public ProgramServices CallOcumpvav(ProgramServices paramBean) {

		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public VAddresses GetDefaultLocation(VAddresses paramBean) {

		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<CourseScheduleRules> DeleteCrsActy(CourseScheduleRules paramBean) {

		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<CourseActivities> CheckDuplicateCodes(CourseActivities paramBean) {

		return null;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<CourseActivities> crsActyExecuteQuery(CourseActivities searchRecord) {
		List<CourseActivities> list = oimworkrRepository.crsActyExecuteQuery(searchRecord);
		if (list != null && list.size() > 0) {
			list.forEach(bo -> {
				VAddresses obj =ocdoapopPkgService.fetchVAddressRecord(bo.getServicesAddressId());
				if(obj!=null) {
					bo.setSuiteNumber(obj.getSuiteNumber());
					bo.setStreetInformation(obj.getStreetInformation());
					bo.setPostalCode(obj.getZipPostalCode());
					bo.setCityName(obj.getCityName());
					bo.setCityCode(obj.getCityCode());
					bo.setProvStateCode(obj.getProvStateCode());
				}
			});
		}
		return list;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCRS_ACTY
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer crsActyCommit(CourseActivitiesCommitBean CommitBean) {
		int liReturn = 0;
		 Long crsActyId = oimworkrRepository.CrsActyIdNextVal();
		if (CommitBean.getInsertList() != null && CommitBean.getInsertList().size() > 0) {
			CommitBean.getInsertList().forEach(bo -> {
				bo.setCreateUserId(CommitBean.getCreateUserId());
				bo.setCourseClass("COURSE");
				bo.setCrsActyId(crsActyId);
				if (bo.getSuiteNumber() != null) {
					bo.setServicesAddressId(Long.parseLong(bo.getSuiteNumber()));
				}
				bo.setHolidayFlag(courseActivitiesT1Service.courseActivitiesT1Trigger(bo.getCaseloadType(), "N", bo.getCreateUserId()));
			});
			liReturn = oimworkrRepository.crsActyInsertCourseActivities(CommitBean.getInsertList());
			CommitBean.getInsertList().forEach(bo -> {
				bo.setCreateUserId(CommitBean.getCreateUserId());
				if (bo.getSuiteNumber() != null) {
					bo.setServicesAddressId(Long.parseLong(bo.getSuiteNumber()));
				}
				courseActivitiesT3Service.courseActivitiesT3Trigger("COURSE", bo.getProgramId(), bo.getCrsActyId(),bo.getCreateUserId());
				bo.setProviderPartyCode(bo.getAgyLocId());
				courseActivitiesT2Service.CourseActivitiesT2Trigger(bo.getProviderPartyCode(), bo.getProviderPartyId(),
						bo.getProviderPartyClass(), bo.getCrsActyId(), bo.getCreateUserId());
			});
		}
		if (CommitBean.getUpdateList() != null && CommitBean.getUpdateList().size() > 0) {
			for (CourseActivities bean : CommitBean.getUpdateList()) {
				bean.setModifyUserId(CommitBean.getCreateUserId());
				liReturn = oimworkrRepository.crsActyUpdateCourseActivitiesOne(bean);
			}
		}
		if (CommitBean.getDeleteList() != null && CommitBean.getDeleteList().size() > 0) {
			for(CourseActivities bean:CommitBean.getDeleteList()) {
				bean.setModifyUserId(CommitBean.getCreateUserId());
				Boolean exist = oimworkrRepository.deleteRecordExist(bean.getCrsActyId());
				if(exist) {
					return 3;
				}
			}
			liReturn = oimworkrRepository.crsActyDeleteCourseActivities(CommitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ProgramServices> rgProjectTypeRecordGroup() {
		return oimworkrRepository.rgProjectTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<Corporates> rgProviderRecordGroup() {
		return oimworkrRepository.rgProviderRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<VAddresses> rgProjectLocationRecordGroup(Integer providerId) {
		return oimworkrRepository.rgProjectLocationRecordGroup(providerId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<AgencyLocations> rgAgencyLocationRecordGroup(String caseLoadId) {
		return oimworkrRepository.rgAgencyLocationRecordGroup(caseLoadId);

	}

	@Override
	public List<VAddresses> crsActyToGEtSuiteOne(Long ownerId) {
		return null;
	}
	

}