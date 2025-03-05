package net.syscon.s4.inst.demographicsbiometrics;

import java.util.ArrayList;
import java.util.Collections;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.beans.AddressesCommitBean;
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.OffenderCurfews;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AddressUsages;
import net.syscon.s4.im.beans.AddressUsagesCommitBean;
//import net.syscon.s4.im.beans.Addresses;
//import net.syscon.s4.im.beans.AddressesCommitBean;
import net.syscon.s4.im.beans.InternetAddressesCommitBean;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.PhonesCommitBean;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.pkgs.common.CommonService;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * Class OcdaddreController
 * 
 */
@EliteController
public class OcdaddreController {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdaddreController.class.getName());

	@Autowired
	private OcdaddreService ocdaddreService;
	
	@Autowired
	private ProsmainService prosmainService;
	
	@Autowired
	private CommonService commonService;

	/**
	 * Fetching the record from database table
	 * 
	 * @Param vaddresses
	 * @return List<VAddresses>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaddre/vAddExecuteQuery", method = RequestMethod.POST)
	public List<VAddresses> vAddSearchVAddresses(@RequestBody final VAddresses vaddresses) {
		List<VAddresses> searchResult = new ArrayList<>();
		final VAddresses bean = new VAddresses();
		try {
			searchResult = ocdaddreService.vAddSearchVAddresses(vaddresses);
		} catch (Exception e) {
			logger.error("In vAddSearchVAddresses method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaddre/rgTownRecordGroup", method = RequestMethod.GET)
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

	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaddre/rgCountryRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCountryRecordGroup() {
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

	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaddre/rgSdirRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSdirRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = ocdaddreService.rgSdirRecordGroup();
		} catch (Exception e) {
			logger.error("In rgSdirRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaddre/rgStateRecordGroup", method = RequestMethod.GET)
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

	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaddre/rgAddressTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgAddressTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = ocdaddreService.rgAddressTypeRecordGroup();
		} catch (Exception e) {
			logger.error("In rgAddressTypeRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaddre/rgPhoneTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPhoneTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = ocdaddreService.rgPhoneTypeRecordGroup();
		} catch (Exception e) {
			logger.error("In rgPhoneTypeRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param addresses
	 * 
	 * @return List<Addresses>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaddre/addrExecuteQuery", method = RequestMethod.POST)
	public Addresses searchAddresses(@RequestBody final Addresses addresses) {
		Addresses searchResult = new Addresses();
		try {
			searchResult = ocdaddreService.searchAddresses(addresses);
		} catch (Exception e) {
			logger.error("In searchAddresses method : ", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @Param commitBean
	 * 
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdaddre/addrCommit", method = RequestMethod.POST)
	public @ResponseBody Integer addrCommit(@RequestBody final AddressesCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocdaddreService.addrCommit(commitBean);
			if(0 != liReturn ) {
				prosmainService.enableTriggers(commitBean, authorization, "91");
			}
		} catch (Exception e) {
			logger.error("In addrCommit method : ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param addressUsages
	 * 
	 * @return List<AddressUsages>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaddre/addrUsageExecuteQuery", method = RequestMethod.POST)
	public List<AddressUsages> addrUsageSearchAddressUsages(@RequestBody final AddressUsages addressUsages) {
		List<AddressUsages> searchResult = new ArrayList<>();
		final AddressUsages bean = new AddressUsages();
		try {
			searchResult = ocdaddreService.addrUsageSearchAddressUsages(addressUsages);
		} catch (Exception e) {
			logger.error("In addrUsageSearchAddressUsages method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @Param commitBean
	 * 
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdaddre/addrUsageCommit", method = RequestMethod.POST)
	public @ResponseBody Integer addrUsageCommit(@RequestBody final AddressUsagesCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocdaddreService.addrUsageCommit(commitBean);
		} catch (Exception e) {

			logger.error("In addrUsageCommit method : ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param phones
	 * @return List<Phones>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaddre/phoneAddrExecuteQuery", method = RequestMethod.POST)
	public List<Phones> phoneAddrSearchPhones(@RequestBody final Phones phones) {
		List<Phones> searchResult = new ArrayList<>();
		final Phones bean = new Phones();
		try {
			searchResult = ocdaddreService.phoneAddrSearchPhones(phones);
		} catch (Exception e) {
			logger.error("In phoneAddrSearchPhones method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @Param commitBean
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdaddre/phoneAddrCommit", method = RequestMethod.POST)
	public @ResponseBody Integer phoneAddrCommit(@RequestBody final PhonesCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocdaddreService.phoneAddrCommit(commitBean);
		} catch (Exception e) {
			logger.error("In phoneAddrCommit method : ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param phones
	 * @return List<Phones>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaddre/phoneGlobalExecuteQuery", method = RequestMethod.POST)
	public List<Phones> phoneGlobalSearchPhones(@RequestBody final Phones phones) {
		List<Phones> searchResult = new ArrayList<>();
		final Phones bean = new Phones();
		try {
			searchResult = ocdaddreService.phoneGlobalSearchPhones(phones);
		} catch (Exception e) {
			logger.error("In phoneGlobalSearchPhones method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @Param commitBean
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdaddre/phoneGlobalCommit", method = RequestMethod.POST)
	public @ResponseBody Integer phoneGlobalCommit(@RequestBody final PhonesCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocdaddreService.phoneGlobalCommit(commitBean);
		} catch (Exception e) {

			logger.error("In phoneGlobalCommit method : ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param internetAddresses
	 * @return List<InternetAddresses>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaddre/emailExecuteQuery", method = RequestMethod.POST)
	public List<InternetAddresses> emailSearchInternetAddresses(
			@RequestBody final InternetAddresses internetAddresses) {
		List<InternetAddresses> searchResult = new ArrayList<>();
		final InternetAddresses bean = new InternetAddresses();
		try {
			searchResult = ocdaddreService.emailSearchInternetAddresses(internetAddresses);
		} catch (Exception e) {
			logger.error("In emailSearchInternetAddresses method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @Param commitBean Return Integer
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdaddre/emailCommit", method = RequestMethod.POST)
	public @ResponseBody Integer emailCommit(@RequestBody final InternetAddressesCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocdaddreService.emailCommit(commitBean);
		} catch (Exception e) {
			logger.error("In emailCommit method : ", e);
		}
		return liReturn;
	}

	/**
	 * method for query calling
	 * 
	 * @param vAddresses
	 * @return List<Object>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaddre/offbkgoncheckdeletemastervaddcur", method = RequestMethod.POST)
	public @ResponseBody List<Object> offbkgoncheckdeletemastervaddcur(@RequestBody final VAddresses vAddresses) {
		List<Object> dataObj = null;
		try {
			dataObj = new ArrayList<Object>();
			dataObj = ocdaddreService.offBkgOnCheckDeleteMastervAddCur(vAddresses);
		} catch (Exception e) {
			logger.error("In offbkgoncheckdeletemastervaddcur method : ", e);
		}
		return dataObj;
	}

	/**
	 * method for query calling
	 * 
	 * @param phones
	 * @return List<Object>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaddre/offBkgOnCheckDeleteMasterphoneGlobalCur", method = RequestMethod.POST)
	public @ResponseBody List<Object> offBkgOnCheckDeleteMasterphoneGlobalCur(@RequestBody final Phones phones) {
		List<Object> dataObj = null;
		try {
			dataObj = new ArrayList<>();
			dataObj = ocdaddreService.offBkgOnCheckDeleteMasterphoneGlobalCur(phones);
		} catch (Exception e) {
			logger.error("In offBkgOnCheckDeleteMasterphoneGlobalCur method : ", e);
		}
		return dataObj;
	}

	/**
	 * method for query calling
	 * 
	 * @param internetAddresses
	 * @return List<Object>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaddre/offBkgOnCheckDeleteMasteremailCur", method = RequestMethod.POST)
	public @ResponseBody List<Object> offBkgOnCheckDeleteMasteremailCur(
			@RequestBody final InternetAddresses internetAddresses) {
		List<Object> dataObj = null;
		try {
			dataObj = new ArrayList<>();
			dataObj = ocdaddreService.offBkgOnCheckDeleteMasteremailCur(internetAddresses);
		} catch (Exception e) {
			logger.error("In offBkgOnCheckDeleteMasteremailCur method : ", e);
		}
		return dataObj;
	}

	/**
	 * method for query calling
	 * 
	 * @param addresses
	 * @return List<Object>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaddre/offBkgOnCheckDeleteMasteraddrCur", method = RequestMethod.POST)
	public @ResponseBody List<Object> offBkgOnCheckDeleteMasteraddrCur(@RequestBody final Addresses addresses) {
		List<Object> dataObj = new ArrayList<Object>();
		try {
			dataObj = ocdaddreService.offBkgOnCheckDeleteMasteraddrCur(addresses);
		} catch (Exception e) {
			logger.error("In offBkgOnCheckDeleteMasteraddrCur method : ", e);
		}
		return dataObj;
	}

	/**
	 * method for query calling
	 * 
	 * @param addresses
	 * @return Object
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaddre/vAddOnCheckDeleteMasteraddrCur", method = RequestMethod.POST)
	public @ResponseBody Object vAddOnCheckDeleteMasteraddrCur(@RequestBody final Addresses addresses) {
		Object dataObj = null;
		try {
			dataObj = new Object();
			dataObj = ocdaddreService.vAddOnCheckDeleteMasteraddrCur(addresses);
		} catch (Exception e) {
			logger.error("In vAddOnCheckDeleteMasteraddrCur method : ", e);
		}
		return dataObj;
	}

	/**
	 * method for query calling
	 * 
	 * @param referenceCodes
	 * @return List<Object>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaddre/nbtCityKeyListvalgetCityDescription", method = RequestMethod.GET)
	public @ResponseBody List<Object> nbtCityKeyListvalgetCityDescription(
			@RequestBody final ReferenceCodes referenceCodes) {
		List<Object> dataObj = null;
		try {
			dataObj = new ArrayList<Object>();
			dataObj = ocdaddreService.nbtCityKeyListvalgetCityDescription(referenceCodes);
		} catch (Exception e) {
			logger.error("In nbtCityKeyListvalgetCityDescription method : ", e);
		}
		return dataObj;
	}

	/**
	 * method for query calling
	 * 
	 * @param phones
	 * @return List<Object>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaddre/addrOnCheckDeleteMasterphoneAddrCur", method = RequestMethod.POST)
	public @ResponseBody List<Object> addrOnCheckDeleteMasterphoneAddrCur(@RequestBody final Phones phones) {
		List<Object> dataObj = null;

		try {
			dataObj = new ArrayList<Object>();

			dataObj = ocdaddreService.addrOnCheckDeleteMasterphoneAddrCur(phones);
		} catch (Exception e) {
			logger.error("In addrOnCheckDeleteMasterphoneAddrCur method : ", e);
		}
		return dataObj;
	}

	/**
	 * method for query calling
	 * 
	 * @param addressUsages
	 * @return Object
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaddre/addrOnCheckDeleteMasteraddrUsageCur", method = RequestMethod.POST)
	public @ResponseBody Object addrOnCheckDeleteMasteraddrUsageCur(@RequestBody final AddressUsages addressUsages) {
		Object dataObj = null;
		try {
			dataObj = new Object();
			dataObj = ocdaddreService.addrOnCheckDeleteMasteraddrUsageCur(addressUsages);
		} catch (Exception e) {
			logger.error("In addrOnCheckDeleteMasteraddrUsageCur method : ", e);
		}
		return dataObj;
	}

	/**
	 * method for query calling
	 * 
	 * @param referenceCodes
	 * @return ReferenceCodes
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaddre/addrWhenCreateRecordgetCountryCur", method = RequestMethod.GET)
	public @ResponseBody List<ReferenceCodes> addrWhenCreateRecordgetCountryCur(
			@RequestBody final ReferenceCodes referenceCodes) {
		List<ReferenceCodes> listOfRecords = null;

		try {
			listOfRecords = ocdaddreService.addrWhenCreateRecordgetCountryCur(referenceCodes);
		} catch (Exception e) {
			logger.error("In addrWhenCreateRecordgetCountryCur method : ", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query calling
	 * 
	 * @param omsModules
	 * @return OmsModules
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaddre/createFormGlobals", method = RequestMethod.POST)
	public @ResponseBody OmsModules createFormGlobals(@RequestBody final OmsModules omsModules) {
		OmsModules dataObj = null;
		try {
			dataObj = new OmsModules();
			dataObj = ocdaddreService.createFormGlobals(omsModules);
		} catch (Exception e) {
			logger.error("In createFormGlobals method : ", e);
		}
		return dataObj;
	}

	/**
	 * method for query calling
	 * 
	 * @param addressUsages
	 * @return AddressUsages
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaddre/addressTypecheckActiveTypeCur", method = RequestMethod.POST)
	public @ResponseBody Object addressTypecheckActiveTypeCur(@RequestBody final AddressUsages addressUsages) {
		Object listOfRecords = null;
		try {
			listOfRecords = ocdaddreService.addressTypecheckActiveTypeCur(addressUsages);
		} catch (Exception e) {
			logger.error("In addressTypecheckActiveTypeCur method : ", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query calling
	 * 
	 * @param referenceCodes
	 * @return Object
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaddre/validateCityInfogetCityDescription", method = RequestMethod.GET)
	public @ResponseBody Object validateCityInfogetCityDescription(@RequestBody final ReferenceCodes referenceCodes) {
		Object dataObj = null;

		try {
			dataObj = new Object();
			dataObj = ocdaddreService.validateCityInfogetCityDescription(referenceCodes);
		} catch (Exception e) {
			logger.error("In validateCityInfogetCityDescription method : ", e);
		}
		return dataObj;
	}

	/**
	 * method for query calling
	 * 
	 * @param referenceCodes
	 * @return ReferenceCodes
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaddre/validateCityInfogetCityCode", method = RequestMethod.GET)
	public @ResponseBody List<ReferenceCodes> validateCityInfogetCityCode(
			@RequestBody final ReferenceCodes referenceCodes) {
		List<ReferenceCodes> listOfRecords = null;

		try {
			listOfRecords = ocdaddreService.validateCityInfogetCityCode(referenceCodes);
		} catch (Exception e) {
			logger.error("In validateCityInfogetCityCode method : ", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query calling
	 * 
	 * @param vAddresses
	 * @return Object
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaddre/checkHdcAddressActivecheckHdcAddressCur", method = RequestMethod.POST)
	public @ResponseBody Object checkHdcAddressActivecheckHdcAddressCur(@RequestBody final VAddresses vAddresses) {
		Object dataObj = null;
		try {
			dataObj = new Object();
			dataObj = ocdaddreService.checkHdcAddressActivecheckHdcAddressCur(vAddresses);
		} catch (Exception e) {
			logger.error("In checkHdcAddressActivecheckHdcAddressCur method : ", e);
		}
		return dataObj;
	}

	/**
	 * method for query calling
	 * 
	 * @param offenderCurfews
	 * @return List<OffenderCurfews>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaddre/checkHdcAddressActive", method = RequestMethod.POST)
	public @ResponseBody List<OffenderCurfews> checkHdcAddressActive(
			@RequestBody final OffenderCurfews offenderCurfews) {
		List<OffenderCurfews> dataObj = null;
		try {
			dataObj = new ArrayList<OffenderCurfews>();
			dataObj = ocdaddreService.checkHdcAddressActive(offenderCurfews);
		} catch (Exception e) {
			logger.error("In checkHdcAddressActive method : ", e);
		}
		return dataObj;
	}

	/**
	 * method for query calling
	 * 
	 * @param vAddresses
	 * @return Object
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaddre/checkHdcAddressExistcheckHdcAddressCur", method = RequestMethod.POST)
	public @ResponseBody Object checkHdcAddressExistcheckHdcAddressCur(@RequestBody final VAddresses vAddresses) {
		Object dataObj = null;
		try {
			dataObj = new Object();
			dataObj = ocdaddreService.checkHdcAddressExistcheckHdcAddressCur(vAddresses);
		} catch (Exception e) {
			logger.error("In checkHdcAddressExistcheckHdcAddressCur method : ", e);
		}
		return dataObj;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaddre/vAddressAndPhoneExecuteQuery", method = RequestMethod.POST)
	public @ResponseBody List<VAddresses> vAddressAndPhoneExecuteQuery(@RequestBody final VAddresses vAddresses) {
		List<VAddresses> resultData = Collections.emptyList();
		try {
			resultData = ocdaddreService.vAddressAndPhoneExecuteQuery(vAddresses);
		} catch (Exception e) {
			logger.error("In vAddressAndPhoneExecuteQuery method : ", e);
		}
		return resultData;
		
	}		
}