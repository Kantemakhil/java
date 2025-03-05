package net.syscon.s4.triggers.impl;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffenderActionPlans;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.inst.casemanagement.beans.OffenderCaseConditions;
import net.syscon.s4.inst.casemanagement.beans.OffenderCriminogenicNeeds;
import net.syscon.s4.triggers.CasePlansT3Repository;
import net.syscon.s4.triggers.CasePlansT3Service;


@Service
public class CasePlansT3ServiceImpl implements CasePlansT3Service{

	@Autowired
	CasePlansT3Repository casePlansT3Repository;


	@Transactional
	@Override
	public void casePlansT3(final CasePlans casePlans,final String userName) {
		if(Objects.nonNull(casePlans)) {
			Integer lvCcCount = null;
			Integer lvCnCount = null;
			Integer lvCcApCount = null;
			Integer lvCnApCount = null;
			Integer lvOffCaseCondId = null;
			lvCcCount=casePlansT3Repository.offenderCaseConditionsCount(casePlans.getOffenderBookId(), casePlans.getCasePlanId()-1);
			if(lvCcCount!=0) {
				List<OffenderCaseConditions> offenderCaseConditionsList = casePlansT3Repository.getOffenderCaseConditions(casePlans.getOffenderBookId(), casePlans.getCasePlanId()-1);
				for (OffenderCaseConditions offenderCaseConditions : offenderCaseConditionsList) {
					if(Objects.nonNull(casePlans.getCasePlanId()) && Objects.nonNull(casePlans.getOffenderBookId())) {
						lvOffCaseCondId = casePlansT3Repository.caseCondsDdCur();
						casePlansT3Repository.insertOffenderCaseConditions(offenderCaseConditions, casePlans, lvOffCaseCondId);
					}
					lvCcApCount = casePlansT3Repository.offenderActionPlansCount(offenderCaseConditions.getOffCaseCondId());
					if(lvCcApCount!=0) {
						List<OffenderActionPlans> offenderActionPlansList = casePlansT3Repository.getOffenderActionPlans(offenderCaseConditions.getOffCaseCondId());
						for (OffenderActionPlans offenderActionPlans : offenderActionPlansList) {
							offenderActionPlans.setCreateUserId(userName);
							casePlansT3Repository.insertOffenderActionPlans(offenderActionPlans, lvOffCaseCondId);
						}
					}
				}
			}
			lvCnCount = casePlansT3Repository.offenderGriminogenicNeedsCount(casePlans.getOffenderBookId(), casePlans.getCasePlanId()-1);
			if(lvCnCount!=0) {
				List<OffenderCriminogenicNeeds> offenderCriminogenicNeedsList= casePlansT3Repository.getOffendercriminogenicNeeds(casePlans.getOffenderBookId(), casePlans.getCasePlanId());
				for (OffenderCriminogenicNeeds offenderCriminogenicNeeds : offenderCriminogenicNeedsList) {
					if(Objects.nonNull(casePlans.getCasePlanId()) && Objects.nonNull(casePlans.getOffenderBookId())) {
						casePlansT3Repository.insertOffenderCriminogenicNeeds(offenderCriminogenicNeeds, casePlans, lvOffCaseCondId);
					}
					lvCnApCount = casePlansT3Repository.offenderActionPlansCount(offenderCriminogenicNeeds.getOffCrimNeedId());
					if(lvCnApCount!=0) {
						List<OffenderActionPlans> offenderActionPlansList =casePlansT3Repository.getOffenderActionPlans(offenderCriminogenicNeeds.getOffCrimNeedId());
						for (OffenderActionPlans offenderActionPlans : offenderActionPlansList) {
							offenderActionPlans.setCreateUserId(userName);
							casePlansT3Repository.insertOffenderActionPlans2(offenderActionPlans, lvOffCaseCondId);
						}
					}
				}
			}
		}

	}
}
