package net.syscon.s4.iwp;

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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffLocationRoles;

@EliteController
public class OcmshierController {
	@Autowired
	private OcmshierService ocmshierService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmshierController.class.getName());

	/**
	 * getting calAgyLocIdRecordGroup LOV values
	 * 
	 * @param caseloadid
	 * @return List<AgencyLocations>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmshier/calAgyLocIdRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> calAgyLocIdRecordGroup(@RequestParam("caseloadId") final String caseloadId) {
		 List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = ocmshierService.calAgyLocIdRecordGroup(caseloadId);
		} catch (Exception e) {
			logger.error("Exception : Ocmshier:", e);
		}
		return recordList;
	}
	/**
	 * getting staffLrDspLastNameRecordGroup LOV values
	 * 
	 * @param agyLocID
	 * @return List<StaffMembers>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmshier/staffLrDspLastNameRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> staffLrDspLastNameRecordGroup(@RequestParam final String agyLocIdLov) {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = ocmshierService.staffLrDspLastNameRecordGroup(agyLocIdLov);
		} catch (Exception e) {
			logger.error("Exception : Ocmshier:", e);
		}
		return recordList;
	}
	/**
	 * getting staffLrPositionRecordGroup LOV values
	 * 
	 * @return List<ReferenceCodes> 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmshier/staffLrPositionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> staffLrPositionRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmshierService.staffLrPositionRecordGroup();
		} catch (Exception e) {
			logger.error("Exception : Ocmshier:", e);
		}
		return recordList;
	}

	/**
	 * getting staffLrRoleRecordGroup LOV values
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmshier/staffLrRoleRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> staffLrRoleRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmshierService.staffLrRoleRecordGroup();
		} catch (Exception e) {
			logger.error("Exception : Ocmshier:", e);
		}
		return recordList;
	}

	/**
	 * getting staffLrScheduleTypeRecordGroup LOV values
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmshier/staffLrScheduleTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> staffLrScheduleTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmshierService.staffLrScheduleTypeRecordGroup();
		} catch (Exception e) {
			logger.error("Exception : Ocmshier:", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 * @return List<StaffLocationRoles>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmshier/staffLrExecuteQuery", method = RequestMethod.POST)
	public List<StaffLocationRoles> staffLrExecuteQuery(@RequestBody final StaffLocationRoles searchBean) {
		List<StaffLocationRoles> searchResult = new ArrayList<>();
		try {
			searchResult = ocmshierService.staffLrExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 * @return List<StaffLocationRoles>
	 */
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmshier/staffLr1ExecuteQuery", method = RequestMethod.POST)
	public List<StaffLocationRoles> staffLr1ExecuteQuery(@RequestBody final StaffLocationRoles searchBean) {
		List<StaffLocationRoles> searchResult = new ArrayList<>();
		try {
			searchResult = ocmshierService.staffLr1ExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
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
	@RequestMapping(value = "/ocmshier/removeStaffMember", method = RequestMethod.POST)
	public @ResponseBody Integer stafflr1Commit(@RequestBody  final StaffLocationRoles staffLocR) {
		int liReturn = 0;
		try {
			if (staffLocR != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				staffLocR.setModifyUserId(userName);
			}
			liReturn = ocmshierService.stafflr1Commit(staffLocR);
		} catch (Exception e) {
			logger.error("Exception : Ocmshier", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * @Param searchRecord
	 * @return List<CaseloadAgencyLocations>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmshier/calExecuteQuery", method = RequestMethod.POST)
	public List<CaseloadAgencyLocations> calExecuteQuery(@RequestBody final CaseloadAgencyLocations searchBean) {
		List<CaseloadAgencyLocations> searchResult = new ArrayList<>();
		try {
			searchResult = ocmshierService.calExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * getting cgfkStaffLr1Position LOV values
	 * @return  List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmshier/cgfkStaffLr1PositionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkStaffLr1PositionRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmshierService.cgfkStaffLr1PositionRecordGroup();
		} catch (Exception e) {
			logger.error("Exception : Ocmshier:", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkStaffLr1ScheduleType LOV values
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmshier/cgfkStaffLr1ScheduleTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkStaffLr1ScheduleTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmshierService.cgfkStaffLr1ScheduleTypeRecordGroup();
		} catch (Exception e) {
			logger.error("Exception : Ocmshier:", e);
		}
		return recordList;
	}
	/**
	 * getting cgfkStaffLr1Role LOV values
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmshier/cgfkStaffLr1RoleRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkStaffLr1RoleRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmshierService.cgfkStaffLr1RoleRecordGroup();
		} catch (Exception e) {
			logger.error("Exception : Ocmshier:", e);
		}
		return recordList;
	}
}