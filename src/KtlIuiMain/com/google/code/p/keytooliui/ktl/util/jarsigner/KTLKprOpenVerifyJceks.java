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
 
 
package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

import java.awt.*;
import java.io.*;
import java.util.jar.*;
import java.util.*;
import java.util.zip.*;


final public class KTLKprOpenVerifyJceks extends KTLKprOpenVerifyAbs
{    
    // ------
    // PUBLIC
    
    public KTLKprOpenVerifyJceks(
        Frame frmOwner,
        String strTitleAppli,
        String strPathAbsKst, // nil value allowed (optional)
        String strProviderKst,
        String strPathAbsSignedJar,
        boolean blnEntrySize,
        boolean blnEntryDate,
        boolean blnEntryCertsType,
        boolean blnEntryCertsX509AlgoName,
        boolean blnEntryCertsX509SubjectDN
        )
    {
        super(
            frmOwner, 
            strTitleAppli, 
            strPathAbsKst, 
            (char[]) null, // chrsPasswdOpenKst
            
            strProviderKst,
            
            strPathAbsSignedJar,
            
            blnEntrySize,
            blnEntryDate,
            blnEntryCertsType,
            blnEntryCertsX509AlgoName,
            blnEntryCertsX509SubjectDN
        );
    }
    
    /**
        if any code error, exit
        else if any other error, show warning-error dialog, then return false
        else return true
    **/
    public boolean doJob()
    {
        String strMethod = "doJob()";
        
        // x) if pathAbsKeystore != nil, load keystore of type "JCEKS"
        java.security.KeyStore kstOpen = null;
        
        if (super._strPathAbsKst_ != null)
        {
            if (super._strProviderKst_ == null)
            {
                MySystem.s_printOutExit(this, strMethod, "nil super._strProviderKst_");
            }
            
            
            //if (super._strProviderKst_.toLowerCase().compareTo(KTLAbs._f_s_strSecurityProviderSunJce_.toLowerCase()) != 0)
              //  MySystem.s_printOutExit(this, strMethod, "wrong value, super._strProviderKst_=" + super._strProviderKst_);
            //MySystem.s_printOutWarning(this, strMethod, "should check for keystore of type JCEKS, provider named SunJCE");
            
            // memo: keystore should be of type "JCEKS", provided by "SunJCE"
            File fleOpen = UtilJsrFile.s_getFileOpen(
                super._frmOwner_, super._strTitleAppli_, super._strPathAbsKst_);
                
            if (fleOpen == null)
            {
                MySystem.s_printOutError(this, strMethod, "nil fleOpen");
                return false;
            }
            
            kstOpen = UtilKstJceks.s_getKeystoreOpen(
                super._frmOwner_, super._strTitleAppli_,
                fleOpen,
                (char[]) null // keystore's Password, not in use for verifying signed jarred file
                );
        
            if (kstOpen == null)
            {
                MySystem.s_printOutError(this, strMethod, "nil kstOpen");
                return false;
            }
        }
        
        super._setEnabledCursorWait_(true);
        
        if (! super._doJob_(kstOpen))
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        super._setEnabledCursorWait_(false);
        
        
        // ----
        return true;
    }
}