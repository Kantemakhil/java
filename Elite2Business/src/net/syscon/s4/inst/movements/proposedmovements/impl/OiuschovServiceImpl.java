package net.syscon.s4.inst.movements.proposedmovements.impl;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.syscon.s4.inst.movements.proposedmovements.OiuschovRepository;
import net.syscon.s4.inst.movements.proposedmovements.OiuschovService;
import net.syscon.s4.inst.movements.proposedmovements.beans.VOffSchOverview;

/**
 * class OiuschovServiceImpl
 */
@Service
public class OiuschovServiceImpl implements OiuschovService{
	
	@Autowired
	private OiuschovRepository oiuschovRepository;
	
	private static Logger logger = LogManager.getLogger(OiuschovServiceImpl.class.getName());

	
	/**Fetch the records from database table
	 *
	 * @param objvOffSchOverview
	 *
	 * @throws SQLException
	 */
	@Override
	public List<VOffSchOverview> vOffSchOverviewExecQuery(VOffSchOverview objvOffSchOverview) {
		List<VOffSchOverview> returnlist=null;
		 String typeDesc = objvOffSchOverview.getTypeDescp();
		 String resaonDesc=objvOffSchOverview.getRsnDescp();
		try {
			returnlist= oiuschovRepository.vOffSchOverviewExecQuery(objvOffSchOverview);
		}catch(Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " extrMoveExecuteQuery() ", e);
		}
		return returnlist;
		}

		
	}


