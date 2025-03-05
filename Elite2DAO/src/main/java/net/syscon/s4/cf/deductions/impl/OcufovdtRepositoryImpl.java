package net.syscon.s4.cf.deductions.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cf.deductions.OcufovdtRepository;
import net.syscon.s4.cf.deductions.maintenance.beans.FeeOverrideDetails;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;

@Repository
public class OcufovdtRepositoryImpl extends RepositoryBase implements OcufovdtRepository {

    /**
     * Logger object used to print the log in the file
     */
    private static Logger logger = LogManager.getLogger(OcufovdtRepositoryImpl.class);

    private final Map<String, FieldMapper> refMapping = new ImmutableMap.Builder<String, FieldMapper>()
            .put("CODE", new FieldMapper("code"))
            .put("DESCRIPTION", new FieldMapper("description")).build();
    private final Map<String, FieldMapper> fodMapping = new ImmutableMap.Builder<String, FieldMapper>()
            .put("COMMENTS", new FieldMapper("comments"))
            .put("CREATE_DATETIME", new FieldMapper("createDatetime"))
            .put("CREATE_USER_ID", new FieldMapper("createUserId"))
            .put("FEE_ID", new FieldMapper("feeId"))
            .put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
            .put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
            .put("OVERRIDE_AMOUNT", new FieldMapper("overrideAmount"))
            .put("OVERRIDE_END_DATE", new FieldMapper("overrideEndDate"))
            .put("OVERRIDE_START_DATE", new FieldMapper("overrideStartDate"))
            .put("OVERRIDE_TYPE", new FieldMapper("overrideType"))
            .put("PRIORITY_INDICATOR", new FieldMapper("priorityIndicator"))
            .put("ADDED_BY", new FieldMapper("addedBy"))
            .put("ADDED_DATE", new FieldMapper("addedDate"))
            .put("FEE_OVERRIDE_ID", new FieldMapper("feeOverrideId"))
            
