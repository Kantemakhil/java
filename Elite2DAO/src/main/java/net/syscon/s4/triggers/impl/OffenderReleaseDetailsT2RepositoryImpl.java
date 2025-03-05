package net.syscon.s4.triggers.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.schedules.bean.OffenderReleaseDetails;
import net.syscon.s4.triggers.OffenderAssocPEventNotifs;
import net.syscon.s4.triggers.OffenderReleaseDetailsT2Repository;

@Repository
public class OffenderReleaseDetailsT2RepositoryImpl extends RepositoryBase
		implements OffenderReleaseDetailsT2Repository {
	private static Logger logger = LogManager.getLogger(OffenderReleaseDetailsT2RepositoryImpl.class);
	final DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	private final Map<String, FieldMapper> offndrMaping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("VERIFIED_FLAG", new FieldMapper("verifiedFlag"))
			.put("APPROVED_RELEASE_DATE", new FieldMapper("approvedReleaseDate"))
			.put("DTO_MID_TERM_DATE", new FieldMapper("dtoMidTermDate"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("DTO_APPROVED_DATE", new FieldMapper("dtoApprovedDate"))
			.put("RELEASE_DATE", new FieldMapper("releaseDate"))
			.put("EVENT_ID", new FieldMapper("eventId"))
			.put("PROPOSED_MVMNT_SEQ", new FieldMapper("proposedMvmntSeq"))
			.put("AUTO_RELEASE_DATE", new FieldMapper("autoReleaseDate"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("MOVEMENT_REASON_CODE", new FieldMapper("movementReasonCode"))
			.put("EVENT_STATUS", new FieldMapper("eventStatus"))
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MOVEMENT_TYPE", new FieldMapper("movementType"))
			.put("FORM_IDENTIFIER", new FieldMapper("formIdentifier"))			
			.put("FORM_INFO_JSON",              new FieldMapper("formInfoJson"))
			.put("FORM_INFO_JSON_BLOB",          new FieldMapper("formInfoJsonBlob"))			
			.put("nbt_name",          new FieldMapper("nbtName"))
			.put("DATA_EXIST_FLAG",          new FieldMapper("dataExistFlag"))
			
			.build();
	
	@Override
	public String lCheckExistCur(final Long offenderBookId, final Date releaseDate) {
		final String sqlname = getQuery("OFFENDER_RELEASE_DETAILS_T2_L_CHECK_EXIST_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname, createParams("offenderBookId", offenderBookId,
					"releaseDate", sdf.format(releaseDate), "offenderBookId", offenderBookId), String.class);
		} catch (final Exception e) {
			logger.error("lCheckExistCur", e);
			return null;
		}

	}

	@Override
	public String lSexCheckExistCur(final Long offenderBookId) {
		final String sqlname = getQuery("OFFENDER_RELEASE_DETAILS_T2_L_SEX_CHECK_EXIST_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname, createParams("offenderBookId", offenderBookId),
					String.class);
		} catch (final Exception e) {
			logger.error("lSexCheckExistCur", e);
			return null;
		}

	}

	@Override
	public Long lTrgEventId() {
		final String sqlname = getQuery("OFFENDER_RELEASE_DETAILS_T2_L_TRG_EVENT_ID");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname, createParams(), Long.class);
		} catch (final Exception e) {
			logger.error("lTrgEventId", e);
			return null;
		}

	}

	@Override
	public Integer inserting(final OffenderAssocPEventNotifs offendAssPEvntNo) {
		final String sql = getQuery("OFFENDER_RELEASE_DETAILS_T2_INSERT");
		try {
			return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(offendAssPEvntNo));
		} catch (final Exception e) {
			logger.error("inserting", e);
			return null;
		}
	}

	@Override
	public Integer updating(final OffenderAssocPEventNotifs offendAssPEvntNo) {
		final String sql = getQuery("OFFENDER_RELEASE_DETAILS_T2_UPDATE");
		try {
			return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(offendAssPEvntNo));
		} catch (final Exception e) {
			logger.error("updating", e);
			return null;
		}
	}

	@Override
	public OffenderReleaseDetails getOffenderReleaseDetails(final Long offeBookId) {
		final String sql = getQuery("OFFENDER_RELEASE_DETAILS_T2_SELECT_FOR_OLD_RECORDS");
		List<OffenderReleaseDetails> returnList=new ArrayList<>();
		OffenderReleaseDetails returnObject=new OffenderReleaseDetails();
		try {
			final RowMapper<OffenderReleaseDetails> offRowMaper = Row2BeanRowMapper.makeMapping(sql,
					OffenderReleaseDetails.class, offndrMaping);
			returnList= (ArrayList<OffenderReleaseDetails>) namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", offeBookId), offRowMaper);
			if(!returnList.isEmpty() && returnList.get(0)!=null ) {
				returnObject =  returnList.get(0);
			}
			return returnObject;
		} catch (final Exception e) {
			logger.error("getOffenderReleaseDetails", e);
			return null;
		}

	}
}
