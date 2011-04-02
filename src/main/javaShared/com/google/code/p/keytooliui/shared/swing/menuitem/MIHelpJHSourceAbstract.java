package com.google.code.p.keytooliui.shared.swing.menuitem;

/**
    Note: JH means JavaHelp

    known subclasses:
    . MIHelpJHSourceStandard
    . MIHelpJHSourceStarted
    . MIHelpJHSourceExpert
    . MIHelpJHSourceForward

**/

import com.google.code.p.keytooliui.shared.lang.*;

import java.awt.event.*;

abstract public class MIHelpJHSourceAbstract extends MIHelpJHAbstract
{   
    // ------
    // PUBLIC

    // either standard or started help
    public void setEnabledHelp(javax.help.HelpBroker hbr)  
    {
       if (hbr == null)
           return;
       
       ActionListener alr = new com.google.code.p.keytooliui.shared.help.MyCSH.MyDisplayHelpFromSource(hbr);
       
       if (alr != null)
       {
         this.addActionListener(alr);
          setEnabled(true);
       }
    }
    
    
    // ---------
    // PROTECTED
    
    protected MIHelpJHSourceAbstract(/*javax.help.HelpBroker hbr,*/ String strText)
    {
        super(strText);
        String strMethod = "MIHelpJHSourceAbstract(hbr, strText)";
        
        /*if (hbr == null)
        {
            if (strText != null)
                MySystem.s_printOutExit(this, strMethod, "nil hbr, strText=" + strText);
            else
                MySystem.s_printOutExit(this, strMethod, "nil hbr, nil strText");
        }
        
        final ActionListener alr = new com.google.code.p.keytooliui.shared.help.MyCSH.MyDisplayHelpFromSource(hbr);
        this.addActionListener(alr);*/
    }
}