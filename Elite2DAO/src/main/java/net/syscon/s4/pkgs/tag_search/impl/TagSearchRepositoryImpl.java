package net.syscon.s4.pkgs.tag_search.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.CourseActivityAreas;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderIdentifier;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.pkgs.tag_search.TagSearchRepository;

@Repository
public class TagSearchRepositoryImpl extends RepositoryBase implements TagSearchRepository {

	private static Logger logger = LogManager.getLogger(TagSearchRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> offMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CRS_ACTY_ID", new FieldMapper("crsActyId"))
			.put("pin_identifier", new FieldMapper("pinIdentifier")).build();

	private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("COMMUNITY_AGY_LOC_ID", new FieldMapper("communityAgyLocId"))
			.put("COMMUNITY_STATUS", new FieldMapper("commStatus")).put("ADDRESS", new FieldMapper("address"))

			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("LIVING_UNIT_ID", new FieldMapper("livingUnitId"))
			.put("AGENCY_IML_ID", new FieldMapper("agencyImlId")).put("PRISON_STATUS", new FieldMapper("prisonStatus"))
			.build();

	// This method is used for Get Offender Identifier Details for a particular
	// offender id.
	@Override
	public List<OffenderIdentifier> getOffenderIdentifiers(final Long offenderId) {
		final String sql = getQuery("GET_OFFENDER_IDENTIFIERS");
		List<OffenderIdentifier> retList = new ArrayList<OffenderIdentifier>();

		final RowMapper<OffenderIdentifier> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderIdentifier.class,
				offMapping);
		try {
			retList = namedParameterJdbcTemplate.query(sql, createParams("p_offender_id", offenderId), rowMapper);
		} catch (Exception e) {
			logger.error("getOffenderIdentifiers", e);
		}
		return retList;
	}

	// This method is uesd for getting profile_value
	@Override
	public String getProfileValue(final String profileType, final String profileCode) {
		final String sql = getQuery("GET_PROFILE_VALUE");
		String retVal = "N";
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_profile_type", profileType, "p_profile_code", profileCode), String.class);
		} catch (Exception e) {
			logger.error("getProfileValue", e);
			retVal = "N";
		}
		if (retVal == null) {
			retVal = "N";
		}
		return retVal;
	}

	// This method is used for retrive the Simple Name Search.
	@Override
	public List<Offenders> resultSetOperationOne(final Offenders offenders) {
		final String sql = getQuery("RESULT_SET_OPERATION_ONE");
		List<Offenders> retList = new ArrayList<Offenders>();

		final RowMapper<Offenders> rowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, offMapping);
		try {
			retList = namedParameterJdbcTemplate.query(sql,
					createParams("p_lname_key", offenders.getLastNameAlphaKey(), "p_last_name", offenders.getLastName(),
							"p_first_name", offenders.getFirstName(), "p_middle_name", offenders.getMiddleName(),
							"lv_from_date", offenders.getLvFromDate(), "lv_to_date", offenders.getLvToDate(),
							"p_birth_date", offenders.getBirthDate(), "p_sex_code", offenders.getSexCode(), "p_agedate",
							offenders.getAgeDate(), "lv_from_agedate", offenders.getLvFromAgedate(), "lv_to_agedate",
							offenders.getLvToAgedate(), "p_ethnicity", offenders.getRaceCode(),"p_gender_code",offenders.getGenderCode(),
							"p_second_middle_name", offenders.getsecondMiddleName()),
					rowMapper);
		} catch (Exception e) {
			logger.error("resultSetOperationOne", e);
			retList = null;
		}
		return retList;

	}
