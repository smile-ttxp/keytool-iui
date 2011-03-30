package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenCCaImpTcr extends IFAbs
{
    public IFKstOpenCCaImpTcr(Frame frmParent, String strTitleAppli)
    {
        super();
        
        super._pnl_ = new PTabUICmdKtlKstOpenCCaImpTcr(frmParent, strTitleAppli);
        setTitle(PTabUICmdKtlKstOpenCCaImpTcr.STR_TITLETASK);
    }

}
