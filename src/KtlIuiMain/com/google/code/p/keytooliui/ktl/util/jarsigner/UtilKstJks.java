package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    "UtilKstJks" ==> "Utility, keystore of type JKS (Java keystore)"
**/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.swing.dialog.*;
import com.google.code.p.keytooliui.shared.io.*;

// ----
import java.security.KeyStore;
// --
// ----


import java.io.*;
import java.awt.*;


final public class UtilKstJks extends UtilKstAbs
{
    // -------------------
    // FINAL STATIC PUBLIC
    
    final static public String f_s_strKeystoreType = "JKS"; // default type
    final static public String f_s_strKeystoreProvider = "SUN"; // default type
    
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJks";
    
    // "changeit"
    /*final static private char[] _f_s_chrsPasswdCacertsDefault = 
    {
        'c', 'h', 'a', 'n', 'g', 'e', 'i', 't'
    };*/
    
    final static private String _f_s_strNameKstCertsCASys = "cacerts"; // memo, default passwd: "changeit"
    
    final static private String[] _f_s_strsDirKstCertsCASys =
    {
        "lib",
        "security"
    };
    
    final static private String _f_s_strNameKstCertsTrustUsr = "trusted.certs"; // memo, default passwd: ""
    
    // eg: C:\Documents and Settings\jsmith\Application Data\Sun\Java\Deployment\security\
    final static private String[] _f_s_strsDirKstCertsTrustUsrWinUS =
    {
        "Application Data", // TBRL, eg: French: "Donn�es d'applications"
        "Sun",
        "Java",
        "Deployment",
        "security"
    };
    
    final static private String[] _f_s_strsDirKstCertsTrustUsrWinFR =
    {
        "Donn�es d'applications", // TBRL, eg: French: "Donn�es d'applications"
        "Sun",
        "Java",
        "Deployment",
        "security"
    };
    
    // Unix & Linux
    // eg: /home/bjones/.java/deployment/deployment.properties\security\
    final static private String[] _f_s_strsDirKstCertsTrustUsrUnix =
    {
        ".java",
        "deployment",
        "security"
    };

    // -------------
    // STATIC PUBLIC
    
    static public void s_manageFile(
        Frame frmParent)
    {
        String strMethod = UtilKstJks._f_s_strClass + "." + "s_manageFile(...)";
        
        File fleOpenKst = com.google.code.p.keytooliui.ktl.io.S_FileChooserUI.s_getOpenFile(
            frmParent, 
            "select",
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strsKstJks, 
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strFileDescKstJks,
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
            MySystem.s_printOutError(strMethod, "! fleOpenKst.canRead(), fleOpenKst.getAbsolutePath()=" + 
                fleOpenKst.getAbsolutePath());
            
            String strBody = "File cannot be read:";
            
            strBody += "\n\n";
            strBody += "  ";
            strBody += fleOpenKst.getAbsolutePath();
            
                
            OPAbstract.s_showDialogError(
                frmParent, strBody);
                
            return;
        }
        
        // !!!!!!! what about "fleOpenKst.canWrite()""
        
        UtilKstJks._s_manageFileOpen(frmParent, fleOpenKst);
   
    }
    
    static private void _s_manageFileOpen(
        Frame frmParent,
        File fleOpenKst)
    {
        String strMethod = UtilKstJks._f_s_strClass + "." + "_s_manageFileOpen(...)";
        
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
        strTitleSuffixDlg += UtilKstJks.f_s_strKeystoreType;
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
        
        
        KeyStore kstOpen = UtilKstJks.s_getKeystoreOpen(
            frmParent,
            fleOpenKst,
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
            fleOpenKst.getAbsolutePath(),
            chrsPasswdKst,
            UtilKstJks.f_s_strKeystoreProvider);
    }
    
    
    
    
    static public void s_showFile(
        Frame frmParent,
        String strPathAbs)
    {
        String strMethod = UtilKstJks._f_s_strClass + "." + "s_showFile(...)";
        
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
        
        UtilKstJks._s_showFile_(frmParent, fleOpenKst/*, chrsPasswdKst*/); 
    }
    
