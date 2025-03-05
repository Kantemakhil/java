package net.syscon.s4.inst.demographicsbiometrics.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderIdentifier;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.core.EliteDateRepository;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.demographicsbiometrics.OcdaliasRepository;

/**
 * Class OcdaliasRepositoryImpl
 */
@Repository
public class OcdaliasRepositoryImpl extends RepositoryBase implements OcdaliasRepository {
	
	@Autowired
	private EliteDateRepository dateRepository;
	
	/**
	 * Logger object used to print the log in the file
	 * 
	 */
	private static Logger logger = LogManager.getLogger(OcdaliasRepositoryImpl.class.getName());
	public final Map<String, FieldMapper> offenderIdentifiersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("OFFENDER_ID_SEQ", new FieldMapper("offenderIdSeq"))
			.put("IDENTIFIER_TYPE", new FieldMapper("identifierType"))
			.put("IDENTIFIER", new FieldMapper("identifier"))
			.put("ISSUED_AUTHORITY_TEXT", new FieldMapper("issuedAuthorityText"))
			.put("ISSUED_DATE", new FieldMapper("issuedDate"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("CASELOAD_TYPE", new FieldMapper("caseloadType"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("VERIFIED_FLAG", new FieldMapper("verifiedFlag"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("domain_access", new FieldMapper("domainAccess"))
			.build();
	public final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DOMAIN", new FieldMapper("domain"))
			.put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.build();
	public final Map<String, FieldMapper> aliasOffenderMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("OFFENDER_NAME_SEQ", new FieldMapper("offenderNameSeq"))
			.put("ID_SOURCE_CODE", new FieldMapper("idSourceCode"))
			.put("LAST_NAME", new FieldMapper("lastName"))
			.put("NAME_TYPE", new FieldMapper("nameType"))
			.put("FIRST_NAME", new FieldMapper("firstName"))
			.put("MIDDLE_NAME", new FieldMapper("middleName"))
			.put("BIRTH_DATE", new FieldMapper("birthDate"))
			.put("SEX_CODE", new FieldMapper("sexCode"))
			.put("SUFFIX", new FieldMapper("suffix"))
			.put("BIRTH_PLACE", new FieldMapper("birthPlace"))
			.put("LAST_NAME_SOUNDEX", new FieldMapper("lastNameSoundex"))
			.put("BIRTH_COUNTRY_CODE", new FieldMapper("birthCountryCode"))
			.put("LAST_NAME_KEY", new FieldMapper("lastNameKey"))
			.put("CREATE_DATE", new FieldMapper("createDate"))
			.put("ALIAS_OFFENDER_ID", new FieldMapper("aliasOffenderId"))
			.put("FIRST_NAME_KEY", new FieldMapper("firstNameKey"))
			.put("MIDDLE_NAME_KEY", new FieldMapper("middleNameKey"))
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("CASELOAD_TYPE", new FieldMapper("caseloadType"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("ALIAS_NAME_TYPE", new FieldMapper("aliasNameType"))
			.put("PARENT_OFFENDER_ID", new FieldMapper("parentOffenderId"))
			.put("UNIQUE_OBLIGATION_FLAG", new FieldMapper("uniqueObligationFlag"))
			.put("SUSPENDED_FLAG", new FieldMapper("suspendedFlag"))
			.put("SUSPENDED_DATE", new FieldMapper("suspendedDate"))
			.put("RACE_CODE", new FieldMapper("raceCode"))
			.put("REMARK_CODE", new FieldMapper("remarkCode"))
			.put("ADD_INFO_CODE", new FieldMapper("addInfoCode"))
			.put("BIRTH_COUNTY", new FieldMapper("birthCounty"))
			.put("BIRTH_STATE", new FieldMapper("birthState"))
			.put("MIDDLE_NAME_2", new FieldMapper("middleName2"))
			.put("TITLE", new FieldMapper("title"))
			.put("AGE", new FieldMapper("age"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("LAST_NAME_ALPHA_KEY", new FieldMapper("lastNameAlphaKey"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("NAME_SEQUENCE", new FieldMapper("nameSequence"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.build();
	public final Map<String, FieldMapper> aliasOffenderBookingsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("BOOKING_BEGIN_DATE", new FieldMapper("bookingBeginDate"))
			.put("BOOKING_END_DATE", new FieldMapper("bookingEndDate"))
			.put("BOOKING_NO", new FieldMapper("bookingNo"))
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId"))
			.put("DISCLOSURE_FLAG", new FieldMapper("disclosureFlag"))
			.put("IN_OUT_STATUS", new FieldMapper("inOutStatus"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("BOOKING_STATUS", new FieldMapper("bookingStatus"))
			.put("FINGER_PRINTED_STAFF_ID", new FieldMapper("fingerPrintedStaffId"))
			.put("SEARCH_STAFF_ID", new FieldMapper("searchStaffId"))
			.put("PHOTO_TAKING_STAFF_ID", new FieldMapper("photoTakingStaffId"))
			.put("ASSIGNED_STAFF_ID", new FieldMapper("assignedStaffId"))
			.put("CREATE_AGY_LOC_ID", new FieldMapper("createAgyLocId"))
			.put("BOOKING_TYPE", new FieldMapper("bookingType"))
			.put("BOOKING_CREATED_DATE", new FieldMapper("bookingCreatedDate"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("AGENCY_IML_ID", new FieldMapper("agencyImlId"))
			.put("SERVICE_FEE_FLAG", new FieldMapper("serviceFeeFlag"))
			.put("EARNED_CREDIT_LEVEL", new FieldMapper("earnedCreditLevel"))
			.put("EKSTRAND_CREDIT_LEVEL", new FieldMapper("ekstrandCreditLevel"))
			.put("INTAKE_AGY_LOC_ID", new FieldMapper("intakeAgyLocId"))
			.put("ACTIVITY_DATE", new FieldMapper("activityDate"))
			.put("INTAKE_CASELOAD_ID", new FieldMapper("intakeCaseLoadId"))
			.put("INTAKE_USER_ID", new FieldMapper("intakeUserId"))
			.put("CASE_OFFICER_ID", new FieldMapper("caseOfficerId"))
			.put("CASE_DATE", new FieldMapper("caseDate"))
			.put("CASE_TIME", new FieldMapper("caseTime"))
			.put("COMMUNITY_ACTIVE_FLAG", new FieldMapper("communityActiveFlag"))
			.put("CREATE_INTAKE_AGY_LOC_ID", new FieldMapper("createIntakeAgyLocId"))
			.put("REQUEST_NAME", new FieldMapper("requestName"))
			.put("RECORD_USER_ID", new FieldMapper("recordUserId"))
			.put("INTAKE_AGY_LOC_ASSIGN_DATE", new FieldMapper("intakeAgyLocAssignDate"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("AGY_LOC_ID_LIST", new FieldMapper("agyLocIdList"))
			.put("STATUS_REASON", new FieldMapper("statusReason"))
			.put("TOTAL_UNEXCUSED_ABSENCES", new FieldMapper("totalUnexecusedAbsences"))
			.put("COMM_STAFF_ID", new FieldMapper("commStaffId"))
			.put("COMM_STATUS", new FieldMapper("commStatus"))
			.put("COMMUNITY_AGY_LOC_ID", new FieldMapper("communityAgyLocId"))
			.put("NO_COMM_AGY_LOC_ID", new FieldMapper("noCommAgyLocId"))
			.put("COMM_STAFF_ROLE", new FieldMapper("commStaffRole"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.build();
	public final Map<String, FieldMapper> vheaderBlockMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID",          new FieldMapper("offenderId"))
			.put(" ALIAS_OFFENDER_ID  ", new FieldMapper("aliasOffenderId"))
			.put("OFFENDER_ID_DISPLAY",  new FieldMapper("offenderIdDisplay"))
			.put("LAST_NAME", 			 new FieldMapper("lastName"))
			.put("FIRST_NAME",           new FieldMapper("firstName"))
			.put("MIDDLE_NAME", 		 new FieldMapper("middleName"))
			.put("SUFFIX", 				 new FieldMapper("suffix"))
			.put("BIRTH_DATE", 			 new FieldMapper("birthDate"))
			.put("OFFENDER_BOOK_ID", 	 new FieldMapper("offenderBookId"))
			.put("BOOKING_NO", 			 new FieldMapper("bookingNo"))
			.put("BOOKING_BEGIN_DATE",   new FieldMapper("bookingBeginDate"))
			.put("BOOKING_END_DATE",     new FieldMapper("bookingEndDate"))
			.put("AGY_LOC_ID",           new FieldMapper("agyLocId"))
			.put("AGENCY_IML_ID",        new FieldMapper("agencyImlId"))
			.put("LIVING_UNIT_ID",       new FieldMapper("livingUnitId"))
			.put("DISCLOSURE_FLAG",      new FieldMapper("disclosureFlag"))
			.put("ACTIVE_FLAG", 		 new FieldMapper("activeFlag"))
			.put("BOOKING_STATUS",		 new FieldMapper("bookingStatus"))
			.put("LIVING_UNIT_DESCRIPTION", new FieldMapper("livingUnitDescription"))
			.put("IN_OUT_STATUS",        new FieldMapper("inOutStatus"))
			.put("STATUS_DISPLAY",       new FieldMapper("statusDisplay"))
			.put("ROOT_OFFENDER_ID",	 new FieldMapper("rootOffenderId"))
			.put("ASSIGNED_STAFF_ID",	 new FieldMapper("assignedStaffId"))
			.put("AGY_LOC_TYPE", 		 new FieldMapper("agyLocType"))
			.put("CREATE_AGY_LOC_ID",	 new FieldMapper("createAgyLocId"))
			.put("BOOKING_TYPE", 		 new FieldMapper("bookingType"))
			.put("BOOKING_CREATED_DATE", new FieldMapper("bookingCreatedDate"))
			.put("LOCATION_CODE", 		 new FieldMapper("locationCode"))
			.put("LIV_UNIT_DESC", new FieldMapper("livUnitDesc"))
			.put("CREATE_INTAKE_AGY_LOC_ID", new FieldMapper("createIntakeAgyLocId"))
			.put("INTAKE_AGY_LOC_ID",	 new FieldMapper("intakeAgyLocId"))
			.put("COMMUNITY_ACTIVE_FLAG",new FieldMapper("communityActiveFlag"))

			.put("COMMUNITY_STATUS",     new FieldMapper("communityStatus"))
			.put("STATUS_REASON",        new FieldMapper("statusReason"))
			.put("HEADER_STATUS",        new FieldMapper("headerStatus"))
			.put("AGE", 				 new FieldMapper("age"))
			.put("GENDER", 				 new FieldMapper("gender"))
			.put("OFFICER", new FieldMapper("officer"))
			.put("PRISON_LOCATION", new FieldMapper("prisonLocation"))
			.put("OFF_ALERTS",      	 new FieldMapper("offAlerts"))
			.put("STATUS_1", 			 new FieldMapper("status1"))
			.put("STATUS_2", 			 new FieldMapper("status2"))
			.put("STATUS_3", 			 new FieldMapper("status3"))
			.put("ETHNICITY",			 new FieldMapper("ethnicity"))
			.put("MOVEMENT_REASON", 	 new FieldMapper("movementReason"))
			.put("IMAGE_ID", 	         new FieldMapper("imageId"))
			.put("OFF_SUP_LEVEL",		 new FieldMapper("offSupLevel")).build();
	
	public final Map<String, FieldMapper> sysProfMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_VALUE",          new FieldMapper("profileValue"))
			.put("PROFILE_VALUE_2",          new FieldMapper("profileValue2"))
			.build();

	/**
	 * Creates new OcdaliasRepositoryImpl class Object
	 */
	public OcdaliasRepositoryImpl() {
	}

	/**
	 * fetch records from OffednerIdentifiers
	 * 
	 * @param aliasesBean
	 * @return List<OffenderIdentifier>
	 */
	@Override
	public List<OffenderIdentifier> offIdAllSearchOffenderIdentifiers(final Offenders aliasesBean) {
		final String sql = getQuery("OCDALIAS_OFFID_FIND_ALL_OFFENDER_IDENTIFIERS");
		final RowMapper<OffenderIdentifier> aliasesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderIdentifier.class, offenderIdentifiersMapping);
		ArrayList<OffenderIdentifier> returnList = new ArrayList<>();
		returnList = (ArrayList<OffenderIdentifier>) namedParameterJdbcTemplate.query(sql,
				createParams("ROOT_OFFENDER_ID", aliasesBean.getRootOffenderId()), aliasesRowMapper);
		return returnList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param objOffenderIdentifiers
	 * @return List<OffenderIdentifier>
	 */
	@Override
	public List<OffenderIdentifier> offIdSearchOffenderIdentifiers(final Offenders aliasesBean) {
		final String sql = getQuery("OCDALIAS_OFFID_FIND_OFFENDER_IDENTIFIERS");
		final RowMapper<OffenderIdentifier> aliasesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderIdentifier.class, offenderIdentifiersMapping);
		ArrayList<OffenderIdentifier> returnList = new ArrayList<OffenderIdentifier>();
		returnList = (ArrayList<OffenderIdentifier>) namedParameterJdbcTemplate.query(sql,
				createParams("offenderId", aliasesBean.getOffenderId(),"userId",aliasesBean.getCreateUserId()), aliasesRowMapper);
		return returnList;
	}

	/**
	 * Inserting OffenderIdentifiers
	 * 
	 * @Param listOffenders
	 * @return Integer
	 */
	@Override
	public Integer offIdInsertOffenderIdentifiers(final List<OffenderIdentifier> listOffenders) {
		Integer idSeq = 0;
		Boolean index = true;
		final String sql = getQuery("OCDALIAS_OFFID_INSERT_OFFENDER_IDENTIFIERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderIdentifier list : listOffenders) {
			if (index) {
				index = false;
				String offenderIdSeq = generateOffenderIdSeqForOffednerIdentifiers(
						(Long) listOffenders.get(idSeq).getOffenderId(),
						listOffenders.get(idSeq).getRootOffenderId().intValue());
				idSeq = Integer.valueOf(offenderIdSeq);
			}
			list.setOffenderIdSeq(idSeq.toString());
			idSeq++;
			list.setIssuedDate((list.getIssuedDate() == null) ? dateRepository.getDBTime() : list.getIssuedDate());
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e);
		}
		if (listOffenders.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * update OffenderIdentifiers
	 * 
	 * @Param listOffenders
	 * @return Integer
	 */
	@Override
	public Integer offIdUpdateOffenderIdentifiers(final List<OffenderIdentifier> listOffenders) {
		final String sql = getQuery("OCDALIAS_OFFID_UPDATE_OFFENDER_IDENTIFIERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderIdentifier list : listOffenders) {
			list.setIssuedDate((list.getIssuedDate() == null) ? dateRepository.getDBTime() : list.getIssuedDate());
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e);
		}
		if (listOffenders.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Delete OffenderIdentifiers
	 * 
	 * @Param listOffenders
	 * @return Integer
	 */
	@Override
	public Integer offIdDeleteOffenderIdentifiers(final List<OffenderIdentifier> listOffenders) {
		final String sql = getQuery("OCDALIAS_OFFID_DELETE_OFFENDER_IDENTIFIERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderIdentifier list : listOffenders) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			String tableName = "OFFENDER_IDENTIFIERS";
			String whereClause = "OFFENDER_ID = :offenderId  and OFFENDER_ID_SEQ = :offenderIdSeq";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offIdDeleteOffenderIdentifiers", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e);
		}
		if (listOffenders.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Insert Offenders
	 * 
	 * @Param listOffenders
	 * @return Integer
	 */
	@Override
	public Integer offNameInsertOffenders(final List<Offenders> listOffenders) {
		Long offenderId = null;
		Boolean check = true;
		final String sql = getQuery("OCDALIAS_OFFNAME_INSERT_OFFENDERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Offenders list : listOffenders) {
			if (check) {
				check = false;
				offenderId = generateOffenderIdForOffenders();
			}
			list.setOffenderId(offenderId);
			offenderId++;
			list.setIdSourceCode((list.getIdSourceCode() == null) ? "SEQ" : list.getIdSourceCode());
			list.setLastNameKey((list.getLastNameKey() == null) ? list.getLastName() : list.getLastNameKey());
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e);
		}
		if (listOffenders.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Generate OffenderId to InsertOffenders
	 * 
	 * @return Integer
	 */
	public Long generateOffenderIdForOffenders() {
		final String sql = getQuery("OCDALIAS_GENERATE_OFFENDER_ID");
		Long returnOffenderId = null;
		returnOffenderId = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		return returnOffenderId;
	}

	/**
	 * update Offenders
	 * 
	 * @Param listOffenders
	 * @return Integer
	 */
	@Override
	public Integer offNameUpdateOffenders(final List<Offenders> listOffenders) {
		final String sql = getQuery("OCDALIAS_OFFNAME_UPDATE_OFFENDERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Offenders list : listOffenders) {
			list.setCreateDate((list.getCreateDate() == null) ? dateRepository.getDBTime() : list.getCreateDate());
			list.setIdSourceCode((list.getIdSourceCode() == null) ? "SEQ" : list.getIdSourceCode());
			list.setLastNameKey((list.getLastNameKey() == null) ? list.getLastName() : list.getLastNameKey());
			list.setAliasOffenderId((list.getAliasOffenderId() == null)
					? Integer.valueOf(list.getRootOffenderId().toString()) : list.getAliasOffenderId());
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e);
		}
		if (listOffenders.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Delete Offenders
	 * 
	 * @Param listOffenders
	 * @return Integer
	 */
	@Override
	public Integer offNameDeleteOffenders(final List<Offenders> listOffenders) {
		final String sql = getQuery("OCDALIAS_OFFNAME_DELETE_OFFENDERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Offenders list : listOffenders) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			String tableName = "OFFENDERS";
			String whereClause = "OFFENDER_ID = :offenderId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offNameDeleteOffenders", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
		}
		if (listOffenders.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<Offenders>
	 * @param objSearchDao
	 */
	@Override
	public List<Offenders> offNameSearchOffenders(final Offenders objSearchDao) {
		final String sql = getQuery("OCDALIAS_OFFNAME_FIND_OFFENDERS");
		final RowMapper<Offenders> offenderRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class,
				aliasOffenderMapping);
		ArrayList<Offenders> returnList = new ArrayList<Offenders>();
		returnList = (ArrayList<Offenders>) (namedParameterJdbcTemplate.query(sql,
				createParams("ROOT_OFFENDER_ID", objSearchDao.getRootOffenderId()), offenderRowMapper));
		return returnList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return String
	 * @param param
	 */
	@Override
	public String offIdPreInsert(final String params) {
		final String sql = getQuery("OCDALIAS_OFF_ID_PREINSERT");
		String returnList = null;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId", params), String.class);
		return returnList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return String
	 * @param offenderId,
	 *            rootOffenderId
	 */
	public String generateOffenderIdSeqForOffednerIdentifiers(final Long offenderId, final Integer rootOffenderId) {
		final String sql = getQuery("OCDALIAS_GENERATE_OFFENDER_IDSEQ_IDENTIFIERS");
		String returnList = null;
		returnList = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderId", offenderId, "rootOffenderId", rootOffenderId), String.class);
		return returnList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return VHeaderBlock
	 * @param lstVHeaderBlock
	 */
	public VHeaderBlock changeWorkingName(final VHeaderBlock lstVHeaderBlock) {
		Integer returnUpdate = 0;
		VHeaderBlock vHeaderBlock = new VHeaderBlock();
		final String sql = getQuery("OCDALIAS_UPDATE_WORKING_NAME_QUERY");
		returnUpdate = namedParameterJdbcTemplate.update(sql,
				createParams("offenderBookId", lstVHeaderBlock.getOffenderBookId(), "rootOffenderId",
						lstVHeaderBlock.getRootOffenderId(), "offenderId", lstVHeaderBlock.getOffenderId(),"modifyUserId",lstVHeaderBlock.getModifyUserId()));
		if (returnUpdate == 1) {
			vHeaderBlock = returnVHeaderBlock(lstVHeaderBlock);
		} else {
			vHeaderBlock = null;
		}
		return vHeaderBlock;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return VHeaderBlock
	 * @param lstVHeaderBlock
	 */
	public VHeaderBlock returnVHeaderBlock(final VHeaderBlock lstVHeaderBlock) {
		final String sql = getQuery("OCDALIAS_CHANGE_WORKING_NAME_QUERY_RETURN_BEAN");
		final RowMapper<VHeaderBlock> vHeaderRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				vheaderBlockMapping);
		VHeaderBlock returnList = new VHeaderBlock();
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId",
				lstVHeaderBlock.getOffenderId(), "rootOffenderId", lstVHeaderBlock.getRootOffenderId()),
				vHeaderRowMapper);
		return returnList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return VHeaderBlock
	 * @param offBookBean
	 */
	public VHeaderBlock getWorkingNameOffenderID(final Offenders offBookBean) {
		final String sql = getQuery("OCDALIAS_MODIFIED_RECORD");
		final RowMapper<VHeaderBlock> offenderRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				vheaderBlockMapping);
		VHeaderBlock returnList = new VHeaderBlock();
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("ROOT_OFFENDER_ID", offBookBean.getRootOffenderId()), offenderRowMapper);
		} catch (Exception e) {
			returnList = new VHeaderBlock();
		}
		return returnList;
	}

	public List<ReferenceCodes> getGenderDescription() {
		List<ReferenceCodes> returnList = new ArrayList<>();
		final String sql = getQuery("OCDALIAS_GET_GENDER_DESCRIPTION");
		final RowMapper<ReferenceCodes> referenceRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), referenceRowMapper);
		return returnList;
	}
	
	/**
	 * Fetching the record from database table
	 * 
	 * @Param objOffenderIdentifiers
	 * @return List<OffenderIdentifier>
	 */
	@Override
	public Integer offNameOnCheckDeleteMasteroffIdCur(final Offenders aliasesBean) {
		final String sql = getQuery("OCDALIAS_OFF_NAME_ONCHECKDELETEMASTER_OFF_ID_CUR");
		Integer returnList;
		returnList = namedParameterJdbcTemplate.queryForObject(sql,createParams("offenderId", aliasesBean.getOffenderId()), Integer.class);
		return returnList;
	}
	/**
	 * MEthod used to get PROFILE_VALUE, PROFILE_VALUE2 from SystemProfiles
	 * @return SystemProfiles
	 */
	public SystemProfiles vsRangeCursor() {
		final String sql = getQuery("OCDALIAS_VS_RANGECUR");
		final RowMapper<SystemProfiles> sysProfMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				sysProfMapping);
		return namedParameterJdbcTemplate.queryForObject(sql,createParams(), sysProfMapper);
	}

	@Override
	public List<Offenders> gettingOldRecord(Long offenderId) {
		final String sql = getQuery("OCDALIAS_GETTING_OLD_RECORD");
		List<Offenders> returnList = new ArrayList<>();

		final RowMapper<Offenders> referenceRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class,
				referenceCodesMapping);
		returnList= namedParameterJdbcTemplate.query(sql,createParams("offenderId",offenderId), referenceRowMapper);
		return returnList;
		
	}

	@Override
	public OffenderBookings gettingOldData(BigDecimal offenderBookId) {
		final String sql = getQuery("OCDALIAS_GETTING_OLDATA");
		OffenderBookings returnObj=new OffenderBookings();
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBokkId",offenderBookId ),
				new BeanPropertyRowMapper<OffenderBookings>(OffenderBookings.class));
		return returnObj;
	}
}
