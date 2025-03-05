package net.syscon.s4.inst.programswithoutschedules.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderPrgObligationHty;
import net.syscon.s4.inst.programswithoutschedules.OcuupstaRepository;
import oracle.jdbc.OracleTypes;

/**
 * Class OcuupstaRepositoryImpl
 */
@Repository
public class OcuupstaRepositoryImpl extends RepositoryBase implements OcuupstaRepository {

	private final Logger log = LogManager.getLogger(OcuupstaRepositoryImpl.class);

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code")).build();
	private final Map<String, FieldMapper> offenderPrgObligationHtyMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_PRG_OBLIGATION_HTY_ID", new FieldMapper("offenderPrgObligationHtyId"))
			.put("OFFENDER_PRG_OBLIGATION_ID", new FieldMapper("offenderPrgObligationId"))
			.put("STATUS", new FieldMapper("status")).put("STATUS_CHANGE_DATE", new FieldMapper("statusChangeDate"))
			.put("STATUS_CHANGE_REASON", new FieldMapper("statusChangeReason"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.build();
	 private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
				.put("PROFILE_CODE", 						new FieldMapper("profileCode"))
				.put("PROFILE_TYPE", 						new FieldMapper("profileType"))
				.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
				.put("SEAL_FLAG", 							new FieldMapper("sealFlag"))
				.put("CREATE_DATETIME", 					new FieldMapper("createDatetime"))
				.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
				.put("OLD_TABLE_NAME", 						new FieldMapper("oldTableName"))
				.put("PROFILE_VALUE", 						new FieldMapper("profileValue"))
				.put("MODIFY_DATETIME", 					new FieldMapper("modifyDatetime"))
				.put("PROFILE_VALUE_2", 					new FieldMapper("profileValue2"))
				.put("DESCRIPTION", 						new FieldMapper("description"))
				.build();

	/**
	 * Creates new OcuupstaRepositoryImpl class Object
	 */
	public OcuupstaRepositoryImpl() {
		// OcuupstaRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderPrgObligationHty
	 *
	 * @return List<OffenderPrgObligationHty>
	 *
	 * @
	 */
	public List<OffenderPrgObligationHty> offPrgOblHtyExecuteQuery(final OffenderPrgObligationHty objSearchDao) {
		final String sql = getQuery("OCUUPSTA_OFFPRGOBLHTY_FIND_OFFENDER_PRG_OBLIGATION_HTY");
		final RowMapper<OffenderPrgObligationHty> OffenderPrgObligationHtyRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPrgObligationHty.class, offenderPrgObligationHtyMapping);
		List<OffenderPrgObligationHty> returnList = new ArrayList<OffenderPrgObligationHty>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("OFFENDER_PRG_OBLIGATION_ID", objSearchDao.getOffenderPrgObligationId()),
					OffenderPrgObligationHtyRowMapper);
		} catch (final Exception e) {
			log.error(this.getClass().getName()+" offPrgOblHtyExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgPsPrgStatRecordGroup(final String statusDesc, final String lovDomain) {
		final String sql = getQuery("OCUUPSTA_FIND_RGPSPRGSTAT");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("PCURRENTSTATUS", statusDesc, "PLOVDOMAIN", lovDomain), mRowMapper);
		} catch (final Exception e) {
			log.error(this.getClass().getName()+" rgPsPrgStatRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgPsPrgObstsRecordGroup(final String parentCode) {
		final String sql = getQuery("OCUUPSTA_FIND_RGPSPRGOBSTS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("PARENTCODE", parentCode), mRowMapper);
		} catch (final Exception e) {
			log.error("rgPsPrgObstsRecordGroup", e);
		}
		return returnList;
	}

	@Override
	public String getDescription(final String statusChangeReason) {
		final String sql = getQuery("OCUUPSTA_GET_DESCRIPTION");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("CODE", statusChangeReason),
					String.class);
		} catch (final Exception e) {
			log.error(this.getClass().getName()+" getDescription", e);
		}
		return returnList;
	}

