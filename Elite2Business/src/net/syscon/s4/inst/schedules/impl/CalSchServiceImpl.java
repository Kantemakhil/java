package net.syscon.s4.inst.schedules.impl;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cm.community_supervision_tiers.OcmtidetRepository;
import net.syscon.s4.cm.community_supervision_tiers.OcmtidetService;
import net.syscon.s4.cm.programsservices.OcdprogrRepository;
import net.syscon.s4.cm.weeklyactivityplans.OweacplnRepository;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.casemanagement.OiioscedRepository;
import net.syscon.s4.inst.legals.OidcrtevRepository;
import net.syscon.s4.inst.legals.OidcrtevService;
import net.syscon.s4.inst.legals.OsanviosRepository;
import net.syscon.s4.inst.schedules.CalSchService;
import net.syscon.s4.inst.schedules.OidstabsRepository;
import net.syscon.s4.inst.schedules.bean.CalScheduleBean;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.inst.schedules.bean.VAddressUsages;
import net.syscon.s4.inst.schedules.bean.VCorporateAddresses;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;

@Service
public class CalSchServiceImpl implements CalSchService {

	@Autowired
	private OiioscedRepository oiioscedRepository;
	@Autowired
	private OidstabsRepository oidstabsRepository;

	@Autowired
	private OweacplnRepository oweacplnRepository;

	@Autowired
	private OidcrtevRepository oidcrtevRepository;

	@Autowired
	private OcdprogrRepository ocdprogrRepository;

	@Autowired
	private OcmtidetService ocmtidetService;

	@Autowired
	private OcmtidetRepository ocmtidetrepository;

	@Autowired
	private OidcrtevService oidcrtevService;

	@Autowired
	private OsanviosRepository osanviosRepository;

	private List<VOffenderAllSchedules> offenderSchedList;
	
	private static final String INT_EVNT = "INT_MOV";
	private static final String EXT_EVNT = "EXT_MOV";
	private static final String INST_ACT = "INST_ACT";
	private static final String TAP = "TAP";
	private static final String CRT = "CRT";
	private static final String COMM_EVNT = "COMM";
	private static final String HYPHEN = "-";
	private static final String INT_COLOR = "internalEventColor";
	private static final String INT_TIME = "intEvntEndTime";
	private static final String EXT_COLOR = "externalEventColor";
	private static final String EXT_TIME = "extEvntEndTime";
	private static final String TEMP_COLOR = "temparoryEventColor";
	private static final String PROG_COLOR = "programEventColor";
	private static final String COURT_COLOR = "courtEventColor";
	private static final String COMM_EVNT_TIME = "communityEndTime";
	private static final String COMM_EVNT_COLOR = "communityEventColor";
	private static final Integer DEAULT_EVNT_TIME = 60;
	private static final String DEAULT_EVNT_CLR = "#3336FF";
	private static final String CANC_STATUS_CLR = "#F02C3D";
	private static final String ACP = "ACP";
	private static final String PEND_TEMP_ABS_EVENT_COLOR = "pendTempAbsEventColor";
	private static final String PEN_STATUS="PEN";

	@Override
	public List<CalScheduleBean> getEventData(CalScheduleBean serachObj) {
		List<CalScheduleBean> searchResult = new ArrayList<>();
		List<CalScheduleBean> finalSearchResult = new ArrayList<>();
		List<VOffenderAllSchedules> returnDBList=new ArrayList<VOffenderAllSchedules>();
		VOffenderAllSchedules obj = new VOffenderAllSchedules();
		obj.setOffenderBookId(serachObj.getOffenderBookId());
		obj.setCaseLoadId(serachObj.getCaseLoadId());
		obj.setEventStatus(serachObj.getEventStatus());
		offenderSchedList =new ArrayList<VOffenderAllSchedules>();
		offenderSchedList = oiioscedRepository.vOffenderAllSchedulesQuery(obj);
		if (offenderSchedList != null && offenderSchedList.size() > 0) {
			for (VOffenderAllSchedules vOffenderAllSchedules : offenderSchedList) {
				vOffenderAllSchedules.setCurrentSelectedViewClass(serachObj.getCurrentSelectedViewClass());
				if (vOffenderAllSchedules.getEventType().equalsIgnoreCase(CRT)) {
					Integer linkData = osanviosRepository.getLinkedCourtEventData(vOffenderAllSchedules.getEventId());
					vOffenderAllSchedules.setLinkData(linkData);
				}
				if(vOffenderAllSchedules.getEventType().equalsIgnoreCase(TAP)) {
					if(vOffenderAllSchedules.getEventStatus().equals(ApplicationConstants.COMP)||vOffenderAllSchedules.getEventStatus().equals(ApplicationConstants.PEN) || vOffenderAllSchedules.getEventStatus().equals(ApplicationConstants.CANC) 
							|| (vOffenderAllSchedules.getEventStatus().equals(ApplicationConstants.SCH) && vOffenderAllSchedules.getReturnDate()!= null) ) {
						returnDBList.add(vOffenderAllSchedules);
					}
				} else {
					returnDBList.add(vOffenderAllSchedules);
				}
			}
		}
		offenderSchedList=new ArrayList<VOffenderAllSchedules>();
		if(!returnDBList.isEmpty()) {		
			offenderSchedList.addAll(returnDBList);
		}
		List<VOffenderAllSchedules> wapSchedulesList = oweacplnRepository.getWapManualActivities(obj);
		for (VOffenderAllSchedules wapObj : wapSchedulesList) {
			wapObj.setCurrentSelectedViewClass(serachObj.getCurrentSelectedViewClass());
		}
		if (!wapSchedulesList.isEmpty()) {
			offenderSchedList.addAll(wapSchedulesList);
		}
		searchResult = setSchedulesData(offenderSchedList, searchResult, serachObj);
		searchResult = searchResult.stream().filter(e -> !e.getEventStatus().equalsIgnoreCase("PROG_CANC")).collect(Collectors.toList());
		return searchResult;
	}

