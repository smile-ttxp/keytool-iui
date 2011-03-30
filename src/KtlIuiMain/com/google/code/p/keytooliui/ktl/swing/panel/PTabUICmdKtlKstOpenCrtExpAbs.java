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

abstract public class PTabUICmdKtlKstOpenCrtExpAbs extends PTabUICmdKtlKstOpenCrtAbs
{    
    // ---------
    // protected
    
    
    protected PTabUICmdKtlKstOpenCrtExpAbs(
        Frame frmOwner, 
        String strTitleAppli,
        String strHelpID
        )
    {
        super(
            strHelpID, 
            frmOwner, 
            strTitleAppli,
            PSelBtnTfdFileSaveCrt.f_s_strDocPropVal
            );

        super._pnlSelectFileIO_ = new PSelBtnTfdFileSaveCrt(
            (javax.swing.event.DocumentListener) this,
            frmOwner, 
            strTitleAppli,
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
        
        super._pnlOutput_.add(super._pnlSelectFileIO_, gbc);

    }

    
    // -------
    // PRIVATE


}