    static public void s_showFile(
        Frame frmParent)
    {
        String strMethod = UtilKstJks._f_s_strClass + "." + "s_showFile(...)";
        
        File fleOpenKst = com.google.code.p.keytooliui.ktl.io.S_FileChooserUI.s_getOpenFile(
            frmParent, 
            "select",
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strsKstJks, 
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strFileDescKstJks,
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strDirNameDefaultKst
            );
            
        if (fleOpenKst == null)
        {
            MySystem.s_printOutTrace(strMethod, "nil fleOpenKst");
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
            frmParent);
                    
        String strTitleSuffixDlg = " ";
        strTitleSuffixDlg += "for";
        strTitleSuffixDlg += " ";
        strTitleSuffixDlg += UtilKstJks.f_s_strKeystoreType;
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
          */  
        UtilKstJks._s_showFile_(frmParent, fleOpenKst/*, chrsPasswdKst*/);
    }
    
    static public void s_manageFileKstCertsTrustSys(
        Frame frmParent)
    {
        String strMethod = UtilKstJks._f_s_strClass + "." + "s_manageFileKstCertsTrustSys(...)";

        // ----
        // open keystore
        
        
        KeyStore kstOpen = UtilKstJks.s_getKstOpenCertsTrustCASys(
            frmParent,       
            true // blnShowDialogError
            );
        
        if (kstOpen == null)
        {
            MySystem.s_printOutError(strMethod, "nil kstOpen");
    
            // don't show error dialog        
            return;
        }
        
        File fleOpen = UtilKstJks.s_getFileKstCertsTrustCASys();
        
        if (fleOpen == null)
        {
            MySystem.s_printOutError(strMethod, "nil kstOpen");
    
            // don't show error dialog        
            return;
        }
        
        // !!!!!!! what about "fleOpenKst.canWrite()""
        
        UtilKstJks._s_manageFileOpen(frmParent, fleOpen);
        
    }
    
    static public void s_showFileKstCertsTrustSys(
        Frame frmParent)
    {
        String strMethod = UtilKstJks._f_s_strClass + "." + "s_showFileKstCertsTrustSys(...)";

        // ----
        // open keystore
        
        
        KeyStore kstOpen = UtilKstJks.s_getKstOpenCertsTrustCASys(
            frmParent,       
            true // blnShowDialogError
            );
        
        if (kstOpen == null)
        {
            MySystem.s_printOutError(strMethod, "nil kstOpen");
            
            
            // don't show error dialog
            
            return;
        }
        File fleOpen = UtilKstJks.s_getFileKstCertsTrustCASys();
        
        if (fleOpen != null)
            UtilKstAbs._s_showKstOpen_(frmParent, kstOpen, fleOpen.getAbsolutePath());
    }
    
    static public void s_showFileKstCertsTrustUsr(
        Frame frmParent)
    {
        String strMethod = UtilKstJks._f_s_strClass + "." + "s_showFileKstCertsTrustUsr(...)";

        // ----
        // open keystore
        
        
        KeyStore kstOpen = UtilKstJks.s_getKstOpenCertsTrustUsr(
            frmParent,       
            true // blnShowDialogError
            );
        
        if (kstOpen == null)
        {
            MySystem.s_printOutError(strMethod, "nil kstOpen");
            
            
            // don't show error dialog
            
            
            return;
        }
        
        File fleOpen = UtilKstJks.s_getFileKstCertsTrustUsr();
        
        if (fleOpen != null)
            UtilKstAbs._s_showKstOpen_(frmParent, kstOpen, fleOpen.getAbsolutePath());
    }
    
