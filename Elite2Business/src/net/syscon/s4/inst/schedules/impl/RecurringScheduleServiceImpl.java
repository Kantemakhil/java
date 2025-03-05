package net.syscon.s4.inst.schedules.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.syscon.s4.cm.community_supervision_tiers.OcmtidetRepository;
import net.syscon.s4.cm.weeklyactivityplans.OweacplnRepository;
import net.syscon.s4.common.ScheduleSeries;
import net.syscon.s4.community_supervision_tiers.MaintainTierDefaultEvents;
import net.syscon.s4.community_supervision_tiers.MaintainTierLevelsCommitBean;
import net.syscon.s4.inst.schedules.RecurringScheduleRepository;
import net.syscon.s4.inst.schedules.RecurringScheduleService;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;
import net.syscon.s4.iwp.OcdclogsRepository;
import net.syscon.s4.iwp.OcdclogsService;
import net.syscon.s4.of.payroll.maintenance.SystemEvents;

@Service
public class RecurringScheduleServiceImpl implements RecurringScheduleService{
	
	private static Logger logger = LogManager.getLogger(RecurringScheduleServiceImpl.class.getName());
	
	@Autowired
	private RecurringScheduleRepository recurrSchDao;
	
	@Autowired
	private OcdclogsService ocdclogsService;
	
	@Autowired
	private OcdclogsRepository ocdclogsRepository;
	
	@Autowired
	private OcmtidetRepository ocmtidetrepository;
	
	@Autowired
	private OweacplnRepository oweacplnRepository;
	
	private List<SystemEvents> holidayList=new ArrayList<SystemEvents>();

	@Override
	public List<Date> nextSchedules(Long recurringSeriesId) {
		return null;
	}
	
