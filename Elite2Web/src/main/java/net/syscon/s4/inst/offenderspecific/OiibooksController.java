package net.syscon.s4.inst.offenderspecific;

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
import net.syscon.s4.common.beans.FormAccessibleForms;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.VHeaderBlock;

@EliteController
public class OiibooksController {
	@Autowired
	private OiibooksService oiibooksService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiibooksController.class.getName());

	static final String HASELITEROLE = "hasEliteRole('read')";
	static final String EXCEPTION = "Exception :";

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiibooks/offBooksExecuteQuery", method = RequestMethod.POST)
	public List<OffenderBookings> offBooksExecuteQuery(@RequestBody final OffenderBookings searchBean) {
		List<OffenderBookings> searchResult = new ArrayList<>();
		try {
			searchResult = oiibooksService.offBooksExecuteQuery(searchBean);
		} catch (final Exception e) {
			final OffenderBookings bean = new OffenderBookings();
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
	@PreAuthorize(HASELITEROLE)
	@RequestMapping(value = "/oiibooks/bookDetailExecuteQuery", method = RequestMethod.POST)
	public List<FormAccessibleForms> bookDetailExecuteQuery(@RequestBody final FormAccessibleForms searchBean) {
		List<FormAccessibleForms> searchResult = new ArrayList<>();
		try {
			searchResult = oiibooksService.bookDetailExecuteQuery(searchBean);
		} catch (final Exception e) {
			final FormAccessibleForms bean = new FormAccessibleForms();
			logger.error(EXCEPTION, e);
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
	@PreAuthorize(HASELITEROLE)
	@RequestMapping(value = "/oiibooks/checkFormAccess", method = RequestMethod.POST)
	public List<String> checkFormAccess(@RequestBody final FormAccessibleForms searchBean) {
		String modulePrivileges = null;
		final List<String> returnList = new ArrayList<>();
		try {
			modulePrivileges = oiibooksService.checkFormAccess(searchBean);
			returnList.add(modulePrivileges);
		} catch (final Exception e) {
			final FormAccessibleForms bean = new FormAccessibleForms();
			logger.error(EXCEPTION, e);
			bean.setErrorMessage(e.getMessage());

		}
		return returnList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize(HASELITEROLE)
	@RequestMapping(value = "/oiibooks/getOffenderObject", method = RequestMethod.POST)
	public @ResponseBody VHeaderBlock getOffenderObject(@RequestBody final VHeaderBlock searchBean) {
		VHeaderBlock returnObj = null;
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			returnObj = oiibooksService.getOffenderObject(searchBean);
		} catch (final Exception e) {
			final FormAccessibleForms bean = new FormAccessibleForms();
			logger.error(EXCEPTION, e);
			bean.setErrorMessage(e.getMessage());

		}
		return returnObj;
	}

}