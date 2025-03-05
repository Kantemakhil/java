package net.syscon.s4.inst.legalscreens.maintenance;

import java.sql.SQLException;
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
import net.syscon.s4.inst.casemanagement.beans.CommunityConditions;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;
import net.syscon.s4.inst.legalscreens.maintenance.bean.CommunityConditionsCommitBean;

/**
 * OcmcondiController
 */
@EliteController
public class OcmcondiController {
	@Autowired
	private OcmcondiService ocmcondiService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmcondiController.class.getName());

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link CommunityConditions}
	 * @return a list of the CommunityConditions {@link CommunityConditions} for the matched CommunityConditions
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmcondi/comCondExecuteQuery", method = RequestMethod.POST)
	public List<CommunityConditions> comCondExecuteQuery(@RequestBody final CommunityConditions searchBean) {
		List<CommunityConditions> searchResult = new ArrayList<>();
		try {
			searchResult = ocmcondiService.comCondExecuteQuery(searchBean);
		} catch (Exception e) {
			final CommunityConditions bean = new CommunityConditions();
			logger.error("Exception :comCondExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmcondi/comCondFilteredData", method = RequestMethod.POST)
	public List<CommunityConditions> comCondFilteredData(@RequestBody final OffenderSentConditions searchBean) {
		List<CommunityConditions> searchResult = new ArrayList<>();
		try {
			searchResult = ocmcondiService.comCondFilteredData(searchBean);
		} catch (Exception e) {
			final CommunityConditions bean = new CommunityConditions();
			logger.error("Exception :comCondFilteredData", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 *
	 * @param commitBean {@link String}
	 * @return a list of the CommunityConditionsCommitBean {@link CommunityConditionsCommitBean} for the matched CommunityConditionsCommitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocmcondi/comCondCommit", method = RequestMethod.POST)
	public @ResponseBody List<CommunityConditions> comCondCommit(
			@RequestBody final CommunityConditionsCommitBean commitBean) {
		List<CommunityConditions> liReturn = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocmcondiService.comCondCommit(commitBean);
		} catch (Exception e) {

			final CommunityConditions error = new CommunityConditions();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception :comCondCommit", e);
		}
		return liReturn;
	}

	/**
	 * getting rgCat LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmcondi/rgCatRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCatRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocmcondiService.rgCatRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : rgCatRecordGroup:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmcondi/rgTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocmcondiService.rgTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : rgTypeRecordGroup:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgUnit LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmcondi/rgUnitRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgUnitRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocmcondiService.rgUnitRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : rgUnitRecordGroup:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgSvcObl LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmcondi/rgSvcOblRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSvcOblRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocmcondiService.rgSvcOblRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : rgSvcOblRecordGroup:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgFunctionType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmcondi/rgFunctionTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgFunctionTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocmcondiService.rgFunctionTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : rgFunctionTypeRecordGroup:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * This method is used to get the parent table list and parent table data
	 *
	 * @throws SQLException
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocmcondi/okToModifyRecord", method = RequestMethod.POST)
	public CommunityConditions okToModifyRecord(@RequestBody final CommunityConditions searchBean) {
		CommunityConditions returnObj = new CommunityConditions();
		try {
			returnObj = ocmcondiService.getDeleteRecordOrNot(searchBean);
		} catch (Exception e) {
			logger.error("Exception : okToModifyRecord:", e);
		}
		return returnObj;
	}

}