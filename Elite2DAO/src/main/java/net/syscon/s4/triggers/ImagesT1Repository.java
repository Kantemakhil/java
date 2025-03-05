package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.im.beans.ImageOriginals;

public interface ImagesT1Repository {

	Integer imageOriginalsInsert(List<ImageOriginals> insertList);

}
