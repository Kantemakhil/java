package net.syscon.s4.inst.schedules;

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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedulesCommitBean;
import net.syscon.s4.inst.schedules.bean.ScheduleMovementSetting;
import net.syscon.s4.inst.schedules.bean.VAddressUsages;
import net.syscon.s4.inst.schedules.bean.VAddressUsagesCommitBean;
import net.syscon.s4.inst.schedules.bean.VAgencyAddresses;
import net.syscon.s4.inst.schedules.bean.VAgencyAddressesCommitBean;
import net.syscon.s4.inst.schedules.bean.VCorporateAddresses;
import net.syscon.s4.inst.schedules.bean.VCorporateAddressesCommitBean;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VPhones;
import net.syscon.s4.inst.schedules.maintenance.OidsmsetService;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * Class OidstabsController
 */
@EliteController
public class OidstabsController {
	@Autowired
	private OidstabsService oidstabsService;
	
	@Autowired
	private ProsmainService prosmainService;
	
	@Autowired
	private OidsmsetService oidsmsetService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidstabsController.class.getName());

	/**
	 * getting rgSubType LOV values
	 * @return a list of the MovementReasons {@link MovementReasons}
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstabs/rgSubTypeRecordGroup", method = RequestMethod.GET)
	public List<MovementReasons> rgSubTypeRecordGroup(@RequestParam("type") final String type) {
		List<MovementReasons> recordList = new ArrayList<MovementReasons>();
		try {
			recordList = oidstabsService.rgSubTypeRecordGroup(type);
		} catch (Exception e) {
			final MovementReasons obj = new MovementReasons();
			logger.error(this.getClass().getName()+"In method rgSubTypeRecordGroup error", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting rgEscort LOV values
	 * @return a list of the ReferenceCodes {@link ReferenceCodes}
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstabs/rgEscortRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgEscortRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidstabsService.rgEscortRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(this.getClass().getName()+"In method rgEscortRecordGroup error", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting rgTransport LOV values
	 * @return a list of the ReferenceCodes {@link ReferenceCodes} for the matched ReferenceCodes
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstabs/rgTransportRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgTransportRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidstabsService.rgTransportRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(this.getClass().getName()+"In method rgTransportRecordGroup error", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting rgStatus LOV values
	 * @return a list of the ReferenceCodes {@link ReferenceCodes}
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstabs/rgStatusRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgStatusRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidstabsService.rgStatusRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(this.getClass().getName()+"In method rgStatusRecordGroup error", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting rgCorpLoc LOV values
	 * @return a list of the VCorporateAddresses {@link VCorporateAddresses} from the DB.
	 *
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstabs/rgCorpLocRecordGroup", method = RequestMethod.GET)
	public List<VCorporateAddresses> rgCorpLocRecordGroup() {
		List<VCorporateAddresses> recordList = new ArrayList<VCorporateAddresses>();
		try {
			recordList = oidstabsService.rgCorpLocRecordGroup();
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method rgCorpLocRecordGroup error", e);
		}
		return recordList;
	}

	/**
	 * getting rgAgyLoc LOV values
	 * @return a list of the VAgencyAddresses {@link VAgencyAddresses} for the matched VAgencyAddresses
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstabs/rgAgyLocRecordGroup", method = RequestMethod.GET)
	public List<VAgencyAddresses> rgAgyLocRecordGroup() {
		List<VAgencyAddresses> recordList = new ArrayList<VAgencyAddresses>();
		try {
			recordList = oidstabsService.rgAgyLocRecordGroup();
		} catch (Exception e) {
			final AgencyLocations obj = new AgencyLocations();
			logger.error(this.getClass().getName()+"In method rgAgyLocRecordGroup erro", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * @param rootOffenderId {@link String}
	 * @return a list of the VAddressUsages {@link VAddressUsages} for the matched VAddressUsages
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstabs/rgOthLocRecordGroup", method = RequestMethod.GET)
	public List<VAddressUsages> rgOthLocRecordGroup(
			@RequestParam(value = "rootOffenderId") final String rootOffenderId) {
		List<VAddressUsages> recordList = new ArrayList<VAddressUsages>();
		try {
			recordList = oidstabsService.rgOthLocRecordGroup(rootOffenderId);
		} catch (Exception e) {
			final Offenders obj = new Offenders();
			logger.error(this.getClass().getName()+"In method rgOthLocRecordGroup erro", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link VOffenderAllSchedules}
	 * @return a list of the VOffenderAllSchedules {@link VOffenderAllSchedules} for the matched VOffenderAllSchedules
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstabs/offSchedulesExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderAllSchedules> offSchedulesExecuteQuery(@RequestBody final VOffenderAllSchedules searchBean) {
		List<VOffenderAllSchedules> searchResult = new ArrayList<>();
		try {
			searchResult = oidstabsService.offSchedulesExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method offSchedulesExecuteQuery error", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 *
	 * @param commitBean {@link OffenderIndSchedulesCommitBean}
	 * @return success/failure of the insert/update {@link Integer} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidstabs/offSchedulesCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offSchedulesCommit(@RequestBody final OffenderIndSchedulesCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidstabsService.offSchedulesCommit(commitBean);
			if(liReturn == 1) {
				if(commitBean.getInsertList().size() > 0) {
					for(OffenderIndSchedules offenderIndSchedules : commitBean.getInsertList()) {
						for(OffenderIndSchedules locationDet : commitBean.getAgyUpdateList()) {
							if(offenderIndSchedules.getEventId() == locationDet.getEventId()) {
								offenderIndSchedules.setToAddressId(locationDet.getToAddressId());
								offenderIndSchedules.setLocType(locationDet.getLocType());
							}
						}
					}
				}
				if(commitBean.getUpdateList().size() > 0) {
					for(OffenderIndSchedules offenderIndSchedules : commitBean.getUpdateList()) {
						for(OffenderIndSchedules locationDet : commitBean.getAgyUpdateList()) {
							if(offenderIndSchedules.getEventId().equals(locationDet.getEventId()) && "SCH".equals(offenderIndSchedules.getEventStatus())) {
								offenderIndSchedules.setToAddressId(locationDet.getToAddressId());
								offenderIndSchedules.setLocType(locationDet.getLocType());
							} else {
								commitBean.setUpdateList(null);
							}
						}
					}
				}
				prosmainService.enableTriggers(commitBean, authorization, "130");
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method offSchedulesCommit error", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstabs/checkNonAssociations", method = RequestMethod.POST)
	public String checkNonAssociations(@RequestBody final OffenderIndSchedulesCommitBean commitBean) {
		String resultString = null;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			resultString = oidstabsService.checkNonAssociations(commitBean);
		} catch (final Exception e) {
			logger.error(this.getClass().getName()+"Error occured in checkNonAssociations error:", e);
		}
		return resultString;
	}
	
	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link VAgencyAddresses}
	 * @return a list of the VAgencyAddresses {@link VAgencyAddresses} for the matched VAgencyAddresses
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstabs/agyAdrExecuteQuery", method = RequestMethod.POST)
	public List<VAgencyAddresses> agyAdrExecuteQuery(@RequestBody final VAgencyAddresses searchBean) {
		List<VAgencyAddresses> searchResult = new ArrayList<>();
		try {
			searchResult = oidstabsService.agyAdrExecuteQuery(searchBean);
		} catch (Exception e) {
			final VAgencyAddresses bean = new VAgencyAddresses();
			logger.error(this.getClass().getName()+"In method agyAdrExecuteQuery error", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 *
	 * @param commitBean {@link VAgencyAddressesCommitBean}
	 * @return a list of the VAgencyAddressesCommitBean {@link VAgencyAddressesCommitBean} for the matched VAgencyAddressesCommitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidstabs/agyAdrCommit", method = RequestMethod.POST)
	public @ResponseBody Integer agyAdrCommit(@RequestBody final VAgencyAddressesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidstabsService.agyAdrCommit(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method agyAdrCommit error", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link VCorporateAddresses}
	 * @return a list of the VCorporateAddresses {@link VCorporateAddresses} for the matched VCorporateAddresses
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstabs/busAdrExecuteQuery", method = RequestMethod.POST)
	public List<VCorporateAddresses> busAdrExecuteQuery(@RequestBody final VCorporateAddresses searchBean) {
		List<VCorporateAddresses> searchResult = new ArrayList<>();
		try {
			searchResult = oidstabsService.busAdrExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method busAdrExecuteQuery error", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 *
	 * @param commitBean {@link VCorporateAddressesCommitBean}
	 * @return success/failure of the insert/update {@link Integer} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidstabs/busAdrCommit", method = RequestMethod.POST)
	public @ResponseBody Integer busAdrCommit(@RequestBody final VCorporateAddressesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean != null) {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
		}
		try {
			liReturn = oidstabsService.busAdrCommit(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method busAdrCommit error", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link VAddressUsages}
	 * @return a list of the VAddressUsages {@link VAddressUsages} for the matched VAddressUsages
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstabs/othAdrExecuteQuery", method = RequestMethod.POST)
	public List<VAddressUsages> othAdrExecuteQuery(@RequestBody final VAddressUsages searchBean) {
		List<VAddressUsages> searchResult = new ArrayList<>();
		try {
			searchResult = oidstabsService.othAdrExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method othAdrExecuteQuery error", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 *
	 * @param commitBean {@link VAddressUsagesCommitBean}
	 * @return success/failure of the insert/update {@link Integer} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidstabs/othAdrCommit", method = RequestMethod.POST)
	public @ResponseBody Integer othAdrCommit(@RequestBody final VAddressUsagesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidstabsService.othAdrCommit(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method othAdrCommit error", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link VPhones}
	 * @return a list of the VPhones {@link VPhones} for the matched VPhones
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstabs/agyPhonesExecuteQuery", method = RequestMethod.POST)
	public List<VPhones> agyPhonesExecuteQuery(@RequestBody final VPhones searchBean) {
		List<VPhones> searchResult = new ArrayList<>();
		try {
			searchResult = oidstabsService.agyPhonesExecuteQuery(searchBean);
		} catch (Exception e) {
			final VPhones bean = new VPhones();
			logger.error(this.getClass().getName()+"In method agyPhonesExecuteQuery error", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link VPhones}
	 * @return a list of the VPhones {@link VPhones} for the matched VPhones
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstabs/busPhonesExecuteQuery", method = RequestMethod.POST)
	public List<VPhones> busPhonesExecuteQuery(@RequestBody final VPhones searchBean) {
		List<VPhones> searchResult = new ArrayList<>();
		try {
			searchResult = oidstabsService.busPhonesExecuteQuery(searchBean);
		} catch (Exception e) {
			final VPhones bean = new VPhones();
			logger.error(this.getClass().getName()+"In method busPhonesExecuteQuery error", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link VPhones}
	 * @return a list of the VPhones {@link VPhones} for the matched VPhones
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstabs/othPhonesExecuteQuery", method = RequestMethod.POST)
	public List<VPhones> othPhonesExecuteQuery(@RequestBody final VPhones searchBean) {
		List<VPhones> searchResult = new ArrayList<>();
		try {
			searchResult = oidstabsService.othPhonesExecuteQuery(searchBean);
		} catch (Exception e) {
			final VPhones bean = new VPhones();
			logger.error(this.getClass().getName()+"In method othPhonesExecuteQuery error", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions
	 * 
	 * @param eventDate {@link String}
	 * @param returnDate {@link String}
	 * @return calculate Days value as Integer {@link Object} form the given event date to return date
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstabs/calculateDays", method = RequestMethod.GET)
	public @ResponseBody Integer calculateDays(@RequestParam(value = "eventDate") final String eventDate,
			@RequestParam(value = "returnDate") final String returnDate) {
		int liReturn = 0;
		try {
			liReturn = oidstabsService.calculateDays(eventDate, returnDate);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method calculateDays error", e);
		}
		return liReturn;
	}

	/**
	 * Perfomring basic Oracle form functions database table
	 *
	 * @param startTime {@link String}
	 * @param returnTime {@link String}
	 * @return calculate hours value as Object {@link Object} form the given start time to return time
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstabs/calculateHours", method = RequestMethod.GET)
	public @ResponseBody Object calculateHours(@RequestParam(value = "startTime") final String startTime,
			@RequestParam(value = "returnTime") final String returnTime) {
		Object liReturn = 0;
		try {
			liReturn = oidstabsService.calculateHours(startTime, returnTime);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method calculateHours error", e);
		}
		return liReturn;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 *
	 * @param commitBean {@link String}
	 * @return success/failure of the address location commit as Integer {@link Integer} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidstabs/adressLocationsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer adressLocationsCommit(@RequestBody final OffenderIndSchedulesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidstabsService.adressLocationsCommit(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method offSchedulesCommit error", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstabs/offSchCheckScheduleConflict", method = RequestMethod.POST)
	public @ResponseBody Integer offSchCheckScheduleConflict(@RequestBody final OffenderIndSchedules obj) {
		int liReturn = 0;
		try {
			liReturn = oidstabsService.offSchCheckScheduleConflict(obj);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method offSchCheckScheduleConflict error", e);
		}
		return liReturn;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 *
	 * @param commitBean {@link OffenderIndSchedulesCommitBean}
	 * @return success/failure of the Offender schedules commit as Integer {@link Integer} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidstabs/offenderSchedulesCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offenderSchedulesCommit(@RequestBody final OffenderIndSchedulesCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = oidstabsService.offenderSchedulesCommit(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method offenderSchedulesCommit error", e);
		}
		return liReturn;
	}
	
	
	@RequestMapping(value = "oidstabs/rgPurposeRecordGroup",method = RequestMethod.GET )
	public List<ReferenceCodes> rgPurposeRecordGroup(@RequestParam final String reason) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidstabsService.rgPurposeRecordGroup(reason);
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(this.getClass().getName()+"In method rgPurposeRecordGroup error", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidstabs/tapScheduleSettingExecuteQuery", method = RequestMethod.GET)
	public List<ScheduleMovementSetting> tapScheduleSettingExecuteQuery() {
		List<ScheduleMovementSetting> searchResult = new ArrayList<>();
		try {
			searchResult = oidsmsetService.tapScheduleSettingExecuteQuery();
		} catch (final Exception e) {
			logger.error("Exception in progServSettingExecuteQuery :", e);
		}
		return searchResult;
	}



}