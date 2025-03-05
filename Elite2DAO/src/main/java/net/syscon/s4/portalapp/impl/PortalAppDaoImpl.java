package net.syscon.s4.portalapp.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.VHeaderBlock2;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.portalapp.PortalAppDao;
import net.syscon.s4.portalapp.beans.CaseOffenseResponse;
import net.syscon.s4.portalapp.beans.CaseSentenceResponse;
import net.syscon.s4.portalapp.beans.CourtScheduleResponse;
import net.syscon.s4.portalapp.beans.OffenderInfo;
import net.syscon.s4.portalapp.beans.OutputBookingPayloadInfo;
import net.syscon.s4.portalapp.beans.RejectOffenderInfo;

@Repository
public class PortalAppDaoImpl extends RepositoryBase implements PortalAppDao {
	
	private static Logger log = LogManager.getLogger(PortalAppDaoImpl.class);
	
	private final Map<String, FieldMapper> Offeder_InPut_Payload = new ImmutableMap.Builder<String, FieldMapper>()
			.put("INPUT_PAYLOAD", 				new FieldMapper("inputPayload"))
			.put("INPUT_REQUEST_ID",  				new FieldMapper("requestId"))
			.put("CREATE_DATE",  				new FieldMapper("createDate"))
			.build();
	
	private final Map<String, FieldMapper> VHEADER_BLOCK_OFFENDERID = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID", 				new FieldMapper("offenderId"))
			.put("OFFENDER_ID_DISPLAY", 		new FieldMapper("offenderIdDisplay"))
			.put("OFFENDER_BOOK_ID", 				new FieldMapper("offenderBookId"))
			.build();
	
	private final Map<String, FieldMapper> Offeder_InPut_Payload_Non_Pending = new ImmutableMap.Builder<String, FieldMapper>()
			.put("INPUT_PAYLOAD", 				new FieldMapper("inputPayload"))
			.put("INPUT_REQUEST_ID",  				new FieldMapper("requestId"))
			.put("STATUS",  				new FieldMapper("requestStatus"))
			.put("CREATE_DATE",  				new FieldMapper("createDate"))
			.put("MODIFY_DATE",  				new FieldMapper("modifyDate"))
			.build();
	
	
	
