package net.syscon.s4.sa.admin;

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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.AgencyLocationsCommitBean;
import net.syscon.s4.im.beans.Areas;

/**
 * @version 1.0
 */
@EliteController
public class OumagyrgController {
	@Autowired
	private OumagyrgService oumagyrgService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumagyrgController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 * @return List<AgencyLocations>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumagyrg/agyLocExecuteQuery", method = RequestMethod.POST)
	public List<AgencyLocations> agyLocExecuteQuery(@RequestBody final AgencyLocations searchBean) {
		List<AgencyLocations> searchResult = new ArrayList<>();
		try {
			searchResult = oumagyrgService.agyLocExecuteQuery(searchBean);
		} catch (Exception e) {
			AgencyLocations bean = new AgencyLocations();
			logger.error("AgencyLocations: ", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @return Integer
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oumagyrg/agyLocCommit", method = RequestMethod.POST)
	public @ResponseBody Integer agyLocCommit(@RequestBody final AgencyLocationsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oumagyrgService.agyLocCommit(commitBean);
		} catch (Exception e) {
			logger.error("AgencyLocationsCommitBean: ", e);
		}
		return liReturn;
	}

	/**
	 * getting agencyLocationTypeRg LOV values
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumagyrg/agencyLocationTypeRgRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> agencyLocationTypeRgRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oumagyrgService.agencyLocationTypeRgRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("agencyLocationTypeRgRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting geographicRegionRg LOV values
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumagyrg/geographicRegionRgRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> geographicRegionRgRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oumagyrgService.geographicRegionRgRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("geographicRegionRgRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting subAreaRg LOV values
	 * 
	 * @return List<Areas>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumagyrg/subAreaRgRecordGroup", method = RequestMethod.GET)
	public List<Areas> subAreaRgRecordGroup(@RequestParam(value="parentSubAreaType") final String subAreaType) {
		List<Areas> recordList = new ArrayList<>();
		try {
			recordList = oumagyrgService.subAreaRgRecordGroup(subAreaType);
		} catch (Exception e) {
			Areas obj = new Areas();
			logger.error("subAreaRgRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	/**
	 * getting subAreaRg LOV values
	 * 
	 * @return List<Areas>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumagyrg/subAreaRgRecordGroupTot", method = RequestMethod.GET)
	public List<Areas> subAreaRgRecordGroup() {
		List recordList = new ArrayList<>();
		try {
			recordList = oumagyrgService.subAreaRgRecordGroupTot();
		} catch (Exception e) {
			Areas obj = new Areas();
			logger.error("subAreaRgRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	/**
	 * getting subAreaRg LOV values
	 * 
	 * @return List<Areas>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumagyrg/areaRgRecordGroupTot", method = RequestMethod.GET)
	public List<Areas> areaRgRecordGroupTot() {
		List recordList = new ArrayList<>();
		try {
			recordList = oumagyrgService.areaRgRecordGroupTot();
		} catch (Exception e) {
			Areas obj = new Areas();
			logger.error("areaRgRecordGroupTot: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	

	/**
	 * getting areaRg LOV values return List<Areas>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumagyrg/areaRgRecordGroup", method = RequestMethod.GET)
	public List<Areas> areaRgRecordGroup(@RequestParam(value="parentAreaCode") final String agyLocType) {
		List<Areas> recordList = new ArrayList<>();
		try {
			recordList = oumagyrgService.areaRgRecordGroup(agyLocType);
		} catch (Exception e) {
			Areas obj = new Areas();
			logger.error("areaRgRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	

	/**
	 * getting justiceAreaRg LOV values
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumagyrg/justiceAreaRgRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> justiceAreaRgRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oumagyrgService.justiceAreaRgRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("justiceAreaRgRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting nomsRegionRg LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumagyrg/nomsRegionRgRecordGroup", method = RequestMethod.GET)
	public List<Areas> nomsRegionRgRecordGroup() {
		List<Areas> recordList = new ArrayList<>();
		try {
			recordList = oumagyrgService.nomsRegionRgRecordGroup();
		} catch (Exception e) {
			Areas obj = new Areas();
			logger.error("nomsRegionRgRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

}