            .build();
    private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", 						new FieldMapper("profileCode"))
			.put("PROFILE_TYPE", 						new FieldMapper("profileType"))
			.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
			.put("SEAL_FLAG", 							new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 					new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", 						new FieldMapper("oldTableName"))
			.put("PROFILE_VALUE", 						new FieldMapper("profileValue"))
			.put("MODIFY_DATETIME", 					new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE_2", 					new FieldMapper("profileValue2"))
			.put("DESCRIPTION", 						new FieldMapper("description"))
			.build();

    private final Map<String, FieldMapper> roleIdMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ROLE_ID", 						new FieldMapper("roleId"))
			
			.build();

    /**
     * Creates new OtdocfeeRepositoryImpl class Object
     */
    public OcufovdtRepositoryImpl() {
        // OcufovdtRepositoryImpl
    }

    @Override
    public List<ReferenceCodes> overrideTypeRecordGroup() {
        final String sql = getQuery("OCUFOVDT_OVERRIDE_TYPE_RG");
        List<ReferenceCodes> returnList = new ArrayList<>();
        final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, refMapping);

        try {
            returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
        } catch (final EmptyResultDataAccessException e) {
            logger.error("overrideTypeRecordGroup ", e);
            return Collections.emptyList();
        }
        return returnList;
    }

    /**
     * Fetch the records from database table
     *
     * @param searchBean FeeOverrideDetails
     * @return List<FeeOverrideDetails>
     */
    @Override
    public List<FeeOverrideDetails> feeOverdDetExecuteQuery(FeeOverrideDetails searchBean) {
        final String sql = getQuery("OCUFOVDT_FIND_FEE_OVERRIDE_DET");
        final RowMapper<FeeOverrideDetails> mapper = Row2BeanRowMapper.makeMapping(sql, FeeOverrideDetails.class, fodMapping);
        return namedParameterJdbcTemplate.query(sql, createParams("offenderFeeId", searchBean.getOffenderFeeId()), mapper);
    }

    @Override
    public String getDeductionDesc(final String deductionType) {
        final String sql = getQuery("OCUFOVDT_GET_DEDUCTION_DESC");
        String result = null;
        try{
            result = namedParameterJdbcTemplate.queryForObject(sql,createParams("deductionType",deductionType),String.class);
        } catch(Exception e) {
            logger.error("Exception :", e);
        }
        return result;
    }

    @Override
    public String getFrequency(final String code) {
        final String sql = getQuery("OCUFOVDT_GET_FREQUENCY");
        String result = null;
        try{
            result = namedParameterJdbcTemplate.queryForObject(sql,createParams("code",code),String.class);
        } catch(Exception e) {
            logger.error("Exception :", e);
            result = null;
        }
        return result;
    }

    @Override
    public String getLocation(final String location) {
        final String sql = getQuery("OCUFOVDT_GET_LOCATION");
        String result = null;
        try{
            result = namedParameterJdbcTemplate.queryForObject(sql,createParams("location",location),String.class);
        } catch(Exception e) {
            logger.error("Exception :", e);
        }
        return result;
    }

    @Override
    public String getUsername() {
        final String sql = getQuery("OCUFOVDT_USERNAME");
        String result = null;
        try{
            result = namedParameterJdbcTemplate.queryForObject(sql,createParams(),String.class);
        } catch(Exception e) {
            logger.error("Exception :", e);
        }
        return result;
    }

    /**
     * This method is used to insert the records in the data base tables based
     * on
     *
     * @param insertList List<FeeOverrideDetails>
     * @return List<Integer>
     */
    @Override
    public Integer insertFeeOvrdDet(List<FeeOverrideDetails> insertList) {
        final String sql = getQuery("OCUFOVDT_INSERT_OFF_FEE_ACCOUNT_OVERRIDES");
        int[] returnArray;
        final List<SqlParameterSource> parameters = new ArrayList<>();
        for (final FeeOverrideDetails feeOvrdDet : insertList) {
            parameters.add(new BeanPropertySqlParameterSource(feeOvrdDet));
        }
        returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
        if (insertList.size() == returnArray.length) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * This method is used to insert the records in the data base tables based
     * on
     *
     * @param updateList List<FeeOverrideDetails>
     * @return List<Integer>
     */
    @Override
    public Integer updateFeeOvrdDet(List<FeeOverrideDetails> updateList) {
        final String sql = getQuery("OCUFOVDT_UPDATE_OFF_FEE_ACCOUNT_OVERRIDES");
        int[] returnArray;
        final List<SqlParameterSource> parameters = new ArrayList<>();
        for (final FeeOverrideDetails feeOvrdDet : updateList) {
            parameters.add(new BeanPropertySqlParameterSource(feeOvrdDet));
        }
        returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
        if (updateList.size() == returnArray.length) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * This method is used to insert the records in the data base tables based
     * on
     *
     * @param deleteList List<FeeOverrideDetails>
     * @return List<Integer>
     */
    @Override
    public Integer deleteFeeOvrdDet(List<FeeOverrideDetails> deleteList) {
        final String sql = getQuery("OCUFOVDT_DELETE_OFF_FEE_ACCOUNT_OVERRIDES");
        int[] returnArray;
        final List<SqlParameterSource> parameters = new ArrayList<>();
        for (final FeeOverrideDetails feeOvrdDet : deleteList) {
            parameters.add(new BeanPropertySqlParameterSource(feeOvrdDet));
        }
        try {
			String tableName = "OFF_FEE_ACCOUNT_OVERRIDES";
			String whereClause = "OFF_FEE_OVERRIDE_ID=:offFeeOverrideId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteFeeOvrdDet", e);
		}
        returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
        if (deleteList.size() == returnArray.length) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
	public List<SystemProfiles> sysPflExecuteQuery() {
		final String sql = getQuery("OCUFOVDT_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		List<SystemProfiles> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), SystemProfilesRowMapper);
		return returnList;
	}

	@Override
	public List<String> getRoleIdList(String userName) {
		final String sql = getQuery("OCUFOVDT_GET_ROLE_ID_LIST_USER");		 
		 return namedParameterJdbcTemplate.query(sql, createParams("userName",userName),
					new RowMapper<String>() {
						public String mapRow(ResultSet rs, int rowNum) throws SQLException {
							return rs.getString(1);
						}
					});
			
	}
    
	
	@Override
	public Long preInsertFeeOverrideId() {
		final String sql = getQuery("OCUFOVDT_FEEOVERRIDE_DETAILS_PREINSERT");		
			return  namedParameterJdbcTemplate.queryForObject(sql, createParams(),
					Long.class);		

	}
	
	
	@Override
	public String getAddedByName() {
		final String sql = getQuery("OCUFOVDT_GET_ADDED_BY_NAME");		
			return  namedParameterJdbcTemplate.queryForObject(sql, createParams(),
					String.class);		

	}

	@Override
	public List<FeeOverrideDetails> feeOverCheckoverLapping(FeeOverrideDetails searchBean) {
		final String sql = getQuery("OCUFOVDT_FIND_FEE_OVERRIDE_DET_COUNTY_START_DATE");
        final RowMapper<FeeOverrideDetails> mapper = Row2BeanRowMapper.makeMapping(sql, FeeOverrideDetails.class, fodMapping);
        return namedParameterJdbcTemplate.query(sql, createParams("offenderFeeId", searchBean.getOffenderFeeId(),"overrideType",searchBean.getOverrideType()), mapper);
	}

	@Override
	public Long getAddedByStaffId(FeeOverrideDetails object) {
		final String sql = getQuery("OCUFOVDT_GET_ADDED_BY_STAFF_ID");		
		return  namedParameterJdbcTemplate.queryForObject(sql, createParams("userId",object.getCreateUserId()),
				Long.class);
	}

	@Override
	public String getUserID(Long addedByStaffId) {
		final String sql = getQuery("OCUFOVDT_GET_USER_ID");		
		return  namedParameterJdbcTemplate.queryForObject(sql, createParams("staffId",addedByStaffId),
				String.class);
	}
	
}