    static public void s_manageFileKstCertsTrustUsr(
        Frame frmParent)
    {
        String strMethod = UtilKstJks._f_s_strClass + "." + "s_manageFileKstCertsTrustUsr(...)";
        
        java.io.File fleCertsTrustUsr = 
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJks.s_getFileKstCertsTrustUsr();
        
        if (fleCertsTrustUsr == null)
        {
            MySystem.s_printOutError(strMethod, "failed to get file");
            // TODO: ask for path of "trusted.certs"
            
            
            // --
            return;
        }
        
        String strPathAbsOpenKst = fleCertsTrustUsr.getAbsolutePath();
        char[] chrsPasswdOpenKst = new char[0];
        String strKeystoreProvider = "JKS";

        // ----
        // open keystore
        
        
        KeyStore kstOpen = UtilKstJks.s_getKstOpenCertsTrustUsr(
            frmParent,        
            true // blnShowDialogError
            );
        
        if (kstOpen == null)
        {
            MySystem.s_printOutError(strMethod, "nil kstOpen");
            
            
            // don't show error dialog
            
            
            return;
        }
        
        UtilKstAbs._s_manageKstOpen_(frmParent, kstOpen,
                strPathAbsOpenKst, chrsPasswdOpenKst, strKeystoreProvider);
    }
    
     static public KeyStore s_getKstOpenCertsTrustCASys(
        Frame frmOwner,       
        boolean blnShowDialogError
        )
    {
        String strMethod = UtilKstJks._f_s_strClass + "." + "s_getKstOpenCertsTrustCASys()";
        
        File fleOpen = UtilKstJks.s_getFileKstCertsTrustCASys();
        
        if (fleOpen == null)
        {
            MySystem.s_printOutError(strMethod, "failed");
            
            if (! blnShowDialogError)
                return null;
        
            String strBody = "Failed to get keystore containing system's trusted CA certs.";
            
            strBody += "\n\n";
            strBody += "See log file to get more info.";
            
                
            OPAbstract.s_showDialogError(
                frmOwner, strBody);
                
            return null;
        }
        
        return UtilKstAbs._s_getKeystoreOpen_(
            frmOwner,
            fleOpen,
            null, // chrsPassword,
            UtilKstJks.f_s_strKeystoreType,
            UtilKstJks.f_s_strKeystoreProvider);
    }
    
    static public KeyStore s_getKstOpenCertsTrustUsr(
        Frame frmOwner,       
        boolean blnShowDialogError
        )
    {
        String strMethod = UtilKstJks._f_s_strClass + "." + "s_getKstOpenCertsTrustUsr()";
        
        File fleOpen = UtilKstJks.s_getFileKstCertsTrustUsr();
        
        if (fleOpen == null)
        {
            MySystem.s_printOutError(strMethod, "failed");
            
            if (! blnShowDialogError)
                return null;
        
            String strBody = "Failed to get keystore containing user's trusted certs.";
            
            strBody += "\n\n";
            strBody += "See log file to get more info.";
            
                
            OPAbstract.s_showDialogError(
                frmOwner, strBody);
                
            return null;
        }
        
        return UtilKstAbs._s_getKeystoreOpen_(
            frmOwner,
            fleOpen,
            null, // chrsPassword,
            UtilKstJks.f_s_strKeystoreType,
            UtilKstJks.f_s_strKeystoreProvider);
    }
          
                       
    
