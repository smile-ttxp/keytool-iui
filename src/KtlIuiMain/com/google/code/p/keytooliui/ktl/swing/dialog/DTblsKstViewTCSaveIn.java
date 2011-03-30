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

public class DTblsKstViewTCSaveIn extends DTblsKstViewTCAbs
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strTitleThisPrefix = "Import certificate as trusted certificate entry in";
    final static private String _f_s_strTitleThisSuffix = "keystore";
    final static private String _STR_BODYBUTTONUSAGE = "Usage:\n  Type in new entry's alias,\n  then click \"OK\" button.";
    final static private String _STR_TEXTLABELALIAS = "Enter new alias:";
    
    // ------
    // public
    
    public DTblsKstViewTCSaveIn(
        Component cmpFrameOwner, 
        String strTitleAppli,
        java.security.KeyStore kseLoaded,
        String strPathAbs)
    {
        super(
                cmpFrameOwner, 
                strTitleAppli, 
                DTblsKstViewTCSaveIn._f_s_strTitleThisPrefix + " " + kseLoaded.getType() + " " + DTblsKstViewTCSaveIn._f_s_strTitleThisSuffix,
                kseLoaded,
                strPathAbs,
                DTblsKstViewTCSaveIn._STR_BODYBUTTONUSAGE,
                DTblsKstViewTCSaveIn._STR_TEXTLABELALIAS,
                true // blnSave (v/s Open)
                );
    }
    
    // ---------
    // protected
    
    // -------
    // private
}  
      
