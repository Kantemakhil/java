package net.syscon.s4.inst.careinplacement.maintenance;

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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.careinplacement.beans.PlacementDurations;
import net.syscon.s4.inst.careinplacement.beans.PlacementDurationsCommitBean;

@EliteController
public class OimpldurController {
	@Autowired
	private OimpldurService oimpldurService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimpldurController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimpldur/placementDurExecuteQuery", method = RequestMethod.POST)
	public List<PlacementDurations> placementDurExecuteQuery(@RequestBody final PlacementDurations searchBean) {
		List<PlacementDurations> searchResult = new ArrayList<>();
		try {
			searchResult = oimpldurService.placementDurExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
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
	@RequestMapping(value = "/oimpldur/placementDurCommit", method = RequestMethod.POST)
	public @ResponseBody PlacementDurations placementDurCommit(
			@RequestBody final PlacementDurationsCommitBean commitBean) {
		PlacementDurations liReturn = new PlacementDurations();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oimpldurService.placementDurCommit(commitBean);
		} catch (Exception e) {
			final String errorMsg = "Error : " + e.getMessage();
			liReturn.setErrorMessage(errorMsg.toUpperCase());
		}
		return liReturn;
	}

	/**
	 * getting rgDurationType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimpldur/rgDurationTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgDurationTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimpldurService.rgDurationTypeRecordGroup();
		} catch (Exception e) {
			logger.error("Exception : Oimpldur:", e);
		}
		return recordList;
	}

	/**
	 * getting rgPlacementType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimpldur/rgPlacementTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPlacementTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimpldurService.rgPlacementTypeRecordGroup();
		} catch (Exception e) {
			logger.error("Exception : Oimpldur:", e);
		}
		return recordList;
	}

}