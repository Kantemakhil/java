package net.syscon.s4.inst.institutionalactivities.maintenance;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRules;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRulesCommitBean;


@EliteController
public class OcmschrcController {
@Autowired
private OcmschrcService ocmschrcService;

	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OcmschrcController.class.getName());
	
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocmschrc/crsScheduleRulExecuteQuery", method=RequestMethod.POST)
	public List<CourseScheduleRules> crsScheduleRulExecuteQuery(@RequestBody final CourseScheduleRules searchBean) {
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
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/ocmschrc/crsScheduleRulCommit",method=RequestMethod.POST)
	public @ResponseBody CourseScheduleRules crsScheduleRulCommit(@RequestBody final CourseScheduleRulesCommitBean commitBean) {
		CourseScheduleRules liReturn = new CourseScheduleRules();
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
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/ocmschrc/buildSchedule",method=RequestMethod.POST)
	public @ResponseBody CourseScheduleRules buildRecurringSchedule(@RequestBody final CourseScheduleRules crcScheduleRules){
		CourseScheduleRules result = new CourseScheduleRules();
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
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/ocmschrc/getProfileValues",method=RequestMethod.POST)
	public @ResponseBody CourseScheduleRules getProfileValues(){
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
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/ocmschrc/getPrgSrvDetails",method=RequestMethod.POST)
	public @ResponseBody CourseActivities getPrgSrvDetails(@RequestBody final Long programId){
		
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
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/ocmschrc/getHolidayFlag",method=RequestMethod.POST)
	public @ResponseBody CourseActivities getHolidayFlag(@RequestBody final CourseActivities crsactModel) {
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
	
	
}