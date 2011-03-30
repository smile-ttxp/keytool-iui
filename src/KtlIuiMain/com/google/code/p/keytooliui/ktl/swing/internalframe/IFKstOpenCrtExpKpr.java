package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenCrtExpKpr extends IFAbs
{
    public IFKstOpenCrtExpKpr(Frame frmParent, String strTitleAppli)
    {
        super();
        
        super._pnl_ = new PTabUICmdKtlKstOpenCrtExpKpr(frmParent, strTitleAppli);
        setTitle(PTabUICmdKtlKstOpenCrtExpKpr.STR_TITLETASK);
    }

}
