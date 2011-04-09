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
    known subclasses:
    . PSelBtnTfdStrXlsAliasAbs
    . PSelBtnTfdStrXlsLengthMin
    . PSelBtnTfdStrXlsCbxAbs
    . PSelBtnTfdStrXlsSigfile
**/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;
import com.google.code.p.keytooliui.shared.swing.textfield.*;

import java.awt.*;

public abstract class PSelBtnTfdStrXlsAbs extends PSelBtnTfdStrAbs
{
    // ------
    // PUBLIC
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // added sept 6, 2002
        super.setEnabledSelect(true);
        
        // ending
        return true;
    }
   
    
    // ---------
    // PROTECTED
    
    protected PSelBtnTfdStrXlsAbs(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent, 

        String strLabel,
        TFAbstract tfd,
        Object objDocPropValue,
        boolean blnFieldRequired
        )
    {
        super(
            frmParent, 
     
            strLabel,
            tfd,
            objDocPropValue,
            blnFieldRequired
            );
        
        String strMethod = "PSelBtnTfdStrXlsAbs(...)";
        
        if (tfd!=null && docListenerParent!=null)
        {
            tfd.getDocument().addDocumentListener(docListenerParent);
        }
    }
}