package net.syscon.s4.pkgs.payroll;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface PayrollRepository {

	List<BigDecimal> getWorkAssignmentIdList(final BigDecimal offId, final String csldId);

	Long getWorkAssignmentStatusesSeq(BigDecimal wrkAsgnId);

	Integer workAssignmentStatusesInsert(BigDecimal wrkAsgnId, BigDecimal nextStsSeq, Date pMovementDate,
			final String userName);

	Integer updateWorkAssignments(BigDecimal offId, String csldId, Date pMovementDate, final String userName);

}