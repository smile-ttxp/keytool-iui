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
 
 
package com.google.code.p.keytooliui.shared.swing.dialog;


import com.google.code.p.keytooliui.shared.swing.panel.*;
import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.awt.*;

final public class DSwUrlMediaAudio extends DSwUrlMediaAbs
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strTitleSuffix = "display audio";
    
    // ------
    // PUBLIC
    

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
            
            if (DSwAbs._f_s_intWidthMin_ < intW)
                intW = DSwAbs._f_s_intWidthMin_;
            
            Dimension dim = new Dimension(intW, getSize().height);
            setSize(dim);
        }
        
        super.setVisible(bln);
        
        if (! bln)
            destroy();
    }
    
        
    public DSwUrlMediaAudio(
        Dialog dlgOwner,
        String strTitleApplication, 
        java.net.URL url)
    {
        super(dlgOwner, strTitleApplication, url);
        
        super._pnlContentPane_ = new PCpSwUrlMediaAudio(url, strTitleApplication, (java.awt.Window) this);
        
        if (getTitle() != null)
            setTitle(getTitle() + " - " + _f_s_strTitleSuffix);
        else
            setTitle(_f_s_strTitleSuffix);
    }
}