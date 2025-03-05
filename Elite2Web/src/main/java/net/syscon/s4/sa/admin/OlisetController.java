package net.syscon.s4.sa.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.Images;
import net.syscon.s4.im.beans.SystemLabelCommitBean;
import net.syscon.s4.im.beans.SystemLables;

@EliteController
public class OlisetController {
 
    
    @Autowired
    private OlisetService olisetService;
    


    /**
     * Logger object used to print the log in the file
     */
    private final static Logger logger = LogManager.getLogger(OlisetController.class.getName());

   
   
    
    


    /**
     * Perfomring basic Oracle form functions i.e. insert,delete, update int the
     * database table
     *
     * @Param commitBean
     */
   
   @RequestMapping(value = "oliset/labelExecuteQuery", method = RequestMethod.POST)
    public List<SystemLables> labelExecuteQuery(@RequestBody final SystemLables systemLables) {
        List<SystemLables> recordList = new ArrayList<>();
        try {
            recordList = olisetService.labelExecuteQuery(systemLables.getModuleName());
        } catch (Exception e) {
            final ReferenceCodes obj = new ReferenceCodes();
            logger.error("Exception :", e);
            obj.setErrorMessage(e.getMessage());
        }
        return recordList;
    }
   
   @RequestMapping(value = "oliset/headerLabelExecuteQuery", method = RequestMethod.POST)
   public List<SystemLables> headerlabelExecuteQuery(@RequestBody final SystemLables systemLables) {
       List<SystemLables> recordList = new ArrayList<>();
       List<SystemLables> hRecordList = new ArrayList<>();
       try {
           recordList = olisetService.labelExecuteQuery(systemLables.getModuleName());
       } catch (Exception e) {
           final ReferenceCodes obj = new ReferenceCodes();
           logger.error("Exception :", e);
           obj.setErrorMessage(e.getMessage());
       }
       for (SystemLables systemLables2 : recordList) {
		if(systemLables2.getMsgKey().equals("home.title.header")) {
			hRecordList.add(systemLables2);
		}
	}
       return hRecordList;
   }
   
   @RequestMapping(value = "oliset/updateSystemlabel", method = RequestMethod.POST)
   public @ResponseBody Integer updateSystemlabel(@RequestBody final SystemLabelCommitBean commitBean) {
       int liReturn = 0;
     final  String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
       try {
    	   commitBean.getUpdateList().forEach(data->{data.setModifyUserId(username);});
           liReturn = olisetService.updateSystemlabel(commitBean.getUpdateList());
       } catch (Exception e) {

           logger.error("Exception :", e);
           if (e instanceof DuplicateKeyException && e.getMessage().contains("ROLE_INACCESSIBLE_REF_CODES_PK")) {
               liReturn = 2;
           }

       }
       return liReturn;
   }
    
	@RequestMapping(value = "/oumsylab/imagesExecuteQuery", method = RequestMethod.POST)
	public List<Images> imagesExecuteQuery(@RequestBody final Images searchBean) {
		List<Images> searchResult = new ArrayList<>();
		try {
			searchResult = olisetService.imagesExecuteQuery(searchBean);
		} catch (Exception e) {
			final Images bean = new Images();
			logger.error(e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@RequestMapping(value="/oumsylab/imagesCommit", method=RequestMethod.POST)
	public @ResponseBody Integer insertContainerImg(@RequestBody final Images images) {
		int liReturn = 0;
		final  String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
	     images.setCreateUserId(username);
		try {
			liReturn = olisetService.insertContainerImg(images);
		}catch(Exception e){
			logger.error("insertContainerImg",e);
		}
		return liReturn;
	}
	
	@RequestMapping(value = "/oumsylab/inactiveImage", method = RequestMethod.GET)
	public String inactiveImage(@RequestBody final Images searchBean) {
		try {
			 olisetService.inactiveImage(searchBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return "SUCCESS";
	}

}