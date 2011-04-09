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

public class DTblsKstViewKeySavePK extends DTblsKstViewKeyAbs
{
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _f_s_strTitleThisSuffix = "keystore";
    private static final String _STR_BODYBUTTONUSAGE = "Usage:\n  Type in new private key entry's alias," +"\n  next enter new password," +"\n  then confirm respective password,"+ "\n  finally click \"OK\" button.";
    private static final String _STR_TEXTLABELALIAS = "Enter new private key entry's alias:";
    
    // ------
    // public
    
    public DTblsKstViewKeySavePK(
        Component cmpFrameOwner, 
   
        java.security.KeyStore kseLoaded,
        String strPathAbs,
        String strTitlePrefix)
    {
        super(
                cmpFrameOwner, 
             
                strTitlePrefix, 
                kseLoaded,
                strPathAbs,
                DTblsKstViewKeySavePK._STR_BODYBUTTONUSAGE,
                DTblsKstViewKeySavePK._STR_TEXTLABELALIAS,
                true, // blnSave (v/s Open)
                true // blnIsPassword
                );
    }
    
    // ---------
    // protected
    
    // -------
    // private
}  
      

