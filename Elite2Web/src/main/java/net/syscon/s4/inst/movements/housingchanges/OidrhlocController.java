package net.syscon.s4.inst.movements.housingchanges;

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
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.movements.beans.ReserveBedLocations;
import net.syscon.s4.inst.movements.beans.ReserveBedLocationscommitBean;

/**
 * Class OidrhlocController
 */
@EliteController
public class OidrhlocController {
	@Autowired
	private OidrhlocService oidrhlocService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidrhlocController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrhloc/resBlExecuteQuery", method = RequestMethod.POST)
	public List<ReserveBedLocations> resBlExecuteQuery(@RequestBody final ReserveBedLocations searchBean) {
		List<ReserveBedLocations> searchResult = new ArrayList<>();
		try {
			searchResult = oidrhlocService.resBlExecuteQuery(searchBean);
		} catch (Exception e) {
			final ReserveBedLocations bean = new ReserveBedLocations();
			logger.error("In method resBlExecuteQuery : ", e);
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
	@RequestMapping(value = "/oidrhloc/resBlCommit", method = RequestMethod.POST)
	public @ResponseBody Integer resBlCommit(@RequestBody final ReserveBedLocationscommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidrhlocService.resBlCommit(commitBean);
		} catch (Exception e) {

			logger.error("In method resBlCommit : ", e);
		}
		return liReturn;
	}
	
	/**
	 * getting cgfkResBlAgyLocId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrhloc/cgfkResBlAgyLocIdRecordGroup", method = RequestMethod.GET)
	public List<CaseloadAgencyLocations> cgfkResBlAgyLocIdRecordGroup(
			@RequestParam(value = "caseloadId") final String caseloadId) {
		List<CaseloadAgencyLocations> recordList = new ArrayList<CaseloadAgencyLocations>();
		try {
			recordList = oidrhlocService.cgfkResBlAgyLocIdRecordGroup(caseloadId);
		} catch (Exception e) {
			final CaseloadAgencyLocations obj = new CaseloadAgencyLocations();
			logger.error("In method cgfkResBlAgyLocIdRecordGroup : ", e);
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
	@RequestMapping(value = "/oidrhloc/validateLivingUnitId", method = RequestMethod.POST)
	public @ResponseBody Integer validateLivingUnitId(@RequestBody final OffenderBookings searchRecord) {
		Integer liReturn = 0;
		try {
			liReturn = oidrhlocService.validateLivingUnitId(searchRecord);
		} catch (Exception e) {
			logger.error("In method validateLivingUnitId : ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrhloc/getCbQuery", method = RequestMethod.GET)
	public LivingUnits getCbQuery(@RequestParam(value = "offenderId") final String offenderId,
			@RequestParam(value = "caseloadId") final String caseloadId) {
		LivingUnits searchResult = new LivingUnits();
		try {
			searchResult = oidrhlocService.getBookingInfoCur(offenderId, caseloadId);
		} catch (Exception e) {
			final OffenderBookings bean = new OffenderBookings();
			logger.error("In method getCbQuery : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrhloc/getOcFlagValue", method = RequestMethod.POST)
	public @ResponseBody Boolean getOcFlagValue(@RequestBody final ReserveBedLocations searchRecord) {
		Boolean liReturn = null;
		try {
			liReturn = oidrhlocService.getOcFlagValue(searchRecord);
		} catch (Exception e) {
			logger.error("In method getOcFlagValue : ", e);
		}
		return liReturn;
	}
}
