package com.google.code.p.keytooliui.ktl.swing.panel;

/**

    
    a panel that displays, from left to right:
    
    . 1 label
    . 1 checkbox
    
**/

import com.google.code.p.keytooliui.shared.swing.panel.*;

public final class PSelCbxTrustcacerts extends PSelCbxAbs
{
    // --------------------
    // PRIVATE STATIC FINAL
    
    // JDK's related: keytool -import "-trustcacerts"
    /*
    Excerpt from SUN's documentation:
	"When importing a new trusted certificate, alias must not yet exist in the keystore. 
	 Before adding the certificate to the keystore, keytool tries to verify it by attempting 
	 to construct a chain of trust from that certificate to a self-signed certificate 
	 (belonging to a root CA), using trusted certificates that are already available in the keystore.

    If the -trustcacerts option has been specified, additional certificates are considered for the chain of trust, 
	namely the certificates in a file named "cacerts", which resides in the JDK security properties directory,
	java.home\lib\security, where java.home is the runtime environment's directory 
	(the jre directory in the SDK or the top-level directory of the Java 2 Runtime Environment). 
	The "cacerts" file represents a system-wide keystore with CA certificates."
    */
    private static final String _f_s_strLabel = "Check root CA certs store:"; 
    
    // ------
    // PUBLIC   

    public PSelCbxTrustcacerts()
    {
        super(
            PSelCbxTrustcacerts._f_s_strLabel
            );
    }
    

}