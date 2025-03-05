package net.syscon.s4.triggers.impl;

import java.util.Date;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderAssessments;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OffenderAssocPEventNotifs;
import net.syscon.s4.triggers.OffenderAssociatedPartiesT1Repository;
import net.syscon.s4.triggers.OffenderAssociatedPartiesT1Service;

@Service
public class OffenderAssociatedPartiesT1ServiceImpl extends RepositoryBase
		implements OffenderAssociatedPartiesT1Service {
	private final Logger logger = LogManager.getLogger(OffenderAssociatedPartiesT1ServiceImpl.class);
	@Autowired
	OffenderAssociatedPartiesT1Repository offenderAssociatedPartiesT1Repository;

	@Override
	public Integer offenderAssociatedPartiesT1(final OffenderAssessments offenderAssessments) {
		String lCheckExistFlag = "N";
		Integer result = 0;
		OffenderAssocPEventNotifs targerObj = new OffenderAssocPEventNotifs();
		try {
			final OffenderAssocPEventNotifs lCheckExistCur = offenderAssociatedPartiesT1Repository
					.lCheckExistCur(offenderAssessments.getOffenderBookId());
			lCheckExistFlag = Optional.ofNullable(lCheckExistCur).isPresent()
					&& Optional.ofNullable(lCheckExistCur.getlCheckExistFlag()).isPresent()
							? lCheckExistCur.getlCheckExistFlag()
							: "N";
			if ("N".equals(lCheckExistFlag)) {
				targerObj = dataMapper(offenderAssessments);
				result = offenderAssociatedPartiesT1Repository.save(targerObj);
			}
			if ("Y".equals(lCheckExistFlag) && Optional.ofNullable(lCheckExistCur.getEventDate()).isPresent()
					&& new Date().compareTo(lCheckExistCur.getEventDate()) != 0) {
				targerObj = dataMapper(offenderAssessments);
				result = offenderAssociatedPartiesT1Repository.save(targerObj);

			}
		} catch (final Exception e) {
			result = 0;
			logger.error("offenderAssociatedPartiesT1", e);
		}
		return result;
	}

	private OffenderAssocPEventNotifs dataMapper(final OffenderAssessments offenderAssessments) {
		OffenderAssocPEventNotifs targerObj;
		targerObj = new OffenderAssocPEventNotifs();
		targerObj.setTrgEventId(offenderAssociatedPartiesT1Repository.trgEventId());
		targerObj.setEventDate(new Date());
		targerObj.setOffenderBookId(offenderAssessments.getOffenderBookId());
		targerObj.setNotificationCode("NEWVIC");
		targerObj.setCreateDatetime(offenderAssessments.getCreateDatetime());
		targerObj.setCreateUserId(offenderAssessments.getCreateUserId());
		targerObj.setModifyDatetime(offenderAssessments.getCreateDatetime());
		return targerObj;
	}

}
