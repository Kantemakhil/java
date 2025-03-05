package net.syscon.s4.pkgs.ocdclist.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.legalscreens.bean.OcdclistCourtListQuery;
import net.syscon.s4.pkgs.ocdclist.OcdclistPkgRepository;

@Repository
public class OcdclistPkgRepositoryImpl extends RepositoryBase implements OcdclistPkgRepository {

	private static Logger logger = LogManager.getLogger(OcdclistPkgRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> couEveMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION",            			   new FieldMapper("description"))
			.put("LAST_NAME",              	           new FieldMapper("pLastName"))
			.put("FIRST_NAME",               		   new FieldMapper("pFirstName"))
			.put("MIDDLE_NAME",                        new FieldMapper("pMiddleName"))
			.put("BIRTH_DATE", 						   new FieldMapper("pBirthDate"))
			.put("OFFENDER_ID_DISPLAY", 			   new FieldMapper("pOffDisplay"))
			.put("START_TIME", 						   new FieldMapper("pStartTime"))
			.put("COURT_EVENT_TYPE", 				   new FieldMapper("pCourtEventType"))
			.put("OFFENDER_BOOK_ID", 				   new FieldMapper("pOffBkgId"))
			.put("EVENT_DATE", 						   new FieldMapper("pEventDate"))
			.put("CASE_INFO_PREFIX", 				   new FieldMapper("pCaseInfoprefix"))
			.put("CASE_INFO_NUMBER",                   new FieldMapper("pCaseInfoNumber"))
			.put("COURT_EVENT_TYPE_DESC",              new FieldMapper("pCourtEventTypeDesc"))
			.put("AGY_LOC_ID",                         new FieldMapper("pAgyLocId"))
			.put("EVENT_ID",                           new FieldMapper("pEventId"))  
			.put("CASE_ID",                            new FieldMapper("pCaseId"))
			.put("CHECK_SUM",                          new FieldMapper("pCheckSum"))
			.put("eventStatus",                          new FieldMapper("pEventStatus"))
			.build();

	@Override
	public List<OcdclistCourtListQuery> courtListDataSelect(final OcdclistCourtListQuery courListQuery,
			final String orderBy) {

		final String sql = getQuery("COURT_LIST_DATA_SELECT");
		List<OcdclistCourtListQuery> couEveList = new ArrayList<OcdclistCourtListQuery>();
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer(sql);
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		sqlQuery.append(" vce.event_date = :pCourtDate AND vce.agy_loc_id = :pAgyLocId AND vce.booking_active_flag !=  'N' ");
		inParameterSource.addValue("pCourtDate", courListQuery.getpCourtDate());
		inParameterSource.addValue("pAgyLocId", courListQuery.getpAgyLocId());
		if (courListQuery.getpLastName() != null) {
			sqlQuery.append(" and vce.last_name like :pLastName");
			inParameterSource.addValue("pLastName", courListQuery.getpLastName());
		}
		if (courListQuery.getpFirstName() != null) {
			sqlQuery.append(" and vce.first_name like :pFirstName");
			inParameterSource.addValue("pFirstName", courListQuery.getpFirstName());
		}
		if (courListQuery.getpMiddleName() != null) {
			sqlQuery.append(" and vce.middle_name = :pMiddleName");
			inParameterSource.addValue("pMiddleName", courListQuery.getpMiddleName());
		}
		if (courListQuery.getpCourtEventType() != null && courListQuery.getpCourtEventTypeDesc() != null) {
			sqlQuery.append(" and vce.court_event_type =  :pCourtEventType");
			inParameterSource.addValue("pCourtEventType", courListQuery.getpCourtEventType());
		}
		preparedSql = sqlQuery.toString().trim();

		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" " + orderBy);

		final RowMapper<OcdclistCourtListQuery> couEveRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OcdclistCourtListQuery.class, couEveMapping);
		try {
			couEveList = namedParameterJdbcTemplate.query(preparedSql, inParameterSource, couEveRowMapper);
		} catch (Exception e) {
			logger.error("courtListDataSelect :", e);
		}
		return couEveList;
	}

}
