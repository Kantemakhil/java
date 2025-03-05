package net.syscon.s4.sa.admin.impl;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderIdentifier;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.sa.admin.UpdoffidRepository;

/**
 * Class OummenusRepositoryImpl
 */
@Repository
public class UpdoffidRepositoryImpl extends RepositoryBase implements UpdoffidRepository {

	/**
	 * Creates new OummenusRepositoryImpl class Object
	 */
	public UpdoffidRepositoryImpl() {
	}
	
	private final Map<String, FieldMapper> offenderMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", 				new FieldMapper("offenderBookId"))
			.build();

	@Override
	public Integer checkOffenderIdDisplay(final String offIdDisplay) {
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		BigDecimal returnVal = null;
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_ID_DISPLAY", Types.VARCHAR),
				new SqlOutParameter("RETURN_VALUE", Types.NUMERIC) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMS_SEARCH").withFunctionName("CHECK_OFFENDER_ID_DISPLAY")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_ID_DISPLAY", offIdDisplay);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		inParamMap = simpleJDBCCall.execute(inParameter);
		returnVal = (BigDecimal) inParamMap.get("RETURN_VALUE");
		return returnVal.intValue();
	}

	public Integer updateOffIdDisplay(final String offIdDisplay, final BigDecimal rootOffId , final String modifyUserId) {
		final String sql = getQuery("UPDOFFID_UPDATEOFF_ID");
		return namedParameterJdbcTemplate.update(sql,
				createParams("c2_off_id_disp", offIdDisplay, "lv_root_off_id", rootOffId, "modifyUserId", modifyUserId));
	}

	public Integer insertOffIdentifiers(final OffenderIdentifier bean) {
		final String sql = getQuery("UPDOFFID_INSER_OFFENDER_IDENTIFIERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(bean));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public String getoffenderSeq(final BigDecimal offenderId) {
		final String sql = getQuery("UPDOFFID_GET_OFFID_SEQ");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId", offenderId), String.class);
	}

	@Override
	public String getProfileValue() {
		final String sql = getQuery("GET_PROFILE_VALUE_NEW");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
	}

	@Override
	public List<Offenders> getOldOffender(final BigDecimal offIdDisplay) {
		final String sql = getQuery("GET_OLD_OFFENDER_LIST");
		final RowMapper<Offenders> OffenderRowMapper = Row2BeanRowMapper.makeMapping(sql,
				Offenders.class, offenderMapping);
		List<Offenders> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("root_offender_id", offIdDisplay), OffenderRowMapper);
		return returnList;
		
	}
}
