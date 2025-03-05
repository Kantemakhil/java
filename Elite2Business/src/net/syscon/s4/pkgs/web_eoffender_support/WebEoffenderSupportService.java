package net.syscon.s4.pkgs.web_eoffender_support;

import net.syscon.s4.eoffender.beans.EoffenderDetails;

public interface WebEoffenderSupportService {

	EoffenderDetails getUserFromKey(final String keyLogin);
}