	private List<CalScheduleBean> setSchedulesData(List<VOffenderAllSchedules> returnList,
			List<CalScheduleBean> searchResult, CalScheduleBean serachObj) {
		ReferenceCodes obj = new ReferenceCodes();
		if (returnList.size() > 0) {
			obj = ocmtidetService.getActiveTierEvent(returnList.get(0).getOffenderBookId().longValue());
		}
		if (returnList != null && !returnList.isEmpty()) {
			for (VOffenderAllSchedules scheduleData : returnList) {
				CalScheduleBean calScheduleBean = new CalScheduleBean();
				String locDesc = null;
				calScheduleBean.setStartTime(scheduleData.getStartTime());
				if (scheduleData.getLinkData() != null) {
					calScheduleBean.setLinkData(scheduleData.getLinkData());
				}
				if(scheduleData.getEventStatus()!=null) {					
					calScheduleBean.setEventStatus(scheduleData.getEventStatus());
				}
				if (scheduleData.getStartTime() != null && scheduleData.getEndTime() == null) {
					calScheduleBean.setEndTime(Date
							.from(scheduleData.getStartTime().toInstant().plus(Duration.ofMinutes(DEAULT_EVNT_TIME))));
				} else if (scheduleData.getEndTime() != null) {
					calScheduleBean.setEndTime(scheduleData.getEndTime());
				}
				calScheduleBean.setCategoryColor(DEAULT_EVNT_CLR);
				if (scheduleData.getEventType() != null) {
					if (scheduleData.getEventClass().equalsIgnoreCase(INT_EVNT)) {
						if (scheduleData.getToInternalLocationDesc() != null && scheduleData.getEventTypeDesc() != null
								&& !scheduleData.getEventType().equals(ACP)) {
							calScheduleBean.setSubject(scheduleData.getToInternalLocationDesc().concat(HYPHEN)
									.concat(scheduleData.getEventTypeDesc()));
							calScheduleBean.setEventDate(scheduleData.getEventDate());
							calScheduleBean.setEventSubType(scheduleData.getEventSubType());
							calScheduleBean.setToIntLocLevel1Code(scheduleData.getToInternalLocationId().toString());
							calScheduleBean.setEventId(scheduleData.getEventId());
							calScheduleBean.setRecordSource(scheduleData.getRecordSource());
							calScheduleBean.setEventOutCome(scheduleData.getEventOutcome());
							calScheduleBean.setEventStatus(scheduleData.getEventStatus());
							calScheduleBean.setToAgyLocId(scheduleData.getAgyLocId());

						} else {
							String programDescription = ocdprogrRepository
									.getProgramDescription(scheduleData.getOffPrgrefId());
							
							if(scheduleData.getCsDescription() == null && programDescription!=null ) {
								scheduleData.setCsDescription(programDescription);
								
							}
							calScheduleBean.setSubject(
									ApplicationConstants.ACCREDITED_PROGRAM.concat(HYPHEN).concat(programDescription));
						}
						if (serachObj.getProfileMap().get(INT_COLOR) != null
								&& serachObj.getProfileMap().get(PROG_COLOR) != null) {
							if (scheduleData.getEventType().equalsIgnoreCase(INST_ACT)) {
								calScheduleBean.setCategoryColor((String) serachObj.getProfileMap().get(PROG_COLOR));
							}
							if ("CANC".equalsIgnoreCase(serachObj.getEventStatus())) {
								calScheduleBean.setCategoryColor(CANC_STATUS_CLR);
							} else {
								calScheduleBean.setCategoryColor((String) serachObj.getProfileMap().get(INT_COLOR));
							}

						}
						if ((scheduleData.getStartTime() != null && scheduleData.getEndTime() == null) && serachObj.getProfileMap().get(INT_TIME) != null) {
							calScheduleBean.setEndTime(Date.from(scheduleData.getStartTime().toInstant().plus(Duration
									.ofMinutes(Long.valueOf((Integer) serachObj.getProfileMap().get(INT_TIME))))));
						}
						calScheduleBean = prepareDataBasedOnViewClick(scheduleData,calScheduleBean);
					}else if (scheduleData.getEventClass().equalsIgnoreCase(EXT_EVNT)) {
						calScheduleBean.setEventSubType(scheduleData.getEventSubType());									
						if (scheduleData.getToAgyLocDesc() != null && scheduleData.getEventTypeDesc() != null
								&& scheduleData.getEventType().equalsIgnoreCase(CRT)) {
							calScheduleBean.setSubject(scheduleData.getToAgyLocDesc().concat(HYPHEN)
									.concat(scheduleData.getEventTypeDesc()));
						}
						if (scheduleData.getEventType().equals(ACP)) {
							String programDescription = ocdprogrRepository.getProgramDescription(scheduleData.getOffPrgrefId());
							calScheduleBean.setSubject(
									ApplicationConstants.ACCREDITED_PROGRAM.concat(HYPHEN).concat(programDescription));
						}
						if (scheduleData.getEventType().equalsIgnoreCase(TAP)) {
							if (serachObj.getProfileMap().get(PEND_TEMP_ABS_EVENT_COLOR) != null && scheduleData.getEventStatus().equalsIgnoreCase(PEN_STATUS)) {
								calScheduleBean.setCategoryColor((String) serachObj.getProfileMap().get(PEND_TEMP_ABS_EVENT_COLOR));
							} else if(serachObj.getProfileMap().get(TEMP_COLOR) != null) {							
										calScheduleBean.setCategoryColor((String) serachObj.getProfileMap().get(TEMP_COLOR));
							}

							calScheduleBean.setEndTime(scheduleData.getReturnTime());
							if (scheduleData.getToAgyLocId() == null && scheduleData.getToAgyLocDesc() == null) {
								locDesc = getLocDesc(scheduleData.getToAddressId());
								if (locDesc == null && scheduleData.getOffenderId() != null) {
									locDesc = getOthLocDesc(scheduleData.getToAddressId(),
											scheduleData.getOffenderId().toString());
								}

							}
						} else if (scheduleData.getEventType().equalsIgnoreCase(CRT)) {
							if (scheduleData.getAppearanceType() != null
									&& (scheduleData.getAppearanceType().equalsIgnoreCase("OME")
											|| scheduleData.getAppearanceType().equalsIgnoreCase("INT")
											|| scheduleData.getAppearanceType().equalsIgnoreCase("VID"))) {
								List<AgencyLocations> appearenceLocList = oidcrtevRepository
										.apperancelocationRecordGroup(scheduleData.getAgyLocId());
								String applocation = "";
								if (!appearenceLocList.isEmpty()) {
									for (AgencyLocations calScheduleBean2 : appearenceLocList) {
										if (scheduleData.getAppearanceLocation() != null && scheduleData
												.getAppearanceLocation().equalsIgnoreCase(calScheduleBean2.getCode())) {
											applocation = calScheduleBean2.getDescription();
										}

									}
								}
								calScheduleBean
										.setSubject(ApplicationConstants.COURT_EVENT.concat(", ").concat(applocation));
							} else {
								calScheduleBean.setSubject(ApplicationConstants.COURT_EVENT.concat(", ")
										.concat(scheduleData.getToAgyLocDesc().concat(HYPHEN)
												.concat(scheduleData.getEventTypeDesc())));
							}

							if (serachObj.getProfileMap().get(COURT_COLOR) != null) {
								calScheduleBean.setCategoryColor((String) serachObj.getProfileMap().get(COURT_COLOR));
							}
							if (scheduleData.getStartTime() != null
									&& serachObj.getProfileMap().get(EXT_TIME) != null) {
								calScheduleBean.setEndTime(
										Date.from(scheduleData.getStartTime().toInstant().plus(Duration.ofMinutes(
												Long.valueOf((Integer) serachObj.getProfileMap().get(EXT_TIME))))));
							}
							calScheduleBean.setEventId(scheduleData.getEventId());
							calScheduleBean.setEventSubType(scheduleData.getEventSubType());
							calScheduleBean.setEventSubTypeDesc(scheduleData.getEventSubTypeDesc());
							calScheduleBean.setEventStatus(scheduleData.getEventStatus());
							calScheduleBean.setEventOutCome(scheduleData.getEventOutcome());
							calScheduleBean.setToAgyLocId(scheduleData.getToAgyLocId());
							calScheduleBean.setAppearanceType(scheduleData.getAppearanceType());
							calScheduleBean.setAppearanceLocation(scheduleData.getAppearanceLocation());
							calScheduleBean.setEventDate(scheduleData.getEventDate());
							calScheduleBean.setStartTime(scheduleData.getStartTime());
							calScheduleBean.setComment(scheduleData.getCommentText());
							calScheduleBean.setMatter(scheduleData.getMatter());
							calScheduleBean.setCancelReason(scheduleData.getReasonCode());

						} else {
							if (serachObj.getProfileMap().get(EXT_COLOR) != null) {
								calScheduleBean.setCategoryColor((String) serachObj.getProfileMap().get(EXT_COLOR));

							}
							if ((scheduleData.getStartTime() != null && scheduleData.getEndTime() == null)
									&& serachObj.getProfileMap().get(EXT_TIME) != null) {
								calScheduleBean.setEndTime(
										Date.from(scheduleData.getStartTime().toInstant().plus(Duration.ofMinutes(
												Long.valueOf((Integer) serachObj.getProfileMap().get(EXT_TIME))))));
							}

						}

					} else if (scheduleData.getEventClass().equalsIgnoreCase(COMM_EVNT)) {
						calScheduleBean.setEventId(scheduleData.getEventId());
						if (scheduleData.getStartTime() != null
								&& serachObj.getProfileMap().get(COMM_EVNT_TIME) != null) {
							calScheduleBean.setEndTime(
									Date.from(scheduleData.getStartTime().toInstant().plus(Duration.ofMinutes(
											Long.valueOf((Integer) serachObj.getProfileMap().get(COMM_EVNT_TIME))))));
						}
						if (serachObj.getProfileMap().get(COMM_EVNT_COLOR) != null) {
							calScheduleBean.setCategoryColor((String) serachObj.getProfileMap().get(COMM_EVNT_COLOR));
						}
						if (scheduleData.getEventType() != null) {
							calScheduleBean.setEventType(scheduleData.getEventType());
						}
						if (scheduleData.getInChargeStaffName() != null) {
							calScheduleBean.setInChargeStaffName(scheduleData.getInChargeStaffName());
						}
						if (scheduleData.getEventSubType() != null) {
							calScheduleBean.setEventSubType(scheduleData.getEventSubType());
						}
						if (scheduleData.getEventSubTypeDesc() != null) {
							calScheduleBean.setEventSubTypeDesc(scheduleData.getEventSubTypeDesc());
						}
						if (scheduleData.getToAgyLocId() != null) {
							calScheduleBean.setToAgyLocId(scheduleData.getToAgyLocId());
						}
						if (scheduleData.getEmailFlag() != null) {
							calScheduleBean.setEmailFlag(scheduleData.getEmailFlag());
							calScheduleBean.setEmailScheduleHoursBefore(scheduleData.getEmailScheduleHoursBefore());
						}
						if (scheduleData.getSmsFlag() != null) {
							calScheduleBean.setSmsFlag(scheduleData.getSmsFlag());
							calScheduleBean.setSmsScheduleHoursBefore(scheduleData.getSmsScheduleHoursBefore());
						}
						if (scheduleData.getSeriesId() != null) {
							calScheduleBean.setSeriesId(scheduleData.getSeriesId());
						}
						if (scheduleData.getEventOutcome() != null) {
							calScheduleBean.setEventOutCome(scheduleData.getEventOutcome());
						}
						if (scheduleData.getEndTime() != null) {
							calScheduleBean.setActualEndTime(scheduleData.getEndTime());
						}
						calScheduleBean.setEventId(scheduleData.getEventId());
						calScheduleBean.setEventStatus(scheduleData.getEventStatus());
						calScheduleBean.setAppearanceType(scheduleData.getAppearanceType());
						calScheduleBean.setAppearanceLocation(scheduleData.getAppearanceLocation());
						calScheduleBean.setEventDate(scheduleData.getEventDate());
						calScheduleBean.setStartTime(scheduleData.getStartTime());
						calScheduleBean.setComment(scheduleData.getCommentText());
						calScheduleBean.setMatter(scheduleData.getMatter());
						calScheduleBean.setCancelReason(scheduleData.getReasonCode());
					} else if (scheduleData.getEventClass().equalsIgnoreCase(ApplicationConstants.WAPACTIVITY)) {
						if (scheduleData.getToAgyLocDesc() != null) {
							calScheduleBean.setToAgyLocId(scheduleData.getToAgyLocDesc());
						}
					} else if (ApplicationConstants.DTE.equalsIgnoreCase(scheduleData.getEventClass())) {
						calScheduleBean.setEventId(scheduleData.getEventId());
						calScheduleBean.setOffenderBookId(scheduleData.getOffenderBookId());
						calScheduleBean.setEventDate(scheduleData.getEventDate());
						calScheduleBean.setStartTime(scheduleData.getStartTime());
						calScheduleBean.setEndTime(scheduleData.getEndTime());
						calScheduleBean.setEventClass(scheduleData.getEventClass());
						calScheduleBean.setEventType(scheduleData.getEventType());
						calScheduleBean.setEventSubType(scheduleData.getEventSubType());
						calScheduleBean.setEventSubTypeDesc(scheduleData.getEventStatusDesc());
						calScheduleBean.setToAgyLocId(scheduleData.getToAgyLocId());
						calScheduleBean.setEmailFlag(scheduleData.getEmailFlag());
						calScheduleBean.setSmsFlag(scheduleData.getSmsFlag());
						calScheduleBean.setSubject(
								scheduleData.getToLocDesc().concat(HYPHEN).concat(scheduleData.getEventTypeDesc()));
						calScheduleBean.setInChargeStaffName(scheduleData.getInChargeStaffName());
						calScheduleBean.setSmsScheduleHoursBefore(scheduleData.getSmsScheduleHoursBefore());
						calScheduleBean.setEmailScheduleHoursBefore(scheduleData.getEmailScheduleHoursBefore());
						String tierLevelCode = ocmtidetrepository.getTierLevelCode(calScheduleBean.getEventId());
						calScheduleBean.setVersionNo(ocmtidetrepository.getOffenderTierLevelVersionNo(
								calScheduleBean.getEventId() != null ? calScheduleBean.getEventId().longValue()
										: null));
						if (obj != null && tierLevelCode != null && obj.getCode().equalsIgnoreCase(tierLevelCode)) {
							calScheduleBean.setSealFlag("Y");
						} else {
							calScheduleBean.setSealFlag("N");
						}
						calScheduleBean.setOffenderTierLevelId(
								ocmtidetrepository.getOffenderTierLevelId(calScheduleBean.getEventId().longValue()));

					} else {

						if (scheduleData.getToAgyLocDesc() != null && scheduleData.getEventTypeDesc() != null) {
							calScheduleBean.setSubject(scheduleData.getToAgyLocDesc().concat(HYPHEN)
									.concat(scheduleData.getEventTypeDesc()));
						}
						if (scheduleData.getToInternalLocationDesc() != null
								&& scheduleData.getEventTypeDesc() != null) {
							calScheduleBean.setSubject(scheduleData.getToInternalLocationDesc().concat(HYPHEN)
									.concat(scheduleData.getEventTypeDesc()));
						}

					}

				}
				if (scheduleData.getEventClass() != null) {
					calScheduleBean.setEventClass(scheduleData.getEventClass());
				}
				calScheduleBean.setDescription(scheduleData.getCommentText());
				if (scheduleData.getEventType() != null) {
					calScheduleBean.setEventType(scheduleData.getEventType());
				}
				
				calScheduleBean = prepareDataBasedOnViewClick(scheduleData,calScheduleBean);
				searchResult.add(calScheduleBean);
			}
		}
		return searchResult;
	}

