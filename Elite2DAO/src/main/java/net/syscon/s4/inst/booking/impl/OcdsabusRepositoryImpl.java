package net.syscon.s4.inst.booking.impl;

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
import net.syscon.s4.inst.booking.OcdsabusRepository;
import net.syscon.s4.inst.booking.beans.OffenderSubstanceDetails;
import net.syscon.s4.inst.booking.beans.OffenderSubstanceTreatments;
import net.syscon.s4.inst.booking.beans.OffenderSubstanceUses;

/**
 * Class OcdsabusRepositoryImpl
 */
@Repository
public class OcdsabusRepositoryImpl extends RepositoryBase implements OcdsabusRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdsabusRepositoryImpl.class);
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper(" description "))
			.put("DSP_DESCRIPTION", new FieldMapper("dspDescription"))
			.put("NBT_DESCRIPTION", new FieldMapper("nbtDescription"))
			.put("MODE", new FieldMapper("mode"))
			.put("TREATMENT_PLACE", new FieldMapper("treatmentPlace"))
			.put("USE_LEVEL", new FieldMapper("useLevel"))
			.put("SOURCE_OF_INFO", new FieldMapper("sourceOfInfo"))
			.put("SUBSTANCE_TYPE", new FieldMapper("substanceType"))
			.put("TREATMENT_CODE", new FieldMapper("treatmentCode"))
			.put("DSP_DESCRIPTION3", new FieldMapper("dspDescription3")).build();
	private final Map<String, FieldMapper> offenderSubstanceDetailsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("SUBSTANCE_TYPE", new FieldMapper("substanceType"))
			.put("SEQ_NUMBER", new FieldMapper("seqNumber"))
			.put("USE_PERIOD", new FieldMapper("usePeriod"))
			.put("USE_LEVEL", new FieldMapper("useLevel"))
			.put("CASELOAD_TYPE", new FieldMapper("caseloadType"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("SOURCE_OF_INFO", new FieldMapper("sourceOfInfo"))
			.build();
	private final Map<String, FieldMapper> offenderSubstanceUsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("SUBSTANCE_TYPE", new FieldMapper("substanceType"))
			.put("AGE_USED", new FieldMapper("ageUsed"))
			.put("CASELOAD_TYPE", new FieldMapper("caseloadType"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("ROW_ID", new FieldMapper("rowId"))
			.build();
	private final Map<String, FieldMapper> offenderSubstanceTreatmentsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SUBSTANCE_TYPE", new FieldMapper("substanceType"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("TREATMENT_SEQ", new FieldMapper("treatmentSeq"))
			.put("TREATMENT_FROM_DATE", new FieldMapper("treatmentFromDate"))
			.put("FROM_DATE_FLAG", new FieldMapper("fromDateFlag"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("CASELOAD_TYPE", new FieldMapper("caseloadType"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("TO_DATE_FLAG", new FieldMapper("toDateFlag"))
			.put("TREATMENT_TO_DATE", new FieldMapper("treatmentToDate"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("TREATMENT_PLACE", new FieldMapper("treatmentPlace"))
			.put("TREATMENT_CODE", new FieldMapper("treatmentCode"))

			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description")).build();

	/**
	 * Creates new OcdsabusRepositoryImpl class Object
	 */
	public OcdsabusRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderSubstanceUs
	 *
	 * @return List<OffenderSubstanceUs>
	 *
	 * @throws SQLException
	 */
	public List<OffenderSubstanceUses> offSuExecuteQuery(final OffenderSubstanceUses objSearchDao) {
		final String sql = getQuery("OCDSABUS_OFFSU_FIND_OFFENDER_SUBSTANCE_USES");
		final RowMapper<OffenderSubstanceUses> offenderSubstanceUsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSubstanceUses.class, offenderSubstanceUsMapping);
		final ArrayList<OffenderSubstanceUses> returnList = (ArrayList<OffenderSubstanceUses>) namedParameterJdbcTemplate
				.query(sql, createParams("offender_book_id", objSearchDao.getOffenderBookId()),
						offenderSubstanceUsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderSubstanceUs
	 *            List<OffenderSubstanceUs>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer offSuInsertOffenderSubstanceUses(final List<OffenderSubstanceUses> lstOffenderSubstanceUs) {
		final String sql = getQuery("OCDSABUS_OFFSU_INSERT_OFFENDER_SUBSTANCE_USES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderSubstanceUses sanction : lstOffenderSubstanceUs) {
			parameters.add(new BeanPropertySqlParameterSource(sanction));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstOffenderSubstanceUs.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderSubstanceUs
	 *            List<OffenderSubstanceUs>
	 *
	 * @throws SQLException
	 */
	public Integer offSuUpdateOffenderSubstanceUses(final List<OffenderSubstanceUses> lstOffenderSubstanceUs) {
		final String sql = getQuery("OCDSABUS_OFFSU_UPDATE_OFFENDER_SUBSTANCE_USES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderSubstanceUses OffenderSubstanceUs : lstOffenderSubstanceUs) {
			parameters.add(new BeanPropertySqlParameterSource(OffenderSubstanceUs));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderSubstanceUs.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderSubstanceUs
	 *            List<OffenderSubstanceUs>
	 *
	 * @throws SQLException
	 */
	public Integer offSuDeleteOffenderSubstanceUses(final List<OffenderSubstanceUses> lstOffenderSubstanceUs) {
		final String sql = getQuery("OCDSABUS_OFFSU_DELETE_OFFENDER_SUBSTANCE_USES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderSubstanceUses OffenderSubstanceUs : lstOffenderSubstanceUs) {
			parameters.add(new BeanPropertySqlParameterSource(OffenderSubstanceUs));
		}
		try {
			String tableName = "OFFENDER_SUBSTANCE_USES";
			String whereClause = "OFFENDER_BOOK_ID=:offenderBookId and SUBSTANCE_TYPE=:substanceType";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offSuDeleteOffenderSubstanceUses", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstOffenderSubstanceUs.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderSubstanceDetails
	 *
	 * @return List<OffenderSubstanceDetails>
	 *
	 * @throws SQLException
	 */
	public List<OffenderSubstanceDetails> offSdExecuteQuery(final OffenderSubstanceDetails objSearchDao) {
		final String sql = getQuery("OCDSABUS_OFFSD_FIND_OFFENDER_SUBSTANCE_DETAILS");
		final RowMapper<OffenderSubstanceDetails> offenderSubstanceRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSubstanceDetails.class, offenderSubstanceDetailsMapping);
		final ArrayList<OffenderSubstanceDetails> returnList = (ArrayList<OffenderSubstanceDetails>) namedParameterJdbcTemplate
				.query(sql, createParams("offender_book_id", objSearchDao.getOffenderBookId(), "substance_type",
						objSearchDao.getSubstanceType()), offenderSubstanceRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderSubstanceDetails
	 *            List<OffenderSubstanceDetails>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer offSdInsertOffenderSubstanceDetails(
			final List<OffenderSubstanceDetails> lstOffenderSubstanceDetails) {
		final String sql = getQuery("OCDSABUS_OFFSD_INSERT_OFFENDER_SUBSTANCE_DETAILS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();

		for (final OffenderSubstanceDetails OffenderSubstanceUs : lstOffenderSubstanceDetails) {
			parameters.add(new BeanPropertySqlParameterSource(OffenderSubstanceUs));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstOffenderSubstanceDetails.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderSubstanceDetails
	 *            List<OffenderSubstanceDetails>
	 *
	 * @throws SQLException
	 */
	public Integer offSdUpdateOffenderSubstanceDetails(
			final List<OffenderSubstanceDetails> lstOffenderSubstanceDetails) {
		final String sql = getQuery("OCDSABUS_OFFSD_UPDATE_OFFENDER_SUBSTANCE_DETAILS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderSubstanceDetails offenderSubstanceDetails : lstOffenderSubstanceDetails) {
			parameters.add(new BeanPropertySqlParameterSource(offenderSubstanceDetails));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderSubstanceDetails.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderSubstanceDetails
	 *            List<OffenderSubstanceDetails>
	 *
	 * @throws SQLException
	 */
	public Integer offSdDeleteOffenderSubstanceDetails(
			final List<OffenderSubstanceDetails> lstOffenderSubstanceDetails) {
		final String sql = getQuery("OCDSABUS_OFFSD_DELETE_OFFENDER_SUBSTANCE_DETAILS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderSubstanceDetails offenderSubstanceDetails : lstOffenderSubstanceDetails) {
			parameters.add(new BeanPropertySqlParameterSource(offenderSubstanceDetails));
		}
		try {
			String tableName = "OFFENDER_SUBSTANCE_DETAILS";
			String whereClause = "OFFENDER_BOOK_ID=:offenderBookId and SUBSTANCE_TYPE=:substanceType and SEQ_NUMBER=:seqNumber";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offSdDeleteOffenderSubstanceDetails", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstOffenderSubstanceDetails.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderSubstanceTreatments
	 *
	 * @return List<OffenderSubstanceTreatments>
	 *
	 * @throws SQLException
	 */
	public List<OffenderSubstanceTreatments> offStExecuteQuery(final OffenderSubstanceTreatments objSearchDao) {
		final String sql = getQuery("OCDSABUS_OFFST_FIND_OFFENDER_SUBSTANCE_TREATMENTS");
		final RowMapper<OffenderSubstanceTreatments> offenderSubstanceTreatmentsRowMapper = Row2BeanRowMapper
				.makeMapping(sql, OffenderSubstanceTreatments.class, offenderSubstanceTreatmentsMapping);
		final ArrayList<OffenderSubstanceTreatments> returnList = (ArrayList<OffenderSubstanceTreatments>) namedParameterJdbcTemplate
				.query(sql, createParams("offender_book_id", objSearchDao.getOffenderBookId(), "substance_type",
						objSearchDao.getSubstanceType()), offenderSubstanceTreatmentsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderSubstanceTreatments
	 *            List<OffenderSubstanceTreatments>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer offStInsertOffenderSubstanceTreatments(
			final List<OffenderSubstanceTreatments> lstOffenderSubstanceTreatments) {
		final String sql = getQuery("OCDSABUS_OFFST_INSERT_OFFENDER_SUBSTANCE_TREATMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();

		for (final OffenderSubstanceTreatments offenderSubstanceDetails : lstOffenderSubstanceTreatments) {

			parameters.add(new BeanPropertySqlParameterSource(offenderSubstanceDetails));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstOffenderSubstanceTreatments.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderSubstanceTreatments
	 *            List<OffenderSubstanceTreatments>
	 *
	 * @throws SQLException
	 */
	public Integer offStUpdateOffenderSubstanceTreatments(
			final List<OffenderSubstanceTreatments> lstOffenderSubstanceTreatments) {
		final String sql = getQuery("OCDSABUS_OFFST_UPDATE_OFFENDER_SUBSTANCE_TREATMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderSubstanceTreatments offenderSubstanceTreatments : lstOffenderSubstanceTreatments) {
			parameters.add(new BeanPropertySqlParameterSource(offenderSubstanceTreatments));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderSubstanceTreatments.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderSubstanceTreatments
	 *            List<OffenderSubstanceTreatments>
	 *
	 * @throws SQLException
	 */
	public Integer offStDeleteOffenderSubstanceTreatments(
			final List<OffenderSubstanceTreatments> lstOffenderSubstanceTreatments) {
		final String sql = getQuery("OCDSABUS_OFFST_DELETE_OFFENDER_SUBSTANCE_TREATMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderSubstanceTreatments offenderSubstanceTreatments : lstOffenderSubstanceTreatments) {
			parameters.add(new BeanPropertySqlParameterSource(offenderSubstanceTreatments));
		}
		try {
			String tableName = "OFFENDER_SUBSTANCE_TREATMENTS";
			String whereClause = "OFFENDER_BOOK_ID=:offenderBookId and SUBSTANCE_TYPE=:substanceType and TREATMENT_SEQ=:treatmentSeq";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offStDeleteOffenderSubstanceTreatments", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstOffenderSubstanceTreatments.size() == returnArray.length) {
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
	public List<ReferenceCodes> ageRecordGroup() {
		final String sql = getQuery("OCDSABUS_FIND_AGE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			logger.error("Exeception in ageRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> lSourceInfoRecordGroup() {
		final String sql = getQuery("OCDSABUS_FIND_LSOURCEINFO");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			logger.error("lSourceInfoRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkOffSuDspDescriptionRecordGroup() {
		final String sql = getQuery("OCDSABUS_FIND_CGFKOFFSUDSPDESCRIPTION");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			logger.error("cgfkOffSuDspDescriptionRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkOffStDspDescription3RecordGroup() {
		final String sql = getQuery("OCDSABUS_FIND_CGFKOFFSTDSPDESCRIPTION3");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			logger.error("cgfkOffStDspDescription3RecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkOffStDspDescriptionRecordGroup() {
		final String sql = getQuery("OCDSABUS_FIND_CGFKOFFSTDSPDESCRIPTION");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			logger.error("cgfkOffStDspDescriptionRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkOffSdDspDescriptionRecordGroup() {
		final String sql = getQuery("OCDSABUS_FIND_CGFKOFFSDDSPDESCRIPTION");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			logger.error("cgfkOffSdDspDescriptionRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offSuOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<Integer> offSuOnCheckDeleteMaster(final OffenderSubstanceUses paramBean) {
		List<Integer> returnList = null;
		final String sql = getQuery("OCDSABUS_OFF_SU_ONCHECKDELETEMASTER");
		returnList = (ArrayList<Integer>) namedParameterJdbcTemplate.queryForList(sql, createParams("offender_book_id",
				paramBean.getOffenderBookId(), "substance_type", paramBean.getSubstanceType()), Integer.class);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offSuOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<Integer> offStOnCheckDeleteMaster(final OffenderSubstanceUses paramBean) {
		final String sql = getQuery("OCDSABUS_OFF_ST_ONCHECKDELETEMASTER");
		List<Integer> returnList = null;
		returnList = (ArrayList<Integer>) namedParameterJdbcTemplate.queryForList(sql, createParams("offender_book_id",
				paramBean.getOffenderBookId(), "substance_type", paramBean.getSubstanceType()), Integer.class);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offStPreInsert
	 *
	 * @param params
	 *
	 */
	public List<OffenderSubstanceTreatments> offStPreInsert(final OffenderSubstanceTreatments paramBean) {
		final String sql = getQuery("OCDSABUS_OFF_ST_PREINSERT");
		final RowMapper<OffenderSubstanceTreatments> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSubstanceTreatments.class, offenderSubstanceTreatmentsMapping);
		final ArrayList<OffenderSubstanceTreatments> returnList = (ArrayList<OffenderSubstanceTreatments>) namedParameterJdbcTemplate
				.query(sql, createParams(paramBean), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffSuOffSuRefCod
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfkchkOffSuOffSuRefCod(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCDSABUS_CGFKCHK_OFF_SU_OFF_SU_REF_COD");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ArrayList<ReferenceCodes> returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
				createParams(paramBean), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpOffSuOffSuRefCod
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfklkpOffSuOffSuRefCod(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCDSABUS_CGFKLKP_OFF_SU_OFF_SU_REF_COD");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ArrayList<ReferenceCodes> returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
				createParams(paramBean), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cguvchkOffenderSubstanceUs
	 *
	 * @param params
	 *
	 */
	public List<OffenderSubstanceUses> cguvchkOffenderSubstanceUs(final OffenderSubstanceUses paramBean) {
		final String sql = getQuery("OCDSABUS_CGUVCHK_OFFENDER_SUBSTANCE_US");
		final RowMapper<OffenderSubstanceUses> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSubstanceUses.class, offenderSubstanceUsMapping);
		final ArrayList<OffenderSubstanceUses> returnList = (ArrayList<OffenderSubstanceUses>) namedParameterJdbcTemplate
				.query(sql, createParams(paramBean), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffSdOffSdRefCod
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfkchkOffSdOffSdRefCod(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCDSABUS_CGFKCHK_OFF_SD_OFF_SD_REF_COD");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ArrayList<ReferenceCodes> returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
				createParams(paramBean), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpOffSdOffSdRefCod
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfklkpOffSdOffSdRefCod(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCDSABUS_CGFKLKP_OFF_SD_OFF_SD_REF_COD");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ArrayList<ReferenceCodes> returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
				createParams(paramBean), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cguvchkOffenderSubstanceDe
	 *
	 * @param params
	 *
	 */
	public List<OffenderSubstanceDetails> cguvchkOffenderSubstanceDe(final OffenderSubstanceDetails paramBean) {
		final String sql = getQuery("OCDSABUS_CGUVCHK_OFFENDER_SUBSTANCE_DE");
		final RowMapper<OffenderSubstanceDetails> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSubstanceDetails.class, offenderSubstanceDetailsMapping);
		final ArrayList<OffenderSubstanceDetails> returnList = (ArrayList<OffenderSubstanceDetails>) namedParameterJdbcTemplate
				.query(sql, createParams(paramBean), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffStOffStRefCod
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfkchkOffStOffStRefCod(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCDSABUS_CGFKCHK_OFF_ST_OFF_ST_REF_COD");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ArrayList<ReferenceCodes> returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
				createParams(paramBean), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpOffStOffStRefCod
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfklkpOffStOffStRefCod(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCDSABUS_CGFKLKP_OFF_ST_OFF_ST_REF_COD");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ArrayList<ReferenceCodes> returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
				createParams(paramBean), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffStOffStRef
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfkchkOffStOffStRef(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCDSABUS_CGFKCHK_OFF_ST_OFF_ST_REF_2");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ArrayList<ReferenceCodes> returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
				createParams(paramBean), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpOffStOffStRef
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfklkpOffStOffStRef(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCDSABUS_CGFKLKP_OFF_ST_OFF_ST_REF_2");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ArrayList<ReferenceCodes> returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
				createParams(paramBean), columnRowMapper);
		return returnList;
	}

	@Override
	public String getoffSdSequence(final long offenderBookId, final String substanceType) {
		final String sql = getQuery("OCDSABUS_OFF_SD_PREINSERT");
		String returnValue = null;
		try {
			returnValue = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDERBOOKID", offenderBookId, "substanceType", substanceType), String.class);
		} catch (final Exception e) {
			logger.error("Exeception in getoffSdSequence", e);
			returnValue = "1";
			return returnValue;
		}
		return returnValue;
	}

	@Override
	public String getOffStSequnce(final long offenderBookId, final String substanceType) {
		final String sql = getQuery("OCDSABUS_OFF_ST_PREINSERT");
		String returnValue = null;
		try {
			returnValue = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDERBOOKID", offenderBookId, "substanceType", substanceType), String.class);
		} catch (final Exception e) {
			logger.error("Exeception in getOffStSequnce", e);
			returnValue = "1";
			return returnValue;
		}
		return returnValue;
	}

}
