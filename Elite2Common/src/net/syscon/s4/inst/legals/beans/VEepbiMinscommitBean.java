package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.inst.legals.legalorders.VEepbiMins;

@XmlRootElement
public class VEepbiMinscommitBean  extends BaseModel implements Serializable{
	private List<VEepbiMins> insertList;
	private List<VEepbiMins> deleteList;
	private List<VEepbiMins> updateList;

}
