package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenCryptEncTC extends IFAbs
{
    public IFKstOpenCryptEncTC(Frame frmParent, String strTitleAppli)
    {
        super();
        
        super._pnl_ = new PTabUICmdKtlKstOpenCryptEncTC(frmParent, strTitleAppli);
        setTitle(PTabUICmdKtlKstOpenCryptEncTC.STR_TITLETASK);
    }

}
