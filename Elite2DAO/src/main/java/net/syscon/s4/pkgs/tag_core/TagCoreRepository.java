package net.syscon.s4.pkgs.tag_core;

import net.syscon.s4.im.beans.Offenders;

public interface TagCoreRepository {

	Offenders getOffDetails(final Long offenderId);
	
	Integer removeStringInstr(String pMainString, String pSubString);
	
	String removeStringSubstr(String pMainString,Integer vPos);
	
	String stringSubstringOne(String pMainString,Integer vPos,Integer vLength);
	
}
