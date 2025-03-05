package net.syscon.s4.pkgs.omuavbed;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OmuavbedLivUnitsQuery;

public interface OmuavbedPkgRepository {

	public Long offIdCursor(final Long long1);

	public Integer emptySearchCur();

	public List<OmuavbedLivUnitsQuery> pRefcursor(final OmuavbedLivUnitsQuery agencInterLoc, String orderBy);

	BigDecimal offIdSelectOperation(final BigDecimal offenderBookId);

	LivingUnits getLivUnitInfo(final BigDecimal offenderBookId,final BigDecimal offenderId,final BigDecimal livingUnitId);

	List<Offenders> getOccupants(final BigDecimal livingUnitId);

	BigDecimal getlvOffId(final BigDecimal offenderBookId);

	Integer reservedBedCur(final BigDecimal lvOffId,final BigDecimal livingUnitId);

	Long getOffenderId(final Long offenderBookId);

	Offenders getOffDetails(final Long lvOffenderId);

}