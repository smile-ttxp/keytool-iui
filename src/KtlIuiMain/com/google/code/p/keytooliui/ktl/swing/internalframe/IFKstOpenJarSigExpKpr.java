package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenJarSigExpKpr extends IFAbs
{
    public IFKstOpenJarSigExpKpr(Frame frmParent, String strTitleAppli)
    {
        super();
        
        super._pnl_ = new PTabUICmdJsrSign(frmParent, strTitleAppli);
        setTitle(PTabUICmdJsrSign.STR_TITLETASK);
    }

}
