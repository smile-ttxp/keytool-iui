package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.Frame;
import com.google.code.p.keytooliui.ktl.swing.panel.*;


public class IFXmlSigVerify extends IFAbs
{
    public IFXmlSigVerify(Frame frmParent, String strTitleAppli)
    {
        super();
        
        super._pnl_ = new PTabUICmdXmlSigVerify(frmParent, strTitleAppli);
        setTitle(PTabUICmdXmlSigVerify.STR_TITLETASK);
    }

}
