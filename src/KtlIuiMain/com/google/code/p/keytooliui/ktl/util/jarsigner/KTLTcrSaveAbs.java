package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    
    
    known subclasses:
    . KTLTcrSaveNewAbs

**/


import java.awt.*;

abstract public class KTLTcrSaveAbs extends KTLTcrAbs
{
    // ---------
    // PROTECTED
    
    protected KTLTcrSaveAbs(
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