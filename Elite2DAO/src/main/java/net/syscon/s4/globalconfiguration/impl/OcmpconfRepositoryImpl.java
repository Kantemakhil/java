package net.syscon.s4.globalconfiguration.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.FormBuilderBean;
import net.syscon.s4.common.beans.OcmpconfUiBean;
import net.syscon.s4.common.beans.OdynfrmSubmitDataBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.globalconfiguration.OcmpconfRepository;
import net.syscon.s4.im.beans.IwpDocuments;
import net.syscon.s4.im.beans.LegalUpdateUsages;
import net.syscon.s4.inst.legals.beans.OffenderPayrolEvent;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalUpdateReasons;
import net.syscon.s4.inst.schedules.bean.CourtEvents;

/**
 * Class OumsypflRepositoryImpl
 */
@Repository
public class OcmpconfRepositoryImpl extends RepositoryBase implements OcmpconfRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmpconfRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> referCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ",                  new FieldMapper("listSeq"))
			.put("CODE",                      new FieldMapper("code"))
			.put("DESCRIPTION",               new FieldMapper("description"))
			.put("PROFILE_TYPE",              new FieldMapper("profileType"))
			.build();
	private final Map<String, FieldMapper> uiBeanMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("COMP_ID",                  new FieldMapper("compId"))
			.put("COMP_TYPE",                new FieldMapper("compType"))
			.put("CREATED_BY",               new FieldMapper("createdBy"))
			.put("MODIFIED_BY",              new FieldMapper("modifiedBy"))
			.put("CREATE_DATE",              new FieldMapper("createDate"))
			.put("MODIFY_DATE",              new FieldMapper("modifyDate"))
			.put("COMP_CONFIG",              new FieldMapper("compConfig"))
			.put("COMP_CONFIG_DEF",          new FieldMapper("compConfigDef"))
			.build();
	
	private final Map<String, FieldMapper> condMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CONDITION_STATUS",                   new FieldMapper("conditionStatus"))
			.put("EXPIRY_DATE",                        new FieldMapper("expiryDate"))              
			.put("START_DATE",                         new FieldMapper("startDate"))               
			.put("OFFENDER_SENT_CONDITION_ID",         new FieldMapper("offenderSentConditionId"))
			.put("allocation_flag", new FieldMapper("allocationFlag"))
			.put("comm_condition_type", new FieldMapper("commConditionType"))
			.put("offender_book_id",new FieldMapper("offenderBookId"))
			.put("sentence_seq", new FieldMapper("sentenceSeq")).build();   

	public OcmpconfRepositoryImpl() {
	}

	public List<OcmpconfUiBean> loadData() {
		final String sql = getQuery("OCMPCONF_LOAD_DATA");
		final RowMapper<OcmpconfUiBean> uiBeanMapper = Row2BeanRowMapper.makeMapping(sql, OcmpconfUiBean.class,
				uiBeanMapping);
		final ArrayList<OcmpconfUiBean> returnList = (ArrayList<OcmpconfUiBean>) namedParameterJdbcTemplate.query(sql, uiBeanMapper);
		return returnList;
	}

	public Integer saveData(List<OcmpconfUiBean> compData) {
		final String sql = getQuery("OCMPCONF_UPDATE_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OcmpconfUiBean component : compData) {
			parameters.add(new BeanPropertySqlParameterSource(component));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (compData.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public Integer saveFormbuilderData(FormBuilderBean formBean) {
		final String sql = getQuery("DYNAMIC_FORM_INSERT");
		final String audit_sql = getQuery("DYNAMIC_FORM_AUDIT_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(formBean));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		namedParameterJdbcTemplate.batchUpdate(audit_sql, parameters.toArray(new SqlParameterSource[0]));
		if (1 == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	@Override
	public List<FormBuilderBean> loadFormbuilderData() {
		final String sql = getQuery("DYNAMIC_FORM_GET");
		final RowMapper<FormBuilderBean> uiBeanMapper = Row2BeanRowMapper.makeMapping(sql, FormBuilderBean.class,
				uiBeanMapping);
		final ArrayList<FormBuilderBean> returnList = (ArrayList<FormBuilderBean>) namedParameterJdbcTemplate.query(sql, uiBeanMapper);
		return returnList;
	}

	@Override
	public int insertOdynFrmData(List<FormBuilderBean> insertList) {
		final String sql = getQuery("DYNAMIC_FORM_INSERT");
		final String audit_sql = getQuery("DYNAMIC_FORM_AUDIT_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final FormBuilderBean formBuilderBean : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(formBuilderBean));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		namedParameterJdbcTemplate.batchUpdate(audit_sql, parameters.toArray(new SqlParameterSource[0]));
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public int updateOdynFrmData(List<FormBuilderBean> updateList) {
		final String sql = getQuery("DYNAMIC_FORM_UPDATE");
		final String audit_sql = getQuery("DYNAMIC_FORM_AUDIT_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final FormBuilderBean formBuilderBean : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(formBuilderBean));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		namedParameterJdbcTemplate.batchUpdate(audit_sql, parameters.toArray(new SqlParameterSource[0]));
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}
	
	@Override
	public int createTable(String formName) {
		final String createTableSql = getQuery("DYNAMIC_FORM_CREATE_TABLE").replace("%formName%", formName.toLowerCase());
		
		try {
			jdbcTemplate.execute(createTableSql);
		}
		catch(Exception ex) {
			return 0;
		}
			return 1;
	}

	@Override
	public OdynfrmSubmitDataBean getFormData(OdynfrmSubmitDataBean odynfrmSubmitDataBean) {
		String sql = getQuery("DYNAMIC_FORM_GET_SUBMIT_DATA").replace("%formName%", odynfrmSubmitDataBean.getFormName().toLowerCase());
		final RowMapper<OdynfrmSubmitDataBean> uiBeanMapper = Row2BeanRowMapper.makeMapping(sql, OdynfrmSubmitDataBean.class,
				uiBeanMapping);
		String searchString = StringUtils.join(Arrays.asList(odynfrmSubmitDataBean.getSearchString().split(";")),"%') AND LOWER(FORM_IDENTIFIER"
				+ "::text) LIKE LOWER('%");
		sql = sql + searchString + "%\')  order by CREATE_DATETIME desc LIMIT 1";
		System.out.println(sql);
		final ArrayList<OdynfrmSubmitDataBean> returnList = (ArrayList<OdynfrmSubmitDataBean>) namedParameterJdbcTemplate.query(sql, uiBeanMapper);
		if(returnList.isEmpty()) {
			return new OdynfrmSubmitDataBean();
		}
		for (OdynfrmSubmitDataBean obj : returnList) {
			obj.setFormInfoJson(new String(obj.getFormInfoJsonBlob()));
		}
		return returnList.get(0);
	}

	@Override
	public Integer submitFormData(OdynfrmSubmitDataBean odynfrmSubmitDataBean) {
		final String sql = getQuery("DYNAMIC_FORM_SUBMIT_DATA").replace("%formName%", odynfrmSubmitDataBean.getFormName().toLowerCase());
		odynfrmSubmitDataBean.setFormInfoJsonBlob(odynfrmSubmitDataBean.getFormInfoJson().getBytes());
		final SqlParameterSource parameters = new BeanPropertySqlParameterSource(odynfrmSubmitDataBean);
		return namedParameterJdbcTemplate.update(sql, parameters);
	}
	
	@Override
	public Integer updateSubmitFormData(OdynfrmSubmitDataBean odynfrmSubmitDataBean) {
		final String sql = getQuery("DYNAMIC_FORM_UPDATE_SUBMIT_DATA").replace("%formName%", odynfrmSubmitDataBean.getFormName().toLowerCase());
		odynfrmSubmitDataBean.setFormInfoJsonBlob(odynfrmSubmitDataBean.getFormInfoJson().getBytes());
		final SqlParameterSource parameters = new BeanPropertySqlParameterSource(odynfrmSubmitDataBean);
		return namedParameterJdbcTemplate.update(sql, parameters);
	}
	
	@Override
	public Integer getFormSeqID(OdynfrmSubmitDataBean odynfrmSubmitDataBean) {
		final String sql = getQuery("DYNAMIC_FORM_GET_SEQ").replace("%formName%", odynfrmSubmitDataBean.getFormName().toLowerCase());
		int seqId = 1;
		List<Map<String, Object>> returnObj = new ArrayList<>();
		try{
			returnObj = jdbcTemplate.queryForList(sql);
			seqId = Integer.valueOf(String.valueOf(returnObj.get(0).get("SEQID")));
		} catch (Exception ex) {
			
		}
		return seqId;
	}

	@Override
	public List<Map<String, Object>> getFormLovData() {
		
		final String sql = getQuery("DYNAMIC_FORM_LOV_FORM_DATA");
		final List<Map<String, Object>> returnList = jdbcTemplate.queryForList(sql);
		return returnList;
	}

	@Override
	public Map<String, Object> getFormData(String formId) {

		final String sql = getQuery("DYNAMIC_FORM_NESTED_FORM_DETAILS");
		final List<Map<String, Object>> returnList = jdbcTemplate.queryForList(sql, new Object[] {new BigInteger(formId)});
		if(!returnList.isEmpty()) {
			return returnList.get(0);
		}
		return new HashMap<String, Object>();
	}

	@Override
	public Integer createModule(FormBuilderBean formBuilderBean) {
		final String sql = getQuery("DYNAMIC_FORM_MODULE_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(formBuilderBean));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (1 == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<FormBuilderBean> getFormDataBasedOnModName(String formModuleName) {
		final String sql = getQuery("DYNAMIC_FORM_GET_ON_MOD_NAME");
		FormBuilderBean formBean = new FormBuilderBean();
		formBean.setModuleName(formModuleName);
		final SqlParameterSource parameters = new BeanPropertySqlParameterSource(formBean);
		final List<FormBuilderBean> returnList =  namedParameterJdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper<FormBuilderBean>(FormBuilderBean.class));
		return returnList;
	}
	
	@Override
	public List<Map<String, Object>> dynamicGridConfig() {
		final String sql = getQuery("OCMPCONF_DYNAMIC_GRID_CONFIG");
		List<Map<String, Object>> returnList = new ArrayList<Map<String,Object>>();
		try {
			returnList = jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			logger.error("dynamicGridConfig", e);
		}
		return returnList;
	}

	@Override
	public Integer updateActionType(OdynfrmSubmitDataBean odynfrmSubmitDataBean) {
		final String sql = getQuery("DYNAMIC_FORM_UPDATE_ACTION_TYPE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(odynfrmSubmitDataBean));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (1 == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer insertOcdLeglsHyt(OdynfrmSubmitDataBean odynfrmSubmitDataBean) {
		final String sql = getQuery("DYNAMIC_FORM_INSERT_OCDLEGLS_HYT");
		int[] returnArray = new int[] {};
		odynfrmSubmitDataBean.setFormInfoJsonBlob(odynfrmSubmitDataBean.getFormInfoJson().getBytes());
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(odynfrmSubmitDataBean));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (1 == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<StaffMemberRoles> getStaffRoles(String userId) {
		final String sql = getQuery("OCMPCONF_GET_STAFF_ROLES");
		return namedParameterJdbcTemplate.query(sql, createParams("userId",userId), new RowMapperResultSetExtractor<StaffMemberRoles>(new BeanPropertyRowMapper<StaffMemberRoles>(StaffMemberRoles.class)));
	}
	
	@Override
	public List<OdynfrmSubmitDataBean> getOcdleglsHytData(OdynfrmSubmitDataBean odynfrmSubmitDataBean) {
		String sql = getQuery("OCMPCONF_GET_OCDLEGLS_HTY_DATA");
		final RowMapper<OdynfrmSubmitDataBean> uiBeanMapper = Row2BeanRowMapper.makeMapping(sql, OdynfrmSubmitDataBean.class,
				uiBeanMapping);
		final ArrayList<OdynfrmSubmitDataBean> returnList = (ArrayList<OdynfrmSubmitDataBean>) namedParameterJdbcTemplate.query(sql,createParams("formdentifier",odynfrmSubmitDataBean.getFormIdentifier()),uiBeanMapper);
		for (OdynfrmSubmitDataBean obj : returnList) {
			obj.setFormInfoJson(new String(obj.getFormInfoJsonBlob()));
		}
		return returnList;
	}

	@Override
	public List<ReferenceCodes> rgConditionCategory(String orderType) {
		String sql = getQuery("OCMPCONF_RG_CONDITION_CATEGORY");
		final RowMapper<ReferenceCodes> uiBeanMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referCodesMapping);
		final ArrayList<ReferenceCodes> returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,createParams("orderType", orderType), uiBeanMapper);
		return returnList;
	}

	@Override
	public List<LegalUpdateReasons> rgOrderStatus(String orderType) {
			final String sql = getQuery("OCMPCONF_GET_ALL_ORDER_STATUS");
			return namedParameterJdbcTemplate.query(sql, createParams(), new RowMapperResultSetExtractor<LegalUpdateReasons>(new BeanPropertyRowMapper<LegalUpdateReasons>(LegalUpdateReasons.class)));
	}
	
	@Override
	public List<OdynfrmSubmitDataBean> getLegalsData() {
		final String sql = getQuery("OCMPCONF_GET_LEGALS_DATA");
		final RowMapper<OdynfrmSubmitDataBean> uiBeanMapper = Row2BeanRowMapper.makeMapping(sql, OdynfrmSubmitDataBean.class,
				uiBeanMapping);
		final ArrayList<OdynfrmSubmitDataBean> returnList = (ArrayList<OdynfrmSubmitDataBean>) namedParameterJdbcTemplate.query(sql, uiBeanMapper);
		return returnList;
	}

	@Override
	public Integer updateLegalsData(List<OdynfrmSubmitDataBean> legalsData) {
		final String sql = getQuery("OCMPCONF_UPDATE_LEGALS_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OdynfrmSubmitDataBean component : legalsData) {
			parameters.add(new BeanPropertySqlParameterSource(component));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (legalsData.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public String getStatusFlag(String code) {
		final String sql = getQuery("OCMPCONF_GET_STATUS_FLAG");
		String statusFlag = "";
		try {
			statusFlag = namedParameterJdbcTemplate.queryForObject(sql, createParams("code", code), String.class);
		} catch (Exception e) {
			logger.error("In getStatusFlag:", e);
		}
		return statusFlag;
	}

	@Override
	public String getResultingStatus(String status) {
		final String sql = getQuery("OCMPCONF_GET_RESULTING_STATUS");
		String resultingStatus = "";
		try {
			resultingStatus = namedParameterJdbcTemplate.queryForObject(sql, createParams("status", status), String.class);
		} catch (Exception e) {
			logger.error("In getResultingStatus:", e);
		}
		return resultingStatus;
	}

	@Override
	public List<OdynfrmSubmitDataBean> getLegalSummaryData() {
		final String sql = getQuery("OCMPCONF_GET_LEGALSUMNMARY_DATA");
		final RowMapper<OdynfrmSubmitDataBean> uiBeanMapper = Row2BeanRowMapper.makeMapping(sql, OdynfrmSubmitDataBean.class,
				uiBeanMapping);
		final ArrayList<OdynfrmSubmitDataBean> returnList = (ArrayList<OdynfrmSubmitDataBean>) namedParameterJdbcTemplate.query(sql, uiBeanMapper);
		return returnList;
	}

	@Override
	public List<OffenderSentConditions> getConditionsData() {
		final String sql = getQuery("OCMPCONF_GET_CONDITIONS_DATA");
		final RowMapper<OffenderSentConditions> uiBeanMapper = Row2BeanRowMapper.makeMapping(sql, OffenderSentConditions.class,
				condMapping);
		final ArrayList<OffenderSentConditions> returnList = (ArrayList<OffenderSentConditions>) namedParameterJdbcTemplate.query(sql, uiBeanMapper);
		return returnList;
	}

	@Override
	public Integer updateConditions(List<OffenderSentConditions> condData) {
		final String sql = getQuery("OCMPCONF_UPDATE_CONDITIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderSentConditions component : condData) {
			parameters.add(new BeanPropertySqlParameterSource(component));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (condData.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public List<LegalUpdateUsages> legalUpdateUsages(String legalClass) {
			final String sql = getQuery("OCMPCONF_GET_LEGAL_UPDATE_USAGES");
			return namedParameterJdbcTemplate.query(sql, createParams("legalClass",legalClass), new RowMapperResultSetExtractor<LegalUpdateUsages>(new BeanPropertyRowMapper<LegalUpdateUsages>(LegalUpdateUsages.class)));
	}

	@Override
	public String deleteOrderFlag(String code, String userId) {
		final String sql = getQuery("OCMPCONF_DELETE_ORDER_FLAG");
		String resultingStatus = "";
		try {
			resultingStatus = namedParameterJdbcTemplate.queryForObject(sql, createParams("code", code,"userId",userId), String.class);
		} catch (Exception e) {
			logger.error("In deleteOrderFlag: ", e);
		}
		return resultingStatus;
	}
	
	@Override
	public Integer deleteSubmitFormData(OdynfrmSubmitDataBean odynfrmSubmitDataBean) {
		final String sql = getQuery("DYNAMIC_FORM_DELETE_SUBMIT_DATA").replace("%formName%", odynfrmSubmitDataBean.getFormName().toLowerCase());
		odynfrmSubmitDataBean.setFormInfoJsonBlob(odynfrmSubmitDataBean.getFormInfoJson().getBytes());
		final SqlParameterSource parameters = new BeanPropertySqlParameterSource(odynfrmSubmitDataBean);
		try {
			String[] table = sql.trim().split(" ");
			String tableName = table[2];
			String whereClause = "id = :id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("id", odynfrmSubmitDataBean.getId());
			inputMap.put("modifyUserId", odynfrmSubmitDataBean.getModifyUserId());
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteSubmitFormData", e);
		}
		return namedParameterJdbcTemplate.update(sql, parameters);
	}
	
	@Override
	public List<CourtEvents> crtEveExecuteQuery(CourtEvents searchBean) {
			final String sql = getQuery("OCMPCONF_GET_COURT_EVENTS");
			return namedParameterJdbcTemplate.query(sql, createParams("offender_book_id",searchBean.getOffenderBookId()), new RowMapperResultSetExtractor<CourtEvents>(new BeanPropertyRowMapper<CourtEvents>(CourtEvents.class)));
	}
	
	@Override
	public String getCustodyStatus(Long offenderBookId) {
		final String sql = getQuery("OCMPCONF_GET_CUSTODY_STATUS");
		String custodyStatus = "";
		try {
			custodyStatus = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId), String.class);
		} catch (Exception e) {
			logger.error("In getCustodyStatus: ", e);
		}
		return custodyStatus;
	}

	@Override
	public String getStatusDesc(String status) {
		final String sql = getQuery("OCMPCONF_GET_STATUS_DESC");
		String custodyStatus = "";
		try {
			custodyStatus = namedParameterJdbcTemplate.queryForObject(sql, createParams("statusCode", status), String.class);
		} catch (Exception e) {
			logger.error("In getStatusDesc: ", e);
		}
		return custodyStatus;
	}

	@Override
	public Integer deleteParoleEvents(List<OffenderPayrolEvent> paroleEvents) {
		int[] returnArray = new int[] {};
		final String sql = getQuery("OCMPCONF_DELETE_PAROLE_EVENTS");
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderPayrolEvent interestedParties : paroleEvents){
			parameters.add(new BeanPropertySqlParameterSource(interestedParties));
		}
		try {
			String tableName = "offender_parole_events";
			String whereClause = "offender_book_id = :offenderBookId and parole_event_id = :paroleEventId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteParoleEvents", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("deleteParoleEvents"+e.getMessage());
			return 0;
		}
		if (paroleEvents.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public List<OdynfrmSubmitDataBean> getOffenderOrders(OdynfrmSubmitDataBean odynfrmSubmitDataBean) {
		String sql = getQuery("DYNAMIC_FORM_GET_SUBMIT_DATA").replace("%formName%", odynfrmSubmitDataBean.getFormName().toLowerCase());
		final RowMapper<OdynfrmSubmitDataBean> uiBeanMapper = Row2BeanRowMapper.makeMapping(sql, OdynfrmSubmitDataBean.class,
				uiBeanMapping);
		String searchString = StringUtils.join(Arrays.asList(odynfrmSubmitDataBean.getSearchString().split(";")),"%') AND LOWER(FORM_IDENTIFIER"
				+ "::text) LIKE LOWER('%");
		sql = sql + searchString + "%\')  order by CREATE_DATETIME desc";
		final ArrayList<OdynfrmSubmitDataBean> returnList = (ArrayList<OdynfrmSubmitDataBean>) namedParameterJdbcTemplate.query(sql, uiBeanMapper);
		for (OdynfrmSubmitDataBean obj : returnList) {
			obj.setFormInfoJson(new String(obj.getFormInfoJsonBlob()));
		}
		return returnList;
	}

	@Override
	public List<IwpDocuments> checkOrderDependency(Integer offenderBookId, String screenId, String displayNo) {
		String sql = getQuery("OCMPCONF_GET_ORDER_DOCUMENTS");
		return namedParameterJdbcTemplate.query(sql, createParams("offenderBookId",offenderBookId,"objectType",screenId,"objectId",displayNo), new RowMapperResultSetExtractor<IwpDocuments>(new BeanPropertyRowMapper<IwpDocuments>(IwpDocuments.class)));	
	}
	
	@Override
	public Integer getOcdleglsSequenceID() {
		final String sql = getQuery("OCMPCONF_GET_OCDLEGLS_SEQ_ID");
		Integer seqId = 1;
		try{
			seqId=  namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (Exception ex) {
			logger.error("getOcdleglsSequence : ", ex.getMessage());
		}
		return seqId;
	}

	@Override
	public List<ReferenceCodes> getCommenceType() {
		String sql = getQuery("OCMPCONF_GET_COMMENCE_TYPE");
		return namedParameterJdbcTemplate.query(sql,createParams(), new RowMapperResultSetExtractor<ReferenceCodes>(new BeanPropertyRowMapper<ReferenceCodes>(ReferenceCodes.class)));
	}
}
