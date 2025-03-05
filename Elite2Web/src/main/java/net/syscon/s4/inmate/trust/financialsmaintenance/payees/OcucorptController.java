package net.syscon.s4.inmate.trust.financialsmaintenance.payees;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.CorporateTypesCommitBean;
import net.syscon.s4.im.beans.CorporateTypes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.pkgs.common.CommonService;

/**
 * OcucorptController
 */
@EliteController
public class OcucorptController {
	@Autowired
	private OcucorptService ocucorptService;
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcucorptController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocucorpt/corporateTypesExecuteQuery", method = RequestMethod.POST)
	public List<CorporateTypes> corporateTypesExecuteQuery(@RequestBody final CorporateTypes searchBean) {
		List<CorporateTypes> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = ocucorptService.corporateTypesExecuteQuery(searchBean);
		} catch (Exception e) {

			logger.error("corporateTypesExecuteQuery :", e);

		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocucorpt/corporateTypesCommit", method = RequestMethod.POST)
	public @ResponseBody Integer corporateTypesCommit(@RequestBody final CorporateTypesCommitBean commitBean) {
		int liReturn = 0;
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocucorptService.corporateTypesCommit(commitBean);
		} catch (Exception e) {

			logger.error("corporateTypesCommit :", e);
		}
		return liReturn;
	}

	/**
	 * getting rgCorpType LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocucorpt/rgCorpTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCorpTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = ocucorptService.rgCorpTypeRecordGroup();
		} catch (Exception e) {

			logger.error("ReferenceCodes :", e);

		}
		return recordList;
	}

	/**
	 * getting rgCorpType LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocucorpt/prevCaseloadCorpExists", method = RequestMethod.POST)
	public Integer prevCaseloadCorpExists(@RequestBody final Corporates paramBean) {
		Integer record = 0;
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			record = ocucorptService.prevCaseloadCorpExists(paramBean);
		} catch (Exception e) {

			logger.error("prevCaseloadCorpExists :", e);

		}
		return record;
	}
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCUCORPT");
	}
}