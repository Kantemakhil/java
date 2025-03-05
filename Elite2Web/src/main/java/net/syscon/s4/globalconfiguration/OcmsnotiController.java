package net.syscon.s4.globalconfiguration;

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
import net.syscon.s4.im.beans.SanctionNotices;
import net.syscon.s4.im.beans.SanctionNoticesCommitBean;

/**
 * class OcmsnotiController
 */
@EliteController
public class OcmsnotiController {
	@Autowired
	private OcmsnotiService ocmsnotiService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmsnotiController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmsnoti/sanNotExecuteQuery", method = RequestMethod.POST)
	public List<SanctionNotices> sanNotExecuteQuery(@RequestBody final SanctionNotices searchBean) {
		List<SanctionNotices> searchResult = new ArrayList<>();
		try {
			searchResult = ocmsnotiService.sanNotExecuteQuery(searchBean);
		} catch (final Exception e) {
			final SanctionNotices bean = new SanctionNotices();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
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
	@RequestMapping(value = "/ocmsnoti/sanNotCommit", method = RequestMethod.POST)
	public @ResponseBody SanctionNotices sanNotCommit(@RequestBody final SanctionNoticesCommitBean commitBean) {
		SanctionNotices sanctionNotice = new SanctionNotices();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			sanctionNotice = ocmsnotiService.sanNotCommit(commitBean);

		} catch (final Exception e) {

			final String errorMsg = "Error : " + e.getMessage();
			sanctionNotice.setErrorMessage(errorMsg);
			logger.error("contactPersonTypesCommit :", e);
		}

		return sanctionNotice;
	}

}