package net.syscon.s4.cm.community_supervision_tiers.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasures;
import net.syscon.s4.cm.community_supervision_tiers.OcmtidetRepository;
import net.syscon.s4.cm.community_supervision_tiers.OcmtidetService;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.community_supervision_tiers.MaintainTierDefaultEvents;
import net.syscon.s4.community_supervision_tiers.MaintainTierLevelsCommitBean;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.iwp.OcdclogsService;

@Service
public class OcmtidetServiceImpl implements OcmtidetService {

	@Autowired
	private OcmtidetRepository ocmtidetrepository;
	
	@Autowired
	private OcdclogsService ocdclogsservice;

	@Override
	public List<MaintainTierDefaultEvents> tierdefaultEventsExecuteQuery(MaintainTierDefaultEvents searchBean) {
		 List<MaintainTierDefaultEvents> list = ocmtidetrepository.tierdefaultEventsExecuteQuery(searchBean);
		 if(list.size()>0 && searchBean.getOffenderBookId()!=null) {
				list.forEach(bo -> {
					VOffenderAllSchedules beanOne = new VOffenderAllSchedules();
					beanOne.setOffenderBookId(new BigDecimal(searchBean.getOffenderBookId()));
					beanOne.setEventType(bo.getScheduleType());
					beanOne.setEventSubType(bo.getScheduleSubType());
					List<EventMeasures> eventlist = ocdclogsservice.getEmailSmsFlag(beanOne);
					if (eventlist.size() > 0) {
						bo.setSmsFlag(eventlist.get(0).getSmsFlag());
						bo.setEmailFlag(eventlist.get(0).getEmailFlag());
						bo.setEmailAddressCount(eventlist.get(0).getEmailAddressCount());
						bo.setPhoneNumberCount(eventlist.get(0).getPhoneNumberCount());
					}
				});
		 }
		 if(searchBean.getSealFlag()!=null && searchBean.getSealFlag().equalsIgnoreCase("Y")) {
			 List<MaintainTierDefaultEvents> newList = new ArrayList<MaintainTierDefaultEvents>();
			 List<MaintainTierDefaultEvents> listTemp = ocmtidetrepository.getActiveTierLevelData(searchBean.getOffenderBookId(),searchBean.getOffenderTierLevelId(),searchBean.getScheduleType(),searchBean.getScheduleSubType(),searchBean.getVersionNo());
			 newList.addAll(listTemp);
			 if(newList!=null && newList.size()>0) {
				 newList.forEach(bo->{
					 MaintainTierDefaultEvents bean = ocmtidetrepository.getDeafultTierEventData(bo.getScheduleType(), bo.getScheduleSubType(),searchBean.getOffenderTierLevelId(),searchBean.getOffenderBookId(),searchBean.getVersionNo());
					 bo.setStartDate(bean.getStartDate());
					 bo.setStartTime(bean.getStartTime());
					 bo.setEndTime(bean.getEndTime());
					 bo.setEventId(bean.getEventId());
					 bo.setCommentText(bean.getCommentText());
					 bo.setLocation(bean.getLocation());
					 bo.setEmailFlag(bean.getEmailFlag());
					 bo.setSmsFlag(bean.getSmsFlag());
					 bo.setTierLevelcode(searchBean.getTierLevelcode());
					 bo.setStaffName(bean.getStaffName());
					 bo.setSmsSchHoursBefore(bean.getSmsSchHoursBefore());
					 bo.setEmailSchHoursBefore(bean.getEmailSchHoursBefore());
					 MaintainTierDefaultEvents beanOne = ocmtidetrepository.getOffenderTierlevelVersionNoBasedDetails(bo.getScheduleType(), bo.getScheduleSubType(), searchBean.getTierLevelcode(), searchBean.getVersionNo());
					 bo.setVersionNo(bean.getVersionNo());
					 bo.setUiRules(beanOne.getUiRules());
					 bo.setTierEventSchVersionId(beanOne.getTierEventSchVersionId());
				 });
			 }
			 return newList;
		 }
		 return list;
	}

	@Override
	public Integer tierdefaultEventsCommit(MaintainTierLevelsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertTierDefEvents() != null && commitBean.getInsertTierDefEvents().size() > 0) {
			commitBean.getInsertTierDefEvents().forEach(bo -> {
				bo.setCreateUserId(commitBean.getCreateUserId());
			});
			liReturn = ocmtidetrepository.insertTierdefaultEvents(commitBean.getInsertTierDefEvents());
		}
		if (commitBean.getUpdateTierDefEvents() != null && commitBean.getUpdateTierDefEvents().size() > 0) {
			commitBean.getUpdateTierDefEvents().forEach(bo -> {
				bo.setCreateUserId(commitBean.getCreateUserId());
			});
			 liReturn = ocmtidetrepository.insertTierdefaultEvents(commitBean.getUpdateTierDefEvents());
		}
		if (commitBean.getDeleteTierDefEvents() != null && commitBean.getDeleteTierDefEvents().size() > 0) {
			commitBean.getDeleteTierDefEvents().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = ocmtidetrepository.deleteTierdefaultEvents(commitBean.getDeleteTierDefEvents());
		}
		return liReturn;
	}

	@Override
	public ReferenceCodes getActiveTierEvent(Long OffnderBooId) {
		return ocmtidetrepository.getActiveTierEvent(OffnderBooId);
	}

	@Override
	public List<ReferenceCodes> rgScheduleTypeRecordGroup(String scheduleType) {
		return ocmtidetrepository.rgScheduleTypeRecordGroup(scheduleType);
	}
}
