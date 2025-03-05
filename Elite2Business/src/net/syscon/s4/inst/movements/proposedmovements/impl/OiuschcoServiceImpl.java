package net.syscon.s4.inst.movements.proposedmovements.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.movements.proposedmovements.OiuschcoRepository;
import net.syscon.s4.inst.movements.proposedmovements.OiuschcoService;
import net.syscon.s4.inst.movements.proposedmovements.beans.VOffSchOverview;

@Service
public class OiuschcoServiceImpl implements OiuschcoService {

	@Autowired
	private OiuschcoRepository oiuschcoRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	@Override
	public List<VOffSchOverview> vOffSchOverviewExecQuery(VOffSchOverview searchRecord) {
		return oiuschcoRepository.vOffSchOverviewExecuteQuery(searchRecord);
	}
}
