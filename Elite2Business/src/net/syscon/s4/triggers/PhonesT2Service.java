package net.syscon.s4.triggers;

import net.syscon.s4.common.beans.Phones;

public interface PhonesT2Service {
	void phonesT2Trigger(Phones old, Phones newObj);
}
