package net.syscon.s4.inst.automatedcounts.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.VIntLocOffenders;
import net.syscon.s4.inst.automatedcounts.OiiinrolRepository;
import net.syscon.s4.inst.automatedcounts.OiiinrolService;

@Service
public class OiiinrolServiceImpl extends BaseBusiness implements OiiinrolService{

@Autowired
private OiiinrolRepository oiiinrolRepository;


/**
 *Creates new OiiinrolServiceImpl class Object 
 */
public OiiinrolServiceImpl(){

}
/**Fetch the records from database table
 *
 * @param searchRecord
 *
 */
public List<VIntLocOffenders> rollListExecuteQuery(final String agyLocId, final Integer internalLocationId){
		return oiiinrolRepository.rollListExecuteQuery(agyLocId,internalLocationId);

}

/**Insert the records from database table
 *
 * @param lstROLL_LIST
 *
 */
@Transactional
public Integer rollListCommit(VIntLocOffenders CommitBean) {
		int liReturn = 0;
		return liReturn;
}

}