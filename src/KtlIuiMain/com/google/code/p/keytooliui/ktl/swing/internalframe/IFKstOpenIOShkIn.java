package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenIOShkIn extends IFAbs
{
    public IFKstOpenIOShkIn(Frame frmParent, String strTitleAppli)
    {
        super();
        
        super._pnl_ = new PTabUICmdKtlKstOpenIOShkIn(frmParent, strTitleAppli);
        setTitle(PTabUICmdKtlKstOpenIOShkIn.STR_TITLETASK);
    }

}
