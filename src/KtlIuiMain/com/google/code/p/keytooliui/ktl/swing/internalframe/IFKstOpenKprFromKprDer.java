package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenKprFromKprDer extends IFAbs
{
    public IFKstOpenKprFromKprDer(Frame frmParent, String strTitleAppli)
    {
        super();
        
        super._pnl_ = new PTabUICmdKtlKstOpenKprFromKprDer(frmParent, strTitleAppli);
        setTitle(PTabUICmdKtlKstOpenKprFromKprDer.STR_TITLETASK);
    }

}

