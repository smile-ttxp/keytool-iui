package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenCrKprEc extends IFAbs
{
    public IFKstOpenCrKprEc(Frame frmParent, String strTitleAppli)
    {
        super();
        
        super._pnl_ = new PTabUICmdKtlKstOpenCrKprEc(frmParent, strTitleAppli);
        setTitle(PTabUICmdKtlKstOpenCrKprEc.STR_TITLETASK);
    }

}
