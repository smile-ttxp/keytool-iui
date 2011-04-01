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
    "UtilKstPkcs12" ==> "Utility, keystore of type PKCS12 (CA certificate)"
**/



import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.swing.dialog.*;

// ----
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
// ----
import java.security.cert.Certificate;
// ----

import java.io.*;
import java.awt.*;
import java.util.*;

final public class UtilKstPkcs12 extends UtilKstAbs
{
    // -------------------
    // FINAL STATIC PUBLIC
    
    final static public String f_s_strKeystoreType = "PKCS12"; // default type
    final static public String f_s_strKeystoreProvider = KTLAbs.f_s_strsProviderKstPkcs12RW[0]; // default type
    
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstPkcs12";
    
    // -------------
    // STATIC PUBLIC
    
    static public void s_manageFile(
        String strTitleAppli,
        Frame frmParent)
    {
        String strMethod = UtilKstPkcs12._f_s_strClass + "." + "s_manageFile(...)";
        
        File fleOpenKstPkcs12 = com.google.code.p.keytooliui.ktl.io.S_FileChooserUI.s_getOpenFile(
            strTitleAppli, 
            frmParent, 
            "select",
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strsKstPkcs12, 
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strFileDescKstPkcs12,
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strDirNameDefaultKst
            );
            
        if (fleOpenKstPkcs12 == null)
        {
            MySystem.s_printOutTrace(strMethod, "nil fleOpenKstPkcs12");
            return;
        }
            
        if (! fleOpenKstPkcs12.exists())
        {
            MySystem.s_printOutError(strMethod, 
                "! fleOpenKstPkcs12.exists(), fleOpenKstPkcs12.getAbsolutePath()=" + 
                fleOpenKstPkcs12.getAbsolutePath());
            
            String strBody = "File does not exist:";
            
            strBody += "\n\n";
            strBody += "  ";
            strBody += fleOpenKstPkcs12.getAbsolutePath();
                
            OPAbstract.s_showDialogError(
                frmParent, strTitleAppli, strBody);
                
            return;
        }
        
        if (! fleOpenKstPkcs12.canRead())
        {
            MySystem.s_printOutError(strMethod, "! fleOpenKstPkcs12.canRead(), fleOpenKstPkcs12.getAbsolutePath()=" + 
                fleOpenKstPkcs12.getAbsolutePath());
            
            String strBody = "File cannot be read:";
            
            strBody += "\n\n";
            strBody += "  ";
            strBody += fleOpenKstPkcs12.getAbsolutePath();
         
            OPAbstract.s_showDialogError(
                frmParent, strTitleAppli, strBody);
                
            return;
        }
        
        // ----
        // get password
        
        char[] chrsPasswdKst = null;
 
        // ----
        // open up a passwordOpen dialog
                
        DPasswordOpen dlgPasswordKst = null;
        
        // ---
        
        dlgPasswordKst = new DPasswordOpen(
            frmParent, 
            strTitleAppli,
            true // blnNoPasswdAllowed
                );
                    
        String strTitleSuffixDlg = " ";
        strTitleSuffixDlg += "for";
        strTitleSuffixDlg += " ";
        strTitleSuffixDlg += UtilKstPkcs12.f_s_strKeystoreType;
        strTitleSuffixDlg += " ";
        strTitleSuffixDlg += "keystore";
 
        dlgPasswordKst.setTitle(dlgPasswordKst.getTitle() + strTitleSuffixDlg);
                    
        if (! dlgPasswordKst.init())
            MySystem.s_printOutExit(strMethod, "failed");
                    
        dlgPasswordKst.setVisible(true);
                
        chrsPasswdKst = dlgPasswordKst.getPassword();
                
        if (chrsPasswdKst == null)
        {
            MySystem.s_printOutTrace(strMethod, "nil chrsPasswdKst, user canceled");
            return;
        }
                
        dlgPasswordKst.destroy();
        dlgPasswordKst = null;

        // ----
        // open keystore        
        
        KeyStore kstOpen = UtilKstPkcs12.s_getKeystoreOpen(
            frmParent, 
            strTitleAppli,
            fleOpenKstPkcs12,
            chrsPasswdKst);
        
        if (kstOpen == null)
        {
            MySystem.s_printOutError(strMethod, "nil kstOpen");
            
            // don't show error dialog
            
            return;
        }
        
        UtilKstAbs._s_manageKstOpen_(
            strTitleAppli, 
            frmParent, 
            kstOpen,
            fleOpenKstPkcs12.getAbsolutePath(),
            chrsPasswdKst,
            UtilKstPkcs12.f_s_strKeystoreProvider // memo: only BC supports storing of PKCS12 at this time
            );
    }
    
