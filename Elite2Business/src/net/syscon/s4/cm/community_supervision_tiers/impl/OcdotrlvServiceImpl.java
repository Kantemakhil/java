package net.syscon.s4.cm.community_supervision_tiers.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.syscon.s4.cm.community_supervision_tiers.OcdotrlvRepository;
import net.syscon.s4.cm.community_supervision_tiers.OcdotrlvService;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.ScheduleSeries;
import net.syscon.s4.community_supervision_tiers.MaintainTierDefaultEvents;
import net.syscon.s4.community_supervision_tiers.OffenderTierLevel;
import net.syscon.s4.community_supervision_tiers.OffenderTierLevelCommitBean;
import net.syscon.s4.inst.demographicsbiometrics.OcuverifRepository;
import net.syscon.s4.inst.schedules.RecurringScheduleRepository;
import net.syscon.s4.inst.schedules.RecurringScheduleService;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;

@Service
public class OcdotrlvServiceImpl implements OcdotrlvService {

	private static Logger logger = LogManager.getLogger(OcdotrlvServiceImpl.class.getName());

	@Autowired
	private OcdotrlvRepository ocdotrlvRepository;

	@Autowired
	private OcuverifRepository ocuverifRepo;

	@Autowired
	private RecurringScheduleService recurringscheduleservice;

	@Autowired
	private RecurringScheduleRepository recurringschedulerepository;

	@Transactional
	@Override
	public List<OffenderTierLevel> offendertierLevelExecuteQuery(OffenderTierLevel bean) {
		List<OffenderTierLevel> list = ocdotrlvRepository.offendertierLevelExecuteQuery(bean);
		if (list.size() > 0 && !list.isEmpty()) {
			Long maxId = ocdotrlvRepository.getMaxTierLevel(bean.getOffenderBookId());
			for (OffenderTierLevel bo : list) {
				if ("Y".equals(bo.getActiveFlag())) {
					if (maxId.equals(bo.getOffenderTierLevelId())) {
						bo.setActiveFlag("Y");
						bo.setDeactivatedDate(null);
					} else {
						bo.setActiveFlag("N");
						bo.setDeactivatedDate(new Date());
					}
				}
			}
			if (list.size() > 0 && !list.isEmpty()) {
				list.forEach(bo->{
					bo.setModifyUserId(bean.getCreateUserId());
				});
				ocdotrlvRepository.updateOffenderTierLevel(list);
			}
		}
		return ocdotrlvRepository.offendertierLevelExecuteQuery(bean);
	}

	@Transactional
	public Integer offendertierLevelCommit(final OffenderTierLevelCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(bo -> {
				bo.setCreateUserId(commitBean.getCreateUserId());
				Long reviwDays = ocdotrlvRepository.getReviewDays(bo.getTierLevel());
				LocalDateTime today = bo.getDateAssigned().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
				today = today.plusDays(reviwDays);
				Date date = Date.from(today.atZone(ZoneId.systemDefault()).toInstant());
				bo.setNextReviewDate(date);
			});
			liReturn = ocdotrlvRepository.insertOffenderTierLevel(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = ocdotrlvRepository.updateOffenderTierLevel(commitBean.getUpdateList());

		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = ocdotrlvRepository.deleteOffenderTierLevel(commitBean.getDeleteList());
		}
		return liReturn;
	}

	@Override
	public List<OffenderTierLevel> offenderTierLevesRecordGroup(String caseLoadId) {
		List<OffenderTierLevel> list = ocdotrlvRepository.offenderTierLevesRecordGroup(caseLoadId);
		if (list != null && list.size() > 0) {
			list.forEach(bo -> {
				if (ApplicationConstants.NFLAG.equalsIgnoreCase(bo.getActiveFlag())) {
					bo.setCanDisplay(false);
				} else {
					bo.setCanDisplay(true);
				}
			});
		}
		return list;
	}

