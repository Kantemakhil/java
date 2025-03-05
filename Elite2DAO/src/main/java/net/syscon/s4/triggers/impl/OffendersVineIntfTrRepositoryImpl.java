package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OrderProposals;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.pkgs.AnAudit;
import net.syscon.s4.pkgs.MeAudit;
import net.syscon.s4.pkgs.OrAudit;
import net.syscon.s4.triggers.OffendersVineIntfTrRepository;
@Repository
public class OffendersVineIntfTrRepositoryImpl extends RepositoryBase implements OffendersVineIntfTrRepository{
	
	private static final Logger logger = LogManager.getLogger(OffendersVineIntfTrRepositoryImpl.class);

	private final Map<String, FieldMapper> referCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ",                  new FieldMapper("listSeq"))
			.put("CODE",                      new FieldMapper("code"))
			.put("DESCRIPTION",               new FieldMapper("description"))
			.put("PROFILE_TYPE",              new FieldMapper("profileType"))
			.build();
	@Override
	public OffenderBookings curActBookAn(final BigDecimal vRootOffId, final Long vOffId) {
		OffenderBookings returnObj=null;
		final String sql = getQuery("OFFENDERS_VINE_INTF_TRG_CUR_ACT_BOOK_AN");
		try {
			returnObj= namedParameterJdbcTemplate.queryForObject(sql, createParams("v_root_off_id", vRootOffId,"v_off_id",vOffId),new BeanPropertyRowMapper<OffenderBookings>(OffenderBookings.class));
		}catch (Exception e) {
			logger.error(e);
		}
		return returnObj;
	}
	@Override
	public Integer insertAnAudit(final AnAudit searchBean) {
		final String sql = getQuery("OFFENDERS_VINE_INTF_TRG_INSERT_AN_AUDIT");
		return namedParameterJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(searchBean));	
	}
	@Override
	public Offenders offendersExecuteQuery(final Long offenderId) {
		final String sql = getQuery("OFFENDERS_VINE_INTF_TRG_OFFEDER_EXECUTE_QUERY");
		final RowMapper<Offenders> offenders = Row2BeanRowMapper.makeMapping(sql,Offenders.class,referCodesMapping );
		return  namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offender_id",offenderId), offenders);
		
	}
	@Override
	public OffenderBookings curActBookOr(final Long vOffId) {
		OffenderBookings returnObj=null;
		final String sql = getQuery("OFFENDERS_VINE_INTF_TRG_CUR_ACT_BOOK_OR");
		try {
			returnObj= namedParameterJdbcTemplate.queryForObject(sql, createParams("v_off_id",vOffId),new BeanPropertyRowMapper<OffenderBookings>(OffenderBookings.class));
		}catch (Exception e) {
			logger.error(e);
		}
		return returnObj;
	}
	@Override
	public Integer updateOrAudit(final OrAudit searchBean) {
		final String sql = getQuery("OFFENDERS_VINE_INTF_TRG_UPDATE_OR_AUDIT");
		return namedParameterJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(searchBean));	
	}
	@Override
	public Integer insertOrAudit(final OrAudit searchBean) {
		final String sql = getQuery("OFFENDERS_VINE_INTF_TRG_INSERT_OR_AUDIT");
		return namedParameterJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(searchBean));	
	}
	@Override
	public Integer updateAnAudit(final AnAudit searchBean) {
		final String sql = getQuery("OFFENDERS_VINE_INTF_TRG_UPDATE_AN_AUDIT");
		return namedParameterJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(searchBean));	
	}
	@Override
	public OffenderBookings curActBookAnMe(final BigDecimal vRootOffId,final Long vOffId,final BigDecimal vOldRootOffId) {
		OffenderBookings returnObj=null;
		final String sql = getQuery("OFFENDERS_VINE_INTF_TRG_CUR_ACT_BOOK_AN_ME");
		try {
			returnObj= namedParameterJdbcTemplate.queryForObject(sql, createParams("v_root_off_id",vRootOffId,"v_off_id",vOffId,"v_old_root_off_id",vOldRootOffId),new BeanPropertyRowMapper<OffenderBookings>(OffenderBookings.class));
		}catch (Exception e) {
			logger.error(e);
		}
		return returnObj;
	}
	@Override
	public Integer curMeExist(final String offenderIdDisplay) {
		Integer returnObj=null;
		final String sql = getQuery("OFFENDERS_VINE_INTF_TRG_CUR_ME_EXIST");
		try {
			returnObj= namedParameterJdbcTemplate.queryForObject(sql, createParams("offender_id_display",offenderIdDisplay),Integer.class);
		}catch (Exception e) {
			logger.error(e);
		}
		return returnObj;
	}
	@Override
	public Integer insertMeAudit(final MeAudit searchBean) {
		final String sql = getQuery("OFFENDERS_VINE_INTF_TRG_INSERT_ME_AUDIT");
		return namedParameterJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(searchBean));	
	}
	
	

}
