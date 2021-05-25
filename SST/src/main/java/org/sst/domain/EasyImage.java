package org.sst.domain;


import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.imageio.ImageIO;

 

public class EasyImage {
    private BufferedImage buffer;
    

    public EasyImage(File file) throws IOException {
        buffer = ImageIO.read(file);
        
    }

    public EasyImage(URL url) throws IOException {
        buffer = ImageIO.read(url);
    }

    public EasyImage(InputStream stream) throws IOException {
        buffer = ImageIO.read(stream);
    }

    public EasyImage(BufferedImage buffer) {
        this.buffer = buffer;
    }

    public int getWidth() {
        return buffer.getWidth();
    }

    public int getHeight() {
        return buffer.getHeight();
    }

    public EasyImage resize(int width, int height) {
        BufferedImage dest = new BufferedImage(width, height,
            BufferedImage.TYPE_INT_RGB);
        Graphics2D g = dest.createGraphics();
        g.setComposite(AlphaComposite.Src);
        g.drawImage(buffer, 0, 0, width, height, null);
        g.dispose();
        return new EasyImage(dest);
    }

    public EasyImage resize(int width) {
        int resizedHeight = (width * buffer.getHeight()) / buffer.getWidth();
        return resize(width, resizedHeight);
    }

    public EasyImage crop(int d, int e, int f, int h) {
        BufferedImage dest = new BufferedImage(f, h,
            BufferedImage.TYPE_INT_RGB);
        Graphics2D g = dest.createGraphics();
        g.setComposite(AlphaComposite.Src);
        g.drawImage(buffer, 0, 0, f, h, d, e, d + f, e + h,
            null);
        g.dispose();
        
        return new EasyImage(dest);
    }

    public void writeTo(OutputStream stream, String formatName)
            throws IOException {
        ImageIO.write(buffer, formatName, stream);
    }

    public boolean isSuppoprtedImageFormat() {
        return buffer != null ? true : false;
    }
}