package net.syscon.s4.inst.legals.impl;

import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.LovList;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.OracleSimpleJdbcCall;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.legals.OcdcCasesRepository;
import net.syscon.s4.inst.legals.beans.BailStatus;
import net.syscon.s4.inst.legals.beans.BondType;
import net.syscon.s4.inst.legals.beans.CaseStatus;
import net.syscon.s4.inst.legals.beans.Category;
import net.syscon.s4.inst.legals.beans.Court;
import net.syscon.s4.inst.legals.beans.CourtCases;
import net.syscon.s4.inst.legals.beans.CourtEvent;
import net.syscon.s4.inst.legals.beans.HearingType;
import net.syscon.s4.inst.legals.beans.OffenderBailDetails;
import net.syscon.s4.inst.legals.beans.OffenderDetailsOffenses;
import net.syscon.s4.inst.legals.beans.OffenseResultCodes;
import net.syscon.s4.inst.legals.beans.OffenseType;
import net.syscon.s4.inst.legals.beans.OffensesOutcome;
import net.syscon.s4.inst.legals.beans.Outcome;
import net.syscon.s4.inst.legals.beans.Plea;
import net.syscon.s4.inst.legals.beans.SentenceDate;
import net.syscon.s4.inst.legals.beans.Sentences;
import net.syscon.s4.inst.legals.beans.Terms;
import net.syscon.s4.inst.legals.beans.Type;
import oracle.jdbc.OracleTypes;

@Repository
public class OcdcCasesRepositoryImpl extends RepositoryBase implements OcdcCasesRepository {

	private static Logger logger = LogManager.getLogger(OcdcCasesRepositoryImpl.class);
	private final JdbcTemplate jdbcTemplate;
	
	public OcdcCasesRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	final Map<String, FieldMapper> courtCasesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASE_ID",     			new FieldMapper("caseId"))
			.put("OFFENDER_BOOK_ID", 		new FieldMapper("offenderBookId"))
			.put("CASE_INFO_NUMBER",  		new FieldMapper("caseInfoNumber"))
			.put("CASE_TYPE", 				new FieldMapper("caseType"))
			.put("CASE_STATUS",   			new FieldMapper("caseStatus"))
			.put("COMBINED_CASE_ID", 		new FieldMapper("combinedCaseId"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDateTime"))
			.put("MODIFY_USER_ID",  		new FieldMapper("modifyUserId"))
			.put("BEGIN_DATE", 				new FieldMapper("beginDate"))
			.put("AGY_LOC_ID",   			new FieldMapper("agy_loc_id"))
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDateTime"))
			.put("CASE_INFO_PREFIX", 		new FieldMapper("caseInfoPrefix"))
			.put("VICTIM_LIAISON_UNIT",  	new FieldMapper("victimLiaisonUnit"))
			.put("STATUS_UPDATE_REASON", 	new FieldMapper("statusUpdateReason"))
			.put("STATUS_UPDATE_COMMENT",   new FieldMapper("statusUpdateComment"))
			.put("STATUS_UPDATE_DATE", 		new FieldMapper("status_update_date"))
			.put("STATUS_UPDATE_STAFF_ID", 	new FieldMapper("status_update_staff_id"))
			.put("LIDS_CASE_NUMBER",   		new FieldMapper("lidsCaseNumber"))
			.put("CASE_SEQ", 				new FieldMapper("case_Seq"))
			.put("SEAL_FLAG", 				new FieldMapper("seal_flag"))
			.put("LINK_CASE_SEQ",	 		new FieldMapper("linkCaseSeq"))
			.build();
	
	private final Map<String, FieldMapper> courtListMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 			new FieldMapper("description"))			
			.put("AGY_LOC_ID",  			new FieldMapper("code"))
			.put("ACTIVE_FLAG", 			new FieldMapper("activeFlag"))
			.put("LIST_SEQ", 			new FieldMapper("listSeq")).
			build();
	
	private final Map<String, FieldMapper> caseStatusMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("NBT_CASE_STATUS", 			new FieldMapper("description"))			
			.put("CASE_STATUS",  				new FieldMapper("code")).build();
	
	private final Map<String, FieldMapper> typeListMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("NBT_CASE_TYPE", 			new FieldMapper("description"))		
			.put("CASE_TYPE",  				new FieldMapper("typeOfCase")).build();
	
	private final Map<String, FieldMapper> courtEventMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", 		new FieldMapper("offenderBookId"))		
			.put("CASE_ID",  		 		new FieldMapper("caseId"))
			.put("EVENT_ID",  		 		new FieldMapper("eventId"))
			.put("EVENT_DATE", 			 	new FieldMapper("eventDate"))
			.put("START_TIME",  		 	new FieldMapper("startTime"))
			.put("AGY_LOC_ID",  		 	new FieldMapper("agyLocId"))
			.put("COURT_EVENT_TYPE", 	 	new FieldMapper("hearingType"))		
			.put("OUTCOME_REASON_CODE",  	new FieldMapper("outcomeReasonCode"))
			.put("NEXT_EVENT_DATE", 	 	new FieldMapper("nextEventDate"))		
			.put("NEXT_EVENT_START_TIME",	new FieldMapper("nextEventStartTime"))
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDateTime"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID",  		new FieldMapper("modifyUserId"))
			.put("HOLD_FLAG", 				new FieldMapper("holdFlag"))		
			.put("ORDER_REQUESTED_FLAG",  	new FieldMapper("orderRequestedFlag"))
			.put("COMMENT_TEXT",  			new FieldMapper("commentText"))
			.put("EVENT_STATUS",  			new FieldMapper("eventStatus"))
			.put("DIRECTION_CODE",  		new FieldMapper("directionCode"))
			.build();
	
	private final Map<String, FieldMapper> hearingTypeListMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("NBT_COURT_EVENT_TYPE", 	new FieldMapper("description"))		
			.put("COURT_EVENT_TYPE",  		new FieldMapper("code")).build();
	
