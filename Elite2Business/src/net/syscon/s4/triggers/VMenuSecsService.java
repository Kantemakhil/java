package net.syscon.s4.triggers;

import net.syscon.s4.im.beans.VMenuSecs;

public interface VMenuSecsService {
	Integer vMenuSecsTrigger(VMenuSecs vMenuSecsNew, String sqlOperation);
}
