package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenSigExpKpr extends IFAbs
{
    public IFKstOpenSigExpKpr(Frame frmParent)
    {
        super();
        
        super._pnl_ = new PTabUICmdKtlKstOpenSigExpKpr(frmParent);
        setTitle(PTabUICmdKtlKstOpenSigExpKpr.STR_TITLETASK);
    }

}
