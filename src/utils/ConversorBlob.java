package utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


import javax.imageio.ImageIO;

public class ConversorBlob {

	public static BufferedImage inputStreamToImage(InputStream inputStream) {
		
	    try {
	        if (inputStream == null) {
	            return null;
	        }

	        // Converte o InputStream em uma imagem.
	        BufferedImage image = ImageIO.read(inputStream);

	        return image;
	    } catch (IOException ex) {
	        ex.printStackTrace();
	        return null;
	    }
	}
}