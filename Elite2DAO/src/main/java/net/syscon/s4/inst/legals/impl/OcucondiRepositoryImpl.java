package net.syscon.s4.inst.legals.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.casemanagement.beans.OffenderCaseConditions;
import net.syscon.s4.inst.legals.OcucondiRepository;
import net.syscon.s4.inst.legals.beans.BondType;
import net.syscon.s4.inst.legals.beans.Category;
import net.syscon.s4.inst.legals.beans.CommonLov;
import net.syscon.s4.inst.legals.beans.Condition;
import net.syscon.s4.inst.legals.beans.OffenderCondTransfer;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;
import oracle.jdbc.internal.OracleTypes;

@Repository
public class OcucondiRepositoryImpl extends RepositoryBase implements OcucondiRepository{

	private static Logger logger = LogManager.getLogger(OcucondiRepositoryImpl.class);
	
	private final Map<String, FieldMapper> categoryMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("IDENTIFIER_TYPE", 	new FieldMapper("description"))			
			.put("CODE",  			    new FieldMapper("code"))
			.put("active_flag",  			    new FieldMapper("activeFlag"))
			.build();	
	
	private final Map<String, FieldMapper> conditionTypeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_DATETIME", 	new FieldMapper("description"))			
			.put("CODE",  			    new FieldMapper("code"))
			.put("program",  			    new FieldMapper("program"))
			.put("PROGRAM_METHOD",  			    new FieldMapper("programMethod"))
			.put("COURSEPROFILESACTS",  			    new FieldMapper("courseProfilesActs"))
			.put("APPOINTMENTSACTS",  			    new FieldMapper("appointmentsActs"))
			.put("APPOINTMENTSSA",  			    new FieldMapper("appointmentsSa"))
			.put("allocation_flag", new FieldMapper("allocationFlag"))
			.put("plan_of_action_flag", new FieldMapper("planOfActionFlag"))
			.put("COMM_PROJ_ALLOC_FLAG", new FieldMapper("commProjAllocFlag"))
			.put("assigned_officer", new FieldMapper("assignedOfficer"))
			.build();
	
	private final Map<String, FieldMapper> conditionGridMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("COMM_CONDITION_CODE", new FieldMapper("conditionTypeCode"))
			.put("LENGTH", 	new FieldMapper("length"))	
			.put("LENGTH_UNIT", 	new FieldMapper("lengthUnit"))	
			.put("START_DATE", 	new FieldMapper("startDate"))
			.put("EXPIRY_DATE", 	new FieldMapper("endDate"))	
			.put("OTHER_PROGRAM", 	new FieldMapper("program"))	
			.put("PROGRAM_ID", 	new FieldMapper("programId"))	
			.put("LONG_COMMENT_TEXT", 	new FieldMapper("commentText"))	
			.put("OFFENDER_SENT_CONDITION_ID", 	new FieldMapper("sentConditionId"))	
			.put("CURFEW_START_TIME", 	new FieldMapper("curfewStartTime"))
			.put("CURFEW_END_TIME", 	new FieldMapper("curfewEndTime"))	
			.put("FINANCIAL_TOTAL_AMOUNT", 	new FieldMapper("finTotalAmount"))	
			.put("NON_ASSOCIATION_TEXT", 	new FieldMapper("nonAssociationText"))	
			.put("ACTIVITY_CODE", 	new FieldMapper("prohibitedContact"))
			.put("COMMENT_TEXT", 	new FieldMapper("sortComment"))
			.put("CONDITION_STATUS",  new FieldMapper("status"))
			.build();
	
	private final Map<String, FieldMapper> condStatusMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("NBT_CASE_STATUS", 			new FieldMapper("description"))			
			.put("CASE_STATUS",  			new FieldMapper("code"))
			.build(); 
	
