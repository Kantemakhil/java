package net.syscon.s4.cm.searchassign.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.searchassign.OcinamesRepository;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.VPimsNameSearch;

/**
 * Class OcinamesRepositoryImpl
 */
@Repository
public class OcinamesRepositoryImpl extends RepositoryBase implements OcinamesRepository {

	private final Map<String, FieldMapper> vPimsNameSearchMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PARENT_CODE", 				new FieldMapper("parentCode"))
			.put("ACTIVE_FLAG", 				new FieldMapper("activeFlag"))
			.put("BOOKING_BEGIN_DATE", 			new FieldMapper("bookingBeginDate"))
			.put("DOMAIN", 						new FieldMapper("domain"))
			.put("PARENT_DOMAIN", 				new FieldMapper("parentDomain"))
			.build();

	/**
	 * Creates new OcinamesRepositoryImpl class Object
	 */
	public OcinamesRepositoryImpl() {
		// OcinamesRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VPimsNameSearch
	 *
	 * @return List<VPimsNameSearch>
	 */
	public List<VPimsNameSearch> vNSearchExecuteQuery(final VPimsNameSearch objSearchDao) {
		final String sql = getQuery("OCINAMES_VNSEARCH_FIND_V_PIMS_NAME_SEARCH_FN");
		final RowMapper<VPimsNameSearch> vPimsNameSrchRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VPimsNameSearch.class, vPimsNameSearchMapping);
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
		}
		if (objSearchDao.getCreateUserId()!=null) {
			params.addValue("userId", objSearchDao.getCreateUserId());
		}
		if (objSearchDao != null && objSearchDao.getLastName() != null
				&& !objSearchDao.getLastName().trim().equals("")) {
			sqlQuery.append("LAST_NAME  like :lastName  " + " and");
			params.addValue("lastName", objSearchDao.getLastName().trim() + "%");
		}
		if (objSearchDao != null && objSearchDao.getFirstName() != null
				&& !objSearchDao.getFirstName().trim().equals("")) {
			sqlQuery.append("  FIRST_NAME  like :firstName " + " and");
			params.addValue("firstName", objSearchDao.getFirstName().trim() + "%");
		}
		if (objSearchDao != null && objSearchDao.getMiddleName() != null
				&& !objSearchDao.getMiddleName().trim().equals("")) {
			sqlQuery.append("  MIDDLE_NAME   = :middleName" + " and");
			params.addValue("middleName", objSearchDao.getMiddleName().trim());
		}
		if (objSearchDao != null && objSearchDao.getOffenderIdDisplay() != null
				&& !objSearchDao.getOffenderIdDisplay().trim().equals("")) {
			sqlQuery.append(" OFFENDER_ID_DISPLAY  =:offenderIdDispaly " + " and");
			params.addValue("offenderIdDispaly", objSearchDao.getOffenderIdDisplay().trim());
		}
		if (objSearchDao != null && objSearchDao.getBirthDate() != null
				&& !objSearchDao.getBirthDate().equals("")) {
			sqlQuery.append(" BIRTH_DATE  =:birthDate " + " and");
			params.addValue("birthDate", objSearchDao.getBirthDate());
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSqlQuery = preparedSql.concat(" ORDER BY LAST_NAME,FIRST_NAME,OFFENDER_ID_DISPLAY ");
		List<VPimsNameSearch> returnList = new ArrayList<VPimsNameSearch>();
		returnList = namedParameterJdbcTemplate.query(preSqlQuery, params, vPimsNameSrchRowMapper);
		return returnList;
	}

}
