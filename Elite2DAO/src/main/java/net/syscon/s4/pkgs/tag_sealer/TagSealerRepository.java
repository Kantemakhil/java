package net.syscon.s4.pkgs.tag_sealer;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.im.beans.Offenders;

public interface TagSealerRepository {
	List<Offenders> gTabs(String keyCol,String vpdSsOwner);
	List<Offenders> gRid(BigDecimal pOffId);
	List<Offenders> gOid(BigDecimal pOffId);
	List<OffenderBookings> gBid(BigDecimal pOffId);
	BigDecimal getIndRid(BigDecimal pOb);
	Integer buildSql(String sqlQuery);
	List <OffenderBookings> buildLockSql(String sqlQuery);
	String getDataType(String tableName,String coloumnName);

}
