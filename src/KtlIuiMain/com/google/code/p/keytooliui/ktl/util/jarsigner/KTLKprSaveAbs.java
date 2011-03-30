package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Kpr" for "keypair"
    
    
    known subclasses:
    . KTLKprSaveNewAbs
    . KTLKprSaveFromPkcs12Abs  

**/


import java.awt.*;

abstract public class KTLKprSaveAbs extends KTLKprAbs
{
    // ---------
    // PROTECTED
    
    protected KTLKprSaveAbs(
        Frame frmOwner, 
        String strTitleAppli,
        
        // input
        String strPathAbsOpenKstTarget, // existing keystore of type [JKS-JCEKS-PKCS12-BKS-UBER] 
        char[] chrsPasswdOpenKstTarget,
        
        String strProviderKstTarget
        )
    {
        super(frmOwner, strTitleAppli, strPathAbsOpenKstTarget, chrsPasswdOpenKstTarget, strProviderKstTarget);
    }
    
}