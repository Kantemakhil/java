package net.syscon.s4.pkgs.ocdperso;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderVisitVisitors;

public interface OcdpersoPkgRepository {
	List<OffenderVisitVisitors> getfutureVisits(final Long pOffenderBookId, final Long pPersonId);

	Integer getCount(final BigDecimal pVisitId);

	Integer updateOffVisitVisitorsOne(final BigDecimal pVisitId, final Long pPersonId, final String userName);

	Integer updateOffenderVisitSecond(final BigDecimal pVisitId, final String userName);

	Integer updateOffVisitVisitorsThird(final BigDecimal pVisitId, final String userName);

	List<Persons> getNamesCur(final Long personId);

	Integer getNextAddressId();

	Integer InsertOpCopyOffAddr(final Integer lvAddressId, final BigDecimal pRootOffId, final Long pPersonId,
			final String userName);

	Addresses getAddressT1Object(BigDecimal pRootOffId);

	Addresses getAddressT3Object(Integer lvAddressId);

}