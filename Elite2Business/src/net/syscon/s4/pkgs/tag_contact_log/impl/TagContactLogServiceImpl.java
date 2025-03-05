package net.syscon.s4.pkgs.tag_contact_log.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderCaseNoteSent;
import net.syscon.s4.common.beans.OffenderIndSchSent;
import net.syscon.s4.inst.legals.beans.OffenderSentences;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules2;
import net.syscon.s4.pkgs.tag_contact_log.TagContactLogRepository;
import net.syscon.s4.pkgs.tag_contact_log.TagContactLogService;

@Service
public class TagContactLogServiceImpl implements TagContactLogService {

	@Autowired
	private TagContactLogRepository tagContactLogRepository;
	private static Logger logger = LogManager.getLogger(TagContactLogServiceImpl.class.getName());

	@Override
	public Integer deleteVirtualSchedule(final VOffenderAllSchedules lstVOffenderAllSchedules2) {
		return tagContactLogRepository.deleteVirtualSchedule(lstVOffenderAllSchedules2);

	}

	@Override
	public Integer insNoteSchAssociation(final Long offenderBookId, final Long caseNoteId, final Long schId,
			final String userName) {
		try {
			final List<OffenderSentences> list = tagContactLogRepository.activeSentaCur(offenderBookId);
			if (caseNoteId != null) {
				list.forEach(bo -> {
					final OffenderCaseNoteSent offSen = new OffenderCaseNoteSent();
					offSen.setCaseNoteId(caseNoteId);
					offSen.setOffenderBookId(offenderBookId);
					offSen.setSentenceSeq(bo.getSentenceSeq());
					offSen.setCreateUserId(userName);
					tagContactLogRepository.insertOffenderCaseNoteSen(offSen);
				});
			}
			if (schId != null) {
				list.forEach(bo -> {
					final OffenderIndSchSent bean = new OffenderIndSchSent();
					bean.setEventId(schId);
					bean.setOffenderBookId(offenderBookId);
					bean.setSentenceSeq(bo.getSentenceSeq());
					bean.setCreateUserId(userName);
					tagContactLogRepository.offenderIndSchSentens(bean);
				});
			}
		} catch (DataAccessException e) {
			logger.error("insertOffenderCaseNoteSen :" + e);
			return 0;
		}
		return 1;
	}

	@Override
	public Integer UpdateSchedule(final VOffenderAllSchedules offsch) {
		Integer count = null;
		try {
			if (offsch.getEventId() != null) {
				// Updating the physical records
				tagContactLogRepository.updateScheduleForPhysicalRecord(offsch);
			} else {
				// Updating the virtual records
				tagContactLogRepository.updateScheduleForVirtualRecords(offsch);
			}
			count = 1;
		} catch (Exception e) {
			count = 0;
		}
		return count;
	}

	@Override
	public Integer delNoteSchAssociation(final Long pOffenderBookId, final BigDecimal pSchId,String modifyUserId) {
		return tagContactLogRepository.deleteNoteschAss(pOffenderBookId, pSchId,modifyUserId);
	}

	@Override
	public String checkUpdOutcomeLogValid(final VOffenderAllSchedules offSch) {
		return tagContactLogRepository.getOutcomeFlagCur(offSch.getEventType(), offSch.getEventSubType(),
				offSch.getEventOutcome());
	}

	@Override
	public String getModuleName(final String pContactType, final String pContactSubType) {
		return tagContactLogRepository.getModuleNameFmWork(pContactType, pContactSubType);

	}

}