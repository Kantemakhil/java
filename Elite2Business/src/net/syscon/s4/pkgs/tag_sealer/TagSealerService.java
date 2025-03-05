package net.syscon.s4.pkgs.tag_sealer;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.im.beans.Offenders;

public interface TagSealerService {
	void sealOffender(BigDecimal pOffenderId, String userName) throws Exception;
	void lockBookingResources(BigDecimal pOffBookId);
	List<Offenders> gTabs(String keyCol);
	List<OffenderBookings> buildLockSql(String pTabName,String pSecondName,String pExtraW,BigDecimal offenderValue);
	List<Offenders> gRid(BigDecimal pOffId);
	void lockResources(BigDecimal pOffenderId);
	List<Offenders> gOid(BigDecimal pOffId);
	Integer buildSql(String pTabName,String pSecondName,String pExtraW,BigDecimal offenderValue,String sealValue,String userName);
	List<OffenderBookings> gBid(BigDecimal pOffId);
	BigDecimal getIndRid(BigDecimal pOb);
	void unsealOffender(BigDecimal pOffenderId, String userName) throws Exception;
	void sealOffenderBooking(BigDecimal pOffenderBookId, String userName) throws Exception;
	void unsealOffenderBooking(BigDecimal pOffenderBookId,String userName) throws Exception;
}
