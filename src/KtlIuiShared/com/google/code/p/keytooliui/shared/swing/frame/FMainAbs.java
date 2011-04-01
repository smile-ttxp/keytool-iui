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



package com.google.code.p.keytooliui.shared.swing.frame;

/**
    known subclasses:
    . FMainReader
    . FMainGenAbs
    . FMainUIKtl
    . FMainDin

    contains:
    . 1 menuBar
    . 1 contentPane

    also contains a "loadingAppli" dialog window
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.menubar.*;
import com.google.code.p.keytooliui.shared.swing.dialog.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;

import javax.swing.*;

abstract public class FMainAbs extends FAbs
{
    // ------
    // PUBLIC

    public void setLocationToolbarMain(int intOrientation)
    {
        String strMethod = "setLocationToolbarMain(intOrientation)";

        if (this._pcpContentPane_ == null)
        {
            this._destroyDialogLoading();
            MySystem.s_printOutExit(this, strMethod, "nil super._pcpContentPane_");
        }

        this._pcpContentPane_.setLocationToolbarMain(intOrientation);
    }
    
    
    public void setVisible(boolean bln)
    {
        if (bln == true)
        {
            this._destroyDialogLoading();
            this.pack(); // needed for windows, let display windows'menu bar 
        }
        
        super.setVisible(bln);
    }

    public boolean init()
    {
        String strMethod = "init()";

        if (! super.init())
        {
            this._destroyDialogLoading();
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        if (this._mbmMenuBar_ == null)
        {
            this._destroyDialogLoading();
            MySystem.s_printOutError(this, strMethod, "nil this._mbmMenuBar_");
            return false;
        }

        setJMenuBar(this._mbmMenuBar_);

        if (! this._mbmMenuBar_.init())
        {
            this._destroyDialogLoading();
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        if (this._pcpContentPane_ == null)
        {
            this._destroyDialogLoading();
            MySystem.s_printOutError(this, strMethod, "nil this._pcpContentPane_");
            return false;
        }


        if (! this._pcpContentPane_.init())
        {
            this._destroyDialogLoading();
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }


        setContentPane(this._pcpContentPane_);

        this._destroyDialogLoading();

        // added, jan 3, 2002
        
        // _TEMPO_

       /*if (! com.google.code.p.keytooliui.shared.help.MyCSH.s_checkAndDumpCmp2ID())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }*/

        // ending
        return true;
    }

    public void destroy()
    {
        super.destroy();

        this._destroyDialogLoading(); // ??????????

        if (this._mbmMenuBar_ != null)
        {
            this._mbmMenuBar_.destroy();
            this._mbmMenuBar_ = null;
        }

        if (this._pcpContentPane_ != null)
        {
            this._pcpContentPane_.destroy();
            this._pcpContentPane_ = null;
        }


    }

    // called by ChangerLookAndFeel memo: ChangerLookAndFeel has been removed on nov 16, 2001
    // 1) do it for all components children
    // 2) do the same for components that are not children (not added)
    public void updateTreeUI()
    {
        javax.swing.SwingUtilities.updateComponentTreeUI(this);

        if (this._mbmMenuBar_ != null)
            this._mbmMenuBar_.updateTreeUI();

        if (this._pcpContentPane_ != null)
            this._pcpContentPane_.updateTreeUI();

        // ?? what about loading dialog?
    }
    
    
            
    public void setContextualHelpID() 
    {
      if (this._pcpContentPane_ != null)
        this._pcpContentPane_.setContextualHelpID();
    }
    
    // F1 key
    public void setEnabledHelpKey(javax.help.HelpBroker hbrHelpStandard) 
    {
      if (hbrHelpStandard == null)
        return;
      
      hbrHelpStandard.enableHelpKey(this.getRootPane(), this._strHelpHomeID, null);
    }
    
    // toolbar's iconButton & menubar's menuItem'
    public void setEnabledHelpSourceAndTrack(javax.help.HelpBroker hbrHelpStandard)  
    {
       if (this._pcpContentPane_ != null)
           this._pcpContentPane_.setEnabledHelpSourceAndTrack(hbrHelpStandard);
       
       if (this._mbmMenuBar_ != null)
           this._mbmMenuBar_.setEnabledHelpSourceAndTrack(hbrHelpStandard);
    }

    // ---------
    // PROTECTED

    protected MBMainAbstract _mbmMenuBar_ = null;
    protected PCPMainAbs _pcpContentPane_ = null;


    private String _strHelpHomeID;    


    protected FMainAbs(
        String strTitleAppli,
        java.awt.Image imgIcon,
        java.awt.event.WindowListener winListenerParent,
        //javax.help.HelpBroker hbrHelpStandard,  
        String strHelpHomeID, 
        boolean blnShowDialogLoadingProgress)
    {
        super(strTitleAppli, imgIcon);

        String strMethod = "FMainAbs(...)";
        
        this._strHelpHomeID = strHelpHomeID;

        // then create-init-show loading dialog (this way the dialog takes the icon assigned previously in the frame)

        if (blnShowDialogLoadingProgress) // not if for example, main appli is RCReader, and used as an help in another appli
        {
            this._dlgLoading = new DLoadingAppli(this, super._strTitleApplication_);

            if (! this._dlgLoading.init())
                MySystem.s_printOutExit(this, strMethod, "failed");

            this._dlgLoading.setVisible(true);
            
        }


        // --
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        if (winListenerParent == null)
        {
            this._destroyDialogLoading();
            MySystem.s_printOutExit(this, strMethod, "nil winListenerParent");
        }

        this.addWindowListener(winListenerParent);
        
        

        // assigning help

            /* _TEMPO_
            if (hbrHelpStandard != null)
              hbrHelpStandard.enableHelpKey(this.getRootPane(), strHelpHomeID, null);
            */
        
        
        
        /**
            AS A REMINDER: FOCUS!
            Enables the Help key on a Component.

            This method works best when the component is the rootPane of a JFrame in Swing implementations,
            or a java.awt.Window (or subclass thereof) in AWT implementations.

            This method sets the default helpID and MyHelpSet for the Component and registers keyboard actions
            to trap the "Help" keypress.

            When the "Help" key is pressed, if the object with the current focus has a helpID,
            the helpID is displayed. otherwise the default helpID is displayed.

        **/
    }

    // -------
    // PRIVATE

    private DLoadingAppli _dlgLoading = null;

    
    private void _destroyDialogLoading()
    {
        if (this._dlgLoading != null)
        {
            this._dlgLoading.destroy();
            this._dlgLoading = null;
        }
        
    }
}