package net.syscon.s4.pkgs.oumpurge;

import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.im.beans.Offenders;

public interface OumpurgePkgRepository {

	Integer getCount(final String query);
	List<Offenders>  getOffenderId(Long pRootOffenderId);
	List<OffenderBookings>  getOffBookId(Long pOffId);
}