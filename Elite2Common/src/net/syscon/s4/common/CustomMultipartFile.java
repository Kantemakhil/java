package net.syscon.s4.common;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class CustomMultipartFile implements MultipartFile {
	private final byte[] fileByteArray;

	private static Logger logger = LogManager.getLogger(CustomMultipartFile.class.getName());
	
	public CustomMultipartFile(byte[] byteArray) {
		this.fileByteArray = byteArray;
	}

	@Override
	public String getName() {
		// TODO - implementation depends on your requirements
		return null;
	}

	@Override
	public String getOriginalFilename() {
		// TODO - implementation depends on your requirements
		return null;
	}

	@Override
	public String getContentType() {
		// TODO - implementation depends on your requirements
		return null;
	}

	@Override
	public boolean isEmpty() {
		return fileByteArray == null || fileByteArray.length == 0;
	}

	@Override
	public long getSize() {
		return fileByteArray.length;
	}

	@Override
	public byte[] getBytes() throws IOException {
		return fileByteArray;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(fileByteArray);
	}

	@Override
	public void transferTo(File dest) throws IOException, IllegalStateException {		
		try (FileOutputStream fos = new FileOutputStream(dest);){
			fos.write(fileByteArray);
		} catch (Exception e) {
			logger.error("Cannot create temp file : ",e);
			throw e;
		} 
	}
}