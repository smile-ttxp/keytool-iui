package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    "UtilKstJks" ==> "Utility, keystore of type JKS (Java keystore)"
**/

import com.google.code.p.keytooliui.ktl.swing.dialog.*;
import com.google.code.p.keytooliui.ktl.io.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.swing.dialog.*;
import com.google.code.p.keytooliui.shared.io.*;

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


final public class UtilKstAll extends UtilKstAbs
{
    // --------------------
    // final static private
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstAll";
    
    // -------------
    // static public
    
    
    
    static public void s_manageFile(
        String strTitleAppli,
        Frame frmParent)
    {
        String strMethod = UtilKstAll._f_s_strClass + "." + "s_manageFile(...)";
        
        File fleOpenKst = S_FileChooserUI.s_getOpenFile(
            strTitleAppli, 
            frmParent, 
            "select",
            S_FileExtensionUI.s_getKstAll(), 
            S_FileExtensionUI.f_s_strFileDescKstAll,
            S_FileExtensionUI.f_s_strDirNameDefaultKst
            );
            
        if (fleOpenKst == null)
        {
            MySystem.s_printOutTrace(strMethod, "nil fleOpenKst");
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
        
        String strPathAbsLC =  fleOpenKst.getAbsolutePath().toLowerCase();
        
        // ----
        
        KeyStore kstOpen = null;
        String strProviderKst = null;
        
        if (S_FileExtensionUI.s_isKstJks(fleOpenKst.getAbsolutePath()))
        {
            kstOpen = UtilKstJks.s_getKeystoreOpen(
                frmParent, strTitleAppli,
                fleOpenKst,
                chrsPasswdKst);
                
            strProviderKst = UtilKstJks.f_s_strKeystoreProvider;
        }
        
        else if (S_FileExtensionUI.s_isKstJceks(fleOpenKst.getAbsolutePath()))
        {
            kstOpen = UtilKstJceks.s_getKeystoreOpen(
                frmParent, strTitleAppli,
                fleOpenKst,
                chrsPasswdKst);
                
            strProviderKst = UtilKstJceks.f_s_strKeystoreProvider;
        }
        
        else if (S_FileExtensionUI.s_isKstPkcs12(fleOpenKst.getAbsolutePath()))
        {
            kstOpen = UtilKstPkcs12.s_getKeystoreOpen(
                frmParent, strTitleAppli,
                fleOpenKst,
                chrsPasswdKst);
                
            strProviderKst = UtilKstPkcs12.f_s_strKeystoreProvider;
        }
        
        else if (S_FileExtensionUI.s_isKstBks(fleOpenKst.getAbsolutePath()))
        {
            kstOpen = UtilKstBks.s_getKeystoreOpen(
                frmParent, strTitleAppli,
                fleOpenKst,
                chrsPasswdKst);
                
            strProviderKst = UtilKstBks.f_s_strKeystoreProvider;
        }
        
        else if (S_FileExtensionUI.s_isKstUber(fleOpenKst.getAbsolutePath()))
        {
            kstOpen = UtilKstUber.s_getKeystoreOpen(
                frmParent, strTitleAppli,
                fleOpenKst,
                chrsPasswdKst);
                
            strProviderKst = UtilKstUber.f_s_strKeystoreProvider;
        }
        
        else
        {
            // sad ending, isn't it!
            MySystem.s_printOutExit(strMethod, "wrong keystore extension, fleOpenKst.getAbsolutePath()=" + fleOpenKst.getAbsolutePath());
        }
        
        // ----
        
        if (kstOpen == null)
        {
            MySystem.s_printOutWarning(strMethod, "nil kstOpen");
            // don't show error dialog
            return;
        }
        
        UtilKstAbs._s_manageKstOpen_(
            strTitleAppli, 
            frmParent, 
            kstOpen,
            fleOpenKst.getAbsolutePath(),
            chrsPasswdKst,
            strProviderKst);
  
    }
    
    
    static public void s_showFile(
        String strTitleAppli,
        Frame frmParent)
    {
        String strMethod = UtilKstAll._f_s_strClass + "." + "s_showFile(...)";
        
        File fleOpenKst = S_FileChooserUI.s_getOpenFile(
            strTitleAppli, 
            frmParent, 
            "select",
            S_FileExtensionUI.s_getKstAll(), 
            S_FileExtensionUI.f_s_strFileDescKstAll,
            S_FileExtensionUI.f_s_strDirNameDefaultKst
            );
            
        if (fleOpenKst == null)
        {
            MySystem.s_printOutTrace(strMethod, "nil fleOpenKst");
            return;
        }
        
        

        
        if (S_FileExtensionUI.s_isKstJks(fleOpenKst.getAbsolutePath()))
        {
            UtilKstJks._s_showFile_(strTitleAppli, frmParent, fleOpenKst);
            return;
        }
        
        if (S_FileExtensionUI.s_isKstJceks(fleOpenKst.getAbsolutePath()))
        {
            UtilKstJceks._s_showFile_(strTitleAppli, frmParent, fleOpenKst);
            return;
        }
        
        if (S_FileExtensionUI.s_isKstPkcs12(fleOpenKst.getAbsolutePath()))
        {     
            char[] chrsPasswdKst = UtilKstAll._s_getPassword(frmParent, strTitleAppli);
            
            if (chrsPasswdKst == null)
                return;
            
            UtilKstPkcs12._s_showFile_(strTitleAppli, frmParent, fleOpenKst, chrsPasswdKst);
            return;
        }
        
        if (S_FileExtensionUI.s_isKstBks(fleOpenKst.getAbsolutePath()))
        {
            UtilKstBks._s_showFile_(strTitleAppli, frmParent, fleOpenKst);
            return;
        }
        
        if (S_FileExtensionUI.s_isKstUber(fleOpenKst.getAbsolutePath()))
        {
            char[] chrsPasswdKst = UtilKstAll._s_getPassword(frmParent, strTitleAppli);
            
            if (chrsPasswdKst == null)
                return;
            
            UtilKstUber._s_showFile_(strTitleAppli, frmParent, fleOpenKst, chrsPasswdKst);
            return;
        }
        
        // yuk yuk
        // sad ending, isn't it!
        MySystem.s_printOutExit(strMethod, "wrong keystore extension, fleOpenKst.getAbsolutePath()=" + fleOpenKst.getAbsolutePath());
    }
    
    // --------------
    // static private
    
    static private char[] _s_getPassword(Frame frmParent, String strTitleAppli)
    {
        String strMethod = "UtilKstAll._s_getPassword(...)";
        
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
        strTitleSuffixDlg += "keystore";
                
                
        dlgPasswordKst.setTitle(dlgPasswordKst.getTitle() + strTitleSuffixDlg);
                    
        if (! dlgPasswordKst.init())
            MySystem.s_printOutExit(strMethod, "failed");
                    
        dlgPasswordKst.setVisible(true);
                
        chrsPasswdKst = dlgPasswordKst.getPassword();
                
        if (chrsPasswdKst == null)
        {
            MySystem.s_printOutTrace(strMethod, "nil chrsPasswdKst, user canceled");
            return null;
        }
                
                
        dlgPasswordKst.destroy();
        dlgPasswordKst = null;
        
        return chrsPasswdKst;
    }
}