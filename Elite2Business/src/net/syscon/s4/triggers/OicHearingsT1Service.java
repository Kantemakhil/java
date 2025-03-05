package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.im.incidentsoic.beans.OicHearings;

public interface OicHearingsT1Service {
	List<OicHearings> oicHearingsT1Tgr(List<OicHearings> oicHearingsList);
}
