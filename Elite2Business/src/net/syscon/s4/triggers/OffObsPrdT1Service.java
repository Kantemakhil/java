package net.syscon.s4.triggers;

import net.syscon.s4.inst.careinplacement.beans.OffObsPrdCharacteristics;

public interface OffObsPrdT1Service {
	Integer offObsPrdT1Trigger(OffObsPrdCharacteristics offObsPrdChara, String sqlOperation);
}
