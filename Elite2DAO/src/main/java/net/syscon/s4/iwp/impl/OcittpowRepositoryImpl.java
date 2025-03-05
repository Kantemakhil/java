package net.syscon.s4.iwp.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.VExtOwnershipTransfer;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.iwp.OcittpowRepository;
import oracle.jdbc.OracleTypes;

@Repository
public class OcittpowRepositoryImpl extends RepositoryBase implements OcittpowRepository {

	/**
	 * Creates new OcittpowRepositoryImpl class Object
	 */
	private static Logger logger = LogManager.getLogger(OcittpowRepositoryImpl.class.getName());

	public OcittpowRepositoryImpl() {
	}

	private final Map<String, FieldMapper> dspDescriptionMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code")).build();

	private final Map<String, FieldMapper> agyLocIdMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code")).build();

	private final Map<String, FieldMapper> transferredOffendersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("DESCRIPTION", new FieldMapper("description"))
			.put("AGY_LOC_ID_FROM", new FieldMapper("agyLocIdFrom"))
			.put("DSP_DESCRIPTION", new FieldMapper("dspDescription"))
			.put("AGY_LOC_ID_TO", new FieldMapper("agyLocIdTo")).put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.build();

	/**
	 * Used to capture results from select query
	 */
	public List<AgencyLocations> dspDescriptionRecordGroup(final String currentCaseLoad) {
		final String sql = getQuery("OCITTPOW_FIND_DSP_DESCRIPTION_RECORD_GROUP");
		final RowMapper<AgencyLocations> dspDescriptionRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyLocations.class, dspDescriptionMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("currentCaseLoad", currentCaseLoad),
					dspDescriptionRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 */
	public List<VExtOwnershipTransfer> agyLocIdFromRecordGroup(final String agyLocIdFrom) {
		final String sql = getQuery("OCITTPOW_FIND_AGYLOCIDFROM");
		List<VExtOwnershipTransfer> returnList = new ArrayList<VExtOwnershipTransfer>();

		final RowMapper<VExtOwnershipTransfer> agyLocIdFromRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VExtOwnershipTransfer.class, agyLocIdMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("agyLocIdFrom", agyLocIdFrom),
					agyLocIdFromRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 */
	public List<VExtOwnershipTransfer> transferredOffendersExecuteQuery(final String code) {
		final String sql = getQuery("OCITTPOW_EXTOTNA_FIND_V_EXT_OWNERSHIP_TRANSFER");
		List<VExtOwnershipTransfer> returnList = new ArrayList<VExtOwnershipTransfer>();

		final RowMapper<VExtOwnershipTransfer> VExtOTRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VExtOwnershipTransfer.class, transferredOffendersMapping);

		returnList = namedParameterJdbcTemplate.query(sql, createParams("code", code), VExtOTRowMapper);
		for (VExtOwnershipTransfer veot : returnList) {
			veot.setStaffName(setStaffName(veot.getStaffFirstName(), veot.getStaffLastName()));
		}
		return returnList;
	}

	@Override
	public Integer agyLocIdToExecuteQuery(final String agyLocIdTo) {
		String query = getQuery("OCITTPOW_AGY_LOC_ID_TO_EXECUTE_QUERY");
		return namedParameterJdbcTemplate.queryForObject(query, createParams("agyLocIdTo", agyLocIdTo), Integer.class);
	}

	/* Post Query For Staff Name */
	private String setStaffName(String fastName, String lastName) {
		String query = getQuery("POST_QUERY_STAFF_NAME");
		String staffName = namedParameterJdbcTemplate.queryForObject(query,
				createParams("fastName", fastName, "lastName", lastName), String.class);
		return staffName;
	}

	/*
	 * Cancel FileTransfer Update Oparation
	 */
	@Override
	public Integer cancelFileTransferUpdateOparation(VExtOwnershipTransfer veot) {
		final String sql = getQuery("CANCEL_FILE_TRANSFER_UPDATE");

		List<VExtOwnershipTransfer> list = new ArrayList<VExtOwnershipTransfer>();
		list.add(veot);

		int[] returnArray = new int[] {};

		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VExtOwnershipTransfer veot1 : list) {
			parameters.add(new BeanPropertySqlParameterSource(veot1));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("cancelFileTransferUpdateOparation:", e);
		}
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * getProfileValue
	 */
	@Override
	public String getProfileValue() {
		final String sql = getQuery("OCITTPOW_GET_PROFILE_VALUE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
	}

	/**
	 * getting offenderId vlaue
	 */
	@Override
	public Integer getOffenderId(Long offenderBookId,String userName) {
		final String sql = getQuery("OCITTPOW_FIND_OFFENDER_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId,"USERID",userName),
				Integer.class);
	}

	/**
	 * getting CurTran vlaue
	 */
	public Integer getCurTran(Integer v_offenderId, String agyLocIdTo) {
		final String sql = getQuery("OCITTPOW_FIND_CUR_TRAN");
		Integer returnSeq = 0;
		try {
			returnSeq = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("v_offenderId", v_offenderId, "agyLocIdTo", agyLocIdTo), Integer.class);
		} catch (Exception e) {
			return returnSeq;
		}
		return returnSeq;
	}

	public Integer deleteExtOwnershipTransfer(List<VExtOwnershipTransfer> list) {
		final String sql = getQuery("OCITTPOW_DELETE_EXT_OWN_TRAF");

		int[] returnArray = new int[] {};

		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VExtOwnershipTransfer veot : list) {
			parameters.add(new BeanPropertySqlParameterSource(veot));
		}
		try {
			String tableName = "ext_ownership_transfer";
			String whereCondition = "offender_book_id = :offenderBookId AND ext_transfer_id = :extTransferId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("deleteExtOwnershipTransfer:", e);
		}
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	public Integer curExists(Long offenderBookId, Long extTransferId) {
		final String sql = getQuery("OCITTPOW_CUR_EXISTS");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderBookId", offenderBookId, "extTransferId", extTransferId), Integer.class);

	}

	public Integer curTran(Integer v_offenderId, String agyLocIdTo) {
		final String sql = getQuery("OCITTPOW_CUR_TRAN");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("v_offenderId", v_offenderId, "agyLocIdTo", agyLocIdTo), Integer.class);
	}

