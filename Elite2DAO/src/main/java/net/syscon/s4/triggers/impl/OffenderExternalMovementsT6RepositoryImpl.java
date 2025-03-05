package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisitVisitors;
import net.syscon.s4.pkgs.tag_visits.impl.TagVisitsRepositoryImpl;
import net.syscon.s4.triggers.AddresesTJNRepository;
import net.syscon.s4.triggers.OffenderExternalMovementsT6Repository;
@Repository
public class OffenderExternalMovementsT6RepositoryImpl extends RepositoryBase implements OffenderExternalMovementsT6Repository{

	private static Logger logger = LogManager.getLogger(OffenderExternalMovementsT6RepositoryImpl.class.getName());
	
	@Override
	public OffenderExternalMovements getOffenderExternalMovements(final Long pOffenderBookId,Long movementSeq) {
		final String sql = getQuery("GET_OFFENDER_EXTERNAL_MOVEMENTS");
		OffenderExternalMovements resp=new OffenderExternalMovements();
		try {
			resp = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDER_BOOK_ID", pOffenderBookId,"MOVEMENT_SEQ",movementSeq),
					new BeanPropertyRowMapper<OffenderExternalMovements>(OffenderExternalMovements.class));
		} catch (Exception e) {
			logger.error("getOffenderExternalMovements :" + e);
		}
		return resp;	
	}
	
	@Override
	public String getReferenceCodesDesc(final String cpRefDomain, final String cpRefCode) {
		final String sql = getQuery("GET_REFERENCE_CODES_DESC");
		String resp=null;
		try {
			resp = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("cp_ref_domain", cpRefDomain, "cp_ref_code", cpRefCode),
					String.class);
		} catch (Exception e) {
			logger.error("getReferenceCodesDesc :" + e);
		}
		return resp;	
	}
	
	@Override
	public Integer updateOffenderCipDetails(final Date movementTime, final String lvCommentText,String modifyUserId) {
		final String sql = getQuery("UPDATE_OFFENDER_CIP_DETAILS");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("movement_time", movementTime);
		inParamMap.put("lv_comment_text", lvCommentText);
		inParamMap.put("modifyUserId", lvCommentText);
		return namedParameterJdbcTemplate.update(sql, inParamMap);
	}

	

}
