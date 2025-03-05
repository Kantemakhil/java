package net.syscon.s4.inst.legals.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.legals.OidsenkdRepository;
import net.syscon.s4.inst.legals.beans.SentenceAggregates;

@Repository
public class OidsenkdRepositoryImpl extends RepositoryBase implements OidsenkdRepository {
	
	private static Logger logger = LogManager.getLogger(OcuholdsRepositoryImpl.class);
	final Map<String, FieldMapper> sentenceAggregateMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SENTENCE_CATEGORY", 				new FieldMapper("sentenceCategory"))
			.put("SENTENCE_CALC_TYPE", 				new FieldMapper("sentenceCalcType"))
			.put("START_DATE",  		    		new FieldMapper("startDate"))
			.put("END_DATE",  		    			new FieldMapper("endDate"))
			.put("SENTENCE_TERM", 					new FieldMapper("sentenceTerm"))
			.put("ELIGIBLE_REMISSION", 				new FieldMapper("remissionElg"))
			.put("AGGREGATE_ADJUST_DAYS",   		new FieldMapper("aggregateAdjustDays"))
			.put("SED_CALCULATED_DATE", 			new FieldMapper("sedCalculatedDate"))
			.put("ARD_CALCULATED_DATE", 			new FieldMapper("ardCalculatedDate"))
			.put("CRD_CALCULATED_DATE", 			new FieldMapper("crdCalculatedDate"))
			.put("PED_CALCULATED_DATE",			 	new FieldMapper("pedCalculatedDate")).build();
	@Override
	public List<SentenceAggregates> populateAggregatesData(Long offenderBookId) {
		List<SentenceAggregates> sentenceAggregateList = new ArrayList<SentenceAggregates>();
		final String sql = getQuery("FETCH_SENTENCE_AGGREGATES_DATA");
		final RowMapper<SentenceAggregates> sentAggregateDataRowMapper = Row2BeanRowMapper.makeMapping(sql,SentenceAggregates.class, sentenceAggregateMapping);
		try {
			sentenceAggregateList=  namedParameterJdbcTemplate.query(sql,createParams("offenderBookId" ,offenderBookId),sentAggregateDataRowMapper);
			} catch (Exception e) {
				logger.error("populateAggregatesData : "+e.getMessage());
		}
		return sentenceAggregateList;
	}
	@Override
	public Integer calculateDaysBetween(Date startDate, Date endDate) {
		Integer daysToArdCrd = 0;
		final String sql = getQuery("CALC_DAYS_BETWEEN");
		daysToArdCrd = namedParameterJdbcTemplate.queryForObject(sql, createParams("startDate",startDate,"endDate",endDate),Integer.class);
		return daysToArdCrd;
	}
	
	

}
