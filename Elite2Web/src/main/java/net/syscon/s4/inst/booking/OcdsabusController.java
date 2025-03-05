package net.syscon.s4.inst.booking;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.booking.beans.OffenderSubstanceDetails;
import net.syscon.s4.inst.booking.beans.OffenderSubstanceDetailsCommitBean;
import net.syscon.s4.inst.booking.beans.OffenderSubstanceTreatments;
import net.syscon.s4.inst.booking.beans.OffenderSubstanceTreatmentsCommitBean;
import net.syscon.s4.inst.booking.beans.OffenderSubstanceUses;
import net.syscon.s4.inst.booking.beans.OffenderSubstanceUsesCommitBean;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * Class OcdsabusController
 */
@EliteController
public class OcdsabusController {
	@Autowired
	private OcdsabusService ocdsabusService;
	
	@Autowired
	private ProsmainService prosmainService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdsabusController.class.getName());

	/**
	 * getting age LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdsabus/ageRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> ageRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdsabusService.ageRecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Ocdsabus:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting lSourceInfo LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdsabus/lSourceInfoRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> lSourceInfoRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdsabusService.lSourceInfoRecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Ocdsabus:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffSuDspDescription LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdsabus/cgfkOffSuDspDescriptionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffSuDspDescriptionRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdsabusService.cgfkOffSuDspDescriptionRecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Ocdsabus:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffStDspDescription3 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdsabus/cgfkOffStDspDescription3RecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffStDspDescription3RecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdsabusService.cgfkOffStDspDescription3RecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Ocdsabus:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffStDspDescription LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdsabus/cgfkOffStDspDescriptionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffStDspDescriptionRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdsabusService.cgfkOffStDspDescriptionRecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Ocdsabus:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffSdDspDescription LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdsabus/cgfkOffSdDspDescriptionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffSdDspDescriptionRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdsabusService.cgfkOffSdDspDescriptionRecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Ocdsabus:", e);
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
	@RequestMapping(value = "/ocdsabus/offSuExecuteQuery", method = RequestMethod.POST)
	public List<OffenderSubstanceUses> offSuExecuteQuery(@RequestBody final OffenderSubstanceUses searchBean) {
		List<OffenderSubstanceUses> searchResult = new ArrayList<>();
		try {
			searchResult = ocdsabusService.offSuExecuteQuery(searchBean);
		} catch (final Exception e) {
			final OffenderSubstanceUses bean = new OffenderSubstanceUses();
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
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdsabus/offSuCommit", method = RequestMethod.POST)
	public @ResponseBody OffenderSubstanceUses offSuCommit(
			@RequestBody final OffenderSubstanceUsesCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		OffenderSubstanceUses returnObj = new OffenderSubstanceUses();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			returnObj = ocdsabusService.offSuCommit(commitBean);
			if("1".equals(returnObj.getSealFlag())) {
				prosmainService.enableTriggers(commitBean, authorization, "97");
			}
		} catch (final Exception e) {
			final String errorMsg = "Error : " + e.getMessage();
			returnObj.setErrorMessage(errorMsg.toUpperCase());
			logger.error("offSuCommit :", e);
		}
		return returnObj;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdsabus/offSdExecuteQuery", method = RequestMethod.POST)
	public List<OffenderSubstanceDetails> offSdExecuteQuery(@RequestBody final OffenderSubstanceDetails searchBean) {
		List<OffenderSubstanceDetails> searchResult = new ArrayList<>();
		try {
			searchResult = ocdsabusService.offSdExecuteQuery(searchBean);
		} catch (final Exception e) {
			final OffenderSubstanceDetails bean = new OffenderSubstanceDetails();
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
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdsabus/offSdCommit", method = RequestMethod.POST)
	public @ResponseBody OffenderSubstanceDetails offSdCommit(
			@RequestBody final OffenderSubstanceDetailsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		OffenderSubstanceDetails returnObj = new OffenderSubstanceDetails();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			returnObj = ocdsabusService.offSdCommit(commitBean);
			if("1".equals(returnObj.getSealFlag())) {
				prosmainService.enableTriggers(commitBean, authorization, "98");
			}
		} catch (final Exception e) {

			final String errorMsg = "Error : " + e.getMessage();
			returnObj.setErrorMessage(errorMsg.toUpperCase());
			logger.error("offSuCommit :", e);
		}
		return returnObj;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdsabus/offStExecuteQuery", method = RequestMethod.POST)
	public List<OffenderSubstanceTreatments> offStExecuteQuery(
			@RequestBody final OffenderSubstanceTreatments searchBean) {
		List<OffenderSubstanceTreatments> searchResult = new ArrayList<>();
		try {
			searchResult = ocdsabusService.offStExecuteQuery(searchBean);
		} catch (final Exception e) {
			final OffenderSubstanceTreatments bean = new OffenderSubstanceTreatments();
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
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdsabus/offStCommit", method = RequestMethod.POST)
	public @ResponseBody OffenderSubstanceTreatments offStCommit(
			@RequestBody final OffenderSubstanceTreatmentsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		OffenderSubstanceTreatments returnObj = new OffenderSubstanceTreatments();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			returnObj = ocdsabusService.offStCommit(commitBean);
			if("1".equals(returnObj.getSealFlag())) {
				prosmainService.enableTriggers(commitBean, authorization, "99");
			}
		} catch (final Exception e) {
			final String errorMsg = "Error : " + e.getMessage();
			returnObj.setErrorMessage(errorMsg.toUpperCase());
			logger.error("offStCommit :", e);
		}
		return returnObj;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdsabus/onDeleteOfSuAbHistory", method = RequestMethod.POST)
	public @ResponseBody OffenderSubstanceUses onDeleteOfSuAbHistory(
			@RequestBody final OffenderSubstanceUses obj) {
		OffenderSubstanceUses returnObj = new OffenderSubstanceUses();
		try {
			returnObj = ocdsabusService.onDeleteOfSuAbHistory(obj);
		} catch (final Exception e) {

			final String errorMsg = "Error : " + e.getMessage();
			returnObj.setErrorMessage(errorMsg);
			logger.error("onDeleteOfSuAbHistory :", e);
		}
		return returnObj;
	}

}