package net.syscon.s4.cm.programsservices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasureOutcomes;
import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasures;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedulesCommitBean;
import net.syscon.s4.iwp.beans.VAcpSchedules;

/**
 * @author Arkin Software Technologies 
 * @version 1.0 
 */
@EliteController
public class OcdpatteController {
@Autowired
private OcdpatteService ocdpatteService;
@Autowired
private OcuschprService ocuschprService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OcdpatteController.class.getName());
	static final String HASELITEROLE = "hasEliteRole('read')";
	static final String EXCEPTION = "Exception :";
	/**
	 *getting rgScheduleType LOV values 
	 */
	@PreAuthorize(HASELITEROLE)
	@RequestMapping(value="/ocdpatte/rgScheduleTypeRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgScheduleTypeRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdpatteService.rgScheduleTypeRecordGroup();
		} catch(final Exception e){
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(EXCEPTION,e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting rgService LOV values 
	 */
	@PreAuthorize(HASELITEROLE)
	@RequestMapping(value="/ocdpatte/rgServiceRecordGroup",method=RequestMethod.GET)
	public List<ProgramServices> rgServiceRecordGroup() {
		List<ProgramServices> recordList =new ArrayList<ProgramServices>();
		try {
			recordList = ocdpatteService.rgServiceRecordGroup();
		} catch(final Exception e){
			final ProgramServices obj = new ProgramServices();
			logger.error(EXCEPTION,e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting rgEngagement LOV values 
	 */
	@PreAuthorize(HASELITEROLE)
	@RequestMapping(value="/ocdpatte/rgEngagementRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgEngagementRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdpatteService.rgEngagementRecordGroup();
		} catch(final Exception e){
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(EXCEPTION,e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting rgInstProvider LOV values 
	 */
	@PreAuthorize(HASELITEROLE)
	@RequestMapping(value="/ocdpatte/rgInstProviderRecordGroup",method=RequestMethod.GET)
	public List<AgencyLocations> rgInstProviderRecordGroup(@RequestParam(value = "caseLoadId") final String caseLoadId) {
		List<AgencyLocations> recordList =new ArrayList<AgencyLocations>();
		try {
 			recordList = ocdpatteService.rgInstProviderRecordGroup(caseLoadId);
		} catch(final Exception e){
			final AgencyLocations obj = new AgencyLocations();
			logger.error(EXCEPTION,e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting rgCommProvider LOV values 
	 */
	@PreAuthorize(HASELITEROLE)
	@RequestMapping(value="/ocdpatte/rgCommProviderRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgCommProviderRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdpatteService.rgCommProviderRecordGroup(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		} catch(final Exception e){
			final Teams obj = new Teams();
			logger.error(EXCEPTION,e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 *getting rgConfirmAttendance LOV values 
	 */
	@PreAuthorize(HASELITEROLE)
	@RequestMapping(value="/ocdpatte/rgConfirmAttendanceRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgConfirmAttendanceRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdpatteService.rgConfirmAttendanceRecordGroup();
		} catch(final Exception e){
			final EventMeasureOutcomes obj = new EventMeasureOutcomes();
			logger.error(EXCEPTION,e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 *getting rgUnderstanding LOV values 
	 */
	@PreAuthorize(HASELITEROLE)
	@RequestMapping(value="/ocdpatte/rgUnderstandingRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgUnderstandingRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdpatteService.rgUnderstandingRecordGroup();
		} catch(final Exception e){
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(EXCEPTION,e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting rgStaffRole LOV values 
	 */
	@PreAuthorize(HASELITEROLE)
	@RequestMapping(value="/ocdpatte/rgStaffRoleRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgStaffRoleRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdpatteService.rgStaffRoleRecordGroup();
		} catch(final Exception e){
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(EXCEPTION,e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting rgStaffName LOV values 
	 */
	@PreAuthorize(HASELITEROLE)
	@RequestMapping(value="/ocdpatte/rgStaffNameRecordGroup",method=RequestMethod.GET)
	public List<StaffMembers> rgStaffNameRecordGroup(@RequestParam final String progInstId) {
		List<StaffMembers> recordList =new ArrayList<StaffMembers>();
		try {
			recordList = ocdpatteService.rgStaffNameRecordGroup(progInstId);
		} catch(final Exception e){
			final StaffMembers obj = new StaffMembers();
			logger.error(EXCEPTION,e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize(HASELITEROLE)
	@RequestMapping(value="/ocdpatte/offCourseAttendancesExecuteQuery", method=RequestMethod.POST)
	public List<OffenderCourseAttendance> offCourseAttendancesExecuteQuery(@RequestBody final OffenderCourseAttendance searchBean) {
		List<OffenderCourseAttendance> searchResult = new ArrayList<>();
		try {
			searchResult = ocdpatteService.offCourseAttendancesExecuteQuery(searchBean);
		} catch (final Exception e) {
			final OffenderCourseAttendance bean = new OffenderCourseAttendance();
			logger.error(EXCEPTION,e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	
	@PreAuthorize(HASELITEROLE)
	@RequestMapping(value="/ocdpatte/getcourseScheduleExecuteQuery", method=RequestMethod.POST)
	public CourseSchedules getcourseScheduleExecuteQuery(@RequestBody final CourseSchedules courseSchedules) {
		CourseSchedules searchResult = new CourseSchedules();
		try {
			searchResult = ocdpatteService.getcourseScheduleExecuteQuery(courseSchedules);
		} catch (final Exception e) {
			searchResult = new CourseSchedules();
			logger.error(EXCEPTION,e);
			return searchResult;
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/ocdpatte/ocdpatteCommitBean",method=RequestMethod.POST)
	public @ResponseBody Integer ocdpatteCommitBean(@RequestBody final OcdpatteCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}

			liReturn = ocdpatteService.ocdpatteCommitBean(commitBean);
		}catch(final Exception e){

			logger.error("Exception : Ocdpatte",e);
		}
		return liReturn;
	}
	
	
	/**   
	 *Perfomring basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/ocdpatte/offCourseAttendancesCommit",method=RequestMethod.POST)
	public @ResponseBody Integer offCourseAttendancesCommit(@RequestBody final OffenderCourseAttendancesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocdpatteService.offCourseAttendancesCommit(commitBean);
		}catch(final Exception e){

			logger.error("Exception : Ocdpatte",e);
		}
		return liReturn;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize(HASELITEROLE)
	@RequestMapping(value="/ocdpatte/courseScheduleStaffsExecuteQuery", method=RequestMethod.POST)
	public List<CourseScheduleStaff> courseScheduleStaffsExecuteQuery(@RequestBody final CourseScheduleStaff searchBean) {
		List<CourseScheduleStaff> searchResult = new ArrayList<>();
		try {
			searchResult = ocdpatteService.courseScheduleStaffsExecuteQuery(searchBean);
		} catch (final Exception e) {
			final CourseScheduleStaff bean = new CourseScheduleStaff();
			logger.error(EXCEPTION,e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 *Perfomring basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/ocdpatte/courseScheduleStaffsCommit",method=RequestMethod.POST)
	public @ResponseBody Integer courseScheduleStaffsCommit(@RequestBody final CourseScheduleStaffsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocdpatteService.courseScheduleStaffsCommit(commitBean);
		}catch(final Exception e){

			logger.error("Exception : Ocdpatte",e);
		}
		return liReturn;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize(HASELITEROLE)
	@RequestMapping(value="/ocdpatte/deliveryDetailsExecuteQuery", method=RequestMethod.POST)
	public List<CourseSchedules> deliveryDetailsExecuteQuery(@RequestBody final CourseSchedules searchBean) {
		List<CourseSchedules> searchResult = null;
		try {
			searchResult = ocdpatteService.deliveryDetailsExecuteQuery(searchBean);
		} catch (final Exception e) {
			final CourseSchedules bean = new CourseSchedules();
			logger.error(EXCEPTION,e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 *Perfomring basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/ocdpatte/deliveryDetailsCommit",method=RequestMethod.POST)
	public @ResponseBody Integer deliveryDetailsCommit(@RequestBody final CourseSchedulesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocdpatteService.deliveryDetailsCommit(commitBean);
		}catch(final Exception e){

			logger.error("Exception : Ocdpatte",e);
		}
		return liReturn;
	}

	@PreAuthorize(HASELITEROLE)
	@RequestMapping(value="/ocdpatte/checkUa", method=RequestMethod.POST)
	public Map checkUa(@RequestBody final OffenderCourseAttendance searchBean) {
		Map searchResult = new HashMap<>();
		try {
			searchResult = ocdpatteService.checkUa(searchBean);
		} catch (final Exception e) {
			final OffenderCourseAttendance bean = new OffenderCourseAttendance();
			logger.error(EXCEPTION,e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}
	
	@PreAuthorize(HASELITEROLE)
	@RequestMapping(value = "/ocdpatte/getproviderType", method = RequestMethod.GET)
	public String getproviderType(
			@RequestParam(value = "caseloadId") final String caseloadId) {
		String providerType =null;
		try {
			providerType = ocdpatteService.getproviderType(caseloadId);
		} catch (final Exception e) {
			final AgencyLocations obj = new AgencyLocations();
			obj.setErrorMessage(e.getMessage());
		}
		return providerType;
	}
	
	
	@PreAuthorize(HASELITEROLE)
	@RequestMapping(value="/ocdpatte/getActOutcomeFlag", method=RequestMethod.POST)
	public OffenderCourseAttendance getActOutcomeFlag(@RequestBody final OffenderCourseAttendance searchBean) {
		OffenderCourseAttendance searchResult = new OffenderCourseAttendance();
		try {
			searchResult = ocdpatteService.getActOutcomeFlag(searchBean);
		} catch (final Exception e) {
			final OffenderCourseAttendance bean = new OffenderCourseAttendance();
			logger.error(EXCEPTION,e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}
	
	@PreAuthorize(HASELITEROLE)
	@RequestMapping(value="/ocdpatte/getProgLocation", method=RequestMethod.POST)
	public VAddresses getProgLocation(@RequestBody final CourseSchedules courseSchedules) {
		VAddresses searchResult = new VAddresses();
		try {
			searchResult = ocdpatteService.getProgLocation(courseSchedules);
		} catch (final Exception e) {
			final VAddresses bean = new VAddresses();
			logger.error(EXCEPTION,e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdpatte/rgAgyLocsRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAgyLocsRecordGroup(@RequestParam(value = "caseLoadId") final String caseLoadId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = ocdpatteService.rgAgyLocsRecordGroup(caseLoadId);
		} catch (Exception e) {
			AgencyLocations obj = new AgencyLocations();
			logger.error("Exception : ocdpatte:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdpatte/rgTeamAgyLocsRecordGroup", method = RequestMethod.GET)
	public List<TeamMembers> rgTeamAgyLocsRecordGroup(@RequestParam(value = "caseLoadId") final String caseLoadId) {
		List<TeamMembers> recordList = new ArrayList<TeamMembers>();
		try {
			recordList = ocdpatteService.rgTeamAgyLocsRecordGroup(caseLoadId);
		} catch (Exception e) {
			TeamMembers obj = new TeamMembers();
			logger.error("Exception", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdpatte/rgCorpLocsRecordGroup", method = RequestMethod.GET)
	public List<TeamMembers> rgCorpLocsRecordGroup() {
		List<TeamMembers> recordList = new ArrayList<TeamMembers>();
		try {
			recordList = ocdpatteService.rgCorpLocsRecordGroup();
		} catch (Exception e) {
			TeamMembers obj = new TeamMembers();
			logger.error("Exception : ocdpatte:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdpatte/cancelFlagOutcomeList", method = RequestMethod.POST)
	public List<EventMeasureOutcomes> cancelFlagOutcomeList(@RequestBody EventMeasures eventMeasureObj) {
		List<EventMeasureOutcomes> recordList = new ArrayList<EventMeasureOutcomes>();
		try {
			recordList = ocdpatteService.cancelFlagOutcomeList(eventMeasureObj);
		} catch (Exception e) {
			logger.error("Exception : cancelFlagOutcomeList:", e);
		}
		return recordList;
	}
	

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocdpatte/vAcpSchedulesExecuteQuery", method=RequestMethod.POST)
	public List<VAcpSchedules> vAcpSchedulesExecuteQuery(@RequestBody VAcpSchedules searchBean) {
		List<VAcpSchedules> searchResult = new ArrayList<>();
		try {
			searchResult = ocuschprService.vAcpSchedulesExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :",e);
		}
		return searchResult;
	}
}