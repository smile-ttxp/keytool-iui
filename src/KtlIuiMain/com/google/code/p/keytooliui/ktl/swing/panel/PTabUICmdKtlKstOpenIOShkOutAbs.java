package com.google.code.p.keytooliui.ktl.swing.panel;

/*
*/

import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import com.google.code.p.keytooliui.ktl.util.jarsigner.*;
import com.google.code.p.keytooliui.ktl.io.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;
import com.google.code.p.keytooliui.shared.swing.dialog.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

abstract public class PTabUICmdKtlKstOpenIOShkOutAbs extends PTabUICmdKtlKstOpenIOShkAbs
{    
    // ---------
    // protected
    
    protected PTabUICmdKtlKstOpenIOShkOutAbs(
        Frame frmOwner, 
        String strTitleAppli,
        String strHelpID
        )
    {
        super(
            strHelpID, 
            frmOwner, 
            strTitleAppli,
            PSelBtnTfdFileSaveShk.f_s_strDocPropVal
            );
        
        
            /*super._pnlSelectFileData_ = new PSelBtnTfdFileSaveAny(
            (javax.swing.event.DocumentListener) this,
            frmOwner, 
            strTitleAppli,
            (java.awt.event.ItemListener) null,
            "Secret key file:" // strLabel
            );*/
        
            super._pnlSelectFileData_ = new PSelBtnTfdFileSaveShk(
            (javax.swing.event.DocumentListener) this,
            frmOwner, 
            strTitleAppli,
            (ItemListener) null
            );
        
    }
    
    protected void _fillInPanelInput_()
    {        
        GridBagConstraints gbc = super._fillInPanelKst_(super._pnlInput_);
        
        //gbc.gridy ++;
        //super._pnlInput_.add(this._pnlSelectFileDataOpen_, gbc);
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
        super._pnlOutput_.add(this._pnlSelectFileData_, gbc);
    }

    // -------
    // PRIVATE

}
