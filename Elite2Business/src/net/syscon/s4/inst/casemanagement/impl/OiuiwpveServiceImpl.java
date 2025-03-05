package net.syscon.s4.inst.casemanagement.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.IwpDocuments;
import net.syscon.s4.im.beans.IwpDocumentsCommitBean;
import net.syscon.s4.im.beans.IwpTemplateObjects;
import net.syscon.s4.im.beans.IwpTemplates;
import net.syscon.s4.inst.casemanagement.OiuiwpveRepository;
import net.syscon.s4.inst.casemanagement.OiuiwpveService;

/**
 * Class OiuiwpveServiceImpl
 */
@Service
public class OiuiwpveServiceImpl extends BaseBusiness implements OiuiwpveService {

	@Autowired
	private OiuiwpveRepository oiuiwpveRepository;
	/**
	 * Logger object used to print the log in the file
	 */


	/**
	 * Creates new OiuiwpveServiceImpl class Object
	 */
	public OiuiwpveServiceImpl() {
		// OiuiwpveServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<IwpTemplates> populateDefltDoc(final IwpTemplates paramBean) {
		List<IwpTemplates> iwpTemplatesList = null;
		// //--@@@ Sarah: Added this proc so that If only one template
		// associated, default that template
		// //TODO
		// List<IwpTemplates> iwpTemplatesList =
		// oiuiwpveRepository.populateDefltDoctemplatedetailscur(paramBean);
		// //TODO
		// List<IwpTemplates> iwpTemplatesList =
		// oiuiwpveRepository.populateDefltDocdefaultdocumentcur(paramBean);
		// //TODO
		// String lvTemplateId = "";
		// String lvCount = 0;
		// String lvOldMsg;
		// //TODO
		// //TODO
		// //-- If only one template associated, default this template
		// //TODO
		// //TODO lv_template_id = rec.template_id;
		// //TODO lv_count = lv_count + 1;
		// //TODO exit when lv_count > 2;
		// //TODO this.}();
		// //TODO
		// if (lvCount===1 ){
		// //-- Populate select document type and descrption
		// //TODO fetch template_details_cur
		// //TODO into templates.template_name, templates.nbt_template_desc,
		// templates.nbt_tmp_id, templates.nbt_active_flag;
		// //TODO
		// //-- Query previous document block
		// if (templatesModel.templateName !== null ){
		// //TODO tag_ole.new_query = true;
		// //TODO lv_old_msg = system.message_level;
		// //TODO go_item('templates.nbt_template_desc');
		// //TODO system.message_level = 20;
		// //TODO
		// //TODO go_block('iwp_doc');
		// //TODO do_key('execute_query');
		// //TODO
		// //TODO system.message_level = lv_old_msg;
		// }
		// //TODO
		// //-- Disable New button when template is deactivated
		// if (iwp10gModel.checkModTemplActive(templatesModel.nbtTmpId,
		// parameterModel.moduleName) || parameterModel.pObjectType in ('c!e',
		// 'psrRep', 'memo', 'task','stg','oic') ){
		// //-- Check for the security of the new iwp linkage.
		// if (iwp10gModel.checkTemplateExists(templatesModel.nbtTmpId) ){
		// if (nvl(templatesModel.nbtActiveFlag, 'n')==='n' ){
		// //TODO set_item_property('button.but_new', enabled, property_false);
		// } else {
		// //TODO set_item_property('button.but_new', enabled, property_true);
		// //TODO set_item_property('button.but_new', navigable, property_true);
		// }
		// } else {
		// //-- Check Import functionality
		// //-- Template being non-existant is used to distinguish that this is
		// an import enabled item instead.
		// if (nvl(templatesModel.nbtActiveFlag, 'n')==='n' ){
		// //TODO set_item_property('button.but_new', enabled, property_false);
		// } else {
		// //TODO set_item_property('button.but_new', enabled, property_true);
		// //TODO set_item_property('button.but_new', navigable, property_true);
		// }
		// }
		// } else {
		// //TODO set_item_property('button.but_new', enabled, property_false);
		// }
		// //TODO
		// }
		// //--@@@ ERIC MAR-2011 Enabling non-offender Context
		// //--message('POPULATE_DEFLT_DOC -
		// lv_template_id:'||to_char(lv_template_id)||':parameter.module_name'||:parameter.module_name||':parameter.p_type'||:parameter.p_type);
		// pause;
		return iwpTemplatesList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<IwpTemplates> isTemplateHasBody(final IwpTemplates paramBean) {
		// --@@ Sarah: This function checks if file is imported or generated
		// TODO

		// TODO


		List<IwpTemplates> iwpTemplatesList = null;
		// TODO
		// List<IwpTemplates> iwpTemplatesList =
		// oiuiwpveRepository.isTemplateHasBody(paramBean);
		// TODO
		// TODO
		// TODO fetch is_template_has_body_cur
		// TODO into lv_tmp_body_null;
		// TODO
		// TODO return(lv_tmp_body_null);
		return iwpTemplatesList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<IwpTemplateObjects> checkVicNotifTemplActive(final IwpTemplateObjects paramBean) {

		// TODO this.is();

		List<IwpTemplateObjects> iwpTemplateObjectsList = null;
		// List<IwpTemplateObjects> iwpTemplateObjectsList =
		// oiuiwpveRepository.checkVicNotifTemplActivevicnotiftemplcur(paramBean);
		// TODO
		// TODO fetch vic_notif_templ_cur into lv_flag;
		// TODO
		// if (lvFlag==='y' ){
		// //TODO return true;
		// } else {
		// //TODO return false;
		// }
		return iwpTemplateObjectsList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<IwpDocuments> iwpDocExecuteQuery(final IwpDocuments searchRecord) {
		final List<IwpDocuments> lstIwpDocuments = oiuiwpveRepository.iwpDocExecuteQuery(searchRecord);

		if (lstIwpDocuments != null && lstIwpDocuments.size() > 0) {
			for (final IwpDocuments objDocuments : lstIwpDocuments) {
				objDocuments.setDocumentBody(null);
			}
		}

		return lstIwpDocuments;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstIWP_DOC
	 *
	 */
	@Transactional
	public Integer iwpDocCommit(final IwpDocumentsCommitBean commitBean) {
		int liReturn = 0;

		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bean -> bean.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oiuiwpveRepository.iwpDocUpdateIwpDocuments(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgStatusRecordGroup() {
		return oiuiwpveRepository.rgStatusRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgTemplateRecordGroup(final String offenderBookId, final String moduleName,
			final String pObjectType, final String pType, final String pSubType, final String pObjectId,
			final String blockName,final String username) {
		final List<IwpTemplates> lstTemplates = (List<IwpTemplates>) oiuiwpveRepository
				.rgTemplateRecordGroup(offenderBookId, moduleName, pObjectType, pType, pSubType, pObjectId, blockName,username);
		final List<ReferenceCodes> lstRef = new ArrayList<>();
		ReferenceCodes objCodes = null;
		for (final IwpTemplates objTem : lstTemplates) {
			objCodes = new ReferenceCodes();
			objCodes.setCode(objTem.getTemplateName());
			objCodes.setDescription(objTem.getDescription());
			objCodes.setListSeq(objTem.getTemplateId());
			lstRef.add(objCodes);
		}

		return lstRef;

	}

}