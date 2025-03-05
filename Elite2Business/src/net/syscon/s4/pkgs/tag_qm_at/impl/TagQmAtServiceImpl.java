package net.syscon.s4.pkgs.tag_qm_at.impl;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.QmActivities;
import net.syscon.s4.pkgs.QmConActivityTeams;
import net.syscon.s4.pkgs.tag_qm_at.TagQmAtRepository;
import net.syscon.s4.pkgs.tag_qm_at.TagQmAtService;
import net.syscon.s4.triggers.VQmAt;

@Service
public class TagQmAtServiceImpl implements TagQmAtService {
	private final Logger logger = LogManager.getLogger(TagQmAtServiceImpl.class);
	@Autowired
	TagQmAtRepository tagQmAtRepository;

	@Override
	public Integer prIns(final VQmAt lrOldRec, final VQmAt lrNewRec) {
		final QmActivities qmActivities = new QmActivities();
		Integer result = 0;
		try {
			final Long vActivityId = tagQmAtRepository.activityIdCur();
			final QmConActivityTeams qmConActivityTeams = new QmConActivityTeams();
			BeanUtils.copyProperties(lrNewRec, qmActivities);
			qmActivities.setActivityId(vActivityId);
			qmActivities.setCreateDatetime(new Date());
			qmActivities.setModifyDatetime(new Date());
			tagQmAtRepository.prInsQmActivities(qmActivities);
			BeanUtils.copyProperties(lrNewRec, qmConActivityTeams);
			qmConActivityTeams.setActivityId(vActivityId);
			qmConActivityTeams.setCreateDatetime(new Date());
			qmConActivityTeams.setModifyDatetime(new Date());
			tagQmAtRepository.prInsQmConActivityTeams(qmConActivityTeams);
		} catch (final Exception e) {
			result = 0;
			logger.error("prIns", e);
		}
		return result;
	}

	@Override
	public Integer prUpd(final VQmAt lrOldRec, final VQmAt lrNewRec) {
		final QmActivities qmActivities = new QmActivities();
		Integer result = 0;
		try {
			final QmConActivityTeams qmConActivityTeams = new QmConActivityTeams();
			BeanUtils.copyProperties(lrNewRec, qmActivities);
			qmActivities.setActivityId(lrOldRec.getActivityId());
			qmConActivityTeams.setModifyUserId(lrNewRec.getCreateUserId());
			qmActivities.setModifyDatetime(new Date());
			tagQmAtRepository.prUpdQmActivities(qmActivities);
			BeanUtils.copyProperties(lrNewRec, qmConActivityTeams);
			qmConActivityTeams.setModifyDatetime(new Date());
			qmConActivityTeams.setModifyUserId(lrNewRec.getCreateUserId());
			qmActivities.setActivityId(lrOldRec.getActivityId());
			tagQmAtRepository.prUpdQmConActivityTeams(qmConActivityTeams);
		} catch (final Exception e) {
			result = 0;
			logger.error("prUpd", e);
		}
		return result;
	}

	@Override
	public Integer prDel(final VQmAt lrOldRec, final VQmAt lrNewRec) {
		return null;
	}

}
