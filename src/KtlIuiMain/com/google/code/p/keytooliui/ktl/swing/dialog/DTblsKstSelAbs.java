
package com.google.code.p.keytooliui.ktl.swing.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.Date;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.google.code.p.keytooliui.ktl.swing.panel.PTblEntPKTCAbs;
import com.google.code.p.keytooliui.ktl.swing.panel.PTblEntPKTCSelAll;
import com.google.code.p.keytooliui.ktl.swing.panel.PTblEntSKAbs;
import com.google.code.p.keytooliui.ktl.swing.panel.PTblEntSKSelAll;
import com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstPkcs12;
import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.swing.button.BESExit24;
import com.google.code.p.keytooliui.shared.swing.button.BESTip24;
import com.google.code.p.keytooliui.shared.swing.button.BEnabledState;
import com.google.code.p.keytooliui.shared.swing.dialog.DEscapeAbstract;
import com.google.code.p.keytooliui.shared.swing.dialog.DPasswordOpen;
import com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract;
import com.google.code.p.keytooliui.shared.util.jarsigner.UtilCrtX509;

abstract public class DTblsKstSelAbs extends DTblsKstAbs 
{
    // --------------------
    // final static private
    

    // ------
    // public
    
    protected Boolean[] _boosIsCandidatePKTC_ = null;

   protected boolean _load_(
        Boolean[] boosIsCandidatePKTC,
        String[] strsAliasPKTC,
        Boolean[] boosIsTCEntryPKTC,
        Boolean[] boosIsValidDatePKTC,
        Boolean[] boosIsSelfSignedCertPKTC,
        Boolean[] boosIsTrustedCertPKTC,
        String[] strsSizeKeyPublPKTC,
        String[] strsTypeCertPKTC,
        String[] strsAlgoSigCertPKTC,
        Date[] dtesLastModifiedPKTC,
        
        Boolean[] boosIsCandidateSK,
        String[] strsAliasSK,    
        Date[] dtesLastModifiedSK
        )
    {
        String strMethod = "_load_(...)";

        this._boosIsCandidatePKTC_ = boosIsCandidatePKTC;
        super._strsAliasPKTC_ = strsAliasPKTC;
        super._boosIsTCEntryPKTC_ = boosIsTCEntryPKTC;
        super._strsAliasSK_ = strsAliasSK;
        
        // ---- PKTC ----
        
        if (this._pnlTablePKTC_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlTablePKTC_");
            return false;
        }
        
        
        
        boolean blnOkPKTC = ((PTblEntPKTCSelAll) super._pnlTablePKTC_).load(
            boosIsCandidatePKTC,
            strsAliasPKTC, 
            boosIsTCEntryPKTC, boosIsValidDatePKTC,
            boosIsSelfSignedCertPKTC, boosIsTrustedCertPKTC, 
            strsSizeKeyPublPKTC,
            strsTypeCertPKTC, 
            strsAlgoSigCertPKTC, dtesLastModifiedPKTC);
            
        if (! blnOkPKTC)
        {
            MySystem.s_printOutError(this, strMethod, "! blnOkPKTC");
            return false;
        }
        
        
        _resetPKTC_();
        
        // ---- SK ----
        
        if (this._pnlTableSK_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlTableSK_");
            return false;
        }
        
        boolean blnOkSK = ((PTblEntSKSelAll) super._pnlTableSK_).load(
            boosIsCandidateSK,
            strsAliasSK, dtesLastModifiedSK);
            
        if (! blnOkSK)
        {
            MySystem.s_printOutError(this, strMethod, "! blnOkSK");
            return false;
        }
        
        
        _resetSK_();
        
        return true;
    }
    
    // ---------
    // protected
    
    
    
    protected DTblsKstSelAbs(
        Component cmpFrameOwner, 
        String strTitleAppli,
        String strTitleThis,
        KeyStore kseLoaded,
        String strPathAbs,
        String strBodyButtonUsage)
    {
        super(
            cmpFrameOwner, 
            strTitleAppli,
            strTitleThis,
            kseLoaded,
            strPathAbs,
            strBodyButtonUsage
                );
        
        super._pnlTablePKTC_ = new PTblEntPKTCSelAll((ListSelectionListener) this, (MouseListener) this);
        super._pnlTableSK_ = new PTblEntSKSelAll((ListSelectionListener) this, (MouseListener) this);
    }
    
    protected boolean _loadSK_(
        Boolean[] boosIsCandidateSK,
        String[] strsAliasSK,    
        Date[] dtesLastModifiedSK
        )
    {
        String strMethod = "_loadSK_(...)";
        
        super._strsAliasSK_ = strsAliasSK;
        
        // ---- SK ----
        
        if (this._pnlTableSK_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlTableSK_");
            return false;
        }
        
        boolean blnOkSK = ((PTblEntSKSelAll) super._pnlTableSK_).load(
            boosIsCandidateSK,
            strsAliasSK, dtesLastModifiedSK);
            
        if (! blnOkSK)
        {
            MySystem.s_printOutError(this, strMethod, "! blnOkSK");
            return false;
        }
        
        
        _resetSK_();
        
        return true;
    }
    
    protected boolean _loadPKTC_(
        Boolean[] boosIsCandidatePKTC,
        String[] strsAliasPKTC,
        Boolean[] boosIsTCEntryPKTC,
        Boolean[] boosIsValidDatePKTC,
        Boolean[] boosIsSelfSignedCertPKTC,
        Boolean[] boosIsTrustedCertPKTC,
        String[] strsSizeKeyPublPKTC,
        String[] strsTypeCertPKTC,
        String[] strsAlgoSigCertPKTC,
        Date[] dtesLastModifiedPKTC
        )
    {
        String strMethod = "_loadPKTC_(...)";

        this._boosIsCandidatePKTC_ = boosIsCandidatePKTC;
        super._strsAliasPKTC_ = strsAliasPKTC;
        super._boosIsTCEntryPKTC_ = boosIsTCEntryPKTC;
        
        
        // ---- PKTC ----
        
        if (this._pnlTablePKTC_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlTablePKTC_");
            return false;
        }
        
        
        
        boolean blnOkPKTC = ((PTblEntPKTCSelAll) super._pnlTablePKTC_).load(
            boosIsCandidatePKTC,
            strsAliasPKTC, 
            boosIsTCEntryPKTC, boosIsValidDatePKTC,
            boosIsSelfSignedCertPKTC, boosIsTrustedCertPKTC, 
            strsSizeKeyPublPKTC,
            strsTypeCertPKTC, 
            strsAlgoSigCertPKTC, dtesLastModifiedPKTC);
            
        if (! blnOkPKTC)
        {
            MySystem.s_printOutError(this, strMethod, "! blnOkPKTC");
            return false;
        }
        
        
        _resetPKTC_();
        return true;
    }
}