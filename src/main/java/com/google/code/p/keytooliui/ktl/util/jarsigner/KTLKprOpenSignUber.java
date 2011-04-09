/*
 *
 * Copyright (c) 2001-2007 keyTool IUI Project.
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

    "Kpr" for "keypair"
    

**/


import com.google.code.p.keytooliui.shared.lang.*;

//import sun.security.util.SignatureFile;

// ----
import java.security.KeyStore;
// --
// ----

import java.awt.*;
import java.io.*;

public final class KTLKprOpenSignUber extends KTLKprOpenSignKPAbs
{
    // ------
    // PUBLIC
    
    public boolean doJob()
    {
        String strMethod = "doJob()";
        
        if (super._strProviderKst_.toLowerCase().compareTo(KTLAbs.f_s_strProviderKstUber.toLowerCase()) != 0)
        {
            MySystem.s_printOutExit(this, strMethod, "wrong value, super._strProviderKst_=" + super._strProviderKst_);
        }
        
        if (! super.doJob())
            return false;
     
        return true;
    }
    
    

    public KTLKprOpenSignUber(
        Frame frmOwner, 
       
        
        // input
        String strPathAbsOpenKst, // existing keystore of type Uber 
        char[] chrsPasswdOpenKst,
        String strProviderKst,
        
        String strPathAbsOpenJarSource, // unsigned
        
        // output
        String strPathAbsSaveJarTarget, // to be signed
        String strNameBaseSigFile // nil value allowed
        )
    {
        super(
            frmOwner, 
        
            strPathAbsOpenKst, 
            chrsPasswdOpenKst,
            strProviderKst, // "Kst": "Uber"
            
            strPathAbsOpenJarSource,
            strPathAbsSaveJarTarget,
            strNameBaseSigFile
            );
    }
    
    // ---------
    // protected
    
    protected KeyStore _getKeystoreOpen_(File fleOpenKst)
    {
        KeyStore kstOpen = UtilKstUber.s_getKeystoreOpen(
            super._frmOwner_, 
            fleOpenKst,
            super._chrsPasswdKst_);
        
        return kstOpen;
    }
}
