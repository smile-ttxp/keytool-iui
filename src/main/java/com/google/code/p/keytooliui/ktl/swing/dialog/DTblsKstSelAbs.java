
package com.google.code.p.keytooliui.ktl.swing.dialog;

import java.awt.Component;
import java.awt.event.MouseListener;
import java.security.KeyStore;
import java.util.Date;
import javax.swing.event.ListSelectionListener;
import com.google.code.p.keytooliui.ktl.swing.panel.PTblEntPKTCSelAll;
import com.google.code.p.keytooliui.ktl.swing.panel.PTblEntSKSelAll;
import com.google.code.p.keytooliui.shared.lang.MySystem;

public abstract class DTblsKstSelAbs extends DTblsKstAbs 
{
    // --------------------
    // private static final
    

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
        String strTitleThis,
        KeyStore kseLoaded,
        String strPathAbs,
        String strBodyButtonUsage)
    {
        super(
            cmpFrameOwner, 
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