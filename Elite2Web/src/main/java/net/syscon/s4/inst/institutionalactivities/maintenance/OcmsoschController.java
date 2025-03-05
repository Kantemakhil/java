package net.syscon.s4.inst.institutionalactivities.maintenance;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRules;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRulesCommitBean;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedulesCommitBean;
import net.syscon.s4.pkgs.common.CommonService;

/**
 * @author Arkin Software Technologies
 * @version 1.0
 */
@EliteController
public class OcmsoschController {
	@Autowired
	private OcmsoschService ocmsoschService;
	@Autowired
	private OcmschrcService ocmschrcService;
	@Autowired
	private CommonService commonService;
	private static final String EXCEPTION = "Exception :";
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmsoschController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmsosch/courseSchedExecuteQuery", method = RequestMethod.POST)
	public List<CourseSchedules> courseSchedExecuteQuery(@RequestBody final CourseSchedules searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<CourseSchedules> searchResult = new ArrayList<>();
		try {
			searchResult = ocmsoschService.courseSchedExecuteQuery(searchBean);
		} catch (Exception e) {
			final CourseSchedules bean = new CourseSchedules();
			logger.error(EXCEPTION, e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmsosch/courseSchedCommit", method = RequestMethod.POST)
	public @ResponseBody Integer courseSchedCommit(@RequestBody final CourseSchedulesCommitBean commitBean) {
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocmsoschService.courseSchedCommit(commitBean);
		} catch (Exception e) {

			logger.error(EXCEPTION, e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmsosch/getDate", method = RequestMethod.POST)
	public @ResponseBody String getDate(@RequestBody final CourseSchedules courseSchedules) {
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		String date = "";
		try {
			date = ocmsoschService.getDate(courseSchedules);
		} catch (Exception e) {

			logger.error(EXCEPTION, e);
		}
		return date;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmsosch/clearSchedules", method = RequestMethod.POST)
	public @ResponseBody String clearSchedules(@RequestBody final CourseSchedules courseSchedules) {
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		String result = "";
		if (courseSchedules != null) {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			courseSchedules.setCreateUserId(userName);
		}
		try {
			result = ocmsoschService.clearSchedules(courseSchedules);
		} catch (Exception e) {

			logger.error(EXCEPTION, e);
		}
		return result;
	}
	
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value="/ocmsosch/crsScheduleRulExecuteQuery", method=RequestMethod.POST)
	public List<CourseScheduleRules> crsScheduleRulExecuteQuery(@RequestBody final CourseScheduleRules searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		 List<CourseScheduleRules> searchResult = new ArrayList<>();
		try {
			searchResult = ocmschrcService.crsScheduleRulExecuteQuery(searchBean);
		} catch (Exception e) {
			final CourseScheduleRules bean = new CourseScheduleRules();
			logger.error("Exception :",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	/**
	 *Perfomring basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value="/ocmsosch/crsScheduleRulCommit",method=RequestMethod.POST)
	public @ResponseBody CourseScheduleRules crsScheduleRulCommit(@RequestBody final CourseScheduleRulesCommitBean commitBean) {
		CourseScheduleRules liReturn = new CourseScheduleRules();
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocmschrcService.crsScheduleRulCommit(commitBean);
		}catch(Exception e){
			logger.error("Exception :",e);
			liReturn.setErrorMessage(e.getMessage());
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value="/ocmsosch/buildSchedule",method=RequestMethod.POST)
	public @ResponseBody CourseScheduleRules buildRecurringSchedule(@RequestBody final CourseScheduleRules crcScheduleRules){
		CourseScheduleRules result = new CourseScheduleRules();
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			if (crcScheduleRules != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				crcScheduleRules.setCreateUserId(userName);
			}
			result = ocmschrcService.buildRecurringSchedule(crcScheduleRules);
		}catch(Exception e){
			logger.error("Exception :",e);
			result.setErrorMessage(e.getMessage());
		}
		return result;
	
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value="/ocmsosch/getProfileValues",method=RequestMethod.POST)
	public @ResponseBody CourseScheduleRules getProfileValues(){
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		final CourseScheduleRules finalData = new CourseScheduleRules();
		long result = 0;
		try {
			result = ocmschrcService.getProfileValues();
			finalData.setNoOfDays(result);
		}catch(Exception e){
			logger.error("Exception :",e);
			finalData.setErrorMessage(e.getMessage());
		}
		return finalData;
		
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value="/ocmsosch/getPrgSrvDetails",method=RequestMethod.POST)
	public @ResponseBody CourseActivities getPrgSrvDetails(@RequestBody final Long programId){
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		final CourseActivities finalData = new CourseActivities();
		String result = "";
		try {
			result = ocmschrcService.getPrgSrvDetails(programId);
			finalData.setProgramCode(result);
		}catch(Exception e){
			logger.error("Exception :",e);
			finalData.setErrorMessage(e.getMessage());
		}
		return finalData;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value="/ocmsosch/getHolidayFlag",method=RequestMethod.POST)
	public @ResponseBody CourseActivities getHolidayFlag(@RequestBody final CourseActivities crsactModel) {
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		final CourseActivities finalData = new CourseActivities();
		String result = "";
		try {
			result = ocmschrcService.getHolidayFlag(crsactModel);
			finalData.setHolidayFlag(result);
		}catch(Exception e){
			logger.error("Exception :",e);
			finalData.setErrorMessage(e.getMessage());
		}
		return finalData;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCMSOSCH");
	}


}