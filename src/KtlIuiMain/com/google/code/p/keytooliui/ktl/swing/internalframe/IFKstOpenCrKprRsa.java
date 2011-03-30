package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenCrKprRsa extends IFAbs
{
    public IFKstOpenCrKprRsa(Frame frmParent, String strTitleAppli)
    {
        super();
        
        super._pnl_ = new PTabUICmdKtlKstOpenCrKprRsa(frmParent, strTitleAppli);
        setTitle(PTabUICmdKtlKstOpenCrKprRsa.STR_TITLETASK);
    }

}
