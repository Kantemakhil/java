package net.syscon.s4.inst.demographicsbiometrics;

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
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.InternetAddressesCommitBean;
import net.syscon.s4.im.beans.PhonesCommitBean;
import net.syscon.s4.pkgs.common.CommonService;

/**
 * 
 *Class  OcdgnumbController
 */
@EliteController
public class OcdgnumbController {
	@Autowired
	private OcdgnumbService ocdgnumbService;
	
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdgnumbController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocdgnumb/phonesExecuteQuery", method = RequestMethod.POST)
	public List<Phones> phonesExecuteQuery(@RequestBody final Phones searchBean) {
		List<Phones> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = ocdgnumbService.phonesExecuteQuery(searchBean);
		} catch (Exception e) {
			final Phones bean = new Phones();
			logger.error("",e);
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
	@RequestMapping(value = "/ocdgnumb/phonesCommit", method = RequestMethod.POST)
	public @ResponseBody Integer phonesCommit(@RequestBody final PhonesCommitBean commitBean) {
		int liReturn = 0;
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = ocdgnumbService.phonesCommit(commitBean);
		} catch (Exception e) {
			logger.error("phonesCommit", e);
		}
		return liReturn;
	}

	/**
	 * getting rgPhoneType LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocdgnumb/rgPhoneTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPhoneTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = ocdgnumbService.rgPhoneTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgPhoneTypeRecordGroup",e);
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
	@RequestMapping(value = "/ocdgnumb/internetAddrExecuteQuery", method = RequestMethod.POST)
	public List<InternetAddresses> internetAddrExecuteQuery(@RequestBody final InternetAddresses searchBean) {
		List<InternetAddresses> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = ocdgnumbService.internetAddrExecuteQuery(searchBean);
		} catch (Exception e) {
			final InternetAddresses bean = new InternetAddresses();
			logger.error("internetAddrExecuteQuery",e);
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
	@RequestMapping(value = "/ocdgnumb/internetAddrCommit", method = RequestMethod.POST)
	public @ResponseBody Integer internetAddrCommit(@RequestBody final InternetAddressesCommitBean commitBean) {
		int liReturn = 0;
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = ocdgnumbService.internetAddrCommit(commitBean);
		} catch (Exception e) {

			logger.error("internetAddrCommit", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocdgnumb/gettingEmailDomains", method = RequestMethod.GET)
	public List<String> gettingEmailDomains() {
		List<String> recordList = new ArrayList<String>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = ocdgnumbService.gettingEmailDomains();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgPhoneTypeRecordGroup",e);
			obj.setErrorMessage(e.getMessage());

		}
		return recordList;
	}

	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCDGNUMB");
	}
}