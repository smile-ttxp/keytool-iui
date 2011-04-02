/*
 *
 * Copyright (c) 2001-2008 keyTool IUI Project.
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
 
 
package com.google.code.p.keytooliui.shared.swing.toolbar;

/**
    known subclasses:
    . TBMainReader,
    . TBMainGenAbstract
    . TBMainUIKtl
    

    contains a series of ImageButtons, images of size 24x24
    . help
    . helpOnItem
    . exit
    
    Important:
    buttons are created, inited, destroyed, BUT added in subclasses
**/

import com.google.code.p.keytooliui.shared.swing.button.*;
import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.awt.event.*;


//import java.beans.*; // used for PropertChangeListener

abstract public class TBMainAbstract extends TBMSAbstract
{
    // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    //final static private Dimension _f_s_dimSeparator2 = new Dimension(3, 3);
    
    // ------
    // PUBLIC
    
    
    /**
        MEMO: bln alaways true,
        method called once the help frame has been successfully loaded
    
    **/
    
    public boolean setEnabledHelpOffline(boolean bln)
    {
        String strMethod = "setEnabledHelpOffline(bln)";
        
        
        
        if (this._btnHelpSource_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._btnHelpSource_");
            return false;
        }
        
        this._btnHelpSource_.setEnabled(bln);
        
        
      
        if (this._btnHelpTrack_ != null)
        {
            this._btnHelpTrack_.setEnabled(bln);
        }
        
        return true;
    }
    
    public void setEnabledHelpSourceAndTrack(javax.help.HelpBroker hbrHelpStandard)
    {
        if (hbrHelpStandard == null)
            return;
        
        if (this._btnHelpSource_ != null) 
            ((BESHelpJHAbstract) this._btnHelpSource_).setEnabledHelp(hbrHelpStandard);
        
        if (this._btnHelpTrack_ != null) 
            ((BESHelpJHAbstract) this._btnHelpTrack_).setEnabledHelp(hbrHelpStandard);
    }
    
    public void updateTreeUI()
    {
        super.updateTreeUI();
        
        if (this._btnHelpSource_ != null) 
        {
            if (! isAncestorOf(this._btnHelpSource_))
                javax.swing.SwingUtilities.updateComponentTreeUI(this._btnHelpSource_);
        }
        
        if (this._btnHelpTrack_ != null) 
        {
            if (! isAncestorOf(this._btnHelpTrack_)) 
                javax.swing.SwingUtilities.updateComponentTreeUI(this._btnHelpTrack_);
        }

        if (this._btnHelpOnlineHome_ != null)
        {
            if (! isAncestorOf(this._btnHelpOnlineHome_))
                javax.swing.SwingUtilities.updateComponentTreeUI(this._btnHelpOnlineHome_);
        }
        
        if (this._btnAboutAppli_ != null) 
        {
            if (! isAncestorOf(this._btnAboutAppli_)) 
                javax.swing.SwingUtilities.updateComponentTreeUI(this._btnAboutAppli_);
        }
        
        if (this._btnExit_ != null) 
        {
            if (! isAncestorOf(this._btnExit_)) // NOTE: should always be the case
                javax.swing.SwingUtilities.updateComponentTreeUI(this._btnExit_);
        }
    }
    
    @Override
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
       
        
        if (this._btnHelpSource_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._btnHelpSource_");
            return false;
        }
        
        if (! ((BESHelpJHAbstract)this._btnHelpSource_).init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        } 
        
         
        
