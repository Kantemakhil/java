package net.syscon.s4.iwp.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.courtcasesandorders.maintenance.impl.OimsreqsRepositoryImpl;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.legals.beans.OcdlegloSanctionHty;
import net.syscon.s4.inst.legals.beans.OffenderSentencesHty;
import net.syscon.s4.iwp.OcuadjcrRepository;

/**
 * Class OcuadjcrRepositoryImpl
 * 
 * @author Arkin Software Technologies
 * @version 1.0
 */
@Repository
public class OcuadjcrRepositoryImpl extends RepositoryBase implements OcuadjcrRepository {

	/**
	 * Creates new OcuadjcrRepositoryImpl class Object
	 */
	public OcuadjcrRepositoryImpl() {
	}

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimsreqsRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code")).build();
	private final Map<String, FieldMapper> offenderSentencesHtyMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_SENTENCE_HTY_ID", new FieldMapper("offenderSentenceHtyId"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("SENTENCE_SEQ", new FieldMapper("sentenceSeq"))
			.put("ADJUST_REASON", new FieldMapper("adjustReason"))
			.put("STAFF_ID", new FieldMapper("staffId"))
			.put("NO_OF_UNEXCUSED_ABSENCE", new FieldMapper("noOfUnexcusedAbsence"))
			.put("ADJUST_DATE", new FieldMapper("adjustDate"))
			.put("ADJUST_TIME", new FieldMapper("adjustTime"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.build();
	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderSentenceHty
	 *
	 * @return List<OffenderSentenceHty>
	 *
	 * @throws SQLException
	 */
	public List<OffenderSentencesHty> ctlBlkExecuteQuery(OffenderSentencesHty objSearchDao) {
		final String sql = getQuery("OCUADJCR_CTLBLK_FIND_OFFENDER_SENTENCE_HTY");
		final RowMapper<OffenderSentencesHty> OffenderSentenceHtyRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSentencesHty.class, offenderSentencesHtyMapping);
		final ArrayList<OffenderSentencesHty> returnList = (ArrayList<OffenderSentencesHty>) namedParameterJdbcTemplate
				.query(sql, createParams("offender_book_id",objSearchDao.getOffenderBookId()), OffenderSentenceHtyRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * @throws SQLException
	 *
	 */
	public int PRE_INSERT() {
		return 0;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderSentenceHty
	 *            List<OffenderSentenceHty>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	@Override
	public Integer ctlBlkInsertOffenderSentenceHty(final OffenderSentencesHty lstOffenderSentenceHty) {
		int insertCount = 0;
		String sql = getQuery("OCUADJCR_CTLBLK_INSERT_OFFENDER_SENTENCE_HTY");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(lstOffenderSentenceHty);
		insertCount = namedParameterJdbcTemplate.update(sql, parameters);
		if (insertCount > 0) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgReasonRecordGroup() {
		final String sql = getQuery("OCUADJCR_FIND_RGREASON");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
 
	@Override
	public String getStaffName(BigDecimal staffId) {

		final String sql = getQuery("OCUADJCR_STAFF_NAME");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("STAFF_ID", staffId),
					String.class);
		} catch (Exception e) {
			returnData = null;
			logger.error(e);

		}
		return returnData;
	} 
	
	@Override
	public BigDecimal getStaffId() {
		final String sql = getQuery("OCUADJCR_STAFF_ID");
		BigDecimal returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams(),BigDecimal.class);
		} catch (Exception e) {
			returnData = null;
			logger.error(e);

		}
		return returnData;
	} 
	
	@Override
	public BigDecimal preInsert() {
		final String sql = getQuery("OCUADJCR_PREINSERT");
		BigDecimal returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams(),BigDecimal.class);
		} catch (Exception e) {
			returnData = null;
			logger.error(e);

		}
		return returnData;
	} 
	
	@Override
	public Integer postInsert(long counter,long offendreBookId,long sentenceSeq) {
		final String sql = getQuery("OCUADJCR_POSTINSERT");
		Integer returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.update(sql, createParams("p_counter",counter,"p_offender_book_id",offendreBookId,"p_sentence_seq",sentenceSeq));
		} catch (Exception e) {
			returnData = null;
			logger.error(e);

		}
		return returnData;
	} 
	
	@Override
	public List<OcdlegloSanctionHty> ocdlegloSenHtyExecuteQuery(OffenderSentencesHty searchBean) {
		final String sql = getQuery("OCDLEGLO_SANCTION_HTY_QUERY");
		final RowMapper<OcdlegloSanctionHty> OffenderSentenceHtyRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OcdlegloSanctionHty.class, offenderSentencesHtyMapping);
		final List<OcdlegloSanctionHty> returnList = namedParameterJdbcTemplate
				.query(sql, createParams("offender_book_id",searchBean.getOffenderBookId()), OffenderSentenceHtyRowMapper);
		return returnList;
	}
	
	@Override
	public BigDecimal getSanctionHtyId() {
		final String sql = getQuery("OCDLEGLO_SANCTION_PRE_INSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), BigDecimal.class);
	}
	
	@Override
	public Integer ocdlegloSentCommit(OcdlegloSanctionHty commitBean) {
		final String sql = getQuery("OCDLEGLO_SANCTION_HISTORY");
		Integer returnVal = 0;
		try {
		final Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("offSanctionSentHtyId", commitBean.getOffSanctionSentHtyId());
		paramMap.put("offenderBookId", commitBean.getOffenderBookId());
		paramMap.put("sentenceSeq", commitBean.getSentenceSeq());
		paramMap.put("orderType", commitBean.getOrderType());
		paramMap.put("adjustReason", commitBean.getAdjustReason());
		paramMap.put("staffId", commitBean.getStaffId());
		paramMap.put("adjustDate", commitBean.getAdjustDate());
		paramMap.put("adjustTime", commitBean.getAdjustTime());
		paramMap.put("newCounter", commitBean.getNewCounter());
		paramMap.put("createUserId", commitBean.getCreateUserId());
		paramMap.put("sealFlag", commitBean.getSealFlag());
		paramMap.put("commentText", commitBean.getCommentText());
		returnVal = namedParameterJdbcTemplate.update(sql, paramMap);
		}catch (Exception e) {
			logger.error("Exception in "+this.getClass().getName() + " ocdlegloSentCommit",e);
		}
		return returnVal;
	}
	
	@Override
	public BigDecimal getStaffId(String userId) {
		final String sql = getQuery("OCUADJCR_STAFF_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("user_id",userId), BigDecimal.class);
	}

}
