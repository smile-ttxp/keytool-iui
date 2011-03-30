package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Kpr" for "keypair"
    
    
    known subclasses:
    . KTLKprOpenManChgAlias
    . KTLKprOpenManChgPasswd
    . KTLKprOpenManDelete (Entry)
    . KTLKprOpenManCopy (Entry)

**/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.swing.dialog.*;

// ----
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.security.KeyStoreException;
import java.security.cert.Certificate;
// ----


import java.awt.*;

abstract public class KTLKprOpenManAbs extends KTLKprOpenAbs
{
    // ------
    // PUBLIC
    
    public KeyStore getKstOpen() { return this._kstOpen_; } // changed and saved
    
    public boolean doJob()
    {
        String strMethod = "doJob()";
                
        if (this._strAlias_ == null)
            MySystem.s_printOutExit(this, strMethod, "nil this._strAlias_");
            
        // check for an exiting keypair entry
        
        try
        {
            if (! this._kstOpen_.isKeyEntry(this._strAlias_))
            {
                MySystem.s_printOutError(this, strMethod, "! this._kstOpen_.isKeyEntry(this._strAlias_)");
                
                String strBody = "Alias not pointing to valid keypair entry:\n  " + this._strAlias_;
            
                OPAbstract.s_showDialogWarning(
                    super._frmOwner_, 
                    super._strTitleAppli_, 
                    strBody);
                
                return false;
            }
        }
        
        catch(KeyStoreException excKeyStore)
        {
            excKeyStore.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excKeyStore caught");
            
            String strBody = "Got keystore exception!";
            
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, 
                super._strTitleAppli_, 
                strBody);
                
                
            return false;
        }
     
        return true;
    }
    
    // ---------
    // PROTECTED

    protected KeyStore _kstOpen_ = null;
    protected String _strAlias_ = null;
    
    protected KTLKprOpenManAbs(
        Frame frmOwner, 
        String strTitleAppli,
        
        // input
        String strPathAbsOpenKst, // existing keystore of type JKS 
        char[] chrsPasswdOpenKst,
        String strProviderKst,
        
        KeyStore kstOpen,
        String strAlias
        )
    {
        super(frmOwner, strTitleAppli, strPathAbsOpenKst, chrsPasswdOpenKst, strProviderKst);
        
        this._kstOpen_ = kstOpen;
        this._strAlias_ = strAlias;
    }
    
    protected char[] _getPasswordKpr_()
    {
        String strMethod = "_getPasswordKpr_()";
        
        
        
        // ---
        // get password for this alias
        
        
        if (this._kstOpen_.getType().toLowerCase().compareTo(UtilKstPkcs12.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            return new char[0];
        }
            
        char[] chrsPasswdKpr = null;
    
        // open up a passwordOpen dialog
                
        DPasswordOpen dlg = new DPasswordOpen(
            super._frmOwner_, 
            super._strTitleAppli_,
            false // blnNoPasswdAllowed
                );
                    
        String strTitleSuffixDlg = " ";
        strTitleSuffixDlg += "for";
        strTitleSuffixDlg += " ";
        strTitleSuffixDlg += "alias";
        strTitleSuffixDlg += " ";
        strTitleSuffixDlg += this._strAlias_;
                
                
        dlg.setTitle(dlg.getTitle() + strTitleSuffixDlg);
                    
        if (! dlg.init())
            MySystem.s_printOutExit(this, strMethod, "failed");
                    
        dlg.setVisible(true);
                
        chrsPasswdKpr = dlg.getPassword();        
     
                
        dlg.destroy();
        dlg = null;
        
        if (chrsPasswdKpr == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil chrsPasswdKpr, user canceled");
            return null;
        }
        
        return chrsPasswdKpr;
    }
}