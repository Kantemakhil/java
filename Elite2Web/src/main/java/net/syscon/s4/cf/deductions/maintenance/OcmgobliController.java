package net.syscon.s4.cf.deductions.maintenance;

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
import net.syscon.s4.im.beans.GroupedObligations;
import net.syscon.s4.im.beans.GroupedObligationsCommitBean;
import net.syscon.s4.im.beans.ObligationGroups;
import net.syscon.s4.im.beans.ObligationGroupsCommitBean;
import net.syscon.s4.im.beans.SanctionNotices;
import net.syscon.s4.inmate.beans.DeductionTypes;

@EliteController
public class OcmgobliController {
	@Autowired
	private OcmgobliService ocmgobliService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmgobliController.class.getName());

	/**
	 * getting cgfkGrpObDeductionType LOV values
	 * @return a list of the DeductionTypes {@link DeductionTypes} from the DB
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmgobli/cgfkGrpObDeductionTypeRecordGroup", method = RequestMethod.GET)
	public List<DeductionTypes> cgfkGrpObDeductionTypeRecordGroup() {
		List<DeductionTypes> recordList = new ArrayList<>();
		try {
			recordList = ocmgobliService.cgfkGrpObDeductionTypeRecordGroup();
		} catch (final Exception e) {
			final DeductionTypes obj = new DeductionTypes();
			logger.error("Exception : Ocmgobli:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkSanctionNotices LOV values
	 * @return a list of the SanctionNotices {@link SanctionNotices} from the DB
	 */

	@PreAuthorize("hasEliteRole('read')")

	@RequestMapping(value = "/ocmgobli/cgfkSanctionNoticesRecordGroup", method = RequestMethod.GET)
	public List<SanctionNotices> cgfkSanctionNoticesRecordGroup() {
		List<SanctionNotices> recordList = new ArrayList<>();
		try {
			recordList = ocmgobliService.cgfkSanctionNoticesRecordGroup();
		} catch (final Exception e) {
			final SanctionNotices obj = new SanctionNotices();
			logger.error("Exception : Ocmgobli:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link ObligationGroups}
	 * @return a list of the ObligationGroups {@link ObligationGroups} for the matched ObligationGroups
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmgobli/obGrpExecuteQuery", method = RequestMethod.POST)
	public List<ObligationGroups> obGrpExecuteQuery(@RequestBody final ObligationGroups searchBean) {
		List<ObligationGroups> searchResult = new ArrayList<>();
		try {
			searchResult = ocmgobliService.obGrpExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 *
	 * @param commitBean {@link ObligationGroupsCommitBean}
	 * @return the ObligationGroupsCommitBean {@link ObligationGroupsCommitBean} for the matched ObligationGroupsCommitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocmgobli/obGrpCommit", method = RequestMethod.POST)
	public @ResponseBody ObligationGroups obGrpCommit(@RequestBody final ObligationGroupsCommitBean commitBean) {
		ObligationGroups liReturn = new ObligationGroups();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			  liReturn = ocmgobliService.obGrpCommit(commitBean);
		} catch (final Exception e) {
			final String errorMsg = "Error : " + e.getMessage();
			liReturn.setErrorMessage(errorMsg.toUpperCase());
			return liReturn;
		}
		 return liReturn;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link GroupedObligations}
	 * @return a list of the GroupedObligations {@link GroupedObligations} for the matched GroupedObligations
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmgobli/grpObExecuteQuery", method = RequestMethod.POST)
	public List<GroupedObligations> grpObExecuteQuery(@RequestBody final GroupedObligations searchBean) {
		List<GroupedObligations> searchResult = new ArrayList<>();
		try {
			searchResult = ocmgobliService.grpObExecuteQuery(searchBean);
		} catch (final Exception e) {
			final GroupedObligations bean = new GroupedObligations();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 *
	 * @param commitBean {@link GroupedObligationsCommitBean}
	 * @return success/failure of the insert/update {@link Integer} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocmgobli/grpObCommit", method = RequestMethod.POST)
	public @ResponseBody Integer grpObCommit(@RequestBody final GroupedObligationsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocmgobliService.grpObCommit(commitBean);
		} catch (final Exception e) {

			logger.error("Exception : Ocmgobli", e);
		}
		return liReturn;
	}

}