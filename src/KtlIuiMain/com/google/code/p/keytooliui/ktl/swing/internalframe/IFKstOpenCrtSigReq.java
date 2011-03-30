package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenCrtSigReq extends IFAbs
{
    public IFKstOpenCrtSigReq(Frame frmParent, String strTitleAppli)
    {
        super();
        
        super._pnl_ = new PTabUICmdKtlKstOpenCrtSigReq(frmParent, strTitleAppli);
        setTitle(PTabUICmdKtlKstOpenCrtSigReq.STR_TITLETASK);
    }

}