	private final Map<String, FieldMapper> outcomeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 			new FieldMapper("description"))		
			.put("RESULT_CODE",  			new FieldMapper("code")).build();
	
	private final Map<String, FieldMapper> offensesOutcomeDataMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STATUTE_CODE", 			new FieldMapper("statuteCode"))		
			.put("OFFENCE_CODE",  			new FieldMapper("offenceCode")).build();
			
	
	private final Map<String, FieldMapper> offensesTypeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DSP_DESCRIPTION3", 			new FieldMapper("description"))		
			.put("OFFENCE_TYPE",	  			new FieldMapper("code")).build();
	
	private final Map<String, FieldMapper> pleaListMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("NBT_PLEA_CODE", 			new FieldMapper("description"))		
			.put("PLEA_CODE",  				new FieldMapper("code")).build();
	
	private final Map<String, FieldMapper> resultListMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("NBT_PLEA_CODE", 			new FieldMapper("description"))		
			.put("PLEA_CODE",  				new FieldMapper("pleaCode")).build();
	
	private final Map<String, FieldMapper> sentenceCategoryMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 					new FieldMapper("description"))		
			.put("SENTENCE_CATEGORY",  				new FieldMapper("code")).build();
	
	private final Map<String, FieldMapper> sentenceTypeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("NBT_SENTENCE_CALC_TYPE", 			new FieldMapper("description"))		
			.put("SENTENCE_CALC_TYPE",  			new FieldMapper("code")).build();
	
	private final Map<String, FieldMapper> sentenceDateMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("EVENT_DATE", 					new FieldMapper("description"))		
			.put("ORDER_ID",  					new FieldMapper("code")).build();
	
	private final Map<String, FieldMapper> consecutiveToLineMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("line_seq", 						new FieldMapper("line"))		
			.put("sentence_seq",  					new FieldMapper("sentenceSeq"))
			.put("end_date",  						new FieldMapper("expiryDate"))
			.put("sentence_calc_type",  			new FieldMapper("code"))
			.put("case_info_number",  				new FieldMapper("caseInfoNumber"))
			.put("description", 			        new FieldMapper("description")).build();
	
	private final Map<String, FieldMapper> sentenceStatusMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("NBT_SENTENCE_STATUS", 				new FieldMapper("description"))		
			.put("SENTENCE_STATUS",  					new FieldMapper("code")).build();
	
	private final Map<String, FieldMapper> sentenceTermCodeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("NBT_SENTENCE_TERM_CODE", 				new FieldMapper("description"))		
			.put("SENTENCE_TERM_CODE",  				new FieldMapper("code")).build();
				
	private final Map<String, FieldMapper> sentenceListMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", 		new FieldMapper("offenderBookId"))
			.put("SENTENCE_SEQ", 			new FieldMapper("line"))		
			.put("SENTENCE_CATEGORY",  		new FieldMapper("category"))
			.put("SENTENCE_CALC_TYPE", 		new FieldMapper("sentenceCalcType"))
			.put("FINE_AMOUNT",  		 	new FieldMapper("fineAmount"))
			.put("CONSEC_TO_SENTENCE_SEQ",  new FieldMapper("consecutiveToLine"))
			.put("START_DATE", 	 			new FieldMapper("startDate"))		
			.put("END_DATE",  				new FieldMapper("expiryDate"))
			.put("SENTENCE_STATUS", 	 	new FieldMapper("status"))	
			.put("CASE_ID", 	 			new FieldMapper("caseId"))
			.put("ORDER_ID", 	 			new FieldMapper("orderId"))
			.put("STATUS_UPDATE_REASON", 	new FieldMapper("statusUpdateReason"))
			.put("STATUS_UPDATE_DATE", 		new FieldMapper("statusUpdateDate"))
			.put("STATUS_UPDATE_STAFF_ID", 	new FieldMapper("statusUpdateStaffId"))
			.put("STATUS_UPDATE_COMMENT", 	new FieldMapper("comment")).build();
	
	private final Map<String, FieldMapper> termsListMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TERM_SEQ", 				new FieldMapper("termSeq"))
			.put("NBT_SENTENCE_TERM_CODE", 	new FieldMapper("sentenceTermCodeDescription"))			
			.put("SENTENCE_TERM_CODE",  	new FieldMapper("sentenceTermCode"))
			.put("OFFENDER_BOOK_ID", 		new FieldMapper("offenderBookId"))		
			.put("LIFE_SENTENCE_FLAG",  	new FieldMapper("lifeSentenceFlag"))
			.put("SENTENCE_SEQ",  			new FieldMapper("sentenceSeq"))
			.put("START_DATE", 				new FieldMapper("startDate"))			
			.put("YEARS",  					new FieldMapper("years"))
			.put("MONTHS", 					new FieldMapper("months"))		
			.put("WEEKS",  		 			new FieldMapper("weeks"))
			.put("DAYS",  					new FieldMapper("days"))
			.put("HOURS", 	 				new FieldMapper("hours"))		
			.put("END_DATE",  				new FieldMapper("expiryDate"))
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDateTime"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID",  		new FieldMapper("modifyUserId"))
			
			.build();
	
	//==================== bail details mapper ============================================
	
	private final Map<String, FieldMapper> bailStatusMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 	 	new FieldMapper("description"))			
			.put("CODE",  			 	new FieldMapper("code")).build();
	
	private final Map<String, FieldMapper> bondTypeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 		new FieldMapper("description"))			
			.put("CODE",  				new FieldMapper("code")).build();
	
	private final Map<String, FieldMapper> offenderOffenseMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASE_ID", 			new FieldMapper("caseId"))
			.put("OFFENDER_BOOK_ID", 	new FieldMapper("bookingId"))
			.put("OFFENDER_CHARGE_ID", 	new FieldMapper("chargeId"))
			.put("OFFENCE_DESC", 		new FieldMapper("description"))
			.put("STATUTE_CODE", 		new FieldMapper("statuteCode"))	
			.put("OFFENCE_CODE",  		new FieldMapper("code")).build();
	
	private final Map<String, FieldMapper> bailDetailsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", 				new FieldMapper("bookId"))
			.put("OFFENDER_CHARGE_ID", 				new FieldMapper("chargeId"))
			.put("BAIL_OFFENDER_BOOK_ID", 			new FieldMapper("bailBookId"))
			.put("BAIL_OFFENDER_CHARGE_ID", 		new FieldMapper("bailChargeId"))
			.put("CASH_FLAG", 						new FieldMapper("cashFlag"))
			.put("CASH", 							new FieldMapper("cash"))
			.put("SURETY", 							new FieldMapper("surety"))	
			.put("PROPERTY", 						new FieldMapper("property"))
			.put("PERFECTED_DATETIME",			 	new FieldMapper("preferedDateTime"))
			.put("PERFECTED_BY", 					new FieldMapper("preferedBy"))
			.put("METHOD", 							new FieldMapper("method"))
			.put("COMMENT_TEXT", 					new FieldMapper("commentText"))
			.put("BAIL_DATE", 						new FieldMapper("BailDate"))
			.put("JUDGE", 							new FieldMapper("judge"))	
			.put("RECEIPT_REFERENCE_TEXT", 			new FieldMapper("receiptText"))
			.put("CREATE_DATETIME", 				new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", 				new FieldMapper("modifyDateTime"))			
			.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
			.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
			.put("OFFENCE_DESC", 					new FieldMapper("offencesDec"))
			.put("BAIL_STATUS",  					new FieldMapper("bailStatus")).build();
	
	private final Map<String, FieldMapper> offensesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", 		new FieldMapper("offenderBookId"))
			.put("SENTENCE_SEQ", 			new FieldMapper("sentenceSeq"))
			.put("OFFENDER_CHARGE_ID", 		new FieldMapper("offenderChargeId")).build();
	
		
	private final Map<String, FieldMapper> offendeOffencesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", 			new FieldMapper("offenderBookId"))		
			.put("OFFENDER_CHARGE_ID",  		new FieldMapper("offenderChargeId"))
			.put("OFFENCE_DESCRIPTION",  		new FieldMapper("offenseDescription"))
			.put("OFFENCE_DATE", 			 	new FieldMapper("offenseDate"))
			.put("RESULT",  		 			new FieldMapper("result"))
			.put("OFFENCE_TYPE",  		 		new FieldMapper("offenseType"))
			.build();
	
	private final Map<String, FieldMapper> offencesOutcomeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("offender_book_id", 			new FieldMapper("offender_book_id"))		
			.put("offender_charge_id",  		new FieldMapper("offenderChargeId"))
			.put("statute_code",  		 		new FieldMapper("offenseDescription"))
			.put("offence_code", 			 	new FieldMapper("offenseDate"))
			.put("offence_date",  		 		new FieldMapper("result"))
			.put("offence_range_date",  		new FieldMapper("offenseType"))
			.put("plea_code", 					new FieldMapper("offenderBookId"))		
			.put("property_value",  		 	new FieldMapper("offenderChargeId"))
			.put("total_property_value",  		new FieldMapper("offenseDescription"))
			.put("cjit_offence_code_1", 		new FieldMapper("offenseDate"))
			.put("cjit_offence_code_2",  		new FieldMapper("result"))
			.put("cjit_offence_code_3",  		new FieldMapper("offenseType"))
			.put("charge_status",  		 		new FieldMapper("result"))
			.put("result_code_1",  		 		new FieldMapper("offenseType"))
			.put("result_code_2", 				new FieldMapper("offenderBookId"))		
			.put("result_code_1_indicator", 	new FieldMapper("offenderChargeId"))
			.put("result_code_2_indicator",  	new FieldMapper("offenseDescription"))
			.put("no_of_offences", 			 	new FieldMapper("offenseDate"))
			.put("case_id",  		 			new FieldMapper("result"))
			.put("most_serious_flag",  		 	new FieldMapper("offenseType"))
			.put("offence_type",  		 		new FieldMapper("offenseType"))
			.build();
	private final Map<String, FieldMapper> casePrefixInfo = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 					new FieldMapper("description"))	
			.put("CODE", 					new FieldMapper("code"))	
			.build();
	
	@Override
	public List<CourtCases> searchCourtCases(CourtCases offenderCases) {
		List<CourtCases> courtCasesData = new ArrayList<CourtCases>();
		final String sql = getQuery("DISPLAY_COURT_CASES");		
		final RowMapper<CourtCases> courtCasesRowMapper = Row2BeanRowMapper.makeMapping(sql,	CourtCases.class, courtCasesMapping);
		try {
			courtCasesData=  namedParameterJdbcTemplate.query(sql,createParams("offenderBookId", offenderCases.getOffenderBookId(),"caseStatus",offenderCases.getCaseStatus()), courtCasesRowMapper);
			 
			} catch (Exception e) {
				logger.error("searchCourtCases"+e.getMessage());
				
				}
		
		return courtCasesData;
		}

	@Override
	public List<Court> populateCourtData() {
		List<Court> courtList = new ArrayList<Court>();
		final String sql = getQuery("GET_COURT_DATA");	
		final RowMapper<Court> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,	Court.class, courtListMapping);
		try {
			courtList=  namedParameterJdbcTemplate.query(sql,referenceCodeRowMapper);
			} catch (Exception e) {
				logger.error("populateCourtData"+e.getMessage());
				}
		return courtList;
	}
	
	@Override
	public List<Type> populateTypeData() {
		List<Type> typeList = new ArrayList<Type>();
		final String sql = getQuery("LOV_CASE_TYPE");		
		final RowMapper<Type> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,	Type.class, typeListMapping);
		try {
			typeList=  namedParameterJdbcTemplate.query(sql,referenceCodeRowMapper);
			} catch (Exception e) {
				logger.error("populateTypeData"+e.getMessage());
				}
		return typeList;
		}
	
	@Override
	public List<CourtEvent> searchCourtEvents(String caseId) {
		List<CourtEvent> courtEventList = new ArrayList<CourtEvent>();
		final String sql = getQuery("COURT_EVENTS");		
		final RowMapper<CourtEvent> courtEventRowMapper = Row2BeanRowMapper.makeMapping(sql,CourtEvent.class,courtEventMapping);
		try {
			courtEventList=  namedParameterJdbcTemplate.query(sql,createParams("caseId", caseId),courtEventRowMapper);
			} catch (Exception e) {
				logger.error("searchCourtEvents"+e.getMessage());
				}
		return courtEventList;
		}
	

	@Override
	public List<HearingType> populateHearingType() {
		List<HearingType> hearingTypeList = new ArrayList<HearingType>();
		final String sql = getQuery("LOV_HEARING_TYPE");		
		final RowMapper<HearingType> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,HearingType.class,hearingTypeListMapping);
		try {
			hearingTypeList=  namedParameterJdbcTemplate.query(sql,referenceCodeRowMapper);
			} catch (Exception e) {
				logger.error("populateHearingType"+e.getMessage());
				}
		return hearingTypeList;
		}
	
	@Override
	public List<Outcome> populateOutcomeData(){
		List<Outcome> outcomeList = new ArrayList<Outcome>();
		final String sql = getQuery("LOV_OUTCOME");		
		final RowMapper<Outcome> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,Outcome.class, outcomeMapping);
		try {
			outcomeList=  namedParameterJdbcTemplate.query(sql,referenceCodeRowMapper);
			} catch (Exception e) {
				logger.error("populateOutcomeData"+e.getMessage());
				}
		return outcomeList;
		}
	
	@Override
	public List<OffensesOutcome> searchOffensesOutcomeData(CourtEvent courtEvent){
		List<OffensesOutcome> offensesOutcomeResultList = null;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { 
				new SqlOutParameter("P_REF_CUR", OracleTypes.CURSOR),
				new SqlParameter("P_OFFENDER_BOOK_ID",  OracleTypes.NUMBER),
				new SqlParameter("P_CASE_ID", 			OracleTypes.NUMBER),
				new SqlParameter("P_EVENT_ID", 			OracleTypes.NUMBER),
				new SqlParameter("P_OFFENCE_CODE", 		OracleTypes.VARCHAR),
				new SqlParameter("P_STATUTE_CODE", 		OracleTypes.VARCHAR),
				new SqlParameter("P_PLEA_CODE", 		OracleTypes.VARCHAR),
				new SqlParameter("P_OFFENCE_DATE", 		OracleTypes.DATE),
				new SqlParameter("P_OFFENCE_RANGE_DATE",OracleTypes.DATE),
				new SqlParameter("P_RESULT_CODE_1", 	OracleTypes.VARCHAR),
				new SqlParameter("P_RESULT_CODE_2", 	OracleTypes.VARCHAR),
				new SqlParameter("P_OFFENCE_TYPE", 		OracleTypes.VARCHAR),
				};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource)
				.withSchemaName("OMS_OWNER")
					.withCatalogName("TAG_LEGAL_CASES")
						.withProcedureName("OFFENCES_QUERY")
							.declareParameters(sqlParameters);
		inParamMap.put("P_REF_CUR", OracleTypes.CURSOR);
		inParamMap.put("P_OFFENDER_BOOK_ID", courtEvent.getOffenderBookId());    
		inParamMap.put("P_CASE_ID", courtEvent.getCaseId());
		inParamMap.put("P_EVENT_ID", courtEvent.getEventId());
		inParamMap.put("P_OFFENCE_CODE", null);
		inParamMap.put("P_STATUTE_CODE", null);
		inParamMap.put("P_PLEA_CODE", null);
		inParamMap.put("P_OFFENCE_DATE", null);
		inParamMap.put("P_OFFENCE_RANGE_DATE", null);
		inParamMap.put("P_RESULT_CODE_1", null);
		inParamMap.put("P_RESULT_CODE_2", null);
		inParamMap.put("P_OFFENCE_TYPE", null);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			offensesOutcomeResultList = new ArrayList<>();
			String Date = null;
			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			Date date;
			final List<Map<String, String>> list = (List<Map<String, String>>) returnObject.get("P_REF_CUR");
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> childMap = list.get(i);
				OffensesOutcome bean = new OffensesOutcome();
				if(String.valueOf(childMap.get("'Y'")).equals("Y")){
					bean.setApply(true);	
				}
				else {
					bean.setApply(false);
				}
				bean.setOffenderBookId(Long.parseLong(String.valueOf(childMap.get("OFFENDER_BOOK_ID"))));
				bean.setOffenseDescription(String.valueOf(childMap.get("OFFENCE_DESCRIPTION")));
				bean.setOffenseType(String.valueOf(childMap.get("OFFENCE_TYPE")));
				bean.setPlea(String.valueOf(childMap.get("PLEA_CODE")));
				bean.setStatuteCode(String.valueOf(childMap.get("STATUTE_CODE")));
				bean.setOffenceCode(String.valueOf(childMap.get("OFFENCE_CODE")));
				bean.setOffenderChargeId(Long.parseLong(String.valueOf(childMap.get("OFFENDER_CHARGE_ID"))));
				bean.setResultcode1desc(String.valueOf(childMap.get("RESULT_CODE1_DESC")));
				bean.setResultcode1(String.valueOf(childMap.get("RESULT_CODE_1")));
				if(String.valueOf(childMap.get("OFFENCE_DATE"))=="null") {
					bean.setOffenseDate(null);
				}
				else {
					date = format.parse(String.valueOf(childMap.get("OFFENCE_DATE")));
					bean.setOffenseDate(date);
					}
				if(String.valueOf(childMap.get("OFFENCE_RANGE_DATE"))=="null") {
					bean.setRange(null);
				}
				else {
					date = format.parse(String.valueOf(childMap.get("OFFENCE_RANGE_DATE")));
					bean.setRange(date);
					}
				bean.setResult(String.valueOf(childMap.get("RESULT_CODE1_DESC")));
				bean.setDisposition(String.valueOf(childMap.get("RESULT_CODE_1_INDICATOR")));
				offensesOutcomeResultList.add(bean);
			}

		} catch (Exception e) {
			logger.error("searchOffensesOutcomeData"+e.getMessage());
			}
		return offensesOutcomeResultList;
		}

	@Override
	public List<OffenseType> populateOffenseType(String offenceCode) {
		List<OffenseType> offensesTypeList = new ArrayList<OffenseType>();
		final String sql = getQuery("LOV_OFFENCE_TYPE");		
		final RowMapper<OffenseType> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,OffenseType.class, offensesTypeMapping);
		try {
			offensesTypeList=  namedParameterJdbcTemplate.query(sql,createParams("offenceCode",offenceCode),referenceCodeRowMapper);
			} catch (Exception e) {
				logger.error("populateOffenseType"+e.getMessage());
				}
		return offensesTypeList;
		}

	@Override
	public List<Plea> populatePleaData() {
		List<Plea> pleaList = new ArrayList<Plea>();
		final String sql = getQuery("LOV_PLEA");		
		final RowMapper<Plea> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,Plea.class, pleaListMapping);
		try {
			pleaList=  namedParameterJdbcTemplate.query(sql,referenceCodeRowMapper);
			} catch (Exception e) {
				logger.error("populatePleaData"+e.getMessage());
				}
		return pleaList;
		}

	@Override
	public List<OffenseResultCodes> populateResultData() {
		List<OffenseResultCodes> resultList = new ArrayList<OffenseResultCodes>();
		final String sql = getQuery("");		
		final RowMapper<OffenseResultCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,	OffenseResultCodes.class, resultListMapping);
		try {
			resultList=  namedParameterJdbcTemplate.query(sql,referenceCodeRowMapper);
			} catch (Exception e) {
			logger.error("populateResultData"+e.getMessage());
		}
		return resultList;
		}

	@Override
	public List<Sentences> populateSentencesData(CourtCases offenderCases) {
		List<Sentences> sentencesdataList = new ArrayList<Sentences>();
		final String sql = getQuery("SENTENCE_DATA");
		final RowMapper<Sentences> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,	Sentences.class, sentenceListMapping);
		try {
			sentencesdataList =  namedParameterJdbcTemplate.query(sql,createParams("offenderBookId", offenderCases.getOffenderBookId(),"caseId", offenderCases.getcaseId()),referenceCodeRowMapper);
			} catch (Exception e) {
			logger.error("populateSentencesData"+e.getMessage());
		}
		 
		return sentencesdataList;
	}
	
	

	@Override
	public List<Terms> populatetermsData(Sentences sentences) {
		List<Terms> termsdataList = new ArrayList<Terms>();
		final String sql = getQuery("TERMS_DATA");
		final RowMapper<Terms> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,	Terms.class, termsListMapping);
		try {
			termsdataList =  namedParameterJdbcTemplate.query(sql,createParams("line", sentences.getLine(),"offenderBookId" , sentences.getOffenderBookId()),referenceCodeRowMapper);
			} catch (Exception e) {
			logger.error("populatetermsData"+e.getMessage());
		}
		return termsdataList;
	}

	@Override
	public List<LovList> populateSentenceTermCode(String sentenceCalcType, String sentenceCategory) {
		List<LovList> teamList = new ArrayList<LovList>();
		final String sql = getQuery("LOV_TERM_CODE");
		final RowMapper<LovList> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,LovList.class, sentenceTermCodeMapping);
		try {
			teamList=  namedParameterJdbcTemplate.query(sql,createParams("sentenceCalcType",sentenceCalcType,"sentenceCategory",sentenceCategory),referenceCodeRowMapper);
			} catch (Exception e) {
				logger.error("populateSentenceTermCode"+e.getMessage());
		}
		return teamList;
	}

	@Override
	public List<OffensesOutcome> populateOffensesData(Sentences sentence) {
		List<OffensesOutcome> resultList = null;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { 
				new SqlOutParameter("P_REF_CUR", OracleTypes.CURSOR),
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_SENTENCE_SEQ", OracleTypes.NUMBER)
				};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource)
				.withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_LEGAL_CASES")
				.withProcedureName("SENTENCE_CHARGES_QUERY")
				.declareParameters(sqlParameters);
		
		inParamMap.put("P_REF_CUR", OracleTypes.CURSOR);
		inParamMap.put("P_OFFENDER_BOOK_ID", sentence.getOffenderBookId());    
		inParamMap.put("P_SENTENCE_SEQ", sentence.getLine());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			resultList = new ArrayList<>();
			final List<Map<String, String>> list = (List<Map<String, String>>) returnObject.get("P_REF_CUR");
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> childMap = list.get(i);
				OffensesOutcome bean = new OffensesOutcome();
				bean.setOffenseDescription(String.valueOf(childMap.get("OFFENCE_DESCRIPTION")));
				bean.setOffenderBookId(Long.parseLong(String.valueOf(childMap.get("OFFENDER_BOOK_ID"))));
				bean.setOffenderChargeId(Long.parseLong(String.valueOf(childMap.get("OFFENDER_CHARGE_ID"))));
				bean.setSentenceSeq(Long.parseLong(String.valueOf(childMap.get("SENTENCE_SEQ"))));
				resultList.add(bean);
			}

		} catch (Exception e) {
			logger.error("populateOffensesData"+e.getMessage());
			}
		return resultList;
	}

	@Override
	public Integer insertOffensesOnSentence(List<OffensesOutcome> offensesOnsentencingList) {
		Integer liReturn = 1;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		
		sqlParameters = new SqlParameter[] { 
				
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_SENTENCE_SEQ", OracleTypes.NUMBER),
				new SqlOutParameter("P_ID", OracleTypes.NUMBER),
				};
		for(OffensesOutcome offenses : offensesOnsentencingList) {
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource)
				.withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_LEGAL_CASES")
				.withProcedureName("SENTENCE_CHARGES_INSERT")
				.declareParameters(sqlParameters);
		offenses.setOffenderChargeId(11250L);
		inParamMap.put("P_OFFENDER_BOOK_ID", offenses.getOffenderBookId());    
		inParamMap.put("P_SENTENCE_SEQ", offenses.getSentenceSeq());
		inParamMap.put("P_ID", 11250);//offenses.getOffenderChargeId());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			}catch (Exception e) {
				liReturn=0;
				logger.error("insertOffensesOnSentence"+e.getMessage());
				}
		}
		return liReturn;
	}

	@Override
	public Integer insertNewCase(List<CourtCases> courtCaseCommitList) {
		final String sql = getQuery("INSERT_COURT_CASE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourtCases list : courtCaseCommitList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
			}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("insertNewCase", e);
			}
		if(courtCaseCommitList.size() == returnArray.length) {
			return 1;
			} else {
				return 0;
				}
		}

	@Override
	public Integer updateCourtCase(List<CourtCases> updatedList) {
		final String sql = getQuery("UPDATE_COURT_CASE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourtCases list : updatedList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
			}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			} catch (Exception e) {
			logger.error("updateCourtCase", e);
			}
		if(updatedList.size() == returnArray.length) {
			return 1;
			} else {
				return 0;
				}
		}

	@Override
	public List<CaseStatus> populateCaseStatus() {
		List<CaseStatus> caseStatusList = new ArrayList<CaseStatus>();
		final String sql = getQuery("CASE_STATUS");	
		final RowMapper<CaseStatus> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,	CaseStatus.class, caseStatusMapping);
		try {
			caseStatusList=  namedParameterJdbcTemplate.query(sql,referenceCodeRowMapper);
			} catch (Exception e) {
				logger.error("populateCaseStatus"+e.getMessage());
				}
		return caseStatusList;
		}

	
	@Override
	public List<CaseStatus> preInsertCasePrefixInfo(String caseType) {
		List<CaseStatus> casePrefix = new ArrayList<CaseStatus>();
		final String sql = getQuery("FETCH_PREINSERT_CASEPREFIX");	
		final RowMapper<CaseStatus> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql, CaseStatus.class, casePrefixInfo);
		try {
			casePrefix=  namedParameterJdbcTemplate.query(sql,createParams("CASETYPE",caseType),referenceCodeRowMapper);			
			/*if(!casePrefix.isEmpty() && casePrefix.size() > 0){
				casePrefix.get(0).setCode(casePrefix.get(0).getDescription());
			}*/
			} catch (Exception e) {
				logger.error("preInsertCasePrefixInfo"+e.getMessage());
				}
		return casePrefix;
		}
	

	@Override
	public Object getPreInsertCaseType() {
		final String sql = getQuery("GET_PREINSERT_CASE_TYPE");
		final Object returnObj = namedParameterJdbcTemplate.queryForObject(sql,createParams(),Object.class);
		return returnObj;
	}

	@Override
	public Object getPreInsertAgyLocation(String offenderBookId) {
		final String sql = getQuery("GET_PREINSERT_AGY_LOCATION");
		final Object returnObj = namedParameterJdbcTemplate.queryForObject(sql,createParams("OFFENDERBOOKID",offenderBookId),Object.class);
		return returnObj;
	}

	@Override
	public Long getPreInsertCaseSeq(Long offenderBookId) {
		Long returnObj = null;
		final String sql = getQuery("OCDCASE_NEWCASE_PREINSERT_CASE_SEQ");
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),Long.class);
		return returnObj;
	}

	@Override
	public Long getPreInsertCaseId() {
		Long returnObj = null;
		final String sql = getQuery("OCDCASE_NEWCASE_PREINSERT_CASE_ID");
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),Long.class);
		return returnObj;
	}
	
	@Override
	public Long getPreInsertEventId() {
		Long returnObj = null;
		final String sql = getQuery("OCDCASE_NEWEVENT_PREINSERT_EVENT_ID");
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),Long.class);
		return returnObj;
	}

	@Override
	public Integer insertNewCourtEvent(List<CourtEvent> courtEventcommitList) {
		final String sql = getQuery("INSERT_COURT_EVENT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourtEvent list : courtEventcommitList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		logger.error(courtEventcommitList.get(0).getcreateDateTime());
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("insertNewCourtEvent", e);
			}
		if(courtEventcommitList.size() == returnArray.length) {
			return 1;
			} else {
				return 0;
				}
		}

	@Override
	public CourtEvent insertNextEventRecord(List<CourtEvent> list) {
		int[] returnArray = new int[] {};
		final CourtEvent bean = new CourtEvent();
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { 
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_CASE_ID", OracleTypes.NUMBER),
				new SqlParameter("P_EVENT_DATE", OracleTypes.DATE),
				new SqlParameter("P_START_TIME", OracleTypes.DATE),
				new SqlParameter("P_COURT_EVENT_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_AGY_LOC_ID", OracleTypes.VARCHAR),
				};
		for(CourtEvent nextEvent : list)
		{
			SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
					.withCatalogName("TAG_LEGAL_CASES").withProcedureName("NEXT_EVENTS_INSERT").declareParameters(sqlParameters);
			inParamMap.put("P_OFFENDER_BOOK_ID", nextEvent.getOffenderBookId());    
			inParamMap.put("P_CASE_ID", nextEvent.getCaseId());
			inParamMap.put("P_EVENT_DATE", nextEvent.getNextEventDate());    
			inParamMap.put("P_START_TIME", nextEvent.getNextEventStartTime());
			inParamMap.put("P_COURT_EVENT_TYPE", nextEvent.getHearingType());    
			inParamMap.put("P_AGY_LOC_ID", nextEvent.getAgyLocId());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			} catch (Exception e) {
			logger.error("insertNextEventRecord"+e.getMessage());
			}
		}
		return bean;
		}
	
	@Override
	public Integer updateAgyLocation(List<CourtEvent> list) {
		final CourtEvent bean = new CourtEvent();
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { 
				new SqlParameter("P_CASE_ID", OracleTypes.NUMBER),
				new SqlParameter("P_AGY_LOC_ID", OracleTypes.VARCHAR),
				};
		for(CourtEvent courtEvent : list)
		{
			SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
					.withCatalogName("TAG_LEGAL_CASES").withProcedureName("UPDATE_CASES_AGY_LOC_ID").declareParameters(sqlParameters);
			inParamMap.put("P_CASE_ID", courtEvent.getCaseId());    
			inParamMap.put("P_AGY_LOC_ID", courtEvent.getAgyLocId());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			} catch (Exception e) {
				logger.error("updateAgyLocation"+e.getMessage());
			}
		}
		return 1;
		}
	
	@Override
	public Integer insertEventCharges(List<CourtEvent> insertList) {
		Integer liReturn = 1;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { 
				new SqlParameter("P_EVENT_ID", OracleTypes.NUMBER),
				new SqlParameter("P_CASE_ID", OracleTypes.NUMBER),
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_OUTCOME_REASON_CODE", OracleTypes.VARCHAR),
				new SqlParameter("P_RESULT_CODE_1_IND", OracleTypes.VARCHAR),
				};
		for(CourtEvent courtEvent : insertList)
		{
			SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
					.withCatalogName("TAG_LEGAL_CASES").withProcedureName("EVENT_CHARGES_INSERT").declareParameters(sqlParameters);
			inParamMap.put("P_EVENT_ID", courtEvent.getEventId());
			inParamMap.put("P_CASE_ID", courtEvent.getCaseId());
			inParamMap.put("P_OFFENDER_BOOK_ID", courtEvent.getOffenderBookId());    
			inParamMap.put("P_OUTCOME_REASON_CODE", courtEvent.getOutcomeReasonCode());
			inParamMap.put("P_RESULT_CODE_1_IND", null);
			
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			} catch (Exception e) {
				liReturn=0;
				logger.error("insertEventCharges"+e.getMessage());
			}
		}
		
		return liReturn;
		}
	
	@Override
	public OffensesOutcome updateEventCharges(List<CourtEvent> updateList) {
		final OffensesOutcome bean = new OffensesOutcome();
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { 
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_CASE_ID", OracleTypes.NUMBER),
				new SqlParameter("P_EVENT_ID", OracleTypes.NUMBER),
				new SqlParameter("P_OUTCOME_REASON_CODE", OracleTypes.VARCHAR),
				new SqlParameter("P_RESULT_CODE_1_INDICATOR", OracleTypes.VARCHAR),
				new SqlParameter("P_CHARGE_STATUS", OracleTypes.VARCHAR),
				};
		for(CourtEvent courtEvent : updateList)
		{
			String chargeStatus = "A";
			if(courtEvent.getChargeStatus()!=null &&
					( "Active".equalsIgnoreCase(courtEvent.getChargeStatus() )|| "A".equalsIgnoreCase(courtEvent.getChargeStatus()))
					) {
				chargeStatus = "A";
			} else {
				chargeStatus = "I";
			}
			SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
					.withCatalogName("TAG_LEGAL_CASES").withProcedureName("EVENT_CHARGES_UPDATE").declareParameters(sqlParameters);
			inParamMap.put("P_EVENT_ID", courtEvent.getEventId());
			inParamMap.put("P_CASE_ID", courtEvent.getCaseId());
			inParamMap.put("P_OFFENDER_BOOK_ID", courtEvent.getOffenderBookId());    
			inParamMap.put("P_OUTCOME_REASON_CODE", courtEvent.getOutcomeReasonCode());
			inParamMap.put("P_RESULT_CODE_1_INDICATOR", courtEvent.getDisposition());
			inParamMap.put("P_CHARGE_STATUS",chargeStatus);
			
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			} catch (Exception e) {
			logger.error("updateEventCharges"+e.getMessage());
			}
		}
		
		return bean;
		}
	
	@Override
	public Integer updateOrderCourtDate(List<CourtEvent> updateList) {
		Integer liReturn = 1;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { 
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_EVENT_ID", OracleTypes.NUMBER),
				new SqlParameter("P_EVENT_DATE", OracleTypes.DATE),
				};
		for(CourtEvent courtEvent : updateList)
		{
			SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
					.withCatalogName("TAG_LEGAL_CASES").withProcedureName("UPDATE_ORDER_COURT_DATE").declareParameters(sqlParameters);
			inParamMap.put("P_OFFENDER_BOOK_ID", courtEvent.getOffenderBookId()); 
			inParamMap.put("P_EVENT_ID", courtEvent.getEventId());
			inParamMap.put("P_EVENT_DATE", courtEvent.getEventDate());
			
			
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			} catch (Exception e) {
				liReturn = 0;
				logger.error("updateOrderCourtDate"+e.getMessage());
			}
		}
		
		return liReturn;
		}
	
	@Override
	public Date getPreInsertBookingDate(Long offenderBookId) {
		Date returnObj;
		final String sql = getQuery("OCDCASE_NEWCOURT_EVENT_GET_BOOKING_DATE");
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),Date.class);
		return returnObj;
	}
	
	@Override
	public Date getLatestMovement(Long offenderBookId) {
		Date returnObj;
		final String sql = getQuery("OCDCASE_NEWCOURT_EVENT_GET_LATEST_MOVEMENT");
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),Date.class);
		return returnObj;
	}

	@Override
	public Integer updateCourtEvent(List<CourtEvent> updateList) {
		final String sql = getQuery("UPDATE_COURT_EVENT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourtEvent list : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
			}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			} catch (Exception e) {
			logger.error("updateCourtEvent:", e);
			}
		if(updateList.size() == returnArray.length) {
			return 1;
			} else {
				return 0;
				}
	}

	@Override
	public List<Category> populateSentencesCategory() {
		List<Category> sentenceCategoryList = new ArrayList<Category>();
		final String sql = getQuery("LOV_SENTENCE_CATEGORY");		
		final RowMapper<Category> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,Category.class, sentenceCategoryMapping);
		try {
			sentenceCategoryList=  namedParameterJdbcTemplate.query(sql,referenceCodeRowMapper);
			} catch (Exception e) {
				logger.error("populateSentencesCategory"+e.getMessage());
				}
		return sentenceCategoryList;
	}
	
	@Override
	public List<Category> populateSentencesType(String sentenceCategory) {
		List<Category> sentenceTypeList = new ArrayList<Category>();
		final String sql = getQuery("LOV_SENTENCE_TYPE");		
		final RowMapper<Category> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,Category.class, sentenceTypeMapping);
		try {
			sentenceTypeList=  namedParameterJdbcTemplate.query(sql,createParams("SENTENCECATEGORY",sentenceCategory),referenceCodeRowMapper);
			} catch (Exception e) {
				logger.error("populateSentencesType"+e.getMessage());
				}
		return sentenceTypeList;
	}

	@Override
	public List<SentenceDate> populateSentenceDate(Long caseId) {
		List<SentenceDate> sentenceDateList = new ArrayList<SentenceDate>();
		final String sql = getQuery("LOV_SENTENCE_DATE");		
		final RowMapper<SentenceDate> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,SentenceDate.class, sentenceDateMapping);
		try {
			sentenceDateList=  namedParameterJdbcTemplate.query(sql,createParams("caseId",caseId),referenceCodeRowMapper);
			} catch (Exception e) {
				logger.error("populateSentenceDate"+e.getMessage());
			}
		
		
		return sentenceDateList;
	}

	@Override
	public List<Sentences> populateConsecutiveToLineData(Sentences sentences) {
		List<Sentences> consecutiveToLineList = new ArrayList<Sentences>();
		final String sql = getQuery("LOV_CONSEC_TO_SENTENCE_SEQ");		
		final RowMapper<Sentences> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,Sentences.class,consecutiveToLineMapping);
		try {
			consecutiveToLineList=  namedParameterJdbcTemplate.query(sql,createParams("offenderBookId", sentences.getOffenderBookId(),"sentenceSeq",sentences.getSentenceSeq()),referenceCodeRowMapper);
			} catch (Exception e) {
				logger.error("populateConsecutiveToLineData"+e.getMessage());
				}
		return consecutiveToLineList;
	}
	
	@Override
	public List<Category> populateSentenceStatus() {
		List<Category> sentenceCategoryList = new ArrayList<Category>();
		final String sql = getQuery("LOV_SENTENCE_STATUS");		
		final RowMapper<Category> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,Category.class, sentenceStatusMapping);
		try {
			sentenceCategoryList=  namedParameterJdbcTemplate.query(sql,referenceCodeRowMapper);
			} catch (Exception e) {
				logger.error("populateSentenceStatus"+e.getMessage());
				}
		return sentenceCategoryList;
	}

	@Override
	public List<BailStatus> populateBailStatus() {
		List<BailStatus> typeList = new ArrayList<BailStatus>();
		final String sql = getQuery("GET_BAIL_STATUS");		
		final RowMapper<BailStatus> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,	BailStatus.class, bailStatusMapping);
		try {
			typeList=  namedParameterJdbcTemplate.query(sql,referenceCodeRowMapper);
			} catch (Exception e) {
				logger.error("populateBailStatus"+e.getMessage());
				}
		return typeList;
	}

	@Override
	public List<BondType> populateBondType() {
		List<BondType> typeList = new ArrayList<BondType>();
		final String sql = getQuery("GET_BAIL_BOND_TYPE");		
		final RowMapper<BondType> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,	BondType.class, bondTypeMapping);
		try {
			typeList=  namedParameterJdbcTemplate.query(sql,referenceCodeRowMapper);
			} catch (Exception e) {
				logger.error("populateBondType"+e.getMessage());
				}
		return typeList;
	}
	
	@Override
	public int[] insertBailDetail(List<OffenderBailDetails> updateRecord) {
		final String sql = getQuery("INSERT_BAIL_DETAILS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderBailDetails list : updateRecord){
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			 logger.error("insertBailDetail"+ e);
		}
		return returnArray;
	}

	@Override
	public Integer updateBailDetails(List<OffenderBailDetails> updateRecord) {
		final String sqlUpdate = getQuery("UPDATE_BAIL_DETAILS");
		final String sqlInsert = getQuery("INSERT_BAIL_DETAILS");
		int[] returnArrayInsert = new int[] {};
		int[] returnArrayUpdate = new int[] {};
		final List<SqlParameterSource> instparameters = new ArrayList<SqlParameterSource>();
		final List<SqlParameterSource> updtparameters = new ArrayList<SqlParameterSource>();

		updateRecord.stream().filter(obj -> obj.getAction().equals("I")).forEach(action -> {
			instparameters.add(new BeanPropertySqlParameterSource(action));
		});

		updateRecord.stream().filter(obj -> obj.getAction().equals("U")).forEach(action -> {
			updtparameters.add(new BeanPropertySqlParameterSource(action));
		});

		try {
			if (instparameters != null && instparameters.size() > 0) {
				returnArrayInsert = namedParameterJdbcTemplate.batchUpdate(sqlInsert,
						instparameters.toArray(new SqlParameterSource[0]));
			}
			if (updtparameters != null && updtparameters.size() > 0) {
				returnArrayUpdate = namedParameterJdbcTemplate.batchUpdate(sqlUpdate,
						updtparameters.toArray(new SqlParameterSource[0]));
			}
		} catch (Exception e) {
			logger.error("updateBailDetails" + e);
		}

		if (returnArrayInsert.length + returnArrayUpdate.length == updateRecord.size()) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public List<OffenderDetailsOffenses> populateBailOfences(Integer caseId) {
		List<OffenderDetailsOffenses> offenses = new ArrayList<OffenderDetailsOffenses>();
		final String sql = getQuery("POPULATE_OFFENDER_OFFENSES");		
		final RowMapper<OffenderDetailsOffenses> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,  OffenderDetailsOffenses.class, offenderOffenseMapping);
		try {
			offenses=  namedParameterJdbcTemplate.query(sql,createParams("caseId" ,caseId),referenceCodeRowMapper);
			} catch (Exception e) {
				logger.error("populateBailOfences"+e.getMessage());
				}
		return offenses;		
	}

	@Override
	public Integer updateBailBondDetails(OffenderBailDetails bailBond) {		    
		final String sql = getQuery("UPDATE_BOND_DETAILS");    
		int[] result = {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();		
			if(bailBond.getCashFlag() == "true") {
				bailBond.setCashFlag("Y");
			}else {
				bailBond.setCashFlag("N");
			}
			bailBond.setCreateDatetime(new Date());
			bailBond.setModifyDateTime(new Date());
			parameters.add(new BeanPropertySqlParameterSource(bailBond));
		
		try {
			result = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("updateBailBondDetails"+ e);
		}
		
		if(result.length>0) {
			return 1;
		} else {
			return 0;
		}
	}	
		
	
	@Override
	public List<OffensesOutcome> getOffenderOffences(String offenderBookId) {
		List<OffensesOutcome> offencesList = new ArrayList<>();
		final String sql = getQuery("OFFENDER_OFFENCES");
		final RowMapper<OffensesOutcome> offencesRowMapper = Row2BeanRowMapper.makeMapping(sql, OffensesOutcome.class, offendeOffencesMapping);
		try {
			offencesList=  namedParameterJdbcTemplate.query(sql,createParams("offenderBookId", offenderBookId), offencesRowMapper);
			} catch (Exception e) {
				logger.error("getOffenderOffences"+e.getMessage());
			}
		return offencesList;
	}

	@Override
	public List<OffenderBailDetails> getAllBailDetails(Integer bookId, Integer caseId) {
		List<OffenderBailDetails> bailDetails = new ArrayList<OffenderBailDetails>();
		final String sql = getQuery("GET_BAIL_DETAILS");		
		final RowMapper<OffenderBailDetails> bailDetailsRowMapper = Row2BeanRowMapper.makeMapping(sql,  OffenderBailDetails.class, bailDetailsMapping);
		try {
			bailDetails=  namedParameterJdbcTemplate.query(sql,createParams("bookId" ,bookId, "caseId", caseId),bailDetailsRowMapper);
			for(OffenderBailDetails bailDetail: bailDetails) {
				if(bailDetail.getBailChargeId()==null) {
						bailDetail.setCash(null);
						bailDetail.setCashFlag("N");
						bailDetail.setBailDate(null);
						bailDetail.setBailStatus(null);
						bailDetail.setProperty(null);
						bailDetail.setSurety(null);
						bailDetail.setJudge(null);
						bailDetail.setPreferedDateTime(null);
						bailDetail.setPreferedBy(null);
						bailDetail.setMethod(null);
						bailDetail.setReceiptText(null);
						bailDetail.setCommentText(null);
						
				}
			}
			} catch (Exception e) {
				logger.error("getAllBailDetails"+e.getMessage());
				}
		return bailDetails;		
	}

	@Override
	public Integer insertOffenderSentenceDetails(List<Sentences> newSentenceRecord) {
		final String sql = getQuery("INSERT_OFFENDER_SENTENCE_RECORD");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Sentences list : newSentenceRecord) {
			parameters.add(new BeanPropertySqlParameterSource(list));
			}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("insertOffenderSentenceDetails : ", e);
			}
		if(newSentenceRecord.size() == returnArray.length) {
			return 1;
			} else {
				return 0;
				}
	}
	
	@Override
	public Integer OffenderSentenceRecord(List<Sentences> newSentenceRecord) {
		final String sql = getQuery("UPDATE_SENTENCE_RECORD");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Sentences list : newSentenceRecord) {
			parameters.add(new BeanPropertySqlParameterSource(list));
			}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("OffenderSentenceRecord", e);
			}
		if(newSentenceRecord.size() == returnArray.length) {
			return 1;
			} else {
				return 0;
				}
	}

	@Override
	public Long getPreInsertSentenceSeq(Long offenderBookId) {
		Long returnObj = null;
		final String sql = getQuery("OCDCASE_PREINSERT_SENTENCE_SEQ");
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId",offenderBookId),Long.class);
		return returnObj;
	}

	@Override
	public Long getPreInsertLineSeq(Long offenderBookId) {
		Long returnObj = null;
		final String sql = getQuery("OCDCASE_PREINSERT_LINE_SEQ");
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId",offenderBookId),Long.class);
		return returnObj;
	}

	@Override
	public Integer insertOffenderSentenceTermRecord(List<Terms> sentenceTermRecordList) {
		final String sql = getQuery("INSERT_OFFENDER_SENTENCE_TERM");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Terms list : sentenceTermRecordList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
			}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("insertOffenderSentenceTermRecord", e);
			}
		if(sentenceTermRecordList.size() == returnArray.length) {
			return 1;
			} else {
				return 0;
				}
	}

	@Override
	public Integer getPreInsertTermSeq(Long offenderBookId, Long sentenceSeq) {
		Integer returnObj = null;
		final String sql = getQuery("GENERATE_TERM_SEQ");
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId",offenderBookId,"sentenceSeq",sentenceSeq),Integer.class);
		return returnObj;
	}
	
	@Override
	public Integer updateOffenderSentenceTerm(List<Terms> updatedList) {
		final String sql = getQuery("UPDATE_SENTENCE_TERMS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Terms list : updatedList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
			}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			} catch (Exception e) {
			logger.error("updateOffenderSentenceTerm", e);
			}
		if(updatedList.size() == returnArray.length) {
			return 1;
			} else {
				return 0;
				}
		}

	@Override
	public Integer insertOffensesOnSentenceByQuery(List<OffensesOutcome> offensesList) {
		final String sql = getQuery("INSERT_OFFENDER_OFFENSES_CHARGES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffensesOutcome list : offensesList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
			}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("insertOffensesOnSentenceByQuery", e);
			}
		if(offensesList.size() == returnArray.length) {
			return 1;
			} else {
				return 0;
				}
	}
	
