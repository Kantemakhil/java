package net.syscon.s4.inst.movementexternal;

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
import net.syscon.s4.inst.movementexternal.beans.OffenderEscapes;
import net.syscon.s4.inst.movementexternal.beans.OffenderEscapesCommitBean;

/**
 * @author class OmuerrcoController
 */
@EliteController
public class OmuerrcoController {
	@Autowired
	private OmuerrcoService omuerrcoService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OmuerrcoController.class.getName());

	/**
	 * getting cgfkOffEscSecurityBreachC LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/omuerrco/cgfkOffEscSecurityBreachCRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffEscSecurityBreachCRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = omuerrcoService.cgfkOffEscSecurityBreachCRecordGroup();
		} catch (Exception e) {
			logger.error("In cgfkOffEscSecurityBreachCRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting cgfkOffEscArrestAgyCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/omuerrco/cgfkOffEscArrestAgyCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffEscArrestAgyCodeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = omuerrcoService.cgfkOffEscArrestAgyCodeRecordGroup();
		} catch (Exception e) {
			logger.error("In cgfkOffEscArrestAgyCodeRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/omuerrco/offEscExecuteQuery", method = RequestMethod.POST)
	public List<OffenderEscapes> offEscExecuteQuery(@RequestBody final OffenderEscapes searchBean) {
		List<OffenderEscapes> searchResult = new ArrayList<>();
		final OffenderEscapes bean = new OffenderEscapes();
		try {
			searchResult = omuerrcoService.offEscExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In offEscExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
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
	@RequestMapping(value = "/omuerrco/offEscCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offEscCommit(@RequestBody final OffenderEscapesCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = omuerrcoService.offEscCommit(commitBean);
		} catch (Exception e) {
			logger.error("In offEscCommit method : ", e);
		}
		return liReturn;
	}

}