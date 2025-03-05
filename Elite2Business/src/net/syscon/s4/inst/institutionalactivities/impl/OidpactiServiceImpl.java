package net.syscon.s4.inst.institutionalactivities.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.core.EliteDateRepository;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.im.beans.OffenderProgramProfilesCommitBean;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.ProgramsNonAssocTmp;
import net.syscon.s4.inst.institutionalactivities.OidpactiRepository;
import net.syscon.s4.inst.institutionalactivities.OidpactiService;
import net.syscon.s4.inst.institutionalactivities.beans.VPrisonActivities;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmpssetService;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEventsCommitBean;
import net.syscon.s4.pkgs.non_association.NonAssociationService;
import net.syscon.s4.pkgs.tag_prison_activities.TagPrisonActivitiesService;
import net.syscon.s4.triggers.OffenderProgramProfilesTrService;
import net.syscon.s4.triggers.VOffenderCourseEventsTdService;
import net.syscon.s4.triggers.VOffenderCourseEventsTuService;

/**
 * Class OidpactiServiceImpl
 */
@Service
public class OidpactiServiceImpl extends BaseBusiness implements OidpactiService {
	private static Logger logger = LogManager.getLogger(OidpactiServiceImpl.class.getName());

	private List<VOffenderCourseEvents> tempData;
	@Autowired
	private OidpactiRepository oidpactiRepository;
	@Autowired
	private EliteDateRepository dateRepository;
	@Autowired
	private TagPrisonActivitiesService tagPrisonActivitiesService;

	@Autowired
	private OffenderProgramProfilesTrService offenderProgramProfilesTrService;

	@Autowired
	private VOffenderCourseEventsTuService vOffenderCourseEventsTuService;

	@Autowired
	private VOffenderCourseEventsTdService vOffenderCourseEventsTdService;

	@Autowired
	private NonAssociationService nonAssociationService;

