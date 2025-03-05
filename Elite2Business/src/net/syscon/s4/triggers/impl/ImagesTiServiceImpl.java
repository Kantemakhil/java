package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.ImageOriginals;
import net.syscon.s4.triggers.ImagesTiRepository;
import net.syscon.s4.triggers.ImagesTiService;

@Service
public class ImagesTiServiceImpl implements ImagesTiService {
	private final Logger logger = LogManager.getLogger(ImagesTiServiceImpl.class.getName());
	@Autowired
	ImagesTiRepository imagesTiRepository;

	@Override
	public Integer imagesTiTrigger(ImageOriginals imageOriginals) {
		Integer result = 0;
		try {
			result = imagesTiRepository.insert(imageOriginals.getImageId(),imageOriginals.getCreateUserId());
		} catch (Exception e) {
			logger.error("imagesTiTrigger", e);
		}
		return result;
	}

}