	private String getLocDesc(BigDecimal addressId) {
		String locDesc = null;
		List<VCorporateAddresses> addList = oidstabsRepository.rgCorpLocRecordGroup();
		if (addList != null && addressId != null) {

			for (VCorporateAddresses address : addList) {
				if (address.getAddressId().compareTo(addressId) == 0) {
					locDesc = address.getDescription();
				}

			}
		}

		return locDesc;
	}

	private String getOthLocDesc(BigDecimal addressId, String offenderId) {
		String locDesc = null;
		List<VAddressUsages> addList = oidstabsRepository.rgOthLocRecordGroup(offenderId);
		if (addList != null && addressId != null) {
			for (VAddressUsages address : addList) {
				if (address.getAddressId().compareTo(addressId) == 0) {
					locDesc = address.getDescription();
				}

			}
		}

		return locDesc;
	}

	@Override
	public Integer courtEventsSave(CourtEvents courtEvents) {
		return oidcrtevService.courtEventsSave(courtEvents);
	}

	@Override
	public Boolean checkScreenAccess(String userId) {
		return oidcrtevRepository.checkScreenAccess(userId);
	}
	
	private CalScheduleBean prepareDataBasedOnViewClick(VOffenderAllSchedules inputData, CalScheduleBean outputData) {
		if (ApplicationConstants.CUSTOM_E_DAY_VIEW.equals(inputData.getCurrentSelectedViewClass())
				|| ApplicationConstants.CUSTOM_E_WEEK_VIEW.equals(inputData.getCurrentSelectedViewClass())
				|| ApplicationConstants.CUSTOM_E_TIMELINE_DAY_VIEW.equals(inputData.getCurrentSelectedViewClass())
				|| ApplicationConstants.CUSTOM_E_TIMELINE_WEEK_VIEW.equals(inputData.getCurrentSelectedViewClass())
				|| ApplicationConstants.CUSTOM_E_WORK_WEEK_VIEW.equals(inputData.getCurrentSelectedViewClass())
				|| ApplicationConstants.CUSTOM_E_TIMELINE_WORK_WEEK_VIEW.equals(inputData.getCurrentSelectedViewClass())
				|| ApplicationConstants.CUSTOM_E_TIMELINE_MONTH_VIEW.equals(inputData.getCurrentSelectedViewClass())) {
			if (inputData.getEventClass().equalsIgnoreCase(INT_EVNT)) {
				if (inputData.getEventType().equals(ApplicationConstants.APP)) {
					outputData.setSubject(ApplicationConstants.INTERNAL_APPOINTMENT.concat(HYPHEN).concat(inputData.getEventSubTypeDesc()));
					outputData.setLocation(inputData.getToInternalLocationDesc());
				} else if (inputData.getEventType().equals(ApplicationConstants.OIC)) {
					outputData.setSubject(ApplicationConstants.OIC_HEARING.concat(HYPHEN).concat(inputData.getOicHearingDescription()));
					outputData.setLocation(inputData.getToInternalLocationDesc());
				} else if (inputData.getEventType().equals(ApplicationConstants.VISIT)) {
					outputData.setSubject(ApplicationConstants.VISIT_TEXT.concat(HYPHEN).concat(inputData.getVisitDescription()));
					outputData.setLocation(inputData.getToInternalLocationDesc());
				} else if (inputData.getEventType().equals(ApplicationConstants.INST_ACT)) {
					outputData.setSubject(ApplicationConstants.INSTITUTIONAL_ACTIVITY.concat(HYPHEN).concat(inputData.getCsDescription()));
					outputData.setLocation(inputData.getToInternalLocationDesc());
				}  else if (inputData.getEventType().equals(ApplicationConstants.ACP)) {
					outputData.setSubject(ApplicationConstants.ACCREDITED_PROGRAM.concat(HYPHEN).concat(inputData.getCsDescription()));
					outputData.setLocation(inputData.getCsAddress());
				}
			} else if (inputData.getEventClass().equalsIgnoreCase(EXT_EVNT)) {
				if(ApplicationConstants.OJ.equals(inputData.getEventSubType()) && inputData.getEventType().equals(ApplicationConstants.TRN)) {
					outputData.setSubject(ApplicationConstants.TRANSFER_OUTSIDE_JURISDICTION);
					outputData.setLocation(inputData.getProvStateDesc());
				} else if(!ApplicationConstants.OJ.equals(inputData.getEventSubType()) && inputData.getEventType().equals(ApplicationConstants.TRN)) {
					outputData.setSubject(ApplicationConstants.TRANSFER_WITHIN_JURISDICTION.concat(HYPHEN).concat(inputData.getEventSubTypeDesc()));
					outputData.setLocation(inputData.getToAgyLocDesc());
				} else if(inputData.getEventSubType().equals(ApplicationConstants.WR)) {
					outputData.setSubject(ApplicationConstants.WORK_RELEASE.concat(HYPHEN).concat(inputData.getWorkRelaeseProjectCode()));
					outputData.setLocation(inputData.getWorkRelaeseProvideDesc());
				} else if (inputData.getEventType().equalsIgnoreCase(TAP)) {
					outputData.setSubject(ApplicationConstants.TEMPORARY_ABSENCE.concat(HYPHEN).concat(inputData.getTapDescription()));
					outputData.setLocation(inputData.getToAgyLocDesc());
				}
				else if (inputData.getEventType().equals(ApplicationConstants.ACP)) {
					outputData.setSubject(ApplicationConstants.ACCREDITED_PROGRAM.concat(HYPHEN).concat(inputData.getCsDescription()));
					outputData.setLocation(inputData.getCsAddress());
				}
			} else if (inputData.getEventClass().equalsIgnoreCase(COMM_EVNT)) {
				if (ApplicationConstants.ACP.equals(inputData.getEventType())) {
					if (inputData.getReferenceId() != null) {
						if (inputData.getEventTypeDesc() != null && inputData.getEventTypeDesc() != null && inputData.getCsDescription() != null) {
							outputData.setSubject(inputData.getEventTypeDesc().concat(HYPHEN).concat(inputData.getCsDescription()));
							outputData.setLocation(inputData.getCsAddress());
							outputData.setToAgyLocId(inputData.getAgyLocId());
						}
					} else {
						if (inputData.getEventTypeDesc() != null && inputData.getEventTypeDesc() != null
								&& inputData.getEventSubTypeDesc() != null) {
							outputData.setSubject(inputData.getEventTypeDesc().concat(HYPHEN).concat(inputData.getEventSubTypeDesc()));
							outputData.setLocation(inputData.getAgyLocDesc());
							outputData.setToAgyLocId(inputData.getAgyLocId());
						}
					}
				} else if (ApplicationConstants.UW.equals(inputData.getEventType())) {
					if (inputData.getEventTypeDesc() != null && inputData.getEventTypeDesc() != null) {
						outputData.setSubject(inputData.getEventTypeDesc().concat(HYPHEN).concat(inputData.getCsDescription()));
						outputData.setLocation(inputData.getCsAddress());
						outputData.setToAgyLocId(inputData.getAgyLocId());
					}
				} else {
					if (inputData.getEventTypeDesc() != null && inputData.getEventSubTypeDesc() != null) {
						outputData.setSubject(inputData.getEventTypeDesc().concat(HYPHEN).concat(inputData.getEventSubTypeDesc()));
					}
					if (inputData.getToAgyLocDesc() != null) {
						outputData.setLocation(inputData.getToAgyLocDesc());
					}
				}
					
			} else if (inputData.getEventClass().equalsIgnoreCase(ApplicationConstants.WAPACTIVITY)) {
				outputData.setDepartStartTime(inputData.getDepartStartTime());
				outputData.setReturnTime(inputData.getReturnTime());
				if (inputData.getEventSubTypeDesc() != null) {
					outputData.setSubject(ApplicationConstants.WEEKLY_ACTIVITY_PLAN.concat(HYPHEN).concat(inputData.getEventSubTypeDesc()));
					outputData.setLocation(inputData.getToAgyLocDesc());
				}
			}
		}

		else if ("custom-e-month-view".equals(inputData.getCurrentSelectedViewClass())) {
			if (inputData.getEventClass().equalsIgnoreCase(INT_EVNT)) {
				if (inputData.getEventType().equals(ApplicationConstants.APP)) {
					if (inputData.getEventSubTypeDesc() != null) {
						outputData.setSubject(inputData.getEventSubTypeDesc());
					}
				} else if (inputData.getEventType().equals(ApplicationConstants.OIC)) {
					outputData.setSubject(ApplicationConstants.OIC_HEARING);
				} else if (inputData.getEventType().equals(ApplicationConstants.VISIT)) {				
					outputData.setSubject(ApplicationConstants.VISIT_TEXT);
				} else if (inputData.getEventType().equals(ApplicationConstants.INST_ACT)) {
					if(inputData.getCsDescription()!=null) {								
						outputData.setSubject(inputData.getCsDescription());
					}
				} else if (inputData.getEventType().equals(ApplicationConstants.ACP)) {
					outputData.setSubject(inputData.getCsDescription());
				}
			} else if (inputData.getEventClass().equalsIgnoreCase(EXT_EVNT)) {
				if(ApplicationConstants.OJ.equals(inputData.getEventSubType()) && inputData.getEventType().equals(ApplicationConstants.TRN)) {
					outputData.setSubject(ApplicationConstants.TRANSFER_OUTSIDE_JURISDICTION);
				} else if(!ApplicationConstants.OJ.equals(inputData.getEventSubType()) && inputData.getEventType().equals(ApplicationConstants.TRN)) {
						outputData.setSubject(ApplicationConstants.TRANSFER_WITHIN_JURISDICTION);
				} else if(inputData.getEventSubType().equals(ApplicationConstants.WR)) {
					outputData.setSubject(ApplicationConstants.WORK_RELEASE);
				} else if (inputData.getEventType().equalsIgnoreCase(TAP)) {
					outputData.setSubject(ApplicationConstants.TEMPORARY_ABSENCE.concat(HYPHEN).concat(inputData.getTapDescription()));
				} else if (inputData.getEventType().equals(ApplicationConstants.ACP)) {
					outputData.setSubject(ApplicationConstants.ACCREDITED_PROGRAM.concat(HYPHEN).concat(inputData.getCsDescription()));
				}
			 }  else if (inputData.getEventClass().equalsIgnoreCase(COMM_EVNT)) {
					if (ApplicationConstants.ACP.equals(inputData.getEventType())) {
						if (inputData.getReferenceId() != null && inputData.getCsDescription() != null) {
							outputData.setSubject(inputData.getCsDescription());
						} else {
							if (inputData.getEventSubTypeDesc() != null) {
								outputData.setSubject(inputData.getEventSubTypeDesc());
							}
						}
					} else if (ApplicationConstants.UW.equals(inputData.getEventType())) {
						if (inputData.getCsDescription() != null) {
							outputData.setSubject(inputData.getCsDescription());
						}
					} else {
						if (inputData.getEventTypeDesc() != null && inputData.getEventSubTypeDesc() != null) {
							outputData.setSubject(inputData.getEventSubTypeDesc());
						}
					}
				} else if (inputData.getEventClass().equalsIgnoreCase(ApplicationConstants.WAPACTIVITY)) {
					outputData.setDepartStartTime(inputData.getDepartStartTime());
					outputData.setReturnTime(inputData.getReturnTime());
					if (inputData.getDepartStartTime() != null) {
						//outputData.setStartTime(inputData.getDepartStartTime());
						outputData.setSubject(inputData.getEventSubTypeDesc());
					} else {
						outputData.setSubject(inputData.getEventSubTypeDesc());
					}
				}
		} else if (ApplicationConstants.CUSTOM_E_AGENDA_VIEW.equals(inputData.getCurrentSelectedViewClass())
				|| ApplicationConstants.CUSTOM_E_MONTH_AGENDA_VIEW.equals(inputData.getCurrentSelectedViewClass())) {
			if (inputData.getEventClass().equalsIgnoreCase(INT_EVNT)) {
				if (inputData.getEventType().equals(ApplicationConstants.APP)) {
					if (inputData.getEventSubTypeDesc() != null) {
						outputData.setSubject(ApplicationConstants.INTERNAL_APPOINTMENT.concat(HYPHEN).concat(inputData.getEventSubTypeDesc()));
						outputData.setLocation(inputData.getToInternalLocationDesc());
					}
				} else if (inputData.getEventType().equals(ApplicationConstants.OIC)) {
					if (inputData.getOicHearingDescription() != null) {
						outputData.setSubject(ApplicationConstants.OIC_HEARING.concat(HYPHEN).concat(inputData.getOicHearingDescription()));
						outputData.setLocation(inputData.getToInternalLocationDesc());
					}
				} else if (inputData.getEventType().equals(ApplicationConstants.VISIT)) {
					if (inputData.getVisitDescription() != null) {
						outputData.setSubject(ApplicationConstants.VISIT_TEXT.concat(HYPHEN).concat(inputData.getVisitDescription()));
						outputData.setLocation(inputData.getToInternalLocationDesc());
					}
				} else if (inputData.getEventType().equals(ApplicationConstants.INST_ACT)) {
					if (inputData.getCsDescription() != null) {
						outputData.setSubject(ApplicationConstants.INSTITUTIONAL_ACTIVITY.concat(HYPHEN).concat(inputData.getCsDescription()));
						outputData.setLocation(inputData.getToInternalLocationDesc());
					}
				} else if (inputData.getEventType().equals(ApplicationConstants.ACP)) {
					outputData.setSubject(ApplicationConstants.ACCREDITED_PROGRAM.concat(HYPHEN).concat(inputData.getCsDescription()));
					outputData.setLocation(inputData.getCsAddress());
				}
			} else if (inputData.getEventClass().equalsIgnoreCase(EXT_EVNT)) {
				if(ApplicationConstants.OJ.equals(inputData.getEventSubType()) && inputData.getEventType().equals(ApplicationConstants.TRN)) {
					outputData.setSubject(ApplicationConstants.TRANSFER_OUTSIDE_JURISDICTION);
					if(inputData.getProvStateDesc()!=null) {									
						outputData.setLocation(inputData.getProvStateDesc());
					} 
				}else if(!ApplicationConstants.OJ.equals(inputData.getEventSubType()) && inputData.getEventType().equals(ApplicationConstants.TRN)) {
						if (inputData.getEventSubTypeDesc() != null) {
							outputData.setSubject(ApplicationConstants.TRANSFER_WITHIN_JURISDICTION.concat(HYPHEN).concat(inputData.getEventSubTypeDesc()));
							outputData.setLocation(inputData.getToAgyLocDesc());
						}
					} else if(inputData.getEventSubType().equals(ApplicationConstants.WR)) {
						outputData.setSubject(ApplicationConstants.WORK_RELEASE.concat(HYPHEN).concat(inputData.getWorkRelaeseProjectCode()));
						outputData.setLocation(inputData.getWorkRelaeseProvideDesc());
					} else if (inputData.getEventType().equalsIgnoreCase(TAP)) {
						if (inputData.getTapDescription() != null) {
							outputData.setSubject(ApplicationConstants.TEMPORARY_ABSENCE.concat(HYPHEN).concat(inputData.getTapDescription()));
							outputData.setLocation(inputData.getToAgyLocDesc());
						}					
				}
					else if (inputData.getEventType().equals(ApplicationConstants.ACP)) {
						outputData.setSubject(ApplicationConstants.ACCREDITED_PROGRAM.concat(HYPHEN).concat(inputData.getCsDescription()));
						outputData.setLocation(inputData.getCsAddress());
					}
			} else if (inputData.getEventClass().equalsIgnoreCase(COMM_EVNT)) {
				if (ApplicationConstants.ACP.equals(inputData.getEventType())) {
					if (inputData.getReferenceId() != null && inputData.getEventTypeDesc() != null && inputData.getCsDescription() != null) {
						outputData.setSubject(inputData.getEventTypeDesc().concat(HYPHEN).concat(inputData.getCsDescription()));
						outputData.setLocation(inputData.getCsAddress());
					} else {
						if (inputData.getEventTypeDesc() != null && inputData.getEventTypeDesc() != null
								&& inputData.getEventSubTypeDesc() != null && inputData.getAgyLocDesc() != null) {
							outputData.setSubject(inputData.getEventTypeDesc().concat(HYPHEN).concat(inputData.getEventSubTypeDesc()));
							outputData.setLocation(inputData.getAgyLocDesc());
						}
					}
				} else if (ApplicationConstants.UW.equals(inputData.getEventType())) {
					outputData.setSubject(inputData.getEventTypeDesc().concat(HYPHEN).concat(inputData.getCsDescription()));
					outputData.setLocation(inputData.getCsAddress());				
				} else {
					if (inputData.getEventTypeDesc() != null && inputData.getEventSubTypeDesc() != null) {
						outputData.setSubject(inputData.getEventTypeDesc().concat(HYPHEN).concat(inputData.getEventSubTypeDesc()));
					}
					if (inputData.getToAgyLocDesc() != null) {
						outputData.setLocation(inputData.getToAgyLocDesc());
					}
				}
			} else if (inputData.getEventClass().equalsIgnoreCase(ApplicationConstants.WAPACTIVITY)) {
				outputData.setDepartStartTime(inputData.getDepartStartTime());
				outputData.setReturnTime(inputData.getReturnTime());
				if (inputData.getEventSubTypeDesc() != null) {
					outputData.setSubject(ApplicationConstants.WEEKLY_ACTIVITY_PLAN.concat(HYPHEN).concat(inputData.getEventSubTypeDesc()));
					outputData.setLocation(inputData.getToAgyLocDesc());
				}
			 }

		}

		return outputData;
	}

