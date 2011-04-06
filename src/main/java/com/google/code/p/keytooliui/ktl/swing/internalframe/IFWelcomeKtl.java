package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFWelcomeKtl extends IFAbs
{
    public IFWelcomeKtl(Frame frmParent)
    {
        super._pnl_ = new PTabHtmlWelcomeKtl(frmParent);
        setTitle(PTabHtmlWelcomeKtl.STR_TITLETASK);
    }

}

