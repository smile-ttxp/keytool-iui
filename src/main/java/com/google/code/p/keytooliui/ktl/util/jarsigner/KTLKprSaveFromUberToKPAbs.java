package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    known subclasses:
    . KTLKprSaveFromUberToKPJks ("KP" for "Key with Password")
    . KTLKprSaveFromUberToKPJceks
    . KTLKprSaveFromUberToKPBks
    . KTLKprSaveFromUberToKPUber
     
    
    !!!!!!!!! not done yet: overwritting existing KeyPair entry

**/




// ----
// --
// ----

import java.awt.*;


public abstract class KTLKprSaveFromUberToKPAbs extends KTLKprSaveFromUberToAbs
{
    // ---------
    // PROTECTED
    
    protected KTLKprSaveFromUberToKPAbs(
        Frame frmOwner, 
      
        
        // 
        String strPathAbsOpenKstTarget, // existing keystore of type [JKS-JCEKS-PKCS12-BKS-UBER] 
        char[] chrsPasswdOpenKstTarget,
        
        String strPathAbsKstSource, // keystore of type UBER 
        char[] chrsPasswdKstSource,
        
        String strProviderKstTarget
        )
    {
        super(
            frmOwner, 
      
            strPathAbsOpenKstTarget, // existing keystore of type [JKS-JCEKS-PKCS12-BKS-UBER] 
            chrsPasswdOpenKstTarget,
            strPathAbsKstSource, // keystore of type UBER 
            chrsPasswdKstSource,
            strProviderKstTarget,
            true // blnIsPasswdKprTarget
            );
            
    }
    
    // -------
    // PRIVATE
    
}