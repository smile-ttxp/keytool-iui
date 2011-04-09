package com.google.code.p.keytooliui.ktl.swing.panel;

/*
*/

import java.awt.Frame;
import java.awt.event.ItemListener;

import net.miginfocom.swing.MigLayout;

public abstract class PTabUICmdKtlKstOpenKprExpAbs extends PTabUICmdKtlKstOpenKprAbs
{    
    // ---------
    // protected
    
    
    protected PTabUICmdKtlKstOpenKprExpAbs(
        Frame frmOwner, 
     
        String strHelpID
        )
    {
        super(
            strHelpID, 
            frmOwner, 
 
            PSelBtnTfdFileSaveKpr.f_s_strDocPropVal,
            PSelBtnTfdFileSaveCrts.f_s_strDocPropVal
            );

        super._pnlSelectFileKpr_ = new PSelBtnTfdFileSaveKpr(
            (javax.swing.event.DocumentListener) this,
            frmOwner, 
      
            (ItemListener) null
            );
        
        super._pnlSelectFileCrts_ = new PSelBtnTfdFileSaveCrts(
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
        super._pnlOutput_.add(super._pnlSelectFileKpr_);
        super._pnlOutput_.add(super._pnlSelectFileCrts_);
    }
}
