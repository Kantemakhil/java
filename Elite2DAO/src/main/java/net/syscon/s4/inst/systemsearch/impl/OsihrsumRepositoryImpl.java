package net.syscon.s4.inst.systemsearch.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.FormAccessibleForms;
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.systemsearch.OsihrsumRepository;
import net.syscon.s4.inst.systemsearch.VHistoricalBookings;
import oracle.jdbc.OracleTypes;

/**
 * Class OsihrsumRepositoryImpl
 */
@Repository
public class OsihrsumRepositoryImpl extends RepositoryBase implements OsihrsumRepository {

	private static Logger logger = LogManager.getLogger(OsihrsumRepositoryImpl.class);

	/**
	 * Creates new OsihrsumRepositoryImpl class Object
	 */
	public OsihrsumRepositoryImpl() {
	}

	/**
	 * Logger object used to print the log in the file
	 */
	private final Map<String, FieldMapper> formAccessibleFormsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ORIGINATING_FORM", new FieldMapper("originatingForm"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("DESTINATION_FORM", new FieldMapper("destinationForm"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime")).build();
	private final Map<String, FieldMapper> vHistoricalBookingsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("BOOKING_NO", new FieldMapper("bookingNo"))
			.put("IN_MOVEMENT_SEQ", new FieldMapper("inMovementSeq"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MODULE_NAME", new FieldMapper("moduleName")).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VHistoricalBookings
	 *
	 * @return List<VHistoricalBookings>
	 *
	 */
	public List<VHistoricalBookings> vHisBooExecuteQuery(final String rootOffenderId) {
		final String sql = getQuery("OSIHRSUM_VHISBOO_FIND_V_HISTORICAL_BOOKINGS");
		final RowMapper<VHistoricalBookings> vHistoricalBookingsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VHistoricalBookings.class, vHistoricalBookingsMapping);
		final ArrayList<VHistoricalBookings> returnList = (ArrayList<VHistoricalBookings>) namedParameterJdbcTemplate
				.query(sql, createParams("rootOffenderId", Integer.parseInt(rootOffenderId)), vHistoricalBookingsRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            FormAccessibleForms
	 *
	 * @return List<FormAccessibleForms>
	 *
	 */
	public List<FormAccessibleForms> fafExecuteQuery(String userName) {
		final String sql = getQuery("OSIHRSUM_FAF_FIND_FORM_ACCESSIBLE_FORMS");
		final RowMapper<FormAccessibleForms> formAccessibleFormsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				FormAccessibleForms.class, formAccessibleFormsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("userName",userName), formAccessibleFormsRowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstFormAccessibleForms
	 *            List<FormAccessibleForms>
	 *
	 * @return List<Integer>
	 *
	 */
	public FormAccessibleForms fafInsertFormAccessibleForms(final List<FormAccessibleForms> lstFormAccessibleForms) {
		final String sql = getQuery("OSIHRSUM_FAF_INSERT_FORM_ACCESSIBLE_FORMS");
		final FormAccessibleForms returnObj = new FormAccessibleForms();
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final FormAccessibleForms accessibleForms : lstFormAccessibleForms) {
			parameters.add(new BeanPropertySqlParameterSource(accessibleForms));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (lstFormAccessibleForms.size() == returnArray.length) {
				returnObj.setSealFlag("1");
			} else {
				returnObj.setSealFlag("0");
			}
		} catch (final Exception e) {
			returnObj.setSealFlag("insertError");
			logger.error("fafInsertFormAccessibleForms", e);
		}
		return returnObj;
	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstFormAccessibleForms
	 *            List<FormAccessibleForms>
	 *
	 */
	public FormAccessibleForms fafDeleteFormAccessibleForms(final List<FormAccessibleForms> lstFormAccessibleForms) {
		final String sql = getQuery("OSIHRSUM_FAF_DELETE_FORM_ACCESSIBLE_FORMS");
		int[] returnArray = new int[] {};
		final FormAccessibleForms returnObj = new FormAccessibleForms();
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final FormAccessibleForms formAccessibleForms : lstFormAccessibleForms) {
			parameters.add(new BeanPropertySqlParameterSource(formAccessibleForms));
		}
		try {
			String tableName = "FORM_ACCESSIBLE_FORMS";
			String whereCondition = "ORIGINATING_FORM = :originatingForm and DESTINATION_FORM = :destinationForm";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (lstFormAccessibleForms.size() == returnArray.length) {
				returnObj.setSealFlag("1");
			} else {
				returnObj.setSealFlag("0");
			}
		} catch (final Exception e) {
			returnObj.setSealFlag("deleteError");
			logger.error("fafInsertFormAccessibleForms", e);
		}
		return returnObj;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */

	public List<OmsModules> cgfkFafDestinationFormRecordGroup() {
		final String sql = getQuery("OSIHRSUM_FIND_CGFKFAFDESTINATIONFORM");
		final RowMapper<OmsModules> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
	}

	@Override
	public Images getImageData(final VHistoricalBookings searchBean) {
		final String sql = getQuery("GET_IMAGE_DATA");
		final RowMapper<Images> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Images.class, mMapping);
		Images images = null;
		if (searchBean != null) {
			images = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", searchBean.getOffenderBookId()), columnRowMapper);
		}
		return images;
	}

	@Override
	public Map<String, Object> checkDataAvaliable(final String pOrigForm, final String destinationForm,
			final BigDecimal offenderBookId, final BigDecimal rootOffenderId) {
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_ORIG_FORM", OracleTypes.VARCHAR),
				new SqlParameter("P_DEST_FORM", OracleTypes.VARCHAR), new SqlParameter("P_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_OFFENDER_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_DATA_AVAIL", OracleTypes.VARCHAR), };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMS_FORM_ACCESS").withProcedureName("CHECK_DATA_AVAILABLE")
				.declareParameters(sqlParameters);
		inParamMap.put("P_ORIG_FORM", pOrigForm);
		inParamMap.put("P_DEST_FORM", destinationForm);
		inParamMap.put("P_BOOK_ID", offenderBookId);
		inParamMap.put("P_OFFENDER_ID", rootOffenderId);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
		} catch (final Exception e) {
			logger.error("checkDataAvaliable" + e);
		}
		return returnObject;
	}

	@Override
	public String getCommentCur(final int offenderBookId, final int movementSeq) {
		final String sql = getQuery("GET_COMMENT_CUR");
		String comment = null;
		try {
			comment = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", offenderBookId, "movementSeq", movementSeq), String.class);
		} catch (final Exception e) {
			logger.error("getCommentCur", e);
		}
		return comment;
	}

	@Override
	public String getCommCommentCur(final VHistoricalBookings vHistoricalBookings, final String additionDate,
			final String additionTime) {
		final String sql = getQuery("GET_COMM_COMMENT_CUR");
		String comment = null;
		try {
			comment = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", vHistoricalBookings.getOffenderBookId(), "agyLocId",
							vHistoricalBookings.getAgyLocId(), "inDate", additionDate, "inTime", additionTime),
					String.class);
		} catch (final Exception e) {
			logger.error("getCommCommentCur", e);
		}
		return comment;
	}

	@Override
	public String closeCommentCur(final VHistoricalBookings vHistoricalBookings, final String additionDate,
			final String additionTime) {
		final String sql = getQuery("GET_COMM_CLOSE_COMMENT_CUR");
		String comment = null;
		try {
			comment = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId",
					vHistoricalBookings.getOffenderBookId(), "outDate", additionDate, "outTime", additionTime),
					String.class);
		} catch (final Exception e) {
			logger.error("closeCommentCur", e);
		}
		return comment;
	}

	@Override
	public String getOffenderReleaseComment(int offenderBookId,int movementSeq) {

		final String sql = getQuery("GET_OFFENDER_COMMENT_TEXT_OFFENDER_RELEASE_DETAILS");
		String comment = null;
		try {
			comment = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId,"movementSeq",movementSeq),
					String.class);
		} catch (final Exception e) {
			logger.error("getCommentCur", e);
		}
		return comment;
	}

}