    // --
    
    static public void s_showFile(
        String strTitleAppli,
        Frame frmParent)
    {
        String strMethod = UtilKstPkcs12._f_s_strClass + "." + "s_showFile(...)";
        
        File fleOpenKst = com.google.code.p.keytooliui.ktl.io.S_FileChooserUI.s_getOpenFile(
            strTitleAppli, 
            frmParent, 
            "select",
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strsKstPkcs12, 
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strFileDescKstPkcs12,
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strDirNameDefaultKst
            );
            
        if (fleOpenKst == null)
        {
            MySystem.s_printOutTrace(strMethod, "nil fleOpenKst");
            return;
        }
            
        if (! fleOpenKst.exists())
        {
            MySystem.s_printOutError(strMethod, "! fleOpenKst.exists(), fleOpenKst.getAbsolutePath()=" + fleOpenKst.getAbsolutePath());
            
            String strBody = "File does not exist:";
            
            strBody += "\n\n";
            strBody += "  ";
            strBody += fleOpenKst.getAbsolutePath();
            
                
            OPAbstract.s_showDialogError(
                frmParent, strTitleAppli, strBody);
                
            return;
        }
        
        if (! fleOpenKst.canRead())
        {
            MySystem.s_printOutError(strMethod, "! fleOpenKst.canRead(), fleOpenKst.getAbsolutePath()=" + fleOpenKst.getAbsolutePath());
            
            String strBody = "File cannot be read:";
            
            strBody += "\n\n";
            strBody += "  ";
            strBody += fleOpenKst.getAbsolutePath();
            
                
            OPAbstract.s_showDialogError(
                frmParent, strTitleAppli, strBody);
                
            return;
        }
        
        // open up a passwordOpen dialog
                
        DPasswordOpen dlgPasswordKst = null;
        
        // ---
        
        dlgPasswordKst = new DPasswordOpen(
            frmParent, 
            strTitleAppli,
            true // blnNoPasswdAllowed
                );
                    
        String strTitleSuffixDlg = " ";
        strTitleSuffixDlg += "for";
        strTitleSuffixDlg += " ";
        strTitleSuffixDlg += UtilKstPkcs12.f_s_strKeystoreType;
        strTitleSuffixDlg += " ";
        strTitleSuffixDlg += "keystore";
                
                
        dlgPasswordKst.setTitle(dlgPasswordKst.getTitle() + strTitleSuffixDlg);
                    
        if (! dlgPasswordKst.init())
            MySystem.s_printOutExit(strMethod, "failed");
                    
        dlgPasswordKst.setVisible(true);
                
        char[] chrsPasswdKst = dlgPasswordKst.getPassword();
                
        if (chrsPasswdKst == null)
        {
            MySystem.s_printOutTrace(strMethod, "nil chrsPasswdKst, user canceled");
            return;
        }
                
                
        dlgPasswordKst.destroy();
        dlgPasswordKst = null;
        
        // ----
        UtilKstPkcs12._s_showFile_(strTitleAppli, frmParent, fleOpenKst, chrsPasswdKst);
    }
    
    static public void s_showFile(
        String strTitleAppli,
        Frame frmParent,
        String strPathAbs, char[] chrsPasswd)
    {
        String strMethod = UtilKstPkcs12._f_s_strClass + "." + "s_showFile(...)";
        
        File fleOpenKst = null;
        
        try
        {

            fleOpenKst = new File(strPathAbs);
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(strMethod, "got exception");
            
            String strBody = "Failed to open file, got exception";
            strBody += "\n" + exc.getMessage();
            
            strBody += "\n\n";
            strBody += "  ";
            strBody += fleOpenKst.getAbsolutePath();
            
                
            OPAbstract.s_showDialogError(
                frmParent, strTitleAppli, strBody);
                
            return;
        }
        
        UtilKstPkcs12._s_showFile_(strTitleAppli, frmParent, fleOpenKst, chrsPasswd); 
    }
    
