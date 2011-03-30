package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenSigVerCrt extends IFAbs
{
    public IFKstOpenSigVerCrt(Frame frmParent, String strTitleAppli)
    {
        super();
        
        super._pnl_ = new PTabUICmdKtlKstOpenSigVerCrt(frmParent, strTitleAppli);
        setTitle(PTabUICmdKtlKstOpenSigVerCrt.STR_TITLETASK);
    }

}
