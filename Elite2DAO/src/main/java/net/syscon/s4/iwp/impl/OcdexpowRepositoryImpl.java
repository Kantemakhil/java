package net.syscon.s4.iwp.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.ExtOwnershipTransfer;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.VOffenderAssigned;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;

import net.syscon.s4.iwp.OcdexpowRepository;

/**
 * Class OcdexpowRepositoryImpl
 *
 * @version 1.0
 */
@Repository
public class OcdexpowRepositoryImpl extends RepositoryBase implements OcdexpowRepository {
	private static Logger logger = LogManager.getLogger(OcdexpowRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("LAST_NAME", new FieldMapper("lastName"))
			.put("FIRST_NAME", new FieldMapper("firstName")).put("DESCRIPTION", new FieldMapper("description"))
			.put("STAFF_ID", new FieldMapper("staffId")).put("CODE", new FieldMapper("code")).build();
	private final Map<String, FieldMapper> csdBenMapping = new ImmutableMap.Builder<String, FieldMapper>()
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
			.put("LONGEST_SUPV_EXPIRY_DATE", new FieldMapper("longestSupvExpiryDate"))
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
			.build();
private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_VALUE_2", 				new FieldMapper(" profileValue2 "))
			.build();
	private final Map<String, FieldMapper> feeAccMapping = new ImmutableMap.Builder<String, FieldMapper>().build();

	
private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 					new FieldMapper("description"))
			.put("AGY_LOC_ID_FROM", 				new FieldMapper("agyLocIdFrom"))
			.put("AGY_LOC_ID_TO", 					new FieldMapper("agyLocIdTo"))
			.put("CASELOAD_ID", 					new FieldMapper("caseloadId"))
			.build();	/**
	 * Creates new OcdexpowRepositoryImpl class Object
	 */
	public OcdexpowRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            ExtOwnershipTransfer
	 *
	 * @return List<ExtOwnershipTransfer>
	 *
	 */
	public List<ExtOwnershipTransfer> extOtExecuteQuery(ExtOwnershipTransfer objSearchDao) {
		final String sql = getQuery("OCDEXPOW_EXTOT_FIND_EXT_OWNERSHIP_TRANSFER");
		final RowMapper<ExtOwnershipTransfer> ExtOwnershipTransferRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ExtOwnershipTransfer.class, mMapping);
		final ArrayList<ExtOwnershipTransfer> returnList = (ArrayList<ExtOwnershipTransfer>) namedParameterJdbcTemplate
				.query(sql, createParams(), ExtOwnershipTransferRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstExtOwnershipTransfer
	 *            List<ExtOwnershipTransfer>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
public Integer extOtInsertextOt(final List<ExtOwnershipTransfer> lstExtOwnershipTransfer) {
	int insertCount = 0;
	String sql = getQuery("OCDEXPOW_EXTOT_INSERT_EXT_OWNERSHIP_TRANSFER");
	int[] returnArray = new int[] {};
	List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	for (int i = 0; i < returnArray.length; i++) {
		insertCount = insertCount++;
	}
	if (lstExtOwnershipTransfer.size() == returnArray.length) {
		return 1;
	} else {
		return 0;
	}

}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VOffenderAssigned
	 *
	 * @return List<VOffenderAssigned>
	 *
	 */
	public List<VOffenderAssigned> vOffenderAssignedExecuteQuery(VOffenderAssigned objSearchDao) {
		final String sql = getQuery("OCDEXPOW_VOFFENDERASSIGNED_FIND_V_OFFENDER_ASSIGNED");
		final RowMapper<VOffenderAssigned> VOffenderAssignedRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderAssigned.class, mMapping);
		final ArrayList<VOffenderAssigned> returnList = (ArrayList<VOffenderAssigned>) namedParameterJdbcTemplate
				.query(sql, createParams("agy_loc_id_from", objSearchDao.getAgyLocId(), "ass_staff_id",
						objSearchDao.getSacStaffId()), VOffenderAssignedRowMapper);

		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVOffenderAssigned
	 *            List<VOffenderAssigned>
	 * @return Integer
	 */
	public Integer vOffenderAssignedUpdateVOffenderAssigned(final List<VOffenderAssigned> lstVOffenderAssigned) {
		String sql = getQuery("OCDEXPOW_VOFFENDERASSIGNED_UPDATE_V_OFFENDER_ASSIGNED");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (VOffenderAssigned vOffenderAssigned : lstVOffenderAssigned) {
			parameters.add(new BeanPropertySqlParameterSource(vOffenderAssigned));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVOffenderAssigned.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 *
	 * @param agylocidfrom
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> cgfkExtOtAgyLocIdToRecordGroup(final String agylocidfrom) {
		List<AgencyLocations> courtList = new ArrayList<AgencyLocations>();
		final String sql = getQuery("OCDEXPOW_FIND_CGFKEXTOTAGYLOCIDTO");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);

		try {
			courtList = namedParameterJdbcTemplate.query(sql, createParams("AGYLOCIDFROM", agylocidfrom), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
		}
		return courtList;
	}

	/**
	 * Used to capture results from select query
	 *
	 * @param agylocidfrom
	 * @return List<M>
	 */
	public List<StaffMembers> rgStaffMembersRecordGroup(@RequestParam final String agylocidfrom) {
		final String sql = getQuery("OCDEXPOW_FIND_RGSTAFFMEMBERS");
		final RowMapper<StaffMembers> mRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("AGYLOCIDFROM", agylocidfrom), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * this method is used to fetch the lov data
	 *
	 * @return List<AgencyLocations>
	 * @param caseloadid
	 */
	public List<AgencyLocations> cgfkExtOtAgyLocIdFromRecordGroup(final String caseloadid) {
		final String sql = getQuery("OCDEXPOW_FIND_CGFKEXTOTAGYLOCIDFROM");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("CASELOADID", caseloadid), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * this method is used to fetch the lov data
	 *
	 * @return <AgencyLocations>
	 * @param agylocidfrom
	 *
	 */
	public List<AgencyLocations> cgfkchkExtOtExtOtAgyLoc(String agylocidfrom) {
		final String sql = getQuery("OCDEXPOW_CGFKCHK_EXT_OT_EXT_OT_AGY_LOC");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		final ArrayList<AgencyLocations> returnList = (ArrayList<AgencyLocations>) namedParameterJdbcTemplate.query(sql,
				createParams("AGYLOCIDTO", agylocidfrom), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is used to get max value
	 *
	 * @param offenderBookId
	 * @return Long
	 *
	 */
	public Long getExtTransferId(final Long offenderBookId) {
		final String sql = getQuery("OCDEXPOW_GET_EXT_TRANSFER_ID");
		Long returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERBOOKID", offenderBookId),
					Long.class);
		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * getProfileValue
	 *
	 * @return List<SystemProfiles>
	 * @param params
	 *
	 */
	public List<SystemProfiles> getProfileValue(SystemProfiles paramBean) {
		final String sql = getQuery("OCDEXPOW_GET_PROFILE_VALUE_2");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	@Override
	public List<FeeAccountProfiles> getFeeActsDet(VOffenderAssigned obj) {
		final String sql = getQuery("OCDEXPOW_FIND_OFFDED_EXECUTEQUERY");
		final RowMapper<FeeAccountProfiles> OffencesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				FeeAccountProfiles.class, csdBenMapping);
		final ArrayList<FeeAccountProfiles> returnList = (ArrayList<FeeAccountProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams("offenderBookId", obj.getOffenderBookId()), OffencesRowMapper);
		return returnList;
	}


	public List<FeeAccountProfiles> getSupData(final ExtOwnershipTransfer bean) {
		final String sql = getQuery("OCDEXPOW_GET_FEE_ACCOUNT_SUP_DATA");
		final RowMapper<FeeAccountProfiles> feeAccRowMapper = Row2BeanRowMapper.makeMapping(sql,
				FeeAccountProfiles.class, feeAccMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("OFFENDER_BOOK_ID", bean.getOffenderBookId()),
				feeAccRowMapper);
	}

	public Date getSupervisionStartdate(final ExtOwnershipTransfer bean) {
		final String sql = getQuery("OCDEXPOW_GET_SUPERVISION_START_DATE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDER_BOOK_ID", bean.getOffenderBookId()), Date.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public Integer updateFeeAccounts(final List<FeeAccountProfiles> updateList) {
		final String sql = getQuery("OCDEXPOW_UPDATE_FEE_ACCOUNTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final FeeAccountProfiles bean : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(bean));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}
	
	
	@Override
	public Integer updateFeeAcntCaseLoad(List<FeeAccountProfiles> feeUpdatelist) {
		final String sql = getQuery("OCDEXPOW_UPDATE_FEE_ACNT_CASELOAD");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final FeeAccountProfiles bean : feeUpdatelist) {
			parameters.add(new BeanPropertySqlParameterSource(bean));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<String> getCaseLoadType(VOffenderAssigned vOffAssignObj) {				
		final String sql = getQuery("OCDEXPOW_GET_CASLOAD_TYPE");
		List<String> returnList=new ArrayList<String>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("agyLocId",vOffAssignObj.getAgyIocIdTo()),
					new RowMapper<String>() {
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getString(1);
				}
			});
		} catch(Exception e) {
			logger.error("Exception in OcdexpowRepositoryImpl class getCaseLoadType : ", e);
			return returnList;	 
		}		
		return returnList;	 

	}
}
