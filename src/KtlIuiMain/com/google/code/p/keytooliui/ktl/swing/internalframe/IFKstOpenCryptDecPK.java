package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenCryptDecPK extends IFAbs
{
    public IFKstOpenCryptDecPK(Frame frmParent, String strTitleAppli)
    {
        super();
        
        super._pnl_ = new PTabUICmdKtlKstOpenCryptDecPK(frmParent, strTitleAppli);
        setTitle(PTabUICmdKtlKstOpenCryptDecPK.STR_TITLETASK);
    }

}
