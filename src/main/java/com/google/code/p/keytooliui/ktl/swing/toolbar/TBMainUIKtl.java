package com.google.code.p.keytooliui.ktl.swing.toolbar;

import com.google.code.p.keytooliui.ktl.swing.button.BESHelpOnlineHome24;

public final class TBMainUIKtl extends TBMainUIAbs
{
    
    // ------
    // PUBLIC
    
 
    
    public TBMainUIKtl(
        java.awt.Frame frmParent,
        java.awt.event.ActionListener actListenerParentAppli
        //javax.help.HelpBroker hbrHelpStandard,
        )
    {
        super(
            actListenerParentAppli,
            //hbrHelpStandard,
            
            com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(
                com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.f_s_strAppliUIKtl16)
                
        
            );

        super._btnHelpOnlineHome_ = new BESHelpOnlineHome24(frmParent);
        
        super._btnHelpTrack_.setToolTipText("Help on active window's task");
        
        /** TODO 
         * TEMPO, should be added while used in
         * advanced NetBeans module
         * 
         
         * super._btnAboutAppli_ = new BESAboutAppliKtl(
                (Component) frmParent // cmpFrameOwner
                );
         **/
    }
}