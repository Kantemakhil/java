package net.syscon.s4.inst.visitsmanagement.maintenance;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.visitsmanagement.beans.IepLevelBean;
import net.syscon.s4.inst.visitsmanagement.beans.VisitCycleLimits;
import net.syscon.s4.inst.visitsmanagement.beans.VisitCycleLimitsCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.VisitTypeLimits;
import net.syscon.s4.inst.visitsmanagement.beans.VisitTypeLimitsCommitBean;

/**
 * OimvlimtController
 * 
 */
@EliteController
public class OimvlimtController {
	@Autowired
	private OimvlimtService oimvlimtService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimvlimtController.class.getName());

	/**
	 * method:rgSecLvlRecordGroup getting rgSecLvlRecordGroup LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimvlimt/rgSecLvlRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSecLvlRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimvlimtService.rgSecLvlRecordGroup();
		} catch (Exception e) {
			logger.error("rgSecLvlRecordGroup :", e);
		}
		return recordList;
	}

	/**
	 * method:rgCycTypRecordGroup getting rgCycTyp LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimvlimt/rgCycTypRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCycTypRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimvlimtService.rgCycTypRecordGroup();
		} catch (Exception e) {
			logger.error("rgCycTypRecordGroup :", e);
		}
		return recordList;
	}

	/**
	 * method:rgVisTypRecordGroup getting rgVisTyp LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimvlimt/rgVisTypRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgVisTypRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimvlimtService.rgVisTypRecordGroup();
		} catch (Exception e) {
			logger.error("rgVisTypRecordGroup :", e);
		}
		return recordList;
	}

	/**
	 * getting rgStrDay LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimvlimt/rgStrDayRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgStrDayRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimvlimtService.rgStrDayRecordGroup();
		} catch (Exception e) {
			logger.error("rgStrDayRecordGroup :", e);
		}
		return recordList;
	}

	/**
	 * method:rgAgyIntLocRecordGroup getting rgAgyIntLoc LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimvlimt/rgAgyIntLocRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAgyIntLocRecordGroup() {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		// searchBean.setCreateUserId(userName);
		try {
			recordList = oimvlimtService.rgAgyIntLocRecordGroup(userName);
		} catch (Exception e) {
			logger.error("rgAgyIntLocRecordGroup :", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimvlimt/visCycExecuteQuery", method = RequestMethod.POST)
	public List<VisitCycleLimits> visCycExecuteQuery(@RequestBody final VisitCycleLimits searchBean) {
		List<VisitCycleLimits> searchResult = new ArrayList<>();
		try {
			searchResult = oimvlimtService.visCycExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("visCycExecuteQuery :", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimvlimt/visCycCommit", method = RequestMethod.POST)
	public @ResponseBody Integer visCycCommit(@RequestBody final VisitCycleLimitsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oimvlimtService.visCycCommit(commitBean);
		} catch (Exception e) {

			logger.error("visCycCommit :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimvlimt/visTypExecuteQuery", method = RequestMethod.POST)
	public List<VisitTypeLimits> visTypExecuteQuery(@RequestBody final VisitTypeLimits searchBean) {
		List<VisitTypeLimits> searchResult = new ArrayList<>();
		try {
			searchResult = oimvlimtService.visTypExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("visTypExecuteQuery :", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimvlimt/visTypCommit", method = RequestMethod.POST)
	public @ResponseBody Integer visTypCommit(@RequestBody final VisitTypeLimitsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oimvlimtService.visTypCommit(commitBean);
		} catch (Exception e) {

			logger.error("visTypCommit :", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimvlimt/rgIepLevelRecordGroup", method = RequestMethod.GET)
	public List<IepLevelBean> rgAccreditedProgramsRecordGroup() {
		List<IepLevelBean> recordList = new ArrayList<IepLevelBean>();
		try {
			recordList = oimvlimtService.rgIepLevelRecordGroup();
		} catch (Exception e) {
			logger.error("In method rgAccreditedProgramsRecordGroup", e);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimvlimt/iepLevelExecuteQuery", method = RequestMethod.POST)
	public List<VisitCycleLimits> iepLevelExecuteQuery(@RequestBody final VisitCycleLimits searchBean) {
		List<VisitCycleLimits> searchResult = new ArrayList<>();
		try {
			searchResult = oimvlimtService.iepLevelExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("visCycExecuteQuery :", e);
		}
		return searchResult;
	}

	@GetMapping("/oimvlimt/getiepDetails")
	List<IepLevelBean> getIEPDetails(Long offenderBookId) {
		List<IepLevelBean> searchResult = new ArrayList<>();
		try {
			searchResult = oimvlimtService.getIEPDetails(offenderBookId);
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() +"In getIEPDetails method : ", e);
		}
		return searchResult;
	}

	@GetMapping("/oimvlimt/saveIepSecData")
	public Integer saveIepSecData(@RequestParam String facility, @RequestParam String iepSecLevel,
			@RequestParam String operaion) {
		Integer searchResult = null;
		try {
			searchResult = oimvlimtService.saveIepSecData(facility, iepSecLevel,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString(), operaion);
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() +"In saveIepSecData method : ", e);
		}
		return searchResult;
	}
    @GetMapping("/oimvlimit/getIepVisitLimis")
	public VisitCycleLimits getIepVisitLimis(@RequestParam String agyLocId) {
    	VisitCycleLimits searchResult = new VisitCycleLimits();
		try {
			searchResult = oimvlimtService.getIepVisitLimis(agyLocId);
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() +"In getIepVisitLimis method : ", e);
		}
		return searchResult;
	}
    
    @GetMapping("/oimvlimit/getIepSecLov")
    public List<ReferenceCodes> getIepSecLov(){
    	List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimvlimtService.getIepSecLov();
		} catch (Exception e) {
			logger.error("getIepSecLov :", e);
		}
		return recordList;
    }

}