/*******INSERT OFFENSES OUTCOME *******************************/
	
	/*@Override
	public Integer insertnewOffenceOutcome(List<OffensesOutcome> insertList) {
		
		final String sql = getQuery("INSERT_OFFENDER_OFFENSES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffensesOutcome list : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
			}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("insertOffensesOnSentenceByQuery : ", e);
			}
		if(insertList.size() == returnArray.length) {
			return 1;
			} else {
				return 0;
				}
	
		
	
	return 0;
	}*/
	
	@Override
	public Integer updateOffenceOutcome(List<OffensesOutcome> updatedList) {
		 try {
			 updatedList.stream().forEach((offenceOutCome) -> {
		                     if(this.updateCourtEventCharges(offenceOutCome)==1) {    
		                     }else{
		                    	 return;
		                     }
		                     
		                     if(offenceOutCome.getResultcode1()!=null && getEventChargesCount(offenceOutCome.getOffenderChargeId())>1) {
		                    	 OffensesOutcome lastestOffenceDetails = getLatestOffenceStatus(offenceOutCome);
		                    	 /**
		                    	  * NVL logic to assign below values
		                    	  */
		                    	 if(lastestOffenceDetails.getResultcode1()!=null) {
		                    		 offenceOutCome.setResultcode1(lastestOffenceDetails.getResultcode1());
		                    	 }
		                    	 if(lastestOffenceDetails.getResultcode1indicator()!=null) {
		                    		 offenceOutCome.setResultcode1indicator(lastestOffenceDetails.getResultcode1indicator());
		                    	 }
		                    	 if(lastestOffenceDetails.getChargeStatus()!=null) {
		                    		 offenceOutCome.setChargeStatus(lastestOffenceDetails.getChargeStatus());
		                    	 }
		                     }
		                     if(updateOffenderCharges(offenceOutCome)==1){
		                    	 
		                     }else {
		                    	 return;
		                     }
		                     if(	"A".equalsIgnoreCase(offenceOutCome.getChargeStatus()) 
		                             && 
		                            ("P".equalsIgnoreCase(offenceOutCome.getResultcode1indicator()) || 
		                            		"F".equalsIgnoreCase(offenceOutCome.getResultcode1indicator())
		                             )
		                       ) {
		                            
		                                   if(!this.isOrderExist(offenceOutCome)) {
		                                          //Insert Order into Orders
		                                          this.insertOrders(offenceOutCome);
		                                   } else {
		                                	   if(("I".equalsIgnoreCase(offenceOutCome.getChargeStatus()) 
		                                			   || "I".equalsIgnoreCase(offenceOutCome.getResultcode1indicator()))
		                                			   || (offenceOutCome.getChargeStatus()==null 
		                                			   || offenceOutCome.getResultcode1indicator()==null)) {
		                                		   if(this.isOrderExist(offenceOutCome) && (!this.isEventChargeFinalActive(offenceOutCome))) {
				                                          //delete from Orders
				                                          this.deleteOrders(offenceOutCome.getOffenderBookId(),offenceOutCome.getEventId());
				                                   }
		                                	   }
		                                   }
		                     			}      
		        });
	        } catch(Exception ex) {
	        	logger.error("updateOffenceOutcome",ex.getMessage());
	        	return 0;
	        }
	        
	        return 1;
	}
	
	
	

	@Override
	public Boolean isOffenceExist(OffensesOutcome offensesOutcome) {
		final String sql = getQuery("P_OFFENCE_REC");
		boolean isExist=false;
		Object eventId=null;
		try {
			eventId = namedParameterJdbcTemplate.queryForObject(sql,createParams(
					"p_off_book_id",offensesOutcome.getOffenderBookId(),
					"offence_range_date",new java.sql.Date(offensesOutcome.getRange().getTime()),
					"offence_code",offensesOutcome.getOffenceCode(),
					"p_offense_date",new java.sql.Date(offensesOutcome.getOffenseDate().getTime()),
					"p_event_id",offensesOutcome.getEventId(),
					"p_case_id",offensesOutcome.getCaseId()),Object.class);
			
		}catch(EmptyResultDataAccessException e) {
			isExist=false;
			logger.warn("isOffenceExist"+e);
		} 
		if(eventId!=null) {
			isExist=true;
		}
		return isExist;
	}

	@Override
	public String fetchStatuteCode(String offenceCode) {
		String statusCode=null;
		final String sql = getQuery("OCDCASE_FETCH_STATUTE_CODE");
		statusCode = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenceCode", offenceCode),String.class);
		return statusCode;
	}

	private Long insertOffenderCharges(OffensesOutcome offenceOutCome) {
		Long offenderChargeId = fetchChargeId();
        try {
	        	
	            offenceOutCome.setOffenderChargeId(offenderChargeId);
	        
	            final String sql = getQuery("INSERT_OFFENDER_OFFENSES");
	            final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	            parameters.add(new BeanPropertySqlParameterSource(offenceOutCome));
	            int[] returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	        } catch (Exception e) {
		               logger.error("fetchStatuteCode", e);
		               return null;
	        }
        return offenderChargeId;
 }


 private Boolean isOrderExist(OffensesOutcome offenses) {
	 String orderExist=null;
		final String sql = getQuery("IS_ORDER_EXIST");
		try {
			orderExist = namedParameterJdbcTemplate.queryForObject(sql,createParams("offenderBookId",offenses.getOffenderBookId(),"eventId",offenses.getEventId()),String.class);
		}catch(EmptyResultDataAccessException e) {
			return false;
		}catch(Exception e) {
			logger.error("isOrderExist"+e.getMessage());
		}
		if(orderExist.equals("X")) {
			return true;
		}else {
			return false;
		}
 }
 private Integer insertOrders(OffensesOutcome offenceOutcome) {
	 Integer liReturn = 1;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		
		sqlParameters = new SqlParameter[] { 
				
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_CASE_ID", OracleTypes.NUMBER),
				new SqlParameter("P_EVENT_ID", OracleTypes.NUMBER),
				new SqlParameter("p_result_code_1_indicator", OracleTypes.VARCHAR),
				new SqlParameter("p_charge_status", OracleTypes.VARCHAR),
				};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource)
				.withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_LEGAL_CASES")
				.withProcedureName("event_order_insert")
				.declareParameters(sqlParameters);
		
		inParamMap.put("P_OFFENDER_BOOK_ID", offenceOutcome.getOffenderBookId());    
		inParamMap.put("P_CASE_ID", offenceOutcome.getCaseId());
		inParamMap.put("P_EVENT_ID", offenceOutcome.getEventId());
		inParamMap.put("p_result_code_1_indicator", offenceOutcome.getResultcode1indicator());
		inParamMap.put("p_charge_status", offenceOutcome.getChargeStatus());
		
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			}catch (Exception e) {
				liReturn=0;
				logger.error("insertOrders"+e.getMessage());
				}
		
		return liReturn;
 }
 /**
 * Copy TAG_LEGAL_CASES.OFFENCES_INSERT logic 
  */
 @Override
 public Integer insertNewOffenceOutcome(List<OffensesOutcome> insertList) {
        try {
	        insertList.stream().forEach((offenceOutCome) -> {
	               //Insert Data into offender_charges If offender_charge_id IS NULL 
	               if(offenceOutCome.getOffenderChargeId()==null) {
	                     Long offenderChargeId = this.insertOffenderCharges(offenceOutCome);
	                     //After Successfully insertion INSERT INTO court_event_charges
	                     offenceOutCome.setOffenderChargeId(offenderChargeId);
	                     this.insertCourtEventCharges(offenceOutCome);
	                     
	                     if("A".equalsIgnoreCase(offenceOutCome.getChargeStatus()) 
	                                   && 
	                                   ("P".equalsIgnoreCase(offenceOutCome.getResultcode1indicator()) || "F".equalsIgnoreCase(offenceOutCome.getResultcode1indicator())
	                                   )) {
	                            
	                                   if(!this.isOrderExist(offenceOutCome)) {
	                                	   	this.insertOrders(offenceOutCome);
	                                   }
	                            
	                     }      
	               }
	               
	        });
        } catch(Exception ex) {
        	logger.error("insertNewOffenceOutcome",ex.getMessage());
        	return 0;
        }
        
        return 1;
 }
 
 
 @Override
 public List<OffensesOutcome> insertNewOffenceOutcomeROI(List<OffensesOutcome> insertList) {
        try {
	        insertList.stream().forEach((offenceOutCome) -> {
	               //Insert Data into offender_charges If offender_charge_id IS NULL 
	               if(offenceOutCome.getOffenderChargeId()==null) {
	                     Long offenderChargeId = this.insertOffenderCharges(offenceOutCome);
	                     //After Successfully insertion INSERT INTO court_event_charges
	                     offenceOutCome.setOffenderChargeId(offenderChargeId);
	                     this.insertCourtEventCharges(offenceOutCome);
	                     
	                     if("A".equalsIgnoreCase(offenceOutCome.getChargeStatus()) 
	                                   && 
	                                   ("P".equalsIgnoreCase(offenceOutCome.getResultcode1indicator()) || "F".equalsIgnoreCase(offenceOutCome.getResultcode1indicator())
	                                   )) {
	                            
	                                   if(!this.isOrderExist(offenceOutCome)) {
	                                	   	this.insertOrders(offenceOutCome);
	                                   }
	                            
	                     }      
	               }
	               
	        });
        } catch(Exception ex) {
        	logger.error("insertNewOffenceOutcome",ex.getMessage());
        	return null;
        }
        
        return insertList;
 }

 
	private Long fetchChargeId() {
		Long chargeId=null;
		final String sql = getQuery("OCDCCASE_PREINSERT_OFFENDER_CHARGE_ID");
		chargeId = namedParameterJdbcTemplate.queryForObject(sql, createParams(),Long.class);
		return chargeId;
	}
 
