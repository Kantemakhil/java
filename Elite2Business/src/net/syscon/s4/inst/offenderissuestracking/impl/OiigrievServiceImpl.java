package net.syscon.s4.inst.offenderissuestracking.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.offenderissuestracking.OiigrievRepository;
import net.syscon.s4.inst.offenderissuestracking.OiigrievService;
import net.syscon.s4.inst.offenderissuestracking.beans.VGrievanceInquiry;

@Service
public class OiigrievServiceImpl extends BaseBusiness implements OiigrievService {

	@Autowired
	private OiigrievRepository oiigrievRepository;

	/**
	 * Logger object used to print the log in the file
	 */

	/**
	 * Creates new OiigrievServiceImpl class Object
	 */
	public OiigrievServiceImpl() {
		
	} 
		

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<VGrievanceInquiry> grieDetExecuteQuery(VGrievanceInquiry searchRecord) {
	searchRecord.setDaysRem(searchRecord.getDaysRem() == null ?BigDecimal.ONE :searchRecord.getDaysRem());
		List<VGrievanceInquiry> resultList = oiigrievRepository.grieDetExecuteQuery(searchRecord);
		resultList.forEach(result -> {
			if (result.getDaysRem() != null) {
			if (result.getDaysRem().compareTo(BigDecimal.valueOf(Long.valueOf("-99"))) == -1) 
				result.setDaysRem(BigDecimal.valueOf(Long.valueOf("-99")));
			 else if (result.getDaysRem().compareTo(BigDecimal.valueOf(Long.valueOf("99"))) == 1) 
					result.setDaysRem(BigDecimal.valueOf(Long.valueOf("99")));
			}
		});

		resultList.forEach(ele -> {
			if (ele.getDaysRem() == null) {
				Long days = oiigrievRepository.getResponceDays(ele.getGrievanceId());
				ele.setDaysRem(days != null ? BigDecimal.valueOf(days) : null);
			}
		});

		return resultList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstGRIE_DET
	 *
	 * 
	 */
	@Transactional
	public Integer grieDetCommit(VGrievanceInquiry CommitBean) {
		int liReturn = 0;
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<AgencyLocations> rgAgyRecordGroup() {
		List<AgencyLocations> resultData = oiigrievRepository.rgAgyRecordGroup();
		resultData.forEach(result -> {
			result.setCode(result.getAgyLocId());
			result.setAgencyLocationType(result.getDescription());
			result.setDescription(result.getCode());
		});
		return resultData;

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> rgGrieTypeRecordGroup(String user) {
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		returnList = oiigrievRepository.rgGrieTypeRecordGroup(user);
		for (ReferenceCodes referenceCodes : returnList) {
			
			if(referenceCodes.getCreateFlag()!= null && referenceCodes.getCreateFlag().equalsIgnoreCase(ApplicationConstants.YFLAG)
					&& referenceCodes.getActiveFlag().equalsIgnoreCase(ApplicationConstants.YFLAG)) {
				referenceCodes.setCanDisplay(true);
			} else {
				referenceCodes.setCanDisplay(false);
			}
		}
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<StaffMembers> rgStaffAsgRecordGroup() {
		List<StaffMembers> returnList = new ArrayList<StaffMembers>();
		returnList =  oiigrievRepository.rgStaffAsgRecordGroup();
		for(StaffMembers obj : returnList) {
			if("Y".equalsIgnoreCase(obj.getSealFlag())) {
				obj.setCanDisplay(true);
			}else {
				obj.setCanDisplay(false);
			}
		}
		return  returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> rgLevelRecordGroup() {
		return oiigrievRepository.rgLevelRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<StaffMembers> rgStaffInvRecordGroup() {
		return oiigrievRepository.rgStaffInvRecordGroup();

	}

	@Override
	public String whenNewRecordInstance(final String userId) {
		return oiigrievRepository.whenNewRecordInstance(userId);
	}


	@Override
	public List<ReferenceCodes> rgGrieReasonCodeRecordGroup(String grievType) {
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		returnList = oiigrievRepository.rgGrievReasonRecordGroup(grievType);
		return returnList;
	}


	@Override
	public List<ReferenceCodes> rgGrieTransactionRecordGroup(String grievType) {
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		returnList = oiigrievRepository.rgGrieTransactionRecordGroup(grievType);
		return returnList;
	}

}