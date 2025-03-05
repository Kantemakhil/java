package net.syscon.s4.pkgs.omuavbed;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OmuavbedLivUnitsQuery;

public interface OmuavbedPkgService {
	List<OmuavbedLivUnitsQuery> livUnitsQuery(final OmuavbedLivUnitsQuery objSearchDao);

	String addMsg(final String pMsg, final String pAddMsg);

	String formatOffList(final List<Offenders> lvOffInfo);

	String formatOffRec(final Offenders lvOffRec);

	Map<String, Object> getConflictWarning(final BigDecimal offenderBookId, final BigDecimal offenderId,
			final BigDecimal livingUnitId, final String agyLocId);

	LivingUnits getLivUnitInfo(final BigDecimal offenderBookId, final BigDecimal offId, final BigDecimal livingUnitId);

	List<Offenders> getOccupants(final BigDecimal livingUnitId);

	Map<String, Object> getConflictWarningNoCs(final Long offenderBookId, final Long offenderId,
			final BigDecimal livingUnitId);

}