package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenSigVerCrt extends IFAbs
{
    public IFKstOpenSigVerCrt(Frame frmParent)
    {
        super();
        
        super._pnl_ = new PTabUICmdKtlKstOpenSigVerCrt(frmParent);
        setTitle(PTabUICmdKtlKstOpenSigVerCrt.STR_TITLETASK);
    }

}
