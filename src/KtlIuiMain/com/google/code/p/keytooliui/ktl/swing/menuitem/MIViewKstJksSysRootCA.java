package com.google.code.p.keytooliui.ktl.swing.menuitem;

/**
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.menuitem.*;

import java.awt.event.*;

final public class MIViewKstJksSysRootCA extends MIKstJksCrtAbs
{
    // --------------
    // STATIC PRIVATE
    
    static public String s_strPrefix = "System-level root CA";
    
   
    // ------
    // PUBLIC
    
    public MIViewKstJksSysRootCA(ActionListener actListenerParent)
    {
        super(
            MIViewKstJksSysRootCA.s_strPrefix,
            actListenerParent
            );
    }
}