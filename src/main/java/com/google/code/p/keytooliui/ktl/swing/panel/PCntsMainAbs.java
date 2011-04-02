package com.google.code.p.keytooliui.ktl.swing.panel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JPanel;
import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.swing.button.BESPrint16;

abstract public class PCntsMainAbs extends JPanel implements
        ActionListener,
        Printable
{
    // ---------------
    // abstract public 
    
    abstract public boolean init();
    abstract public void destroy();
    
    // ------
    // public
    
    public void actionPerformed(ActionEvent e)
    {
        String strMethod = "actionPerformed(e)";
        
        if (e.getSource() instanceof BESPrint16)
        {
        
            _doPrint();
            // ending
            return;
        }
        
        // bug
        MySystem.s_printOutExit(this, strMethod, "uncaught e.getSource()=" + e.getSource());
        
        
    }
    
    // ---------
    // protected
    
    protected PCntsMainAbs()
    {
        super();
    }        
        
    
    // -------
    // private
    
    private boolean _doPrint()
    {
        String strMethod = "_doPrint()";
        
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
        PrinterJob job = PrinterJob.getPrinterJob(); 
        job.setPrintable(this); 
        boolean blnOk = true;
        
        if (job.printDialog())
        { 
            try
            { 
                job.print(); 
            } 
            
            catch (Exception exc)
            { 
                exc.printStackTrace();
                MySystem.s_printOutError(this, strMethod, "exc caught");
                //setCursor(Cursor.getDefaultCursor());
                
                blnOk = false;
            } 
        } 
        
        // else the user has cancelled the printing (AS FOR INFO)
        
        setCursor(Cursor.getDefaultCursor()); 
        return blnOk;
    }
    
    /** 
     * The method @print@ must be implemented for @Printable@ interface. 
     * Parameters are supplied by system. 
     */ 
    public int print(Graphics g, PageFormat pft, int intPageIndex) 
        throws PrinterException
    { 
        String strMethod = "print(g, pft, int intPageIndex)";
        
        Graphics2D g2 = (Graphics2D)g; 
        g2.setColor(Color.black);    //set default foreground color to black 
        //for faster printing, turn off double buffering 
        //RepaintManager.currentManager(this).setDoubleBufferingEnabled(false); 
        Dimension d = this.getSize();    //get size of document 
        
        double dblPanelWidth  = d.width;    //width in pixels 
        double dblPanelHeight = d.height;   //height in pixels 
        double dblPageHeight = pft.getImageableHeight();   //height of printer page 
        double dblPageWidth  = pft.getImageableWidth();    //width of printer page 
        double dblScale = dblPageWidth/dblPanelWidth; 
        
        int intPageNb = (int)Math.ceil(dblScale * dblPanelHeight / dblPageHeight); 
        
        //make sure not print empty pages 
        if(intPageIndex >= intPageNb)
        { 
            return Printable.NO_SUCH_PAGE; 
        } 
        
        //shift Graphic to line up with beginning of print-imageable region 
        g2.translate(pft.getImageableX(), pft.getImageableY()); 
        //shift Graphic to line up with beginning of next page to print 
        g2.translate(0f, -intPageIndex*dblPageHeight); 
        //dblScale the page so the width fits... 
        g2.scale(dblScale, dblScale); 
        this.paint(g2);   //repaint the page for printing 
 
        return Printable.PAGE_EXISTS; 
    }
}


