package net.syscon.s4.inst.demographicsbiometrics;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.OffenderEducations;
import net.syscon.s4.common.beans.OffenderEducationsCommitBean;
import net.syscon.s4.common.beans.OffenderEmployments;
import net.syscon.s4.common.beans.OffenderEmploymentsCommitBean;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VOffenderEducationAddresses;
import net.syscon.s4.common.beans.VOffenderEmployAddresses;

/**
 * Class OcdedempController
 */
@EliteController
public class OcdedempController {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdedempController.class.getName());
	
	@Autowired
	private OcdedempService ocdedempService;
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchBean
	 * @return List<OffenderEducations>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdedemp/offEducationsExecuteQuery", method = RequestMethod.POST)
	public List<OffenderEducations> offEducationsSearchOffenderEducations(@RequestBody final OffenderEducations searchBean) {
	    List<OffenderEducations> searchResult = new ArrayList<OffenderEducations>();
		final OffenderEducations bean = new OffenderEducations();
		try {		
			searchResult = ocdedempService.offEducationsSearchOffenderEducations(searchBean);
		} catch (Exception e) {
			logger.error("offEducationsExecuteQuery",e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update in the
	 * database
	 * 
	 * @Param commitBean
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdedemp/offEducationsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offEducationsCommit(@RequestBody final OffenderEducationsCommitBean commitBean) {
	 int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = ocdedempService.offEducationsCommit(commitBean);
			} catch (Exception e) {

			logger.error("offEducationsCommit",e);
		}
		return liReturn;
	}

	/**
	 * Getting eduSchedRg LOV values
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdedemp/eduSchedRgRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> eduSchedRgRgroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = ocdedempService.eduSchedRgRgroup();
		} catch (Exception e) {
			logger.error("eduSchedRgRecordGroup",e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * Getting payPeriodRg LOV values
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdedemp/payPeriodRgRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> payPeriodRgRgroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = ocdedempService.payPeriodRgRgroup();
		} catch (Exception e) {
			logger.error("payPeriodRgRecordGroup",e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * Getting occupationRg LOV values
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdedemp/occupationRgRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> occupationRgRgroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = ocdedempService.occupationRgRgroup();
		} catch (Exception e) {
			logger.error("occupationRgRecordGroup",e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * Getting scheduleTypeRg LOV values
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdedemp/scheduleTypeRgRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> scheduleTypeRgRgroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = ocdedempService.scheduleTypeRgRgroup();
		} catch (Exception e) {
			logger.error("scheduleTypeRgRecordGroup",e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * Getting employStsRg LOV values
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdedemp/employStsRgRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> employStsRgRgroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = ocdedempService.employStsRgRgroup();
		} catch (Exception e) {
			logger.error("employStsRgRecordGroup",e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * Getting studyAreaRg LOV values
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdedemp/studyAreaRgRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> studyAreaRgRgroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = ocdedempService.studyAreaRgRgroup();
		} catch (Exception e) {
			logger.error("studyAreaRgRecordGroup",e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * Getting eduLevelRg LOV values
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdedemp/eduLevelRgRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> eduLevelRgRgroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = ocdedempService.eduLevelRgRgroup();
		} catch (Exception e) {
			logger.error("eduLevelRgRecordGroup",e);
			obj.setErrorMessage(e.getMessage());			
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchBean
	 * @return List<VOffenderEducationAddresses>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdedemp/vOffEduAddrExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderEducationAddresses> vOffEduAddrSearchVOffenderEducationAddresses(
			@RequestBody final VOffenderEducationAddresses searchBean) {
		List<VOffenderEducationAddresses> searchResult = new ArrayList<>();
		final VOffenderEducationAddresses bean = new VOffenderEducationAddresses();
		try {
			searchResult = ocdedempService.vOffEduAddrSearchVOffenderEducationAddresses(searchBean);
		} catch (Exception e) {
			logger.error("vOffEduAddrExecuteQuery",e);
			bean.setErrorMessage(e.getMessage());			
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchBean
	 * @return List<OffenderEmployments>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdedemp/offEmploymentsExecuteQuery", method = RequestMethod.POST)
	public List<OffenderEmployments> offEmploymentsSearchOffenderEmployments(
			@RequestBody final OffenderEmployments searchBean) {
		List<OffenderEmployments> searchResult = new ArrayList<>();
		final OffenderEmployments bean = new OffenderEmployments();
		try {
			searchResult = ocdedempService.offEmploymentsSearchOffenderEmployments(searchBean);
		} catch (Exception e) {
			logger.error("offEmploymentsExecuteQuery",e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update in the
	 * database table
	 * 
	 * @Param commitBean
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdedemp/offEmploymentsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offEmploymentsCommit(@RequestBody final OffenderEmploymentsCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn=ocdedempService.offEmploymentsCommit(commitBean);
		} catch (Exception e) {

			logger.error("offEmploymentsCommit",e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchBean
	 * @return List<VOffenderEmployAddresses>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdedemp/vOffEmpAddrExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderEmployAddresses> vOffEmpAddrSearchVOffenderEmployAddresses(
			@RequestBody final  VOffenderEmployAddresses searchBean) {
		List<VOffenderEmployAddresses> searchResult = new ArrayList<>();
		final VOffenderEmployAddresses bean = new VOffenderEmployAddresses();
		try {
			searchResult = ocdedempService.vOffEmpAddrSearchVOffenderEmployAddresses(searchBean);
		} catch (Exception e) {
			logger.error("vOffEmpAddrExecuteQuery",e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * Method for query calling
	 * 
	 * @Param paramBean
	 * @return List<String>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdedemp/offbkgoncheckdeletemasteroffeducationscur", method = RequestMethod.POST)
	public @ResponseBody List<String> offBkgOnCheckDeleteMasteroffEducationsCur(
			@RequestBody final  OffenderEducations paramBean) {
		List<String> dataObj = new ArrayList<String>();
		try {			
			dataObj = ocdedempService.offBkgOnCheckDeleteMasteroffEducationsCur(paramBean);
		} catch (Exception e) {
			logger.error("offbkgoncheckdeletemasteroffeducationscur",e);
		}
		return dataObj;
	}

	/**
	 * Method for query calling
	 * 
	 * @Param paramBean
	 * @return List<String>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdedemp/offbkgoncheckdeletemasteroffemploymentscur", method = RequestMethod.POST)
	public @ResponseBody List<String> offBkgOnCheckDeleteMasteroffEmploymentsCur(
			@RequestBody final OffenderEmployments paramBean) {
		List<String> dataObj =new ArrayList<String>();
		try {
			dataObj = ocdedempService.offBkgOnCheckDeleteMasteroffEmploymentsCur(paramBean);
		} catch (Exception e) {
			logger.error("offbkgoncheckdeletemasteroffemploymentscur",e);
		}
		return dataObj;
	}

	/**
	 * Method for query calling
	 * 
	 * @Param paramBean
	 * @return List<String>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdedemp/offeducationsoncheckdeletemastervoffeduaddrcur", method = RequestMethod.POST)
	public @ResponseBody List<String> offEducationsOnCheckDeleteMastervOffEduAddrCur(
			@RequestBody final VOffenderEducationAddresses paramBean) {
		List<String> dataObj = new ArrayList<String>();
		try {
			dataObj = ocdedempService.offEducationsOnCheckDeleteMastervOffEduAddrCur(paramBean);
		} catch (Exception e) {
			logger.error("offeducationsoncheckdeletemastervoffeduaddrcur",e);
		}
		return dataObj;
	}

	/**
	 * Method for query calling
	 * 
	 * @Param paramBean
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdedemp/offeducationspreinsert", method = RequestMethod.POST)
	public @ResponseBody Integer offEducationsPreInsert(@RequestBody final OffenderEducations paramBean) {
		Integer listOfRecords = null;		
		try {			
			listOfRecords = ocdedempService.offEducationsPreInsert(paramBean);
		} catch (Exception e) {
			logger.error("offeducationspreinsert",e);
		}
		return listOfRecords;
	}

	/**
	 * Method for query calling
	 * 
	 * @Param paramBean
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdedemp/offeducationspredeleteaddress", method = RequestMethod.POST)
	public @ResponseBody Integer offEducationsPreDeleteAddress(@RequestBody final Addresses paramBean) {
		Integer listOfRecords = null;
		try {
			listOfRecords = ocdedempService.offEducationsPreDeleteAddress(paramBean);
		} catch (Exception e) {
			logger.error("offeducationspredeleteaddress",e);
		}
		return listOfRecords;
	}

	/**
	 * Method for query calling
	 * 
	 * @Param paramBean
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdedemp/offeducationspredeletephones", method = RequestMethod.POST)
	public @ResponseBody Integer offEducationsPreDeletePhones(@RequestBody final Phones paramBean) {
		Integer listOfRecords = null;
		try {
			listOfRecords = ocdedempService.offEducationsPreDeletePhones(paramBean);
		} catch (Exception e) {
			logger.error("offeducationspredeletephones",e);
		}
		return listOfRecords;
	}

	/**
	 * Method for query calling
	 * 
	 * @Param paramBean
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdedemp/offeducationspredeleteinternetaddress", method = RequestMethod.POST)
	public @ResponseBody Integer offEducationsPreDeleteInternetAddress(@RequestBody final InternetAddresses paramBean) {
		Integer listOfRecords = null;
		try {
			listOfRecords = ocdedempService.offEducationsPreDeleteInternetAddress(paramBean);
		} catch (Exception e) {
			logger.error("offeducationspredeleteinternetaddress",e);
		}
		return listOfRecords;
	}

	/**
	 * Method for query calling
	 * 
	 * @param paramBean
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdedemp/offemploymentspreinsert", method = RequestMethod.POST)
	public @ResponseBody Integer offEmploymentsPreInsert(@RequestBody final OffenderEmployments paramBean) {
		Integer listOfRecords = null;
		try {
			listOfRecords = ocdedempService.offEmploymentsPreInsert(paramBean);
		} catch (Exception e) {
			logger.error("offemploymentspreinsert",e);
		}
		return listOfRecords;
	}

	/**
	 * Method for query calling
	 * 
	 * @param paramBean
	 * @return List<String>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdedemp/offemploymentsoncheckdeletemastervoffempaddrcur", method = RequestMethod.POST)
	public @ResponseBody List<String> offEmploymentsOnCheckDeleteMastervOffEmpAddrCur(
			@RequestBody final VOffenderEmployAddresses paramBean) {
		List<String> dataObj = new ArrayList<String>();
		try {
			dataObj = ocdedempService.offEmploymentsOnCheckDeleteMastervOffEmpAddrCur(paramBean);
		} catch (Exception e) {
			logger.error("offemploymentsoncheckdeletemastervoffempaddrcur",e);
		}
		return dataObj;
	}

	/**
	 * Method for query calling
	 * 
	 * @param paramBean
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdedemp/offemploymentspredeleteaddress", method = RequestMethod.POST)
	public @ResponseBody Integer offEmploymentsPreDeleteAddress(@RequestBody final Addresses paramBean) {
		Integer listOfRecords = null;
		try {
			listOfRecords = ocdedempService.offEmploymentsPreDeleteAddress(paramBean);
		} catch (Exception e) {
			logger.error("offemploymentspredeleteaddress",e);
		}
		return listOfRecords;
	}

	/**
	 * Method for query calling
	 * 
	 * @param paramBean
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdedemp/offemploymentspredeletephones", method = RequestMethod.POST)
	public @ResponseBody Integer offEmploymentsPreDeletePhones(@RequestBody final Phones paramBean) {
		Integer listOfRecords = null;
		try {
			listOfRecords = ocdedempService.offEmploymentsPreDeletePhones(paramBean);
		} catch (Exception e) {
			logger.error("offemploymentspredeletephones",e);
		}
		return listOfRecords;
	}

	/**
	 * Method for query calling
	 * 
	 * @param paramBean
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdedemp/offemploymentspredeleteinternetaddress", method = RequestMethod.POST)
	public @ResponseBody Integer offEmploymentsPreDeleteInternetAddress(@RequestBody final InternetAddresses paramBean) {
		Integer listOfRecords = null;
		try {
			listOfRecords = ocdedempService.offEmploymentsPreDeleteInternetAddress(paramBean);
		} catch (Exception e) {
			logger.error("offemploymentspredeleteinternetaddress",e);
		}
		return listOfRecords;
	}

}
