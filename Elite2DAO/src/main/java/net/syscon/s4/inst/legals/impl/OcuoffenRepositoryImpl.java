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
import net.syscon.s4.inst.legals.OcuoffenRepository;
import net.syscon.s4.inst.legals.beans.Offenses;

@Repository
public class OcuoffenRepositoryImpl extends RepositoryBase implements OcuoffenRepository {

	private static Logger logger = LogManager.getLogger(OcuoffenRepositoryImpl.class);
	
	private final Map<String, FieldMapper> offencesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 			new FieldMapper("description"))		
			.put("HO_CODE",  				new FieldMapper("category"))
			.put("OFFENCE_CODE",  			new FieldMapper("code")).build();

	@Override
	public List<Offenses> offencesAgainstOrdersData() {
		List<Offenses> offensesSearchResult = new ArrayList<Offenses>();
		final String sql = getQuery("OFN_DATA_BLOCK");		
		final RowMapper<Offenses> offensesRowMapper = Row2BeanRowMapper.makeMapping(sql,Offenses.class, offencesMapping);
		try {
			offensesSearchResult=  namedParameterJdbcTemplate.query(sql,createParams(), offensesRowMapper);
			} catch (Exception e) {
				logger.error("Court Cases Exception: "+e.getMessage());
				}
		return offensesSearchResult;
		}
	}

