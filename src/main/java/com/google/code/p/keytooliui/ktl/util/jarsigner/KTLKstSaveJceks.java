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
 
 
package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**   
    "KTL" means "KeyTool"

    (create an empty keystore of type JCEKS)
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



public final class KTLKstSaveJceks extends KTLKstSaveAbs
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
        
        
        KeyStore kstNew = UtilKstJceks.s_getKeystoreNew(super._frmOwner_);
        
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
    
    public KTLKstSaveJceks(
        Frame frmOwner, 
  
        String strPathAbs,
        char[] chrsPasswd
        )
    {
        super(
            frmOwner, 
  
            strPathAbs, 
            chrsPasswd, 
            KTLAbs.f_s_strProviderKstJceks
            );
    }
}