package net.syscon.s4.inst.incidentsoic.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentRepairs;
import net.syscon.s4.inst.incidentsoic.OiuincrpRepository;

@Repository
public class OiuincrpRepositoryImpl extends RepositoryBase implements OiuincrpRepository{

private final Map<String, FieldMapper> agencyIncidentRepairsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("COMMENT_TEXT", 						new FieldMapper("commentText"))
.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
.put("REPAIR_SEQ", 						new FieldMapper("repairSeq"))
.put("AGENCY_INCIDENT_ID", 						new FieldMapper("agencyIncidentId"))
.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
.put("REPAIR_COST", 						new FieldMapper("repairCost"))
.put("CREATE_DATETIME", 						new FieldMapper("createDateTime"))
.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
.put("MODIFY_DATETIME", 						new FieldMapper("modifyDateTime"))
.put("REPAIR_TYPE", 						new FieldMapper("repairType"))
.build();

	/**
	 * Creates new OiuincrpRepositoryImpl class Object
	 */
	public OiuincrpRepositoryImpl() {
		
	}

	/**
	 * @param AgencyIncidentRepairs
	 *            objSearchDao
	 *
	 * @return List<AgencyIncidentRepairs>
	 *
	 * @throws SQLException
	 */
	public List<AgencyIncidentRepairs> agyIncExecuteQuery(final AgencyIncidentRepairs objSearchDao)  {
		final String sql = getQuery("OIUINCRP_AGYINC_FIND_AGENCY_INCIDENT_REPAIRS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");		
		if (objSearchDao.getAgencyIncidentId() != null) {
			sqlQuery.append("AGENCY_INCIDENT_ID = :agencyIncidentId " + " and ");
			valuesList.addValue("agencyIncidentId", objSearchDao.getAgencyIncidentId());
		}
		if (objSearchDao.getRepairSeq() != null) {
			sqlQuery.append("REPAIR_SEQ = :repairSeq " + " and ");
			valuesList.addValue("repairSeq", objSearchDao.getRepairSeq());
		}
		if (objSearchDao.getRepairType() != null) {
			sqlQuery.append("REPAIR_TYPE = :repairType " + " and ");
			valuesList.addValue("repairType", objSearchDao.getRepairType());
		}
		if (objSearchDao.getCommentText() != null) {
			sqlQuery.append("COMMENT_TEXT = :commentText " + " and ");
			valuesList.addValue("commentText", objSearchDao.getCommentText());
			
		}
		if (objSearchDao.getModifyUserId() != null) {
			sqlQuery.append("MODIFY_USER_ID = :modifyUserId " + " and ");
			valuesList.addValue("modifyUserId", objSearchDao.getModifyUserId());
			
		}
		if (objSearchDao.getModifyDateTime() != null) {
			sqlQuery.append("MODIFY_DATETIME = :modifyDateTime " + " and ");
			valuesList.addValue("modifyDateTime", objSearchDao.getModifyDateTime());
		}
		if (objSearchDao.getRepairCost() != null) {
			sqlQuery.append("REPAIR_COST = :repairCost " + " and ");
			valuesList.addValue("repairCost", objSearchDao.getRepairCost());
		}
		if (objSearchDao.getCreateDateTime() != null) {
			sqlQuery.append("CREATE_DATETIME = :createDateTime " + " and ");
			valuesList.addValue("createDateTime", objSearchDao.getCreateDateTime());
		}
		if (objSearchDao.getCreateUserId() != null) {
			sqlQuery.append("CREATE_USER_ID = :createUserId " + " and ");
			valuesList.addValue("createUserId", objSearchDao.getCreateUserId());
		}
		if (objSearchDao.getSealFlag() != null) {
			sqlQuery.append("SEAL_FLAG = :sealFlag " + " and ");
			valuesList.addValue("sealFlag", objSearchDao.getSealFlag());
		}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		final RowMapper<AgencyIncidentRepairs> AgencyIncidentRepairsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyIncidentRepairs.class, agencyIncidentRepairsMapping);
		List<AgencyIncidentRepairs> returnList=new ArrayList<AgencyIncidentRepairs>();
		 returnList = (ArrayList<AgencyIncidentRepairs>) namedParameterJdbcTemplate
				.query(preparedSql, valuesList, AgencyIncidentRepairsRowMapper);
		return returnList;
	}

	

	
}
