package net.syscon.s4.inmate.trust.trustaccounts.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.trust.trustaccounts.OtucpayeRepository;
/**
 * Class OtucpayeRepositoryImpl
 */
@Repository
public class OtucpayeRepositoryImpl extends RepositoryBase implements OtucpayeRepository{


private final Map<String, FieldMapper> corporatesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("ACCOUNT_TERM_CODE", 				new FieldMapper("accountTermCode"))
.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 				new FieldMapper("createDatetime"))
.put("FEI_NUMBER", 						new FieldMapper("feiNumber"))
.put("MODIFY_DATETIME", 				new FieldMapper("modifyDatetime"))
.put("SUSPENDED_FLAG", 					new FieldMapper("suspendedFlag"))
.put("COMMENT_TEXT", 					new FieldMapper("commentText"))
.put("MAXIMUM_PURCHASE_AMOUNT", 		new FieldMapper("maximumPurchaseAmount"))
.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
.put("MEMO_TEXT", 						new FieldMapper("memoText"))
.put("SUSPENDED_DATE", 					new FieldMapper("suspendedDate"))
.put("ACTIVE_FLAG", 					new FieldMapper("activeFlag"))
.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
.put("USER_ID", 						new FieldMapper("userId"))
.put("MINIMUM_PURCHASE_AMOUNT", 		new FieldMapper("minimumPurchaseAmount"))
.put("CONTACT_PERSON_NAME", 			new FieldMapper("contactPersonName"))
.put("CASELOAD_ID", 					new FieldMapper("caseloadId"))
.put("CORPORATE_NAME", 					new FieldMapper("corporateName"))
.put("START_DATE", 						new FieldMapper("startDate"))
.put("TAX_NO", 						    new FieldMapper("taxNo"))
.put("SHIPPING_TERM_CODE", 				new FieldMapper("shippingTermCode"))
.put("CREATED_DATE", 					new FieldMapper("createdDate"))
.put("EXPIRY_DATE", 					new FieldMapper("expiryDate"))
.put("UPDATED_DATE", 					new FieldMapper("updatedDate"))
.put("CORPORATE_ID", 					new FieldMapper("corporateId"))
.put("CORPORATE_TYPE", 					new FieldMapper("corporateType"))
.build();
private final Map<String, FieldMapper> phonesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("OWNER_ID", 						new FieldMapper("ownerId"))
.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
.put("PHONE_ID", 						new FieldMapper("phoneId"))
.put("OWNER_CLASS", 					new FieldMapper("ownerClass"))
.put("OWNER_CODE", 						new FieldMapper("ownerCode"))
.put("EXT_NO", 							new FieldMapper("extNo"))
.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
.put("OWNER_SEQ", 						new FieldMapper("ownerSeq"))
.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 				new FieldMapper("createDatetime"))
.put("PHONE_TYPE", 						new FieldMapper("phoneType"))
.put("PHONE_NO", 						new FieldMapper("phoneNo"))
.put("MODIFY_DATETIME", 				new FieldMapper("modifyDatetime"))
.build();

	/**
	 * Creates new OtucpayeRepositoryImpl class Object
	 */
	public OtucpayeRepositoryImpl() {
		// OtucpayeRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            Corporates
	 *
	 * @return List<Corporates>
	 *
	 * @throws SQLException
	 */
	public List<Corporates> corpExecuteQuery(final Corporates objSearchDao) {
		final String sql = getQuery("OTUCPAYE_CORP_FIND_CORPORATES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao != null && objSearchDao.getCorporateName() != null) {
				sqlQuery.append(" CORPORATE_NAME = :corporateName  " + " and ");
				params.addValue("corporateName", objSearchDao.getCorporateName());
			}

			if (objSearchDao != null && objSearchDao.getCorporateType() == null) {
				if (objSearchDao.getCorporateId() != null) {
					sqlQuery.append(" active_flag = 'Y' and CORPORATE_ID = :corporateId  ");
					params.addValue("corporateId", objSearchDao.getCorporateId());
				} else {
					sqlQuery.append(
							" active_flag ='Y' and corporate_id IN ( SELECT crp.corporate_id FROM corporates crp, corporate_types ctp  WHERE crp.corporate_id = ctp.corporate_id AND ctp.corporate_type NOT IN ( 'VENDOR' ,'PROGRAM') UNION SELECT corporate_id FROM corporates crp2 WHERE NOT EXISTS ( SELECT 1 FROM corporate_types ctp2 WHERE crp2.corporate_id = ctp2.corporate_id ) )");
				}
			} else {
				sqlQuery.append(
						" corporate_id IN ( SELECT crp.corporate_id FROM corporates crp, corporate_types ctp WHERE crp.corporate_id = ctp.corporate_id AND ctp.corporate_type  =:corporateType ");
				params.addValue("corporateType", objSearchDao.getCorporateType());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		preparedSql = preparedSql + " ORDER BY CORPORATE_NAME ";
		final RowMapper<Corporates> CorporatesRowMapper = Row2BeanRowMapper.makeMapping(preparedSql, Corporates.class,
				corporatesMapping);
		final ArrayList<Corporates> returnList = (ArrayList<Corporates>) namedParameterJdbcTemplate.query(preparedSql,
				params, CorporatesRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * @throws SQLException
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            TagWorkflowBrowseQueue
	 *
	 * @return List<TagWorkflowBrowseQueue>
	 *
	 * @
	 */
	@Override
	public Phones postQuery(final BigDecimal corporateId) {
		final String sql = getQuery("OTUCPAYE_CORP_POST_QUERY");
		final RowMapper<Phones> phonesRowMapper = Row2BeanRowMapper.makeMapping(sql, Phones.class, phonesMapping);
		Phones returnList = new Phones();
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("CORPORATE_ID", corporateId, "CORPORATE_ID", corporateId), phonesRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return returnList;
		}
		return returnList;
	}
	
}
