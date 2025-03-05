package net.syscon.s4.triggers.impl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderActionPlans;
import net.syscon.s4.inst.casemanagement.beans.OffApV2;
import net.syscon.s4.pkgs.tag_off_ap_v2.TagOffApV2Service;
import net.syscon.s4.triggers.Ap2VrIudRepository;
import net.syscon.s4.triggers.Ap2VrIudService;

@Service
public class Ap2VrIudServiceImpl implements Ap2VrIudService {
	Logger logger = LogManager.getLogger(Ap2VrIudServiceImpl.class);
	@Autowired
	Ap2VrIudRepository ap2VrIudRepository;
	@Autowired
	TagOffApV2Service tagOffApV2Service;

	@Override
	public Integer ap2VrIudTrigger(final OffApV2 newOffApV2, String action) {
		final OffenderActionPlans lrNewRec = new OffenderActionPlans();
		final OffenderActionPlans lrOldRec = new OffenderActionPlans();
		final OffApV2 oldOffApV2 = ap2VrIudRepository.getAp2VrIud(newOffApV2.getOffActionPlanId());
		if (Optional.ofNullable(oldOffApV2).isPresent()) {
			lrOldRec.setOffActionPlanId(oldOffApV2.getOffActionPlanId());
			lrOldRec.setCaseworkType(oldOffApV2.getCaseworkType());
			lrOldRec.setOffCrimNeedId(oldOffApV2.getOffCrimNeedId());
			lrOldRec.setOffCaseCondId(oldOffApV2.getOffCaseCondId());
			lrOldRec.setProgramId(oldOffApV2.getProgramId());
			lrOldRec.setNotes(oldOffApV2.getNotes());
			lrOldRec.setEndDate(oldOffApV2.getEndDate());
			lrOldRec.setStartDate(oldOffApV2.getStartDate());
		}

		if (Optional.ofNullable(newOffApV2).isPresent()) {
			lrNewRec.setOffActionPlanId(newOffApV2.getOffActionPlanId());
			lrNewRec.setCaseworkType(newOffApV2.getCaseworkType());
			lrNewRec.setOffCrimNeedId(newOffApV2.getOffCrimNeedId());
			lrNewRec.setOffCaseCondId(newOffApV2.getOffCaseCondId());
			lrNewRec.setProgramId(newOffApV2.getProgramId());
			lrNewRec.setNotes(newOffApV2.getNotes());
			lrNewRec.setEndDate(newOffApV2.getEndDate());
			lrNewRec.setStartDate(newOffApV2.getStartDate());
		}
		try {
//		IF INSERTING THEN
			if ("INSERTING".equalsIgnoreCase(action)) {
				lrNewRec.setCreateUserId(newOffApV2.getCreateUserId());
				tagOffApV2Service.prInsProcedure(lrOldRec, lrNewRec);
			}
//		 ELSIF UPDATING THEN
			if ("UPDATING".equalsIgnoreCase(action)) {
				lrNewRec.setModifyUserId(newOffApV2.getModifyUserId());
				tagOffApV2Service.prUpdProcedure(lrOldRec, lrNewRec);
			}
//		ELSIF DELETING THEN
			if ("DELETING".equalsIgnoreCase(action)) {
				lrOldRec.setModifyUserId(newOffApV2.getModifyUserId());
				tagOffApV2Service.prDelProcedure(lrOldRec, lrNewRec);
			}
		} catch (final Exception e) {
			logger.error("aonVrIud", e);
		}

		return 1;
	}

}
