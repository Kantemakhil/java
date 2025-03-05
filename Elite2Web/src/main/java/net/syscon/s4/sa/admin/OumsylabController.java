package net.syscon.s4.sa.admin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.SystemLabelCommitBean;
import net.syscon.s4.im.beans.SystemLables;

@EliteController
public class OumsylabController {
 
    
    @Autowired
    private OumsylabService oumsylabService;
    
    private static String DEFAULT_LANGUAGE = "en-US";  
	
	private static String PROP_FILE_EXT = ".properties";
	
	private static String BACK_SLASH = "/";
	
	private static String I10N_FOLDER = "l10n";
	
	private static String APP_PROP_FILE_NAME = "appmsg";
	
	private static String LOGIN_PROP_FILE_NAME = "loginmsg";
	
	private static String SYSTEM_PROFILE = "system-profile.";
	
	private static List<OmsModules> moduleList=null;

    /**
     * Logger object used to print the log in the file
     */
    private final static Logger logger = LogManager.getLogger(OumsylabController.class.getName());

    /**
     * Fetching the record from database table
     *
     * @Param searchRecord
     */
    @PreAuthorize("hasEliteRole('read')")
    @RequestMapping(value = "/oumsylab/rleInarcExecuteQuery", method = RequestMethod.POST)
    public List<OmsModules> rleInarcExecuteQuery(@RequestBody final OmsModules searchBean) {
        List<OmsModules> searchResult = new ArrayList<>();
        try {
            searchResult = oumsylabService.rleInarcExecuteQuery(searchBean);
            moduleList=searchResult;
        } catch (Exception e) {
            final OmsModules bean = new OmsModules();
            logger.error("Exception :", e);
            bean.setErrorMessage(e.getMessage());
            searchResult.add(bean);
        }
        return searchResult;
    }


    /**
     * getting cgfkRleInarc1Code LOV values
     */
    @RequestMapping(value = "oumsylab/labelExecuteQuery", method = RequestMethod.POST)
    public List<SystemLables> labelExecuteQuery(@RequestBody final SystemLables systemLables) {
        List<SystemLables> recordList = new ArrayList<>();
        try {
            recordList = oumsylabService.labelExecuteQuery(systemLables.getModuleName());
        } catch (Exception e) {
            final ReferenceCodes obj = new ReferenceCodes();
            logger.error("oumsylab/labelExecuteQuery Error :", e);
            obj.setErrorMessage(e.getMessage());
        }
        return recordList;
    }
    
    /**
     * getting Keys by using msg Key values
     */
    @RequestMapping(value = "oumsylab/labelsByMsgKey", method = RequestMethod.POST)
    public List<SystemLables> labelByKeyExecuteQuery(@RequestBody final SystemLables systemLables) {
        List<SystemLables> recordList = new ArrayList<>();
        try {
            recordList = oumsylabService.labelByKeyExecuteQuery(systemLables.getMsgKey());
        } catch (Exception e) {
            final ReferenceCodes obj = new ReferenceCodes();
            logger.error("oumsylab/labelsByMsgKey Error :", e);
            obj.setErrorMessage(e.getMessage());
        }
        return recordList;
    }
    
    @RequestMapping(value = "/oumsylab/countOfLabel", method = RequestMethod.GET)
    public Integer countOfLabel() {
        Integer count=0;
        try {
        	count = oumsylabService.countOfLabel();
        } catch (Exception e) {
            final ReferenceCodes obj = new ReferenceCodes();
            logger.error("/oumsylab/countOfLabel Error :", e);
            obj.setErrorMessage(e.getMessage());
        }
        return count;
    }

    @RequestMapping(value = "/oumsylab/countOfProfile", method = RequestMethod.GET)
    public Integer countOfProfile() {
        Integer count=0;
        try {
        	count = oumsylabService.countOfProfile();
        } catch (Exception e) {
            final ReferenceCodes obj = new ReferenceCodes();
            logger.error("Exception :", e);
            obj.setErrorMessage(e.getMessage());
        }
        return count;
    }
   
    /**
     * Fetching the record from database table
     *
     * @Param searchRecord
     */
    
    @RequestMapping(value = "oumsylab/updateSystemlabel", method = RequestMethod.POST)
    public @ResponseBody Integer updateSystemlabel(@RequestBody final SystemLabelCommitBean commitBean) {
        int liReturn = 0;
        String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        commitBean.getUpdateList().forEach(data->data.setModifyUserId(userName));
        try {
            liReturn = oumsylabService.updateSystemlabel(commitBean.getUpdateList());
        } catch (Exception e) {

            logger.error("Exception :", e);
            if (e instanceof DuplicateKeyException && e.getMessage().contains("ROLE_INACCESSIBLE_REF_CODES_PK")) {
                liReturn = 2;
            }

        }
        return liReturn;
    }
    
    


