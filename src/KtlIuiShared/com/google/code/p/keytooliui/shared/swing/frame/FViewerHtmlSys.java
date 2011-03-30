/*
 *
 * Copyright (c) 2001-2002 RagingCat Project.
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
 
 
package com.google.code.p.keytooliui.shared.swing.frame;

/**
    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    ?TO BE REPLACED by FSwUrlTextHTML?
    only called once a time in com.google.code.p.keytooliui.rcr/RCReader.java
    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!





    a frame that displays HTML pages from local files
    ==> used as a secondary window by RCReader
    
**/



import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;

import javax.swing.*;

import java.net.*;
import java.awt.*;
import java.awt.event.*;

final public class FViewerHtmlSys extends FAbs implements
    ActionListener
{
    // ------
    // PUBLIC
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";
        
        if (evtAction.getSource() instanceof com.google.code.p.keytooliui.shared.swing.menuitem.MIFileExit ||
            evtAction.getSource() instanceof com.google.code.p.keytooliui.shared.swing.button.BESExit24)
	    {
	        setVisible(false);
            dispose();
	                  
	        return;
	    }
	    
        MySystem.s_printOutExit(this, strMethod, "unknown source");
    }
    
    /**
        MEMO: sst may be nil
    **/
    public boolean doFileOpen(URL urlFileLocal, javax.swing.text.html.StyleSheet sst)
        throws Exception
    {
        String strMethod = "doFileOpen(urlFileLocal, sst)";
        
        if (urlFileLocal == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil urlFileLocal");
            return false;
        }
        
        
        if (! ((PViewerFileTextHtmlViewSys)this._pnd).doFileOpen(urlFileLocal, sst))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (super._strTitleApplication_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._strTitleApplication_");
            return false;
        }
        
        setTitle(super._strTitleApplication_ + " - " + urlFileLocal.toString());
        
        // ending
        return true;
    }
    
    /**
        overwriting superclass's method
    **/
    public void setVisible(boolean bln)
    {
        String strMethod = "setVisible(bln)";
        
        if (bln)
        {
            this.pack(); // needed for windows, let display windows'menu bar 
            Dimension dimScreen = null;
        
            try
            {
                Toolkit tkt = Toolkit.getDefaultToolkit();
                dimScreen = tkt.getScreenSize();
            }
        
            catch(AWTError errAWT)
            {
                errAWT.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, "errAWT caught");
            }
        
            Insets ins = getInsets();
        
            int intW = dimScreen.width - ins.left - ins.right;
            int intH = dimScreen.height - ins.top - ins.bottom;
            Dimension dim = new Dimension(intW, intH);
            setSize(dim);
        }
        
        super.setVisible(bln);
        
        if (! bln)
            destroy();
    }
    
	
	
	// ----
    
    

    
   
    
    /**public void exit(PViewerHtmlAbstractEvent evtPViewerHtmlAbstract)
    {
        setVisible(false);
        dispose();
    }**/
    
    
    // ----
    
    public boolean init()
    {
        String strMethod = "init()";        
               
        if (! super.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        if (this._pnd == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnd");
            return false;
        }
        
        if (! this._pnd.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        _addChildren();
        
    
        // ending
        return true;
    }
    
    public void destroy()
    {    
        super.destroy();
        
        if (this._pnd != null)
        {
            this._pnd.destroy();
            this._pnd = null;
        }
        
        //System.gc();
    }
    
    
    // --------
    // children
    public PViewerFileTextHtmlAbs _pnd = null;

    
    
    public FViewerHtmlSys(
        String strTitleApplication,
        Image imgIcon)
    {
        super(strTitleApplication, imgIcon);
                
        String strMethod = "FViewerHtmlSys(...)";
                
        
        try
	    {
	        this._pnd = new PViewerFileTextHtmlViewSys((ActionListener) this, (Component) this, strTitleApplication);
	    }
	    
	    catch(Exception exc)
	    {
	        exc.printStackTrace();
	        MySystem.s_printOutExit(this, strMethod, "exc caught");
	    }
    }

    // -------
    // PRIVATE    
        
    private boolean _addChildren()
    {
        String strMethod = "_addChildren()";
          // ----
        Container cntContentPane = getContentPane();
        cntContentPane.setLayout(new BorderLayout());
        
        if (this._pnd == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnd");
            return false;
        }
        
        cntContentPane.add(this._pnd, BorderLayout.CENTER);
    
        // ending
        return true;
    }
    
    
}