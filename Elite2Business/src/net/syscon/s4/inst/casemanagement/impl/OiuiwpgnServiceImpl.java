package net.syscon.s4.inst.casemanagement.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.casemanagement.OiuiwpgnRepository;
import net.syscon.s4.inst.casemanagement.OiuiwpgnService;
/**
 * Class OiuiwpgnServiceImpl
 */
@Service
public class OiuiwpgnServiceImpl extends BaseBusiness implements OiuiwpgnService{

@Autowired
private OiuiwpgnRepository oiuiwpgnRepository;



/**
 *Creates new OiuiwpgnServiceImpl class Object 
 */
public OiuiwpgnServiceImpl(){
	//OiuiwpgnServiceImpl
}		

//TODO package tag_ole is
//		String localfname;
//		String errornum;
//		String errortxt;
//		String localcache;
//		String localtmpdir;
//		String localtemplate;
//		String localzfile;
		  //TODO variable_list iwp_10g.iwp_system_params_tab;
		  //TODO 
		  //TODO args         client_ole2.list_type;
		  //TODO mswordapp    client_ole2.obj_type;
		  //TODO doc          client_ole2.obj_type;
		  //TODO template     client_ole2.obj_type;
		  //TODO tmpobj2      client_ole2.obj_type;
		  //TODO tmpobj       client_ole2.obj_type;
		  //TODO tmpbook      client_ole2.obj_type;
		  //TODO 
		  //TODO isvisible    boolean = false;
		  //TODO flag         boolean = false;
		  //TODO 
		  //TODO 
		  //TODO function gettype(intype in varchar2) return varchar2;
		  //TODO function getshortname(inname in varchar2) return varchar2;
		  //TODO function getlocalseq return varchar2;



/**
 * This method is execute a Dao class method when trigger event is fired
 *
 * @param params
 *
 * @throws Exception
*/
	public List<ReferenceCodes> getReferenceCode(final ReferenceCodes paramBean) {
		  //TODO function get_reference_code
//		String ( = "";

		  //TODO )
		 //TODO  this.is();
		 //--@@@Abhianv: 17-Jul-2009.This function returns reference code for the given domain and list_seq.

		  //TODO 
			List<ReferenceCodes> referenceCodesList = oiuiwpgnRepository.getReferenceCode(paramBean);
		  //TODO 
		//TODO fetch ref_code_cur into v_code;
		  //TODO 
		  //TODO return v_code;
		  //TODO 
return referenceCodesList;
}


/**
 * This method is execute a Dao class method when trigger event is fired
 *
 * @param params
 *
 * @throws Exception
*/
//AssignContextParam(IwpTemplateModules paramBean) {
//		String ,pTemplateId = "";
//		  //TODO ) is
//		  //TODO 
//		String lvTotRec = 0;
//		String lvModuleName;
//		String lvBlockName;
//		String lvFieldName;
//		String lvContextFlag;
//		String lvFieldDataType;
//		String lvFieldValue;
//		String lvDataString;
//		String lvTemplateId;
//		  //TODO i pls_integer = 1;
//		  //TODO t pls_integer = 0;
//		  //TODO 
//		 //--@@@ ERIC MAR-2011 Enabling non-offender Context
//		String lvLastParameterName;
//		  //TODO found_flag boolean;
//		  //TODO 
//		  //TODO 
//		  //TODO lv_tot_rec = iwp_10g.get_record_count;
//		  //TODO 
//		if (lvTotRec > 0 ){
//		 //TODO  this.{();
//		 //--except the field value, all the other parametres have been pushed into the array as upper case
//		  //TODO this.iwp10gGetBlockData(lv_module_name,lv_block_name,lv_field_name,lv_field_data_type,lv_field_value,lv_context_flag,lv_template_id,i);
//		  //TODO 
//		  //TODO 
//		 //--@@@ Ruxandra 02-FEB-2012 - DEBUG
///*
//         message('NO: ' || TO_CHAR(lv_tot_rec ) || ' template id: ' ||lv_template_id || ' module_name: ' ||lv_module_name || ' block_name: ' ||lv_block_name 
//                             || ' field name: ' || lv_field_name || ' field type: ' || lv_field_data_type 
//                             || ' field value: ' || lv_field_value || ' document context flag: ' || lv_context_flag);
//         pause;
//         */      
//		  //TODO 
//		if (    lvTemplateId===contDataRecModel.templateId &&  lvFieldName ===contDataRecModel.fieldName --02-feb-2012  missing condition resulting in total mismatch of parameters with their values when multiple bookmarks with multiple parameters ){
//		 //--3587 : Added code to change date formate.
//		if (contDataRecModel.parameterDataType==='d' ){
//		  //TODO lv_field_value = to_char(to_date(lv_field_value , 'dd-mon-yyyy'), oms_miscellaneous.get_profile_value('display', 'date'));
//		}
//		  //TODO 
//		 //--when the parameter is marked as document context
//		if (    upper(lvFieldName)===upper(contDataRecModel.fieldName) &&  contDataRecModel.documentContextFlag==='y' ){
//		  //TODO 
//		 //--@@@ ERIC MAR-2011 Enabling non-offender Context - DEBUG
//		 //--MESSAGE( 'lv_data_string: '||lv_data_string);pause;
//		  //TODO 
//		 //--@@@ ERIC MAR-2011 -- Check to see if parameter has already been added.
//		if (nvl(instr(lvDataString, lvFieldName),0)===0 ){
//		if (lvDataString = null ){
//		  //TODO lv_data_string = substr(lv_field_name||' '||lv_field_value,1,256);
//		} else {
//		  //TODO lv_data_string = substr(lv_data_string||' '||lv_field_name||' '||lv_field_value,1,256);
//		}
//		}
//		}
//		  //TODO 
///*-- ORIGINAL CODE FOR ADDING PARAMETERS TO ARRAY
//						t := parameter.last;
//						parameter(t+1).name := cont_data_rec.parameter_name;
//						parameter(t+1).parameter_value := lv_field_value;  
//						*/
//		 //--start Eric
//		 //--@@@ ERIC MAR-2011 Enabling non-offender Context - DEBUG
//		 //--MESSAGE('lv_last_parameter_name:'||NVL(lv_last_parameter_name,'0')||' cont_data_rec.parameter_name:'||cont_data_rec.parameter_name);pause;
//		  //TODO 
//		 //--@@@ ERIC MAR-2011 Controling how many times the same parameter is added to parameter list.
//		if (nvl(lvLastParameterName,'0')  !==  contDataRecModel.parameterName ){
//		  //TODO 
//		 //-- Reset found flag before search.
//		  //TODO found_flag = false;
//		  //TODO 
//		 //-- Check if parameter already in array
//		if (parameterModel.exists(i) ){
//		if (parameter(i)Model.name===contDataRecModel.parameterName ){
//		 //-- Found in array. Set value at current position. Set found_flag TRUE
//		  //TODO parameter(i).parameter_value = lv_field_value;
//		  //TODO found_flag = true;
//		 //TODO  this.exit();
//		}
//		}
//		 //TODO  this.}();
//		  //TODO 
//		 //-- If parameter not found in list add it to the end
//		if (foundFlag===false ){
//		  //TODO t = parameter.last;
//		  //TODO parameter(t+1).name = cont_data_rec.parameter_name;
//		  //TODO parameter(t+1).parameter_value = lv_field_value;
//		}
//		  //TODO 
//		}
//		 //--Set last_parameter
//		  //TODO lv_last_parameter_name = cont_data_rec.parameter_name;
//		  //TODO 
//		 //--end Eric
//		}
//		 //TODO  this.}();
//		  //TODO 
//		  //TODO exit when i = lv_tot_rec;
//		  //TODO i = i+1;
//		 //TODO  this.}();
//		  //TODO ctrl_bl.document_context = lv_data_string;
//		 //-- MESSAGE( 'lv_data_string: '||lv_data_string);
//		 //--pause;
//		  //TODO 
//		  //TODO 
//		 //--@@@ ERIC MAR-2011 Check content of array. FOR DEBUGGING ERIC
//		 //----------------------------------
//		 //-- TABLE ARRAY: parameter
//		 //----------------------------------
//		String ;
//		String ;
//		String ;
//		String ;
//		 //----------------------------------
///*
//   for i in parameter.first..parameter.last loop
//      if parameter.exists(i) then
//         message('ASSIGN_CONTEXT_PARAM: NAME:'||parameter(i).name||' ,VALUE:'||parameter(i).parameter_value);pause;
//      end if;
//   end loop;
//*/
//		  //TODO 
//		}
//}


/**
 * This method is used to execute a record group
 *
*/
public List<ReferenceCodes> rgStatusRecordGroup() {
		return oiuiwpgnRepository.rgStatusRecordGroup();

}

}