package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenSigExpKpr extends IFAbs
{
    public IFKstOpenSigExpKpr(Frame frmParent, String strTitleAppli)
    {
        super();
        
        super._pnl_ = new PTabUICmdKtlKstOpenSigExpKpr(frmParent, strTitleAppli);
        setTitle(PTabUICmdKtlKstOpenSigExpKpr.STR_TITLETASK);
    }

}
