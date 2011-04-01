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
    "UtilKstJceks" ==> "Utility, keystore of type JCEKS"
**/

import com.google.code.p.keytooliui.ktl.swing.dialog.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.swing.dialog.*;

// ----
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
// --
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
// ----


import java.io.*;
import java.awt.*;
import java.util.*;


final public class UtilKstJceks extends UtilKstAbs
{
    // -------------------
    // FINAL STATIC PUBLIC
    
    final static public String f_s_strKeystoreType = "JCEKS"; // default type
    final static public String f_s_strKeystoreProvider = "SunJCE"; // default type
    
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJceks";

    // -------------
    // STATIC PUBLIC
    
    static public void s_manageFile(
        String strTitleAppli,
        Frame frmParent)
    {
        String strMethod = UtilKstJceks._f_s_strClass + "." + "s_manageFile(...)";
        
        File fleOpenKstJceks = com.google.code.p.keytooliui.ktl.io.S_FileChooserUI.s_getOpenFile(
            strTitleAppli, 
            frmParent, 
            "select",
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strsKstJceks, 
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strFileDescKstJceks,
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strDirNameDefaultKst
            );
            
        if (fleOpenKstJceks == null)
        {
            MySystem.s_printOutTrace(strMethod, "nil fleOpenKstJceks");
            return;
        }
            
        if (! fleOpenKstJceks.exists())
        {
            MySystem.s_printOutError(strMethod, "! fleOpenKstJceks.exists(), fleOpenKstJceks.getAbsolutePath()=" + fleOpenKstJceks.getAbsolutePath());
            
            String strBody = "File does not exist:";
            
            strBody += "\n\n";
            strBody += "  ";
            strBody += fleOpenKstJceks.getAbsolutePath();
                
            OPAbstract.s_showDialogError(
                frmParent, strTitleAppli, strBody);
                
            return;
        }
        
        if (! fleOpenKstJceks.canRead())
        {
            MySystem.s_printOutError(strMethod, "! fleOpenKstJceks.canRead(), fleOpenKstJceks.getAbsolutePath()=" + 
                fleOpenKstJceks.getAbsolutePath());
            
            String strBody = "File cannot be read:";
            
            strBody += "\n\n";
            strBody += "  ";
            strBody += fleOpenKstJceks.getAbsolutePath();
            
                
            OPAbstract.s_showDialogError(
                frmParent, strTitleAppli, strBody);
                
            return;
        }
        
        // ----
        // get password
        
        char[] chrsPasswdKst = null;
        
        
        
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
        strTitleSuffixDlg += UtilKstJceks.f_s_strKeystoreType;
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
        
        
        KeyStore kstOpen = UtilKstJceks.s_getKeystoreOpen(
            frmParent, strTitleAppli,
            fleOpenKstJceks,
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
            fleOpenKstJceks.getAbsolutePath(),
            chrsPasswdKst,
            UtilKstJceks.f_s_strKeystoreProvider);
    }
    
    static public void s_showFile(
        String strTitleAppli,
        Frame frmParent)
    {
        String strMethod = UtilKstJceks._f_s_strClass + "." + "s_showFile(...)";
        
        File fleOpenKst = com.google.code.p.keytooliui.ktl.io.S_FileChooserUI.s_getOpenFile(
            strTitleAppli, 
            frmParent, 
            "select",
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strsKstJceks, 
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strFileDescKstJceks,
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
        
        /*
        // ----
        // get password
        
        char[] chrsPasswdKst = null;
        
        
        
        // open up a passwordOpen dialog
                
        DPasswordOpen dlgPasswordKst = null;
        
        // ---
        
        dlgPasswordKst = new DPasswordOpen(
            frmParent, 
            strTitleAppli);
                    
        String strTitleSuffixDlg = " ";
        strTitleSuffixDlg += "for";
        strTitleSuffixDlg += " ";
        strTitleSuffixDlg += UtilKstJceks.f_s_strKeystoreType;
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
        dlgPasswordKst = null;*/

        // ----
        UtilKstJceks._s_showFile_(strTitleAppli, frmParent, fleOpenKst/*, chrsPasswdKst*/);
    }
    
    static public void s_showFile(
        String strTitleAppli,
        Frame frmParent,
        String strPathAbs/*,
        char[] chrsPasswdKst*/)
    {
        String strMethod = UtilKstJceks._f_s_strClass + "." + "s_showFile(...)";
        
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
        
        UtilKstJceks._s_showFile_(strTitleAppli, frmParent, fleOpenKst/*, chrsPasswdKst*/); 
    }
    
    // ----
    
    static protected void _s_showFile_(
        String strTitleAppli,
        Frame frmParent,
        File fle/*,
        char[] chrsPassword*/)
    {
        String strMethod = UtilKstJceks._f_s_strClass + "." + "_s_showFile_(...)";
            
        if (fle==null /*|| chrsPassword==null*/)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
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
        
        // ----
        // open keystore
        
        
        KeyStore kstOpen = UtilKstJceks.s_getKeystoreOpen(
            frmParent, strTitleAppli,
            fle,
            // chrsPassword
            null
                );
        
        if (kstOpen == null)
        {
            MySystem.s_printOutError(strMethod, "nil kstOpen");
            
            // don't show error dialog
            
            return;
        }
        
        UtilKstAbs._s_showKstOpen_(strTitleAppli, frmParent, kstOpen, fle.getAbsolutePath());
    }                   
    
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
            UtilKstJceks.f_s_strKeystoreType,
            UtilKstJceks.f_s_strKeystoreProvider);
    }
    
    /**
        if any code or config error, exiting
        else if any other type of error, show warning/error dialog, then return null
        
        
    **/
    /**static public Key s_getKey(
        Component cmpOwner, 
        String strTitleAppli,
        KeyStore kseLoaded,
        String strAliasKpr,
        char[] chrsPasswdKpr
        )
    {
        return UtilKstAbs._s_getKey_(
            cmpOwner, 
            strTitleAppli,
            kseLoaded,
            strAliasKpr,
            chrsPasswdKpr);
    }**/
    
    
    
    
    static public KeyStore s_getKeystoreNew(
        Frame frmOwner, 
        String strTitleAppli
        )
    {
        return UtilKstAbs._s_getKeystoreNew_(
            frmOwner,
            strTitleAppli,
            UtilKstJceks.f_s_strKeystoreType,
            UtilKstJceks.f_s_strKeystoreProvider);     
    }
    
    // --------------
    // STATIC PRIVATE
    
    
    
    
}