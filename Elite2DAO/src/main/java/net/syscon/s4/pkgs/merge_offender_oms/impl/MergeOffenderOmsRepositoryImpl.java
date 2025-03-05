package net.syscon.s4.pkgs.merge_offender_oms.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import net.syscon.s4.common.beans.OffenderIdentifier;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.legalorders.OffenderCommunityFiles;
import net.syscon.s4.legalorders.OffenderFileTransactions;
import net.syscon.s4.pkgs.merge_offender_oms.MergeOffenderOmsRepository;
import net.syscon.s4.pkgs.merge_process.impl.MergeProcessRepositoryImpl;


@Repository
public class MergeOffenderOmsRepositoryImpl extends RepositoryBase implements MergeOffenderOmsRepository {
	
	private static Logger logger = LogManager.getLogger(MergeProcessRepositoryImpl.class.getName());

	@Override
	public List<OffenderNonAssociations> lockOffNonAssociations(Long offenderId) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_LOCK_OFF_NON_ASSOCIATIONS");
		List<OffenderNonAssociations> offenderNonAssVals = new ArrayList<OffenderNonAssociations>();
		try {
			offenderNonAssVals = namedParameterJdbcTemplate.query(sql, createParams("offenderId", offenderId),
					new RowMapperResultSetExtractor<OffenderNonAssociations>(
							new BeanPropertyRowMapper<OffenderNonAssociations>(OffenderNonAssociations.class)));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method lockOffNonAssociations", e);
		}
		return offenderNonAssVals;
	}

	@Override
	public List<OffenderNaDetails> lockOffenderNaDetails(Long offenderId) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_LOCK_OFFENDER_NA_DETAILS");
		List<OffenderNaDetails> offenderNaDetailsVals = new ArrayList<OffenderNaDetails>();
		try {
			offenderNaDetailsVals = namedParameterJdbcTemplate.query(sql, createParams("offenderId", offenderId),
					new RowMapperResultSetExtractor<OffenderNaDetails>(
							new BeanPropertyRowMapper<OffenderNaDetails>(OffenderNaDetails.class)));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method lockOffenderNaDetails", e);
		}
		return offenderNaDetailsVals;
	}

	@Override
	public List<OffenderNaDetails> lockOffNaDetailsNs(Long rootOffenderId) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_LOCK_OFF_NA_DETAILS_NS");
		List<OffenderNaDetails> OffenderNaDetails = new ArrayList<OffenderNaDetails>();
		try {
			OffenderNaDetails = namedParameterJdbcTemplate.query(sql, createParams("rootOffenderId", rootOffenderId),
					new RowMapperResultSetExtractor<OffenderNaDetails>(
							new BeanPropertyRowMapper<OffenderNaDetails>(OffenderNaDetails.class)));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method lockOffNaDetailsNs", e);
		}
		return OffenderNaDetails;
	}

	@Override
	public List<OffenderNonAssociations> lockOffNonAssociationsNs(Long rootOffenderId) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_LOCK_OFF_NON_ASSOCIATIONS_NS");
		List<OffenderNonAssociations> offenderNonAssVals = new ArrayList<OffenderNonAssociations>();
		try {
			offenderNonAssVals = namedParameterJdbcTemplate.query(sql, createParams("rootOffenderId", rootOffenderId),
					new RowMapperResultSetExtractor<OffenderNonAssociations>(
							new BeanPropertyRowMapper<OffenderNonAssociations>(OffenderNonAssociations.class)));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method lockOffNonAssociationsNs", e);
		}
		return offenderNonAssVals;
	}

	@Override
	public List<OffenderFileTransactions> lockOffFileTransactions(Long rootOffenderId) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_LOCK_OFF_FILE_TRANSACTIONS");
		List<OffenderFileTransactions> OffenderFileTransactionsVals = new ArrayList<OffenderFileTransactions>();
		try {
			OffenderFileTransactionsVals = namedParameterJdbcTemplate.query(sql, createParams("rootOffenderId", rootOffenderId),
					new RowMapperResultSetExtractor<OffenderFileTransactions>(
							new BeanPropertyRowMapper<OffenderFileTransactions>(OffenderFileTransactions.class)));

		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method lockOffFileTransactions", e);
		}
		return OffenderFileTransactionsVals;
	}

	@Override
	public List<OffenderNonAssociations> lockOffFileDeliveries(Long rootOffenderId) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_LOCK_OFF_FILE_DELIVERIES");
		List<OffenderNonAssociations> offenderNonAssVals = new ArrayList<OffenderNonAssociations>();
		try {
			offenderNonAssVals = namedParameterJdbcTemplate.query(sql, createParams("rootOffenderId", rootOffenderId),
					new RowMapperResultSetExtractor<OffenderNonAssociations>(
							new BeanPropertyRowMapper<OffenderNonAssociations>(OffenderNonAssociations.class)));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method lockOffNonAssociations", e);
		}
		return offenderNonAssVals;
	}

	@Override
	public List<Offenders> lockOffenders(Long rootOffenderId) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_LOCK_OFFENDERS");
		List<Offenders> lockOffendersVals = new ArrayList<Offenders>();
		try {
			lockOffendersVals = namedParameterJdbcTemplate.query(sql, createParams("rootOffenderId", rootOffenderId),
					new RowMapperResultSetExtractor<Offenders>(new BeanPropertyRowMapper<Offenders>(Offenders.class)));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method lockOffenders", e);
		}
		return lockOffendersVals;
	}

	@Override
	public List<OffenderNonAssociations> mergeNonAssoc1(Long rootOffenderIdFrom) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_MERGE_NON_ASSOC_1");
		List<OffenderNonAssociations> offenderNonAssVals = new ArrayList<OffenderNonAssociations>();
		try {
			offenderNonAssVals = namedParameterJdbcTemplate.query(sql,
					createParams("rootOffenderIdFrom", rootOffenderIdFrom),
					new RowMapperResultSetExtractor<OffenderNonAssociations>(
							new BeanPropertyRowMapper<OffenderNonAssociations>(OffenderNonAssociations.class)));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method mergeNonAssoc1", e);
		}
		return offenderNonAssVals;
	}

	@Override
	public List<OffenderNonAssociations> mergeNonAssoc2(Long rootOffenderIdFrom) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_MERGE_NON_ASSOC_2");
		List<OffenderNonAssociations> offenderNonAssVals = new ArrayList<OffenderNonAssociations>();
		try {

			offenderNonAssVals = namedParameterJdbcTemplate.query(sql,
					createParams("rootOffenderIdFrom", rootOffenderIdFrom),
					new RowMapperResultSetExtractor<OffenderNonAssociations>(
							new BeanPropertyRowMapper<OffenderNonAssociations>(OffenderNonAssociations.class)));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method mergeNonAssoc1", e);
		}
		return offenderNonAssVals;
	}

	@Override
	public Long getLatestBooking(Long fromRootOffenderId, Long toRootOffenderId) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_GET_LATEST_BOOKING");
		Long processId = null;
		try {
			processId = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("fromRootOffenderId", fromRootOffenderId, "toRootOffenderId", toRootOffenderId), Long.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getLatestBooking", e);
		}
		return processId;
	}

	@Override
	public Integer updateOffenderNonAssociations(Long pRootOffenderIdTo, Long offenderId, Long nsOffenderId, String user) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_UPDATE_OFFENDER_NON_ASSOCIATIONS");
		int result = 0;
		try {
			result = namedParameterJdbcTemplate.update(sql, createParams("pRootOffenderIdTo", pRootOffenderIdTo,
					"offenderId", offenderId, "nsOffenderId", nsOffenderId, "modifyUserId", user));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method updateOffenderNonAssociations", e);
		}
		return result;
	}

	@Override
	public Integer updateOffenderNaDetails(Long pRootOffenderIdTo, Long offenderId, Long nsOffenderId, String user) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_UPDATE_OFFENDER_NA_DETAILS");
		int result = 0;
		try {
			result = namedParameterJdbcTemplate.update(sql, createParams("pRootOffenderIdTo", pRootOffenderIdTo,
					"offenderId", offenderId, "nsOffenderId", nsOffenderId, "modifyUserId", user));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method updateOffenderNaDetails", e);
		}
		return result;
	}

	@Override
	public Integer deleteOffenderNaDetails(Long offenderId, Long nsOffenderId,String modifyUserId) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_DELETE_OFFENDER_NA_DETAILS");
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
			result= namedParameterJdbcTemplate.update(sql, createParams("offenderId", offenderId , "nsOffenderId",nsOffenderId));
		return result;
	}

	@Override
	public Integer deleteOffenderNonAssociations(Long offenderId, Long nsOffenderId,String modifyUserId) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_DELETE_OFFENDER_NON_ASSOCIATIONS");
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
			result= namedParameterJdbcTemplate.update(sql, createParams("offenderId", offenderId , "nsOffenderId",nsOffenderId));
		return result;
	}

	@Override
	public Integer updateOffenderNonAssociations1(Long lvLatestBookingId, Long pRootOffenderIdTo, Long nsOffenderId) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_UPDATE_OFFENDER_NON_ASSOCIATIONS1");
		int result = 0;
		try {
			result = namedParameterJdbcTemplate.update(sql, createParams("lvLatestBookingId", lvLatestBookingId,
					"pRootOffenderIdTo", pRootOffenderIdTo, "nsOffenderId", nsOffenderId));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method updateOffenderNaDetails", e);
		}
		return result;
	}

	@Override
	public Integer updateOffenderNaDetails1(Long lvLatestBookingId, Long pRootOffenderIdTo, Long nsOffenderId) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_UPDATE_OFFENDER_NA_DETAILS1");
		int result = 0;
		try {
			result = namedParameterJdbcTemplate.update(sql, createParams("lvLatestBookingId", lvLatestBookingId,
					"pRootOffenderIdTo", pRootOffenderIdTo, "nsOffenderId", nsOffenderId));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method updateOffenderNaDetails", e);
		}
		return result;
	}
	
	
	

	@Override
	public Integer updateOffenderNonAssociations2(Long pRootOffenderIdTo, Long offenderId, Long nsOffenderId, String user) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_UPDATE_OFFENDER_NON_ASSOCIATIONS2");
		int result = 0;
		try {
			result= namedParameterJdbcTemplate.update(sql, createParams("pRootOffenderIdTo", pRootOffenderIdTo, "offenderId", offenderId , "nsOffenderId",nsOffenderId, "modifyUserId", user));
	} catch (Exception e) {
		logger.error("Exception occured in " + this.getClass().getName() + " in method updateOffenderNonAssociations2", e);
	}
			return result;
	}

	@Override
	public Integer updateOffenderNaDetails2(Long pRootOffenderIdTo, Long offenderId, Long nsOffenderId, String user) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_UPDATE_OFFENDER_NA_DETAILS2");
		int result = 0;
		try {
			result= namedParameterJdbcTemplate.update(sql, createParams("pRootOffenderIdTo", pRootOffenderIdTo, "offenderId", offenderId , "nsOffenderId",nsOffenderId, "modifyUserId", user));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method updateOffenderNaDetails2", e);
		}
			return result;
	}

	@Override
	public Integer deleteOffenderNaDetails2(Long offenderId, Long nsOffenderId,String modifyUserId) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_DELETE_OFFENDER_NA_DETAILS2");
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
			result= namedParameterJdbcTemplate.update(sql, createParams("offenderId", offenderId , "nsOffenderId",nsOffenderId));
		return result;
	}

	@Override
	public Integer deleteOffenderNonAssociations2(Long offenderId, Long nsOffenderId,String modifyUserId) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_DELETE_OFFENDER_NON_ASSOCIATIONS2");
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
			result= namedParameterJdbcTemplate.update(sql, createParams("offenderId", offenderId , "nsOffenderId",nsOffenderId));
		return result;
	}

	@Override
	public Integer updateOffenderNaDetails21(Long lvLatestBookingId, Long offenderId, Long pRootOffenderIdTo) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_UPDATE_OFFENDER_NA_DETAILS21");
		int result = 0;
		try {
			result = namedParameterJdbcTemplate.update(sql, createParams("lvLatestBookingId", lvLatestBookingId,
					"offenderId", offenderId, "pRootOffenderIdTo", pRootOffenderIdTo));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method updateOffenderNaDetails21",e);
		}
		return result;
	}

	@Override
	public Integer updateOffenderNonAssociations21(Long lvLatestBookingId, Long offenderId, Long pRootOffenderIdTo) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_UPDATE_OFFENDER_NON_ASSOCIATIONS21");
		int result = 0;
		try {
			result = namedParameterJdbcTemplate.update(sql, createParams("lvLatestBookingId", lvLatestBookingId,
					"offenderId", offenderId, "pRootOffenderIdTo", pRootOffenderIdTo));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method updateOffenderNonAssociations21",e);
		}
		return result;
	}
	

	@Override
	public BigDecimal getNextFileSeqCurser(Long lvRootOffIdTo) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_GET_NEXT_FILE_SEQ");
		BigDecimal returnList = BigDecimal.ZERO;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("lvRootOffIdTo",
				lvRootOffIdTo), BigDecimal.class);
		return returnList;
	}

	@Override
	public List<OffenderCommunityFiles> getOldOffFilesCurserData(Long lvRootOffIdFrom) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_OLD_OFFENDER_FILES_PAPER_CURSER_DATA");
		List<OffenderCommunityFiles> offendercommunityPaperFiles = new ArrayList<OffenderCommunityFiles>();
		try {
			offendercommunityPaperFiles = namedParameterJdbcTemplate.query(sql, createParams("lvRootOffIdFrom", lvRootOffIdFrom),
					new BeanPropertyRowMapper<OffenderCommunityFiles>(OffenderCommunityFiles.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getOldOffFilesCurserData", e);
		}
		return offendercommunityPaperFiles;
	}

	@Override
	public BigDecimal getNextFileNo(Long lvRootOffIdTo, String fileType, String fileSubType) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_GET_NEXT_FILE_NUMBER");
		BigDecimal returnList = BigDecimal.ZERO;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("lvRootOffIdTo",
				lvRootOffIdTo,"fileType",fileType,"fileSubType",fileSubType), BigDecimal.class);
		return returnList;
	}

	@Override
	public BigDecimal getNextFileNoCurser(Long lvRootOffIdTo) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_GET_NEXT_FILE_NUMBER_CURSER");
		BigDecimal returnList = BigDecimal.ZERO;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("lvRootOffIdTo",
				lvRootOffIdTo), BigDecimal.class);
		return returnList;
	}

	@Override
	public BigDecimal getNextVolumeSeqCurser(BigDecimal lvNxtOfn,Long lvRootOffIdTo) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_GET_NEXT_VOLUME_SEQ_CURSER");
		BigDecimal returnList = BigDecimal.ZERO;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("lvNxtOfn",
				lvNxtOfn,"lvRootOffIdTo",lvRootOffIdTo), BigDecimal.class);
		return returnList;
	}

	@Override
	public Integer insertOffenderCommunityFile(List<OffenderCommunityFiles> insertList) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_INSERT_OFFENDER_COMMUNITY_FILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderCommunityFiles sentenceTerms : insertList) {
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
	public Integer updateOffenderFileTransaction(Long lvRootOffIdTo, Long longValue, Long lvRootOffIdFrom,
			Long offenderFileSeq, String user) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_UPDATE_OFFENDER_FILE_TRANSACTION");
		int result = 0;
		try {
			result= namedParameterJdbcTemplate.update(sql, createParams("lvRootOffIdTo", lvRootOffIdTo, "longValue",longValue, "lvRootOffIdFrom", lvRootOffIdFrom,"offenderFileSeq", offenderFileSeq,"modifyUserId",user));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method updateOffenderFileTransaction", e);
		}
		return result;
	}

	@Override
	public Integer updateOffenderFileDeliveries(Long lvRootOffIdTo, long longValue, Long lvRootOffIdFrom,
			long offenderFileSeq, String user) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_UPDATE_OFFENDER_FILE_DELIVIRIES");
		int result = 0;
		try {
			result= namedParameterJdbcTemplate.update(sql, createParams("lvRootOffIdTo", lvRootOffIdTo, "longValue",longValue, "lvRootOffIdFrom", lvRootOffIdFrom,"offenderFileSeq", offenderFileSeq));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method updateOffenderFileDeliveries", e);
		}
		return result;
	}

	@Override
	public Integer deleteOffenderCommunityFiles(OffenderCommunityFiles offenderCommunityFile) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_DELETE_OFFENDER_COMMUNITY_FILES");
		int result = 0;
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "OFFENDER_COMMUNITY_FILES";
			String whereCondition = "OFFENDER_ID=:offenderId and OFFENDER_FILE_SEQ=:offFileSeq";
			inputMap.put("offenderId", offenderCommunityFile.getOffenderId());
			inputMap.put("offFileSeq", offenderCommunityFile.getOffenderFileSeq());
			inputMap.put("modifyUserId", offenderCommunityFile.getModifyUserId());
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteOffenderCommunityFiles " + e.getMessage());
		}
		try {
			result= namedParameterJdbcTemplate.update(sql, createParams("offenderId", offenderCommunityFile.getOffenderId() , "offFileSeq",offenderCommunityFile.getOffenderFileSeq()));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteOffenderCommunityFiles", e);
		}
		return result;
	}

	@Override
	public String getOffIdenTypeCur() {
		final String sql = getQuery("MERGE_OFFENDER_OMS_GET_OFF_IDEN_CURSER");
		String returnList = null;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		return returnList;
	}

	@Override
	public Long getlvOffIdSeq(Long offenderIdTo) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_GET_LV_OFF_ID_SEQ");
		Long returnList = null;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderIdTo",offenderIdTo), Long.class);
		return returnList;
	}

	@Override
	public String getCaseloadType(String user) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_GET_CASELOAD_TYPE_ON_USER");
		String returnList = null;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("userId",user), String.class);
		return returnList;
	}

	@Override
	public Integer insertOffenderIdentifiers(List<OffenderIdentifier> insertList) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_INSERT_OFFENDER_IDENTIFIERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderIdentifier sentenceTerms : insertList) {
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
	public Integer deleteOffenderIdentifier(String lvIdentType, Long lvRootOffIdTo,String modifyUserId) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_DELETE_OFFENDER_IDENTIFIERS");
		int result = 0;
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "OFFENDER_IDENTIFIERS";
			String whereCondition = "IDENTIFIER_TYPE = :lvIdentType AND ROOT_OFFENDER_ID = :lvRootOffIdTo";
			inputMap.put("lvIdentType", lvIdentType);
			inputMap.put("lvRootOffIdTo", lvRootOffIdTo);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteOffenderIdentifier " + e.getMessage());
		}
		try {
			result= namedParameterJdbcTemplate.update(sql, createParams("lvIdentType", lvIdentType , "lvRootOffIdTo",lvRootOffIdTo));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteOffenderIdentifier", e);
		}
		return result;
	}

	@Override
	public List<Addresses> getAddressesData(Long lvRootOffIdFrom) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_GET_OFFENDER_ADDRESS_DATA");
		List<Addresses> offendercommunityPaperFiles = new ArrayList<Addresses>();
		try {
			offendercommunityPaperFiles = namedParameterJdbcTemplate.query(sql, createParams("lvRootOffId", lvRootOffIdFrom),
					new BeanPropertyRowMapper<Addresses>(Addresses.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getAddressesData", e);
		}
		return offendercommunityPaperFiles;
	}

	@Override
	public Integer checkOffStatusCur(Long lvRootOffId) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_CHECK_OFF_STATUS_CURSER");
		Integer returnList = null;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("lvRootOffId",lvRootOffId), Integer.class);
		return returnList;
	}

	@Override
	public Integer checkOffPreFlagCur(Long lvRootOffId) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_CHECK_OFF_PRE_FLAG_CURSER");
		Integer returnList = null;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("lvRootOffId",lvRootOffId), Integer.class);
		return returnList;
	}

	@Override
	public Integer updateAdreessData(Long lvRootOffIdTo, String user) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_UPDATE_OFFENDER_ADDRESS_DATA");
		int result = 0;
		try {
			result= namedParameterJdbcTemplate.update(sql, createParams("lvRootOffIdTo", lvRootOffIdTo, "modifyUserId",user));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method updateAdreessData", e);
		}
		return result;
	}

	@Override
	public String getOffIdDisplayCur(Long lvRootOffIdTo) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_GET_OFFENDER_ID_DISPLAY");
		String lvOffIdDisplay = null;
		try {
			lvOffIdDisplay = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("lvRootOffIdTo", lvRootOffIdTo), String.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getOffIdDisplayCur", e);
		}
		return lvOffIdDisplay;

	}

	@Override
	public Integer updateOffenders(Long lvRootOffIdTo, String lvOffIdDisplay, Long lvRootOffIdFrom, String user) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_UPDATE_OFFENDERS");
		int result = 0;
		try {
			result= namedParameterJdbcTemplate.update(sql, createParams("lvRootOffIdTo", lvRootOffIdTo, "lvOffIdDisplay", lvOffIdDisplay , "lvRootOffIdFrom",lvRootOffIdFrom, "modifyUserId",user));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method updateOffenders", e);
		}
		return result;
	}
	
	@Override
	public List<OffenderIdentifier> getOldOffenderIdentifierDet(Long offenderIdTo) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_GET_OLD_OFFENDER_IDENTIFIER_DET");
		List<OffenderIdentifier> oldOffIdentifier = new ArrayList<OffenderIdentifier>();
		try {
			oldOffIdentifier = namedParameterJdbcTemplate.query(sql, createParams("offenderIdTo", offenderIdTo),
					new BeanPropertyRowMapper<OffenderIdentifier>(OffenderIdentifier.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method oldOffIdentifier", e);
		}
		return oldOffIdentifier;
	}
	
	
	@Override
	public Addresses getAddrData(Long offenderIdTo) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_GET_ADDRESS_DATA");
		Addresses addrData = new Addresses();
		try {
			addrData = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderIdTo", offenderIdTo), (Addresses.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getAddrData", e);
		}
		return addrData;
	}

	@Override
	public List<Offenders> getOffenders(Long offenderIdTo) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_GET_OFFENDERS_DATA");
		List<Offenders> offendersData = new ArrayList<Offenders>();
		try {
			offendersData = namedParameterJdbcTemplate.query(sql, createParams("offenderIdTo", offenderIdTo),
					new BeanPropertyRowMapper<Offenders>(Offenders.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getOffenders", e);
		}
		return offendersData;
		
	}

	@Override
	public List<OffenderNonAssociations> getOffNonAssData(Long nsOffenderId) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_GET_OFFENDER_NON_ASSOCIATIONS_DATA");
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
		final String sql = getQuery("MERGE_OFFENDER_OMS_GET_OFFENDER_NA_DETAILS_DATA");
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
		final String sql = getQuery("MERGE_OFFENDER_OMS_INSERT_OFFENDER_NON_ASSOCIATIONS_DATA");
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
		final String sql = getQuery("MERGE_OFFENDER_OMS_INSERT_OFFENDER_NA_DETAILS_DATA");
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
	public Integer updateFromOffenderIdentifiers(String identifierType, String modifyUserId, Long offenderId, String offenderIdSeq) {
		final String sql = getQuery("MERGE_OFFENDER_OMS_UPDATE_OFFENDER_IDENTIFIERS");
		int result = 0;
		try {
			result= namedParameterJdbcTemplate.update(sql, createParams("identifierType", identifierType, "modifyUserId", modifyUserId , "offenderId",offenderId, "offenderIdSeq",offenderIdSeq));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method updateFromOffenderIdentifiers", e);
		}
		return result;
	}
	
}
