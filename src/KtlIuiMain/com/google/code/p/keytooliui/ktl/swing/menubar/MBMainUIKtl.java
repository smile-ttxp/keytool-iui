package com.google.code.p.keytooliui.ktl.swing.menubar;


import com.google.code.p.keytooliui.ktl.swing.menu.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.menubar.*;
import com.google.code.p.keytooliui.shared.swing.menu.*;



final public class MBMainUIKtl extends MBMainUIAbs
{        
    // ------
    // PUBLIC


    public MBMainUIKtl(
        java.awt.Component cmpFrameOwner,
        String strTitleApplication,
        
        java.awt.event.ActionListener actListenerParent,
        java.awt.event.ItemListener itmListenerParent,
        
        //javax.help.HelpBroker hbrHelpStandard, 
        String strLic
        )
    {
        super(cmpFrameOwner, strTitleApplication, actListenerParent);
        
        
        super._menView_ = new MViewAllMainUIKtl(actListenerParent);
       
        
        super._menHelp_ = new MHelpAllMainUIKtl(
            cmpFrameOwner, strTitleApplication/*, hbrHelpStandard*/, actListenerParent, strLic);
    }
}