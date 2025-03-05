package net.syscon.s4.pkgs.oidcoasi.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.OidcoasiOffenderAssignments;
import net.syscon.s4.pkgs.oidcoasi.OidcoasiPkgRepository;

@Repository
public class OidcoasiPkgRepositoryImpl extends RepositoryBase implements OidcoasiPkgRepository {

	private static Logger logger = LogManager.getLogger(OidcoasiPkgRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("abv", new FieldMapper("")).build();
	
	private final Map<String, FieldMapper> assignQueryMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderId"))
			.put("LAST_NAME", new FieldMapper("lastName"))
			.put("FIRST_NAME", new FieldMapper("firstName"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookingId"))
			.put("DESCRIPTION", new FieldMapper("agyLocDescription"))
			.put("CASE_OFFICER_ID", new FieldMapper("caseOfficerId"))
			.put("STAFF_LAST_NAME", new FieldMapper("staffLastName"))
			.put("STAFF_FIRST_NAME", new FieldMapper("staffFirstName"))
			.put("CASE_ASSIGNED_DATE", new FieldMapper("caseAssignedDate"))
			.put("CASE_ASSIGNED_TIME", new FieldMapper("caseAssignedTime"))
			.put("CONFIRM_FLAG", new FieldMapper("confirmationFlag"))
			.put("CASE_AGY_LOC_ID", new FieldMapper("agyLocId")).build();

	@Override
	public BigDecimal getLivingUnitIdFmLU(final String pDesc) {
		final String sql = getQuery("GET_LIVING_UNIT_ID");
		BigDecimal retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_desc", pDesc), BigDecimal.class);
		} catch (Exception e) {
			logger.error("Exception in Class " + this.getClass().getName() + "getLivingUnitId",e);
		}
		return retVal;
	}

	@Override
	public List<OidcoasiOffenderAssignments> getoffAssigQuerySelectOne(final OidcoasiOffenderAssignments offAssign) {
		final String sql = getQuery("OFFENDER_ASSIGNMENTS_QUERY_SELECT_ONE");
		List<OidcoasiOffenderAssignments> retObj = new ArrayList<OidcoasiOffenderAssignments>(); 
		final RowMapper<OidcoasiOffenderAssignments> assignQueryRowMapper = Row2BeanRowMapper
				.makeMapping(sql, OidcoasiOffenderAssignments.class, assignQueryMapping);
		try {
			retObj = namedParameterJdbcTemplate.query(sql,
					createParams("p_confirm_flag", offAssign.getConfirmationAllFlag(), "p_agy_loc_id", offAssign.getAgyLocId(), "p_offender_id_display",
							offAssign.getOffenderId(), "p_off_last_name", offAssign.getLastName(), "p_off_first_name", offAssign.getFirstName(),
							"v_living_unit_desc", offAssign.getvLivingUnitDesc(), "v_living_unit_id", offAssign.getvLivingUnitId(),
							"v_case_officer_id", offAssign.getvCaseOfficerId()),
					assignQueryRowMapper);
		} catch (Exception e) {
			logger.error("Exception in Class " + this.getClass().getName() + "getoffAssigQuerySelectOne :", e);
		}
		return retObj;
	}

	@Override
	public List<OidcoasiOffenderAssignments> getoffAssigQuerySelectSecond(final OidcoasiOffenderAssignments offAssign) {
		final String sql = getQuery("OFFENDER_ASSIGNMENTS_QUERY_SELECT_SECOND");
		List<OidcoasiOffenderAssignments> retObj =  new ArrayList<OidcoasiOffenderAssignments>(); 
		final RowMapper<OidcoasiOffenderAssignments> assignQueryRowMapper = Row2BeanRowMapper
				.makeMapping(sql, OidcoasiOffenderAssignments.class, assignQueryMapping);
		try {
			retObj = namedParameterJdbcTemplate.query(sql,
					createParams("p_confirm_flag", offAssign.getConfirmationAllFlag(), "p_offender_id_display", offAssign.getOffenderId(),
							"p_off_last_name", offAssign.getLastName(), "p_off_first_name", offAssign.getFirstName(), "v_living_unit_desc",
							offAssign.getvLivingUnitDesc(), "v_living_unit_id", offAssign.getvLivingUnitId(), "p_agy_loc_id",  offAssign.getAgyLocId(),
							"p_current_officer_id", offAssign.getCurrentOfficerStaffId(), "p_case_officer_id", offAssign.getCurrentOfficerStaffId()),
					assignQueryRowMapper);
		} catch (Exception e) {
			logger.error("Exception in Class " + this.getClass().getName() + "getoffAssigQuerySelectSecond :", e);
		}
		return retObj;
	}

	@Override
	public List<OidcoasiOffenderAssignments> getoffAssigQuerySelectThird(final OidcoasiOffenderAssignments offAssign) {
		final String sql = getQuery("OFFENDER_ASSIGNMENTS_QUERY_SELECT_THIRD");
		List<OidcoasiOffenderAssignments> retObj =  new ArrayList<OidcoasiOffenderAssignments>(); 
		final RowMapper<OidcoasiOffenderAssignments> assignQueryRowMapper = Row2BeanRowMapper
				.makeMapping(sql, OidcoasiOffenderAssignments.class, assignQueryMapping);
		try {
			retObj = namedParameterJdbcTemplate.query(sql,
					createParams("p_confirm_flag", offAssign.getConfirmationAllFlag(), "p_offender_id_display", offAssign.getOffenderId(),
							"p_off_last_name", offAssign.getLastName(), "p_off_first_name", offAssign.getFirstName(), "v_living_unit_desc",
							offAssign.getvLivingUnitDesc(), "v_living_unit_id", offAssign.getvLivingUnitId(), "p_agy_loc_id",  offAssign.getAgyLocId()),
					assignQueryRowMapper);
		} catch (Exception e) {
			logger.error("Exception in Class " + this.getClass().getName() + "getoffAssigQuerySelectThird :", e);
		}
		return retObj;
	}

}