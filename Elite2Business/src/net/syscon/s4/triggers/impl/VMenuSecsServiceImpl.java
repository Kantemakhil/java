package net.syscon.s4.triggers.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.VMenuSecs;
import net.syscon.s4.triggers.VMenuSecsRepository;
import net.syscon.s4.triggers.VMenuSecsService;

@Service
public class VMenuSecsServiceImpl implements VMenuSecsService {
	private final Logger logger = LogManager.getLogger(VMenuSecsServiceImpl.class);
	@Autowired
	VMenuSecsRepository vMenuSecsRepository;

//	@Autowired
//	TagMenuSecsService tagMenuSecsService; 
	@Override
	public Integer vMenuSecsTrigger(final VMenuSecs vMenuSecsNew, final String sqlOperation) {
		final List<VMenuSecs> vMenuSecsList = vMenuSecsRepository.getVMenuSecs();
		VMenuSecs lrOldRec = new VMenuSecs();
		VMenuSecs lrNewRec = new VMenuSecs();
		Integer result = 0;
		try {
			if (!vMenuSecsList.isEmpty()) {
				for (final VMenuSecs old : vMenuSecsList) {
					lrOldRec = new VMenuSecs();
					lrNewRec = new VMenuSecs();
//				-- Capture OLD Values
					BeanUtils.copyProperties(old, lrOldRec);
//				-- Capture NEW Values
					BeanUtils.copyProperties(vMenuSecsNew, lrNewRec);
					if ("INSERTING".equalsIgnoreCase(sqlOperation)) {
//						result = tagMenuSecsService.prIns(lrOldRec, lrNewRec);
					} else if ("UPDATING".equalsIgnoreCase(sqlOperation)) {
//						result = tagMenuSecsService.prUpd(lrOldRec, lrNewRec);
					} else if ("DELETING".equalsIgnoreCase(sqlOperation)) {
//						result = tagMenuSecsService.prDel(lrOldRec, lrNewRec);
					}
				}
			}
		} catch (final Exception e) {
			logger.error("vMenuSecsTrigger", e);
		}
		return result;
	}

}
