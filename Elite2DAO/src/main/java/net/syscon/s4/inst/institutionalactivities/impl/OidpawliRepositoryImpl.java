package net.syscon.s4.inst.institutionalactivities.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.ProgramsNonAssocTmp;
import net.syscon.s4.inst.institutionalactivities.OidpawliRepository;
import net.syscon.s4.inst.institutionalactivities.beans.VPrisonActivities;

@Repository
public class OidpawliRepositoryImpl extends RepositoryBase implements OidpawliRepository {

	private static Logger logger = LogManager.getLogger(OidpawliRepositoryImpl.class.getName());

	/**
	 * Creates new OidpawliRepositoryImpl class Object
	 */
	public OidpawliRepositoryImpl() {
	}

	private final Map<String, FieldMapper> offeProgProfMap = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_END_DATE", new FieldMapper("offenderEndDate"))
			.put("OFFENDER_PROGRAM_STATUS", new FieldMapper("offenderProgramStatus"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("OFFENDER_START_DATE", new FieldMapper("offenderStartDate"))
			.put("OFF_PRGREF_ID", new FieldMapper("offPrgrefId")).put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("PROGRAM_ID", new FieldMapper("programId"))
			.put("MIN(OFFENDER_START_DATE)", new FieldMapper("min(offenderStartDate)"))
			.put("REFERRAL_DATE", new FieldMapper("referralDate"))
			.put("REFCOMMENTVAL", new FieldMapper("refCommentVal")).put("REJDATE", new FieldMapper("rejDate"))
			.put("REJREASON", new FieldMapper("rejReason")).put("DECISION", new FieldMapper("decision"))
			.put("REFERRAL_COMMENT_TEXT", new FieldMapper("referralCommentText"))
			.put("REFERRAL_PRIORITY", new FieldMapper("referralPriority"))
			.put("REJECT_REASON_CODE", new FieldMapper("rejectReasonCode"))
			.put("REJECT_DATE", new FieldMapper("rejectDate"))
			.put("WAITLIST_DECISION_CODE", new FieldMapper("waitlistDecisionCode"))
			.put("SUSPENDED_FLAG", new FieldMapper("suspendedFlag"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("COMMENT_TEXT", new FieldMapper("commentText")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("CRS_ACTY_ID", new FieldMapper("crsActyId")).build();
	private final Map<String, FieldMapper> vHeadBloMap = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID", new FieldMapper(" offenderId "))
			.put("ROOT_OFFENDER_ID", new FieldMapper(" rootOffenderId")).build();
	private final Map<String, FieldMapper> vPrsnActMap = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", new FieldMapper("listSeq")).put("DESCRIPTION", new FieldMapper("description"))
			.put("CODE", new FieldMapper("code")).put("SERVICE", new FieldMapper("service"))
			.put("PROGRAM_ID", new FieldMapper("programId")).put("PROGRAM_CODE", new FieldMapper("programCode"))
			.build();

	private final Map<String, FieldMapper> agencyLocMap = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code")).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderProgramProfiles
	 *
	 * @return List<OffenderProgramProfiles>
	 *
	 * @throws SQLException
	 */
	public List<OffenderProgramProfiles> waitlistExecuteQuery(final OffenderProgramProfiles objSearchDao) {
		final String sql = getQuery("OIDPAWLI_WAITLIST_FIND_OFFENDER_PROGRAM_PROFILES");
		final RowMapper<OffenderProgramProfiles> OffProgProfRowMap = Row2BeanRowMapper.makeMapping(sql,
				OffenderProgramProfiles.class, offeProgProfMap);
		return (ArrayList<OffenderProgramProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams("programId", objSearchDao.getProgramId(), "agyLocId", objSearchDao.getAgyLocId()),
				OffProgProfRowMap);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderGrievancesPreInsert
	 *
	 * @param params
	 *
	 */

	public Long ocdxprogOffPrgrefId() {
		final String sql = getQuery("OIDPAWLI_OFF_PRGREF_ID");
		Long obj = null;
		Long returnval = null;
		obj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		if (obj != null) {
			returnval = obj;
		}
		return returnval;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderProgramProfiles List<OffenderProgramProfiles>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer waitlistInsertOffenderProgramProfiles(final List<OffenderProgramProfiles> listOpf) {
		final String sql = getQuery("OIDPAWLI_WAITLIST_INSERT_OFFENDER_PROGRAM_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderProgramProfiles opf : listOpf) {
			parameters.add(new BeanPropertySqlParameterSource(opf));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (listOpf.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderProgramProfiles List<OffenderProgramProfiles>
	 *
	 * @throws SQLException
	 */
	public Integer waitlistUpdateOffenderProgramProfiles(final List<OffenderProgramProfiles> list, String user) {
		final String sql = getQuery("OIDPAWLI_WAITLIST_UPDATE_OFFENDER_PROGRAM_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderProgramProfiles opf : list) {
			opf.setModifyUserId(user);
			parameters.add(new BeanPropertySqlParameterSource(opf));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderProgramProfiles List<OffenderProgramProfiles>
	 *
	 * @throws SQLException
	 */
	public Integer waitlistDeleteOffenderProgramProfiles(
			final List<OffenderProgramProfiles> lstOffenderProgramProfiles) {
		final String sql = getQuery("OIDPAWLI_WAITLIST_DELETE_OFFENDER_PROGRAM_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderProgramProfiles opf : lstOffenderProgramProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(opf));
		}
		try {
			batchUpdatePreDeletedRows("OFFENDER_PROGRAM_PROFILES", "OFF_PRGREF_ID = :offPrgrefId", parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderProgramProfiles.size() == returnArray.length) {
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
	public List<ReferenceCodes> rgReasonRecordGroup() {
		final String sql = getQuery("OIDPAWLI_FIND_RGREASON");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				agencyLocMap);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "In method rgReasonRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgPriorityRecordGroup() {
		final String sql = getQuery("OIDPAWLI_FIND_RGPRIORITY");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				agencyLocMap);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "In method rgPriorityRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<VPrisonActivities> rgServicesRecordGroup(final String ageLocId) {
		final String sql = getQuery("OIDPAWLI_FIND_RGSERVICES");
		final RowMapper<VPrisonActivities> mRowMapper = Row2BeanRowMapper.makeMapping(sql, VPrisonActivities.class,
				vPrsnActMap);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("AGYLOCID", ageLocId), mRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "In method rgServicesRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> rgEstablishmentRecordGroup(final String agyLocId) {
		final String sql = getQuery("OIDPAWLI_FIND_RGESTABLISHMENT");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocMap);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("agyLocId", agyLocId), mRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "In method rgEstablishmentRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgDecisionRecordGroup(String systemMode) {
		final String sql = getQuery("OIDPAWLI_FIND_RGDECISION");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				agencyLocMap);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "In method rgDecisionRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<CourseActivities> rgActDescRecordGroup(final String programId, final String agyLocId) {
		final String sql = getQuery("OIDPAWLI_FIND_RGACTDESC");
		final RowMapper<CourseActivities> mRowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				agencyLocMap);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("programId", programId, "agyLocId", agyLocId),
					mRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "In method rgActDescRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkConflict
	 *
	 * @param params
	 *
	 */
	public List<VHeaderBlock> checkConflict(final VHeaderBlock paramBean) {
		final String sql = getQuery("OIDPAWLI_CHECK_CONFLICT");
		final RowMapper<VHeaderBlock> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				vHeadBloMap);
		final ArrayList<VHeaderBlock> returnList = (ArrayList<VHeaderBlock>) namedParameterJdbcTemplate.query(sql,
				createParams(VHeaderBlock.class), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkAssignConflict
	 *
	 * @param params
	 *
	 */
	public List<OffenderProgramProfiles> checkAssignConflict(final OffenderProgramProfiles paramBean) {

		final String sql = getQuery("OIDPAWLI_CHECK_ASSIGN_CONFLICT");

		final RowMapper<OffenderProgramProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProgramProfiles.class, offeProgProfMap);
		return (ArrayList<OffenderProgramProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams(OffenderProgramProfiles.class), columnRowMapper);
	}

	@Override
	public String getRejectName(final String rejectReasonCode) {
		final String sql = getQuery("OIDPAWLI_GET_REJECT_REASON");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("rejectCode", rejectReasonCode),
				String.class);
	}

	@Override
	public Date checkAssignConflictLvDate(final Long offenderBookId, final Long programId) {

		final String sql = getQuery("OIDPAWLI_CHECK_ASSIGN_CONFLICT_LV_DATE");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderBookId", offenderBookId, "programId", programId), Date.class);
	}

	@Override
	public Date checkAssignConflictLvDateOne(final Date offenderStartDate, final Date offenderEndDate,
			final Long offenderBookId, final Long programId) {
		final String sql = getQuery("OIDPAWLI_CHECK_ASSIGN_CONFLICT_LV_DATE_ONE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderStartDate", offenderStartDate,
				"offenderEndDate", offenderEndDate, "offenderBookId", offenderBookId, "programId", programId),
				Date.class);
	}

	@Override
	public List<Offenders> getOffDetails(final OffenderProgramProfiles obj) {
		final String sql = getQuery("CKECK_CONFLICT_GET_OFF_DETAILS");
		final RowMapper<Offenders> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, offeProgProfMap);
		return namedParameterJdbcTemplate.query(sql, createParams("offenderId", obj.getOffenderId()), mRowMapper);

	}

	@Override
	public List<Offenders> getNaPrgSrv(final OffenderProgramProfiles bean) {
		List<Offenders> returnObj = new ArrayList<Offenders>();
		if (bean.getOffenderStartDate() != null) {
			Calendar cal = Calendar.getInstance();
			String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/"
					+ cal.get(Calendar.YEAR);
			System.out.println("formatedDate : " + formatedDate);
		}
		final String sql = getQuery("CHECK_CONFLICT_GET_NA_PRG_SRV");
		final RowMapper<Offenders> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, offeProgProfMap);
		try {
			returnObj = namedParameterJdbcTemplate.query(sql, createParams("offenderId", bean.getOffenderId(),
					"crsActyId", bean.getCrsActyId(), "offenderStartDate", bean.getOffenderStartDate()), mRowMapper);
		} catch (Exception e) {

			logger.error("getNaPrgSrv :", e);
			returnObj = null;
		}
		return returnObj;
	}

	@Override
	public List<Offenders> getStgNaPrgSrv(final OffenderProgramProfiles bean) {
		final String sql = getQuery("CHECK_CONFLICT_GET_STG_NA_PRG_SRV");
		final RowMapper<Offenders> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, offeProgProfMap);
		return namedParameterJdbcTemplate.query(sql,
				createParams("offenderBookId", bean.getOffenderBookId(), "crsActyId", bean.getCrsActyId()), mRowMapper);
	}

	@Override
	public OffenderProgramProfiles checkConflictDateLv(final Date lvDate, final OffenderProgramProfiles paramBean)
			throws ParseException {
		final String sql = getQuery("OIDPAWLI_CHECK_CONFLICT");
		final Long crsActvityId = paramBean.getCrsActyId();
		OffenderProgramProfiles conflictBean = new OffenderProgramProfiles();
		final RowMapper<OffenderProgramProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProgramProfiles.class, offeProgProfMap);
		conflictBean = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderBookId", paramBean.getOffenderBookId(), "USERID", paramBean.getCreateUserId()),
				columnRowMapper);
		conflictBean.setCrsActyId(crsActvityId);
		conflictBean.setOffenderStartDate(lvDate);
		final ProgramsNonAssocTmp nonAss = checkConflict(conflictBean);
		conflictBean.setWarningPrompt(nonAss.getWarningPrompt());
		conflictBean.setWarningMsg(nonAss.getWarningMsg());

		return conflictBean;
	}

	private ProgramsNonAssocTmp checkConflict(final OffenderProgramProfiles bean) throws ParseException {
		final ProgramsNonAssocTmp returnBean = new ProgramsNonAssocTmp();
		int i = 0;
		final List<Offenders> offList = new ArrayList<>();
		List<Offenders> tagcoreList = getOffDetails(bean);
		List<Offenders> beanone = getNaPrgSrv(bean);
		List<Offenders> beantwo = getStgNaPrgSrv(bean);
		if (!beanone.isEmpty()) {
			offList.addAll(beanone);
		}
		if (!beantwo.isEmpty()) {
			offList.addAll(beantwo);
		}

		if (!offList.isEmpty() && !tagcoreList.isEmpty()) {
			offList.addAll(tagcoreList);
			for (Offenders beans : offList) {
				if (i == 0) {
					returnBean.setWarningMsg("A non-association linkage exists between " + beans.getLastName() + ","
							+ beans.getFirstName() + " " + beans.getOffenderIdDisplay());
				} else {
					returnBean.setWarningMsg(returnBean.getWarningMsg() + " and " + beans.getLastName() + ","
							+ beans.getFirstName() + " " + beans.getOffenderIdDisplay());
				}
				i = 1;
			}
			returnBean.setWarningMsg(returnBean.getWarningMsg() + " in this location.");
			returnBean.setWarningPrompt(
					"Before proceeding with location change investigate possible risk. Only proceed if you are satisfied with the risk. Do you wish to proceed ?");
		} else {
			return returnBean;
		}
		return returnBean;
	}

	@Override
	public Map<String, Object> chkAllocated(final OffenderProgramProfiles inputObj) {
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_CRS_ACTY_ID", Types.NUMERIC),

				new SqlParameter("P_OFFENDER_BOOK_ID", Types.NUMERIC),
				new SqlParameter("P_OFFENDER_START_DATE", Types.DATE),
				new SqlOutParameter("RETURN_VALUE", Types.NUMERIC) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PRISON_ACTIVITIES").withFunctionName("CHK_ALLOCATED")
				.declareParameters(sqlParameters);

		inParamMap.put("P_CRS_ACTY_ID", inputObj.getCrsActyId());
		inParamMap.put("P_OFFENDER_BOOK_ID", inputObj.getOffenderBookId());
		inParamMap.put("P_OFFENDER_START_DATE", inputObj.getOffenderStartDate());

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);

		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnObject;
	}

	@Override
	public Long getOffenderId(final Long offenderBookId, String userId) {

		final String sql = getQuery("OIDPAWLI_GET_OFFENDER_ID");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderBookId", offenderBookId, "USERID", userId), Long.class);
	}

	@Override
	public Long futureDays(final Date offenderStartDate) {
		SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
		String starDays = sf.format(offenderStartDate);

		final String sql = getQuery("OIDPAWLI_GET_FUTURE_DAYS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderStartDate", starDays), Long.class);
	}

	@Override
	public Map<String, Object> bulkUpdate(final OffenderProgramProfiles inputObj) {
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", Types.NUMERIC),
				new SqlParameter("P_CRS_ACTY_ID", Types.NUMERIC), };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PRISON_ACTIVITIES").withProcedureName("BULK_UPDATE")
				.declareParameters(sqlParameters);

		inParamMap.put("P_OFFENDER_BOOK_ID", inputObj.getOffenderBookId());
		inParamMap.put("P_CRS_ACTY_ID", inputObj.getCrsActyId());

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);

		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnObject;
	}

