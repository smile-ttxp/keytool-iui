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
    known subclasses:
    . KTLKstSaveJks
    . KTLKstSaveJceks


    "KTL" means "KeyTool"

    (create an empty keystore)
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



abstract public class KTLKstSaveAbs extends KTLKstAbs
{
    // ---------
    // PROTECTED
    
    protected KTLKstSaveAbs(
        Frame frmOwner, 
        String strTitleAppli,
        String strPathAbs,
        char[] chrsPasswd,
        
        String strProvider
        )
    {
        super(frmOwner, strTitleAppli,
        strPathAbs, // existing keystore
        chrsPasswd,
        strProvider
        );
    }
    
    protected boolean _doJob_(KeyStore kstNew)
    {
        String strMethod = "_doJob_(kstNew)";
        
        // --
        if (kstNew == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil kstNew");
        }

        // --
        if (super._strPathAbsKst_ == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil super._strPathAbs_");
        }
        
        File fleSaveNew = UtilJsrFile.s_getFileSave(
            super._frmOwner_, super._strTitleAppli_, super._strPathAbsKst_,
            true // blnShowDlgOverwrite
            );
        
        if (fleSaveNew == null)
        {
            MySystem.s_printOutWarning(this, strMethod, "nil fleSaveNew");
            return false;
        }
        
        // ----
        
        if (! super._saveKeyStore_(kstNew, fleSaveNew, super._chrsPasswdKst_))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        return true;
    }
    
}