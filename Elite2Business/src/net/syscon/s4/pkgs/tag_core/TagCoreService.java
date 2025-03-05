package net.syscon.s4.pkgs.tag_core;

import net.syscon.s4.im.beans.Offenders;

public interface TagCoreService {

	Offenders getOffDetails(final Long offenderId);

	String addString(String pMainString, String pSubString);

	String removeString(String pMainString, String pSubString);
}
