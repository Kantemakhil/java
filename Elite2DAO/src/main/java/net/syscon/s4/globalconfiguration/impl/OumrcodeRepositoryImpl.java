package net.syscon.s4.globalconfiguration.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.ReferenceDomains;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.globalconfiguration.OumrcodeRepository;
import net.syscon.s4.inst.demographicsbiometrics.impl.OidadmisRepositoryImpl;
/**
 * Class OumrcodeRepositoryImpl
 */
@Repository
public class OumrcodeRepositoryImpl extends RepositoryBase implements OumrcodeRepository{

	/**
	 * Creates new OumrcodeRepositoryImpl class Object 
	 */
	public OumrcodeRepositoryImpl() {
	}
	private final Map<String, FieldMapper> referenceDomainsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
			.put("OWNER_CODE", 						new FieldMapper("ownerCode"))
			.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
			.put("OLD_CODE_TABLE", 						new FieldMapper("oldCodeTable"))
			.put("DOMAIN", 						new FieldMapper("domain"))
			.put("APPLN_CODE", 						new FieldMapper("applnCode"))
			.put("CODE_LENGTH", 						new FieldMapper("codeLength"))
			.put("PARENT_DOMAIN", 						new FieldMapper("parentDomain"))
			.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 						new FieldMapper("createDatetime"))
			.put("DOMAIN_STATUS", 						new FieldMapper("domainStatus"))
			.put("MODIFY_DATETIME", 						new FieldMapper("modifyDatetime"))
			.put("DESCRIPTION", 						new FieldMapper("description"))
			.put("SUPER_SET_DOMAIN", 						new FieldMapper("superSetDomain"))
			.put("'1'", 						new FieldMapper("  '1' "))
			.build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
			.put("ACTIVE_FLAG", 						new FieldMapper("activeFlag"))
			.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
			.put("CODE", 						new FieldMapper("code"))
			.put("LIST_SEQ", 						new FieldMapper("listSeq"))
			.put("SYSTEM_DATA_FLAG", 						new FieldMapper("systemDataFlag"))
			.put("DOMAIN", 						new FieldMapper("domain"))
			.put("NEW_CODE", 						new FieldMapper("newCode"))
			.put("EXPIRED_DATE", 						new FieldMapper("expiredDate"))
			.put("PARENT_DOMAIN", 						new FieldMapper("parentDomain"))
			.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 						new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", 						new FieldMapper("modifyDatetime"))
			.put("PARENT_CODE", 						new FieldMapper("parentCode"))
			.put("DESCRIPTION", 						new FieldMapper("description"))
			.put("'1'", 						new FieldMapper("  '1' "))
			.build();

	private static Logger logger = LogManager.getLogger(OidadmisRepositoryImpl.class.getName());


	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao ReferenceDomains
	 *
	 * @return List<ReferenceDomains>
	 *
	 * @
	 */
	public List<ReferenceDomains> refDmnExecuteQuery(ReferenceDomains objSearchDao)  {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OUMRCODE_REFDMN_FIND_REFERENCE_DOMAINS"), referenceDomainsMapping).build();
		String preparedSql = null;
		StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getDomain() != null) {
				sqlQuery.append("DOMAIN = :domain and ");
				params.addValue("domain", objSearchDao.getDomain());
			}
			if (objSearchDao.getDescription() != null) {
				sqlQuery.append("DESCRIPTION = :description and ");
				params.addValue("description", objSearchDao.getDescription());
			}
			if (objSearchDao.getDomainStatus() != null) {
				sqlQuery.append("DOMAIN_STATUS = :domainStatus and ");
				params.addValue("domainStatus", objSearchDao.getDomainStatus());
			}
			if (objSearchDao.getOwnerCode() != null) {
				sqlQuery.append("OWNER_CODE = :ownerCode and ");
				params.addValue("ownerCode", objSearchDao.getOwnerCode());
			}
			if (objSearchDao.getApplnCode() != null) {
				sqlQuery.append("APPLN_CODE = :applnCode and ");
				params.addValue("applnCode", objSearchDao.getApplnCode());
			}
			if (objSearchDao.getParentDomain() != null) {
				sqlQuery.append("PARENT_DOMAIN = :parentDomain and ");
				params.addValue("parentDomain", objSearchDao.getParentDomain());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		sqlQuery.delete(0, sqlQuery.length());
		sqlQuery.append(preparedSql);
		sqlQuery.append(" Order by DOMAIN");
		preparedSql = sqlQuery.toString().trim();
		final RowMapper<ReferenceDomains> ReferenceDomainsRowMapper = Row2BeanRowMapper.makeMapping(preparedSql, ReferenceDomains.class,
				referenceDomainsMapping);
		List<ReferenceDomains> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preparedSql,
				params, ReferenceDomainsRowMapper);

		return returnList;
	} 
	/**
	 * @param 
	 *
	 * @ 
	 *
	 */
	public int PRE_INSERT()  {
		return 0;
	}

	/**
	 *  This method is used to insert the records in the data base tables based on
	 *
	 * @param lstReferenceDomains List<ReferenceDomains>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer refDmnInsertReferenceDomains(final List<ReferenceDomains> lstReferenceDomains)  {
		String sql = getQuery("OUMRCODE_REFDMN_INSERT_REFERENCE_DOMAINS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (ReferenceDomains referenceDomains : lstReferenceDomains) {
			parameters.add(new BeanPropertySqlParameterSource(referenceDomains));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstReferenceDomains.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 *  This method is used to insert the records in the data base tables based on
	 *
	 * @param lstReferenceDomains List<ReferenceDomains>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer refCodeInsertReferenceDomains(final List<ReferenceCodes> lstReferenceCodes)  {
		String sql = getQuery("OUMRCODE_REFCODE_INSERT_REFERENCE_CODES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (ReferenceCodes referenceCodes : lstReferenceCodes) {
			parameters.add(new BeanPropertySqlParameterSource(referenceCodes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstReferenceCodes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstReferenceDomains List<ReferenceDomains>
	 *
	 * @
	 */
	public Integer refDmnUpdateReferenceDomains(final List<ReferenceDomains> lstReferenceDomains)  {
		String sql = getQuery("OUMRCODE_REFDMN_UPDATE_REFERENCE_DOMAINS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (ReferenceDomains referenceDomains : lstReferenceDomains) {
			parameters.add(new BeanPropertySqlParameterSource(referenceDomains));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		}catch (Exception e) {
			logger.error(this.getClass().getName() + " error in refDmnUpdateReferenceDomains " + e);
			if(e!=null && e.getMessage()!=null && e.getMessage().contains("ref_domain_ref_domain_fk1")) {
				return 18;
			}
		}
		if (lstReferenceDomains.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 *  This method is used to insert the records in the data base tables based on
	 *
	 * @param lstReferenceDomains List<ReferenceDomains>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer refCodeUpdateReferenceDomains(final List<ReferenceCodes> lstReferenceCodes)  {
		String sql = getQuery("OUMRCODE_REFCODE_UPDATE_REFERENCE_CODES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (ReferenceCodes referenceCodes : lstReferenceCodes) {
			parameters.add(new BeanPropertySqlParameterSource(referenceCodes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstReferenceCodes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao ReferenceCodes
	 *
	 * @return List<ReferenceCodes>
	 *
	 * @
	 */
	public List<ReferenceCodes> refCodeExecuteQuery(ReferenceCodes objSearchDao)  {
		final String sql = getQuery("OUMRCODE_REFCODE_FIND_REFERENCE_CODES");
		final RowMapper<ReferenceCodes> ReferenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,referenceCodesMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("domain",objSearchDao.getDomain()), 
				ReferenceCodesRowMapper);
		return returnList;
	} 

	/**
	 *  This method is used to insert the records in the data base tables based on
	 *
	 * @param lstReferenceCodes List<ReferenceCodes>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer refCodeInsertReferenceCodes(final List<ReferenceCodes> lstReferenceCodes)  {
		String sql = getQuery("OUMRCODE_REFCODE_INSERT_REFERENCE_CODES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstReferenceCodes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstReferenceCodes List<ReferenceCodes>
	 *
	 * @
	 */
	public Integer refCodeUpdateReferenceCodes(final List<ReferenceCodes> lstReferenceCodes){
		int insertCount=0;
		String sql = getQuery("OUMRCODE_REFCODE_UPDATE_REFERENCE_CODES");
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (ReferenceCodes referenceCodes : lstReferenceCodes) {
			parameters.add(new BeanPropertySqlParameterSource(referenceCodes));
		}
		namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstReferenceCodes.size() == insertCount) {
			return 1;
		} else {
			return 0;
		}

	}


	public List<ReferenceDomains> referenceDomainsMapping(final ReferenceDomains objSearchDao) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OUMRCODE_REFDMN"), referenceDomainsMapping).build();
		String preparedSql = null;
		StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getDomain() != null) {
				sqlQuery.append("Domain = :domain and");
				params.addValue("domain", objSearchDao.getDomain());
			}
			if (objSearchDao.getDescription() != null) {
				sqlQuery.append("Description = :description  " + " and ");
				params.addValue("description", objSearchDao.getDescription());
			}
			if (objSearchDao.getDomainStatus() != null) {
				sqlQuery.append("DomainStatus = :domainStatus  " + " and ");
				params.addValue("domainStatus", objSearchDao.getDomainStatus());
			}
			if (objSearchDao.getOwnerCode() != null) {
				sqlQuery.append("OwnerCode =:ownerCode " + " and ");
				params.addValue("ownerCode", objSearchDao.getOwnerCode());
			}
			if (objSearchDao.getApplnCode() != null) {
				sqlQuery.append("ApplnCode =:applnCode " + " and ");
				params.addValue("applnCode", objSearchDao.getApplnCode());
			}
			if (objSearchDao.getParentDomain() != null) {
				sqlQuery.append("ParentDomain =:parentDomain " + " and ");
				params.addValue("parentDomain", objSearchDao.getParentDomain());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		final RowMapper<ReferenceDomains> ReferenceDomainsRowMapper = Row2BeanRowMapper.makeMapping(preparedSql, ReferenceDomains.class,
				referenceDomainsMapping);
		List<ReferenceDomains> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preparedSql,
				params, ReferenceDomainsRowMapper);
		return returnList;
	}


	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkRefDmnRfeDmnRef
	 *
	 * @param params
	 *
	 */
	public ReferenceDomains cgfkchkRefDmnRfeDmnRef(ReferenceDomains paramBean)  {
		final String sql = getQuery("OUMRCODE_CGFKCHK_REF_DMN_RFE_DMN_REF_D");
		final RowMapper<ReferenceDomains> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceDomains.class,  referenceDomainsMapping);
		ReferenceDomains returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cguvchkReferenceDomainsPk
	 *
	 * @param params
	 *
	 */
	public ReferenceDomains cguvchkReferenceDomainsPk(ReferenceDomains paramBean)  {
		final String sql = getQuery("OUMRCODE_CGUVCHK_REFERENCE_DOMAINS_PK");
		final RowMapper<ReferenceDomains> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceDomains.class,  referenceDomainsMapping);
		ReferenceDomains returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cguvchkReferenceCodesPk
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cguvchkReferenceCodesPk(ReferenceCodes paramBean)  {
		final String sql = getQuery("OUMRCODE_CGUVCHK_REFERENCE_CODES_PK");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class,  referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkReferenceDomains
	 *
	 * @param params
	 *
	 */
	public List<ReferenceDomains> cgrichkReferenceDomains(ReferenceDomains paramBean)  {
		final String sql = getQuery("OUMRCODE_CGRICHK_REFERENCE_DOMAINS");
		final RowMapper<ReferenceDomains> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceDomains.class,  referenceDomainsMapping);
		final ArrayList<ReferenceDomains> returnList = (ArrayList<ReferenceDomains>)namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}
}
