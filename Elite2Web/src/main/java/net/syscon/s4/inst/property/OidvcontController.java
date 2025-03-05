package net.syscon.s4.inst.property;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderPptyConTxnsCommitBean;
import net.syscon.s4.inst.property.bean.OffenderPptyConTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxnsCommitBean;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;

@EliteController
public class OidvcontController {

	@Autowired
	private OidvcontService oidvcontService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidvcontController.class.getName());

	/**
	 * getting cgfkConTxActionCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvcont/cgfkchkOffConOffConRef", method = RequestMethod.GET)
	public ReferenceCodes cgfkchkOffConOffConRef(@RequestBody final ReferenceCodes searchBean) {
		ReferenceCodes recordList = new ReferenceCodes();
		try {
			recordList = oidvcontService.cgfkchkOffConOffConRef(searchBean);
		} catch (Exception e) {
			logger.error("cgfkchkOffConOffConRef", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkConTxActionCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvcont/cgfkConTxActionCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkConTxActionCodeRecordGroup(
			@RequestParam("propertyContainerId") final String propertyContainerId) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
 			recordList = oidvcontService.cgfkConTxActionCodeRecordGroup(propertyContainerId);
		} catch (Exception e) {
			logger.error("cgfkConTxActionCodeRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvcont/offConExecuteQuery", method = RequestMethod.POST)
	public List<OffenderPptyContainers> offConExecuteQuery(@RequestBody final OffenderPptyContainers searchBean) {
		List<OffenderPptyContainers> searchResult = new ArrayList<>();
		try {
			searchResult = oidvcontService.offConExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("offConExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvcont/conTxExecuteQuery", method = RequestMethod.POST)
	public List<OffenderPptyConTxns> conTxExecuteQuery(@RequestBody final OffenderPptyConTxns searchBean) {
		List<OffenderPptyConTxns> searchResult = new ArrayList<>();
		try {
			searchResult = oidvcontService.conTxExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("conTxExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidvcont/conTxCommit", method = RequestMethod.POST)
	public @ResponseBody Integer conTxCommit(@RequestBody final OffenderPptyConTxnsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}

			liReturn = oidvcontService.conTxCommit(commitBean);
		} catch (Exception e) {
			logger.error("conTxCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvcont/itmTxExecuteQuery", method = RequestMethod.POST)
	public List<OffenderPptyItemTxns> itmTxExecuteQuery(@RequestBody final OffenderPptyItemTxns searchBean) {
		List<OffenderPptyItemTxns> searchResult = new ArrayList<>();
		try {
			searchResult = oidvcontService.itmTxExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("itmTxExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidvcont/itmTxCommit", method = RequestMethod.POST)
	public @ResponseBody Integer itmTxCommit(@RequestBody final OffenderPptyItemTxnsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidvcontService.itmTxCommit(commitBean);
		} catch (Exception e) {
			logger.error("itmTxCommit", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvcont/cgfkchkOffConOffConPpty", method = RequestMethod.GET)
	public AgencyInternalLocations cgfkchkOffConOffConPpty(@RequestBody final AgencyInternalLocations searchBean) {
		AgencyInternalLocations recordList = new AgencyInternalLocations();
		try {
			recordList = oidvcontService.cgfkchkOffConOffConPpty(searchBean);
		} catch (Exception e) {
			logger.error("cgfkchkOffConOffConPpty", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvcont/offPItemExecuteQuery", method = RequestMethod.POST)
	public List<OffenderPptyItems> offPItemExecuteQuery(@RequestBody final OffenderPptyItemTxns searchBean) {
		List<OffenderPptyItems> searchResult = new ArrayList<>();
		try {
			searchResult = oidvcontService.offPItemExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("offPItemExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidvcont/updateOffenderPptyContainers", method = RequestMethod.POST)
	public @ResponseBody Integer updateOffenderPptyContainers(@RequestBody final OffenderPptyContainers commitBean) {
		int liReturn = 0;
		
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}

			liReturn = oidvcontService.updateOffenderPptyContainers(commitBean);
		} catch (Exception e) {
			logger.error("updateOffenderPptyContainers", e);
		}
		return liReturn;
	}

}