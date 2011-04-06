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
 

package com.google.code.p.keytooliui.ktl.swing.panel;

/**
    CP ==> content pane

    contains:
    . on top:    toolBar
    . centered:  tabbedPane
    . on bottom: status bar
**/

import com.google.code.p.keytooliui.ktl.swing.splitpane.SPUIMainKtl;
import com.google.code.p.keytooliui.ktl.swing.toolbar.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.util.eventlistener.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;

import java.awt.*;

final public class PCPMainUIKtl extends PCPMainUIAbs
{    
    // ------
    // PUBLIC
    
    public void setSelectedTaskCreateKst()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskCreateKst();
    }
    
    public void setSelectedTaskCreateKprV3CDsa()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskCreateKprV3CDsa();
    }
    
    public void setSelectedTaskCreateKprV1CDsa()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskCreateKprV1CDsa();
    }
    
    public void setSelectedTaskCreateKprV3CEc()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskCreateKprV3CEc();
    }
    
    public void setSelectedTaskCreateKprV1CEc()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskCreateKprV1CEc();
    }
    
    public void setSelectedTaskCreateKprV3CRsa()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskCreateKprV3CRsa();
    }
    
    public void setSelectedTaskCreateKprV1CRsa()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskCreateKprV1CRsa();
    }
    
    public void setSelectedTaskCreateShkAll()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskCreateShkAll();
    }
    
    public void setSelectedTaskKprFromKprKst()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskKprFromKprKst();
    }
    
    public void setSelectedTaskKprAnyToCsr()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskKprAnyToCsr();
    }  
    
    public void setSelectedTaskTcrToCrt()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskTcrToCrt();
    }
    
    
    
    public void setSelectedTaskKprToKpr()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskKprToKpr();
    }
    
    public void setSelectedTaskCrtToSig()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskCrtToSig();
    }
    
    public void setSelectedTaskWelcomeKtl()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskWelcomeKtl();
    }
    
    public void setSelectedTaskCmsToSig()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskCmsToSig();
    }
    
    public void setSelectedTaskXmlToSig()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskXmlToSig();
    }
    
    
    public void setSelectedTaskJarToSig()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskJarToSig();
    }
    
    public void setSelectedTaskKprToSCms()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskKprToSCms();
    }
    
    public void setSelectedTaskKprToSig()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskKprToSig();
    }
    
    public void setSelectedTaskKprToXmlSig()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskKprToXmlSig();
    }
    
    public void setSelectedTaskKprToJarSig()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskKprToJarSig();
    }
    
    public void setSelectedTaskDirToArc()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskDirToArc();
    }
    
    public void setSelectedTaskShkToCryptEnc()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskShkToCryptEnc();
    }
    
    public void setSelectedTaskTcrRsaToCryptEnc()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskTcrRsaToCryptEnc();
    }
    
    
    
    public void setSelectedTaskKprRsaToCryptEnc()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskKprRsaToCryptEnc();
    }
    
     public void setSelectedTaskIOShkOut()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskIOShkOut();
    }
    
    
    public void setSelectedTaskIOShkIn()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskIOShkIn();
    }
    
    public void setSelectedTaskShkToCryptDec()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskShkToCryptDec();
    }
    
    
    
    public void setSelectedTaskKprRsaToCryptDec()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskKprRsaToCryptDec();
    }
    
    public void setSelectedTaskKprToCrt()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskKprToCrt();
    }
    
    public void setSelectedTaskTcrFromCCa()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskTcrFromCCa();
    }
    
    public void setSelectedTaskTcrFromCrt()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskTcrFromCrt();
    }
    
    public void setSelectedTaskKprFromKprDer()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskKprFromKprDer();
    }
    
    
    
    public void setSelectedTaskKprFromKprPem()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskKprFromKprPem();
    }
    
    public void setSelectedTaskKprAnyFromCrt()
    {
        if (super._spe_ == null)
            return;
            
        ((SPUIMainKtl) super._spe_).setSelectedTaskKprAnyFromCrt();
    }
    
    // --
   
    
    public PCPMainUIKtl(
        Frame frmParent, 
        java.awt.event.ActionListener alrParentAppli,
        javax.swing.event.ChangeListener chgListenerParent/*,
        javax.help.HelpBroker hbrHelpStandard*/
        )
    {
        // create children
        super._tbrToolBar_ = new TBMainUIKtl(frmParent, alrParentAppli/*, hbrHelpStandard*/);
        super._spe_ = new SPUIMainKtl(frmParent);
    }
}