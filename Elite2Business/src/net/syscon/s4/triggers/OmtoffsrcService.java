package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.im.beans.Offenders;

public interface OmtoffsrcService {
	
	List<Offenders> omtoffsrc(final List<Offenders> offenderList, final String operation);
}
