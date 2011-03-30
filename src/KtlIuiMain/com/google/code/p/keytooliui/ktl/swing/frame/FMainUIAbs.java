/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
 * LGPL License.
 * http://code.google.com/p/keytool-iui/
 *
 * This software is the confidential and proprietary information of RagingCat Project.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of RagingCat Project's license agreement.
 *
 * THE SOFTWARE IS PROVIDED AND LICENSED "AS IS" WITHOUT WARRANTY OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.
 *
 * LICENSE FOR THE SOFTWARE DOES NOT INCLUDE ANY CONSIDERATION FOR ASSUMPTION OF RISK
 * BY KEYTOOL IUI PROJECT, AND KEYTOOL IUI PROJECT DISCLAIMS ANY AND ALL LIABILITY FOR INCIDENTAL
 * OR CONSEQUENTIAL DAMAGES ARISING OUT OF THE USE OR OPERATION OF OR INABILITY
 * TO USE THE SOFTWARE, EVEN IF KEYTOOL IUI PROJECT HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 */


 package com.google.code.p.keytooliui.ktl.swing.frame;


 import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import com.google.code.p.keytooliui.ktl.swing.panel.*;
 import com.google.code.p.keytooliui.ktl.swing.menubar.*;

 import com.google.code.p.keytooliui.shared.lang.*;
 import com.google.code.p.keytooliui.shared.swing.frame.*;
 import com.google.code.p.keytooliui.shared.util.eventlistener.*;


// tempo, for new LAF
/*import java.awt.*;

import javax.swing.*;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;

//import com.jgoodies.clearlook.ClearLookManager;
import com.jgoodies.plaf.Options;
//import com.jgoodies.plaf.plastic.PlasticLookAndFeel;
//import com.jgoodies.uif_lite.component.Factory;
//import com.jgoodies.uif_lite.panel.SimpleInternalFrame;
*/


 /**
    contains:
    . on top:   1 menuBar
    . centered: 1 contentPane
 **/

 abstract public class FMainUIAbs extends FMainAbs implements
         Printable
 {
    public boolean doPrint()
    {
        String strMethod = "doPrint()";
        
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
    
    
    // ---------
    // PROTECTED

    protected FMainUIAbs(
        String strTitleAppli,
        java.awt.Image imgIcon,
        java.awt.event.WindowListener winListenerParent,
        //javax.help.HelpBroker hbrHelpStandard,
        String strHelpHomeID
        )
    {
        super(
            strTitleAppli,
            imgIcon,
            winListenerParent,
            //hbrHelpStandard, 
            strHelpHomeID,
            true // blnShowDialogLoadingProgress
            );
                  
        setTitle(strTitleAppli);
    }
    
    
    // -------
    // private
    
    /**
     * Applique les paramètres de l'IHM en fonction des données du membre settings.
     * Ce code est extrait de la demo Looks de jgoodies.com, voir en-tête du fichier.
     * *
     * @author Karsten Lentzsch
     * @author Romain Guy
     */
  /**  private void configureUI() {
        Options.setDefaultIconSize(new Dimension(18, 18));

        UIManager.put(Options.USE_SYSTEM_FONTS_APP_KEY, settings.isUseSystemFonts());
        Options.setGlobalFontSizeHints(settings.getFontSizeHints());
        Options.setUseNarrowButtons(settings.isUseNarrowButtons());

        Options.setTabIconsEnabled(settings.isTabIconsEnabled());
        ClearLookManager.setMode(settings.getClearLookMode());
        ClearLookManager.setPolicy(settings.getClearLookPolicyName());
        UIManager.put(Options.POPUP_DROP_SHADOW_ENABLED_KEY, settings.isPopupDropShadowEnabled());

        LookAndFeel selectedLaf = settings.getSelectedLookAndFeel();
        if (selectedLaf instanceof PlasticLookAndFeel) {
            PlasticLookAndFeel.setMyCurrentTheme(settings.getSelectedTheme());
            PlasticLookAndFeel.setTabStyle(settings.getPlasticTabStyle());
            PlasticLookAndFeel.setHighContrastFocusColorsEnabled(
                settings.isPlasticHighContrastFocusEnabled());
        } else if (selectedLaf.getClass() == MetalLookAndFeel.class) {
            MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
        }

        JRadioButton radio = new JRadioButton();
        radio.getUI().uninstallUI(radio);
        JCheckBox checkBox = new JCheckBox();
        checkBox.getUI().uninstallUI(checkBox);

        try {
            UIManager.setLookAndFeel(selectedLaf);
        } catch (Exception e) {
            System.out.println("Can't change L&F: " + e);
        }
    }**/
 }