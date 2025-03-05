package net.syscon.s4.pkgs.tag_visits.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VNameSearch2;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.booking.beans.OffenderContactPersons;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.visitsmanagement.beans.VOcuavlocAvailable;
import net.syscon.s4.inst.visitsmanagement.beans.VOcuavlocUnavailable;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisitVisitors;
import net.syscon.s4.inst.visitsmanagement.beans.VisitCycleLimits;
import net.syscon.s4.inst.visitsmanagement.beans.VisitTypeLimits;
import net.syscon.s4.pkgs.tag_visits.TagVisitsRepository;

@Repository
public class TagVisitsRepositoryImpl extends RepositoryBase implements TagVisitsRepository {

	private static Logger logger = LogManager.getLogger(TagVisitsRepositoryImpl.class.getName());

	final Map<String, FieldMapper> visitCycleLimitsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", new FieldMapper("seal_flag")).build();

	private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("WEEK_DAY", new FieldMapper("weekDay"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("AGENCY_VISIT_SLOT_ID", new FieldMapper("agencyVisitSlotId"))
			.put("MAX_GROUPS", new FieldMapper("maxGroups")).put("MAX_ADULTS", new FieldMapper("maxAdults"))
			.put("CAPACITY", new FieldMapper("capacity")).put("GROUPS_BOOKED", new FieldMapper("groupsBooked"))
			.put("TOTAL_BOOKED", new FieldMapper("totalBooked")).put("ADULTS_BOOKED", new FieldMapper("adultsBooked"))
			.put("DESCRIPTION", new FieldMapper("description")).put("START_TIME", new FieldMapper("startTime"))
			.put("END_TIME", new FieldMapper("endTime")).put("VISIT_DATE", new FieldMapper("visitDate")).build();

	// This method to be used for getting CAPACITY.
	@Override
	public Integer getCapacity(final String agyLocId, final Integer internalLocationId) {
		final String sql = getQuery("GET_CAPACITY");
		Integer retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_agy_loc_id", agyLocId, "p_int_loc_id", internalLocationId), Integer.class);
		} catch (Exception e) {
			logger.error("getCapacity", e);
			retVal = null;
		}
		return retVal;
	}

	// this method used for getting DESCRIPTION in AGENCY_INTERNAL_LOCATIONS table.
	@Override
	public String getLocationDesc(final AgencyInternalLocations bean) {
		final String sql = getQuery("DESCRIPTION_CUR");
		String description = null;
		try {
			description = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_agy_loc_id", bean.getAgyLocId(), "p_int_loc_id", bean.getInternalLocationId()),
					String.class);
		} catch (Exception e) {
			logger.error("getLocationDesc :" + e);
		}
		return description;
	}

	@Override
	public Persons getNamesCur(final Long personId) {
		final String sql = getQuery("GET_NAMES_CUR");
		Persons retObj = null;
		final RowMapper<Persons> personsRowMapper = Row2BeanRowMapper.makeMapping(sql, Persons.class, mapping);
		try {
			final ArrayList<Persons> returnList = (ArrayList<Persons>) namedParameterJdbcTemplate.query(sql,
					createParams("p_person_id", personId), personsRowMapper);
			if (returnList != null && returnList.size() > 0)
				retObj = returnList.get(0);
		} catch (Exception e) {
			logger.error("getNamesCur", e);
		}
		return retObj;
	}

	@Override
	public String getParentCodeGlRestrictionCur(final Long personId, final Date pVisitDate) {
		final String sql = getQuery("GL_RESTRICTION_CUR");
		String parentCode = null;
		try {
			parentCode = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_PERSON_ID", personId, "P_VISIT_DATE", pVisitDate), String.class);
		} catch (Exception e) {
			logger.error("getParentCodeGlRestrictionCur :" + e);
		}
		return parentCode;
	}

	@Override
	public Integer getAgeForPersons(final Date pVisitDate) {
		final String sql = getQuery("GET_AGE");
		Integer age = null;
		try {
			age = namedParameterJdbcTemplate.queryForObject(sql, createParams("V_BIRTHDATE", pVisitDate),
					Integer.class);
		} catch (Exception e) {
			logger.error("getAgeForPersons :" + e);
		}
		return age;
	}

