package net.syscon.s4.inst.offenderissuestracking.impl;

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
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderGrievanceTxns;
import net.syscon.s4.inst.offenderissuestracking.OiuprresRepository;

/**
 * Class OiuprresRepositoryImpl
 */
@Repository
public class OiuprresRepositoryImpl extends RepositoryBase implements OiuprresRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiuprresRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> offenderGrievanceTxnsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("END_DATE", new FieldMapper("endDate")).put("PROPOSED_RESPONSE", new FieldMapper("proposedResponse"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("FINDING", new FieldMapper("finding"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("TXN_SEQ", new FieldMapper("txnSeq"))
			.put("GRIEV_LEVEL", new FieldMapper("grievLevel")).put("GRIEV_TYPE", new FieldMapper("grievType"))
			.put("START_DATE", new FieldMapper("startDate")).put("GRIEVANCE_ID", new FieldMapper("grievanceId"))
			.put("OFFICIAL_RESPONSE", new FieldMapper("officialResponse")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime")).put("TXN_TYPE", new FieldMapper("txnType"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("ASSIGNED_STAFF_ID", new FieldMapper("assignedStaffId")).put("STATUS", new FieldMapper("status"))
			.build();

	/**
	 * Creates new OiuprresRepositoryImpl class Object
	 */
	public OiuprresRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderGrievanceTxns
	 *
	 * @return List<OffenderGrievanceTxns>
	 *
	 * @
	 */
	public List<OffenderGrievanceTxns> prresExecuteQuery(final OffenderGrievanceTxns objSearchDao) {
		final String sql = getQuery("OIUPRRES_PRRES_FIND_OFFENDER_GRIEVANCE_TXNS");
		final RowMapper<OffenderGrievanceTxns> OffenderGrievanceTxnsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderGrievanceTxns.class, offenderGrievanceTxnsMapping);
		List<OffenderGrievanceTxns> returnList = new ArrayList<OffenderGrievanceTxns>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("grievanceId", objSearchDao.getGrievanceId(), "txnSeq", objSearchDao.getTxnSeq()),
					OffenderGrievanceTxnsRowMapper);
		} catch (Exception e) {
			logger.error("prresExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderGrievanceTxns List<OffenderGrievanceTxns>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer prresInsertOffenderGrievanceTxns(final List<OffenderGrievanceTxns> lstOffenderGrievanceTxns) {
		final String sql = getQuery("OIUPRRES_PRRES_INSERT_OFFENDER_GRIEVANCE_TXNS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderGrievanceTxns offenderGrievanceTxns : lstOffenderGrievanceTxns) {
			parameters.add(new BeanPropertySqlParameterSource(offenderGrievanceTxns));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderGrievanceTxns.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getProposedResponse
	 *
	 * @param params
	 *
	 */
	public OffenderGrievanceTxns getProposedResponse(final OffenderGrievanceTxns paramBean) {
		final String sql = getQuery("OIUPRRES_GET_PROPOSED_RESPONSE");
		final RowMapper<OffenderGrievanceTxns> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderGrievanceTxns.class, offenderGrievanceTxnsMapping);
		OffenderGrievanceTxns returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	@Override
	public Integer prresUpdateOffenderGrievanceTxns(final List<OffenderGrievanceTxns> updateList) {
		final String sql = getQuery("OIUPRRES_PRRES_UPDATE_OFFENDER_GRIEVANCE_TXNS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderGrievanceTxns offenderGrievanceTxns : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(offenderGrievanceTxns));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
}
