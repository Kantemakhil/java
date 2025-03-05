package net.syscon.s4.sa.admin.impl;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.ImageIcon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.ImageOriginals;
import net.syscon.s4.im.beans.Images;
import net.syscon.s4.im.beans.SystemLables;
import net.syscon.s4.sa.admin.OlisetRepository;
import net.syscon.s4.sa.admin.OlisetService;
import net.syscon.s4.triggers.impl.ImagesTiServiceImpl;

/**
 * Class OumrestaServiceImpl
 */
@Service
public class OlisetServiceImpl extends BaseBusiness implements OlisetService {
	
	@Autowired
	private ImagesTiServiceImpl imagesTiServiceImpl;
	
	 private final static Logger logger = LogManager.getLogger(OlisetServiceImpl.class.getName());
    public OlisetServiceImpl() {
    }

    
    
    @Autowired
    private OlisetRepository olisetRepository;

    
   


    /**
     * This method is used to execute a record group
     *
     * @throws SQLException
     */
    @Override
	public List<SystemLables> labelExecuteQuery(String codeInput) {
		return olisetRepository.labelExecuteQuery(codeInput);
	}

	@Override
	public int updateSystemlabel(List<SystemLables> updateList) {
		return olisetRepository.updateSystemlabel(updateList);
	}
	
	public byte[] resizeImage(byte[] img, int width, int height) {
		double scale;
		Integer newWidth = 0;
		Integer newHeight = 0;

		try {
			final Blob imgBlob = new SerialBlob(img);
			final InputStream iImage = imgBlob.getBinaryStream();
			final BufferedImage bImage = ImageIO.read(iImage);
			final double xScale = (double) bImage.getWidth() / (double) width;
			final double yScale = (double) bImage.getWidth() / (double) height;
			if (xScale < yScale) {
				scale = xScale;
			} else {
				scale = yScale;
			}
			newWidth = (int) (bImage.getWidth() / scale);
			newHeight = (int) (bImage.getHeight() / scale);
		} catch (Exception e) {
			logger.error("resizeImage", e);
		}
		final ImageIcon imageIcon = new ImageIcon(img);
		final BufferedImage bufferImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
		final Graphics2D g2d = (Graphics2D) bufferImage.createGraphics();
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(imageIcon.getImage(), 0, 0, newWidth, newHeight, null);
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(bufferImage, "jpeg", baos);
			final byte[] byteArray = baos.toByteArray();
			return byteArray;
		} catch (Exception e) {
			logger.error("resizeImage", e);
		}
		return null;
	}


	@Override
	public int insertContainerImg(Images images) {
		String string="";
		Images searchBean=new Images();
		int lireturn = 0;
		ImageOriginals obj = new ImageOriginals();
		obj.setImageId(images.getImageId().intValue());
		obj.setCreateUserId(images.getCreateUserId());
		if(images!=null && images.getPreviousImageId()!=null) {
			searchBean.setImageId(images.getPreviousImageId());
			searchBean.setModifyUserId(images.getCreateUserId());
			string=olisetRepository.inactiveImage(searchBean);
		}else {
			string="SUCCESS";
		}
		if(string.equals("SUCCESS")) {
			lireturn =  olisetRepository.insertContainerImg(images);
			imagesTiServiceImpl.imagesTiTrigger(obj);
			
		}else {
			return lireturn;
		}
		return lireturn;
	}

	@Override
	public List<Images> imagesExecuteQuery(Images searchBean) {
		List<Images> returnList = new ArrayList<Images>();
		
		returnList = olisetRepository.imagesExecuteQuery(searchBean);
		return returnList;
		
	}

	@Override
	public String inactiveImage(Images searchBean) {
		return olisetRepository.inactiveImage(searchBean);
	}
	
	@Override
	public List<Images> getLoginLogo() {
		
		return olisetRepository.getLoginLogo();
	}
}