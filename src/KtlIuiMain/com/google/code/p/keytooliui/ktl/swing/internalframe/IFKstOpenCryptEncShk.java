package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenCryptEncShk extends IFAbs
{
    public IFKstOpenCryptEncShk(Frame frmParent, String strTitleAppli)
    {
        super();
        
        super._pnl_ = new PTabUICmdKtlKstOpenCryptEncShk(frmParent, strTitleAppli);
        setTitle(PTabUICmdKtlKstOpenCryptEncShk.STR_TITLETASK);
    }

}
