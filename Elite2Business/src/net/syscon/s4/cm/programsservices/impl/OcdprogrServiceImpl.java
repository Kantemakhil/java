
package net.syscon.s4.cm.programsservices.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasures;
import net.syscon.s4.cm.programsservices.OcdprogrRepository;
import net.syscon.s4.cm.programsservices.OcdprogrService;
import net.syscon.s4.cm.programsservices.OffenderCourseAttendancesCommitBean;
import net.syscon.s4.cm.programsservices.VAcpProgress;
import net.syscon.s4.cm.programsservices.VAcpProgressCommitBean;
import net.syscon.s4.cm.programsservices.VOffenderPrgObligations;
import net.syscon.s4.cm.programsservices.VOffenderPrgObligationsCommitBean;
import net.syscon.s4.cm.weeklyactivityplans.WeeklyActivityPlans;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.AgyIntLocProfiles;
import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.InternalLocationUsages;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.im.beans.OffenderPrgObligations;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.im.beans.OffenderProgramProfilesCommitBean;
import net.syscon.s4.im.beans.OffenderStgAffiliations;
import net.syscon.s4.im.beans.VOffenderCourseAttendances;
import net.syscon.s4.im.beans.VOffenderProgramProfiles;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.OcupaoffRepository;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmpssetService;
import net.syscon.s4.inst.legals.beans.OffenderSentences;
import net.syscon.s4.inst.legals.beans.VOffenderSentenceEvents;
import net.syscon.s4.inst.programswithoutschedules.OcdxprogService;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.iwp.OcdclogsRepository;
import net.syscon.s4.iwp.impl.OcdclogsServiceImpl;
import net.syscon.s4.pkgs.non_association.NonAssociationRepository;
import net.syscon.s4.pkgs.non_association.NonAssociationService;
import net.syscon.s4.pkgs.tag_multiple_failure.TagMultipleFailureService;
import net.syscon.s4.pkgs.tag_prg.TagPrgService;
import net.syscon.s4.pkgs.tag_programmes.TagProgrammesService;
import net.syscon.s4.pkgs.tag_service.TagServiceService;
import net.syscon.s4.triggers.OffenderCourseAttendances;
import net.syscon.s4.triggers.OffenderCourseAttendancesT1Service;
import net.syscon.s4.triggers.OffenderCourseAttndnsTwfService;
import net.syscon.s4.triggers.OffenderPrgObligationsT1Service;
import net.syscon.s4.triggers.OffenderPrgObligationsT2Service;
import net.syscon.s4.triggers.OffenderProgramProfilesTrService;
import net.syscon.s4.triggers.VOffenderPrgObligationsTiService;

/**
 * Class OcdprogrServiceImpl
 */
@Service
public class OcdprogrServiceImpl extends BaseBusiness implements OcdprogrService {

	@Autowired
	private OcdprogrRepository ocdprogrRepository;
	@Autowired
	private OcdclogsRepository ocdclogsRepository;
	@Autowired
	private EliteDateService eliteDateService;

	@Autowired
	private TagServiceService tagServiceService;
	@Autowired
	private TagProgrammesService tagProgrammesService;
	@Autowired
	private TagPrgService tagPrgService;
	@Autowired
	private TagMultipleFailureService tagMultipleFailureService;
	@Autowired
	private OffenderPrgObligationsT1Service offenderPrgObligationsT1Service;
	@Autowired
	private OffenderPrgObligationsT2Service offenderPrgObligationsT2Service;
	@Autowired
	private OffenderProgramProfilesTrService offenderProgramProfilesTrService;
	@Autowired
	private VOffenderPrgObligationsTiService vOffenderPrgObligationsTiService;
	@Autowired
	private OffenderCourseAttndnsTwfService OffenderCourseAttndnsTwfService;
	@Autowired
	private OffenderCourseAttendancesT1Service offenderCourseAttendancesT1Service;

	@Autowired
	private OcdxprogService ocdxprogRepository;
	@Autowired
	private NonAssociationService nonAssociationService;
	@Autowired
	private OcupaoffRepository ocupaoffRepository;
	@Autowired
	private NonAssociationRepository nonAssociationRepository;
	@Autowired
	private OcmpssetService ocmpssetService;
	
	@Autowired
	private OcdclogsServiceImpl ocdclogsServiceImpl;
	