    static public KeyStore s_getKeystoreOpen(
        Frame frmOwner, 
        File fleOpen,
        char[] chrsPassword // nil value allowed (eg: verify signed jarred file)
        )
    {
        return UtilKstAbs._s_getKeystoreOpen_(
            frmOwner,
            fleOpen,
            chrsPassword,
            UtilKstJks.f_s_strKeystoreType,
            UtilKstJks.f_s_strKeystoreProvider);
    }
    
    
    static public KeyStore s_getKeystoreNew(
        Frame frmOwner
        )
    {
        return UtilKstAbs._s_getKeystoreNew_(
            frmOwner,
            UtilKstJks.f_s_strKeystoreType,
            UtilKstJks.f_s_strKeystoreProvider);     
    }
    
    
    // memo:
    // cacerts, keystore of type JKS, password: "changeit"
    static public File s_getFileKstCertsTrustCASys()
    {
        String strMethod = UtilKstJks._f_s_strClass + "." + "s_getFileKstCertsTrustCASys()";
        File fleKeystore = null;
        
        try
        {
            String strDirHome = System.getProperty("java.home");
            
            String strPathAbs = strDirHome;
            
            for (int i=0; i<UtilKstJks._f_s_strsDirKstCertsCASys.length; i++)
            {
                strPathAbs += File.separator;
                strPathAbs += UtilKstJks._f_s_strsDirKstCertsCASys[i];
            }
            
            strPathAbs += File.separator;
            strPathAbs += UtilKstJks._f_s_strNameKstCertsCASys;
            
       
            fleKeystore = new File(strPathAbs);
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(strMethod, "exc caught");
            return null;
        }
        
        if (! fleKeystore.exists())
        {
            MySystem.s_printOutError(strMethod, "! fleKeystore.exists(), fleKeystore.getAbsolutePath()=" + fleKeystore.getAbsolutePath());
            return null;
        }
        
        if (fleKeystore.isDirectory())
        {
            MySystem.s_printOutError(strMethod, "fleKeystore.isDirectory(), fleKeystore.getAbsolutePath()=" + fleKeystore.getAbsolutePath());
            return null;
        }
        
        if (! fleKeystore.canRead())
        {
            MySystem.s_printOutError(strMethod, "! canRead.exists(), fleKeystore.getAbsolutePath()=" + fleKeystore.getAbsolutePath());
            return null;
        }
        
        return fleKeystore;
    }
    
    // OS is Windows
    static private File _s_getFileKstCertsTrustUsrFrWindows() // "Fr": French
    {
        String strMethod = UtilKstJks._f_s_strClass + "." + "_s_getFileKstCertsTrustUsrFrWindows()";
        File fleKeystore = null;
        
        
        try
        {
            String strDirHome = System.getProperty("user.home");
            
            String strPathAbs = strDirHome;
            
            String[] strs = UtilKstJks._f_s_strsDirKstCertsTrustUsrWinFR;

            for (int i=0; i<strs.length; i++)
            {
                strPathAbs += File.separator;
                strPathAbs += strs[i];
            }
      
            strPathAbs += File.separator;
            strPathAbs += UtilKstJks._f_s_strNameKstCertsTrustUsr;
            
            fleKeystore = new File(strPathAbs);
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(strMethod, "exc caught");
            return null;
        }
        
        if (! fleKeystore.exists())
        {
            MySystem.s_printOutTrace(strMethod, "! fleKeystore.exists(), fleKeystore.getAbsolutePath()=" + fleKeystore.getAbsolutePath());
            return null;
        }
        
        if (fleKeystore.isDirectory())
        {
            MySystem.s_printOutWarning(strMethod, "fleKeystore.isDirectory(), fleKeystore.getAbsolutePath()=" + fleKeystore.getAbsolutePath());
            return null;
        }
        
        if (! fleKeystore.canRead())
        {
            MySystem.s_printOutWarning(strMethod, "! canRead.exists(), fleKeystore.getAbsolutePath()=" + fleKeystore.getAbsolutePath());
            return null;
        }
        
        return fleKeystore;
    }
    
    // 
    // memo:
    // trusted.certs, keystore of type JKS, password: "", trusted by user
    
