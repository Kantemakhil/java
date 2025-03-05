package net.syscon.s4.triggers;


import java.util.List;

import net.syscon.s4.common.beans.OffenderActionPlans;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.inst.casemanagement.beans.OffenderCaseConditions;
import net.syscon.s4.inst.casemanagement.beans.OffenderCriminogenicNeeds;


public interface CasePlansT3Repository {

	Integer offenderGriminogenicNeedsCount(Long OffenderBookId, Long lVCpIdO);

	List<OffenderCriminogenicNeeds> getOffendercriminogenicNeeds(Long OffenderBookId, Long lVCpIdO);

	Integer crimNeedIdNextval();

	Integer offenderActionPlansCount(Long lVCpIdO);
	
	List<OffenderActionPlans> getOffenderActionPlans(Long vCnId);

	Integer offenderCaseConditionsCount(Long OffenderBookId, Long lVCpIdO);

	List<OffenderCaseConditions> getOffenderCaseConditions(Long OffenderBookId, Long lVCpIdO);

	Integer caseCondsDdCur();

	Integer insertOffenderCaseConditions(OffenderCaseConditions offenderCaseConditions, CasePlans casePlans,
			Integer lvOffCaseCondId);

	Integer insertOffenderActionPlans(OffenderActionPlans offenderActionPlans, Integer lvOffCaseCondId);

	Integer insertOffenderCriminogenicNeeds(OffenderCriminogenicNeeds offenderCriminogenicNeeds, CasePlans casePlans,
			Integer lvOffCaseCondId);

	Integer insertOffenderActionPlans2(OffenderActionPlans offenderActionPlans, Integer lvOffCaseCondId);
}


