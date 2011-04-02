package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenCrtImpReply extends IFAbs
{
    public IFKstOpenCrtImpReply(Frame frmParent)
    {
        super();
        
        super._pnl_ = new PTabUICmdKtlKstOpenCrtImpReply(frmParent);
        setTitle(PTabUICmdKtlKstOpenCrtImpReply.STR_TITLETASK);
    }

}
