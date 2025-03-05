package net.syscon.s4.pkgs.payroll;

import java.math.BigDecimal;
import java.util.Date;

public interface PayrollService {

	void updateWorkAsgnStatuses(final BigDecimal offId, final String csldId, final Date pMovementDate,
			final String userName);

}