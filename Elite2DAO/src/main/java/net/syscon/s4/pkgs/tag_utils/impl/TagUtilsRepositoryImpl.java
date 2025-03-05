package net.syscon.s4.pkgs.tag_utils.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.pkgs.AllConsColumns;
import net.syscon.s4.pkgs.tag_utils.TagUtilsRepository;

@Repository
public class TagUtilsRepositoryImpl extends RepositoryBase implements TagUtilsRepository {

	private static Logger logger = LogManager.getLogger(TagUtilsRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>().build();

	private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TABLE_NAME", new FieldMapper("tableName")).put("COLUMN_NAME", new FieldMapper("columnName")).build();

	@Override
	public String getLivingUnitDescp(final Integer pLivUnitId) {
		final String sql = getQuery("GET_LIVING_UNIT_DESCP");
		String desc = null;
		try {
			desc = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_LIV_UNIT_ID", pLivUnitId),
					String.class);
		} catch (Exception e) {
			logger.error("getDesc :" + e);
			desc = null;
		}
		return desc;
	}

	@Override
	public List<AllConsColumns> getConstraintsDetails(final String pOwner, final String pTableName,
			final String columName) {
		final String sql = getQuery("TABLE_CUR");
		List<AllConsColumns> retList = new ArrayList<AllConsColumns>();
		final RowMapper<AllConsColumns> rowMapper = Row2BeanRowMapper.makeMapping(sql, AllConsColumns.class, mapping);
		try {
			retList = namedParameterJdbcTemplate.query(sql, createParams("P_OWNER", pOwner.toLowerCase(),
					"P_TABLE_NAME", pTableName.toLowerCase(), "P_COLUMN_NAME", columName.toLowerCase()), rowMapper);
		} catch (Exception e) {
			logger.error("getConstraintsDetails :" + e);
			retList = null;
		}
		return retList;
	}

	@Override
	public Integer getOneForLRet(final String query) {
		final String sql = query;
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (Exception e) {
			logger.error("getOneForLRet :" + e);
			retVal = 0;
		}
		return retVal;
	}

	@Override
	public String getTabName(final String pConstraintName) {
		final String sql = getQuery("TABLE_CUR_ONE");
		String tabName = null;
		try {
			tabName = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_CONSTRAINT_NAME", pConstraintName),
					String.class);
		} catch (Exception e) {
			logger.error("getTabName :" + e);
			tabName = null;
		}
		return tabName;
	}

	@Override
	public List<Object[]> tableCur(final Integer cni) {
		final String sql = getQuery("TABLE_CUR");
		List<Object[]> returnArray = new ArrayList();
		final RowMapper<Object[]> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Object[].class, mMapping);

		try {
			returnArray = namedParameterJdbcTemplate.query(sql, createParams("P_OWNER", "OMS_OWNER", "P_TABLE_NAME",
					"OFFENDER_CASE_NOTES", "P_COLUMN_NAME", "CASE_NOTE_ID"), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("getModuleName :", e);
		}
		return returnArray;

	}

	@Override
	public Integer offenderCaseNotesSelect(final Integer voff) {
		final String sql = getQuery("OFFENDER_CASE_NOTES_SELECT");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_KEY_ID", voff), Integer.class);
		} catch (Exception e) {
			logger.error("offenderCaseNotesSelect :" + e);
			retVal = 0;
		}
		return retVal;
	}

	@Override
	public String subStr(final String key) {
		final String sql = getQuery("SUBSTR_FUNCTION");
		String retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("L_KEY_STRING", key), String.class);
		} catch (Exception e) {
			logger.error("subStr :" + e);
		}
		return retVal;
	}

	@Override
	public String subStr1(final String key) {
		final String sql = getQuery("SUBSTR_FUNCTION1");
		String retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("L_KEY_STRING", key), String.class);
		} catch (Exception e) {
			logger.error("subStr1 :" + e);
		}
		return retVal;
	}

	@Override
	public String subStr2(final String key) {
		final String sql = getQuery("SUBSTR_FUNCTION2");
		String retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("L_KEY_STRING", key), String.class);
		} catch (Exception e) {
			logger.error("subStr2 :" + e);
		}
		return retVal;
	}

	@Override
	public List<String> sysProfileCur(final String profileType, final String profileCode) {
		final String sql = getQuery("SYS_PROFILE_CUR");
		final RowMapper<String> mRowMapper = Row2BeanRowMapper.makeMapping(sql, String.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("P_PTYPE", profileType, "P_PCODE", profileCode),
				mRowMapper);
	}

	@Override
	public String sysProfileCurForProfileValue(String profileType, String profileCode) {
		String sql = getQuery("SYS_PROFILE_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_PTYPE", profileType, "P_PCODE", profileCode), String.class);
	}

	@Override
	public String getStaffCur(String userId) {
		String sql = getQuery("GET_STAFF_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_USER_ID",userId), String.class);
	}

}