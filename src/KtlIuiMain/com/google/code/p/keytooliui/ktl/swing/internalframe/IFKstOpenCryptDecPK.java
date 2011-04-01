package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenCryptDecPK extends IFAbs
{
    public IFKstOpenCryptDecPK(Frame frmParent)
    {
        super();
        
        super._pnl_ = new PTabUICmdKtlKstOpenCryptDecPK(frmParent);
        setTitle(PTabUICmdKtlKstOpenCryptDecPK.STR_TITLETASK);
    }

}
