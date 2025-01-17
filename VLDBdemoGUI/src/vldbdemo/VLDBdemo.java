/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vldbdemo;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import vldbdemo.SplashScreen.MySplashScreen;

//The main class
public class VLDBdemo extends JApplet {

    public Background bg;
    public JMenuBar myBar;
    public double cpu_burden[] = new double[1000000];
    public double gpu_burden[] = new double[1000000];
    public double cpu_burden_max;
    public double gpu_burden_max;
    public double spendTime;
    public int totalIndex_Burden;
    public int totalIndex_Schedule;
    public boolean loaded = false;
    public Style style;
    public JFrame frame;

    @Override
    public void init() {
        //setLookAndFeel();
        bg = new Background(this);
        // Create GUI
        Container container = getContentPane();
        //TODO: read data produced by experiment.
        //now use dummy data to test
        //simulate();
        bg.addComponent();
        container.add(bg);
        StyleConstants.setForeground(style, Color.red);
        StyleConstants.setFontSize(style, 20);
        this.update("Welcome!", style);
        StyleConstants.setForeground(style, Color.green);
        StyleConstants.setFontSize(style, 20);
        this.update("Please load experiment data first!", style);
    }
    // Main method

    private void simulate() {
        for (int i = 0; i < 100000; i++) {
            cpu_burden[i] = i;
            gpu_burden[i] = i + 1;
//            schedule[i] = i % 2;
        }
    }

    private void setLookAndFeel() {
        try {
          //  UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            
           // UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
           // UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
           
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VLDBdemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(VLDBdemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(VLDBdemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(VLDBdemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static void renderSplashFrame(Graphics2D g, int frame) {
        final String[] comps = {"foo", "bar", "baz"};
        g.setComposite(AlphaComposite.Clear);
        g.fillRect(120, 140, 200, 40);
        g.setPaintMode();
        g.setColor(Color.BLACK);
        g.drawString("Loading " + comps[(frame / 5) % 3] + "...", 120, 150);
    }

    public void LoadProgram(VLDBdemo applet) {
        frame = new JFrame();
        myBar = new Menu(applet);

        //JMenuBar mMenuBar = new JMenuBar();
        //JMenu mMenu = new JMenu("FatCat");
        //mMenu.add(new JMenuItem("Dingo"));
        //mMenuBar.add(mMenu);
        //JMenuBar myBar = new JMenuBar();
        //myBar.add(new PopupMenu("ah"));
        frame.setJMenuBar(myBar);
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        frame.getContentPane().add(applet);
        applet.init(); //	applet.start();
        frame.setTitle("OmniDB");
        frame.setSize(800, 800);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension windowDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (windowDimension.width - frame.getWidth()) / 2;
        int y = (windowDimension.height - frame.getHeight()) / 3;
        frame.setLocation(x, y);
        frame.setVisible(true);
    }

    public void update(String msg, Style style) {
        this.bg.mp.lg.update(msg, style);
        this.bg.mp.lg.setVisible(true);
    }

    public static void main(String[] args) {
        VLDBdemo applet = new VLDBdemo();
        MySplashScreen s = new MySplashScreen(applet);
        s.setVisible(true);

    }
}
