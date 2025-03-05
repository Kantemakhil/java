/**
 *
 */
package net.syscon.s4.iwp.eoffender.impl;

import org.springframework.core.io.ByteArrayResource;

/**
 * @author ankur.gupta
 *
 */
public class FileResource extends ByteArrayResource {

	/**
     * The filename to be associated with the {@link MimeMessage} in the form data.
     */
    private final String filename;


    /**
     * Constructs a new {@link FileResource}.
     * @param byteArray A byte array containing data from a {@link MimeMessage}.
     * @param filename The filename to be associated with the {@link MimeMessage} in
     * 	the form data.
     */
    public FileResource(final byte[] byteArray, final String fileName) {
    	 super(byteArray);
         this.filename = fileName;
	}

	@Override
    public String getFilename() {
        return this.filename;
    }


}
