package net.syscon.s4.inmate.trust.financialreports.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.trust.financialreports.OtuacodeRepository;
import net.syscon.s4.inmate.trust.financialreports.OtuacodeService;

/**
 * Class OtuacodeServiceImpl
 */
@Service
public class OtuacodeServiceImpl extends BaseBusiness implements OtuacodeService {

	@Autowired
	private OtuacodeRepository otuacodeRepository;

	/**
	 * Creates new OtuacodeServiceImpl class Object
	 */
	public OtuacodeServiceImpl() {
	}

	/**
	 * Fetch the records from database table method:acCodeExecuteQuery
	 * 
	 * @param searchRecord
	 * @return List<AccountCodes>
	 */
	public List<AccountCodes> acCodeExecuteQuery(final AccountCodes searchRecord) {
		return otuacodeRepository.acCodeExecuteQuery(searchRecord);

	}

}