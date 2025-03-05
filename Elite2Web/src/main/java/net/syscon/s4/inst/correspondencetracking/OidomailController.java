package net.syscon.s4.inst.correspondencetracking;

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

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.correspondencetracking.beans.OffMailRestrictions;
import net.syscon.s4.inst.correspondencetracking.beans.OffenderMailLog;
import net.syscon.s4.inst.correspondencetracking.beans.OidomailCommonCommitBean;
import net.syscon.s4.inst.correspondencetracking.impl.OidomailServiceImpl;

@EliteController
public class OidomailController {

	private static Logger logger = LogManager.getLogger(OidomailServiceImpl.class.getName());

	@Autowired
	private OidomailService oidomailService;

	@RequestMapping(value = "/oidomail/getContactTypeLov",method = RequestMethod.GET)
	public List<ReferenceCodes> getContactTypeLov() {
		List<ReferenceCodes> contactList = new ArrayList<ReferenceCodes>();
		try {
			contactList = oidomailService.getContactTypeLov();
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getContactTypeLov", e);
		}

		return contactList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidomail/getOffenderMailLogsData", method = RequestMethod.GET)
	public List<OffenderMailLog> mailLOgExecuteQuery(final Long offenderBookId) {
		List<OffenderMailLog> searchResult = new ArrayList<>();
		try {
			searchResult = oidomailService.mailLogExecuteQuery(offenderBookId );
		} catch (Exception e) {
			OffenderMailLog bean = new OffenderMailLog();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidomail/getOffenderMailResrtrictions", method = RequestMethod.GET)
	public List<OffMailRestrictions> mailRestrictionExecuteQuery(final Long offenderBookId ) {
		List<OffMailRestrictions> searchResult = new ArrayList<>();
		try {
			searchResult = oidomailService.mailRestrictionExecuteQuery(offenderBookId);
		} catch (Exception e) {
			OffMailRestrictions bean = new OffMailRestrictions();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}


	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidomail/mailAndRestrictionCommonSave", method = RequestMethod.POST)
	public Integer mailAndRestrictionCommonSave(@RequestBody final OidomailCommonCommitBean commitBean) {
		int liReturn = 0;
		try {
			final String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(user);
			liReturn = oidomailService.mailLogAndResrtrictionsCommonSave(commitBean);
			
		} catch (Exception e) {
			logger.error("Exception raised in mailAndRestrictionCommonSave", e);
		}
		return liReturn;
	}

}
