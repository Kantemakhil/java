package net.syscon.s4.cm.programsservices.maintenance;

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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.VProgramPhases;
import net.syscon.s4.pkgs.common.CommonService;

@EliteController
public class OcmsvphaController {
	@Autowired
	private OcmsvphaService ocmsvphaService;
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmsvphaController.class.getName());

	/**
	 * getting rgPsModType LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmsvpha/rgPsModTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPsModTypeRecordGroup() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmsvphaService.rgPsModTypeRecordGroup();
		} catch (Exception e) {
			logger.error("Exception : Ocmsvpha:", e);
		}
		return recordList;
	}

	/**
	 * getting rgPsPhase LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmsvpha/rgPsPhaseRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPsPhaseRecordGroup() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmsvphaService.rgPsPhaseRecordGroup();
		} catch (Exception e) {
			logger.error("Exception : Ocmsvpha:", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 * @return List<VProgramPhases>
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmsvpha/vPrgPhssExecuteQuery", method = RequestMethod.POST)
	public List<VProgramPhases> vPrgPhssExecuteQuery(@RequestBody final VProgramPhases searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<VProgramPhases> searchResult = new ArrayList<>();
		try {
			searchResult = ocmsvphaService.vPrgPhssExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 * @return VProgramPhases
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmsvpha/vPrgPhssCommit", method = RequestMethod.POST)
	public @ResponseBody VProgramPhases vPrgPhssCommit(@RequestBody VProgramPhasesCommitBean commitBean) {
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		VProgramPhases liReturn = new VProgramPhases();

		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);

			liReturn = ocmsvphaService.vPrgPhssCommit(commitBean);
			if (liReturn.getSealFlag().length() > 2) {
				liReturn.setSealFlag(ocmsvphaService.errorNameValidation(liReturn.getSealFlag()));
			}
		} catch (Exception e) {
			logger.error("Exception : Ocmsvpha", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the Max value of listSeq from database table
	 * 
	 * @Param obj
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmsvpha/getListSeqMaxCount", method = RequestMethod.POST)
	public Integer getListSeq(@RequestBody VProgramPhases obj) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		Integer searchResult = 0;
		try {
			searchResult = ocmsvphaService.getListSeqMaxCount(obj.getProgramId());
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the Max value of getCourceActivityCount from database table
	 * 
	 * @Param obj
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmsvpha/getCourceActivityCount", method = RequestMethod.POST)
	public Integer getCourceActivityCount(@RequestBody VProgramPhases obj) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		Integer searchResult = 0;
		try {
			searchResult = ocmsvphaService.getCourceActivityCount(obj.getProgramId());
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCMSVPHA");
	}


}