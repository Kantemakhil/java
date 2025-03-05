package net.syscon.s4.iwp.impl;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.VAssignedOffenders;
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.legals.au.StaffWorkAssignmentsV1;
import net.syscon.s4.iwp.OcipowofRepository;
import oracle.jdbc.OracleTypes;

@Repository
public class OcipowofRepositoryImpl extends RepositoryBase implements OcipowofRepository {


	private static org.apache.logging.log4j.Logger logger =   LogManager.getLogger(OcipowofRepositoryImpl.class.getName());
	
	
	
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("ORDER_CODE", new FieldMapper("orderCode"))
			.put("ORDER_TYPE", new FieldMapper("orderType")).build();
	
	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("STAFF_ID", new FieldMapper("staffId"))
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("MIDDLE_NAME", new FieldMapper("middleName"))
			.put("LAST_NAME", new FieldMapper("lastName"))
			.put("FIRST_NAME", new FieldMapper("firstName"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("IMAGEDATA", new FieldMapper("imageData"))
			.put("CAL_AGY_LOC_ID", new FieldMapper("calAgyLocId"))
			.build();

	public List<StaffMembers> staffExecuteQuery(final StaffMembers objSearchDao) {
		final String sql = getQuery("OCIPOWOF_STAFF_FIND_STAFF_MEMBERS");
		final RowMapper<StaffMembers> rowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				agencyLocationsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("userId", objSearchDao.getCreateUserId()), rowMapper);

	}

	public List<StaffMemberRoles> gettingStaffPostQuery(final StaffMembers objSearchDao) {
		final String sql = getQuery("OCIPOWOF_GETTING_POST_STAFF_DATA");
		final RowMapper<StaffMemberRoles> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				StaffMemberRoles.class, agencyLocationsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("VSTAFFID", objSearchDao.getStaffId()),
				rowMapper);

	}

	public List<VAssignedOffenders> vAssOffExecuteQuery(final VAssignedOffenders objSearchDao) {
		final String sql = getQuery("OCIPOWOF_VASSOFF_FIND_V_ASSIGNED_OFFENDERS");
		final RowMapper<VAssignedOffenders> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				VAssignedOffenders.class, agencyLocationsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("STAFF_ID", objSearchDao.getSacStaffId(),"username",objSearchDao.getCreateUserId()),
				rowMapper);

	}

	public List<StaffWorkAssignmentsV1> vswaExecuteQuery(final StaffWorkAssignmentsV1 objSearchDao) {
		final String sql = getQuery("OCIPOWOF_VSWA_EXECUTE_QUERRY_STAFF_WORK_ASSIGNMENTS_V1");
		final RowMapper<StaffWorkAssignmentsV1> rowMapper = Row2BeanRowMapper.makeMapping(sql,StaffWorkAssignmentsV1.class,
				mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("offenderbookid", objSearchDao.getOffenderBookId(),
				"sacstaffid", objSearchDao.getStaffId()), rowMapper);

	}

	@Override
	public Integer gettingWorkload(final StaffMemberRoles searchRecord) {
		Integer returnVal = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Map<String, Object> returnValObj = null;
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] {
				new SqlParameter("p_agy_loc_id", Types.VARCHAR),
				new SqlParameter("p_staff_id", Types.INTEGER),
				new SqlParameter("p_position", Types.VARCHAR),
				new SqlParameter("p_role", Types.VARCHAR), 
				new SqlParameter("p_from_date", Types.DATE),
				new SqlOutParameter("RETURN_VAL", OracleTypes.NUMBER) };

		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("PIMS_WEIGHT").withFunctionName("OFFICER_WORK").declareParameters(sqlParameters);

		inParamMap.put("p_agy_loc_id", searchRecord.getAgyLocID());
		inParamMap.put("p_staff_id", searchRecord.getStaffId());
		inParamMap.put("p_position", searchRecord.getPosition());
		inParamMap.put("p_role", searchRecord.getRole());
		inParamMap.put("p_from_date", searchRecord.getFromDate());

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnValObj = simpleJDBCCall.execute(inParameter);
			returnVal = Integer.valueOf(returnValObj.get("RETURN_VAL").toString());
		} catch (final Exception e) {
			logger.error("Exception in gettingTotalWorkLoad Ocipowof:",e);
		}
		return returnVal;
	}

	@Override
	public Integer gettingOffenderStaffPostQuerry(final StaffMembers searchRecord) {
		final String sql = getQuery("OCIPOWOF_GETTING_OFFENDER_COUNT");
		return	 namedParameterJdbcTemplate.queryForObject(sql, createParams("STAFF_ID", searchRecord.getStaffId()),
					Integer.class);

	}

	@Override
	public Images imageData(final Integer offenderBookId) {
		final String sql = getQuery("OCIPOWOF_GETTING_IMAGE_DATA");
		Images returnObj = new Images();
		final RowMapper<Images> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Images.class, mMapping);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderbookid", offenderBookId),
					mRowMapper);
		} catch (final Exception e) {
			logger.error("Exception in ImageData Ocipowof", e);
		}
		return returnObj;
	}

	@Override
	public Integer gettingHpCountNumber(final Integer offenderBookId) {
		Integer returnObj = null;
		final String sql = getQuery("OCIPOWOF_GETTING_HP_COUNT");

		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderbookid", offenderBookId),
				Integer.class);
		return returnObj;
	}

	@Override
	public Integer gettingYCountNumber(final Integer offenderBookId) {
		Integer returnObj = null;
		final String sql = getQuery("OCIPOWOF_GETTING_HP_COUNT");

		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderbookid", offenderBookId),
				Integer.class);
		return returnObj;
	}

	@Override
	public Integer gettingACountNumber(final Integer offenderBookId) {
		Integer returnObj = null;
		final String sql = getQuery("OCIPOWOF_GETTING_HP_COUNT");

		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderbookid", offenderBookId),
				Integer.class);
		return returnObj;
	}

	@Override
	public BigDecimal gettingLinePostQuerry(final StaffWorkAssignmentsV1 element) {

		BigDecimal returnVal = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Map<String, Object> returnValObj = null;
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter(" p_offender_book_id", Types.INTEGER),
				new SqlParameter("p_charge_seq", Types.INTEGER), new SqlParameter("p_seq", Types.INTEGER),
				new SqlParameter("p_order_type", Types.VARCHAR), new SqlParameter("p_order_code", Types.VARCHAR),
				new SqlParameter("p_component", Types.VARCHAR), new SqlOutParameter("RETURN_VAL", OracleTypes.NUMBER) };

		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("PIMS_WEIGHT").withFunctionName("get_weighting").declareParameters(sqlParameters);

		inParamMap.put("p_offender_book_id", element.getOffenderBookId());
		inParamMap.put("p_charge_seq", element.getChargeSeq());
		inParamMap.put("p_seq", element.getSentenceSeq());
		inParamMap.put("p_order_type", element.getOrderType());
		inParamMap.put("p_order_code", element.getOrderCode());
		inParamMap.put("p_component", element.getComponent());

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnValObj = simpleJDBCCall.execute(inParameter);
			returnVal = (BigDecimal) returnValObj.get("RETURN_VAL");
		} catch (final Exception e) {
			logger.error("Exception in gettingLineData Ocipowof:",e);
		}
		return returnVal;

	}

}
