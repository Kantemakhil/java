package net.syscon.s4.inst.securitythreatgroups;

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
import net.syscon.s4.im.beans.OffenderStgAssociations;
import net.syscon.s4.im.beans.OffenderStgAssociationsCommitBean;

@EliteController
public class OidmbrasController {
	@Autowired
	private OidmbrasService oidmbrasService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidmbrasController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmbras/offenderStgAssociationsExecuteQuery", method = RequestMethod.POST)
	public List<OffenderStgAssociations> offenderStgAssociationsExecuteQuery(
			@RequestBody final OffenderStgAssociations searchBean) {
		List<OffenderStgAssociations> searchResult = new ArrayList<>();
		try {
			searchResult = oidmbrasService.offenderStgAssociationsExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("offenderStgAssociationsExecuteQuery :", e);
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
	@RequestMapping(value = "/oidmbras/offenderStgAssociationsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offenderStgAssociationsCommit(
			@RequestBody final OffenderStgAssociationsCommitBean commitBean) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		int liReturn = 0;
		try {
			liReturn = oidmbrasService.offenderStgAssociationsCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * getting rgReasonCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmbras/rgReasonCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgReasonCodeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidmbrasService.rgReasonCodeRecordGroup();
		} catch (Exception e) {
			logger.error("offenderStgAssociationsExecuteQuery :", e);
		}
		return recordList;
	}

}