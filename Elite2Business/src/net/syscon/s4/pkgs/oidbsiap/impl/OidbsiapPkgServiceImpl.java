package net.syscon.s4.pkgs.oidbsiap.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.pkgs.oidbsiap.OidbsiapPkgRepository;
import net.syscon.s4.pkgs.oidbsiap.OidbsiapPkgService;

@Service
public class OidbsiapPkgServiceImpl implements OidbsiapPkgService {
	@Autowired
	private OidbsiapPkgRepository oidbsiapRepository;

	 private static Logger logger = LogManager.getLogger(OidbsiapPkgServiceImpl.class.getName());

	@Override
	public VHeaderBlock getLivingUnitDesc(final VHeaderBlock vHBlock) {
		final VHeaderBlock bean = new VHeaderBlock();
		String retVal = null;
		try {
			retVal = oidbsiapRepository.getDesc(vHBlock.getOffenderBookId(),vHBlock.getCreateuserId());
			bean.setLivingUnitDescription(retVal);
		} catch (Exception e) {
			logger.error("getLivingUnitDesc", e);
		}
		return bean;
	}

}
