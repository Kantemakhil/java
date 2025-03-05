package net.syscon.s4.sa.admin.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.sa.admin.OumcdtabRepository;
import net.syscon.s4.sa.admin.beans.AllTabColumns;
import net.syscon.s4.sa.admin.beans.CopyTables;

/**
 * Class OumcdtabRepositoryImpl
 * 
 * @author Arkin Software Technologies
 * @version 1.0
 */
@Repository
public class OumcdtabRepositoryImpl extends RepositoryBase implements OumcdtabRepository {

	private static Logger logger = LogManager.getLogger(OumcdtabRepositoryImpl.class.getName());
	
	/**
	 * Creates new OumcdtabRepositoryImpl class Object
	 */
	public OumcdtabRepositoryImpl() {
	}

	private final Map<String, FieldMapper> copyTablesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("TABLE_NAME", new FieldMapper("tableName"))
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("TABLE_OPERATION_CODE", new FieldMapper("tableOperationCode"))
			.put("COL_NAME", new FieldMapper("colName"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("SEQ_NAME", new FieldMapper("seqName"))
			.put("MOVEMENT_REASON_CODE", new FieldMapper("movementReasonCode"))
			.put("PARENT_TABLE", new FieldMapper("parentTable"))
			.put("UPDATE_ALLOWED_FLAG", new FieldMapper("updateAllowedFlag"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MOVEMENT_TYPE", new FieldMapper("movementType"))
			.put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TABLE_NAME", new FieldMapper("tableName"))
			.put("CODE", new FieldMapper("code"))
			.put("MOVEMENT_REASON_CODE", new FieldMapper("movementReasonCode"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("DISTINCT", new FieldMapper("distinct"))
			.put("COLUMN_NAME", new FieldMapper("columnName"))
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            CopyTables
	 *
	 * @return List<CopyTables>
	 *
	 * @throws SQLException
	 */
	public List<CopyTables> modifyTabExecuteQuery(CopyTables objSearchDao) {
		final String sql = getQuery("OUMCDTAB_MODIFYTAB_FIND_COPY_TABLES");
		final RowMapper<CopyTables> CopyTablesRowMapper = Row2BeanRowMapper.makeMapping(sql, CopyTables.class,
				copyTablesMapping);
		final ArrayList<CopyTables> returnList = (ArrayList<CopyTables>) namedParameterJdbcTemplate.query(sql,
				createParams(), CopyTablesRowMapper);
		 return returnList;
	}

	/**
	 * @param
	 *
	 * @throws SQLException
	 *
	 */
	public int PRE_INSERT() {
		return 0;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstCopyTables
	 *            List<CopyTables>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer modifyTabInsertCopyTables(final List<CopyTables> lstCopyTables) {
		String sql = getQuery("OUMCDTAB_MODIFYTAB_INSERT_COPY_TABLES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CopyTables copyTables : lstCopyTables) {
			parameters.add(new BeanPropertySqlParameterSource(copyTables));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCopyTables.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstCopyTables
	 *            List<CopyTables>
	 *
	 * @throws SQLException
	 */
	public Integer modifyTabUpdateCopyTables(final List<CopyTables> lstCopyTables) {
		String sql = getQuery("OUMCDTAB_MODIFYTAB_UPDATE_COPY_TABLES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CopyTables copyTables : lstCopyTables) {
			parameters.add(new BeanPropertySqlParameterSource(copyTables));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCopyTables.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstCopyTables
	 *            List<CopyTables>
	 *
	 * @throws SQLException
	 */
	public Integer modifyTabDeleteCopyTables(final List<CopyTables> lstCopyTables) {
		String sql = getQuery("OUMCDTAB_MODIFYTAB_DELETE_COPY_TABLES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CopyTables copyTables : lstCopyTables) {
			parameters.add(new BeanPropertySqlParameterSource(copyTables));
		}
		try {
			String tableName = "COPY_TABLES";
			String whereClause = "TABLE_OPERATION_CODE  = :tableOperationCode AND MOVEMENT_TYPE  = :movementType AND MOVEMENT_REASON_CODE  = :movementReasonCode AND TABLE_NAME  = :tableName";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method modifyTabDeleteCopyTables", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCopyTables.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkModifyTabMovementTypeRecordGroup() {
		final String sql = getQuery("OUMCDTAB_FIND_CGFKMODIFYTABMOVEMENTTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<MovementReasons> cgfkModifyTabMovementReasoRecordGroup(String movementType) {
		final String sql = getQuery("OUMCDTAB_FIND_CGFKMODIFYTABMOVEMENTREASON");
		final RowMapper<MovementReasons> mRowMapper = Row2BeanRowMapper.makeMapping(sql, MovementReasons.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("movementType",movementType), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AllTabColumns> lovParentTableRecordGroup() {
		final String sql = getQuery("OUMCDTAB_FIND_LOVPARENTTABLE");
		final RowMapper<AllTabColumns> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AllTabColumns.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AllTabColumns> lovTableNameRecordGroup() {
		final String sql = getQuery("OUMCDTAB_FIND_LOVTABLENAME");
		final RowMapper<AllTabColumns> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AllTabColumns.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AllTabColumns> lovColumnNameRecordGroup(String tableName) {
		final String sql = getQuery("OUMCDTAB_FIND_LOVCOLUMNNAME");
		final RowMapper<AllTabColumns> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AllTabColumns.class, mMapping);

		try {
			  return namedParameterJdbcTemplate.query(sql, createParams("tableName",tableName), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AllTabColumns> lovSeqNameRecordGroup() {
		final String sql = getQuery("OUMCDTAB_FIND_LOVSEQNAME");
		final RowMapper<AllTabColumns> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AllTabColumns.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}
}
