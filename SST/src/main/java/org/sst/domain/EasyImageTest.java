package org.sst.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class EasyImageTest {

	public static void main(String[] args) throws IOException {
		File f = new File("xxxxx.jpg");

		EasyImage easyImage = new EasyImage(f);

		if (!easyImage.isSuppoprtedImageFormat()) {

		    System.out.println("not supported image type");
		    return;

		}

		/* resize */

		EasyImage resizedImage = easyImage.resize(80,80);
		FileOutputStream out = new FileOutputStream(f);
		resizedImage.writeTo(out, "png"); //resizedImage.writeTo(out, "jpg");

		/* crop */

		// EasyImage cropedImage = easyImage.crop(x,y,w,h);
		// FileOutputStream out = new FileOutputStream(f);
		// cropedImage.writeTo(out, "png"); //cropedImage.writeTo(out, "jpg");

	}

}
