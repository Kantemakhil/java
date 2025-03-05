package net.syscon.s4.inmate.trust.financialreports.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inmate.trust.financialreports.OiuhofflRepository;
import net.syscon.s4.inmate.trust.financialreports.OiuhofflService;

/**
 * Class OiuhofflServiceImpl
 */
@Service
public class OiuhofflServiceImpl extends BaseBusiness implements OiuhofflService {

	@Autowired
	private OiuhofflRepository oiuhofflRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<VHeaderBlock> vOffBkgExecuteQuery(VHeaderBlock searchRecord) {
		return oiuhofflRepository.vOffBkgExecuteQuery(searchRecord);
	}

}