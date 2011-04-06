package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenKprExpKpr extends IFAbs
{
    public IFKstOpenKprExpKpr(Frame frmParent)
    {
        super._pnl_ = new PTabUICmdKtlKstOpenKprExpKpr(frmParent);
        setTitle(PTabUICmdKtlKstOpenKprExpKpr.STR_TITLETASK);
    }

}
