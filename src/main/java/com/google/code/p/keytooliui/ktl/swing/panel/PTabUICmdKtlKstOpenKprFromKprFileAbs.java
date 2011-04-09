package com.google.code.p.keytooliui.ktl.swing.panel;

/**
**/

import com.google.code.p.keytooliui.ktl.util.jarsigner.*;
import com.google.code.p.keytooliui.ktl.io.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public abstract class PTabUICmdKtlKstOpenKprFromKprFileAbs extends PTabUICmdKtlKstOpenKprAbs
{
    // protected
    
    protected void _fillInPanelOutput_()
    {
        super._fillInPanelKst_(super._pnlOutput_);
    }
    
    protected PTabUICmdKtlKstOpenKprFromKprFileAbs(
            String strHelpID, 
            Frame frmOwner, 
      
            String strDocPropValKprKpr,
            String strDocPropValCrtsKpr,
             boolean blnDerVersusPem)
    {
        super(strHelpID, frmOwner, strDocPropValKprKpr, strDocPropValCrtsKpr);
        
        super._pnlSelectFileKpr_ = new PSelBtnTfdFileOpenKprKpr(
            (javax.swing.event.DocumentListener) this,
            frmOwner, 
      
            (ItemListener) null,
            blnDerVersusPem // XOR
            );
        
        super._pnlSelectFileCrts_ = new PSelBtnTfdFileOpenCrtsKpr(
            (javax.swing.event.DocumentListener) this,
            frmOwner, 
       
            (ItemListener) null,
             blnDerVersusPem // default selection
            );
    }
    
}
