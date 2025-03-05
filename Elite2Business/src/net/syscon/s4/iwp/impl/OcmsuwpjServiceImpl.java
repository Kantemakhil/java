package net.syscon.s4.iwp.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.CourseActivitiesCommitBean;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.accreditedprograms.beans.VProgramProviders;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.schedules.bean.IntLocUsageLocations;
import net.syscon.s4.inst.schedules.bean.VCorporateAddresses;
import net.syscon.s4.iwp.OcmsuwpjRepository;
import net.syscon.s4.iwp.OcmsuwpjService;
import net.syscon.s4.pkgs.ocmsuwpj.OcmsuwpjPkgService;
import net.syscon.s4.pkgs.tag_unpaid_work.TagUnpaidWorkService;
import net.syscon.s4.triggers.CourseActivitiesT1Service;
import net.syscon.s4.triggers.CourseActivitiesT2Service;
import net.syscon.s4.triggers.CourseActivitiesT3Service;

/**
 * Class OcmsuwpjServiceImpl
 */
@Service
public class OcmsuwpjServiceImpl extends BaseBusiness implements OcmsuwpjService {

	@Autowired
	private OcmsuwpjRepository ocmsuwpjRepository;
	@Autowired
	private TagUnpaidWorkService tagUnpaidService;
	@Autowired
	private OcmsuwpjPkgService ocmsuwpjService;
	@Autowired
	private CourseActivitiesT2Service courseActivitiesT2Service;
	@Autowired
	private CourseActivitiesT1Service courseActivitiesT1Service;
	@Autowired
	private CourseActivitiesT3Service courseActivitiesT3Service;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<CourseActivities> courseActExecuteQuery(CourseActivities searchRecord) {
		List<CourseActivities> returnList = ocmsuwpjRepository.courseActExecuteQuery(searchRecord);
		for (final CourseActivities courseActivities : returnList) {
			if (courseActivities.getPlacementCorporateId() != null && courseActivities.getServicesAddressId() != null) {
				CourseActivities data = ocmsuwpjService.getPlacementDetails(searchRecord);
				courseActivities.setHouseInformation(data.getHouseInformation());
				courseActivities.setPostalCode(data.getPostalCode());
				courseActivities.setCountry(data.getCountry());
				courseActivities.setAreaInformation(data.getAreaInformation());
				courseActivities.setStreetInformation(data.getStreetInformation());
				courseActivities.setName(data.getName());
			}
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCOURSE_ACT
	 *
	 * 
	 */
	@Transactional
	public CourseActivities courseActCommit(CourseActivitiesCommitBean commitBean) {
		CourseActivities returnData = new CourseActivities();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			Long progIdTmp = null;
			List<ProgramServices> services=null;
			for (CourseActivities bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
				if (bean.getProviderPartyId() == null) {
					returnData.setSealFlag("2");
					return returnData;
				}
				bean.setProviderPartyClass("TEAM");
				bean.setProviderPartyId(bean.getProviderPartyId());
				bean.setCourseActivityType("WS");
				bean.setCaseloadType("COMM");
				Integer count = ocmsuwpjRepository.getCodeUniqueCntCur(bean.getProviderPartyId(), bean.getCode());
				if (count > 0) {
					returnData.setSealFlag("3");
					return returnData;
				}
				if (bean.getCrsActyId() == null) {
					Long lvNextId = ocmsuwpjRepository.getCrsActyIdCur();
					bean.setCrsActyId(lvNextId);
				}

				if (progIdTmp == null) {
					services = ocmsuwpjRepository.getProgramId();
				}
				if (bean.getProgramId() == null) {
					if(services.size() == 0) {
						returnData.setSealFlag("6");
						return returnData;
					}
					services.forEach(obj->bean.setProgramId(obj.getProgramId()));
				}
				
				courseActivitiesT1Service.courseActivitiesT1Trigger(bean.getCaseloadType(),bean.getHolidayFlag(), bean.getCreateUserId());

			}
			returnData = ocmsuwpjRepository.courseActInsertCourseActivities(commitBean.getInsertList());
			for (CourseActivities bean : commitBean.getInsertList()) {
			courseActivitiesT2Service.CourseActivitiesT2Trigger(bean.getProviderPartyCode(), bean.getProviderPartyId(), 
					bean.getProviderPartyClass(), bean.getCrsActyId(),bean.getCreateUserId());
			courseActivitiesT3Service.courseActivitiesT3Trigger(bean.getCourseClass(), bean.getProgramId(), bean.getCrsActyId(),bean.getCreateUserId());

			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			Integer count = 0;
			Integer vExists;
			List<CourseActivities> dataList = new ArrayList<CourseActivities>();
			for (CourseActivities bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
				bean.setProviderPartyClass("TEAM");
				bean.setProviderPartyId(bean.getProviderPartyId());
				bean.setCourseActivityType("WS");
				if ("UPDATE".equals(bean.getRowId())) {
					String lvCodeDbValue = bean.getCode();
					if (lvCodeDbValue != bean.getCode()) {
						count = ocmsuwpjRepository.getCodeUniqueCntCur(bean.getProviderPartyId(), bean.getCode());
						if (count > 0) {
							returnData.setSealFlag("3");
							return returnData;
						}
					}
					if (bean.getScheduleEndDate() != null) {
						vExists = tagUnpaidService.allocExists(bean.getCrsActyId(), bean.getScheduleEndDate());
						Integer data = vExists.intValue();
						if (data > 0) {
							returnData.setSealFlag("4");
							return returnData;
						}
					}
					if (bean.getScheduleEndDate() != null) {
						bean.setModifyUserId(commitBean.getCreateUserId());
						tagUnpaidService.updateEnddate(bean);

					}
				}
				dataList.clear();
				dataList.add(bean);
				returnData = ocmsuwpjRepository.courseActUpdateCourseActivities(dataList);
			}

		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			returnData = ocmsuwpjRepository.courseActDeleteCourseActivities(commitBean.getDeleteList());
		}
		return returnData;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<Teams> rgTeamRecordGroup(String user) {
		return ocmsuwpjRepository.rgTeamRecordGroup(user);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgBeneficiaryTypeRecordGroup() {
		return ocmsuwpjRepository.rgBeneficiaryTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<Corporates> rgPlacementNameRecordGroup() {
		List<Corporates> returnList = ocmsuwpjRepository.rgPlacementNameRecordGroup();
		returnList.forEach(action -> {
			if (action.getCorporateId() != null) {
				action.setCode(action.getCorporateId().toString());
			}

		});
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<VCorporateAddresses> rgPlacementAddressesRecordGroup(final BigDecimal placementCorporateId) {
		List<VCorporateAddresses> returnList = ocmsuwpjRepository.rgPlacementAddressesRecordGroup(placementCorporateId);
		returnList.forEach(address -> {
			address.setCode(address.getDescription());
			if(address.getProvStateDesc() == null) {
        		address.setProvStateDesc(address.getProvStateCode());
          }
			
          if(address.getCityName() == null) {
      		address.setCityName(address.getCityCode());
          }
		});
		return returnList;

	}

	@Override
	public List<CourseActivities> placementExecuteQuery(CourseActivities searchBean) {
		List<CourseActivities> returnList = ocmsuwpjRepository.placementExecuteQuery(searchBean);
		for (final CourseActivities courseActivities : returnList) {
			if (courseActivities.getServicesAddressId() != null) {
				CourseActivities data = ocmsuwpjService.getPlacementDetails(courseActivities);
	        	  courseActivities.setProvStateDesc(data.getProvStateDesc());
	        	  courseActivities.setCityName(data.getCityName());
	               courseActivities.setStreetAddress(data.getStreetAddress());
				courseActivities.setPostalCode(data.getPostalCode());
				courseActivities.setCountry(data.getCountry());
				courseActivities.setSuiteNumber(data.getSuiteNumber());
				courseActivities.setName(data.getName());
			}
		}
		return returnList;
	}
	
	public List<VProgramProviders> rgProviderRecordGroup(final String caseLoadId, final String caseLoadType, final String providerType,String user) {
		return ocmsuwpjRepository.rgProviderRecordGroup(caseLoadId, caseLoadType, providerType,user);

	}
	
	public List<IntLocUsageLocations> rgIntLocRecordGroup(final String agyLocId) {
		final List<IntLocUsageLocations> returnList = ocmsuwpjRepository.rgIntLocRecordGroup(agyLocId);
		for (final IntLocUsageLocations bean : returnList) {
			if ("Y".equals(bean.getActiveFlag())) {
				bean.setCanDisplay(true);
			} else {
				bean.setCanDisplay(false);
			}
		}
		return returnList;
	}

	public List<ProgramServices> rgProgramTypeRecordGroup() {
		final List<ProgramServices> returnList = ocmsuwpjRepository.rgProgramTypeRecordGroup();
		for (final ProgramServices bean : returnList) {
			if ("Y".equals(bean.getActiveFlag())) {
				bean.setCanDisplay(true);
			} else {
				bean.setCanDisplay(false);
			}
		}
		return returnList;

	}
	
	public List<VProgramProviders> rgProviderRecordGroupTeam(final String caseLoadId,final String caseLoadType,
			final String providerType,String user) {
		return ocmsuwpjRepository.rgProviderRecordGroupTeam(caseLoadId, caseLoadType, providerType,user);

	}

	@Override
	public List<Corporates> getPlacementLocationByCaseload(String caseload) {
		List<Corporates> returnList = ocmsuwpjRepository.getPlacementLocationByCaseload(caseload);
		returnList.forEach(action -> {
			if (action.getCorporateId() != null) {
				action.setCode(action.getCorporateId().toString());
			}

		});
		return returnList;
	}

}