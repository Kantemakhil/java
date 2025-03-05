package net.syscon.s4.inmate.trust.trustaccounts.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderSubAcShadows;
import net.syscon.s4.im.beans.VTrustHeader;
import net.syscon.s4.inmate.trust.trustaccounts.OtusubadRepository;

/**
 * Class OtusubadRepositoryImpl
 */
@Repository
public class OtusubadRepositoryImpl extends RepositoryBase implements OtusubadRepository {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtusubadRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> vTrustHeaderMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID_DISPLAY", 			new FieldMapper("offenderIdDisplay"))
			.put("OFFICER", 						new FieldMapper("officer"))
			.put("SUFFIX", 							new FieldMapper("suffix"))
			.put("PRISON_LOCATION", 				new FieldMapper("prisonLocation"))
			.put("DISCLOSURE_FLAG", 				new FieldMapper("disclosureFlag"))
			.put("ROOT_OFFENDER_ID", 				new FieldMapper("rootOffenderId"))
			.put("LIVING_UNIT_ID", 					new FieldMapper("livingUnitId"))
			.put("COMMUNITY_STATUS", 				new FieldMapper("communityStatus"))
			.put("COMMISSARY_TRUST_CASELOAD", 		new FieldMapper("commissaryTrustCaseload"))
			.put("MIDDLE_NAME", 					new FieldMapper("middleName"))
			.put("STATUS_3", 						new FieldMapper("status3"))
			.put("LIVING_UNIT_DESCRIPTION", 		new FieldMapper("livingUnitDescription"))
			.put("IN_OUT_STATUS", 					new FieldMapper("inOutStatus"))
			.put("OFFENDER_ID", 					new FieldMapper("offenderId"))
			.put("LAST_NAME", 						new FieldMapper("lastName"))
			.put("STATUS_1", 						new FieldMapper("status1"))
			.put("INTAKE_AGY_LOC_ID", 				new FieldMapper("intakeAgyLocId"))
			.put("MOVEMENT_REASON", 				new FieldMapper("movementReason"))
			.put("OFFENDER_BOOK_ID", 				new FieldMapper("offenderBookId"))
			.put("INDIGENT_FLAG", 					new FieldMapper("indigentFlag"))
			.put("ACTIVE_FLAG", 					new FieldMapper("activeFlag"))
			.put("BOOKING_BEGIN_DATE", 				new FieldMapper("bookingBeginDate"))
			.put("TRUST_CASELOAD_ID", 				new FieldMapper("trustCaseloadId"))
			.put("STATUS_2", 						new FieldMapper("status2"))
			.put("STATUS_REASON", 					new FieldMapper("statusReason"))
			.put("STATUS_DISPLAY", 					new FieldMapper("statusDisplay"))
			.put("OFF_SUP_LEVEL", 					new FieldMapper("offSupLevel"))
			.put("ALIAS_OFFENDER_ID", 				new FieldMapper("aliasOffenderId"))
			.put("CASELOAD_ID", 					new FieldMapper("caseloadId"))
			.put("BOOKING_TYPE", 					new FieldMapper("bookingType"))
			.put("BOOKING_END_DATE", 				new FieldMapper("bookingEndDate"))
			.put("COMMUNITY_ACTIVE_FLAG", 			new FieldMapper("communityActiveFlag"))
			.put("AGY_LOC_ID", 						new FieldMapper("agyLocId"))
			.put("ACCOUNT_CLOSED_FLAG", 			new FieldMapper("accountClosedFlag"))
			.put("BOOKING_NO", 						new FieldMapper("bookingNo"))
			.put("COMMUNITY_TRUST_CASELOAD", 		new FieldMapper("communityTrustCaseload"))
			.put("HEADER_STATUS", 					new FieldMapper("headerStatus"))
			.put("AGY_LOC_TYPE", 					new FieldMapper("agyLocType"))
			.put("GENDER", 							new FieldMapper("gender"))
			.put("BIRTH_DATE", 						new FieldMapper("birthDate"))
			.put("FIRST_NAME", 						new FieldMapper("firstName"))
			.put("BOOKING_STATUS", 					new FieldMapper("bookingStatus"))
			.put("CREATE_INTAKE_AGY_LOC_ID", 		new FieldMapper("createIntakeAgyLocId"))
			.put("AGE", 							new FieldMapper("age"))
			.put("OFF_ALERTS", 						new FieldMapper("offAlerts"))
			.put("ETHNICITY", 						new FieldMapper("ethnicity"))
			.build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TRUST_ACCOUNT_CODE", 				new FieldMapper("trustAccountCode"))
			.put("DESCRIPTION", 					new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> offenderSubAcShadowsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASELOAD_ID", 					new FieldMapper("caseloadId"))
			.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
			.put("TRUST_ACCOUNT_CODE", 				new FieldMapper("trustAccountCode"))
			.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 				new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", 				new FieldMapper("modifyDatetime"))
			.put("OFFENDER_ID", 					new FieldMapper("offenderId"))
			.put("TRANSFERED_AMOUNT", 				new FieldMapper("transferedAmount"))
			.put("BALANCE", 						new FieldMapper("balance"))
			.build();

	/**
	 * Creates new OtusubadRepositoryImpl class Object
	 */
	public OtusubadRepositoryImpl() {
		// OtusubadRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VTrustHeader
	 *
	 * @return List<VTrustHeader>
	 *
	 * @throws SQLException
	 */
	public List<VTrustHeader> vThaExecuteQuery(final VTrustHeader objSearchDao) {
		final String sql = getQuery("OTUSUBAD_VTHA_FIND_V_TRUST_HEADER");
		final RowMapper<VTrustHeader> VTrustHeaderRowMapper = Row2BeanRowMapper.makeMapping(sql, VTrustHeader.class,
				vTrustHeaderMapping);
		List<VTrustHeader> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), VTrustHeaderRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderSubAcShadows
	 *
	 * @return List<OffenderSubAcShadows>
	 *
	 * @throws SQLException
	 */
	public List<OffenderSubAcShadows> offSasExecuteQuery(final OffenderSubAcShadows objSearchDao) {
		final String sql = getQuery("OTUSUBAD_OFFSAS_FIND_OFFENDER_SUB_AC_SHADOWS");
		final RowMapper<OffenderSubAcShadows> OffenderSubAcShadowsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSubAcShadows.class, offenderSubAcShadowsMapping);
		List<OffenderSubAcShadows> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("CASELOADID",objSearchDao.getCaseloadId(),"OFFID",objSearchDao.getOffenderId()), OffenderSubAcShadowsRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffSasOffSasRef
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffSasOffSasRef(final ReferenceCodes paramBean) {
		final String sql = getQuery("OTUSUBAD_CGFKCHK_OFF_SAS_OFF_SAS_REF_C");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	@Override
	public String getTrustacntdesc(final String trustAccountCode) {
		final String sql = getQuery("OTUSUBAD_CGFKCHK_OFF_SAS_OFF_SAS_REF_C");
		String trustAcntDesc=null;
		try{
		trustAcntDesc =namedParameterJdbcTemplate.queryForObject(sql, createParams("TRUSTACCOUNTCODE",trustAccountCode), String.class);
		} catch(Exception e ) {
			logger.error("getTrustacntdesc",e);
		}
		return trustAcntDesc;
	}

	@Override
	public List<VTrustHeader> getRootOffenderId(final VTrustHeader searchBean) {
		final String sql = getQuery("OTUSUBAD_GET_ROOT_OFFENDER_ID");
		 List<VTrustHeader> returnList= new ArrayList<VTrustHeader>();
		 final RowMapper<VTrustHeader> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VTrustHeader.class,
					vTrustHeaderMapping);
		 returnList= namedParameterJdbcTemplate.query(sql, createParams("caseloadType",searchBean.getCaseloadType(),"offenderIdDisplay",searchBean.getOffenderIdDisplay()),columnRowMapper);
		 return returnList;
	}
}
