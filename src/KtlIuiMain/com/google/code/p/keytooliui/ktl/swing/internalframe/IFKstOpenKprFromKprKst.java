package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenKprFromKprKst extends IFAbs
{
    public IFKstOpenKprFromKprKst(Frame frmParent)
    {
        super();
        
        super._pnl_ = new PTabUICmdKtlKstOpenKprFromKprKst(frmParent);
        setTitle(PTabUICmdKtlKstOpenKprFromKprKst.STR_TITLETASK);
    }

}
