package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.InternetAddressesT2Repository;
@Repository
public class InternetAddressesT2RepositoryImpl extends RepositoryBase implements InternetAddressesT2Repository{
	private static Logger logger = LogManager.getLogger(InternetAddressesT2RepositoryImpl.class.getName());


	public InternetAddresses getInternetAddrsesOldObject(InternetAddresses object) {
		InternetAddresses internetAddresses;
		final String sql = getQuery("INTERNET_ADDRESS_T2_GET_ADDRESS_OBJECT");
		try {
			internetAddresses=  namedParameterJdbcTemplate
					.queryForObject(sql, createParams("internetAdressId",object.getInternetAddressId()),new BeanPropertyRowMapper<InternetAddresses>(InternetAddresses.class));
		} catch (Exception e) {
			internetAddresses=null;
			logger.error("getInternetAddrsesOldObject :", e);
		}
		
		return internetAddresses;
	} 



}
