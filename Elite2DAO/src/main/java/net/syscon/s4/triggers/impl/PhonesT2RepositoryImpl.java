package net.syscon.s4.triggers.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.PhonesT2Repository;
@Repository
public class PhonesT2RepositoryImpl extends RepositoryBase implements PhonesT2Repository{

	public Phones getPhonesOldObject(Phones object) {
		Phones retObj=null;
		final String sql = getQuery("PHONES_T2_GET_ADDRESS_OBJECT");
		try {
			retObj=namedParameterJdbcTemplate
				.queryForObject(sql, createParams("phoneId",object.getPhoneId()),new BeanPropertyRowMapper<Phones>(Phones.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retObj;
	} 

}
