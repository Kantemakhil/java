package net.syscon.s4.pkgs.ocdoapop.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.pkgs.ocdoapop.OcdoapopPkgRepository;

@Repository
public class OcdoapopPkgRepositoryImpl extends RepositoryBase implements OcdoapopPkgRepository {

	private static Logger logger = LogManager.getLogger(OcdoapopPkgRepositoryImpl.class.getName());

	@Override
	public VAddresses fetchVAddressRecord(final Long pAddressId) {
		final String sql = getQuery("FETCH_V_ADDRESS_RECORD");
		VAddresses retObj = new VAddresses();
		try {
			retObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_ADDRESS_ID", pAddressId),
					new BeanPropertyRowMapper<VAddresses>(VAddresses.class));
		} catch (Exception e) {
			logger.error("fetchVAddressRecord :" + e);
			retObj = null;
		}
		return retObj;
	}

}
