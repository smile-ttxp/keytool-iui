package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenCryptEncPK extends IFAbs
{
    public IFKstOpenCryptEncPK(Frame frmParent, String strTitleAppli)
    {
        super();
        
        super._pnl_ = new PTabUICmdKtlKstOpenCryptEncPK(frmParent, strTitleAppli);
        setTitle(PTabUICmdKtlKstOpenCryptEncPK.STR_TITLETASK);
    }

}