	@Override
	public String getStatusDescription(final String status) {
		final String sql = getQuery("OCUUPSTA_GET_STATUS_DESCRIPTION");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("CODE", status), String.class);
		} catch (final Exception e) {
			log.error(this.getClass().getName()+" getStatusDescription", e);
		}
		return returnList;
	}

	@Override
	public Integer updateStatus(final OffenderPrgObligationHty searchBean) {
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_PRG_OBLIGATION_ID", OracleTypes.INTEGER),
				new SqlParameter("P_STATUS", OracleTypes.VARCHAR), new SqlParameter("P_REASON", OracleTypes.VARCHAR),
				new SqlParameter("P_DATE", OracleTypes.DATE) ,new SqlParameter("P_COMMENT_TEXT", OracleTypes.VARCHAR)};

		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PROGRAMMES").withProcedureName("UPDATE_STATUS").declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_PRG_OBLIGATION_ID", searchBean.getOffenderPrgObligationId());
		inParamMap.put("P_STATUS", searchBean.getStatus());
		inParamMap.put("P_REASON", searchBean.getStatusChangeReason());
		inParamMap.put("P_DATE", searchBean.getStatusChangeDate());
		inParamMap.put("P_COMMENT_TEXT", searchBean.getCommentText());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
		} catch (final Exception e) {
			log.error(this.getClass().getName()+" updateStatus", e);
		}
		return 1;
	}

	@Override
	public Date getRefferalDate(final Integer offenderPrgObligationId) {
		final String sql = getQuery("OCUUPSTA_GET_REFFERAL_DATE");
		Date date = new Date();
		try {
			date = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDER_PRG_OBLIGATION_ID", offenderPrgObligationId), Date.class);
		} catch (final Exception e) {
			log.error(this.getClass().getName()+" getRefferalDate", e);
		}
		return date;
	}

	@Override
	public Date getMaxDate(final Integer offenderPrgObligationId) {
		final String sql = getQuery("OCUUPSTA_GET_MAX_DATE");
		Date date = new Date();
		try {
			date = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDER_PRG_OBLIGATION_ID", offenderPrgObligationId), Date.class);
		} catch (final Exception e) {
			log.error(this.getClass().getName()+" getMaxDate", e);
		}
		return date;
	}
	
	
	@Override
	public String updateStatusBtn(final String code) {
		final String sql = getQuery("OCUUPSTA_UPDATE_STATUS_BTN");
		String result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, createParams("code", code), String.class);
		} catch (Exception e) {
			log.error(this.getClass().getName()+" updateStatusBtn", e);
			return result;
		}
		return result;
	}
	
	public SystemProfiles sysPflExecuteQuery() {
		final String sql = getQuery("OCUUPSTA_SYSPFL_FIND_SYSTEM_PROFILES");
		SystemProfiles obj = new SystemProfiles();
		try {
			obj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
					new BeanPropertyRowMapper<SystemProfiles>(SystemProfiles.class));
		} catch (Exception e) {
			log.error(this.getClass().getName()+" sysPflExecuteQuery", e);
			return obj;
		}
		return obj;
	}
	
	@Override
	public String reasonForSuspendingOrEndingProgramDisable(String code) {
		final String sql = getQuery("OCUUPSTA_REASON_FOR_SUSPENDING_OR_ENDING_PROGRAM_DISABLE");
		String result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, createParams("code", code), String.class);
		} catch (Exception e) {
			log.error(this.getClass().getName()+" reasonForSuspendingOrEndingProgramDisable", e);
			return result;
		}
		return result;
	}
	
	public List<String> getRoleIdList(String userName) {
		final String sql = getQuery("OCUUPSTA_GET_ROLE_ID_LIST_USER");		 
		 return namedParameterJdbcTemplate.query(sql, createParams("userName",userName),
					new RowMapper<String>() {
						public String mapRow(ResultSet rs, int rowNum) throws SQLException {
							return rs.getString(1);
						}
					});
			
	}
}
