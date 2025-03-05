package net.syscon.s4.cm.programsservices.maintenance.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.programsservices.maintenance.OcmsvacpRepository;
import net.syscon.s4.cm.programsservices.maintenance.OcmsvacpService;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.CourseActivitiesCommitBean;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.accreditedprograms.beans.VCoursePhaseOfferings;
import net.syscon.s4.inst.accreditedprograms.beans.VCoursePhaseOfferingsCommitBean;
import net.syscon.s4.inst.accreditedprograms.beans.VProgramProviders;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.pkgs.ocmsvacp.OcmsvacpPkgService;
import net.syscon.s4.pkgs.tag_service.TagServiceService;
import net.syscon.s4.triggers.CourseActivitiesT1Service;
import net.syscon.s4.triggers.CourseActivitiesT2Service;
import net.syscon.s4.triggers.CourseActivitiesT3Service;
import net.syscon.s4.triggers.VCoursePhaseOfferingsTuRepository;
import net.syscon.s4.triggers.VCoursePhaseOfferingsTuService;

/**
 * Class OcmsvacpServiceImpl
 */
@Service
public class OcmsvacpServiceImpl extends BaseBusiness implements OcmsvacpService {

	@Autowired
	private OcmsvacpRepository ocmsvacpRepository;
	@Autowired
	private CourseActivitiesT2Service courseActivitiesT2Service;
	@Autowired
	private CourseActivitiesT1Service courseActivitiesT1Service;
	@Autowired
	private CourseActivitiesT3Service courseActivitiesT3Service;
	@Autowired
	private net.syscon.s4.pkgs.ocmsvacp.OcmsvacpPkgService ocmsvacpService;

	@Autowired
	private VCoursePhaseOfferingsTuService vCoursePhaseOfferingsTuService;

	@Autowired
	private VCoursePhaseOfferingsTuRepository vCoursePhaseOfferingsTuRepository;
	@Autowired
	private OcmsvacpPkgService ocmsvacpPkgService;
	
