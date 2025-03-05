package net.syscon.s4.inst.movements.proposedmovements.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderOicSanctions;
import net.syscon.s4.inst.movements.proposedmovements.OiusanctRepository;
import net.syscon.s4.inst.movements.proposedmovements.OiusanctService;

/**
 * Class OiusanctServiceImpl
 */
@Service
public class OiusanctServiceImpl implements OiusanctService {

	@Autowired
	private OiusanctRepository oiusanctRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderOicSanctions> offenderOicSanctionsExecuteQuery(OffenderOicSanctions searchRecord) {
		return oiusanctRepository.offenderOicSanctionsExecuteQuery(searchRecord);

	}

}