package net.syscon.s4.triggers;


import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;

@Repository
public class OmtofsbRepositoryImpl extends RepositoryBase implements OmtofsbRepository {
	private static Logger logger = LogManager.getLogger(OmtofsbRepositoryImpl.class.getName());
	@Override
	public Integer deleteOffenderFileTrigger(String modifuUserId) {
		Integer count=null;
		final String sql = getQuery("delete_offender_files_trig");
		try {
			String tableName = "offender_files_trig";
			String whereClause = null;
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("modifyUserId", modifuUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteOffenderFileTrigger", e);
		}
		try {
			count= namedParameterJdbcTemplate.update(sql,createParams());
		} catch (final Exception e) {
			count=0;
			logger.error("deleteExtOwnershipTransfer:", e);
		}
		return count;
	
	}

}
