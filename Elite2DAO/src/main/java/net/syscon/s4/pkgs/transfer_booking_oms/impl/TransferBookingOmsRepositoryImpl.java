package net.syscon.s4.pkgs.transfer_booking_oms.impl;

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
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderIdentifier;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.AddressUsages;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.legalorders.OffenderCommunityFiles;
import net.syscon.s4.pkgs.transfer_booking_oms.TransferBookingOmsRepository;

@Repository
public class TransferBookingOmsRepositoryImpl extends RepositoryBase implements TransferBookingOmsRepository {

	private static Logger logger = LogManager.getLogger(TransferBookingOmsRepositoryImpl.class.getName());

	@Override
	public String getOffIdenTypeCur() {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_GET_OFF_IDEN_TYPE_CUR");
		String profileValue = null;
		try {
		profileValue = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
	} catch (Exception e) {
		logger.error("Exception occured in " + this.getClass().getName() + " in method getOffIdenTypeCur", e);
	}
		return profileValue;
	}

	@Override
	public Integer getNewSeqCur(Long pOffenderIdTo) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_GET_NEW_SEQ_CUR");
		Integer maxVal = null;
		try {
			maxVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("pOffenderIdTo", pOffenderIdTo), Integer.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getNewSeqCur", e);
		}
		return maxVal;
	}

	@Override
	public String getCaseloadTypeCur(String user) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_GET_CASELOAD_TYPE_CUR");
		String caseLoadTpe = null;
		try {
			caseLoadTpe = namedParameterJdbcTemplate.queryForObject(sql, createParams("user", user), String.class);
	} catch (Exception e) {
		logger.error("Exception occured in " + this.getClass().getName() + " in method getCaseloadTypeCur", e);
	}
		return caseLoadTpe;
	}

	@Override
	public Integer insertOffIdentRecord(List<OffenderIdentifier> insertList) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_INSERT_OFF_IDENT_RECORD");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderIdentifier sentenceTerms : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offMailRestrictionsInsert", e);
		}
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public Integer deleteOffenderIdentifier(String lvIdentType, Long pRootOffenderIdTo,String modifyUserId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_DELETE_OFF_IDENT_RECORD");
		int result = 0;
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "OFFENDER_IDENTIFIERS";
			String whereCondition = "IDENTIFIER_TYPE = :lvIdentType AND ROOT_OFFENDER_ID = :pRootOffenderIdTo";
			inputMap.put("lvIdentType", lvIdentType);
			inputMap.put("pRootOffenderIdTo", pRootOffenderIdTo);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteOffenderIdentifier " + e.getMessage());
		}
		try {
			result= namedParameterJdbcTemplate.update(sql, createParams("lvIdentType", lvIdentType , "pRootOffenderIdTo",pRootOffenderIdTo));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteOffenderIdentifier", e);
		}
		return result;
	}
	
	@Override
	public List<OffenderNonAssociations> txnferNonAssoc1(Long pFromOffenderBookId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_TXNFER_NON_ASSOC_1");
		List<OffenderNonAssociations> offenderNonAssVals = new ArrayList<OffenderNonAssociations>();
		try {
			offenderNonAssVals = namedParameterJdbcTemplate.query(sql,
					createParams("pFromOffenderBookId", pFromOffenderBookId),
					new RowMapperResultSetExtractor<OffenderNonAssociations>(
							new BeanPropertyRowMapper<OffenderNonAssociations>(OffenderNonAssociations.class)));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method txnferNonAssoc1", e);
		}
		return offenderNonAssVals;
	}

	@Override
	public List<OffenderNonAssociations> txnferNonAssoc2(Long pFromOffenderBookId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_TXNFER_NON_ASSOC_2");
		List<OffenderNonAssociations> offenderNonAssVals = new ArrayList<OffenderNonAssociations>();
		try {
			offenderNonAssVals = namedParameterJdbcTemplate.query(sql,
					createParams("pFromOffenderBookId", pFromOffenderBookId),
					new RowMapperResultSetExtractor<OffenderNonAssociations>(
							new BeanPropertyRowMapper<OffenderNonAssociations>(OffenderNonAssociations.class)));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method txnferNonAssoc2", e);
		}
		return offenderNonAssVals;
	}

	@Override
	public List<OffenderNaDetails> txnferNaDetails1(Long pFromOffenderBookId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_TXNFER_NA_DETAILS_1");
		List<OffenderNaDetails> offenderNAVals = new ArrayList<OffenderNaDetails>();
		try {
			offenderNAVals = namedParameterJdbcTemplate.query(sql,
					createParams("pFromOffenderBookId", pFromOffenderBookId),
					new RowMapperResultSetExtractor<OffenderNaDetails>(
							new BeanPropertyRowMapper<OffenderNaDetails>(OffenderNaDetails.class)));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method txnferNaDetails1", e);
		}
		return offenderNAVals;
	}

	@Override
	public List<OffenderNaDetails> txnferNaDetails2(Long pFromOffenderBookId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_TXNFER_NA_DETAILS_2");
		List<OffenderNaDetails> offenderNAVals = new ArrayList<OffenderNaDetails>();
		try {
			offenderNAVals = namedParameterJdbcTemplate.query(sql,
					createParams("pFromOffenderBookId", pFromOffenderBookId),
					new RowMapperResultSetExtractor<OffenderNaDetails>(
							new BeanPropertyRowMapper<OffenderNaDetails>(OffenderNaDetails.class)));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method txnferNaDetails2", e);
		}
		return offenderNAVals;
	}

	@Override
	public Long getLatestBooking(Long pToOffenderId, Long pFromOffenderBookId) {
		
		final String sql = getQuery("TRANSFER_BOOKING_OMS_GET_LATEST_BOOKING");
		Long processId = null;
		try {
			processId = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("pToOffenderId", pToOffenderId, "pFromOffenderBookId", pFromOffenderBookId), Long.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getLatestBooking", e);
		}
		return processId;
	}
	
	
	
	@Override
	public Integer updateOffenderNonAssociations(Long pToOffenderId, Long offenderId, Long nsOffenderId, String user) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_UPDATE_OFFENDER_NON_ASSOCIATIONS");
		int result = 0;
		try {
			result = namedParameterJdbcTemplate.update(sql, createParams("pToOffenderId", pToOffenderId,
					"offenderId", offenderId, "nsOffenderId", nsOffenderId, "modifyUserId",user));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method updateOffenderNonAssociations", e);
		}
		return result;
	}

	@Override
	public Integer updateOffenderNaDetails(Long pToOffenderId, Long offenderId, Long nsOffenderId, String user) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_UPDATE_OFFENDER_NA_DETAILS");
		int result = 0;
		try {
			result= namedParameterJdbcTemplate.update(sql, createParams("pToOffenderId", pToOffenderId, "offenderId", offenderId , "nsOffenderId",nsOffenderId, "modifyUserId",user));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method updateOffenderNaDetails", e);
		}
		return result;
	}

	@Override
	public Integer deleteOffenderNaDetails(Long offenderId, Long nsOffenderId,String modifyUserId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_DELETE_OFFENDER_NA_DETAILS");
		int result = 0;
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "OFFENDER_NA_DETAILS";
			String whereCondition = "OFFENDER_ID = :offenderId AND NS_OFFENDER_ID = :nsOffenderId";
			inputMap.put("offenderId", offenderId);
			inputMap.put("nsOffenderId", nsOffenderId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteOffenderNaDetails " + e.getMessage());
		}
		try {
			result= namedParameterJdbcTemplate.update(sql, createParams("offenderId", offenderId , "nsOffenderId",nsOffenderId));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteOffenderNaDetails", e);
		}
		return result;
	}

	@Override
	public Integer deleteOffenderNonAssociations(Long offenderId, Long nsOffenderId,String modifyUserId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_DELETE_OFFENDER_NON_ASSOCIATIONS");
		int result = 0;
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "OFFENDER_NON_ASSOCIATIONS";
			String whereCondition = "OFFENDER_ID = :offenderId AND NS_OFFENDER_ID = :nsOffenderId";
			inputMap.put("offenderId", offenderId);
			inputMap.put("nsOffenderId", nsOffenderId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteOffenderNonAssociations " + e.getMessage());
		}
		try {
			result= namedParameterJdbcTemplate.update(sql, createParams("offenderId", offenderId , "nsOffenderId",nsOffenderId));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteOffenderNonAssociations", e);
		}
			return result;
	}

	@Override
	public Integer updateOffenderNaDetails1(Long lvLatestBookingId, Long pToOffenderId, Long nsOffenderId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_UPDATE_OFFENDER_NA_DETAILS1");
		int result = 0;
		try {
			result= namedParameterJdbcTemplate.update(sql, createParams("lvLatestBookingId", lvLatestBookingId, "pToOffenderId", pToOffenderId , "nsOffenderId",nsOffenderId));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method updateOffenderNaDetails1", e);
		}
		return result;
	}
	
	@Override
	public Integer updateOffenderNonAssociations1(Long lvLatestBookingId, Long pToOffenderId, Long nsOffenderId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_UPDATE_OFFENDER_NON_ASSOCIATIONS1");
		int result = 0;
		try {
			result= namedParameterJdbcTemplate.update(sql, createParams("lvLatestBookingId", lvLatestBookingId, "pToOffenderId", pToOffenderId , "nsOffenderId",nsOffenderId));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method updateOffenderNonAssociations1", e);
		}
		return result;
	}

	
	
	

	@Override
	public Integer updateOffenderNonAssociations2(Long pToOffenderId, Long offenderId, Long nsOffenderId, String user) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_UPDATE_OFFENDER_NON_ASSOCIATIONS2");
		int result = 0;
		try {
			result= namedParameterJdbcTemplate.update(sql, createParams("pToOffenderId", pToOffenderId, "offenderId", offenderId , "nsOffenderId",nsOffenderId, "modifyUserId",user));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method updateOffenderNonAssociations2", e);
		}
		return result;
	}

	@Override
	public Integer updateOffenderNaDetails2(Long pToOffenderId, Long offenderId, Long nsOffenderId, String user) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_UPDATE_OFFENDER_NA_DETAILS2");
		int result = 0;
		try {
			result= namedParameterJdbcTemplate.update(sql, createParams("pToOffenderId", pToOffenderId, "offenderId", offenderId , "nsOffenderId",nsOffenderId, "modifyUserId",user));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method updateOffenderNaDetails2", e);
		}
		return result;
	}

	@Override
	public Integer deleteOffenderNaDetails2(Long offenderId, Long nsOffenderId,String modifyUserId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_DELETE_OFFENDER_NA_DETAILS2");
		int result = 0;
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "OFFENDER_NA_DETAILS";
			String whereCondition = "OFFENDER_ID = :offenderId AND NS_OFFENDER_ID = :nsOffenderId";
			inputMap.put("offenderId", offenderId);
			inputMap.put("nsOffenderId", nsOffenderId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteOffenderNaDetails2 " + e.getMessage());
		}
		try {
			result= namedParameterJdbcTemplate.update(sql, createParams("offenderId", offenderId , "nsOffenderId",nsOffenderId));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteOffenderNaDetails2", e);
		}
		return result;
	}

	@Override
	public Integer deleteOffenderNonAssociations2(Long offenderId, Long nsOffenderId,String modifyUserId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_DELETE_OFFENDER_NON_ASSOCIATIONS2");
		int result = 0;
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "OFFENDER_NON_ASSOCIATIONS";
			String whereCondition = "OFFENDER_ID = :offenderId AND NS_OFFENDER_ID = :nsOffenderId";
			inputMap.put("offenderId", offenderId);
			inputMap.put("nsOffenderId", nsOffenderId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteOffenderNonAssociations2 " + e.getMessage());
		}
		try {
			result= namedParameterJdbcTemplate.update(sql, createParams("offenderId", offenderId , "nsOffenderId",nsOffenderId));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteOffenderNonAssociations2", e);
		}
		return result;
	}

	@Override
	public Integer updateOffenderNaDetails21(Long lvLatestBookingId, Long offenderId, Long pToOffenderId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_UPDATE_OFFENDER_NA_DETAILS21");
		int result = 0;
		try {
			result= namedParameterJdbcTemplate.update(sql, createParams("lvLatestBookingId", lvLatestBookingId, "offenderId",offenderId, "pToOffenderId", pToOffenderId));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method updateOffenderNaDetails21", e);
		}
		return result;
	}

	@Override
	public Integer updateOffenderNonAssociations21(Long lvLatestBookingId, Long offenderId, Long pToOffenderId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_UPDATE_OFFENDER_NON_ASSOCIATIONS21");
		int result = 0;
		try {
			result= namedParameterJdbcTemplate.update(sql, createParams("lvLatestBookingId", lvLatestBookingId, "offenderId",offenderId, "pToOffenderId", pToOffenderId));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method updateOffenderNonAssociations21", e);
		}
		return result;
	}

	
	
	
	@Override
	public Integer phoneNumExists(Long pPhoneId, Long pFromOffenderId, Long pToOffenderId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_PHONE_NUM_EXISTS");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("pPhoneId", pPhoneId, "pFromOffenderId", pFromOffenderId, "pToOffenderId", pToOffenderId), Integer.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method phoneNumExists", e);
		}
		return count;
	}

	@Override
	public List<Phones> transPhones(Date pStartDate, Date pEndDate,Long pFromOffenderId) {
		List<Phones> list = new ArrayList<Phones>();
		final String sq = getQuery("TRANSFER_BOOKING_OMS_GET_TRANS_PHONES") ;
		final String sql = "select PHONE_ID from PHONES where OWNER_CLASS = 'OFF'and OWNER_ID = :pFromOffenderId and CREATE_DATETIME between :pStartDate and :pEndDate and (MODIFY_DATETIME is null or MODIFY_DATETIME between :pStartDate and :pEndDate)";
		try {
			list = namedParameterJdbcTemplate.query(sql,
					createParams("pStartDate", pStartDate, "pEndDate", pEndDate, "pFromOffenderId", pFromOffenderId),
					new RowMapperResultSetExtractor<Phones>(
							new BeanPropertyRowMapper<Phones>(Phones.class)));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method transPhones", e);
		}
		return list;
	}

	@Override
	public List<Phones> lockPhone(String pOwnerClass, Long pOwnerId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_LOCK_PHONE");
		List<Phones> list = new ArrayList<Phones>();
		try {
			list = namedParameterJdbcTemplate.query(sql,
					createParams("pOwnerClass", pOwnerClass, "pOwnerId", pOwnerId),
					new RowMapperResultSetExtractor<Phones>(
							new BeanPropertyRowMapper<Phones>(Phones.class)));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method lockPhone", e);
		}
		return list;
	}

	@Override
	public Integer deletePhones(Long pPhoneId,String modifyUserId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_DELETE_PHONE");
		int result = 0;
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "PHONES";
			String whereCondition = "PHONE_ID = :pPhoneId";
			inputMap.put("pPhoneId", pPhoneId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deletePhones " + e.getMessage());
		}
		try {
			result = namedParameterJdbcTemplate.update(sql, createParams("pPhoneId", pPhoneId));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deletePhones", e);
		}
		return result;
	}

	@Override
	public Integer updatePhones(Long pOwnerId, Long pPhoneId, String user) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_UPDATE_PHONE");
		int result = 0;
		try {
			result = namedParameterJdbcTemplate.update(sql, createParams("pOwnerId", pOwnerId, "pPhoneId", pPhoneId, "modifyUserId", user));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method updatePhones", e);
		}
		return result;
	}

	@Override
	public Integer addressExists(Long pAddressId, Long pFromOffenderId, Long pToOffenderId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_ADDRESS_EXISTS");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("pAddressId", pAddressId, "pFromOffenderId", pFromOffenderId, "pToOffenderId", pToOffenderId), Integer.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method addressExists", e);
		}
		return count;
	}

	@Override
	public List<Addresses> transAddresses(Date pStartDate, Date pEndDate, Long pFromOffenderId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_ADDR_DATA");
		List<Addresses> list = new ArrayList<Addresses>();
		try {
			list = namedParameterJdbcTemplate.query(sql,
					createParams("pStartDate", pStartDate, "pEndDate", pEndDate, "pFromOffenderId", pFromOffenderId),
					new RowMapperResultSetExtractor<Addresses>(
							new BeanPropertyRowMapper<Addresses>(Addresses.class)));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method transAddresses", e);
		}
		return list;
	}

	@Override
	public List<InternetAddresses> lockInternetAddr(String pOwnerClass, Long pOwnerId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_LOCK_INTERNET_ADDR");
		List<InternetAddresses> list = new ArrayList<InternetAddresses>();
		try {
			list = namedParameterJdbcTemplate.query(sql,
					createParams("pOwnerClass", pOwnerClass, "pOwnerId", pOwnerId),
					new RowMapperResultSetExtractor<InternetAddresses>(
							new BeanPropertyRowMapper<InternetAddresses>(InternetAddresses.class)));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method lockInternetAddr", e);
		}
		return list;
	}

	@Override
	public List<AddressUsages> lockAddressUsage(Long pAddressId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_LOCK_ADDRESS_USAGE");
		List<AddressUsages> list = new ArrayList<AddressUsages>();
		try {
			list = namedParameterJdbcTemplate.query(sql,
					createParams("pAddressId", pAddressId),
					new RowMapperResultSetExtractor<AddressUsages>(
							new BeanPropertyRowMapper<AddressUsages>(AddressUsages.class)));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method lockAddressUsage", e);
		}
		return list;
	}

	@Override
	public Integer deletePhonesByAddId(Long pAddressId,String modifyUserId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_DELETE_PHONES_BY_ADDR_ID");
		int result = 0;
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "PHONES";
			String whereCondition = "OWNER_CLASS = 'ADDR' AND OWNER_ID = :pAddressId";
			inputMap.put("pAddressId", pAddressId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deletePhonesByAddId " + e.getMessage());
		}
		try {
			result = namedParameterJdbcTemplate.update(sql, createParams("pAddressId", pAddressId));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deletePhonesByAddId", e);
		}
		return result;
	}

	@Override
	public Integer deleteInternetAddresses(Long pAddressId,String modifyUserId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_DELETE_INTERNET_ADDRESSES");
		int result = 0;
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "INTERNET_ADDRESSES";
			String whereCondition = "OWNER_CLASS = 'ADDR' AND OWNER_ID = :pAddressId";
			inputMap.put("pAddressId", pAddressId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteInternetAddresses " + e.getMessage());
		}
		try {
			result = namedParameterJdbcTemplate.update(sql, createParams("pAddressId", pAddressId));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteInternetAddresses", e);
		}
		return result;
	}

	@Override
	public Integer deleteAddressUsages(Long pAddressId,String modifyUserId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_DELETE_ADDRESS_USAGES");
		int result = 0;
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "ADDRESS_USAGES";
			String whereCondition = "ADDRESS_ID = :pAddressId";
			inputMap.put("pAddressId", pAddressId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteAddressUsages " + e.getMessage());
		}
		try {
			result = namedParameterJdbcTemplate.update(sql, createParams("pAddressId", pAddressId));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteAddressUsages", e);
		}
		return result;
	}

	@Override
	public Integer deleteAddresses(Long pAddressId,String modifyUserId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_DELETE_ADDRESSES");
		int result = 0;
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "ADDRESSES";
			String whereCondition = "ADDRESS_ID = :pAddressId";
			inputMap.put("pAddressId", pAddressId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteAddresses " + e.getMessage());
		}
		try {
			result = namedParameterJdbcTemplate.update(sql, createParams("pAddressId", pAddressId));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteAddresses", e);
		}
		return result;
	}

	@Override
	public Integer updateAddresses(Long pToOffenderId, Long pAddressId, String user) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_UPDATE_ADDRESSES");
		int result = 0;
		try {
			result = namedParameterJdbcTemplate.update(sql, createParams("pToOffenderId", pToOffenderId, "pAddressId", pAddressId, "modifyUserId", user));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method updateAddresses", e);
		}
		return result;
	}

	@Override
	public Integer internetAddressExists(Long pIntAddressId, Long pFromOffenderId, Long pToOffenderId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_INT_ADDRESS_EXISTS");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("pIntAddressId", pIntAddressId, "pFromOffenderId", pFromOffenderId, "pToOffenderId", pToOffenderId), Integer.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method internetAddressExists", e);
		}
		return count;
	}

	@Override
	public List<InternetAddresses> transIntAddr(Date pStartDate, Date pEndDate, Long pFromOffenderId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_TRANS_INT_ADDR");
		List<InternetAddresses> list = new ArrayList<InternetAddresses>();
		try {
			list = namedParameterJdbcTemplate.query(sql,
					createParams("pStartDate", pStartDate, "pEndDate", pEndDate, "pFromOffenderId", pFromOffenderId),
					new RowMapperResultSetExtractor<InternetAddresses>(
							new BeanPropertyRowMapper<InternetAddresses>(InternetAddresses.class)));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method transIntAddr", e);
		}
		return list;
	}

	@Override
	public Integer deleteIntAddresses(Long pInternetAddressId,String modifyUserId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_DELETE_INTERNET_ADDRESSES_BY_INT_ADD_ID");
		int result = 0;
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "INTERNET_ADDRESSES";
			String whereCondition = "INTERNET_ADDRESS_ID = :pInternetAddressId";
			inputMap.put("pInternetAddressId", pInternetAddressId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteIntAddresses " + e.getMessage());
		}
		try {
			result = namedParameterJdbcTemplate.update(sql, createParams("pInternetAddressId", pInternetAddressId));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteIntAddresses", e);
		}
		return result;
	}

	@Override
	public Integer updateIntAddresses(Long pToOffenderId, Long pInternetAddressId, String user) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_UPDATE_INTERNET_ADDRESSES");
		int result = 0;
		try {
			result = namedParameterJdbcTemplate.update(sql, createParams("pToOffenderId", pToOffenderId, "pInternetAddressId", pInternetAddressId, "modifyUserId", user));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method updateIntAddresses", e);
		}
		return result;
	}

	@Override
	public OffenderCommunityFiles getOffCommFileVals(Long pToRootOffenderId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_GET_OFF_COMM_FILE_VALS");
		OffenderCommunityFiles offCommFiles = new OffenderCommunityFiles();
		try {
			offCommFiles = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("pToRootOffenderId", pToRootOffenderId),
					new BeanPropertyRowMapper<OffenderCommunityFiles>(OffenderCommunityFiles.class));
		}catch(Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getOffCommFileVals", e);
		}
		return offCommFiles;
	}

	@Override
	public Integer updateOffFileDelv(Long pToRootOffenderId, Integer offenderFileSeq, Long pFromRootOffenderId,
			Date lvStartDate, Date lvEndDate, String user) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_UPDATE_OFFENDER_FILE_DELIVERIES");
		int result = 0;
		try {
			result = namedParameterJdbcTemplate.update(sql,
					createParams("pToRootOffenderId", pToRootOffenderId, "offenderFileSeq", offenderFileSeq,
							"pFromRootOffenderId", pFromRootOffenderId, "lvStartDate", lvStartDate, "lvEndDate",lvEndDate, "modifyUserId", user));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method updateOffFileDelv", e);
		}
		return result;
	}

	@Override
	public Integer updateOffFileTrans(Long pToRootOffenderId, Integer offenderFileSeq, Long pFromRootOffenderId,
			Date lvStartDate, Date lvEndDate, String user) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_UPDATE_OFFENDER_FILE_TRANSACTIONS");
		int result = 0;
		try {
			result = namedParameterJdbcTemplate.update(sql,
					createParams("pToRootOffenderId", pToRootOffenderId, "offenderFileSeq", offenderFileSeq,
							"pFromRootOffenderId", pFromRootOffenderId, "lvStartDate", lvStartDate, "lvEndDate",lvEndDate, "modifyUserId", user));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method updateOffFileTrans", e);
		}
		return result;
	}

	@Override
	public Integer updateOffCommFiles(Long pToRootOffenderId, Integer offenderFileSeq, Long offenderFileNum,
			Long pFromRootOffenderId, Date lvStartDate, Date lvEndDate, String user) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_UPDATE_OFFENDER_COMMUNITY_FILES");
		int result = 0;
		try {
			result = namedParameterJdbcTemplate.update(sql,
					createParams("pToRootOffenderId", pToRootOffenderId, "offenderFileSeq", offenderFileSeq, "offenderFileNum", offenderFileNum,
							"pFromRootOffenderId", pFromRootOffenderId, "lvStartDate", lvStartDate, "lvEndDate",lvEndDate, "modifyUserId", user));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method updateOffCommFiles", e);
		}
		return result;
	}

	@Override
	public Offenders getOffCount(Long pToRootOffId, Long pFromOffId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_TRANSFER_BOOKING_TABLE_GET_OFF_COUNT");
		Offenders offenders = new Offenders();
		try {
			offenders = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("pToRootOffId", pToRootOffId, "pFromOffId", pFromOffId),
					new BeanPropertyRowMapper<Offenders>(Offenders.class));
		}catch(Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getOffCount", e);
		}
		return offenders;
	}

	@Override
	public Long getNxtVal() {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_TRANSFER_BOOKING_TABLE_GET_NXT_VAL");
		Long result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getNxtVal", e);
		}
		return result;
	}

	
	@Override
	public Integer insertTrOffVals(List<Offenders> insertList) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_TRANSFER_BOOKING_TABLE_INSERT_TR_OFF_VALS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();	
		for (final Offenders sentenceTerms : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer updateOffenderBookings(Long lvOffenderId, Long pToRootOffId, Long pFromOffBookId, String user) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_TRANSFER_BOOKING_TABLE_UPDATE_OFFENDER_BOOKINGS");
		int result = 0;
		try {
			result = namedParameterJdbcTemplate.update(sql,
					createParams("lvOffenderId", lvOffenderId, "pToRootOffId", pToRootOffId, "pFromOffBookId", pFromOffBookId,"modifyUserId", user));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method updateOffenderBookings", e);
		}
		return result;
	}

	@Override
	public Integer getNewSeqCurs(Long pToOffId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_TRANSFER_OFF_IDENTIFIERS_GET_NEW_SEQ_CURS");
		Integer result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, createParams("pToOffId", pToOffId), Integer.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getNewSeqCurs", e);
		}
		return result;
	}

	@Override
	public List<OffenderIdentifier> getOffIdSeqCur(Long pFromOffId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_TRANSFER_OFF_IDENTIFIERS_GET_OFF_ID_SEQ_CUR");
		List<OffenderIdentifier> list = new ArrayList<OffenderIdentifier>();
		try {
			list = namedParameterJdbcTemplate.query(sql, createParams("pFromOffId", pFromOffId),
					new RowMapperResultSetExtractor<OffenderIdentifier>(
							new BeanPropertyRowMapper<OffenderIdentifier>(OffenderIdentifier.class)));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getOffIdSeqCur", e);
		}
		return list;
	}

	@Override
	public Integer deleteOffIdentf(Long pFromOffId, Long pToRootOffId, Date lvStartDate, Date lvEndDate) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_TRANSFER_OFF_IDENTIFIERS_DELETE_OFF_IDENTF");
		int result = 0;
		try {
			result = namedParameterJdbcTemplate.update(sql, createParams("pFromOffId", pFromOffId, "pToRootOffId",
					pToRootOffId, "lvStartDate", lvStartDate, "lvEndDate", lvEndDate));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteOffIdentf", e);
		}
		return result;
	}

	@Override
	public Integer updateOffIdentfiers(Integer lCount1, Long pFromOffId, Integer lvOffenderIdSeqP, String user) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_TRANSFER_OFF_IDENTIFIERS_UPDATE_OFFENDER_IDENTIFIERS");
		int result = 0;
		try {
			result = namedParameterJdbcTemplate.update(sql,
					createParams("lCount1", lCount1, "pFromOffId", pFromOffId, "lvOffenderIdSeqP", lvOffenderIdSeqP, "modifyUserId", user));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method updateOffIdentfiers", e);
		}
		return result;
	}

	@Override
	public Integer updateFinalOffIdentfiers(Long lvToOffenderId, Long pToRootOffenderId, Long pFromOffenderId,
			Integer lvOffenderIdSeqP, Date lvStartDate, Date lvEndDate, String user) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_TRANSFER_OFF_IDENTIFIERS_UPDATE_FINAL_OFFENDER_IDENTIFIERS");
		int result = 0;
		try {
			result = namedParameterJdbcTemplate.update(sql,
					createParams("lvToOffenderId", lvToOffenderId, "pToRootOffenderId", pToRootOffenderId, "pFromOffenderId", pFromOffenderId,
							"lvOffenderIdSeqP", lvOffenderIdSeqP,"lvStartDate", lvStartDate, "lvEndDate", lvEndDate, "modifyUserId", user));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method updateFinalOffIdentfiers", e);
		}
		return result;
	}

	
	@Override
	public Phones getPhoneData(Long pPhoneId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_GET_OLD_PHONE_DATA");
		Phones phnData = new Phones();
		try {
			phnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("pPhoneId", pPhoneId), (Phones.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getPhoneData", e);
		}
		return phnData;
	}

	@Override
	public Phones getPhnDataByAddId(Long addressId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_GET_PHONES_BY_ADDR_ID");
		Phones phnData = new Phones();
		try {
			phnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("addressId", addressId), (Phones.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getPhnDataByOwId", e);
		}
		return phnData;
	}

	@Override
	public InternetAddresses getIntAddrDataByAddrId(Long addressId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_GET_INTERNET_ADDRESSES_BY_ADDR_ID");
		InternetAddresses IntAddrData = new InternetAddresses();
		try {
			IntAddrData = namedParameterJdbcTemplate.queryForObject(sql, createParams("addressId", addressId), (InternetAddresses.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getIntAddrDataByAddrId", e);
		}
		return IntAddrData;
	}

	@Override
	public InternetAddresses getIntAddrByIntAddrDet(Long pInternetAddressId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_GET_INTERNET_ADDRESSES_BY_INT_ADD_ID");
		InternetAddresses IntAddrData = new InternetAddresses();
		try {
			IntAddrData = namedParameterJdbcTemplate.queryForObject(sql, createParams("pInternetAddressId", pInternetAddressId), (InternetAddresses.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getIntAddrByIntAddrDet", e);
		}
		return IntAddrData;
	}

	@Override
	public Addresses getAddressesByAddrId(Long pAddressId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_GET_ADDRESSES_BY_ADDR_ID");
		Addresses addrData = new Addresses();
		try {
			addrData = namedParameterJdbcTemplate.queryForObject(sql, createParams("pAddressId", pAddressId), (Addresses.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getAddressesByAddrId", e);
		}
		return addrData;
	}

	@Override
	public List<OffenderCommunityFiles> getOffCommFileObj(Long pToRootOffenderId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_GET_OFF_COMM_FILE_OBJ");
		List<OffenderCommunityFiles> list = new ArrayList<OffenderCommunityFiles>();
		try {
			list = namedParameterJdbcTemplate.query(sql, createParams("pToRootOffenderId", pToRootOffenderId),
					new RowMapperResultSetExtractor<OffenderCommunityFiles>(
							new BeanPropertyRowMapper<OffenderCommunityFiles>(OffenderCommunityFiles.class)));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getOffCommFileObj", e);
		}
		return list;
	}

	@Override
	public List<Offenders> getOffenderdata(Long offenderId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_TRANSFER_BOOKING_TABLE_GET_TR_OFF_VALS");
		List<Offenders> list = new ArrayList<Offenders>();
		try {
			list = namedParameterJdbcTemplate.query(sql, createParams("offenderId", offenderId),
					new RowMapperResultSetExtractor<Offenders>(
							new BeanPropertyRowMapper<Offenders>(Offenders.class)));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getOffenderdata", e);
		}
		return list;
	}

	@Override
	public OffenderBookings getOffBooksData(Long pFromOffBookId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_TRANSFER_BOOKING_TABLE_GET_OFFENDER_BOOKINGS");
		OffenderBookings offBookData = new OffenderBookings();
		try {
			  offBookData = namedParameterJdbcTemplate.queryForObject(sql, createParams("pFromOffBookId", pFromOffBookId),new BeanPropertyRowMapper<OffenderBookings>(OffenderBookings.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getOffBooksData", e);
		}
		return offBookData;
	}
	

	@Override
	public List<OffenderNonAssociations> getOffNonAssData(Long nsOffenderId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_GET_OFFENDER_NON_ASSOCIATIONS_DATA");
		List<OffenderNonAssociations> offNonAssList = new ArrayList<OffenderNonAssociations>();
		try {
			offNonAssList = namedParameterJdbcTemplate.query(sql, createParams("nsOffenderId", nsOffenderId),
					new BeanPropertyRowMapper<OffenderNonAssociations>(OffenderNonAssociations.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getOffNonAssData", e);
		}
		return offNonAssList;
	}

	@Override
	public List<OffenderNaDetails> getOffNaData(Long nsOffenderId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_GET_OFFENDER_NA_DETAILS_DATA");
		List<OffenderNaDetails> offNaList = new ArrayList<OffenderNaDetails>();
		try {
			offNaList = namedParameterJdbcTemplate.query(sql, createParams("nsOffenderId", nsOffenderId),
					new BeanPropertyRowMapper<OffenderNaDetails>(OffenderNaDetails.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getOffNaData", e);
		}
		return offNaList;
	}

	@Override
	public Integer insertOffNonAssData(List<OffenderNonAssociations> insertList) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_INSERT_OFFENDER_NON_ASSOCIATIONS_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderNonAssociations sentenceTerms : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getOffNaData", e);
		}
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer insertOffNaData(List<OffenderNaDetails> insertList) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_INSERT_OFFENDER_NA_DETAILS_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderNaDetails sentenceTerms : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getOffNaData", e);
		}
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	

	@Override
	public List<Offenders> getOffendersData(Long offenderId) {
		final String sql = getQuery("TRANSFER_BOOKING_OMS_GET_OFFENDERS_DETAILS");
		List<Offenders> offList = new ArrayList<Offenders>();
		try {
			offList = namedParameterJdbcTemplate.query(sql, createParams("offenderId", offenderId),
					new BeanPropertyRowMapper<Offenders>(Offenders.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getOffendersData", e);
		}
		return offList;
	}	
}
