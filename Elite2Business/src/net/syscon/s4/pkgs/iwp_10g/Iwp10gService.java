package net.syscon.s4.pkgs.iwp_10g;

import java.util.List;

import net.syscon.s4.im.beans.IwpTemplates;
import net.syscon.s4.sa.admin.beans.IwpTemplateRoles;

public interface Iwp10gService {

	 List<IwpTemplateRoles> getTemplRoles(final IwpTemplates templRolesCur);
}
