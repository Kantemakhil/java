package net.syscon.s4.triggers.impl;

import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.triggers.OffenderExternalMovementsT1Repository;

@Repository
public class OffenderExternalMovementsT1RepositoryImpl extends RepositoryBase
		implements OffenderExternalMovementsT1Repository {

	@Override
	public Integer save(final MovementReasons oldRef, final MovementReasons newRef, final Long offenderBookId) {
		final String sql = getQuery("OFFENDER_EXTERNAL_MOVEMENTS_T1_INSERTING");
		return namedParameterJdbcTemplate.update(sql,
				createParams("movementType", newRef.getMovementType(), "modifyUserId", newRef.getModifyUserId(),
						"movementReasonCode", newRef.getMovementReasonCode(), "offenderBookId", offenderBookId));

	}

	@Override
	public Integer update(final MovementReasons oldRef, final MovementReasons newRef, final Long offenderBookId) {
		final String sql = getQuery("OFFENDER_EXTERNAL_MOVEMENTS_T1_UPDATING");
		return namedParameterJdbcTemplate.update(sql,
				createParams("newMovementType", newRef.getMovementType(), "oldMovementType", oldRef.getMovementType(),
						"newMovementReasonCode", newRef.getMovementReasonCode(), "oldMovementReasonCode",
						oldRef.getMovementReasonCode(), "oldOffenderBookId", offenderBookId, "modifyUserId",
						newRef.getModifyUserId()));
	}

	
	
}