	@Override
	public Offenders getOffernderDataWithOffenderBookId(final BigDecimal offenderBookId) {
		final String sql = getQuery("CKECK_CONFLICT_GET_OFF_DETAILS_OFFENDER_BOOK_ID");
		final RowMapper<Offenders> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, offeProgProfMap);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
				mRowMapper);
	}

	@Override
	public List<Offenders> getNonAssocationOffenderDetailsForInd(OffenderProgramProfiles bean,
			List<Long> offenderIdsList) {
		List<Offenders> returnObj = new ArrayList<Offenders>();

		final String sql = getQuery("OIDPAWLI_CKECK_CONFLICT_BY_INDIVIDUAL");
		final RowMapper<Offenders> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, offeProgProfMap);
		try {
			returnObj = namedParameterJdbcTemplate.query(sql,
					createParams("offenderId", bean.getOffenderId(), "offenderIdsList", offenderIdsList), mRowMapper);
		} catch (Exception e) {

			logger.error("getNonAssocationOffenderDetailsForInd :", e);
			returnObj = null;
		}
		return returnObj;
	}

	@Override
	public List<Offenders> getNonAssocationOffenderDetailsForGang(OffenderProgramProfiles bean,
			List<Long> offenderIdsList) {
		List<Offenders> returnList = new ArrayList<Offenders>();
		final String sql = getQuery("OIDPAWLI_CKECK_CONFLICT_BY_GANG");
		final RowMapper<Offenders> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, offeProgProfMap);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("offenderBookId", bean.getOffenderBookId(), "offendersList", offenderIdsList),
					mRowMapper);
		} catch (Exception e) {
			returnList = Collections.emptyList();
		}
		return returnList;
	}

}
