package net.syscon.s4.inst.property;

import java.util.ArrayList;
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
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyContainersCommitBean;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;
import net.syscon.s4.inst.property.bean.PropertyStorages;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * Class OidtpconController
 */
@EliteController
public class OidtpconController {
	
	@Autowired
	private OidtpconService oidtpconService;
	@Autowired
	private ProsmainService prosmainService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidtpconController.class.getName());

	/**
	 * getting cgfkOffConTrnToAgyLocId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtpcon/cgfkOffConTrnToAgyLocIdRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> cgfkOffConTrnToAgyLocIdRecordGroup(@RequestParam(value = "agyLocId") final String agyLocId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = oidtpconService.cgfkOffConTrnToAgyLocIdRecordGroup(agyLocId);
		} catch (Exception e) {
			logger.error("cgfkOffConTrnToAgyLocIdRecordGroup:",e);
		}
		return recordList;
	}

	/**
	 * getting rgSelectAll LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtpcon/rgSelectAllRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgSelectAllRecordGroup() {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = oidtpconService.rgSelectAllRecordGroup();
		} catch (Exception e) {
			logger.error("rgSelectAllRecordGroup:",e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtpcon/offConExecuteQuery", method = RequestMethod.POST)
	public List<OffenderPptyContainers> offConExecuteQuery(@RequestBody final OffenderPptyContainers searchBean) {
		List<OffenderPptyContainers> searchResult = new ArrayList<>();
		try {
			searchResult = oidtpconService.offConExecuteQuery(searchBean,"ALL");
		} catch (Exception e) {
			logger.error("errro in offConExecuteQuery:"+e.getMessage());
		}
		return searchResult;
	}
 
	
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtpcon/offPenPropConExecuteQuery", method = RequestMethod.POST)
	public List<OffenderPptyContainers> offPenPropConExecuteQuery(@RequestBody final OffenderPptyContainers searchBean) {
		List<OffenderPptyContainers> searchResult = new ArrayList<>();
		try {
			searchResult = oidtpconService.offConExecuteQuery(searchBean,"PENDING");
		} catch (Exception e) {
			logger.error("errro in offPenPropConExecuteQuery:"+e.getMessage());
		}
		return searchResult;
	}
	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidtpcon/offConCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offConCommit(@RequestBody final OffenderPptyContainersCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = oidtpconService.offConCommit(commitBean);
			if(liReturn != 0) {
				prosmainService.enableTriggers(commitBean, authorization, "54");
			}
		} catch (Exception e) {
			logger.error("offConCommit:",e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidtpcon/offPenPropConCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offPenPropConCommit(@RequestBody final OffenderPptyContainersCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = oidtpconService.offPenPropConCommit(commitBean);
			/*if(liReturn != 0) {
				prosmainService.enableTriggers(commitBean, authorization, "54");
			}*/
		} catch (Exception e) {
			logger.error("offConCommit:",e);
		}
		return liReturn;
	}
	

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtpcon/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = oidtpconService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("sysPflExecuteQuery:",e);
		}
		return searchResult;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtpcon/vPheadOnCheckDeleteMaster", method = RequestMethod.POST)
	public List<Object> vPheadOnCheckDeleteMaster(@RequestBody final OffenderPptyContainers paramBean) {
		List<Object> dataObj = null;
		try {
			dataObj = oidtpconService.vPheadOnCheckDeleteMaster(paramBean);
		} catch (Exception e) {
			logger.error("vPheadOnCheckDeleteMaster:",e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtpcon/offConPostQuery", method = RequestMethod.POST)
	public @ResponseBody AgencyInternalLocations offConPostQuery(@RequestBody final AgencyInternalLocations paramBean) {
		AgencyInternalLocations dataObj = null;
		try {
			dataObj = oidtpconService.offConPostQuery(paramBean);
		} catch (Exception e) {
			logger.error("offConPostQuery:",e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtpcon/offConWhenNewRecordInstance", method = RequestMethod.POST)
	public @ResponseBody String offConWhenNewRecordInstance(@RequestParam(value = "intLocID") final Integer intLocID) {
		String dataObj = null;
		try {
			dataObj = oidtpconService.offConWhenNewRecordInstance(intLocID);
		} catch (Exception e) {
			logger.error("offConWhenNewRecordInstance:",e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtpcon/cgfkchkOffConOffConRef", method = RequestMethod.POST)
	public @ResponseBody ReferenceCodes cgfkchkOffConOffConRef(@RequestBody final ReferenceCodes paramBean) {
		ReferenceCodes listOfRecords = null;
		try {
			listOfRecords = oidtpconService.cgfkchkOffConOffConRef(paramBean);
		} catch (Exception e) {
			logger.error("cgfkchkOffConOffConRef:",e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtpcon/cgfkchkOffConOffConAgy", method = RequestMethod.POST)
	public @ResponseBody AgencyLocations cgfkchkOffConOffConAgy(@RequestBody final AgencyLocations paramBean) {
		AgencyLocations listOfRecords = null;
		try {
			listOfRecords = oidtpconService.cgfkchkOffConOffConAgy(paramBean);
		} catch (Exception e) {
			logger.error("cgfkchkOffConOffConAgy",e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtpcon/cgfkchkOffConOffConPpty", method = RequestMethod.POST)
	public @ResponseBody PropertyStorages cgfkchkOffConOffConPpty(@RequestBody final PropertyStorages paramBean) {
		PropertyStorages dataObj = null;
		try {
			dataObj = oidtpconService.cgfkchkOffConOffConPpty(paramBean);
		} catch (Exception e) {
			logger.error("cgfkchkOffConOffConPpty:",e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtpcon/cgwhenNewFormInstance", method = RequestMethod.POST)
	public @ResponseBody Dual cgwhenNewFormInstance() {
		Dual listOfRecords = null;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			listOfRecords = oidtpconService.cgwhenNewFormInstance(userName);
		} catch (Exception e) {
			logger.error(e);
		}
		return listOfRecords;
	}
	
	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtpcon/getItemStatus", method = RequestMethod.POST)
	public List<OffenderPptyItems> getItemStatus(@RequestBody final OffenderPptyItems paramBean) {
		List<OffenderPptyItems> dataObj = null;
		try {
			dataObj = oidtpconService.getItemStatus(paramBean);
		} catch (Exception e) {
			logger.error("getItemStatus:",e);
		}
		return dataObj;
	}

}