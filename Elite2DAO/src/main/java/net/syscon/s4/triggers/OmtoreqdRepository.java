package net.syscon.s4.triggers;

import net.syscon.s4.inst.legals.au.OffenderRequests;

public interface OmtoreqdRepository {
	OffenderRequests getOffenderRequests(OffenderRequests OffenderRequests);

	Integer save(OffenderRequests offenderRequests);

}
