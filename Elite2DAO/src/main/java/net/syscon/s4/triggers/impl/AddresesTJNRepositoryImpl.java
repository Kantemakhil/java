package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.AddresesTJNRepository;
@Repository
public class AddresesTJNRepositoryImpl extends RepositoryBase implements AddresesTJNRepository{

	@Override
	public Integer addressTJNTrigger(List<Addresses> lstAddresses) {
		final String sql = getQuery("INSERT_ADDRESS_JN_ADDRESSES_TABLE_ONE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Addresses addresses : lstAddresses) {

			parameters.add(new BeanPropertySqlParameterSource(addresses));
			
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAddresses.size() == returnArray.length) {
			return 1;
		} else {
			return returnArray[0];
		}
	}

	

}
