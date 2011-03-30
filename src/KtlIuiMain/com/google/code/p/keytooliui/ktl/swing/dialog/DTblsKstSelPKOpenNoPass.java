package com.google.code.p.keytooliui.ktl.swing.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.security.KeyStoreException;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ListSelectionEvent;
import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.swing.button.BCancel;
import com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract;

public class DTblsKstSelPKOpenNoPass extends DTblsKstSelPKAbs
{
    // --------------------
    // FINAL STATIC PRIVATE

    final static private String _f_s_strTitleThisSuffix = "keystore";
    final static private String _STR_BODYBUTTONUSAGE = "Usage:\n  Mouse-click  valid candidate row to select respective entry's alias,\n  then click \"OK\" button.";
    final static private String _STR_TEXTLABELALIAS = "Selected entry's alias:";
    
    // ------
    // public
    
    public char[] getPassword() { return new char[0]; } // trick
    
    public DTblsKstSelPKOpenNoPass(
        Component cmpFrameOwner, 
        String strTitleAppli,
        java.security.KeyStore kseLoaded,
        String strPathAbs,
        String strTitlePrefix)
    {
        super(
                cmpFrameOwner, 
                strTitleAppli, 
                strTitlePrefix,
                kseLoaded,
                strPathAbs,
                DTblsKstSelPKOpenNoPass._STR_BODYBUTTONUSAGE,
                DTblsKstSelPKOpenNoPass._STR_TEXTLABELALIAS,
                false // blnSave (v/s Open) !!!!!!!!!!!!! always false !!!!!!!!!
                );
    }
    
    // ---------
    // protected
    
    protected boolean _contentsOk_()
    {
        return super._contentsAliasOk_();
    }
    
    // -------
    // private
}  
      