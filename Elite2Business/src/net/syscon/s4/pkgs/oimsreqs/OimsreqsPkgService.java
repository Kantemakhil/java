package net.syscon.s4.pkgs.oimsreqs;

import net.syscon.s4.common.beans.SentenceUpdateReasons;

public interface OimsreqsPkgService {

	SentenceUpdateReasons getReasonDesc(final SentenceUpdateReasons senUpReasons);
}
