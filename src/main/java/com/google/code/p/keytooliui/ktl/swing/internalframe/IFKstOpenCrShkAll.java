package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenCrShkAll extends IFAbs
{
    public IFKstOpenCrShkAll(Frame frmParent)
    {
        super._pnl_ = new PTabUICmdKtlKstOpenCrShkAll(frmParent);
        setTitle(PTabUICmdKtlKstOpenCrShkAll.STR_TITLETASK);
    }

}
