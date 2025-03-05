package net.syscon.s4.inst.institutionalactivities.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.im.beans.OffenderProgramProfilesCommitBean;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.ProgramsNonAssocTmp;
import net.syscon.s4.inst.institutionalactivities.OidpawliRepository;
import net.syscon.s4.inst.institutionalactivities.OidpawliService;
import net.syscon.s4.inst.institutionalactivities.beans.VPrisonActivities;
import net.syscon.s4.pkgs.non_association.NonAssociationService;
import net.syscon.s4.pkgs.oms_miscellaneous.OmsMiscellaneousService;
import net.syscon.s4.pkgs.tag_prg.TagPrgService;
import net.syscon.s4.pkgs.tag_prison_activities.TagPrisonActivitiesService;
import net.syscon.s4.triggers.OffenderProgramProfilesTrService;

/**
 * Class OidpawliServiceImpl
 */
@Service
public class OidpawliServiceImpl extends BaseBusiness implements OidpawliService {
	private static Logger logger = LogManager.getLogger(OidpawliServiceImpl.class.getName());

	@Autowired
	private OidpawliRepository oidpawliRepository;
	@Autowired
	private TagPrisonActivitiesService tagPrisonActivitiesService;

	@Autowired
	private OmsMiscellaneousService omsMiscellaneousService;

	@Autowired
	private TagPrgService tagPrgService;

	@Autowired
	private OffenderProgramProfilesTrService OffenderProgramProfilesTrService;

	@Autowired
	private NonAssociationService nonAssociationService;

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 * @throws ParseException
	 *
	 * @throws Exception
	 */
	public List<OffenderProgramProfiles> checkAssignConflict(final List<OffenderProgramProfiles> paramList)
			throws ParseException {
		final List<OffenderProgramProfiles> listConflicts = new ArrayList<OffenderProgramProfiles>();
		for (OffenderProgramProfiles paramBean : paramList) {
			String userName = paramList.get(0).getCreateUserId();
			paramBean.setCreateUserId(userName);
			final ProgramsNonAssocTmp nonAss = checkConflict(paramBean);
			paramBean.setWarningPrompt(nonAss.getWarningPrompt());
			paramBean.setWarningMsg(nonAss.getWarningMsg());
			listConflicts.add(paramBean);
			if (nonAss.getWarningMsg() != null && nonAss.getWarningMsg() != null) {
				return listConflicts;
			}
		}
		return listConflicts;
	}

