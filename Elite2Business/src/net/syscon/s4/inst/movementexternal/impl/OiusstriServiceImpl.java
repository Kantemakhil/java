package net.syscon.s4.inst.movementexternal.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.movementexternal.OiusstriRepository;
import net.syscon.s4.inst.movementexternal.OiusstriService;
import net.syscon.s4.inst.movementexternal.beans.VOiusstri;

@Service
public class OiusstriServiceImpl implements OiusstriService {

	private static Logger logger = LogManager.getLogger(OiusstriServiceImpl.class.getName());

	@Autowired
	private OiusstriRepository oiusstriRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param objvOffSchOverview
	 *
	 * @throws SQLException
	 */
	@Override
	public List<VOiusstri> scheduledtripsExecuteQuery(VOiusstri objvOiusstri) {
		List<VOiusstri> returnlist = null;
		try {
			returnlist = oiusstriRepository.scheduledtripsExecuteQuery(objvOiusstri);

		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " scheduledtripsExecuteQuery() ", e);
		}
		return returnlist;
	}

}
