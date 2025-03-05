package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.pkgs.OrAudit;
import net.syscon.s4.triggers.OffIdentVineIntfTrgRepository;
@Repository
public class OffIdentVineIntfTrgRepositoryImpl extends RepositoryBase implements OffIdentVineIntfTrgRepository{
	
	private static final Logger logger = LogManager.getLogger(OffIdentVineIntfTrgRepositoryImpl.class);
	
	@Override
	public OffenderBookings curBookOr(final Long vOffId) {
		OffenderBookings returnObj=null;
		final String sql = getQuery("OFF_IDENT_VINE_INTF_TRG_CUR_BOOK_OR");
		try {
			returnObj= namedParameterJdbcTemplate.queryForObject(sql, createParams("v_off_id",vOffId),new BeanPropertyRowMapper<OffenderBookings>(OffenderBookings.class));
		}catch (Exception e) {
			logger.error(e);
		}
		return returnObj;
	}

	@Override
	public Offenders curOff(final Long pOffId) {
		Offenders returnObj=null;
		final String sql = getQuery("OFF_IDENT_VINE_INTF_TRG_CUR_OFF");
		try {
			returnObj= namedParameterJdbcTemplate.queryForObject(sql, createParams("p_off_id",pOffId),new BeanPropertyRowMapper<Offenders>(Offenders.class));
		}catch (Exception e) {
			logger.error(e);
		}
		return returnObj;
	}
	
	@Override
	public Integer updateOrAudit(final OrAudit searchBean) {
		Integer retVal=null;
		final String sql = getQuery("OFF_IDENT_VINE_INTF_TRG_UPDATE_OR_AUDIT");
		try {
			retVal= namedParameterJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(searchBean));	
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}
	
	@Override
	public Integer insertOrAudit(final OrAudit searchBean) {
		Integer retVal=null;
		final String sql = getQuery("OFF_IDENT_VINE_INTF_TRG_INSERT_OR_AUDIT");
		try {
			retVal=namedParameterJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(searchBean));	
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

}