	@Autowired
	private OcmpssetService ocmpssetService;

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<OffenderProgramProfiles> chkActiveIaAllocation(OffenderProgramProfiles paramBean) {
		List<OffenderProgramProfiles> offenderProgramProfilesList = oidpactiRepository.chkActiveIaAllocation(paramBean);
		return offenderProgramProfilesList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderProgramProfiles> offProgProfExecuteQuery(OffenderProgramProfiles searchRecord) {
		List<OffenderProgramProfiles> returnList = oidpactiRepository.offProgProfExecuteQuery(searchRecord);
		for (OffenderProgramProfiles bean : returnList) {
			Map<String, Object> populateActivity = tagPrisonActivitiesService.populateActivity(bean);
			bean.setActivityDescription(
					populateActivity.get("P_ACTIVITIES") != null ? String.valueOf(populateActivity.get("P_ACTIVITIES"))
							: null);
			bean.setProgramDescription(populateActivity.get("P_ESTABLISHMENT") != null
					? String.valueOf(populateActivity.get("P_ESTABLISHMENT"))
					: null);
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_PROG_PROF
	 *
	 * @throws SQLException
	 */
	@Transactional
	public OffenderProgramProfiles offProgProfCommit(OffenderProgramProfilesCommitBean commitBean) {
		OffenderProgramProfiles returnBean = new OffenderProgramProfiles();
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (OffenderProgramProfiles bean : commitBean.getInsertList()) {
				bean.setOffPrgrefId(oidpactiRepository.getPrgRefId());
				Integer dupCount = oidpactiRepository.checkDup(bean);
				bean.setCreateUserId(commitBean.getCreateUserId());
				if (dupCount > 0) {
					returnBean.setSealFlag(ApplicationConstants.CONSTANTVALUE);
					return returnBean;
				}
			}
			for (OffenderProgramProfiles bean : commitBean.getInsertList()) {
				offenderProgramProfilesTrService.offenderProgramProfilesTr(bean);
			}
			String returnVal = oidpactiRepository.offProgProfInsertOffenderProgramProfiles(commitBean.getInsertList());
			if (returnVal != null && "1".equals(returnVal)) {
				returnBean.setSealFlag(ApplicationConstants.SUCCESSMSG);
			} else {
				returnBean.setSealFlag(returnVal);
				return returnBean;
			}
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (OffenderProgramProfiles bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			for (OffenderProgramProfiles bean : commitBean.getUpdateList()) {
				offenderProgramProfilesTrService.offenderProgramProfilesTr(bean);
			}
			String returnVal = oidpactiRepository.offProgProfUpdateOffenderProgramProfiles(commitBean.getUpdateList());
			if (returnVal != null && "1".equals(returnVal)) {
				returnBean.setSealFlag(ApplicationConstants.SUCCESSMSG);
			} else {
				returnBean.setSealFlag(returnVal);
				return returnBean;
			}
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			for (OffenderProgramProfiles bean : commitBean.getDeleteList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
				VOffenderCourseEvents obj = new VOffenderCourseEvents();
				BeanUtils.copyProperties(bean, obj);
				vOffenderCourseEventsTdService.vOffenderCourseEventsTdTgr(obj, obj);
				oidpactiRepository.deleteOffenderCourseApptGrps(bean);
				oidpactiRepository.deleteOffenderCourseAttendances(bean);
			}
			String returnVal = oidpactiRepository.offProgProfDeleteOffenderProgramProfiles(commitBean.getDeleteList());
			if (returnVal != null && "1".equals(returnVal)) {
				returnBean.setSealFlag(ApplicationConstants.SUCCESSMSG);
			} else {
				returnBean.setSealFlag(returnVal);
				return returnBean;
			}
		}
		return returnBean;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<VOffenderCourseEvents> vOffCourseEvntsExecuteQuery(VOffenderCourseEvents searchRecord) {
		Integer rowId = 0;
		List<VOffenderCourseEvents> list = oidpactiRepository.vOffCourseEvntsExecuteQuery(searchRecord);
		tempData = list;
		if (tempData != null && tempData.size() > 0) {
			for (VOffenderCourseEvents data : tempData) {
				data.setRowId(++rowId);
			}
		}
		return list;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstV_OFF_COURSE_EVNTS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer vOffCourseEvntsCommit(VOffenderCourseEventsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (VOffenderCourseEvents bean : commitBean.getUpdateList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
				bean.setModifyUserId(commitBean.getCreateUserId());
				VOffenderCourseEvents oldObj = null;
				if (tempData != null && tempData.size() > 0) {
					for (VOffenderCourseEvents oldData : tempData) {
						if (oldData != null && oldData.getRowId() == bean.getRowId()) {
							oldObj = oldData;
							break;
						}
					}
				}
				liReturn = vOffenderCourseEventsTuService.vOffenderCourseEventsTuTgr(oldObj, bean);
				if (liReturn > 1) {
					ocmpssetService.updateSchedulePay(bean.getOffenderBookId(), liReturn, "INST_ACT" ,commitBean.getCreateUserId());
					liReturn = 1;
				}
			}
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = oidpactiRepository.vOffCourseEvntsDeleteVOffenderCourseEvents(commitBean.getDeleteList());
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
	public List<OffenderProgramProfiles> offenderProgramProfileswaitExecuteQuery(OffenderProgramProfiles searchRecord) {
		List<OffenderProgramProfiles> returnList = oidpactiRepository
				.offenderProgramProfileswaitExecuteQuery(searchRecord);
		for (OffenderProgramProfiles bean : returnList) {
			Map<String, Object> populateActivity = tagPrisonActivitiesService.populateActivity(bean);
			bean.setActivityDescription(
					populateActivity.get("P_ACTIVITIES") != null ? String.valueOf(populateActivity.get("P_ACTIVITIES"))
							: null);
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFFENDER_PROGRAM_PROFILES_2
	 *
	 * @throws SQLException
	 */
	@Transactional
	public OffenderProgramProfiles offenderProgramProfiles2Commit(OffenderProgramProfilesCommitBean commitBean) {
		OffenderProgramProfiles returnBean = new OffenderProgramProfiles();
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (OffenderProgramProfiles bean : commitBean.getInsertList()) {
				bean.setOffPrgrefId(oidpactiRepository.getPrgRefId());
				bean.setCreateUserId(commitBean.getCreateUserId());
				Long result = tagPrisonActivitiesService.chkwaitList(bean);

				if (result == 1) {
					returnBean.setSealFlag(ApplicationConstants.CONSTANTVALUE);
					return returnBean;
				}
			}
			String returnVal = oidpactiRepository.offProgProfInsertOffenderProgramProfiles(commitBean.getInsertList());
			if (returnVal != null && "1".equals(returnVal)) {
				returnBean.setSealFlag(ApplicationConstants.SUCCESSMSG);
			} else {
				returnBean.setSealFlag(returnVal);
				return returnBean;
			}
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (OffenderProgramProfiles bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			for (OffenderProgramProfiles bean : commitBean.getUpdateList()) {
				offenderProgramProfilesTrService.offenderProgramProfilesTr(bean);
			}
			String returnVal = oidpactiRepository.offProgProfUpdateOffenderProgramProfiles(commitBean.getUpdateList());
			if (returnVal != null && "1".equals(returnVal)) {
				returnBean.setSealFlag(ApplicationConstants.SUCCESSMSG);
			} else {
				returnBean.setSealFlag(returnVal);
				return returnBean;
			}
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			String returnVal = oidpactiRepository.offProgProfDeleteOffenderProgramProfiles(commitBean.getDeleteList());
			if (returnVal != null && "1".equals(returnVal)) {
				returnBean.setSealFlag(ApplicationConstants.SUCCESSMSG);
			} else {
				returnBean.setSealFlag(returnVal);
				return returnBean;
			}
		}
		return returnBean;
	}

	public Integer postInsert(List<OffenderProgramProfiles> list) {
		Integer seq = 0;
		for (OffenderProgramProfiles bean : list) {
			if (bean.getOffenderStartDate().compareTo(dateRepository.getDBTime()) <= 0) {
				seq = tagPrisonActivitiesService.backdateAttendances(bean);
			}
		}
		return seq;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<AgencyLocations> rgEstablishmentRecordGroup(final String caseloadId) {
		return oidpactiRepository.rgEstablishmentRecordGroup(caseloadId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<VPrisonActivities> rgServicesRecordGroup(String agyLocId) {

		List<VPrisonActivities> returnList = oidpactiRepository.rgServicesRecordGroup(agyLocId);
		for (VPrisonActivities bean : returnList) {
			if ("Y".equals(bean.getCheckFlag())) {
				bean.setCanDisplay(true);
			} else {
				bean.setCanDisplay(false);
			}
		}
		return returnList;

	}

	public List<VPrisonActivities> getServices() {
		return oidpactiRepository.getServices();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgEndReasonRecordGroup() {
		return oidpactiRepository.rgEndReasonRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> pgPsRejRsnRecordGroup() {
		return oidpactiRepository.pgPsRejRsnRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgPerformanceRecordGroup() {
		return oidpactiRepository.rgPerformanceRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgPriorityRecordGroup() {
		return oidpactiRepository.rgPriorityRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgDecisionRecordGroup(final String systemMode) {
		List<ReferenceCodes> returnlist = oidpactiRepository.rgDecisionRecordGroup(systemMode);
		for (ReferenceCodes bean : returnlist) {
			if ("Y".equals(bean.getSealFlag())) {
				bean.setCanDisplay(true);
			} else {
				bean.setCanDisplay(false);
			}
		}
		return returnlist;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgAttendenceRecordGroup(final String pshowoutcome) {
		return oidpactiRepository.rgAttendenceRecordGroup(pshowoutcome);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<VPrisonActivities> lovServices2RecordGroup() {
		return oidpactiRepository.lovServices2RecordGroup();

	}

	@Override
	public OffenderProgramProfiles getCourseActivity(OffenderProgramProfiles bean) {
		return tagPrisonActivitiesService.getCourseActivity(bean);
	}

	@Override
	public Map<String, Object> checkWaitList2(OffenderProgramProfiles searchRecord) {
		Map<String, Object> waitList = null;
		waitList = tagPrisonActivitiesService.ChkWaitlist2(searchRecord);
		return waitList;
	}

	@Override
	public Integer courseVacancy(OffenderProgramProfiles object) {
		return tagPrisonActivitiesService.courseVacancy(object.getCrsActyId());
	}

	@Override
	public Integer cntAsnCur(OffenderProgramProfiles object) {
		return oidpactiRepository.cntAsnCur(object);
	}

	@Override
	public OffenderProgramProfiles assignCommit(OffenderProgramProfiles objPrograms) {
		String returnVal = null;
		OffenderProgramProfiles returnBean = new OffenderProgramProfiles();
		List<OffenderProgramProfiles> insertList = new ArrayList<>();
		List<OffenderProgramProfiles> updateList = new ArrayList<>();
		if (objPrograms.getOffPrgrefId() == null) {
			insertList.add(objPrograms);
			objPrograms.setOffPrgrefId(oidpactiRepository.getPrgRefId());
			Long result = tagPrisonActivitiesService.chkwaitList(objPrograms);
			if (result == 1) {
				returnBean.setSealFlag(ApplicationConstants.CONSTANTVALUE);
				return returnBean;
			}
			returnVal = oidpactiRepository.insertOffenderProgramProfiles(insertList);
			if (returnVal == null) {
				returnBean.setSealFlag(returnVal);
				return returnBean;
			}
		} else {
			updateList.add(objPrograms);
			for (OffenderProgramProfiles bean : updateList) {
				offenderProgramProfilesTrService.offenderProgramProfilesTr(bean);
			}
			returnVal = oidpactiRepository.offProgProfUpdateOffenderProgramProfiles(updateList);
			if (returnVal == null) {
				returnBean.setSealFlag(returnVal);
				return returnBean;
			}
		}
		if ("1".equals(returnVal)) {
			if (objPrograms.getOffenderStartDate().compareTo(dateRepository.getDBTime()) < 0
					&& objPrograms.getCrsActyId() != null && objPrograms.getOffenderBookId() != null
					&& objPrograms.getOffPrgrefId() != null) {
				tagPrisonActivitiesService.backdateAttendances(objPrograms);
			}
			returnBean.setSealFlag(ApplicationConstants.SUCCESSMSG);
			return returnBean;
		} else {
			returnBean.setSealFlag(ApplicationConstants.CONSTANTVALUE);
			return returnBean;
		}
	}

	@Override
	public String getProfileValue() {
		return oidpactiRepository.getProfileValue();
	}

	public Date getAdmissionDate(final OffenderProgramProfiles bean) {
		return tagPrisonActivitiesService.getAdmissionDate(bean.getOffenderBookId());
	}

	public ProgramsNonAssocTmp checkConflict(final OffenderProgramProfiles bean) {
		ProgramsNonAssocTmp returnBean = new ProgramsNonAssocTmp();
		int i = 0;
		List<Offenders> offList = new ArrayList<>();
		List<Offenders> tagcoreList = oidpactiRepository.getOffDetails(bean);
		List<Offenders> beanone = oidpactiRepository.getNaPrgSrv(bean);
		List<Offenders> beantwo = oidpactiRepository.getStgNaPrgSrv(bean);
		if (!beanone.isEmpty()) {
			offList.addAll(beanone);
		}
		if (!beantwo.isEmpty()) {
			offList.addAll(beantwo);
		}

		if (!offList.isEmpty() && !tagcoreList.isEmpty()) {
			offList.addAll(tagcoreList);
			for (Offenders beans : offList) {
				if (i == 0) {
					returnBean.setWarningMsg("A non-association linkage exists between " + beans.getLastName() + ","
							+ beans.getFirstName() + " " + beans.getOffenderIdDisplay());
				} else {
					returnBean.setWarningMsg(returnBean.getWarningMsg() + " and " + beans.getLastName() + ","
							+ beans.getFirstName() + " " + beans.getOffenderIdDisplay());
				}
				i = 1;
			}
			returnBean.setWarningMsg(returnBean.getWarningMsg() + " in this location.");
			returnBean.setWarningPrompt(
					"Before proceeding with location change investigate possible risk. Only proceed if you are satisfied with the risk. Do you wish to proceed ?");
		} else {
			return returnBean;
		}
		return returnBean;

	}

	public Date getBookingDate(final VHeaderBlock bean) {
		return tagPrisonActivitiesService.getBookingDate(bean.getOffenderBookId().longValue());
	}

	@Override
	public String checkNonAssociations(OffenderProgramProfiles objPrograms) {
		String returnMessage = "";
		try {

			returnMessage = getNonAssociationsData(objPrograms, objPrograms.getCreateUserId());
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "Error in checkNonAssociations ", e);
		}

		return returnMessage;
	}

	private String getNonAssociationsData(OffenderProgramProfiles OffenderProgramProfilesFinalList,
			String createUserId) {
		String returnMessage = "";
		String individualNonAssociationMessages = "";
		String gandNonAssociationMessages = "";
		try {
			if (OffenderProgramProfilesFinalList != null
					&& OffenderProgramProfilesFinalList.getOffenderBookId() != null) {
				List<OffenderNaDetails> nonAssociationList = nonAssociationService
						.getNonAssociationOffenderList(OffenderProgramProfilesFinalList.getOffenderBookId().intValue());
				if (nonAssociationList != null && nonAssociationList.size() > 0) {

					List<Offenders> sameActiveDescriptionLocationData = oidpactiRepository
							.getNaPrgSrv(OffenderProgramProfilesFinalList);

					if (sameActiveDescriptionLocationData != null && sameActiveDescriptionLocationData.size() > 0) {
						for (Offenders data : sameActiveDescriptionLocationData) {
							String message = "";

							message = data.getLastName() + " " + data.getFirstName() + ", "
									+ data.getOffenderIdDisplay() + "\n";

							message = message + "\n";
							individualNonAssociationMessages = individualNonAssociationMessages + message;
						}
						individualNonAssociationMessages = individualNonAssociationMessages + "\n";
					}
				}
				if (individualNonAssociationMessages != null
						&& !individualNonAssociationMessages.equalsIgnoreCase("")) {
					individualNonAssociationMessages = "oidpacti.indinonassocconflict:\n\n"
							+ individualNonAssociationMessages;
				}
				List<Offenders> gangMemberConfilctData = oidpactiRepository
						.getStgNaPrgSrv(OffenderProgramProfilesFinalList);
				String message = "";
				if (gangMemberConfilctData != null && gangMemberConfilctData.size() > 0) {
					for (Offenders data : gangMemberConfilctData) {

						message = data.getLastName() + " " + data.getFirstName() + ", " + data.getOffenderIdDisplay()
								+ "\n";

						message = message + "\n";
					}
					gandNonAssociationMessages = gandNonAssociationMessages + message + "\n";
				}

				if (gandNonAssociationMessages != null && !gandNonAssociationMessages.equalsIgnoreCase("")) {
					gandNonAssociationMessages = "oidpacti.gangnonassocconflict:\n\n" + gandNonAssociationMessages;
				}

				if ((individualNonAssociationMessages != null && !individualNonAssociationMessages.equalsIgnoreCase(""))
						&& (gandNonAssociationMessages != null && !gandNonAssociationMessages.equalsIgnoreCase(""))) {
					returnMessage = getFinalMessageString(
							(individualNonAssociationMessages + "\n" + gandNonAssociationMessages),
							OffenderProgramProfilesFinalList);
				} else if (individualNonAssociationMessages != null
						&& !individualNonAssociationMessages.equalsIgnoreCase("")) {
					returnMessage = getFinalMessageString(individualNonAssociationMessages,
							OffenderProgramProfilesFinalList);
				} else if (gandNonAssociationMessages != null && !gandNonAssociationMessages.equalsIgnoreCase("")) {
					returnMessage = getFinalMessageString(gandNonAssociationMessages, OffenderProgramProfilesFinalList);
				} else {
					returnMessage = ApplicationConstants.EMPTYDATA;
				}
			} else {
				returnMessage = ApplicationConstants.EMPTYDATA;
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "Error in getNonAssociationsData ", e);
		}
		return returnMessage;

	}

	private String getFinalMessageString(String messageData, OffenderProgramProfiles data) {
		return "oidpacti.selectedoffender" + " " + data.getLastName() + " " + data.getFirstName() + " "
				+ data.getOffenderIdDisplay() + " " + "oidpacti.nonassociationwithoffender \n\n" + messageData
				+ " \n oidpacti.doyouwanttocontinue ";
	}

}
