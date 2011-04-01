package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenKprFromKprDer extends IFAbs
{
    public IFKstOpenKprFromKprDer(Frame frmParent)
    {
        super();
        
        super._pnl_ = new PTabUICmdKtlKstOpenKprFromKprDer(frmParent);
        setTitle(PTabUICmdKtlKstOpenKprFromKprDer.STR_TITLETASK);
    }

}

