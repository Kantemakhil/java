package net.syscon.s4.pkgs.omuavbed.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OmuavbedLivUnitsQuery;
import net.syscon.s4.pkgs.omuavbed.OmuavbedPkgRepository;

@Repository
public class OmuavbedPkgRepositoryImpl extends RepositoryBase implements OmuavbedPkgRepository {
	private static Logger logger = LogManager.getLogger(OmuavbedPkgRepositoryImpl.class.getName());

	@Autowired
	private DataSource ds;
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).build();

	private final Map<String, FieldMapper> offDetaieMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).build();

	private final Map<String, FieldMapper> offendersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("living_unit_id", new FieldMapper("offenderId"))
			.put("offender_book_id", new FieldMapper("offenderBookId")).put("ns_type", new FieldMapper("nameType"))
			.build();

	private final Map<String, FieldMapper> livUnitsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("NO_OF_AVAILABLE", new FieldMapper("noOfAvailable"))
			.put("UNIT_AT_CAPACITY", new FieldMapper("unitAtCapacity"))
			.put("PRISONER_CONFLICT", new FieldMapper("prisonerConflict"))
			.put("SECURITY_CONFLICT", new FieldMapper("securityConflict"))
			.put("CELL_SHARING_CONFLICT", new FieldMapper("cellSharingConflict")).build();

	@Override
	public Long offIdCursor(final Long offBookId) {
		final String sql = getQuery("GET_OFF_ID_CURSOR");
		Long offenderId = null;

		try {
			offenderId = namedParameterJdbcTemplate.queryForObject(sql, createParams("offBookId", offBookId),
					Long.class);
		} catch (Exception e) {
			logger.error("offIdCursor", e);
			offenderId = null;
		}
		return offenderId;
	}
	public static boolean tableExist(Connection conn, String tableName)  {
		boolean tExists = false;
		try {
	    try (ResultSet rs = conn.getMetaData().getTables(null, null, tableName, null)) {
	        while (rs.next()) { 
	            String tName = rs.getString("TABLE_NAME");
	            if (tName != null && tName.equals(tableName)) {
	                tExists = true;
	                break;
	            }
	        }
	    }
		}catch (Exception e) {
			logger.error(e);
		}
	    return tExists;
	}

	@Override
	public Integer emptySearchCur() {
		boolean table=false;
		try(Connection conn = ds.getConnection()) {
			table=tableExist(conn, "temp_living_unit_profiles");
		} catch (SQLException e1) {
			logger.error(e1);
		}
		if(!table) {
			this.createTable();
		}
		final String sql = getQuery("EMPTY_SEARCH_CUR");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (Exception e) {
			logger.error("emptySearchCur", e);
			count = 0;
		}
		return count;
	}

	public  String getTableName() {
		String table=null;
		try(Connection conn = ds.getConnection()) {
			DatabaseMetaData meta = conn.getMetaData();
			ResultSet res = meta.getTables( null, null, "temp_living_unit_profiles",  new String[] {"TABLE"}); 
			while (res.next()) {
				table= res.getString("TABLE_NAME");
			}
		}
		catch (Exception e) {
			logger.error("getTableName", e);
		}
		return table;
	}

	@Override
	public List<OmuavbedLivUnitsQuery> pRefcursor(final OmuavbedLivUnitsQuery agencInterLoc, String orderBy) {

		final String sql = "with recursive liv_units as ( select lu.internal_location_id living_unit_id, lu.parent_internal_location_id, lu.active_flag, lu.list_seq , lu.description description, lu.agy_loc_id, lu.unit_type, lu.capacity capacity, lu.operation_capacity, lu.no_of_occupant no_of_occupant, 0 AS level, cast(lu.list_seq AS VARCHAR(1000)) || '-' || cast(lu.description AS VARCHAR(1000)) || '-' || cast(lu.internal_location_id AS VARCHAR(1000)) AS order_sequence from agency_internal_locations lu where lu.agy_loc_id = :AGYLOCID AND lu.parent_internal_location_id IS null union all select lu.internal_location_id living_unit_id, lu.parent_internal_location_id, lu.active_flag, lu.list_seq , lu.description description, lu.agy_loc_id, lu.unit_type, lu.capacity capacity, lu.operation_capacity, lu.no_of_occupant no_of_occupant, level+1 AS level, CAST(order_sequence || '-' || cast(lu.list_seq AS VARCHAR(1000)) || '-' || cast(lu.description AS VARCHAR(1000)) || '-' || CAST(lu.internal_location_id AS VARCHAR(1000)) AS VARCHAR(1000)) AS order_sequence from agency_internal_locations lu join liv_units c on (lu.parent_internal_location_id = c.living_unit_id) )";

		List<OmuavbedLivUnitsQuery> returnList = new ArrayList<OmuavbedLivUnitsQuery>();
		String preparedSql = null;

		StringBuffer sqlQuery = new StringBuffer(sql);
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();



		inParameterSource.addValue("OFFENDERID", agencInterLoc.getpOffenderId());
		inParameterSource.addValue("OFFENDERBOOKID", agencInterLoc.getpOffenderBookId());
		inParameterSource.addValue("AGYLOCID", agencInterLoc.getpAgyLocId());
		inParameterSource.addValue("LIVINGUNITTYPE", agencInterLoc.getpLivingUnitType());
		inParameterSource.addValue("LIVINGUNITTYPE", agencInterLoc.getpLivingUnitType());
		inParameterSource.addValue("LEVEL1CODE", agencInterLoc.getpLevel1Code());
		inParameterSource.addValue("LEVEL2CODE", agencInterLoc.getpLevel2Code());
		inParameterSource.addValue("LEVEL3CODE", agencInterLoc.getpLevel3Code());
		inParameterSource.addValue("LEVEL4CODE", agencInterLoc.getpLevel4Code());

		if (agencInterLoc.getEmptySearchCount() == 0) {
			sqlQuery.append(",sup_level as ( select oa.review_sup_level_type sup_level_type, rank() over( order by oa.assessment_seq desc) ass_seq from offender_assessments oa join assessments a on a.assessment_id = oa.assessment_type_id where oa.offender_book_id = :OFFENDERBOOKID::bigint and oa.assess_status = 'A' and oa.evaluation_result_code = 'APP' and a.determine_sup_level_flag = 'Y' and a.caseload_type in ('INST', 'BOTH') ), non_assoc as ( select count(*) na_count from offender_na_details ona join offender_bookings ob on ob.root_offender_id = ona.ns_offender_id and ob.active_flag = 'Y' join agency_internal_locations ail2 on ail2.internal_location_id = ob.living_unit_id and ail2.agy_loc_id = :AGYLOCID where ona.offender_id = ( select root_offender_id from offenders where offender_id = :OFFENDERID::bigint) and date_trunc('day', clock_timestamp()) between ona.ns_effective_date and coalesce(ona.ns_expiry_date, TO_DATE('01/01/3000', 'DD/MM/YYYY'))) select living_unit_id, parent_internal_location_id, active_flag, level, order_sequence, list_seq , description, agy_loc_id, unit_type, capacity, no_of_occupant , capacity - no_of_occupant no_of_available, case when operation_capacity is not null and operation_capacity <= no_of_occupant then 'Y' when parent_internal_location_id is null then 'N' else substr(tag_int_loc_operation_flag (parent_internal_location_id), 1, 1) end unit_at_capacity , case when coalesce(:OFFENDERBOOKID::text, '') = '' then 'N' when not exists ( select 1 from living_unit_profiles where living_unit_id = lu.living_unit_id and int_loc_profile_type = 'SUP_LVL_TYPE' ) then 'N' when exists ( select 1 from living_unit_profiles where living_unit_id = lu.living_unit_id and int_loc_profile_type = 'SUP_LVL_TYPE' and int_loc_profile_code = s.sup_level_type ) then 'N' else 'Y' end as security_conflict, omuavbed_chk_cs_conflict(:OFFENDERBOOKID::bigint, living_unit_id, no_of_occupant) cell_sharing_conflict , case when coalesce(:OFFENDERID::text, '') = '' then 'N' else case when ( select na_count from non_assoc) > 0 then case when non_association_check_non_association(:OFFENDERID::bigint, living_unit_id)= 'Y' then 'N' else 'Y' end else 'N' end end prisoner_conflict from liv_units lu left join sup_level s on s.ass_seq = 1 where agy_loc_id = :AGYLOCID and not exists ( select 1 from agency_internal_locations ail2 where ail2.parent_internal_location_id = living_unit_id) and ( coalesce(:LIVINGUNITTYPE::text, '') = '' or unit_type = :LIVINGUNITTYPE ) and ( coalesce(:LEVEL1CODE::text, '') = '' or tag_int_loc_level_code(description, 1) = :LEVEL1CODE ) and ( coalesce(:LEVEL2CODE::text, '') = '' or tag_int_loc_level_code(description, 2) = :LEVEL2CODE ) and ( coalesce(:LEVEL3CODE::text, '') = '' or tag_int_loc_level_code(description, 3) = :LEVEL3CODE ) and ( coalesce(:LEVEL4CODE::text, '') = '' or tag_int_loc_level_code(description, 4) = :LEVEL4CODE ) and active_flag = 'Y' and capacity > no_of_occupant and (unit_type is not null and unit_type::text <> '') order by order_sequence");

			inParameterSource.addValue("AGYLOCID", agencInterLoc.getpAgyLocId());
		} else {
			sqlQuery=new StringBuffer();
			sqlQuery.append("with recursive cte as ( select lu.internal_location_id living_unit_id,lu.agy_loc_id,lu.unit_type,lu.active_flag, lu.description description, lu.capacity capacity, lu.no_of_occupant no_of_occupant, lu.capacity - lu.no_of_occupant no_of_available, substr(tag_int_loc_operation_flag(lu.internal_location_id), 1, 1) unit_at_capacity, case when coalesce(:OFFENDERID::text, '') = '' then 'N' else case when non_association_check_non_association(:OFFENDERID, lu.internal_location_id)= 'Y' then 'N' else 'Y' end end prisoner_conflict, case when coalesce(:OFFENDERBOOKID::text, '') = '' then 'N' else case when omuavbed_check_security(:OFFENDERBOOKID, lu.internal_location_id)= 'Y' then 'N' else 'Y' end end security_conflict, omuavbed_chk_cs_conflict(:OFFENDERBOOKID, lu.internal_location_id, lu.no_of_occupant) cell_sharing_conflict from agency_internal_locations lu where lu.internal_location_id in ( select ail.internal_location_id from agy_int_loc_profiles ailp join agency_internal_locations ail on ail.internal_location_id = ailp.internal_location_id join( select profile_type, profile_code, COUNT(*) over () cnt from temp_living_unit_profiles )tlup on tlup.profile_type = ailp.int_loc_profile_type and tlup.profile_code = ailp.int_loc_profile_code where ail.agy_loc_id = :AGYLOCID group by ail.internal_location_id, tlup.cnt having count(*) = tlup.cnt ) union all select lu.internal_location_id living_unit_id,lu.agy_loc_id,lu.unit_type,lu.active_flag, lu.description description, lu.capacity capacity, lu.no_of_occupant no_of_occupant, lu.capacity - lu.no_of_occupant no_of_available, substr(tag_int_loc_operation_flag(lu.internal_location_id), 1, 1) unit_at_capacity, case when coalesce(:OFFENDERID::text, '') = '' then 'N' else case when non_association_check_non_association(:OFFENDERID, lu.internal_location_id)= 'Y' then 'N' else 'Y' end end prisoner_conflict, case when coalesce(:OFFENDERBOOKID::text, '') = '' then 'N' else case when omuavbed_check_security(:OFFENDERBOOKID, lu.internal_location_id)= 'Y' then 'N' else 'Y' end end security_conflict, omuavbed_chk_cs_conflict(:OFFENDERBOOKID, lu.internal_location_id, lu.no_of_occupant) cell_sharing_conflict from agency_internal_locations lu join cte c on (c.living_unit_id = lu.parent_internal_location_id)) select * from cte where agy_loc_id = :AGYLOCID and ( select case when COUNT(*)= 0 then 'Y' else 'N' end lowest_level_flag from agency_internal_locations ail2 where ail2.parent_internal_location_id = living_unit_id ) = 'Y' and ( coalesce(:livingUnitType::text, '') = '' or unit_type = :LIVINGUNITTYPE ) and ( coalesce(:LEVEL1CODE::text, '') = '' or tag_int_loc_level_code(description, 1) = :LEVEL1CODE ) and ( coalesce(:LEVEL2CODE::text, '') = '' or tag_int_loc_level_code(description, 2) = :LEVEL2CODE ) and ( coalesce(:LEVEL3CODE::text, '') = '' or tag_int_loc_level_code(description, 3) = :LEVEL3CODE ) and ( coalesce(:LEVEL4CODE::text, '') = '' or tag_int_loc_level_code(description, 4) = :LEVEL4CODE ) and active_flag = 'Y' and capacity >no_of_occupant and OMUAVBED_get_profile_id(living_unit_id) <> living_unit_id and active_flag = 'Y' and capacity > no_of_occupant");
			inParameterSource.addValue("AGYLOCID", agencInterLoc.getpAgyLocId());
		}

		preparedSql = sqlQuery.toString().trim();

		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 3);
		}

		final RowMapper<OmuavbedLivUnitsQuery> agyIntlLocRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				OmuavbedLivUnitsQuery.class, mMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(preparedSql, inParameterSource, agyIntlLocRowMapper);
		} catch (Exception e) {
			logger.error("pRefcursor :", e);
		}
		return returnList;
	}


	public OmuavbedPkgRepositoryImpl() {

	}

	public int createTable()   {
		String createTableSQL=" CREATE GLOBAL TEMPORARY TABLE TEMP_LIVING_UNIT_PROFILES  ( PROFILE_TYPE VARCHAR(12) not null, PROFILE_CODE VARCHAR(12) not null, CREATE_DATETIME TIMESTAMP (9) DEFAULT current_timestamp not null, CREATE_USER_ID VARCHAR(32) DEFAULT current_user not null, MODIFY_DATETIME TIMESTAMP (9), MODIFY_USER_ID VARCHAR(32) ) ON COMMIT DELETE ROWS"; 
		return namedParameterJdbcTemplate.update(createTableSQL,createParams());
	}

	@Override
	public BigDecimal offIdSelectOperation(final BigDecimal offenderBookId) {
		final String sql = getQuery("OFF_ID_SELECT_OPERATION");
		BigDecimal offId = null;

		try {
			offId = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
					BigDecimal.class);

		} catch (Exception e) {
			logger.error("offIdSelectOperation", e);
			offId = null;
		}
		return offId;
	}

	@Override
	public LivingUnits getLivUnitInfo(final BigDecimal offenderBookId, final BigDecimal offenderId,
			final BigDecimal livingUnitId) {
		final String sql = getQuery("GET_LIV_UNIT_INFO");
		LivingUnits retBean = null;
		final RowMapper<LivingUnits> mRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				offDetaieMapping);

		try {
			retBean = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId,
					"offenderId", offenderId, "livingUnitId", livingUnitId), mRowMapper);
		} catch (Exception e) {
			logger.error("getLivUnitInfo :", e);
			retBean = null;
		}
		return retBean;
	}

	@Override
	public List<Offenders> getOccupants(final BigDecimal livingUnitId) {
		final String sql = getQuery("GET_OCCUPANTS");
		List<Offenders> returnList = new ArrayList<Offenders>();
		final RowMapper<Offenders> rowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, offendersMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("livingUnitId", livingUnitId), rowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("getOccupants :", e);
		}
		return returnList;

	}

	@Override
	public Integer reservedBedCur(final BigDecimal offId, final BigDecimal livingUnitId) {
		final String sql = getQuery("RESERVED_BED_CUR");
		Integer id = 0;
		try {
			id = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offId", offId, "livingUnitId", livingUnitId), Integer.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error("reservedBedCur :", e);
			id = 0;
		}
		return id;
	}

	@Override
	public BigDecimal getlvOffId(final BigDecimal offenderBookId) {
		final String sql = getQuery("GET_LV_OFF_ID");
		BigDecimal retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_offender_book_id", offenderBookId),
					BigDecimal.class);
		} catch (Exception e) {
			logger.error("getlvOffId", e);
			retVal = null;
		}
		return retVal;
	}

	@Override
	public Long getOffenderId(final Long offenderBookId) {
		final String sql = getQuery("GET_OFFENDERID_BY_PASSING_BOOKID");
		Long retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
					Long.class);
		} catch (Exception e) {
			logger.error("getOffenderId", e);
			retVal = null;
		}
		return retVal;
	}

	@Override
	public Offenders getOffDetails(final Long lvOffenderId) {
		final String sql = getQuery("GET_OFF_DETAILS");
		Offenders retBean = null;

		final RowMapper<Offenders> rowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, livUnitsMapping);
		try {
			retBean = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_offender_id", lvOffenderId),
					rowMapper);
		} catch (Exception e) {
			logger.error("getOffDetails", e);
		}
		return retBean;
	}
}