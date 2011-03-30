package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenCrtImpReply extends IFAbs
{
    public IFKstOpenCrtImpReply(Frame frmParent, String strTitleAppli)
    {
        super();
        
        super._pnl_ = new PTabUICmdKtlKstOpenCrtImpReply(frmParent, strTitleAppli);
        setTitle(PTabUICmdKtlKstOpenCrtImpReply.STR_TITLETASK);
    }

}
