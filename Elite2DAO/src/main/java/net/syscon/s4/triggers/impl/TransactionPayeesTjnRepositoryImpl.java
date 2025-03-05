package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.TransactionPayees;
import net.syscon.s4.triggers.TransactionPayeesTjnRepository;
@Repository
public class TransactionPayeesTjnRepositoryImpl extends RepositoryBase implements TransactionPayeesTjnRepository{

	@Override
	public Integer transactionPayessTJNTrigger(List<TransactionPayees> lstTransactionPayess) {
		final String sql = getQuery("INSERT_TRANSACTION_PAYEES_JN_TABLE");
		int[] returnArray = new int[] {};
		Integer addressId = null;
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final TransactionPayees addresses : lstTransactionPayess) {
			parameters.add(new BeanPropertySqlParameterSource(addresses));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstTransactionPayess.size() == returnArray.length) {
			return 1;
		} else {
			return returnArray[0];
		}

	}

}