private Integer insertCourtEventCharges(OffensesOutcome offenses) {
		
		final String sql = getQuery("INSERT_COURT_EVENT_CHARGES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		
			parameters.add(new BeanPropertySqlParameterSource(offenses));
		
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("insertCourtEventCharges", e);
			}
		if(returnArray.length==1) {
			return 1;
			} else {
				return 0;
				}
 }	

private Integer updateCourtEventCharges(OffensesOutcome offenses) {
	
	final String sql = getQuery("UPDATE_COURT_EVENT_CHARGES");
	int[] returnArray = new int[] {};
	final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	
		parameters.add(new BeanPropertySqlParameterSource(offenses));
	
	try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	} catch (Exception e) {
		logger.error("updateCourtEventCharges", e);
		}
	if(returnArray.length==1) {
		return 1;
		} else {
			return 0;
			}
}

private Integer updateOffenderCharges(OffensesOutcome offenses) {
	
	final String sql = getQuery("UPDATE_OFFENDER_CHARGES");
	int[] returnArray = new int[] {};
	final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	
		parameters.add(new BeanPropertySqlParameterSource(offenses));
	
	try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	} catch (Exception e) {
		logger.error("updateOffenderCharges", e);
		}
	if(returnArray.length==1) {
		return 1;
		} else {
			return 0;
			}
}

