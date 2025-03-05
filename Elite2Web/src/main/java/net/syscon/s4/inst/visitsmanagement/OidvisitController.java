package net.syscon.s4.inst.visitsmanagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import net.syscon.s4.common.beans.AgencyVisitTimes;
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderVisitVisitors;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderVisitVisitorsCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.VOcuavlocAvailable;
import net.syscon.s4.inst.visitsmanagement.beans.VOcuavlocUnavailable;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderAuthorisedVisitors;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisitVisitors;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisitVisitorsCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisits;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisitsCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.ValidateVisitorBean;
import net.syscon.s4.inst.visitsmanagement.beans.VisitCycleLimits;
import net.syscon.s4.inst.visitsmanagement.maintenance.OimvlimtService;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * Class OidvisitController
 */
@EliteController
public class OidvisitController {
	@Autowired
	private OidvisitService oidvisitService;
	@Autowired
	private ProsmainService prosmainService;
	
	@Autowired
	private OcuavlocService ocuavlocService;
	
	@Autowired
	private OcuvwarnService ocuvwarnService;
	
	@Autowired
	private OmuaprisService omuaprisService;
	
	@Autowired
	 private OimvlimtService oimvlimtService;  
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidvisitController.class.getName());

	/**
	 * getting rgMoveCancRs LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvisit/rgMoveCancRsRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgMoveCancRsRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidvisitService.rgMoveCancRsRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(this.getClass().getName() + "error in rgMoveCancRsRecordGroup  : ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgVisitTimeSlots LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvisit/rgVisitTimeSlotsRecordGroup", method = RequestMethod.GET)
	public List<VOffenderVisits> rgVisitTimeSlotsRecordGroup(
			@RequestParam(value = "parentField") final String parentField) {
		List<VOffenderVisits> recordList = new ArrayList<VOffenderVisits>();
		try {
			recordList = oidvisitService.rgVisitTimeSlotsRecordGroup(parentField);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in rgVisitTimeSlotsRecordGroup  : ", e);
		}
		return recordList;
	}

	/**
	 * getting rgVisComplete LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvisit/rgVisCompleteRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgVisCompleteRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidvisitService.rgVisCompleteRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(this.getClass().getName() + "error in rgVisCompleteRecordGroup  : ", e);
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
	@RequestMapping(value = "/oidvisit/offVstExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderVisits> offVstExecuteQuery(@RequestBody final VOffenderVisits searchBean) {
		List<VOffenderVisits> searchResult = new ArrayList<>();
		try {
			searchBean.setUserName(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			searchResult = oidvisitService.offVstExecuteQuery(searchBean);
		} catch (Exception e) {
			final VOffenderVisits bean = new VOffenderVisits();
			logger.error(this.getClass().getName() + "error in offVstExecuteQuery  : ", e);
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
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvisit/offVstCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offVstCommit(@RequestBody final VOffenderVisitsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (Optional.ofNullable(commitBean).isPresent())
				commitBean.setCreateUserId(
						SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			liReturn = oidvisitService.offVstCommit(commitBean);
			if(1==liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "23");
			}
		} catch (Exception e) {

			logger.error(this.getClass().getName() + "error in offVstCommit  : ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvisit/offVstPersExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderVisitVisitors> offVstPersExecuteQuery(@RequestBody final VOffenderVisitVisitors searchBean) {
		List<VOffenderVisitVisitors> searchResult = new ArrayList<>();
		try {
			searchResult = oidvisitService.offVstPersExecuteQuery(searchBean);
		} catch (Exception e) {
			final VOffenderVisitVisitors bean = new VOffenderVisitVisitors();
			logger.error(this.getClass().getName() + "error in offVstPersExecuteQuery  : ", e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	/*
	 * /** Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidvisit/offVstPersCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offVstPersCommit(@RequestBody final VOffenderVisitVisitorsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (Optional.ofNullable(commitBean).isPresent())
				commitBean.setCreateUserId(
						SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			liReturn = oidvisitService.offVstPersCommit(commitBean);
			if(1==liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "24");
			}
		} catch (Exception e) {

			logger.error(this.getClass().getName() + "error in offVstPersCommit  : ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvisit/imagesVisitorsExecuteQuery", method = RequestMethod.POST)
	public List<Images> imagesVisitorsExecuteQuery(@RequestBody final Images searchBean) {
		List<Images> searchResult = new ArrayList<>();
		try {
			searchResult = oidvisitService.imagesVisitorsExecuteQuery(searchBean);
		} catch (Exception e) {
			final Images bean = new Images();
			logger.error(this.getClass().getName() + "error in imagesVisitorsExecuteQuery  : ", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvisit/offVstOffExecuteQuery", method = RequestMethod.POST)
	public List<OffenderVisitVisitors> offVstOffExecuteQuery(@RequestBody final OffenderVisitVisitors searchBean) {
		List<OffenderVisitVisitors> searchResult = new ArrayList<>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchBean.setCreateUserId(userName);
			searchResult = oidvisitService.offVstOffExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderVisitVisitors bean = new OffenderVisitVisitors();
			logger.error(this.getClass().getName() + "error in offVstOffExecuteQuery  : ", e);
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
	@RequestMapping(value = "/oidvisit/offVstOffCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offVstOffCommit(@RequestBody final OffenderVisitVisitorsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (Optional.ofNullable(commitBean).isPresent())
				commitBean.setCreateUserId(
						SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			liReturn = oidvisitService.offVstOffCommit(commitBean);
			if(1==liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "25");
			}
		} catch (Exception e) {

			logger.error(this.getClass().getName() + "error in offVstOffCommit  : ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvisit/imagesVisitingOffExecuteQuery", method = RequestMethod.POST)
	public List<Images> imagesVisitingOffExecuteQuery(@RequestBody final Images searchBean) {
		List<Images> searchResult = new ArrayList<>();
		try {
			searchResult = oidvisitService.imagesVisitingOffExecuteQuery(searchBean);
		} catch (Exception e) {
			final Images bean = new Images();
			logger.error(this.getClass().getName() + "error in imagesVisitingOffExecuteQuery  : ", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvisit/endTimeValidateQuery", method = RequestMethod.POST)
	public VOffenderVisits endTimeValidateQuery(@RequestBody final VOffenderVisits searchBean) {
		VOffenderVisits searchResult = new VOffenderVisits();
		try {
			searchBean.setUserName(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			searchResult = oidvisitService.endTimeValidateQuery(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in endTimeValidateQuery  : ", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvisit/visitTypeValidateQuery", method = RequestMethod.POST)
	public VOffenderVisits visitTypeValidateQuery(@RequestBody final VOffenderVisits searchBean) {
		VOffenderVisits searchResult = new VOffenderVisits();
		try {
			searchBean.setUserName(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			searchResult = oidvisitService.visitTypeValidateQuery(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in visitTypeValidateQuery  : ", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvisit/getOffenderRestrictions", method = RequestMethod.POST)
	public Integer getOffenderRestrictions(@RequestBody final VOffenderVisits searchBean) {
		Integer returnValue = 0;
		try {
			searchBean.setUserName(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			returnValue = oidvisitService.getOffenderRestrictions(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in getOffenderRestrictions  : ", e);
		}
		return returnValue;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvisit/duplicateVisit", method = RequestMethod.POST)
	public Integer duplicateVisit(@RequestBody final VOffenderVisits searchBean) {
		Integer returnValue = 0;
		try {
			returnValue = oidvisitService.duplicateVisit(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in duplicateVisit  : ", e);
		}
		return returnValue;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvisit/validateVisitor", method = RequestMethod.POST)
	public String validateVisitor(@RequestBody final ValidateVisitorBean bean) {
		String returnValue = null;
		try {
			returnValue = oidvisitService.validateVisitor(bean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in validateVisitor  : ", e);
		}
		return returnValue;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvisit/visitPerPreInsert", method = RequestMethod.POST)
	public Integer visitPerPreInsert(@RequestBody final VOffenderVisitVisitors searchBean) {
		Integer returnValue = 0;
		try {
			returnValue = oidvisitService.visitPerPreInsert(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in visitPerPreInsert  : ", e);
		}
		return returnValue;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvisit/visitOffPreInsert", method = RequestMethod.POST)
	public String visitOffPreInsert(@RequestBody final OffenderVisitVisitors searchBean) {
		String returnValue = null;
		try {
			returnValue = oidvisitService.visitOffPreInsert(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in visitOffPreInsert  : ", e);
		}
		return returnValue;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvisit/checkVisitorLimit", method = RequestMethod.POST)
	public Integer checkVisitorLimit(@RequestBody final VOffenderVisits searchBean) {
		Integer returnValue = 0;
		try {
			searchBean.setUserName(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			returnValue = oidvisitService.checkVisitorLimit(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in checkVisitorLimit  : ", e);
		}
		return returnValue;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvisit/recheckTimeSlots", method = RequestMethod.POST)
	public Integer recheckTimeSlots(@RequestBody final VOffenderVisits searchBean) {
		Integer returnValue = 0;
		try {
			returnValue = oidvisitService.recheckTimeSlots(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in recheckTimeSlots  : ", e);
		}
		return returnValue;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvisit/getOffenderDetails", method = RequestMethod.GET)
	public VOffenderVisits getOffenderDetails(
			@RequestParam(value = "offenderIdDisplay") final String offenderIdDisplay) {
		VOffenderVisits returnVal = new VOffenderVisits();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			returnVal = oidvisitService.getOffenderDetails(offenderIdDisplay, userName);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in getOffenderDetails  : ", e);
		}
		return returnVal;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvisit/isAuthorisedOffender", method = RequestMethod.GET)
	public Integer isAuthorisedOffender(@RequestParam(value = "hoffenderBookId") final Integer hoffenderBookId,
			@RequestParam(value = "voffenderBookId") final Integer voffenderBookId) {
		Integer returnVal = 0;
		try {
			returnVal = oidvisitService.isAuthorisedOffender(hoffenderBookId, voffenderBookId);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in isAuthorisedOffender  : ", e);
		}
		return returnVal;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvisit/isAuthorisedPerson", method = RequestMethod.GET)
	public Integer isAuthorisedPerson(@RequestParam(value = "personId") final Integer personId,
			@RequestParam(value = "offenderBookId") final Integer offenderBookId) {
		Integer returnVal = 0;
		try {
			returnVal = oidvisitService.isAuthorisedPerson(personId, offenderBookId);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in isAuthorisedPerson  : ", e);
		}
		return returnVal;
	}

	/**
	 * VOffenderVisitVisitors
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvisit/getVisitorRestrictions", method = RequestMethod.POST)
	public Integer getVisitorRestrictions(@RequestBody final VOffenderVisitVisitors searchBean) {
		Integer returnValue = 0;
		try {
			returnValue = oidvisitService.getVisitorRestrictions(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in getVisitorRestrictions  : ", e);
		}
		return returnValue;
	}

	/**
	 * overlapVisitForVisitors
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvisit/overlapVisitForVisitors", method = RequestMethod.POST)
	public String overlapVisitForVisitors(@RequestBody final VOffenderVisits searchBean) {
		String returnValue = null;
		try {
			returnValue = oidvisitService.overlapVisitForVisitors(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in overlapVisitForVisitors  : ", e);
		}
		return returnValue;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvisit/checkContactActive", method = RequestMethod.GET)
	public Integer checkContactActive(@RequestParam(value = "offenderBookId") final Integer offenderBookId,
			@RequestParam(value = "personId") final Integer personId) {
		Integer returnVal = 0;
		try {
			returnVal = oidvisitService.checkContactActive(offenderBookId, personId);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in checkContactActive  : ", e);
		}
		return returnVal;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvisit/oidvisitCheckListEntry", method = RequestMethod.GET)
	public List<AgencyVisitTimes> oidvisitCheckListEntry() {
		List<AgencyVisitTimes> returnVal = new ArrayList<>();
		try {
			returnVal = oidvisitService.oidvisitCheckListEntry();
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in oidvisitCheckListEntry  : ", e);
		}
		return returnVal;
	}

	/**
	 * chkVisitConflicts
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvisit/chkVisitConflicts", method = RequestMethod.POST)
	public VOffenderVisits chkVisitConflicts(@RequestBody final VOffenderVisits searchBean) {
		VOffenderVisits returnValue = new VOffenderVisits();
		try {
			returnValue = oidvisitService.chkVisitConflicts(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in chkVisitConflicts  : ", e);
		}
		return returnValue;
	}
	
	/* 
	 To check Non-Association with VISITS
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvisit/checkNonAssociations", method = RequestMethod.POST)
	public String nonAssopcation(@RequestBody final VOffenderVisitsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		String nonAssocationOffendersDeatils="";
		try {
			if (Optional.ofNullable(commitBean).isPresent())
				commitBean.setCreateUserId(
						SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			nonAssocationOffendersDeatils = oidvisitService.checkNonAssocationDetails(commitBean);
			
		} catch (Exception e) {

			logger.error(this.getClass().getName() + "error in nonAssopcation  : ", e);
		}
		return nonAssocationOffendersDeatils;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvisit/getMaxVisitors", method = RequestMethod.POST)
	public Integer getMaxVisitors(@RequestBody final VOffenderVisits vOffVisitrs) {
		Integer maxVisitCout = 0;
		try {
			maxVisitCout = oidvisitService.getMaxVisitors(vOffVisitrs);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in getMaxVisitors  : ", e);
		}
		return maxVisitCout;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvisit/rgVisitTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgVisitTypeRecordGroup(@RequestParam String offenderDetails) {
		String[] offDetails = offenderDetails.split(",");
		String agyLocId = offDetails[0];
		Long offenderBookId = Long.parseLong(offDetails[1]);
		String iepSecLevels = offDetails[2];
		String caseLoadType= offDetails[3];
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidvisitService.rgVisitTypeRecordGroup(agyLocId, offenderBookId,iepSecLevels,caseLoadType);
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(this.getClass().getName() + "error in rgVisitTypeRecordGroup  : ", e);
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
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidvisit/avlLocExecuteQuery", method = RequestMethod.POST)
	public List<VOcuavlocAvailable> avlLocExecuteQuery(@RequestBody final VOcuavlocAvailable searchBean) {
		List<VOcuavlocAvailable> searchResult = new ArrayList<>();
		try {
			searchResult = ocuavlocService.avlLocExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("avlLocExecuteQuery:",e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidvisit/fboLocExecuteQuery", method = RequestMethod.POST)
	public List<VOcuavlocUnavailable> fboLocExecuteQuery(@RequestBody final VOcuavlocUnavailable searchBean) {
		List<VOcuavlocUnavailable> searchResult = new ArrayList<>();
		try {
			searchResult = ocuavlocService.fboLocExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("fboLocExecuteQuery:",e);
		}
		return searchResult;
	}
	/**
	 * used to execute GET_OCUAVLOC_AVAILABLE procedure.
	 * 
	 * @Param objSearchDao
	 * @return List<VOcuavlocAvailable>
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidvisit/getOcuavlocAvailable", method = RequestMethod.POST)
	public List<VOcuavlocAvailable> getOcuavlocAvailable(@RequestBody final VOcuavlocAvailable searchBean) {
		List<VOcuavlocAvailable> searchResult = new ArrayList<>();
		try {
			searchResult = ocuavlocService.getOcuavlocAvailable(searchBean);
		} catch (Exception e) {
			logger.error("getOcuavlocAvailable:",e);
		}
		return searchResult;
	}
	/**
	 * used to execute GET_OCUAVLOC_UNAVAILABLE procedure.
	 * 
	 * @Param objSearchDao
	 * @return List<VOcuavlocUnavailable>
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidvisit/getOcuavlocUnAvailable", method = RequestMethod.POST)
	public List<VOcuavlocUnavailable> getOcuavlocUnAvailable(@RequestBody final VOcuavlocUnavailable searchBean) {
		List<VOcuavlocUnavailable> searchResult = new ArrayList<>();
		try {
			searchResult = ocuavlocService.getOcuavlocUnAvailable(searchBean);
		} catch (Exception e) {
			logger.error("getOcuavlocUnAvailable:",e);
		}
		return searchResult;
	}
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidvisit/reCheckTimeSlot", method = RequestMethod.POST)
	public Integer reCheckTimeSlot(@RequestBody final VOcuavlocAvailable searchBean) {
		Integer returnValue = 0;
		try {
			returnValue = ocuavlocService.reCheckTimeSlot(searchBean);
		} catch (Exception e) {
			logger.error("reCheckTimeSlot:",e);
		}
		return returnValue;
	}
	
	/**
	 * getting rgVisitTimeSlots LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvisit/getProfileValues", method = RequestMethod.GET)
	public SystemProfiles getProfileValues(@RequestParam(value = "profileType") final String profileType,
			@RequestParam(value = "profileCode") final String profileCode) {
		SystemProfiles recordList = new SystemProfiles();
		try {
			recordList = ocuvwarnService.getProfileValues(profileType, profileCode);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidvisit/vOffAuthVisExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderAuthorisedVisitors> vOffAuthVisExecuteQuery(@RequestBody final VOffenderAuthorisedVisitors searchBean) {
		List<VOffenderAuthorisedVisitors> searchResult = new ArrayList<>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchBean.setCreateUserId(userName);
			searchResult = omuaprisService.vOffAuthVisExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("vOffAuthVisExecuteQuery",e);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidvisit/getIepVisitLimis", method = RequestMethod.GET)
	public VisitCycleLimits getIepVisitLimis(@RequestParam String agyLocId) {
		VisitCycleLimits searchResult = new VisitCycleLimits();
		try {
			searchResult= oimvlimtService.getIepVisitLimis(agyLocId);
		} catch (Exception e) {
			logger.error("getIepVisitLimis",e);
		}
		return searchResult;
	}
	
	
	
}