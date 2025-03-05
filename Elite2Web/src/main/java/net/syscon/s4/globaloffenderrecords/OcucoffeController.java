package net.syscon.s4.globaloffenderrecords;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderIdentifier;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.globaloffenderrecords.impl.OcucoffeServiceImpl;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffenderIdentifiersCommitBean;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OffendersCommitBean;
import net.syscon.s4.im.beans.OmsModules;

/**
 * class OcucoffeController
 * Create offender record.
 */
@EliteController
public class OcucoffeController {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcucoffeController.class.getName());

	@Autowired
	private OcucoffeServiceImpl ocucoffeService;

	/**
     * Returns a list of offender records from the database.
     * <p>The request includes search criteria for limiting the list of records returned.
     * The response includes a list of offenders who meet the search criteria, or null if
     * no offenders meet the search criteria.</p>
	 * 
	 * @param searchBean - The set of search criteria for an offender.
	 * @return searchResult - The set of offenders who meet the search criteria.
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucoffe/offExecuteQuery", method = RequestMethod.POST)
	public List<Offenders> offsearchOffenders(@RequestBody final Offenders searchBean) {
		List<Offenders> searchResult = new ArrayList<>();
		final Offenders bean = new Offenders();
		try {
			searchResult = ocucoffeService.offsearchOffenders(searchBean);
		} catch (Exception e) {
			logger.error("offExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
     * Insert a new offender record on the database.
     * <p>Insert a new offender record on the database. Returns an error code number
	 * if the insert fails.</p>
	 * 
	 * @param commitBean - The offender record to commit.
     * @return liReturn - The status code indicating success or failure.
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocucoffe/offCommit", method = RequestMethod.POST)
	public @ResponseBody Long offCommit(@RequestBody final OffendersCommitBean commitBean) {
		Long liReturn = 0L;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}

			liReturn = ocucoffeService.offInsertOffenders(commitBean);
		} catch (Exception e) {
			logger.error("offCommit", e);
		}
		return liReturn;
	}

	/**
	 * getting rgAliasNameType LOV values
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucoffe/rgAliasNameTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgAliasNameTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = ocucoffeService.rgAliasNameTypeRecordgroup();
		} catch (Exception e) {
			logger.error("rgAliasNameTypeRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);

		}
		return recordList;
	}

	/**
	 * getting rgIdentifierType LOV values
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucoffe/rgIdentifierTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgIdentifierTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = ocucoffeService.rgIdentifierTypeRecordGroup();
		} catch (Exception e) {
			logger.error("rgIdentifierTypeRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgOffSuffix LOV values
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucoffe/rgOffSuffixRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgOffSuffixRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = ocucoffeService.rgOffSuffixRecordGroup();
		} catch (Exception e) {
			logger.error("rgOffSuffixRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgOffSex LOV values
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucoffe/rgOffSexRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgOffSexRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = ocucoffeService.rgOffSexRecordGroup();
		} catch (Exception e) {
			logger.error("rgOffSexRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgOffRace LOV values
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucoffe/rgOffRaceRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgOffRaceRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = ocucoffeService.rgOffRaceRecordGroup();
		} catch (Exception e) {
			logger.error("rgOffRaceRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgIdSource LOV values
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucoffe/rgIdSourceRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgIdSourceRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = ocucoffeService.rgIdSourceRecordGroup();
		} catch (Exception e) {
			logger.error("rgIdSourceRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<Offenders>
	 * @param Offenders searchBean {@inheritDoc}
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucoffe/aliasExecuteQuery", method = RequestMethod.POST)
	public List<Offenders> aliasSearchOffenders(@RequestBody final Offenders searchBean) {
		List<Offenders> searchResult = new ArrayList<>();
		final Offenders bean = new Offenders();
		try {
			searchResult = ocucoffeService.aliasSearchOffenders(searchBean);
		} catch (Exception e) {
			logger.error("aliasExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @param OffendersCommitBean commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocucoffe/aliasCommit", method = RequestMethod.POST)
	public @ResponseBody Integer aliasCommit(@RequestBody final OffendersCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = ocucoffeService.insertUpdateDeleteOffenders(commitBean);
		} catch (Exception e) {
			logger.error("aliasCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<OffenderIdentifiers>
	 * @param OffenderIdentifiers searchBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucoffe/offIdExecuteQuery", method = RequestMethod.POST)
	public List<OffenderIdentifier> offIdSearchOffenderIdentifiers(@RequestBody final OffenderIdentifier searchBean) {
		List<OffenderIdentifier> searchResult = new ArrayList<>();
		final OffenderIdentifier bean = new OffenderIdentifier();
		try {
			searchResult = ocucoffeService.offIdSearchOffenderIdentifiers(searchBean);
		} catch (Exception e) {
			logger.error("offIdExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @param OffenderIdentifiersCommitBean commitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucoffe/offIdCommit", method = RequestMethod.POST)
	public @ResponseBody String offIdCommit(@RequestBody final OffenderIdentifiersCommitBean commitBean) {
		String liReturn = "0";
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocucoffeService.insertUpdateDeleteOffenderIdentifiers(commitBean);
		} catch (Exception e) {
			logger.error("offIdCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<OffenderIdentifiers>
	 * @param OffenderIdentifiers searchBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucoffe/offIdAllExecuteQuery", method = RequestMethod.POST)
	public List<OffenderIdentifier> offIdAllSearchOffenderIdentifiers(
			@RequestBody final OffenderIdentifier searchBean) {
		List<OffenderIdentifier> searchResult = new ArrayList<>();
		final OffenderIdentifier bean = new OffenderIdentifier();
		try {
			searchResult = ocucoffeService.offIdAllSearchOffenderIdentifiers(searchBean);
		} catch (Exception e) {
			logger.error("offIdAllExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<Object>
	 * @param OffenderIdentifiers
	 *            paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucoffe/offOnCheckDeleteMasteroffIdAllCur", method = RequestMethod.POST)
	public @ResponseBody List<Object> offoncheckdeletemasteroffidallcur(
			@RequestBody final OffenderIdentifier paramBean) {
		List<Object> dataObj = null;
		try {
			dataObj = ocucoffeService.offOnCheckDeleteMasteroffIdAllCur(paramBean);
		} catch (Exception e) {
			logger.error("offOnCheckDeleteMasteroffIdAllCur", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<Object>
	 * @param Offenders
	 *            paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucoffe/offOnCheckDeleteMasteraliasCur", method = RequestMethod.POST)
	public @ResponseBody List<Object> offoncheckdeletemasteraliascur(@RequestBody final Offenders paramBean) {
		List<Object> dataObj = null;
		try {
			dataObj = new ArrayList<Object>();
			dataObj = ocucoffeService.offOnCheckDeleteMasteraliasCur(paramBean);
		} catch (Exception e) {
			logger.error("offOnCheckDeleteMasteraliasCur", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<Object>
	 * @param OffenderIdentifiers
	 *            paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucoffe/aliasOnCheckDeleteMasteroffIdCur", method = RequestMethod.POST)
	public @ResponseBody List<Object> aliasoncheckdeletemasteroffidcur(
			@RequestBody final OffenderIdentifier paramBean) {
		List<Object> dataObj = null;
		try {
			dataObj = ocucoffeService.aliasOnCheckDeleteMasteroffIdCur(paramBean);
		} catch (Exception e) {
			logger.error("aliasOnCheckDeleteMasteroffIdCur", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @param ReferenceCodes
	 *            paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucoffe/whenNewRecordInstancedefaultSeqCur", method = RequestMethod.GET)
	public @ResponseBody ReferenceCodes whennewrecordinstancedefaultseqcur(
			@RequestBody final ReferenceCodes paramBean) {
		ReferenceCodes listOfRecords = null;
		try {
			listOfRecords = ocucoffeService.whenNewRecordInstancedefaultSeqCur(paramBean);
		} catch (Exception e) {
			logger.error("whenNewRecordInstancedefaultSeqCur", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 * 
	 * @param Dual
	 *            paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucoffe/whenNewFormInstance", method = RequestMethod.POST)
	public @ResponseBody Object whennewforminstance(@RequestBody final Dual paramBean) {
		Object dataObj = null;
		try {
			dataObj = new Object();
			dataObj = ocucoffeService.whenNewFormInstance(paramBean);
		} catch (Exception e) {
			logger.error("whenNewFormInstance", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @param ReferenceCodes
	 *            paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucoffe/postQueryreferenceCodesC", method = RequestMethod.POST)
	public @ResponseBody ReferenceCodes postQueryreferenceCodesC(@RequestBody final ReferenceCodes paramBean) {
		ReferenceCodes dataObj = null;
		try {
			dataObj = new ReferenceCodes();
			dataObj = ocucoffeService.postQueryreferenceCodesC(paramBean);
		} catch (Exception e) {
			logger.error("postQueryreferenceCodesC", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @param Offenders
	 *            paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucoffe/postInsertgetRootOffenderId", method = RequestMethod.POST)
	public @ResponseBody Offenders postInsertgetRootOffenderId(@RequestBody final Offenders paramBean) {
		Offenders dataObj = null;
		try {
			dataObj = ocucoffeService.postInsertgetRootOffenderId(paramBean);
		} catch (Exception e) {
			logger.error("postInsertgetRootOffenderId", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @param Dual
	 *            paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucoffe/preInsertgetNextAlias", method = RequestMethod.POST)
	public @ResponseBody Object preInsertgetNextAlias(@RequestBody final Dual paramBean) {
		Object dataObj = null;
		try {
			dataObj = ocucoffeService.preInsertgetNextAlias(paramBean);
		} catch (Exception e) {
			logger.error("preInsertgetNextAlias", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @param OffenderIdentifiers
	 *            paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucoffe/preInsertgetNextIdentifier", method = RequestMethod.POST)
	public @ResponseBody List<Object> preInsertgetNextIdentifier(@RequestBody final OffenderIdentifier paramBean) {
		List<Object> listOfRecords = null;
		try {
			listOfRecords = ocucoffeService.preInsertgetNextIdentifier(paramBean);
		} catch (Exception e) {
			logger.error("preInsertgetNextIdentifier", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<OffenderIdentifiers>
	 * @param OffenderIdentifiers
	 *            paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucoffe/preInsertrecordEx", method = RequestMethod.POST)
	public @ResponseBody List<Object> preinsertrecordex(@RequestBody final OffenderIdentifier paramBean) {
		List<Object> dataObj = null;
		try {
			dataObj = ocucoffeService.preInsertrecordEx(paramBean);
		} catch (Exception e) {
			logger.error("preInsertrecordEx", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @param OmsModules
	 *            paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucoffe/createFormGlobals", method = RequestMethod.POST)
	public @ResponseBody List<Object> createformglobals(@RequestBody final OmsModules paramBean) {
		List<Object> dataObj = null;
		try {
			dataObj = ocucoffeService.createFormGlobals(paramBean);
		} catch (Exception e) {
			logger.error("createFormGlobals", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @param SystemProfiles
	 *            paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucoffe/ageValidationvsRangecur", method = RequestMethod.GET)
	public @ResponseBody SystemProfiles agevalidationvsrangecur() {
		SystemProfiles listOfRecords = null;
		try {
			listOfRecords = ocucoffeService.ageValidationvsRangecur(null);
		} catch (Exception e) {
			logger.error("ageValidationvsRangecur", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 * 
	 * @param Dual
	 *            paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucoffe/ageValidationvsAgecur", method = RequestMethod.POST)
	public @ResponseBody Object agevalidationvsagecur(@RequestBody final Offenders paramBean) {
		Object listOfRecords = null;
		try {
			listOfRecords = ocucoffeService.ageValidationvsAgecur(paramBean.getBirthDate());
		} catch (Exception e) {
			logger.error("ageValidationvsAgecur", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 * 
	 * @param Offenders
	 *            paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucoffe/validateAliasescheckForDupOffCur", method = RequestMethod.GET)
	public @ResponseBody Object validatealiasescheckfordupoffcur(@RequestBody final Offenders paramBean) {
		Object listOfRecords = null;
		try {
			listOfRecords = ocucoffeService.validateAliasescheckForDupOffCur(paramBean);
		} catch (Exception e) {
			logger.error("validateAliasescheckForDupOffCur", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 * 
	 * @param Offenders
	 *            paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucoffe/validateAliasescheckDupNameCur", method = RequestMethod.POST)
	public @ResponseBody Object validatealiasescheckdupnamecur(@RequestBody final Offenders paramBean) {
		Object listOfRecords = new Object();
		try {
			listOfRecords = ocucoffeService.validateAliasescheckDupNameCur(paramBean);
		} catch (Exception e) {
			logger.error("validateAliasescheckDupNameCur", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<OffenderIdentifiers>
	 * @param OffenderIdentifiers
	 *            paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucoffe/checkPncExistsgetPncEx", method = RequestMethod.POST)
	public @ResponseBody List<Object> checkpncexistsgetpncex(@RequestBody final OffenderIdentifier paramBean) {
		List<Object> listOfRecords = null;
		OffenderIdentifier bean = null;
		try {
			bean = new OffenderIdentifier();
			listOfRecords = ocucoffeService.checkPncExistsgetPncEx(paramBean);
		} catch (Exception e) {
			logger.error("checkPncExistsgetPncEx", e);
			bean.setErrorMessage(e.getMessage());
			listOfRecords.add(bean);

		}
		return listOfRecords;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucoffe/getOffenderMinAge", method = RequestMethod.GET)
	public Integer getOffenderMinAge(@RequestParam(value = "caseload") final String caseload) {
		Integer value = null;
		try {
			value = ocucoffeService.getOffenderMinAge(caseload);
		} catch (Exception e) {
			logger.error("getOffenderMinAge", e);
		}
		return value;
	}

	// checkOffenderIdDisplay
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucoffe/checkOffenderIdDisplay", method = RequestMethod.GET)
	public String checkOffenderIdDisplay(@RequestParam(value = "offenderIdDisplay") final String offenderIdDisplay) {
		String value = null;
		try {
			value = ocucoffeService.checkOffenderIdDisplay(offenderIdDisplay);
		} catch (Exception e) {
			logger.error("checkOffenderIdDisplay", e);
		}
		return value;
	}
	
	@RequestMapping(value = "/ocucoffe/ocucoffeGetCurrentDate", method = RequestMethod.GET)
	public Date ocucoffeGetCurrentDate() {
		Date value = null;
		try {
			value = ocucoffeService.ocucoffeGetCurrentDate();
		} 
		catch (Exception e) {
			logger.error("ocucoffeGetCurrentDate",e);
		}
		return value;
	}
}