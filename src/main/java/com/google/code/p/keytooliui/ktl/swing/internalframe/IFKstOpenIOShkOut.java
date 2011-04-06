package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenIOShkOut extends IFAbs
{
    public IFKstOpenIOShkOut(Frame frmParent)
    {
        super._pnl_ = new PTabUICmdKtlKstOpenIOShkOut(frmParent);
        setTitle(PTabUICmdKtlKstOpenIOShkOut.STR_TITLETASK);
    }

}
