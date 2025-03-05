package net.syscon.s4.common.beans;
import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

import java.util.List;

@XmlRootElement
public class RelatedSentenceOrdersCommitBean extends BaseModel {
	private static final long serialVersionUID = 1L;
	private List<RelatedSentenceOrders> insertList;
	private List<RelatedSentenceOrders> deleteList;
	private List<RelatedSentenceOrders> updateList;

	public void setInsertList(List<RelatedSentenceOrders> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<RelatedSentenceOrders> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<RelatedSentenceOrders> deleteList){
		this.deleteList = deleteList;
	}

	public List<RelatedSentenceOrders> getInsertList(){
		return insertList;
	}

	public List<RelatedSentenceOrders> getUpdateList(){
		return updateList;
	}

	public List<RelatedSentenceOrders> getDeleteList(){
		return deleteList;
	}

}
