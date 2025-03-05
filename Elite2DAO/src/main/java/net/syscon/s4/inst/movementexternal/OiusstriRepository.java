package net.syscon.s4.inst.movementexternal;

import java.util.List;

import net.syscon.s4.inst.movementexternal.beans.VOiusstri;

public interface OiusstriRepository {

	List<VOiusstri> scheduledtripsExecuteQuery(VOiusstri objvOiusstri);

}
