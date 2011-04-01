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
import java.util.Date;
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
import com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstAbs;
import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.swing.button.BCancel;
import com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract;

public class DTblsKstSelTCOpenRsa extends DTblsKstSelTCOpen
{

    // ------
    // public
    
    // overwrite superclass's method
    public boolean load(
        String[] strsAliasPKTC,
        Boolean[] boosIsTCEntryPKTC,
        Boolean[] boosIsValidDatePKTC,
        Boolean[] boosIsSelfSignedCertPKTC,
        Boolean[] boosIsTrustedCertPKTC,
        String[] strsSizeKeyPublPKTC,
        String[] strsTypeCertPKTC,
        String[] strsAlgoSigCertPKTC,
        Date[] dtesLastModifiedPKTC,
        
        String[] strsAliasSK,    
        Date[] dtesLastModifiedSK
        )
    {
        String strMethod = "load(..)";
        
        
       
        
        Boolean[] boosIsTCRsaEntryPKTC = 
            UtilKstAbs.s_getBoosEntryTcrRsa(this,
            super._kstOpen_, strsAliasPKTC);
        
        if (boosIsTCRsaEntryPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosIsTCRsaEntryPKTC");
        }
        
        if (! _keystoreContainsValidCandidate(boosIsTCRsaEntryPKTC))
        {
           
           
            MySystem.s_printOutWarning(this, strMethod, "got exc");

            
            String strBody = "No valid entry found in selected keystore."; 
            strBody += "\n\n" + "Choosen keystore does not contain any entry of type trusted certificate with RSA public key algorithm.";
   
            OPAbstract.s_showDialogWarning(this, strBody);
            return false;
        }
        
        if (boosIsTCEntryPKTC == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil boosIsTCEntryPKTC");
            return false;
        }
        
        
        if (strsAliasSK == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strsAliasSK");
            return false;
        }
        
        Boolean[] boosIsCandidateSK = new Boolean[strsAliasSK.length];
        
        for (int i=0; i<strsAliasSK.length; i++)
            boosIsCandidateSK[i] = new Boolean(false);
        
        
        
        return super._load_(
            boosIsTCRsaEntryPKTC,
            strsAliasPKTC,
            boosIsTCEntryPKTC,
            boosIsValidDatePKTC,
            boosIsSelfSignedCertPKTC,
            boosIsTrustedCertPKTC,
            strsSizeKeyPublPKTC,
            strsTypeCertPKTC,
            strsAlgoSigCertPKTC,
            dtesLastModifiedPKTC,
        
            boosIsCandidateSK,
            strsAliasSK,    
            dtesLastModifiedSK
                );
    }
    
    public DTblsKstSelTCOpenRsa(
        Component cmpFrameOwner, 
      
        java.security.KeyStore kseLoaded,
        String strPathAbs,
        String strTitleSuffix)
    {
        super(cmpFrameOwner, kseLoaded, strPathAbs, strTitleSuffix);
    }
    
    // ---------
    // protected
    
     
    
    // -------
    // private
    
    private boolean  _keystoreContainsValidCandidate(Boolean[] boosValidCandidate)
    {
        if (boosValidCandidate == null)
            return false; // !!!!
        
        for (int i=0; i<boosValidCandidate.length; i++)
        {
            if (boosValidCandidate[i].booleanValue())
                return true;
        }
        
        return false;
    }
}  
