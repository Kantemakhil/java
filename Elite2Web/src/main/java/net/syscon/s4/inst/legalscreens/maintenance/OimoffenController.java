package net.syscon.s4.inst.legalscreens.maintenance;

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
import net.syscon.s4.common.beans.Offence;
import net.syscon.s4.common.beans.OffenceIndicator;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.Statutes;
import net.syscon.s4.inst.legalscreens.maintenance.bean.AllowableOffenceTypes;
import net.syscon.s4.inst.legalscreens.maintenance.bean.AllowableOffenceTypesCommitBean;
import net.syscon.s4.inst.legalscreens.maintenance.bean.HoCodes;
import net.syscon.s4.inst.legalscreens.maintenance.bean.OffenceCommitBean;
import net.syscon.s4.inst.legalscreens.maintenance.bean.OffenceIndicatorsCommitBean;

/**
 * 
 * class OimoffenController
 * 
 */
@EliteController
public class OimoffenController {
	@Autowired
	private OimoffenService oimoffenService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimoffenController.class.getName());

	/**
	 * Fetching the record from database table
	 * @param searchBean {@link Statutes}
	 * @return a list of the Statutes {@link Statutes} for the matched statues
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoffen/statExecuteQuery", method = RequestMethod.POST)
	public List<Statutes> statExecuteQuery(@RequestBody final Statutes searchBean) {
		List<Statutes> searchResult = new ArrayList<>();
		try {
			searchResult = oimoffenService.statExecuteQuery(searchBean);
		} catch (Exception e) {
			Statutes bean = new Statutes();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * getting cgfkOfnSeverityRanking LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoffen/cgfkOfnSeverityRankingRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOfnSeverityRankingRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimoffenService.cgfkOfnSeverityRankingRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting ReferenceCodes LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoffen/cgfkAlwOtOffenceTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkAlwOtOffenceTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimoffenService.cgfkAlwOtOffenceTypeRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting offIndLov LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoffen/offIndLovRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> offIndLovRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimoffenService.offIndLovRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting ofnHoOffSubcl LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoffen/ofnHoOffSubclRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> ofnHoOffSubclRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimoffenService.ofnHoOffSubclRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting statStatutesCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoffen/statStatutesCodeRecordGroup", method = RequestMethod.GET)
	public List<Statutes> statStatutesCodeRecordGroup() {
		List<Statutes> recordList = new ArrayList<Statutes>();
		try {
			recordList = oimoffenService.statStatutesCodeRecordGroup();
		} catch (Exception e) {
			Statutes obj = new Statutes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgHoCode LOV values
	 */
	//@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoffen/rgHoCodeRecordGroup", method = RequestMethod.GET)
	public List<HoCodes> rgHoCodeRecordGroup() {
		List<HoCodes> recordList = new ArrayList<HoCodes>();
		try {
			recordList = oimoffenService.rgHoCodeRecordGroup();
		} catch (Exception e) {
			HoCodes obj = new HoCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * @param searchBean {@link String}
	 * @return a list of the Offence {@link Offence} for the matched Offence
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoffen/ofnExecuteQuery", method = RequestMethod.POST)
	public List<Offence> ofnExecuteQuery(@RequestBody final Offence searchBean) {
		List<Offence> searchResult = new ArrayList<>();
		try {

			searchResult = oimoffenService.ofnExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("ofnExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 *
	 * @param commitBean {@link OffenceCommitBean}
	 * @return a list of the OffenceCommitBean {@link OffenceCommitBean} for the matched offenceCommitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimoffen/ofnCommit", method = RequestMethod.POST)
	public @ResponseBody Offence ofnCommit(@RequestBody final OffenceCommitBean commitBean) {
		Offence liReturn = new Offence();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = oimoffenService.ofnCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link AllowableOffenceTypes}
	 * @return a list of the AllowableOffenceTypes {@link AllowableOffenceTypes} for the matched allowableOffenceTypes
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoffen/alwOtExecuteQuery", method = RequestMethod.POST)
	public List<AllowableOffenceTypes> alwOtExecuteQuery(@RequestBody final AllowableOffenceTypes searchBean) {
		List<AllowableOffenceTypes> searchResult = new ArrayList<>();
		try {
			searchResult = oimoffenService.alwOtExecuteQuery(searchBean);
		} catch (Exception e) {
			AllowableOffenceTypes bean = new AllowableOffenceTypes();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * @param commitBean {@link String}
	 * @return a list of the AllowableOffenceTypesCommitBean {@link AllowableOffenceTypesCommitBean} for the matched
	 * AllowableOffenceTypesCommitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimoffen/alwOtCommit", method = RequestMethod.POST)
	public @ResponseBody Integer alwOtCommit(@RequestBody final AllowableOffenceTypesCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = oimoffenService.alwOtCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link OffenceIndicator}
	 * @return a list of the OffenceIndicator {@link OffenceIndicator} for the matched OffenceIndicator
	 */
	//@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoffen/offIndExecuteQuery", method = RequestMethod.POST)
	public List<OffenceIndicator> offIndExecuteQuery(@RequestBody final OffenceIndicator searchBean) {
		List<OffenceIndicator> searchResult = new ArrayList<>();
		try {
			searchResult = oimoffenService.offIndExecuteQuery(searchBean);
		} catch (Exception e) {
			OffenceIndicator bean = new OffenceIndicator();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 *
	 * @param commitBean {@link OffenceIndicatorsCommitBean}
	 * @return a list of the OffenceIndicatorsCommitBean {@link OffenceIndicatorsCommitBean} for the matched OffenceIndicatorsCommitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimoffen/offIndCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offIndCommit(@RequestBody final OffenceIndicatorsCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = oimoffenService.offIndCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}
	
	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link Offence}
	 * @return a list of the Offence {@link Offence} for the matched Offence
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoffen/oimoffenStatOncheckdeletemasterOffences", method = RequestMethod.POST)
	public Offence oimoffenStatOncheckdeletemasterOffences(@RequestBody final Offence searchBean) {
		Offence searchResult = new Offence();
		try {

			searchResult = oimoffenService.oimoffenStatOncheckdeletemasterOffences(searchBean);
		} catch (Exception e) {
			logger.error("ofnExecuteQuery", e);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoffen/isChgDependOnOffences", method = RequestMethod.GET)
	public Boolean isChgDependOnOffences(@RequestParam final Integer offenceId) {
		Boolean isChargeLinked = false;
		try {
			isChargeLinked = oimoffenService.isChgDependOnOffences(offenceId);
		} catch (Exception e) {
			logger.error("isChgDependOnOffences", e);
		}
		return isChargeLinked;
	}
}