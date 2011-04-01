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

final public class KTLKprOpenVerifyPkcs12 extends KTLKprOpenVerifyAbs
{
    // ------
    // PUBLIC
    
    public KTLKprOpenVerifyPkcs12(
        Frame frmOwner,
        String strTitleAppli,
        String strPathAbsKst, // nil value allowed (optional)
        String strProviderKst,
        String strPathAbsSignedJar,
        boolean blnEntrySize,
        boolean blnEntryDate,
        boolean blnEntryCertsType,
        boolean blnEntryCertsX509AlgoName,
        boolean blnEntryCertsX509SubjectDN,
        
        char[] chrsPasswdOpenKst // if (strPathAbsKst != null) ==> this one SHOULD NOT BE NIL!
        )
    {
        super(
            frmOwner, 
            strTitleAppli, 
            strPathAbsKst, 
            chrsPasswdOpenKst,
            strProviderKst,
            
            strPathAbsSignedJar,
            
            blnEntrySize,
            blnEntryDate,
            blnEntryCertsType,
            blnEntryCertsX509AlgoName,
            blnEntryCertsX509SubjectDN
            );
            
         //this._chrsPasswdKst = chrsPasswdKst;
    }
    
    
    /**
        if any code error, exit
        else if any other error, show warning-error dialog, then return false
        else return true
    **/
    public boolean doJob()
    {
        String strMethod = "doJob()";
        
        // x) if pathAbsKeystore != nil, load keystore of type "PKCS12"
        java.security.KeyStore kstOpen = null;
        
        if (super._strPathAbsKst_ != null)
        {
            if (super._strProviderKst_ == null)
            {
                MySystem.s_printOutExit(this, strMethod, "nil super._strProviderKst_");
            }
            
            if (super._chrsPasswdKst_ == null)
            {
                MySystem.s_printOutExit(this, strMethod, "nil super._chrsPasswdKst_");
            }

                  
            //if (! _addProviders())
              //  MySystem.s_printOutExit(this, strMethod, "failed");
            
            // memo: keystore should be of type "PKCS12", provided by "BC", or "SunRsaSign"
            File fleOpenKst = UtilJsrFile.s_getFileOpen(
                super._frmOwner_, super._strTitleAppli_, super._strPathAbsKst_);
                
            if (fleOpenKst == null)
            {
                MySystem.s_printOutError(this, strMethod, "nil fleOpenKst");
                return false;
            }
            
            kstOpen = UtilKstPkcs12.s_getKeystoreOpen(
                super._frmOwner_, super._strTitleAppli_,
                fleOpenKst,
                super._chrsPasswdKst_ // keystore's Password, REQUIRED!
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
        
        // --
        return true;
    }
    
    //  ------
    // PRIVATE
    

     /**
        !!!! SAME CODE AS IN KTLKprCreateImportRsa !!!!
        

        
        if any error, NO ERROR/WARNING dialog, just return false, calling method should perform an exit
    
    
    **/ 
    /**private boolean _addProviders()
    {
        String strMethod = "_addProviders()";
        
        if (super._strProviderKst_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._strProviderKst_");
            return false;
        }
        
        if (super._strProviderKst_.toLowerCase().compareTo(
            KTLAbs.f_s_strProviderKstBC.toLowerCase()) == 0)
        {
            //if (! super._addProviderBC_())
            //{
             //   MySystem.s_printOutError(this, strMethod, "failed");
             //   return false;
           // }
        }
        
        else if (super._strProviderKst_.toLowerCase().compareTo(
            KTLAbs.f_s_strSecurityProviderSunRsaSign.toLowerCase()) == 0)
        {
            if (! super._addProviderSunJSSE_())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
        }
        
        else
        {
            MySystem.s_printOutError(this, strMethod, "uncaught value, super._strProviderKst_=" + super._strProviderKst_);
            return false;
            
        }
        
        return true;
    }**/
}