    static public void s_showFile(
        String strTitleAppli,
        Frame frmParent,
        String strPathAbs)
    {
        String strMethod = UtilKstPkcs12._f_s_strClass + "." + "s_showFile(...)";
        
        File fleOpenKst = null;
        
        try
        {

            fleOpenKst = new File(strPathAbs);
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(strMethod, "got exception");
            
            String strBody = "Failed to open file, got exception";
            strBody += "\n" + exc.getMessage();
            
            strBody += "\n\n";
            strBody += "  ";
            strBody += fleOpenKst.getAbsolutePath();
            
                
            OPAbstract.s_showDialogError(
                frmParent, strTitleAppli, strBody);
                
            return;
        }
        
        // open up a passwordOpen dialog
                
        DPasswordOpen dlgPasswordKst = null;
        
        // ---
        
        dlgPasswordKst = new DPasswordOpen(
            frmParent, 
            strTitleAppli,
            true // blnNoPasswdAllowed
                );
                    
        String strTitleSuffixDlg = " ";
        strTitleSuffixDlg += "for";
        strTitleSuffixDlg += " ";
        strTitleSuffixDlg += UtilKstPkcs12.f_s_strKeystoreType;
        strTitleSuffixDlg += " ";
        strTitleSuffixDlg += "keystore";
                
                
        dlgPasswordKst.setTitle(dlgPasswordKst.getTitle() + strTitleSuffixDlg);
                    
        if (! dlgPasswordKst.init())
            MySystem.s_printOutExit(strMethod, "failed");
                    
        dlgPasswordKst.setVisible(true);
                
        char[] chrsPasswdKst = dlgPasswordKst.getPassword();
                
        if (chrsPasswdKst == null)
        {
            MySystem.s_printOutTrace(strMethod, "nil chrsPasswdKst, user canceled");
            return;
        }
                
                
        dlgPasswordKst.destroy();
        dlgPasswordKst = null;
        
        UtilKstPkcs12._s_showFile_(strTitleAppli, frmParent, fleOpenKst, chrsPasswdKst); 
    }
    
    // chrsPasswdKst
    
    // ----
    
    static protected void _s_showFile_(
        String strTitleAppli,
        Frame frmParent,
        File fle,
        char[] chrsPasswd)
    {
        String strMethod = UtilKstPkcs12._f_s_strClass + "." + "_s_showFile_(...)";
            
        if (fle == null)
        {
            MySystem.s_printOutError(strMethod, "nil fle");
            String strBody = "Failed to load file.";            
            OPAbstract.s_showDialogError(frmParent, strTitleAppli, strBody);
                
            return;
        }
            
        if (! fle.exists())
        {
            MySystem.s_printOutError(strMethod, "! fle.exists(), fle.getAbsolutePath()=" + fle.getAbsolutePath());
            
            String strBody = "File does not exist:";
            
            strBody += "\n\n";
            strBody += "  ";
            strBody += fle.getAbsolutePath();
            
                
            OPAbstract.s_showDialogError(
                frmParent, strTitleAppli, strBody);
                
            return;
        }
        
        if (! fle.canRead())
        {
            MySystem.s_printOutError(strMethod, "! fle.canRead(), fle.getAbsolutePath()=" + fle.getAbsolutePath());
            
            String strBody = "File cannot be read:";
            
            strBody += "\n\n";
            strBody += "  ";
            strBody += fle.getAbsolutePath();
            
                
            OPAbstract.s_showDialogError(
                frmParent, strTitleAppli, strBody);
                
            return;
        }
        /*
        
        */
        
        // ----
        // open keystore
        
        
        KeyStore kstOpen = UtilKstPkcs12.s_getKeystoreOpen(
            frmParent, strTitleAppli,
            fle,
            chrsPasswd //new char[0] 
                );
        
        if (kstOpen == null)
        {
            MySystem.s_printOutError(strMethod, "nil kstOpen");
            
            // don't show error dialog
            
            return;
        }
        
        UtilKstAbs._s_showKstOpen_(strTitleAppli, frmParent, kstOpen, fle.getAbsolutePath());
    }
    
    
    static public boolean s_setKeyEntry(
        Frame frmOwner, 
        String strTitleAppli,
        KeyStore kstOpen,
        String strAliasKpr,
        Key keyPrivate,
        Certificate[] crtsChain
        )
     {
        return UtilKstAbs.s_setKeyEntry(
            frmOwner,
            strTitleAppli,
            kstOpen,
            strAliasKpr,
            keyPrivate,
            new char[0], // passwdKpr 
            crtsChain
            ); 
     }
     
