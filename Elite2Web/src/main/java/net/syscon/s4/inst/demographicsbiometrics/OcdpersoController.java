package net.syscon.s4.inst.demographicsbiometrics;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.booking.beans.OffenderContactPersons;
import net.syscon.s4.inst.booking.beans.OffenderContactPersonsCommitBean;
import net.syscon.s4.inst.booking.beans.PersonEmployments;
import net.syscon.s4.inst.booking.beans.PersonEmploymentsCommitBean;
import net.syscon.s4.inst.booking.beans.PersonIdentifiers;
import net.syscon.s4.inst.booking.beans.PersonIdentifiersCommitBean;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.booking.beans.PersonsCommitBean;
import net.syscon.s4.inst.booking.beans.VPersonAddress;

/**
 * class OcdpersoController
 */
@EliteController
public class OcdpersoController {
	@Autowired
	private OcdpersoService ocdpersoService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdpersoController.class.getName());

	/**
	 * getting rgContactType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdperso/rgContactTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgContactTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdpersoService.rgContactTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgContactTypeRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgRelType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdperso/rgRelTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgRelTypeRecordGroup(@RequestParam(value = "contactType") final String contactType) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdpersoService.rgRelTypeRecordGroup(contactType);
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgRelTypeRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgLanguageCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdperso/rgLanguageCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgLanguageCodeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdpersoService.rgLanguageCodeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgLanguageCodeRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgMaritalStatus LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdperso/rgMaritalStatusRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgMaritalStatusRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdpersoService.rgMaritalStatusRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgMaritalStatusRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgSexCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdperso/rgSexCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSexCodeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdpersoService.rgSexCodeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgSexCodeRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgSearchType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdperso/rgSearchTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSearchTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdpersoService.rgSearchTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgSearchTypeRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgIdentifierType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdperso/rgIdentifierTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgIdentifierTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdpersoService.rgIdentifierTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgIdentifierTypeRecordGroup: ", e);
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
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdperso/offCntPerExecuteQuery", method = RequestMethod.POST)
	public List<OffenderContactPersons> offCntPerExecuteQuery(@RequestBody final OffenderContactPersons searchBean) {
		List<OffenderContactPersons> searchResult = new ArrayList<>();
		try {
			searchResult = ocdpersoService.offCntPerExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderContactPersons bean = new OffenderContactPersons();
			logger.error("offCntPerExecuteQuery: ", e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdperso/offCntPerCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offCntPerCommit(@RequestBody final OffenderContactPersonsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocdpersoService.offCntPerCommit(commitBean);
		} catch (Exception e) {
			logger.error("offCntPerCommit: ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdperso/perAddrExecuteQuery", method = RequestMethod.POST)
	public List<VPersonAddress> perAddrExecuteQuery(@RequestBody final VPersonAddress searchBean) {
		List<VPersonAddress> searchResult = new ArrayList<>();
		try {
			searchResult = ocdpersoService.perAddrExecuteQuery(searchBean);
		} catch (Exception e) {
			final VPersonAddress bean = new VPersonAddress();
			logger.error("perAddrExecuteQuery: ", e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdperso/perIdentExecuteQuery", method = RequestMethod.POST)
	public List<PersonIdentifiers> perIdentExecuteQuery(@RequestBody final PersonIdentifiers searchBean) {
		List<PersonIdentifiers> searchResult = new ArrayList<>();
		try {
			searchResult = ocdpersoService.perIdentExecuteQuery(searchBean);
		} catch (Exception e) {
			final PersonIdentifiers bean = new PersonIdentifiers();
			logger.error("perIdentExecuteQuery: ", e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdperso/perIdentCommit", method = RequestMethod.POST)
	public @ResponseBody Integer perIdentCommit(@RequestBody final PersonIdentifiersCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocdpersoService.perIdentCommit(commitBean);
		} catch (Exception e) {
			logger.error("perIdentCommit: ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdperso/perInfoExecuteQuery", method = RequestMethod.POST)
	public List<Persons> perInfoExecuteQuery(@RequestBody final Persons searchBean) {
		List<Persons> searchResult = new ArrayList<>();
		try {
			searchResult = ocdpersoService.perInfoExecuteQuery(searchBean);
		} catch (Exception e) {
			final Persons bean = new Persons();
			logger.error("perInfoExecuteQuery: ", e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdperso/perInfoCommit", method = RequestMethod.POST)
	public @ResponseBody Integer perInfoCommit(@RequestBody final PersonsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocdpersoService.perInfoCommit(commitBean);
		} catch (Exception e) {
			logger.error("perInfoCommit: ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdperso/perEmpExecuteQuery", method = RequestMethod.POST)
	public List<PersonEmployments> perEmpExecuteQuery(@RequestBody final PersonEmployments searchBean) {
		List<PersonEmployments> searchResult = new ArrayList<>();
		try {
			searchResult = ocdpersoService.perEmpExecuteQuery(searchBean);
		} catch (Exception e) {
			final PersonEmployments bean = new PersonEmployments();
			logger.error("perEmpExecuteQuery: ", e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * basic insert, delete, update int the database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdperso/perEmpCommit", method = RequestMethod.POST)
	public @ResponseBody Integer perEmpCommit(@RequestBody final PersonEmploymentsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocdpersoService.perEmpCommit(commitBean);
		} catch (Exception e) {
			logger.error("perEmpCommit: ", e);
		}
		return liReturn;
	}

	/**
	 * basic insert, delete, update int the database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdperso/copyOffAddr", method = RequestMethod.GET)
	public @ResponseBody Integer copyOffAddr(@RequestParam(value = "rootOffId") final Integer rootOffId,
			@RequestParam(value = "personId") final Integer personId) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			liReturn = ocdpersoService.copyOffAddress(new BigDecimal(rootOffId), Long.valueOf(personId.toString()), userName);
		} catch (Exception e) {
			logger.error("copyOffAddr: ", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdperso/checkChildRecords", method = RequestMethod.GET)
	public @ResponseBody Integer checkChildRecords(@RequestParam(value = "offenderBookId") final Long offenderBookId,
			@RequestParam(value = "personId") final Integer personId) {
		int liReturn = 0;
		try {
			liReturn = ocdpersoService.checkChildRecords(offenderBookId, personId);
		} catch (Exception e) {
			logger.error("checkChildRecords: ", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdperso/checkFutureVisits", method = RequestMethod.GET)
	public @ResponseBody Integer checkFutureVisits(@RequestParam(value = "offenderBookId") final Long offenderBookId,
			@RequestParam(value = "personId") final Integer personId) {
		int liReturn = 0;
		try {
			liReturn = ocdpersoService.checkFutureVisits(offenderBookId, personId);
		} catch (Exception e) {
			logger.error("checkChildRecords: ", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdperso/cancelFutureVisits", method = RequestMethod.GET)
	public @ResponseBody void cancelFutureVisits(@RequestParam(value = "offenderBookId") final Long offenderBookId,
			@RequestParam(value = "personId") final Integer personId) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			ocdpersoService.cancelFutureVisit(offenderBookId, personId, userName);
		} catch (Exception e) {
			logger.error("checkChildRecords: ", e);
		}
	}

}