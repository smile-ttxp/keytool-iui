package com.google.code.p.keytooliui.ktl.swing.menuitem;

/**
    known subclasses:
    . MIViewKstJksUsrTrusSig ==> User-level trusted signer certificate store
    . MIViewKstJksSysRootCA  ==> System-level root CA certificate store
    
    
    more about this:
    http://java.sun.com/j2se/1.5.0/docs/guide/deployment/deployment-guide/properties.html
    
    excerpt from online site above, eg, linux:
    
    /<uname>/.java/deployment/security/trusted.cacerts     ==> User-level root CA certificate store
    /<uname>/.java/deployment/security/trusted.jssecacerts ==> User-level JSSE CA certificate store
    /<uname>/.java/deployment/security/trusted.certs       ==> User-level trusted signer certificate store
    /<uname>/.java/deployment/security/trusted.jssecerts   ==> User-level trusted JSSE certificate store
    /<uname>/.java/deployment/security/trusted.clientcerts ==> User-level client authentication certificate store
    
    /<jre-home>/lib/security/cacerts             ==> System-level root CA certificate store
    /<jre-home>/lib/security/jssecacerts         ==> System-level JSSE CA certificate store
    /<jre-home>/lib/security/trusted.certs       ==> System-level trusted signer certificate store
    /<jre-home>/lib/security/trusted.jssecerts   ==> System-level trusted JSSE certificate store
    /<jre-home>/lib/security/trusted.clientcerts ==> System-level client authentication certificate store
    
    
    for windows, parent folder should be like this:
    . User-level ...   ==> <user-home>\Application Data\Sun\Java\Deployment\security
    . System-level ... ==> <jre-home>\lib\security
    
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.menuitem.*;

import java.awt.event.*;

abstract public class MIKstJksCrtAbs extends MIAbstract
{
    // -------------
    // static public
    
    static public String s_strSuffix = "Certificate store";
    
    // ------
    // PUBLIC
    
    protected MIKstJksCrtAbs(
        String strPrefix,
        ActionListener actListenerParent
        )
    {
        super(
            strPrefix + " " + MIKstJksCrtAbs.s_strSuffix.toLowerCase() + " ...",
            actListenerParent
            );
    }
}
