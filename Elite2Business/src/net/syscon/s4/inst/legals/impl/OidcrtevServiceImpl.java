package net.syscon.s4.inst.legals.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.programsservices.OcdprogrRepository;
import net.syscon.s4.cm.weeklyactivityplans.WeeklyActivityPlans;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderStgAffiliations;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.legals.OidcrtevRepository;
import net.syscon.s4.inst.legals.OidcrtevService;
import net.syscon.s4.inst.legals.beans.Court;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.inst.schedules.bean.CourtEventsCommitBean;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.iwp.OcdclogsRepository;
import net.syscon.s4.iwp.impl.OcdclogsServiceImpl;
import net.syscon.s4.pkgs.non_association.NonAssociationService;
import net.syscon.s4.triggers.OffCourtEventVineIntfTrgService;

@Service
public class OidcrtevServiceImpl implements OidcrtevService {

	@Autowired
	private OidcrtevRepository oidcrtevRepository;

	@Autowired
	private OffCourtEventVineIntfTrgService offCourtEventVineIntfTrgService;

	@Autowired
	private OcdprogrRepository ocdprogrRepository;

	@Autowired
	private NonAssociationService nonAssociationService;

	@Autowired
	private OcdclogsServiceImpl ocdclogsServiceImpl;
	
	
	private static Logger logger = LogManager.getLogger(OidcrtevServiceImpl.class.getName());

	public List<CourtEvents> crtEveExecuteQuery(final CourtEvents searchRecord) {
		logger.info(searchRecord.toString());
		List<CourtEvents> returnList = new ArrayList<CourtEvents>();
		
		List<AgencyLocations> apperancelocationList =  oidcrtevRepository.getApperancelocationRecordGroup(searchRecord.getAgyLocId());

		try {
	        returnList = oidcrtevRepository.crtEveExecuteQuery(searchRecord);
	        returnList.forEach(e -> {
	           
	            if (e.getEventStatus() != null && !(ApplicationConstants.COMP.equals(e.getEventStatus()) ||ApplicationConstants.CANC.equals(e.getEventStatus())) &&
	                    !apperancelocationList.stream().anyMatch(location -> Objects.equals(location.getCode(), e.getAppearanceLocation()))) {
	                        e.setAppearanceLocation(null);
	                }
	        });

		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in crtEveExecuteQuery" + e);
			return Collections.emptyList();
		}
		return returnList;
	}

	@Override
	public List<Court> populateCourtData() {
		List<Court> courtList = new ArrayList<Court>();
		try {
			courtList = oidcrtevRepository.populateCourtData();
		} catch (Exception e) {
			courtList = Collections.emptyList();
			logger.error(this.getClass().getName() + " error in populateCourtData" + e);
		}
		return courtList;
	}

