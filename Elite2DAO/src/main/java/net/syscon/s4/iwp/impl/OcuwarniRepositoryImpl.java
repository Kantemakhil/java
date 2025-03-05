package net.syscon.s4.iwp.impl;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.inst.offenderissuestracking.beans.VStaffLocationRoles;
import net.syscon.s4.iwp.OcuwarniRepository;
import oracle.jdbc.internal.OracleTypes;

/**
 * Class OcuwarniRepositoryImpl
 */
@Repository
public class OcuwarniRepositoryImpl extends RepositoryBase implements OcuwarniRepository {
	private static Logger logger = LogManager.getLogger(OcuwarniRepositoryImpl.class.getName());

	/**
	 * Creates new OcuwarniRepositoryImpl class Object
	 */
	public OcuwarniRepositoryImpl() {
		// OcuwarniRepositoryImpl
	}

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description ")).put("CODE", new FieldMapper("code")).build();
	private final Map<String, FieldMapper> vStaffLocationRolesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code")).build();
	private final Map<String, FieldMapper> offenderCaseNotesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("FIRST_NAME", new FieldMapper("firstName")).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderCaseNotes
	 *
	 * @return List<OffenderCaseNotes>
	 *
	 * @throws SQLException
	 */
	public List<OffenderCaseNotes> offCaseNotesExecuteQuery(final OffenderCaseNotes objSearchDao) {
		final String sql = getQuery("OCUWARNI_OFFCASENOTES_FIND_OFFENDER_CASE_NOTES");
		final RowMapper<OffenderCaseNotes> offenderCaseNotesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCaseNotes.class, offenderCaseNotesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("offenderBookId", objSearchDao.getOffenderBookId()), offenderCaseNotesRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgConSubTypeRecordGroup() {
		final String sql = getQuery("OCUWARNI_FIND_RGCONSUBTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MM>
	 */
	public List<VStaffLocationRoles> rgStaffNameRecordGroup(final Long offenderBookId, final String caseLoadId, final String agyLocId) {
		final String sql = getQuery("OCUWARNI_FIND_RGSTAFFNAME");
		final RowMapper<VStaffLocationRoles> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, VStaffLocationRoles.class,
				vStaffLocationRolesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("OFFENDERBOOKID", offenderBookId, "CASELOADID",
					caseLoadId, "PAGYLOCID", agyLocId, "CASELOADID", caseLoadId), mMRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

	public String offCaseNotesExecutePostQuery(final OffenderCaseNotes offenderCaseNotes) {
		Map<String, Object> returnObject = null;
		String pNbtNoteSourceCodeDesc = null;
		final Map<String, Object> inParamMap = new HashMap<>();
		final SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("P_NOTE_SOURCE_CODE", OracleTypes.VARCHAR),
				new SqlParameter("P_CASE_NOTE_SUB_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_STAFF_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_NBT_NOTE_SOURCE_CODE_DESC", OracleTypes.VARCHAR),
				new SqlOutParameter("P_NBT_CASE_NOTE_SUB_TYPE_DESC", OracleTypes.VARCHAR),
				new SqlOutParameter("P_NBT_STAFF_NAME_DESC", OracleTypes.VARCHAR), };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OCDENFOR").withProcedureName("POP_OCUWARNI").declareParameters(sqlParameters);
		inParamMap.put("P_NOTE_SOURCE_CODE", offenderCaseNotes.getNoteSourceCode());
		inParamMap.put("P_CASE_NOTE_SUB_TYPE", offenderCaseNotes.getCaseNoteSubType());
		inParamMap.put("P_STAFF_ID", offenderCaseNotes.getStaffId());
		inParamMap.put("P_NBT_NOTE_SOURCE_CODE_DESC", offenderCaseNotes.getpNbtNoteSourceCodeDesc());
		inParamMap.put("P_NBT_CASE_NOTE_SUB_TYPE_DESC", offenderCaseNotes.getpNbtCaseNoteSubTypeDesc());
		inParamMap.put("P_NBT_STAFF_NAME_DESC", offenderCaseNotes.getpNbtStaffNameDesc());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.withoutProcedureColumnMetaDataAccess().execute(inParameter);
			if (Optional.ofNullable(returnObject.get("P_NBT_NOTE_SOURCE_CODE_DESC")).isPresent()) {
				pNbtNoteSourceCodeDesc = returnObject.get("P_NBT_NOTE_SOURCE_CODE_DESC").toString();
			}
		} catch (final Exception e) {
			logger.error("offCaseNotesExecutePostQuery", e);
			return null;
		}
		return pNbtNoteSourceCodeDesc;
	}

}
