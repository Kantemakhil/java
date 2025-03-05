package net.syscon.s4.inst.legalscreens.maintenance.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.legalscreens.maintenance.OimcustsRepository;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalCustodyStatuses;
@Repository
public class OimcustsRepositoryImpl extends RepositoryBase implements OimcustsRepository {

	private static Logger logger = LogManager.getLogger(OimcustsRepositoryImpl.class.getName());

	@Override
	public Integer saveCustodyData(List<LegalCustodyStatuses> reasons) {
		return insertUpdateRecords(getQuery("OIMCUSTAS_SAVE_DATA"),reasons);
	}
	
	@Override
	public Integer updateCustodyData(List<LegalCustodyStatuses> reasons) {
		return insertUpdateRecords(getQuery("OIMCUSTAS_UPDATE_DATA"),reasons);
	}
	
	@Override
	public List<LegalCustodyStatuses> getLegalsData() {
		return findAll(getQuery("OIMCUSTAS_FETCH_DATA"),new MapSqlParameterSource(),LegalCustodyStatuses.class);
	}
	
	
	private <T> int insertUpdateRecords(String sql, List<T> lstOfProcessMain) {
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();

		lstOfProcessMain.forEach(t -> parameters.add(new BeanPropertySqlParameterSource(t)));
		try {

			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			getLogMessage("insertUpdateRecords",e);
		}

		return returnArray.length>0?1:0;
	}

	private <T> List<T> findAll(String query,MapSqlParameterSource map, Class<T> clazz) {
		return namedParameterJdbcTemplate.query(query,map,new RowMapperResultSetExtractor<T>(new BeanPropertyRowMapper<T>(clazz)));
	}


	private void getLogMessage(String methodName, Exception e) {
		logger.error("Method in " + this.getClass().getName() + " " + methodName, e);
	}
}
