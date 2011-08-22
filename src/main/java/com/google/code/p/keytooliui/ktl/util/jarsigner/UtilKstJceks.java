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


public final class UtilKstJceks extends UtilKstAbs
{
    // -------------------
    // PUBLIC STATIC FINAL
    
    public static final String f_s_strKeystoreType = "JCEKS"; // default type
    public static final String f_s_strKeystoreProvider = "SunJCE"; // default type
    
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _f_s_strClass = "com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJceks";

    // -------------
    // PUBLIC STATIC
    
    public static void s_manageFile(
        Frame frmParent)
    {
        String strMethod = UtilKstJceks._f_s_strClass + "." + "s_manageFile(...)";
        
        File fleOpenKstJceks = com.google.code.p.keytooliui.ktl.io.S_FileChooserUI.s_getOpenFile(
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
                frmParent, strBody);
                
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
                frmParent, strBody);
                
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
            frmParent,
            fleOpenKstJceks,
            chrsPasswdKst);
        
        if (kstOpen == null)
        {
            MySystem.s_printOutError(strMethod, "nil kstOpen");
            
            // don't show error dialog
            
            return;
        }
        
        UtilKstAbs._s_manageKstOpen_( 
            frmParent, 
            kstOpen,
            fleOpenKstJceks.getAbsolutePath(),
            chrsPasswdKst,
            UtilKstJceks.f_s_strKeystoreProvider);
    }
    
    public static void s_showFile(
        Frame frmParent)
    {
        String strMethod = UtilKstJceks._f_s_strClass + "." + "s_showFile(...)";
        
        File fleOpenKst = com.google.code.p.keytooliui.ktl.io.S_FileChooserUI.s_getOpenFile(
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
                frmParent, strBody);
                
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
                frmParent, strBody);
                
            return;
        }
        

        // ----
        UtilKstJceks._s_showFile_(frmParent, fleOpenKst/*, chrsPasswdKst*/);
    }
    
    public static void s_showFile(
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
                frmParent, strBody);
                
            return;
        }
        
        UtilKstJceks._s_showFile_(frmParent, fleOpenKst/*, chrsPasswdKst*/); 
    }
    
    // ----
    
    protected static void _s_showFile_(
        Frame frmParent,
        File fle/*,
        char[] chrsPassword*/)
    {
        String strMethod = UtilKstJceks._f_s_strClass + "." + "_s_showFile_(...)";
            
        if (fle==null /*|| chrsPassword==null*/)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            String strBody = "Failed to load file.";            
            OPAbstract.s_showDialogError(frmParent, strBody);
                
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
                frmParent, strBody);
                
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
                frmParent, strBody);
                
            return;
        }
        
        // ----
        // open keystore
        
        
        KeyStore kstOpen = UtilKstJceks.s_getKeystoreOpen(
            frmParent,
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
        
        UtilKstAbs._s_showKstOpen_(frmParent, kstOpen, fle.getAbsolutePath());
    }                   
    
    public static KeyStore s_getKeystoreOpen(
        Frame frmOwner, 
        File fleOpen,
        char[] chrsPassword // nil value allowed (eg: verify signed jarred file)
        )
    {
        return UtilKstAbs._s_getKeystoreOpen_(
            frmOwner,
            fleOpen,
            chrsPassword,
            UtilKstJceks.f_s_strKeystoreType,
            UtilKstJceks.f_s_strKeystoreProvider);
    }
    
    
    
    
    public static KeyStore s_getKeystoreNew(
        Frame frmOwner
        )
    {
        return UtilKstAbs._s_getKeystoreNew_(
            frmOwner,
            UtilKstJceks.f_s_strKeystoreType,
            UtilKstJceks.f_s_strKeystoreProvider);     
    }
    
    // --------------
    // PRIVATE STATIC
    
    
    
    
}