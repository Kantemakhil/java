package net.syscon.s4.triggers.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OffenderExternalMovementsT5Repository;
@Repository
public class OffenderExternalMovementsT5RepositoryImpl extends RepositoryBase implements OffenderExternalMovementsT5Repository{

	private static Logger logger = LogManager.getLogger(OffenderExternalMovementsT5RepositoryImpl.class.getName());
	
	@Override
		public OffenderExternalMovements getOffenderExternalMovements(final Long pOffenderBookId,final Long movementSeq) {
			final String sql = getQuery("GET_OFFENDER_EXTERNAL_MOVEMENTS_DATA");
			OffenderExternalMovements resp = new OffenderExternalMovements();
			try {
				if(pOffenderBookId != null && movementSeq != null) {
				resp = namedParameterJdbcTemplate.queryForObject(sql,
						createParams("OFFENDER_BOOK_ID", pOffenderBookId,"MOVEMENT_SEQ",movementSeq),
						new BeanPropertyRowMapper<OffenderExternalMovements>(OffenderExternalMovements.class));
				}
			} catch (Exception e) {
				logger.error("getOffenderExternalMovements :" + e);
			}
			return resp;	
		}
		
	@Override
	public String getY(final Long pOffenderBookId) {
		final String sql = getQuery("GET_Y");
		String description = null;
		try {
			description = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_offender_book_id", pOffenderBookId),
					String.class);
		} catch (Exception e) {
			logger.error("getY :" + e);
		}
		return description;	
	}

	
	@Override
	public Integer getTrgEventId() {
		final String sql = getQuery("GET_TRG_EVENT_ID");
		Integer value = null;
		try {
			value = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
					Integer.class);
		} catch (Exception e) {
			logger.error("getTrgEventId :" + e);
		}
		return value;
	}
	
	
	@Override
	public Integer insertOffenderAssocPEventNotifs(final Integer lTrgEventId, final Date lMoveDate,
			final Long lOffenderBookId,  String value,String createUserId) {
		final String sql = getQuery("INSERT_OFFENDER_ASSOC_P_EVENT_NOTIFS");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("l_trg_event_id", lTrgEventId);
		inParamMap.put("l_move_date", lMoveDate);
		inParamMap.put("l_offender_book_id", lOffenderBookId);
		
		String[] values=value.split("-");
		if(values!=null && values.length>0) {
			value=values[0];
			inParamMap.put("input_value", value);
			inParamMap.put("createUserId", createUserId);
		}
		return namedParameterJdbcTemplate.update(sql, inParamMap);
	}

}
