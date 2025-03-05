package net.syscon.s4.inst.securitythreatgroups.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VStgMembershipInquiry;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.securitythreatgroups.OiistgmiRepository;

/**
 * Class OiistgmiRepositoryImpl
 * 
 */
@Repository
public class OiistgmiRepositoryImpl extends RepositoryBase implements OiistgmiRepository {

	/**
	 * Creates new OiistgmiRepositoryImpl class Object
	 */
	public OiistgmiRepositoryImpl() {
	}

	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_VALUE", new FieldMapper("profileValue"))
			.build();
	private final Map<String, FieldMapper> vStgMembershipInquiryMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("FIRST_NAME", new FieldMapper("firstName"))
			.put("LAST_NAME", new FieldMapper("lastName"))
			.put("ACTION_CODE", new FieldMapper("actionCode"))
			.put("STATUS_REASON", new FieldMapper("statusReason"))
			.put("STG_ID", new FieldMapper("stgId"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("STG_AFF_ACTIVE_FLAG", new FieldMapper("stgAffActiveFlag"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LAN", new FieldMapper("lan"))
			.put("REPORT_CODE", new FieldMapper("reportCode"))
			.put("DECOD", new FieldMapper("decod"))
			.put("'PORTRAIT')", new FieldMapper("portrait"))
			.put("'LANDSCAPE'", new FieldMapper("landscape"))
			.build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VStgMembershipInquiry
	 *
	 * @return List<VStgMembershipInquiry>
	 *
	 * @throws SQLException
	 */
	public List<VStgMembershipInquiry> vStgMembershipInquiryExecuteQuery(final VStgMembershipInquiry objSearchDao) {
		final String sql = getQuery("OIISTGMI_VSTGMEMBERSHIPINQUIRY_FIND_V_STG_MEMBERSHIP_INQUIRY");
		final RowMapper<VStgMembershipInquiry> VStgMembershipInquiryRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VStgMembershipInquiry.class, vStgMembershipInquiryMapping);
		final ArrayList<VStgMembershipInquiry> returnList = (ArrayList<VStgMembershipInquiry>) namedParameterJdbcTemplate
				.query(sql, createParams("STG_ID", objSearchDao.getStgId()), VStgMembershipInquiryRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oiistgmiWhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public List<SystemProfiles> oiistgmiWhenNewFormInstance(final SystemProfiles paramBean) {
		final String sql = getQuery("OIISTGMI_OIISTGMI_WHENNEWFORMINSTANCE");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobals
	 *
	 * @param params
	 *
	 */
	public List<OmsModules> createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OIISTGMI_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		final ArrayList<OmsModules> returnList = (ArrayList<OmsModules>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getStgGroupDescription
	 *
	 * @param params
	 *
	 */
	public String getStgGroupDescription(final SecurityThreatGroups paramBean) {
		final String sql = getQuery("OIISTGMI_GET_STG_GROUP_DESCRIPTION");
		List<String> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.queryForList(sql, createParams("STGID", paramBean.getStgId()),
				String.class);
		if (returnList != null && !returnList.isEmpty()) {
			return returnList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VStgMembershipInquiry
	 *
	 * @return List<VStgMembershipInquiry>
	 *
	 * @throws SQLException
	 */
	public List<VStgMembershipInquiry> vStgMembershipInqExecuteQuery(final VStgMembershipInquiry searchBean) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIISTGMI_FIND_V_STG_MEMBERSHIP_INQUIRY"), vStgMembershipInquiryMapping)
				.build();
		final RowMapper<VStgMembershipInquiry> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				VStgMembershipInquiry.class, vStgMembershipInquiryMapping);
		String preparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (searchBean != null) {
			sqlQuery.append(" WHERE ");
			if (searchBean.getOffenderIdDisplay() != null) {
				sqlQuery.append("OFFENDER_ID_DISPLAY = :OFFENDER_ID_DISPLAY" + " AND ");
				inParameterSource.addValue("OFFENDER_ID_DISPLAY", searchBean.getOffenderIdDisplay());
			}
			if (searchBean.getStgId() != null) {
				sqlQuery.append("STG_ID = :STG_ID" + " AND ");
				inParameterSource.addValue("STG_ID", searchBean.getStgId());
			}
			if (searchBean.getLastName() != null && searchBean.getLastName().trim().length() > 0) {
				sqlQuery.append("LAST_NAME =  :LAST_NAME" + " AND ");
				inParameterSource.addValue("LAST_NAME", searchBean.getLastName().trim());
			}
			if (searchBean.getFirstName() != null && searchBean.getFirstName().trim().length() > 0) {
				sqlQuery.append("FIRST_NAME = :FIRST_NAME" + " AND ");
				inParameterSource.addValue("FIRST_NAME", searchBean.getFirstName().trim());
			}
			if (searchBean.getActionCode() != null && searchBean.getActionCode().trim().length() > 0) {
				sqlQuery.append("ACTION_CODE = :ACTION_CODE" + " AND ");
				inParameterSource.addValue("ACTION_CODE", searchBean.getActionCode().trim());
			}
			if (searchBean.getDescription() != null && searchBean.getDescription().trim().length() > 0) {
				sqlQuery.append("DESCRIPTION = :DESCRIPTION" + " AND ");
				inParameterSource.addValue("DESCRIPTION", searchBean.getDescription().trim());
			}
			if ("Y".equals(searchBean.getStgAffActiveFlag())) {
				sqlQuery.append("STG_AFF_ACTIVE_FLAG = :STG_AFF_ACTIVE_FLAG" + " AND ");
				inParameterSource.addValue("STG_AFF_ACTIVE_FLAG", searchBean.getStgAffActiveFlag());
				if (searchBean.getActiveFlag() != null) {
					sqlQuery.append("ACTIVE_FLAG = :ACTIVE_FLAG" + " AND ");
					inParameterSource.addValue("ACTIVE_FLAG", searchBean.getActiveFlag());
				}
				if (!"Y".equals(searchBean.getActiveFlag())) {
					sqlQuery.append(
							" V_STG_MEMBERSHIP_INQUIRY.OFFENDER_BOOK_ID = (SELECT MAX(OFFENDER_BOOK_ID)  FROM OFFENDER_BOOKINGS OB  WHERE V_STG_MEMBERSHIP_INQUIRY.ROOT_OFFENDER_ID = OB.ROOT_OFFENDER_ID) ");
				}
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		List<VStgMembershipInquiry> returnList = namedParameterJdbcTemplate.query(preparedSql, inParameterSource,
				rowMapper);
		return returnList;
	}
}
