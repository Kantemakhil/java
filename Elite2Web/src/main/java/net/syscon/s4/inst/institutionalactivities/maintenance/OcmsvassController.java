package net.syscon.s4.inst.institutionalactivities.maintenance;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.inst.casemanagement.beans.ProgramAssessments;
import net.syscon.s4.inst.casemanagement.beans.ProgramAssessmentsCommitBean;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.pkgs.common.CommonService;

/**
 * 
 * OcmsvassController
 * 
 */
@EliteController
public class OcmsvassController {

	@Autowired
	private OcmsvassService ocmsvassService;
	@Autowired
	private CommonService commonService;

	/**
	 * Logger object used to print the log in the file
	 */

	private static Logger logger = LogManager.getLogger(OcmsvassController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmsvass/prgQstExecuteQuery", method = RequestMethod.POST)
	public List<ProgramAssessments> prgQstExecuteQuery(@RequestBody final ProgramAssessments searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ProgramAssessments> searchResult = new ArrayList<ProgramAssessments>();
		try {
			searchResult = ocmsvassService.prgQstExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring functions insert,delete, update int the database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmsvass/prgQstCommit", method = RequestMethod.POST)
	public @ResponseBody Integer prgQstCommit(@RequestBody final ProgramAssessmentsCommitBean commitBean) {
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocmsvassService.prgQstCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
			if(e!=null && e.getMessage()!=null && e.getMessage().contains("program_assessments_pk")) {
				liReturn= 18;
			}
		}
		return liReturn;
	}

	/**
	 * getting rgAssessments LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmsvass/rgAssessmentsRecordGroup", method = RequestMethod.GET)
	public List<Assessments> rgAssessmentsRecordGroup(@RequestParam(value = "assesmentCode") final String assesmentCode ) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<Assessments> recordList = new ArrayList<Assessments>();
		try {
			recordList = ocmsvassService.rgAssessmentsRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCMSVASS");
	}

}