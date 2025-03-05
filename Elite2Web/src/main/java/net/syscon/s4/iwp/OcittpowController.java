package net.syscon.s4.iwp;

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

import net.syscon.s4.cm.primaryofficeragentassignment.beans.VExtOwnershipTransfer;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.VExtOwnershipTransferCommitBean;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.beans.AgencyLocations;

@EliteController
public class OcittpowController {
	@Autowired
	private OcittpowService ocittpowService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcittpowController.class.getName());

	/*
	 * Loading of lov's of the Ext_Ot_Dsp _DescriptionRecordGroup
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocittpow/dspDescriptionRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> dspDescriptionRecordGroup(
			@RequestParam(value = "currentCaseLoad") final String currentCaseLoad) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = ocittpowService.dspDescriptionRecordGroup(currentCaseLoad);
		} catch (Exception e) {
			logger.error("dspDescriptionRecordGroup :", e);
		}
		return recordList;
	}

	/**
	 * 
	 * getting agyLocIdFrom LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocittpow/agyLocIdFromRecordGroup", method = RequestMethod.GET)
	public List<VExtOwnershipTransfer> agyLocIdFromRecordGroup(
			@RequestParam(value = "agyLocIdFrom") final String agyLocIdFrom) {
		List<VExtOwnershipTransfer> recordList = new ArrayList<VExtOwnershipTransfer>();
		try {
			recordList = ocittpowService.agyLocIdFromRecordGroup(agyLocIdFrom);
		} catch (Exception e) {
			logger.error("agyLocIdFromRecordGroup :", e);
		}
		return recordList;
	}

	/**
	 * Transferred Offenders Execute Query
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocittpow/transferredOffendersExecuteQuery", method = RequestMethod.GET)
	public List<VExtOwnershipTransfer> transferredOffendersExecuteQuery(
			@RequestParam(value = "code") final String code) {
		List<VExtOwnershipTransfer> searchResult = new ArrayList<>();
		try {
			searchResult = ocittpowService.transferredOffendersExecuteQuery(code);
		} catch (Exception e) {
			logger.error("transferredOffendersExecuteQuery :", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring Save Operation i.e. insert,delete, update in the database table
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocittpow/transferredOffendersCommit", method = RequestMethod.POST)
	public @ResponseBody Integer transferredOffendersCommit(@RequestBody  final VExtOwnershipTransferCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocittpowService.transferredOffendersCommit(commitBean.getUpdateList(),userName);
		} catch (Exception e) {
			logger.error("transferredOffendersCommit :", e);
		}
		return liReturn;
	}

	/**
	 * agyLocIdToExecuteQuery Execute Query
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocittpow/agyLocIdToExecuteQuery", method = RequestMethod.GET)
	public Integer agyLocIdToExecuteQuery(@RequestParam(value = "agyLocIdTo") final String agyLocIdTo) {
		int count = 0;
		try {
			count = ocittpowService.agyLocIdToExecuteQuery(agyLocIdTo);
		} catch (Exception e) {
			logger.error("agyLocIdToExecuteQuery :", e);
		}
		return count;
	}
}