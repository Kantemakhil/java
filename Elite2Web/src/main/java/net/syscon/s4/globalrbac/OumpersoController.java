package net.syscon.s4.globalrbac;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.InternetAddressesCommitBean;
import net.syscon.s4.im.beans.PhonesCommitBean;
import net.syscon.s4.im.beans.StaffMembersCommitBean;
import net.syscon.s4.im.beans.VStaffAddresses;
import net.syscon.s4.inst.demographicsbiometrics.OcdaddreService;
import net.syscon.s4.pkgs.common.CommonService;

/**
 * class OumpersoController
 */
@EliteController
public class OumpersoController {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumpersoController.class.getName());

	@Autowired
	private OumpersoService oumpersoService;
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private OcdaddreService ocdaddreService;
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumperso/staffExecuteQuery", method = RequestMethod.POST)
	public List<StaffMembers> staffExecuteQuery(@RequestBody final StaffMembers searchBean) {
		List<StaffMembers> searchResult = new ArrayList<>();
		final StaffMembers bean = new StaffMembers();
		try {
			searchResult = oumpersoService.staffExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In staffExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oumperso/staffCommit", method = RequestMethod.POST)
	public @ResponseBody String staffCommit(@RequestBody final StaffMembersCommitBean commitBean) {
		String liReturn = null;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oumpersoService.staffCommit(commitBean);
		} catch (Exception e) {
			logger.error("In staffCommit method : ", e);
		}
		return liReturn;
	}

	/**
	 * getting rgPhoneType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumperso/rgPhoneTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPhoneTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes bean = new ReferenceCodes();
		try {
			recordList = oumpersoService.rgPhoneTypeRecordGroup();
		} catch (Exception e) {
			logger.error("In rgPhoneTypeRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting rgSuffix LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumperso/rgSuffixRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSuffixRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes bean = new ReferenceCodes();
		try {
			recordList = oumpersoService.rgSuffixRecordGroup();
		} catch (Exception e) {
			logger.error("In rgSuffixRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting rgSexCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumperso/rgSexCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSexCodeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes bean = new ReferenceCodes();
		try {
			recordList = oumpersoService.rgSexCodeRecordGroup();
		} catch (Exception e) {
			logger.error("In rgSexCodeRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting rgStatus LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumperso/rgStatusRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgStatusRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes bean = new ReferenceCodes();
		try {
			recordList = oumpersoService.rgStatusRecordGroup();
		} catch (Exception e) {
			logger.error("In rgStatusRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting rgPersonnelType LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumperso/rgPersonnelTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPersonnelTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes bean = new ReferenceCodes();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oumpersoService.rgPersonnelTypeRecordGroup();
		} catch (Exception e) {
			logger.error("In rgPersonnelTypeRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting rgPosition LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumperso/rgPositionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPositionRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes bean = new ReferenceCodes();
		try {
			recordList = oumpersoService.rgPositionRecordGroup();
		} catch (Exception e) {
			logger.error("In rgPositionRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumperso/imageExecuteQuery", method = RequestMethod.POST)
	public List<Images> imageExecuteQuery(@RequestBody final Images searchBean) {
		List<Images> searchResult = new ArrayList<>();
		final Images bean = new Images();
		try {
			searchResult = oumpersoService.imageExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In imageExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumperso/vStfAddrExecuteQuery", method = RequestMethod.POST)
	public List<VStaffAddresses> vStfAddrExecuteQuery(@RequestBody final VStaffAddresses searchBean) {
		List<VStaffAddresses> searchResult = new ArrayList<>();
		final VStaffAddresses bean = new VStaffAddresses();
		try {
			searchResult = oumpersoService.vStfAddrExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In vStfAddrExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumperso/addrPhonesExecuteQuery", method = RequestMethod.POST)
	public List<Phones> addrPhonesExecuteQuery(@RequestBody final Phones searchBean) {
		List<Phones> searchResult = new ArrayList<>();
		final Phones bean = new Phones();
		try {
			searchBean.setOwnerClass("ADDR");
			searchResult = oumpersoService.addrPhonesExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In addrPhonesExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oumperso/addrPhonesCommit", method = RequestMethod.POST)
	public @ResponseBody Integer addrPhonesCommit(@RequestBody final PhonesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oumpersoService.addrPhonesCommit(commitBean);
		} catch (Exception e) {
			logger.error("In addrPhonesCommit method : ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumperso/stfPhonesExecuteQuery", method = RequestMethod.POST)
	public List<Phones> stfPhonesExecuteQuery(@RequestBody final Phones searchBean) {
		List<Phones> searchResult = new ArrayList<>();
		final Phones bean = new Phones();
		try {
			searchBean.setOwnerClass("STF");
			searchResult = oumpersoService.addrPhonesExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In stfPhonesExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oumperso/stfPhonesCommit", method = RequestMethod.POST)
	public @ResponseBody Integer stfPhonesCommit(@RequestBody final PhonesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oumpersoService.stfPhonesCommit(commitBean);
		} catch (Exception e) {
			logger.error("In stfPhonesCommit method : ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumperso/emailAddrExecuteQuery", method = RequestMethod.POST)
	public List<InternetAddresses> emailAddrExecuteQuery(@RequestBody final InternetAddresses searchBean) {
		List<InternetAddresses> searchResult = new ArrayList<>();
		final InternetAddresses bean = new InternetAddresses();
		try {
			searchResult = oumpersoService.emailAddrExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In stfPhonesExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oumperso/emailAddrCommit", method = RequestMethod.POST)
	public @ResponseBody Integer emailAddrCommit(@RequestBody final InternetAddressesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oumpersoService.emailAddrCommit(commitBean);
		} catch (Exception e) {
			logger.error("In emailAddrCommit method : ", e);
		}
		return liReturn;
	}
	
	@RequestMapping(value="/oumperso/getStaffDetails", method=RequestMethod.POST)
	public List<StaffMembers> getStaffDetails(@RequestBody final Map<String, String> fromDate) {
		Date startDate = null;
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fromDate.get("fromDate"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<StaffMembers> searchResult = new ArrayList<>();
		final StaffMembers bean = new StaffMembers();
		try {
			searchResult = oumpersoService.getStaffDetails(startDate);
		} catch (Exception e) {
			logger.error("In getStaffDetails method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumperso/rgTownRecordGroup", method = RequestMethod.GET)
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
	@RequestMapping(value = "/oumperso/rgStateRecordGroup", method = RequestMethod.GET)
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
	@RequestMapping(value = "/oumperso/rgCountryRecordGroup1", method = RequestMethod.GET)
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
		return commonService.checkCallFormAccess(userId, role,"OUMPERSO");
	}
}