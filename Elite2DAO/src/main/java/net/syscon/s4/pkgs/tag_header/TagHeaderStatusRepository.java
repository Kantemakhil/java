package net.syscon.s4.pkgs.tag_header;

import java.math.BigDecimal;

public interface TagHeaderStatusRepository {

	public String cnoteCur(Long offenderBookId, String caseNoteType);
	
	public String getTypeCur(String currentUser);
	
	public int getCountCur(BigDecimal rootOffenderId);
	
	public Long checkMaxBooking(BigDecimal rootOffenderId);
	
}
