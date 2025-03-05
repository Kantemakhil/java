package net.syscon.s4.pkgs.tag_header;

import java.math.BigDecimal;

public interface TagHeaderStatusService {

	String getHeaderStatus(String activeFlag, String commActiveFlag, String statusReason, String heaferStatusFLag, String commStatus, String inOutStatus, BigDecimal rootOffenderId, Long offenderBookId, String currentUser);

	String getCaseLoadType(String currentUser);
	
	String getBookingCount(BigDecimal rootOffenderId, Long offenderBookId, String caseLoadType);
	
	String getOmsSystemProfile(String profileType, String profileCode, int profileValueNo);
	
}