    /**
     * Perfomring basic Oracle form functions i.e. insert,delete, update int the
     * database table
     *
     * @Param commitBean
     */
    public boolean checkModuleExists(String Module){
    	boolean checkMod=false;
    	for (OmsModules omsModules : moduleList) {
			if(omsModules!=null && omsModules.getModuleName().equals(Module.toUpperCase())) {
				checkMod= true;
			}
		}
    	return checkMod;
    }
    @RequestMapping(value = "/oumsylab/setSystemProfilesLables" , method = RequestMethod.GET)
	public String setSystemProfileToSystemlabels(){
		List<SystemProfiles> systemProfiles = oumsylabService.getSystemProfiles();
		SystemLables systemLablesTemp= new SystemLables();
		for(SystemProfiles sysProfiles : systemProfiles) {
			if(sysProfiles.getProfileType().equals("LABEL")) {
			systemLablesTemp.setMsgKey(OumsylabController.SYSTEM_PROFILE+sysProfiles.getProfileCode().toLowerCase().replaceAll("_", "-"));
			systemLablesTemp.setMsgType(sysProfiles.getProfileType());
			systemLablesTemp.setMsgValue(sysProfiles.getProfileValue());
			systemLablesTemp.setModuleName("PROFILE");
			systemLablesTemp.setCreateUserId("OMS_OWNER");
			oumsylabService.setSystemProfileToSystemlabels(systemLablesTemp);
			}
		}
		return "success";
	}
	
	@RequestMapping(value = "/oumsylab/setSystemLables" , method = RequestMethod.GET)
	public String setSystemLables() {
		String appMsgfilename = this.getClass().getResource(OumsylabController.BACK_SLASH + OumsylabController.I10N_FOLDER
				+ OumsylabController.BACK_SLASH + OumsylabController.DEFAULT_LANGUAGE + OumsylabController.BACK_SLASH
				+ OumsylabController.APP_PROP_FILE_NAME + OumsylabController.PROP_FILE_EXT).getPath();
		String loginMsgfilename = this.getClass().getResource(OumsylabController.BACK_SLASH + OumsylabController.I10N_FOLDER
				+ OumsylabController.BACK_SLASH + OumsylabController.DEFAULT_LANGUAGE + OumsylabController.BACK_SLASH
				+ OumsylabController.LOGIN_PROP_FILE_NAME + OumsylabController.PROP_FILE_EXT).getPath();
		List<SystemLables> checkKeyexits= new ArrayList<>();
		try {
			System.out.println("Load " + appMsgfilename);
			Properties appProp = loadProperties(appMsgfilename);
			Set<String> appProprietes = appProp.stringPropertyNames();
			System.out.println("Properties are" + appMsgfilename);
			for (String prop : appProprietes) {
				if (prop != null) {
					System.out.println(prop + "=" + appProp.getProperty(prop));
					SystemLables systemlable = new SystemLables();
					systemlable.setMsgKey(prop);
					systemlable.setMsgValue(appProp.getProperty(prop));
					if(prop.indexOf(".")==-1 ||prop.substring(0, prop.indexOf(".")).toUpperCase().equals("COMMON") || !(checkModuleExists(prop.substring(0, prop.indexOf("."))))) {
						systemlable.setModuleName("COMMON");	
					}else if(checkModuleExists(prop.substring(0, prop.indexOf(".")))) {
						systemlable.setModuleName(prop.substring(0, prop.indexOf(".")).toUpperCase());
					}
					systemlable.setMsgType("LABEL");
					systemlable.setCreateUserId("OMS_OWNER");
					if(checkKeyExits(checkKeyexits,systemlable.getMsgKey())){
						checkKeyexits.add(systemlable);
						oumsylabService.setSystemLables(systemlable);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("Load " + loginMsgfilename);
			Properties loginProp = loadProperties(loginMsgfilename);
			Set<String> loginProprietes = loginProp.stringPropertyNames();
			System.out.println("Properties are" + loginMsgfilename);
			for (String prop : loginProprietes) {
				if (prop != null) {
					System.out.println(prop + "=" + loginProp.getProperty(prop));
					SystemLables systemlable = new SystemLables();
					systemlable.setMsgKey(prop);
					systemlable.setMsgValue(loginProp.getProperty(prop));
					systemlable.setModuleName("LOGIN");
					systemlable.setMsgType("LABEL");
					systemlable.setCreateUserId("OMS_OWNER");
					if(checkKeyExits(checkKeyexits,systemlable.getMsgKey())){
						checkKeyexits.add(systemlable);
						oumsylabService.setSystemLables(systemlable);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	boolean checkKeyExits(List<SystemLables> sl, String key ) {
		boolean isTrue=true;
		for (SystemLables systemLables : sl) {
			if(systemLables.getMsgKey().equals(key)) {
				isTrue=false;
				break;
			}
		}
		return isTrue;
	}
	
	public static Properties loadProperties(String filename1) throws FileNotFoundException, IOException {
		  Properties prop = new Properties();
		  BufferedReader reader = new BufferedReader(new FileReader(filename1));
		  prop.load(reader);
		  return prop;
	}


}