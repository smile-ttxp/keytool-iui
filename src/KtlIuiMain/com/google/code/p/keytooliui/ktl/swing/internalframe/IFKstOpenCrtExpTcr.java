package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenCrtExpTcr extends IFAbs
{
    public IFKstOpenCrtExpTcr(Frame frmParent, String strTitleAppli)
    {
        super();
        
        super._pnl_ = new PTabUICmdKtlKstOpenCrtExpTcr(frmParent, strTitleAppli);
        setTitle(PTabUICmdKtlKstOpenCrtExpTcr.STR_TITLETASK);
    }

}

