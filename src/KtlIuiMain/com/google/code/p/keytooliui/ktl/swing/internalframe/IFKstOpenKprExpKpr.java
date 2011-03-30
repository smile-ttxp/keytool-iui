package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenKprExpKpr extends IFAbs
{
    public IFKstOpenKprExpKpr(Frame frmParent, String strTitleAppli)
    {
        super();
        
        super._pnl_ = new PTabUICmdKtlKstOpenKprExpKpr(frmParent, strTitleAppli);
        setTitle(PTabUICmdKtlKstOpenKprExpKpr.STR_TITLETASK);
    }

}
