package net.syscon.s4.inst.incidentsoic;

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

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgyIncInvStatements;
import net.syscon.s4.im.beans.AgyIncInvStatementsCommitBean;
import net.syscon.s4.im.beans.AgyIncInvestigations;
import net.syscon.s4.im.beans.AgyIncInvestigationsCommitBean;

@EliteController
public class OcuoicinController {

	@Autowired
	private OcuoicinService ocuoicinService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuoicinController.class.getName());

	/**
	 * method for query callings
	 * 
	 * @return List<AgyIncInvestigations>
	 * @param searchBean
	 * 
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoicin/oicInvestExecuteQuery", method = RequestMethod.POST)
	public List<AgyIncInvestigations> oicInvestSearchAgyIncInvestigations(
			@RequestBody final AgyIncInvestigations searchBean) {
		List<AgyIncInvestigations> searchResult = new ArrayList<>();
		final AgyIncInvestigations bean = new AgyIncInvestigations();
		try {
			searchResult = ocuoicinService.oicInvestSearchAgyIncInvestigations(searchBean);
		} catch (Exception e) {
			logger.error("In this method oicInvestSearchAgyIncInvestigations"+e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param AgyIncInvestigationsCommitBean commitBean
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoicin/oicInvestCommit", method = RequestMethod.POST)
	public @ResponseBody Integer oicInvestCommit(@RequestBody final AgyIncInvestigationsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocuoicinService.oicInvestInsertAgyIncInvestigations(commitBean);
		} catch (Exception e) {

			logger.error("In this method oicInvestCommit"+e);
		}
		return liReturn;
	}

	/**
	 * getting rgStatementType LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoicin/rgStatementTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgStatementTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = ocuoicinService.rgStatementTypeRecordGroup();
		} catch (Exception e) {
			logger.error("In this method rgStatementTypeRecordGroup"+e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<StaffMembers>
	 * @param caseloadId
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoicin/rgAgyIncpStaffIdRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgAgyIncpStaffIdRecordGroup(@RequestParam(value = "caseloadId") final String caseloadId) {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		final StaffMembers obj = new StaffMembers();
		try {
			recordList = ocuoicinService.rgAgyIncpStaffIdRecordGroup(caseloadId);
		} catch (Exception e) {
			logger.error("In this method rgAgyIncpStaffIdRecordGroup"+e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<AgyIncInvStatements>
	 * @param searchBean
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoicin/oicInvestStaExecuteQuery", method = RequestMethod.POST)
	public List<AgyIncInvStatements> oicInvestStaSearchAgyIncInvStatements(
			@RequestBody final AgyIncInvStatements searchBean) {
		List<AgyIncInvStatements> searchResult = new ArrayList<>();
		final AgyIncInvStatements bean = new AgyIncInvStatements();
		try {
			searchResult = ocuoicinService.oicInvestStaSearchAgyIncInvStatements(searchBean);
		} catch (Exception e) {
			logger.error("In this method oicInvestStaSearchAgyIncInvStatements"+e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param AgyIncInvStatementsCommitBean commitBean
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoicin/oicInvestStaCommit", method = RequestMethod.POST)
	public @ResponseBody Integer oicInvestStaCommit(@RequestBody final AgyIncInvStatementsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocuoicinService.oicInvestStaInsertAgyIncInvStatements(commitBean);
		} catch (Exception e) {
			logger.error("In this method oicInvestStaCommit"+e);
		}
		return liReturn;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<Object>
	 * @param AgyIncInvestigationId
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoicin/oicInvestOnCheckDeleteMasteroicInvestStaCur", method = RequestMethod.POST)
	public @ResponseBody List<Object> oicInvestOnCheckDeleteMasteroicInvestStaCur(
			@RequestParam(value = "AgyIncInvestigationId") final String AgyIncInvestigationId) {
		List<Object> dataObj = null;
		final String investgation = AgyIncInvestigationId;
		try {
			dataObj = ocuoicinService.oicInvestOnCheckDeleteMasteroicInvestStaCur(investgation);
		} catch (Exception e) {
			logger.error("In this method oicInvestOnCheckDeleteMasteroicInvestStaCur"+e);
		}
		return dataObj;
	}

}