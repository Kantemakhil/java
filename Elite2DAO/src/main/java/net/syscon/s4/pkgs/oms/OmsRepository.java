package net.syscon.s4.pkgs.oms;

import net.syscon.s4.common.beans.SystemProfiles;

import java.util.List;

public interface OmsRepository{
	List<SystemProfiles> getResults(final String profiletype, final String profilecode);
}