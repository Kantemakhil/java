package net.syscon.s4.inst.incidentsoic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.VOicIncidents;
import net.syscon.s4.im.incidentsoic.beans.IncidentStaffReport;
import net.syscon.s4.inst.incidentsoic.OidoicusRepository;

@Repository
public class OidoicusRepositoryImpl extends RepositoryBase implements OidoicusRepository {

	public static final String ENTERQUERY = "ENTER-QUERY";
	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidoicusRepositoryImpl.class.getName());
	
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> vOicIncidentsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("INCIDENT_TYPE_DESC",  new FieldMapper("incidentTypeDesc"))
			.put("INT_LOC_DESCRIPTION", new FieldMapper("intLocDescription"))
			.put("AGY_LOC_ID",          new FieldMapper("agyLocId"))
			.put("BOOKING_NO",          new FieldMapper("bookingNo"))
			.put("OIC_INCIDENT_ID",     new FieldMapper("oicIncidentId"))
			.put("INCIDENT_TYPE",       new FieldMapper("incidentType"))
			.put("PARTY_SEQ",           new FieldMapper("partySeq"))
			.put("INCIDENT_TIME",       new FieldMapper("incidentTime"))
			.put("INCIDENT_DETAILS",    new FieldMapper("incidentDetails"))
			.put("OFFENDERBOOKID",      new FieldMapper("offenderBookId"))
			.build();
	private final Map<String, FieldMapper> incidentStaffReportMapping = new ImmutableMap.Builder<String, FieldMapper>()
						.put("INCIDENT_REPORT_ID", new FieldMapper("incidentReportId"))
						.put("STAFF_ID", new FieldMapper("staffId"))
						.put("REPORT_DATE", new FieldMapper("reportDate"))
						.put("REPORT_TIME", new FieldMapper("reportTime"))
						.put("REPORT_TYPE", new FieldMapper("reportType"))
						.put("lOCK_FLAG", new FieldMapper("lockFlag"))
						.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
						.put("CREATE_USER_ID", new FieldMapper("createUserId"))
						.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
						.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
						.put("AGENCY_INCIDENT_ID", new FieldMapper("agencyIncidentId"))
						.put("PARTY_SEQ", new FieldMapper("partySeq"))
						.put("SEAL_FLAG", new FieldMapper("sealFlag"))
						.put("INCIDENT_DETAILS", new FieldMapper("incidentDetails"))
						.build();
	/**
	 * Creates new OidoicusRepositoryImpl class Object
	 */
	public OidoicusRepositoryImpl() {

	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param objSearchDao
	 * @return List<VOicIncidents>
	 */
	public List<VOicIncidents> vOicInciSearchVOicIncidents(final VOicIncidents objSearchDao){
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDOICUS_VOICINCI_FIND_V_OIC_INCIDENTS"), vOicIncidentsMapping)
				.build();
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params=new MapSqlParameterSource();
		sqlQuery.append(sql);

		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getOffenderBookId() != null) {
				sqlQuery.append("OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID" + " and ");
				params.addValue("OFFENDER_BOOK_ID", objSearchDao.getOffenderBookId());

			}
			if (objSearchDao.getReportedStaffId() != null) {
				sqlQuery.append("REPORTED_STAFF_ID = :REPORTED_STAFF_ID" + " and ");
				params.addValue("REPORTED_STAFF_ID", objSearchDao.getReportedStaffId());
			}
			if (objSearchDao.getBookingNo() != null) {
				sqlQuery.append("BOOKING_NO = :BOOKING_NO" + " and ");
				params.addValue("BOOKING_NO", objSearchDao.getBookingNo());
			}
			if (objSearchDao.getAgencyIncidentId() != null) {
				sqlQuery.append("AGENCY_INCIDENT_ID =:AGENCY_INCIDENT_ID" + " and ");
				params.addValue("AGENCY_INCIDENT_ID", objSearchDao.getAgencyIncidentId());
			}
			if (objSearchDao.getPartySeq() != null) {
				sqlQuery.append("PARTY_SEQ =:PARTY_SEQ" + " and ");
				params.addValue("PARTY_SEQ", objSearchDao.getPartySeq());
			}
			if (objSearchDao.getIncidentDate() != null) {
				sqlQuery.append("INCIDENT_DATE =:INCIDENT_DATE" + " and ");
				params.addValue("INCIDENT_DATE", objSearchDao.getIncidentDate());
			}
			if (objSearchDao.getIntLocDescription() != null) {
				sqlQuery.append("INT_LOC_DESCRIPTION =:INT_LOC_DESCRIPTION" + " and ");
				params.addValue("INT_LOC_DESCRIPTION", objSearchDao.getIntLocDescription());
			}
			if (objSearchDao.getIncidentTime() != null) {
				sqlQuery.append("INCIDENT_TIME =:INCIDENT_TIME" + " and ");
				params.addValue("INCIDENT_TIME", objSearchDao.getIncidentTime());
			}
			if (objSearchDao.getIncidentType() != null) {
				sqlQuery.append("INCIDENT_TYPE =:INCIDENT_TYPE" + " and ");
				params.addValue("INCIDENT_TYPE", objSearchDao.getIncidentType());
			}
			if (objSearchDao.getIncidentTypeDesc() != null) {
				sqlQuery.append("INCIDENT_TYPE_DESC =:INCIDENT_TYPE_DESC" + " and ");
				params.addValue("INCIDENT_TYPE_DESC", objSearchDao.getIncidentTypeDesc());
			}
			if (objSearchDao.getIncidentDetails() != null) {
				sqlQuery.append("INCIDENT_DETAILS =:INCIDENT_DETAILS" + " and ");
				params.addValue("INCIDENT_DETAILS", objSearchDao.getIncidentDetails());
			}
			if (objSearchDao.getReportDate() != null) {
				sqlQuery.append("REPORT_DATE =:REPORT_DATE" + " and ");
				params.addValue("REPORT_DATE", objSearchDao.getReportDate());
			}
			if (objSearchDao.getAgyLocId() != null) {
				sqlQuery.append("AGY_LOC_ID =:AGY_LOC_ID" + " and ");
				params.addValue("AGY_LOC_ID", objSearchDao.getAgyLocId());
			}
			if (objSearchDao.getOicIncidentId() != null) {
				sqlQuery.append("OIC_INCIDENT_ID =:OIC_INCIDENT_ID" + " and ");
				params.addValue("OIC_INCIDENT_ID", objSearchDao.getOicIncidentId());
			}
			if (objSearchDao.getOicChargeFlag() != null) {
				sqlQuery.append("OIC_CHARGE_FLAG =:OIC_CHARGE_FLAG" + " and ");
				params.addValue("OIC_CHARGE_FLAG", objSearchDao.getOicChargeFlag());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" order by  INCIDENT_DATE desc, INCIDENT_TIME desc ");
		final RowMapper<VOicIncidents> VOicIncidentsRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				VOicIncidents.class, vOicIncidentsMapping);
		final ArrayList<VOicIncidents> returnList = (ArrayList<VOicIncidents>) namedParameterJdbcTemplate
				.query(preparedSql, params, VOicIncidentsRowMapper);
		return returnList;
	}

	/**
	 * @param
	 */
	public int preInsert(){
		return 0;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgIncTypeRecordGroup(){
		final String sql = getQuery("OIDOICUS_FIND_RGINCTYPE");
		List<ReferenceCodes> returnList= new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		try {
			returnList= namedParameterJdbcTemplate.query(sql, createParams("MODE", ENTERQUERY), referenceCodesRowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return List<Object>
	 * @param offenderbookid
	 */
	public List<Object> offBkgOnCheckDeleteMastervOicInciCur(final String offenderbookid){
		final String sql = getQuery("OIDOICUS_OFF_BKG_ONCHECKDELETEMASTER_V_OIC_INCI_CUR");
		List<Object> returnList = new ArrayList<Object>();
		try {
			returnList = namedParameterJdbcTemplate.queryForList(sql, createParams("OFFENDERBOOKID", offenderbookid),
					Object.class);
		} catch (Exception e) {
			logger.error(e);;
		}
		return returnList;

	}
	
	/**
	 * This method is used to get Description of staffId
	 * 
	 * @return String
	 * @param staffId
	 */
	public String getDescriptionOfStaffId(final Integer staffId){
		final String sql = getQuery("OIDOICUS_FIND_STAFFID_DESCRIPTION");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("STAFFID", staffId),
					String.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;

	}

	@Override
	public List<String> findLocationList() {
		final String sql = getQuery("OIDOICUS_FIND_LOCATION_LIST");
		 return namedParameterJdbcTemplate.queryForList(sql,createParams(),String.class);
	}

	@Override
	public List<IncidentStaffReport> staffReportsData(final VOicIncidents objSearchDao) {
			List<IncidentStaffReport> returnList=null;
				String staffReportQuery = getQuery("OIDSTFRPPOP_SELECT_STAFF_REPORT_ALL_DATA");
					final String sql = queryBuilderFactory.getQueryBuilder(staffReportQuery,incidentStaffReportMapping).build();
				String preparedSql = null;
			final StringBuffer sqlQuery = new StringBuffer();
				sqlQuery.append(sql);
				preparedSql = sqlQuery.toString().trim();
				final RowMapper<IncidentStaffReport> offenderRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
						IncidentStaffReport.class, incidentStaffReportMapping);
				try {
					returnList = namedParameterJdbcTemplate.query(preparedSql, createParams("agencyIncidentId",objSearchDao.getAgencyIncidentId(),"offenderBookId",objSearchDao.getOffenderBookId()),offenderRowMapper);
				} catch (EmptyResultDataAccessException e) {
					logger.error(e);
				}
				
			return returnList;
		}


}
