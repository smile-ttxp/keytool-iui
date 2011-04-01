package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFKstOpenJarSigExpKpr extends IFAbs
{
    public IFKstOpenJarSigExpKpr(Frame frmParent)
    {
        super();
        
        super._pnl_ = new PTabUICmdJsrSign(frmParent);
        setTitle(PTabUICmdJsrSign.STR_TITLETASK);
    }

}
