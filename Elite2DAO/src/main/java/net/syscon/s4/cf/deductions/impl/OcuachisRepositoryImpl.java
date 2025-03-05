package net.syscon.s4.cf.deductions.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cf.deductions.OcuachisRepository;
import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;

@Repository
public class OcuachisRepositoryImpl extends RepositoryBase implements OcuachisRepository{
	private static Logger logger = LogManager.getLogger(OcuachisRepositoryImpl.class);
	private final Map<String, FieldMapper> accountHistorymapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASELOAD_DED_BENEFICIARY_ID", new FieldMapper("caseloadDedBeneficiaryId"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("DEDUCTION_TYPE", new FieldMapper("deductionType"))
			.put("PERSON_ID", new FieldMapper("personId"))
			.put("CORPORATE_ID", new FieldMapper("corporateId"))
			.put("PRIORITY", new FieldMapper("priority"))
			.put("AMOUNT", new FieldMapper("amount"))
			.put("PERCENT", new FieldMapper("percent"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MODIFY_DATE", new FieldMapper("modifyDate"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CO_CREDIT_WHEN_INDIGENT_FLAG", new FieldMapper("coCreditWhenIndigentFlag"))
			.put("LONGEST_SUPV_EXP_DATE", new FieldMapper("longestSupvExpDate"))
			.put("SERVICE_DATE", new FieldMapper("serviceDate"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("OFFENDER_FEE_ID", new FieldMapper("offenderFeeId"))
			.put("DAY_OF_MONTH", new FieldMapper("dayOfMonth"))
			.put("EFFECTIVE_DATE", new FieldMapper("effectiveDate"))
			.put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("FEE_ACT_STATUS", new FieldMapper("feeActStatus"))
			.put("FEE_CODE", new FieldMapper("feeCode"))
			.put("INFO_NUMBER", new FieldMapper("infoNumber"))
			.put("ODP", new FieldMapper("odp"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("START_DATE", new FieldMapper("startDate"))
			.put("STATUS_EFFECTIVE_DATE", new FieldMapper("statusEffectiveDate"))
			.put("OFF_FEE_DED_BENEFICIARY_ID", new FieldMapper("offFeeDedBeneficiaryId"))
			.put("non_billable_status", new FieldMapper("nonBillableStatus"))
			.put("RECORD_DATETIME", new FieldMapper("recordDatetime"))
			
			
			.build();
	@Override
	public List<FeeAccountProfiles> accountHistoryQuery(FeeAccountProfiles searchObject) {
		final String sql = getQuery("OCUACHIS_FIND_HISTORY_EXECUTEQUERY");
		final RowMapper<FeeAccountProfiles> offencesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				FeeAccountProfiles.class, accountHistorymapping);
		final ArrayList<FeeAccountProfiles> returnList = (ArrayList<FeeAccountProfiles>) namedParameterJdbcTemplate.query(sql, createParams("offenderFeeId",searchObject.getOffenderFeeId()), offencesRowMapper);
		return returnList;
	}
	
	
	@Override
	public String getDescription(FeeAccountProfiles searchObject) {
		final String sql = getQuery("OCUACHIS_FEE_CODE_DESCRIPTION_QUERY");
		String returnVal = "";
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("feeCode", searchObject.getFeeCode()),
					String.class);
		} catch (Exception e) {
			returnVal = "";
		}

		return returnVal;
	}


	@Override
	public String getUserName(FeeAccountProfiles feeAccountProfiles) {
		final String sql = getQuery("OCUACHIS_GET_USER_ID");
		String userName=null;
		try {			
			 userName= namedParameterJdbcTemplate.queryForObject(sql, createParams("userid",feeAccountProfiles.getCreateUserId()),
					String.class);
		}  catch (Exception e) {
			return userName;
		}
		return userName;
	}

	

}
