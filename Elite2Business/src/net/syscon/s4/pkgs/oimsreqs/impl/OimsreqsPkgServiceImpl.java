package net.syscon.s4.pkgs.oimsreqs.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.LegalUpdateReasons;
import net.syscon.s4.common.beans.SentenceUpdateReasons;
import net.syscon.s4.pkgs.oimsreqs.OimsreqsPkgRepository;
import net.syscon.s4.pkgs.oimsreqs.OimsreqsPkgService;

@Service
public class OimsreqsPkgServiceImpl implements OimsreqsPkgService {

	@Autowired
	private OimsreqsPkgRepository oimsreqsRepository;

	private static Logger logger = LogManager.getLogger(OimsreqsPkgServiceImpl.class.getName());

	@Override
	public SentenceUpdateReasons getReasonDesc(final SentenceUpdateReasons senUpReasons) {
		List<LegalUpdateReasons> descNdActiveList = null;
		String pDesc = null;
		String pType = null;
		try {
			// desc_cur 157
			descNdActiveList = oimsreqsRepository.getDescNdActive(senUpReasons.getUpdateReasonCode());
			for (final LegalUpdateReasons legUpRea : descNdActiveList) {
				pDesc = legUpRea.getDescription();
				pType = legUpRea.getActiveType();
			}
			senUpReasons.setpDesc(pDesc);
			senUpReasons.setpType(pType);
		} catch (Exception e) {
			logger.error("getReasonDesc", e);
		}
		return senUpReasons;
	}

}
