package net.syscon.s4.inst.schedules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchWaitLists;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchWaitListsCommitBean;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;
/**
 * class OidstwjuController
 */
@EliteController
public class OidstwjuController {
@Autowired
private OidstwjuService oidstwjuService;

@Autowired
private ProsmainService prosmainService;

@Autowired
private OidscmovService oidscmovService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OidstwjuController.class.getName());
	/**
	 *getting rgEscort LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidstwju/rgEscortRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgEscortRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = oidstwjuService.rgEscortRecordGroup();
		} catch(Exception e){
			final ReferenceCodes bean = new ReferenceCodes();
			logger.error(this.getClass().getName()+"In rgEscortRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 *getting rgAgencyLocation LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidstwju/rgAgencyLocationRecordGroup",method=RequestMethod.GET)
	public List<AgencyLocations> rgAgencyLocationRecordGroup(@RequestParam(value = "agyLocId") final String agyLocId)  {
		List<AgencyLocations> recordList =new ArrayList<AgencyLocations>();
		try {
			recordList = oidstwjuService.rgAgencyLocationRecordGroup(agyLocId);
		} catch(Exception e){
			final AgencyLocations bean = new AgencyLocations();
			logger.error(this.getClass().getName()+"In rgAgencyLocationRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 *getting rgMoveReason LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidstwju/rgMoveReasonRecordGroup",method=RequestMethod.GET)
	public List<MovementReasons> rgMoveReasonRecordGroup() {
		List<MovementReasons> recordList =new ArrayList<MovementReasons>();
		try {
			recordList = oidstwjuService.rgMoveReasonRecordGroup();
		} catch(Exception e){
			final MovementReasons bean = new MovementReasons();
			logger.error(this.getClass().getName()+"In rgMoveReasonRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 *getting rgStatus LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidstwju/rgStatusRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgStatusRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = oidstwjuService.rgStatusRecordGroup();
		} catch(Exception e){
			final ReferenceCodes bean = new ReferenceCodes();
			logger.error(this.getClass().getName()+"In rgStatusRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 *getting rgPriority LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidstwju/rgPriorityRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgPriorityRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = oidstwjuService.rgPriorityRecordGroup();
		} catch(Exception e){
			final ReferenceCodes bean = new ReferenceCodes();
			logger.error(this.getClass().getName()+"In rgPriorityRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 *getting rgCancelReason LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidstwju/rgCancelReasonRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgCancelReasonRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = oidstwjuService.rgCancelReasonRecordGroup();
		} catch(Exception e){
			final ReferenceCodes bean = new ReferenceCodes();
			logger.error(this.getClass().getName()+"In rgCancelReasonRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 *getting rgApprovedBy LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidstwju/rgApprovedByRecordGroup",method=RequestMethod.GET)
	public List<StaffMembers> rgApprovedByRecordGroup(@RequestParam(value = "caseloadId") final String caseloadId) {
		List<StaffMembers> recordList =new ArrayList<StaffMembers>();
		try {
			recordList = oidstwjuService.rgApprovedByRecordGroup(caseloadId);
		} catch(Exception e){
			final StaffMembers bean = new StaffMembers();
			logger.error(this.getClass().getName()+"In rgApprovedByRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 *Fetching the record from database table
	 * @param searchBean {@link VOffenderAllSchedules}
	 * @return a list of the VOffenderAllSchedules {@link VOffenderAllSchedules} for the matched VOffenderAllSchedules
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidstwju/offSchExecuteQuery", method=RequestMethod.POST)
	public List<VOffenderAllSchedules> offSchExecuteQuery(@RequestBody final VOffenderAllSchedules searchBean) {
		List<VOffenderAllSchedules> searchResult = new ArrayList<>();
		try {
			searchResult = oidstwjuService.offSchExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In offSchExecuteQuery method : ", e);
		}
		return searchResult;
	}

	/**
	 *Performing basic Oracle form functions i.e. insert,delete, update into the database table
	 * @param commitBean {@link VOffenderAllSchedulesCommitBean}
	 * @return offender schedule commit status as Integer {@link Integer} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/oidstwju/offSchCommit",method=RequestMethod.POST)
	public @ResponseBody Integer offSchCommit(@RequestBody final VOffenderAllSchedulesCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidstwjuService.offSchCommit(commitBean);
			if(1==liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "3");
			}
		}catch(Exception e){
			logger.error(this.getClass().getName()+"In offSchCommit method : ", e);
		}
		return liReturn;
	}
	

	/**
	 *Fetching the record from database table
	 * @param searchBean {@link OffenderIndSchWaitLists}
	 * @return a list of the OffenderIndSchWaitLists {@link OffenderIndSchWaitLists} for the matched OffenderIndSchWaitLists
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidstwju/offSwlExecuteQuery", method=RequestMethod.POST)
	public List<OffenderIndSchWaitLists> offSwlExecuteQuery(@RequestBody final OffenderIndSchWaitLists searchBean) {
		List<OffenderIndSchWaitLists> searchResult = new ArrayList<>();
		try {
			searchResult = oidstwjuService.offSwlExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderIndSchWaitLists bean = new OffenderIndSchWaitLists();
			logger.error(this.getClass().getName()+"In offSwlExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 *Performing basic Oracle form functions i.e. insert,delete, update into the database table
	 * @param commitBean {@link OffenderIndSchWaitListsCommitBean}
	 * @return success/failure of the insert/update {@link Integer} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/oidstwju/offSwlCommit",method=RequestMethod.POST)
	public @ResponseBody Integer offSwlCommit(@RequestBody final OffenderIndSchWaitListsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidstwjuService.offSwlCommit(commitBean);
		}catch(Exception e){
			logger.error(this.getClass().getName()+"In offSwlCommit method : ", e);
		}
		return liReturn;
	}
	/**
	 *getting getCurrentDate  
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstwju/getCurrentDate", method = RequestMethod.GET)
	public Date getCurrentDate() {
		Date value = null;
		try {
			value = oidstwjuService.getCurrentDate();
		} 
		catch (Exception e) {
			logger.error(this.getClass().getName()+"In getCurrentDate method :",e);
		}
		return value;
	}
	
	/**
	 *getting getParentCode LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidstwju/getParentCode",method=RequestMethod.POST)
	public ReferenceCodes getParentCode(@RequestBody final ReferenceCodes refCodesBean) {
		 ReferenceCodes refResultBean = new ReferenceCodes();
		try {
			refResultBean = oidstwjuService.getParentCode(refCodesBean);
		} catch(Exception e){
			final ReferenceCodes bean = new ReferenceCodes();
			logger.error(this.getClass().getName()+"In getParentCode method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return refResultBean;
	}
	
	/**
	 *getting getStaffId 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstwju/getStaffId", method = RequestMethod.GET)
	public Integer getStaffId() {
		Integer value = 0;
    String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			value = oidstwjuService.getStaffId(userName);
		} 
		catch (Exception e) {
			logger.error(this.getClass().getName()+"In getStaffId method : ",e);
		}
		return value;
	}
	
	/**
	 *Before inserting the record it verifying whether any other schedules are assigned to the offender
	 * @param offIndSch {@link VOffenderAllSchedulesCommitBean}
	 * @return check schedule conflict as Integer {@link Integer} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidstwju/checkScheduleConflict",method=RequestMethod.POST)
	public @ResponseBody Integer checkScheduleConflict(@RequestBody final VOffenderAllSchedulesCommitBean offIndSch) {
		int liReturn = 0;
		try {
			liReturn = oidstwjuService.checkScheduleConflict(offIndSch);
		}catch(Exception e){
			logger.error(this.getClass().getName()+"In checkScheduleConflict method : ", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidstwju/offSchOnCheckDeleteMaster",method=RequestMethod.POST)
	public List<Object> offSchOnCheckDeleteMaster(final String eventId) {
		List<Object> lstSchWait = new ArrayList<Object>();
		try {
			lstSchWait = oidstwjuService.offSchOnCheckDeleteMaster(eventId);
		} catch (Exception e) {
			final  OffenderIndSchWaitLists bean = new OffenderIndSchWaitLists();
			logger.error(this.getClass().getName()+"In offSchOnCheckDeleteMaster method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return lstSchWait;
	}
	
	/**
	 *getting getStaffId 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstwju/showApprovedDetails", method = RequestMethod.POST)
	public StaffMembers showApprovedDetails(@RequestBody final StaffMembers staffMemBean) {
		 StaffMembers bean = new StaffMembers();
		try {
			bean = oidstwjuService.showApprovedDetails(staffMemBean);
		} 
		catch (Exception e) {
			final  StaffMembers obj = new StaffMembers();
			logger.error(this.getClass().getName()+"In showApprovedDetails method : ",e);
			obj.setErrorMessage(e.getMessage());
		}
		return bean;
	}
	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into the database table
	 * @param commitBean {@link VOffenderAllSchedulesCommitBean}
	 * @return checking the non association as Integer {@link Integer} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidstwju/chkNonAssociation",method=RequestMethod.POST)
	public @ResponseBody Integer chkNonAssociation(@RequestBody final VOffenderAllSchedulesCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = oidstwjuService.chkNonAssociation(commitBean);
		}catch(Exception e){
			logger.error(this.getClass().getName()+"In chkNonAssociation method : ", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstwju/chkNonAssociationDate", method = RequestMethod.POST)
	public String checkNonAssociations(@RequestBody final VOffenderAllSchedulesCommitBean commitBean) {
		String resultString = null;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			resultString = oidstwjuService.checkNonAssociations(commitBean);
		} catch (final Exception e) {
			logger.error(this.getClass().getName()+"Error occured in checkNonAssociations :", e);
		}
		return resultString;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstwju/checkScheduleConflictmsg", method = RequestMethod.POST)
	public @ResponseBody Integer checkScheduleConflict(@RequestBody final CourtEvents offCourts) {
		int liReturn = 0;
		try {
			liReturn = oidscmovService.checkScheduleConflict(offCourts);
		} catch (Exception e) {
			logger.error("In checkScheduleConflict method : ", e);
		}
		return liReturn;
	}
}