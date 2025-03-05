package net.syscon.s4.inst.shiftlogs;

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
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.shiftlogs.bean.AgencyShiftLogs;
import net.syscon.s4.inst.shiftlogs.bean.AgencyShiftLogsCommitBean;
import net.syscon.s4.inst.shiftlogs.bean.OffendersShiftLog;
import net.syscon.s4.inst.shiftlogs.bean.OffendersShiftLogCommitBean;

/**
 * class OidshlogController
 */
@EliteController
public class OidshlogController {
	@Autowired
	private OidshlogService oidshlogService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidshlogController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidshlog/agyShilExecuteQuery", method = RequestMethod.POST)
	public List<AgencyShiftLogs> agyShilExecuteQuery(@RequestBody final AgencyShiftLogs searchBean) {
		List<AgencyShiftLogs> searchResult = new ArrayList<AgencyShiftLogs>();
		try {
			searchResult = oidshlogService.agyShilExecuteQuery(searchBean);
		} catch (Exception e) {
			final AgencyShiftLogs bean = new AgencyShiftLogs();
			logger.error("In agyShilExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidshlog/agyShilCommit", method = RequestMethod.POST)
	public @ResponseBody List<AgencyShiftLogs> agyShilCommit(@RequestBody final AgencyShiftLogsCommitBean commitBean) {
		List<AgencyShiftLogs> recordList = new ArrayList<AgencyShiftLogs>();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			recordList = oidshlogService.agyShilCommit(commitBean);
		} catch (Exception e) {
			logger.error("In agyShilCommit method : ", e);
		}
		return recordList;
	}
	/**
	 * Generates shiftLogSequences
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidshlog/agyShilWithoutLock", method = RequestMethod.POST)
	public @ResponseBody List<AgencyShiftLogs> agyShilWithoutLock(@RequestBody final AgencyShiftLogsCommitBean commitBean) {
		List<AgencyShiftLogs> recordList = new ArrayList<AgencyShiftLogs>();
		try {
			recordList = oidshlogService.agyShilWithoutLock(commitBean);
		} catch (Exception e) {
			logger.error("In agyShilWithoutLock method : ", e);
		}
		return recordList;
	}
	
	/**
	 * getting cgfkAgyShilDspAgyLocId4 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidshlog/cgfkAgyShilDspAgyLocId4RecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> cgfkAgyShilDspAgyLocId4RecordGroup(@RequestParam(value = "caseloadId") final String caseloadId)  {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = oidshlogService.cgfkAgyShilDspAgyLocId4RecordGroup(caseloadId);
		} catch (Exception e) {
			final AgencyLocations obj = new AgencyLocations();
			logger.error("In cgfkAgyShilDspAgyLocId4RecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting cgfkAgyShilStaffId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidshlog/cgfkAgyShilStaffIdRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> cgfkAgyShilStaffIdRecordGroup(@RequestParam(value = "caseloadId") final String caseloadId)  {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = oidshlogService.cgfkAgyShilStaffIdRecordGroup(caseloadId);
		} catch (Exception e) {
			final StaffMembers obj = new StaffMembers();
			logger.error("In cgfkAgyShilStaffIdRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting cgfkAgyShilDspAgyLocId3 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidshlog/cgfkAgyShilDspAgyLocId3RecordGroup", method = RequestMethod.GET)
	public List<AgencyInternalLocations> cgfkAgyShilDspAgyLocId3RecordGroup(@RequestParam(value = "agyLocId") final String agyLocId)  {
		List<AgencyInternalLocations> recordList = new ArrayList<>();
		try {
			recordList = oidshlogService.cgfkAgyShilDspAgyLocId3RecordGroup(agyLocId);
		} catch (Exception e) {
			final AgencyInternalLocations obj = new AgencyInternalLocations();
			logger.error("In cgfkAgyShilDspAgyLocId3RecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting cgfkAgyShilAgyActivityCod LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidshlog/cgfkAgyShilAgyActivityCodRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkAgyShilAgyActivityCodRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidshlogService.cgfkAgyShilAgyActivityCodRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In cgfkAgyShilAgyActivityCodRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}
	
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidshlog/agyShilWhenNewRecordInstance", method=RequestMethod.GET)
	public StaffMembers agyShilWhenNewRecordInstance() {
		StaffMembers objStaff = new StaffMembers();
		String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			objStaff = oidshlogService.agyShilWhenNewRecordInstance(user);
		} catch (Exception e) {
			final StaffMembers bean = new StaffMembers();
			logger.error("In agyShilWhenNewRecordInstance method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return objStaff;
	}
	
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidshlog/getBackDateHours", method=RequestMethod.GET)
	public String getBackDateHours() {
		String strHours = null;
		try {
			strHours = oidshlogService.getBackDateHours();
		} catch (Exception e) {
			logger.error("In getBackDateHours method : ", e);
		}
		return strHours;
	}
	
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidshlog/checkBoxHideData", method=RequestMethod.GET)
	public String checkBoxHideData() {
		String checkBxHide = null;
		try {
			checkBxHide = oidshlogService.checkBoxHideData();
		} catch (Exception e) {
			logger.error("checkBoxHideData", e);
		}
		return checkBxHide;
	}
	
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidshlog/checkBoxShlogData", method=RequestMethod.GET)
	public Integer checkBoxShlogData() {
		String userid = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		Integer checkBxHide = 0;
		try {
			checkBxHide = oidshlogService.checkBoxShlogData(userid);
		} catch (Exception e) {
			logger.error("checkBoxHideData", e);
		}
		return checkBxHide;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidshlog/relatedOffendersExcuteQuery", method=RequestMethod.GET)
	public List<VHeaderBlock> relatedOffendersExcuteQuery(Integer internalLocationId) {
		List<VHeaderBlock> recordList = new ArrayList<VHeaderBlock>();
		try {
			recordList = oidshlogService.relatedOffendersExcuteQuery(internalLocationId);
		} catch (Exception e) {
			logger.error("In getBackDateHours method : ", e);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidshlog/offShilCommit", method = RequestMethod.POST)
	public @ResponseBody List<OffendersShiftLog> offShilCommit(@RequestBody final OffendersShiftLogCommitBean commitBean) {
		List<OffendersShiftLog> recordList = new ArrayList<OffendersShiftLog>();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			recordList = oidshlogService.offShilCommit(commitBean);
		} catch (Exception e) {
			logger.error("In agyShilCommit method : ", e);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidshlog/OffendersShiftLogExcuteQuery", method=RequestMethod.GET)
	public List<OffendersShiftLog> OffendersShiftLogExcuteQuery(@RequestParam BigDecimal shiftLogSeq , @RequestParam Long internalLocationId) {
		List<OffendersShiftLog> recordList = new ArrayList<OffendersShiftLog>();
		try {
			recordList = oidshlogService.OffendersShiftLogExcuteQuery(shiftLogSeq , internalLocationId);
		} catch (Exception e) {
			logger.error("In getBackDateHours method : ", e);
		}
		return recordList;
	}
}