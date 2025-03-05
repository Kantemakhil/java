package net.syscon.s4.triggers.impl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderActionPlans;
import net.syscon.s4.inst.casemanagement.beans.OffApV1;
import net.syscon.s4.pkgs.tag_off_ap_v1.TagOffApV1Service;
import net.syscon.s4.triggers.ApVrIudRepository;
import net.syscon.s4.triggers.ApVrIudService;

@Service
class ApVrIudServiceImpl implements ApVrIudService {
	Logger logger = LogManager.getLogger(ApVrIudServiceImpl.class);
	@Autowired
	ApVrIudRepository apVrIudRepository;
	@Autowired
	TagOffApV1Service tagOffApV1Service;
	@Override
	public Integer apVrIudTrigger(final OffApV1 newOffApV1, final String opertion) {
		Integer retVal = 0;
		final OffenderActionPlans lrNewRec = new OffenderActionPlans();
		final OffenderActionPlans lrOldRec = new OffenderActionPlans();
		final OffApV1 oldOffApV1 = apVrIudRepository.getApVrIud(newOffApV1.getOffActionPlanId());
		if (Optional.ofNullable(oldOffApV1).isPresent()) {
			lrOldRec.setOffActionPlanId(oldOffApV1.getOffActionPlanId());
			lrOldRec.setCaseworkType(oldOffApV1.getCaseworkType());
			lrOldRec.setOffCrimNeedId(oldOffApV1.getOffCrimNeedId());
			lrOldRec.setOffCaseCondId(oldOffApV1.getOffCaseCondId());
			lrOldRec.setProgramId(oldOffApV1.getProgramId());
			lrOldRec.setNotes(oldOffApV1.getNotes());
			lrOldRec.setEndDate(oldOffApV1.getEndDate());
			lrOldRec.setStartDate(oldOffApV1.getStartDate());
		}

		if (Optional.ofNullable(newOffApV1).isPresent()) {
			lrNewRec.setOffActionPlanId(newOffApV1.getOffActionPlanId());
			lrNewRec.setCaseworkType(newOffApV1.getCaseworkType());
			lrNewRec.setOffCrimNeedId(newOffApV1.getOffCrimNeedId());
			lrNewRec.setOffCaseCondId(newOffApV1.getOffCaseCondId());
			lrNewRec.setProgramId(newOffApV1.getProgramId());
			lrNewRec.setNotes(newOffApV1.getNotes());
			lrNewRec.setEndDate(newOffApV1.getEndDate());
			lrNewRec.setStartDate(newOffApV1.getStartDate());
			lrNewRec.setCreateUserId(newOffApV1.getCreateUserId());
			lrNewRec.setModifyUserId(newOffApV1.getModifyUserId());
		}
		try {
//		IF INSERTING THEN
			if (opertion.equals("INSERTING")) {
				retVal = tagOffApV1Service.prInsProcedure(lrOldRec, lrNewRec);
			}
//		 ELSIF UPDATING THEN
			if (opertion.equals("UPDATING")) {
				retVal = tagOffApV1Service.prUpdProcedure(lrOldRec, lrNewRec);
			}
//		ELSIF DELETING THEN
			if (opertion.equals("DELETING")) {
				lrOldRec.setModifyUserId(newOffApV1.getModifyUserId());
				retVal = tagOffApV1Service.prDelProcedure(lrOldRec, lrNewRec);
			}
		} catch (final Exception e) {
			logger.error("apVrIudTrigger", e);
			retVal = 0;
		}
		return retVal;
	}

}