	private static Logger logger = LogManager.getLogger(OcdprogrServiceImpl.class.getName());

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<VOffenderPrgObligations> offBkgOnCheckDeleteMaster(VOffenderPrgObligations paramBean) {
		return ocdprogrRepository.offBkgOnCheckDeleteMaster(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<VOffenderPrgObligations> vOffPrgOblExecuteQuery(VOffenderPrgObligations searchRecord) {
		List<VOffenderPrgObligations> returnList = new ArrayList<VOffenderPrgObligations>();
		returnList = ocdprogrRepository.vOffPrgOblExecuteQuery(searchRecord);
		if (returnList != null && !returnList.isEmpty()) {
			List<OffenderSentences> sentences = new ArrayList<OffenderSentences>();
			sentences = rgOffenderSentencesRecordGroup(searchRecord.getOffenderBookId().intValue());
			for (VOffenderPrgObligations prgObj : returnList) {
				for (OffenderSentences senObj : sentences) {
					if (prgObj.getSentenceSeq() != null
							&& (prgObj.getSentenceSeq().equals(new BigDecimal(senObj.getSentenceSeq())))) {
						prgObj.setSentenceStartDate(senObj.getStartDate());
						prgObj.setSentenceEndDate(senObj.getEndDate());
						prgObj.setSentenceDesc(senObj.getDescription());
						prgObj.setCourtName(senObj.getJurisCode());
						prgObj.setSentenceCategory(senObj.getSentenceCategory());
					}
				}
			}
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstV_OFF_PRG_OBL
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer vOffPrgOblCommit(VOffenderPrgObligationsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			final List<BigDecimal> offBkgId = commitBean.getInsertList().stream().map(data -> data.getOffenderBookId())
					.collect(Collectors.toList());
			final List<BigDecimal> programid = commitBean.getInsertList().stream().map(data -> data.getProgramId())
					.collect(Collectors.toList());
			for (VOffenderPrgObligations bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			final Integer childCount = ocdprogrRepository.checkActiveObligation(offBkgId, programid);
			if (childCount > 0) {
				return ApplicationConstants.INTCONSTANTVALUE;
			}

			for (VOffenderPrgObligations bean : commitBean.getInsertList()) {
				List<VOffenderPrgObligations> list = new ArrayList<VOffenderPrgObligations>();
				BigDecimal offenderPrgId = ocdprogrRepository.gettingOffenderPrgObligationId();
				bean.setOffenderPrgObligationId(offenderPrgId);
				list.add(bean);
				liReturn = ocdprogrRepository.vOffPrgOblInsertVOffenderPrgObligations(list);
				OffenderPrgObligations newRec = new OffenderPrgObligations();
				newRec.setOffenderPrgObligationId(
						bean.getOffenderPrgObligationId() != null ? bean.getOffenderPrgObligationId().longValue()
								: null);
				newRec.setStatus(bean.getStatus());
				newRec.setStatusChangeReason(bean.getStatusChangeReason());
				newRec.setCreateUserId(bean.getCreateUserId());
				newRec.setProgramId(bean.getProgramId().longValue());
				newRec.setOffenderBookId(
						bean.getOffenderBookId() != null ? bean.getOffenderBookId().longValue() : null);
				newRec.setStatusChangeDate(bean.getReferralDate());
				offenderPrgObligationsT1Service.offenderPrgObligationsT1(newRec, null);
				offenderPrgObligationsT2Service.offenderPrgObligationsT2(newRec);
			}

		}
		// updateRecords
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (VOffenderPrgObligations bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
				OffenderPrgObligations old = ocdprogrRepository.gettingOldData(bean.getOffenderPrgObligationId());
				liReturn = ocdprogrRepository.vOffPrgOblUpdateVOffenderPrgObligations(commitBean.getUpdateList());
				OffenderPrgObligations newRec = new OffenderPrgObligations();
				newRec.setOffenderPrgObligationId(bean.getOffenderPrgObligationId().longValue());
				newRec.setStatus(bean.getStatus());
				newRec.setStatusChangeReason(bean.getStatusChangeReason());
				newRec.setCreateUserId(bean.getCreateUserId());
				newRec.setStatusChangeDate(bean.getReferralDate());
				offenderPrgObligationsT1Service.offenderPrgObligationsT1(newRec, old);
			}

		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			for (VOffenderPrgObligations bean : commitBean.getDeleteList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocdprogrRepository.vAcpProgressPreDelete(commitBean.getDeleteList());
			liReturn = ocdprogrRepository.vOffPrgOblDeleteVOffenderPrgObligations(commitBean.getDeleteList());
			for (VOffenderPrgObligations old : commitBean.getDeleteList()) {
				String operation = "deleting";
				liReturn = vOffenderPrgObligationsTiService.vOffenderPrgObligationsTiTgr(old, null, operation);
			}

		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<VAcpProgress> vAcpProgressExecuteQuery(VAcpProgress searchRecord) {
		return ocdprogrRepository.vAcpProgressExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstV_ACP_PROGRESS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer vAcpProgressCommit(VAcpProgressCommitBean commitBean) {
		int liReturn = 0;
		// updateRecords
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (VAcpProgress bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
				OffenderProgramProfiles newRec = new OffenderProgramProfiles();
				newRec = ocdprogrRepository.gettingOffenderProgramStatus(bean.getOffPrgrefId());
				if (newRec.getOffenderProgramStatus() != null) {
					newRec.setOffenderProgramStatus(newRec.getOffenderProgramStatus());
				}
				newRec.setOffenderEndDate(newRec.getOffenderEndDate());
				newRec.setWaitlistDecisionCode(newRec.getWaitlistDecisionCode());
				newRec.setCrsActyId(newRec.getCrsActyId());
				offenderProgramProfilesTrService.offenderProgramProfilesTr(newRec);
				liReturn = ocdprogrRepository.vAcpProgressUpdateVAcpProgress(commitBean.getUpdateList());
			}
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderProgramProfiles> offPgmProfExecuteQuery(OffenderProgramProfiles searchRecord) {
		List<OffenderProgramProfiles> returnlist = ocdprogrRepository.offPgmProfExecuteQuery(searchRecord);
		for (OffenderProgramProfiles bean : returnlist) {
			bean.setProgramLastEventDate(ocdprogrRepository.getProgramLastEventDateTime(bean.getOffPrgrefId()));
			ProgramServices returnbean = ocdprogrRepository.getPrgSrvDetails(bean);
			bean.setPhaseDesc(returnbean.getDescription());
			bean.setModuleFlag(returnbean.getModuleFlag());
			OffenderProgramProfiles offprgBean = tagServiceService.getAllocationInfo(bean.getCrsActyId());
			bean.setProviderName(offprgBean.getProviderName());
			bean.setOccuranceCode(offprgBean.getOccuranceCode());
			if ("Y".equals(bean.getModuleFlag())) {
				OffenderProgramProfiles prgBean = tagProgrammesService.getAllocationListSeqRange(bean);
				bean.setModuleFrom(prgBean.getModuleFrom());
				bean.setModuleTo(prgBean.getModuleTo());
			}
		}
		return returnlist;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_PGM_PROF
	 *
	 * @throws SQLException
	 */
	@Transactional
	public OffenderProgramProfiles offPgmProfCommit(OffenderProgramProfilesCommitBean commitBean) {
		OffenderProgramProfiles retBean = new OffenderProgramProfiles();
		Boolean lvOverlap = false;
		Integer liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (OffenderProgramProfiles bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
				if ("Y".equals(bean.getModuleFlag())) {
					lvOverlap = tagProgrammesService.checkModuleAllocated(bean);
				} else {
					lvOverlap = tagProgrammesService.checkSessionAllocated(bean);
				}
				if (lvOverlap) {
					retBean.setSealFlag("2");
					return retBean;
				}
				bean.setOffPrgrefId(ocdprogrRepository.getOffPrgrefId());
				Integer programOffPrgrefId = ocdprogrRepository.getOffPrgrefIdOne(bean.getOffenderPrgObligationId(),
						bean.getProgramId());
				if (programOffPrgrefId != null) {
					bean.setProgramOffPrgrefId(programOffPrgrefId.longValue());
				}

				offenderProgramProfilesTrService.offenderProgramProfilesTr(bean);
			}
			liReturn = ocdprogrRepository.offPgmProfInsertOffenderProgramProfiles(commitBean.getInsertList());
			if (liReturn == 1) {
				retBean.setSealFlag("1");
				postInsert(commitBean.getInsertList());
			}

		}
		// updateRecords
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (OffenderProgramProfiles bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
				if ("END".equals(bean.getOffenderProgramStatus()) && (bean.getOffEndDate() == null
						|| bean.getOffenderEndDate().compareTo(bean.getOffEndDate()) < 0) && // ocdprogrRepository.checkAttendanceTaken(bean)
						tagProgrammesService.checkAttendanceTaken(bean.getOffPrgrefId(), bean.getOffenderEndDate())) {
					retBean.setOffenderEndDate(bean.getOffenderEndDate());
					retBean.setSealFlag("3");
					return retBean;
				}
				if ("CANC".equals(bean.getOffenderProgramStatus()) && // ocdprogrRepository.checkAttendanceTaken(bean)
						tagProgrammesService.checkAttendanceTaken(bean.getOffPrgrefId(), bean.getOffEndDate())) {
					retBean.setSealFlag("4");
					return retBean;
				}
				offenderProgramProfilesTrService.offenderProgramProfilesTr(bean);
				liReturn = ocdprogrRepository.offPgmProfUpdateOffenderProgramProfiles(commitBean.getUpdateList());
				if (liReturn == 1) {
					for (OffenderProgramProfiles offenderProgramProfiles : commitBean.getUpdateList()) {
						if (!offenderProgramProfiles.getOffenderProgramStatus().equals("ALLOC")) {
							ocdprogrRepository.updateCourseActivitiesEmailAndSms(offenderProgramProfiles);
						}
					}
				}
			}
			if (liReturn == 1) {
				retBean.setSealFlag("1");
				commitBean.getUpdateList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
				postUpdate(commitBean.getUpdateList());
			}
		}
		return retBean;
	}

	public Integer postInsert(List<OffenderProgramProfiles> insertList) {
		int count = 0;
		for (OffenderProgramProfiles bean : insertList) {
			bean.setModifyUserId(bean.getCreateUserId());
			if ("Y".equals(bean.getModuleFlag())) {
				tagProgrammesService.updateModuleAllocations(bean);
			} else {
				if (bean.getOffenderStartDate()!=null) {
					tagProgrammesService.createOppAttendances(bean.getOffPrgrefId(), bean.getCreateUserId());//2
				}
			}
			if (!"ALLOC".equals(bean.getNbtStatus())) {
				tagProgrammesService.updateStatus(bean.getOffenderPrgObligationId(), bean.getCreateUserId());
				if(count == 0 && bean.getNbtStatus().equalsIgnoreCase("REF")) {
					bean.setStatusChangeFlag("Y");
				}else {
					bean.setStatusChangeFlag("N");
				}
				count++;
			}
		}
		return 1;
	}

	public Integer postUpdate(List<OffenderProgramProfiles> updateList) {
		for (OffenderProgramProfiles bean : updateList) {
			if ("Y".equals(bean.getModuleFlag())) {
				if (!"END".equals(bean.getOffPrgStatusDbVal()) && "END".equals(bean.getOffenderProgramStatus())) {
					tagProgrammesService.endModuleAllocations(bean);
				} else if (!"CANC".equals(bean.getOffPrgStatusDbVal())
						&& "CANC".equals(bean.getOffenderProgramStatus())) {
					tagProgrammesService.cancelModuleAllocations(bean);
				} else {
					tagProgrammesService.updateModuleAllocations(bean);
				}
			}
			if ("END".equals(bean.getOffenderProgramStatus()) && (bean.getOffEndDate() == null
					|| bean.getOffenderEndDate().compareTo(bean.getOffEndDate()) < 0)) {
				tagProgrammesService.deleteOppAttendances(bean);
			}
			if ("CANC".equals(bean.getOffenderProgramStatus())) {
				tagProgrammesService.deleteOppAttendances(bean);
			}
		}

		return 1;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderCourseAttendance> offCrsAppExecuteQuery(OffenderCourseAttendance searchRecord) {
		List<OffenderCourseAttendance> returnList = ocdprogrRepository.offCrsAppExecuteQuery(searchRecord);
		for (OffenderCourseAttendance bean : returnList) {
			OffenderCourseAttendance reurnObj = tagProgrammesService.getProgramInfo(bean.getOffPrgrefId().longValue());
			bean.setModuleId(reurnObj.getModuleId());
			bean.setPhaseId(reurnObj.getPhaseId());
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_CRS_APP
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer offCrsAppCommit(OffenderCourseAttendancesCommitBean commitBean) {
		Integer liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (OffenderCourseAttendance bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
				List<OffenderCourseAttendance> list = new ArrayList<OffenderCourseAttendance>();
				Long eventId = ocdprogrRepository.gettingEventId();
				bean.setEventId(eventId);
				list.add(bean);

				liReturn = ocdprogrRepository.offCrsAppInsertOffenderCourseAttendances(commitBean.getInsertList());
				String sqlOperation = "INSERTING";
				OffenderCourseAttendances newOj = new OffenderCourseAttendances();
				newOj.setEventId(bean.getEventId());
				newOj.setEventOutcome(bean.getEventOutcome());
				newOj.setEventType(bean.getEventType());
				newOj.setOffenderBookId(bean.getOffenderBookId());
				newOj.setCreateUserId(bean.getCreateUserId());
				newOj.setOffPrgrefId(bean.getOffPrgrefId() != null ? bean.getOffPrgrefId().longValue() : null);
				OffenderCourseAttndnsTwfService.offenderCourseAttndnsTwf(newOj, sqlOperation);
				if (liReturn == 1) {
					ocmpssetService.updateSchedulePay(BigDecimal.valueOf(bean.getOffenderBookId()),
							Integer.valueOf(eventId.toString()), "ACP",bean.getCreateUserId());
				}
			}
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (OffenderCourseAttendance bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
				String eventClass = tagPrgService.prgApptEventClass(bean.getOffenderBookId(), bean.getAgyLocId());
				bean.setEventClass(eventClass);
				VOffenderAllSchedules voff = new VOffenderAllSchedules();
				voff.setEventType(bean.getEventType());
				voff.setEventSubType(bean.getEventSubType());
				voff.setEventOutcome(bean.getEventOutcome());
				tagMultipleFailureService.adjustUa(voff, bean.getCreateUserId());
				VOffenderCourseAttendances newOffender = new VOffenderCourseAttendances();
				newOffender.setEventId(bean.getEventId());
				newOffender.setSealFlag(bean.getSealFlag());
				newOffender.setEventStatus(bean.getEventStatus());
				newOffender.setEventOutcome(bean.getEventOutcome());
				newOffender.setEventDate(bean.getEventDate());
				VOffenderCourseAttendances obj = offenderCourseAttendancesT1Service
						.offenderCourseAttendancesT1(newOffender);
				if (obj.getEventStatus() != null) {
					bean.setEventStatus(obj.getEventStatus());
				}
				liReturn = ocdprogrRepository.offCrsAppUpdateOffenderCourseAttendances(commitBean.getUpdateList());
				String sqlOperation = "UPDATING";
				OffenderCourseAttendances newOj = new OffenderCourseAttendances();
				newOj.setEventId(bean.getEventId());
				newOj.setEventOutcome(bean.getEventOutcome());
				newOj.setEventType(bean.getEventType());
				newOj.setOffenderBookId(bean.getOffenderBookId());
				newOj.setCreateUserId(bean.getCreateUserId());
				newOj.setOffPrgrefId(bean.getOffPrgrefId().longValue());
				OffenderCourseAttndnsTwfService.offenderCourseAttndnsTwf(newOj, sqlOperation);
				if (liReturn == 1) {
					ocmpssetService.updateSchedulePay(BigDecimal.valueOf(bean.getOffenderBookId()),
							Integer.valueOf(String.valueOf(bean.getEventId())), "ACP",bean.getModifyUserId());
				}
				
				VOffenderAllSchedules offsch = new VOffenderAllSchedules();
				offsch.setEventDate(bean.getEventDate());
				offsch.setStartTime(bean.getStartTime());
				offsch.setOffenderBookId(BigDecimal.valueOf(bean.getOffenderBookId()));
				offsch.setEventOutcome(bean.getEventOutcome());
				offsch.setToAgyLocId(bean.getAgyLocId());
				offsch.setScheduleType("ACPA");
				offsch.setCreateUserId(commitBean.getCreateUserId());
				offsch.setEventId(BigDecimal.valueOf(bean.getEventId()));
				List<WeeklyActivityPlans> returnList = ocdclogsServiceImpl.getWeeklyActivityPlanData(offsch);
				ocdclogsServiceImpl.saveWeeklyActivitePlanData(returnList, offsch);
				
				
				
			}

		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgOffPrgStsRecordGroup() {
		return ocdprogrRepository.rgOffPrgStsRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<InternalLocationUsages> rgIntLocationRecordGroup(final String agyLocId) {
		return ocdprogrRepository.rgIntLocationRecordGroup(agyLocId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ProgramServices> rgProgramServicesRecordGroup() {
		List<ProgramServices> returnList = new ArrayList<ProgramServices>();
		returnList = ocdprogrRepository.rgProgramServicesRecordGroup();
		if(returnList!=null && !returnList.isEmpty()) {
			returnList.forEach(ele -> {
				if(ApplicationConstants.YFLAG.equalsIgnoreCase(ele.getActiveFlag()) && ele.getExpiryDate()== null) {
					ele.setCanDisplay(true);
				}else {
					ele.setCanDisplay(false);
				}
			});
		}
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgPsPrgAvailRecordGroup() {
		return ocdprogrRepository.rgPsPrgAvailRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<OffenderSentences> rgOffenderSentencesRecordGroup(final Integer offenderBookId) {
		List<OffenderSentences> returnList = new ArrayList<OffenderSentences>();
		returnList = ocdxprogRepository.rgOffenderSentencesRecordGroup(offenderBookId);
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<EventMeasures> rgEventSubTypesRecordGroup() {
		return ocdprogrRepository.rgEventSubTypesRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgOutcomeReasonsRecordGroup(String eventOutcome) {
		List<ReferenceCodes> returnList = new ArrayList<>();
		if ("futureAttendance".equals(eventOutcome)) {
			return rgFutureAttendanceRecordGroup();
		}
		if (eventOutcome != null && eventOutcome != "") {
			final String[] inputArray = eventOutcome.split("-");
			returnList = ocdprogrRepository.rgOutcomeReasonsRecordGroup(inputArray[0], inputArray[1]);
			for (ReferenceCodes bean : returnList) {
				if ("Y".equals(bean.getActiveFlag())) {
					bean.setCanDisplay(true);
				} else {
					bean.setCanDisplay(false);
				}
			}
		}
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<AgencyLocations> rgAgyLocIdRecordGroup(final String caseloadId) {
		List<AgencyLocations> returnList = ocdprogrRepository.rgAgyLocIdRecordGroup(caseloadId);
		for (AgencyLocations bean : returnList) {
			if ("Y".equals(bean.getActiveFlag())) {
				bean.setCanDisplay(true);
			} else {
				bean.setCanDisplay(false);
			}
		}
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ProgramServices> rgPhasesRecordGroup(final Integer programId) {
		return ocdprogrRepository.rgPhasesRecordGroup(programId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ProgramServices> rgModulesRecordGroup(final Integer phaseId) {
		return ocdprogrRepository.rgModulesRecordGroup(phaseId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgEngagementRecordGroup() {
		return ocdprogrRepository.rgEngagementRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgUnderstandingRecordGroup() {
		return ocdprogrRepository.rgUnderstandingRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgPsEndAllocRecordGroup() {
		return ocdprogrRepository.rgPsEndAllocRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgFutureAttendanceRecordGroup() {
		List<ReferenceCodes> returnList = ocdprogrRepository.rgFutureAttendanceRecordGroup();
		for (ReferenceCodes bean : returnList) {
			if ("Y".equals(bean.getActiveFlag())) {
				bean.setCanDisplay(true);
			} else {
				bean.setCanDisplay(false);
			}
		}
		return returnList;
	}

	@Override
	public void vOffPrgOblPreDelete() {

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public Date getOffenderDates(final BigDecimal offenderBookId) {
		return tagProgrammesService.getOffenderDates(offenderBookId.longValue());

	}

	@Override
	public List<ReferenceCodes> rgObligationSource() {
		return ocdprogrRepository.rgObligationSource();
	}

	public Integer checkScheduleConflict(final OffenderCourseAttendance bean) {
		return ocdprogrRepository.checkScheduleConflict(bean);

	}

	@Override
	public OffenderCourseAttendance checkUa(OffenderCourseAttendance bean) {
		Boolean oldUa = tagMultipleFailureService.checkUaEventOutcome(bean);
		Boolean newUa = tagMultipleFailureService.checkUaEventOutcome(bean);
		bean.setLvMultipleFailure(false);
		if (!oldUa && !newUa) {
			bean.setLvNewUa(false);
		} else {
			bean.setLvNewUa(true);
		}
		if (newUa) {
			List<VOffenderSentenceEvents> list = ocdprogrRepository.failureCur(bean);
			for (VOffenderSentenceEvents beanObj : list) {
				beanObj.setEventId(BigDecimal.valueOf(bean.getEventId()));
				Integer count = ocdprogrRepository.countSentenceUa(beanObj);
				if (count > 0) {
					bean.setLvMultipleFailure(true);
				}
			}
		}
		return bean;
	}

	@Override
	public Boolean validAllocationEndDate(OffenderProgramProfiles bean) {
		return tagProgrammesService.validAllocationEndDate(bean);
	}

	@Override
	public Boolean checkAttendanceOutcomes(OffenderProgramProfiles bean) {
		return tagProgrammesService.checkAttendanceOutcomes(bean.getOffPrgrefId());
	}

	@Override
	public OffenderProgramProfiles checkAllocationExists(OffenderProgramProfiles bean) {
		Boolean allocFlag = tagProgrammesService.checkAllocationExists(bean);
		if (allocFlag) {
			bean.setSealFlag("2");
			return bean;
		} else {
			CourseActivities returnBean = tagServiceService.getCrsDetails(bean.getCrsActyId());
			bean.setProgramId(returnBean.getProgramId());
			if (returnBean.getScheduleStartDate() != null
					&& returnBean.getScheduleStartDate().compareTo(eliteDateService.getDBTime()) > 0) {
				bean.setOffenderStartDate(returnBean.getScheduleStartDate());
				bean.setOffenderEndDate(returnBean.getScheduleEndDate());
				bean.setStartSessionNo(Long.valueOf("1"));
			}
			if (bean.getProgramId() != null) {
				ProgramServices prgBean = ocdprogrRepository.getPrgSrvDetails(bean);
				bean.setPhaseDesc(prgBean.getDescription());
				bean.setModuleFlag(prgBean.getModuleFlag());
				if ("Y".equals(bean.getModuleFlag()) && bean.getStartSessionNo() != null) {
					OffenderProgramProfiles seqBean = tagProgrammesService.getListSeqRange(bean);
					bean.setModuleFrom(seqBean.getModuleFrom());
					bean.setModuleTo(seqBean.getModuleTo());
				}
			}
			return bean;
		}
	}

	public String checkNonAssociations(final OffenderCourseAttendancesCommitBean commitBean) {
		String returnData = "";
		if (commitBean.getInsertList().size() > 0) {
			returnData = generateNonAssociationMessage(commitBean.getInsertList(), commitBean.getCreateUserId());
		}
		if (commitBean.getUpdateList().size() > 0) {
			returnData = generateNonAssociationMessage(commitBean.getUpdateList(), commitBean.getCreateUserId());
		}
		if (returnData != null && returnData.length() > 0)
			returnData = getFinalMessageString(returnData);

		if ("".equals(returnData)) {
			returnData = ApplicationConstants.EMPTYDATA;
		}
		return returnData;
	}

	public String generateNonAssociationMessage(List<OffenderCourseAttendance> insertList, String userName) {
		String returnMessage = "";
		for (OffenderCourseAttendance search : insertList) {
			List<OffenderNonAssociations> returnList = ocdprogrRepository.checkNonAssociations(search);
			int count;
			if (returnList.size() > 0) {
				for (OffenderNonAssociations offNonAss : returnList) {
					count = 0;
					List<OffenderCourseAttendance> nonAssOffCourseList = ocdprogrRepository
							.checkNonAssociationAppointments(search, offNonAss);
					if (nonAssOffCourseList != null && nonAssOffCourseList.size() > 0) {
						for (OffenderCourseAttendance nonAssOffCourse : nonAssOffCourseList) {
							String message = "";
							count++;
							if (count == 1) {
								VHeaderBlock bean = ocdprogrRepository
										.ocdprogrGetOffenderNames(offNonAss.getNsOffenderBookId(), userName);
								message = bean.getLastName() + " " + bean.getFirstName() + ", "
										+ bean.getOffenderIdDisplay() + "\n";
							}
							java.util.Date stDate = nonAssOffCourse.getStartTime();
							java.util.Date enDate = nonAssOffCourse.getEndTime();
							java.util.Date apDate = nonAssOffCourse.getEventDate();
							SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
							String startTime = sdf.format(stDate);
							String endTime = sdf.format(enDate);
							SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
							String appDate = sdf1.format(apDate);
							message = message + appDate + ", " + startTime + ", " + endTime + "\n";
							returnMessage = returnMessage + message;
						}
						returnMessage = returnMessage + "\n";
					}

					count = 0;
					VOffenderAllSchedules vOffSch = new VOffenderAllSchedules();
					vOffSch.setEventDate(search.getEventDate());
					vOffSch.setToAgyLocId(search.getAgyLocId());
					List<OffenderIndSchedules> offSchedules = ocdclogsRepository
							.checkOffenderScheduleConflicts(offNonAss, vOffSch);
					if (offSchedules != null && offSchedules.size() > 0) {
						for (OffenderIndSchedules nonAssOffCourse : offSchedules) {
							String message = "";
							count++;
							if (count == 1) {
								VHeaderBlock bean = ocdprogrRepository
										.ocdprogrGetOffenderNames(offNonAss.getNsOffenderBookId(), userName);
								message = bean.getLastName() + " " + bean.getFirstName() + ", "
										+ bean.getOffenderIdDisplay() + "\n";
							}
							java.util.Date stDate = nonAssOffCourse.getStartTime();
							java.util.Date enDate = nonAssOffCourse.getEndTime();
							java.util.Date apDate = nonAssOffCourse.getEventDate();
							SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
							String startTime = stDate != null ? sdf.format(stDate) : "";
							String endTime = enDate != null ? sdf.format(enDate) : null;
							SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
							String appDate = sdf1 != null ? sdf1.format(apDate) : "";
							message = message + appDate + ", " + startTime + (endTime != null ? ", " + endTime : "")
									+ "\n";
							returnMessage = returnMessage + message;
						}
						returnMessage = returnMessage + "\n";
					}

				}
			}
		}
		return returnMessage;
	}

	public String getFinalMessageString(final String messageData) {
		return "ocdprogr.nonassociationconflictmsg \n\n" + messageData + " \n ocdprogr.doyouwanttocontinue ";
	}

	@Override
	public String checkInstNonAssociation(final OffenderProgramProfilesCommitBean commitBean) {
		String returnMessage = "";
		String message = "";
		String individualConflictsMessage = "";
		String gangConflictsMessage = "";
		List<OffenderProgramProfiles> vOffenderAllSchedulesList = new ArrayList<OffenderProgramProfiles>();
		try {
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				vOffenderAllSchedulesList.addAll(commitBean.getInsertList());
			}
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				vOffenderAllSchedulesList.addAll(commitBean.getUpdateList());
			}
			if (vOffenderAllSchedulesList != null && vOffenderAllSchedulesList.size() > 0) {
				for (OffenderProgramProfiles schedulesList : vOffenderAllSchedulesList) {
					List<OffenderNaDetails> detailsList = nonAssociationService
							.checkNonAssociationOffendersWithLivingUnit(
									new BigDecimal(schedulesList.getOffenderBookId()),
									new BigDecimal(schedulesList.getInternalLocationId()), null);
					logger.info(this.getClass().getName() + "checkInstNonAssociation method call");
					if (detailsList != null && detailsList.size() > 0) {
						for (OffenderNaDetails details : detailsList) {
							List<OffenderNaDetails> offendersList = nonAssociationService
									.getNonAssociationOffenderList(details.getOffenderBookId().intValue());
							if (offendersList != null && offendersList.size() > 0) {
								for (OffenderNaDetails off : offendersList) {
									VHeaderBlock block = ocdprogrRepository.ocdprogrGetOffenderNames(
											off.getNsOffenderBookId(), commitBean.getCreateUserId());
									message = message + block.getFirstName() + " " + block.getLastName() + " "
											+ block.getOffenderBookId();
								}
								individualConflictsMessage = individualConflictsMessage + message + "\n";
							}
						}
					}
				}
			}
			if (individualConflictsMessage != null && !individualConflictsMessage.equalsIgnoreCase("")) {
				individualConflictsMessage = "ocdprogr.indinonassocconflict:\n\n" + individualConflictsMessage;
			}
			logger.info(this.getClass().getName() + "getOffendersOfNonAssociationGroup method call");
			List<OffenderStgAffiliations> offenderStgAffiliationsList = nonAssociationService
					.getOffendersOfNonAssociationGroup(
							new BigDecimal(vOffenderAllSchedulesList.get(0).getOffenderBookId()));
			if (offenderStgAffiliationsList != null && offenderStgAffiliationsList.size() > 0) {
				for (OffenderStgAffiliations offenderStgAffiliations : offenderStgAffiliationsList) {
					for (OffenderProgramProfiles vOffenderAllSchedule : vOffenderAllSchedulesList) {
						logger.info(this.getClass().getName() + "ocdprogrGetOffenderNames method call");
						logger.info(this.getClass().getName() + "ocdprogrGetOffenderNames method call");
						VHeaderBlock bean = ocdprogrRepository.ocdprogrGetOffenderNames(
								offenderStgAffiliations.getOffenderBookId(), commitBean.getCreateUserId());
						message = bean.getLastName() + " " + bean.getFirstName() + ", " + bean.getOffenderIdDisplay()
								+ "\n";
						gangConflictsMessage = gangConflictsMessage + message + "\n";
					}
				}

			}

			if (gangConflictsMessage != null && !gangConflictsMessage.equalsIgnoreCase("")) {
				gangConflictsMessage = "ocdprogr.gangnonassocconflict:\n\n" + gangConflictsMessage;
			}

			if ((individualConflictsMessage != null && !individualConflictsMessage.equalsIgnoreCase(""))
					&& (gangConflictsMessage != null && !gangConflictsMessage.equalsIgnoreCase(""))) {
				returnMessage = getFinalMessageString(individualConflictsMessage + "\n" + gangConflictsMessage);
			} else if (individualConflictsMessage != null && !individualConflictsMessage.equalsIgnoreCase("")) {
				returnMessage = getFinalMessageString(individualConflictsMessage);
			} else if (gangConflictsMessage != null && !gangConflictsMessage.equalsIgnoreCase("")) {
				returnMessage = getFinalMessageString(gangConflictsMessage);
			} else {
				returnMessage = ApplicationConstants.EMPTYDATA;
			}
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + "In checkInstNonAssociation method : ", e);
		}
		return returnMessage;
	}

	@Override
	public String checkNonAssociationsforInstWhileAssigning(OffenderProgramProfilesCommitBean commitBean) {
		String returnMessage = "";
		try {
			List<OffenderProgramProfiles> offenderProgramProfilesFinalList = new ArrayList<OffenderProgramProfiles>();
			if (commitBean != null && commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				offenderProgramProfilesFinalList.addAll(commitBean.getInsertList());
			}
			if (commitBean != null && commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				offenderProgramProfilesFinalList.addAll(commitBean.getUpdateList());
			}
			returnMessage = getNonAssociationsData(offenderProgramProfilesFinalList, commitBean.getCreateUserId());
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "Error in checkNonAssociations", e);
		}
		return returnMessage;
	}

	public String getNonAssociationsData(List<OffenderProgramProfiles> courseActivities, String createUserId) {
		String returnMessage = "";
		String individualConflictsMessage = "";
		String gangConflictsMessage = "";
		String message = "";
		try {
			List<VOffenderProgramProfiles> listOfAllocOffenders = new ArrayList<VOffenderProgramProfiles>();
			if (courseActivities != null) {
				for (OffenderProgramProfiles obj : courseActivities) {
					VOffenderProgramProfiles objSearchDao = new VOffenderProgramProfiles();
					objSearchDao.setCrsActyId(obj.getCrsActyId());
					List<VOffenderProgramProfiles> listOfAllocOffendersTwo = ocupaoffRepository
							.vOffPrgProfilesExecuteQuery(objSearchDao);
					listOfAllocOffenders.addAll(listOfAllocOffendersTwo);
				}
			}
			List<OffenderNonAssociations> detailsList = new ArrayList<>();
			if (!listOfAllocOffenders.isEmpty()) {
				for (OffenderProgramProfiles activity : courseActivities) {

					detailsList = ocdprogrRepository
							.getNonAssociationforInst(new BigDecimal(activity.getOffenderBookId()));

					if (detailsList != null) {
						for (OffenderNonAssociations activeOffenderObj : detailsList) {
						listOfAllocOffenders.stream()
									.filter(bean -> bean.getOffenderBookId()
											.equals(activeOffenderObj.getNsOffenderBookId().longValue()))
									.collect(Collectors.toList());
							VHeaderBlock block = ocdprogrRepository
									.ocdprogrGetOffenderNames(activeOffenderObj.getNsOffenderBookId(), createUserId);
							message = block.getLastName() + " " + block.getFirstName() + " " + block.getOffenderBookId()
									+ block.getOffenderIdDisplay() + "\n";
							individualConflictsMessage = individualConflictsMessage + message;

						}
						if (individualConflictsMessage != null && !individualConflictsMessage.equalsIgnoreCase("")) {
							individualConflictsMessage = "ocdprogr.indinonassocconflict:\n\n"
									+ individualConflictsMessage;
						}
					}

				}
			}

			logger.info(this.getClass().getName() + "getOffendersOfNonAssociationGroup method call");
			List<OffenderStgAffiliations> offenderStgAffiliationsList = nonAssociationService
					.getOffendersOfNonAssociationGroup(new BigDecimal(courseActivities.get(0).getOffenderBookId()));
			for (OffenderProgramProfiles vOffenderAllSchedule : courseActivities) {
				logger.info(this.getClass().getName() + "ocdprogrGetOffenderNames method call");
				if (offenderStgAffiliationsList != null && offenderStgAffiliationsList.size() > 0) {
					for (OffenderStgAffiliations gangObj : offenderStgAffiliationsList) {
						logger.info(this.getClass().getName() + "ocdprogrGetOffenderNames method call");
						VHeaderBlock bean = ocdprogrRepository.ocdprogrGetOffenderNames(gangObj.getOffenderBookId(),
								createUserId);
						message = bean.getLastName() + " " + bean.getFirstName() + ", " + bean.getOffenderIdDisplay()
								+ "\n";
						gangConflictsMessage = gangConflictsMessage + message + "\n";
					}
				}
			}

			if (gangConflictsMessage != null && !gangConflictsMessage.equalsIgnoreCase("")) {
				gangConflictsMessage = "ocdprogr.gangnonassocconflict:\n\n" + gangConflictsMessage;
			}

			if ((individualConflictsMessage != null && !individualConflictsMessage.equalsIgnoreCase(""))
					&& (gangConflictsMessage != null && !gangConflictsMessage.equalsIgnoreCase(""))) {
				returnMessage = getFinalMessageString(individualConflictsMessage + "\n" + gangConflictsMessage);
			} else if (individualConflictsMessage != null && !individualConflictsMessage.equalsIgnoreCase("")) {
				returnMessage = getFinalMessageString(individualConflictsMessage);
			} else if (gangConflictsMessage != null && !gangConflictsMessage.equalsIgnoreCase("")) {
				returnMessage = getFinalMessageString(gangConflictsMessage);
			} else {
				returnMessage = ApplicationConstants.EMPTYDATA;
			}

		} catch (Exception e) {
			logger.error(this.getClass().getName() + "Error in checkNonAssociations", e);
		}
		return returnMessage;
	}

	@Override
	public String checkNonAssociationsWhileScheduling(OffenderCourseAttendancesCommitBean commitBean) {
		String returnMessage = "";
		String individualConflictsMessage = "";
		String gangConflictsMessage = "";
		List<OffenderCourseAttendance> vOffenderAllSchedulesList = new ArrayList<>();
		try {
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				vOffenderAllSchedulesList.addAll(commitBean.getInsertList());
			}
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				vOffenderAllSchedulesList.addAll(commitBean.getUpdateList());
			}
			if (vOffenderAllSchedulesList != null && vOffenderAllSchedulesList.size() > 0) {
				List<OffenderNonAssociations> offenderNaDetailsList = ocdprogrRepository
						.getNonAssociationforInst(new BigDecimal(vOffenderAllSchedulesList.get(0).getOffenderBookId()));
				for (OffenderCourseAttendance vOffenderAllSchedule : vOffenderAllSchedulesList) {
					List<AgyIntLocProfiles> agyIntLocProfilesList = new ArrayList<AgyIntLocProfiles>();
					OffenderCourseAttendance newObj = new OffenderCourseAttendance();
					BeanUtils.copyProperties(vOffenderAllSchedule, newObj);
					logger.info(this.getClass().getName() + "checkNonAssociationOffendersWithLivingUnit method call");
					if (offenderNaDetailsList != null && offenderNaDetailsList.size() > 0) {
						for (OffenderNonAssociations offenderNaDetails : offenderNaDetailsList) {
							if (vOffenderAllSchedule.getToInternalLocationId() != null
									&& !vOffenderAllSchedule.getToInternalLocationId().equals(BigDecimal.ZERO)) {
								agyIntLocProfilesList = nonAssociationRepository.getNonAssociationConfigForLocation(
										vOffenderAllSchedule.getToInternalLocationId());
								int congifCount = agyIntLocProfilesList.stream()
										.filter(e -> e.getIntLocProfileCode() != null
												&& e.getIntLocProfileCode().equals(offenderNaDetails.getNsReasonCode()))
										.collect(Collectors.toList()).size();
								if (congifCount == 0) {
									continue;
								}
								newObj.setToInternalLocationId(vOffenderAllSchedule.getToInternalLocationId());
							}
							newObj.setOffenderBookId(offenderNaDetails.getNsOffenderBookId().longValue());
							List<OffenderCourseAttendance> offenderIndSchedulesList = ocdprogrRepository
									.getNonAssociationsforAppointments(newObj);
							if (offenderIndSchedulesList != null
									&& vOffenderAllSchedule.getToInternalLocationId() != null
									&& !vOffenderAllSchedule.getToInternalLocationId().equals(BigDecimal.ZERO)) {
								offenderIndSchedulesList = offenderIndSchedulesList.stream()
										.filter(e -> e.getToInternalLocationId() == null || vOffenderAllSchedule
												.getToInternalLocationId().equals(e.getToInternalLocationId()))
										.collect(Collectors.toList());
							}
							if (offenderIndSchedulesList != null && offenderIndSchedulesList.size() > 0) {
								logger.info(this.getClass().getName() + "ocdprogrGetOffenderNames method call");
								VHeaderBlock bean = ocdprogrRepository.ocdprogrGetOffenderNames(
										offenderNaDetails.getNsOffenderBookId(), commitBean.getCreateUserId());
								String message = "";
								message = bean.getLastName() + " ," + bean.getFirstName() + " "
										+ (bean.getMiddleName() != null ? bean.getMiddleName() : "") + " "
										+ bean.getOffenderIdDisplay() + "\n";
								for (OffenderCourseAttendance offenderIndSchedule : offenderIndSchedulesList) {
									java.util.Date stDate = offenderIndSchedule.getStartTime();
									java.util.Date enDate = offenderIndSchedule.getEndTime();
									java.util.Date apDate = offenderIndSchedule.getEventDate();
									SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
									String startTime = stDate != null ? sdf.format(stDate) : "";
									String endTime = enDate != null ? sdf.format(enDate) : null;
									SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
									String appDate = sdf1 != null ? sdf1.format(apDate) : "";
									message = message + appDate + ", " + startTime
											+ (endTime != null ? ", " + endTime : "") + "\n";
								}
								individualConflictsMessage = individualConflictsMessage + message + "\n";
							}

						}
					}
				}
				if (individualConflictsMessage != null && !individualConflictsMessage.equalsIgnoreCase("")) {
					individualConflictsMessage = "ocdprogr.indinonassocconflict:\n\n" + individualConflictsMessage;
				}
				logger.info(this.getClass().getName() + "getOffendersOfNonAssociationGroup method call");
				List<OffenderStgAffiliations> offenderStgAffiliationsList = nonAssociationService
						.getOffendersOfNonAssociationGroup(
								new BigDecimal(vOffenderAllSchedulesList.get(0).getOffenderBookId()));
				for (OffenderCourseAttendance vOffenderAllSchedule : vOffenderAllSchedulesList) {
					OffenderCourseAttendance newObj = new OffenderCourseAttendance();
					BeanUtils.copyProperties(vOffenderAllSchedule, newObj);
					logger.info(this.getClass().getName() + "ocdprogrGetOffenderNames method call");
					if (offenderStgAffiliationsList != null && offenderStgAffiliationsList.size() > 0) {
						for (OffenderStgAffiliations gangObj : offenderStgAffiliationsList) {
							newObj.setOffenderBookId(gangObj.getOffenderBookId().longValue());
							List<OffenderCourseAttendance> offenderIndSchedulesList = ocdprogrRepository
									.getNonAssociationsforAppointments(newObj);

							if (offenderIndSchedulesList != null && offenderIndSchedulesList.size() > 0) {
								logger.info(this.getClass().getName() + "ocdprogrGetOffenderNames method call");
								VHeaderBlock bean = ocdprogrRepository.ocdprogrGetOffenderNames(
										gangObj.getOffenderBookId(), commitBean.getCreateUserId());
								String message = "";
								message = bean.getLastName() + " " + bean.getFirstName() + ", "
										+ (bean.getMiddleName() != null ? bean.getMiddleName() : "")
										+ bean.getOffenderIdDisplay() + "\n";
								for (OffenderCourseAttendance offenderIndSchedule : offenderIndSchedulesList) {
									java.util.Date stDate = offenderIndSchedule.getStartTime();
									java.util.Date enDate = offenderIndSchedule.getEndTime();
									java.util.Date apDate = offenderIndSchedule.getEventDate();
									SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
									String startTime = stDate != null ? sdf.format(stDate) : "";
									String endTime = enDate != null ? sdf.format(enDate) : null;
									SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
									String appDate = sdf1 != null ? sdf1.format(apDate) : "";
									message = message + appDate + ", " + startTime
											+ (endTime != null ? ", " + endTime : "") + "\n";
								}
								gangConflictsMessage = gangConflictsMessage + message + "\n";
							}
						}
					}
				}
				if (gangConflictsMessage != null && !gangConflictsMessage.equalsIgnoreCase("")) {
					gangConflictsMessage = "ocdprogr.gangnonassocconflict:\n\n" + gangConflictsMessage;
				}

			}

			if ((individualConflictsMessage != null && !individualConflictsMessage.equalsIgnoreCase(""))
					&& (gangConflictsMessage != null && !gangConflictsMessage.equalsIgnoreCase(""))) {
				returnMessage = getFinalMessageString(individualConflictsMessage + "\n" + gangConflictsMessage);
			} else if (individualConflictsMessage != null && !individualConflictsMessage.equalsIgnoreCase("")) {
				returnMessage = getFinalMessageString(individualConflictsMessage);
			} else if (gangConflictsMessage != null && !gangConflictsMessage.equalsIgnoreCase("")) {
				returnMessage = getFinalMessageString(gangConflictsMessage);
			} else {
				returnMessage = ApplicationConstants.EMPTYDATA;
			}
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " in checkNonAssociations " + e);
		}
		return returnMessage;
	}

	@Override
	public Integer updateoffPgmProfCommit(OffenderProgramProfilesCommitBean commitBean) {
		Integer liReturn = null;
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (OffenderProgramProfiles bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
				offenderProgramProfilesTrService.offenderProgramProfilesTr(bean);
				liReturn = ocdprogrRepository.offPgmProfUpdateOffenderProgramProfiles(commitBean.getUpdateList());
				if (liReturn == 1) {
					for (OffenderProgramProfiles offenderProgramProfiles : commitBean.getUpdateList()) {
						if (offenderProgramProfiles.getEmailFlag().equals("Y") || offenderProgramProfiles.getEmailFlag().equals("N")) {
							ocdprogrRepository.updateCourseActivitiesEmail(offenderProgramProfiles);
						}
						if (offenderProgramProfiles.getSmsFlag().equals("Y") || offenderProgramProfiles.getSmsFlag().equals("N")) {
							ocdprogrRepository.updateCourseActivitiesSms(offenderProgramProfiles);
						}
					}
				}
			}
			if (liReturn == 1) {
				postUpdate(commitBean.getUpdateList());
			}
		}
		return liReturn;
	}
}
