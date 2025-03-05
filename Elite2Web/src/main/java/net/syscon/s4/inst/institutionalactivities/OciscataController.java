package net.syscon.s4.inst.institutionalactivities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.cm.programsservices.VOffenderPrgObligations;
import net.syscon.s4.cm.programsservices.maintenance.OidowrelService;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderProgramProfilesCommitBean;
import net.syscon.s4.im.beans.ProgramsNonAssocTmp;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.im.beans.VCourseActivities;
import net.syscon.s4.im.beans.VOffenderProgramProfiles;
import net.syscon.s4.inst.programswithoutschedules.OcdxprogService;
import net.syscon.s4.pkgs.common.CommonService;

/**
 * Class OciscataController
 */
@EliteController
public class OciscataController {
	@Autowired
	private OciscataService ociscataService;
	@Autowired
	private OcupaoffService ocupaoffService;
	@Autowired
	private OidowrelService oidowrelService;
	@Autowired
	private OcdxprogService ocdxprogService;
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OciscataController.class.getName());

	/**
	 * getting rgAreas LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ociscata/rgAreasRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgAreasRecordGroup(@RequestParam(value = "environment") final String environment,
			@RequestParam(value = "region") final String region) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ociscataService.rgAreasRecordGroup(environment, region);
		} catch (Exception e) {
			logger.error("In rgAreasRecordGroup method : ", e);
		}
		return recordList;
	}

	/**
	 * getting rgPsAgeRange LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ociscata/rgPsAgeRangeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPsAgeRangeRecordGroup() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ociscataService.rgPsAgeRangeRecordGroup();
		} catch (Exception e) {
			logger.error("In rgPsAgeRangeRecordGroup method : ", e);
		}
		return recordList;
	}

	/**
	 * getting rgPsAvail LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ociscata/rgPsAvailRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPsAvailRecordGroup() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ociscataService.rgPsAvailRecordGroup();
		} catch (Exception e) {
			logger.error("In rgPsAvailRecordGroup method : ", e);
		}
		return recordList;
	}

	/**
	 * getting rgPsCategory LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ociscata/rgPsCategoryRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPsCategoryRecordGroup() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ociscataService.rgPsCategoryRecordGroup();
		} catch (Exception e) {
			logger.error("In rgPsCategoryRecordGroup method : ", e);
		}
		return recordList;
	}

	/**
	 * getting rgPsNeeds LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ociscata/rgPsNeedsRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPsNeedsRecordGroup() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ociscataService.rgPsNeedsRecordGroup();
		} catch (Exception e) {
			logger.error("In rgPsNeedsRecordGroup method : ", e);
		}
		return recordList;
	}

	/**
	 * getting rgPsOffGrps LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ociscata/rgPsOffGrpsRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPsOffGrpsRecordGroup() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ociscataService.rgPsOffGrpsRecordGroup();
		} catch (Exception e) {
			logger.error("In rgPsOffGrpsRecordGroup method : ", e);
		}
		return recordList;
	}

	/**
	 * getting rgPsProvType LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ociscata/rgPsProvTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPsProvTypeRecordGroup() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ociscataService.rgPsProvTypeRecordGroup();
		} catch (Exception e) {
			logger.error("In rgPsProvTypeRecordGroup method : ", e);
		}
		return recordList;
	}

	/**
	 * getting rgPsSex LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ociscata/rgPsSexRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPsSexRecordGroup() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ociscataService.rgPsSexRecordGroup();
		} catch (Exception e) {
			logger.error("In rgPsSexRecordGroup method : ", e);
		}
		return recordList;
	}

	/**
	 * getting rgEthnicity LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ociscata/rgEthnicityRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgEthnicityRecordGroup() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ociscataService.rgEthnicityRecordGroup();
		} catch (Exception e) {
			logger.error("In rgEthnicityRecordGroup method : ", e);
		}
		return recordList;
	}

	/**
	 * getting rgRegion LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ociscata/rgRegionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgRegionRecordGroup() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ociscataService.rgRegionRecordGroup();
		} catch (Exception e) {
			logger.error("In rgRegionRecordGroup method : ", e);
		}
		return recordList;
	}

	/**
	 * getting rgServices LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ociscata/rgServicesRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgServicesRecordGroup(@RequestParam(value = "category") final String category) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ociscataService.rgServicesRecordGroup(category);
		} catch (Exception e) {
			logger.error("In rgServicesRecordGroup method : ", e);
		}
		return recordList;
	}

	/**
	 * getting rgCsldCode LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ociscata/rgCsldCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCsldCodeRecordGroup() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ociscataService.rgCsldCodeRecordGroup();
		} catch (Exception e) {
			logger.error("In rgCsldCodeRecordGroup method : ", e);
		}
		return recordList;
	}

	/**
	 * getting rgTeamAgyLocs LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ociscata/rgTeamAgyLocsRecordGroup", method = RequestMethod.GET)
	public List<Teams> rgTeamAgyLocsRecordGroup() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<Teams> recordList = new ArrayList<Teams>();
		try {
			recordList = ociscataService.rgTeamAgyLocsRecordGroup();
		} catch (Exception e) {
			logger.error("In rgTeamAgyLocsRecordGroup method : ", e);
		}
		return recordList;
	}

	/**
	 * getting rgCorpLocs LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ociscata/rgCorpLocsRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCorpLocsRecordGroup(@RequestParam(value = "category") final String category) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ociscataService.rgCorpLocsRecordGroup(category);
		} catch (Exception e) {
			logger.error("In rgCorpLocsRecordGroup method : ", e);
		}
		return recordList;
	}

	/**
	 * getting rgAgyLocs LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ociscata/rgAgyLocsRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgAgyLocsRecordGroup() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ociscataService.rgAgyLocsRecordGroup();
		} catch (Exception e) {
			logger.error("In rgAgyLocsRecordGroup method : ", e);
		}
		return recordList;
	}

	/**
	 * getting rgAgyLocCl LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ociscata/rgAgyLocClRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgAgyLocClRecordGroup() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ociscataService.rgAgyLocClRecordGroup();
		} catch (Exception e) {
			logger.error("In rgAgyLocClRecordGroup method : ", e);
		}
		return recordList;
	}

	/**
	 * getting rgTeamUnpaidWk LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ociscata/rgTeamUnpaidWkRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgTeamUnpaidWkRecordGroup() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			final String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			recordList = ociscataService.rgTeamUnpaidWkRecordGroup(userName);
		} catch (Exception e) {
			logger.error("In rgTeamUnpaidWkRecordGroup method : ", e);
		}
		return recordList;
	}

	/**
	 * getting rgProviderDtto LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ociscata/rgProviderDttoRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgProviderDttoRecordGroup() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ociscataService.rgProviderDttoRecordGroup();
		} catch (Exception e) {
			logger.error("In rgProviderDttoRecordGroup method : ", e);
		}
		return recordList;
	}

	/**
	 * getting rgTeamAcp LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ociscata/rgTeamAcpRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgTeamAcpRecordGroup() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			final String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			recordList = ociscataService.rgTeamAcpRecordGroup(userName);
		} catch (Exception e) {
			logger.error("In rgTeamAcpRecordGroup method : ", e);
		}
		return recordList;
	}

	/**
	 * getting rgAcpProviderInst LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ociscata/rgAcpProviderInstRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgAcpProviderInstRecordGroup(
			@RequestParam(value = "caseloadId") final String caseloadId) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ociscataService.rgAcpProviderInstRecordGroup(caseloadId);
		} catch (Exception e) {
			logger.error("In rgAcpProviderInstRecordGroup method : ", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ociscata/vCrsActExecuteQuery", method = RequestMethod.POST)
	public List<VCourseActivities> vCrsActExecuteQuery(@RequestBody final VCourseActivities searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<VCourseActivities> searchResult = new ArrayList<>();
		try {
			searchResult = ociscataService.vCrsActExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In vCrsActExecuteQuery method : ", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ociscata/setupDefaults", method = RequestMethod.GET)
	public String setupDefaults(@RequestParam(value = "listSeq") final BigDecimal listSeq) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		String returnValue = null;
		try {
			returnValue = ociscataService.setupDefaults(listSeq);
		} catch (Exception e) {
			logger.error("In setupDefaults method : ", e);
		}
		return returnValue;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ociscata/getDefaultDomain", method = RequestMethod.GET)
	public String getDefaultDomain() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		String returnValue = null;
		try {
			returnValue = ociscataService.getDefaultDomain();
		} catch (Exception e) {
			logger.error("In getDefaultDomain method : ", e);
		}
		return returnValue;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ociscata/getDefaultAgency", method = RequestMethod.GET)
	public String getDefaultAgency(@RequestParam(value = "caseloadId") final String caseloadId) {
		String returnValue = null;
		try {
			returnValue = ociscataService.getDefaultAgency(caseloadId);
		} catch (Exception e) {
			logger.error("In getDefaultAgency method : ", e);
		}
		return returnValue;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ociscata/getDescCode", method = RequestMethod.GET)
	public String getDescCode(@RequestParam(value = "strCode") final String strCode,
			@RequestParam(value = "strDesc") final String strDesc) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		String returnValue = null;
		try {
			returnValue = ociscataService.getDescCode(strCode, strDesc);
		} catch (Exception e) {
			logger.error("In getDescCode method : ", e);
		}
		return returnValue;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ociscata/getAccProgram", method = RequestMethod.GET)
	public String getAccProgram(@RequestParam(value = "programId") final BigDecimal programId) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		String returnValue = null;
		try {
			returnValue = ociscataService.getAccProgram(programId);
		} catch (Exception e) {
			logger.error("In getAccProgram method : ", e);
		}
		return returnValue;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean //
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ociscata/getProgramsNonAssTmp", method = RequestMethod.POST)
	public ProgramsNonAssocTmp getProgramsNonAssTmp(@RequestBody final ProgramsNonAssocTmp objPrograms) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		ProgramsNonAssocTmp objProg = new ProgramsNonAssocTmp();
		try {
			objProg = ociscataService.getProgramsNonAssTmp(objPrograms);
		} catch (Exception e) {
			logger.error("In getProgramsNonAssTmp method : ", e);
		}
		return objProg;
	}

	/**
	 * This method will get the default agency code
	 * 
	 * @param caseloadId
	 * @return ReferenceCodes
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ociscata/getCommDefaults", method = RequestMethod.GET)
	public ReferenceCodes getCommDefaults(@RequestParam(value = "crystalId") final String caseloadId) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		ReferenceCodes objRef = null;
		try {
			objRef = ociscataService.getCommDefaults(caseloadId);
		} catch (Exception e) {
			logger.error("In getCommDefaults method : ", e);
		}
		return objRef;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ociscata/vCrsActWhenNewRecordInstance", method = RequestMethod.GET)
	public Integer vCrsActWhenNewRecordInstance(@RequestParam(value = "crystalId") final Long crystalId) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		Integer returnValue = null;
		try {
			returnValue = ociscataService.vCrsActWhenNewRecordInstance(crystalId);
		} catch (Exception e) {
			logger.error("In setupDefaults method : ", e);
		}
		return returnValue;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ociscata/checkNonAssociationConflict", method = RequestMethod.POST)
	public List<CourseActivities> checkNonAssociationConflict(
			@RequestBody List<CourseActivities> courseActivitiesList) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			if (courseActivitiesList != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				courseActivitiesList.get(0).setCreateUserId(userName);
			}
			return ociscataService.checkNonAssociationConflict(courseActivitiesList);
		} catch (Exception e) {
			logger.error("In checkNonAssociationConflict method : ", e);
			return null;
		}
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ociscata/checkNonAssociationConflictWithAllocatedOffenders", method = RequestMethod.POST)
	public List<VOffenderPrgObligations> checkNonAssociationConflictWithAllocatedOffenders(
			@RequestBody CourseActivities courseActivities) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			return ociscataService.checkNonAssociationConflictWithAllocatedOffenders(courseActivities);
		} catch (Exception e) {
			logger.error("In checkNonAssociationConflictWithAllocatedOffenders method : ", e);
			return null;
		}
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ociscata/checkNonAssociationConflictByIndAndGang", method = RequestMethod.POST)
	public List<CourseActivities> checkNonAssociationConflictByIndAndGang(
			@RequestBody List<CourseActivities> courseActivitiesList) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<CourseActivities> courseActivitieslist = new ArrayList<CourseActivities>();
		try {
			if (courseActivitiesList != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				courseActivitiesList.get(0).setCreateUserId(userName);
			}
			courseActivitieslist = ociscataService.checkNonAssociationConflictByIndAndGang(courseActivitiesList);
		} catch (Exception e) {
			logger.error("In checkNonAssociationConflict method : ", e);
			courseActivitieslist = null;
		}
		return courseActivitieslist;
	}

		@PreAuthorize("hasEliteRole('no')")
		@RequestMapping(value = "/ociscata/checkNonAssociationConflictWithAllocatedOffendersByIndAndGang", method = RequestMethod.POST)
		public CourseActivities checkNonAssociationConflictWithAllocatedOffendersByIndAndGang(
				@RequestBody CourseActivities courseActivitiesList) {
			if(!checkCallFormAccess("read")) {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN);
			}
			try {
				courseActivitiesList=  ociscataService.checkNonAssociationConflictWithAllocatedOffendersByIndAndGang(courseActivitiesList);
			} catch (Exception e) {
				logger.error("In checkNonAssociationConflict method : ", e);
				return null;
			}
			return courseActivitiesList;
		}
		
		@PreAuthorize("hasEliteRole('no')")
		@RequestMapping(value = "/ociscata/vOffPrgProfilesExecuteQuery", method = RequestMethod.POST)
		public List<VOffenderProgramProfiles> vOffPrgProfilesExecuteQuery(
				@RequestBody final VOffenderProgramProfiles searchBean) {
			if(!checkCallFormAccess("read")) {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN);
			}
			List<VOffenderProgramProfiles> searchResult = new ArrayList<>();
			try {
				searchResult = ocupaoffService.vOffPrgProfilesExecuteQuery(searchBean);
			} catch (Exception e) {
				VOffenderProgramProfiles bean = new VOffenderProgramProfiles();
				logger.error("Exception :", e);
				bean.setErrorMessage(e.getMessage());
				searchResult.add(bean);
			}
			return searchResult;
		}
		@PreAuthorize("hasEliteRole('no')")
		@RequestMapping(value = "/ociscata/checkNonAssociationsWorkRelease", method = RequestMethod.POST)
		public String checkNonAssociations(@RequestBody final OffenderProgramProfilesCommitBean commitBean) {
			if(!checkCallFormAccess("read")) {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN);
			}
			String resultString = null;
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			try {
				resultString = oidowrelService.checkNonAssociations(commitBean);
			} catch (final Exception e) {
				logger.error("Error occured in checkNonAssociationsWorkRelease :", e);
			}
			return resultString;
		}
		@PreAuthorize("hasEliteRole('no')")
		@RequestMapping(value = "/ociscata/checkNonAssociationsSpecializedPrg", method = RequestMethod.POST)
		public String checkNonAssociationsWorkRelease(@RequestBody final OffenderProgramProfilesCommitBean commitBean) {
			if(!checkCallFormAccess("read")) {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN);
			}
			String resultString = null;
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			try {
				resultString = ocdxprogService.checkNonAssociations(commitBean);
			} catch (final Exception e) {
				logger.error("Error occured in checkNonAssociationsWorkRelease :", e);
			}
			return resultString;
		}
		
		private Boolean checkCallFormAccess(String role) {
			String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			return commonService.checkCallFormAccess(userId, role,"OCISCATA");
		}
}