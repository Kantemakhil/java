package net.syscon.s4.triggers.impl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.schedules.bean.OffenderReleaseDetails;
import net.syscon.s4.triggers.OffenderAssocPEventNotifs;
import net.syscon.s4.triggers.OffenderReleaseDetailsT2Repository;
import net.syscon.s4.triggers.OffenderReleaseDetailsT2Service;

@Service
public class OffenderReleaseDetailsT2ServiceImpl implements OffenderReleaseDetailsT2Service {
	private static Logger logger = LogManager.getLogger(OffenderReleaseDetailsT2ServiceImpl.class);
	@Autowired
	OffenderReleaseDetailsT2Repository offenderReleaseDetailsT2Repository;

	@Override
	public Integer offenderReleaseDetailsT2(final OffenderReleaseDetails offenderReleaseDet) {
		OffenderAssocPEventNotifs offenderAssPEveNot;
		String lCheckExistFlag;
		final OffenderReleaseDetails offenRelOld = offenderReleaseDetailsT2Repository
				.getOffenderReleaseDetails(offenderReleaseDet.getOffenderBookId());
		if (Optional.ofNullable(offenderReleaseDet).isPresent() && offenderReleaseDet.getSealFlag() == null
				|| (Optional.ofNullable(offenRelOld).isPresent()
						&& offenderReleaseDet.getSealFlag().equals(offenRelOld.getSealFlag()))) {
			if (Optional.ofNullable(offenderReleaseDet.getReleaseDate()).isPresent() && offenderReleaseDet.getNbtName()!=null &&  "INSERTING".equals(offenderReleaseDet.getNbtName())) {
				lCheckExistFlag = "N";
				final String lCheckExistCur = offenderReleaseDetailsT2Repository
						.lCheckExistCur(offenderReleaseDet.getOffenderBookId(), offenderReleaseDet.getReleaseDate());
				if (lCheckExistCur == null || "".equals(lCheckExistCur)) {
					lCheckExistFlag = "N";
				}
				if ("N".equals(lCheckExistFlag)) {
					offenderAssPEveNot = dataMapping(offenderReleaseDet);
					offenderAssPEveNot.setNotificationCode("DISCH");
					try {
						offenderReleaseDetailsT2Repository.inserting(offenderAssPEveNot);
						lCheckExistFlag = "N";
						lCheckExistFlag = offenderReleaseDetailsT2Repository
								.lSexCheckExistCur(offenderReleaseDet.getOffenderBookId());

						if ("Y".equals(lCheckExistFlag)) {
							offenderAssPEveNot = dataMapping(offenderReleaseDet);
							offenderAssPEveNot.setNotificationCode("DISCH_SO");
							offenderReleaseDetailsT2Repository.inserting(offenderAssPEveNot);
						}
						if ("EXEC".equals(offenderReleaseDet.getMovementReasonCode())) {
							offenderAssPEveNot = dataMapping(offenderReleaseDet);
							offenderAssPEveNot.setNotificationCode("EXEC");
							offenderReleaseDetailsT2Repository.inserting(offenderAssPEveNot);
						}
					} catch (final Exception e) {
						logger.error("offenderReleaseDetailsT2 :: insert", e);
						return null;
					}
				}
			}
			else {				
				if (Optional.ofNullable(offenderReleaseDet.getReleaseDate()).isPresent()) {
					lCheckExistFlag = "N";
					final String lCheckExistCur = offenderReleaseDetailsT2Repository
							.lCheckExistCur(offenderReleaseDet.getOffenderBookId(), offenderReleaseDet.getReleaseDate());
					if (lCheckExistCur == null || "".equals(lCheckExistCur)) {
						lCheckExistFlag = "N";
						try {
							offenderAssPEveNot = dataMapping(offenderReleaseDet);
							offenderAssPEveNot.setNotificationCode("DISCH");
							offenderReleaseDetailsT2Repository.updating(offenderAssPEveNot);
							lCheckExistFlag = offenderReleaseDetailsT2Repository
									.lSexCheckExistCur(offenderReleaseDet.getOffenderBookId());
							
							if ("Y".equals(lCheckExistFlag)) {
								offenderAssPEveNot = dataMapping(offenderReleaseDet);
								offenderAssPEveNot.setNotificationCode("DISCH_SO");
								offenderReleaseDetailsT2Repository.updating(offenderAssPEveNot);
							}
							if ("EXEC".equals(offenderReleaseDet.getMovementReasonCode())) {
								offenderAssPEveNot = dataMapping(offenderReleaseDet);
								offenderAssPEveNot.setNotificationCode("EXEC");
								offenderReleaseDetailsT2Repository.updating(offenderAssPEveNot);
							}
						} catch (final Exception e) {
							logger.error("offenderReleaseDetailsT2:: update", e);
							return null;
						}
						
					}
				}
			}

		}
		return null;
	}

	private OffenderAssocPEventNotifs dataMapping(final OffenderReleaseDetails offenderReleaseDet) {
		OffenderAssocPEventNotifs offenderAssPEveNot;
		offenderAssPEveNot = new OffenderAssocPEventNotifs();
		offenderAssPEveNot.setTrgEventId(offenderReleaseDetailsT2Repository.lTrgEventId());
		offenderAssPEveNot.setEventDate(offenderReleaseDet.getReleaseDate());
		offenderAssPEveNot.setOffenderBookId(offenderReleaseDet.getOffenderBookId());
		offenderAssPEveNot.setCreateUserId(offenderReleaseDet.getCreateUserId());
		offenderAssPEveNot.setModifyUserId(offenderReleaseDet.getModifyUserId());
		return offenderAssPEveNot;
	}
}
