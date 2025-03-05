package net.syscon.s4.inmate.trust.financialsmaintenance.payees;

import java.math.BigDecimal;
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
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.im.beans.Addresses;
import net.syscon.s4.im.beans.AddressesCommitBean;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.CorporatesCommitBean;
import net.syscon.s4.im.beans.InternetAddressesCommitBean;
import net.syscon.s4.im.beans.PhonesCommitBean;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.schedules.bean.VCorporateAddresses;
import net.syscon.s4.pkgs.common.CommonService;

/**
 * Class OumagencController
 */
@EliteController
public class OumagencController {
	@Autowired
	private OumagencService oumagencService;
	
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumagencController.class.getName());

	/**
	 * Fetching the record from database table corpExecuteQuery
	 * 
	 * @Param searchRecord
	 * @return searchResult
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumagenc/corpExecuteQuery", method = RequestMethod.POST)
	public List<Corporates> corpExecuteQuery(@RequestBody final Corporates searchBean) {
		List<Corporates> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oumagencService.corpExecuteQuery(searchBean);
		} catch (Exception e) {
			Corporates bean = new Corporates();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table corpCommit
	 * 
	 * @Param commitBean
	 * @return liReturn
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumagenc/corpCommit", method = RequestMethod.POST)
	public String corpCommit(@RequestBody final CorporatesCommitBean commitBean) {
		String liReturn = "0";
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			liReturn = oumagencService.corpCommit(commitBean);

		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * getting rgCaseload LOV values rgCaseloadRecordGroup
	 * 
	 * @return recordList
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumagenc/rgCaseloadRecordGroup", method = RequestMethod.GET)
	public List<Caseloads> rgCaseloadRecordGroup() {
		List<Caseloads> recordList = new ArrayList<Caseloads>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oumagencService.rgCaseloadRecordGroup();
		} catch (Exception e) {
			Caseloads obj = new Caseloads();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgType LOV values rgTypeRecordGroup
	 * 
	 * @return recordList
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumagenc/rgTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oumagencService.rgTypeRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgIclass LOV values rgIclassRecordGroup
	 * 
	 * @return recordList
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumagenc/rgIclassRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgIclassRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oumagencService.rgIclassRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table addrExecuteQuery
	 * 
	 * @Param searchResult
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumagenc/addrExecuteQuery", method = RequestMethod.POST)
	public List<VCorporateAddresses> addrExecuteQuery(@RequestBody final VCorporateAddresses searchBean) {
		List<VCorporateAddresses> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oumagencService.addrExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table addPhExecuteQuery
	 * 
	 * @Param searchRecord
	 * @return searchResult
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumagenc/addPhExecuteQuery", method = RequestMethod.POST)
	public List<Phones> addPhExecuteQuery(@RequestBody final Phones searchBean) {
		List<Phones> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oumagencService.addPhExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table addPhCommit
	 * 
	 * @Param commitBean
	 * @return liReturn
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumagenc/addPhCommit", method = RequestMethod.POST)
	public Integer addPhCommit(@RequestBody final PhonesCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			liReturn = oumagencService.addPhCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumagenc/corPhoneExecuteQuery", method = RequestMethod.POST)
	public List<Phones> corPhoneExecuteQuery(@RequestBody final Phones searchBean) {
		List<Phones> searchResult = new ArrayList<Phones>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oumagencService.corPhoneExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table corPhoneCommit
	 * 
	 * @Param commitBean
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumagenc/corPhoneCommit", method = RequestMethod.POST)
	public Integer corPhoneCommit(@RequestBody final CorporatesCommitBean commitBean) {
		int liReturn = 0;
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			if(commitBean!=null) {
				commitBean.setCreateUserId(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			}
			liReturn = oumagencService.corPhoneCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table iAddExecuteQuery
	 * 
	 * @Param searchRecord
	 * @return searchResult
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumagenc/iAddExecuteQuery", method = RequestMethod.POST)
	public List<InternetAddresses> iAddExecuteQuery(@RequestBody final InternetAddresses searchBean) {
		List<InternetAddresses> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oumagencService.iAddExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table iAddCommit
	 * 
	 * @Param commitBean
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumagenc/iAddCommit", method = RequestMethod.POST)
	public Integer iAddCommit(@RequestBody final InternetAddressesCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			liReturn = oumagencService.iAddCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table addressesExecuteQuery
	 * 
	 * @Param searchRecord
	 * @return searchResult
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumagenc/addressesExecuteQuery", method = RequestMethod.POST)
	public List<Addresses> addressesExecuteQuery(@RequestBody final Addresses searchBean) {
		List<Addresses> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oumagencService.addressesExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table addressesCommit
	 * 
	 * @Param commitBean
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumagenc/addressesCommit", method = RequestMethod.POST)
	public Integer addressesCommit(@RequestBody final AddressesCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			liReturn = oumagencService.addressesCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumagenc/getCorporateChilds", method = RequestMethod.GET)
	public BigDecimal getCorporateChilds(@RequestParam(value = "corporateId") final BigDecimal corporateId) {
		BigDecimal liReturn = BigDecimal.ZERO;
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			liReturn = oumagencService.getCorporateChilds(corporateId);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OUMAGENC");
	}
}