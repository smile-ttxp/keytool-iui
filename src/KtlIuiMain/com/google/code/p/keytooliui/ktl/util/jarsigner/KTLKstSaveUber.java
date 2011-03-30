/*
 *
 * Copyright (c) 2001-2007 RagingCat Project.
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
 
 
package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**   
    "KTL" means "KeyTool"

    (create an empty keystore of type UBER)
    and store it in a new file,
     or overwrite an existing file (pending)
    
   
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;

// memo: assigning full class path coz ambiguous: same class name in several Java packages
import java.security.KeyStore;
import java.security.KeyStoreException;



import java.awt.*;
import java.io.*;



final public class KTLKstSaveUber extends KTLKstSaveAbs
{
    // ------
    // PUBLIC
    
    /**
        if any error in code, exiting
        in case of tbrl: open up a warning dialog, and return false;
    **/
    public boolean doJob()
    {
        String strMethod = "doJob()";
        
        KeyStore kstNew = UtilKstUber.s_getKeystoreNew(
            super._frmOwner_, super._strTitleAppli_);
            
        MySystem.s_printOutTrace(this, strMethod, "super._strProviderKst_=" + super._strProviderKst_);
        
        if (kstNew == null)
        {
            MySystem.s_printOutWarning(this, strMethod, "nil kstNew");
            return false;
        }
        
        if (! super._doJob_(kstNew))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ----
        return true;
    }
    
    public KTLKstSaveUber(
        Frame frmOwner, 
        String strTitleAppli,
        String strPathAbs,
        char[] chrsPasswd
        )
    {
        super(
            frmOwner, 
            strTitleAppli, 
            strPathAbs, 
            chrsPasswd, 
            KTLAbs.f_s_strProviderKstBC
            );
    }
}