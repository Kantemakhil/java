package net.syscon.s4.cm.programsservices.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.programsservices.OcumultiRepository;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;

/**
 * Class OcumultiRepositoryImpl
 */
@Repository
public class OcumultiRepositoryImpl extends RepositoryBase implements OcumultiRepository {

	/**
	 * Creates new OcumultiRepositoryImpl class Object
	 */
	public OcumultiRepositoryImpl() {
	}

	private final Map<String, FieldMapper> vOffenderAllSchedulesMapping = new ImmutableMap.Builder<String, FieldMapper>()

			.put("ESCORT_CODE", new FieldMapper("escortCode"))
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("EVENT_DATE", new FieldMapper("eventDate"))
			.put("EVENT_OUTCOME", new FieldMapper("eventOutcome"))
			.put("EVENT_OUTCOME_DESC", new FieldMapper("eventOutcomeDesc"))
			.put("EVENT_TYPE_DESC", new FieldMapper("eventTypeDesc"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("EVENT_TYPE", new FieldMapper("eventType"))
			.put("END_TIME", new FieldMapper("endTime"))
			.put("START_TIME", new FieldMapper("startTime"))
			.put("TO_LOC_DESC", new FieldMapper("toLocDesc"))
			.put("EVENT_SUB_TYPE_DESC", new FieldMapper("eventSubTypeDesc"))
			.put("UNEXCUSED_ABSENCE_FLAG", new FieldMapper("unexcusedAbsenceFlag")).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VOffenderAllSchedules
	 *
	 * @return List<VOffenderAllSchedules>
	 *
	 */
	public List<VOffenderAllSchedules> offBlockExecuteQuery(final VOffenderAllSchedules objSearchDao) {
		final String sql = getQuery("OCUMULTI_OFFBLOCK_FIND_V_OFFENDER_ALL_SCHEDULES");
		final RowMapper<VOffenderAllSchedules> VOffenderAllSchedulesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderAllSchedules.class, vOffenderAllSchedulesMapping);
		final ArrayList<VOffenderAllSchedules> returnList = (ArrayList<VOffenderAllSchedules>) namedParameterJdbcTemplate
				.query(sql, createParams("offBookId", objSearchDao.getOffenderBookId(), "eventDate", objSearchDao.getEventDate(), "eventId",
						objSearchDao.getEventId()), VOffenderAllSchedulesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstVOffenderAllSchedules
	 *            List<VOffenderAllSchedules>
	 *
	 * @return List<Integer>
	 *
	 */ 
	public Integer offBlockInsertVOffenderAllSchedules(final List<VOffenderAllSchedules> lstVOffenderAllSchedules) {
		int insertCount = 0;
		final String sql = getQuery("OCUMULTI_OFFBLOCK_INSERT_V_OFFENDER_ALL_SCHEDULES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		for (int i = 0; i < returnArray.length; i++) {
			insertCount = insertCount++;
		}
		if (lstVOffenderAllSchedules.size() == insertCount) {
			return 1;
		} else {
			return 0;
		}

	}

}
