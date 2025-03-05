package net.syscon.s4.globaloffenderrecords.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.globaloffenderrecords.OcualertRepository;
import net.syscon.s4.im.beans.OffenderAlerts;
/**
 * Class OcualertRepositoryImpl
 */
@Repository
public class OcualertRepositoryImpl extends RepositoryBase implements OcualertRepository{

/**
 * Creates new OcualertRepositoryImpl class Object 
 */
public OcualertRepositoryImpl() {
	// OcualertRepositoryImpl
}
private final Map<String, FieldMapper> offenderAlertsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("OFFENDER_BOOK_ID", 			new FieldMapper("offenderBookId"))
.put("COMMENT_TEXT", 				new FieldMapper("commentText"))
.put("ALERT_TYPE", 					new FieldMapper("alertType"))
.put("VERIFIED_FLAG", 				new FieldMapper("verifiedFlag"))
.put("CREATE_USER_ID", 				new FieldMapper("createUserId"))
.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
.put("ROOT_OFFENDER_ID", 			new FieldMapper("rootOffenderId"))
.put("ALERT_STATUS", 				new FieldMapper("alertStatus"))
.put("AUTHORIZE_PERSON_TEXT", 		new FieldMapper("authorizePersonText"))
.put("CASELOAD_TYPE", 				new FieldMapper("caseloadType"))
.put("CASELOAD_ID", 				new FieldMapper("caseloadId"))
.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 			new FieldMapper("createDatetime"))
.put("MODIFY_DATETIME", 			new FieldMapper("modifyDatetime"))
.put("ALERT_DATE", 				    new FieldMapper("alertDate"))
.put("CREATE_DATE", 				new FieldMapper("createDate"))
.put("ALERT_SEQ", 					new FieldMapper("alertSeq"))
.put("EXPIRY_DATE", 				new FieldMapper("expiryDate"))
.put("ALERT_CODE", 					new FieldMapper("alertCode"))
.build();
public final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DESCRIPTION",   new FieldMapper("description"))
.put("CODE",          new FieldMapper("code"))
.put("PARENT_CODE",   new FieldMapper("parentCode"))
.put("PARENT_DOMAIN", new FieldMapper("parentDomain"))
.put("DOMAIN",        new FieldMapper("domain"))
.put("LIST_SEQ",      new FieldMapper("listSeq"))
.build();
	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderAlerts
	 *
	 * @return List<OffenderAlerts>
	 *
	 */
	public List<OffenderAlerts> alertExecuteQuery(final OffenderAlerts objSearchDao) {
		final String sql = getQuery("OCUALERT_ALERT_FIND_OFFENDER_ALERTS");
		final RowMapper<OffenderAlerts> offenderAlertsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAlerts.class, offenderAlertsMapping);
		final ArrayList<OffenderAlerts> returnList = (ArrayList<OffenderAlerts>) namedParameterJdbcTemplate.query(sql,
				createParams("OFFENDER_BOOK_ID",objSearchDao.getOffenderBookId()), offenderAlertsRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 */
	public int preInsert(){
		return 0;
	}
	/**
	 * @param
	 *
	 */
	public ReferenceCodes postQuery(final ReferenceCodes searchBean){
		final String sql = getQuery("OCUALERT_FIND_REFERENCE_CODES");
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		final ReferenceCodes returnList =  (ReferenceCodes) namedParameterJdbcTemplate.queryForObject(sql,
				createParams("DOMAIN",searchBean.getDomain(), "CODE",searchBean.getCode()), referenceCodesRowMapper);
		return returnList;
		
	}
	/**
	 * @param
	 *
	 */
	public List<ReferenceCodes> rgAlertDescription(final String domain){
		final String sql = getQuery("OCUALERT_FIND_REFERENCE_DESCRIPTION");
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		final ArrayList<ReferenceCodes> returnList =  (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
				createParams("DOMAIN",domain), referenceCodesRowMapper);
		return returnList;
		
	}
}
