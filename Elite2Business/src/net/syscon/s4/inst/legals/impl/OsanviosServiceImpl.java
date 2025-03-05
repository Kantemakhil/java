package net.syscon.s4.inst.legals.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.syscon.s4.cm.weeklyactivityplans.WeeklyActivityPlans;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.legals.OcucalcrRepository;
import net.syscon.s4.inst.legals.OsanviosRepository;
import net.syscon.s4.inst.legals.OsanviosService;
import net.syscon.s4.inst.legals.beans.Court;
import net.syscon.s4.inst.legals.beans.CourtEvnetAppointmentOutcome;
import net.syscon.s4.inst.legals.beans.CourtEvnetAppointmentOutcomeCommitBean;
import net.syscon.s4.inst.legals.beans.OffenderCommunitySentense;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.inst.schedules.bean.CourtEventsCommitBean;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.iwp.impl.OcdclogsServiceImpl;
import net.syscon.s4.triggers.OffCourtEventVineIntfTrgService;

@Service
public class OsanviosServiceImpl implements OsanviosService {
	@Autowired
	private OsanviosRepository osanviosRepository;
	@Autowired
	private OffCourtEventVineIntfTrgService offCourtEventVineIntfTrgService;
	
	@Autowired
	OcucalcrRepository ocucalcrRepository;
	
	@Autowired
	private OcdclogsServiceImpl ocdclogsServiceImpl;
	
	private static String YFLAG = "Y";

	public List<AgencyLocations> hearingreasonRecordGroup() {
		List<AgencyLocations> list = osanviosRepository.hearingreasonRecordGroup();
		for (AgencyLocations bean : list) {
			if (YFLAG.equalsIgnoreCase(bean.getActiveFlag())) {
				bean.setCanDisplay(true);
			} else {
				bean.setCanDisplay(false);
			}
		}
		return list;
	}

	@Override
	public List<Court> populateCourtData() {
		List<Court> courtList = new ArrayList<Court>();
		courtList = osanviosRepository.populateCourtData();
		return courtList;
	}

	public List<CourtEvents> crtEveExecuteQuery(final CourtEvents searchRecord) {

		List<CourtEvents> returnList = osanviosRepository.crtEveExecuteQuery(searchRecord);

		if (returnList != null && returnList.size() > 0) {
			returnList.forEach(ele -> {
				ele.setCountOutcomeReason(osanviosRepository.getLinkedCourtEventData(new BigDecimal(ele.getEventId())));
			});
		}
		return returnList;
	}

