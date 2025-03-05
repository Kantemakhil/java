package net.syscon.s4.inst.movements.housingchanges.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.movements.housingchanges.OidcholoRepository;
import net.syscon.s4.inst.movements.housingchanges.beans.CourtMovementTmp;

@Repository
public class OidcholoRepositoryImpl extends RepositoryBase implements OidcholoRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidcholoRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).put("DOMAIN", new FieldMapper("domain"))
			.put("MOVEMENT_REASON", new FieldMapper("movementReason"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("DSP_DESCRIPTION", new FieldMapper("dspDescription"))
			.put("ASSIGNMENT_REASON", new FieldMapper("assignmentReason")).build();
	private final Map<String, FieldMapper> vHeaderBlockMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DSP_LAST_NAME", new FieldMapper("dspLastName"))
			.put("DSP_FIRST_NAME", new FieldMapper("dspFirstName"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("LIVING_UNIT_DESCRIPTION", new FieldMapper("livingUnitDescription"))
			.put("LAST_NAME", new FieldMapper("lastName"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("FIRST_NAME", new FieldMapper("firstName"))
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("ASSIGNMENT_DATE", new FieldMapper("assignmentDate"))
			.put("ASSIGNMENT_TIME", new FieldMapper("assignmentTime"))
			.put("BOOKING_BEGIN_DATE", new FieldMapper("bookingBeginDate"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId")).build();
	private final Map<String, FieldMapper> livingUnitsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DSP_LIVING_UNIT_CODE3", new FieldMapper("dspLivingUnitCode3"))
			.put("CAPACITY", new FieldMapper("capacity")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("DSP_LIVING_UNIT_CODE", new FieldMapper("dspLivingUnitCode"))
			.put("DSP_LIVING_UNIT_CODE5", new FieldMapper("dspLivingUnitCode5"))
			.put("DSP_LIVING_UNIT_CODE4", new FieldMapper("dspLivingUnitCode4"))
			.put("LIVING_UNIT_ID1", new FieldMapper("livingUnitId1"))
			.put("LIVING_UNIT_ID2", new FieldMapper("livingUnitId2"))
			.put("LIVING_UNIT_CODE", new FieldMapper("livingUnitCode"))
			.put("LIVING_UNIT_ID3", new FieldMapper("livingUnitId3"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId"))
			.put("LIVING_UNIT_ID4", new FieldMapper("livingUnitId4")).build();

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("CAPACITY", new FieldMapper("capacity")).put("CODE", new FieldMapper("code"))
			.put("DOMAIN", new FieldMapper("domain")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("LIVING_UNIT_CODE", new FieldMapper("livingUnitCode"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId")).put("LAST_NAME", new FieldMapper("lastName"))
			.put("FIRST_NAME", new FieldMapper("firstName")).put("DESCRIPTION", new FieldMapper("description"))
			.put("LIVING_UNIT_DESCRIPTION", new FieldMapper("livingUnitDescription"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).build();
	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("DESCRIPTION", new FieldMapper("description"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId")).build();

	/**
	 * Creates new OidcholoRepositoryImpl class Object
	 */

	public OidcholoRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            CourtMovementTmp
	 *
	 * @return List<CourtMovementTmp>
	 *
	 * 
	 */
	public List<VHeaderBlock> crtMvTmpExecuteQuery(CourtMovementTmp objSearchDao) {
		final String sql = getQuery("OIDCHOLO_CRTMVTMP_FIND_COURT_MOVEMENT_TMP");
		final RowMapper<VHeaderBlock> CourtMovementTmpRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				vHeaderBlockMapping);
		final ArrayList<VHeaderBlock> returnList = (ArrayList<VHeaderBlock>) namedParameterJdbcTemplate.query(sql,
				createParams("agyLocId", objSearchDao.getAgyLocId(), "level1Code", objSearchDao.getLivingUnitCode5(),
						"level2Code", objSearchDao.getLivingUnitCode4(), "level3Code",
						objSearchDao.getLivingUnitCode3(), "level4Code", objSearchDao.getLivingUnitCode2(),"USER_ID",objSearchDao.getCreateUserId(),"USERID",objSearchDao.getCreateUserId()),
				CourtMovementTmpRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * 
	 *
	 */
	public int PRE_INSERT() {
		return 0;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstCourtMovementTmp
	 *            List<CourtMovementTmp>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer crtmvtmpInsertCourtMovementTmp(final List<CourtMovementTmp> lstCourtMovementTmp) {
		String sql = getQuery("OIDCHOLO_CRTMVTMP_INSERT_COURT_MOVEMENT_TMP");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstCourtMovementTmp.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstCourtMovementTmp
	 *            List<CourtMovementTmp>
	 *
	 * 
	 */
	public Integer crtMvTmpUpdateCourtMovementTmp(final List<BedAssignmentHistories> lstCourtMovementTmp) {

		String sql = getQuery("OIDCHOLO_CRTMVTMP_UPDATE_COURT_MOVEMENT_TMP");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final BedAssignmentHistories bedAssigmentHistories : lstCourtMovementTmp) {
			parameters.add(new BeanPropertySqlParameterSource(bedAssigmentHistories));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCourtMovementTmp.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstCourtMovementTmp
	 *            List<CourtMovementTmp>
	 *
	 * 
	 */
	public Integer crtMvTmpDeleteCourtMovementTmp(final List<CourtMovementTmp> lstCourtMovementTmp) {
		String sql = getQuery("OIDCHOLO_CRTMVTMP_DELETE_COURT_MOVEMENT_TMP");

		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CourtMovementTmp courtMovementTmp : lstCourtMovementTmp) {
			parameters.add(new BeanPropertySqlParameterSource(courtMovementTmp));
		}
		try {
			batchUpdatePreDeletedRows("COURT_MOVEMENT_TMP", null, parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in deleteOffederCondTransfer"+e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstCourtMovementTmp.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkCrtMvTmpMovementReasoRecordGroup() {
		final String sql = getQuery("OIDCHOLO_FIND_CGFKCRTMVTMPMOVEMENTREASO");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("cgfkCrtMvTmpMovementReasoRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkBedAhDspDescriptionRecordGroup() {
		final String sql = getQuery("OIDCHOLO_FIND_CGFKBEDAHDSPDESCRIPTION");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("cgfkBedAhDspDescriptionRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkBedAhDspOffenderIdDiRecordGroup(String userName) {
		final String sql = getQuery("OIDCHOLO_FIND_CGFKBEDAHDSPOFFENDERIDDI");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("USERID",userName), mRowMapper);
		} catch (Exception e) {
			logger.error("cgfkBedAhDspOffenderIdDiRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<LivingUnits> cgfkCrtMvTmpDspLiving4RecordGroup(final String agyLocId) {
		final String sql = getQuery("OIDCHOLO_FIND_CGFKCRTMVTMPDSPLIVING4");
		final RowMapper<LivingUnits> mRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class, mMapping);
		List<LivingUnits> returnList = new ArrayList<LivingUnits>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("AGYLOCID", agyLocId), mRowMapper);
		} catch (Exception e) {
			logger.error("cgfkCrtMvTmpDspLiving4RecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<LivingUnits> cgfkCrtMvTmpDspLiving3RecordGroup(final String agyLocId, final String livingUnitId) {
		final String sql = getQuery("OIDCHOLO_FIND_CGFKCRTMVTMPDSPLIVING3");
		final RowMapper<LivingUnits> mRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class, mMapping);
		List<LivingUnits> returnList = new ArrayList<LivingUnits>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("AGYLOCID", agyLocId, "LIVINGUNITID1", livingUnitId), mRowMapper);
		} catch (Exception e) {
			logger.error("cgfkCrtMvTmpDspLiving3RecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkCrtMvTmpDspLiving2RecordGroup() {
		final String sql = getQuery("OIDCHOLO_FIND_CGFKCRTMVTMPDSPLIVING2");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> resultList = new ArrayList<ReferenceCodes>();
		try {
			resultList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("cgfkCrtMvTmpDspLiving2RecordGroup", e);
		}
		return resultList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkCrtMvTmpDspLivingUniRecordGroup() {
		final String sql = getQuery("OIDCHOLO_FIND_CGFKCRTMVTMPDSPLIVINGUNI");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> resultList = new ArrayList<ReferenceCodes>();
		try {
			resultList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("cgfkCrtMvTmpDspLivingUniRecordGroup", e);
		}
		return resultList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkCrtMvTmpAgyLocIdRecordGroup(final String caseload) {
		final String sql = getQuery("OIDCHOLO_FIND_CGFKCRTMVTMPAGYLOCID");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> resultList = new ArrayList<ReferenceCodes>();
		try {
			resultList = namedParameterJdbcTemplate.query(sql, createParams("CASELOADID", caseload), mRowMapper);
		} catch (Exception e) {
			logger.error("cgfkCrtMvTmpDspLivingUniRecordGroup", e);
		}
		return resultList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCrtMvTmpCrtMvTmp
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfkchkCrtMvTmpCrtMvTmp(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDCHOLO_CGFKCHK_CRT_MV_TMP_CRT_MV_TMP");
		List<ReferenceCodes> returnObj = namedParameterJdbcTemplate.queryForList(sql,
				createParams("ReferenceCodes", paramBean), ReferenceCodes.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCrtMvTmpCrtMv
	 *
	 * @param params
	 *
	 */
	public AgencyLocations cgfkchkCrtMvTmpCrtMv(final AgencyLocations paramBean) {
		final String sql = getQuery("OIDCHOLO_CGFKCHK_CRT_MV_TMP_CRT_MV_2");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		AgencyLocations returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("AgencyLocations", paramBean), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCrtMvTmpCrtMv
	 *
	 * @param params
	 *
	 */
	public List<LivingUnits> cgfkchkCrtMvTmpCrtMv1(final LivingUnits paramBean) {
		final String sql = getQuery("OIDCHOLO_CGFKCHK_CRT_MV_TMP_CRT_MV_3");
		final RowMapper<LivingUnits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		final List<LivingUnits> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("LivingUnits", paramBean), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpCrtMvTmpCrtMvTmp
	 *
	 * @param params
	 *
	 */
	public List<LivingUnits> cgfklkpCrtMvTmpCrtMvTmp(final LivingUnits paramBean) {
		final String sql = getQuery("OIDCHOLO_CGFKLKP_CRT_MV_TMP_CRT_MV_TMP");
		final RowMapper<LivingUnits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		final ArrayList<LivingUnits> returnList = (ArrayList<LivingUnits>) namedParameterJdbcTemplate.query(sql,
				createParams("LivingUnits", paramBean), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCrtMvTmpCrtMv
	 *
	 * @param params
	 *
	 */
	public List<LivingUnits> cgfkchkCrtMvTmpCrtMv(final LivingUnits paramBean) {
		final String sql = getQuery("OIDCHOLO_CGFKCHK_CRT_MV_TMP_CRT_MV_6");
		final RowMapper<LivingUnits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		final ArrayList<LivingUnits> returnList = (ArrayList<LivingUnits>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpCrtMvTmpCrtMv
	 *
	 * @param params
	 *
	 */
	public List<LivingUnits> cgfklkpCrtMvTmpCrtMv(final LivingUnits paramBean) {
		final String sql = getQuery("OIDCHOLO_CGFKLKP_CRT_MV_TMP_CRT_MV_4");
		final RowMapper<LivingUnits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		final ArrayList<LivingUnits> returnList = (ArrayList<LivingUnits>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkBedAhBedAhVhbF1
	 *
	 * @param params
	 *
	 */
	public List<VHeaderBlock> cgfkchkBedAhBedAhVhbF1(final VHeaderBlock paramBean) {
		final String sql = getQuery("OIDCHOLO_CGFKCHK_BED_AH_BED_AH_VHB_F1");
		final RowMapper<VHeaderBlock> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				vHeaderBlockMapping);
		final ArrayList<VHeaderBlock> returnList = (ArrayList<VHeaderBlock>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpBedAhBedAhVhbF1
	 *
	 * @param params
	 *
	 */
	public List<VHeaderBlock> cgfklkpBedAhBedAhVhbF1(final VHeaderBlock paramBean) {
		final String sql = getQuery("OIDCHOLO_CGFKLKP_BED_AH_BED_AH_VHB_F1");
		final RowMapper<VHeaderBlock> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				vHeaderBlockMapping);
		final ArrayList<VHeaderBlock> returnList = (ArrayList<VHeaderBlock>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkBedAhBedAhRefCod
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfkchkBedAhBedAhRefCod(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDCHOLO_CGFKCHK_BED_AH_BED_AH_REF_COD");
		List<ReferenceCodes> returnObj = namedParameterJdbcTemplate.queryForList(sql, createParams(),
				ReferenceCodes.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpBedAhBedAhRefCod
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfklkpBedAhBedAhRefCod(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDCHOLO_CGFKLKP_BED_AH_BED_AH_REF_COD");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	@Override
	public Integer crtMvTmpInsertCourtMovementTmp(final List<BedAssignmentHistories> lstCourtMovementTmp) {
		final String sql = getQuery("OIDCHOLO_CRTMVTMP_INSERT_COURT_MOVEMENT_TMP");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final BedAssignmentHistories bedAssigmentHistories : lstCourtMovementTmp) {
			parameters.add(new BeanPropertySqlParameterSource(bedAssigmentHistories));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCourtMovementTmp.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer oidcholoCrtmvtmpUpdateOffbkgs(final List<OffenderBookings> offBkgsList) {
		final String sql = getQuery("OIDCHOLO_CRTMVTMP_UPDATE_OFFBKGS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderBookings bedAssigmentHistories : offBkgsList) {
			parameters.add(new BeanPropertySqlParameterSource(bedAssigmentHistories));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (offBkgsList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<VHeaderBlock> oidcholoCgfklkpBedAhBedDatetimeProc(Integer livingUnitId, Integer offenderBookId) {
		final String sql = getQuery("OIDCHOLO_CGFKLKP_BED_AH_BED_DATETIME_PROC");
		final RowMapper<VHeaderBlock> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				vHeaderBlockMapping);
		final List<VHeaderBlock> returnList =  namedParameterJdbcTemplate.query(sql,
				createParams("livingUnitId", livingUnitId, "offenderBookId", offenderBookId), columnRowMapper);
		return returnList;
	}
	
	@Override
	public List<OffenderBookings> getOldRecOffBooking(Integer offenderBookId) {
		final String sql = getQuery("GET_OLD_REC_OFF_BOOKING");
		List<OffenderBookings> retObj = new ArrayList<OffenderBookings>();
		final RowMapper<OffenderBookings> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				vHeaderBlockMapping);
		try {
			retObj =  namedParameterJdbcTemplate.query(sql, createParams("offender_book_id", offenderBookId),
					columnRowMapper);
		}
		catch(Exception e) {
			logger.error("getOldRecOffBooking "+e);
		}
		return retObj;
	}

}