	@Override
	public List<Date> calculateSchedules(ScheduleSeries scheduleSeries) {
		List<Date> dates = new ArrayList<>();
		try {
			if("Y".equals(scheduleSeries.getExcludeHoliday())) {
				holidayList = recurrSchDao.getHolidaysList(scheduleSeries.getStartDate());
			}
			HashMap<String,Object> rules = new ObjectMapper().readValue(scheduleSeries.getUiRules(), HashMap.class);
			scheduleSeries.setDays((List<String>)rules.get("BYDAY"));
			scheduleSeries.setRepeatFrequency((Integer) rules.get("INTERVAL"));
			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			String endDate = (String) rules.get("UNTIL");
			if(endDate != null) {
				Date date = inputFormat.parse(endDate);
				scheduleSeries.setEndDate(date);
			}
			if (scheduleSeries.getDays() != null && scheduleSeries.getDays().size() > 0) {
				scheduleSeries.setRepeatType("WEEKLY");
			} else {
				scheduleSeries.setRepeatType("DAILY");
			}
			scheduleSeries.setTotalcount((Integer) rules.get("COUNT"));
			if ("DAILY".equalsIgnoreCase(scheduleSeries.getRepeatType()) ) {
				dates = getDailyMeetingDates(scheduleSeries.getStartDate(), scheduleSeries.getEndDate(), scheduleSeries.getRepeatFrequency(),scheduleSeries.getTotalcount(),scheduleSeries.getExcludeHoliday());
			} else if( "WEEKLY".equalsIgnoreCase(scheduleSeries.getRepeatType()) ) {
				dates = getWeeklyMeetingDates(scheduleSeries.getStartDate(), scheduleSeries.getEndDate(), scheduleSeries.getRepeatFrequency(), scheduleSeries.getDays(), scheduleSeries.getTotalcount(),scheduleSeries.getExcludeHoliday());
			} else if ("MONTHLY".equalsIgnoreCase(scheduleSeries.getRepeatType()) ) {

			} else if ("YEARLY".equalsIgnoreCase(scheduleSeries.getRepeatType()) ) {

			}


		} catch (Exception e) {
			logger.error("Exception  in " + this.getClass().getName() + " calculateSchedules ", e);
		}
		return dates;
	}
	
	
	private List<Date> getMonthlyMeetingDates(Date fromDate, Date endDate, int frequency, String repeatOn, int repeatOn1) {
		LocalDate localFromDate = convertToLocalDateViaInstant(fromDate); 
		LocalDate localEndDate = convertToLocalDateViaInstant(endDate);
		List<Date> dates = new ArrayList<>();
		int count = 0;
		while(localFromDate.isBefore(localEndDate)) {
			if(frequency==1) {
				localFromDate = localFromDate.with(TemporalAdjusters.next(DayOfWeek.valueOf(repeatOn)));
			} else if (frequency >1) {
				if(count==0) {
					localFromDate = localFromDate.with(TemporalAdjusters.next(DayOfWeek.valueOf(repeatOn)));
				} else {
					localFromDate = localFromDate.plusDays(7*frequency);
				}
				
			}
			dates.add(asDate(localFromDate));
			count++;
		}
	  	return dates;
	}
	
	
	private List<Date> getWeeklyMeetingDatesOld(Date fromDate, Date endDate, int frequency, List<String> repeatOn, Integer totalcount,String excludeHoliday) {
		LocalDate localFromDate = convertToLocalDateViaInstant(fromDate); 
		LocalDate localEndDate = endDate!=null?convertToLocalDateViaInstant(endDate):null;
		List<Date> dates = new ArrayList<>();
		try {
			int count = 0;
			while((localEndDate !=null && (localFromDate.isBefore(localEndDate) || localFromDate.isEqual(localEndDate))) || ( totalcount != null && count < totalcount )) {
				boolean isScheduleAdded = false;
				for(String repeatOnDay:repeatOn ) {
					
					int weekDayNumber = getDayNumber(repeatOnDay);
					int currentDayNumber = asDate(localFromDate).getDay();
					
					if(count == 0 && asDate(localFromDate).getDay() > getDayNumber(repeatOnDay)) {
						continue;
					}
					
					localFromDate = localFromDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(repeatOnDay)));
					
					if((localEndDate !=null && (localFromDate.isBefore(localEndDate) || localFromDate.isEqual(localEndDate))) || ( totalcount != null && count < totalcount )) {
					if("N".equals(excludeHoliday) ) {
						isScheduleAdded = true;
						dates.add(asDate(localFromDate));
						count++;
					}else if("Y".equals(excludeHoliday) && !isHoliday(asDate(localFromDate))) {
						isScheduleAdded = true;
						dates.add(asDate(localFromDate));
						count++;
					}
					if(totalcount != null && count >= totalcount) {
						break;
					}
				}
				}
				if(!isScheduleAdded) {
					localFromDate = localFromDate.plusDays(1);
				}
				if (frequency > 1) {
					localFromDate = localFromDate.plusDays(7*(frequency-1));	
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	  	return dates;
	}
	
	private List<Date> getDailyMeetingDates(Date fromDate, Date endDate, int frequency,Integer totalcount,String excludeHoliday) {
		LocalDate localFromDate = convertToLocalDateViaInstant(fromDate); 
		LocalDate localEndDate = null;
		if(endDate != null) {
			localEndDate = convertToLocalDateViaInstant(endDate);
		}
		List<Date> dates = new ArrayList<>();
		int count = 0;
		while((localEndDate != null && (localFromDate.isBefore(localEndDate) || localFromDate.isEqual(localEndDate))) || ( totalcount != null && count < totalcount ) ) {
				if("N".equals(excludeHoliday) ) {
					dates.add(asDate(localFromDate));
					count++;
				}else if("Y".equals(excludeHoliday) && !isHoliday(asDate(localFromDate))) {
					dates.add(asDate(localFromDate));
					count++;
				}
			localFromDate = localFromDate.plusDays(frequency);
		}
	  	return dates;
	}
	
	private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	
	public Date asDate(LocalDate localDate) {
	    return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}
	
	private Boolean isHoliday(final Date pDate) {
		Boolean retVal = false;
		for (SystemEvents hol : holidayList) {
			if(hol.getEventEndDate() != null && pDate != null) {
				if(pDate.compareTo(hol.getEventDate()) >= 0 && pDate.compareTo(hol.getEventEndDate()) <= 0) {
					retVal = true;
					break;
				}
			}
			if (pDate != null && pDate.compareTo(hol.getEventDate()) == 0) {
				retVal = true;
				break;
			}
		}
		return retVal;
	}
	
	@Override
	public List<VOffenderAllSchedules> getScheduleConflicts(List<OffenderIndSchedules> obj) {
		List<VOffenderAllSchedules> returnList = new ArrayList<>();
		List<Date> eventDates = new ArrayList<>();
		try {
		if(obj != null && obj.size() > 0) {
			Integer offenderBookId = obj.get(0).getOffenderBookId();
			Integer seriesId = obj.get(0).getSeriesId();
			obj.forEach(e -> {
				eventDates.add(e.getEventDate());
			});
			returnList = recurrSchDao.getScheduleConflicts(offenderBookId, eventDates,seriesId);
			VOffenderAllSchedules vOffObj = new VOffenderAllSchedules();
			vOffObj.setOffenderBookId(BigDecimal.valueOf(offenderBookId));
			List<VOffenderAllSchedules> wapSchedulesList = oweacplnRepository.getWapManualActivitiesBasedOnEventDate(vOffObj,eventDates);
			if(!wapSchedulesList.isEmpty()) {
				returnList.addAll(wapSchedulesList);
			}
		}
		}catch(Exception e) {
			logger.error("Exception in " + this.getClass().getName() + " getScheduleConflicts",e);
		}
		return returnList;
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Integer recurrRuleCommit(VOffenderAllSchedulesCommitBean commitBean) {
		Integer returnVal = 0;
		final LocalDate localDate = LocalDate.now().minusDays(1);
		if(commitBean != null && commitBean.getSeriesInsertList() != null && commitBean.getSeriesInsertList().getUiRules() != null) {
			if(commitBean.getInsertList() != null) {
				Integer oldSeriesId = commitBean.getInsertList().get(0).getSeriesId();
				if(oldSeriesId != null) {
					recurrSchDao.schedulesUpdate(oldSeriesId,commitBean.getSeriesInsertList().getStartDate(),commitBean.getCreateUserId());
				}
			}
			Integer seriesId = recurrSchDao.recurrPreInsert();
			commitBean.getSeriesInsertList().setSeriesId(seriesId);
			commitBean.getSeriesInsertList().setCreateUserId(commitBean.getCreateUserId());
			Integer insertSeriesVal = recurrSchDao.recurrRuleCommit(commitBean.getSeriesInsertList());
			if(insertSeriesVal > 0) {
				for( VOffenderAllSchedules obj : commitBean.getInsertList()) {
					obj.setCreateUserId(commitBean.getCreateUserId());					
					final Date sysdateMinusOne = Date
							.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
					if (obj.getEventDate()!=null && obj.getEventDate().compareTo(sysdateMinusOne) <= 0) {
						obj.setEventStatus("EXP");
					} else  {
						obj.setEventStatus("SCH");
					}
					
					if(obj.getInChargeStaffName() != null) {
						obj.setInChargeStaffId(ocdclogsRepository.inChargeStaffId(obj.getInChargeStaffName()));
					}
					obj.setSeriesId(seriesId);
				}
			}
			returnVal = recurrSchDao.schedulesInsert(commitBean.getInsertList());
		}else if(commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()){
			if(commitBean.getDeleteList().get(0).getSeriesId() != null) {
				returnVal = recurrSchDao.schedulesUpdate(commitBean.getDeleteList().get(0).getSeriesId(),commitBean.getDeleteList().get(0).getEventDate(),commitBean.getCreateUserId());
			}else {
				returnVal = recurrSchDao.schedulesDelete(commitBean.getDeleteList().get(0).getEventId(),commitBean.getCreateUserId());
			}
			
		}else {
			if(commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
				BigDecimal oldEventId = commitBean.getInsertList().get(0).getEventId();
				if(oldEventId != null) {
					commitBean.setUpdateList(commitBean.getInsertList());
					commitBean.setInsertList(Collections.emptyList());
				}else {
					for( VOffenderAllSchedules obj : commitBean.getInsertList()) {
						obj.setCreateUserId(commitBean.getCreateUserId());
						final Date sysdateMinusOne = Date
								.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
						if (obj.getEventDate()!=null && obj.getEventDate().compareTo(sysdateMinusOne) <= 0) {
							obj.setEventStatus("EXP");
						} else  {
							obj.setEventStatus("SCH");
						}
						if(obj.getInChargeStaffName() != null) {
							obj.setInChargeStaffId(ocdclogsRepository.inChargeStaffId(obj.getInChargeStaffName()));
						}
					}
					returnVal = recurrSchDao.schedulesInsert(commitBean.getInsertList());
				}
			}else {
				returnVal = ocdclogsService.offSchCommit(commitBean);
			}
		}
		return returnVal;
	}
	
	@Override
	public ScheduleSeries getScheduleSeries(ScheduleSeries searchBean) {
		ScheduleSeries scheduleSeries = new ScheduleSeries();
		HashMap<String, Object> rules;
		try {
			scheduleSeries = recurrSchDao.getScheduleSeries(searchBean);
			if(scheduleSeries.getUiRules() != null) {
				rules = new ObjectMapper().readValue(scheduleSeries.getUiRules(), HashMap.class);
				scheduleSeries.setDays((List<String>)rules.get("BYDAY"));
				scheduleSeries.setRepeatFrequency((Integer) rules.get("INTERVAL"));
				scheduleSeries.setRepeatType((String) rules.get("FREQ"));
				SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				String endDate = (String) rules.get("UNTIL");
				if(endDate != null) {
					Date date = inputFormat.parse(endDate);
					scheduleSeries.setEndDate(date);
				}
				scheduleSeries.setTotalcount((Integer) rules.get("COUNT"));
			}
		} catch (Exception e) {
			logger.error("Exception  in " + this.getClass().getName() + " getScheduleSeries ", e);
		} 
		
		return scheduleSeries;
	}
	
	int getDayNumber(String day){
		int i = 0;
		switch (day) {
		case "SUNDAY":
			i = 0 ;
			break;
		case "MONDAY":
			i = 1;
			break;
		case "TUESDAY":
			i = 2;
			break;
		case "WEDNESDAY":
			i = 3;
			break;
		case "THURSDAY":
			i = 4;
			break;
		case "FRIDAY":
			i = 5;
			break;
		case "SATURDAY":
			i = 6;
			break;
	}
		return i;
	}

	//newly implemented method for getWeeklyMeetingDates
	private List<Date> getWeeklyMeetingDates(Date fromDate, Date endDate, int frequency, List<String> repeatOn, Integer totalcount,String excludeHoliday) {
		LocalDate localFromDate = convertToLocalDateViaInstant(fromDate); 
		LocalDate localEndDate = endDate!=null?convertToLocalDateViaInstant(endDate):null;
		List<Date> dates = new ArrayList<>();
		try {
			int count = 0;
			while((localEndDate !=null && (localFromDate.isBefore(localEndDate) || localFromDate.isEqual(localEndDate))) || ( totalcount != null && count < totalcount )) {
				for (String repeatOnDay : repeatOn) {
					int weekDayNumber = getDayNumber(repeatOnDay);
					int currentDayNumber = asDate(localFromDate).getDay();
					if (weekDayNumber == currentDayNumber) {
						if ("N".equals(excludeHoliday)) {
							dates.add(asDate(localFromDate));
							count++;
						} else if ("Y".equals(excludeHoliday) && !isHoliday(asDate(localFromDate))) {
							dates.add(asDate(localFromDate));
							count++;
						}
					}
				}
				localFromDate = localFromDate.plusDays(1);
				if (frequency > 1 && asDate(localFromDate).getDay() == 1) {
					localFromDate = localFromDate.plusDays(7*(frequency-1));	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	  	return dates;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Integer commitScheduledEventDetails(MaintainTierLevelsCommitBean commitBean) {
		Integer result = 0;
		List<VOffenderAllSchedules> insertList = new ArrayList<VOffenderAllSchedules>();
		List<VOffenderAllSchedules> insertListOne = new ArrayList<VOffenderAllSchedules>();
		if (commitBean.getInsertTierDefEvents() != null && commitBean.getInsertTierDefEvents().size() > 0) {
			final LocalDate localDate = LocalDate.now().minusDays(1);
			MaintainTierDefaultEvents obj = ocmtidetrepository.getActiveTierLevelRecord(commitBean.getInsertTierDefEvents().get(0).getOffenderBookId());
			for (MaintainTierDefaultEvents bean : commitBean.getInsertTierDefEvents()) {
				//Inactivate existing schedules 
				Integer count = recurrSchDao.deleteDefaultTierEventsData(bean.getTierLevelcode(), bean.getScheduleType(), bean.getScheduleSubType(),bean.getStartDate(),obj.getOffenderTierLevelId(),bean.getVersionNo(),commitBean.getCreateUserId());
				VOffenderAllSchedules vOff = dataMapping(bean);
				vOff.setOffenderTierLevelId(obj.getOffenderTierLevelId());
				vOff.setCreateUserId(commitBean.getCreateUserId());
				vOff.setModifyUserId(commitBean.getCreateUserId());
				if ("N".equalsIgnoreCase(bean.getIsSeriesFalg())) {
					final Date sysdateMinusOne = Date
							.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
					if (vOff.getEventDate() != null && vOff.getEventDate().compareTo(sysdateMinusOne) <= 0) {
						vOff.setEventStatus("EXP");
					} else {
						vOff.setEventStatus("SCH");
					}
					insertList.add(vOff);
				} else if ("Y".equalsIgnoreCase(bean.getIsSeriesFalg())) {
					ScheduleSeries schdSeries = new ScheduleSeries();
					if(bean.getExcludeFlag() == null) {
						schdSeries.setExcludeHoliday("Y");
					}
					schdSeries.setUiRules(bean.getUiRules());
					schdSeries.setStartDate(bean.getStartDate());
					schdSeries.setStartTime(bean.getStartTime());
					schdSeries.setEndTime(bean.getEndTime());
					schdSeries.setEndDate(bean.getEndTime());
					schdSeries.setEndTime(bean.getEndTime());
					schdSeries.setExcludeHoliday(bean.getExcludeFlag());
					List<Date> list = calculateSchedules(schdSeries);
					if(list.size()>0 && !list.isEmpty()) {
						list.forEach(bo->{
							VOffenderAllSchedules vOffOne = new VOffenderAllSchedules();
							vOffOne = dataMapping(bean);
							vOffOne.setEventDate(bo);
							
							//Setting Start Time
							if (bean.getStartTime() != null) {
								Date startTime = new Date();
								startTime.setDate(bo.getDate());
								startTime.setYear(bo.getYear());
								startTime.setMonth(bo.getMonth());
								startTime.setHours(bean.getStartTime().getHours());
								startTime.setMinutes(bean.getStartTime().getMinutes());
								startTime.setSeconds(0);
								vOffOne.setStartTime(startTime);
							}
							
							final Date sysdateMinusOne = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
							if (vOffOne.getEventDate() != null && vOffOne.getEventDate().compareTo(sysdateMinusOne) <= 0) {
								vOffOne.setEventStatus("EXP");
							} else {
								vOffOne.setEventStatus("SCH");
							}
							vOffOne.setOffenderTierLevelId(obj.getOffenderTierLevelId());
							vOffOne.setCreateUserId(commitBean.getCreateUserId());
							vOffOne.setModifyUserId(commitBean.getCreateUserId());
							insertListOne.add(vOffOne);
						});
					}
				}
			}
		}
		
		if (commitBean.getDeleteTierDefEvents() != null && commitBean.getDeleteTierDefEvents().size() > 0) {
			result = recurrSchDao.schedulesDelete(new BigDecimal(commitBean.getDeleteTierDefEvents().get(0).getEventId()),commitBean.getCreateUserId());
		}
		
		if (commitBean.getUpdateTierDefEvents() != null && commitBean.getUpdateTierDefEvents().size() > 0) {
			commitBean.getUpdateTierDefEvents().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
				final LocalDate localDate = LocalDate.now().minusDays(1);
				final Date sysdateMinusOne = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
				if (bo.getStartDate() != null && bo.getStartDate().compareTo(sysdateMinusOne) <= 0) {
					bo.setEventStatus("EXP");
				} else {
					bo.setEventStatus("SCH");
				}
			});
			List<VOffenderAllSchedules> updateList = new ArrayList<VOffenderAllSchedules>();
			VOffenderAllSchedules vOffOne = new VOffenderAllSchedules();
			MaintainTierDefaultEvents obj = ocmtidetrepository.getActiveTierLevelRecord(commitBean.getUpdateTierDefEvents().get(0).getOffenderBookId());
			vOffOne = dataMapping(commitBean.getUpdateTierDefEvents().get(0));
			vOffOne.setOffenderTierLevelId(obj.getOffenderTierLevelId());
			final LocalDate localDate = LocalDate.now().minusDays(1);
			final Date sysdateMinusOne = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
			if (vOffOne.getEventDate() != null && vOffOne.getEventDate().compareTo(sysdateMinusOne) <= 0) {
				vOffOne.setEventStatus("EXP");
			} else {
				vOffOne.setEventStatus("SCH");
			}
			vOffOne.setCreateUserId(commitBean.getCreateUserId());
			vOffOne.setModifyUserId(commitBean.getCreateUserId());
			updateList.add(vOffOne);
			try {
				if (getEndDateValidation(commitBean.getUpdateTierDefEvents().get(0)) == 3) {
					return 3;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			result = recurrSchDao.schedulesDelete(new BigDecimal(commitBean.getUpdateTierDefEvents().get(0).getEventId()),commitBean.getCreateUserId());
			result = recurrSchDao.schedulesInsert(updateList);
		}
		
		if(insertListOne.size()>0) {
			insertListOne.forEach(bo->{
				//Setting End Time
				if (bo.getEndTime() != null) {
					Date endTime = new Date();
					endTime.setDate(bo.getStartTime().getDate());
					endTime.setYear(bo.getStartTime().getYear());
					endTime.setMonth(bo.getStartTime().getMonth());
					endTime.setHours(bo.getEndTime().getHours());
					endTime.setMinutes(bo.getEndTime().getMinutes());
					endTime.setSeconds(0);
					bo.setEndTime(endTime);
				}
			});
			insertList.addAll(insertListOne);
		}
		if (insertList.size() > 0 && !insertList.isEmpty()) {
			result = recurrSchDao.schedulesInsert(insertList);
		}
		return result;
	}

	private VOffenderAllSchedules dataMapping(MaintainTierDefaultEvents bean) {
		VOffenderAllSchedules obj = new VOffenderAllSchedules();
		obj.setOffenderBookId(new BigDecimal(bean.getOffenderBookId()));
		obj.setEventDate(bean.getStartDate());
		obj.setStartTime(bean.getStartTime());
		obj.setEndTime(bean.getEndTime());
		obj.setEventClass("DTE");
		obj.setEventType(bean.getScheduleType());
		obj.setEventSubType(bean.getScheduleSubType());
		obj.setCommentText(bean.getCommentText());
		obj.setToAgyLocId(bean.getLocation());
		if (bean.getStaffName() != null) {
			obj.setInChargeStaffId(ocdclogsRepository.inChargeStaffId(bean.getStaffName()));
		}
		obj.setCreateUserId(bean.getCreateUserId());
		obj.setEmailScheduleHoursBefore(bean.getEmailSchHoursBefore()!=null ? bean.getEmailSchHoursBefore().intValue():null);
		obj.setSmsScheduleHoursBefore(bean.getSmsSchHoursBefore()!=null ? bean.getSmsSchHoursBefore().intValue():null);
		obj.setEmailFlag(bean.getEmailFlag());
		obj.setSmsFlag(bean.getSmsFlag());
		obj.setSeriesId(null);
		obj.setTierLevelCode(bean.getTierLevelcode());
		obj.setVersionNo(bean.getVersionNo()!=null ? bean.getVersionNo():null);
		return obj;
	}
	
	Integer getEndDateValidation(MaintainTierDefaultEvents obj) throws ParseException ,Exception{
		String uiRules = recurrSchDao.getDefaultTierLevelUiRules(obj.getScheduleType(), obj.getScheduleSubType(), obj.getOffenderBookId(), obj.getOffenderTierLevelId());
		if(uiRules!=null) {
			HashMap<String, Object> rules = new ObjectMapper().readValue(uiRules, HashMap.class);
			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			String endDate = (String) rules.get("UNTIL");
			if(endDate != null) {
				Date date = inputFormat.parse(endDate);
				if(date.compareTo(obj.getStartDate()) < 0) {
					return 3;
				}
			}
		}
		return 1;
	}
	
}