	public Integer curLoc(Integer v_offenderId, String agyLocIdFrom) {
		final String sql = getQuery("OCITTPOW_CUR_LOC");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("v_offenderId", v_offenderId, "agyLocIdFrom", agyLocIdFrom), Integer.class);
	}

	public String getVagylocIdTo(Long offenderBookId, Long extTransferId) {
		final String sql = getQuery("OCITTPOW_V_AGY_LOC_ID");
		String vagylocIdTo ="";
		try {
			vagylocIdTo = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", offenderBookId, "extTransferId", extTransferId),String.class);
		} catch (Exception e) {
			return vagylocIdTo;
		}
		return vagylocIdTo;
	}

	public Integer agylocIdToUpdate(VExtOwnershipTransfer veot) {
		final String sql = getQuery("OCITTPOW_AGY_LOC_ID_UPDATE");
		List<VExtOwnershipTransfer> list = new ArrayList<VExtOwnershipTransfer>();
		list.add(veot);

		int[] returnArray = new int[] {};

		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VExtOwnershipTransfer veot1 : list) {
			parameters.add(new BeanPropertySqlParameterSource(veot1));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("agylocIdToUpdate:", e);
		}
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/*
	 * Procedure pimsFileTrackingCancelTransfer
	 */
	@Override
	public Integer pimsFileTrackingCancelTransfer(Integer v_offenderId, Integer vOffenderFileSeq,
			VExtOwnershipTransfer veot) {
		int count = 0;
		Map<String, Object> returnObject = null;

		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[9];

		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_FILE_SEQ", OracleTypes.NUMBER),
				new SqlParameter("P_OFFENDER_ID", OracleTypes.NUMBER),
				new SqlParameter("P_FILE_TRANS_COMMENT", OracleTypes.VARCHAR),
				new SqlParameter("P_AGY_LOC_ID_FROM", OracleTypes.VARCHAR),
				new SqlParameter("P_AGY_LOC_ID_TO", OracleTypes.VARCHAR),
				new SqlParameter("P_STAFF_ID_FROM", OracleTypes.NUMBER),
				new SqlParameter("P_STAFF_ID_TO", OracleTypes.NUMBER),
				new SqlParameter("P_NON_OFFICER_FROM", OracleTypes.VARCHAR),
				new SqlParameter("P_NON_OFFICER_TO", OracleTypes.VARCHAR) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("PIMS_FILE_TRACKING").withProcedureName("CANCEL_TRANSFER")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_FILE_SEQ", vOffenderFileSeq);
		inParamMap.put("P_OFFENDER_ID", v_offenderId);
		inParamMap.put("P_FILE_TRANS_COMMENT", null);
		inParamMap.put("P_AGY_LOC_ID_FROM", veot.getAgyLocIdFrom());
		inParamMap.put("P_AGY_LOC_ID_TO", null);
		inParamMap.put("P_STAFF_ID_FROM", veot.getAssStaffId());
		inParamMap.put("P_STAFF_ID_TO", null);
		inParamMap.put("P_NON_OFFICER_FROM", null);
		inParamMap.put("P_NON_OFFICER_TO", null);

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			if (returnObject.size() == 0) {
				count = 1;
			}
		} catch (Exception e) {
			count = 0;
		}
		return count;
	}
}
