package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenCrtExpTcr extends IFAbs
{
    public IFKstOpenCrtExpTcr(Frame frmParent)
    {
        super._pnl_ = new PTabUICmdKtlKstOpenCrtExpTcr(frmParent);
        setTitle(PTabUICmdKtlKstOpenCrtExpTcr.STR_TITLETASK);
    }

}

