package net.syscon.s4.inst.movements.housingchanges.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.movements.housingchanges.OcuwarngRepository;
import net.syscon.s4.inst.movements.housingchanges.OcuwarngService;

/**
 * Class OcuwarngServiceImpl
 */
@Service
public class OcuwarngServiceImpl extends BaseBusiness implements OcuwarngService {

	@Autowired
	private OcuwarngRepository ocuwarngRepository;

	/**
	 * Creates new OcuwarngServiceImpl class Object
	 */
	public OcuwarngServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<String> allowOverride(String userName) {
	final 	List<String> returnList = ocuwarngRepository.allowOverride(userName);
		return returnList;
	}

}