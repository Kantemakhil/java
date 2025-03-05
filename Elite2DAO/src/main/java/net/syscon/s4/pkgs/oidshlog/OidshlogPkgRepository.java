package net.syscon.s4.pkgs.oidshlog;

import java.math.BigDecimal;
import java.sql.Clob;

import net.syscon.s4.inst.shiftlogs.bean.AgencyShiftLogs;

public interface OidshlogPkgRepository {

	AgencyShiftLogs checkClobState(final Long shiftLogSeq);

	Integer updateAencyShiftLogs(final Long shiftLogSeq, final String userName);

	AgencyShiftLogs selectAgyShiftLogs(final Long shiftLogSeq);

	Clob getObservText(final BigDecimal pShiftLogSeq);
}