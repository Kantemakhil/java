package net.syscon.s4.globalconfiguration.impl;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.syscon.s4.common.beans.ModuleInsDashboardBean;
import net.syscon.s4.common.beans.ModuleInsDashboardCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globalconfiguration.OuminsdbRepository;
import net.syscon.s4.globalconfiguration.OuminsdbService;
import net.syscon.s4.globalconfiguration.OumsysetService;
import net.syscon.s4.globalconfiguration.OumucreatService;

@Service
public class OuminsdbServiceImpl extends BaseBusiness implements OuminsdbService{
	
	@Autowired
	private OuminsdbRepository ouminsdbRepository;
	
	@Autowired
	OumucreatService oumucreatService;
	
	@Autowired
	OumsysetService oumsysetService;
	
	RestTemplate restTemplate = new RestTemplate();
	
	public static final String SLASH = "/";
	public static final String API = "api";
	public static final String KEY_CODE = "KEY_CODE";
	public static final String VALUE = "VALUE";
	public static final String DASHBOARDS = "v2.0/items?ItemType=2";
	
	private static Logger logger = LogManager.getLogger(OumsysetServiceImpl.class.getName());

	/**
	 * Creates new OuminsdbServiceImpl class Object
	 */
	public OuminsdbServiceImpl() {
	}


	@Override
	public List<ModuleInsDashboardBean> getInsModDashboard() {
		List<ModuleInsDashboardBean> list = new ArrayList<ModuleInsDashboardBean>();
		list = ouminsdbRepository.getInsModDashboard();
		for(ModuleInsDashboardBean obj:list) {
			if(obj.getActiveFlag().equals("Y") && obj.getOffenderSpecificFlag().equals("Y")) {
				obj.setCanDisplay(true);
			}else{
				obj.setCanDisplay(false);
			}
		}
		return list;
	}

	@Transactional
	public Integer InsModDashboardCommit(ModuleInsDashboardCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(data->{data.setCreateUserId(commitBean.getCreateUserId());});
			liReturn = ouminsdbRepository.insModDashboardInsert(commitBean.getInsertList());
			if(1 == liReturn) {
				liReturn = ouminsdbRepository.postInsert(commitBean.getInsertList());
				commitBean.getInsertList().forEach(data->{
					if(data.getOffenderSpecificFlag() !=null && data.getOffenderSpecificFlag().equals("Y")) {
						ouminsdbRepository.offenderSpecificModuleInsert(data);
					}
				});
				
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(data->{data.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = ouminsdbRepository.insModDashboardUpdate(commitBean.getUpdateList());
			if(1 == liReturn) {
				commitBean.getUpdateList().forEach(data->{
					if(data.getOffenderSpecificFlag() !=null && data.getOffenderSpecificFlag().equals("Y")) {
						ouminsdbRepository.offenderSpecificModuleInsert(data);
					}else {
						ouminsdbRepository.offenderSpecificModuleDelete(data.getModule());
					}
				
				});
				
			}
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(data->{data.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = ouminsdbRepository.insModDashboardDelete(commitBean.getDeleteList());
			if(1 == liReturn) {
				commitBean.getDeleteList().forEach(data->{
					ouminsdbRepository.offenderSpecificModuleDelete(data.getModule());
			});
				liReturn = ouminsdbRepository.postDelete(commitBean.getDeleteList());
				
				
			}
		}
		return liReturn;
	}
	

	@Override
	public ModuleInsDashboardBean getInsDashboardId(String moduleName) {
		return ouminsdbRepository.getInsDashboardId(moduleName);
	}


	@Override
	public List<ReferenceCodes> getInsDashboardList() {
		List<ReferenceCodes> dashBoardList = new ArrayList<ReferenceCodes>();
		String url = "";
		String insSerUrl = "";
		String siteId = "";
		try {
			HttpHeaders headers = new HttpHeaders();
			String authToken = oumucreatService.getAuthToken();
			headers.set("Authorization", authToken);
			List<Map<String, Object>> returnList = oumsysetService.getSysData("INSIGHTS", "INSIGHTS");
			if (returnList != null && !returnList.isEmpty()) {
				for (Map<String, Object> object : returnList) {
					if (object.containsKey(KEY_CODE) && object.get(KEY_CODE) != null
							&& object.get(KEY_CODE).equals("INSI_PRIV_URL") && object.containsKey(VALUE)
							&& object.get(VALUE) != null) {
						insSerUrl = object.get(VALUE).toString();
					} else if (object.containsKey(KEY_CODE) && object.get(KEY_CODE) != null
							&& object.get(KEY_CODE).equals("INS_SITE_IDF") && object.containsKey(VALUE)
							&& object.get(VALUE) != null) {
						siteId = object.get(VALUE).toString();
					} 
				}
			}
			url = insSerUrl + SLASH + API + SLASH + siteId + SLASH + DASHBOARDS ;
			URI uri = new URI(url);
			HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
			HttpEntity<byte[]> response = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, byte[].class);
			String responseString = StringUtils.toEncodedString(response.getBody(), StandardCharsets.UTF_8);
			List<Map<String, Object>> responseList = new ObjectMapper().readValue(responseString, ArrayList.class);
			System.out.println(responseList);
			for(Map<String, Object> responseMap : responseList) {
				ReferenceCodes referenceCodes = new ReferenceCodes();
				referenceCodes.setCode(responseMap.get("Id").toString());
				referenceCodes.setDescription(responseMap.get("Name").toString());
				dashBoardList.add(referenceCodes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dashBoardList;
	}

}
