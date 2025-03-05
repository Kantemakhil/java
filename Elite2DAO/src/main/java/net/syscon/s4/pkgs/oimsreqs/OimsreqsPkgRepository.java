package net.syscon.s4.pkgs.oimsreqs;

import java.util.List;

import net.syscon.s4.common.beans.LegalUpdateReasons;

public interface OimsreqsPkgRepository {

	List<LegalUpdateReasons> getDescNdActive(final String updateReasonCode);
}
