package net.syscon.s4.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import net.syscon.s4.common.beans.dao.RecordGroup;

//@EnableCaching
@Component
public class CacheService {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(CacheService.class.getName());
	private static Map<Long, List<RecordGroup>> store = new HashMap<Long, List<RecordGroup>>();
	static {
		
		List<RecordGroup> recordList = null;
		//IOidreleaBusiness businessObj = null;
		RecordGroup obj =  null;
		try {
			obj = new RecordGroup();
			//businessObj = ConfigBusinessManager.getOidreleaBusiness();
			recordList=new ArrayList<RecordGroup>();
			//recordList = businessObj.rgMovementReasonCodeRgroup();
		} catch(Exception e){
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		
		
		store.put(1L, recordList);
		store.put(3L, recordList);
		store.put(5L, recordList);
		 
	}

	@Cacheable(value = "reasons", key = "#id")
	public List<RecordGroup> get(long id) {
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			logger.error(e);
		}
		List<RecordGroup> recordListCache =store.get(id);
		return recordListCache;
	}

	@CachePut(value = "reasons", key = "#id")
	public List<RecordGroup> putRGReasonCodeBean(String value, long id, String  code) {
		List<RecordGroup> recordListCache = store.get(id);
		//recordListCache.setValue(value);
		//recordListCache.setCode(code);
		return recordListCache;
	}

	@CacheEvict(value = "reasons", key = "#id")
	public void evict(long id) {		
		// evict
	}
}