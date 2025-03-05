package net.syscon.s4.pkgs.oidbsiap;

import java.math.BigDecimal;

public interface OidbsiapPkgRepository {
	String getDesc(final BigDecimal offBkgId,String userName);
}