package com.google.code.p.keytooliui.ktl.swing.toolbar;

import java.awt.Component;
import com.google.code.p.keytooliui.ktl.swing.button.BESAboutAppliKtl;

final public class TBMainUIKtl extends TBMainUIAbs
{
    
    // ------
    // PUBLIC
    
 
    
    public TBMainUIKtl(
        java.awt.Frame frmParent,
        java.awt.event.ActionListener actListenerParentAppli,
        //javax.help.HelpBroker hbrHelpStandard,
        String strTitleAppli)
    {
        super(
            actListenerParentAppli,
            //hbrHelpStandard,
            
            com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(
                com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.f_s_strAppliUIKtl16),
                
            strTitleAppli
            );
        
        
        super._btnHelpTrack_.setToolTipText("Help on active window's task");
        
        /** TODO 
         * TEMPO, should be added while used in
         * advanced NetBeans module
         * 
         
         * super._btnAboutAppli_ = new BESAboutAppliKtl(
                (Component) frmParent, // cmpFrameOwner
                strTitleAppli);
         **/
    }
}