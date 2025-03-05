package net.syscon.s4.triggers.impl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.triggers.OffenderAssocPEventNotifs;
import net.syscon.s4.triggers.OffenderTmpRelSchedules;
import net.syscon.s4.triggers.OffenderTmpRelSchedulesT1Repository;
import net.syscon.s4.triggers.OffenderTmpRelSchedulesT1Service;

@Service
public class OffenderTmpRelSchedulesT1ServiceImpl implements OffenderTmpRelSchedulesT1Service {
	private final Logger logger = LogManager.getLogger(OffenderTmpRelSchedulesT1ServiceImpl.class);
	@Autowired
	OffenderTmpRelSchedulesT1Repository offenderTmpRelSchedulesT1Repository;

	@Override
	public Integer offenderTmpRelSchedulesT1Trigger(final OffenderTmpRelSchedules offeTmpRelSche,
			final String SqlOperation) {
		String lCheckExistFlag;
		OffenderAssocPEventNotifs offeAssPEvntNoti = new OffenderAssocPEventNotifs();
		Integer result = 0;
		try {
			if ("INSERTING".equalsIgnoreCase(SqlOperation)) {
				if (Optional.ofNullable(offeTmpRelSche.getReleaseDate()).isPresent()) {
					lCheckExistFlag = "N";
					lCheckExistFlag = offenderTmpRelSchedulesT1Repository
							.lCheckExistCur(offeTmpRelSche.getOffenderBookId(), offeTmpRelSche.getReleaseDate());
					if ("N".equals(lCheckExistFlag)) {
						offeAssPEvntNoti = new OffenderAssocPEventNotifs();
						final Long trgEventId = offenderTmpRelSchedulesT1Repository.trgEventIdSeq();
						offeAssPEvntNoti.setTrgEventId(trgEventId);
						offeAssPEvntNoti.setEventDate(offeTmpRelSche.getReleaseDate());
						offeAssPEvntNoti.setOffenderBookId(offeTmpRelSche.getOffenderBookId());
						offeAssPEvntNoti.setNotificationCode("DISCH");
						offeAssPEvntNoti.setCreateDatetime(offeTmpRelSche.getCreateDatetime());
						offeAssPEvntNoti.setCreateUserId(offeTmpRelSche.getCreateUserId());
						offeAssPEvntNoti.setModifyDatetime(offeTmpRelSche.getCreateDatetime());
						result = offenderTmpRelSchedulesT1Repository.insert(offeAssPEvntNoti);
						lCheckExistFlag = offenderTmpRelSchedulesT1Repository
								.lSexCheckExistCur(offeTmpRelSche.getOffenderBookId());
						if ("Y".equals(lCheckExistFlag)) {
							offeAssPEvntNoti = new OffenderAssocPEventNotifs();
							final Long trgEventIdSo = offenderTmpRelSchedulesT1Repository.trgEventIdSeq();
							offeAssPEvntNoti.setTrgEventId(trgEventIdSo);
							offeAssPEvntNoti.setEventDate(offeTmpRelSche.getReleaseDate());
							offeAssPEvntNoti.setOffenderBookId(offeTmpRelSche.getOffenderBookId());
							offeAssPEvntNoti.setNotificationCode("DISCH_SO");
							result = offenderTmpRelSchedulesT1Repository.insert(offeAssPEvntNoti);
						}
					}
				}

			} else if ("UPDATING".equalsIgnoreCase(SqlOperation)) {
				if (Optional.ofNullable(offeTmpRelSche.getReleaseDate()).isPresent()) {
					lCheckExistFlag = "N";
					lCheckExistFlag = offenderTmpRelSchedulesT1Repository
							.lCheckExistCur(offeTmpRelSche.getOffenderBookId(), offeTmpRelSche.getReleaseDate());
					if ("N".equals(lCheckExistFlag)) {
						offeAssPEvntNoti = new OffenderAssocPEventNotifs();
						final Long trgEventId = offenderTmpRelSchedulesT1Repository.trgEventIdSeq();
						offeAssPEvntNoti.setTrgEventId(trgEventId);
						offeAssPEvntNoti.setEventDate(offeTmpRelSche.getReleaseDate());
						offeAssPEvntNoti.setOffenderBookId(offeTmpRelSche.getOffenderBookId());
						offeAssPEvntNoti.setNotificationCode("DISCH");
						offeAssPEvntNoti.setModifyDatetime(offeTmpRelSche.getCreateDatetime());
						offeAssPEvntNoti.setModifyUserId(offeTmpRelSche.getCreateUserId());
						result = offenderTmpRelSchedulesT1Repository.update(offeAssPEvntNoti);
						lCheckExistFlag = offenderTmpRelSchedulesT1Repository
								.lSexCheckExistCur(offeTmpRelSche.getOffenderBookId());
						if ("Y".equals(lCheckExistFlag)) {
							offeAssPEvntNoti = new OffenderAssocPEventNotifs();
							final Long trgEventIdSo = offenderTmpRelSchedulesT1Repository.trgEventIdSeq();
							offeAssPEvntNoti.setTrgEventId(trgEventIdSo);
							offeAssPEvntNoti.setEventDate(offeTmpRelSche.getReleaseDate());
							offeAssPEvntNoti.setOffenderBookId(offeTmpRelSche.getOffenderBookId());
							offeAssPEvntNoti.setNotificationCode("DISCH_SO");
							offeAssPEvntNoti.setModifyDatetime(offeTmpRelSche.getCreateDatetime());
							offeAssPEvntNoti.setModifyUserId(offeTmpRelSche.getCreateUserId());
							result = offenderTmpRelSchedulesT1Repository.update(offeAssPEvntNoti);
						}
					}
				}

			}
		} catch (final Exception e) {
			logger.error("offenderTmpRelSchedulesT1Trigger", e);
			result = 0;
		}
		return result;
	}

}
