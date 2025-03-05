package net.syscon.s4.pkgs.oms_trigger_objects.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.OffenderPptyConTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxns;
import net.syscon.s4.pkgs.oms_trigger_objects.OmsTriggerObjectsRepository;
import net.syscon.s4.pkgs.oms_trigger_objects.OmsTriggerObjectsService;

@Service
public class OmsTriggerObjectsServiceImpl implements OmsTriggerObjectsService {
	private final Logger logger = LogManager.getLogger(OmsTriggerObjectsServiceImpl.class);
	@Autowired
	OmsTriggerObjectsRepository omsTriggerObjectsRepository;

	@Override
	public Integer createItemTransaction(OffenderPptyItemTxns offenderPptyItemTxns) {
		return omsTriggerObjectsRepository.createItemTransaction(offenderPptyItemTxns);
	}
   // below method is used to create new container transaction in OFFENDER_PPTY_CON_TXNS table
	@Override
	public Integer createContainerTransaction(OffenderPptyConTxns offenderPptyConTxns) {
		return omsTriggerObjectsRepository.createContainerTransaction(offenderPptyConTxns);
	}

}
