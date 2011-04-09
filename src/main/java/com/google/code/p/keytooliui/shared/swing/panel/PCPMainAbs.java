/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
 * LGPL License.
 * http://code.google.com/p/keytool-iui/
 *
 *
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of keyTool IUI Project's license agreement.
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
 
 
package com.google.code.p.keytooliui.shared.swing.panel;

/**

    known subclasses:
    . PCPMainGenAbs
    . PCPMainUIAbs
    . PCPMainReader
    . PCPMainDin

    contains:
    . 1 toolbar on top
    . 1 ?? on center
    
    may contain:
    . 1 statusBar on bottom
**/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.toolbar.*;
import com.google.code.p.keytooliui.shared.util.changer.*;


import javax.swing.*;

abstract public class PCPMainAbs extends JPanel
{
    // ------------------------
    // PRIVATE STATIC FINAL INT
    
    private static final int _f_s_intWidth = 1000; // 800; // 680; // 600;
    private static final int _f_s_intHeight = 800; //600; // 480; //480;
    
    
    // ---------------
    // ABSTRACT PUBLIC
    abstract public void setContextualHelpID();
    
    // ------
    // PUBLIC
    
     
    
    // toolbar's iconButton
    public void setEnabledHelpSourceAndTrack(javax.help.HelpBroker hbrHelpStandard)  
    {
       if (this._tbrToolBar_ != null)
           this._tbrToolBar_.setEnabledHelpSourceAndTrack(hbrHelpStandard);
    }
    
    public void setLocationToolbarMain(int intLocationNew)
    {
        String strMethod = "setLocationToolbarMain(intLocationNew)";
        
        MySystem.s_printOutFlagDev(this, strMethod, "TODO: if same location, just returning");
        
        int intOrientationCur = this._tbrToolBar_.getOrientation();
        
        remove(this._tbrToolBar_);
        // tempo, march 11, 2002
        this._tbrToolBar_.removeNotify();
        invalidate();
        
        
        // TEMPO COMMENTS, BUG LINUX, january 21, 2002, unexpected signal: 11
        //this._tbrToolBar_.removeNotify();
        
        if (intLocationNew==ChgLocAbstract.F_S_INT_TOP || intLocationNew==ChgLocAbstract.F_S_INT_BOTTOM)
        {
            // CHANGED may 28, 2001 
            //   in order to fix up bug docked toolbar not being hidden
      
            //if (intOrientationCur != SwingConstants.HORIZONTAL)
                
                
                this._tbrToolBar_.setOrientation(SwingConstants.HORIZONTAL);
        }
        
        else if (intLocationNew==ChgLocAbstract.F_S_INT_LEFT ||intLocationNew==ChgLocAbstract.F_S_INT_RIGHT)
        {
             //CHANGED may 28, 2001 
             //  in order to fix up bug docked toolbar not being hidden
               
           // if (intOrientationCur != SwingConstants.VERTICAL)
                
                this._tbrToolBar_.setOrientation(SwingConstants.VERTICAL);
        }
        
        else
            MySystem.s_printOutExit(this, strMethod, "wrong value, intLocationNew=, " + intLocationNew);
        
        // ----
        
        if (intLocationNew==ChgLocAbstract.F_S_INT_TOP)
            this.add(this._tbrToolBar_, java.awt.BorderLayout.NORTH); 
        else if (intLocationNew==ChgLocAbstract.F_S_INT_LEFT)
            this.add(this._tbrToolBar_, java.awt.BorderLayout.WEST);
        else if (intLocationNew==ChgLocAbstract.F_S_INT_BOTTOM)
            this.add(this._tbrToolBar_, java.awt.BorderLayout.SOUTH);
        else
            this.add(this._tbrToolBar_, java.awt.BorderLayout.EAST);
        
        // TEMPO COMMENTS, BUG LINUX, january 21, 2002, unexpected signal: 11
        //this._tbrToolBar_.addNotify();
        
        MySystem.s_printOutFlagDev(this, strMethod, "about linux os");
        
        //this.paintAll(getGraphics());
        //tempo march 11, 2002
        this._tbrToolBar_.addNotify();
        validate();
    }
    
    public void updateTreeUI()
    {
        if (this._tbrToolBar_ != null)
            this._tbrToolBar_.updateTreeUI();
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (this._tbrToolBar_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._tbrToolBar_");
            return false;
        }
        
        if (! this._tbrToolBar_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ----------
        // set layout
        
        setLayout(new java.awt.BorderLayout());
        int intWidth = _f_s_intWidth;
        int intHeight = _f_s_intHeight;
        
        if (com.google.code.p.keytooliui.shared.io.S_OperatingSystem.s_isULinux())
        {
            intWidth += 20;
            intHeight += 10;
        }
        
        setPreferredSize(new java.awt.Dimension(intWidth, intHeight));
        
        // ---------------
        // adding children
        //add(this._tbrToolBar_, java.awt.BorderLayout.NORTH);
        
        // ending
        return true;
    }
    
    public void destroy()
    {
        if (this._tbrToolBar_ != null)
        {
            this._tbrToolBar_.destroy();
            this._tbrToolBar_ = null;
        }
    }
    
    
    // ---------
    // PROTECTED
    
    protected TBMainAbstract _tbrToolBar_ = null;
    
    protected PCPMainAbs()
    {
        super(true);
    }
     
}