        if (this._btnHelpTrack_ != null)
        {
            if (! ((BESHelpJHAbstract)this._btnHelpTrack_).init())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
        }
        


        
        // ending
        return true;
    }
    
    @Override
    public void destroy()
    {
        super.destroy();
        
        
        
        if (this._btnHelpSource_ != null)
        {
            this._btnHelpSource_.destroy();
            this._btnHelpSource_ = null;
        }
        
        if (this._btnHelpTrack_ != null)
        {
            this._btnHelpTrack_.destroy();
            this._btnHelpTrack_ = null;
        }

        if (this._btnHelpOnlineHome_ != null)
        {
            this._btnHelpOnlineHome_.destroy();
            this._btnHelpOnlineHome_ = null;
        }
        
        if (this._btnAboutAppli_ != null)
        {
            this._btnAboutAppli_.destroy();
            this._btnAboutAppli_ = null;
        }
        
        if (this._btnExit_ != null)
        {
            this._btnExit_.destroy();
            this._btnExit_ = null;
        }
    }
    
    // ---------
    // PROTECTED
    
    
    protected BEnabledState _btnHelpSource_ = null;
    protected BEnabledState _btnHelpTrack_ = null;
    protected BEnabledState _btnHelpOnlineHome_ = null;

    protected BEnabledState _btnAboutAppli_ = null;
    protected BEnabledState _btnExit_ = null;
    
    /**
        the code below does not work in JDK 1.3.1/WinNT,
        ==> when the toolbar is vertical, lines are not correct (smaller size, on the left)
        replacing by a simple hidden separator!
    **/
    protected void _addSeparatorLine_()
    {
        _addSeparator_();
        
        /**
        final Dimension dimVertical = new Dimension(2, 24);
        final Dimension dimHorizontal = new Dimension(24, 2);
        
        
        
        final JSeparator sep = new JSeparator();
        
        if (this.getOrientation() == JToolBar.HORIZONTAL)
        {
            sep.setOrientation(JSeparator.VERTICAL);
            sep.setMinimumSize(dimVertical);
            sep.setPreferredSize(dimVertical);
            sep.setMaximumSize(dimVertical);
        }
        
        else
        {
            sep.setOrientation(JSeparator.HORIZONTAL);
            sep.setMinimumSize(dimHorizontal);
            sep.setPreferredSize(dimHorizontal);
            sep.setMaximumSize(dimHorizontal);
        }
        
        addPropertyChangeListener(new PropertyChangeListener()
        {
            public void propertyChange(PropertyChangeEvent evtPropertyChange)
            {
                String strMethod = "propertyChange(evtPropertyChange)";

                if (evtPropertyChange.getPropertyName().compareTo("orientation") != 0)
                    return;
            
                if (getOrientation() == JToolBar.HORIZONTAL)
                {
                    sep.setOrientation(JSeparator.VERTICAL);
                    sep.setMinimumSize(dimVertical);
                    sep.setPreferredSize(dimVertical);
                    sep.setMaximumSize(dimVertical);
                }
                
                else
                {
                    sep.setOrientation(JSeparator.HORIZONTAL);
                    sep.setMinimumSize(dimHorizontal);
                    sep.setPreferredSize(dimHorizontal);
                    sep.setMaximumSize(dimHorizontal);
                }
            }
            
        });
        
        addSeparator(_f_s_dimSeparator2);
        this.add(sep);
        addSeparator(_f_s_dimSeparator2);
        **/
    }
    
    protected void _addSeparator_()
    {        
        addSeparator(TBAbs._f_s_dimSeparator10_);
    }
    
    /*
        memo: two buttons!
    */
    protected boolean _addButtonHelp_()
    {
        String strMethod = "_addButtonHelp_()";
        
        if (this._btnHelpSource_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._btnHelpSource_");
            return false;
        }
        
        add(this._btnHelpSource_);
       
        
        if (this._btnHelpTrack_ != null)
        {
            add(this._btnHelpTrack_);
        }

        if (this._btnHelpOnlineHome_ != null)
        {
            add(this._btnHelpOnlineHome_);
        }
        
        // ending
        return true;
    }
    
    protected boolean _addButtonAboutAppli_()
    {
        String strMethod = "_addButtonAboutAppli_()";
        
        if (this._btnAboutAppli_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._btnAboutAppli_");
            return false;
        }
        
        add(this._btnAboutAppli_);
        
        
        // ending
        return true;
    }
    
    protected boolean _addButtonExit_()
    {
        String strMethod = "_addButtonExit_()";
        
        add(Box.createHorizontalGlue());
        add(Box.createVerticalGlue());
        
        if (this._btnExit_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._btnExit");
            return false;
        }
        
        add(this._btnExit_);
        //this._addSeparator_();
        return true;
    }
    
    protected TBMainAbstract(
        String strHelpID,
        ActionListener actListenerParent,
        //javax.help.HelpBroker hbrHelpStandard,
        int intOrientation,
        javax.swing.ImageIcon iinFrameFloatable, // should be used once bug fixed "setFloatable(true), see below
        boolean blnDoHelpOnItem,
        boolean blnFloatable
        )
    {
        super(
            intOrientation, 
            iinFrameFloatable, 
            strHelpID,
            blnFloatable
            );
       
        String strMethod = "TBMainAbstract(...)";
        
        
               
        try
        {
            this._btnHelpSource_ = new BESHelpJHSource();
        
            if (blnDoHelpOnItem)
            {
                this._btnHelpTrack_ = new BESHelpJHTrack();
            }
        }
        
        catch(NullPointerException excNullPointer)
        {
            excNullPointer.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "excNullPointerCaught");
        }
         
         
        
        
        this._btnExit_ = new BESExit24(actListenerParent);
        
        if (actListenerParent != null)
            super._btnPrint_ = new BESPrint24(actListenerParent);
    }
    
    // -------
    // PRIVATE
    
    
}