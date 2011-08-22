/*
 *
 * Copyright (c)  2001-2011 keyTool IUI Project.
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

    KTLShkSaveNewAllJceks: "Shk" for "Shared key"
    
    
    !!!!!!!!! not done yet: overwritting existing Shared key entry

**/

import com.google.code.p.keytooliui.ktl.swing.dialog.*;

import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;

// memo: assigning full class path coz ambiguous: same class name in several Java packages
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;


import java.awt.*;
import java.io.*;
import java.util.*;

public final class KTLShkSaveNewAllJceks extends KTLShkSaveNewAllJAbs
{
    // ------
    // PUBLIC
    
    /**
        if any error in code, exiting
        in case of trbrl: open up a warning dialog, and return false;
        
        algo:
        . get fileOpen keystore
        . open keystore
        . fill in tables
        . show dialog new 
          . get aliasShk
          . get passwdShk
        . create new shared key
        . assign new entry to open keystore
        . save keystore
    **/
    public boolean doJob()
    {
        String strMethod = "doJob()";
        
        //MySystem.s_printOutWarning(this, strMethod, "<<<< IN PROGRESS >>>>");
        
        
        super._setEnabledCursorWait_(true);
        
        
        // ---
        
        // memo: keystore should be of type "JCEKS", provided by "SunJCE"
        File fleOpenKstJceks = UtilJsrFile.s_getFileOpen(
            super._frmOwner_,  super._strPathAbsKst_);
        
        if (fleOpenKstJceks == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil fleOpenKstJceks");
            return false;
        }
        
        // ----
        // open JCEKS keystore
        
        if (super._chrsPasswdKst_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._chrsPasswdKst_"); 
        }
        
        KeyStore kstOpenJceks = UtilKstJceks.s_getKeystoreOpen(
            super._frmOwner_, 
            fleOpenKstJceks,
            super._chrsPasswdKst_);
        
        if (kstOpenJceks == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil kstOpenJceks");
            return false;
        }
        
        if (! super._doJob_(fleOpenKstJceks, kstOpenJceks))
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        super._setEnabledCursorWait_(false);
        
        
        
        // ending
        return true;
    }
    
    public KTLShkSaveNewAllJceks(
        Frame frmOwner, 

        
        // input
        String strPathAbsOpenKst, // existing keystore of type JCEKS 
        char[] chrsPasswdOpenKst, 
        
        // output
        String strInstanceKeygenerator
        )
    {
        super(
            frmOwner, 
    
        
            // input
            strPathAbsOpenKst, // existing keystore of type JCEKS 
            chrsPasswdOpenKst, 
        
            // output
            strInstanceKeygenerator,
            KTLAbs.f_s_strProviderKstJceks // provider for keystore
            
            );
    }
    
}