	/**
	 * 
	 */
	@Override
	public List<OffenderInfo> getAllNewBookings(String actionCode) {
		List<OffenderInfo> offednerInfo = new ArrayList<>();
		final String sql=getQuery("GET_ALL_INPUT_PAYLOAD");
		final RowMapper<OffenderInfo> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderInfo.class, Offeder_InPut_Payload);
		 try{
			 offednerInfo = namedParameterJdbcTemplate.query(sql, createParams("action_code",actionCode, "status", "PENDING"), rowMapper);
			 }catch(Exception e){
				 log.error("getAllIWPTemplateList "+e.getMessage());
			 }
			return offednerInfo;
	}
	
	@Override
	public List<OffenderInfo> getAllNonPendingBookings() {
		List<OffenderInfo> offednerInfo = new ArrayList<>();
		final String sql=getQuery("GET_ALL_NON_PENDING_INPUT_PAYLOAD");
		final RowMapper<OffenderInfo> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderInfo.class, Offeder_InPut_Payload_Non_Pending);
		 try{
			 offednerInfo = namedParameterJdbcTemplate.query(sql, createParams("action_code","BOOKING", "status", "PENDING"), rowMapper);
			 }catch(Exception e){
				 log.error("getAllIWPTemplateList "+e.getMessage());
			 }
			return offednerInfo;
	}
	
	@Override
	public List<OffenderInfo> getFailedLegals() {
		List<OffenderInfo> offednerInfo = new ArrayList<>();
		final String sql=getQuery("GET_ALL_NON_PENDING_INPUT_PAYLOAD");
		final RowMapper<OffenderInfo> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderInfo.class, Offeder_InPut_Payload_Non_Pending);
		 try{
			 offednerInfo = namedParameterJdbcTemplate.query(sql, createParams("action_code","CASE", "status", "FAILED"), rowMapper);
			 }catch(Exception e){
				 log.error("getAllIWPTemplateList "+e.getMessage());
			 }
			return offednerInfo;
	}
	
	@Override
	public Long getNumberOfExactMatches(OffenderInfo offenderInfo) {
		Long assRelCount = 0L;
		final String sql = getQuery("NUMBER_OF_EXACT_MATCH");
		try {
			assRelCount = namedParameterJdbcTemplate.queryForObject(sql, createParams("personId", offenderInfo.getPersonId()),
					Long.class);
		} catch (final Exception e) {
			log.error("getNumberOfExactMatches", e);
		}
		return assRelCount;
	}
	
	@Override
	public Long getNumberOfMaybeMatches(OffenderInfo offenderInfo) {
		Long assRelCount = 0L;
		final String sql = getQuery("NUMBER_OF_MAY_BE_MATCHES");
		try {
			assRelCount = namedParameterJdbcTemplate.queryForObject(sql, 
					createParams("lastName", offenderInfo.getLastName().toUpperCase(),
					"firstName",offenderInfo.getFirstName().toUpperCase()),
					Long.class);
		} catch (final Exception e) {
			log.error("getNumberOfMaybeMatches", e);
		}
		return assRelCount;
	}
	
	@Override
	public List<VHeaderBlock2> getNumberOfMaybeMatchesOffeneder(OffenderInfo offenderInfo) {
		List<VHeaderBlock2> offednerInfo = new ArrayList<>();
		final String sql=getQuery("NUMBER_OF_MAY_BE_MATCHES_OFFENDER");
		final RowMapper<VHeaderBlock2> rowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock2.class, VHEADER_BLOCK_OFFENDERID);
		 try{
			 offednerInfo = namedParameterJdbcTemplate.query(sql, createParams("lastName", offenderInfo.getLastName().toUpperCase(),
						"firstName",offenderInfo.getFirstName().toUpperCase()), rowMapper);
			 }catch(Exception e){
				 log.error("getNumberOfExactMatchesOffeneder "+e.getMessage());
			 }
			return offednerInfo;
	}
	
	@Override
	public List<VHeaderBlock2> getNumberOfExactMatchesOffeneder(OffenderInfo offenderInfo) {
		List<VHeaderBlock2> offednerInfo = new ArrayList<>();
		final String sql=getQuery("NUMBER_OF_EXACT_MATCH_OFFENDER");
		final RowMapper<VHeaderBlock2> rowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock2.class, VHEADER_BLOCK_OFFENDERID);
		 try{
			 offednerInfo = namedParameterJdbcTemplate.query(sql, createParams("personId", offenderInfo.getPersonId()), rowMapper);
			 }catch(Exception e){
				 log.error("getNumberOfExactMatchesOffeneder "+e.getMessage());
			 }
			return offednerInfo;
	}
	
	@Override
	public Integer rejectPersonToAdmit(RejectOffenderInfo rejectOffenderInfo) {
		OutputBookingPayloadInfo outputBookingPayloadInfo = new OutputBookingPayloadInfo();
		outputBookingPayloadInfo.setErrorMessage(rejectOffenderInfo.getRejectionReason());
		ObjectMapper objectMapper = new ObjectMapper();
		Integer returnArray = null;
		try {
			String inputPayload = objectMapper.writeValueAsString(outputBookingPayloadInfo);
			final String sql = getQuery("REJECT_INPUT_PAYLOAD");
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("outputPayload", inputPayload);
			paramMap.put("requetId", rejectOffenderInfo.getRequestId());
			returnArray = namedParameterJdbcTemplate.update(sql, paramMap);
			if (returnArray != 0) {
				returnArray = 1;
				return returnArray;
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return returnArray;
	}
	
	@Override
	public Integer acceptPersonToAdmit(OffenderInfo offenderInfo) {
		OutputBookingPayloadInfo outputBookingPayloadInfo = new OutputBookingPayloadInfo();
		outputBookingPayloadInfo.setErrorMessage(null);
		outputBookingPayloadInfo.setOffenderBookId(offenderInfo.getOffenderBookId());
		outputBookingPayloadInfo.setBookingNumber(offenderInfo.getBookingNumber());
		outputBookingPayloadInfo.setOffenderId(offenderInfo.getOffenderId());
		ObjectMapper objectMapper = new ObjectMapper();
		Integer returnArray = null;
		try {
			String inputPayload = objectMapper.writeValueAsString(outputBookingPayloadInfo);
			final String sql = getQuery("ACCEPT_INPUT_PAYLOAD");
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("outputPayload", inputPayload);
			paramMap.put("requetId", offenderInfo.getRequestId());
			returnArray = namedParameterJdbcTemplate.update(sql, paramMap);
			if (returnArray != 0) {
				returnArray = 1;
				return returnArray;
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return returnArray;
	}
	
	@Override
	public Integer updateScheduleOutputPayload(CourtScheduleResponse courtScheduleResponse) {
		/*OutputBookingPayloadInfo outputBookingPayloadInfo = new OutputBookingPayloadInfo();
		outputBookingPayloadInfo.setErrorMessage(null);
		outputBookingPayloadInfo.setOffenderBookId(offenderInfo.getOffenderBookId());
		outputBookingPayloadInfo.setBookingNumber(offenderInfo.getBookingNumber());
		outputBookingPayloadInfo.setOffenderId(offenderInfo.getOffenderId());*/
		ObjectMapper objectMapper = new ObjectMapper();
		Integer returnArray = null;
		try {
			String inputPayload = objectMapper.writeValueAsString(courtScheduleResponse);
			final String sql = getQuery("SCHEDULE_UPDATE_OUTPUT_PAYLOAD");
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("status", courtScheduleResponse.getStatus());
			paramMap.put("outputPayload", inputPayload);
			paramMap.put("requetId", courtScheduleResponse.getRequestId());
			returnArray = namedParameterJdbcTemplate.update(sql, paramMap);
			if (returnArray != 0) {
				returnArray = 1;
				return returnArray;
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return returnArray;
	}
	
	@Override
	public Integer updateOffenseOutputPayload(CaseOffenseResponse courtScheduleResponse) {
		/*OutputBookingPayloadInfo outputBookingPayloadInfo = new OutputBookingPayloadInfo();
		outputBookingPayloadInfo.setErrorMessage(null);
		outputBookingPayloadInfo.setOffenderBookId(offenderInfo.getOffenderBookId());
		outputBookingPayloadInfo.setBookingNumber(offenderInfo.getBookingNumber());
		outputBookingPayloadInfo.setOffenderId(offenderInfo.getOffenderId());*/
		ObjectMapper objectMapper = new ObjectMapper();
		Integer returnArray = null;
		try {
			String inputPayload = objectMapper.writeValueAsString(courtScheduleResponse);
			final String sql = getQuery("SCHEDULE_UPDATE_OUTPUT_PAYLOAD");
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("status", courtScheduleResponse.getStatus());
			paramMap.put("outputPayload", inputPayload);
			paramMap.put("requetId", courtScheduleResponse.getRequestId());
			returnArray = namedParameterJdbcTemplate.update(sql, paramMap);
			if (returnArray != 0) {
				returnArray = 1;
				return returnArray;
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return returnArray;
	}
	
	@Override
	public Integer updateSentenceOutputPayload(CaseSentenceResponse courtScheduleResponse) {
		/*OutputBookingPayloadInfo outputBookingPayloadInfo = new OutputBookingPayloadInfo();
		outputBookingPayloadInfo.setErrorMessage(null);
		outputBookingPayloadInfo.setOffenderBookId(offenderInfo.getOffenderBookId());
		outputBookingPayloadInfo.setBookingNumber(offenderInfo.getBookingNumber());
		outputBookingPayloadInfo.setOffenderId(offenderInfo.getOffenderId());*/
		ObjectMapper objectMapper = new ObjectMapper();
		Integer returnArray = null;
		try {
			String outputPayload = objectMapper.writeValueAsString(courtScheduleResponse);
			final String sql = getQuery("SCHEDULE_UPDATE_OUTPUT_PAYLOAD");
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("status", courtScheduleResponse.getStatus());
			paramMap.put("outputPayload", outputPayload);
			paramMap.put("requetId", courtScheduleResponse.getRequestId());
			returnArray = namedParameterJdbcTemplate.update(sql, paramMap);
			if (returnArray != 0) {
				returnArray = 1;
				return returnArray;
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return returnArray;
	}
	
	@Override
	public Long getRootOfenderId(Long offenderId) {
		Long rootOffenderId = null;
		String sql = getQuery("GET_ROOT_OFFENDER_ID");
		try {
			rootOffenderId = namedParameterJdbcTemplate.queryForObject(sql, 
					createParams("offenderId", offenderId),
					Long.class);
		} catch (final Exception e) {
			log.error("getNumberOfMaybeMatches", e);
		}
		return rootOffenderId;
	}
	
	@Override
	public List<VHeaderBlock2> offenderBookingAvailable(List<String> offenderDisplayIds) {
		List<VHeaderBlock2> offednerInfo = new ArrayList<>();
		final String sql=getQuery("OFFENDER_BOOKING_EXIST");
		final RowMapper<VHeaderBlock2> rowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock2.class, VHEADER_BLOCK_OFFENDERID);
		 try{
			 offednerInfo = namedParameterJdbcTemplate.query(sql, createParams("offedner_display_ids", offenderDisplayIds), rowMapper);
			 }catch(Exception e){
				 log.error("getNumberOfExactMatchesOffeneder "+e.getMessage());
			 }
		return offednerInfo;
	}

}
