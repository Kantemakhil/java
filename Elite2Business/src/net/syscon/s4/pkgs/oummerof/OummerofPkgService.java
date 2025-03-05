package net.syscon.s4.pkgs.oummerof;

import java.math.BigDecimal;

public interface OummerofPkgService {

	Integer checkIsBothBookingsActive(BigDecimal getpFromRootOffId, BigDecimal getpToRootOffId);

}
