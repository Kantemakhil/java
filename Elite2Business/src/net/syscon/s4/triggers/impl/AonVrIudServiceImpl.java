package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.tag_ass_off_needs.TagAssOffNeedsService;
import net.syscon.s4.pkgs.tag_ass_off_needs.impl.TagAssOffNeedsServiceImpl;
import net.syscon.s4.triggers.AonVrIudRepository;
import net.syscon.s4.triggers.AonVrIudService;
import net.syscon.s4.triggers.AssessedOffenderNeeds;
import net.syscon.s4.triggers.VAssOffNeeds;

@Service
public class AonVrIudServiceImpl implements AonVrIudService {
	Logger logger = LogManager.getLogger(TagAssOffNeedsServiceImpl.class);
	@Autowired
	AonVrIudRepository aonVrIudRepository;
	@Autowired
	TagAssOffNeedsService tagAssOffNeedsService;

	@Override
	public Integer aonVrIud(final VAssOffNeeds vAssOffNeedsNew,String operation) {
		int res =0;
		final AssessedOffenderNeeds newObj = new AssessedOffenderNeeds();
		final AssessedOffenderNeeds oldObj = new AssessedOffenderNeeds();
		final VAssOffNeeds oldVAssOffNeeds = aonVrIudRepository.getVAssOffNeeds(vAssOffNeedsNew.getOffAssNeedId());
		if(oldVAssOffNeeds!=null) {
			oldObj.setAssessmentId(oldVAssOffNeeds.getAssessmentId());
			oldObj.setOffAssNeedId(oldVAssOffNeeds.getOffAssNeedId());
			oldObj.setAssessedNeedCode(oldVAssOffNeeds.getAssessedNeedCode());
			oldObj.setObjective(oldVAssOffNeeds.getObjective());
			oldObj.setTargetDate(oldVAssOffNeeds.getTargetDate());
			oldObj.setActiveFlag(oldVAssOffNeeds.getActiveFlag());
			oldObj.setExpiryDate(oldVAssOffNeeds.getExpiryDate());
		}

		newObj.setAssessmentId(vAssOffNeedsNew.getAssessmentId());
		newObj.setOffAssNeedId(vAssOffNeedsNew.getOffAssNeedId());
		newObj.setAssessedNeedCode(vAssOffNeedsNew.getAssessedNeedCode());
		newObj.setObjective(vAssOffNeedsNew.getObjective());
		newObj.setTargetDate(vAssOffNeedsNew.getTargetDate());
		newObj.setActiveFlag(vAssOffNeedsNew.getActiveFlag());
		newObj.setExpiryDate(vAssOffNeedsNew.getExpiryDate());
		try {
			if(operation!=null && operation.equals("INSERTING")){
				oldObj.setCreateUserId(vAssOffNeedsNew.getCreateUserId());
				newObj.setCreateUserId(vAssOffNeedsNew.getCreateUserId());
				res = tagAssOffNeedsService.prInsProcedure(oldObj, newObj);
			}
			else if(operation!=null && operation.equals("UPDATING")){
				oldObj.setModifyUserId(vAssOffNeedsNew.getCreateUserId());
				tagAssOffNeedsService.prDelProcedure(oldObj, newObj);
			}
			else if(operation!=null && operation.equals("DELETING")){
				tagAssOffNeedsService.prUpdProcedure(oldObj, newObj);
			}
		} catch (final Exception e) {
			logger.error("aonVrIud", e);
		}
		return res;
	}

}