	private final Map<String, FieldMapper> sentenceCategoryMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 			new FieldMapper("description"))		
			.put("SENTENCE_CATEGORY",  				new FieldMapper("code"))
			.build();
	
	private final Map<String, FieldMapper> commonLovMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.put("ID", 			new FieldMapper("id"))		
			.put("CODE",  				new FieldMapper("code"))
			.build();
	
	@Override
	public List<BondType> getCategory() {
		final String sql = getQuery("GET_CATEGORY");
		final RowMapper<BondType> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, BondType.class, categoryMapping);
		List<BondType> returnObj = new ArrayList<>();
		try {
			returnObj = namedParameterJdbcTemplate.query(sql, columnRowMapper);
		} catch (Exception e) {
			logger.error("getCategory" + e);
		}
		return returnObj;
	}

	@Override
	public List<BondType> getUnit() {
		final String sql = getQuery("GET_UNIT");
		final RowMapper<BondType> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, BondType.class, categoryMapping);
		List<BondType> returnObj = new ArrayList<>();
		try {
			returnObj = namedParameterJdbcTemplate.query(sql, columnRowMapper);
		} catch (Exception e) {
			logger.error("getUnit" + e);
		}
		return returnObj;
	}

	@Override
	public List<CommonLov> getProgram() {
		final String sql = getQuery("GET_PROGRAM");
		final RowMapper<CommonLov> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, CommonLov.class,
				commonLovMapping);
		List<CommonLov> returnObj = new ArrayList<>();
		try {
			returnObj = namedParameterJdbcTemplate.query(sql, columnRowMapper);
		} catch (Exception e) {
			logger.error("getProgram" + e);
		}
		return returnObj;
	}

	@Override
	public List<OffenderSentConditions> getConditionTypeGrid(OffenderSentConditions searchBean) {
		String sql = null;
		if (searchBean != null && searchBean.getSealFlag() != null && "Y".equals(searchBean.getSealFlag())) {
			sql = getQuery("GET_CONDITION_TYPE_GRID");
		} else {
			sql = getQuery("GET_CONDITION_TYPE_GRID_QUERY");
		}
		final RowMapper<OffenderSentConditions> conditionTypeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSentConditions.class, conditionTypeMapping);
		String preparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (searchBean != null) {
			sqlQuery.append(" WHERE ");

			if (searchBean.getSentenceSeq() != null) {
				sqlQuery.append(" OSC.SENTENCE_SEQ = :SENTENCE_SEQ" + " AND ");
				inParameterSource.addValue("SENTENCE_SEQ", searchBean.getSentenceSeq());
			}

			if (searchBean.getCommConditionType() != null) {
				sqlQuery.append(" OSC.COMM_CONDITION_TYPE = :COMM_CONDITION_TYPE" + " AND ");
				inParameterSource.addValue("COMM_CONDITION_TYPE", searchBean.getCommConditionType());
			}
			if (searchBean.getCategoryType() != null) {
				sqlQuery.append(" OSC.CATEGORY_TYPE = :CATEGORY_TYPE" + " AND ");
				inParameterSource.addValue("CATEGORY_TYPE", searchBean.getCategoryType());
			}
			if (searchBean.getOffenderBookId() != 0) {
				sqlQuery.append("OSC.OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID" + " AND ");
				inParameterSource.addValue("OFFENDER_BOOK_ID", searchBean.getOffenderBookId());

			}
			if (searchBean.getObjectId() != null) {
				sqlQuery.append(" OSC.OBJECT_ID = :OBJECT_ID" + " AND ");
				inParameterSource.addValue("OBJECT_ID", searchBean.getObjectId());

			}
			if (searchBean.getObjectType() != null) {
				sqlQuery.append(" OSC.OBJECT_TYPE = :OBJECT_TYPE" + " AND ");
				inParameterSource.addValue("OBJECT_TYPE", searchBean.getObjectType());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		if (searchBean != null && searchBean.getSealFlag() != null && "Y".equals(searchBean.getSealFlag())) {
			preparedSql = preparedSql + " ORDER BY category_type";
		} else {
			preparedSql = preparedSql + "  ORDER BY category_type";
		}

		return namedParameterJdbcTemplate.query(preparedSql, inParameterSource, conditionTypeRowMapper);
	}

	@Override
	public List<Condition> getConditionGrid(Condition condition) {
		List<Condition> conditionGrid = new ArrayList<>();
		final String sql = getQuery("GET_CONDITION_GRID");
		final RowMapper<Condition> conditionTypeRowMapper = Row2BeanRowMapper.makeMapping(sql, Condition.class,
				conditionGridMapping);
		try {
			conditionGrid = namedParameterJdbcTemplate.query(sql,
					createParams("sentenceSeq", condition.getSentenceSeq(), "conditionTypeCode",
							condition.getConditionTypeCode(), "categoryTypeCode", condition.getCategoryTypeCode(),
							"offenderBookId", condition.getOffenderBookId()),
					conditionTypeRowMapper);
		} catch (Exception e) {
			logger.error("getConditionGrid" + e);
		}

		return conditionGrid;
	}
	@Override
	public List<OffenderSentConditions> getCompleteConditionGrid(OffenderSentConditions condition) {
		List<OffenderSentConditions> conditionGrid = new ArrayList<>();
		final String sql = getQuery("GET_COMPLETE_CONDITION_GRID");
		final RowMapper<OffenderSentConditions> conditionTypeRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderSentConditions.class,
				conditionGridMapping);
		try {
			conditionGrid = namedParameterJdbcTemplate.query(sql,
					createParams("sentenceSeq", condition.getSentenceSeq(), "conditionTypeCode",
							condition.getCommConditionType(), "offenderBookId", condition.getOffenderBookId()),
					conditionTypeRowMapper);
		} catch (Exception e) {
			logger.error("getConditionGrid" + e);
		}

		return conditionGrid;
	}

	@Override
	public List<CommonLov> getConditionLov(Condition condition) {
		List<CommonLov> conditionGrid = new ArrayList<>();
		final String sql = getQuery("GET_CONDITION_LOV");
		final RowMapper<CommonLov> conditionTypeRowMapper = Row2BeanRowMapper.makeMapping(sql, CommonLov.class,
				categoryMapping);
		try {
			conditionGrid = namedParameterJdbcTemplate.query(sql, createParams("condition",
					condition.getConditionTypeCode(), "catogry", condition.getCategoryTypeCode()),
					conditionTypeRowMapper);

		} catch (Exception e) {
			logger.error("getConditionLov" + e);
		}

		return conditionGrid;
	}

	@Override
	public List<CommonLov> getConditionLov(String condition, String catogry) {
		List<CommonLov> conditionGrid = new ArrayList<>();
		final String sql = getQuery("GET_CONDITION_LOV");
		final RowMapper<CommonLov> conditionTypeRowMapper = Row2BeanRowMapper.makeMapping(sql, CommonLov.class,
				categoryMapping);
		try {
			conditionGrid = namedParameterJdbcTemplate.query(sql,
					createParams("condition", condition, "catogry", catogry), conditionTypeRowMapper);

		} catch (Exception e) {
			logger.error("getConditionLov", e);
		}

		return conditionGrid;
	}

	@Override
	public Integer getPreInsertSentSentenseId() {
		Integer returnObj = null;
		final String sql = getQuery("PREINSERT_ORDER_ID");
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		return returnObj;
	}

	@Override
	public List<Condition> populateCaseStatus() {
		List<Condition> typeList = new ArrayList<>();
		final String sql = getQuery("CONDITION_STATUS");
		final RowMapper<Condition> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql, Condition.class,
				condStatusMapping);
		try {
			typeList = namedParameterJdbcTemplate.query(sql, referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("populateCaseStatus" + e);
		}
		return typeList;
	}

	@Override
	public String fetchOrderTypeDesc(String conditionType) {
		String desc = null;
		final String sql = getQuery("FETCH_ORDER_TYPE_DESC");
		desc = namedParameterJdbcTemplate.queryForObject(sql, createParams("conditionType", conditionType),
				String.class);
		return desc;
	}

	@Override
	public Condition populateProgramComment(Condition condition) {
		List<Condition> typeList = new ArrayList<>();
		Condition getProComment = new Condition();
		final String sql = getQuery("PROGRAM_COMMENT");
		final RowMapper<Condition> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql, Condition.class,
				conditionGridMapping);
		try {
			typeList = namedParameterJdbcTemplate.query(sql,
					createParams("sentConditionId", condition.getSentConditionId()), referenceCodeRowMapper);
			if (typeList.size() > 0) {
				getProComment = typeList.get(0);
			}
		} catch (Exception e) {
			logger.error("populateProgramComment" + e);
		}
		return getProComment;
	}

	@Override
	public List<Category> populateSentencesCategory() {
		List<Category> sentenceCategoryList = new ArrayList<>();
		final String sql = getQuery("SENTENCE_CATEGORY_LOV");
		final RowMapper<Category> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql, Category.class,
				sentenceCategoryMapping);
		try {
			sentenceCategoryList = namedParameterJdbcTemplate.query(sql, referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("populateSentencesCategory" + e);
		}
		return sentenceCategoryList;
	}

	@Override
	public List<CommonLov> populateProhibitedLov() {
		List<CommonLov> sentenceCategoryList = new ArrayList<>();
		final String sql = getQuery("PROHIBITED_LOV");
		final RowMapper<CommonLov> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql, CommonLov.class,
				commonLovMapping);
		try {
			sentenceCategoryList = namedParameterJdbcTemplate.query(sql, referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("populateProhibitedLov" + e);
		}
		return sentenceCategoryList;

	}

	@Override
	public Long getProgramId(String program) {
		Long getId = null;
		final String sql = getQuery("GET_PROGRAM_ID");
		getId = namedParameterJdbcTemplate.queryForObject(sql, createParams("code", program), Long.class);
		return getId;
	}

	@Override
	public String getProgramCode(Long id) {
		String getCode = null;
		final String sql = getQuery("GET_PROGRAM_CODE");
		getCode = namedParameterJdbcTemplate.queryForObject(sql, createParams("id", id), String.class);
		return getCode;
	}

	@Override
	public String insertoffSentConditionData(List<OffenderSentConditions> insertList) {
		String getResult = null;
		final String sql = getQuery("OCUCONDI_INSERT_OFFENDER_SENT_CONDITIONS");

		int[] returnArray = {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderSentConditions list : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("insertoffSentConditionData" + e);
		}
		if (returnArray.length > 0) {
			getResult = "1";
		}
		return getResult;
	}

	@Override
	public String updateoffsentConData(List<OffenderSentConditions> updatetList) {
		String liReturn = null;
		final String sql = getQuery("OCUCONDI_UPDATE_OFFENDER_SENT_CONDITIONS");

		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderSentConditions list : updatetList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("updateoffsentConData" + e);
		}
		if (returnArray.length > 0) {
			liReturn = "1";
		}
		return liReturn;
	}

	@Override
	public String deleteOffSenConditionData(List<OffenderSentConditions> deleteList) {
		final String sql = getQuery("DELETE_CONDITION_DATA");
		String liReturn = null;
		int[] returnArray = {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderSentConditions list : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			batchUpdatePreDeletedRows("offender_sent_conditions", "OFFENDER_SENT_CONDITION_ID =:offenderSentConditionId", parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			String error = "Error : " + e.getMessage();
			if (error.contains("OMS_OWNER")) {
				error = error.substring(error.indexOf("OMS_OWNER."), error.indexOf(") violated"))
						.replaceFirst("OMS_OWNER.", "");
				return getFkTableNames(error);
			}
		}
		if (returnArray.length > 0) {
			liReturn = "1";
		}
		return liReturn;
	}
	@Override
	public String deleteTypeConditionData(List<OffenderSentConditions> deleteList) {
		final String sql = getQuery("OCUCONDI_DELETE_CONDITION_TYPE_DATA");
		String liReturn = null;
		int[] returnArray = {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderSentConditions list : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			batchUpdatePreDeletedRows("offender_sent_conditions", "CATEGORY_TYPE =:categoryType and COMM_CONDITION_TYPE=:commConditionType and OFFENDER_BOOK_ID=:offenderBookId and OBJECT_ID = :objectId", parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			String error = "Error : " + e.getMessage();
			if (error.contains("OMS_OWNER")) {
				error = error.substring(error.indexOf("OMS_OWNER."), error.indexOf(") violated"))
						.replaceFirst("OMS_OWNER.", "");
				return getFkTableNames(error);
			}
		}
		if (returnArray.length > 0) {
			liReturn = "1";
		}
		return liReturn;
	}
	@Override
	public Integer isDuplicateDetailAcp(OffenderSentConditions bean) {
		final String sql = getQuery("IS_DUPLICATE_DETAIL_ACP");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("p_program_id", bean.getProgramId(), "p_sentence_seq", bean.getSentenceSeq(),
						"p_comm_cond_type", bean.getCommConditionType(), "p_offender_book_id",
						bean.getOffenderBookId()),
				Integer.class);
	}

	@Override
	public List<OffenderSentConditions> getDefaultConditions(OffenderSentConditions sentConBean) {
		final String sql = getQuery("GET_DEFAULT_CONDITIONS");
		final RowMapper<OffenderSentConditions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSentConditions.class, conditionTypeMapping);
		List<OffenderSentConditions> returnObj = new ArrayList<>();
		try {
			returnObj = namedParameterJdbcTemplate.query(sql, createParams("p_comm_condition_type",
					sentConBean.getCommConditionType(), "p_category_type", sentConBean.getCategoryType()),
					columnRowMapper);
		} catch (Exception e) {
			logger.error("getDefaultConditions" + e);
		}
		return returnObj;
	}

	@Override
	public Integer getLvCnt(OffenderSentConditions bean) {
		final String sql = getQuery("GET_LV_CNT");
		return namedParameterJdbcTemplate
				.queryForObject(sql,
						createParams("comm_condition_code", bean.getCommConditionCode(), "category_type",
								bean.getCategoryType(), "comm_condition_type", bean.getCommConditionType()),
						Integer.class);
	}

	@Override
	public String chkAccountStatus(final String caseloadId, final Long rootOffenderId) {
		SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("P_CSLD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_OFFENDER_ID", OracleTypes.NUMBER) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("CHK_ACCOUNT_STATUS").declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<>();
		inParamMap.put("P_CSLD_ID", caseloadId);
		inParamMap.put("P_OFFENDER_ID", rootOffenderId);

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);

		if (returnObject.get("P_OPEN_AN_ACCOUNT") != null) {
			return returnObject.get("P_OPEN_AN_ACCOUNT").toString();
		}
		return "";
	}

	@Override
	public String getLvCaseInfoNumber(OffenderSentConditions bean) {
		final String sql = getQuery("GET_LV_CASE_INFO_NUMBER");
		String caseInfoNo = null;
		try {
			caseInfoNo = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offender_book_id", bean.getOffenderBookId(), "sentence_seq", bean.getSentenceSeq()),
					String.class);
		} catch (Exception e) {
			caseInfoNo = null;
		}
		return caseInfoNo;
	}

	@Override
	public Integer createConditionDeductions(OffenderSentConditions bean) {
		SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("P_CSLD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_OFF_ID", OracleTypes.NUMBER),
				new SqlParameter("P_INFO_NUMBER", OracleTypes.VARCHAR),
				new SqlParameter("P_CONDITION_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_CONDITION_CODE", OracleTypes.VARCHAR),
				new SqlParameter("P_CON_CATEGORY_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_TOTAL_AMOUNT", OracleTypes.NUMBER), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("DEDUCTIONS").withProcedureName("CREATE_CONDITION_DEDUCTIONS")
				.declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<>();
		inParamMap.put("P_CSLD_ID", bean.getCaseloadId());
		inParamMap.put("P_OFF_ID", bean.getRootOffenderId());
		inParamMap.put("P_INFO_NUMBER", bean.getCaseInfoNumber());
		inParamMap.put("P_CONDITION_TYPE", bean.getCommConditionType());
		inParamMap.put("P_CONDITION_CODE", bean.getCommConditionCode());
		inParamMap.put("P_CON_CATEGORY_TYPE", bean.getCategoryType());
		inParamMap.put("P_TOTAL_AMOUNT", bean.getFinancialTotalAmount());

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);
			if (returnObject.size() > 0) {
				return 1;
			} else {
				return 0;
			}

		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public Integer getLvRootoffenderId(OffenderSentConditions bean) {
		final String sql = getQuery("GET_LV_ROOT_OFFENDER_ID");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offender_book_id", bean.getOffenderBookId(),"USERID",bean.getCreateUserId()), Integer.class);
	}

	public String getFkTableNames(final String constraintName) {
		final String sql = getQuery("OCUCONDI_GET_FK_TABLE_NAMES");
		String tableName = null;
		try {
			tableName = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_CONSTRAINT_NAME", constraintName), String.class);
		} catch (Exception e) {
			logger.error("getFkTableNames", e);
			tableName = null;
			return tableName;
		}
		return tableName;
	}
	
	@Override
	public Integer postInsertIntoOffenderCondTrf(List<OffenderCondTransfer> insertList) {
		Integer returnVal = null;
		final String sql = getQuery("OCUCONDI_POST_INSERT_OFFENDER_COND_TRANSFERS");

		int[] returnArray = {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderCondTransfer list : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Exception in"+ this.getClass().getName() +  "postInsertIntoOffenderCondTrf" + e);
		}
		if (returnArray.length > 0) {
			returnVal = 1;
		}
		return returnVal;
	}
	
	@Override
	public long preInsertGetSeq() {
		final String sql = getQuery("OCUCONDI_PRE_INSERT_GET_SEQ");
		long seq = 0;
		try {
			seq = namedParameterJdbcTemplate.queryForObject(sql, createParams(), long.class);
		}catch (Exception e) {
			logger.error("Exception in"+ this.getClass().getName() +  "preInsertGetSeq" + e);
		}
		return seq;
	}
	
	@Override
	public List<OffenderCondTransfer> getCondTransferData(BigDecimal offenderSentConditionId) {
		final String sql = getQuery("OCUCONDI_GET_COND_TRANSFER_DATA");
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("offenderSentConditionId",offenderSentConditionId), new RowMapperResultSetExtractor<OffenderCondTransfer>(new BeanPropertyRowMapper<OffenderCondTransfer>(OffenderCondTransfer.class)));
		} catch (Exception e) {
			logger.error("getCondTransferData : ",e);
			return Collections.emptyList();
		}
	}
	
	@Override
	public String deleteOffederCondTransfer(List<OffenderCondTransfer> deleteList) {
		final String sql = getQuery("OCUCONDI_DELETE_OFFENDER_COND_TRANSFER");
		String liReturn = null;
		int[] returnArray = {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderCondTransfer list : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			batchUpdatePreDeletedRows("OFFENDER_COND_TRANSFER", "con_transfer_id  = :conTransferId", parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in deleteOffederCondTransfer"+e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {;
			logger.error("deleteOffederCondTransfer : ",e);
		}
		if (returnArray.length > 0) {
			liReturn = "1";
		}
		return liReturn;
	}

	@Override
	public List<OffenderCaseConditions> getCasePlanConditions(BigDecimal offSentConditionId) {
		final String sql = getQuery("OCUCONDI_GET_CASEPLAN_CONDITIONS");
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("offenderSentConditionId",offSentConditionId), new RowMapperResultSetExtractor<OffenderCaseConditions>(new BeanPropertyRowMapper<OffenderCaseConditions>(OffenderCaseConditions.class)));
		} catch (Exception e) {
			logger.error("getCasePlanConditions : ",e);
			return Collections.emptyList();
		}
	}
	
	@Override
	public String deleteCasePlanConditions(List<OffenderCaseConditions> offenderCaseConditionList) {
		final String sql = getQuery("OCUCONDI_DELETE_CASEPLAN_CONDITIONS");
		String liReturn = null;
		int[] returnArray = {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderCaseConditions list : offenderCaseConditionList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			batchUpdatePreDeletedRows("offender_case_conditions", "off_case_cond_id  = :offCaseCondId", parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in deleteCasePlanConditions"+e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {;
			logger.error("deleteCasePlanConditions : ",e);
		}
		if (returnArray.length > 0) {
			liReturn = "1";
		}
		return liReturn;
	}

	@Override
	public String findOffenderAgyLocId(long offenderBookId) {
		final String sql = getQuery("GET_AGY_LOC_ID");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderBookId",offenderBookId), String.class);
	}
}
