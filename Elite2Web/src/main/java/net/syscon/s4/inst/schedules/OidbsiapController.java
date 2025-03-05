package net.syscon.s4.inst.schedules;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedulesCommitBean;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules2;

/**
 * Class OidbsiapController
 */
@EliteController
public class OidbsiapController {
	@Autowired
	private OidbsiapService oidbsiapService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidbsiapController.class.getName());

	/**
	 * getting rgSchInternalSchedule LOV values
	 * @return a list of the InternalScheduleReasons {@link InternalScheduleReasons}
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidbsiap/rgSchInternalScheduleRecordGroup", method = RequestMethod.GET)
	public List<InternalScheduleReasons> rgSchInternalScheduleRecordGroup() {
		List<InternalScheduleReasons> recordList = new ArrayList<InternalScheduleReasons>();
		try {
			recordList = oidbsiapService.rgSchInternalScheduleRecordGroup();
		} catch (Exception e) {
			final InternalScheduleReasons obj = new InternalScheduleReasons();
			logger.error("In method rgSchInternalScheduleRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgAgyLoc LOV values
	 * @param caseloadId {@link String}
	 * @return a list of the AgencyLocations {@link AgencyLocations} for the matched caseloadId
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidbsiap/rgAgyLocRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAgyLocRecordGroup(@RequestParam(value = "caseloadId") final String caseloadId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = oidbsiapService.rgAgyLocRecordGroup(caseloadId);
		} catch (Exception e) {
			final AgencyLocations obj = new AgencyLocations();
			logger.error("In method rgAgyLocRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgInternalMoveLocations LOV values
	 * @param agyLocId {@link String}
	 * @return a list of the AgencyInternalLocations {@link AgencyInternalLocations} for the matched agyLocId
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidbsiap/rgInternalMoveLocationsRecordGroup", method = RequestMethod.GET)
	public List<AgencyInternalLocations> rgInternalMoveLocationsRecordGroup(
			@RequestParam(value = "agyLocId") final String agyLocId) {
		List<AgencyInternalLocations> recordList = new ArrayList<AgencyInternalLocations>();
		try {
			recordList = oidbsiapService.rgInternalMoveLocationsRecordGroup(agyLocId);
		} catch (Exception e) {
			final AgencyInternalLocations obj = new AgencyInternalLocations();
			logger.error("In method rgInternalMoveLocationsRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link VOffenderAllSchedules2}
	 * @return a list of the VOffenderAllSchedules2 {@link VOffenderAllSchedules2} for the matched VOffenderAllSchedules2
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidbsiap/offSchExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderAllSchedules2> offSchExecuteQuery(@RequestBody final VOffenderAllSchedules2 searchBean) {
		List<VOffenderAllSchedules2> searchResult = new ArrayList<>();
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			searchResult = oidbsiapService.offSchExecuteQuery(searchBean);
		} catch (Exception e) {
			final VOffenderAllSchedules2 bean = new VOffenderAllSchedules2();
			logger.error("In method offSchExecuteQuery", e);
			searchResult.add(bean);
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
	@RequestMapping(value = "/oidbsiap/offSchCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offSchCommit(@RequestBody final OffenderIndSchedulesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidbsiapService.offSchCommit(commitBean);
		} catch (RuntimeException e) {
			liReturn = 2;
			return liReturn;
		} catch (Exception e) {
			logger.error("In method offSchCommit", e);
		}
		return liReturn;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 *
	 * @param searchBean {@link VOffenderAllSchedules2}
	 * @return success/failure of the insert/update {@link Integer} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidbsiap/offSchCheckScheduleConflict", method = RequestMethod.POST)
	public @ResponseBody Integer offSchCheckScheduleConflict(@RequestBody final VOffenderAllSchedules2 searchBean) {
		int liReturn = 0;
		try {
			liReturn = oidbsiapService.offSchCheckScheduleConflict(searchBean);
		} catch (Exception e) {
			logger.error("In method offSchCheckScheduleConflict", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidbsiap/checkNonAssociationOffenders", method = RequestMethod.POST)
	public  List<OffenderIndSchedules> checkNonAssociationOffendersWithLivingUnit(@RequestBody final OffenderIndSchedulesCommitBean commitBean){
		return oidbsiapService.getNonAssociationWarnings(commitBean.getInsertList());
		
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidsiapp/checkNonAssociationOffendersWithLivingUnit", method = RequestMethod.GET)
	public List<OffenderNaDetails> checkNonAssociationOffendersWithLivingUnit(@RequestParam final BigDecimal offenderBookId,
			@RequestParam BigDecimal livingUnitId) {
		List<OffenderNaDetails> liReturn = new ArrayList<>();
		try {
			liReturn = oidbsiapService.checkNonAssociationOffendersWithLivingUnit(offenderBookId,livingUnitId);
		} catch (Exception e) {
			logger.error("In checkNonAssociations method : ", e);
		}
		return liReturn;
	}

	@PostMapping("/oidbsiapp/getnsOffenderBookId")
	public List<OffenderNonAssociations> getnsOffenderBookId(@RequestBody OffenderIndSchedulesCommitBean commitBean) {
		List<OffenderNonAssociations> offNonList=new ArrayList<>();
		try {
			offNonList= oidbsiapService.getNsOffenderBookId(commitBean);
		} catch (Exception e) {
		logger.error("error in getnsOffenderBookId",  e);
		}
		return offNonList;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidbsiap/checkNonAssociationOffendersexternal", method = RequestMethod.POST)
	public  List<String> checkNonAssociationOffendersexternal(@RequestBody final OffenderIndSchedulesCommitBean commitBean){
		return oidbsiapService.checkNonAssociations(commitBean);
		
	}
}