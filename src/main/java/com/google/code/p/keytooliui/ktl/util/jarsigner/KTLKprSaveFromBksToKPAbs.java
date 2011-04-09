package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    known subclasses:
    . KTLKprSaveFromBksToKPJks ("KP" for "Key with Password")
    . KTLKprSaveFromBksToKPJceks
    . KTLKprSaveFromBksToKPBks
    . KTLKprSaveFromBksToKPUber
     
    
    !!!!!!!!! not done yet: overwritting existing KeyPair entry

**/




// ----
// --
// ----

import java.awt.*;


public abstract class KTLKprSaveFromBksToKPAbs extends KTLKprSaveFromBksToAbs
{
    // ---------
    // PROTECTED
    
    protected KTLKprSaveFromBksToKPAbs(
        Frame frmOwner, 
    
        
        // 
        String strPathAbsOpenKstTarget, // existing keystore of type [JKS-JCEKS-PKCS12-BKS-UBER] 
        char[] chrsPasswdOpenKstTarget,
        
        String strPathAbsKstSource, // keystore of type BKS 
        char[] chrsPasswdKstSource,
        
        String strProviderKstTarget
        )
    {
        super(
            frmOwner, 
         
            strPathAbsOpenKstTarget, // existing keystore of type [JKS-JCEKS-PKCS12-BKS-UBER] 
            chrsPasswdOpenKstTarget,
            strPathAbsKstSource, // keystore of type BKS 
            chrsPasswdKstSource,
            strProviderKstTarget,
            true // blnIsPasswdKprTarget
            );
            
    }
    
    // -------
    // PRIVATE
    
}