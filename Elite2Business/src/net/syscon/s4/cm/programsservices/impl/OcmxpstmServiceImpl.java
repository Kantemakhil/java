package net.syscon.s4.cm.programsservices.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.programsservices.OcmxpstmRepository;
import net.syscon.s4.cm.programsservices.OcmxpstmService;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.ReferenceCodesCommitBean;
import net.syscon.s4.globalconfiguration.OumrcodeRepository;

@Service
public class OcmxpstmServiceImpl implements OcmxpstmService {

	private static Logger logger = LogManager.getLogger(OcmxpstmServiceImpl.class.getName());

	@Autowired
	private OcmxpstmRepository ocmxpstmRepository;

	@Autowired
	private OumrcodeRepository oumrcodeRepository;

	@Override
	public ReferenceCodes refCodeCondExecuteQuery(final String code) {
		return ocmxpstmRepository.refCodeCondExecuteQuery(code);
	}

	@Override
	public List<ReferenceCodes> refCodeExecuteQuery(ReferenceCodes searchRecord) {
		List<ReferenceCodes> list = oumrcodeRepository.refCodeExecuteQuery(searchRecord);
		logger.info(this.getClass().getName() + " refCodeExecuteQuery");
		if (list.size() > 0 && !list.isEmpty()) {
			list.forEach(bo -> {
				ReferenceCodes bean = refCodeCondExecuteQuery(bo.getCode());
				if (bean != null && bean.getUpdateFlag() != null) {
					bo.setUpdateFlag(bean.getUpdateFlag());
				}
				if (bean != null && bean.getUpdateReasonFlag() != null) {
					bo.setUpdateReasonFlag(bean.getUpdateReasonFlag());
				}
			});
		}
		return list;
	}

	@Transactional
	public Integer refCodeCondCommit(ReferenceCodesCommitBean commitBean) {
		int liReturn = 0;
		if(commitBean.getUpdateList()!=null && commitBean.getUpdateList().size()>0) {
			commitBean.getUpdateList().forEach(bo->{
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (ReferenceCodes obj : commitBean.getUpdateList()) {
				ReferenceCodes bean = refCodeCondExecuteQuery(obj.getCode());
				logger.info(this.getClass().getName() + " refCodeCondExecuteQuery");
				if (bean != null && bean.getCode() != null) {
					obj.setModifyUserId(commitBean.getCreateUserId());
					liReturn = ocmxpstmRepository.refCodeCondUpdateReference(commitBean.getUpdateList());
					logger.info(this.getClass().getName() + " refCodeCondUpdateReference");
				} else {
					obj.setCreateUserId(commitBean.getCreateUserId());
					liReturn = ocmxpstmRepository.refCodeCondInsertReference(commitBean.getUpdateList());
					logger.info(this.getClass().getName() + " refCodeCondInsertReference");
				}
			}
		}
		return liReturn;
	}

}
