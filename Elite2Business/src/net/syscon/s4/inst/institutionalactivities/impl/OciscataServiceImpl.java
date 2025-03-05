package net.syscon.s4.inst.institutionalactivities.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cm.programsservices.VOffenderPrgObligations;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.im.beans.OffenderStgAffiliations;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.ProgramsNonAssocTmp;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.im.beans.VCourseActivities;
import net.syscon.s4.im.beans.VOffenderProgramProfiles;
import net.syscon.s4.inst.institutionalactivities.OciscataRepository;
import net.syscon.s4.inst.institutionalactivities.OciscataService;
import net.syscon.s4.inst.institutionalactivities.OcupaoffRepository;
import net.syscon.s4.inst.movements.beans.OffRec;
import net.syscon.s4.pkgs.non_association.NonAssociationService;
import net.syscon.s4.pkgs.ocmsvacp.OcmsvacpPkgService;
import net.syscon.s4.pkgs.oms_miscellaneous.OmsMiscellaneousService;
import net.syscon.s4.pkgs.tag_establishment.TagEstablishmentService;
import net.syscon.s4.pkgs.tag_reference_codes.TagReferenceCodesService;
import net.syscon.s4.pkgs.tag_service.TagServiceService;

/**
 * Class OciscataServiceImpl
 */
@Service
public class OciscataServiceImpl extends BaseBusiness implements OciscataService {

	@Autowired
	private OciscataRepository ociscataRepository;

	@Autowired
	private TagEstablishmentService tagEstablishmentService;
	@Autowired
	private OmsMiscellaneousService omsMiscellaneousService;
	@Autowired
	private TagServiceService tagServiceService;
	@Autowired
	private NonAssociationService nonAssociationService;
	@Autowired
	private OcmsvacpPkgService ocmsvacpService;
	@Autowired
	private TagReferenceCodesService tagReferenceCodesService;

	@Autowired
	private OcupaoffRepository ocupaoffRepository;
	