// this method id used for Name Search with Switching of Last Name and First Name
	@Override
	public List<Offenders> resultSetOperationTwo(final Offenders offenders) {
		final String sql = getQuery("RESULT_SET_OPERATION_TWO");
		List<Offenders> retList = new ArrayList<Offenders>();

		final RowMapper<Offenders> rowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, offMapping);
		try {
			retList = namedParameterJdbcTemplate.query(sql,
					createParams("p_last_name", offenders.getLastName(), "p_first_name", offenders.getFirstName(),
							"p_middle_name", offenders.getMiddleName(), "lv_from_date", offenders.getLvFromDate(),
							"lv_to_date", offenders.getLvToDate(), "p_birth_date", offenders.getBirthDate(),
							"p_sex_code", offenders.getSexCode(), "p_agedate", offenders.getAgeDate(),
							"lv_from_agedate", offenders.getLvFromAgedate(), "lv_to_agedate",
							offenders.getLvToAgedate(), "p_ethnicity", offenders.getRaceCode(),"p_gender_code", offenders.getGenderCode(),
							"p_second_middle_name", offenders.getsecondMiddleName()),
					rowMapper);
		} catch (Exception e) {
			logger.error("resultSetOperationTwo", e);
			retList = null;
		}
		return retList;

	}

	// This method is used for Name Search with Last Name plus First Name
	// Variations.
	@Override
	public List<Offenders> resultSetOperationThree(final Offenders offenders) {
		final String sql = getQuery("RESULT_SET_OPERATION_THREE");
		List<Offenders> retList = new ArrayList<Offenders>();

		final RowMapper<Offenders> rowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, offMapping);
		try {
			retList = namedParameterJdbcTemplate.query(sql,
					createParams("p_last_name", offenders.getLastName(), "p_first_name", offenders.getFirstName(),
							"p_middle_name", offenders.getMiddleName(), "lv_from_date", offenders.getLvFromDate(),
							"lv_to_date", offenders.getLvToDate(), "p_birth_date", offenders.getBirthDate(),
							"p_sex_code", offenders.getSexCode(), "p_agedate", offenders.getAgeDate(),
							"lv_from_agedate", offenders.getLvFromAgedate(), "lv_to_agedate",
							offenders.getLvToAgedate(), "p_ethnicity", offenders.getRaceCode(),"p_gender_code",offenders.getGenderCode(),
							"p_second_middle_name", offenders.getsecondMiddleName()),
					rowMapper);
		} catch (Exception e) {
			logger.error("resultSetOperationThree", e);
			retList = null;
		}
		return retList;

	}

	// this method is used for getting Offender ID Display Search Only
	@Override
	public List<Offenders> resultSetOperationFour(final String vOffenderIdDisplay) {
		final String sql = getQuery("RESULT_SET_OPERATION_FOUR");
		List<Offenders> retList = new ArrayList<Offenders>();

		final RowMapper<Offenders> rowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, offMapping);
		try {
			retList = namedParameterJdbcTemplate.query(sql, createParams("v_offender_id_display", vOffenderIdDisplay),
					rowMapper);
		} catch (Exception e) {
			logger.error("resultSetOperationFour", e);
			retList = null;
		}
		return retList;

	}
	// this method is used for offender_Id_Display and Booking No. getting.

	@Override
	public List<Offenders> resultSetOperationFive(final String vOffenderIdDisplay, final String bookingNo) {
		final String sql = getQuery("RESULT_SET_OPERATION_FIVE");
		List<Offenders> retList = new ArrayList<Offenders>();

		final RowMapper<Offenders> rowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, offMapping);
		try {
			retList = namedParameterJdbcTemplate.query(sql,
					createParams("v_offender_id_display", vOffenderIdDisplay, "p_book_no", bookingNo), rowMapper);
		} catch (Exception e) {
			logger.error("resultSetOperationFive", e);
			retList = null;
		}
		return retList;
	}

	// this method is used for New cond. added When only Booking No. is avaialble.
	@Override
	public List<Offenders> resultSetOperationSix(final String bookingNo) {
		final String sql = getQuery("RESULT_SET_OPERATION_SIX");
		List<Offenders> retList = new ArrayList<Offenders>();

		final RowMapper<Offenders> rowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, offMapping);
		try {
			retList = namedParameterJdbcTemplate.query(sql, createParams("p_book_no", bookingNo), rowMapper);
		} catch (Exception e) {
			logger.error("resultSetOperationSix", e);
			retList = null;
		}
		return retList;
	}

	// thid method is used for Identifier Search Only.
	@Override
	public List<Offenders> resultSetOperationSeven(final String bookingNo, final String identifier,
			final String identifierType) {
		final String sql = getQuery("RESULT_SET_OPERATION_SEVEN");
		List<Offenders> retList = new ArrayList<Offenders>();

		final RowMapper<Offenders> rowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, offMapping);
		try {
			retList = namedParameterJdbcTemplate.query(sql, createParams("p_book_no", bookingNo, "p_identifier_value",
					identifier, "p_identifier_type", identifierType), rowMapper);
		} catch (Exception e) {
			logger.error("resultSetOperationSeven", e);
			retList = null;
		}
		return retList;
	}

	// this method is used for Both Offender ID Display and Identifier Search.
	@Override
	public List<Offenders> resultSetOperationEigth(final String bookingNo, final String identifier,
			final String identifierType, final String vOffenderIdDisplay) {
		final String sql = getQuery("RESULT_SET_OPERATION_EIRHT");
		List<Offenders> retList = new ArrayList<Offenders>();

		final RowMapper<Offenders> rowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, offMapping);
		try {
			retList = namedParameterJdbcTemplate.query(sql, createParams("p_book_no", bookingNo, "p_identifier_value",
					identifier, "p_identifier_type", identifierType, "v_offender_id_display", vOffenderIdDisplay),
					rowMapper);
		} catch (Exception e) {
			logger.error("resultSetOperationEigth", e);
			retList = null;
		}
		return retList;
	}

	/*
	 * This procedure is migrated from oracle GET_PARTIAL_RECORDSGET_PARTIAL_RECORDS
	 * 
	 * @Procedure get_partial_searches to be used for returning partial and soundex
	 * search results.
	 */
	@Override
	public List<Offenders> getPartialRecordsSelectOperation(final Offenders offenders) {
		final String sql = getQuery("GET_PARTIAL_RECORDS_SELECT_OPERATION");
		List<Offenders> retList = new ArrayList<Offenders>();
		final RowMapper<Offenders> rowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, offMapping);
		try {
			retList = namedParameterJdbcTemplate.query(sql,
					createParams("p_last_name", offenders.getLastName(), "p_first_name", offenders.getFirstName(),
							"p_middle_name", offenders.getMiddleName(), "lv_from_date", offenders.getLvFromDate(),
							"lv_to_date", offenders.getLvToDate(), "p_birth_date", offenders.getBirthDate(),
							"p_sex_code", offenders.getSexCode(), "p_agedate", offenders.getAgeDate(),
							"lv_from_agedate", offenders.getLvFromAgedate(), "lv_to_agedate",
							offenders.getLvToAgedate(), "p_ethnicity", offenders.getRaceCode(),
							"p_second_middle_name", offenders.getsecondMiddleName()),
					rowMapper);
		} catch (Exception e) {
			logger.error("getPartialRecordsSelectOperation", e);
			retList = null;
		}
		return retList;
	}

	/*
	 * This procedure is migrated from oracle GET_PARTIAL_RECORDSGET_PARTIAL_RECORDS
	 * 
	 * @Procedure get_partial_searches to be used for returning partial and soundex
	 * search results.
	 */
	@Override
	public List<Offenders> getPartialRecordsSelectOperationOne(final Offenders offenders) {
		final String sql = getQuery("GET_PARTIAL_RECORDS_SELECT_OPERATION_ONE");
		List<Offenders> retList = new ArrayList<Offenders>();

		final RowMapper<Offenders> rowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, offMapping);
		try {
			retList = namedParameterJdbcTemplate.query(sql,
					createParams("p_first_name", offenders.getFirstName(), "p_middle_name", offenders.getMiddleName(),
							"lv_from_date", offenders.getLvFromDate(), "lv_to_date", offenders.getLvToDate(),
							"p_birth_date", offenders.getBirthDate(), "p_sex_code", offenders.getSexCode(), "p_agedate",
							offenders.getAgeDate(), "lv_from_agedate", offenders.getLvFromAgedate(), "lv_to_agedate",
							offenders.getLvToAgedate(), "p_ethnicity", offenders.getRaceCode(), "p_soundex_lname",
							offenders.getLastNameSoundex(),"p_second_middle_name", offenders.getsecondMiddleName()),
					rowMapper);
		} catch (Exception e) {
			logger.error("getPartialRecordsSelectOperationOne", e);
			retList = null;
		}
		return retList;
	}

	@Override
	public String getAgyDescription(final String pAgyLocId) {
		final String sql = getQuery("GET_AGY_LOC_DESCRIPTION");
		String desc = null;
		try {
			desc = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_AGY_LOC_ID", pAgyLocId),
					String.class);
		} catch (Exception e) {
			logger.error("getAgyDescription :" + e);
			desc = null;
		}
		return desc;
	}

	// This methos is used to get community_details_cur
	@Override
	public OffenderBookings getCommunityDetailsCur(final BigDecimal pRootOffenderId) {
		final String sql = getQuery("GET_COMMUNITY_DETAILS_CUR");
		OffenderBookings retObj = new OffenderBookings();
		final RowMapper<OffenderBookings> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				mapping);
		try {
			retObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_ROOT_OFFENDER_ID", pRootOffenderId),
					rowMapper);
		} catch (Exception e) {
			logger.error("getCommunityDetailsCur :" + e);
			retObj = null;
		}
		return retObj;
	}

	@Override
	public List<AgencyInternalLocations> getInternalLocId(final Integer pInternalLocationId) {
		final String sql = getQuery("GET_INT_LOC_PATH");
		List<AgencyInternalLocations> retObj = new ArrayList<AgencyInternalLocations>();
		final RowMapper<AgencyInternalLocations> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, mapping);
		try {
			retObj = namedParameterJdbcTemplate.query(sql, createParams("P_INTERNAL_LOCATION_ID", pInternalLocationId),
					rowMapper);
		} catch (Exception e) {
			logger.error("getInternalLocId :" + e);
			retObj = null;
		}
		return retObj;
	}

