package net.syscon.s4.pkgs.oumhlhis.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import net.syscon.s4.im.beans.AgyIntLocAmendQuery;
import net.syscon.s4.pkgs.VAgyIntLocAmendments;
import net.syscon.s4.pkgs.oumhlhis.OumhlhisPkgRepository;

@Repository
public class OumhlhisPkgRepositoryImpl extends RepositoryBase implements OumhlhisPkgRepository {

	final private static Logger logger = LogManager.getLogger(OumhlhisPkgRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIVING_UNIT_CODE", new FieldMapper("livingUnitCode")).build();

	@Override
	public String getDescCode(final String domain, final String prefCode) {
		final String sql = getQuery("GET_REFERENCECODE");
		String result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_Domain", domain, "p_RefCode", prefCode), String.class);
		} catch (Exception e) {
			logger.error("getDescCode :" + e);
		}
		return result;
	}

	@Override
	public List<AgyIntLocAmendQuery> agyIntLocAmendQuery(final AgyIntLocAmendQuery bean, final String getOrderBy) {
		 String sql = getQuery("P_REFCURSOR");
		List<AgyIntLocAmendQuery> vAgy = new ArrayList<AgyIntLocAmendQuery>();
		final RowMapper<AgyIntLocAmendQuery> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgyIntLocAmendQuery.class,
				mMapping);
	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    String strDate= formatter.format(bean.getpAmendDateFrom());  
	    String toDate = formatter.format(bean.getpAmendDateTo()); 
//		String agLocId=caseLoad(bean.getpAgyLocId());
		if (getOrderBy != null && !getOrderBy.isEmpty()) {
			sql = sql.concat(" "+getOrderBy);
		}else {
			sql = sql.concat(" ORDER BY amend_date DESC");
		}
		try {
			vAgy = namedParameterJdbcTemplate.query(sql,
					createParams("p_level_1_code", bean.getpLevel1Code(), "p_level_2_code", bean.getpLevel2Code(),
							"p_level_3_code", bean.getpLevel3Code(), "p_level_4_code", bean.getpLevel4Code(),
							"p_amend_date_from", strDate, "p_amend_date_to", toDate,
							"p_agy_loc_id",bean.getpAgyLocId()),
					mRowMapper);
		} catch (Exception e) {
			logger.error("agyIntLocAmendQuery :" + e);
		}
		return vAgy;
	}

	private String caseLoad(String desxcription) {
		final String sql = getQuery("GETTING_CASELOAD");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("DESCRIPTION", desxcription.toUpperCase()),
				String.class);
	}

}