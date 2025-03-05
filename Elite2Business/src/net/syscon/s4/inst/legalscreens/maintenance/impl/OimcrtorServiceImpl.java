package net.syscon.s4.inst.legalscreens.maintenance.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.IwpTemplateObjects;
import net.syscon.s4.im.beans.IwpTemplateObjectsCommitBean;
import net.syscon.s4.im.beans.IwpTemplates;
import net.syscon.s4.im.beans.OrderTypes;
import net.syscon.s4.im.beans.OrderTypesCommitBean;
import net.syscon.s4.inst.legalscreens.maintenance.OimcrtorRepository;
import net.syscon.s4.inst.legalscreens.maintenance.OimcrtorService;

/**
 * Class OimcrtorServiceImpl
 */
@Service
public class OimcrtorServiceImpl extends BaseBusiness implements OimcrtorService {

	@Autowired
	private OimcrtorRepository oimcrtorRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OrderTypes> orderTypesExecuteQuery(OrderTypes searchRecord) {
		return oimcrtorRepository.orderTypesExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstORDER_TYPES
	 *
	 */
	@Transactional
	public Integer orderTypesCommit(OrderTypesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (OrderTypes obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oimcrtorRepository.orderTypesInsertOrderTypes(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (OrderTypes obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimcrtorRepository.orderTypesUpdateOrderTypes(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<IwpTemplateObjects> iwpTemplateObjectsExecuteQuery(IwpTemplateObjects searchRecord) {
		String templateCode = null;
		List<IwpTemplateObjects> returnList = oimcrtorRepository.iwpTemplateObjectsExecuteQuery(searchRecord);
		if (returnList != null && !returnList.isEmpty()) {
			for (IwpTemplateObjects iwpTemplateObjects : returnList) {
				if (iwpTemplateObjects.getTemplateId() != null) {
					templateCode = oimcrtorRepository.getAttchedTemplateCode(iwpTemplateObjects.getTemplateId());
					iwpTemplateObjects.setTemplateName(templateCode);
				}
			}
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstIWP_TEMPLATE_OBJECTS
	 *
	 */
	@Transactional
	public Integer iwpTemplateObjectsCommit(IwpTemplateObjectsCommitBean commitBean) {
		int liReturn = 0;

		liReturn = insertIwpTemplateObjects(commitBean, liReturn);
		liReturn = updateIwpTemplateObjects(commitBean, liReturn);
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().stream().forEach(e -> e.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oimcrtorRepository.iwpTemplateObjectsDeleteIwpTemplateObjects(commitBean.getDeleteList());
		}
		return liReturn;
	}

	private int updateIwpTemplateObjects(IwpTemplateObjectsCommitBean commitBean, int liReturn) {
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (IwpTemplateObjects obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimcrtorRepository.iwpTemplateObjectsUpdateIwpTemplateObjects(commitBean.getUpdateList());
		}
		return liReturn;
	}

	private int insertIwpTemplateObjects(IwpTemplateObjectsCommitBean commitBean, int liReturn) {
		BigDecimal iwpTemplateObjectId;
		BigDecimal templateId;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (IwpTemplateObjects iwpTemplateObjects : commitBean.getInsertList()) {
				iwpTemplateObjects.setCreateUserId(commitBean.getCreateUserId());
				iwpTemplateObjectId = oimcrtorRepository.getNextIwpId();
				iwpTemplateObjects.setIwpTemplateObjectId(iwpTemplateObjectId);
				templateId = oimcrtorRepository.getTemplateId(iwpTemplateObjects.getTemplateName());
				iwpTemplateObjects.setTemplateId(templateId);
			}
			liReturn = oimcrtorRepository.iwpTemplateObjectsInsertIwpTemplateObjects(commitBean.getInsertList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<IwpTemplates> rgTemplateRecordGroup() {
		return oimcrtorRepository.rgTemplateRecordGroup();

	}

	@Override
	public Integer iwpTempOnCheckDeleteMaster(BigDecimal templateId) {
		return oimcrtorRepository.iwpTempOnCheckDeleteMaster(templateId);
	}
}