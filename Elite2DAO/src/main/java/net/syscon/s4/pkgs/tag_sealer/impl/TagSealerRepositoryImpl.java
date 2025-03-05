package net.syscon.s4.pkgs.tag_sealer.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.functors.CatchAndRethrowClosure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.pkgs.tag_sealer.TagSealerRepository;
@Repository
public class TagSealerRepositoryImpl extends RepositoryBase implements TagSealerRepository {
	
	private final Map<String, FieldMapper> courtListMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId")).build();
	
	
	
	private static Logger logger = LogManager.getLogger(TagSealerRepositoryImpl.class.getName());
	@Override
	public List<Offenders> gTabs(String keyCol,String vpdSsOwner) {
		String sql=getQuery("GTABS");
		List<Offenders> a = new ArrayList<Offenders>();
		RowMapper<Offenders> mapper= Row2BeanRowMapper.makeMapping(sql, Offenders.class, courtListMapping);
		return  namedParameterJdbcTemplate.query(sql, createParams("key_col",keyCol,"vpd_ss_owner",vpdSsOwner),mapper);
	}
	
	
	@Override
	public List<Offenders> gRid(BigDecimal pOffId) {
		String sql=getQuery("GRID");
		RowMapper<Offenders> mapper= Row2BeanRowMapper.makeMapping(sql, Offenders.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_off_id",pOffId),mapper);
	}
	
	@Override
	public List<Offenders> gOid(BigDecimal pOffId) {
		String sql=getQuery("GOID");
		RowMapper<Offenders> mapper= Row2BeanRowMapper.makeMapping(sql, Offenders.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_off_id",pOffId),mapper);
	}
	
	@Override
	public List<OffenderBookings> gBid(BigDecimal pOffId) {
		String sql=getQuery("GBID");
		RowMapper<OffenderBookings> mapper= Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_off_id",pOffId),mapper);
	}
	
	@Override
	public BigDecimal getIndRid(BigDecimal pOb) {
		String sql=getQuery("GETINDRID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_ob",pOb),BigDecimal.class);
	}


	@Override
	public Integer buildSql(String sqlQuery) {
		Integer returnValue=0;
		try {		
			  returnValue=namedParameterJdbcTemplate.update(sqlQuery, createParams());
		} catch (Exception e) {
			logger.error("Exception in buildLockSql Method"+e);
			return returnValue;
		}
		return  returnValue;
	}


	@Override
	public List<OffenderBookings> buildLockSql(String sqlQuery) {
		List<OffenderBookings> returnList=new ArrayList<OffenderBookings>();
		RowMapper<OffenderBookings> mapper= Row2BeanRowMapper.makeMapping(sqlQuery, OffenderBookings.class, courtListMapping);
		try {		
			returnList =namedParameterJdbcTemplate.query(sqlQuery, createParams(),mapper);
		} catch (Exception e) {
			logger.error("Exception in buildLockSql Method"+e);
			return returnList;
		}
		return returnList;
	}

	@Override
	public String getDataType(String tableName, String coloumnName) {
		String sql=getQuery("TAG_SEALER_GET_TABLE_NAME");
		String dataType="";
		try {
			dataType=namedParameterJdbcTemplate.queryForObject(sql, createParams("table",tableName,"column",coloumnName),String.class);
		}catch (Exception e) {
			logger.error(e);
		}
		return dataType;
	}

}
