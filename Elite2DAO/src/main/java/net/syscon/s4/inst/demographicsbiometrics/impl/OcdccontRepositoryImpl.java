package net.syscon.s4.inst.demographicsbiometrics.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
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
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.demographicsbiometrics.OcdccontRepository;

/**
 * Class OcdccontRepositoryImpl
 * 
 */
@Repository
public class OcdccontRepositoryImpl extends RepositoryBase implements OcdccontRepository {

	/**
	 * Creates new OcdccontRepositoryImpl class Object
	 */
	private static Logger logger = LogManager.getLogger(OcdccontRepositoryImpl.class.getName());

	public OcdccontRepositoryImpl() {
	}

	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> phonesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OWNER_ID", new FieldMapper("ownerId"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("PHONE_ID", new FieldMapper("phoneId"))
			.put("OWNER_CLASS", new FieldMapper("ownerClass"))
			.put("OWNER_CODE", new FieldMapper("ownerCode"))
			.put("EXT_NO", new FieldMapper("extNo"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("OWNER_SEQ", new FieldMapper("ownerSeq"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("PHONE_TYPE", new FieldMapper("phoneType"))
			.put("PHONE_NO", new FieldMapper("phoneNo"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.build();
	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            Phones
	 *
	 * @return List<Phones>
	 *
	 * @throws SQLException
	 */
	public List<Phones> phonesExecuteQuery(final Phones objSearchDao) {
		final String sql = getQuery("OCDCCONT_PHONES_FIND_PHONES");
		final RowMapper<Phones> PhonesRowMapper = Row2BeanRowMapper.makeMapping(sql, Phones.class, phonesMapping);
		final ArrayList<Phones> returnList;
		returnList = (ArrayList<Phones>) namedParameterJdbcTemplate.query(sql,
				createParams("ownerClass", objSearchDao.getOwnerClass(), "ownerId", objSearchDao.getOwnerId()),
				PhonesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param phones
	 *            List<Phones>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer phonesInsertPhones(final List<Phones> phones) {
		Long ownerSeq = null;
		final Integer index = 0;
		ownerSeq = phonesOwnerSeq(phones.get(index).getOwnerClass(), phones.get(index).getOwnerId());
		final String sql = getQuery("OCDCCONT_PHONES_INSERT_PHONES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Phones list : phones) {
			list.setOwnerSeq(BigDecimal.valueOf(ownerSeq));
			ownerSeq = ownerSeq+1;
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("insertPhones: ", e);
		}
		if (phones.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param phones
	 *            List<Phones>
	 *
	 * @throws SQLException
	 */
	public Integer phonesUpdatePhones(final List<Phones> phones) {
		final String sql = getQuery("OCDCCONT_PHONES_UPDATE_PHONES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Phones phonesObj : phones) {
			parameters.add(new BeanPropertySqlParameterSource(phonesObj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (phones.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param phones
	 *            List<Phones>
	 *
	 * @throws SQLException
	 */
	public Integer phonesDeletePhones(final List<Phones> phones) {
		final String sql = getQuery("OCDCCONT_PHONES_DELETE_PHONES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Phones phonesObj : phones) {
			parameters.add(new BeanPropertySqlParameterSource(phonesObj));
		}
		try {
			String tableName = "PHONES";
			String whereClause = "PHONE_ID = :PhoneId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method phonesDeletePhones", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (phones.size() == returnArray.length) {
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
	public List<ReferenceCodes> rgPhoneTypeRecordGroup() {
		List<ReferenceCodes> returnList = new ArrayList<>();
		final String sql = getQuery("OCDCCONT_FIND_RGPHONETYPE");
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		try {
			returnList =  namedParameterJdbcTemplate.query(sql, createParams(), referenceCodesRowMapper);
		} catch (Exception e) {
			logger.error("", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * phonesPreInsertPRE-INSERT
	 *
	 * @param params
	 *
	 */
	public Long phonesPreInsert() {
		final String sql = getQuery("OCDCCONT_PHONES_PREINSERT_PREINSERT");
		Long returnList = null;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		return returnList;
	}

	public Long phonesOwnerSeq(final String ownerClass, final BigDecimal ownerId) {
		final String sql = getQuery("OCDCCONT_PHONES_PREINSERT_OWNERSEQ");
		Long returnList = null;
		returnList = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OWNER_CLASS", ownerClass, "OWNER_ID", ownerId), Long.class);
		return returnList;
	}
}
