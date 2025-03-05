package net.syscon.s4.pkgs.oms_intake;

import net.syscon.s4.common.beans.SystemProfiles;

public interface OmsIntakeRepository {

	String refCur(final String pSupLevel, final String pDomain);

//	SystemProfiles sysCur();

}
