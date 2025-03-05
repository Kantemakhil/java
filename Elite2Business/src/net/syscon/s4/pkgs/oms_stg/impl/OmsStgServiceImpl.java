package net.syscon.s4.pkgs.oms_stg.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.im.beans.StgRelationships;
import net.syscon.s4.pkgs.oms_stg.OmsStgRepository;
import net.syscon.s4.pkgs.oms_stg.OmsStgService;

@Service("OmsStgServiceImpl_pkg")
public class OmsStgServiceImpl implements OmsStgService {
	@Autowired
	private OmsStgRepository omsStgRepository;
	@Override
	@Transactional
	public void nonaEnemychildToStg(final StgRelationships data) {
		final List<StgRelationships> list = omsStgRepository.stgIdCur(data.getStgId(), data.getRelatedStgId());
		for (final StgRelationships obj : list) {
			Integer relationSeq = omsStgRepository.seqCur(obj.getStgId());
			data.setStgId(obj.getStgId());
			data.setRelationshipSeq(relationSeq.longValue());
			data.setRelatedStgId(obj.getRelatedStgId());
			try {
				omsStgRepository.omsStgInsert(data); 
			} catch (Exception e) {

			}
		}

	}
}
