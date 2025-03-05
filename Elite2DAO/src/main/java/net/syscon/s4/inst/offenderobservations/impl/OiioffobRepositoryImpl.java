package net.syscon.s4.inst.offenderobservations.impl;

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
import net.syscon.s4.inst.offenderobservations.OiioffobRepository;
import net.syscon.s4.inst.offenderobservations.beans.OffenderObservationInquiry;

@Repository
public class OiioffobRepositoryImpl extends RepositoryBase implements OiioffobRepository {
	private static Logger logger = LogManager.getLogger(OiioffobRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> observationInquiryMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("observation_type", new FieldMapper("observationType")).put("frequency", new FieldMapper("frequency"))
			.put("CHECK_DATE", new FieldMapper("checkDate")).put("CHECK_TIME", new FieldMapper("checkTime"))
			.put("LAST_NAME", new FieldMapper("lastName")).put("FIRST_NAME", new FieldMapper("firstName"))
			.put("living_unit_description", new FieldMapper("livingUnitDescription"))
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("check_id", new FieldMapper("checkId"))
			.put("obs_period_id", new FieldMapper("obsPeriodId"))
			.put("over_due_flag", new FieldMapper("overDueFlag"))		
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId")).build();

	@Override
	public List<OffenderObservationInquiry> getOffenderPeriodInquiryQuery(OffenderObservationInquiry searchBean) {
		final String sql = getQuery("OIIOFFOB_GET_OBSERVATION_PERIOD_INQUIRY_DATA");
		ArrayList<OffenderObservationInquiry> returnList = new ArrayList<OffenderObservationInquiry>();
		try {
			final RowMapper<OffenderObservationInquiry> observationTypesRowMpr = Row2BeanRowMapper.makeMapping(sql,
					OffenderObservationInquiry.class, observationInquiryMapping);
			returnList = (ArrayList<OffenderObservationInquiry>) namedParameterJdbcTemplate.query(sql,
					createParams("observationType", searchBean.getObservationType(), "agyLocId",
							searchBean.getAgyLocId(), "zoneCode", searchBean.getZoneId(), "overDueFlag",
							searchBean.getOverDueFlag()),
					observationTypesRowMpr);
		} catch (Exception e) {
			logger.error("getOffenderPeriodInquiryQuery", e);
		}
		return returnList;
	}

}
