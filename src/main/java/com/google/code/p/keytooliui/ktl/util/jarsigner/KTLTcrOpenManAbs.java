package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Tcr" for "Trusted certificate"
    
    
    known subclasses:
    . KTLTcrOpenManChgAlias
    . KTLTcrOpenManDelete (Entry)

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

public abstract class KTLTcrOpenManAbs extends KTLTcrOpenAbs
{
    // ------
    // PUBLIC
    
    public KeyStore getKstOpen() { return this._kstOpen_; } // changed and saved
    
    public boolean doJob()
    {
        String strMethod = "doJob()";
                
        if (this._strAlias_ == null)
            MySystem.s_printOutExit(this, strMethod, "nil this._strAlias_");
            
        // check for an exiting trusted certificate entry
        
        try
        {
            if (! this._kstOpen_.isCertificateEntry(this._strAlias_))
            {
                MySystem.s_printOutError(this, strMethod, "! this._kstOpen_.isCertificateEntry(this._strAlias_)");
                
                String strBody = "Alias not pointing to valid trusted certificate entry:\n  " + this._strAlias_;
            
                OPAbstract.s_showDialogWarning(
                    super._frmOwner_, 
                  
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
        
                strBody);
                
                
            return false;
        }
     
        return true;
    }
    
    // ---------
    // PROTECTED

    protected KeyStore _kstOpen_ = null;
    protected String _strAlias_ = null;
    
    protected KTLTcrOpenManAbs(
        Frame frmOwner, 

        
        // input
        String strPathAbsOpenKst, // existing keystore 
        char[] chrsPasswdOpenKst,
        String strProviderKst,
        
        KeyStore kstOpen,
        String strAlias
        )
    {
        super(frmOwner, strPathAbsOpenKst, chrsPasswdOpenKst, strProviderKst);
        
        this._kstOpen_ = kstOpen;
        this._strAlias_ = strAlias;
    }
}