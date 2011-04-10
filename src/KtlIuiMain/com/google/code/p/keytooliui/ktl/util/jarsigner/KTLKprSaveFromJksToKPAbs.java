package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    known subclasses:
    . KTLKprSaveFromJksToKPJks ("KP" for "Key with Password")
    . KTLKprSaveFromJksToKPJceks
    . KTLKprSaveFromJksToKPBks
    . KTLKprSaveFromJksToKPUber
     
    
    !!!!!!!!! not done yet: overwritting existing KeyPair entry

**/




// ----
// --
// ----

import java.awt.*;


abstract public class KTLKprSaveFromJksToKPAbs extends KTLKprSaveFromJksToAbs
{
    // ---------
    // PROTECTED
    
    
    
    
    protected KTLKprSaveFromJksToKPAbs(
        Frame frmOwner, 
      
        
        // 
        String strPathAbsOpenKstTarget, // existing keystore of type [JKS-JCEKS-PKCS12-BKS-UBER] 
        char[] chrsPasswdOpenKstTarget,
        
        String strPathAbsKstSource, // keystore of type JKS 
        char[] chrsPasswdKstSource,
        
        String strProviderKstTarget
        )
    {
        super(
            frmOwner, 
       
            strPathAbsOpenKstTarget, // existing keystore of type [JKS-JCEKS-PKCS12-BKS-UBER] 
            chrsPasswdOpenKstTarget,
            strPathAbsKstSource, // keystore of type JKS 
            chrsPasswdKstSource,
            strProviderKstTarget,
            true // blnIsPasswdKprTarget
            );
            
    }
    
    // -------
    // PRIVATE
    
}