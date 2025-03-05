package net.syscon.s4.programswithoutschedules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderPrgObligationHty;
import net.syscon.s4.im.beans.OffenderPrgObligationHtyCommitBean;
import net.syscon.s4.inst.programswithoutschedules.OcuupstaService;
import net.syscon.s4.pkgs.common.CommonService;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * Class OcuupstaController
 */
@EliteController
public class OcuupstaController {
	@Autowired
	private OcuupstaService ocuupstaService;
	@Autowired
	private ProsmainService prosmainService;
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuupstaController.class.getName());

	/**
	 * getting rgPsPrgStat LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuupsta/rgPsPrgStatRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPsPrgStatRecordGroup(@RequestParam(value = "statusDesc") final String statusDesc,
			@RequestParam(value = "lovDomain") final String lovDomain) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocuupstaService.rgPsPrgStatRecordGroup(statusDesc, lovDomain);
		} catch (final Exception e) {
			logger.error(this.getClass().getName()+" rgPsPrgStatRecordGroup"+e);
		}
		return recordList;
	}

	/**
	 * getting rgPsPrgObsts LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuupsta/rgPsPrgObstsRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPsPrgObstsRecordGroup(@RequestParam(value = "parentCode") final String parentCode) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocuupstaService.rgPsPrgObstsRecordGroup(parentCode);
		} catch (final Exception e) {
			logger.error(this.getClass().getName()+" rgPsPrgObstsRecordGroup"+e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuupsta/offPrgOblHtyExecuteQuery", method = RequestMethod.POST)
	public List<OffenderPrgObligationHty> offPrgOblHtyExecuteQuery(
			@RequestBody final OffenderPrgObligationHty searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<OffenderPrgObligationHty> searchResult = new ArrayList<>();
		try {
			searchResult = ocuupstaService.offPrgOblHtyExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error(this.getClass().getName()+" offPrgOblHtyExecuteQuery "+e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuupsta/updateStatus", method = RequestMethod.POST)
	public Integer updateStatus(@RequestBody final OffenderPrgObligationHty searchBean, @RequestHeader HttpHeaders headers) {
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		Integer searchResult = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			
			searchResult = ocuupstaService.updateStatus(searchBean);
			if(1==searchResult) {
				OffenderPrgObligationHtyCommitBean commitBean = new OffenderPrgObligationHtyCommitBean();
				List<OffenderPrgObligationHty> prgObligationHtyList = new ArrayList<OffenderPrgObligationHty>();
				prgObligationHtyList.add(searchBean);
				commitBean.setInsertList(prgObligationHtyList);
				prosmainService.enableTriggers(commitBean, authorization, "35");
			}
		} catch (final Exception e) {
			logger.error(this.getClass().getName()+""+e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuupsta/getRefferaldate", method = RequestMethod.GET)
	public Date getRefferalDate(
			@RequestParam(value = "offenderPrgObligationId") final Integer offenderPrgObligationId) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		Date searchResult = new Date();
		try {
			searchResult = ocuupstaService.getRefferalDate(offenderPrgObligationId);
		} catch (final Exception e) {
			logger.error(this.getClass().getName()+" getRefferaldate "+ e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuupsta/getMaxDate", method = RequestMethod.GET)
	public Date getMaxDate(@RequestParam(value = "offenderPrgObligationId") final Integer offenderPrgObligationId) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		Date searchResult = new Date();
		try {
			searchResult = ocuupstaService.getMaxDate(offenderPrgObligationId);
		} catch (final Exception e) {
			logger.error(this.getClass().getName()+" getMaxDate "+e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuupsta/updateStatusBtn", method = RequestMethod.GET)
	public Boolean updateStatusBtn(@RequestParam("code") final String code) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		Boolean result=false;
		try {
			result=ocuupstaService.updateStatusBtn(code);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" updateStatusBtn "+e);
		}
		return result;
	}
	
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuupsta/getAdministratorUserAccsess", method = RequestMethod.GET)
	public Boolean getAdministratorUserAccsess() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		Boolean result = false;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			result = ocuupstaService.getAdministratorUserAccsess(userName);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getAdministratorUserAccsess " + e);
		}
		return result;
	}
	
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuupsta/reasonForSuspendingOrEndingProgramDisable", method = RequestMethod.GET)
	public Boolean reasonForSuspendingOrEndingProgramDisable(@RequestParam("code") final String code) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		Boolean result=false;
		try {
			result=ocuupstaService.reasonForSuspendingOrEndingProgramDisable(code);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" updateStatusBtn "+e);
		}
		return result;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuupsta/refCodeExecuteQuery", method = RequestMethod.POST)
	public List<ReferenceCodes> refCodeExecuteQuery(@RequestBody ReferenceCodes searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> searchResult = new ArrayList<>();
		try {
			searchResult = ocuupstaService.refCodeExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " refCodeExecuteQuery"+ e);
		}
		return searchResult;
	}
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCUUPSTA");
	}
}