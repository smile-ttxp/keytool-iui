package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenXmlSigExpKpr extends IFAbs
{
    public IFKstOpenXmlSigExpKpr(Frame frmParent, String strTitleAppli)
    {
        super();
        
        super._pnl_ = new PTabUICmdKtlKstOpenXmlSigExpKpr(frmParent, strTitleAppli);
        setTitle(PTabUICmdKtlKstOpenXmlSigExpKpr.STR_TITLETASK);
    }

}
