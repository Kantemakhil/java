package net.syscon.s4.cm.programsservices;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRules;
import net.syscon.s4.iwp.beans.OcmschprCommitBean;
import net.syscon.s4.iwp.beans.VAcpSchedules;
import net.syscon.s4.pkgs.common.CommonService;


@EliteController
public class OcmschprController {
	@Autowired
	private OcmschprService ocmschprService;
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmschprController.class.getName());
	
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmschpr/crsActExecuteQuery", method = RequestMethod.POST)
	public List<CourseActivities> crsActExecuteQuery(@RequestBody CourseActivities searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<CourseActivities> searchResult = new ArrayList<>();
		try {
			searchResult = ocmschprService.crsActExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}
	
	

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmschpr/rgRemainingRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgRemainingRecordGroup(@RequestParam String link) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = ocmschprService.rgRemainingRecordGroup(link);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}
	  
	 /**
		 * Fetching the record from database table
		 * 
		 * @Param searchRecord
		 */

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmschpr/vAcpSchedulesExecuteQuery", method = RequestMethod.POST)
	public List<VAcpSchedules> vAcpSchedulesExecuteQuery(@RequestBody VAcpSchedules searchBean) {
		List<VAcpSchedules> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = ocmschprService.vAcpSchedulesExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmschpr/vAcpSchedulesValidate", method = RequestMethod.POST)
	public CourseActivities vAcpSchedulesValidate(@RequestBody VAcpSchedules searchBean) {
		CourseActivities searchResult = new CourseActivities();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = ocmschprService.vAcpSchedulesValidate(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}
	  
	
	
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmschpr/crsScheduleRulExecuteQuery", method = RequestMethod.POST)
	public List<CourseScheduleRules> crsScheduleRulExecuteQuery(@RequestBody CourseScheduleRules searchBean) {
		List<CourseScheduleRules> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = ocmschprService.crsScheduleRulExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}
	
	

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmschpr/vAcpSchedulesInsertChecking", method = RequestMethod.GET)
	public VAcpSchedules vAcpSchedulesInsertChecking(@RequestParam Long crsActyId) {
		VAcpSchedules searchResult = new VAcpSchedules();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = ocmschprService.vAcpSchedulesInsertChecking(crsActyId);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}
	
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmschpr/getWeekday", method = RequestMethod.POST)
	public String getWeekday(@RequestBody VAcpSchedules searchBean) {
		String weekDay=null;
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			weekDay = ocmschprService.getWeekday(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return weekDay;
	}
	  
	 
	

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmschpr/ocsmchprCommit", method = RequestMethod.POST)
	public CourseActivities ocsmchprCommit(@RequestBody OcmschprCommitBean searchBean) {
		CourseActivities searchResult = null;
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			searchResult = ocmschprService.ocsmchprCommit(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}
	
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmschpr/vAcpSchedulesDelete", method = RequestMethod.POST)
	public Integer vAcpSchedulesDelete(@RequestBody VAcpSchedules searchBean) {
		Integer retVal=null;
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			retVal = ocmschprService.vAcpSchedulesDelete(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return retVal;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmschpr/chkAllocationExists", method = RequestMethod.POST)
	public Boolean chkAllocationExists(@RequestBody CourseActivities searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		Boolean retVal=null;
		try {
			retVal = ocmschprService.chkAllocationExists(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return retVal;
	}
	
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmschpr/buildSchedule", method = RequestMethod.POST)
	public CourseActivities buildSchedule(@RequestBody CourseActivities searchBean) {
		CourseActivities retVal=new CourseActivities();
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			retVal = ocmschprService.buildSchedule(searchBean);
		} catch (Exception e) {
			if(e.getMessage().equalsIgnoreCase("No Build rules exist")) {
				retVal.setCode("info");
				retVal.setDescription(e.getMessage());
			} else {
				retVal.setCode("warn");
				retVal.setDescription(e.getMessage());
			}
			logger.error("Exception :", e);
		}
		return retVal;
	}
	

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmschpr/reSchedule", method = RequestMethod.POST)
	public CourseActivities reSchedule(@RequestBody CourseActivities searchBean) {
		CourseActivities retVal=null;
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			retVal = ocmschprService.reSchedule(searchBean);
			if(retVal == null) {
				retVal=new CourseActivities();
			}
		} catch (Exception e) {
			logger.error("Exception :", e);
			retVal=new CourseActivities();
		}
		return retVal;
	}
	
	
	@RequestMapping(value = "/ocmschpr/defaultBuildParameters", method = RequestMethod.POST)
	public CourseActivities defaultBuildParameters(@RequestBody CourseActivities searchBean) {
		CourseActivities retVal=null;
		try {
			retVal = ocmschprService.defaultBuildParameters(searchBean);
			if(retVal == null) {
				retVal=new CourseActivities();
			}
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return retVal;
	}
	
	@RequestMapping(value = "/ocmschpr/updateCrsActyChecksum", method = RequestMethod.GET)
	public Integer updateCrsActyChecksum(@RequestParam Long programInstanceId) {
		Integer retVal=null;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			retVal = ocmschprService.updateCrsActyChecksum(programInstanceId, userName);
		} catch (Exception e) {
			logger.error("Exception :", e);
			retVal=0;
		}
		return retVal;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCMSCHPR");
	}
}