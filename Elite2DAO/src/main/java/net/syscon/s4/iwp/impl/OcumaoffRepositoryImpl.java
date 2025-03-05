package net.syscon.s4.iwp.impl;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.iwp.OcumaoffRepository;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffLocationRoles;

/**
 * Class OcumaoffRepositoryImpl
 * @version 1.0 
 */
@Repository
public class OcumaoffRepositoryImpl extends RepositoryBase implements OcumaoffRepository{

	/**
	 * Creates new OcumaoffRepositoryImpl class Object 
	 */
	public OcumaoffRepositoryImpl() {
		//OcumaoffRepositoryImpl;
	}

	private final Map<String, FieldMapper> StaffLocationRolesMapping = new ImmutableMap.Builder<String, FieldMapper>()
	.put("AGY_LOC_ID", 						new FieldMapper("agyLocId"))
	.put("BIRTHDATE", 						new FieldMapper("birthdate"))
	.put("LAST_NAME", 						new FieldMapper("lastName"))
	.put("FIRST_NAME", 						new FieldMapper("firstName"))
	.put("SAC_STAFF_ID", 					new FieldMapper("sacStaffId"))
	.put("SCHEDULE_TYPE", 					new FieldMapper("scheduleType"))
	.put("ROLE", 							new FieldMapper("role"))
	.put("POSITION", 						new FieldMapper("position"))
	.put("CAL_AGY_LOC_ID",                  new FieldMapper("calAgyLocId"))         
	.put("HOURS_PER_WEEK",                  new FieldMapper("hoursPerWeek"))         
	.put("SUPERVISOR_AGY_LOC_ID",           new FieldMapper("supervisorAgyLocId"))  
	.put("SUPERVISOR_STAFF_ID",             new FieldMapper("supervisorStaffId"))    
	.put("SUPERVISOR_FROM_DATE",            new FieldMapper("supervisorFromDate"))   
	.put("SUPERVISOR_POSITION",             new FieldMapper("supervisorPosition"))    
	.put("SUPERVISOR_ROLE",                 new FieldMapper("supervisorRole"))        
	.put("FROM_DATE",                       new FieldMapper("fromDate"))  
	.put("TO_DATE",                         new FieldMapper("toDate"))    
	.build();
	
	@Override
	public List<StaffLocationRoles> addOfficerExecuteQuery(final StaffLocationRoles searchBean) {
		final String sql = getQuery("OCUMAOFF_EXECUTE_QUERY");
		final RowMapper<StaffLocationRoles> StaffLocationRolesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				StaffLocationRoles.class, StaffLocationRolesMapping);
		final ArrayList<StaffLocationRoles> returnList = (ArrayList<StaffLocationRoles>) namedParameterJdbcTemplate
				.query(sql,
						createParams("sacStaffId", searchBean.getSacStaffId(), "agyLocId", searchBean.getCalAgyLocId()),
						StaffLocationRolesRowMapper);
		return returnList;
	}

	@Override
	public Integer updateSupervosor(final List<StaffLocationRoles> updateList) {
		String sql = getQuery("OCUMAOFF_UPDATE_SUPERVOSOR");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StaffLocationRoles staffLocationRoles : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(staffLocationRoles));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

}
