package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenCryptEncShk extends IFAbs
{
    public IFKstOpenCryptEncShk(Frame frmParent)
    {
        super();
        
        super._pnl_ = new PTabUICmdKtlKstOpenCryptEncShk(frmParent);
        setTitle(PTabUICmdKtlKstOpenCryptEncShk.STR_TITLETASK);
    }

}
