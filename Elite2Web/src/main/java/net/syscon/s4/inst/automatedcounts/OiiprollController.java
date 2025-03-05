package net.syscon.s4.inst.automatedcounts;

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

import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.VIntLocOffenders;
import net.syscon.s4.im.beans.VLivingUnitOffenders;
import net.syscon.s4.im.beans.VLivingUnitSummaries;
import net.syscon.s4.inst.movements.beans.VIntLocSummaries;
import net.syscon.s4.inst.offenderissuestracking.OiigrievService;

@EliteController
public class OiiprollController {
	@Autowired
	private OiiprollService oiiprollService;
	
	@Autowired
	private OiigrievService oiigrievService;
	@Autowired
	private OiiunrolService oiiunrolService;
	
	@Autowired
	private OiiinrolService oiiinrolService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiiprollController.class.getName());

	/**
	 * getting rgAgyLoc LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiproll/rgAgyLocRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAgyLocRecordGroup(@RequestParam(value = "caseloadId") final String caseloadId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = oiiprollService.rgAgyLocRecordGroup(caseloadId);
		} catch (Exception e) {
			logger.error("rgAgyLocRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiiproll/lvUntSmExecuteQuery", method = RequestMethod.POST)
	public List<VLivingUnitSummaries> lvUntSmExecuteQuery(@RequestParam(value = "type") final String type,
			@RequestBody final VLivingUnitSummaries searchBean) {
		List<VLivingUnitSummaries> searchResult = new ArrayList<>();
		try {
			searchResult = oiiprollService.lvUntSmExecuteQuery(searchBean, type);
		} catch (Exception e) {
			logger.error("lvUntSmExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiproll/lvUntSmTotalCount", method = RequestMethod.POST)
	public VLivingUnitSummaries lvUntSmTotalCount(@RequestParam(value = "type") final String type,
			@RequestBody final VLivingUnitSummaries searchBean) {
		VLivingUnitSummaries searchResult = new VLivingUnitSummaries();
		try {
			searchResult = oiiprollService.lvUntSmTotalCount(searchBean, type);
		} catch (Exception e) {
			logger.error("lvUntSmTotalCount", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord, type
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiproll/itLcSmExecuteQuery", method = RequestMethod.POST)
	public List<VIntLocSummaries> itLcSmExecuteQuery(@RequestParam(value = "type") final String type,
			@RequestBody final VIntLocSummaries searchBean) {
		List<VIntLocSummaries> searchResult = new ArrayList<>();
		try {
			searchResult = oiiprollService.itLcSmExecuteQuery(searchBean, type);
		} catch (Exception e) {
			logger.error("itLcSmExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord, type
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiproll/itLcSmTotalCount", method = RequestMethod.POST)
	public VIntLocSummaries itLcSmTotalCount(@RequestParam(value = "type") final String type,
			@RequestBody final VIntLocSummaries searchBean) {
		VIntLocSummaries searchResult = new VIntLocSummaries();
		try {
			searchResult = oiiprollService.itLcSmTotalCount(searchBean, type);
		} catch (Exception e) {
			logger.error("itLcSmTotalCount", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiproll/whenNewRecordInstance", method = RequestMethod.GET)
	public String whenNewRecordInstance() {
		String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		String searchResult = null;
		try {
			searchResult = oiigrievService.whenNewRecordInstance(user);
		} catch (Exception e) {
			logger.error("whenNewRecordInstance", e);
		}
		return searchResult;
	}
	
	//copied form OiiunrolController for security reasons 
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiproll/rollListExecuteQuery", method = RequestMethod.POST)
	public List<VLivingUnitOffenders> rollListExecuteQuery(@RequestBody final VLivingUnitOffenders searchBean) {
		List<VLivingUnitOffenders> searchResult = new ArrayList<>();
		try {
			searchResult = oiiunrolService.rollListExecuteQuery(searchBean);
		} catch (Exception e) {
			final VLivingUnitOffenders bean = new VLivingUnitOffenders();
			logger.error("In method rollListExecuteQuery",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	//copied from OIIINROL
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oiiproll/rollListExecuteQuery1", method=RequestMethod.GET)
	public List<VIntLocOffenders> rollListExecuteQuery1(@RequestParam(value="agyLocId") final String agyLocId, 
								@RequestParam(value="internalLocationId") final Integer internalLocationId ) {
		List<VIntLocOffenders> searchResult = new ArrayList<>();
		try {
			searchResult = oiiinrolService.rollListExecuteQuery(agyLocId,internalLocationId);
		} catch (Exception e) {
			VIntLocOffenders bean = new VIntLocOffenders();
			logger.error("rollListExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
}