private Boolean isEventChargeFinalActive(OffensesOutcome offenses) {
	 String orderExist=null;
		final String sql = getQuery("IS_EVENT_CHARGES_FINAL_ACTIVE");
		try {
			orderExist = namedParameterJdbcTemplate.queryForObject(sql,createParams("eventId",offenses.getEventId()),String.class);
		}catch(EmptyResultDataAccessException e) {
			return false;
		}catch(Exception e) {
			logger.error("isEventChargeFinalActive"+e.getMessage());
		}
		if(orderExist.equals("X")) {
			return true;
		}else {
			return false;
		}
}

private Integer deleteOrders(Long offenderBookId, Long eventId) {
	 Integer liReturn = 1;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		
		sqlParameters = new SqlParameter[] { 
				
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_EVENT_ID", OracleTypes.NUMBER),
				};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource)
				.withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_LEGAL_CASES")
				.withProcedureName("ORDERS_DELETE")
				.declareParameters(sqlParameters);
		
		inParamMap.put("P_OFFENDER_BOOK_ID", offenderBookId);
		inParamMap.put("P_EVENT_ID", eventId);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			}catch (Exception e) {
				liReturn=0;
				logger.error("deleteOrders"+e.getMessage());
				}
		
		return liReturn;
	}

private Integer getEventChargesCount(Long offenderChargeId) {
	Integer returnObj = null;
	final String sql = getQuery("GET_EVENT_CHARGES_COUNT");
	returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderChargeId",offenderChargeId),Integer.class);
	return returnObj;
	}

