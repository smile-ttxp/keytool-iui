package com.google.code.p.keytooliui.ktl.swing.toolbar;

import javax.swing.SwingConstants;
import com.google.code.p.keytooliui.shared.swing.button.*;
import com.google.code.p.keytooliui.shared.swing.toolbar.*;

import com.google.code.p.keytooliui.ktl.swing.button.*;

public class TBSubCntKtl extends TBSubAbstract
{
    
    public TBSubCntKtl(String strTitleAppli,
            java.awt.event.ActionListener actListenerParentPrint
            )
    {
        super("strHelpID",
        actListenerParentPrint,
        SwingConstants.HORIZONTAL, //int intOrientation,
        com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(
                com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.f_s_strAppliUIKtl16) //javax.swing.ImageIcon iinFrameFloatable // should be used once bug fixed "setFloatable(true), see below
        );
        
        
        
        
        // ----
        setBorderPainted(true);
        this.setFloatable(true);
        setName(strTitleAppli + " - " + "Contents' toolbar"); // in case of floatable toolbar
    }
    
    public boolean init()
    {
        if (! super.init())
            return false;
        

        add(super._btnPrint_);
        
        
        
        super._btnPrint_.setToolTipText("print contents panel ..."); 
         
        return true;
    }
    
    // -------
    // private
    

    
}

