package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.inst.legals.au.OffenderRequests;

public interface OmtoreqdService {
	Integer save(List<OffenderRequests> offenderRequestsList);
}