	private ProgramsNonAssocTmp checkConflict(final OffenderProgramProfiles bean) throws ParseException {
		final ProgramsNonAssocTmp returnBean = new ProgramsNonAssocTmp();
		int i = 0;
		final List<Offenders> offList = new ArrayList<>();
		final Long offenderId = oidpawliRepository.getOffenderId(bean.getOffenderBookId(), bean.getCreateUserId());
		bean.setOffenderId(offenderId);
		final List<Offenders> tagcoreList = oidpawliRepository.getOffDetails(bean);
		final List<Offenders> beanone = oidpawliRepository.getNaPrgSrv(bean);
		final List<Offenders> beantwo = oidpawliRepository.getStgNaPrgSrv(bean);
		if (!beanone.isEmpty()) {
			offList.addAll(beanone);
		}
		if (!beantwo.isEmpty()) {
			offList.addAll(beantwo);
		}
		if (!offList.isEmpty() && !tagcoreList.isEmpty()) {
			offList.addAll(tagcoreList);
			for (final Offenders beans : offList) {
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

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderProgramProfiles> waitlistExecuteQuery(final OffenderProgramProfiles searchRecord) {
		final List<OffenderProgramProfiles> waitList = oidpawliRepository.waitlistExecuteQuery(searchRecord);
		final List<OffenderProgramProfiles> finalWaitList = new ArrayList<OffenderProgramProfiles>();
		Map<String, Object> waitListPost = null;
		if (!waitList.isEmpty()) {
			for (final OffenderProgramProfiles opf : waitList) {
				waitListPost = tagPrisonActivitiesService.displayWaitlistDetails(opf);
				final String rejectReason = oidpawliRepository.getRejectName(opf.getRejectReasonCode());
				opf.setOffenderIdDisplay((String) waitListPost.get("P_NBT_NOMS"));
				opf.setLastName((String) waitListPost.get("P_NBT_NAME"));
				opf.setActivity((String) waitListPost.get("P_NBT_ACTIVITY_DESC"));
				opf.setVacancy((String) waitListPost.get("P_NBT_VACANCY"));
				opf.setReferralPriorityDesc((String) waitListPost.get("P_NBT_PRIORITY"));
				opf.setWaitlistDecisionCode(opf.getWaitlistDecisionCode());
				opf.setRejectReason(rejectReason);
				finalWaitList.add(opf);
			}
		}

		return finalWaitList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstWAITLIST
	 *
	 * @throws SQLException
	 */
	@Transactional
	public List<OffenderProgramProfiles> waitlistCommit(final OffenderProgramProfilesCommitBean commitBean) {
		final List<OffenderProgramProfiles> liReturnData = new ArrayList<>();
		final List<OffenderProgramProfiles> liReturnValue = new ArrayList<>();
		final OffenderProgramProfiles offProFiles = new OffenderProgramProfiles();
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			final List<OffenderProgramProfiles> recordSavingList = new ArrayList<>();
			for (final OffenderProgramProfiles offenderPropertyItemObj : commitBean.getInsertList()) {
				final Long offPrgrefId = oidpawliRepository.ocdxprogOffPrgrefId();
				offenderPropertyItemObj.setOffPrgrefId(offPrgrefId);
				offenderPropertyItemObj.setCreateUserId(commitBean.getCreateUserId());
				preInsert(offenderPropertyItemObj);
				if (offenderPropertyItemObj.getSentenceSeq() != null) {
					liReturnData.add(offenderPropertyItemObj);
					return liReturnData;
				}
				recordSavingList.add(offenderPropertyItemObj);
			}
			for (OffenderProgramProfiles bean : commitBean.getInsertList()) {
				OffenderProgramProfilesTrService.offenderProgramProfilesTr(bean);
			}
			liReturn = oidpawliRepository.waitlistInsertOffenderProgramProfiles(recordSavingList);
			offProFiles.setSentenceSeq((long) liReturn);
		}

		if (liReturn == 1) {
			for (final OffenderProgramProfiles offenderattendace : commitBean.getInsertList()) {
				offenderattendace.setCreateUserId(commitBean.getCreateUserId());
				backdateAttendances(offenderattendace);
			}
		}

		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final OffenderProgramProfiles offenderPropertyItemObj : commitBean.getUpdateList()) {
				offenderPropertyItemObj.setModifyUserId(commitBean.getCreateUserId());
				if (offenderPropertyItemObj.getSentenceSeq() != null) {
					liReturnData.add(offenderPropertyItemObj);
					return liReturnData;
				}
				liReturnValue.clear();
				liReturnValue.add(offenderPropertyItemObj);
				liReturn = oidpawliRepository.waitlistUpdateOffenderProgramProfiles(liReturnValue,
						commitBean.getCreateUserId());
			}
			for (OffenderProgramProfiles bean : commitBean.getUpdateList()) {
				OffenderProgramProfilesTrService.offenderProgramProfilesTr(bean);
			}
			liReturn = oidpawliRepository.waitlistUpdateOffenderProgramProfiles(commitBean.getUpdateList(),
					commitBean.getCreateUserId());
			offProFiles.setSentenceSeq((long) liReturn);
			if (liReturn == 1) {
				for (final OffenderProgramProfiles offenderattendace : commitBean.getUpdateList()) {
					backdateAttendances(offenderattendace);
					tagPrisonActivitiesService.bulkUpdate(offenderattendace);
				}

			}
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (final OffenderProgramProfiles offenderPropertyItemObj : commitBean.getDeleteList()) {
				if (offenderPropertyItemObj.getWaitlistDecisionCode().equals("APP")
						|| offenderPropertyItemObj.getWaitlistDecisionCode().equals("REJ")) {
					Map<String, Object> getProfileValue = omsMiscellaneousService.getProfileValues("CLIENT",
							"ASOFF_DEL");
					final String value = getProfileValue.get("P_PROFILE_VALUE").toString();
					if ("Y".equals(value)) {
						offProFiles.setSentenceSeq((long) 5);
						liReturnData.add(offProFiles);

					} else if ("N".equals(value)) {
						offProFiles.setSentenceSeq((long) 6);
						liReturnData.add(offProFiles);

					}
				} else {
					for (OffenderProgramProfiles object : commitBean.getDeleteList()) {
						object.setModifyUserId(commitBean.getCreateUserId());
					}
					liReturn = oidpawliRepository.waitlistDeleteOffenderProgramProfiles(commitBean.getDeleteList());
					offProFiles.setSentenceSeq((long) liReturn);
				}
			}

		}
		liReturnData.add(offProFiles);
		return liReturnData;
	}

	private OffenderProgramProfiles preInsert(final OffenderProgramProfiles offenderProperty) {
		Long result = tagPrisonActivitiesService.chkwaitList(offenderProperty);
		if (result == 1) {
			offenderProperty.setSentenceSeq(9L);
		}
		return offenderProperty;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgReasonRecordGroup() {
		return oidpawliRepository.rgReasonRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgPriorityRecordGroup() {
		return oidpawliRepository.rgPriorityRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<VPrisonActivities> rgServicesRecordGroup(final String ageLocId) {
		return oidpawliRepository.rgServicesRecordGroup(ageLocId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<AgencyLocations> rgEstablishmentRecordGroup(final String agyLocId) {
		return oidpawliRepository.rgEstablishmentRecordGroup(agyLocId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgDecisionRecordGroup(final String systemMode) {
		final List<ReferenceCodes> returnlist = oidpawliRepository.rgDecisionRecordGroup(systemMode);
		for (final ReferenceCodes bean : returnlist) {
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
	public List<CourseActivities> rgActDescRecordGroup(final String input) {
		if (input.contains("-")) {
			final String[] returnArray = input.split("-");
			return oidpawliRepository.rgActDescRecordGroup(returnArray[0], returnArray[1]);
		} else {
			return new ArrayList<>();
		}

	}

	/**
	 * This method is used to get booking date
	 *
	 * @throws SQLException
	 */
	public Date getBookingDate(final Integer bookingId) {
		return tagPrisonActivitiesService.getBookingDate(bookingId.longValue());

	}

	public Map<String, Object> checkWaitList2(final OffenderProgramProfiles searchRecord) {
		Map<String, Object> waitList = null;
		waitList = tagPrisonActivitiesService.ChkWaitlist2(searchRecord);
		return waitList;
	}

	@Override
	public OffenderProgramProfiles getCourseActivity(final OffenderProgramProfiles offProfiles) {
		OffenderProgramProfiles retData = new OffenderProgramProfiles();
		retData = tagPrisonActivitiesService.getCourseActivity(offProfiles);
		if (retData != null && retData.getCrsActyId() != null) {
			Integer vacancy = tagPrgService.courseVacancy(retData.getCrsActyId());
			retData.setVacancy(vacancy.toString());
		}
		return retData;
	}

	@Override
	public Long ocdxprogOffPrgrefId() {
		return oidpawliRepository.ocdxprogOffPrgrefId();
	}

	@Override
	public Map<String, Object> chkAllocated(final List<OffenderProgramProfiles> searchRecord) {
		Map<String, Object> waitList = new HashedMap<String, Object>();
		for (OffenderProgramProfiles obj : searchRecord) {
			Long count = tagPrisonActivitiesService.chkAllocated(obj.getCrsActyId(), obj.getOffenderBookId(),
					obj.getOffenderStartDate());
			waitList.put("RETURN_VALUE", count);
			if (waitList.get("RETURN_VALUE") == Integer.valueOf(1)) {
				return waitList;
			}
		}
		return waitList;
	}

	@Override
	public Map<String, Object> backdateAttendances(final OffenderProgramProfiles searchRecord) {
		Map<String, Object> waitList = new HashedMap<String, Object>();
		Integer count = tagPrisonActivitiesService.backdateAttendances(searchRecord);
		waitList.put("COUNT", count);
		return waitList;
	}

	@Override
	public Long futureDays(final OffenderProgramProfiles searchBean) {
		return oidpawliRepository.futureDays(searchBean.getOffenderStartDate());
	}

	@Override
	public List<OffenderProgramProfiles> checkNonAssociations(List<OffenderProgramProfiles> searchRecord,
			String userName) {

		List<OffenderProgramProfiles> returnMessage = new ArrayList<OffenderProgramProfiles>();
		try {
			List<OffenderProgramProfiles> OffenderProgramProfilesFinalList = new ArrayList<OffenderProgramProfiles>();

			if (searchRecord != null) {
				OffenderProgramProfilesFinalList.addAll(searchRecord);
			}

			returnMessage = getNonAssociationsData(OffenderProgramProfilesFinalList, userName);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "Error in checkNonAssociations ", e);
		}

		return returnMessage;
	}

	private List<OffenderProgramProfiles> getNonAssociationsData(
			List<OffenderProgramProfiles> OffenderProgramProfilesFinalList, String createUserId) {
		String returnMessage = "";
		List<Long> offendersList = new ArrayList<Long>();
		
		for (OffenderProgramProfiles offenderProgramProfiles : OffenderProgramProfilesFinalList) {
			offendersList.add(offenderProgramProfiles.getOffenderId());
		}
		
		try {
			if (OffenderProgramProfilesFinalList != null && OffenderProgramProfilesFinalList.size() > 0
					&& OffenderProgramProfilesFinalList.get(0) != null
					&& OffenderProgramProfilesFinalList.get(0).getOffenderBookId() != null) {

				for (OffenderProgramProfiles vOffSch : OffenderProgramProfilesFinalList) {
					String individualNonAssociationMessages = "";
					String gandNonAssociationMessages = "";
					Offenders mainOffender = oidpawliRepository
							.getOffernderDataWithOffenderBookId(BigDecimal.valueOf(vOffSch.getOffenderBookId()));
					List<OffenderNaDetails> nonAssociationList = nonAssociationService
							.getNonAssociationOffenderList(vOffSch.getOffenderBookId().intValue());
					if (nonAssociationList != null && nonAssociationList.size() > 0) {

						List<Offenders> sameActiveDescriptionLocationData = oidpawliRepository.getNaPrgSrv(vOffSch);
						
						List<Offenders> individualList = oidpawliRepository
								.getNonAssocationOffenderDetailsForInd(vOffSch, offendersList);
						if (individualList != null && individualList.size() > 0)
							sameActiveDescriptionLocationData.addAll(individualList);

						if (sameActiveDescriptionLocationData != null && sameActiveDescriptionLocationData.size() > 0) {
							for (Offenders data : sameActiveDescriptionLocationData) {
								String message = "";

								message = data.getLastName() + ", " + data.getFirstName() + ", "
										+ data.getOffenderIdDisplay() + "\n";

								message = message + "\n";
								individualNonAssociationMessages = individualNonAssociationMessages + message;
							}
							individualNonAssociationMessages = individualNonAssociationMessages + "\n";

						}

					}
					if (individualNonAssociationMessages != null
							&& !individualNonAssociationMessages.equalsIgnoreCase("")) {
						individualNonAssociationMessages = "oidpawli.indinonassocconflict:\n\n"
								+ individualNonAssociationMessages;
					}
					List<Offenders> gangMemberActivityLocationData = oidpawliRepository.getStgNaPrgSrv(vOffSch);
					
					List<Offenders> gangNonAssocationList = oidpawliRepository
							.getNonAssocationOffenderDetailsForGang(vOffSch, offendersList);

					if (gangNonAssocationList != null && gangNonAssocationList.size() > 0)
						gangMemberActivityLocationData.addAll(gangNonAssocationList);
					
					String message = "";
					if (gangMemberActivityLocationData != null && gangMemberActivityLocationData.size() > 0) {
						for (Offenders bean : gangMemberActivityLocationData) {

							message = bean.getLastName() + ", " + bean.getFirstName() + ", "
									+ bean.getOffenderIdDisplay() + "\n";

							message = message + "\n";
						}
						gandNonAssociationMessages = gandNonAssociationMessages + message + "\n";
					}

					if (gandNonAssociationMessages != null && !gandNonAssociationMessages.equalsIgnoreCase("")) {
						gandNonAssociationMessages = "oidpawli.gangnonassocconflict:\n\n" + gandNonAssociationMessages;
					}

					if ((individualNonAssociationMessages != null
							&& !individualNonAssociationMessages.equalsIgnoreCase(""))
							&& (gandNonAssociationMessages != null
									&& !gandNonAssociationMessages.equalsIgnoreCase(""))) {
						returnMessage = getFinalMessageString(
								individualNonAssociationMessages + "\n" + gandNonAssociationMessages, mainOffender);
					} else if (individualNonAssociationMessages != null
							&& !individualNonAssociationMessages.equalsIgnoreCase("")) {
						returnMessage = getFinalMessageString(individualNonAssociationMessages, mainOffender);
					} else if (gandNonAssociationMessages != null && !gandNonAssociationMessages.equalsIgnoreCase("")) {
						returnMessage = getFinalMessageString(gandNonAssociationMessages, mainOffender);
					} else {
						returnMessage = ApplicationConstants.EMPTYDATA;
					}
					vOffSch.setNonAssocationByIngAndGang(returnMessage);

				}
			} else {
				returnMessage = ApplicationConstants.EMPTYDATA;
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "Error in getNonAssociationsData ", e);
		}
		return OffenderProgramProfilesFinalList;
	}

	private String getFinalMessageString(String messageData, Offenders mainOffender) {
		return "oidpawli.nonassociationconflictmsg (" + mainOffender.getLastName() + ", " + mainOffender.getFirstName()
				+ ", " + mainOffender.getOffenderIdDisplay() + ")oidpawli.waitlisted" + "\n\n" + messageData
				+ " \n oidpawli.doyouwanttocontinue ";
	}

}
