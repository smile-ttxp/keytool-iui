package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenIOShkOut extends IFAbs
{
    public IFKstOpenIOShkOut(Frame frmParent, String strTitleAppli)
    {
        super();
        
        super._pnl_ = new PTabUICmdKtlKstOpenIOShkOut(frmParent, strTitleAppli);
        setTitle(PTabUICmdKtlKstOpenIOShkOut.STR_TITLETASK);
    }

}
