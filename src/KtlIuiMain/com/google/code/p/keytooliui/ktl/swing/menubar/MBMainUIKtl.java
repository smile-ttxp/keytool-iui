package com.google.code.p.keytooliui.ktl.swing.menubar;


import com.google.code.p.keytooliui.ktl.swing.menu.MHelpAllMainUIKtl;
import com.google.code.p.keytooliui.ktl.swing.menu.MViewAllMainUIKtl;

final public class MBMainUIKtl extends MBMainUIAbs
{        
    public MBMainUIKtl(
        java.awt.Component cmpFrameOwner,
     
        
        java.awt.event.ActionListener actListenerParent,
        java.awt.event.ItemListener itmListenerParent,
        String strLic
        )
    {
        super(cmpFrameOwner, actListenerParent);
        
        super._menView_ = new MViewAllMainUIKtl(actListenerParent);
        
        super._menHelp_ = new MHelpAllMainUIKtl(
            cmpFrameOwner, actListenerParent, strLic);

    }
}
