package net.syscon.s4.inst.movements.housingchanges;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.BedAssignmentHistoriesCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.movements.housingchanges.beans.CourtMovementTmp;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

@EliteController
public class OidcholoController {
	@Autowired
	private OidcholoService oidcholoService;
	
	@Autowired
	private ProsmainService prosmainService;
	
	@Autowired
	private OidchlocService oidchlocService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidcholoController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcholo/crtMvTmpExecuteQuery", method = RequestMethod.POST)
	public List<VHeaderBlock> crtMvTmpExecuteQuery(@RequestBody final CourtMovementTmp searchBean) {
		List<VHeaderBlock> searchResult = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		searchBean.setCreateUserId(userName);
		try {
			searchResult = oidcholoService.crtMvTmpExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("crtMvTmpExecuteQuery", e);
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
	@RequestMapping(value = "/oidcholo/crtMvTmpCommit", method = RequestMethod.POST)
	public @ResponseBody Integer crtMvTmpCommit(@RequestBody final BedAssignmentHistoriesCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			
			liReturn = oidcholoService.crtMvTmpCommit(commitBean);
			
			if(1 == liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "75");
			}
		} catch (Exception e) {

			logger.error("crtMvTmpCommit ", e);
		}
		return liReturn;
	}

	/**
	 * getting cgfkCrtMvTmpMovementReaso LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcholo/cgfkCrtMvTmpMovementReasoRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkCrtMvTmpMovementReasoRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidcholoService.cgfkCrtMvTmpMovementReasoRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("cgfkCrtMvTmpMovementReasoRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkBedAhDspDescription LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcholo/cgfkBedAhDspDescriptionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkBedAhDspDescriptionRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidcholoService.cgfkBedAhDspDescriptionRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("cgfkBedAhDspDescriptionRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkBedAhDspOffenderIdDi LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcholo/cgfkBedAhDspOffenderIdDiRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkBedAhDspOffenderIdDiRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			recordList = oidcholoService.cgfkBedAhDspOffenderIdDiRecordGroup(userName);
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("cgfkBedAhDspOffenderIdDiRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkCrtMvTmpDspLiving4 LOV values
	 */
	@RequestMapping(value = "/oidcholo/cgfkCrtMvTmpDspLiving4RecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> cgfkCrtMvTmpDspLiving4RecordGroup(
			@RequestParam(value = "agyLocId") final String agyLocId) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = oidcholoService.cgfkCrtMvTmpDspLiving4RecordGroup(agyLocId);
		} catch (Exception e) {
			logger.error("cgfkCrtMvTmpDspLiving4RecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkCrtMvTmpDspLiving3 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcholo/cgfkCrtMvTmpDspLiving3RecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> cgfkCrtMvTmpDspLiving3RecordGroup(@RequestParam(value = "agyLocId") final String agyLocId,
			@RequestParam(value = "livingUnitId") final String livingUnitId) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = oidcholoService.cgfkCrtMvTmpDspLiving3RecordGroup(agyLocId, livingUnitId);
		} catch (Exception e) {
			logger.error("cgfkCrtMvTmpDspLiving3RecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkCrtMvTmpDspLiving2 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcholo/cgfkCrtMvTmpDspLiving2RecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkCrtMvTmpDspLiving2RecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidcholoService.cgfkCrtMvTmpDspLiving2RecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("cgfkCrtMvTmpDspLiving2RecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkCrtMvTmpDspLivingUni LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcholo/cgfkCrtMvTmpDspLivingUniRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkCrtMvTmpDspLivingUniRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidcholoService.cgfkCrtMvTmpDspLivingUniRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("cgfkCrtMvTmpDspLivingUniRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkCrtMvTmpAgyLocId LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidcholo/cgfkcrtmvtmpagylocidrecordgroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkCrtMvTmpAgyLocIdRecordGroup(
			@RequestParam(value = "caseload") final String caseload) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidcholoService.cgfkCrtMvTmpAgyLocIdRecordGroup(caseload);
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("cgfkCrtMvTmpAgyLocIdRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	/**
	 * getting cgfkCrtMvTmpAgyLocId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcholo/oidcholoCgfklkpBedAhBedDatetimeProc", method = RequestMethod.GET)
	public List<VHeaderBlock> oidcholoCgfklkpBedAhBedDatetimeProc(
			@RequestParam(value = "livingUnitId") final Integer livingUnitId,
			@RequestParam(value = "offenderBookId") final Integer offenderBookId) {
		List<VHeaderBlock> recordList = new ArrayList<VHeaderBlock>();
		try {
			recordList = oidcholoService.oidcholoCgfklkpBedAhBedDatetimeProc(livingUnitId,offenderBookId);
		} catch (Exception e) {
			final VHeaderBlock obj = new VHeaderBlock();
			logger.error("oidcholoCgfklkpBedAhBedDatetimeProc", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcholo/checkNonIndGangConficts", method = RequestMethod.POST)
	public List<BedAssignmentHistories> checkNonIndGangConficts(@RequestBody final List<BedAssignmentHistories> searchList) {
		List<BedAssignmentHistories> searchResult = new ArrayList<>();
		try {
			searchResult = oidchlocService.checkNonIndGangConficts(searchList);
		} catch (Exception e) {
			logger.error("checkAllConficts", e);
		}
		return searchResult;
	}

}