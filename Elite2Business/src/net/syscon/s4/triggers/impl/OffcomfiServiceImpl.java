package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.legalorders.OffenderCommunityFiles;
import net.syscon.s4.legalorders.OffenderFileTransactions;
import net.syscon.s4.pkgs.pims_file_tracking.PimsFileTrackingService;
import net.syscon.s4.triggers.OffcomfiService;

@Service
public class OffcomfiServiceImpl implements OffcomfiService {
	private static Logger logger = LogManager.getLogger(OffcomfiServiceImpl.class);
	@Autowired
	PimsFileTrackingService pimsFileTrackingService;

	@Override
	public Integer offcomfiTrigger(final OffenderCommunityFiles offenderCommunityFiles) {
		final OffenderFileTransactions bean = new OffenderFileTransactions();
		try {
			bean.setOffenderFileSeq(offenderCommunityFiles.getOffenderFileSeq());
			bean.setOffenderId(offenderCommunityFiles.getOffenderId());
			bean.setFileTransComment(null);
			bean.setAgyLocIdTo(offenderCommunityFiles.getHoldingAgyLocId());
			bean.setStaffIdTo(offenderCommunityFiles.getHoldingStaffId());
			bean.setNonOfficerTo(offenderCommunityFiles.getNonOfficerStatus());
			bean.setCreateUserId(offenderCommunityFiles.getCreateUserId());
			bean.setCreationDate(offenderCommunityFiles.getCreationDate());
			return pimsFileTrackingService.insertTrans(bean, offenderCommunityFiles.getCreateUserId());
		} catch (final Exception e) {
			logger.error("offcomfiTrigger", e);
			return null;
		}

	}

}
