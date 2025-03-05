package net.syscon.s4.inst.legals.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.legals.OcusofncRepository;
import net.syscon.s4.inst.legals.beans.OffensesOutcome;
import net.syscon.s4.inst.legals.beans.Sentences;
import oracle.jdbc.OracleTypes;

@Repository
public class OcusofncRepositoryImpl extends RepositoryBase implements OcusofncRepository{

	private static Logger logger = LogManager.getLogger(OcupsrdeRepositoryImpl.class);
	
	final Map<String, FieldMapper> offensesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", 					new FieldMapper("offenderBookId"))
			.put("OFFENDER_CHARGE_ID", 					new FieldMapper("offenderChargeId"))
			.put("STATUTE_CODE", 						new FieldMapper("statuteCode"))
			.put("OFFENCE_CODE", 						new FieldMapper("offenceCode"))
			.put("OFFENCE_DATE", 						new FieldMapper("offenseDate"))
			.put("OFFENCE_RANGE_DATE", 					new FieldMapper("range"))
			.put("RESULT_CODE_1", 						new FieldMapper("result"))
			.put("RESULT_CODE_1_INDICATOR", 			new FieldMapper("disposition"))
			.build();

	@Override
	public List<OffensesOutcome> fetechOffensesdialogData(OffensesOutcome selectedSentenceData) {
		List<OffensesOutcome> offensesDailogList = new ArrayList<OffensesOutcome>();
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { 
				new SqlOutParameter("P_REF_CUR", OracleTypes.CURSOR),
				new SqlParameter("P_OFFENDER_BOOK_ID",  OracleTypes.NUMBER),
				new SqlParameter("P_SENTENCE_SEQ", 		OracleTypes.NUMBER),
				new SqlParameter("P_CASE_ID", 			OracleTypes.NUMBER),
				new SqlParameter("P_SENTENCE_CATEGORY", OracleTypes.VARCHAR),
				new SqlParameter("P_SENTENCE_CALC_TYPE",OracleTypes.VARCHAR),
				new SqlParameter("P_SENTENCE_TYPE", 	OracleTypes.VARCHAR),
				};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource)
				.withSchemaName("OMS_OWNER")
					.withCatalogName("TAG_LEGAL_CASES")
						.withProcedureName("charge_sentences_query")
							.declareParameters(sqlParameters);
		inParamMap.put("P_REF_CUR", OracleTypes.CURSOR);
		inParamMap.put("P_OFFENDER_BOOK_ID", selectedSentenceData.getOffenderBookId());    
		inParamMap.put("P_SENTENCE_SEQ", selectedSentenceData.getSentenceSeq());
		inParamMap.put("P_CASE_ID", selectedSentenceData.getCaseId());
		inParamMap.put("P_SENTENCE_CATEGORY", selectedSentenceData.getCategory());
		inParamMap.put("P_SENTENCE_CALC_TYPE", selectedSentenceData.getSentenceCalcType());
		inParamMap.put("P_SENTENCE_TYPE", selectedSentenceData.getSentenceType());
		
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			offensesDailogList = new ArrayList<>();
			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			Date date;
			final List<Map<String, String>> list = (List<Map<String, String>>) returnObject.get("P_REF_CUR");
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> childMap = list.get(i);
				OffensesOutcome bean = new OffensesOutcome();
				if(String.valueOf(childMap.get("APPLY_FLAG")).equals("Y")){
					bean.setApply(true);	
				}
				else {
					bean.setApply(false);
				}
				bean.setOffenseDescription(String.valueOf(childMap.get("OFFENCE_DESCRIPTION")));
				bean.setResult(String.valueOf(childMap.get("RESULT_CODE1_DESC")));
				bean.setDisposition(String.valueOf(childMap.get("RESULT_CODE_1_INDICATOR")));
				bean.setOffenderChargeId(Long.parseLong(String.valueOf(childMap.get("OFFENDER_CHARGE_ID"))));
				if(String.valueOf(childMap.get("OFFENCE_DATE"))=="null") {
					bean.setOffenseDate(null);
				}
				else {
					date = format.parse(String.valueOf(childMap.get("OFFENCE_DATE")));
					bean.setOffenseDate(date);
					}
				if(String.valueOf(childMap.get("OFFENCE_DATE_RANGE"))=="null") {
					bean.setRange(null);
				}
				else {
					date = format.parse(String.valueOf(childMap.get("OFFENCE_DATE_RANGE")));
					bean.setRange(date);
					}
				offensesDailogList.add(bean);
			}

		} catch (Exception e) {
			logger.error("fetechOffensesdialogData"+e.getMessage());
			}
		return offensesDailogList;
	}

	@Override
	public String fetchSentenceType(String category, String sentenceCalcType) {
		final String sql = getQuery("FETCH_SENTENCE_CATEGORY");
		final String sentenceType = namedParameterJdbcTemplate.queryForObject(sql,createParams("category",category, "sentenceCalcType", sentenceCalcType),String.class);
		return sentenceType;
	}
}	