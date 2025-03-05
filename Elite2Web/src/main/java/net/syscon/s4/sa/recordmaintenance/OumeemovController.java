package net.syscon.s4.sa.recordmaintenance;

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
import net.syscon.s4.common.beans.OffenderExternalMovementsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.MovementReasons;

/**
 * OumeemovController
 */
@EliteController
public class OumeemovController {
	@Autowired
	private OumeemovService oumeemovService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumeemovController.class.getName());

	/**
	 * getting cgfkOffEmFromCity LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumeemov/cgfkOffEmFromCityRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffEmFromCityRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oumeemovService.cgfkOffEmFromCityRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Oumeemov:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffEmFromAgyLocId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumeemov/cgfkOffEmFromAgyLocIdRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> cgfkOffEmFromAgyLocIdRecordGroup() {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = oumeemovService.cgfkOffEmFromAgyLocIdRecordGroup();
		} catch (Exception e) {
			AgencyLocations obj = new AgencyLocations();
			logger.error("Exception : Oumeemov:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffEmToAgyLocId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumeemov/cgfkOffEmToAgyLocIdRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> cgfkOffEmToAgyLocIdRecordGroup() {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = oumeemovService.cgfkOffEmToAgyLocIdRecordGroup();
		} catch (Exception e) {
			AgencyLocations obj = new AgencyLocations();
			logger.error("Exception : Oumeemov:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffEmMovementType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumeemov/cgfkOffEmMovementTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffEmMovementTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oumeemovService.cgfkOffEmMovementTypeRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Oumeemov:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffEmDirectionCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumeemov/cgfkOffEmDirectionCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffEmDirectionCodeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oumeemovService.cgfkOffEmDirectionCodeRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Oumeemov:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffEmMovementReasonCo LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumeemov/cgfkOffEmMovementReasonCoRecordGroup", method = RequestMethod.GET)
	public List<MovementReasons> cgfkOffEmMovementReasonCoRecordGroup(@RequestParam String movementType) {
		List<MovementReasons> recordList = new ArrayList<MovementReasons>();
		try {
			recordList = oumeemovService.cgfkOffEmMovementReasonCoRecordGroup(movementType);
		} catch (Exception e) {
			MovementReasons obj = new MovementReasons();
			logger.error("Exception : Oumeemov:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffEmToCity LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumeemov/cgfkOffEmToCityRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffEmToCityRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oumeemovService.cgfkOffEmToCityRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Oumeemov:", e);
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
	@RequestMapping(value = "/oumeemov/offEmExecuteQuery", method = RequestMethod.POST)
	public List<OffenderExternalMovements> offEmExecuteQuery(@RequestBody OffenderExternalMovements searchBean) {
		List<OffenderExternalMovements> searchResult = new ArrayList<>();
		try {
			searchResult = oumeemovService.offEmExecuteQuery(searchBean);
		} catch (Exception e) {
			OffenderExternalMovements bean = new OffenderExternalMovements();
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
	@RequestMapping(value = "/oumeemov/offEmCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offEmCommit(@RequestBody OffenderExternalMovementsCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = oumeemovService.offEmCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception : Oumeemov", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oumeemov/validateSeqNum", method = RequestMethod.POST)
	public String validateSeqNum(@RequestBody OffenderExternalMovements paramBean) {
		String liReturn = null;
		try {
			liReturn = oumeemovService.ValidateSeqNum(paramBean);
		} catch (Exception e) {

			logger.error("Exception : Oumeemov", e);
		}
		return liReturn;
	}

}