	@Override
	public List<CalScheduleBean> updateViewClickData(CalScheduleBean serachObj) {
		List<CalScheduleBean> searchResult = new ArrayList<>();
		VOffenderAllSchedules obj = new VOffenderAllSchedules();
		obj.setOffenderBookId(serachObj.getOffenderBookId());
		obj.setCaseLoadId(serachObj.getCaseLoadId());
		obj.setEventStatus(serachObj.getEventStatus());
		 List<VOffenderAllSchedules> offenderSchedListOnloadList=new ArrayList() ;
		 offenderSchedListOnloadList =offenderSchedList;
		if (offenderSchedListOnloadList != null && offenderSchedListOnloadList.size() > 0) {
			for (VOffenderAllSchedules vOffenderAllSchedules : offenderSchedListOnloadList) {
				if (vOffenderAllSchedules.getEventType().equalsIgnoreCase(CRT)) {
					Integer linkData = osanviosRepository.getLinkedCourtEventData(vOffenderAllSchedules.getEventId());
					vOffenderAllSchedules.setLinkData(linkData);
				}
			}
		}
		for (VOffenderAllSchedules object : offenderSchedListOnloadList) {
			object.setCurrentSelectedViewClass(serachObj.getCurrentSelectedViewClass());
		}
		searchResult = setSchedulesData(offenderSchedListOnloadList, searchResult, serachObj);
		searchResult = searchResult.stream().filter(e -> !e.getEventStatus().equalsIgnoreCase("PROG_CANC")).collect(Collectors.toList());
		return searchResult;
	}
}