// This methos is used to get prison_details_cur
	@Override
	public OffenderBookings getPrisonDetailsCur(final BigDecimal pRootOffenderId) {
		final String sql = getQuery("GET_PRISON_DETAILS_CUR");
		OffenderBookings retObj = new OffenderBookings();
		final RowMapper<OffenderBookings> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				mapping);
		try {
			retObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_ROOT_OFFENDER_ID", pRootOffenderId),
					rowMapper);
		} catch (Exception e) {
			logger.error("getCommunityDetailsCur :" + e);
			retObj = null;
		}
		return retObj;
	}

	// This methos is used to get prison_details_inactive_cur
	@Override
	public OffenderBookings prisonDetailsInactiveCur(final BigDecimal pRootOffenderId) {
		final String sql = getQuery("GET_PRISON_DETAILS_INACTIVE_CUR");
		OffenderBookings retObj = new OffenderBookings();
		final RowMapper<OffenderBookings> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				mapping);
		try {
			retObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_ROOT_OFFENDER_ID", pRootOffenderId),
					rowMapper);
		} catch (Exception e) {
			logger.error("prisonDetailsInactiveCur :" + e);
			retObj = null;
		}
		return retObj;
	}

	@Override
	public Integer deleteCourseActivityAreasDeleteOperation(final Long crsActyId,String modifyUserId) {
		final String sql = getQuery("DELETE_COURSE_ACTIVITY_AREAS_DELETE_OPERATION");

		final CourseActivityAreas obj = new CourseActivityAreas();
//		obj.setCrsActyId(crsActyId);

		final List<CourseActivityAreas> list = new ArrayList<CourseActivityAreas>();
		list.add(obj);

		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();

		for (final CourseActivityAreas couActAre : list) {
			parameters.add(new BeanPropertySqlParameterSource(couActAre));
		}
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "COURSE_ACTIVITY_AREAS";
			String whereCondition = "crs_acty_id = :crsActyId";
			inputMap.put("crsActyId", crsActyId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteCourseActivityAreasDeleteOperation " + e.getMessage());
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("deleteCourseActivityAreasDeleteOperation", e);
			return 1;
		}
		return 0;
	}

	@Override
	public List<Areas> cArea(final String areaCode) {
		final String sql = getQuery("SELECT_C_AREA");
		final RowMapper<Areas> rowMapper = Row2BeanRowMapper.makeMapping(sql, Areas.class, mapping);
		return namedParameterJdbcTemplate.query(sql, createParams("areaCode", areaCode), rowMapper);
	}

	@Override
	public List<Offenders> resultSetOperationDob(Offenders offenders) {
		final String sql = getQuery("RESULT_SET_OPERATION_DOB");
		List<Offenders> retList = new ArrayList<Offenders>();

		final RowMapper<Offenders> rowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, offMapping);
		try {
			retList = namedParameterJdbcTemplate.query(sql,
					createParams(
							"lv_from_date", offenders.getLvFromDate(), "lv_to_date", offenders.getLvToDate(),
							"p_birth_date",  offenders.getBirthDate(), "p_agedate",	offenders.getAgeDate(), "lv_from_agedate", offenders.getLvFromAgedate(), "lv_to_agedate",
							offenders.getLvToAgedate(),	"p_birth_year", offenders.getBirthYear()),
					rowMapper);
		} catch (Exception e) {
			logger.error("resultSetOperationOne", e);
			retList = null;
		}
		return retList;
	
	}

}