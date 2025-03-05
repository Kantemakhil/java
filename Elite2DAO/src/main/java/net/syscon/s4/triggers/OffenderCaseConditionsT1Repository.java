package net.syscon.s4.triggers;

import java.math.BigDecimal;
import java.util.Map;

import net.syscon.s4.inst.casemanagement.beans.OffenderCaseConditions;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;

public interface OffenderCaseConditionsT1Repository {

	OffenderSentConditions getOffenderSentConditions(Long offenderSentConditionId);

	int getCommunityConditions(String commConditionType, String commConditionCode, String categoryType);

	Map getCasePlans(BigDecimal offenderBookId);

	Long getOffCaseConIid();

	Integer insertOffenderCaseConditions(Long vOffCcId, Long vObi, Long vCaseplanId,
			OffenderSentConditions offenderSentConditions);

	OffenderCaseConditions getOffenderCaseConditions(Long offenderSentConditionId);

	Integer updateOffenderCaseConditions(Long vOffCcId, OffenderSentConditions offenderSentConditions);

	

}
