package lv.id.bure.www;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.time.LocalDateTime;
import javax.swing.JComponent;

public class DrawArea extends JComponent{
	
	private static final long serialVersionUID = 1L;
    private int hours, minutes, seconds;
    private Graphics2D graph2;

	private record Data(int x1, int y1, Color cl, int deltaHours, String cityName) {
	}
	
	@Override
	protected void paintComponent(Graphics graph) {
        super.paintComponent(graph);

        // Current time
        hours = LocalDateTime.now().getHour();
        minutes = LocalDateTime.now().getMinute();
        seconds = LocalDateTime.now().getSecond();
        // Debug thread:
        //System.out.println(hour + ":" + minute + ":" + second);
        
        graph2 = (Graphics2D)graph;
        // This voodoo makes the output prettier
        graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graph2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        // Clock face hardcoded values
        Data[] mainData = new Data[6];
        mainData[0] = new Data(20,   10, Color.BLACK,   Main.timeZones[0], Main.cities[0]);
        mainData[1] = new Data(190,  10, Color.RED,     Main.timeZones[1], Main.cities[1]);
        mainData[2] = new Data(360,  10, Color.BLUE,    Main.timeZones[2], Main.cities[2]);
        mainData[3] = new Data(20,  205, Color.MAGENTA, Main.timeZones[3], Main.cities[3]);
        mainData[4] = new Data(190, 205, Color.GRAY,    Main.timeZones[4], Main.cities[4]);
        mainData[5] = new Data(360, 205, Color.PINK,    Main.timeZones[5], Main.cities[5]);
        
        // Draw each face
        for (byte clockFace = 0; clockFace < 6; clockFace++) {
        	drawOneClockFace(mainData[clockFace]);
        }
        
        graph2.dispose();
	}
	
	private void drawOneClockFace(Data data) {
        int tx, ty;
        final double MIN_SEC_DEG = (double) Math.PI / 30;
        final double HOURS_DEG = (double) Math.PI / 6;
        final double ADD_MINUTES_DEG = (double) Math.PI / 360;
        final double ADD_SECONDS_DEG = (double) Math.PI / 1800;
        
        graph2.setColor(data.cl);
        Font currentFont = new Font("Serif", Font.BOLD, 14);
        
        // Big circle
        graph2.setStroke(new BasicStroke(1));
        graph2.draw(new Ellipse2D.Double(data.x1, data.y1, 160, 160));
        
        // Numbers
        for (int i = 1; i <= 12; i++) {
        	graph2.setFont(currentFont);
            tx = 78 + data.x1 + (int)(60 * Math.sin(i * HOURS_DEG)) - (i == 12 ? 4 : 0);
            ty = 86 + data.y1 - (int)(60 * Math.cos(i * HOURS_DEG));
            graph2.drawString(Integer.toString(i), tx, ty);
        }
        
        // Pointers
        for (int i = 0; i <= 59; i++) {
            int deltaR = (i % 5 == 0 ? 77 : 78);
            tx = 80 + data.x1 + (int) (deltaR * Math.sin(i * MIN_SEC_DEG));
            ty = 80 + data.y1 - (int) (deltaR * Math.cos(i * MIN_SEC_DEG));
            deltaR = (i % 5 == 0 ? 70 : 75);
            int tx2 = 80 + data.x1 + (int) (deltaR * Math.sin(i * MIN_SEC_DEG));
            int ty2 = 80 + data.y1 - (int) (deltaR * Math.cos(i * MIN_SEC_DEG));
            graph2.setStroke(new BasicStroke(i % 5 == 0 ? 3 : 1));
            graph2.draw(new Line2D.Double(tx, ty, tx2, ty2));
        }
        
        // Second hand
        tx = 80 + data.x1 + (int) (77 * Math.sin(seconds * MIN_SEC_DEG));
        ty = 80 + data.y1 - (int) (77 * Math.cos(seconds * MIN_SEC_DEG));
        graph2.setStroke(new BasicStroke(1));
        graph2.draw(new Line2D.Double(data.x1 + 80, data.y1 + 80, tx, ty));

        // Minute hand
        tx = 80 + data.x1 + (int) (69 * Math.sin(minutes * MIN_SEC_DEG + seconds * ADD_SECONDS_DEG));
        ty = 80 + data.y1 - (int) (69 * Math.cos(minutes * MIN_SEC_DEG + seconds * ADD_SECONDS_DEG));
        graph2.setStroke(new BasicStroke(2));
        graph2.draw(new Line2D.Double(data.x1 + 80, data.y1 + 80, tx, ty));

        // Hour hand
        int currentHours = hours + data.deltaHours;
        tx = 80 + data.x1 + (int) (39 * Math.sin(currentHours * HOURS_DEG + minutes * ADD_MINUTES_DEG));
        ty = 80 + data.y1 - (int) (39 * Math.cos(currentHours * HOURS_DEG + minutes * ADD_MINUTES_DEG));
        graph2.setStroke(new BasicStroke(4));
        graph2.draw(new Line2D.Double(data.x1 + 80, data.y1 + 80, tx, ty));
        
        // Point at the center
        graph2.setStroke(new BasicStroke(3));
        graph2.draw(new Ellipse2D.Double(77 + data.x1, 77 + data.y1, 6, 6));
        
        // City names
        FontMetrics metrics = graph2.getFontMetrics(currentFont);
        graph2.drawString(data.cityName, data.x1 + (160 - metrics.stringWidth(data.cityName)) / 2, data.y1 + 180);
	}
	
}
