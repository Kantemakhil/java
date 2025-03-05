package net.syscon.s4.inst.visitsmanagement.maintenance.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.AgencyVisitDays;
import net.syscon.s4.im.beans.AgencyVisitDaysCommitBean;
import net.syscon.s4.im.beans.AgencyVisitSlots;
import net.syscon.s4.im.beans.AgencyVisitSlotsCommitBean;
import net.syscon.s4.im.beans.AgencyVisitTimes;
import net.syscon.s4.im.beans.AgencyVisitTimesCommitBean;
import net.syscon.s4.inst.visitsmanagement.maintenance.OimvdtslRepository;
import net.syscon.s4.inst.visitsmanagement.maintenance.OimvdtslService;
import net.syscon.s4.pkgs.tag_visits.TagVisitsService;
/**
 * Class OimvdtslServiceImpl */
@Service
public class OimvdtslServiceImpl extends BaseBusiness implements OimvdtslService{

	@Autowired
	private OimvdtslRepository oimvdtslRepository;
	@Autowired
	private TagVisitsService tagVisitsService;
	/**
	 *to check child records exist or not from this method
	 */
	public Integer agyVisitDaysOnCheckDeleteMaster(final AgencyVisitTimes paramBean)  {
		return oimvdtslRepository.agyVisitDaysOnCheckDeleteMaster(paramBean);

	}
	/**
	 *to check child records exist or not from this method
	 */
	public Integer agyVisitTimesOnCheckDeleteMaster(final AgencyVisitSlots paramBean)  {
		return oimvdtslRepository.agyVisitTimesOnCheckDeleteMaster(paramBean);

	}
	/**Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLExceptions
	 */
	public List<AgencyVisitDays> agyVisitDaysExecuteQuery(final AgencyVisitDays searchRecord)  {
		return oimvdtslRepository.agyVisitDaysExecuteQuery(searchRecord);

	}

