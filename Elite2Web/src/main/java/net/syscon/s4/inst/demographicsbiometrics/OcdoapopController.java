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
import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.beans.AddressesCommitBean;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.pkgs.common.CommonService;

/**
 * Class OcdoapopController
 */
@EliteController
public class OcdoapopController {
	@Autowired
	private OcdoapopService ocdoapopService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private OcdaddreService ocdaddreService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdoapopController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocdoapop/addressExecuteQuery", method = RequestMethod.POST)
	public List<Addresses> addressExecuteQuery(@RequestBody final Addresses searchBean) {
		List<Addresses> searchResult = new ArrayList<>();
		try {
			searchResult = ocdoapopService.addressExecuteQuery(searchBean);
		} catch (Exception e) {
			final Addresses bean = new Addresses();
			logger.error("In method addressExecuteQuery", e);
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
	@RequestMapping(value = "/ocdoapop/addressCommit", method = RequestMethod.POST)
	public @ResponseBody Addresses addressCommit(@RequestBody final AddressesCommitBean commitBean) {
		Addresses returnObj=new Addresses();
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			returnObj = ocdoapopService.addressCommit(commitBean);
		} catch (Exception e) {
			logger.error("In method addressCommit"+e.getMessage());
		}
		return returnObj;
	}

	/**
	 * getting rgCity LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdoapop/rgCityRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCityRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdoapopService.rgCityRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method rgCityRecordGroup",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgCounty LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdoapop/rgCountyRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCountyRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdoapopService.rgCountyRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method rgCountyRecordGroup",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgCountry LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdoapop/rgCountryRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCountryRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdoapopService.rgCountryRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method rgCountryRecordGroup",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdoapop/rgTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdoapopService.rgTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method rgTypeRecordGroup",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgSpecialNeeds LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdoapop/rgSpecialNeedsRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSpecialNeedsRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdoapopService.rgSpecialNeedsRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method rgSpecialNeedsRecordGroup",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgProvStateCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdoapop/rgProvStateCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgProvStateCodeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdoapopService.rgProvStateCodeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method rgProvStateCodeRecordGroup",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgStreetDir LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdoapop/rgStreetDirRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgStreetDirRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdoapopService.rgStreetDirRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method rgStreetDirRecordGroup",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgStreetDir LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocdoapop/addressKeyDelrec", method = RequestMethod.POST)
	public List<Phones> addressKeyDelrec(@RequestBody final Phones searchBean) {
		List<Phones> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = ocdoapopService.addressKeyDelrec(searchBean);
		} catch (Exception e) {
			logger.error("In method addressKeyDelrec",e);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocdoapop/rgTownRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgTownRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = ocdaddreService.rgTownRecordGroup();
		} catch (Exception e) {
			logger.error("In rgTownRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocdoapop/rgStateRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgStateRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = ocdaddreService.rgStateRecordGroup();
		} catch (Exception e) {
			logger.error("In rgStateRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocdoapop/rgCountryRecordGroup1", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCountryRecordGroup1() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = ocdaddreService.rgCountryRecordGroup();
		} catch (Exception e) {
			logger.error("In rgCountryRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCDOAPOP");
	}
}