	@Override
	public String offTierLevesUserIdRecordGroup(String userName) {
		return ocuverifRepo.getUserName(userName);
	}

	@Override
	public Integer defaultTierLevelAfterCustodyIntake(Long offenderBookId, String UserId) {
		OffenderTierLevel bean = new OffenderTierLevel();
		List<OffenderTierLevel> list = new ArrayList<OffenderTierLevel>();
		logger.info(this.getClass().getName() + " getDefaultIntakeTier method call");
		OffenderTierLevel obj = ocdotrlvRepository.getDefaultIntakeTier();
		logger.info(this.getClass().getName() + " getStaffId method call");
		if(obj!=null) {			
			Long assignedStaffId = ocdotrlvRepository.getStaffId();
			bean.setTierLevel(obj.getTierLevel());
			bean.setOffenderBookId(offenderBookId);
			bean.setDateAssigned(new Date());
			bean.setCreateUserId(UserId);
			bean.setAssignmentReason(null);
			bean.setApproveFlag("Y");
			bean.setActiveFlag("Y");
			bean.setAssignedByStaffId(assignedStaffId);
			LocalDateTime today = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			today = today.plusDays(obj.getReviewDays());
			Date date = Date.from(today.atZone(ZoneId.systemDefault()).toInstant());
			bean.setNextReviewDate(date);
			bean.setAssignmentReason("DFASS");
			list.add(bean);
			logger.info(this.getClass().getName() + " insertOffenderTierLevel method call");
			ocdotrlvRepository.insertOffenderTierLevel(list);
		}

		return 1;
	}

	private Integer getSchedules(String tierLevel, Long OffenderBookId, String userId) {
		int count = 0;
		List<MaintainTierDefaultEvents> mainDefEvntList = new ArrayList<MaintainTierDefaultEvents>();
		logger.info(this.getClass().getName() + " getMainTierDefaultEvents method call");
		mainDefEvntList = ocdotrlvRepository.getMainTierDefaultEvents(tierLevel);
		List<VOffenderAllSchedules> insertListSched = new ArrayList<VOffenderAllSchedules>();
		for (MaintainTierDefaultEvents bean : mainDefEvntList) {
			ScheduleSeries scheduleSeries = new ScheduleSeries();

			HashMap<String, Object> rules;
			try {
				rules = new ObjectMapper().readValue(bean.getUiRules(), HashMap.class);
				scheduleSeries.setDays((List<String>) rules.get("BYDAY"));

				if (scheduleSeries.getDays().size() > 0 && !scheduleSeries.getDays().isEmpty()) {
					scheduleSeries.setRepeatType("WEEKLY");
				} else {
					scheduleSeries.setRepeatType("DAILY");
				}
				scheduleSeries.setExcludeHoliday("N");
				scheduleSeries.setStartDate(new Date());
				scheduleSeries.setUiRules(bean.getUiRules());
				logger.info(this.getClass().getName() + " calculateSchedules method call");
				List<Date> reccDates = recurringscheduleservice.calculateSchedules(scheduleSeries);
				for (Date date : reccDates) {
					VOffenderAllSchedules vOffSc = new VOffenderAllSchedules();
					vOffSc.setOffenderBookId(new BigDecimal(OffenderBookId));
					vOffSc.setEventStatus("SCH");
					vOffSc.setEventType(bean.getScheduleType());
					vOffSc.setEventSubType(bean.getScheduleSubType());
					vOffSc.setEventClass("DTE");
					vOffSc.setCreateUserId(userId);
					vOffSc.setEventDate(date);
					vOffSc.setStartTime(date);
					vOffSc.setEndTime(date);
					insertListSched.add(vOffSc);
				}
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " getMainTierDefaultEvents method call");
			}
		}
		if (insertListSched.size() > 0 && !insertListSched.isEmpty()) {
			logger.info(this.getClass().getName() + " schedulesInsert method call");
			count = recurringschedulerepository.schedulesInsert(insertListSched);
		}
		return count;
	}

}
