package net.syscon.s4.inst.movements.housingchanges.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.incidentsoic.impl.OcuoichnRepositoryImpl;
import net.syscon.s4.inst.movements.housingchanges.OidchlocRepository;

/**
 * Class OidchlocRepositoryImpl
 * 
 */
@Repository
public class OidchlocRepositoryImpl extends RepositoryBase implements OidchlocRepository {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuoichnRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> livUnitsMap = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("DESCRIPTION", new FieldMapper("description"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId")).build();
	private final Map<String, FieldMapper> bedAssHisMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ASSIGNMENT_TIME", new FieldMapper("assignmentTime"))
			.put("ASSIGNMENT_DATE", new FieldMapper("assignmentDate"))
			.put("ASSIGNMENT_REASON", new FieldMapper("assignmentReason")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("ASSIGNMENT_END_TIME", new FieldMapper("assignmentEndTime"))
			.put("BED_ASSIGN_SEQ", new FieldMapper("bedAssignSeq"))
			.put("ASSIGNMENT_END_DATE", new FieldMapper("assignmentEndDate")).build();
	private final Map<String, FieldMapper> sysProfMap = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", new FieldMapper("profileCode")).put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", new FieldMapper("oldTableName"))
			.put("PROFILE_VALUE", new FieldMapper("profileValue"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE_2", new FieldMapper("profileValue2")).put("DESCRIPTION", new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> refCodesMap = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).put("DESCRIPTION", new FieldMapper("description")).build();

	/**
	 * Creates new OidchlocRepositoryImpl class Object
	 */
	public OidchlocRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao BedAssignmentHistories
	 *
	 * @return List<BedAssignmentHistories>
	 *
	 */
	public List<BedAssignmentHistories> bedAhExecuteQuery(final BedAssignmentHistories objSearchDao) {
		logger.info(this.getClass().getName() + ".bedAhExecuteQuery start :: ", objSearchDao);
		final String sql = getQuery("OIDCHLOC_BEDAH_FIND_BED_ASSIGNMENT_HISTORIES");
		List<BedAssignmentHistories> returnList = new ArrayList<BedAssignmentHistories>();
		final RowMapper<BedAssignmentHistories> bedAssHisRowMapp = Row2BeanRowMapper.makeMapping(sql,
				BedAssignmentHistories.class, bedAssHisMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("OFFENDERBOOKID",
					objSearchDao.getOffenderBookId(), "BEDASSIGNSEQ", objSearchDao.getBedAssignSeq()),
					bedAssHisRowMapp);
			logger.info(this.getClass().getName() + ".bedAhExecuteQuery end");
		} catch (Exception e) {
			logger.error("Error in calss " + this.getClass().getName() + "bedAhExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao SystemProfiles
	 *
	 * @return List<SystemProfiles>
	 *
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		logger.info(this.getClass().getName() + ".sysPflExecuteQuery start :: ", objSearchDao);
		final String sql = getQuery("OIDCHLOC_SYSPFL_FIND_SYSTEM_PROFILES");
		List<SystemProfiles> returnList = new ArrayList<SystemProfiles>();
		final RowMapper<SystemProfiles> sysProfRowMap = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				sysProfMap);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("PROFILETYPE",
					objSearchDao.getProfileType(), "PROFILECODE", objSearchDao.getProfileCode()), sysProfRowMap);
			logger.info(this.getClass().getName() + ".sysPflExecuteQuery end");
		} catch (Exception e) {
			logger.error("Error in calss " + this.getClass().getName() + "sysPflExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgAssignmentReasonRecordGroup() {
		logger.info(this.getClass().getName() + "rgAssignmentReasonRecordGroup start :: ");
		final String sql = getQuery("OIDCHLOC_FIND_RGASSIGNMENTREASON");
		List<ReferenceCodes> returnList = new ArrayList<>();
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodesMap);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
			logger.info(this.getClass().getName() + "rgAssignmentReasonRecordGroup end");
		} catch (Exception e) {
			logger.error("Error in calss " + this.getClass().getName() + "rgAssignmentReasonRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBkgOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<Object> offBkgOnCheckDeleteMaster(final BedAssignmentHistories paramBean) {
		logger.info(this.getClass().getName() + "offBkgOnCheckDeleteMaster start :: ", paramBean);
		final String sql = getQuery("OIDCHLOC_OFF_BKG_ONCHECKDELETEMASTER");
		List<Object> returnObj = new ArrayList<Object>();
		try {
			returnObj = namedParameterJdbcTemplate.queryForList(sql,
					createParams("OFFENDERBOOKID", paramBean.getOffenderBookId()), Object.class);
			logger.info(this.getClass().getName() + "offBkgOnCheckDeleteMaster end");

		} catch (Exception e) {
			logger.error("Error in calss " + this.getClass().getName() + "rgAssignmentReasonRecordGroup", e);
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * bedAhPreInsert
	 *
	 * @param params
	 *
	 */
	public Integer bedAhPreInsert(final BedAssignmentHistories paramBean) {
		final String sql = getQuery("OIDCHLOC_BED_AH_PREINSERT");
		Integer returnObj = 0;
		returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OFFENDERBOOKID", paramBean.getOffenderBookId()), Integer.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkBedAhBedAhVLiv
	 *
	 * @param params
	 *
	 */
	public LivingUnits cgfkchkBedAhBedAhVLiv(final LivingUnits paramBean) {
		final String sql = getQuery("OIDCHLOC_CGFKCHK_BED_AH_BED_AH_V_LIV_U");
		LivingUnits returnList = new LivingUnits();
		final RowMapper<LivingUnits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livUnitsMap);
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("LIVINGUNITID", paramBean.getLivingUnitId(), "AGYLOCID", paramBean.getAgyLocId()),
					columnRowMapper);
		} catch (EmptyResultDataAccessException e) {
			returnList = new LivingUnits();
		} catch (Exception e) {
			logger.error("", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstBedAssignmentHistories List<BedAssignmentHistories>
	 *
	 * @return List<Integer>
	 *
	 */
	@Override
	public Integer bedAhInsertBedAssignmentHistories(final List<BedAssignmentHistories> list, String beforeiep) {
		String beforeIep = null, afterIep = null;
		Date nextReviewDate = null;
		Long staffId = null;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final BedAssignmentHistories lstBedAsgnHist : list) {
			parameters.add(new BeanPropertySqlParameterSource(lstBedAsgnHist));
			try {
				beforeIep = namedParameterJdbcTemplate.queryForObject(getQuery("OIDCHLOC_GET_IEP_DESCRIPTION"),
						createParams("offenderBookId", lstBedAsgnHist.getOffenderBookId()), String.class);
			} catch (EmptyResultDataAccessException e) {
				logger.error(this.getClass().getName() + " In bedAhInsertBedAssignmentHistories" + e);
			}
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(
					getQuery("OIDCHLOC_BEDAH_INSERT_BED_ASSIGNMENT_HISTORIES"),
					parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " In bedAhInsertBedAssignmentHistories" + e);
		}

		if (list.size() == returnArray.length) {
			try {
				for (final BedAssignmentHistories lstBedAsgnHist : list) {
					List<String> iepList = new ArrayList<String>();
					iepList = namedParameterJdbcTemplate.queryForList(getQuery("OIDCHLOC_GET_IEP_DESCRIPTION_AFTER"),
							createParams("offenderBookId", lstBedAsgnHist.getOffenderBookId()), String.class);
					
					for (String iep : iepList) {
						if (iep != null) {
							afterIep = iep;
							break;
						}
					}
					
				}
				if(afterIep==null) {
					afterIep =getDefaultIepLevelForAgyLoc(list.get(0).getAgyLocId());
				}

			} catch (EmptyResultDataAccessException e) {
				logger.error(this.getClass().getName() + " In bedAhInsertBedAssignmentHistories" + e);
			}
			if ((afterIep != null ) && (!afterIep.equals(beforeIep))) {
				for (final BedAssignmentHistories lstBedAsgnHist : list) {
					try {
						nextReviewDate = namedParameterJdbcTemplate.queryForObject(
								getQuery("OIDCHLOC_FETCH_NEXT_REVIEW_DATE"), createParams(), Date.class);
					} catch (EmptyResultDataAccessException e) {
						logger.error(this.getClass().getName() + " In bedAhInsertBedAssignmentHistories" + e);
					}

					try {
						staffId = namedParameterJdbcTemplate.queryForObject(getQuery("OIDCHLOC_GET_STAFF_ID"),
								createParams(), Long.class);
					} catch (EmptyResultDataAccessException e) {
						logger.error(this.getClass().getName() + " In bedAhInsertBedAssignmentHistories" + e);
					}

					try {
						MapSqlParameterSource map = new MapSqlParameterSource();
						map.addValue("offenderBookId", lstBedAsgnHist.getOffenderBookId());
						map.addValue("iepLevelCode", afterIep);
						map.addValue("assignedDate", new Date());
						map.addValue("approvedStaffId", staffId);
						map.addValue("nextReviewDate", nextReviewDate);
						map.addValue("reviewComment", ApplicationConstants.REVIEW_COMMENT);
						map.addValue("modifyUserId", null);
						map.addValue("createUserId", lstBedAsgnHist.getCreateUserId());
						map.addValue("sealFlag", null);
						map.addValue("modifyDatetime", null);
						namedParameterJdbcTemplate.update(getQuery("OIDCHLOC_INSERT_IEP_DATA"), map);
					} catch (Exception e) {
						logger.error(this.getClass().getName() + " In bedAhInsertBedAssignmentHistories" + e);
					}
				}
			}
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstBedAssignmentHistories List<BedAssignmentHistories>
	 *
	 * @return List<Integer>
	 *
	 */
	public Integer bedAhUpdateBedAssignmentHistories(final List<BedAssignmentHistories> list) {
		final String sql = getQuery("OIDCHLOC_BEDAH_UPDATE_BED_ASSIGNMENT_HISTORIES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final BedAssignmentHistories lstBedAsgnHist : list) {
			parameters.add(new BeanPropertySqlParameterSource(lstBedAsgnHist));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to updtae the records in the data base tables based on
	 *
	 * @param VHeaderBlock vblock
	 *
	 * @return Integer
	 *
	 */
	public Integer offBookingUpdate(final VHeaderBlock vblock) {
		final String sql = getQuery("OIDCHLOC_UPDATE_OFFENDER_BOOKINGS");
		int[] returnArray = new int[] {};
		final ArrayList<VHeaderBlock> list = new ArrayList<VHeaderBlock>();
		list.add(vblock);
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VHeaderBlock bedAssignHist : list) {
			parameters.add(new BeanPropertySqlParameterSource(bedAssignHist));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (DataAccessException e) {
			logger.error("offBookingUpdate", e);
		}
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetch the Date and Time from database table
	 *
	 * @param objSearchDao BedAssignmentHistories
	 *
	 * @return BedAssignmentHistories
	 *
	 */
	public BedAssignmentHistories getMovementDateAndTime(final BedAssignmentHistories objSearchDao) {
		final String sql = getQuery("GET_LATEST_BED_ASS_REC_CUR");
		BedAssignmentHistories returnList = new BedAssignmentHistories();
		final RowMapper<BedAssignmentHistories> bedAsHistRowMap = Row2BeanRowMapper.makeMapping(sql,
				BedAssignmentHistories.class, bedAssHisMapping);
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDERBOOKID", objSearchDao.getOffenderBookId()), bedAsHistRowMap);
		} catch (EmptyResultDataAccessException e) {
			returnList = new BedAssignmentHistories();
		} catch (Exception e) {
			logger.error("", e);
		}
		return returnList;
	}

	/**
	 * below method is used to execute DB procedure to check Conflicts param
	 * objSearchDao returns BedAssignmentHistories
	 */
	public BedAssignmentHistories checkAllConficts(final BedAssignmentHistories objSearchDao) {
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		final BedAssignmentHistories returnList = new BedAssignmentHistories();
		SqlParameter[] sqlParameters = new SqlParameter[6];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", Types.INTEGER),
				new SqlParameter("P_LIVING_UNIT_ID", Types.INTEGER), new SqlParameter("P_OFFENDER_ID", Types.INTEGER),
				new SqlInOutParameter("P_WARNING_MSG", Types.VARCHAR),
				new SqlInOutParameter("P_WARNING_PROMPT", Types.VARCHAR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMUAVBED").withProcedureName("GET_CONFLICT_WARNING").declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", objSearchDao.getOffenderBookId());
		inParamMap.put("P_OFFENDER_ID", 0);
		inParamMap.put("P_LIVING_UNIT_ID", objSearchDao.getLivingUnitId());
		inParamMap.put("P_WARNING_MSG", null);
		inParamMap.put("P_WARNING_PROMPT", null);

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			returnList.setWarningMsg(String.valueOf(returnObject.get("P_WARNING_MSG")));
			returnList.setWarningPrompt(String.valueOf(returnObject.get("P_WARNING_PROMPT")));
		} catch (Exception e) {
			logger.error("", e);
		}
		return returnList;
	}

	@Override
	public OffenderBookings getOldDataOffenderBookings(final Long offdenderBookId) {
		final String sql = getQuery("OIDCHLOC_GET_OLD_DATA_OFFENDER_BOOKINGS");
		OffenderBookings bean = new OffenderBookings();
		try {
			bean = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offdenderBookId),
					new BeanPropertyRowMapper<OffenderBookings>(OffenderBookings.class));
		} catch (Exception e) {
			logger.error("getOldDataOffenderBookings", e);
		}
		return bean;
	}

	@Override
	public List<String> getNonAssocationOffenderDetails(Integer offenderId, Integer livingunitId) {
		final String sql = getQuery("OIDCHLOC_GET_NONASSOCATION_OFFENDER_DETAILS");
		List<String> offenderDetailsList = new ArrayList<String>();
		try {
			offenderDetailsList = namedParameterJdbcTemplate.queryForList(sql,
					createParams("offenderId", offenderId, "livingUnitId", livingunitId), String.class);
		} catch (Exception e) {
			logger.error("getNonAssocationOffenderDetails", e);
		}

		return offenderDetailsList;
	}

	@Override
	public String offenderDetailsByOffenderId(Integer offenderId) {
		final String sql = getQuery("OIDCHLOC_GET_OFFENDER_DETAILS");
		String offenderDetailsList =null;
		try {
			offenderDetailsList = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId",offenderId), String.class);
		} catch (Exception e) {
			logger.error("offenderDetailsByOffenderId", e);
		}

		return offenderDetailsList;
	}

	@Override
	public Integer getOffenderId(Integer offenderBookId) {

		final String sql = getQuery("OIDCHLOC_GET_OFFENDER_ID_FROM_OFFENDER_BOOK_ID");

		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
					Integer.class);

		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	@Override
	public Boolean checkMoveAdminRoleForUser(String userId) {
		String moveAdminRoleCheck = null;
		final String sql = getQuery("OIDCHLOC_MOVE_ADMIN_ROLE_FOR_USER");

		try {
			moveAdminRoleCheck = namedParameterJdbcTemplate.queryForObject(sql, createParams("userId", userId),
					String.class);

		} catch (Exception e) {
			logger.error(e);
		}
		return moveAdminRoleCheck.equals("Y") ? true : false;
	}

	 String getDefaultIepLevelForAgyLoc(String aygLocId) {
			String iepLevel = null;
			final String sql = getQuery("OIDCHLOC_GET_AGYLOCID_IEPLEVEL");

			try {
				iepLevel = namedParameterJdbcTemplate.queryForObject(sql, createParams("agyLocId", aygLocId), String.class);

			} catch (Exception e) {
				logger.error(e);
			}

			return iepLevel;
		}

	@Override
	public BedAssignmentHistories offenderName(Integer offenderBookId) {
		final String sql = getQuery("OIDCHLOC_OFFENDER_NAME");
		BedAssignmentHistories returnObj = new BedAssignmentHistories();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId), new BeanPropertyRowMapper<BedAssignmentHistories>(BedAssignmentHistories.class));
		} catch(Exception e) {
			logger.error("offenderName", e);
		}
		return returnObj;
	}
}