	@Autowired
	private TagServiceService tagServiceService;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<CourseActivities> crsActExecuteQuery(CourseActivities searchRecord) {
		return ocmsvacpRepository.crsActExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCRS_ACT
	 *
	 * 
	 */
	@Transactional
	public Integer crsActCommit(CourseActivitiesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (CourseActivities courseActivities : commitBean.getInsertList()) {
				Long actyId = ocmsvacpRepository.getCrsActyId();
				courseActivities.setCreateUserId(commitBean.getCreateUserId());
				boolean value =ocmsvacpPkgService.checkCodeExists(courseActivities.getProviderPartyId(), courseActivities.getProviderPartyCode(), courseActivities.getCode(), "ACP");
				if(value) {
					return 4;
				}
				if ("INT".equals(courseActivities.getProviderType())
						&& "INST".equals(courseActivities.getCaseloadType())) {
					courseActivities.setProviderPartyClass("AGY");
				} else if ("INT".equals(courseActivities.getProviderType())
						&& "COMM".equals(courseActivities.getCaseloadType())) {
					courseActivities.setProviderPartyClass("TEAM");

				} else if ("EXT".equals(courseActivities.getProviderType())) {
					courseActivities.setProviderPartyClass("CORP");
				}

				if ("INT".equals(courseActivities.getProviderType())
						&& "INST".equals(courseActivities.getCaseloadType())) {
					ocmsvacpService.getDefaultaddress(courseActivities);
				} else if ("INT".equals(courseActivities.getProviderType())
						&& "COMM".equals(courseActivities.getCaseloadType())) {
					ocmsvacpService.getDefaultaddress(courseActivities);
				} else {
					ocmsvacpService.getDefaultaddress(courseActivities);
				}
				courseActivities.setCrsActyId(actyId);
				courseActivities.setCourseActivityType("AP");
				for (CourseActivities bean : commitBean.getInsertList()) {
				String holidayFlag = 	courseActivitiesT1Service.courseActivitiesT1Trigger(bean.getCaseloadType(), bean.getHolidayFlag(),
							bean.getCreateUserId());
				bean.setHolidayFlag(holidayFlag);
				}
			}
			liReturn = ocmsvacpRepository.crsActInsertCourseActivities(commitBean.getInsertList());
			for (CourseActivities bean : commitBean.getInsertList()) {
				courseActivitiesT2Service.CourseActivitiesT2Trigger(bean.getProviderPartyCode(),
						bean.getProviderPartyId(), bean.getProviderPartyClass(), bean.getCrsActyId(),
						bean.getCreateUserId());
				courseActivitiesT3Service.courseActivitiesT3Trigger(bean.getCourseClass(), bean.getProgramId(),
						bean.getCrsActyId(),bean.getCreateUserId());

			}
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (CourseActivities courseActivities : commitBean.getUpdateList()) {
				courseActivities.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmsvacpRepository.crsActUpdateCourseActivities(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			for (CourseActivities bean : commitBean.getDeleteList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
				Boolean existsVal = ocmsvacpRepository.isCoursePhaseExists(bean);
				if (existsVal) {
					return 2;
				}
				tagServiceService.deleteCourseActivityAreas(bean.getCrsActyId(),commitBean.getCreateUserId());
				tagServiceService.deleteCourseActivityParties(bean.getCrsActyId(),commitBean.getCreateUserId());
				tagServiceService.deleteCourseActivityProf(bean.getCrsActyId(),commitBean.getCreateUserId());
			}

			liReturn = ocmsvacpRepository.crsActDeleteCourseActivities(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<VCoursePhaseOfferings> vCrsPhsExecuteQuery(CourseActivities searchRecord) {
		return ocmsvacpRepository.vCrsPhsExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstV_CRS_PHS
	 *
	 * 
	 */
	public VCoursePhaseOfferings vCrsPhsCommit(final VCoursePhaseOfferingsCommitBean commitBean) {
		VCoursePhaseOfferings oldObj = new VCoursePhaseOfferings();
		
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {

			for (final VCoursePhaseOfferings newObj : commitBean.getUpdateList()) {
				newObj.setCreateUserId(commitBean.getCreateUserId());
				newObj.setModifyUserId(commitBean.getCreateUserId());
				oldObj = vCoursePhaseOfferingsTuRepository.getOldValuesVCoursePhaseOfferings(newObj);
				vCoursePhaseOfferingsTuService.vCoursePhaseOfferingsTuTgr(newObj, oldObj);
				oldObj.setSealFlag("1");
			}
		}
		return oldObj;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgRefCodeRecordGroup() {
		return ocmsvacpRepository.rgRefCodeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<VProgramProviders> rgProviderRecordGroup(String providerType, String caseLoadType, String caseLoadId,String userId) {
		return ocmsvacpRepository.rgProviderRecordGroup(providerType, caseLoadType, caseLoadId, userId);

	}

	/**
	 * This method is used to execute a record group Integer count = 0; for (final
	 * Areas areas : returnList) { c areas.setListSeq(count); } return returnList;
	 * 
	 */
	public List<TeamMembers> rgTeamAgyLocsRecordGroup(String user, String caseloadId) {
		final List<TeamMembers> returnList = ocmsvacpRepository.rgTeamAgyLocsRecordGroup(user,caseloadId);

		Integer count = 0;
		for (final TeamMembers areas : returnList) {
			count = count + 1;
			areas.setListSeq(count);
			areas.setCanDisplay(true);
		}
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<TeamMembers> rgCorpLocsRecordGroup() {
		List<TeamMembers> returnList = ocmsvacpRepository.rgCorpLocsRecordGroup();
		returnList.forEach(action -> {
			action.setCanDisplay(true);
		});
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<AgencyLocations> rgAgyLocsRecordGroup(String caseLoadId) {
		final List<AgencyLocations> returnList = ocmsvacpRepository.rgAgyLocsRecordGroup(caseLoadId);
		Integer count = 0;
		for (final AgencyLocations areas : returnList) {
			count = count + 1;
			areas.setListSeq(count);
			areas.setCanDisplay(true);
		}
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ProgramServices> rgAccProgramRecordGroup() {
		List<ProgramServices> returnList = ocmsvacpRepository.rgAccProgramRecordGroup();
		returnList.forEach(action -> {
			if ("Y".equals(action.getActiveFlag())) {
				action.setCanDisplay(true);
			} else {
				action.setCanDisplay(false);
			}
		});
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<StaffMembers> rgIntLocationRecordGroup(String providerPartyCode) {
		return ocmsvacpRepository.rgIntLocationRecordGroup(providerPartyCode);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<VAddresses> rgAddressRecordGroup(Long providerPartyId) {
		return ocmsvacpRepository.rgAddressRecordGroup(providerPartyId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<VAddresses> rgAgyAddressRecordGroup(String providerPartyCode) {
		return ocmsvacpRepository.rgAgyAddressRecordGroup(providerPartyCode);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<VAddresses> rgAllAgyAddressRecordGroup(Long providerPartyId) {
		List<VAddresses> returnList = ocmsvacpRepository.rgAllAgyAddressRecordGroup(providerPartyId);
		returnList.forEach(action -> {
			action.setCode(action.getAddressId().toString());
		});
		return returnList;

	}

	@Override
	public List<VCoursePhaseOfferings> addressExecuteQuery(VCoursePhaseOfferings searchBean) {
		List<VCoursePhaseOfferings> returnList = ocmsvacpRepository.addressExecuteQuery(searchBean);
		returnList.forEach(action -> {
			if (action != null) {
				VAddresses data = ocmsvacpRepository.getPlacementDetails(action);
				if(data.getProvStateDesc() == null) {
					action.setProvStateDesc(data.getProvStateCode());
	          }else {
	        	  action.setProvStateDesc(data.getProvStateDesc());
	          }
				
	          if(data.getCityName() == null) {
	        	  action.setCityName(data.getCityCode());
	          }else {
	        	  action.setCityName(data.getCityName());
	          }
	          action.setSuiteNumber(data.getSuiteNumber());
	          action.setStreetAddress(data.getStreetAddress());
				action.setPostalCode(data.getZipPostalCode());
				action.setCountry(data.getCountry());
			
			}

		});
		return returnList;
	}

	public List<VAddresses> addressExecuteQueryDialog(CourseActivities searchBean) {
		List<VAddresses> returnList=null;
		if("INT".equals(searchBean.getProviderType())){
			returnList=ocmsvacpRepository.rgAllAddressDeatails(searchBean.getCaseloadId());
		} else {
			returnList= ocmsvacpRepository.rgAddressRecordGroup(searchBean.getProviderPartyId());
			
		}
		if(returnList!=null && !returnList.isEmpty()) {
			for(VAddresses address:returnList) {
				if(address.getProvStateDesc() == null) {
					address.setProvStateDesc(address.getProvStateCode());
	          }
				
	          if(address.getCityName() == null) {
	        	  address.setCityName(address.getCityCode());
	          }
	          address.setStreetAddress(address.getStreetAddress());
			}
		}
		return returnList;
		
		
	}

	@Override
	public String errorNameValidation(String error) {
		return ocmsvacpRepository.errorNameValidation(error);
	}
}