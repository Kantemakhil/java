package net.syscon.s4.inst.property;

import java.util.List;

import net.syscon.s4.sa.admin.beans.PropertyBundles;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.sa.admin.beans.PropertyBundleCommitBean;
import net.syscon.s4.sa.admin.beans.PropertyBundleItems;
import net.syscon.s4.sa.admin.beans.PropertyBundleItemsCommitBean;

public interface OumbundlService {


	List<PropertyBundles> getPropertyGroups();

	List<PropertyBundleItems> getPropertyItems(String groupId);

	Integer insertUpdateDeletePropertyBundles(PropertyBundleCommitBean commitBean);

	Integer insertUpdateDeletePropertyItems(PropertyBundleItemsCommitBean commitBean);

	List<Caseloads> getCaseloads(StaffMembers searchBean);

}
