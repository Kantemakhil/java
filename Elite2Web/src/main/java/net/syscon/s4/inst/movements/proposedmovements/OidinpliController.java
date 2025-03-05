package net.syscon.s4.inst.movements.proposedmovements;

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
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.movements.proposedmovements.beans.VHousingMoves;
import net.syscon.s4.inst.movements.proposedmovements.beans.VHousingMovesCommitBean;
import net.syscon.s4.inst.transportation.maintenance.OffenderLocChngDtls;

@EliteController
public class OidinpliController {

	@Autowired
	private OidinpliService oidinpliService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidinpliController.class.getName());

	/**
	 * getting rgLoc LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidinpli/rgLocRecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> rgLocRecordGroup(@RequestParam(value = "caseLoadId") final String caseLoadId) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = oidinpliService.rgLocRecordGroup(caseLoadId);
		} catch (Exception e) {
			LivingUnits obj = new LivingUnits();
			logger.error("Exception occured in " + this.getClass().getName() + " rgLocRecordGroup() ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgMoveType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidinpli/rgMoveTypeRecordGroup", method = RequestMethod.GET)
	public List<InternalScheduleReasons> rgMoveTypeRecordGroup() {
		List<InternalScheduleReasons> recordList = new ArrayList<InternalScheduleReasons>();
		try {
			recordList = oidinpliService.rgMoveTypeRecordGroup();
		} catch (Exception e) {
			InternalScheduleReasons obj = new InternalScheduleReasons();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	/**
	 * getting rgMoveType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidinpli/rgLocFromRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgLocFromRecordGroup(@RequestParam(value = "agyLocId") final String agyLocId) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidinpliService.rgLocFromRecordGroup(agyLocId);
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception in rgLocFromRecordGroup:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	/**
	 * getting rgMoveType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidinpli/rgLocToRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgLocToRecordGroup(@RequestParam(value = "fromAgyLocId") final String fromAgyLocId) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidinpliService.rgLocToRecordGroup(fromAgyLocId);
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception in rgLocToRecordGroup:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgMoveReason LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidinpli/rgMoveReasonRecordGroup", method = RequestMethod.GET)
	public List<InternalScheduleReasons> rgMoveReasonRecordGroup(
			@RequestParam("movementType") final String movementType) {
		List<InternalScheduleReasons> recordList = new ArrayList<InternalScheduleReasons>();
		try {
			recordList = oidinpliService.rgMoveReasonRecordGroup(movementType);
		} catch (Exception e) {
			InternalScheduleReasons obj = new InternalScheduleReasons();
			logger.error("Exception occured in " + this.getClass().getName() + " rgMoveReasonRecordGroup() ", e);
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
	@RequestMapping(value = "/oidinpli/extrMoveExecuteQuery", method = RequestMethod.POST)
	public List<VHousingMoves> extrMoveExecuteQuery(@RequestBody VHousingMoves searchBean) {
		List<VHousingMoves> searchResult = new ArrayList<>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchBean.setCreateUserId(userName);
			searchResult = oidinpliService.extrMoveExecuteQuery(searchBean);
		} catch (Exception e) {
			VHousingMoves bean = new VHousingMoves();
			logger.error("Exception occured in " + this.getClass().getName() + " extrMoveExecuteQuery() ", e);
			bean.setErrorMessage(e.getMessage());
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
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidinpli/inmaDetCommit", method = RequestMethod.POST)
	public @ResponseBody Integer inmaDetCommit(@RequestBody VHousingMoves commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setModifyUserId(userName);
			liReturn = oidinpliService.inmaDetCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception occured in " + this.getClass().getName() + " inmaDetCommit() ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidinpli/statDetExecuteQuery", method = RequestMethod.POST)
	public List<OffenderLocChngDtls> statDetExecuteQuery(@RequestBody OffenderLocChngDtls searchBean) {
		List<OffenderLocChngDtls> searchResult = new ArrayList<>();
		try {
			searchResult = oidinpliService.statDetExecuteQuery(searchBean);
		} catch (Exception e) {
			OffenderLocChngDtls bean = new OffenderLocChngDtls();
			logger.error("Exception occured in " + this.getClass().getName() + " statDetExecuteQuery() ", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into the
	 * database table
	 * 
	 * @Param bean
	 */

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidinpli/saveStatDetails", method = RequestMethod.POST)
	public Integer saveStatDet(@RequestBody OffenderLocChngDtls bean) {
		Integer liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			bean.setCreateUserId(userName);
			liReturn = oidinpliService.saveStatDet(bean);
		} catch (Exception e) {

			logger.error("Exception occured in " + this.getClass().getName() + " saveStatDetails() ", e);
		}
		return liReturn;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into the
	 * database table
	 * 
	 * @Param bean
	 */

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidinpli/transactCommitQuery", method = RequestMethod.POST)
	public Integer transactCommitQuery(@RequestBody VHousingMovesCommitBean commitBean) {
		Integer liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = oidinpliService.transactCommitQuery(commitBean);
		} catch (Exception e) {

			logger.error("Exception occured in " + this.getClass().getName() + " transactCommitQuery() ", e);
		}
		return liReturn;
	}
}