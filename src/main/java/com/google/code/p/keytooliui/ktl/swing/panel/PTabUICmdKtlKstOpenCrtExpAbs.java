package com.google.code.p.keytooliui.ktl.swing.panel;

/*
*/

import java.awt.Frame;
import java.awt.event.ItemListener;

import net.miginfocom.swing.MigLayout;

public abstract class PTabUICmdKtlKstOpenCrtExpAbs extends PTabUICmdKtlKstOpenCrtAbs
{    
    // ---------
    // protected
    
    
    protected PTabUICmdKtlKstOpenCrtExpAbs(
        Frame frmOwner, 
 
        String strHelpID
        )
    {
        super(
            strHelpID, 
            frmOwner, 
    
            PSelBtnTfdFileSaveCrt.f_s_strDocPropVal
            );

        super._pnlSelectFileIO_ = new PSelBtnTfdFileSaveCrt(
            (javax.swing.event.DocumentListener) this,
            frmOwner, 
      
            (ItemListener) null
            );
            
    }
    
    
    
    
    protected void _fillInPanelInput_()
    {        
        super._fillInPanelKst_(super._pnlInput_);
    }
    
    
    protected void _fillInPanelOutput_()
    {
        super._pnlOutput_.setLayout(new MigLayout("fill, wrap 1", "[left]"));
        super._pnlOutput_.add(super._pnlSelectFileIO_);
    }
}
