package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFCmsSigVerify extends IFAbs
{
    public IFCmsSigVerify(Frame frmParent)
    {
        super._pnl_ = new PTabUICmdCmsSigVerify(frmParent);
        setTitle(PTabUICmdCmsSigVerify.STR_TITLETASK);
    }

}
