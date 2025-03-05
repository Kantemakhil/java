package net.syscon.s4.inst.securitythreatgroups.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderStgAssociations;
import net.syscon.s4.inst.securitythreatgroups.OidmbrasRepository;

/**
 * Class OidmbrasRepositoryImpl
 */
@Repository
public class OidmbrasRepositoryImpl extends RepositoryBase implements OidmbrasRepository {

	private static Logger logger = LogManager.getLogger(OidmbrasRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> ofndrStgMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("0", 							new FieldMapper("0"))
			.put("NVL(MAX(STG_SEQ)", 			new FieldMapper("nvl(max(stgSeq)"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.build();
	
	/**
	 * Creates new OidmbrasRepositoryImpl class Object
	 */
	public OidmbrasRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderStgAssociations
	 *
	 * @return List<OffenderStgAssociations>
	 */
	public List<OffenderStgAssociations> offenderStgAssociationsExecuteQuery(
			final OffenderStgAssociations objSearchDao) {
		final String sql = getQuery("OIDMBRAS_OFFENDERSTGASSOCIATIONS_FIND_OFFENDER_STG_ASSOCIATIONS");
		final RowMapper<OffenderStgAssociations> ofndrStgRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderStgAssociations.class, ofndrStgMapping);
		List<OffenderStgAssociations> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("OFFENDERBOOKID", objSearchDao.getOffenderBookId()), ofndrStgRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderStgAssociations
	 *            List<OffenderStgAssociations>
	 *
	 * @return List<Integer>
	 */
	public Integer offenderStgAssociationsInsertOffenderStgAssociations(
			final List<OffenderStgAssociations> lstofndrStgAss) {
		final String sql = getQuery("OIDMBRAS_OFFENDERSTGASSOCIATIONS_INSERT_OFFENDER_STG_ASSOCIATIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderStgAssociations ofndrStgAss : lstofndrStgAss) {
			parameters.add(new BeanPropertySqlParameterSource(ofndrStgAss));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstofndrStgAss.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderStgAssociations
	 *            List<OffenderStgAssociations>
	 */
	public Integer offenderStgAssociationsUpdateOffenderStgAssociations(
			final List<OffenderStgAssociations> lstofndrStgAss) {
		final String sql = getQuery("OIDMBRAS_OFFENDERSTGASSOCIATIONS_UPDATE_OFFENDER_STG_ASSOCIATIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderStgAssociations ofndrStgAss : lstofndrStgAss) {
			parameters.add(new BeanPropertySqlParameterSource(ofndrStgAss));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstofndrStgAss.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderStgAssociations
	 *            List<OffenderStgAssociations>
	 */
	public Integer offenderStgAssociationsDeleteOffenderStgAssociations(
			final List<OffenderStgAssociations> lstofndrStgAss) {
		final String sql = getQuery("OIDMBRAS_OFFENDERSTGASSOCIATIONS_DELETE_OFFENDER_STG_ASSOCIATIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderStgAssociations ofndrStgAss : lstofndrStgAss) {
			parameters.add(new BeanPropertySqlParameterSource(ofndrStgAss));
		}
		try {
			String tableName = "OFFENDER_STG_ASSOCIATIONS";
			String whereCondition = "OFFENDER_BOOK_ID=:offenderBookId AND STG_SEQ=:stgSeq";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstofndrStgAss.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgReasonCodeRecordGroup() {
		final String sql = getQuery("OIDMBRAS_FIND_RGREASONCODE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderStgAssociationsPreInsert
	 *
	 * @param params
	 */
	public Long offenderStgAssociationsPreInsert(final Long offenderBookId) {
		final String sql = getQuery("OIDMBRAS_OFFENDER_STG_ASSOCIATIONS_PREINSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("PBOOKID", offenderBookId), Long.class);
	}

}
