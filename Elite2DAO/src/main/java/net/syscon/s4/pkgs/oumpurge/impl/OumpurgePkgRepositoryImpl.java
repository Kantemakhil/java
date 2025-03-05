package net.syscon.s4.pkgs.oumpurge.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.pkgs.oumpurge.OumpurgePkgRepository;

@Repository
public class OumpurgePkgRepositoryImpl extends RepositoryBase implements OumpurgePkgRepository {

	private static Logger logger = LogManager.getLogger(OumpurgePkgRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> courtListMapping = new ImmutableMap.Builder<String, FieldMapper>().build();

	@Override
	public Integer getCount(final String query) {
		final String sql = query;
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}
	
	@Override
	public List<Offenders> getOffenderId(Long pRootOffenderId) {
		String sql=getQuery("GET_OFFENDER_ID");
		//RowMapper<BigDecimal> mapper= Row2BeanRowMapper.makeMapping(sql, BigDecimal.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_root_offender_id",pRootOffenderId),new BeanPropertyRowMapper<Offenders>(Offenders.class));
	}
	
	@Override
	public List<OffenderBookings> getOffBookId(Long pOffId) {
		String sql=getQuery("GET_OFF_BOOK_ID");
		return namedParameterJdbcTemplate.query(sql, createParams("p_off_id",pOffId),new BeanPropertyRowMapper<OffenderBookings>(OffenderBookings.class));
	}



}