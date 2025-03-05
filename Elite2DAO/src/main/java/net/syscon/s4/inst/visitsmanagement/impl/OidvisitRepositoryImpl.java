package net.syscon.s4.inst.visitsmanagement.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.AgencyVisitTimes;
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.global.Omss40Repository;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCounts;
import net.syscon.s4.inst.visitsmanagement.OidvisitRepository;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderVisitVisitors;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisitVisitors;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisits;
import oracle.jdbc.OracleTypes;

/**
 * Class OidvisitRepositoryImpl
 */
@Repository
public class OidvisitRepositoryImpl extends RepositoryBase implements OidvisitRepository {

	@Autowired
	private Omss40Repository omss40Repository;

	/**
	 * Creates new OidvisitRepositoryImpl class Object
	 */
	public OidvisitRepositoryImpl() {
	}

	private final Map<String, FieldMapper> imagesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("IMAGE_ID", new FieldMapper("imageId")).put("OFFENDER_VISIT_ID", new FieldMapper("offenderVisitId"))
			.put("GROUP_LEADER_FLAG", new FieldMapper("groupLeaderFlag"))
			.put("IMAGE_OBJECT_TYPE", new FieldMapper("imageObjectType"))
			.put("IMAGE_OBJECT_ID", new FieldMapper("imageObjectId"))
			.put("IMAGE_THUMBNAIL", new FieldMapper("imageThumbnail"))
			.put("ORIENTATION_TYPE", new FieldMapper("orientationType")).build();
	private final Map<String, FieldMapper> vOffenderVisitsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_VISIT_ID", new FieldMapper("offenderVisitId"))
			.put("RAISED_INCIDENT_NUMBER", new FieldMapper("raisedIncidentNumber"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("END_TIME", new FieldMapper("endTime"))
			.put("START_TIME", new FieldMapper("startTime")).put("DESCRIPTION", new FieldMapper("description"))
			.put("agency_visit_config", new FieldMapper("visitConfigType"))
			.put("visitInternalLocationId", new FieldMapper("visitInternalLocationId")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("START_TIME", new FieldMapper("startTime")).put("DESCRIPTION", new FieldMapper("description"))
			.put("'HH24:MI'", new FieldMapper("'hh24:mi'")).put("DISTINCT", new FieldMapper("distinct"))
			.put("TO_CHAR", new FieldMapper("toChar")).put("END_TIME", new FieldMapper("endTime"))
			.put("WEEK_DAY", new FieldMapper("weekDay")).put("agyLocId", new FieldMapper("agyLocId"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).build();
	private final Map<String, FieldMapper> vOffenderVisitVisitorsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_VISIT_ID", new FieldMapper("offenderVisitId"))
			.put("GROUP_LEADER_FLAG", new FieldMapper("groupLeaderFlag"))
			.put("EVENT_STATUS", new FieldMapper("eventStatus")).build();
	private final Map<String, FieldMapper> offenderVisitVisitorsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("IMAGE_ID", new FieldMapper("imageId")).put("OFFENDER_VISIT_ID", new FieldMapper("offenderVisitId"))
			.put("GROUP_LEADER_FLAG", new FieldMapper("groupLeaderFlag"))
			.put("IMAGE_OBJECT_TYPE", new FieldMapper("imageObjectType"))
			.put("IMAGE_OBJECT_ID", new FieldMapper("imageObjectId"))
			.put("IMAGE_THUMBNAIL", new FieldMapper("imageThumbnail"))
			.put("ORIENTATION_TYPE", new FieldMapper("orientationType")).build();
	private final Map<String, FieldMapper> vagencyCountMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TOTAL_ACTUAL", new FieldMapper("totalActual")).put("OUT_TOTAL", new FieldMapper("outTotal"))
			.put("TOTAL_OTHER_OUT", new FieldMapper("totalOtherOut")).build();

	private final Map<String, FieldMapper> agencyVisitTimesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("START_TIME", new FieldMapper("startTime")).put("END_TIME", new FieldMapper("endTime"))
			.put("WEEK_DAY", new FieldMapper("weekDay")).put("AGY_LOC_ID", new FieldMapper("agyLocId")).build();

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidvisitRepositoryImpl.class.getName());

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VOffenderVisits
	 *
	 * @return List<VOffenderVisits>
	 *
	 * 
	 */
	@Override
	public List<VOffenderVisits> offVstExecuteQuery(final VOffenderVisits objSearchDao) {
		final String sql = getQuery("OIDVISIT_OFFVST_FIND_V_OFFENDER_VISITS");
		final RowMapper<VOffenderVisits> VOffenderVisitsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderVisits.class, vOffenderVisitsMapping);
		ArrayList<VOffenderVisits> returnList = new ArrayList<VOffenderVisits>();
		try {
			returnList = (ArrayList<VOffenderVisits>) namedParameterJdbcTemplate.query(sql,
					createParams("offenderBookId", objSearchDao.getOffenderBookId(),"agyLocId",objSearchDao.getAgyLocId()), VOffenderVisitsRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in offVstExecuteQuery " + e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstVOffenderVisits List<VOffenderVisits>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer offVstInsertVOffenderVisits(final List<VOffenderVisits> lstVOffenderVisits) {
		final String sql = getQuery("OIDVISIT_OFFVST_INSERT_V_OFFENDER_VISITS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VOffenderVisits vOffenderVisits : lstVOffenderVisits) {
			parameters.add(new BeanPropertySqlParameterSource(vOffenderVisits));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in offVstInsertVOffenderVisits  " + e);
		}
		if (lstVOffenderVisits.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	public Integer insertIntoOffenderVisits(final List<VOffenderVisits> lstVOffenderVisits) {
		final String sql = getQuery("OIDVISIT_OFFVST_INSERT_OFFENDER_VISITS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VOffenderVisits vOffenderVisits : lstVOffenderVisits) {
			parameters.add(new BeanPropertySqlParameterSource(vOffenderVisits));
		}
		try {
			
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in insertIntoOffenderVisits " + e);
		}
		if (lstVOffenderVisits.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVOffenderVisits List<VOffenderVisits>
	 *
	 * 
	 */
	public Integer offVstUpdateVOffenderVisits(final List<VOffenderVisits> lstVOffenderVisits) {
		final String sql = getQuery("OIDVISIT_OFFVST_UPDATE_OFFENDER_VISITS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VOffenderVisits vOffenderVisits : lstVOffenderVisits) {
			parameters.add(new BeanPropertySqlParameterSource(vOffenderVisits));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in offVstUpdateVOffenderVisits ", e);
		}
		if (lstVOffenderVisits.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VOffenderVisitVisitors
	 *
	 * @return List<VOffenderVisitVisitors>
	 *
	 * 
	 */
	@Override
	public List<VOffenderVisitVisitors> offVstPersExecuteQuery(final VOffenderVisitVisitors objSearchDao) {
		final String sql = getQuery("OIDVISIT_OFFVSTPERS_FIND_V_OFFENDER_VISIT_VISITORS");
		final RowMapper<VOffenderVisitVisitors> VOffenderVisitVisitorsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderVisitVisitors.class, vOffenderVisitVisitorsMapping);
		List<VOffenderVisitVisitors> returnList = new ArrayList<VOffenderVisitVisitors>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderVisitId",
					objSearchDao.getOffenderVisitId(), "offenderBookId", objSearchDao.getOffenderBookId()),
					VOffenderVisitVisitorsRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in offVstPersExecuteQuery " + e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstVOffenderVisitVisitors List<VOffenderVisitVisitors>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	@Override
	public Integer offVstPersInsertVOffenderVisitVisitors(
			final List<VOffenderVisitVisitors> lstVOffenderVisitVisitors) {
		final String sql = getQuery("OIDVISIT_OFFVSTPERS_INSERT_V_OFFENDER_VISIT_VISITORS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VOffenderVisitVisitors vOffenderVisits : lstVOffenderVisitVisitors) {
			parameters.add(new BeanPropertySqlParameterSource(vOffenderVisits));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in offVstPersInsertVOffenderVisitVisitors: ", e);
		}
		if (lstVOffenderVisitVisitors.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVOffenderVisitVisitors List<VOffenderVisitVisitors>
	 *
	 * 
	 */
	@Override
	public Integer offVstPersUpdateVOffenderVisitVisitors(
			final List<VOffenderVisitVisitors> lstVOffenderVisitVisitors) {
		final String sql = getQuery("OIDVISIT_OFFVSTPERS_UPDATE_V_OFFENDER_VISIT_VISITORS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VOffenderVisitVisitors vOffenderVisitVisitors : lstVOffenderVisitVisitors) {
			parameters.add(new BeanPropertySqlParameterSource(vOffenderVisitVisitors));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in offVstPersUpdateVOffenderVisitVisitors " + e);
		}
		if (lstVOffenderVisitVisitors.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstVOffenderVisitVisitors List<VOffenderVisitVisitors>
	 *
	 * 
	 */
	@Override
	public Integer offVstPersDeleteVOffenderVisitVisitors(
			final List<VOffenderVisitVisitors> lstVOffenderVisitVisitors) {
		final String sql = getQuery("OIDVISIT_OFFVSTPERS_DELETE_V_OFFENDER_VISIT_VISITORS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VOffenderVisitVisitors vOffenderVisitVisitors : lstVOffenderVisitVisitors) {
			parameters.add(new BeanPropertySqlParameterSource(vOffenderVisitVisitors));
		}
		try {
			String tableName = "OFFENDER_VISIT_VISITORS";
			String whereCondition = "OFFENDER_VISIT_VISITOR_ID = :offenderVisitVisitorId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in offVstPersDeleteVOffenderVisitVisitors " + e);
		}
		if (lstVOffenderVisitVisitors.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao Images
	 *
	 * @return List<Images>
	 *
	 * 
	 */
	@Override
	public List<Images> imagesVisitorsExecuteQuery(final Images objSearchDao) {
		final String sql = getQuery("OIDVISIT_IMAGES1_FIND_IMAGES");
		final RowMapper<Images> ImagesRowMapper = Row2BeanRowMapper.makeMapping(sql, Images.class, imagesMapping);
		List<Images> returnList = new ArrayList<Images>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("personId", objSearchDao.getImageObjectId()), ImagesRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in imagesVisitorsExecuteQuery " + e);
		}
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao Images
	 *
	 * @return List<Images>
	 *
	 * 
	 */
	@Override
	public List<Images> imagesVisitingOffExecuteQuery(final Images objSearchDao) {
		final String sql = getQuery("OIDVISIT_IMAGES_OFF_FIND_IMAGES");
		final RowMapper<Images> ImagesRowMapper = Row2BeanRowMapper.makeMapping(sql, Images.class, imagesMapping);
		List<Images> returnList = new ArrayList<Images>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("personId", objSearchDao.getImageObjectId()), ImagesRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in imagesVisitingOffExecuteQuery " + e);
		}
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderVisitVisitors
	 *
	 * @return List<OffenderVisitVisitors>
	 *
	 * 
	 */
	@Override
	public List<OffenderVisitVisitors> offVstOffExecuteQuery(final OffenderVisitVisitors objSearchDao) {
		final String sql = getQuery("OIDVISIT_OFFVSTOFF_FIND_OFFENDER_VISIT_VISITORS");
		final RowMapper<OffenderVisitVisitors> OffenderVisitVisitorsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderVisitVisitors.class, offenderVisitVisitorsMapping);
		List<OffenderVisitVisitors> returnList = new ArrayList<OffenderVisitVisitors>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderVisitId",
					objSearchDao.getOffenderVisitId(), "offenderBookId", objSearchDao.getOffenderBookId()),
					OffenderVisitVisitorsRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in offVstOffExecuteQuery  " + e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderVisitVisitors List<OffenderVisitVisitors>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	@Override
	public Integer offvstInsertOffenderVisitVisitors(final List<VOffenderVisits> lstOffenderVisitVisitors) {
		final String sql = getQuery("OIDVISIT_OFFVST_INSERT_OFFENDER_VISIT_VISITORS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VOffenderVisits vOffenderVisits : lstOffenderVisitVisitors) {
			parameters.add(new BeanPropertySqlParameterSource(vOffenderVisits));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in offvstInsertOffenderVisitVisitors " + e);
		}
		if (lstOffenderVisitVisitors.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public Integer offVstOffInsertOffenderVisitVisitors(final List<OffenderVisitVisitors> lstOffenderVisitVisitors) {
		final String sql = getQuery("OIDVISIT_OFFVSTOFF_INSERT_OFFENDER_VISIT_VISITORS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderVisitVisitors vOffenderVisits : lstOffenderVisitVisitors) {
			parameters.add(new BeanPropertySqlParameterSource(vOffenderVisits));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "  error in offVstOffInsertOffenderVisitVisitors " + e);
		}
		if (lstOffenderVisitVisitors.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderVisitVisitors List<OffenderVisitVisitors>
	 *
	 * 
	 */
	@Override
	public Integer offVstOffUpdateOffenderVisitVisitors(final List<OffenderVisitVisitors> lstOffenderVisitVisitors) {
		final String sql = getQuery("OIDVISIT_OFFVSTOFF_UPDATE_OFFENDER_VISIT_VISITORS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderVisitVisitors offenderVisitVisitors : lstOffenderVisitVisitors) {
			parameters.add(new BeanPropertySqlParameterSource(offenderVisitVisitors));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in offVstOffUpdateOffenderVisitVisitors " + e);
		}
		if (lstOffenderVisitVisitors.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderVisitVisitors List<OffenderVisitVisitors>
	 *
	 * 
	 */
	@Override
	public Integer offVstOffDeleteOffenderVisitVisitors(final List<OffenderVisitVisitors> lstOffenderVisitVisitors) {
		final String sql = getQuery("OIDVISIT_OFFVSTOFF_DELETE_OFFENDER_VISIT_VISITORS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderVisitVisitors offenderVisitVisitors : lstOffenderVisitVisitors) {
			parameters.add(new BeanPropertySqlParameterSource(offenderVisitVisitors));
		}
		try {
			String tableName = "OFFENDER_VISIT_VISITORS";
			String whereCondition = "OFFENDER_VISIT_VISITOR_ID = :offenderVisitVisitorId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in offVstOffUpdateOffenderVisitVisitors " + e);
		}
		if (lstOffenderVisitVisitors.size() == returnArray.length) {
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
	@Override
	public List<ReferenceCodes> rgVisitTypeRecordGroup() {
		final String sql = getQuery("OIDVISIT_FIND_RGVISITTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in rgVisitTypeRecordGroup " + e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	@Override
	public List<ReferenceCodes> rgMoveCancRsRecordGroup() {
		final String sql = getQuery("OIDVISIT_FIND_RGMOVECANCRS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + " error in rgMoveCancRsRecordGroup " + e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	@Override
	public List<VOffenderVisits> rgVisitTimeSlotsRecordGroup(final String visitAgyLocId, final String agyLocId,
			final String visitDate) {
		final String sql = getQuery("OIDVISIT_FIND_RGVISITTIMESLOTS");
		final RowMapper<VOffenderVisits> mRowMapper = Row2BeanRowMapper.makeMapping(sql, VOffenderVisits.class,
				mMapping);
		List<String> profileCode = new ArrayList<String>();
		profileCode.add("DISPLAY");
		List<ProfileCodes> profiles = omss40Repository.searchProfileCodes(profileCode);
		List<ProfileCodes> dateProfile = profiles.stream()
				.filter(profile -> profile.getProfileCode().equalsIgnoreCase("DATE")).collect(Collectors.toList());
		String dateFormat = dateProfile.get(0).getProfileValue();
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("visitAgyLocId", visitAgyLocId, "agyLocId",
					agyLocId, "visitDate", visitDate, "dateFormat", dateFormat), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + " error in rgVisitTimeSlotsRecordGroup " + e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	@Override
	public List<ReferenceCodes> rgVisCompleteRecordGroup() {
		final String sql = getQuery("OIDVISIT_FIND_RGVISCOMPLETE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + " error in rgVisCompleteRecordGroup " + e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query doVisitCal
	 * 
	 */
	@Override
	public VOffenderVisits doVisitCal(final String hAgyLocId, final BigDecimal hOffenderBookId, final String supLevel,
			final String visitType, final Date visitDate) {
		final VOffenderVisits bean = new VOffenderVisits();
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_AGY_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_BKG_ID", OracleTypes.NUMBER), new SqlParameter("P_SEC_LVL", OracleTypes.VARCHAR),
				new SqlParameter("P_VIS_TP", OracleTypes.VARCHAR), new SqlParameter("P_VIS_DT", OracleTypes.DATE),
				new SqlOutParameter("P_CYC_TP", OracleTypes.VARCHAR),
				new SqlOutParameter("P_CYC_END", OracleTypes.DATE),
				new SqlOutParameter("P_TREM_VIS", OracleTypes.VARCHAR),
				new SqlOutParameter("P_TREM_HRS", OracleTypes.VARCHAR),
				new SqlOutParameter("P_TYPE_VIS", OracleTypes.VARCHAR),
				new SqlOutParameter("P_TYPE_HRS", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_VISITS").withProcedureName("VISIT_CALC").declareParameters(sqlParameters);
		inParamMap.put("P_AGY_ID", hAgyLocId);
		inParamMap.put("P_BKG_ID", hOffenderBookId);
		inParamMap.put("P_SEC_LVL", supLevel);
		inParamMap.put("P_VIS_TP", visitType);
		inParamMap.put("P_VIS_DT", visitDate);
		inParamMap.put("P_CYC_TP", "");
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			bean.setTotalRemainingVisits(
					returnObject.get("P_TREM_VIS") != null ? Long.valueOf(returnObject.get("P_TREM_VIS").toString())
							: null);
			bean.setRemainingVisitsType(
					returnObject.get("P_TYPE_VIS") != null ? Long.valueOf(returnObject.get("P_TYPE_VIS").toString())
							: null);
			bean.setTotalRemainingTime(
					returnObject.get("P_TREM_HRS") != null ? new BigDecimal(returnObject.get("P_TREM_HRS").toString())
							: null);
			bean.setRemainingTimeType(
					returnObject.get("P_TYPE_HRS") != null ? (new BigDecimal(returnObject.get("P_TYPE_HRS").toString()))
							: null);
			bean.setCycleEnds((Date) returnObject.get("P_CYC_END"));
		} catch (Exception e) {
		}
		return bean;
	}

	/**
	 * Used to capture results from select query getSupLevel
	 * 
	 */
	@Override
	public String getSupLevel(final BigDecimal offBookId) {
		final String value;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] { new SqlInOutParameter("RETURN_VALUE", OracleTypes.VARCHAR),
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.INTEGER) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("PIMS_WEIGHT").withFunctionName("GET_SUP_LEVEL").declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", offBookId);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		value = simpleJDBCCall.executeFunction(String.class, inParameter);
		return value;
	}

	/**
	 * Used to capture results from select query classPending
	 * 
	 */
	@Override
	public String classPending() {
		final String sql = getQuery("OIDVISIT_CLASSPENDING");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in classPending " + e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query populateVisitorDetails
	 * 
	 */
	@Override
	public VOffenderVisitVisitors populateVisitorDetails(final BigDecimal hOffenderBookId, final BigDecimal personId,
			final Date visitDate) {
		final VOffenderVisitVisitors bean = new VOffenderVisitVisitors();
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_PERSON_ID", OracleTypes.NUMBER),
				new SqlParameter("P_OFFENDER_CONTACT_PERSON_ID", OracleTypes.NUMBER),
				new SqlParameter("P_VISIT_DATE", OracleTypes.DATE) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_VISITS").withProcedureName("POPULATE_VISITOR_DETAILS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", hOffenderBookId);
		inParamMap.put("P_PERSON_ID", personId);
		inParamMap.put("P_OFFENDER_CONTACT_PERSON_ID", 0);
		inParamMap.put("P_VISIT_DATE", visitDate);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			bean.setLastName((String) returnObject.get("P_LAST_NAME"));
			bean.setFirstName((String) returnObject.get("P_FIRST_NAME"));
			bean.setContactType((String) returnObject.get("P_CONTACT_TYPE"));
			bean.setRelationshipType((String) returnObject.get("P_RELATIONSHIP_TYPE"));
			bean.setRestriction((String) returnObject.get("P_RESTRICTION"));
			bean.setGlobalRestriction((String) returnObject.get("P_GL_RESTRICTION"));
			bean.setAge((BigDecimal) returnObject.get("P_AGE"));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in populateVisitorDetails " + e);
		}
		return bean;
	}

	/**
	 * Used to capture results from select query getRootOffenderId
	 * 
	 */
	@Override
	public BigDecimal getRootOffenderId(final BigDecimal offBookId) {
		Integer value1;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] { new SqlInOutParameter("RETURN_VALUE", OracleTypes.INTEGER),
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.INTEGER) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_VISITS").withFunctionName("GET_ROOT_OFFENDER_ID_FROM_BOOK")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", offBookId);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		value1 = simpleJDBCCall.executeFunction(Integer.class, inParameter);
		return BigDecimal.valueOf(value1);
	}

	/**
	 * Used to capture results from select query populateOffenderDetails
	 * 
	 */
	@Override
	public OffenderVisitVisitors populateOffenderDetails(final BigDecimal hOffenderBookId,
			final BigDecimal rootOffenderId, final Date visitDate) {
		final OffenderVisitVisitors bean = new OffenderVisitVisitors();
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_CONTACT_OFFENDER_ROOT_ID", OracleTypes.NUMBER),
				new SqlParameter("P_VISIT_DATE", OracleTypes.DATE) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_VISITS").withProcedureName("POPULATE_OFFENDER_DETAILS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", hOffenderBookId);
		inParamMap.put("P_CONTACT_OFFENDER_ROOT_ID", rootOffenderId);
		inParamMap.put("P_VISIT_DATE", visitDate);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			bean.setLastName((String) returnObject.get("P_LAST_NAME"));
			bean.setFirstName((String) returnObject.get("P_FIRST_NAME"));
			bean.setContactType((String) returnObject.get("P_CONTACT_TYPE"));
			bean.setRelationshipType((String) returnObject.get("P_RELATIONSHIP_TYPE"));
			bean.setRestriction((String) returnObject.get("P_RESTRICTION_TYPE"));
			bean.setAgyLocId((String) returnObject.get("P_LOCATION"));
			bean.setOffenderIdDisplay((String) returnObject.get("P_OFFENDER_ID_DISPLAY"));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in populateOffenderDetails " + e);
		}
		return bean;
	}

	/**
	 * Used to capture results from select query getNxtOffVisitId
	 * 
	 */
	@Override
	public BigDecimal getNxtOffVisitId() {
		final Integer value;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] { new SqlInOutParameter("RETURN_VALUE", OracleTypes.INTEGER) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_VISITS").withFunctionName("GET_NEXT_OFF_VISIT_ID")
				.declareParameters(sqlParameters);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		value = simpleJDBCCall.executeFunction(Integer.class, inParameter);
		return BigDecimal.valueOf(value);
	}

	/**
	 * Used to capture results from select query getEventId
	 * 
	 */
	@Override
	public BigDecimal getEventId() {
		final Integer value;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] { new SqlInOutParameter("RETURN_VALUE", OracleTypes.INTEGER) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_VISITS").withFunctionName("GET_EVENT_ID").declareParameters(sqlParameters);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		value = simpleJDBCCall.executeFunction(Integer.class, inParameter);
		return BigDecimal.valueOf(value);
	}

	/**
	 * Used to capture results from select query getTimeSlotDetails
	 * 
	 */
	@Override
	public VOffenderVisits getTimeSlotDetails(final Integer agencyVisitSlotId) {
		VOffenderVisits bean = new VOffenderVisits();
		final String sql = getQuery("GET_TIME_SLOT_DETAILS");
		final RowMapper<VOffenderVisits> vOffeVisRowMapper = Row2BeanRowMapper.makeMapping(sql, VOffenderVisits.class,
				vOffenderVisitsMapping);
		try {
			bean = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_AGENCY_VISIT_SLOT_ID", agencyVisitSlotId), vOffeVisRowMapper);
		} catch (Exception e) {

			logger.error(this.getClass().getName() + " error in getTimeSlotDetails " + e);
		}
		return bean;
	}

	/**
	 * Used to capture results from select query insertOffenderVisitVisitor
	 * 
	 */
	@Override
	public Integer insertOffenderVisitVisitor(final VOffenderVisitVisitors bean) {
		Integer returnValue = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_VISIT_ID", OracleTypes.NUMBER),
				new SqlParameter("P_PERSON_ID", OracleTypes.NUMBER),
				new SqlParameter("P_GROUP_LEADER_FLAG", OracleTypes.VARCHAR),
				new SqlParameter("P_OFFENDER_VISIT_VISITOR_ID", OracleTypes.NUMBER),
				new SqlParameter("P_COMMENT_TEXT", OracleTypes.VARCHAR),
				new SqlParameter("P_EVENT_OUTCOME", OracleTypes.VARCHAR),
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_VISITS").withProcedureName("INSERT_OFFENDER_VISIT_VISITOR")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_VISIT_ID", bean.getOffenderVisitId());
		inParamMap.put("P_PERSON_ID", bean.getPersonId());
		inParamMap.put("P_GROUP_LEADER_FLAG", bean.getGroupLeaderFlag());
		inParamMap.put("P_OFFENDER_VISIT_VISITOR_ID", bean.getOffenderVisitVisitorId());
		inParamMap.put("P_COMMENT_TEXT", bean.getCommentText());
		inParamMap.put("P_EVENT_OUTCOME", bean.getEventOutcome());
		inParamMap.put("P_OFFENDER_BOOK_ID", bean.getOffenderBookId());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.execute(inParameter);
			returnValue = 1;
		} catch (Exception e) {
			returnValue = 0;
		}
		return returnValue;
	}

	/**
	 * Used to capture results from select query getOffenderBookId
	 * 
	 */
	@Override
	public BigDecimal getOffenderBookId(final String offenderIdDisplay, String userName) {
		final String sql = getQuery("GETOFFENDERBOOKID_FROM_OFFENDERIDDISPLAY");
		Long returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderIdDisplay", offenderIdDisplay, "userId", userName), Long.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in getOffenderBookId " + e);
		}
		return returnObj!=null?BigDecimal.valueOf(returnObj):null;
	}

	/**
	 * Used to capture results from select query offenderVisitLimitForVisitType
	 * 
	 */
	public String offenderVisitLimitForVisitType(final String agyLocId, final String supLevel, final String visitType) {
		final String sql = getQuery("OIDVISIT_VISITCYCLELIMITS_WITH_VISITTYPE");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("agyLocId", agyLocId, "supLevel", supLevel, "visitType", visitType), String.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + " error in offenderVisitLimitForVisitType  " + e);
			returnList = null;
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query offenderVisitLimit
	 * 
	 */
	@Override
	public String offenderVisitLimit(final String agyLocId, final String supLevel) {
		final String sql = getQuery("OIDVISIT_VISITCYCLELIMITS");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("agyLocId", agyLocId, "supLevel", supLevel), String.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + " error in offenderVisitLimit  " + e);
			returnList = null;
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query duplicateVisit
	 * 
	 */
	@Override
	public String duplicateVisit(final VOffenderVisits searchRecord) {
		final String sql = getQuery("OIDVISIT_DUPLICATE_VISIT");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_DATE", searchRecord.getVisitDate(), "P_OFFENDER_BOOK_ID",
							searchRecord.getOffenderBookId(), "P_START_TIME", searchRecord.getStartTime(), "P_END_TIME",
							searchRecord.getEndTime()),
					String.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + " error in duplicateVisit  " + e);
			returnList = null;
		}
		return returnList;

	}

	/**
	 * Used to capture results from select query duplicateVisitQueryTwo
	 * 
	 */
	@Override
	public String duplicateVisitQueryTwo(final VOffenderVisits searchRecord) {
		final String sql = getQuery("OIDVISIT_DUPLICATE_VISIT_QUERY_TWO");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_DATE", searchRecord.getVisitDate(), "P_OFFENDER_BOOK_ID",
							searchRecord.getOffenderBookId(), "P_START_TIME", searchRecord.getStartTime(), "P_END_TIME",
							searchRecord.getEndTime(), "P_OFFENDER_VISIT_ID", searchRecord.getOffenderVisitId()),
					String.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + " error in duplicateVisitQueryTwo  " + e);
			returnList = null;
		}
		return returnList;

	}

	/**
	 * Used to capture results from select query isAuthorisedPerson
	 * 
	 */
	@Override
	public Integer isAuthorisedPerson(final Integer personId, final Integer offenderBookId) {
		final String sql = getQuery("OIDVISIT_GET_AUTHORISED_PERSON");
		Integer returnList = 0;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDER_BOOK_ID", offenderBookId, "PERSON_ID", personId), Integer.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + " error in isAuthorisedPerson  " + e);
			returnList = 0;
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query getRootOffenderIdFromBook
	 * 
	 */
	@Override
	public BigDecimal getRootOffenderIdFromBook(final Integer offenderBookId) {
		final String sql = getQuery("OIDVIST_GET_ROOT_OFFENDER_ID");
		BigDecimal returnList = new BigDecimal(0);
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDER_BOOK_ID", offenderBookId), BigDecimal.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + " error in getRootOffenderIdFromBook  " + e);
			returnList = new BigDecimal(0);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query isAuthorisedOffender
	 * 
	 */
	@Override
	public Integer isAuthorisedOffender(final BigDecimal rootOffId, final Integer offenderBookId) {
		final String sql = getQuery("OIDVISIT_GET_AUTHORISED_OFFENDER");
		Integer returnList = 0;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("ROOT_OFFENDER_BOOK_ID", offenderBookId, "CONTACT_ROOT_OFFENDER_ID", rootOffId),
					Integer.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + " error in isAuthorisedOffender  " + e);
			returnList = 0;
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query duplicateVisitorPerson
	 * 
	 */
	@Override
	public Integer duplicateVisitorPerson(final VOffenderVisitVisitors searchBean) {
		final String sql = getQuery("OIDVISIT_DUPLICATE_VISITOR_PERSON");
		Integer returnList = 0;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_person_id",
					searchBean.getPersonId(), "OFFENDERVISITID", searchBean.getOffenderVisitId()), Integer.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + " error in duplicateVisitorPerson  " + e);

			returnList = 0;
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query duplicateVisitorPerson
	 * 
	 */
	@Override
	public Integer duplicateVisitorOffender(final OffenderVisitVisitors searchBean) {
		final String sql = getQuery("OIDVISIT_DUPLICATE_VISITOR_OFFENDER");
		Integer returnList = 0;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_offender_book_id",
					searchBean.getOffenderBookId(), "p_offender_visit_id", searchBean.getOffenderVisitId()),
					Integer.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + " error in duplicateVisitorOffender  " + e);
			returnList = 0;
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query overlapVisit
	 * 
	 */
	@Override
	public BigDecimal overlapVisit(final VOffenderVisitVisitors searchBean) {
		final String sql = getQuery("OIDVISIT_OVERLAPVISIT_PERSONID");
		BigDecimal returnList = new BigDecimal(0);
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_person_id", searchBean.getPersonId(), "p_offender_visit_id",
							searchBean.getOffenderVisitId(), "p_date", searchBean.getVisitDate(), "p_start_time",
							searchBean.getStartTime(), "p_end_time", searchBean.getEndTime()),
					BigDecimal.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in overlapVisit  " + e);
			returnList = null;
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query overlapVisitBookId
	 * 
	 */
	public BigDecimal overlapVisitBookId(final OffenderVisitVisitors searchBean) {
		final String sql = getQuery("OIDVISIT_OVERLAPVISIT_BOOKID");
		BigDecimal returnList = new BigDecimal(0);
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_offender_book_id", searchBean.getOffenderBookId(), "p_offender_visit_id",
							searchBean.getOffenderVisitId(), "p_date", searchBean.getVisitDate(), "p_start_time",
							searchBean.getStartTime(), "p_end_time", searchBean.getEndTime()),
					BigDecimal.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in overlapVisitBookId  " + e);
			returnList = null;
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query totalBooked
	 * 
	 */
	@Override
	public Integer totalBooked(final VOffenderVisits searchBean) {
		final String sql = getQuery("OIDVISIT_TOTALBOOKED_VISITOR");
		Integer returnList = 0;
		try {
			returnList = namedParameterJdbcTemplate
					.queryForObject(sql,
							createParams("p_offender_book_id", searchBean.getOffenderBookId(), "p_offender_visit_id",
									searchBean.getOffenderVisitId(), "p_visit_date", searchBean.getVisitDate()),
							Integer.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + " error in totalBooked  " + e);
			returnList = 0;
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query findMaxVisitorType
	 * 
	 */
	@Override
	public Integer findMaxVisitorType(final VOffenderVisits searchBean) {
		final String sql = getQuery("OIDVISIT_MAX_VISITORS_TYPE");
		Integer returnList = 0;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_visit_type", searchBean.getVisitType(), "p_sup_lvl", searchBean.getSupLevel(),
							"p_agy_loc_id", searchBean.getAgyLocId()),
					Integer.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in findMaxVisitorType  " + e);
			returnList = null;
		}
		return returnList;
	}

	@Override
	public Integer cancelVisitors(final VOffenderVisits bean) {
		final Integer returnValue = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_VISIT_ID", OracleTypes.NUMBER),
				new SqlParameter("P_OUTCOME_REASON_CODE", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_VISITS").withProcedureName("CANCEL_VISITORS").declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_VISIT_ID", bean.getOffenderVisitId());
		inParamMap.put("P_OUTCOME_REASON_CODE", bean.getOutcomeReasonCode());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.execute(inParameter);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in cancelVisitors  " + e);
		}
		return returnValue;
	}

	@Override
	public Integer completeVisitors(final VOffenderVisits bean) {
		final Integer returnValue = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_VISIT_ID", OracleTypes.NUMBER),
				new SqlParameter("P_VISIT_STATUS", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_VISITS").withProcedureName("COMPLETE_VISITORS").declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_VISIT_ID", bean.getOffenderVisitId());
		inParamMap.put("P_VISIT_STATUS", "COMP");
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.execute(inParameter);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in completeVisitors  " + e);
		}
		return returnValue;
	}

	@Override
	public Integer findAdultAge() {
		final String sql = getQuery("OIDVISIT_FIND_ADULTAGE");
		Integer returnList = 0;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + " error in findAdultAge  " + e);
			returnList = null;
		}
		return returnList;
	}

	@Override
	public AgencyCounts findCSlots(final BigDecimal agencyVisitSlotId) {
		AgencyCounts bean = new AgencyCounts();
		final String sql = getQuery("OIDVISIT_FIND_C_SLOT");
		final RowMapper<AgencyCounts> VOffenderVisitsRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyCounts.class,
				vagencyCountMapping);
		try {
			bean = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_agency_visit_slot_id", agencyVisitSlotId), VOffenderVisitsRowMapper);
		} catch (EmptyResultDataAccessException e) {
			bean = new AgencyCounts();
			logger.error(this.getClass().getName() + " error in findCSlots  " + e);
		}
		return bean;
	}

	@Override
	public AgencyCounts findCVis(final VOffenderVisits searchBean) {
		AgencyCounts bean = new AgencyCounts();
		final String sql = getQuery("OIDVISIT_FIND_C_VIS");
		final RowMapper<AgencyCounts> VOffenderVisitsRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyCounts.class,
				vagencyCountMapping);
		try {
			bean = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_offender_visit_id", searchBean.getOffenderVisitId(), "p_visit_date",
							searchBean.getVisitDate(), "p_offender_book_id", searchBean.getOffenderBookId(),
							"p_adult_age", searchBean.getAdultAge()),
					VOffenderVisitsRowMapper);
		} catch (EmptyResultDataAccessException e) {
			bean = new AgencyCounts();
			logger.error(this.getClass().getName() + " error in findCVis  " + e);
		}
		return bean;
	}

	@Override
	public AgencyCounts findCAll(final VOffenderVisits searchBean) {
		AgencyCounts bean = new AgencyCounts();
		final String sql = getQuery("OIDVISIT_FIND_C_ALL");
		final RowMapper<AgencyCounts> vOffVisRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyCounts.class,
				vagencyCountMapping);
		try {
			bean = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_offender_visit_id", searchBean.getOffenderVisitId(), "p_visit_date",
							searchBean.getVisitDate(), "p_offender_book_id", searchBean.getOffenderBookId(),
							"p_adult_age", searchBean.getAdultAge(), "p_agency_visit_slot_id",
							searchBean.getAgencyVisitSlotId()),
					vOffVisRowMapper);
		} catch (EmptyResultDataAccessException e) {
			bean = new AgencyCounts();
			logger.error(this.getClass().getName() + " error in findCAll  " + e);
		}
		return bean;
	}

	@Override
	public Integer getOffenderRestrictions(final VOffenderVisits searchBean) {
		final String sql = getQuery("OIDVISIT_GET_OFFENDER_RESTRICTIONS");
		Integer returnList = 0;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_offender_book_id",
					searchBean.getOffenderBookId(), "p_visit_date", searchBean.getVisitDate()), Integer.class);
		} catch (Exception e) {
			returnList = 0;
			logger.error(this.getClass().getName() + " error in getOffenderRestrictions  " + e);
		}
		return returnList;
	}

	@Override
	public BigDecimal getOffenderId(final String offenderIdDisplay) {
		final String sql = getQuery("OIDVISIT_GET_OFFENDER_ID");
		BigDecimal returnList = new BigDecimal(0);
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("pOffenderIdDisplay", offenderIdDisplay), BigDecimal.class);
		} catch (Exception e) {
			returnList = new BigDecimal(0);
			logger.error(this.getClass().getName() + " error in getOffenderId  " + e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	@Override
	public VOffenderVisits getOnVisitMaster(final BigDecimal offenderVisitId, final BigDecimal offenderBookId) {
		final String sql = getQuery("OIDVISIT_OFF_BKG_ONCHECKVISITMASTER_PERS");
		final RowMapper<VOffenderVisits> mRowMapper = Row2BeanRowMapper.makeMapping(sql, VOffenderVisits.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDERVISITID", offenderVisitId, "OFFENDERBOOKID", offenderBookId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + " error in getOnVisitMaster  " + e);
			return new VOffenderVisits();
		}
	}

	@Override
	public Integer offVstPersMaster(final BigDecimal offenderVisitId, final BigDecimal offenderBookId) {
		final String sql = getQuery("OIDVISIT_OFF_BKG_ONCHECK_PER_VISITOR_MASTER");
		Integer returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderVisitId", offenderVisitId, "offenderBookId", offenderBookId), Integer.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in offVstPersMaster " + e);
		}
		return returnList;
	}

	@Override
	public Integer offVstOffMaster(final BigDecimal offenderVisitId) {
		final String sql = getQuery("OIDVISIT_OFF_BKG_ONCHECK_OFF_VISITOR_MASTER");
		Integer returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderVisitId", offenderVisitId), Integer.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in offVstPersMaster " + e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	@Override
	public VOffenderVisits getOnVisitMasterFromVisitingOffenders(final BigDecimal offenderVisitId,
			final BigDecimal offenderBookId) {
		final String sql = getQuery("OIDVISIT_OFF_BKG_ONCHECKVISITMASTER");
		final RowMapper<VOffenderVisits> mRowMapper = Row2BeanRowMapper.makeMapping(sql, VOffenderVisits.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDERVISITID", offenderVisitId, "offenderBookId", offenderBookId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + " error in getOnVisitMasterFromVisitingOffenders " + e);
			return new VOffenderVisits();
		}
	}

	@Override
	public Integer getVisitorRestrictions(final BigDecimal personId, final BigDecimal offenderBookId,
			final Date visitDate) {
		final String sql = getQuery("OIDVISIT_GET_VISITORS_RESTRICTIONS");
		Integer returnList = 0;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_offender_book_id",
					offenderBookId, "p_visit_date", visitDate, "p_person_id", personId), Integer.class);
		} catch (Exception e) {
			returnList = 0;
			logger.error(this.getClass().getName() + " error in getVisitorRestrictions " + e);
		}
		return returnList;
	}

	@Override
	public BigDecimal overlapVisitBookId(final BigDecimal offenderBookId, final BigDecimal offenderVisitId,
			final Date visitDate, final Date startTime, final Date endTime) {
		final String sql = getQuery("OIDVISIT_OVERLAPVISIT_BOOKID");
		BigDecimal returnList = new BigDecimal(0);
		try {
			returnList = namedParameterJdbcTemplate
					.queryForObject(sql,
							createParams("p_offender_book_id", offenderBookId, "p_offender_visit_id", offenderVisitId,
									"p_date", visitDate, "p_start_time", startTime, "p_end_time", endTime),
							BigDecimal.class);
		} catch (Exception e) {
			returnList = null;
			logger.error(this.getClass().getName() + " error in overlapVisitBookId " + e);
		}
		return returnList;
	}

	@Override
	public BigDecimal overlapVisitPerId(final Integer personId, final BigDecimal offenderVisitId, final Date visitDate,
			final Date startTime, final Date endTime) {
		final String sql = getQuery("OIDVISIT_OVERLAPVISIT_PERSON_ID");
		BigDecimal returnList = new BigDecimal(0);
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_person_id", personId, "p_offender_visit_id", offenderVisitId, "p_date", visitDate,
							"p_start_time", startTime, "p_end_time", endTime),
					BigDecimal.class);
		} catch (Exception e) {
			returnList = null;
			logger.error(this.getClass().getName() + " error in overlapVisitPerId " + e);
		}
		return returnList;
	}

	@Override
	public String getOverLapVisitor(final Integer personId, final BigDecimal offenderVisitId, final Date visitDate,
			final Date startTime, final Date endTime, final BigDecimal pOffenderBookId) {
		String value;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] { new SqlInOutParameter("P_OVERLAP_DETAILS", OracleTypes.VARCHAR),
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.INTEGER) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_VISITS").withFunctionName("OVERLAP_VISIT").declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_VISIT_ID", offenderVisitId);
		inParamMap.put("P_OFFENDER_BOOK_ID", pOffenderBookId);
		inParamMap.put("P_PERSON_ID", personId);
		inParamMap.put("P_DATE", visitDate);
		inParamMap.put("P_START_TIME", startTime);
		inParamMap.put("P_END_TIME", endTime);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		value = simpleJDBCCall.executeFunction(String.class, inParameter);
		return value;
	}

	@Override
	public String getlOverlapDetails(final BigDecimal offBookId) {
		final String sql = getQuery("OIDVISIT_GET_VISITORS_RESTRICTIONS_OVERLAPDETAILS");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("l_offender_book_id", offBookId),
					String.class);
		} catch (Exception e) {
			returnList = null;
			logger.error(this.getClass().getName() + " error in getlOverlapDetails " + e);
		}
		return returnList;
	}

	@Override
	public Integer checkContactActive(final Integer offenderBookId, final Integer personId) {
		final String sql = getQuery("OIDVISIT_CHECK_CONTACT_ACTIVE");
		Integer returnList = 0;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_offender_book_id", offenderBookId, "p_person_id", personId), Integer.class);
		} catch (Exception e) {
			returnList = 0;
			logger.error(this.getClass().getName() + " error in checkContactActive " + e);
		}
		return returnList;
	}

	@Override
	public BigDecimal getNextOffVisitVisitorId() {
		final Integer value;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] { new SqlInOutParameter("RETURN_VALUE", OracleTypes.INTEGER) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_VISITS").withFunctionName("GET_NEXT_OFF_VISIT_VISITOR_ID")
				.declareParameters(sqlParameters);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		value = simpleJDBCCall.executeFunction(Integer.class, inParameter);
		return BigDecimal.valueOf(value);
	}

	public List<AgencyVisitTimes> oidvisitCheckListEntry() {
		final String sql = getQuery("OIDVISIT_CHECK_LIST_ENTRY");
		final RowMapper<AgencyVisitTimes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyVisitTimes.class,
				agencyVisitTimesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, mRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in oidvisitCheckListEntry " + e);
			return Collections.emptyList();
		}
	}

	/**
	 * 
	 * @param objSearchDao
	 * @return
	 */
	public String chkVisitConflicts(final VOffenderVisits objSearchDao) {
		String returnVal = null;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_VISITED_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_OFFENDER_VISIT_ID", OracleTypes.NUMBER),
				new SqlParameter("P_VISIT_DATE", OracleTypes.DATE),
				new SqlParameter("P_VISIT_START_TIME", OracleTypes.DATE),
				new SqlParameter("P_VISIT_END_TIME", OracleTypes.DATE),
				new SqlParameter("P_INTERNAL_LOCATION_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_WARNING_MESSAGE", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_VISITS").withProcedureName("CHK_VISIT_CONFLICTS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_VISITED_OFFENDER_BOOK_ID", objSearchDao.getOffenderBookId());
		inParamMap.put("P_OFFENDER_VISIT_ID", objSearchDao.getOffenderVisitId());
		inParamMap.put("P_VISIT_DATE", objSearchDao.getVisitDate());
		inParamMap.put("P_VISIT_START_TIME", objSearchDao.getStartTime());
		inParamMap.put("P_VISIT_END_TIME", objSearchDao.getEndTime());
		inParamMap.put("P_INTERNAL_LOCATION_ID", objSearchDao.getVisitInternalLocationId());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			returnVal = returnObject.get("P_WARNING_MESSAGE") != null ? returnObject.get("P_WARNING_MESSAGE").toString()
					: null;
		} catch (Exception e) {
			returnVal = null;
		}
		return returnVal;
	}

	@Override
	public List<VOffenderVisits> checkNonAssocationDetails(BigDecimal offenderBookId, Date visitDate,
			Integer locationId) {

		List<VOffenderVisits> vOffenderVisitsList = new ArrayList<VOffenderVisits>();

		final String sql = getQuery("OIDVISIT_CHECK_NONASSOCATION");
		final RowMapper<VOffenderVisits> VOffenderVisitsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderVisits.class, vOffenderVisitsMapping);
		try {
			vOffenderVisitsList = namedParameterJdbcTemplate.query(sql, createParams("nsOffenderBookId", offenderBookId,
					"visitDate", visitDate, "internalLocationId", locationId), VOffenderVisitsRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in checkNonAssocationDetails " + e);
		}

		return vOffenderVisitsList;
	}
	
	@Override
	public String checkIeplevel() {
		final String sql = getQuery("OIDVISIT_CHECK_IEP_LEVEL");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in classPending " + e);
		}
		return returnList;
	}
	
	@Override
	public String checkIepSecConfig(String agyLocId) {
		final String sql = getQuery("OIDVISIT_CHECK_IEP_SEC_CONFIG");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams( "agyLocId" ,agyLocId), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in classPending " + e);
		}
		return returnList;
	}
	
	@Override
	public String getOffenderIepConfigData(Long offenderBookId) {
		final String sql = getQuery("OIDVISIT_GET_OFFENDER_IEP_CONFIG_DATA");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId" , offenderBookId), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in classPending " + e);
		}
		return returnList;
	}

	@Override
	public String offenderVisitLimitForVisitTypeForIpLevel(String agyLocId, String iepLevel, String visitType) {
		final String sql = getQuery("OIDVISIT_VISITCYCLELIMITS_WITH_VISITTYPE_FOR_IP_LEVEL");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("agyLocId", agyLocId, "iepLevel", iepLevel, "visitType", visitType), String.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + " error in offenderVisitLimitForVisitType  " + e);
			returnList = null;
		}
		return returnList;
	}

	@Override
	public Date getCycleEndForOffender(String code, Integer offenderBookId) {
		final String sql = getQuery("OIDVISIT_REVIEW_DATE_FOR_CYCLE_END");
		Date returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("iepLevelCode",code,"offenderBookId",offenderBookId), Date.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + " error in offenderVisitLimitForVisitType  " + e);
			returnList = null;
		}
		return returnList;
	}

	@Override
	public Integer getMaxVisitors(VOffenderVisits vOffVisitrs) {
		final String sql = getQuery("OIDVISIT_GET_MAX_VISITORS_COUNT");
		Integer maxVisitCout = 0;
		try {
			maxVisitCout = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("agyLocId",vOffVisitrs.getAgyLocId()  ,"offenderBookId",vOffVisitrs.getOffenderBookId(),"visitType", vOffVisitrs.getVisitType()), Integer.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + " error in getMaxVisitors  " + e);
		}
		return maxVisitCout;
	}
	
	@Override
	public List<ReferenceCodes> rgVisitTypeRecordGroup(String agyLocId, Long offenderBookId) {
		final String sql = getQuery("OIDVISIT_FIND_RGVISITTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("agyLocId", agyLocId, "offenderBookId", offenderBookId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + " error in rgVisitTypeRecordGroup " + e);
			return Collections.emptyList();
		}
	}
	public Integer OidvisitAttendedChanges(BigDecimal offenderVisitId) {
		final String sql = getQuery("OIDVISIT_ATTENDED_CHANGES");
		Integer returnval = null;
		try {
		returnval = namedParameterJdbcTemplate.queryForObject(sql, createParams("offender_visit_id",offenderVisitId ),
				Integer.class);
		}catch (Exception e) {
			logger.error(this.getClass().getName() + " error in OidvisitAttendedChanges " + e);
		}
		return returnval;
	}
	@Override
	public List<ReferenceCodes> getRgVisitType(String agyLocId, Long offenderBookId ,String caseLoadType ,String supLvlType) {
		final String sql = getQuery("OIDVISIT_GET_RGVISITTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("agyLocId", agyLocId, "offenderBookId", offenderBookId ,"caseLoadType",caseLoadType,"supLvlType",supLvlType), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + " error in getRgVisitType " + e);
			return Collections.emptyList();
		}
	}
	
	@Override
	public Integer updateOffenderVisits(BigDecimal offenderVisitId,  String userName) {
		final String sql = getQuery("OIDVISIT_UPDATE_COMPLETION");
		Integer returnval = null;
		try {
			returnval= namedParameterJdbcTemplate.update(sql,
					createParams("offender_visit_id", offenderVisitId, "modifyUserId", userName));
		}catch (Exception e) {
			logger.error(this.getClass().getName() + " error in updateOffenderVisits " + e);
		}
		return returnval;
	}
	@Override
	public String getSupLevel(Long offenderBookId, String caseLoadType) {
		final String sql = getQuery("OIDVISIT_GET_SUP_LEVE_TYPE");
		String supLevel="";
		try {
			supLevel= namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId ,"caseLoadType",caseLoadType), String.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + " error in getSupLevel " + e);
		}
		return supLevel;
	}

	@Override
	public List<ReferenceCodes> getRgVisitTypesOff(String agyLocId, Long offenderBookId, String caseLoadType) {
		final String sql = getQuery("OIDVISIT_GET_RG_GROUP");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if(caseLoadType.equals("COMM")) sqlQuery.append(" profile_value_2 ");	
		if(caseLoadType.equals("INST")) sqlQuery.append(" profile_value ");
		sqlQuery.append("from system_profiles where profile_type = 'CLIENT' and profile_code = 'PENDING_STAT') and vtl.active_flag = 'Y' and vcl.agy_loc_id = :agyLocId and vcl.visit_cycle_limit_id = vtl.visit_cycle_limit_id and vtl.active_flag = 'Y')");
		preparedSql=sqlQuery.toString().trim();
		try {
			return  namedParameterJdbcTemplate.query(preparedSql,createParams("agyLocId",agyLocId),mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + " error in getRgVisitTypesOff " + mRowMapper);
			return Collections.emptyList();
		}	
	}
}
