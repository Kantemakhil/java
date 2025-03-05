package net.syscon.s4.inst.visitsmanagement.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.AgencyVisitSlots;
import net.syscon.s4.inst.visitsmanagement.OiivisitRepository;
import net.syscon.s4.inst.visitsmanagement.OiivisitService;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderAllVisitors;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisitDetails;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisits;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisitsCommitBean;
import net.syscon.s4.pkgs.tag_visits.TagVisitsService;
import net.syscon.s4.triggers.OffenderVisitsT1Service;

/**
 * Class OiivisitServiceImpl
 */
@Service
public class OiivisitServiceImpl extends BaseBusiness implements OiivisitService {

	@Autowired
	private OiivisitRepository oiivisitRepository;
	
	@Autowired
	private OffenderVisitsT1Service offenderVisitsT1Service;
	
	@Autowired
	private TagVisitsService tagVisitsService;
	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiivisitServiceImpl.class.getName());

	/**
	 * Creates new OiivisitServiceImpl class Object
	 */
	public OiivisitServiceImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<VOffenderVisitDetails> offVisExecuteQuery(final VOffenderVisitDetails searchRecord) {
		List<VOffenderVisitDetails> returnList=new ArrayList<VOffenderVisitDetails>();
		List<VOffenderVisitDetails> finalReturnList=new ArrayList<VOffenderVisitDetails>();
		returnList = oiivisitRepository.offVisExecuteQuery(searchRecord);
		if(searchRecord.getFacilityList()!=null && !searchRecord.getFacilityList().isEmpty()) {
			for (VOffenderVisitDetails vOffenderVisitDetails : returnList) {
				for (String facilityObj : searchRecord.getFacilityList()) {
					if(vOffenderVisitDetails!=null && facilityObj!=null && facilityObj.equals(vOffenderVisitDetails.getVisitType())) {
						finalReturnList.add(vOffenderVisitDetails);
					}
					
				}
			}
		} else {
			finalReturnList=returnList;
		}	
		return finalReturnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<VOffenderAllVisitors> offImpExecuteQuery(final VOffenderVisitDetails searchRecord) {
		return oiivisitRepository.offImpExecuteQuery(searchRecord);

	}

	public List<AgencyLocations> rgAgencyLocationsRecordGroup(final String caseloadId, final String caseloadType) {
		final List<AgencyLocations> returnList = oiivisitRepository.rgAgencyLocationsRecordGroup(caseloadId,
				caseloadType);
		returnList.forEach(result -> {
			result.setCode(result.getAgyLocId());
			result.setDescription(result.getDescription());

		});
		if(Optional.ofNullable(returnList).isPresent()) {
			returnList.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return returnList;
	}

	@Override
	public List<AgencyInternalLocations> rgAgyVisitDayOfWeekRecordGroup(String agyLocId) {
		return oiivisitRepository.rgAgyVisitDayOfWeekRecordGroup(agyLocId);
	}

	@Override
	public List<VOffenderVisits> rgAgyVisitTimeSlotRecRecordGroup(String agyLocId, String weekDay) {
		return oiivisitRepository.rgAgyVisitTimeSlotRecRecordGroup(agyLocId, weekDay);
	}

	@Override
	public List<AgencyVisitSlots> rgAgyVisitSlotsRecRecordGroup(String agyLocId, String weekDay, String timeSlotSeq) {
		// TODO Auto-generated method stub
		return oiivisitRepository.rgAgyVisitSlotsRecRecordGroup(agyLocId, weekDay,timeSlotSeq);
	}

	@Override
	@Transactional
	public Integer offenderVisitsSaveForm(VOffenderVisitsCommitBean commitBean) {
		Integer liReturn = 0;
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bean -> {
				bean.setModifyUserId(commitBean.getCreateUserId());
				bean.setRecordUserId(commitBean.getCreateUserId());
				offenderVisitsT1Service.offenderVisitsT1(bean.getVisitStatus());
			});
			liReturn = oiivisitRepository.offenderVisitsSaveForm(commitBean.getUpdateList());
			for (final VOffenderVisits visitsPostUpdate : commitBean.getUpdateList()) {
				if (visitsPostUpdate.getOutcomeReasonCode() != null) {
					 tagVisitsService.cancelVisitors(
							visitsPostUpdate.getOffenderVisitId() == null ? null
									: visitsPostUpdate.getOffenderVisitId().longValue(),
							visitsPostUpdate.getOutcomeReasonCode(), commitBean.getCreateUserId());
				} else if (visitsPostUpdate.getVisitStatus() != null) {
					 tagVisitsService.completeVisitors(visitsPostUpdate, commitBean.getCreateUserId());
				}

			}
		}
		
		return liReturn;
	}

	@Override
	public List<AgencyVisitSlots> rgVisitLocationWithoutDay(String agyLocId) {
		return oiivisitRepository.rgVisitLocationWithoutDay(agyLocId);
	}
	
}