private OffensesOutcome getLatestOffenceStatus(OffensesOutcome offenses ) {
	 Integer liReturn = 1;
	 OffensesOutcome bean= new OffensesOutcome();
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		
		sqlParameters = new SqlParameter[] { 
				
				new SqlParameter("P_OFFENDER_CHARGE_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_RESULT_CODE", OracleTypes.VARCHAR),
				new SqlOutParameter("P_DISPOSITION_CODE", OracleTypes.VARCHAR),
				new SqlOutParameter("P_CHARGE_STATUS", OracleTypes.VARCHAR)
				
				};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource)
				.withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_LEGAL_CASES")
				.withProcedureName("ORDERS_INSERT")
				.declareParameters(sqlParameters);
		
		inParamMap.put("P_OFFENDER_CHARGE_ID", offenses.getOffenderChargeId());    
		inParamMap.put("P_RESULT_CODE", OracleTypes.VARCHAR);
		inParamMap.put("P_DISPOSITION_CODE", OracleTypes.VARCHAR);
		inParamMap.put("P_CHARGE_STATUS", OracleTypes.VARCHAR);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			if (returnObject !=null) {
				bean.setResultcode1(String.valueOf(returnObject.get("P_RESULT_CODE")));
				bean.setResultcode1indicator(String.valueOf(returnObject.get("P_DISPOSITION_CODE")));
				bean.setChargeStatus(String.valueOf(returnObject.get("P_CHARGE_STATUS")));
				}
				
			}catch (Exception e) {
				liReturn=0;
				logger.error("getLatestOffenceStatus"+e.getMessage());
				}
		
		return bean;
}


