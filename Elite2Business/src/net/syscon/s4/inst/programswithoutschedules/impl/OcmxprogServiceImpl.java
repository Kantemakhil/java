package net.syscon.s4.inst.programswithoutschedules.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.CourseActivitiesCommitBean;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.accreditedprograms.beans.VProgramProviders;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.programswithoutschedules.OcmxprogRepository;
import net.syscon.s4.inst.programswithoutschedules.OcmxprogService;
import net.syscon.s4.inst.schedules.bean.IntLocUsageLocations;
import net.syscon.s4.triggers.CourseActivitiesT1Service;
import net.syscon.s4.triggers.CourseActivitiesT2Service;
import net.syscon.s4.triggers.CourseActivitiesT3Service;

/**
 * Class OcmxprogServiceImpl
 */
@Service
public class OcmxprogServiceImpl extends BaseBusiness implements OcmxprogService {

	@Autowired
	private OcmxprogRepository ocmxprogRepository;
	@Autowired
	private CourseActivitiesT1Service courseActivitiesT1Service;
	@Autowired
	private CourseActivitiesT2Service CourseActivitiesT2Service;
	@Autowired
	private CourseActivitiesT3Service CourseActivitiesT3Service;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<CourseActivities> crsActExecuteQuery(final CourseActivities searchRecord) {
		return ocmxprogRepository.crsActExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCRS_ACT
	 *
	 * @throws SQLException
	 */
	@Transactional
	public List<CourseActivities> crsActCommit(final CourseActivitiesCommitBean commitBean) {
		final List<CourseActivities> saveList = new ArrayList<CourseActivities>();
		final List<CourseActivities> recordSaveLit = new ArrayList<CourseActivities>();
		Integer codeAlredyExist = 0;
		final CourseActivities returnObject = new CourseActivities();
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (int i = 0; i < commitBean.getInsertList().size(); i++) {
				final CourseActivities object = commitBean.getInsertList().get(i);
				object.setCreateUserId(commitBean.getCreateUserId());
				object.setCrsActyId(ocmxprogRepository.getCrsActyId());
				codeAlredyExist = ocmxprogRepository.checkCodeExistOcmsvacp(commitBean.getInsertList().get(i));
				if (codeAlredyExist > 0) {
					returnObject.setSealFlag("E");
					saveList.add(returnObject);
					return saveList;
				} else {
					if (("AGY").equals(commitBean.getInsertList().get(i).getProviderPartyClass())) {
						object.setServicesAddressId(ocmxprogRepository
								.getAgyAddressID(commitBean.getInsertList().get(i).getProviderPartyCode()));
					} else if (("TEAM").equals(commitBean.getInsertList().get(i).getProviderPartyClass())) {
						object.setServicesAddressId(ocmxprogRepository
								.getTeamAddressId(commitBean.getInsertList().get(i).getProviderPartyId(),object.getCreateUserId()));
					} else if (("CORP").equals(commitBean.getInsertList().get(i).getProviderPartyClass())) {
						object.setServicesAddressId(ocmxprogRepository
								.getCorpAddressId(commitBean.getInsertList().get(i).getNbtDescription()));
					}
					recordSaveLit.add(object);
				}
				courseActivitiesT1Service.courseActivitiesT1Trigger(object.getCaseloadType(), object.getHolidayFlag(),
						object.getCreateUserId());
				List<CourseActivities> list = new ArrayList<CourseActivities>();
				list.add(object);
				liReturn = ocmxprogRepository.crsActInsertCourseActivities(list);
				CourseActivitiesT2Service.CourseActivitiesT2Trigger(object.getProviderPartyCode(),
						object.getProviderPartyId(), object.getProviderPartyClass(), object.getCrsActyId(),object.getCreateUserId());
				CourseActivitiesT3Service.courseActivitiesT3Trigger(object.getCourseClass(), object.getProgramId(),
						object.getCrsActyId(),object.getCreateUserId());
			}
			
			
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (CourseActivities courseActivities : commitBean.getUpdateList() ) {
				courseActivities.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmxprogRepository.crsActUpdateCourseActivities(commitBean.getUpdateList());
		}
		returnObject.setReturnValue(liReturn);
		saveList.add(returnObject);
		return saveList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgPsProvTypeRecordGroup() {
		return ocmxprogRepository.rgPsProvTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<VProgramProviders> rgProviderRecordGroup(final String caseLoadId, final String caseLoadType, final String providerType,String user) {
		return ocmxprogRepository.rgProviderRecordGroup(caseLoadId, caseLoadType, providerType,user);

	}

	public List<VProgramProviders> rgProviderRecordGroupTeam(final String caseLoadId,final String caseLoadType,
			final String providerType,String user) {
		return ocmxprogRepository.rgProviderRecordGroupTeam(caseLoadId, caseLoadType, providerType,user);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ProgramServices> rgProgramTypeRecordGroup() {
		final List<ProgramServices> returnList = ocmxprogRepository.rgProgramTypeRecordGroup();
		for (final ProgramServices bean : returnList) {
			if ("Y".equals(bean.getActiveFlag())) {
				bean.setCanDisplay(true);
			} else {
				bean.setCanDisplay(false);
			}
		}
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<IntLocUsageLocations> rgIntLocRecordGroup(final String agyLocId) {
		final List<IntLocUsageLocations> returnList = ocmxprogRepository.rgIntLocRecordGroup(agyLocId);
		for (final IntLocUsageLocations bean : returnList) {
			if ("Y".equals(bean.getActiveFlag())) {
				bean.setCanDisplay(true);
			} else {
				bean.setCanDisplay(false);
			}
		}
		return returnList;
	}

	@Override
	public List<VAddresses> GetAddressId(final VAddresses paramBean) {
		return ocmxprogRepository.getAddressId(paramBean);
	}

}