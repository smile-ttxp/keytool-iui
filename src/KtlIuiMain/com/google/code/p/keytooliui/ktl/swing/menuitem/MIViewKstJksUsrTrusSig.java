package com.google.code.p.keytooliui.ktl.swing.menuitem;

/**
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.menuitem.*;

import java.awt.event.*;

final public class MIViewKstJksUsrTrusSig extends MIKstJksCrtAbs
{
    // --------------
    // STATIC PUBLIC
    
    static public String s_strPrefix = "User-level trusted signer";
    
  
    
    // ------
    // PUBLIC
    
    public MIViewKstJksUsrTrusSig(ActionListener actListenerParent)
    {
        super(
            MIViewKstJksUsrTrusSig.s_strPrefix,
            actListenerParent
            );
            
        // ----
        // the following: only for windows or unix or linux
        
        if (! (com.google.code.p.keytooliui.shared.io.S_OperatingSystem.s_isUnix() || com.google.code.p.keytooliui.shared.io.S_OperatingSystem.s_isWindows()))
        {
            setEnabled(false);
            setVisible(false);
            return;
        }
        
        java.io.File fleCertsTrustUsr = 
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJks.s_getFileKstCertsTrustUsr();
        
        if (fleCertsTrustUsr == null)
        {
            setEnabled(false);
            setVisible(false);
            return;
        }
        
        if (! fleCertsTrustUsr.exists())
        {
            setEnabled(false);
            setVisible(false);
            return;
        }
        
        // ending
    }
}