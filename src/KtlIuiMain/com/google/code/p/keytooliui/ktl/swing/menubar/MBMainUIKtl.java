package com.google.code.p.keytooliui.ktl.swing.menubar;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import com.google.code.p.keytooliui.ktl.swing.menu.MHelpAllMainUIKtl;
import com.google.code.p.keytooliui.ktl.swing.menu.MViewAllMainUIKtl;

final public class MBMainUIKtl extends MBMainUIAbs
{        
    public MBMainUIKtl(Component cmpFrameOwner, String strTitleApplication, ActionListener actListenerParent, ItemListener itmListenerParent, String strLic)
    {
        super(cmpFrameOwner, strTitleApplication, actListenerParent);
        
        super._menView_ = new MViewAllMainUIKtl(actListenerParent);
        
        super._menHelp_ = new MHelpAllMainUIKtl(cmpFrameOwner, strTitleApplication, actListenerParent, strLic);
    }
}
