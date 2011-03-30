package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Shk" for "keypair"
    
    
    known subclasses:
    . KTLShkSaveNewAbs
    . KTLShkSaveFromPkcs12Abs  ??

**/


import java.awt.*;

abstract public class KTLShkSaveAbs extends KTLShkAbs
{
    // ---------
    // PROTECTED
    
    protected KTLShkSaveAbs(
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
