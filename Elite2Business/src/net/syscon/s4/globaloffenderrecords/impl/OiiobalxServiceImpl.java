package net.syscon.s4.globaloffenderrecords.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globaloffenderrecords.OiiobalxRepository;
import net.syscon.s4.globaloffenderrecords.OiiobalxService;
import net.syscon.s4.inmate.beans.OffExternalAccountBalances;

@Service
public class OiiobalxServiceImpl extends BaseBusiness implements OiiobalxService {
	
	private static Logger logger = LogManager.getLogger(OiiobalxServiceImpl.class.getName());
	@Autowired
	private OiiobalxRepository oiiobalxRepository;
	
	@Override
	public List<OffExternalAccountBalances> getOffExternalAccountBalances(OffExternalAccountBalances searchBean) {
		return oiiobalxRepository.getOffExternalAccountBalances(searchBean);
	}

	@Override
	public String saveOffExternalAccountBalances(String data,String userId) {
		if(data!=null) {
			List<OffExternalAccountBalances> accountList=prepareAccountBalanceData(data);
			logger.info(accountList);
			if(accountList!=null && !accountList.isEmpty()) {
				for(OffExternalAccountBalances accBalObj:accountList) {
					if(accBalObj!=null && accBalObj.getRootOffenderId()!=null && accBalObj.getRootOffenderId()!= 0 && accBalObj.getAccountType()!=null) {
					OffExternalAccountBalances offenderAccInf=oiiobalxRepository.getOffenderAccountDetails(accBalObj.getRootOffenderId(), accBalObj.getAccountType());
					if(offenderAccInf!=null) {
						accBalObj.setModifyUserId(userId);
						oiiobalxRepository.updateOffenderExternalBalance(accBalObj);
					}else {
						accBalObj.setCreateUserId(userId);
						oiiobalxRepository.InsertOffenderExternalBalance(accBalObj);
					}
					
					}
				}
			}
			
		}
		return "success";
	}
	
	private List<OffExternalAccountBalances> prepareAccountBalanceData(String data){
		JSONObject jsonObject = null;
		List<OffExternalAccountBalances> accountList=new ArrayList<>();
		Set<String> accounIdList=new HashSet<>();
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	    dateFormat.setTimeZone(TimeZone.getTimeZone("Z"));
		if(data!=null) {
			try {
				data = data.replaceAll("\"\\[", "[").replaceAll("]\"","]").replaceAll("\"\\{", "{").replaceAll("}\"","}");
				jsonObject = new JSONObject(data);
				JSONObject obj=(JSONObject) jsonObject.get("accountsData");
				JSONArray array=obj.getJSONArray("accountBalances");
				 for(int i = 0; i < array.length(); ++i) {
					 JSONObject jsonObj = array.getJSONObject(i);
					 if(jsonObj!=null) {
					 OffExternalAccountBalances balanceObj=new OffExternalAccountBalances();
					 if( jsonObj.get("accountId")!=null) {
						 balanceObj.setAccountId(Long.parseLong(jsonObj.get("accountId").toString()));
						 balanceObj.setOffenderIdDisplay(StringUtils.leftPad(jsonObj.get("accountId").toString(), 10, "0"));
						 accounIdList.add(balanceObj.getOffenderIdDisplay());
					 } 
					 if(jsonObj.get("balanceType")!=null) {
						 balanceObj.setAccountType(jsonObj.get("balanceType").toString());
					 }
					 if(jsonObj.get("prisonerName")!=null) {
						 balanceObj.setAccountDetails(jsonObj.get("prisonerName").toString());
					 }
					 if(jsonObj.get("lastUpdated")!=null) {
						 Date dateConverted = dateFormat.parse(jsonObj.get("lastUpdated").toString());
						 balanceObj.setLastChanged(dateConverted);
					 }
					 if(jsonObj.get("balanceAmount")!=null) {
						balanceObj.setBalance(Double.parseDouble(jsonObj.get("balanceAmount").toString()));
					 }
					 accountList.add(balanceObj);
					 }
				}
				 List<VHeaderBlock> rootOffenderData=oiiobalxRepository.getRootOffenderId(accounIdList);
				 if(accountList!=null && !accountList.isEmpty() && rootOffenderData!=null && !rootOffenderData.isEmpty()) {
					 setRootOffenderId(rootOffenderData,accountList);
				 }
				 
				
			} catch (Exception e) {
				logger.error("OiiobalxServiceImpl :: error in prepareAccountBalanceData ::"+e.getMessage());
			}
		}
		return accountList;
	
		
	}
	
	
	private void setRootOffenderId(List<VHeaderBlock> rootOffenderData,List<OffExternalAccountBalances> accountList) {
		for(OffExternalAccountBalances obj:accountList) {
			for(VHeaderBlock data:rootOffenderData) {
				if(obj.getOffenderIdDisplay().equals(data.getOffenderIdDisplay())) {
					obj.setRootOffenderId(new Long(data.getRootOffenderId().toString()));
				}
			}
		}
		
		
	}

	@Override
	public Date getLastUpdatedDate() {
		Date returnDate=null;
		try {
			returnDate=oiiobalxRepository.getLastUpdatedDate();
		}catch(Exception e){
			logger.error("error in getLastUpdatedDate"+ e.getMessage());
		}
		 
		
		return returnDate;
	}

}