    // if Unix or Linux or Windows, OK, else: returns NIL
    // more: http://java.sun.com/j2se/1.5.0/docs/guide/deployment/deployment-guide/properties.html
    static public File s_getFileKstCertsTrustUsr()
    {
        String strMethod = UtilKstJks._f_s_strClass + "." + "s_getFileKstCertsTrustUsr()";
        File fleKeystore = null;
        
        
        try
        {
            String strDirHome = System.getProperty("user.home");
            
            String strPathAbs = strDirHome;
            
            if (S_OperatingSystem.s_isWindows())
            {
                String strUserLanguage = System.getProperty("user.language");
                
                if (strUserLanguage == null)
                {
                    MySystem.s_printOutTrace(strMethod, "nil strUserLanguage");
                    return null;
                }
                
                if (strUserLanguage.toLowerCase().compareTo("fr") == 0)
                {
                    File fle = UtilKstJks._s_getFileKstCertsTrustUsrFrWindows();
                    
                    if (fle != null)
                        return fle;
                }
                
                String[] strs = null;
                
                
                
                if (strUserLanguage.toLowerCase().compareTo("us") == 0 ||
                    strUserLanguage.toLowerCase().compareTo("en") == 0 ||
                    strUserLanguage.toLowerCase().compareTo("fr") == 0)
                {
                    strs = UtilKstJks._f_s_strsDirKstCertsTrustUsrWinUS;
                }
                
                else
                {
                    MySystem.s_printOutTrace(strMethod, "uncaught strUserLanguage:" + strUserLanguage +", ignoring");
                    return null;
                }
                
                
                for (int i=0; i<strs.length; i++)
                {
                    strPathAbs += File.separator;
                    strPathAbs += strs[i];
                }
            }
            
            else if (S_OperatingSystem.s_isUnix()) // either unix or linux
            {
                for (int i=0; i<UtilKstJks._f_s_strsDirKstCertsTrustUsrUnix.length; i++)
                {
                    strPathAbs += File.separator;
                    strPathAbs += UtilKstJks._f_s_strsDirKstCertsTrustUsrUnix[i];
                }
            }
            
            else // don't know
            {
                MySystem.s_printOutWarning(strMethod, "OS not supported, returning NIL");
                return null;
            } 
            
            strPathAbs += File.separator;
            strPathAbs += UtilKstJks._f_s_strNameKstCertsTrustUsr;
            
            fleKeystore = new File(strPathAbs);
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(strMethod, "exc caught");
            return null;
        }
        
        if (! fleKeystore.exists())
        {
            MySystem.s_printOutTrace(strMethod, "! fleKeystore.exists(), fleKeystore.getAbsolutePath()=" + fleKeystore.getAbsolutePath());
            return null;
        }
        
        if (fleKeystore.isDirectory())
        {
            MySystem.s_printOutError(strMethod, "fleKeystore.isDirectory(), fleKeystore.getAbsolutePath()=" + fleKeystore.getAbsolutePath());
            return null;
        }
        
        if (! fleKeystore.canRead())
        {
            MySystem.s_printOutError(strMethod, "! canRead.exists(), fleKeystore.getAbsolutePath()=" + fleKeystore.getAbsolutePath());
            return null;
        }
        
        return fleKeystore;
    }
    
    // ----------------
    // STATIC PROTECTED
    
    static protected void _s_showFile_(
        Frame frmParent,
        File fle)
    {
        String strMethod = UtilKstJks._f_s_strClass + "." + "_s_showFile_(...)";
        
        
        if (fle==null/* || chrsPasswdKst==null*/)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            
            String strBody = "Failed to load file.";            
                
            OPAbstract.s_showDialogError(
                frmParent, strBody);
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
                
        KeyStore kstOpen = UtilKstJks.s_getKeystoreOpen(
            frmParent,
            fle,
            null
            //chrsPasswdKst
                );
        
        if (kstOpen == null)
        {
            MySystem.s_printOutError(strMethod, "nil kstOpen");
            
            // don't show error dialog
            
            return;
        }
        
        UtilKstAbs._s_showKstOpen_(frmParent, kstOpen, fle.getAbsolutePath());
    }
    
    
    
}