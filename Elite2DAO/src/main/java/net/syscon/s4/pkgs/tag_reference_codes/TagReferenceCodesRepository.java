package net.syscon.s4.pkgs.tag_reference_codes;

import net.syscon.s4.common.beans.ReferenceCodes;

public interface TagReferenceCodesRepository {
	ReferenceCodes domainCursor(final String domain, final String refCode);

	String getDescCode(final String domain, final String code);
	
	public String domainCursorToString(final String domain, final String refCode);
	
    String defaultDomain(final String domain);
}