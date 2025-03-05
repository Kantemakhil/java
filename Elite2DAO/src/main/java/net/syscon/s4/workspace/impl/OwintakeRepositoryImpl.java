package net.syscon.s4.workspace.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffendersIntakeSummary;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.workspace.OwintakeRepository;

@Repository
public class OwintakeRepositoryImpl extends RepositoryBase implements OwintakeRepository{
	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger log = LogManager.getLogger(OwintakeRepositoryImpl.class.getName());
	
	private final Map<String, FieldMapper> offenderSummaryListMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID", 	new FieldMapper("offenderId"))
			.put("LAST_NAME", 	new FieldMapper("lastName"))
			.put("FIRST_NAME", 	new FieldMapper("firstName"))
			.put("ADMITTED_TIMESTAMP",   new FieldMapper("admittedDateTime"))
			.put("INTERNAL_LOCATION_CODE", new FieldMapper("locationCode"))
			.put("IMAGE_PRESENT",  new FieldMapper("imagePresent"))
			.put("PROPERTY_PRESENT",  new FieldMapper("propertyPresent"))
			.put("FINGERPRINT_PRESENT",  new FieldMapper("fingerprintPresent"))
			.put("TRUST_ACCOUNT_PRESENT", new FieldMapper("trustAccountPresent"))
			.put("ASSESSMENT_PRESENT",  new FieldMapper("assesmentPresent"))
			.put("LEGAL_CASE_PRESENT", new FieldMapper("legalCasePresent")).build();
	
	@Override
	public List<OffendersIntakeSummary> getOffendersSummary(String caseLoadId) {
		final String sql = getQuery("OMSS40_GET_OFFENDER_SUMMARY");
		final RowMapper<OffendersIntakeSummary> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffendersIntakeSummary.class,
				offenderSummaryListMapping);
		List<OffendersIntakeSummary> offendersSummaryList = new ArrayList<OffendersIntakeSummary>();
			offendersSummaryList =  namedParameterJdbcTemplate.query(sql,createParams("CASELOAD_ID",caseLoadId), columnRowMapper);
		return offendersSummaryList;
		
		    
	}

}
