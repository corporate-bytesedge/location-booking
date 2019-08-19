package com.bytesedge.bookvenue.controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

@Controller
@SuppressWarnings("serial")
public class CaptchaServlet extends HttpServlet {
	public static final String SESSION_CAPTCHA_KEY = "session.captcha.key";
	public static final String INPUT_CAPTCHA_KEY = "captchaText";
	
    private static final Random random = new Random(System.currentTimeMillis()); 

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        generateCaptcha(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        generateCaptcha(request, response);
    }
    
    private void generateCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        String captcha = generateRandomText();
        session.setAttribute(SESSION_CAPTCHA_KEY, captcha);
        response.setContentType("image/jpeg");
        response.setHeader("Cache-Control", "no-cache");
        BufferedImage captchaImage = generateImage(captcha);
        ServletOutputStream sout = response.getOutputStream();
        ImageIO.write(captchaImage, "jpg", sout);
    }

    public static void resetCaptcha(HttpServletRequest request) {
    	try {
	        HttpSession session = request.getSession(true);
	        session.setAttribute(SESSION_CAPTCHA_KEY, System.currentTimeMillis());
    	} catch (Exception e) {
    		// ignore
    	} 
    }

    private BufferedImage generateImage(String text) {
        BufferedImage buffer = new BufferedImage(10, 20, BufferedImage.TYPE_INT_RGB);        
        Graphics2D g2 = buffer.createGraphics();
        Font font = new Font("Arial", Font.HANGING_BASELINE | Font.ITALIC, 70);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        FontRenderContext fc = g2.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(text, fc);

        // calculate the size of the text
        int width  =  20+(int) bounds.getWidth();
        int height =  10+(int) bounds.getHeight();

        // prepare some output
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        g2 = buffer.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setFont(font);

        // actually do the drawing
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, width, height);

        //int r = (int) ((Math.random() * 250));
        //int g = (int) ((Math.random() * 50));
        //int b = (int) ((Math.random() * 100));

        makeNoise(buffer, .1f, .1f, .25f, .25f);
        makeNoise(buffer, .1f, .25f, .5f, .9f);

        g2.setColor(new Color(23,163,230));
        g2.drawString(text, 5, (int) -(bounds.getY()-10));        
        return buffer;
    }

    public void makeNoise(BufferedImage image, float factorOne, float factorTwo, float factorThree, float factorFour) {
        // image size
        int width = image.getWidth();
        int height = image.getHeight();

        // the points where the line changes the stroke and direction
        Point2D[] pts = null;
        Random rand = new Random();

        // the curve from where the points are taken
        CubicCurve2D cc = new CubicCurve2D.Float(width * factorOne, height * rand.nextFloat(), width * factorTwo, height * rand.nextFloat(), width * factorThree, height * rand.nextFloat(), width * factorFour, height * rand.nextFloat());

        // creates an iterator to define the boundary of the flattened curve
        PathIterator pi = cc.getPathIterator(null, 2);
        Point2D tmp[] = new Point2D[200];
        int i = 0;

        // while pi is iterating the curve, adds points to tmp array
        while (!pi.isDone()) {
            float[] coords = new float[6];
            switch (pi.currentSegment(coords)) {
            case PathIterator.SEG_MOVETO:
            case PathIterator.SEG_LINETO:
                tmp[i] = new Point2D.Float(coords[0], coords[1]);
            }
            i++;
            pi.next();
        }

        pts = new Point2D[i];
        // copies points from tmp to pts
        System.arraycopy(tmp, 0, pts, 0, i);

        Graphics2D graph = (Graphics2D) image.getGraphics();
        graph.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

        Color mycol = Color.black;

        graph.setColor(mycol);

        // for the maximum 3 point change the stroke and direction
        for (i = 0; i < pts.length - 1; i++) {
            if (i < 3)
                graph.setStroke(new BasicStroke(0.9f * (4 - i)));
            graph.drawLine((int) pts[i].getX(), (int) pts[i].getY(), (int) pts[i + 1].getX(), (int) pts[i + 1].getY());
        }

        graph.dispose();

    }

    private String generateRandomText() {
        int len = 6;
        byte[] buf = new byte[len * 2];
        char[] text = new char[len];
        int c = 0;
        while (c < text.length) {
            random.nextBytes(buf);
            for (byte b : buf) {
                if ((b >= 'a' && b <= 'z' || (b >= 'A' && b <= 'Z')) && b != 'O' && b != 'o' && b != 'l' && b != 'L' && b != 'i' && b != 'I') {
                    text[c++] = (char) b;
                    if (c >= text.length)
                        break;
                }
            }
        }
        return new String(text).toLowerCase();
    }

}