     /**
        if any code or config error, exiting
        else if any other type of error, show warning/error dialog, then return null
        
        
    **/
    static public Key s_getKeyProviderAny(
        Component cmpOwner, 
        String strTitleAppli,
        KeyStore kseLoaded,
        String strAliasKpr,
        char[] chrsPasswdKpr,
        String strProvider
        )
    {
        String strMethod = _f_s_strClass + "." + "s_getKeyProviderAny(...)";
        
        if (strProvider == null)
        {
            MySystem.s_printOutError(strMethod, "nil strProvider");
            return null;
        }
        
        if (strProvider.toLowerCase().compareTo(
                //"SunRsaSign"
                KTLAbs.f_s_strSecurityProviderSunRsaSign // modified may 17, 07
                .toLowerCase()) == 0)
        {
            return UtilKstPkcs12.s_getKeyProviderSunJsse(
                cmpOwner, 
                strTitleAppli,
                kseLoaded,
                strAliasKpr,
                chrsPasswdKpr)
                ;
        }
        
        if (strProvider.toLowerCase().compareTo("BC".toLowerCase()) == 0)
        {
            return UtilKstPkcs12.s_getKeyProviderBc(
                cmpOwner, 
                strTitleAppli,
                kseLoaded,
                strAliasKpr)
                ;
        }
        
        MySystem.s_printOutError(strMethod, "uncaught provider, strProvider=" + strProvider);
        return null;
    }
    
    /**
        if any code or config error, exiting
        else if any other type of error, show warning/error dialog, then return null
        
        
    **/
    static public Key s_getKeyProviderBc(
        Component cmpOwner, 
        String strTitleAppli,
        KeyStore kseLoaded,
        String strAliasKpr
        )
    {
        return UtilKstAbs.s_getKey(
            cmpOwner, 
            strTitleAppli,
            kseLoaded,
            strAliasKpr,
            new char[]{} // chrsPasswdKpr
            );
    }
    
    static public Key s_getKeyProviderSunJsse(
        Component cmpOwner, 
        String strTitleAppli,
        KeyStore kseLoaded,
        String strAliasKpr,
        char[] chrsPasswdKpr
        )
    {
        return UtilKstAbs.s_getKey(
            cmpOwner, 
            strTitleAppli,
            kseLoaded,
            strAliasKpr,
            chrsPasswdKpr
            );
    }
    
    static public KeyStore s_getKeystoreNew(
        Frame frmOwner, 
        String strTitleAppli,
        String strProviderKst
        )
    {
        return UtilKstAbs._s_getKeystoreNew_(
            frmOwner,
            strTitleAppli,
            UtilKstPkcs12.f_s_strKeystoreType,
            UtilKstPkcs12.f_s_strKeystoreProvider
            );     
    }
    
    /**
        open an existing keystore,
        keystore may be empty (no entries)
        
        if code error, exiting
        else if any error, show warning dialog and return false
    **/
    static public KeyStore s_getKeystoreOpen(
        Frame frmOwner, 
        String strTitleAppli,
        File fleOpen,
        char[] chrsPassword // nil value allowed (eg: verify signed jarred file)
        )
    {
        return UtilKstAbs._s_getKeystoreOpen_(
            frmOwner,
            strTitleAppli,
            fleOpen,
            chrsPassword,
            UtilKstPkcs12.f_s_strKeystoreType,
            UtilKstPkcs12.f_s_strKeystoreProvider
            );
    }
    
    // ----
}