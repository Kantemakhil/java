package net.syscon.s4.pkgs.tag_multiple_failure.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.inst.legals.beans.VOffenderSentenceEvents;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.pkgs.tag_multiple_failure.TagMultipleFailureRepository;
import net.syscon.s4.pkgs.tag_multiple_failure.TagMultipleFailureService;

@Service
public class TagMultipleFailureServiceImpl implements TagMultipleFailureService {

	@Autowired
	private TagMultipleFailureRepository tagMultipleFailureRepositoryRepo;

	private static final String N_VALUE = "N";
	private static final String Y_VALUE = "Y";
	private static Logger logger = LogManager.getLogger(TagMultipleFailureServiceImpl.class.getName());

	public void adjustUa(final VOffenderAllSchedules vOffSch, final String userName) {
		final int oldNum = tagMultipleFailureRepositoryRepo.checkUaEventOutcomeOld(vOffSch);
		final int newNum = tagMultipleFailureRepositoryRepo.checkUaEventOutcomeNew(vOffSch);

		if (oldNum > 0) {
			destroyUas(vOffSch.getEventId(),userName);
		}
		if (newNum > 0) {
			vOffSch.setNbtCreateUserId(userName);
			vOffSch.setModifyUserId(userName);
			createUas(vOffSch);
		}

	}

	public void destroyUas(final BigDecimal eventId,String modifyUserId) {
		List<VOffenderAllSchedules> list = new ArrayList<VOffenderAllSchedules>();
		list = tagMultipleFailureRepositoryRepo.osuaCurSelectOperation(eventId);
		for (final VOffenderAllSchedules vOffSch : list) {
			vOffSch.setModifyUserId(modifyUserId);
			tagMultipleFailureRepositoryRepo.osuaRecDeleteOperation(vOffSch);
			if (Y_VALUE.equals(vOffSch.getCountedFlag())) {
				vOffSch.setAdjustment(-1);
				mfDdjust(vOffSch);
			}
		}
	}

	public void mfDdjust(final VOffenderAllSchedules vOffSch) {

		final int count = tagMultipleFailureRepositoryRepo.mfADdjustSelectOperation(vOffSch);
		if (count > 0) {
			tagMultipleFailureRepositoryRepo.mfDAdjustUpdate(vOffSch);
		} else {
			tagMultipleFailureRepositoryRepo.mfDAdjustInsert(vOffSch);
		}
	}

	public void createUas(final VOffenderAllSchedules vOffSch) {
		List<VOffenderAllSchedules> list = new ArrayList<VOffenderAllSchedules>();
		list = tagMultipleFailureRepositoryRepo.oseCurSelectOperation(vOffSch.getEventId());
		for (final VOffenderAllSchedules vOff : list) {
			vOff.setCountedFlag(Y_VALUE);
			if (N_VALUE.equals(vOff.getUnexcusedAbsenceFlag())) {
				final int countUa = tagMultipleFailureRepositoryRepo.countSentenceUaSelectOperation(vOff);
				if (countUa > 0) {
					vOff.setCountedFlag(N_VALUE);
				}
			}
			final int uaEvent = tagMultipleFailureRepositoryRepo.uaEventExists(vOff);
			if (uaEvent > 0) {
				tagMultipleFailureRepositoryRepo.uaEventExistsDelOperation(vOff);
			}
			vOff.setNbtCreateUserId(vOffSch.getNbtCreateUserId());
			tagMultipleFailureRepositoryRepo.createUasInsert(vOff);
			if ("Y".equals(vOff.getCountedFlag())) {
				vOffSch.setAdjustment(1);
				mfDdjust(vOffSch);
			}
		}
	}

	@Override
	public Boolean checkUaEventOutcome(final OffenderCourseAttendance bean) {
		return tagMultipleFailureRepositoryRepo.checkUaEventOutcome(bean);
	}

	public Map<String, Object> checkUa(final VOffenderSentenceEvents bean) {
		final Map<String, Object> returnMap = new HashedMap();
		Integer pOdUa = 0;
		Integer pNewUa = 0;
		Integer pMultiplefailure = 0;
		try {
			List<VOffenderSentenceEvents> vOffSeEv = tagMultipleFailureRepositoryRepo
					.failcur(bean.getEventId().longValue());
			pMultiplefailure = 0;
			String result1 = tagMultipleFailureRepositoryRepo.curUaOldOutcome(bean.getEventType(),
					bean.getEventSubType(), bean.getEventOutcome());
			if (result1.equals(Y_VALUE)) {
				pOdUa = 1;
			} else {
				pOdUa = 0;
			}
			String result2 = tagMultipleFailureRepositoryRepo.curUaOldOutcome(bean.getEventType(),
					bean.getEventSubType(), bean.getEventOutcome());
			if (result2.equals(Y_VALUE)) {
				pNewUa = 1;
			} else {
				pNewUa = 0;
			}

			if (pOdUa != 1 && pNewUa != 1) {
				return null;
			}

			for (final VOffenderSentenceEvents vean : vOffSeEv) {
				final Integer count3 = tagMultipleFailureRepositoryRepo.selectCount(vean);
				if (count3 > 0) {
					pMultiplefailure = 1;
				}
			}
			returnMap.put("P_OD_UA", pOdUa);
			returnMap.put("P_NEW_UA", pNewUa);
			returnMap.put("P_MULTIPLE_FAILURE", pMultiplefailure);
		} catch (final Exception e) {
			logger.error("checkUa", e);
			return null;
		}
		return returnMap;
	}
}