	/**
	 * Creates new OciscataServiceImpl class Object
	 */
	public OciscataServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Areas> vCrsActPreQuery(final Areas paramBean) {
		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<OffenderBookings> ociscataWhenNewFormInstance(final OffenderBookings paramBean) {
		return null;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<VCourseActivities> vCrsActExecuteQuery(final VCourseActivities searchRecord) {
		return ociscataRepository.vCrsActExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstV_CRS_ACT
	 *
	 */
	/**
	 * This method is used to execute a record group
	 * 
	 * @return
	 *
	 */

	public List<ReferenceCodes> rgAreasRecordGroup(final String environment, final String region) {
		return ociscataRepository.rgAreasRecordGroup(environment, region);

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgPsAgeRangeRecordGroup() {
		return ociscataRepository.rgPsAgeRangeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgPsAvailRecordGroup() {
		return ociscataRepository.rgPsAvailRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgPsCategoryRecordGroup() {
		return ociscataRepository.rgPsCategoryRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgPsNeedsRecordGroup() {
		return ociscataRepository.rgPsNeedsRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgPsOffGrpsRecordGroup() {
		return ociscataRepository.rgPsOffGrpsRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgPsProvTypeRecordGroup() {
		return ociscataRepository.rgPsProvTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgPsSexRecordGroup() {
		return ociscataRepository.rgPsSexRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgEthnicityRecordGroup() {
		return ociscataRepository.rgEthnicityRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgRegionRecordGroup() {
		return ociscataRepository.rgRegionRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgServicesRecordGroup(final String category) {
		return ociscataRepository.rgServicesRecordGroup(category);

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgCsldCodeRecordGroup() {
		return ociscataRepository.rgCsldCodeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<Teams> rgTeamAgyLocsRecordGroup() {
		final List<Teams> teamList = ociscataRepository.rgTeamAgyLocsRecordGroup();
		for (final Teams obj : teamList) {
			obj.setCode(obj.getTeamCode());
		}
		return teamList;

	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @return
	 *
	 */
	public List<ReferenceCodes> rgCorpLocsRecordGroup(final String category) {
		return ociscataRepository.rgCorpLocsRecordGroup(category);

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgAgyLocsRecordGroup() {
		return ociscataRepository.rgAgyLocsRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @return
	 *
	 */
	public List<ReferenceCodes> rgAgyLocClRecordGroup() {
		return ociscataRepository.rgAgyLocClRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @return
	 *
	 */

	public List<ReferenceCodes> rgTeamUnpaidWkRecordGroup(final String userName) {
		return ociscataRepository.rgTeamUnpaidWkRecordGroup(userName);

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgProviderDttoRecordGroup() {
		return ociscataRepository.rgProviderDttoRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @return
	 *
	 */

	public List<ReferenceCodes> rgTeamAcpRecordGroup(final String userName) {
		return ociscataRepository.rgTeamAcpRecordGroup(userName);

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgAcpProviderInstRecordGroup(final String caseloadId) {
		return ociscataRepository.rgAcpProviderInstRecordGroup(caseloadId);

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * setupDefaults
	 *
	 * @param params
	 *
	 */
	public String setupDefaults(final BigDecimal listSeq) {
		return ociscataRepository.setupDefaults(listSeq);
	}
	/**
	 * This method get the default status code
	 */
	public String getDefaultDomain() {
		return tagReferenceCodesService.defaultDomain("PS_AVAIL");
	}

	/**
	 * This method will get the default agency code
	 * 
	 * @return String
	 */
	public String getDefaultAgency(final String caseloadId) {
		return tagEstablishmentService.defaultAgency(caseloadId);
	}

	/**
	 * This method will get the default desc code
	 */
	public String getDescCode(final String strCode, final String strDesc) {
		return omsMiscellaneousService.getDescCode(strCode, strDesc);
	}

	/**
	 * This method will get the program description
	 */
	public String getAccProgram(final BigDecimal programId) {
		return ocmsvacpService.getAccProgram(programId);
	}

	/**
	 * This method will get Programs
	 */
	public ProgramsNonAssocTmp getProgramsNonAssTmp(final ProgramsNonAssocTmp objProgramsNon) {

		final Integer getTempCount = getProgramsNonAssTmpCount();
		ProgramsNonAssocTmp objProg = null;
		final ProgramsNonAssocTmp objProgNonTmp = new ProgramsNonAssocTmp();
		Map<String, Object> returnObject = null;
		Map<String, Object> returngetWarn = null;
		String conflict = null;
		if (getTempCount > 0) {
			final List<ProgramsNonAssocTmp> lstPrograms = (List<ProgramsNonAssocTmp>) ociscataRepository
					.getProgramsNonAssTmp();
			for (final ProgramsNonAssocTmp objPrograms : lstPrograms) {
				if (objPrograms.getOffenderId() != null && objPrograms.getOffenderBookId() != null) {
					if ("ACP".equals(objProgramsNon.getWarningMsg())) {
						returnObject = nonAssociationService.chkNaPrgSrvConflictRt(objPrograms,
								String.valueOf(objPrograms.getCoursePhaseId()));
						conflict = returnObject.get("P_CONFLICT_FLAG").toString();
						if (conflict != null && "Y".equals(conflict)) {
							final List<OffRec> st = (List<OffRec>) returnObject.get("P_OFF_INFO");
							returngetWarn = nonAssociationService
									.getConflictWarning(objPrograms.getOffenderId().longValue(), st);
							objProg.setWarningMsg(String.valueOf(returngetWarn.get("P_WARNING_MSG")));
							objProg.setWarningPrompt(String.valueOf(returngetWarn.get("P_WARNING_PROMPT")));
						}
					} else {
						returnObject = nonAssociationService.chkNaPrgSrvConflictRt(objPrograms,
								String.valueOf(objPrograms.getCoursePhaseId()));
						conflict = returnObject.get("P_CONFLICT_FLAG").toString();
						if (conflict != null && "Y".equals(conflict)) {
							final List<OffRec> st = (List<OffRec>) returnObject.get("P_OFF_INFO");
							returngetWarn = nonAssociationService
									.getConflictWarning(objPrograms.getOffenderId().longValue(), st);
							objProg.setWarningMsg(String.valueOf(returngetWarn.get("P_WARNING_MSG")));
							objProg.setWarningPrompt(String.valueOf(returngetWarn.get("P_WARNING_PROMPT")));
						}
					}
				}
			}
		} else {
			if (objProgramsNon.getLvRootOffenderId() != null && objProgramsNon.getLvOffenderId() != null) {
				objProgNonTmp.setOffenderId(objProgramsNon.getLvOffenderId());
				objProgNonTmp.setOffenderBookId(objProgramsNon.getLvRootOffenderId());
				if ("ACP".equals(objProgramsNon.getWarningMsg())) {
					returnObject = nonAssociationService.chkNaPrgSrvConflictRt(objProgNonTmp,
							String.valueOf(objProgramsNon.getCoursePhaseId()));
					conflict = returnObject.get("P_CONFLICT_FLAG").toString();
					if (conflict != null && "Y".equals(conflict)) {
						final List<OffRec> st = (List<OffRec>) returnObject.get("P_OFF_INFO");
						returngetWarn = nonAssociationService
								.getConflictWarning(objProgNonTmp.getOffenderId().longValue(), st);
						objProg.setWarningMsg(String.valueOf(returngetWarn.get("P_WARNING_MSG")));
						objProg.setWarningPrompt(String.valueOf(returngetWarn.get("P_WARNING_PROMPT")));
					}
				} else {
					returnObject = nonAssociationService.chkNaPrgSrvConflictRt(objProgNonTmp,
							String.valueOf(objProgNonTmp.getCrsActyId()));
					conflict = returnObject.get("P_CONFLICT_FLAG").toString();
					if (conflict != null && "Y".equals(conflict)) {
						final List<OffRec> st = (List<OffRec>) returnObject.get("P_OFF_INFO");
						returngetWarn = nonAssociationService
								.getConflictWarning(objProgNonTmp.getOffenderId().longValue(), st);
						objProg.setWarningMsg(String.valueOf(returngetWarn.get("P_WARNING_MSG")));
						objProg.setWarningPrompt(String.valueOf(returngetWarn.get("P_WARNING_PROMPT")));
					}
				}
			}

		}
		return objProg;
	}

	/**
	 * This method will get the count of programs non ass tmp
	 */
	public Integer getProgramsNonAssTmpCount() {
		return ociscataRepository.getProgramsNonAssTmpCount();
	}

	/**
	 * This method will get the default agency code
	 * 
	 * @param caseloadId
	 * @return ReferenceCodes
	 */
	public ReferenceCodes getCommDefaults(final String caseloadId) {
		return tagServiceService.getCommDefault(caseloadId);
	}

	public Integer vCrsActWhenNewRecordInstance(final Long crystalId) {
		return ociscataRepository.vCrsActWhenNewRecordInstance(crystalId);
	}

	@Override
	public List<CourseActivities> checkNonAssociationConflict(List<CourseActivities> courseActivitiesList) {
		for (CourseActivities obj : courseActivitiesList) {
			String userName = courseActivitiesList.get(0).getCreateUserId();
			List<OffenderNonAssociations> nonAssList = ociscataRepository.checkNonAssociations(obj.getOffenderBookId(),
					userName);
			if (nonAssList != null && nonAssList.size() > 0) {
				obj.setOffenderNonAssociations(nonAssList);
			} else {
				obj.setOffenderNonAssociations(null);
			}
		}
		return courseActivitiesList;
	}

	@Override
	public List<VOffenderPrgObligations> checkNonAssociationConflictWithAllocatedOffenders(
			CourseActivities courseActivities) {
		List<VOffenderProgramProfiles> listOfAllocOffenders = new ArrayList<VOffenderProgramProfiles>();
		if (courseActivities != null && courseActivities.getPrgServiceList() != null
				&& courseActivities.getPrgServiceList().size() > 0) {
			for (VCourseActivities obj : courseActivities.getPrgServiceList()) {
				VOffenderProgramProfiles objSearchDao = new VOffenderProgramProfiles();
				objSearchDao.setCrsActyId(obj.getCoursePhaseId());
				List<VOffenderProgramProfiles> listOfAllocOffendersTwo = ocupaoffRepository
						.vOffPrgProfilesExecuteQuery(objSearchDao);
				listOfAllocOffenders.addAll(listOfAllocOffendersTwo);
			}
		}
		if (!listOfAllocOffenders.isEmpty()) {
			if (courseActivities.getOffenderList() != null && courseActivities.getOffenderList().size() > 0) {
				for (VOffenderPrgObligations offenderObj : courseActivities.getOffenderList()) {
					String Msg = null;
					int offenderCount = 1;
					for (VOffenderProgramProfiles activeOffenderObj : listOfAllocOffenders) {
						Integer count = ociscataRepository.checkNonAssociationConflict(offenderObj.getOffenderBookId(),
								new BigDecimal(activeOffenderObj.getOffenderBookId()));
						if (count > 0) {
							if (Msg == null) {
								Msg = "ociscata.offender " + offenderObj.getOffenderName() + " , "
										+ offenderObj.getOffenderId() + " ociscata.hasNonAssociation " + " \n" + " "
										+ offenderCount + ") " + activeOffenderObj.getOffenderName() + " , "
										+ activeOffenderObj.getOffenderId() + " \n";
								offenderCount++;
							} else {
								Msg = Msg + " " + offenderCount + ") " + activeOffenderObj.getOffenderName() + " , "
										+ activeOffenderObj.getOffenderId() + " \n";
								offenderCount++;
							}
						}
					}
					if (Msg != null) {
						Msg = Msg + " \n\n " + "ociscata.doyouwanttoproceed";
						offenderObj.setConflictMsg(Msg);
					} else {
						offenderObj.setConflictMsg(null);
					}
				}
			}
		} else {
			return new ArrayList<VOffenderPrgObligations>();
		}
		return courseActivities.getOffenderList();
	}

	@Override
	public List<CourseActivities> checkNonAssociationConflictByIndAndGang(List<CourseActivities> courseActivitiesList) {
		List<Integer> offenderBookIdList = new ArrayList<Integer>();
		for (CourseActivities offebderBookId : courseActivitiesList) {
			offenderBookIdList.add(offebderBookId.getOffenderBookId().intValue());
		}
		for (int i = 0; i < courseActivitiesList.size(); i++) {
			List<Offenders> offenderInd = new ArrayList<Offenders>();
			List<Offenders> offenderGang = new ArrayList<Offenders>();
			List<OffenderNaDetails> nonAssList = nonAssociationService
					.getNonAssociationOffenderList(courseActivitiesList.get(i).getOffenderBookId().intValue());
			List<OffenderStgAffiliations> nonAssListGang = nonAssociationService
					.getOffendersOfNonAssociationGroup(courseActivitiesList.get(i).getOffenderBookId());
			if (nonAssList != null && nonAssList.size() > 0) {
				for (OffenderNaDetails offenderNaDetails : nonAssList) {
					if (offenderBookIdList.contains(offenderNaDetails.getNsOffenderBookId().intValue())) {

						List<Offenders> offender = ociscataRepository
								.getOffenderDetails(offenderNaDetails.getNsOffenderBookId());
						offender.get(0).setOffenderBookId(offenderNaDetails.getNsOffenderBookId().longValue());
						offenderInd.addAll(offender);
					}
				}
			}

			if (nonAssListGang != null && nonAssListGang.size() > 0) {
				for (OffenderStgAffiliations offenderStgAffiliations : nonAssListGang) {
					if (offenderBookIdList.contains(offenderStgAffiliations.getOffenderBookId().intValue())) {
						List<Offenders> offender = ociscataRepository
								.getOffenderDetails(offenderStgAffiliations.getOffenderBookId());
						offender.get(0).setOffenderBookId(offenderStgAffiliations.getOffenderBookId().longValue());
						offenderGang.addAll(offender);
					}

				}
			}
			if ((offenderGang != null && offenderGang.size() > 0) || (offenderInd != null && offenderInd.size() > 0)) {
				courseActivitiesList.get(i)
						.setOffenderName(ociscataRepository.offenderName(courseActivitiesList.get(i).getOffenderId()));
			}
			
			if (offenderGang != null && offenderGang.size() > 0)
				courseActivitiesList.get(i).setOffenderNonAssociationsByGang(offenderGang);
			else
				courseActivitiesList.get(i).setOffenderNonAssociationsByGang(null);
			if (offenderInd != null && offenderInd.size() > 0)
				courseActivitiesList.get(i).setOffenderNonAssociationsByInd(offenderInd);
			else
				courseActivitiesList.get(i).setOffenderNonAssociationsByInd(null);
		}

		return courseActivitiesList;
	}

	@Override
	public CourseActivities checkNonAssociationConflictWithAllocatedOffendersByIndAndGang(
			CourseActivities courseActivities) {
		List<Integer> listOfProgramIds = new ArrayList<Integer>();
		for (VCourseActivities vCourseActivities : courseActivities.getPrgServiceList()) {
			listOfProgramIds.add(vCourseActivities.getCoursePhaseId().intValue());

		}

		for (VOffenderPrgObligations vOffenderPrgObligations : courseActivities.getOffenderList()) {

			vOffenderPrgObligations.setOffenderName(
					ociscataRepository.offenderName(vOffenderPrgObligations.getOffenderId().longValue()));
			
			List<Offenders> nonAssocationByInd = ociscataRepository
					.getOffenderDetailsByInd(vOffenderPrgObligations.getOffenderBookId(), listOfProgramIds);
			vOffenderPrgObligations.setOffenderNonAssociationsByInd(nonAssocationByInd);

			List<Offenders> nonAssocationByGang = ociscataRepository
					.getOffenderDetailsByGang(vOffenderPrgObligations.getOffenderBookId(), listOfProgramIds);
			vOffenderPrgObligations.setOffenderNonAssociationsByGang(nonAssocationByGang);

			String indAndGang = getOffenderDetails(vOffenderPrgObligations.getOffenderNonAssociationsByInd(),
					vOffenderPrgObligations.getOffenderNonAssociationsByGang());
			
			vOffenderPrgObligations.setNonAssocationByIngAndGang(indAndGang);

		}

		return courseActivities;
	}

	public String getOffenderDetails(List<Offenders> offendersListInd, List<Offenders> offendersListGang) {
		String offenderDetails = "";
		String offenderDetailsInd = "";
		String offenderDetailsGang = "";
		if (offendersListInd != null && offendersListInd.size() > 0) {
			for (Offenders offenders : offendersListInd) {
				String name = offenders.getLastName() +","+ offenders.getFirstName();
				String offenderId ="(PID: "+ offenders.getOffenderIdDisplay()+")";
				offenderDetailsInd = offenderDetailsInd+ name + " " + offenderId + "\n";
			}
		}
		if (offendersListGang != null && offendersListGang.size() > 0) {
			for (Offenders offenders : offendersListGang) {
				String name = offenders.getLastName() +","+ offenders.getFirstName();
				String offenderId = "(PID: "+offenders.getOffenderIdDisplay()+")";
				offenderDetailsGang = offenderDetailsGang+name + " " + offenderId + "\n";
			}
		}
		if (offenderDetailsInd.length() > 0 && offenderDetailsGang.length() > 0) {
			offenderDetails = "ociscata.indinonassocconflict:\n" + offenderDetailsInd+"\n\n"
					+ "ociscata.gangnonassocconflict:\n" + offenderDetailsGang+"\n\n";
		} else if (offenderDetailsInd.length() > 0) {
			offenderDetails = "ociscata.indinonassocconflict:\n" + offenderDetailsInd+"\n\n";
		} else if (offenderDetailsGang.length() > 0) {
			offenderDetails = "ociscata.gangnonassocconflict:\n" + offenderDetailsGang+"\n\n";
		} 
		if(offenderDetails.length()>0)
			return offenderDetails;
		else
		return ApplicationConstants.EMPTYDATA;
	}
}
