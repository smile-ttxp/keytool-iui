package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenCrKprDsa extends IFAbs
{
    public IFKstOpenCrKprDsa(Frame frmParent, String strTitleAppli)
    {
        super();
        
        super._pnl_ = new PTabUICmdKtlKstOpenCrKprDsa(frmParent, strTitleAppli);
        setTitle(PTabUICmdKtlKstOpenCrKprDsa.STR_TITLETASK);
    }

}
