package net.syscon.s4.pkgs.oms_form_access.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.pkgs.oms_form_access.OmsFormAccessRepository;
import net.syscon.s4.sa.admin.beans.AccessibleFormTables;

@Repository
public class OmsFormAccessRepositoryImpl extends RepositoryBase implements OmsFormAccessRepository {

	private final Map<String, FieldMapper> checkDataMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).build();

	@Override
	public List<AccessibleFormTables> tableCur(final String origForm, final String destForm) {
		final String sql = getQuery("TABLE_CUR_SELECT_OPERATION");
		final RowMapper<AccessibleFormTables> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AccessibleFormTables.class, checkDataMapping);
		List<AccessibleFormTables> list=new ArrayList<AccessibleFormTables>();
		try {
			list= namedParameterJdbcTemplate.query(sql, createParams("origForm", origForm, "destForm", destForm),
				mRowMapper);
		}catch (Exception e) {
			return list;
		}
		return list;
	}

	@Override
	public Integer lvStmtSelect(final String tableName) {
		final String sql = getQuery("LV_STMT_SELECT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("tableName", tableName.toLowerCase()),
				Integer.class);
	}

	@Override
	public Integer lvStmtIfSelect(final String tableName, final BigDecimal offenderId) {
		final String sql = getQuery("LV_STMT_IF_SELECT_ONE");
		String sqlQuery = "SELECT COUNT(0) FROM " + tableName
				+ " WHERE OFFENDER_BOOK_ID IN ( SELECT OFFENDER_BOOK_ID FROM OFFENDER_BOOKINGS WHERE ROOT_OFFENDER_ID = "
				+ offenderId + ")";
		return namedParameterJdbcTemplate.queryForObject(sqlQuery,
				createParams("tableName",tableName, "offenderId", offenderId), Integer.class);
	}

	@Override
	public Integer lvStmtElseSelect(final String tableName, final BigDecimal bookId) {
		final String sql = getQuery("LV_STMT_ELSE_SELECT");
		String sqlQuery = "SELECT COUNT(0)   FROM " + tableName + "  WHERE OFFENDER_BOOK_ID = " + bookId;
		return namedParameterJdbcTemplate.queryForObject(sqlQuery, createParams(), Integer.class);

	}

	@Override
	public Integer lvStmtLastElseSelect(final String tableName, final BigDecimal offenderId) {
		final String sql = getQuery("LV_STMT_LAST_ELSE_SELECT_NEW");
		Integer retVal = 0;
		String sqlQuery = "SELECT COUNT(0) FROM " + tableName + " WHERE offender_id = " + offenderId;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sqlQuery, createParams(), Integer.class);
		} catch (Exception e) {
			retVal = 0;
		}
		return retVal;
	}

	@Override
	public Integer lvStmt(final String tableName,Long stgdId) {
		final String sql = "SELECT COUNT(0) FROM "+tableName+" WHERE stg_id="+stgdId;
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("TAB_NAME", tableName), Integer.class);
		} catch (Exception e) {
			count = 0;
		}
		return count;
	}

}