package com.google.code.p.keytooliui.ktl.swing.panel;

/*
*/

import com.google.code.p.keytooliui.ktl.util.jarsigner.*;
import com.google.code.p.keytooliui.ktl.io.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;
import com.google.code.p.keytooliui.shared.swing.dialog.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

abstract public class PTabUICmdKtlKstOpenKprExpAbs extends PTabUICmdKtlKstOpenKprAbs
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
        super._pnlOutput_.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        //natural height, maximum width
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.fill = GridBagConstraints.VERTICAL;
        
        gbc.anchor = GridBagConstraints.WEST; // ? left side of space
        //gbc.anchor = GridBagConstraints.NORTH; // ? left side of space
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        super._pnlOutput_.add(super._pnlSelectFileKpr_, gbc);

        gbc.gridy ++;
        super._pnlOutput_.add(super._pnlSelectFileCrts_, gbc);
    }

    
    // -------
    // PRIVATE


}
