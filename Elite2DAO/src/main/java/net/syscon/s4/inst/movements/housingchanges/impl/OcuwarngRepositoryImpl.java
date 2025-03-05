package net.syscon.s4.inst.movements.housingchanges.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.movements.housingchanges.OcuwarngRepository;

/**
 * Class OcuwarngRepositoryImpl
 * 
 */
@Repository
public class OcuwarngRepositoryImpl extends RepositoryBase implements OcuwarngRepository {
	
	private static Logger logger = LogManager.getLogger(OcuwarngRepositoryImpl.class.getName());

	/**
	 * Creates new OidchlocRepositoryImpl class Object
	 */
	public OcuwarngRepositoryImpl() {
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * allowOverride
	 *
	 *
	 */
	@Override
	public List<String> allowOverride(String userName) {
		logger.info(this.getClass().getName() + ".allowOverride start :: ",userName);
		final String sql = getQuery("OCUWARNG_ALLOWED_OVERRIDE");
		List<String> returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForList(sql, createParams("createUserId",userName), String.class);
			logger.info(this.getClass().getName() + ".allowOverride end");
		}catch (Exception e) {
			logger.error("Error in calss " + this.getClass().getName() + "allowOverride", e);
		}
		return returnObj;
	}
}