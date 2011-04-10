package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenSCmsExpKpr extends IFAbs
{
    public IFKstOpenSCmsExpKpr(Frame frmParent)
    {
        super();
        
        super._pnl_ = new PTabUICmdKtlKstOpenSCmsExpKpr(frmParent);
        setTitle(PTabUICmdKtlKstOpenSCmsExpKpr.STR_TITLETASK);
    }

}

