package net.syscon.s4.sa.audit.maintenance.impl;
import java.sql.Connection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.dao.RecordGroup;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.legalscreens.maintenance.bean.TableColumnNameBean;
import net.syscon.s4.sa.audit.maintenance.OmsysjntRepository;
import net.syscon.s4.sa.audit.maintenance.userTabColumns;
/**
 * Class OmsysjntRepositoryImpl
 */
@Repository
public class OmsysjntRepositoryImpl extends RepositoryBase implements OmsysjntRepository{
	@Autowired
	protected DataSource dataSource;

/**
* Logger object used to print the log in the file
*/
	private static Logger logger = LogManager.getLogger(OmsysjntRepositoryImpl.class.getName());
private final Map<String, FieldMapper> tableColumnNameMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("TABLE_NAME", 						new FieldMapper("tableName"))
.build();
private final Map<String, FieldMapper> userTabColumnsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DATA_PRECISION", 						new FieldMapper("dataPrecision"))
.put("COLUMN_NAME", 						new FieldMapper("columnName"))
.put("DATA_LENGTH", 						new FieldMapper("dataLength"))
.put("DATA_TYPE", 						new FieldMapper("dataType"))
.put("DATA_SCALE", 						new FieldMapper("dataScale"))
.build();
private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("CODE", 						new FieldMapper("code"))
.build();

	/**
	 * Creates new OmsysjntRepositoryImpl class Object
	 */
	public OmsysjntRepositoryImpl() {
		// OmsysjntRepositoryImpl
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<RecordGroup> tableRgRecordGroup() {
		final String sql = getQuery("OMSYSJNT_FIND_TABLERG");
		final RowMapper<RecordGroup> mRowMapper = Row2BeanRowMapper.makeMapping(sql, RecordGroup.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception in tableRgRecordGroup: Omsysjnt:", e);
			return Collections.emptyList();
		}
	}

	@Override
	public List<TableColumnNameBean> createTr() {
		final String sql = getQuery("OMSYSJNT_CREATE_TR");
		final RowMapper<TableColumnNameBean> mRowMapper = Row2BeanRowMapper.makeMapping(sql, TableColumnNameBean.class,
				tableColumnNameMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
	}

	@Override
	public Integer createOneTr(String tableName) {
		return null;
	}

	@Override
	public List<userTabColumns> colLenCur(final String tableName) {
		final String sql = getQuery("OMSYSJNT_CREATE_ONE_TR");
		final RowMapper<userTabColumns> mRowMapper = Row2BeanRowMapper.makeMapping(sql, userTabColumns.class,
				userTabColumnsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("tableName", tableName), mRowMapper);
	}

	@Override
	public String checkSynonymCur(final String tableNamejn) {
		final String sql = getQuery("CHECK_SYNONYM_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("TABNAMEJN", tableNamejn), String.class);
		} catch (Exception e) {
			return "N";
		}
	}

	@Override
	public Integer createTable(String vDdlStmt) {
		final String sql = vDdlStmt;
		try (Connection connection = dataSource.getConnection()){
			connection.createStatement().execute(sql);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public Integer synonyms(String vDdlStmt) {
		final String sql = vDdlStmt;
		try {
			namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public Integer grantQuery(String vDdlStmt) {
		final String sql = vDdlStmt;
		try {
			namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public Integer createTriggers(String vDdlStmt) {
		final String sql = vDdlStmt;
		try (Connection connection = dataSource.getConnection()) {
			connection.createStatement().execute(sql);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

}
