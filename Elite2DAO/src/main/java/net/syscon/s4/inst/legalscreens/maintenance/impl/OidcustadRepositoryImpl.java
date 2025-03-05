package net.syscon.s4.inst.legalscreens.maintenance.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.legals.beans.OffenderSentenceAdjustment;
import net.syscon.s4.inst.legals.beans.SentenceAdjustment;
import net.syscon.s4.inst.legalscreens.maintenance.OidcustadRepository;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalSettings;
@Repository
public class OidcustadRepositoryImpl extends RepositoryBase implements OidcustadRepository {

	private static Logger logger = LogManager.getLogger(OidcustadRepositoryImpl.class.getName());
	

	

	@Override
	public Integer saveBookings(List<OffenderSentenceAdjustment> savesentences) {
		return insertUpdateRecords(getQuery("OIDCUSTAD_SAVE_BOOKINGS"),savesentences,"OIDCUSTAD_UPDATE_BOOKINGS");
	}


	@Override
	public Integer deleteBookings(List<OffenderSentenceAdjustment> deletesentences) {
		return insertUpdateRecords(getQuery("OIDCUSTAD_DELETE_BOOKINGS"),deletesentences,"OIDCUSTAD_DELETE_BOOKINGS");
	}


	@Override
	public Integer updateBookings(List<OffenderSentenceAdjustment> updatesentences) {
		return insertUpdateRecords(getQuery("OIDCUSTAD_UPDATE_BOOKINGS"),updatesentences,"OIDCUSTAD_UPDATE_BOOKINGS");
	}

	@Override
	public List<OffenderSentenceAdjustment> fetchBookingDetails(Long offenderBookId) {
		List<OffenderSentenceAdjustment> bookingAdjustments=findAll(getQuery("OIDCUSTAD_FETCH_BOOKING_DETAILS"),new MapSqlParameterSource("offenderBookId",offenderBookId),OffenderSentenceAdjustment.class);
		return bookingAdjustments;
	}

	public List<SentenceAdjustment> getBookingCodes(){
		return findAll(getQuery("OIDCUSTAD_FETCH_BOOKING_LOV_VALUES"),createParams(), SentenceAdjustment.class);
	}
	
	public List<SentenceAdjustment> getSentenceCodes(){
		return findAll(getQuery("OIDCUSTAD_FETCH_SENTENCE_LOV_VALUES"),createParams(), SentenceAdjustment.class);
	}
	
	public List<OffenderSentenceAdjustment> getSentenceData(String objectType,Long offenderBookId){
		return findAll(getQuery("OIDCUSTAD_FETCH_SENTENCE_DATA"),createParams("offenderBookId",offenderBookId,"objectId",objectType!=null?Long.parseLong(objectType):null), OffenderSentenceAdjustment.class);
	}

	public String  getDebitorCreditCode(String code){
		return findById(getQuery("OIDCUSTAD_FETCH_DEBIT_CREDIT_CODE"),createParams("sentenceAdjustCode",code), String.class)+","+getusagecode(code);
		
	}
	
	public String getusagecode(String code) {
		return findById(getQuery("OIDCUSTAD_FETCH_USAGE_CODE"),createParams("sentenceAdjustCode",code), String.class);	
	}
	
	
	public Date getEscapeCount(Long offenderBookId) {
		return findById(getQuery("OIDCUSTAD_FETCH_ESCAPE_COUNT"),createParams("offenderBookId",offenderBookId), Date.class);	
	}
	
	public List<LegalSettings> getRemissionEligibility() {
		return findAll(getQuery("OIDCUSTAD_FETCH_REMISSION_ELIGIBLITY"),createParams(), LegalSettings.class);	
	}
	
	public OffenderExternalMovements getIntakeDetails(Long offenderBookId) {
		return fetchById(getQuery("OIDCUSTAD_GET_INTKAE_DETAILS"),createParams("offenderBookId",offenderBookId),OffenderExternalMovements.class);
	}
	
	
	
	
	
	private <T> int insertUpdateRecords(String sql, List<T> lstOfProcessMain,String queryConst) {
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();

		lstOfProcessMain.forEach(t -> parameters.add(new BeanPropertySqlParameterSource(t)));
		if("OIDCUSTAD_DELETE_BOOKINGS".equals(queryConst)) {
			try {
				batchUpdatePreDeletedRows("offender_legal_adjustments", "offender_order_adjust_id=:offenderOrderAdjustId", parameters);
			} catch (Exception e) {
				logger.error("batchUpdatePreDeletedRows in insertUpdateRecords"+e);
			}
		}
		try {

			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			getLogMessage("insertUpdateRecords",e);
		}

		return returnArray.length>0?1:0;
	}

	private <T> List<T> findAll(String query,MapSqlParameterSource map, Class<T> clazz) {
		return namedParameterJdbcTemplate.query(query,map,new RowMapperResultSetExtractor<T>(new BeanPropertyRowMapper<T>(clazz)));
	}

	private <T> T findById(String query,MapSqlParameterSource map, Class<T> clazz) {
		T t=null;
		try {
			t= namedParameterJdbcTemplate.queryForObject(query,map,clazz);
		}catch (Exception e) {
			getLogMessage("findById",e);
		}
		return t;
	}
	
	private <T> T fetchById(String query,MapSqlParameterSource map, Class<T> clazz) {
		T t=null;
		try {
			t= namedParameterJdbcTemplate.queryForObject(query,map,new BeanPropertyRowMapper<T>(clazz));
		}catch (Exception e) {
			getLogMessage("findById",e);
		}
		return t;
	}

	private void getLogMessage(String methodName, Exception e) {
		logger.error("Method in " + this.getClass().getName() + " " + methodName, e);
	}
	
}
