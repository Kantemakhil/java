package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.inst.casemanagement.beans.OffenderCaseConditions;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;
import net.syscon.s4.triggers.OffenderCaseConditionsT1Service;
@Service
public class OffenderCaseConditionsT1ServiceImpl implements OffenderCaseConditionsT1Service {
	@Autowired
	private OffenderCaseConditionsT1RepositoryImpl offenderCaseConditionsT1Repository;
	@Transactional
	@Override
	public void offenderCaseConditionsT1(OffenderSentConditions newOffenderSentConditions) {

		if(Objects.nonNull(newOffenderSentConditions)) { 
			OffenderSentConditions oldOffenderSentConditions = offenderCaseConditionsT1Repository.getOffenderSentConditions(newOffenderSentConditions.getOffenderSentConditionId());
			OffenderCaseConditions oldObj = offenderCaseConditionsT1Repository.getOffenderCaseConditions(newOffenderSentConditions.getOffenderSentConditionId());
			if(oldOffenderSentConditions != null) {
				if(Objects.isNull(newOffenderSentConditions.getSealFlag()) || StringUtils.equals(oldOffenderSentConditions.getSealFlag(), newOffenderSentConditions.getSealFlag())) {
					if(oldObj!=null) {
						offenderCaseConditionsT1Repository.updateOffenderCaseConditions(oldObj.getOffCaseCondId(), oldOffenderSentConditions);
					} else {
						int lvCnt = offenderCaseConditionsT1Repository.getCommunityConditions(newOffenderSentConditions.getCommConditionType(),
								newOffenderSentConditions.getCommConditionCode(), newOffenderSentConditions.getCategoryType());
						if(lvCnt!=0) {
							Map<String, Long> resultMap = offenderCaseConditionsT1Repository.getCasePlans(BigDecimal.valueOf(newOffenderSentConditions.getOffenderBookId()));
							if(resultMap!=null && !resultMap.isEmpty()) {
								Long vObi =resultMap.get("offender_book_id");
								Long vCaseplanId=resultMap.get("case_plan_id");
								if(Objects.nonNull(vCaseplanId)) {
									Long vOffCcId=offenderCaseConditionsT1Repository.getOffCaseConIid();
									offenderCaseConditionsT1Repository.insertOffenderCaseConditions(vOffCcId, vObi, vCaseplanId, oldOffenderSentConditions);
								}

							}
						}
					}
				}
			}
		}
	}

}
