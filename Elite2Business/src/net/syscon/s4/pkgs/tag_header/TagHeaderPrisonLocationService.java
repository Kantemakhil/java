package net.syscon.s4.pkgs.tag_header;

import java.math.BigDecimal;

public interface TagHeaderPrisonLocationService {
	
	String getHeaderPrisonLocation(String activeFlag, String commActiveFlag, String commAgyLocId, String agyLocId, BigDecimal livingUnitId, BigDecimal agyImlId, Long offenderBookId, String currentUser);

}
