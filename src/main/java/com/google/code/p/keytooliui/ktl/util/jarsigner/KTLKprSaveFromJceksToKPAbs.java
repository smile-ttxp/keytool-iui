package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    known subclasses:
    . KTLKprSaveFromJceksToKPJks ("KP" for "Key with Password")
    . KTLKprSaveFromJceksToKPJceks
    . KTLKprSaveFromJceksToKPBks
    . KTLKprSaveFromJceksToKPUber
     
    
    !!!!!!!!! not done yet: overwritting existing KeyPair entry

**/




// ----
// --
// ----

import java.awt.*;


public abstract class KTLKprSaveFromJceksToKPAbs extends KTLKprSaveFromJceksToAbs
{
    // ---------
    // PROTECTED
    
    
    
    
    protected KTLKprSaveFromJceksToKPAbs(
        Frame frmOwner, 
      
        // 
        String strPathAbsOpenKstTarget, // existing keystore of type [JKS-JCEKS-PKCS12-BKS-UBER] 
        char[] chrsPasswdOpenKstTarget,
        
        String strPathAbsKstSource, // keystore of type JCEKS 
        char[] chrsPasswdKstSource,
        
        String strProviderKstTarget
        )
    {
        super(
            frmOwner, 
         
            strPathAbsOpenKstTarget, // existing keystore of type [JKS-JCEKS-PKCS12-BKS-UBER] 
            chrsPasswdOpenKstTarget,
            strPathAbsKstSource, // keystore of type JCEKS 
            chrsPasswdKstSource,
            strProviderKstTarget,
            true // blnIsPasswdKprTarget
            );
            
    }
    
    // -------
    // PRIVATE
    
}