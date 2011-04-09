package com.google.code.p.keytooliui.ktl.swing.panel;

/*
*/

import java.awt.Frame;
import java.awt.event.ItemListener;

public abstract class PTabUICmdKtlKstOpenSigVerAbs extends PTabUICmdKtlKstOpenSigAbs
{    
    // ---------
    // protected
    
    
    protected PTabUICmdKtlKstOpenSigVerAbs(
        Frame frmOwner, 
  
        String strHelpID
        )
    {
        super(
            strHelpID, 
            frmOwner, 
    
            PSelBtnTfdFileOpenAnyFile.f_s_strDocPropVal,
            PSelBtnTfdFileOpenSig.f_s_strDocPropVal
            );
        
        

        super._pnlSelectFileSig_ = new PSelBtnTfdFileOpenSig(
            (javax.swing.event.DocumentListener) this,
            frmOwner, 
     
            (ItemListener) null
            );
    }
    
    
    
    // override superclass's method'
    protected void _fillInPanelInput_()
    {        
        super._fillInPanelKst_(super._pnlInput_);
        super._pnlInput_.add(super._pnlSelectFileData_);
        super._pnlInput_.add(super._pnlSelectFileSig_);
    }
    
    
    protected void _fillInPanelOutput_()
    {
        //super._pnlOutput_.setLayout(new MigLayout("fill, wrap 1", "[left]"));
        //super._pnlOutput_.add(super._pnlSelectFileSig_);
    }
}
