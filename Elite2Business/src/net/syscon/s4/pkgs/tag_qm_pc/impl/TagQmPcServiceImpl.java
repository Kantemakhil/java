package net.syscon.s4.pkgs.tag_qm_pc.impl;

import java.util.Date;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.QmCompositions;
import net.syscon.s4.pkgs.QmObjects;
import net.syscon.s4.pkgs.QmProcesses;
import net.syscon.s4.pkgs.tag_qm_pc.TagQmPcRepository;
import net.syscon.s4.pkgs.tag_qm_pc.TagQmPcService;
import net.syscon.s4.triggers.VQmPc;

@Service
public class TagQmPcServiceImpl implements TagQmPcService {
	private final Logger logger = LogManager.getLogger(TagQmPcServiceImpl.class);
	@Autowired
	TagQmPcRepository tagQmPcRepository;

	@Override
	public Integer prIns(final VQmPc lrOldRec, final VQmPc lrNewRec) {
		Integer result = 0;
		QmProcesses qmProcesses = new QmProcesses();
		QmCompositions qmCompositions = new QmCompositions();
		QmObjects qmObjects = new QmObjects();
		final Long vProIdSeq = tagQmPcRepository.qmProcessesCur(lrNewRec.getName());
		try {
			if (Optional.ofNullable(vProIdSeq).isPresent()) {
				qmCompositions = new QmCompositions();
				qmCompositions.setCompositionId(lrNewRec.getCompositionId());
				qmCompositions.setManagingAgyLocId(lrNewRec.getManagingAgyLocId());
				qmCompositions.setEventTypeAgyLocId(lrNewRec.getEventTypeAgyLocId());
				qmCompositions.setProcessId(vProIdSeq);
				qmCompositions.setEventType(lrNewRec.getEventType());
				qmCompositions.setActiveFlag(lrNewRec.getQmcActiveFlag());
				qmCompositions.setExpiryDate(lrNewRec.getQmcExpiryDate());
				qmCompositions.setCreateDatetime(new Date());
				qmCompositions.setModifyDatetime(new Date());
				qmCompositions.setCreateUserId(lrNewRec.getCreateUserId());
				result = tagQmPcRepository.prInsQmCompositions(qmCompositions);
			} else {
				qmProcesses = new QmProcesses();
				qmProcesses.setProcessId(lrNewRec.getProcessId());
				qmProcesses.setName(lrNewRec.getName());
				qmProcesses.setDescription(lrNewRec.getDescription());
				qmProcesses.setExecutionType("PARALELL");
				qmProcesses.setLocationType("SINGLE");
				qmProcesses.setActiveFlag(lrNewRec.getActiveFlag());
				qmProcesses.setExpiryDate(lrNewRec.getExpiryDate());
				qmProcesses.setEventType(lrNewRec.getEventType());
				qmProcesses.setCreateDatetime(new Date());
				qmProcesses.setModifyDatetime(new Date());
				qmProcesses.setCreateUserId(lrNewRec.getCreateUserId());
				result = tagQmPcRepository.prInsQmProcesses(qmProcesses);
				qmCompositions = new QmCompositions();
				qmCompositions.setCompositionId(lrNewRec.getCompositionId());
				qmCompositions.setManagingAgyLocId(lrNewRec.getManagingAgyLocId());
				qmCompositions.setEventTypeAgyLocId(lrNewRec.getEventTypeAgyLocId());
				qmCompositions.setProcessId(lrNewRec.getProcessId());
				qmCompositions.setEventType(lrNewRec.getEventType());
				qmCompositions.setActiveFlag(lrNewRec.getQmcActiveFlag());
				qmCompositions.setExpiryDate(lrNewRec.getQmcExpiryDate());
				qmCompositions.setCreateDatetime(new Date());
				qmCompositions.setModifyDatetime(new Date());
				qmCompositions.setCreateUserId(lrNewRec.getCreateUserId());
				result = tagQmPcRepository.prInsQmCompositions(qmCompositions);
				qmObjects = new QmObjects();
				qmObjects.setName("OFFENDER BOOK ID");
				qmObjects.setDescription("OFFENDER BOOK ID");
				qmObjects.setProcessId(lrNewRec.getProcessId());
				qmObjects.setQmDataTypeDomain("QM_DATA_TYPE");
				qmObjects.setQmDataTypeCode("NUMBER");
				qmObjects.setPurpose("EVENT");
				qmObjects.setCreateDatetime(new Date());
				qmObjects.setModifyDatetime(new Date());
				qmObjects.setCreateUserId(lrNewRec.getCreateUserId());
				result = tagQmPcRepository.prInsQmObjects(qmObjects);
			}
		} catch (final Exception e) {
			result = 0;
			logger.error("prIns", e);
		}
		return result;
	}

	@Override
	public Integer prUpd(final VQmPc lrOldRec, final VQmPc lrNewRec) {
		final QmCompositions qmCompositions = new QmCompositions();
		qmCompositions.setActiveFlag(lrNewRec.getQmcActiveFlag());
		qmCompositions.setExpiryDate(lrNewRec.getQmcExpiryDate());
		qmCompositions.setCompositionId(lrOldRec.getCompositionId());
		qmCompositions.setProcessId(lrOldRec.getProcessId());
		try {
			return tagQmPcRepository.prUpdQmCompositions(qmCompositions);
		} catch (final Exception e) {
			logger.error("prUpd", e);
			return null;
		}
	}

	@Override
	public Integer prDel(final VQmPc lrOldRec, final VQmPc lrNewRec) {
		return null;
	}

}
