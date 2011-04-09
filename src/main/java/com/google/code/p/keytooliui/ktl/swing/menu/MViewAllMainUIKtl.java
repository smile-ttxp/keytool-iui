

package com.google.code.p.keytooliui.ktl.swing.menu;

/**
**/

import com.google.code.p.keytooliui.ktl.swing.menuitem.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.menu.*;
import com.google.code.p.keytooliui.shared.swing.menuitem.*;
import com.google.code.p.keytooliui.shared.io.*;

import javax.swing.*;

import java.awt.event.*;

public final class MViewAllMainUIKtl extends MViewAllMainUIAbs
{
    // ------
    // PUBLIC
    
    
    public MViewAllMainUIKtl(ActionListener actListenerParent)
    {
        super(actListenerParent);
        super._menSelectTab_ = new MViewSelTabUIKtl(actListenerParent);
    }
}