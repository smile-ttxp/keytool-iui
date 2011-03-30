package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenCryptDecShk extends IFAbs
{
    public IFKstOpenCryptDecShk(Frame frmParent, String strTitleAppli)
    {
        super();
        
        super._pnl_ = new PTabUICmdKtlKstOpenCryptDecShk(frmParent, strTitleAppli);
        setTitle(PTabUICmdKtlKstOpenCryptDecShk.STR_TITLETASK);
    }

}
