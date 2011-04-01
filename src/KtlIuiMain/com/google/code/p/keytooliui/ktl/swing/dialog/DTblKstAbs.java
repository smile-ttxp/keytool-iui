package com.google.code.p.keytooliui.ktl.swing.dialog;

/**
    "Kst" means "KeyStore"
    
    known subclasses:
    . DTblKstView
    . DTblKstManage
**/

import com.google.code.p.keytooliui.ktl.swing.button.*;
import com.google.code.p.keytooliui.ktl.swing.panel.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.dialog.*;
import com.google.code.p.keytooliui.shared.swing.button.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;

import javax.swing.*;

// ----
import java.security.KeyStore;
import java.security.KeyStoreException;
// ----
import java.security.cert.X509Certificate;
// ----

import javax.swing.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

abstract public class DTblKstAbs extends DEscapeAbstract implements
    ListSelectionListener,
    ActionListener
{
    // ------
    // PUBLIC
    
    public void actionPerformed(ActionEvent evtAction) 
    {
        String strMethod = "actionPerformed(evtAction)";
        
        if (evtAction.getSource() instanceof JButton)
        {
            JButton btnSource = (JButton) evtAction.getSource();
        
            if (btnSource == this._btnCancel)
            {
                 _cancel_();
                 
                 // --
                 return;
            }
            
            if (btnSource==this._btnViewKprCrt || btnSource==this._btnViewTcrCrt)
            {                
                if (! _viewEntryCrt())
                    MySystem.s_printOutExit(this, strMethod, "failed");
                 
                 // --
                 return;
            }
            
            if (btnSource==this._btnVerifyKprCrt ||  btnSource==this._btnVerifyTcrCrt) // verify signed (unjarred) file
            {                
                if (! _verifyEntryCrt())
                    MySystem.s_printOutExit(this, strMethod, "failed");
                 
                 // --
                 return;
            }
            
            if (btnSource == this._btnViewKprCrtsChain)
            {                
                if (! _viewKprCrtsChain())
                    MySystem.s_printOutExit(this, strMethod, "failed");
                 
                 // --
                 return;
            }
        }
        
        MySystem.s_printOutExit(this, strMethod, "uncaught evtAction.getSource()");
    }
    
    public void valueChanged(ListSelectionEvent evtListSelection)
    {
        String strMethod = "valueChanged(evtListSelection)";
        
        if (evtListSelection.getValueIsAdjusting())
        {
            return;
        }
        
        ListSelectionModel lsm = (ListSelectionModel) evtListSelection.getSource();
        
        if (lsm.isSelectionEmpty())
        {
            _reset_();
            return;
        }
        
        this._intSelectionRowCur_ = lsm.getMinSelectionIndex();
        
        _updateButtonEntry(); 
    }
    
    private void _updateButtonEntry()
    {
        String strMethod = "_updateButtonEntry()";
        
         if (this._intSelectionRowCur_ < 0)
            MySystem.s_printOutExit(this, strMethod, "this._intSelectionRowCur_ < 0");
        
        if (this._strsAlias_ == null)
            MySystem.s_printOutExit(this, strMethod, "nil this._strsAlias_");

        if (this._intSelectionRowCur_ > this._strsAlias_.length-1)
            MySystem.s_printOutExit(this, strMethod, "this._intSelectionRowCur_ > this._strsAlias_.length-1");
            
        _setEnabledButtonViewKprCrt(false);
        _setEnabledButtonVerifyKprCrt(false);
        _setEnabledButtonViewKprCrtsChain(false);
        _setEnabledButtonViewTcrCrt(false);
        _setEnabledButtonVerifyTcrCrt(false);
     
        Boolean boo = null;
        
        boo = this._boosIsEntryKpr_[this._intSelectionRowCur_];
        boolean blnGotIt = false;
        
        if (boo.booleanValue()) // privateKeyEntry?
        {
            blnGotIt = true;
            _updateButtonKpr();
        }
        
        else // trustedCertifcateEntry?, or secretKeyEntry?  
        {
            boo = this._boosIsEntryTcr_[this._intSelectionRowCur_];
            
            if (boo.booleanValue()) // trustedCertificateEntry
            {
                blnGotIt = true;
                _updateButtonTcr();
            }
            
            else // not done yet: secretKeyEntry?
            {
                // for now, bug
                MySystem.s_printOutExit(this, strMethod, "neither privateKeyEntry nor trustedCertificateEntry");
            }
            
        }
        
        if (! blnGotIt) // bug
        {
            MySystem.s_printOutExit(this, strMethod, "neither privateKeyEntry nor trustedCertificateEntry");
        }
        
        
    }
    
    private void _updateButtonKpr()
    {
        _updateButtonViewKprCrt();
        _updateButtonVerifyKprCrt();
        _updateButtonViewKprCrtsChain();
    }
    
    private void _updateButtonTcr()
    {
        _updateButtonViewTcrCrt();
        _updateButtonVerifyTcrCrt();
    }

    public boolean load(
        String[] strsAlias,
        Boolean[] boosIsEntryKpr,
        Boolean[] boosIsEntryTcr,
        Boolean[] boosIsSelfSignedCert,
        Boolean[] boosIsTrustedCert,
        String[] strsAlgoKeyPubl, // DSA v/s RSA
        String[] strsSizeKeyPubl,
        String[] strsTypeCert,
        String[] strsAlgoSigCert,
        Date[] dtesLastModified
        )
    {
        String strMethod = "load(...)";
        
        this._boosIsEntryKpr_ = boosIsEntryKpr;
        this._boosIsEntryTcr_ = boosIsEntryTcr;
        this._strsAlias_ = strsAlias;
        this._strsTypeCert = strsTypeCert;
        
        if (this._pnlTable_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlTable_");
            return false;
        }
        
        
        
        boolean blnOk = this._pnlTable_.load(
            strsAlias, 
            boosIsEntryKpr, boosIsEntryTcr, 
            boosIsSelfSignedCert, boosIsTrustedCert, 
            strsAlgoKeyPubl, 
            strsSizeKeyPubl,
            strsTypeCert, 
            strsAlgoSigCert, dtesLastModified);
            
        if (! blnOk)
        {
            MySystem.s_printOutError(this, strMethod, "! blnOk");
            return false;
        }
        
        
        _reset_();
        
        return true;
    }
    
    public void destroy()
    {
        super.destroy();
        
        if (this._pnlTable_ != null)
        {
            this._pnlTable_.destroy();
            this._pnlTable_ = null;
        }
        
        if (this._btnCancel != null)
        {
            this._btnCancel.destroy();
            this._btnCancel = null;
        }
        
        if (this._btnViewKprCrt != null)
        {
            this._btnViewKprCrt.destroy();
            this._btnViewKprCrt = null;
        }
        
        if (this._btnVerifyKprCrt != null)
        {
            this._btnVerifyKprCrt.destroy();
            this._btnVerifyKprCrt = null;
        }
        
        if (this._btnViewKprCrtsChain != null)
        {
            this._btnViewKprCrtsChain.destroy();
            this._btnViewKprCrtsChain = null;
        }
        
        if (this._btnViewTcrCrt != null)
        {
            this._btnViewTcrCrt.destroy();
            this._btnViewTcrCrt = null;
        }
        
        if (this._btnVerifyTcrCrt != null)
        {
            this._btnVerifyTcrCrt.destroy();
            this._btnVerifyTcrCrt = null;
        }
        
        this._pnlContentsAll = null;
        this._pnlButtonAll_ = null;
        this._pnlButtonKpr_ = null;
        this._pnlButtonTcr_ = null;
        this._pnlCloseThis = null;
        
        
        this._boosIsEntryKpr_ = null;
        this._boosIsEntryTcr_ = null;
        this._strsAlias_ = null;
        this._kstOpen_ = null;
    
 
    }
    
    
    public boolean init()
    {
        String strMethod = "init()";
        
        // ----
        
        if (! this._pnlTable_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // contents-all
        this._pnlContentsAll.setBorder(new javax.swing.border.EmptyBorder(4, 4, 4, 4));
        
        this._pnlContentsAll.add(this._pnlTable_, BorderLayout.CENTER);
        
        this._pnlContentsAll.add(this._pnlButtonAll_, BorderLayout.SOUTH);
        
        this._pnlButtonAll_.add(this._pnlButtonKpr_, BorderLayout.NORTH);
        this._pnlButtonAll_.add(this._pnlButtonTcr_, BorderLayout.CENTER);
        
        
        
        // contents-buttons
        // TEMPO this._pnlButtonKpr_.add(this._btnViewKprCrt);
        this._pnlButtonKpr_.add(this._btnViewKprCrtsChain);
        // TEMPO this._pnlButtonKpr_.add(this._btnVerifyKprCrt);
        
        this._pnlButtonTcr_.add(this._btnViewTcrCrt);
        // TEMPO this._pnlButtonTcr_.add(this._btnVerifyTcrCrt);
        
        // action
        
        this._pnlCloseThis.add(this._btnCancel);

        // all 
        
        getContentPane().add(this._pnlContentsAll, BorderLayout.CENTER);
        getContentPane().add(this._pnlCloseThis, BorderLayout.SOUTH);
        
        getRootPane().setDefaultButton(this._btnCancel);
        
        
        // ----
        return true;
    }
    
    // ---------
    // PROTECTED
    
    
    protected PTblEntryKprShowAll _pnlTable_ = null;
    
    protected JPanel _pnlButtonKpr_ = null;
    protected JPanel _pnlButtonTcr_ = null;
    
    protected JPanel _pnlButtonAll_ = null;
    
    protected Boolean[] _boosIsEntryKpr_ = null;
    protected Boolean[] _boosIsEntryTcr_ = null;
    protected String[] _strsAlias_ = null;
    protected KeyStore _kstOpen_ = null;
    
    protected int _intSelectionRowCur_ = -1;
    
  
    
    // change in the table, no more selection
    protected void _reset_()
    {
        this._intSelectionRowCur_ = -1;
        _setEnabledButtonViewKprCrt(false);
        _setEnabledButtonVerifyKprCrt(false);
        _setEnabledButtonViewKprCrtsChain(false);
        _setEnabledButtonViewTcrCrt(false);
        _setEnabledButtonVerifyTcrCrt(false);
    }
    
    protected String _getCurAlias_()
    {
        String strMethod = "_getCurAlias_()";
        
        if (this._strsAlias_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._strsAlias_");
            return null;
        }
        
        if (this._strsAlias_.length < 1)
        {
            MySystem.s_printOutError(this, strMethod, "this._strsAlias_.length < 1");
            return null;
        }
        
        if (this._intSelectionRowCur_<0 || this._intSelectionRowCur_>this._strsAlias_.length-1)
        {
            MySystem.s_printOutError(this, strMethod, "out of range, this._intSelectionRowCur_=" + this._intSelectionRowCur_);
            return null;
        }
        
        return this._strsAlias_[this._intSelectionRowCur_];
    }
    
    protected DTblKstAbs(
        Component cmpFrameOwner, 
     
        String strTitleThis,
        KeyStore kseLoaded
        )
    {
        super(
            (Frame) cmpFrameOwner, 
            //strTitleAppli + " - " + strTitleThis, // title
            true // true ==> modal=true
            ); 
            
        
    
        this._kstOpen_ = kseLoaded;
        
        // ----
        this._pnlTable_ = new PTblEntryKprShowAll((ListSelectionListener) this);
        
        this._btnCancel = new BESExit24((ActionListener) this);
        
        this._btnViewKprCrt = new BESView24((ActionListener) this);
        this._btnViewKprCrt.setToolTipText("View certificate");
        
        this._btnVerifyKprCrt = new BESVerify24((ActionListener) this);
        
        this._btnViewKprCrtsChain = new BESView24((ActionListener) this);
        this._btnViewKprCrtsChain.setToolTipText("view certificates chain");
        
        this._btnViewTcrCrt = new BESView24((ActionListener) this);
        this._btnViewTcrCrt.setToolTipText("view this certificate");
        
        this._btnVerifyTcrCrt = new BESVerify24((ActionListener) this);
        
        //
        
        this._pnlContentsAll = new JPanel();
        this._pnlButtonAll_ = new JPanel();
        this._pnlButtonKpr_ = new JPanel();
        this._pnlButtonTcr_ = new JPanel();
        this._pnlCloseThis = new JPanel();  
        

        // ----
        // layouts
        getContentPane().setLayout(new BorderLayout());
        this._pnlContentsAll.setLayout(new BorderLayout());
        this._pnlButtonAll_.setLayout(new BorderLayout());
        this._pnlButtonKpr_.setLayout(new BoxLayout(this._pnlButtonKpr_, BoxLayout.X_AXIS));
        this._pnlButtonTcr_.setLayout(new BoxLayout(this._pnlButtonTcr_, BoxLayout.X_AXIS));
        
        
        this._pnlCloseThis.setLayout(new FlowLayout(FlowLayout.CENTER)); 
        
        this._pnlButtonKpr_.setBorder(new TitledBorder("Highlighted keypair entry"));
        this._pnlButtonTcr_.setBorder(new TitledBorder("Highlighted trusted certificate entry"));
    }
    
    // -------
    // PRIVATE
    
    private BEnabledState _btnViewKprCrt; // not yet visible
    private BEnabledState _btnVerifyKprCrt; // not yet visible
    private BEnabledState _btnViewKprCrtsChain;
    private BEnabledState _btnViewTcrCrt;
    private BEnabledState _btnVerifyTcrCrt;
    
    private String[] _strsTypeCert = null;
    
    private BEnabledState _btnCancel = null;    
    
    private JPanel _pnlContentsAll = null;
    private JPanel _pnlCloseThis = null;
    
    
    private void _setEnabledButtonViewKprCrt(boolean bln)
    {
        if (this._btnViewKprCrt != null)
            this._btnViewKprCrt.setEnabled(bln);
    }
    
    private void _setEnabledButtonVerifyKprCrt(boolean bln)
    {
        if (this._btnVerifyKprCrt != null)
            this._btnVerifyKprCrt.setEnabled(bln);
    }
    
    private void _setEnabledButtonViewKprCrtsChain(boolean bln)
    {
        if (this._btnViewKprCrtsChain != null)
            this._btnViewKprCrtsChain.setEnabled(bln);
    }
    
    private void _setEnabledButtonViewTcrCrt(boolean bln)
    {
        if (this._btnViewTcrCrt != null)
            this._btnViewTcrCrt.setEnabled(bln);
    }
    
    private void _setEnabledButtonVerifyTcrCrt(boolean bln)
    {
        if (this._btnVerifyTcrCrt != null)
            this._btnVerifyTcrCrt.setEnabled(bln);
    }
    
    private boolean _viewEntryCrt()
    {
        String strMethod = "_viewEntryCrt()";
        
        // ---------
        // get alias
        
        
        String strEntryAlias = this._getCurAlias_();
        
        if (strEntryAlias == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strEntryAlias");
            return false;
        }
        
        // ---------------
        // get certs chain
        
        X509Certificate crtX509 = UtilCrtX509.s_getX509Certificate(
            this._kstOpen_, 
            strEntryAlias
            );
            
        if (crtX509 == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil crtX509");
            return false;
        }
        
        
        UtilCrtX509.s_show((Frame) getOwner(), crtX509);
        
        // ending
        return true;
    }
    
    private boolean _verifyEntryCrt()
    {
        String strMethod = "_verifyEntryCrt()";
        
        // ---------
        // get alias
        
        
        String strEntryAlias = this._getCurAlias_();
        
        if (strEntryAlias == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strEntryAlias");
            return false;
        }
        
        // ---------------
        // get certs chain
        
        X509Certificate crtX509 = UtilCrtX509.s_getX509Certificate(
            this._kstOpen_, 
            strEntryAlias
            );
            
        if (crtX509 == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil crtX509");
            return false;
        }
        
        
        //UtilCrtX509.s_show((Frame) getOwner(), crtX509);
        
        // TODO: IN PROGRESS
        /*
         *
         */
        
        // Get public key
        java.security.PublicKey pkyPublic = crtX509.getPublicKey();
        
        
        
        // ending
        return true;
    }
    
    private boolean _viewKprCrtsChain()
    {
        String strMethod = "_viewKprCrtsChain()";
        
        // ---------
        // get alias
        
        
        
        String strKprAlias = this._getCurAlias_();
        
        if (strKprAlias == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strKprAlias");
            return false;
        }
        
        // ---------------
        // get certs chain
        
        X509Certificate[] crtsX509 = UtilCrtX509.s_getX509CertificateChain(
            this._kstOpen_, 
            strKprAlias, 
            false // blnOrderChain
            );
            
        if (crtsX509 == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil crtsX509");
            return false;
        }
        
        if (crtsX509.length < 1)
        {
            MySystem.s_printOutError(this, strMethod, "crtsX509.length < 1");
            return false;
        }
        
        UtilCrtX509.s_showChain((Frame) getOwner(), crtsX509);
        
        // ending
        return true;
    }
    
    private void _updateButtonViewKprCrtsChain()
    {
        String strMethod = "_updateButtonViewKprCrtsChain()";
                     
        // --
        
        boolean blnIsCertChain = false;
        
        try
        {
            if (this._kstOpen_.getCertificateChain(this._getCurAlias_()) != null)
                blnIsCertChain = true;
        }
        
        catch(KeyStoreException excKeyStore)
        {
            excKeyStore.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "excKeyStore caught");
        }
        
        // --
        
        if (! blnIsCertChain)
            return;
            
        _setEnabledButtonViewKprCrtsChain(true);
    }
    
    private void _updateButtonViewKprCrt()
    {
        String strTypeCrtCur = this._strsTypeCert[this._intSelectionRowCur_];
        
        if (strTypeCrtCur.toLowerCase().compareTo(UtilCrtX509.f_s_strType.toLowerCase()) != 0)
            return;

        _setEnabledButtonViewKprCrt(true);
    }
    
    private void _updateButtonVerifyKprCrt()
    {
        String strTypeCrtCur = this._strsTypeCert[this._intSelectionRowCur_];
        
        if (strTypeCrtCur.toLowerCase().compareTo(UtilCrtX509.f_s_strType.toLowerCase()) != 0)
            return;

        _setEnabledButtonVerifyKprCrt(true);
    }
    
    private void _updateButtonViewTcrCrt()
    {
        String strTypeCrtCur = this._strsTypeCert[this._intSelectionRowCur_];
        
        if (strTypeCrtCur.toLowerCase().compareTo(UtilCrtX509.f_s_strType.toLowerCase()) != 0)
            return;

        _setEnabledButtonViewTcrCrt(true);
    }
    
    private void _updateButtonVerifyTcrCrt()
    {
        String strTypeCrtCur = this._strsTypeCert[this._intSelectionRowCur_];
        
        if (strTypeCrtCur.toLowerCase().compareTo(UtilCrtX509.f_s_strType.toLowerCase()) != 0)
            return;

        _setEnabledButtonVerifyTcrCrt(true);
    }
}