@Override
public Integer updateOrderCourtDate(CourtEvent courtEvent) {
	 Integer liReturn = 1;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		
		sqlParameters = new SqlParameter[] { 
				
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_EVENT_ID", OracleTypes.NUMBER),
				new SqlParameter("P_EVENT_DATE", OracleTypes.DATE),
				};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource)
				.withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_LEGAL_CASES")
				.withProcedureName("update_order_court_date")
				//.withoutProcedureColumnMetaDataAccess()
				.declareParameters(sqlParameters);
		
		inParamMap.put("P_OFFENDER_BOOK_ID", courtEvent.getOffenderBookId());    
		inParamMap.put("P_EVENT_ID", courtEvent.getEventId());
		inParamMap.put("P_EVENT_DATE", courtEvent.getEventDate());
		
		
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			}catch (Exception e) {
				liReturn=0;
				logger.error("updateOrderCourtDate"+e.getMessage());
				}
		
		return liReturn;
	}

@Override
public Boolean isSameEventExist(CourtEvent updatedData) {	
	SqlParameterSource  args = new MapSqlParameterSource()
		.addValue("p_offender_book_id", updatedData.getOffenderBookId())
		.addValue("p_case_id", updatedData.getCaseId())
		.addValue("p_event_date", updatedData.getNextEventDate())
		.addValue("p_start_time", updatedData.getNextEventStartTime());
					

	
	SimpleJdbcCall jdbcCall = new OracleSimpleJdbcCall(jdbcTemplate)
			.withSchemaName("OMS_OWNER")
			.withCatalogName("tag_legal_cases")
			.withFunctionName("is_same_event_exists")
			.withoutProcedureColumnMetaDataAccess();
	
	
	jdbcCall.declareParameters(	
		new SqlOutParameter("RETURN", Types.BOOLEAN),
		new SqlParameter("p_offender_book_id", Types.INTEGER),
		new SqlParameter("p_case_id", Types.INTEGER),
		new SqlParameter("p_event_date", Types.DATE),
		new SqlParameter("p_start_time", Types.DATE)
	);
		
	Boolean result = jdbcCall.executeFunction(Boolean.class, args);
	return result;
	}

	@Override
	public CourtEvent fetchLatestCourtevent(Long courtCaseId) {
		final String sql = getQuery("OCDCCASE_FETCH_EVENTS");
		CourtEvent courtEvent = null;
		final RowMapper<CourtEvent> courtEventRowMapper = Row2BeanRowMapper.makeMapping(sql, CourtEvent.class, courtEventMapping);
		try {
				List<CourtEvent> courtEvents=  namedParameterJdbcTemplate.query(sql,createParams("courtCaseId", courtCaseId), courtEventRowMapper);
				courtEvent = courtEvents!=null && courtEvents.size()>0?courtEvents.get(0):null;
			} catch (Exception e) {
				logger.error("getOffenderOffences"+e.getMessage());
			}
		return courtEvent;
	
	}

	@Override
	public String getActiveAgencyLocationDesc(String agyLoc) {
		String activeAgy = null;
		final String sql = getQuery("OCDCASE_GET_ACTIVE_AGY_LOC");
		activeAgy = namedParameterJdbcTemplate.queryForObject(sql, createParams("agyLocation", agyLoc),String.class);
		return activeAgy;
		} 
	
		@Override
		public List<OffensesOutcome> getLegalsDetails(String offenderBookId) {
			final String sql = getQuery("OCDCCASE_GET_LEGALS_DETAILS");
			List<OffensesOutcome> list = new ArrayList<OffensesOutcome>();
			final RowMapper<OffensesOutcome> courtEventRowMapper = Row2BeanRowMapper.makeMapping(sql,
					OffensesOutcome.class, courtEventMapping);
			try {
				list = namedParameterJdbcTemplate.query(sql, createParams("offender_book_id", offenderBookId),
						courtEventRowMapper);
			} catch (Exception e) {
				logger.error("Error in getLegalsDetails()", e);
			}
			return list;
		}
}


