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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.ProgramServicesProfiles;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.ProgramServicesProfilesCommitBean;
import net.syscon.s4.pkgs.common.CommonService;

/**
 * OcmstoffController
 */
@EliteController
public class OcmstoffController {
	final String exceptionString = "Exception : Ocmstoff:";
	@Autowired
	private OcmstoffService ocmstoffService;
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmstoffController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmstoff/prgPrfGdExecuteQuery", method = RequestMethod.POST)
	public List<ProgramServicesProfiles> prgPrfGdExecuteQuery(@RequestBody final ProgramServicesProfiles searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ProgramServicesProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocmstoffService.prgPrfGdExecuteQuery(searchBean);
		} catch (Exception e) {
			final ProgramServicesProfiles bean = new ProgramServicesProfiles();
			logger.error(exceptionString, e);
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
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmstoff/prgPrfGdCommit", method = RequestMethod.POST)
	public @ResponseBody List<ProgramServicesProfiles> prgPrfGdCommit(
			@RequestBody final ProgramServicesProfilesCommitBean commitBean) {
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ProgramServicesProfiles> liReturn = new ArrayList<>();

		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocmstoffService.prgPrfGdCommit(commitBean);
		} catch (Exception e) {
			final ProgramServicesProfiles error = new ProgramServicesProfiles();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error(exceptionString, e);
		}
		return liReturn;
	}

	/**
	 * getting rgPsSex LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmstoff/rgPsSexRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPsSexRecordGroup() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmstoffService.rgPsSexRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(exceptionString, e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgEthnicity LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmstoff/rgEthnicityRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgEthnicityRecordGroup() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmstoffService.rgEthnicityRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(exceptionString, e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgPsNeeds LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmstoff/rgPsNeedsRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPsNeedsRecordGroup() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmstoffService.rgPsNeedsRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(exceptionString, e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgPsAgeRange LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmstoff/rgPsAgeRangeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPsAgeRangeRecordGroup() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmstoffService.rgPsAgeRangeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(exceptionString, e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgPsOffGrps LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmstoff/rgPsOffGrpsRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPsOffGrpsRecordGroup() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmstoffService.rgPsOffGrpsRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(exceptionString, e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmstoff/prgPrfRcExecuteQuery", method = RequestMethod.POST)
	public List<ProgramServicesProfiles> prgPrfRcExecuteQuery(@RequestBody final ProgramServicesProfiles searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ProgramServicesProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocmstoffService.prgPrfRcExecuteQuery(searchBean);
		} catch (Exception e) {
			final ProgramServicesProfiles bean = new ProgramServicesProfiles();
			logger.error(exceptionString, e);
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
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmstoff/prgPrfAgExecuteQuery", method = RequestMethod.POST)
	public List<ProgramServicesProfiles> prgPrfAgExecuteQuery(@RequestBody final  ProgramServicesProfiles searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ProgramServicesProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocmstoffService.prgPrfAgExecuteQuery(searchBean);
		} catch (Exception e) {
			final ProgramServicesProfiles bean = new ProgramServicesProfiles();
			logger.error(exceptionString, e);
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
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmstoff/prgPrfFaExecuteQuery", method = RequestMethod.POST)
	public List<ProgramServicesProfiles> prgPrfFaExecuteQuery(@RequestBody final ProgramServicesProfiles searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ProgramServicesProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocmstoffService.prgPrfFaExecuteQuery(searchBean);
		} catch (Exception e) {
			final ProgramServicesProfiles bean = new ProgramServicesProfiles();
			logger.error("Exception : prgPrfFaExecuteQuery", e);
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
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmstoff/prgPrfIgExecuteQuery", method = RequestMethod.POST)
	public List<ProgramServicesProfiles> prgPrfIgExecuteQuery(@RequestBody final ProgramServicesProfiles searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ProgramServicesProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocmstoffService.prgPrfIgExecuteQuery(searchBean);
		} catch (Exception e) {
			final ProgramServicesProfiles bean = new ProgramServicesProfiles();
			logger.error("Exception : prgPrfIgExecuteQuery", e);
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
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmstoff/prgPrfXgExecuteQuery", method = RequestMethod.POST)
	public List<ProgramServicesProfiles> prgPrfXgExecuteQuery(@RequestBody final ProgramServicesProfiles searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ProgramServicesProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocmstoffService.prgPrfXgExecuteQuery(searchBean);
		} catch (Exception e) {
			final ProgramServicesProfiles bean = new ProgramServicesProfiles();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmstoff/getProfileExist", method = RequestMethod.POST)
	public @ResponseBody Integer getProfileExist(@RequestBody final ProgramServicesProfiles commitBean) {
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		int liReturn = 0;
		try {
			liReturn = ocmstoffService.getProfileExist(commitBean);
		} catch (Exception e) {

			logger.error("Exception : Ocmstoff", e);
		}
		return liReturn;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCMSTOFF");
	}


}