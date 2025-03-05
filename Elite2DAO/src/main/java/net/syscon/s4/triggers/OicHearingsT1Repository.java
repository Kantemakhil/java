package net.syscon.s4.triggers;

import net.syscon.s4.im.incidentsoic.beans.OicHearings;

public interface OicHearingsT1Repository {
	OicHearings getOicHearings(OicHearings oicHearings);

	Integer lEventId();
}
