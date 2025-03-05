package net.syscon.s4.cm.searchassign.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.searchassign.OsinamesRepository;
import net.syscon.s4.common.beans.VNameSearch2;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * Class OsinamesRepositoryImpl
 */
@Repository
public class OsinamesRepositoryImpl extends RepositoryBase implements
		OsinamesRepository {

	private final Map<String, FieldMapper> vNameSearch2Mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("SUFFIX", new FieldMapper("suffix"))
			.put("LIV_UNIT_DESC", new FieldMapper("livUnitDesc"))
			.put("STAFF_FIRST_NAME", new FieldMapper("staffFirstName"))
			.put("DISCLOSURE_FLAG", new FieldMapper("disclosureFlag"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("CREATE_AGY_LOC_ID", new FieldMapper("createAgyLocId"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId"))
			.put("COMMUNITY_STATUS", new FieldMapper("communityStatus"))
			.put("MIDDLE_NAME", new FieldMapper("middleName"))
			.put("LIVING_UNIT_DESCRIPTION",
					new FieldMapper("livingUnitDescription"))
			.put("IN_OUT_STATUS", new FieldMapper("inOutStatus"))
			.put("BOOKING_CREATED_DATE", new FieldMapper("bookingCreatedDate"))
			.put("STAFF_LAST_NAME", new FieldMapper("staffLastName"))
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("LAST_NAME", new FieldMapper("lastName"))
			.put("LOCATION_CODE", new FieldMapper("locationCode"))
			.put("INTAKE_AGY_LOC_ID", new FieldMapper("intakeAgyLocId"))
			.put("COMMUNITY_AGY_LOC_ID", new FieldMapper("communityAgyLocId"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("BOOKING_BEGIN_DATE", new FieldMapper("bookingBeginDate"))
			.put("AGENCY_IML_ID", new FieldMapper("agencyImlId"))
			.put("AGY_LOC_NAME", new FieldMapper("agyLocName"))
			.put("STATUS_DISPLAY", new FieldMapper("statusDisplay"))
			.put("ALIAS_OFFENDER_ID", new FieldMapper("aliasOffenderId"))
			.put("BOOKING_TYPE", new FieldMapper("bookingType"))
			.put("BOOKING_END_DATE", new FieldMapper("bookingEndDate"))
			.put("COMMUNITY_ACTIVE_FLAG",
					new FieldMapper("communityActiveFlag"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("BOOKING_NO", new FieldMapper("bookingNo"))
			.put("ASSIGNED_STAFF_ID", new FieldMapper("assignedStaffId"))
			.put("AGY_LOC_TYPE", new FieldMapper("agyLocType"))
			.put("BIRTH_DATE", new FieldMapper("birthDate"))
			.put("FIRST_NAME", new FieldMapper("firstName"))
			.put("BOOKING_STATUS", new FieldMapper("bookingStatus"))
			.put("CREATE_INTAKE_AGY_LOC_ID", new FieldMapper("createIntakeAgyLocId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.build();

	/**
	 * Creates new OsinamesRepositoryImpl class Object
	 */
	public OsinamesRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VNameSearch2
	 *
	 * @return List<VNameSearch2>
	 *
	 * 
	 */
	public List<VNameSearch2> nameSrchExecuteQuery(
			final VNameSearch2 objSearchDao) {
		final String sql = getQuery("OSINAMES_NAMESRCH_FIND_V_NAME_SEARCH2");
		String preparedSql = null;
		String preSqlQuery = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if(objSearchDao.getCreateUserId()!=null) {				
				inParameterSource.addValue("userId", objSearchDao
						.getCreateUserId());
			}
			
			if (objSearchDao.getLastName() != null
					&& !objSearchDao.getLastName().trim().equals("")) {
				sqlQuery.append("regexp_replace(upper(LAST_NAME),'[- \\,''\"]','','g') LIKE regexp_replace(upper(:lastName),'[- \\,''\"]','','g')" + " and ");
				inParameterSource.addValue("lastName", objSearchDao
						.getLastName().trim() + "%");
			}
			if (objSearchDao != null && objSearchDao.getFirstName() != null
					&& !objSearchDao.getFirstName().trim().equals("")) {
				sqlQuery.append("regexp_replace(upper(FIRST_NAME),'[- \\,''\"]','','g') LIKE regexp_replace(upper(:firstName),'[- \\,''\"]','','g')" + " and ");
				inParameterSource.addValue("firstName", objSearchDao
						.getFirstName().trim() + "%");
			} else {
				sqlQuery.append(" FIRST_NAME is not null " + " and ");
			}
			if (objSearchDao != null
					&& objSearchDao.getOffenderIdDisplay() != null
					&& !objSearchDao.getOffenderIdDisplay().trim().equals("")) {
				sqlQuery.append("LTRIM(OFFENDER_ID_DISPLAY::text, '0') like LTRIM(:offenderIdDispaly::text,'0')" + " and ");
				inParameterSource.addValue("offenderIdDispaly", objSearchDao
						.getOffenderIdDisplay().trim());
			}
			if (objSearchDao != null && objSearchDao.getAgyLocId() != null
					&& !objSearchDao.getAgyLocId().trim().equals("")) {
				sqlQuery.append(" AGY_LOC_ID  =:agyLocId " + " and");
				inParameterSource.addValue("agyLocId", objSearchDao
						.getAgyLocId().trim());
			}
			if (objSearchDao != null && objSearchDao.getActiveFlag() != null
					&& !objSearchDao.getActiveFlag().trim().equals("")) {
				sqlQuery.append(" ACTIVE_FLAG   = :activeFalg" + " and");
				inParameterSource.addValue("activeFalg", objSearchDao
						.getActiveFlag().trim());
			}
			if (objSearchDao != null
					&& objSearchDao.getLivingUnitDescription() != null
					&& !objSearchDao.getLivingUnitDescription().trim()
							.equals("")) {
				sqlQuery.append(" LIVING_UNIT_DESCRIPTION   = :livingUnitDescription"
						+ " and");
				inParameterSource.addValue("livingUnitDescription",
						objSearchDao.getLivingUnitDescription().trim());
			}
			if (objSearchDao != null
					&& objSearchDao.getCommunityAgyLocId() != null
					&& !objSearchDao.getCommunityAgyLocId().trim().equals("")) {
				sqlQuery.append(" COMMUNITY_AGY_LOC_ID   = :communityAgyLocId"
						+ " and");
				inParameterSource.addValue("communityAgyLocId", objSearchDao
						.getCommunityAgyLocId().trim());
			}
			if (objSearchDao != null
					&& objSearchDao.getCommunityActiveFlag() != null
					&& !objSearchDao.getCommunityActiveFlag().trim().equals("")) {
				sqlQuery.append(" COMMUNITY_ACTIVE_FLAG   = :communityActiveFlag"
						+ " and");
				inParameterSource.addValue("communityActiveFlag", objSearchDao
						.getCommunityActiveFlag().trim());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSqlQuery = preparedSql.concat(" ORDER BY LAST_NAME, FIRST_NAME ");
		final RowMapper<VNameSearch2> VNameSearch2RowMapper = Row2BeanRowMapper
				.makeMapping(sql, VNameSearch2.class, vNameSearch2Mapping);
		List<VNameSearch2> returnList = new ArrayList<VNameSearch2>();
		returnList = namedParameterJdbcTemplate.query(preSqlQuery,
				inParameterSource, VNameSearch2RowMapper);
		return returnList;
	}

	@Override
	public String getDescription(String agyLocId) {
		final String sql = getQuery("OSINAMES_NAMESRCH_AGENCY_DESCR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("agyLocId", agyLocId), String.class);
		} catch (Exception e) {
			return null;
		}
	}

}
