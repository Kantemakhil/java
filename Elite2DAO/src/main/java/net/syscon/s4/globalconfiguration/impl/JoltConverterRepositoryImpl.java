package net.syscon.s4.globalconfiguration.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.JsonSpecification;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.globalconfiguration.JoltConverterRepository;

@Repository
public class JoltConverterRepositoryImpl extends RepositoryBase implements JoltConverterRepository {
    private static Logger log = LogManager.getLogger(JoltConverterRepositoryImpl.class.getName());

    private final Map<String, FieldMapper> jsonSpecMapping = new ImmutableMap.Builder<String, FieldMapper>()
            .put("ID", new FieldMapper("id"))
            .put("SPEC_KEY", new FieldMapper("specKey"))
            .put("JSON_SPECS", new FieldMapper("jsonSpecs"))
            .build();

    @Override
    public JsonSpecification getJsonSpecData(String specKey) {
        final String sql = getQuery("GET_JSON_SPEC");
        JsonSpecification returnObj = null;
        final RowMapper<JsonSpecification> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, JsonSpecification.class,
                jsonSpecMapping);
        try {
            returnObj = namedParameterJdbcTemplate.queryForObject(sql,
                    createParams("specKey", specKey), columnRowMapper);
        } catch (Exception e) {
            log.error("JoltConverterRepository :" + e.getMessage());
            return null;
        }

        return returnObj;
    }

    @Override
    public List<String> getAllSpecKey() {
        List<String> listSpecKeys = null;
        final String sql = getQuery("GET_SPEC_KEY_LIST");
        try {
            listSpecKeys = jdbcTemplate.queryForList(sql, String.class);
        } catch (Exception e) {
            log.error("JoltConverterRepository :" + e.getMessage());
            return null;
        }
        return listSpecKeys;
    }

    @Override
    public Boolean findJsonExist(String specKey) {
        final String sql = getQuery("CHECK_EXIST_SPECKEY");
        Integer returnObj = null;
        try {
            returnObj = namedParameterJdbcTemplate.queryForObject(sql,
                    createParams("specKey", specKey), Integer.class);
        } catch (Exception e) {
            log.error("JoltConverterRepository :" + e.getMessage());
            return false;
        }
        return (null == returnObj ? Boolean.FALSE : returnObj > 0);
    }

    @Override
    public Integer insertJsonData(JsonSpecification jsonSpecification) {
        int insertCount = 0;
        final String sql = getQuery("INSERT_JSON_DATA");
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(jsonSpecification);
        insertCount = namedParameterJdbcTemplate.update(sql, parameters);
        return insertCount;
    }

    @Override
    public Integer updateJsonData(JsonSpecification jsonSpecification) {
        int updateCount = 0;
        final String sql = getQuery("UPDATE_JSON_DATA");
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(jsonSpecification);
        updateCount = namedParameterJdbcTemplate.update(sql, parameters);
        return updateCount;
    }
}