	@Transactional(rollbackFor = Exception.class)
	public Integer crtEveCommit(final CourtEventsCommitBean commitBean) {

		int liReturn = 0;
		Integer triggerRetVal = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final CourtEvents obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = osanviosRepository.crtEveInsertCourtEvents(commitBean.getInsertList());
			for (CourtEvents bean : commitBean.getInsertList()) {
				triggerRetVal = offCourtEventVineIntfTrgService.offCourtEventVineIntfTrg(bean, "INSERTING");
			}
		}

		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final CourtEvents obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = osanviosRepository.crtEveUpdateCourtEvents(commitBean.getUpdateList());
			for (CourtEvents bean : commitBean.getUpdateList()) {
				triggerRetVal = offCourtEventVineIntfTrgService.offCourtEventVineIntfTrg(bean, "UPDATING");
			}
			for (CourtEvents obj : commitBean.getUpdateList()) {
				try {
					VOffenderAllSchedules offsch = new VOffenderAllSchedules();
					offsch.setEventDate(obj.getEventDate());
					offsch.setStartTime(obj.getStartTime());
					offsch.setOffenderBookId(BigDecimal.valueOf(obj.getOffenderBookId()));
					offsch.setEventOutcome(obj.getEventStatus());
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

	@Override
	public List<OffenderCommunitySentense> retriveSentenceData(OffenderCommunitySentense searchBean) {
		List<OffenderCommunitySentense> returnList = new ArrayList<OffenderCommunitySentense>();
		List<OffenderCommunitySentense> finalResult = new ArrayList<OffenderCommunitySentense>();
		try {
			returnList = osanviosRepository.retriveSentenceData(searchBean);
			for (OffenderCommunitySentense obj : returnList) {
				obj.setFormInfoJson(new String(obj.getFormInfoJsonBlob()));
				ObjectMapper mapper = new ObjectMapper();
				Map<String, Object> map = mapper.readValue(obj.getFormInfoJson(), Map.class);
				if (map != null && map.get("myJsonRowData") != null) {
					List<Map<String, Object>> Sentencelist = new ArrayList();
					Sentencelist = (List<Map<String, Object>>) map.get("myJsonRowData");
					for (Map<String, Object> sentenceObj : Sentencelist) {
						OffenderCommunitySentense sentenseObject = new OffenderCommunitySentense();
						sentenseObject.setNo((Integer) sentenceObj.get("orderNo"));
						sentenseObject.setOffenderBookId(searchBean.getOffenderBookId());
						sentenseObject.setCommenceType((String) sentenceObj.get("commenceType"));
						SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
						Date date = formatter.parse((String) sentenceObj.get("commenceDate"));
						sentenseObject.setCommenceDate(date);
						sentenseObject.setMatter((String) sentenceObj.get("matter"));
						finalResult.add(sentenseObject);
					}

				}

			}
		} catch (Exception e) {
		}

		return finalResult;
	}

	@Override
	public List<CourtEvnetAppointmentOutcome> appointMentsData(CourtEvnetAppointmentOutcome searchBean) {
		List<CourtEvnetAppointmentOutcome> dbList = osanviosRepository.appointMentsData(searchBean);
		for (CourtEvnetAppointmentOutcome courtEvnetAppointmentOutcome : dbList) {
			if (courtEvnetAppointmentOutcome.getCourtEvntSanctDtlId() != null) {
				CourtEvnetAppointmentOutcome returnObject = osanviosRepository
						.getCrtEvntSancDetailsData(courtEvnetAppointmentOutcome);
				courtEvnetAppointmentOutcome.setCourtEventId(returnObject.getCourtEventId());
				courtEvnetAppointmentOutcome.setRecordSanctionRewardCount(returnObject.getRecordSanctionRewardCount());
				courtEvnetAppointmentOutcome.setSessionEventId(returnObject.getRecordEventId());
				courtEvnetAppointmentOutcome.setCourtEvntSanctDtlId(returnObject.getCourtEvntSanctDtlId());
				courtEvnetAppointmentOutcome.setCountType(returnObject.getCountType());
				courtEvnetAppointmentOutcome.setAdjournedFlag(returnObject.getAdjournedFlag());
				courtEvnetAppointmentOutcome.setLinkFlag(returnObject.getLinkFlag());
				courtEvnetAppointmentOutcome.setCommentText(returnObject.getCommentText());
			}

		}
		return dbList;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer crtEventAppointmentCommit(CourtEvnetAppointmentOutcomeCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final CourtEvnetAppointmentOutcome obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = osanviosRepository.crtEventAppointmentInsertCourtEvents(commitBean.getInsertList());
		}

		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final CourtEvnetAppointmentOutcome obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = osanviosRepository.crtEventAppointmentUpdateCourtEvents(commitBean.getUpdateList());

		}

		return liReturn;
	}

	@Override
	public String getDefaultCancellationReason() {

		return osanviosRepository.getDefaultCancellationReason();
	}

	@Override
	public List<CourtEvnetAppointmentOutcome> progOutComeEecuteQuery(CourtEvnetAppointmentOutcome object) {
		List<CourtEvnetAppointmentOutcome> returnList = osanviosRepository.progOutComeEecuteQuery(object);
		for (CourtEvnetAppointmentOutcome bean : returnList) {
			if (bean.getCourtEvntSanctDtlId() != null) {
				CourtEvnetAppointmentOutcome returnObj = osanviosRepository.getCrtEvntSancDetailsData(bean);
				bean.setRecordSanctionRewardCount(returnObj.getRecordSanctionRewardCount());
				bean.setCountType(returnObj.getCountType());
				bean.setLinkFlag(returnObj.getLinkFlag());
				bean.setAdjournedFlag(returnObj.getAdjournedFlag());
				bean.setCommentText(returnObj.getCommentText());
				bean.setSessionEventId(returnObj.getRecordEventId());
			}
		}
		return returnList;
	}

	@Override
	public List<CourtEvnetAppointmentOutcome> progAppointmentEecuteQuery(CourtEvnetAppointmentOutcome searchBean) {
		List<CourtEvnetAppointmentOutcome> returnList = osanviosRepository.progAppointmentEecuteQuery(searchBean);
		for (CourtEvnetAppointmentOutcome bean : returnList) {
			if (bean.getCourtEvntSanctDtlId() != null) {
				CourtEvnetAppointmentOutcome returnObj = osanviosRepository.getCrtEvntSancDetailsData(bean);
				bean.setRecordSanctionRewardCount(returnObj.getRecordSanctionRewardCount());
				bean.setCountType(returnObj.getCountType());
				bean.setLinkFlag(returnObj.getLinkFlag());
				bean.setAdjournedFlag(returnObj.getAdjournedFlag());
				bean.setCommentText(returnObj.getCommentText());
				bean.setSessionEventId(returnObj.getRecordEventId());
			}
		}
		return returnList;
	}

	@Override
	public List<CourtEvnetAppointmentOutcome> comServiceEecuteQuery(CourtEvnetAppointmentOutcome searchBean) {
		List<CourtEvnetAppointmentOutcome> returnList = osanviosRepository.comServiceEecuteQuery(searchBean);
		for (CourtEvnetAppointmentOutcome bean : returnList) {
			if (bean.getCourtEvntSanctDtlId() != null) {
				CourtEvnetAppointmentOutcome returnObj = osanviosRepository.getCrtEvntSancDetailsData(bean);
				bean.setRecordSanctionRewardCount(returnObj.getRecordSanctionRewardCount());
				bean.setCountType(returnObj.getCountType());
				bean.setLinkFlag(returnObj.getLinkFlag());
				bean.setAdjournedFlag(returnObj.getAdjournedFlag());
				bean.setCommentText(returnObj.getCommentText());
				bean.setSessionEventId(returnObj.getRecordEventId());
			}
		}
		return returnList;
	}

	@Override
	public String populateLoggedStaffName(String userName) {
		final Integer staffId=generateStaffId(userName);
		final String staffName = ocucalcrRepository.populateStaffName(staffId);
		return staffName;
	}
	
	private Integer generateStaffId(String username) {
		return ocucalcrRepository.generateStaffId(username);
	}
}
