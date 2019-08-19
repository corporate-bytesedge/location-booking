package com.bytesedge.bookvenue.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

// Ref : http://blog.netgloo.com/2015/03/03/spring-boot-crop-uploaded-image/
public class ImageUtil {
	public BufferedImage cropImageSquare(byte[] image) throws IOException {
		// Get a BufferedImage object from a byte array
		InputStream in = new ByteArrayInputStream(image);
		BufferedImage originalImage = ImageIO.read(in);

		// Get image dimensions
		int height = originalImage.getHeight();
		int width = originalImage.getWidth();

		// The image is already a square
		if (height == width) {
			return originalImage;
		}

		// Compute the size of the square
		int squareSize = (height > width ? width : height);

		// Coordinates of the image's middle
		int xc = width / 2;
		int yc = height / 2;

		// Crop
		BufferedImage croppedImage = originalImage.getSubimage(
				xc - (squareSize / 2), 
				yc - (squareSize / 2),
				squareSize, // widht
				squareSize // height
		);

		return croppedImage;
	}
}