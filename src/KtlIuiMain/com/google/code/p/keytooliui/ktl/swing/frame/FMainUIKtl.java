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


 package com.google.code.p.keytooliui.ktl.swing.frame;


 import com.google.code.p.keytooliui.ktl.swing.panel.*;
 import com.google.code.p.keytooliui.ktl.swing.menubar.*;

 import com.google.code.p.keytooliui.shared.lang.*;
 import com.google.code.p.keytooliui.shared.swing.frame.*;
 import com.google.code.p.keytooliui.shared.util.eventlistener.*;



 /**
    contains:
    . on top:   1 menuBar
    . centered: 1 contentPane
 **/

 final public class FMainUIKtl extends FMainUIAbs
 {
    // --------------------
    // STATIC PUBLIC STRING

    static public String s_strHelpHomeID = null;

    // ------------------
    // STATIC INITIALIZER

    static
    {
        String strWhere = "com.google.code.p.keytooliui.ktl.swing.frame.FMainUIKtl";

        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.F_STR_BUNDLE_DIR +
            ".FMainUIKtl" // class name
            ;

        String strBundleFileLong = strBundleFileShort + ".properties";

        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort,
                java.util.Locale.getDefault());

            s_strHelpHomeID = rbeResources.getString("helpHomeID");
        }

        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught, " + strBundleFileLong + " not found");
        }
        
        strWhere = null;
        strBundleFileShort = null;
        strBundleFileLong = null;
    }

    // ------
    // PUBLIC

    // --

    public void setSelectedTaskCreateKst()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskCreateKst();
    }
    
    public void setSelectedTaskCreateKprV3CDsa()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskCreateKprV3CDsa();
    }

    public void setSelectedTaskCreateKprV1CDsa()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskCreateKprV1CDsa();
    }
    
    public void setSelectedTaskCreateKprV3CEc()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskCreateKprV3CEc();
    }
    
    public void setSelectedTaskCreateKprV1CEc()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskCreateKprV1CEc();
    }
    
    public void setSelectedTaskCreateKprV3CRsa()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskCreateKprV3CRsa();
    }
    
    public void setSelectedTaskCreateKprV1CRsa()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskCreateKprV1CRsa();
    }

    public void setSelectedTaskCreateShkAll()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskCreateShkAll();
    }

    public void setSelectedTaskKprFromKprKst()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskKprFromKprKst();
    }

    public void setSelectedTaskKprAnyToCsr()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskKprAnyToCsr();
    }
    
    public void setSelectedTaskTcrToCrt()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskTcrToCrt();
    }
    
    
    
    public void setSelectedTaskKprToKpr()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskKprToKpr();
    }
    
    public void setSelectedTaskCrtToSig()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskCrtToSig();
    }
    
    public void setSelectedTaskWelcomeKtl()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskWelcomeKtl();
    }
    
    
    
    public void setSelectedTaskCmsToSig()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskCmsToSig();
    }
    
    public void setSelectedTaskXmlToSig()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskXmlToSig();
    }
    
    
    
    public void setSelectedTaskJarToSig()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskJarToSig();
    }
    
    public void setSelectedTaskKprToSig()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskKprToSig();
    }
    
    public void setSelectedTaskKprToSCms()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskKprToSCms();
    }
    
    public void setSelectedTaskKprToXmlSig()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskKprToXmlSig();
    }
    
    public void setSelectedTaskKprToJarSig()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskKprToJarSig();
    }
            
    public void setSelectedTaskDirToArc()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskDirToArc();
    }
    
    public void setSelectedTaskShkToCryptEnc()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskShkToCryptEnc();
    }
    
    public void setSelectedTaskTcrRsaToCryptEnc()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskTcrRsaToCryptEnc();
    }
    
    
    
    public void setSelectedTaskKprRsaToCryptEnc()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskKprRsaToCryptEnc();
    }
    
    public void setSelectedTaskIOShkOut()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskIOShkOut();
    }
    
    
    
    public void setSelectedTaskIOShkIn()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskIOShkIn();
    }
    
    public void setSelectedTaskShkToCryptDec()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskShkToCryptDec();
    }
    
    
    
    public void setSelectedTaskKprRsaToCryptDec()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskKprRsaToCryptDec();
    }

    public void setSelectedTaskKprToCrt()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskKprToCrt();
    }
    
    public void setSelectedTaskTcrFromCCa()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskTcrFromCCa();
    }
    
    
    public void setSelectedTaskTcrFromCrt()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskTcrFromCrt();
    }
    
    public void setSelectedTaskKprFromKprDer()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskKprFromKprDer();
    }
    
    
    
    public void setSelectedTaskKprFromKprPem()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskKprFromKprPem();
    }

    public void setSelectedTaskKprAnyFromCrt()
    {
        if (super._pcpContentPane_ == null)
            return;

        ((PCPMainUIKtl) super._pcpContentPane_).setSelectedTaskKprAnyFromCrt();
    }



    public FMainUIKtl(
      
        java.awt.event.WindowListener winListenerParent,
        java.awt.event.ActionListener actListenerParent,
        java.awt.event.ItemListener itmListenerParent,
        javax.swing.event.ChangeListener chgListenerParent, // tabbedPane

        //javax.help.HelpBroker hbrHelpStandard,
        String strLic,
        boolean blnIsReg
        )
    {
        super(
         
            
            com.google.code.p.keytooliui.shared.awt.image.S_Image.s_get(
                com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.f_s_strAppliUIKtl16),
                
            winListenerParent,
            //hbrHelpStandard, 
            FMainUIKtl.s_strHelpHomeID
            );

        /*
            modif june 26, 2003
            from v1.1 to v1.2: disabling all
        */
                
        
        /**
        if (blnIsReg)
            setTitle(com.google.code.p.keytooliui.ktl.UIKeytool.s_getTitleAppli());
        else
            setTitle(com.google.code.p.keytooliui.ktl.UIKeytool.s_getTitleAppli() + " (U" + "nr" + "egis" + "te" + "red" + ")");
            **/
            
        // end modif   
            
            
        // ---------------
        // create children

        super._mbmMenuBar_ = new MBMainUIKtl(
            (java.awt.Component) this,
          
            actListenerParent,
            itmListenerParent,
            //hbrHelpStandard,
            strLic);

        super._pcpContentPane_ = new PCPMainUIKtl(
            (java.awt.Frame) this,
        
            actListenerParent, chgListenerParent/*, hbrHelpStandard*/);
    }
 }