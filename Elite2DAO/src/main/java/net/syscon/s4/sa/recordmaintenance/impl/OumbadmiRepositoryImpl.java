package net.syscon.s4.sa.recordmaintenance.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.sa.admin.beans.VBookAdmin;
import net.syscon.s4.sa.recordmaintenance.OumbadmiRepository;

/**
 * Class OumbadmiRepositoryImpl
 * 
 */
@Repository
public class OumbadmiRepositoryImpl extends RepositoryBase implements OumbadmiRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumbadmiRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("BOOKING_STATUS", new FieldMapper("bookingStatus"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).build();
	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGENCY_LOCATION_TYPE", new FieldMapper("agencyLocationType"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("ABBREVIATION", new FieldMapper("abbreviation"))
			.build();
	private final Map<String, FieldMapper> vBookAdminMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code"))
			.put("MIDDLE_NAME", new FieldMapper("middleName"))
			.put("SUFFIX", new FieldMapper("suffix"))
			.put("BIRTH_DATE", new FieldMapper("birthDate"))
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("LAST_NAME", new FieldMapper("lastName"))
			.put("FIRST_NAME", new FieldMapper("firstName"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.build();
	private final Map<String, FieldMapper> offenderBookingsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).build();
	private final Map<String, FieldMapper> sysDualMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).build();

	/**
	 * Creates new OumbadmiRepositoryImpl class Object
	 */
	public OumbadmiRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VBookAdmin
	 *
	 * @return List<VBookAdmin>
	 *
	 */
	
	public List<VBookAdmin> vBookAdmExecuteQuery(final VBookAdmin objSearchDao) {
		final String sql = getQuery("OUMBADMI_VBOOKADM_FIND_V_BOOK_ADMIN");
		final StringBuffer pSql = new StringBuffer(sql);
		String preparedSql = "";
		final MapSqlParameterSource param = new MapSqlParameterSource();
		if (objSearchDao != null) {
			pSql.append(" WHERE ");
			if (objSearchDao.getOffenderIdDisplay() != null && !objSearchDao.getOffenderIdDisplay().isEmpty() && !objSearchDao.getOffenderIdDisplay().trim().equals("")) {
				pSql.append(" OFFENDER_ID_DISPLAY LIKE :offenderIdDisplay  AND ");
				param.addValue("offenderIdDisplay", objSearchDao.getOffenderIdDisplay().trim());
			}
			if (objSearchDao.getLastName() != null && !objSearchDao.getLastName().isEmpty() && !objSearchDao.getLastName().trim().equals("")) {
				pSql.append("regexp_replace(upper(LAST_NAME),'[- \\,''\"]','','g') LIKE regexp_replace(upper(:lastName),'[- \\,''\"]','','g')" + " AND ");
				
				param.addValue("lastName", objSearchDao.getLastName().trim());
			}
			if (objSearchDao.getFirstName() != null && !objSearchDao.getFirstName().isEmpty() && !objSearchDao.getFirstName().trim().equals("")) {
				pSql.append("regexp_replace(upper(FIRST_NAME),'[- \\,''\"]','','g') LIKE regexp_replace(upper(:firstName),'[- \\,''\"]','','g')" + " AND ");
				param.addValue("firstName", objSearchDao.getFirstName().trim());
			}
			
			if (objSearchDao.getBirthDate() != null) {
				pSql.append(" BIRTH_DATE = :birthDate AND ");
				param.addValue("birthDate", objSearchDao.getBirthDate());
			}
		}
		preparedSql = pSql.toString().trim();

		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 3);
		}
		final RowMapper<VBookAdmin> HoCodesRowMapper = Row2BeanRowMapper.makeMapping(sql, VBookAdmin.class, vBookAdminMapping);
		ArrayList<VBookAdmin> returnList = new ArrayList<>();
		try {
			returnList = (ArrayList<VBookAdmin>) namedParameterJdbcTemplate.query(preparedSql, param, HoCodesRowMapper);
		} catch (Exception e) {
			logger.error("hoCodesExecuteQuery", e);
		}
		return returnList;
	}
	
	
	
	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderBookings
	 *
	 * @return List<OffenderBookings>
	 *
	 */
	public List<OffenderBookings> offContactsExecuteQuery(OffenderBookings objSearchDao) {
		final String sql = getQuery("OUMBADMI_OFFCONTACTS_FIND_OFFENDER_BOOKINGS");
		final RowMapper<OffenderBookings> OffenderBookingsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBookings.class, offenderBookingsMapping);
		List<OffenderBookings> returnList = new ArrayList<OffenderBookings>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("ROOTOFFENDERID", objSearchDao.getRootOffenderId()), OffenderBookingsRowMapper);
		} catch (Exception e) {
			logger.error("", e);
		}
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderBookings
	 *            List<OffenderBookings>
	 */
	public Integer offContactsUpdateOffenderBookings(final List<OffenderBookings> lstOffenderBookings) {
		String sql = getQuery("OUMBADMI_OFFCONTACTS_UPDATE_OFFENDER_BOOKINGS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderBookings offenderBookings : lstOffenderBookings) {
			parameters.add(new BeanPropertySqlParameterSource(offenderBookings));
		}
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}
		catch (Exception e) {
			e.getMessage();
		}
		if (lstOffenderBookings.size() == returnArray.length) {
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
	public List<ReferenceCodes> cgfkOffContactsBookingStatRecordGroup() {
		final String sql = getQuery("OUMBADMI_FIND_CGFKOFFCONTACTSBOOKINGSTAT");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffContactsOffBkg
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfkchkOffContactsOffBkg(ReferenceCodes paramBean) {
		final String sql = getQuery("OUMBADMI_CGFKCHK_OFF_CONTACTS_OFF_BKG");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ArrayList<ReferenceCodes> returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffContactsOffB2
	 *
	 * @param params
	 *
	 */
	public List<AgencyLocations> cgfkchkOffContactsOffB2(AgencyLocations paramBean) {
		final String sql = getQuery("OUMBADMI_CGFKCHK_OFF_CONTACTS_OFF_B2");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		final ArrayList<AgencyLocations> returnList = (ArrayList<AgencyLocations>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	@Override
	public List<Object> cgwhenNewFormInstance(SysDual paramBean) {
		return null;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	
	public Integer offenderBookingsBookingStatus(final OffenderBookings paramBean){
		final String sql = getQuery("OUMBADMI_OFFENDER_BOOKINGS_BOOKING_STATUS");
		Integer returnList  = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId",paramBean.getOffenderId()), Integer.class);
		return returnList;
		
	}

}