	@Transactional(rollbackFor = Exception.class)
	public Integer crtEveCommit(final CourtEventsCommitBean commitBean) {
		logger.info(commitBean.toString());
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final CourtEvents obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			try {
				liReturn = oidcrtevRepository.crtEveInsertCourtEvents(commitBean.getInsertList());
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " error in crtEveCommit" + e);
				liReturn = 0;
			}
			for (CourtEvents bean : commitBean.getInsertList()) {
				try {
					 offCourtEventVineIntfTrgService.offCourtEventVineIntfTrg(bean, "INSERTING");
				} catch (Exception e) {
					logger.error(this.getClass().getName() + " error in  crtEveCommit" + e);
				}
			}
		}

		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final CourtEvents obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			try {
				liReturn = oidcrtevRepository.crtEveUpdateCourtEvents(commitBean.getUpdateList());						
			} catch (Exception e) {
				liReturn = 0;
				logger.error(this.getClass().getName() + " error  in crtEveCommit" + e);
			}
			for (CourtEvents bean : commitBean.getUpdateList()) {
				try {
					 offCourtEventVineIntfTrgService.offCourtEventVineIntfTrg(bean, "UPDATING");
				} catch (Exception e) {
					logger.error(this.getClass().getName() + " error in crtEveCommit" + e);
				}
			}
			
			for (CourtEvents obj : commitBean.getUpdateList()) {
				try {
					VOffenderAllSchedules offsch = new VOffenderAllSchedules();
					offsch.setEventDate(obj.getEventDate());
					offsch.setStartTime(obj.getStartTime());
					offsch.setOffenderBookId(BigDecimal.valueOf(obj.getOffenderBookId()));
					offsch.setEventOutcome(obj.getOutcomeReasonCode());
					offsch.setToAgyLocId(obj.getCourt());
					offsch.setScheduleType("CE");
					offsch.setCreateUserId(commitBean.getCreateUserId());
					offsch.setEventId(BigDecimal.valueOf(obj.getEventId()));
					List<WeeklyActivityPlans> returnList = ocdclogsServiceImpl.getWeeklyActivityPlanData(offsch);
					ocdclogsServiceImpl.saveWeeklyActivitePlanData(returnList, offsch);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return liReturn;
	}

	public List<AgencyLocations> hearingreasonRecordGroup() {
		List<AgencyLocations> list = new ArrayList<AgencyLocations>();
		try {
			list = oidcrtevRepository.hearingreasonRecordGroup();
		} catch (Exception e) {
			list = Collections.emptyList();
			logger.error(this.getClass().getName() + " error in hearingreasonRecordGroup" + e);
		}
		for (AgencyLocations bean : list) {
			if (ApplicationConstants.YFLAG.equalsIgnoreCase(bean.getActiveFlag())) {
				bean.setCanDisplay(true);
			} else {
				bean.setCanDisplay(false);
			}
		}
		return list;
	}

	@Override
	public List<AgencyLocations> apperancelocationRecordGroup(String agyLocId) {
		try {
				
			List<AgencyLocations> returnList = new ArrayList<>();
			returnList = oidcrtevRepository.apperancelocationRecordGroup(agyLocId);
			returnList.forEach(ele -> {
				if(!agyLocId.equalsIgnoreCase(ele.getAgyLocId())){
					ele.setCanDisplay(false);
				}
			});
			return returnList;
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in apperancelocationRecordGroup" + e);
			return Collections.emptyList();
		}
	}

	@Override
	public String checkNonAssociations(CourtEventsCommitBean commitBean) {
		String ressponseMessage = "";
		String inpMessage = "";
		String omevidGroupMessage = "";
		List<CourtEvents> courtEventsAllList = new ArrayList<CourtEvents>();
		try {
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				courtEventsAllList.addAll(commitBean.getInsertList());
			}
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				courtEventsAllList.addAll(commitBean.getUpdateList());
			}
			if (courtEventsAllList != null && courtEventsAllList.size() > 0) {
				inpMessage = checkNonAssociationsDataForINP(courtEventsAllList, commitBean.getCreateUserId());
				omevidGroupMessage = checkNonAssociationsDataForOMEVID(courtEventsAllList,
						commitBean.getCreateUserId());
			}

		} catch (Exception e) {

		}
		return getFinalMessageString(inpMessage, omevidGroupMessage);
	}

	public String checkNonAssociationsDataForINP(List<CourtEvents> courtEventsList, String userId) {
		String inpIndividualMessage = "";
		String inpGroupMessage = "";
		String returnMessage = "";
		if (courtEventsList != null && courtEventsList.size() > 0) {
			List<OffenderNaDetails> offenderNaDetailsList = new ArrayList<OffenderNaDetails>();
			try {
				offenderNaDetailsList = nonAssociationService
						.getNonAssociationOffenderList(courtEventsList.get(0).getOffenderBookId());
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " error checkNonAssociationsDataForINP" + e);
				offenderNaDetailsList = Collections.emptyList();
			}
			for (CourtEvents courtEvent : courtEventsList) {
				if (courtEvent.getAppearanceType().equals("INP")) {
					for (OffenderNaDetails offenderNaDetails : offenderNaDetailsList) {
						List<CourtEvents> courtDataList = new ArrayList<CourtEvents>();
						try {
							courtDataList = oidcrtevRepository
									.getOffenderCourtData(offenderNaDetails.getNsOffenderBookId(), courtEvent);
						} catch (Exception e) {
							courtDataList = Collections.emptyList();
							logger.error(this.getClass().getName() + " error in checkNonAssociationsDataForINP" + e);
						}
						if (courtDataList != null && courtDataList.size() > 0) {
							VHeaderBlock bean = new VHeaderBlock();
							try {
								bean = ocdprogrRepository
										.ocdprogrGetOffenderNames(offenderNaDetails.getNsOffenderBookId(), userId);
							} catch (Exception e) {
								bean = null;
								logger.error(
										this.getClass().getName() + " error in checkNonAssociationsDataForINP" + e);
							}
							String message = "";
							message = bean.getLastName() + " " + bean.getFirstName() + ", "
									+ bean.getOffenderIdDisplay() + "\n";
							for (CourtEvents courtData : courtDataList) {
								java.util.Date stDate = courtData.getStartTime();
								java.util.Date apDate = courtData.getEventDate();
								SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
								String startTime = stDate != null ? sdf.format(stDate) : "";
								SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
								String appDate = sdf1 != null ? sdf1.format(apDate) : "";
								message = message + appDate + ", " + startTime + "\n";
							}
							inpIndividualMessage = inpIndividualMessage + message + "\n";
						}
					}
				}
			}
			if (inpIndividualMessage != null && !inpIndividualMessage.equalsIgnoreCase("")) {
				inpIndividualMessage = "oidcrtev.indinonassocconflict:\n\n" + inpIndividualMessage;
			}
			List<OffenderStgAffiliations> offenderStgAffiliationsList = new ArrayList<OffenderStgAffiliations>();
			try {
				offenderStgAffiliationsList = nonAssociationService.getOffendersOfNonAssociationGroup(
						BigDecimal.valueOf(courtEventsList.get(0).getOffenderBookId()));
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " error in checkNonAssociationsDataForINP" + e);
			}
			if (offenderStgAffiliationsList != null && offenderStgAffiliationsList.size() > 0) {
				for (OffenderStgAffiliations offenderStgAffiliations : offenderStgAffiliationsList) {
					for (CourtEvents courtEvent : courtEventsList) {
						if (courtEvent.getAppearanceType().equals("INP")) {
							List<CourtEvents> courtDataList = new ArrayList<CourtEvents>();
							try {
								courtDataList = oidcrtevRepository
										.getOffenderCourtData(offenderStgAffiliations.getOffenderBookId(), courtEvent);
							} catch (Exception e) {
								courtDataList = Collections.emptyList();
								logger.error(
										this.getClass().getName() + " error in checkNonAssociationsDataForINP" + e);
							}
							if (courtDataList != null && courtDataList.size() > 0) {
								VHeaderBlock bean = new VHeaderBlock();
								try {
									bean = ocdprogrRepository.ocdprogrGetOffenderNames(
											offenderStgAffiliations.getOffenderBookId(), userId);
								} catch (Exception e) {
									bean = null;
									logger.error(
											this.getClass().getName() + " error in checkNonAssociationsDataForINP" + e);
								}
								String message = "";
								message = bean.getLastName() + " " + bean.getFirstName() + ", "
										+ bean.getOffenderIdDisplay() + "\n";
								for (CourtEvents courtData : courtDataList) {
									java.util.Date stDate = courtData.getStartTime();
									java.util.Date apDate = courtData.getEventDate();
									SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
									String startTime = stDate != null ? sdf.format(stDate) : "";
									SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
									String appDate = sdf1 != null ? sdf1.format(apDate) : "";
									message = message + appDate + ", " + startTime + "\n";
								}
								inpGroupMessage = inpGroupMessage + message + "\n";
							}
						}

					}
				}
			}
			if (inpGroupMessage != null && !inpGroupMessage.equalsIgnoreCase("")) {
				inpGroupMessage = "oidcrtev.gangnonassocconflict:\n\n" + inpGroupMessage;
			}
		}
		if ((inpIndividualMessage != null && !inpIndividualMessage.equalsIgnoreCase(""))
				&& (inpGroupMessage != null && !inpGroupMessage.equalsIgnoreCase(""))) {
			returnMessage = inpIndividualMessage + "\n" + inpGroupMessage;
		} else if (inpIndividualMessage != null && !inpIndividualMessage.equalsIgnoreCase("")) {
			returnMessage = inpIndividualMessage;
		} else if (inpGroupMessage != null && !inpGroupMessage.equalsIgnoreCase("")) {
			returnMessage = inpGroupMessage;
		}
		return returnMessage;
	}

	public String checkNonAssociationsDataForOMEVID(List<CourtEvents> courtEventsList, String userId) {
		String inpIndividualMessage = "";
		String inpGroupMessage = "";
		String returnMessage = "";
		if (courtEventsList != null && courtEventsList.size() > 0) {
			for (CourtEvents courtEvent : courtEventsList) {
				
				List<Offenders> offenderDetails = oidcrtevRepository
						.getIndividualNonassocationDetailsByOMEORVID(courtEvent);
				
				if (offenderDetails != null && offenderDetails.size() > 0) {
					String message = "";
					for (Offenders courtData : offenderDetails) {
						String offenderName =courtData.getLastName()+ " "+ courtData.getFirstName()+ " " +courtData.getOffenderIdDisplay();
						java.util.Date stDate = courtData.getCreateDateTime();
						java.util.Date apDate = courtData.getModifyDateTime();
						SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
						String startTime = stDate != null ? sdf.format(stDate) : "";
						SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
						String appDate = sdf1 != null ? sdf1.format(apDate) : "";
						message = message+offenderName +" " + appDate + ", " + startTime + "\n";
					}
					if (message.length() > 0)
						inpIndividualMessage = inpIndividualMessage + message + "\n";
				}
			}
			if (inpIndividualMessage != null && !inpIndividualMessage.equalsIgnoreCase("")) {
				inpIndividualMessage = "oidcrtev.indinonassocconflict:\n\n" + inpIndividualMessage;
			}
			List<OffenderStgAffiliations> offenderStgAffiliationsList = new ArrayList<OffenderStgAffiliations>();
			try {
				offenderStgAffiliationsList = nonAssociationService.getOffendersOfNonAssociationGroup(
						BigDecimal.valueOf(courtEventsList.get(0).getOffenderBookId()));
			} catch (Exception e) {
				offenderStgAffiliationsList = Collections.emptyList();
				logger.error(this.getClass().getName() + " error in checkNonAssociationsDataForOMEVID" + e);
			}
			if (offenderStgAffiliationsList != null && offenderStgAffiliationsList.size() > 0) {
				for (OffenderStgAffiliations offenderStgAffiliations : offenderStgAffiliationsList) {
					for (CourtEvents courtEvent : courtEventsList) {
						if (courtEvent.getAppearanceType().equals("OME")
								|| courtEvent.getAppearanceType().equals("VID")) {
							List<CourtEvents> courtDataList = new ArrayList<CourtEvents>();
							try {
								courtDataList = oidcrtevRepository.getOffenderCourtDataByOMEORVID(
										offenderStgAffiliations.getOffenderBookId(), courtEvent.getAppearanceLocation(),
										courtEvent.getEventDate());
							} catch (Exception e) {
								courtDataList = Collections.emptyList();
								logger.error(
										this.getClass().getName() + " error in checkNonAssociationsDataForOMEVID" + e);
							}
							if (courtDataList != null && courtDataList.size() > 0) {
								VHeaderBlock bean = new VHeaderBlock();
								try {
									bean = ocdprogrRepository.ocdprogrGetOffenderNames(
											offenderStgAffiliations.getOffenderBookId(), userId);
								} catch (Exception e) {
									bean = null;
									logger.error(this.getClass().getName()
											+ " error in checkNonAssociationsDataForOMEVID" + e);
								}
								String message = "";
								message = bean.getLastName() + " " + bean.getFirstName() + ", "
										+ bean.getOffenderIdDisplay() + "\n";
								for (CourtEvents courtData : courtDataList) {
									java.util.Date stDate = courtData.getStartTime();
									java.util.Date apDate = courtData.getEventDate();
									SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
									String startTime = stDate != null ? sdf.format(stDate) : "";
									SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
									String appDate = sdf1 != null ? sdf1.format(apDate) : "";
									message = message + appDate + ", " + startTime + "\n";
								}
								if (!inpGroupMessage.contains(message))
									inpGroupMessage = inpGroupMessage + message + "\n";
							}
						}

					}
				}
			}
			if (inpGroupMessage != null && !inpGroupMessage.equalsIgnoreCase("")) {
				inpGroupMessage = "oidcrtev.gangnonassocconflict:\n\n" + inpGroupMessage;
			}
		}
		if ((inpIndividualMessage != null && !inpIndividualMessage.equalsIgnoreCase(""))
				&& (inpGroupMessage != null && !inpGroupMessage.equalsIgnoreCase(""))) {
			returnMessage = inpIndividualMessage + "\n" + inpGroupMessage;
		} else if (inpIndividualMessage != null && !inpIndividualMessage.equalsIgnoreCase("")) {
			returnMessage = inpIndividualMessage;
		} else if (inpGroupMessage != null && !inpGroupMessage.equalsIgnoreCase("")) {
			returnMessage = inpGroupMessage;
		}
		return returnMessage;
	}

	public String getFinalMessageString(String finalINPMessage, String finalOVMessge) {
		String returnMessage = "";
		if ((finalINPMessage != null && !finalINPMessage.equalsIgnoreCase(""))
				&& (finalOVMessge != null && !finalOVMessge.equalsIgnoreCase(""))) {
			returnMessage = "oidcrtev.inPersonCourtData \n\n" + finalINPMessage + "\n" + "oidcrtev.cctvORonline \n\n"
					+ finalOVMessge;
		} else if (finalINPMessage != null && !finalINPMessage.equalsIgnoreCase("")) {
			returnMessage = "oidcrtev.inPersonCourtData \n\n" + finalINPMessage;
		} else if (finalOVMessge != null && !finalOVMessge.equalsIgnoreCase("")) {
			returnMessage = "oidcrtev.cctvORonline \n\n" + finalOVMessge;
		}
		if (returnMessage != null && !returnMessage.equalsIgnoreCase("")) {
			return returnMessage + " \n oidcrtev.doyouwanttocontinue";
		}
		return ApplicationConstants.EMPTYDATA;
	}

	@Override
	public String getDefaultCancellationReason() {

		return oidcrtevRepository.getDefaultCancellationReason();
	}

	@Override
	public Integer courtEventsSave(CourtEvents courtEvents) {
		List<CourtEvents> courtEventsList = new ArrayList<CourtEvents>();
		Integer result = null;
		courtEventsList.add(courtEvents);
		if (courtEvents.getModifyUserId() != null) {
			try {
				result = oidcrtevRepository.crtEveUpdateCourtEvents(courtEventsList);			
			} catch (Exception e) {
				result = 0;
			}			
			for (CourtEvents obj : courtEventsList) {
				try {
					VOffenderAllSchedules offsch = new VOffenderAllSchedules();
					offsch.setEventDate(obj.getEventDate());
					offsch.setStartTime(obj.getStartTime());
					offsch.setOffenderBookId(BigDecimal.valueOf(obj.getOffenderBookId()));
					offsch.setEventOutcome(obj.getEventStatus());
					offsch.setToAgyLocId(obj.getCourt());
					offsch.setScheduleType("CE");
					offsch.setCreateUserId(courtEvents.getModifyUserId());
					offsch.setEventId(BigDecimal.valueOf(obj.getEventId()));
					List<WeeklyActivityPlans> returnList = ocdclogsServiceImpl.getWeeklyActivityPlanData(offsch);
					ocdclogsServiceImpl.saveWeeklyActivitePlanData(returnList, offsch);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		} else {
			try {
				result = oidcrtevRepository.crtEveInsertCourtEvents(courtEventsList);
			} catch (Exception e) {
				result = 0;
			}
		}
		return result;
	}

	@Override
	public CourtEvents phoneNumberAndEmailCheck(BigDecimal offenderBookId) {
		CourtEvents emailAndPhoneNumberCheck = new CourtEvents();
		Object[] obj = oidcrtevRepository.phoneNumberAndEmailCheck(offenderBookId);
		if (obj != null) {
			emailAndPhoneNumberCheck.setEmailCheckFlag(
					(obj[0].toString() != null && Integer.parseInt(obj[0].toString()) > 0) ? true : false);
			emailAndPhoneNumberCheck.setPhoneNumberCheckFlag(
					(obj[1].toString() != null && Integer.parseInt(obj[1].toString()) > 0) ? true : false);

		}
		return emailAndPhoneNumberCheck;
	}

}