	@Override
	public Integer insertOffenderVisitVisitor(final VOffenderVisitVisitors offVisitors, final String userName) {
		final String sql = getQuery("INSERT_OFFENDER_VISIT_VISITOR");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("p_offender_visit_id", offVisitors.getOffenderVisitId());
		inParamMap.put("p_person_id", offVisitors.getPersonId());
		inParamMap.put("p_group_leader_flag", offVisitors.getGroupLeaderFlag());

		inParamMap.put("p_offender_visit_visitor_id", offVisitors.getOffenderVisitVisitorId());
		inParamMap.put("p_comment_text", offVisitors.getCommentText());
		inParamMap.put("p_event_outcome", offVisitors.getEventOutcome());

		inParamMap.put("p_offender_book_id", offVisitors.getOffenderBookId());
		inParamMap.put("createUserId", userName);
		return namedParameterJdbcTemplate.update(sql, inParamMap);
	}

	@Override
	public OffenderContactPersons typrCursor(final OffenderContactPersons bean) {
		final String sql = getQuery("TYPE_CURSOR");
		OffenderContactPersons offContPer = new OffenderContactPersons();
		try {
			offContPer = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_offender_book_id", bean.getOffenderBookId(), "p_contact_offender_root_id",
							bean.getContactRootOffenderId()),
					new BeanPropertyRowMapper<OffenderContactPersons>(OffenderContactPersons.class));
		} catch (Exception e) {
			logger.error("typeCursor" + e);
		}
		return offContPer;
	}

	@Override
	public VNameSearch2 offenderContactCur(final BigDecimal offnderBookId, final String userId) {
		final String sql = getQuery("OFFENDERCONTACT_CUR");
		VNameSearch2 bean = new VNameSearch2();
		try {
			bean = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("v_offender_book_id", offnderBookId, "userId", userId),
					new BeanPropertyRowMapper<VNameSearch2>(VNameSearch2.class));
		} catch (Exception e) {
			logger.error("offenderContactCur" + e);
		}
		return bean;
	}

	@Override
	public ReferenceCodes offenderContactRefCur(final BigDecimal offenderBookId, final Date vPositionDate) {
		final String sql = getQuery("OFFENDER_CONTACTS_REF_CUR");
		ReferenceCodes bean = new ReferenceCodes();
		try {
			bean = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("v_offender_book_id", offenderBookId, "p_visit_date", vPositionDate),
					new BeanPropertyRowMapper<ReferenceCodes>(ReferenceCodes.class));
		} catch (Exception e) {
			logger.error("offenderContactRefCur" + e);
		}
		return bean;
	}

	@Override
	public Offenders birthDateCur(final BigDecimal offenderBookId) {
		final String sql = getQuery("BIRTH_DATE_CUR");
		Offenders bean = new Offenders();
		try {
			bean = namedParameterJdbcTemplate.queryForObject(sql, createParams("v_offender_book_id", offenderBookId),
					new BeanPropertyRowMapper<Offenders>(Offenders.class));
		} catch (Exception e) {
			logger.error("birthDateCur" + e);
		}
		return bean;
	}

	@Override
	public String contactOffenderRestCur(final BigDecimal contactOffenderRootId, final Date visitDate) {
		final String sql = getQuery("CONTACT_OFFENDER_REST_CUR");
		String parentCode = null;
		try {
			parentCode = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("v_offender_book_id", contactOffenderRootId, "p_visit_date", visitDate), String.class);
		} catch (Exception e) {
			logger.error("contactOffenderRestCur :" + e);
		}
		return parentCode;
	}

	@Override
	public Long getNextAgyVisitSlotId() {
		final String sql = getQuery("GET_NEXT_AGY_VISIT_SLOT_ID_SEQUENCE");
		Long seq = null;
		try {
			seq = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return seq;
	}

	@Override
	public String checkTimeslot(final BigDecimal pIntLocId, final String pWeekDay, final String pAgyLocId,
			final String pStartTime) {
		final String sql = getQuery("CHECK_TIMESLOT_CHECK_SLOT");
		String returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_start_time", pStartTime,
					"p_int_loc_id", pIntLocId, "p_week_day", pWeekDay, "p_agy_loc_id", pAgyLocId), String.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnVal;
	}

	@Override
	public List<VOcuavlocAvailable> getOcuAvailable(final VOcuavlocAvailable objSearchDao) {
		final String sql = getQuery("GET_OCUAVLOC_AVAILABLE_SELECT");
		List<VOcuavlocAvailable> retList = new ArrayList<VOcuavlocAvailable>();
		final RowMapper<VOcuavlocAvailable> rowMapper = Row2BeanRowMapper.makeMapping(sql, VOcuavlocAvailable.class,
				mapping);
		try {
			retList = namedParameterJdbcTemplate.query(sql,
					createParams("P_DATE", objSearchDao.getVisitDate(), "P_AGY_LOC_ID", objSearchDao.getAgyLocId(),
							"P_WEEK_DAY", objSearchDao.getWeekDay(), "P_START_TIME", objSearchDao.getStartTime()),
					rowMapper);
		} catch (Exception e) {
			logger.error("getOcuAvailable :", e);
		}
		return retList;
	}

	// this method is used for Getting VOcuavlocUnavailable records.
	@Override
	public List<VOcuavlocUnavailable> getOcuUnavailable(final VOcuavlocUnavailable objSearchDao) {
		final String sql = getQuery("GET_OCUAVLOC_UNAVAILABLE");
		List<VOcuavlocUnavailable> retList = new ArrayList<VOcuavlocUnavailable>();
		final RowMapper<VOcuavlocUnavailable> rowMapper = Row2BeanRowMapper.makeMapping(sql, VOcuavlocUnavailable.class,
				mapping);
		try {
			retList = namedParameterJdbcTemplate.query(sql,
					createParams("P_DATE", objSearchDao.getVisitDate(), "P_AGY_LOC_ID", objSearchDao.getAgyLocId(),
							"P_WEEK_DAY", objSearchDao.getWeekDay(), "P_START_TIME", objSearchDao.getStartTime()),
					rowMapper);
		} catch (Exception e) {
			logger.error("getOcuAvailable :", e);
		}
		return retList;
	}

	@Override
	public VisitCycleLimits getVisitCycleLimits(final Long pAgyId, final String pSecLvl, final Date pVisDt) {
		final String sql = getQuery("GET_VISIT_CYCLE_LIMITS");
		final RowMapper<VisitCycleLimits> visitCycleLimitsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VisitCycleLimits.class, visitCycleLimitsMapping);
		VisitCycleLimits visitCycleLimits = null;
		try {
			visitCycleLimits = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_agy_id", pAgyId, "p_sec_lvl", pSecLvl, "p_vis_dt", pVisDt),
					visitCycleLimitsRowMapper);
		} catch (Exception e) {
			logger.error("getVisitCycleLimits :", e);
		}
		return visitCycleLimits;
	}

	@Override
	public Map<String, Object> getUsedVisCur(final Long pAgyId, final String pSecLvl, final Long pBkgId,
			final Date pCycStart, final Date pCycEnd, final String pVisTp) {
		final String sql = getQuery("GET_USED_VIS_CUR");
		Map<String, Object> map = new HashMap<>();
		try {
			map = namedParameterJdbcTemplate.queryForMap(sql, createParams("p_agy_id", pAgyId, "p_sec_lvl", pSecLvl,
					"p_bkg_id", pBkgId, "p_cyc_start", pCycStart, "p_cyc_end", pCycEnd, "v_vis_tp", pVisTp));
		} catch (Exception e) {
			logger.error("getUsedVisCur :", e);
		}
		return map;
	}

	@Override
	public VisitTypeLimits getTotTypeCur(final Long pLimId, final String pVisTp) {
		final String sql = getQuery("GET_TOT_TYPE_CUR");
		final RowMapper<VisitTypeLimits> visitTypesLimitsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VisitTypeLimits.class, visitCycleLimitsMapping);
		VisitTypeLimits visitTypeLimits = null;
		try {
			visitTypeLimits = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_lim_id", pLimId, "p_vis_tp", pVisTp), visitTypesLimitsRowMapper);
		} catch (Exception e) {
			logger.error("getTotTypeCur :", e);
		}
		return visitTypeLimits;
	}

	@Override
	public Integer updateOffeVisitVisitors(final BigDecimal pOffenderVisitId, final String pVisitStatus,
			final String userName) {
		final String sql = getQuery("UPDATE_OFFENDER_VISIT_VISITORS");
		try {
			namedParameterJdbcTemplate.update(sql, createParams("P_VISIT_STATUS", pVisitStatus, "P_OFFENDER_VISIT_ID",
					pOffenderVisitId, "modifyUserId", userName));
		} catch (DataAccessException e) {
			logger.error("updateOffeVisitVisitors :" + e);
		}
		return 0;
	}

	@Override
	public VisitCycleLimits cycEndCur(final Date pVisDt, final String pAgyId, final String pSecLvl) {
		final String sql = getQuery("CYC_END_CUR");
		VisitCycleLimits returnBean = new VisitCycleLimits();

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String visDate = formatter.format(pVisDt);
		try {
			returnBean = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_vis_dt", visDate, "p_agy_id", pAgyId, "p_sec_lvl", pSecLvl),
					new BeanPropertyRowMapper<VisitCycleLimits>(VisitCycleLimits.class));
		} catch (Exception e) {
			logger.error("cycEndCur", e);
		}
		return returnBean;
	}

	@Override
	public Object[] usedVisCur(final Long pBkgId, final String pAgyId, final String pSecLvl, final String vVisTp,
			final Date pCycStart, final Date pCycEnd) {
		try {
			final String sql = getQuery("USED_VIS_CUR");
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_bkg_id", pBkgId, "p_agy_id", pAgyId,
					"p_sec_lvl", pSecLvl, "v_vis_tp", vVisTp, "p_cyc_start", pCycStart, "p_cyc_end", pCycEnd),
					new RowMapper<Object[]>() {
						public Object[] mapRow(final ResultSet rs, int rowNum) throws SQLException {
							Object[] obj = new Object[2];
							obj[0] = rs.getInt("count");
							obj[1] = rs.getString("time");

							return obj;
						}
					});
		} catch (Exception e) {

			logger.error(e);
			return null;
		}
	}

	@Override
	public VisitTypeLimits totTypeCur(final Long pLimId, final String pVisTp) {
		final String sql = getQuery("TOT_TYPE_CUR");
		VisitTypeLimits retBean = new VisitTypeLimits();
		try {
			retBean = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_lim_id", pLimId, "p_vis_tp", pVisTp),
					new BeanPropertyRowMapper<VisitTypeLimits>(VisitTypeLimits.class));
		} catch (Exception e) {
			logger.error("totTypeCur", e);
		}
		return retBean;
	}

	@Override
	public Integer getContactOffenderBookId(final BigDecimal rootOffenderId, final String userId) {
		final String sql = getQuery("GET_CONTACT_OFFENDER_BOOK_ID");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("p_contact_root_offender_id", rootOffenderId, "userId", userId), Integer.class);
	}

	@Override
	public BigDecimal getRootOffenderIdFromBook(final BigDecimal offBookId) {
		final String sql = getQuery("GET_ROOT_OFFENDER_ID_FROM_BOOK");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_offender_book_id", offBookId),
				BigDecimal.class);
	}

	@Override
	public BigDecimal getNextOffVisitId() {
		final String sql = getQuery("GET_NEXT_OFF_VISIT_ID");
		BigDecimal returnValue=null;
		try {
			returnValue= namedParameterJdbcTemplate.queryForObject(sql, createParams(), BigDecimal.class);
		}catch (Exception e) {
			logger.error("getNextOffVisitId", e);
		}
		return returnValue;
	}

	@Override
	public BigDecimal getEventId() {
		final String sql = getQuery("TAG_VISITS_GET_EVENT_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), BigDecimal.class);
	}

	@Override
	public BigDecimal getNextOffVisitVisitorId() {
		final String sql = getQuery("GET_NEXT_OFF_VISIT_VISITOR_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), BigDecimal.class);
	}

	@Override
	public BigDecimal getOffenderId(final String vstOffIdDisplay) {
		final String sql = getQuery("GET_OFFENDER_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_offender_id_display", vstOffIdDisplay),
				BigDecimal.class);
	}

	@Override
	public BigDecimal getlOffenderBookId(final BigDecimal offenderVisitId, final Integer personId, final Date visitDate,
			final Date startTime, final Date endTime) {
		final String sql = getQuery("GET_L_OFFENDER_BOOK_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_offender_visit_id", offenderVisitId,
				"p_date", visitDate, "p_person_id", personId, "p_start_time", startTime, "p_end_time", endTime),
				BigDecimal.class);
	}

	@Override
	public BigDecimal getlOffenderBookIdOne(final BigDecimal offenderVisitId, final BigDecimal offenderBookId,
			final Date visitDate, final Date startTime, final Date endTime) {
		final String sql = getQuery("GET_L_OFFENDER_BOOK_ID_ONE");
		return namedParameterJdbcTemplate
				.queryForObject(
						sql, createParams("p_offender_visit_id", offenderVisitId, "p_date", visitDate,
								"p_offender_book_id", offenderBookId, "p_start_time", startTime, "p_end_time", endTime),
						BigDecimal.class);
	}

	@Override
	public String getOverlapDetails(final BigDecimal lOffenderBookId) {
		final String sql = getQuery("GET_OVERLAP_DETAILS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("l_offender_book_id", lOffenderBookId),
				String.class);
	}

	// This method is used for Selecting Visitor Details person_cur
	@Override
	public Persons getPersonCur(final Long personId) {
		final String sql = getQuery("PERSON_CUR");
		Persons person = new Persons();
		try {
			person = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_PERSON_ID", personId),
					new BeanPropertyRowMapper<Persons>(Persons.class));
		} catch (Exception e) {
			logger.error("GET_PERSON_CUR :" + e);
		}
		return person;
	}

	// This method is used for Selecting global visitor restriction
	@Override
	public ReferenceCodes restrictionCur(final Long offenderBookId, final Long personId, final Date visitsDate) {
		final String sql = getQuery("RESTRICTION_CUR");
		ReferenceCodes refCodes = new ReferenceCodes();
		try {
			refCodes = namedParameterJdbcTemplate
					.queryForObject(
							sql, createParams("p_offender_book_id", offenderBookId, "p_person_id", personId,
									"p_visit_date", visitsDate),
							new BeanPropertyRowMapper<ReferenceCodes>(ReferenceCodes.class));
		} catch (Exception e) {
			logger.error("RESTRICTION_CUR :" + e);
		}
		return refCodes;
	}

	// This method is used for Selecting Visitor restriction
	@Override
	public ReferenceCodes glRestrictionCur(final Long offenderBookId, final Long personId, final Date visitsDate) {
		final String sql = getQuery("GL_RESTRICTION_CUR_ONE");
		ReferenceCodes refCodes = new ReferenceCodes();
		try {
			refCodes = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_person_id", personId, "p_visit_date", visitsDate),
					new BeanPropertyRowMapper<ReferenceCodes>(ReferenceCodes.class));
		} catch (Exception e) {
			logger.error("GL_RESTRICTION_CUR_ONE :" + e);
		}
		return refCodes;
	}

	// This method is used for Selecting Contact Type, Restriction Type
	@Override
	public List<OffenderContactPersons> typeCur(final Long offenderBookId, final Long personId) {
		final String sql = getQuery("TYPE_CUR");
		List<OffenderContactPersons> refCodes = new ArrayList<OffenderContactPersons>();
		final RowMapper<OffenderContactPersons> visitTypesLimitsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderContactPersons.class, visitCycleLimitsMapping);
		try {
			refCodes = namedParameterJdbcTemplate.query(sql,
					createParams("P_OFFENDER_BOOK_ID", offenderBookId, "P_PERSON_ID", personId),
					visitTypesLimitsRowMapper);
		} catch (Exception e) {
			logger.error("TYPE_CUR :" + e);
		}
		return refCodes;

	}

	// This method is used to get months_between
	@Override
	public Long getpAge(final Date visitDate, final Date birthDate) {
		final String sql = getQuery("SELECT_PAGE");
		Long age = null;
		try {
			age = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_VISIT_DATE", visitDate, "P_BIRTHDATE", birthDate), Long.class);
		} catch (Exception e) {
			logger.error("GET_PAGE :" + e);
		}
		return age;
	}

	@Override
	public Integer updateOffenderVisitVisitors(final Long pOffenderVisitId, final String pOutcomRreasonCode,
			final String userName) {
		final String sql = getQuery("UPDATE_OFFENDER_VISIT_VISITORS");
		return namedParameterJdbcTemplate.update(sql, createParams("P_OUTCOME_REASON_CODE", pOutcomRreasonCode,
				"P_OFFENDER_VISIT_ID", pOffenderVisitId, "modifyUserId", userName));
	}

	@Override
	public Integer updateOffenderVisits(final Long pOffenderVisitId, final String userName) {
		final String sql = getQuery("UPDATE_OFFENDER_VISITS");
		return namedParameterJdbcTemplate.update(sql,
				createParams("P_OFFENDER_VISIT_ID", pOffenderVisitId, "modifyUserId", userName));
	}

	@Override
	public List<Offenders> otherVisitsOffsCur(final Long offenderBookId, final Long offenderVisitId,
			final Date visitDate, final Date startTime, final Date endTime, final Long visitInternalLocationId) {
		final String sql = getQuery("OTHER_VISITS_OFFS_CUR");
		final RowMapper<Offenders> rowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, mapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("p_offender_visit_id", offenderVisitId, "p_visit_date", visitDate, "p_visit_end_time",
						endTime, "p_visit_start_time", startTime, "p_internal_location_id", visitInternalLocationId),
				rowMapper);
	}

	@Override
	public List<Offenders> visitVisitingOffsCur(final Long offenderVisitId) {
		final String sql = getQuery("VISIT_VISITING_OFFS_CUR");
		final RowMapper<Offenders> rowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, mapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_visit_id", offenderVisitId), rowMapper);
	}

	@Override
	public List<Persons> visitPersonsCur(final Long offenderVisitId) {
		final String sql = getQuery("VISIT_PERSONS_CUR");
		final RowMapper<Persons> rowMapper = Row2BeanRowMapper.makeMapping(sql, Persons.class, mapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_visit_id", offenderVisitId), rowMapper);
	}

	@Override
	public List<Persons> otherVisitsPersonsCur(final Long offenderBookId, final Long offenderVisitId,
			final Date visitDate, final Date startTime, final Date endTime, final Long visitInternalLocationId) {
		final String sql = getQuery("VISIT_PERSONS_CUR");
		final RowMapper<Persons> rowMapper = Row2BeanRowMapper.makeMapping(sql, Persons.class, mapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("p_offender_visit_id", offenderVisitId, "p_visit_date", visitDate, "p_visit_end_time",
						endTime, "p_visit_start_time", startTime, "p_internal_location_id", visitInternalLocationId),
				rowMapper);
	}

	@Override
	public String isVictimCur(final Long offenderBookId, final Long personId) {
		final String sql = getQuery("IS_VICTIM_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("p_offender_book_id", offenderBookId, "p_person_id", personId), String.class);
	}

	@Override
	public String visitedOffenderCur(final Long offenderBookId) {
		final String sql = getQuery("VISITED_OFFENDER_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("p_visited_offender_book_id", offenderBookId), String.class);
	}

	@Override
	public String lvNonAssocWarningMessageagess(final String lvNonAssocWarning, final String tbOtherVisitsOffsOffName,
			final String lvVisitedOffName) {
		final String sql = getQuery("LV_NON_ASSOC_WARNING_MESSAGEAGESS");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("LV_NON_ASSOC_WARNING_MESSAGE", lvNonAssocWarning, "TB_OTHER_VISITS_OFFS_OFF_NAME",
						tbOtherVisitsOffsOffName, "LV_VISITED_OFFENDER_NAME", lvVisitedOffName),
				String.class);
	}

	@Override
	public String lvNonAssocWarningMessageagessChk2(final String lvNonAssocWarning,
			final String tbOtherVisitsOffsOffName, final String lvVisitedOffName) {
		final String sql = getQuery("LV_NON_ASSOC_WARNING_MESSAGEAGESS_CHK2");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("LV_NON_ASSOC_WARNING_MESSAGE", lvNonAssocWarning, "TB_VISIT_VISITING_OFFS_NAME",
						tbOtherVisitsOffsOffName, "LV_VISITED_OFFENDER_NAME", lvVisitedOffName),
				String.class);
	}

	@Override
	public String lvNonAssocWarningMessageagessChk3(final String lvNonAssocWarning,
			final String tbOtherVisitsOffsOffName, final String lvVisitedOffName) {
		final String sql = getQuery("LV_NON_ASSOC_WARNING_MESSAGEAGESS_CHK3");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("LV_NON_ASSOC_WARNING_MESSAGE", lvNonAssocWarning, "TB_VISIT_VISITING_OFFS_NAME",
						tbOtherVisitsOffsOffName, "LV_VISITED_OFFENDER_NAME", lvVisitedOffName),
				String.class);
	}

	@Override
	public String lvNonAssocWarningMessageagessChk4(final String lvNonAssocWarning,
			final String tbOtherVisitsOffsOffName, final String lvVisitedOffName) {
		final String sql = getQuery("LV_NON_ASSOC_WARNING_MESSAGEAGESS_CHK4");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("LV_NON_ASSOC_WARNING_MESSAGE", lvNonAssocWarning, "TB_VISIT_VISITING_OFFS_OFF_NAME",
						tbOtherVisitsOffsOffName, "TB_VISIT_VISITING_OFFS_C__OFF_NAME", lvVisitedOffName),
				String.class);
	}

	@Override
	public String lvNonAssocWarningMessageagessChk5(final String lvNonAssocWarning,
			final String tbOtherVisitsOffsOffName, final String lvVisitedOffName) {
		final String sql = getQuery("LV_NON_ASSOC_WARNING_MESSAGEAGESS_CHK5");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("LV_VICTIM_WARNING_MESSAGE", lvNonAssocWarning, "LV_RELATIONSHIP_DESC",
						tbOtherVisitsOffsOffName, "LV_VISITED_OFFENDER_NAME", lvVisitedOffName),
				String.class);
	}

	@Override
	public String lvNonAssocWarningMessageagessChk6(final String lvNonAssocWarning,
			final String tbOtherVisitsOffsOffName, final String lvVisitedOffName, final String lvRelationshiDesc) {
		final String sql = getQuery("LV_NON_ASSOC_WARNING_MESSAGEAGESS_CHK6");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("LV_VICTIM_WARNING_MESSAGE", lvNonAssocWarning, "TB_VISIT_PERSONS_PERS_NAME",
						tbOtherVisitsOffsOffName, "LV_VISITED_OFFENDER_NAME", lvVisitedOffName,
						"LV_VISITED_OFFENDER_NAME", lvRelationshiDesc),
				String.class);
	}

	@Override
	public String lvNonAssocWarningMessageagessChk7(final String lvNonAssocWarning,
			final String tbOtherVisitsOffsOffName, final String lvVisitedOffName, final String lvRelationshiDesc) {
		final String sql = getQuery("LV_NON_ASSOC_WARNING_MESSAGEAGESS_CHK7");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("LV_VICTIM_WARNING_MESSAGE", lvNonAssocWarning, "TB_VISIT_PERSONS_PERS_NAME",
						tbOtherVisitsOffsOffName, "TB_VISIT_VISITING_OFFS_OFF_NAME", lvVisitedOffName,
						"LV_RELATIONSHIP_DESC", lvRelationshiDesc),
				String.class);
	}

	@Override
	public String lvNonAssocWarningMessageagessChk8(final String lvNonAssocWarning,
			final String tbOtherVisitsOffsOffName, final String lvVisitedOffName, final String lvRelationshiDesc) {
		final String sql = getQuery("LV_NON_ASSOC_WARNING_MESSAGEAGESS_CHK8");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("LV_VICTIM_WARNING_MESSAGE", lvNonAssocWarning, "TB_VISIT_PERSONS_PERS_NAME",
						tbOtherVisitsOffsOffName, "TB_OTHER_VISITS_OFFS_OFF_NAME", lvVisitedOffName,
						"LV_RELATIONSHIP_DESC", lvRelationshiDesc),
				String.class);
	}

	@Override
	public String lvNonAssocWarningMessageagessChk9(final String lvNonAssocWarning,
			final String tbOtherVisitsOffsOffName, final String lvVisitedOffName, final String lvRelationshiDesc) {
		final String sql = getQuery("LV_NON_ASSOC_WARNING_MESSAGEAGESS_CHK9");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("LV_VICTIM_WARNING_MESSAGE", lvNonAssocWarning, "TB_OTHER_VISITS_PERSONS_PERS_NAME",
						tbOtherVisitsOffsOffName, "LV_VISITED_OFFENDER_NAME", lvVisitedOffName, "LV_RELATIONSHIP_DESC",
						lvRelationshiDesc),
				String.class);
	}

	@Override
	public String lvNonAssocWarningMessageagessChk10(final String lvNonAssocWarning,
			final String tbOtherVisitsOffsOffName, final String lvVisitedOffName, final String lvRelationshiDesc) {
		final String sql = getQuery("LV_NON_ASSOC_WARNING_MESSAGEAGESS_CHK10");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("LV_VICTIM_WARNING_MESSAGE", lvNonAssocWarning, "TB_OTHER_VISITS_PERSONS_PERS_NAME",
						tbOtherVisitsOffsOffName, "LV_VISITED_OFFENDER_NAME", lvVisitedOffName, "LV_RELATIONSHIP_DESC",
						lvRelationshiDesc),
				String.class);
	}

	@Override
	public String warningMsg(final String lvNonAssociatedWar) {
		final String sql = getQuery("LV_NON_ASSOC_WARNING_MESSAGEAGESS_CHK11");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("LV_NON_ASSOC_WARNING_MESSAGE", lvNonAssociatedWar), String.class);
	}

	@Override
	public String lVictimWarningMsg(final String lvNonAssociatedWar) {
		final String sql = getQuery("LV_NON_ASSOC_WARNING_MSG");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("LV_VICTIM_WARNING_MESSAGE", lvNonAssociatedWar), String.class);
	}

	@Override
	public String lVictimWarningMsg1(final String lvNonAssociatedWar, final String pWarningMsges) {
		final String sql = getQuery("LV_NON_ASSOC_WARNING_MSG1");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("LV_VICTIM_WARNING_MESSAGE", lvNonAssociatedWar, "P_WARNING_MESSAGE", pWarningMsges),
				String.class);
	}

	@Override
	public VisitCycleLimits cycEndCurIep(final Date pVisDt, final String pAgyId, final String iepLevel) {
		final String sql = getQuery("CYC_END_CUR_IEP");
		VisitCycleLimits returnBean = new VisitCycleLimits();

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String visDate = formatter.format(pVisDt);
		try {
			returnBean = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_vis_dt", visDate, "p_agy_id", pAgyId, "iepLevel", iepLevel),
					new BeanPropertyRowMapper<VisitCycleLimits>(VisitCycleLimits.class));
		} catch (Exception e) {
			logger.error("cycEndCur", e);
		}
		return returnBean;
	}

	@Override
	public Object[] usedVisCurIep(final Long pBkgId, final String pAgyId, final String iepLevel, final String vVisTp,
			final Date pCycStart, final Date pCycEnd) {
		final String sql = getQuery("OIMVLIMIT_IEP_USED_VIS_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_bkg_id", pBkgId, "p_agy_id", pAgyId,
					"iepLevel", iepLevel, "v_vis_tp", vVisTp, "p_cyc_start", pCycStart, "p_cyc_end", pCycEnd),
					new RowMapper<Object[]>() {
						public Object[] mapRow(final ResultSet rs, int rowNum) throws SQLException {
							Object[] obj = new Object[2];
							obj[0] = rs.getInt("count");
							obj[1] = rs.getString("time");

							return obj;
						}
					});
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public Object[] usedVisCurIepTotalUsed(final Long pBkgId, final String pAgyId,Date startDate,Date endDate) {
		final String sql = getQuery("OIMVLIMIT_IEP_USED_VIS_CUR_TOTAL_USED");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_bkg_id", pBkgId, "pAgyId", pAgyId,"startDate",startDate,"endDate",endDate),
					new RowMapper<Object[]>() {
						public Object[] mapRow(final ResultSet rs, int rowNum) throws SQLException {
							Object[] obj = new Object[2];
							obj[0] = rs.getInt("count");
							obj[1] = rs.getString("time");

							return obj;
						}
					});
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public VisitTypeLimits totTypeCurIep(final Long pLimId, final String pVisTp) {
		final String sql = getQuery("OIMVLIMIT_IEP_TOT_TYPE_CUR");
		VisitTypeLimits retBean = new VisitTypeLimits();
		try {
			retBean = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_lim_id", pLimId, "p_vis_tp", pVisTp),
					new BeanPropertyRowMapper<VisitTypeLimits>(VisitTypeLimits.class));
		} catch (Exception e) {
			retBean = null;
			logger.error("totTypeCur", e);
		}
		return retBean;
	}

}