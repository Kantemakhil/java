package net.syscon.s4.inst.movementexternal;

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
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.beans.VNameSearch;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.inst.movementexternal.beans.VHeaderBlockCommitBean;

/**
 * class OidbutabController
 */
@EliteController
public class OidbutabController {
	@Autowired
	private OidbutabService oidbutabService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidbutabController.class.getName());

	/**
	 * getting rgInstitution LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidbutab/rgInstitutionRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgInstitutionRecordGroup(@RequestParam(value = "caseloadId") final String caseloadId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		final AgencyLocations obj = new AgencyLocations();
		try {
			recordList = oidbutabService.rgInstitutionRecordGroup(caseloadId);
		} catch (Exception e) {
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgActiveAgency LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidbutab/rgActiveAgencyRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgActiveAgencyRecordGroup(@RequestParam(value = "agylocId") final String agyLocId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		final AgencyLocations obj = new AgencyLocations();
		try {
			recordList = oidbutabService.rgActiveAgencyRecordGroup(agyLocId);
		} catch (Exception e) {
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgActiveAgency LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidbutab/rgActiveAgencyRecordGroupGd", method = RequestMethod.GET)
	public List<AgencyLocations> rgActiveAgencyRecordGroupForGrid(
			@RequestParam(value = "agylocId") final String agyLocId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		final AgencyLocations obj = new AgencyLocations();
		try {
			recordList = oidbutabService.rgActiveAgencyRecordGroupForGrid(agyLocId);
		} catch (Exception e) {
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgLuLevel1 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidbutab/rgLuLevel1RecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> rgLuLevel1RecordGroup(@RequestParam(value = "agylocId") final String agyLocId) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		final LivingUnits obj = new LivingUnits();
		try {
			recordList = oidbutabService.rgLuLevel1RecordGroup(agyLocId);
		} catch (Exception e) {
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgLuLevel2 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidbutab/rgLuLevel2RecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> rgLuLevel2RecordGroup(@RequestParam(value = "agylocId") final String agyLocId,
			@RequestParam(value = "livingUnitId") final String livingUnitId) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		final LivingUnits obj = new LivingUnits();
		try {
			recordList = oidbutabService.rgLuLevel2RecordGroup(agyLocId, livingUnitId);
		} catch (Exception e) {
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgLuLevel3 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidbutab/rgLuLevel3RecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> rgLuLevel3RecordGroup(@RequestParam(value = "agylocId") final String agyLocId,
			@RequestParam(value = "livingUnitId") final String livingUnitId) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		final LivingUnits obj = new LivingUnits();
		try {
			recordList = oidbutabService.rgLuLevel3RecordGroup(agyLocId, livingUnitId);
		} catch (Exception e) {
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgCity LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidbutab/rgCityRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCityRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = oidbutabService.rgCityRecordGroup();
		} catch (Exception e) {
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgDirection values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidbutab/rgDirectionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgDirectionRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = oidbutabService.rgDirectionRecordGroup();
		} catch (Exception e) {
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgReason LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidbutab/rgReasonRecordGroup", method = RequestMethod.GET)
	public List<MovementReasons> rgReasonRecordGroup() {
		List<MovementReasons> recordList = new ArrayList<MovementReasons>();
		final MovementReasons obj = new MovementReasons();
		try {
			recordList = oidbutabService.rgReasonRecordGroup();
		} catch (Exception e) {
			logger.error(e);
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
	@RequestMapping(value = "/oidbutab/vhbExecuteQuery", method = RequestMethod.POST)
	public List<VHeaderBlock> vhbExecuteQuery(@RequestBody final VHeaderBlock searchBean) {
		List<VHeaderBlock> searchResult = new ArrayList<>();
		final VHeaderBlock bean = new VHeaderBlock();
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			searchResult = oidbutabService.vhbExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
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
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidbutab/vhbCommit", method = RequestMethod.POST)
	public @ResponseBody Integer vhbCommit(@RequestBody final VHeaderBlockCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidbutabService.vhbCommit(commitBean);
		} catch (Exception e) {

			logger.error(e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidbutab/whenValidateItem", method = RequestMethod.POST)
	public @ResponseBody Integer whenValidateItem(@RequestBody final VNameSearch vnamesearch) {
		int liReturn = 0;
		try {
			if (vnamesearch != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				vnamesearch.setCreateUserId(userName);
			}
			liReturn = oidbutabService.whenValidateItem(vnamesearch);
		} catch (Exception e) {
			logger.error(e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidbutab/hasLaterMovement", method = RequestMethod.POST)
	public @ResponseBody OffenderExternalMovements hasLaterMovement(
			@RequestBody final OffenderExternalMovements offExMovementsSearch) {
		OffenderExternalMovements liReturn = new OffenderExternalMovements();
		try {
			liReturn = oidbutabService.hasLaterMovement(offExMovementsSearch);
		} catch (Exception e) {
			logger.error("hasLaterMovement: ", e);
		}
		return liReturn;
	}
}