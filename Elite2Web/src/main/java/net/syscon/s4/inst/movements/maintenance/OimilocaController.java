package net.syscon.s4.inst.movements.maintenance;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
import net.syscon.s4.im.beans.AgencyInternalLocationsCommitBean;
import net.syscon.s4.im.beans.AgencyLocations;

@EliteController
public class OimilocaController {
	@Autowired
	private OimilocaService oimilocaService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimilocaController.class.getName());

	/**
	 * getting rgAgyLoc LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimiloca/rgAgyLocRecordGroup", method = RequestMethod.GET)
	 public List<AgencyLocations> rgAgyLocRecordGroup() {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
	
		try {
		recordList = oimilocaService.rgAgyLocRecordGroup(userName);
		} catch (Exception e) {
			logger.error("Exception : Oimiloca:", e);
		}
		return recordList;
	}

	/**
	 * getting rgLevelType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimiloca/rgLevelTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgLevelTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimilocaService.rgLevelOneTypeRecordGroup();
		} catch (Exception e) {
			logger.error("Exception : Oimiloca:", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimiloca/intLocL1ExecuteQuery", method = RequestMethod.POST)
	public List<AgencyInternalLocations> intLocLOneExecuteQuery(@RequestBody final AgencyInternalLocations searchBean) {
		List<AgencyInternalLocations> searchResult = new ArrayList<>();
		try {
			searchResult = oimilocaService.intLocLOneExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimiloca/intLocL1Commit", method = RequestMethod.POST)
	public @ResponseBody AgencyInternalLocations intLocLOneCommit(
			@RequestBody final AgencyInternalLocationsCommitBean commitBean) {
		AgencyInternalLocations liReturn = new AgencyInternalLocations();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oimilocaService.intLocLOneCommit(commitBean);
		}
		catch(DuplicateKeyException e) {
			logger.error("Exception : Oimiloca", e);
			liReturn.setSealFlag("3");
		}
		catch (Exception e) {
			logger.error("Exception : Oimiloca", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimiloca/intLocL2ExecuteQuery", method = RequestMethod.POST)
	public List<AgencyInternalLocations> intLocLTwoExecuteQuery(@RequestBody final AgencyInternalLocations searchBean) {
		List<AgencyInternalLocations> searchResult = new ArrayList<>();
		try {
			searchResult = oimilocaService.intLocLTwoExecuteQuery(searchBean);
		} catch (Exception e) {
			final AgencyInternalLocations bean = new AgencyInternalLocations();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimiloca/getResDescValues", method = RequestMethod.POST)
	public AgencyInternalLocations getResDescValues(@RequestBody final AgencyInternalLocations searchBean) {
		AgencyInternalLocations searchResult = new AgencyInternalLocations();
		try {
			searchResult = oimilocaService.getResDescValues(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimiloca/locationTypeLOVRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> locationTypeLOVRecordGroup(@RequestParam final String unitType) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimilocaService.locationTypeLOVRecordGroup(unitType);
		} catch (Exception e) {
			logger.error("Exception : Oimiloca:", e);
		}
		return recordList;
	}
	
}