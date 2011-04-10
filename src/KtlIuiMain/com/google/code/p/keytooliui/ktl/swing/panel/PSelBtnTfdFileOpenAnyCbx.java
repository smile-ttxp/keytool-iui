package com.google.code.p.keytooliui.ktl.swing.panel;

/**
    contains a JCheckBox at the end
**/



import com.google.code.p.keytooliui.ktl.io.*;
import com.google.code.p.keytooliui.ktl.swing.button.*;

import com.google.code.p.keytooliui.shared.swing.button.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.swing.textfield.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.io.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class PSelBtnTfdFileOpenAnyCbx extends PSelBtnTfdFileOpenAnyFile 
{   
    // ------
    // public
    
    public PSelBtnTfdFileOpenAnyCbx(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent, 
    
        ItemListener itmListenerParent,
        String strLabel,
        String strLabelCbx,
        String strTipCbx
        )
    {
        super(docListenerParent, frmParent, itmListenerParent, strLabel);
        
        this._cbx = new JCheckBox(strLabelCbx);
        this._cbx.setToolTipText(strTipCbx);
    }
    
    public boolean init()
    {
        if (! super.init())
            return false;
            
        super._pnl_.add(this._cbx);

        // ending
        return true;
    }
    
    public boolean isSelectedCheckbox() { return this._cbx.isSelected(); }
    
    // -------
    // private
    
    
    private JCheckBox _cbx = null;
}
