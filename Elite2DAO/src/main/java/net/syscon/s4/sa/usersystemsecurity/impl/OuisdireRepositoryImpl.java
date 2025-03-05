package net.syscon.s4.sa.usersystemsecurity.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.sa.usersystemsecurity.OuisdireRepository;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffSkills;
import net.syscon.s4.sa.usersystemsecurity.beans.VMemberSkills;

/**
 * Class OuisdireRepositoryImpl
 */
@Repository
public class OuisdireRepositoryImpl extends RepositoryBase implements OuisdireRepository {

	private final Map<String, FieldMapper> stskMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SUB_TYPE", new FieldMapper("subType"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("STSK_COMMENT", new FieldMapper("stskComment"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("STAFF_SKILL_ID", new FieldMapper("staffSkillId"))
			.put("'1'", new FieldMapper("  '1' "))
			.put("SKILL_TYPE", new FieldMapper("skillType")).build();
	private final Map<String, FieldMapper> iAddresMaping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("INTERNET_ADDRESS_ID", new FieldMapper("internetAddressId"))
			.put("INTERNET_ADDRESS_CLASS", new FieldMapper("internetAddressClass"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.build();
	private final Map<String, FieldMapper> reCodeMaping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> vSkillMaping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ROLE", new FieldMapper("role"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("STAFF_ID", new FieldMapper("staffId"))
			.put("STATUS", new FieldMapper("status"))
			.put("SCHEDULE_TYPE", new FieldMapper("scheduleType"))
			.put("SEX_CODE", new FieldMapper("sexCode"))
			.put("LAST_NAME", new FieldMapper("lastName"))
			.put("FIRST_NAME", new FieldMapper("firstName"))
			.put("POSITION", new FieldMapper("position"))
			.build();
	private final Map<String, FieldMapper> phonesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OWNER_ID", new FieldMapper("ownerId"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("PHONE_ID", new FieldMapper("phoneId"))
			.put("OWNER_CLASS", new FieldMapper("ownerClass"))
			.put("OWNER_CODE", new FieldMapper("ownerCode"))
			.put("EXT_NO", new FieldMapper("extNo"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("OWNER_SEQ", new FieldMapper("ownerSeq"))
			.put("STAFF_ID", new FieldMapper("staffId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("PHONE_TYPE", new FieldMapper("phoneType"))
			.put("PHONE_NO", new FieldMapper("phoneNo"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("AREA_CODE", new FieldMapper(" areaCode "))
			.put("ABBREVIATION", new FieldMapper("abbreviation"))
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.build();
	private final Map<String, FieldMapper> agyLocMaping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("CITY_NAME", new FieldMapper("cityName"))
			.put("CITY_COD", new FieldMapper("cityCod"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.build();
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OuisdireRepositoryImpl.class.getName());

	/**
	 * Creates new OuisdireRepositoryImpl class Object
	 */
	public OuisdireRepositoryImpl() {
	}

	

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VMemberSkills
	 *
	 * @return List<VMemberSkills>
	 *
	 * @throws SQLException
	 */
	public List<VMemberSkills> vms1ExecuteQuery(final VMemberSkills objSearchDao) {
		final String sql = getQuery("OUISDIRE_VMS1_FIND_V_MEMBER_SKILLS");
		final StringBuffer pSql = new StringBuffer(sql);
		String preparedSql = "";
		final MapSqlParameterSource param = new MapSqlParameterSource();
		if (objSearchDao != null) {
			pSql.append(" WHERE ");

			if (objSearchDao.getLastName() != null && !objSearchDao.getLastName().isEmpty()) {
				pSql.append(" LAST_NAME LIKE :lastName AND ");
				param.addValue("lastName", objSearchDao.getLastName()+"%");
			}
			if (objSearchDao.getFirstName() != null && !objSearchDao.getFirstName().isEmpty()) {
				pSql.append(" FIRST_NAME LIKE :firstName AND ");
				param.addValue("firstName", objSearchDao.getFirstName()+"%");
			}
			if (objSearchDao.getSexCode() != null && !objSearchDao.getSexCode().isEmpty()) {
				pSql.append(" SEX_CODE LIKE :sexCode AND ");
				param.addValue("sexCode", objSearchDao.getSexCode());
			}

			if (objSearchDao.getCity() != null && !objSearchDao.getCity().isEmpty()) {
				pSql.append(
						" agy_loc_id IN (select AGY_LOC_ID from v_agency_addresses c where c.CITY_CODE =:city) AND ");
				param.addValue("city", objSearchDao.getCity());
			}

			if (objSearchDao.getNomsRegionCode() != null && !objSearchDao.getNomsRegionCode().isEmpty()) {
				pSql.append(
						" agy_loc_id IN (select agy_loc_id from agency_locations b where b.NOMS_REGION_CODE =:nomsRegionCode) AND ");
				param.addValue("nomsRegionCode", objSearchDao.getNomsRegionCode());
			}
			if (objSearchDao.getScheduleType() != null && !objSearchDao.getScheduleType().isEmpty()) {
				pSql.append(" SCHEDULE_TYPE LIKE :scheduleType AND ");
				param.addValue("scheduleType", objSearchDao.getScheduleType());
			}
			if (objSearchDao.getAgencyLocationType() != null && !objSearchDao.getAgencyLocationType().isEmpty()) {
				pSql.append(
						" agy_loc_id IN (select agy_loc_id from agency_locations b where b.AGENCY_LOCATION_TYPE =:agencyLocationType) AND ");
				param.addValue("agencyLocationType", objSearchDao.getAgencyLocationType());
			}
			if (objSearchDao.getAgyLocId() != null && !objSearchDao.getAgyLocId().isEmpty()) {
				pSql.append(" AGY_LOC_ID LIKE :agyLocId AND ");
				param.addValue("agyLocId", objSearchDao.getAgyLocId());
			}
			if (objSearchDao.getPosition() != null && !objSearchDao.getPosition().isEmpty()) {
				pSql.append(" POSITION LIKE :position AND ");
				param.addValue("position", objSearchDao.getPosition());
			}
			if (objSearchDao.getRole() != null && !objSearchDao.getRole().isEmpty()) {
				pSql.append(" ROLE LIKE :role AND ");
				param.addValue("role", objSearchDao.getRole());
			}

			if (objSearchDao.getSkillType() != null && !objSearchDao.getSkillType().isEmpty()
					&& objSearchDao.getSubType() == null) {
				pSql.append(" staff_id IN (select staff_id from staff_skills a where a.SKILL_TYPE =:skillType) AND ");
				param.addValue("skillType", objSearchDao.getSkillType());
			}

			if (objSearchDao.getSkillType() != null && !objSearchDao.getSkillType().isEmpty()
					&& objSearchDao.getSubType() != null) {
				pSql.append(
						" staff_id IN (select staff_id from staff_skills a where a.SKILL_TYPE =:skillType AND a.SUB_TYPE=:subType) AND ");
				param.addValue("skillType", objSearchDao.getSkillType());
				param.addValue("subType", objSearchDao.getSubType());
			}

		}
		preparedSql = pSql.toString().trim();

		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql
				.concat(" order by LAST_NAME,FIRST_NAME,AGY_LOC_ID,SEX_CODE,POSITION,ROLE,SCHEDULE_TYPE ");
		final RowMapper<VMemberSkills> staffRowMaper = Row2BeanRowMapper.makeMapping(preparedSql,
				VMemberSkills.class, vSkillMaping);
		List<VMemberSkills> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(preparedSql, param, staffRowMaper);
		} catch (Exception e) {
			logger.error("senCalcExecuteQuery", e);
		}
		return returnList;
	}

	

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstVMemberSkills
	 *            List<VMemberSkills>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer vms1InsertVMemberSkills(final List<VMemberSkills> lstVMemberSkills) {
		final String sql = getQuery("OUISDIRE_VMS1_INSERT_V_MEMBER_SKILLS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		
		if (lstVMemberSkills.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            StaffSkills
	 *
	 * @return List<StaffSkills>
	 *
	 * @throws SQLException
	 */
	public List<StaffSkills> stskExecuteQuery(final StaffSkills objSearchDao) {
		final String sql = getQuery("OUISDIRE_STSK_FIND_STAFF_SKILLS");
		final RowMapper<StaffSkills> skRowMaper = Row2BeanRowMapper.makeMapping(sql, StaffSkills.class,
				stskMapping);
		return  (ArrayList<StaffSkills>) namedParameterJdbcTemplate.query(sql,
				createParams("staffId", objSearchDao.getStaffId()), skRowMaper);
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstStaffSkills
	 *            List<StaffSkills>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer stskInsertStaffSkills(final List<StaffSkills> lstStaffSkills) {
		final String sql = getQuery("OUISDIRE_STSK_INSERT_STAFF_SKILLS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstStaffSkills.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            Phones
	 *
	 * @return List<Phones>
	 *
	 * @throws SQLException
	 */
	public List<Phones> hmPhoneExecuteQuery(final Phones objSearchDao) {
		final String sql = getQuery("OUISDIRE_HMPHONE_FIND_PHONES");
		final RowMapper<Phones> PhonesRowMapper = Row2BeanRowMapper.makeMapping(sql, Phones.class, phonesMapping);
		final ArrayList<Phones> returnList = (ArrayList<Phones>) namedParameterJdbcTemplate.query(sql,
				createParams("staffId", objSearchDao.getOwnerId()), PhonesRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            Phones
	 *
	 * @return List<Phones>
	 *
	 * @throws SQLException
	 */
	public List<Phones> busPhoneExecuteQuery(final Phones objSearchDao) {
		final String sql = getQuery("OUISDIRE_BUSPHONE_FIND_PHONES");
		final RowMapper<Phones> PhonesRowMapper = Row2BeanRowMapper.makeMapping(sql, Phones.class, phonesMapping);
		return (ArrayList<Phones>) namedParameterJdbcTemplate.query(sql,
				createParams("staffId", objSearchDao.getOwnerId()), PhonesRowMapper);
	}
	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            InternetAddresses
	 *
	 * @return List<InternetAddresses>
	 *
	 * @throws SQLException
	 */
	public List<InternetAddresses> emailExecuteQuery(final InternetAddresses objSearchDao) {
		final String sql = getQuery("OUISDIRE_EMAIL_FIND_INTERNET_ADDRESSES");
		final RowMapper<InternetAddresses> iAdrsMaper = Row2BeanRowMapper.makeMapping(sql,
				InternetAddresses.class, iAddresMaping);
		return (ArrayList<InternetAddresses>) namedParameterJdbcTemplate
				.query(sql, createParams("staffId", objSearchDao.getOwnerId()), iAdrsMaper);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<Areas> nomRegionRgRecordGroup() {
		final String sql = getQuery("OUISDIRE_FIND_NOMREGIONRG");
		final RowMapper<Areas> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Areas.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("nomRegionRgRecordGroup :ouisdire"+e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkStskSkillTypeRecordGroup() {
		final String sql = getQuery("OUISDIRE_FIND_CGFKSTSKSKILLTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("cgfkStskSkillTypeRecordGroup :ouisdire"+e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkStskSubTypeRecordGroup(final String subType) {
		final String sql = getQuery("OUISDIRE_FIND_CGFKSTSKSUBTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("subType", subType), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("cgfkStskSubTypeRecordGroup :ouisdire"+e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkVmsSexCodeRecordGroup() {
		final String sql = getQuery("OUISDIRE_FIND_CGFKVMSSEXCODE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("cgfkVmsSexCodeRecordGroup :ouisdire"+e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkVmsAgencyLocationTypeRecordGroup() {
		final String sql = getQuery("OUISDIRE_FIND_CGFKVMSAGENCYLOCATIONTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("cgfkVmsAgencyLocationTypeRecordGroup :ouisdire"+e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> cgfkVmsAgyLocIdRecordGroup() {
		final String sql = getQuery("OUISDIRE_FIND_CGFKVMSAGYLOCID");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("cgfkVmsAgyLocIdRecordGroup :ouisdire"+e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkVmsCityRecordGroup() {
		final String sql = getQuery("OUISDIRE_FIND_CGFKVMSCITY");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("cgfkVmsCityRecordGroup :ouisdire"+e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkVmsScheduleTypeRecordGroup() {
		final String sql = getQuery("OUISDIRE_FIND_CGFKVMSSCHEDULETYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("cgfkVmsScheduleTypeRecordGroup :ouisdire"+e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkVmsPositionRecordGroup() {
		final String sql = getQuery("OUISDIRE_FIND_CGFKVMSPOSITION");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("cgfkVmsPositionRecordGroup :ouisdire"+e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkVmsRoleRecordGroup() {
		final String sql = getQuery("OUISDIRE_FIND_CGFKVMSROLE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("cgfkVmsRoleRecordGroup :ouisdire"+e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * vms1OnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<InternetAddresses> vms1OnCheckDeleteMaster(final InternetAddresses paramBean) {
		final String sql = getQuery("OUISDIRE_VMS1_ONCHECKDELETEMASTER");
		final RowMapper<InternetAddresses> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, InternetAddresses.class,
				iAddresMaping);
		return (ArrayList<InternetAddresses>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * vms1OnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public StaffSkills vms1OnCheckDeleteMaster(final StaffSkills paramBean) {
		final String sql = getQuery("OUISDIRE_VMS1_ONCHECKDELETEMASTER");
		final RowMapper<StaffSkills> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffSkills.class,
				stskMapping);	
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * vms1WhenNewRecordInstance
	 *
	 * @param params
	 *
	 */
	public Phones vms1WhenNewRecordInstance(final Phones paramBean) {
		final String sql = getQuery("OUISDIRE_VMS1_WHENNEWRECORDINSTANCE");
		final RowMapper<Phones> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Phones.class, phonesMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkVms1AgyLocIdF3
	 *
	 * @param params
	 *
	 */
	public List<AgencyLocations> cgfkchkVms1AgyLocIdF3(final AgencyLocations paramBean) {
		final String sql = getQuery("OUISDIRE_CGFKCHK_VMS1_AGY_LOC_ID_F3");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agyLocMaping);
		return (ArrayList<AgencyLocations>) namedParameterJdbcTemplate.query(sql,createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkVms1AgyLocIdF3c
	 *
	 * @param params
	 *
	 */
	public List<AgencyLocations> cgfkchkVms1AgyLocIdF3c(final AgencyLocations paramBean) {
		final String sql = getQuery("OUISDIRE_CGFKCHK_VMS1_AGY_LOC_ID_F3_C");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agyLocMaping);
		return (ArrayList<AgencyLocations>) namedParameterJdbcTemplate.query(sql,createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cguvchkStskPk
	 *
	 * @param params
	 *
	 */
	public StaffSkills cguvchkStskPk(final StaffSkills paramBean) {
		final String sql = getQuery("OUISDIRE_CGUVCHK_STSK_PK");
		final RowMapper<StaffSkills> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffSkills.class,
				stskMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * postQuery
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes postQuery(final ReferenceCodes paramBean) {
		final String sql = getQuery("OUISDIRE_POST_QUERY");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				reCodeMaping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	@Override
	public String getcityCode(final String agyLocId) {
		final String sql = getQuery("OUISDIRE_GET_CITYCODE");
		String returnValue = null;
		try {
			returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("agyLocId", agyLocId),
					String.class);
		} catch (Exception e) {
			return null;
		}
		return returnValue;
	}
}