	/**Insert the records from database table
	 *
	 * @param lstAGY_VISIT_DAYS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public List<AgencyVisitDays> agyVisitDaysCommit(final AgencyVisitDaysCommitBean commitBean)  {
		final List<AgencyVisitDays> liReturnData = new ArrayList<>();
		final AgencyVisitDays agencyVisitDays = new AgencyVisitDays();
		int liReturn = 0;
		//insertRecords
		if (commitBean.getInsertList() != null	&& commitBean.getInsertList().size() > 0) {
			for (AgencyVisitDays bean : commitBean.getInsertList()) {
				//adding user to bean
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oimvdtslRepository.agyvisitdaysInsertAgencyVisitDays(commitBean.getInsertList());

		}
		//updateRecords
		if (commitBean.getUpdateList() != null	&& commitBean.getUpdateList().size() > 0) {
			for (AgencyVisitDays bean : commitBean.getUpdateList()) {
				//adding user to bean
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimvdtslRepository.agyVisitDaysUpdateAgencyVisitDays(commitBean.getUpdateList());
		}
		//deleteRecords
		if (commitBean.getDeleteList() != null	&& commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oimvdtslRepository.agyVisitDaysDeleteAgencyVisitDays(commitBean.getDeleteList());
		}
		agencyVisitDays.setReturnValue(BigDecimal.valueOf(liReturn));
		liReturnData.add(agencyVisitDays);
		return liReturnData;
	}

	/**Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<AgencyVisitTimes> agyVisitTimesExecuteQuery(final AgencyVisitTimes searchRecord)  {
		return oimvdtslRepository.agyVisitTimesExecuteQuery(searchRecord);

	}

	/**Insert the records from database table
	 *
	 * @param lstAGY_VISIT_TIMES
	 *
	 * @throws SQLException
	 */
	@Transactional
	public List<AgencyVisitTimes> agyVisitTimesCommit(final AgencyVisitTimesCommitBean commitBean)  {
		final List<AgencyVisitTimes> liReturnData = new ArrayList<>();
		AgencyVisitTimes agencyVisitTimes = new AgencyVisitTimes();
		int liReturn = 0;
		//insertRecords
		if (commitBean.getInsertList() != null	&& commitBean.getInsertList().size() > 0) {
			for (AgencyVisitTimes bean : commitBean.getInsertList()) {
				//adding user to bean
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oimvdtslRepository.agyvisittimesInsertAgencyVisitTimes(commitBean.getInsertList());
			agencyVisitTimes.setReturnValue(BigDecimal.valueOf(liReturn));
		}
		//updateRecords
		if (commitBean.getUpdateList() != null	&& commitBean.getUpdateList().size() > 0) {
			for (AgencyVisitTimes bean : commitBean.getUpdateList()) {
				//adding user to bean
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			agencyVisitTimes = oimvdtslRepository.agyVisitTimesUpdateAgencyVisitTimes(commitBean.getUpdateList());
		}
		//deleteRecords
		if (commitBean.getDeleteList() != null	&& commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			agencyVisitTimes = oimvdtslRepository.agyVisitTimesDeleteAgencyVisitTimes(commitBean.getDeleteList());
		}
		
		liReturnData.add(agencyVisitTimes);
		return liReturnData;
	}

	/**Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<AgencyVisitSlots> agyVisitSlotsExecuteQuery(final AgencyVisitSlots searchRecord)  {
		final List<AgencyVisitSlots> returnList = oimvdtslRepository.agyVisitSlotsExecuteQuery(searchRecord);
		returnList.forEach(ele -> {
			Integer capacity =  tagVisitsService.getCapacity(ele.getAgyLocId(), ele.getInternalLocationId().intValue());
			ele.setpCapacity(String.valueOf(capacity));

		});
		return returnList;
	}

	/**Fetch the nbt description from procedure
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public AgencyVisitSlots getLocationDescription(final AgencyVisitSlots objSearchDao){
		AgencyInternalLocations bean = new AgencyInternalLocations();
		bean.setAgyLocId(objSearchDao.getAgyLocId());
		bean.setInternalLocationId(objSearchDao.getInternalLocationId().intValue());
		String desc =  tagVisitsService.getLocationDesc(bean);
		objSearchDao.setNbtLocationDesc(desc);
		return objSearchDao;
	}

	/**
	 *getting capacity value from this method
	 */
	public AgencyVisitSlots agyGetCapaityFrom(final AgencyVisitSlots agencyVisitSlots)  {
		Integer capasity =  tagVisitsService.getCapacity(agencyVisitSlots.getAgyLocId(), agencyVisitSlots.getInternalLocationId().intValue());
		agencyVisitSlots.setpCapacity(capasity.toString());
		return agencyVisitSlots;
	}
	/**Insert the records from database table
	 *
	 * @param lstAGY_VISIT_SLOTS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public List<AgencyVisitSlots> agyVisitSlotsCommit(final AgencyVisitSlotsCommitBean commitBean)  {
		final List<AgencyVisitSlots> liReturnData = new ArrayList<>();
		AgencyVisitSlots agencyVisitSlots = new AgencyVisitSlots();

		int liReturn = 0;
		//insertRecords
		if (commitBean.getInsertList() != null	&& commitBean.getInsertList().size() > 0) {
			List<AgencyVisitSlots> recordSavingObject = new ArrayList<>();
			if (commitBean.getInsertList().size() > 0) {
				for (int i = 0; i < commitBean.getInsertList().size(); i++) {
					recordSavingObject = new ArrayList<>();
					final AgencyVisitSlots offenderPropertyItemObj = commitBean.getInsertList().get(i);
					final String startDate = new SimpleDateFormat("HH:mm").format(offenderPropertyItemObj.getStartTime());
					final String timeSlotDate = tagVisitsService.checkTimeslot(offenderPropertyItemObj.getInternalLocationId(), offenderPropertyItemObj.getWeekDay(),
							offenderPropertyItemObj.getAgyLocId(), startDate);
					if (timeSlotDate != null) {
						liReturn = 2;
						agencyVisitSlots.setReturnValue(BigDecimal.valueOf(liReturn));
						liReturnData.add(agencyVisitSlots);
						return liReturnData;
					}else
					{
						final Long agencyVisitSlotId = tagVisitsService.getNextAgyVisitSlotId();
						offenderPropertyItemObj.setAgencyVisitSlotId(agencyVisitSlotId);	
					}
					offenderPropertyItemObj.setCreateUserId(commitBean.getCreateUserId());
					recordSavingObject.add(offenderPropertyItemObj);
					liReturn = oimvdtslRepository.agyvisitslotsInsertAgencyVisitSlots(recordSavingObject);
					agencyVisitSlots.setReturnValue(BigDecimal.valueOf(liReturn));
				}
			}
		}
		//updateRecords
		if (commitBean.getUpdateList() != null	&& commitBean.getUpdateList().size() > 0) {
			for (AgencyVisitSlots bean : commitBean.getUpdateList()) {
				//adding user to bean
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimvdtslRepository.agyVisitSlotsUpdateAgencyVisitSlots(commitBean.getUpdateList());
			agencyVisitSlots.setReturnValue(BigDecimal.valueOf(liReturn));
		}
		//deleteRecords
		if (commitBean.getDeleteList() != null	&& commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			agencyVisitSlots = oimvdtslRepository.agyVisitSlotsDeleteAgencyVisitSlots(commitBean.getDeleteList());
		}

		liReturnData.add(agencyVisitSlots);
		return liReturnData;
	}
	/**
	 * This method is used to execute a record group
	 *
	 *@throws SQLException
	 */
	public List<ReferenceCodes> rgAgyVisitDaysRecordGroup()  {
		return oimvdtslRepository.rgAgyVisitDaysRecordGroup();

	}
	/**
	 * This method is used to execute a record group
	 *
	 *@throws SQLException
	 */
	public List<AgencyLocations> rgAgyIntLocRecordGroup(final String userId)  {
		List<AgencyLocations> returnList = oimvdtslRepository.rgAgyIntLocRecordGroup(userId);
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
	/**
	 * This method is used to execute a record group
	 *
	 *@throws SQLException
	 */
	public List<AgencyInternalLocations> rgAgyVisitSlotsRecordGroup(final String agyLocId, final String userId)  {
		final List<AgencyInternalLocations> returnList = oimvdtslRepository.rgAgyVisitSlotsRecordGroup(agyLocId, userId);
		returnList.forEach(ele -> {
			if (ele.getInternalLocationId() != null) {
				 ele.setCode(ele.getInternalLocationId().toString());
					if("Y".equals(ele.getActiveFlag())){
						ele.setCanDisplay(true);
						ele.setSealFlag("true");
					}else {
						ele.setCanDisplay(false);
					}
					}
					
				});
		return returnList;
	}
	/**
	 * This method is used to execute a record group
	 *
	 *@throws SQLException
	 */
	public List<AgencyVisitDays> rgWeekdayRecordGroup()  {
		return oimvdtslRepository.rgWeekdayRecordGroup();

	}
	/**
	 *to validate the check box in times grid
	 */
	@Override
	public String agyVisitTimescheckboxChange(final AgencyVisitTimes searchBean) {
		return oimvdtslRepository.agyVisitTimescheckboxChange(searchBean);
	}
}