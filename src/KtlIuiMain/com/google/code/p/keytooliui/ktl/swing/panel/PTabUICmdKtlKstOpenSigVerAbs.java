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

abstract public class PTabUICmdKtlKstOpenSigVerAbs extends PTabUICmdKtlKstOpenSigAbs
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
        GridBagConstraints gbc = super._fillInPanelKst_(super._pnlInput_);
        
        gbc.gridy ++;
        super._pnlInput_.add(super._pnlSelectFileData_, gbc);
        
        gbc.gridy ++;
        super._pnlInput_.add(super._pnlSelectFileSig_, gbc);
    }
    
    
    protected void _fillInPanelOutput_()
    {
        /*super._pnlOutput_.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        //natural height, maximum width
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.fill = GridBagConstraints.VERTICAL;
        
        gbc.anchor = GridBagConstraints.WEST; // ? left side of space
        //gbc.anchor = GridBagConstraints.NORTH; // ? left side of space
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        super._pnlOutput_.add(super._pnlSelectFileSig_, gbc);
       */
    }

    // -------
    // PRIVATE
}

