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
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.casemanagement.beans.ProgramServicesCommitBean;
import net.syscon.s4.pkgs.common.CommonService;

/**
 * class OcmphblkController
 */
@EliteController
public class OcmphblkController {

	@Autowired
	private OcmphblkService ocmphblkService;
	@Autowired
	private CommonService commonService;

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmphblkController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<ProgramServices>
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmphblk/prgSrvExecuteQuery", method = RequestMethod.POST)
	public List<ProgramServices> prgSrvExecuteQuery(@RequestBody final ProgramServices searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ProgramServices> searchResult = new ArrayList<>();
		try {
			searchResult = ocmphblkService.prgSrvExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("prgSrvExecuteQuery :", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 *
	 * @return Integer
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmphblk/prgSrvCommit", method = RequestMethod.POST)
	public Integer prgSrvCommit(@RequestBody final ProgramServicesCommitBean commitBean) {
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocmphblkService.prgSrvCommit(commitBean);
		} catch (final Exception e) {

			logger.error("prgSrvCommit", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmphblk/getNextPrgSrvListSeq", method = RequestMethod.POST)
	public Integer getNextPrgSrvListSeq(@RequestBody final ProgramServices commitBean) {
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		int liReturn = 0;
		try {
			liReturn = ocmphblkService.getNextPrgSrvListSeq(commitBean);
		} catch (final Exception e) {
			logger.error("getNextPrgSrvListSeq", e);
		}
		return liReturn;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCMPHBLK");
	}

}