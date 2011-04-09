package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    
    
    known subclasses:
    . KTLTcrSaveNewAbs

**/


import java.awt.*;

public abstract class KTLTcrSaveAbs extends KTLTcrAbs
{
    // ---------
    // PROTECTED
    
    protected KTLTcrSaveAbs(
        Frame frmOwner, 
    
        
        // input
        String strPathAbsOpenKstTarget, // existing keystore of type [JKS-JCEKS-PKCS12-BKS-UBER] 
        char[] chrsPasswdOpenKstTarget,
        
        String strProviderKstTarget
        )
    {
        super(frmOwner,  strPathAbsOpenKstTarget, chrsPasswdOpenKstTarget, strProviderKstTarget);
    }
    
}