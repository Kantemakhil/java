package net.syscon.s4.sa.admin.beans;

import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class IwpCompositeBookmarkCommitBean extends BaseModel  {
	private static final long serialVersionUID = 1L;
	private List<IwpCompositeBookMarks> updateList;
	
	public List<IwpCompositeBookMarks> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<IwpCompositeBookMarks> updateList) {
		this.updateList = updateList;
	}

}
