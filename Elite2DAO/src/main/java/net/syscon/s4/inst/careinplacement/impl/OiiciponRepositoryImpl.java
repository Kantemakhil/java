package net.syscon.s4.inst.careinplacement.impl;

import java.util.ArrayList;
import java.util.Collections;
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
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.careinplacement.OiiciponRepository;
import net.syscon.s4.inst.careinplacement.beans.OffenderCipDetails;


@Repository
public class OiiciponRepositoryImpl extends RepositoryBase implements OiiciponRepository {
	
	private static Logger logger = LogManager.getLogger(OiiciponRepositoryImpl.class.getName());
	
	private final Map<String, FieldMapper> clAgyLocMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_Id", new FieldMapper("agyLocId")).put("DESCRIPTION", new FieldMapper("description"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId")).build();
	private final Map<String, FieldMapper> offCipDetMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DURATION_TYPE", new FieldMapper("durationType")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("AUTH_BY_PER_NAME", new FieldMapper("authByPerName"))
			.put("REQ_BY_PER_CODE", new FieldMapper("reqByPerCode"))
			.put("EFFECTIVE_TIME", new FieldMapper("effectiveTime")).put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.build();
	
	
	public List<AgencyLocations> rgAgyLocsRecordGroup(final String caseloadId) {
		final String sql = getQuery("OIICIPON_FIND_RGAGYLOCS");
		final RowMapper<AgencyLocations> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				clAgyLocMapping);
		List<AgencyLocations> returnList = new ArrayList<AgencyLocations>();
		try {
			returnList = (List<AgencyLocations>) namedParameterJdbcTemplate.query(sql,
					createParams("CASELOADID", caseloadId), mMRowMapper);
		} catch (Exception e) {
			logger.error("In rgAgyLocsRecordGroup method : ", e);
		}
		return returnList;
	}
	
	public List<OffenderCipDetails> offCipDetailsExecuteQuery(final OffenderCipDetails objSearchDao) {
		final String sql = getQuery("OIICIPON_OFFCIPDETAILS_FIND_OFFENDER_CIP_DETAILS");
		final RowMapper<OffenderCipDetails> offCipDetRM = Row2BeanRowMapper.makeMapping(sql, OffenderCipDetails.class,
				offCipDetMapping);
		try {
			return (List<OffenderCipDetails>) namedParameterJdbcTemplate.query(sql, createParams("AGYLOCID",objSearchDao.getAgyLocId(),"PLACEMENTTYPE",
													objSearchDao.getPlacementType(),"PLACEMENTREASONCODE",objSearchDao.getPlacementReasonCode()),offCipDetRM);
		}catch (Exception e) {
			logger.error("In offCipDetailsExecuteQuery method : ", e);
			return Collections.emptyList();
		}
	}

}
