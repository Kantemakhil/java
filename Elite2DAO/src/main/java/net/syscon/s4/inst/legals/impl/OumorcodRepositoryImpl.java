package net.syscon.s4.inst.legals.impl;

import java.util.ArrayList;
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
import net.syscon.s4.inst.legals.OumorcodRepository;
import net.syscon.s4.inst.legals.beans.OffenseResultCodes;

@Repository
public class OumorcodRepositoryImpl extends RepositoryBase implements OumorcodRepository {

	private static Logger logger = LogManager.getLogger(OumorcodRepositoryImpl.class);
	
	private final Map<String, FieldMapper> offenseResultCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("RESULT_CODE", 				new FieldMapper("code"))		
			.put("DESCRIPTION",  				new FieldMapper("description"))
			.put("DISPOSITION_CODE",  			new FieldMapper("disposition"))
			.put("CODE",  						new FieldMapper("dispositionDescription"))
			.put("OFFENSE_STATUS",  			new FieldMapper("offenseStatus"))
			.put("CONVICTION_FLAG",  			new FieldMapper("conviction")).build();

	@Override
	public List<OffenseResultCodes> offencesResultsCodes() {
		List<OffenseResultCodes> offencesResultsCodesData = new ArrayList<OffenseResultCodes>();
		final String sql = getQuery("RES_COD");		
		final RowMapper<OffenseResultCodes> offensesRowMapper = Row2BeanRowMapper.makeMapping(sql,OffenseResultCodes.class, offenseResultCodesMapping);
		try {
			offencesResultsCodesData = namedParameterJdbcTemplate.query(sql,createParams(), offensesRowMapper);
			} catch (Exception e) {
				logger.error("offencesResultsCodesData,"+e.getMessage());
				}
		return offencesResultsCodesData;
		}
	}


