package net.syscon.s4.triggers;

import net.syscon.s4.im.beans.OffenderSentConditions;

public interface OffenderSentConditionsT1Repository {
	OffenderSentConditions getOffenderSentConditions(OffenderSentConditions newObj);

	Integer insert(OffenderSentConditions newObj);
}
