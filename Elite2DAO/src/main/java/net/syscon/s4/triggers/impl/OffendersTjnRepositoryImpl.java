package net.syscon.s4.triggers.impl;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.triggers.OffendersJn;
import net.syscon.s4.triggers.OffendersTjnRepository;
@Repository
public class OffendersTjnRepositoryImpl extends RepositoryBase implements OffendersTjnRepository{
	
	private static final Logger logger = LogManager.getLogger(OffendersTjnRepositoryImpl.class);
	
	private final Map<String, FieldMapper> referCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ",                  new FieldMapper("listSeq"))
			.put("CODE",                      new FieldMapper("code"))
			.put("DESCRIPTION",               new FieldMapper("description"))
			.put("PROFILE_TYPE",              new FieldMapper("profileType"))
			.build();

	@Override
	public Integer insertOffendersJn(OffendersJn obj) {
		Integer retVal=null;
		final String sql = getQuery("OFFENDERS_TJN_INSERT_OFFENDERS_JN");
		try {
			retVal=namedParameterJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(obj));	
		} catch(Exception e) {
			logger.error(e);
		}
		return retVal;
	}
	
	@Override
	public Offenders offendersExecuteQuery(final Long offenderId) {
		Offenders returnObj=null;
		final String sql = getQuery("OFFENDERS_TJN_OFFEDER_EXECUTE_QUERY");
		final RowMapper<Offenders> offendersMapper = Row2BeanRowMapper.makeMapping(sql,Offenders.class,referCodesMapping );
		try {
			returnObj=namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offender_id",offenderId), offendersMapper);
		}catch (Exception e) {
			logger.error(e);
		}
		return returnObj;
		
	}

}
