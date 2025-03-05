package net.syscon.s4.inst.movements.proposedmovements;

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
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.movements.proposedmovements.beans.OffenderProposedIntlocs;
import net.syscon.s4.inst.movements.proposedmovements.beans.OffenderProposedIntlocsCommitBean;
import net.syscon.s4.inst.transportation.maintenance.OffenderLocChngDtls;

@EliteController
public class OidphuncController {

	@Autowired
	private OidphuncService oidphuncService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidphuncController.class.getName());

	/**
	 * getting rgFromLivUnit LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidphunc/rgFromLivUnitRecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> rgFromLivUnitRecordGroup() {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = oidphuncService.rgFromLivUnitRecordGroup();
		} catch (Exception e) {
			LivingUnits obj = new LivingUnits();
			logger.error(this.getClass().getName() + "In method rgFromLivUnitRecordGroup error", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgLevel1 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidphunc/rgLevelOneRecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> rgLevelOneRecordGroup(@RequestParam(value = "agyLocId") final String agyLocId) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = oidphuncService.rgLevelOneRecordGroup(agyLocId);
		} catch (Exception e) {
			LivingUnits obj = new LivingUnits();
			logger.error(this.getClass().getName() + "In method rgLevelOneRecordGroup error", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgLevel2 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidphunc/rgLevelTwoRecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> rgLevelTwoRecordGroup(
			@RequestParam(value = "livingUnitId") final BigDecimal livingUnitId) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = oidphuncService.rgLevelTwoRecordGroup(livingUnitId);
		} catch (Exception e) {
			LivingUnits obj = new LivingUnits();
			logger.error(this.getClass().getName() + "In method rgLevelTwoRecordGroup error", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgLevel3 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidphunc/rgLevelThreeRecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> rgLevelThreeRecordGroup(
			@RequestParam(value = "livingUnitId") final BigDecimal livingUnitId) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = oidphuncService.rgLevelThreeRecordGroup(livingUnitId);
		} catch (Exception e) {
			LivingUnits obj = new LivingUnits();
			logger.error(this.getClass().getName() + "In method rgLevelThreeRecordGroup error", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgLevel4 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidphunc/rgLevelFourRecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> rgLevelFourRecordGroup(
			@RequestParam(value = "livingUnitId") final BigDecimal livingUnitId) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = oidphuncService.rgLevelFourRecordGroup(livingUnitId);
		} catch (Exception e) {
			LivingUnits obj = new LivingUnits();
			logger.error(this.getClass().getName() + "In method rgLevelFourRecordGroup error", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidphunc/rgTypeRecordGroup", method = RequestMethod.GET)
	public List<InternalScheduleReasons> rgTypeLivUnitRecordGroup() {
		List<InternalScheduleReasons> recordList = new ArrayList<InternalScheduleReasons>();
		try {
			recordList = oidphuncService.rgTypeLivUnitRecordGroup();
		} catch (Exception e) {
			InternalScheduleReasons obj = new InternalScheduleReasons();
			logger.error(this.getClass().getName() + "In method rgTypeRecordGroup error", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgReason LOV values
	 * 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidphunc/rgReasonRecordGroup", method = RequestMethod.GET)
	public List<InternalScheduleReasons> rgReasonLivUnitRecordGroup(
			@RequestParam(value = "internalScheduleType") final String internalScheduleType) {
		List<InternalScheduleReasons> recordList = new ArrayList<InternalScheduleReasons>();
		try {
			recordList = oidphuncService.rgReasonLivUnitRecordGroup(internalScheduleType);
		} catch (Exception e) {
			InternalScheduleReasons obj = new InternalScheduleReasons();
			logger.error(this.getClass().getName() + "In method rgReasonRecordGroup error", e);
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
	@RequestMapping(value = "/oidphunc/propMoveExecuteQuery", method = RequestMethod.POST)
	public List<OffenderProposedIntlocs> propMoveExecuteQuery(@RequestBody OffenderProposedIntlocs searchBean) {
		List<OffenderProposedIntlocs> searchResult = new ArrayList<>();
		try {
			searchResult = oidphuncService.propMoveExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "In method propMoveExecuteQuery error", e);
		}
		return searchResult;
	}

	/**
	 * Performing insert,delete, update into the database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidphunc/propMoveCommit", method = RequestMethod.POST)
	public @ResponseBody Integer propMoveCommit(@RequestBody OffenderProposedIntlocsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oidphuncService.propMoveCommit(commitBean);
		} catch (Exception e) {

			logger.error(this.getClass().getName() + "In method propMoveCommit error", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidphunc/offenderLocChngDtlsExecuteQuery", method = RequestMethod.POST)
	public OffenderLocChngDtls offenderLocChngDtlsExecuteQuery(@RequestBody OffenderProposedIntlocs searchBean) {
		OffenderLocChngDtls searchResult = new OffenderLocChngDtls();
		try {
			searchResult = oidphuncService.getStatuses(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "In method offenderLocChngDtlsExecuteQuery error", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidphunc/checkNonAssociationAndSecurity", method = RequestMethod.POST)
	public OffenderProposedIntlocs checkNonAssociationAndSecurity(@RequestBody List<OffenderProposedIntlocs> listObj) {
		OffenderProposedIntlocs searchResult = new OffenderProposedIntlocs();
		try {
			searchResult = oidphuncService.checkNonAssociationAndSecurity(listObj);
		} catch (Exception e) {
			OffenderProposedIntlocs bean = new OffenderProposedIntlocs();
			logger.error(this.getClass().getName() + "In method checkNonAssociationAndSecurity error", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}
}