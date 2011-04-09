package com.google.code.p.keytooliui.ktl.swing.dialog;

/**
 *
 *MEMO: in progress: toolbarIcon "Import to this keystore ..."
 *  that should open a popupMenu
**/

import com.google.code.p.keytooliui.ktl.util.jarsigner.*;
import com.google.code.p.keytooliui.ktl.swing.button.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.button.*;

import javax.swing.*;
import javax.swing.event.*;

// --
import java.security.KeyStore;
// --
// --

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public final class DTblsKstManage extends DTblsKstViewAbs
{   
    // -------------------
    // PUBLIC STATIC FINAL
    
    public static final String f_s_strTitleThisSuffix = "keystore manager";
    
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _STR_importSK2Kst = "Import secret key entry from file in signature algorithm";
    
    private static final String _STR_deleteEntryPK = "Delete private key entry ...";
    private static final String _STR_deleteEntryTC = "Delete trusted certificate entry ...";
    private static final String _STR_deleteEntrySK = "Delete secret key entry ...";
    
    private static final String _STR_renameAliasPK = "Rename private key alias ...";
    private static final String _STR_renameAliasTC = "Rename trusted certificate alias ...";
    private static final String _STR_renameAliasSK = "Rename secret key alias ...";
    
    private static final String _STR_copyEntryPK = "Copy private key entry ...";
    private static final String _STR_copyEntryTC = "Copy trusted certificate entry ...";
    private static final String _STR_copyEntrySK = "Copy secret key entry ...";
    
    private static final String _STR_changePasswordKeyPK = "Change private key's password ...";
    private static final String _STR_changePasswordKeySK = "Change secret key's password ...";
   
    
    // ------
    // PUBLIC
    
    public void mouseClicked(MouseEvent evtMouse) 
    {
        if (evtMouse.getSource() == this._btnPopupKstImport)
        {
            this._pmuKstImport.show((Component) (evtMouse.getSource()) , evtMouse.getX(), evtMouse.getY());
            return;
        }
            
        super.mouseClicked(evtMouse);
    }
    
    
    
    public void valueChanged(ListSelectionEvent evtListSelection)
    {
        String strMethod = "valueChanged(evtListSelection)";
        
        super.valueChanged(evtListSelection);
        
        /*if (evtListSelection.getValueIsAdjusting())
        {
            return;
        }
        
        ListSelectionModel lsm = (ListSelectionModel) evtListSelection.getSource();
        
        if (lsm.isSelectionEmpty())
        {
            // already done thanks to superclass's method
            return;
        }
        
        _updateButtonsKpr();
        _updateButtonsTcr();
        */
    }
    
    private boolean _renameAliasPK()
    {
        String strMethod = "_renameAliasPK()";
        
        String strAliasKpr = super._getCurAliasPKTC_();
        
        if (strAliasKpr == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strAliasKpr");
            return false;
        }
        
        
        KTLKprOpenManAbs ktl = new KTLKprOpenManChgAlias(
            (Frame) getOwner(), 
      
        
            // input
            super._strPathAbs_, // existing keystore of type JKS/JCEKS/?PKCS12? 
            this._chrsPasswdOpenKst,
            this._strProviderKst,
        
            super._kstOpen_,
            strAliasKpr
            );
        
        
        if (! ktl.doJob()) // could be cancelled by user
        {
            MySystem.s_printOutTrace(this, strMethod, "! ktl.doJob()");
            return true;
        }
        
        
        KeyStore kstOpenChanged = ktl.getKstOpen(); // coz changed and saved
        
        if (kstOpenChanged == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil kstOpenChanged");
            return false;
        }
        
        String strAliasNew = ((KTLKprOpenManChgAlias) ktl).getAliasNew();
        
        if (strAliasNew == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strAliasNew");
            return false;
        }
        
        
        // assign newly changed keystore
        super._kstOpen_ = kstOpenChanged;
        
        // reload
        
        if (! _reloadPKTC())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // highlight new alias
        
        if (super._strsAliasPKTC_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._strsAliasPKTC_");
            return false;
        }
        
        
        int intIdRow = -1;
        
        for (int i=0; i<super._strsAliasPKTC_.length; i++)
        {
            String strCur = super._strsAliasPKTC_[i];
            
            if (strCur.toLowerCase().compareTo(strAliasNew.toLowerCase()) == 0)
            {
                intIdRow = i;
                break;
            }
        }
        
        if (intIdRow == -1)
        {
            MySystem.s_printOutError(this, strMethod, "intIdRow == -1");
            return false;
        }
        
        if (super._pnlTablePKTC_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._pnlTablePKTC_");
            return false;
        }
        
        if (! super._pnlTablePKTC_.updateSelectionRowId(intIdRow))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        // ending
        return true;
    }
    
    private boolean _renameAliasTC()
    {
        String strMethod = "_renameAliasTC()";
        
        String strAliasTcr = super._getCurAliasPKTC_();
        
        if (strAliasTcr == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strAliasTcr");
            return false;
        }
        
        
        KTLTcrOpenManAbs ktl = new KTLTcrOpenManChgAlias(
            (Frame) getOwner(), 

        
            // input
            super._strPathAbs_, // existing keystore of type JKS/JCEKS/?PKCS12? 
            this._chrsPasswdOpenKst,
            this._strProviderKst,
        
            super._kstOpen_,
            strAliasTcr
            );
        
        
        if (! ktl.doJob()) // could be cancelled by user
        {
            MySystem.s_printOutTrace(this, strMethod, "! ktl.doJob()");
            return true;
        }
        
        
        KeyStore kstOpenChanged = ktl.getKstOpen(); // coz changed and saved
        
        if (kstOpenChanged == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil kstOpenChanged");
            return false;
        }
        
        String strAliasNew = ((KTLTcrOpenManChgAlias) ktl).getAliasNew();
        
        if (strAliasNew == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strAliasNew");
            return false;
        }
        
        
        // assign newly changed keystore
        super._kstOpen_ = kstOpenChanged;
        
        // reload
        
        if (! _reloadPKTC())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // highlight new alias
        
        if (super._strsAliasPKTC_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._strsAliasPKTC_");
            return false;
        }
        
        
        int intIdRow = -1;
        
        for (int i=0; i<super._strsAliasPKTC_.length; i++)
        {
            String strCur = super._strsAliasPKTC_[i];
            
            if (strCur.toLowerCase().compareTo(strAliasNew.toLowerCase()) == 0)
            {
                intIdRow = i;
                break;
            }
        }
        
        if (intIdRow == -1)
        {
            MySystem.s_printOutError(this, strMethod, "intIdRow == -1");
            return false;
        }
        
        if (super._pnlTablePKTC_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._pnlTablePKTC_");
            return false;
        }
        
        if (! super._pnlTablePKTC_.updateSelectionRowId(intIdRow))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        // ending
        return true;
    }
    
    private boolean _renameAliasSK()
    {
        String strMethod = "_renameAliasSK()";
        
        String strAliasShk = super._getCurAliasSK_();
        
        if (strAliasShk == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strAliasShk");
            return false;
        }
        
        
        KTLShkOpenManAbs ktl = new KTLShkOpenManChgAlias(
            (Frame) getOwner(), 
     
        
            // input
            super._strPathAbs_, // existing keystore of type JKS/JCEKS/?PKCS12? 
            this._chrsPasswdOpenKst,
            this._strProviderKst,
        
            super._kstOpen_,
            strAliasShk
            );
        
        
        if (! ktl.doJob()) // could be cancelled by user
        {
            MySystem.s_printOutTrace(this, strMethod, "! ktl.doJob()");
            return true;
        }
        
        
        KeyStore kstOpenChanged = ktl.getKstOpen(); // coz changed and saved
        
        if (kstOpenChanged == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil kstOpenChanged");
            return false;
        }
        
        String strAliasNew = ((KTLShkOpenManChgAlias) ktl).getAliasNew();
        
        if (strAliasNew == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strAliasNew");
            return false;
        }
        
        
        // assign newly changed keystore
        super._kstOpen_ = kstOpenChanged;
        
        // reload
        
        if (! _reloadSK())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // highlight new alias
        
        if (super._strsAliasSK_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._strsAliasSK_");
            return false;
        }
        
        
        int intIdRow = -1;
        
        for (int i=0; i<super._strsAliasSK_.length; i++)
        {
            String strCur = super._strsAliasSK_[i];
            
            if (strCur.toLowerCase().compareTo(strAliasNew.toLowerCase()) == 0)
            {
                intIdRow = i;
                break;
            }
        }
        
        if (intIdRow == -1)
        {
            MySystem.s_printOutError(this, strMethod, "intIdRow == -1");
            return false;
        }
        
        if (super._pnlTableSK_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._pnlTableSK_");
            return false;
        }
        
        if (! super._pnlTableSK_.updateSelectionRowId(intIdRow))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        // ending
        return true;
    }
    
    private boolean _copyEntryPK()
    {
        String strMethod = "_copyEntryPK()";

        String strAliasKpr = super._getCurAliasPKTC_();
        
        if (strAliasKpr == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strAliasKpr");
            return false;
        }
        
        
        KTLKprOpenManAbs ktl = new KTLKprOpenManCopy(
            (Frame) getOwner(), 
      
        
            // input
            super._strPathAbs_, // existing keystore of type JKS/JCEKS/?PKCS12? 
            this._chrsPasswdOpenKst,
            this._strProviderKst,
        
            super._kstOpen_,
            strAliasKpr
            );
        
        
        if (! ktl.doJob()) // could be cancelled by user
        {
            MySystem.s_printOutTrace(this, strMethod, "! ktl.doJob()");
            return true;
        }
        
        
        KeyStore kstOpenChanged = ktl.getKstOpen(); // coz changed and saved
        
        if (kstOpenChanged == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil kstOpenChanged");
            return false;
        }
        
        String strAliasNew = ((KTLKprOpenManCopy) ktl).getAliasNew();
        
        if (strAliasNew == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strAliasNew");
            return false;
        }
        
        
        // assign newly changed keystore
        super._kstOpen_ = kstOpenChanged;
        
        // reload
        
        if (! _reloadPKTC())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // highlight new alias
        
        if (super._strsAliasPKTC_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._strsAliasPKTC_");
            return false;
        }
        
        
        int intIdRow = -1;
        
        for (int i=0; i<super._strsAliasPKTC_.length; i++)
        {
            String strCur = super._strsAliasPKTC_[i];
            
            if (strCur.toLowerCase().compareTo(strAliasNew.toLowerCase()) == 0)
            {
                intIdRow = i;
                break;
            }
        }
        
        if (intIdRow == -1)
        {
            MySystem.s_printOutError(this, strMethod, "intIdRow == -1");
            return false;
        }
        
        if (super._pnlTablePKTC_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._pnlTablePKTC_");
            return false;
        }
        
        if (! super._pnlTablePKTC_.updateSelectionRowId(intIdRow))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    private boolean _copyEntryTC()
    {
        String strMethod = "_copyEntryTC()";

        String strAliasTcr = super._getCurAliasPKTC_();
        
        if (strAliasTcr == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strAliasTcr");
            return false;
        }
        
        
        KTLTcrOpenManAbs ktl = new KTLTcrOpenManCopy(
            (Frame) getOwner(), 

        
            // input
            super._strPathAbs_, // existing keystore of type JKS/JCEKS/?PKCS12? 
            this._chrsPasswdOpenKst,
            this._strProviderKst,
        
            super._kstOpen_,
            strAliasTcr
            );
        
        
        if (! ktl.doJob()) // could be cancelled by user
        {
            MySystem.s_printOutTrace(this, strMethod, "! ktl.doJob()");
            return true;
        }
        
        
        KeyStore kstOpenChanged = ktl.getKstOpen(); // coz changed and saved
        
        if (kstOpenChanged == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil kstOpenChanged");
            return false;
        }
        
        String strAliasNew = ((KTLTcrOpenManCopy) ktl).getAliasNew();
        
        if (strAliasNew == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strAliasNew");
            return false;
        }
        
        
        // assign newly changed keystore
        super._kstOpen_ = kstOpenChanged;
        
        // reload
        
        if (! _reloadPKTC())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // highlight new alias
        
        if (super._strsAliasPKTC_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._strsAliasPKTC_");
            return false;
        }
        
        
        int intIdRow = -1;
        
        for (int i=0; i<super._strsAliasPKTC_.length; i++)
        {
            String strCur = super._strsAliasPKTC_[i];
            
            if (strCur.toLowerCase().compareTo(strAliasNew.toLowerCase()) == 0)
            {
                intIdRow = i;
                break;
            }
        }
        
        if (intIdRow == -1)
        {
            MySystem.s_printOutError(this, strMethod, "intIdRow == -1");
            return false;
        }
        
        if (super._pnlTablePKTC_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._pnlTablePKTC_");
            return false;
        }
        
        if (! super._pnlTablePKTC_.updateSelectionRowId(intIdRow))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    private boolean _copyEntrySK()
    {
        String strMethod = "_copyEntrySK()";

        String strAliasShk = super._getCurAliasSK_();
        
        if (strAliasShk == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strAliasShk");
            return false;
        }
        
        
        KTLShkOpenManAbs ktl = new KTLShkOpenManCopy(
            (Frame) getOwner(), 
      
        
            // input
            super._strPathAbs_, // existing keystore of type JKS/JCEKS/?PKCS12? 
            this._chrsPasswdOpenKst,
            this._strProviderKst,
        
            super._kstOpen_,
            strAliasShk
            );
        
        
        if (! ktl.doJob()) // could be cancelled by user
        {
            MySystem.s_printOutTrace(this, strMethod, "! ktl.doJob()");
            return true;
        }
        
        
        KeyStore kstOpenChanged = ktl.getKstOpen(); // coz changed and saved
        
        if (kstOpenChanged == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil kstOpenChanged");
            return false;
        }
        
        String strAliasNew = ((KTLShkOpenManCopy) ktl).getAliasNew();
        
        if (strAliasNew == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strAliasNew");
            return false;
        }
        
        
        // assign newly changed keystore
        super._kstOpen_ = kstOpenChanged;
        
        // reload
        
        if (! _reloadSK())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // highlight new alias
        
        if (super._strsAliasSK_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._strsAliasSK_");
            return false;
        }
        
        
        int intIdRow = -1;
        
        for (int i=0; i<super._strsAliasSK_.length; i++)
        {
            String strCur = super._strsAliasSK_[i];
            
            if (strCur.toLowerCase().compareTo(strAliasNew.toLowerCase()) == 0)
            {
                intIdRow = i;
                break;
            }
        }
        
        if (intIdRow == -1)
        {
            MySystem.s_printOutError(this, strMethod, "intIdRow == -1");
            return false;
        }
        
        if (super._pnlTableSK_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._pnlTableSK_");
            return false;
        }
        
        if (! super._pnlTableSK_.updateSelectionRowId(intIdRow))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    private boolean _importSK2Kst(String strSignatureAlgo)
    {
        String strMethod = "_importSK2Kst(strSignatureAlgo)";
        MySystem.s_printOutWarning(this, strMethod, "<<<< IN PROGRESS >>>>");
        
        return true;
    }
    
    private boolean _deleteEntryPK()
    {
        String strMethod = "_deleteEntryPK()";
        
        
        String strAliasKpr = super._getCurAliasPKTC_();
        
        String strAliasEntryPrev = null;
        
        if (super._intSelectionRowCurPKTC_-1 > 0)
        {
            strAliasEntryPrev = super._strsAliasPKTC_[super._intSelectionRowCurPKTC_-1];
        }
        
        if (strAliasKpr == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strAliasKpr");
            return false;
        }
        
        
        KTLKprOpenManAbs ktl = new KTLKprOpenManDelete(
            (Frame) getOwner(), 
      
        
            // input
            super._strPathAbs_, // existing keystore of type JKS/JCEKS/?PKCS12? 
            this._chrsPasswdOpenKst,
            this._strProviderKst,
        
            super._kstOpen_,
            strAliasKpr
            );
        
        
        if (! ktl.doJob()) // could be cancelled by user
        {
            MySystem.s_printOutTrace(this, strMethod, "! ktl.doJob()");
            return true;
        }
        
        
        KeyStore kstOpenChanged = ktl.getKstOpen(); // coz changed and saved
        
        if (kstOpenChanged == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil kstOpenChanged");
            return false;
        }
        
        // assign newly changed keystore
        super._kstOpen_ = kstOpenChanged;
        
        // reload
        
        if (! _reloadPKTC())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // highlight prev alias
        
        if (strAliasEntryPrev == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil strAliasEntryPrev");
            return true;
        }
        
        if (super._strsAliasPKTC_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._strsAliasPKTC_");
            return false;
        }
        
        int intIdRow = -1;
        
        for (int i=0; i<super._strsAliasPKTC_.length; i++)
        {
            String strCur = super._strsAliasPKTC_[i];
            
            if (strCur.toLowerCase().compareTo(strAliasEntryPrev.toLowerCase()) == 0)
            {
                intIdRow = i;
                break;
            }
        }
        
        if (intIdRow == -1)
        {
            MySystem.s_printOutError(this, strMethod, "intIdRow == -1");
            return false;
        }
        
        if (super._pnlTablePKTC_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._pnlTablePKTC_");
            return false;
        }
        
        if (! super._pnlTablePKTC_.updateSelectionRowId(intIdRow))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    private boolean _deleteEntryTC()
    {
        String strMethod = "_deleteEntryTC()";
        
        
        String strAliasTcr = super._getCurAliasPKTC_();
        
        String strAliasEntryPrev = null;
        
        if (super._intSelectionRowCurPKTC_-1 > 0)
        {
            strAliasEntryPrev = super._strsAliasPKTC_[super._intSelectionRowCurPKTC_-1];
        }
        
        if (strAliasTcr == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strAliasTcr");
            return false;
        }
        
        
        KTLTcrOpenManAbs ktl = new KTLTcrOpenManDelete(
            (Frame) getOwner(), 
        
        
            // input
            super._strPathAbs_, // existing keystore of type JKS/JCEKS/?PKCS12? 
            this._chrsPasswdOpenKst,
            this._strProviderKst,
        
            super._kstOpen_,
            strAliasTcr
            );
        
        
        if (! ktl.doJob()) // could be cancelled by user
        {
            MySystem.s_printOutTrace(this, strMethod, "! ktl.doJob()");
            return true;
        }
        
        
        KeyStore kstOpenChanged = ktl.getKstOpen(); // coz changed and saved
        
        if (kstOpenChanged == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil kstOpenChanged");
            return false;
        }
        
        // assign newly changed keystore
        super._kstOpen_ = kstOpenChanged;
        
        // reload
        
        if (! _reloadPKTC())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // highlight prev alias
        
        if (strAliasEntryPrev == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil strAliasEntryPrev");
            return true;
        }
        
        if (super._strsAliasPKTC_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._strsAlias_");
            return false;
        }
        
        int intIdRow = -1;
        
        for (int i=0; i<super._strsAliasPKTC_.length; i++)
        {
            String strCur = super._strsAliasPKTC_[i];
            
            if (strCur.toLowerCase().compareTo(strAliasEntryPrev.toLowerCase()) == 0)
            {
                intIdRow = i;
                break;
            }
        }
        
        if (intIdRow == -1)
        {
            MySystem.s_printOutError(this, strMethod, "intIdRow == -1");
            return false;
        }
        
        if (super._pnlTablePKTC_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._pnlTablePKTC_");
            return false;
        }
        
        if (! super._pnlTablePKTC_.updateSelectionRowId(intIdRow))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    private boolean _deleteEntrySK()
    {
        String strMethod = "_deleteEntrySK()";
        
        
        String strAliasShk = super._getCurAliasSK_();
        
        String strAliasEntryPrev = null;
        
        if (super._intSelectionRowCurSK_-1 > 0)
        {
            strAliasEntryPrev = super._strsAliasSK_[super._intSelectionRowCurSK_-1];
        }
        
        if (strAliasShk == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strAliasShk");
            return false;
        }
        
        
        KTLShkOpenManAbs ktl = new KTLShkOpenManDelete(
            (Frame) getOwner(), 
 
        
            // input
            super._strPathAbs_, // existing keystore of type JKS/JCEKS/?PKCS12? 
            this._chrsPasswdOpenKst,
            this._strProviderKst,
        
            super._kstOpen_,
            strAliasShk
            );
        
        
        if (! ktl.doJob()) // could be cancelled by user
        {
            MySystem.s_printOutTrace(this, strMethod, "! ktl.doJob()");
            return true;
        }
        
        
        KeyStore kstOpenChanged = ktl.getKstOpen(); // coz changed and saved
        
        if (kstOpenChanged == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil kstOpenChanged");
            return false;
        }
        
        // assign newly changed keystore
        super._kstOpen_ = kstOpenChanged;
        
        // reload
        
        if (! _reloadSK())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // highlight prev alias
        
        if (strAliasEntryPrev == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil strAliasEntryPrev");
            return true;
        }
        
        if (super._strsAliasSK_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._strsAliasSK_");
            return false;
        }
        
        int intIdRow = -1;
        
        for (int i=0; i<super._strsAliasSK_.length; i++)
        {
            String strCur = super._strsAliasSK_[i];
            
            if (strCur.toLowerCase().compareTo(strAliasEntryPrev.toLowerCase()) == 0)
            {
                intIdRow = i;
                break;
            }
        }
        
        if (intIdRow == -1)
        {
            MySystem.s_printOutError(this, strMethod, "intIdRow == -1");
            return false;
        }
        
        if (super._pnlTableSK_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._pnlTableSK_");
            return false;
        }
        
        if (! super._pnlTableSK_.updateSelectionRowId(intIdRow))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    public void actionPerformed(ActionEvent evtAction) 
    {
        String strMethod = "actionPerformed(evtAction)";
        
        if (evtAction.getSource() instanceof JMenuItem)
        {
            JMenuItem mimSource = (JMenuItem) evtAction.getSource();
            
            // import sk
            
            for (int i=0; i<KTLAbs.f_s_strsSigAlgoSKJceks.length; i++)
            {
                if (mimSource.getText().compareTo(KTLAbs.f_s_strsSigAlgoSKJceks[i]) == 0)
                {
                    // got it
                    if (! this._importSK2Kst(mimSource.getText()))
                    {
                        MySystem.s_printOutExit(this, strMethod, "failed");
                    }
                    
                    return;
                }
            }
            
            // ----
            
            if (mimSource.getText().compareTo(DTblsKstManage._STR_renameAliasPK) == 0)
            {
                if (! this._renameAliasPK())
                    MySystem.s_printOutExit(this, strMethod, "failed");
                    
                return;
            }
            
            if (mimSource.getText().compareTo(DTblsKstManage._STR_renameAliasTC) == 0)
            {
                if (! this._renameAliasTC())
                    MySystem.s_printOutExit(this, strMethod, "failed");
                
                return;
            }
            
            if (mimSource.getText().compareTo(DTblsKstManage._STR_renameAliasSK) == 0)
            {
                if (! this._renameAliasSK())
                    MySystem.s_printOutExit(this, strMethod, "failed");
                
                return;
            }
            
            // 
            
            // ----
            
            if (mimSource.getText().compareTo(DTblsKstManage._STR_changePasswordKeyPK) == 0)
            {
                if (! this._changePasswordKeyPK())
                    MySystem.s_printOutExit(this, strMethod, "failed");
                    
                return;
            }
            
            if (mimSource.getText().compareTo(DTblsKstManage._STR_changePasswordKeySK) == 0)
            {
                if (! this._changePasswordKeySK())
                    MySystem.s_printOutExit(this, strMethod, "failed");
                    
                return;
            }
            
            // ----

            
            // ----
            
            if (mimSource.getText().compareTo(DTblsKstManage._STR_deleteEntryPK) == 0)
            {
                if (! this._deleteEntryPK())
                    MySystem.s_printOutExit(this, strMethod, "failed");
                    
                return;
            }
            
            if (mimSource.getText().compareTo(DTblsKstManage._STR_deleteEntryTC) == 0)
            {
                if (! this._deleteEntryTC())
                    MySystem.s_printOutExit(this, strMethod, "failed");
                
                return;
            }
            
            if (mimSource.getText().compareTo(DTblsKstManage._STR_deleteEntrySK) == 0)
            {
                if (! this._deleteEntrySK())
                    MySystem.s_printOutExit(this, strMethod, "failed");
                
                return;
            }
            
            // ----
            
            if (mimSource.getText().compareTo(DTblsKstManage._STR_copyEntryPK) == 0)
            {
                if (! this._copyEntryPK())
                    MySystem.s_printOutExit(this, strMethod, "failed");
                    
                return;
            }
            
            if (mimSource.getText().compareTo(DTblsKstManage._STR_copyEntryTC) == 0)
            {
                if (! this._copyEntryTC())
                    MySystem.s_printOutExit(this, strMethod, "failed");
                
                return;
            }
            
            if (mimSource.getText().compareTo(DTblsKstManage._STR_copyEntrySK) == 0)
            {
                if (! this._copyEntrySK())
                    MySystem.s_printOutExit(this, strMethod, "failed");
                
                return;
            }
            
            // ----
        }
        
        if (evtAction.getSource() instanceof JButton)
        {
            JButton btnSource = (JButton) evtAction.getSource();
            
            if (btnSource == this._btnChangePasswordKst)
            {                
                if (! _changePasswordKst())
                    MySystem.s_printOutExit(this, strMethod, "failed");
                 
                 // --
                 return;
            }
           
            /**/
        }
        
        super.actionPerformed(evtAction);
        
        
        
        
        /*if (evtAction.getSource() instanceof JButton)
        {
            JButton btnSource = (JButton) evtAction.getSource();
            
            if (btnSource == this._btnChgAliasKpr)
            {                
                if (! _changeAliasKpr())
                    MySystem.s_printOutExit(this, strMethod, "failed");
                 
                 // --
                 return;
            }
            
            if (btnSource == this._btnChgAliasTcr)
            {                
                if (! _changeAliasTcr())
                    MySystem.s_printOutExit(this, strMethod, "failed");
                 
                 // --
                 return;
            }
            
            if (this._btnChangePasswordKpr != null)
            {
                if (btnSource == this._btnChangePasswordKpr)
                {                
                    if (! _changePasswordKpr())
                        MySystem.s_printOutExit(this, strMethod, "failed");
                     
                    // --
                    return;
                }
            }
            
            if (this._btnSignKpr != null)
            {
                if (btnSource == this._btnSignKpr)
                {                
                    if (! _signKpr())
                        MySystem.s_printOutExit(this, strMethod, "failed");
                     
                    // --
                    return;
                }
            }
            
            if (btnSource == this._btnChangePasswordKst)
            {                
                if (! _changePasswordKst())
                    MySystem.s_printOutExit(this, strMethod, "failed");
                 
                 // --
                 return;
            }
            
            if (btnSource == this._btnCopyKpr)
            {                
                if (! _copyEntryKpr())
                    MySystem.s_printOutExit(this, strMethod, "failed");
                 
                 // --
                 return;
            }
            
            if (btnSource == this._btnDeleteTcr)
            {                
                if (! _deleteTcr())
                    MySystem.s_printOutExit(this, strMethod, "failed");
                 
                 // --
                 return;
            }
            
        
        }*/
        
        
    }
    
    public boolean init()
    {
        if (! super.init())
            return false;
        
        // popup menus
        _createPopupMenuKstImport();
        
        _createPopupMenuPK();
        _createPopupMenuTC();
        _createPopupMenuSK();
        
        super._tbr_.add(this._btnChangePasswordKst);
        
        // TEMPO, IN PROGRESS
        //super._tbr_.add(this._btnPopupKstImport);
        
        super._addToolbarButtonExit_();
        
        // add btn kpr, always before the last one "view certs chain", coz not an image icon
        
        /*super._pnlButtonKpr_.add(this._btnDeleteKpr);
        super._pnlButtonKpr_.add(this._btnChgAliasKpr);
        super._pnlButtonKpr_.add(this._btnCopyKpr);
        
        if (this._btnChangePasswordKpr != null)
            super._pnlButtonKpr_.add(this._btnChangePasswordKpr);
        */
        
        // TEMPO if (this._btnSignKpr != null)
            // TEMPO super._pnlButtonKpr_.add(this._btnSignKpr/*, super._pnlButtonKpr_.getComponentCount()-1*/);
        
        // add btn tcr
        
        /*super._pnlButtonTcr_.add(this._btnDeleteTcr);
        super._pnlButtonTcr_.add(this._btnChgAliasTcr);
        
        // add btn kst    
        this._pnlButtonKst.add(this._btnChangePasswordKst);
            
        // add all
        super._pnlButtonAll_.add(this._pnlButtonKst, BorderLayout.SOUTH);*/
        
        // ending
        return true;
    }
    
    private void _createPopupMenuKstImport()
    {
        String strMethod = "_createPopupMenuKstImport()";
        
        this._pmuKstImport = new JPopupMenu();
        
        JMenu men = new JMenu(DTblsKstManage._STR_importSK2Kst);

        //JMenuItem mim = null;
        
        // ----
        //mim = new JMenuItem(DTblsKstManage._STR_importSK2Kst);
        
        for (int i=0; i<KTLAbs.f_s_strsSigAlgoSKJceks.length; i++)
        {
            JMenuItem mimCur = new JMenuItem(KTLAbs.f_s_strsSigAlgoSKJceks[i]);
            mimCur.addActionListener(this);
            men.add(mimCur);
        }
        
        
        
        
        // DO NOT SHOW button right now
        // memo: this button should allow to show popup menu "import secret key | of signature format ...."
        MySystem.s_printOutFlagDev(this, strMethod, "PENDING ...");
        //this._pmuKstImport.add(men);
    }
    
    private void _createPopupMenuPK()
    {
        JMenuItem mim = null;
        
        // ----
        mim = new JMenuItem(DTblsKstManage._STR_deleteEntryPK);
        super._addItemToMenuPK_(mim);
        
        // ----
        mim = new JMenuItem(DTblsKstManage._STR_renameAliasPK);
        super._addItemToMenuPK_(mim);
        
        // ----
        mim = new JMenuItem(DTblsKstManage._STR_copyEntryPK);
        super._addItemToMenuPK_(mim);
        
        // ----
        // ----
        // modif feb 19, 08: if PKCS12: don't allow to change password
        
        if (super._kstOpen_.getType().toLowerCase().compareTo(
                com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstPkcs12.f_s_strKeystoreType.toLowerCase()) != 0)
        {
            mim = new JMenuItem(DTblsKstManage._STR_changePasswordKeyPK);
            super._addItemToMenuPK_(mim);
        }
        
    }
    
    private void _createPopupMenuTC()
    {
        JMenuItem mim = null;
        
        // ----
        mim = new JMenuItem(DTblsKstManage._STR_deleteEntryTC);
        super._addItemToMenuTC_(mim);
        
        // ----
        mim = new JMenuItem(DTblsKstManage._STR_renameAliasTC);
        super._addItemToMenuTC_(mim);
        
        // ----
        mim = new JMenuItem(DTblsKstManage._STR_copyEntryTC);
        super._addItemToMenuTC_(mim);
    }
    
    private void _createPopupMenuSK()
    {
        JMenuItem mim = null;
        
        // ----
        mim = new JMenuItem(DTblsKstManage._STR_deleteEntrySK);
        super._addItemToMenuSK_(mim);
        
        // ----
        mim = new JMenuItem(DTblsKstManage._STR_renameAliasSK);
        super._addItemToMenuSK_(mim);
        
        // ----
        mim = new JMenuItem(DTblsKstManage._STR_copyEntrySK);
        super._addItemToMenuSK_(mim);
        
        
        mim = new JMenuItem(DTblsKstManage._STR_changePasswordKeySK);
        super._addItemToMenuSK_(mim);
        
    }

    public DTblsKstManage(
        Component cmpFrameOwner, 

        KeyStore kseLoaded,
        
        String strPathAbsOpenKst,
        char[] chrsPasswdOpenKst,
        String strProviderKst
        )
    {
        super(
            cmpFrameOwner, 
        
            kseLoaded.getType() + " " + DTblsKstManage.f_s_strTitleThisSuffix,
            kseLoaded,
            strPathAbsOpenKst,
            (String) null // strBodyButtonUsage
            ); 
        
        // --
        super._strPathAbs_ = strPathAbsOpenKst;
        this._chrsPasswdOpenKst = chrsPasswdOpenKst;
        this._strProviderKst = strProviderKst;
        
        
        this._btnChangePasswordKst = new BESPassword24(this, BESPasswordAbs.f_s_intModeChange);
        
        this._btnChangePasswordKst.setToolTipText("Change this keystore's password ...");
        this._btnChangePasswordKst.setEnabled(true);
        
        this._btnPopupKstImport = new BESImport24((ActionListener) null);
        this._btnPopupKstImport.addMouseListener(this);
        this._btnPopupKstImport.setToolTipText("Import to this keystore ...");
        this._btnPopupKstImport.setEnabled(true);
        
        
    }
    
    // -------
    // PRIVATE
    
    private JButton _btnChangePasswordKst = null;
    private JButton _btnPopupKstImport = null;
    private char[] _chrsPasswdOpenKst = null;
    private String _strProviderKst = null;
    
    private boolean _changePasswordKst()
    {
        String strMethod = "_changePasswordKst()";

        KTLKstOpenAbs ktl = new KTLKstOpenManChgPasswd(
            (Frame) getOwner(), 
         
        
            // input
            super._strPathAbs_, // existing keystore of type JKS/JCEKS/?PKCS12? 
            this._chrsPasswdOpenKst,
            this._strProviderKst,
        
            super._kstOpen_
            );
        
        
        if (! ktl.doJob()) // could be cancelled by user
        {
            MySystem.s_printOutTrace(this, strMethod, "! ktl.doJob()");
            return true;
        }
        
        
        KeyStore kstOpenChanged = ((KTLKstOpenManChgPasswd) ktl).getKstOpen(); // coz changed and saved
        
        if (kstOpenChanged == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil kstOpenChanged");
            return false;
        }
        
        char[] chrsPasswdNewKst = ((KTLKstOpenManChgPasswd) ktl).getPasswordNew();
        
        if (chrsPasswdNewKst == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil chrsPasswdNewKst");
            return false;
        }
        
        
        // update changed and saved keystore
        super._kstOpen_ = kstOpenChanged;
        
        // update changed and saved keystore's respective password
        this._chrsPasswdOpenKst = chrsPasswdNewKst;
        
        // --
        // no need for reloading
        // no need for highlighting any alias
        
        
        
        // ending
        return true;
    }
   
    
    
    // a change has been made in the table, eg: alias changed, cloned entry, deleted entry, ...
    private boolean _reloadPKTC()
    {
        String strMethod = "_reloadPKTC()";
        
        // ----
        // get aliases
        
        String[] strsAlias = UtilKstAbs.s_getStrsAlias(
            (Frame) getOwner(), 
            
            super._kstOpen_);
        
        if (strsAlias == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strsAlias");
            return false;
        }
        
         // ----
        // check for existing aliases
        
        if (strsAlias.length < 1)
        {
            MySystem.s_printOutWarning(this, strMethod, "strsAlias.length < 1");
            
            if (! super._loadPKTC_(
                new String[0],
                new Boolean[0],
                new Boolean[0],
                new Boolean[0],
                new Boolean[0],
                new String[0],
                new String[0],
                new String[0],
                new Date[0]
                ))
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }   
            
            return true;
        }
        
        
        // ----
        // get aliases
        
        String[] strsAliasPKTC = UtilKstAbs.s_getStrsAliasPKTC(
            (Frame) getOwner(), 
          
            this._kstOpen_);
        
        if (strsAliasPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsAliasPKTC");
        }
        
        
        // --
        // get arrays for dialogTableSelectKeypair
        // TC versus PK
        Boolean[] boosIsTCEntryPKTC = 
            UtilKstAbs.s_getBoosEntryTcr((Frame) getOwner(), 
      
            this._kstOpen_, strsAliasPKTC);
        
        if (boosIsTCEntryPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosIsTCEntryPKTC");
        }
        
        Boolean[] boosValidDatePKTC = 
            UtilKstAbs.s_getBoosValidDatePKTC((Frame) getOwner(), 
        
            this._kstOpen_, strsAliasPKTC);
        
        if (boosValidDatePKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosValidDatePKTC");
        }

        Boolean[] boosSelfSignedCertPKTC = 
            UtilKstAbs.s_getBoosSelfSigned((Frame) getOwner(), 
  
            this._kstOpen_, strsAliasPKTC);
        
        if (boosSelfSignedCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosSelfSignedCertPKTC");
        }
        
        Boolean[] boosTrustedCertPKTC = 
            UtilKstAbs.s_getBoosTrusted((Frame) getOwner(), 
     
            this._kstOpen_, strsAliasPKTC);
        
        if (boosTrustedCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosTrustedCertPKTC");
        }
        
        String[] strsSizeKeyPublPKTC = UtilKstAbs.s_getStrsSizeKeyPubl((Frame) getOwner(), 
           
            this._kstOpen_, strsAliasPKTC);
        
        if (strsSizeKeyPublPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsSizeKeyPublPKTC");
        }
        
        String[] strsTypeCertPKTC = UtilKstAbs.s_getStrsTypeCertificatePKTC((Frame) getOwner(), 
          
            this._kstOpen_, strsAliasPKTC);
        
        if (strsTypeCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsTypeCertPKTC");
        }
        
        String[] strsAlgoSigCertPKTC = UtilKstAbs.s_getStrsAlgoSigCertPKTC((Frame) getOwner(), 
          
            this._kstOpen_, strsAliasPKTC);
        
        if (strsAlgoSigCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsAlgoSigCertPKTC");
        }

        Date[] dtesLastModifiedPKTC = UtilKstAbs.s_getDtesLastModified((Frame) getOwner(), 
           
            this._kstOpen_, strsAliasPKTC);

        if (dtesLastModifiedPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil dtesLastModifiedPKTC");
        }
        
        
        if (! super._loadPKTC_(
            strsAliasPKTC,
            boosIsTCEntryPKTC,
            boosValidDatePKTC,
            boosSelfSignedCertPKTC,
            boosTrustedCertPKTC,
            strsSizeKeyPublPKTC,
            strsTypeCertPKTC,
            strsAlgoSigCertPKTC,
            dtesLastModifiedPKTC
                ))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }   
        
        // --
        return true;
    }
    
    private JPopupMenu _pmuKstImport = null;
    
    
    
    
    private boolean _changePasswordKeyPK()
    {
        String strMethod = "_changePasswordKeyPK()";

        String strAliasKpr = super._getCurAliasPKTC_();
        
        if (strAliasKpr == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strAliasKpr");
            return false;
        }
        
        
        KTLKprOpenManAbs ktl = new KTLKprOpenManChgPasswd(
            (Frame) getOwner(), 
        
        
            // input
            super._strPathAbs_, // existing keystore of type JKS/JCEKS/?PKCS12? 
            this._chrsPasswdOpenKst,
            this._strProviderKst,
        
            super._kstOpen_,
            strAliasKpr
            );
        
        
        if (! ktl.doJob()) // could be cancelled by user
        {
            MySystem.s_printOutTrace(this, strMethod, "! ktl.doJob()");
            return true;
        }
        
        
        KeyStore kstOpenChanged = ktl.getKstOpen(); // coz changed and saved
        
        if (kstOpenChanged == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil kstOpenChanged");
            return false;
        }
        
        
        
        // assign newly changed keystore
        super._kstOpen_ = kstOpenChanged;
        
        // reload
        
        if (! _reloadPKTC())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // highlight new alias
        
        if (super._strsAliasPKTC_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._strsAliasPKTC_");
            return false;
        }
        
        
        int intIdRow = -1;
        
        for (int i=0; i<super._strsAliasPKTC_.length; i++)
        {
            String strCur = super._strsAliasPKTC_[i];
            
            if (strCur.toLowerCase().compareTo(strAliasKpr.toLowerCase()) == 0)
            {
                intIdRow = i;
                break;
            }
        }
        
        if (intIdRow == -1)
        {
            MySystem.s_printOutError(this, strMethod, "intIdRow == -1");
            return false;
        }
        
        if (super._pnlTablePKTC_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._pnlTablePKTC_");
            return false;
        }
        
        if (! super._pnlTablePKTC_.updateSelectionRowId(intIdRow))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        // ending
        return true;
    }
    
    private boolean _changePasswordKeySK()
    {
        String strMethod = "_changePasswordKeySK()";

        String strAliasShk = super._getCurAliasSK_();
        
        if (strAliasShk == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strAliasShk");
            return false;
        }
        
        
        KTLShkOpenManAbs ktl = new KTLShkOpenManChgPasswd(
            (Frame) getOwner(), 
            
            // input
            super._strPathAbs_, // existing keystore of type JKS/JCEKS/?PKCS12? 
            this._chrsPasswdOpenKst,
            this._strProviderKst,
        
            super._kstOpen_,
            strAliasShk
            );
        
        
        if (! ktl.doJob()) // could be cancelled by user
        {
            MySystem.s_printOutTrace(this, strMethod, "! ktl.doJob()");
            return true;
        }
        
        
        KeyStore kstOpenChanged = ktl.getKstOpen(); // coz changed and saved
        
        if (kstOpenChanged == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil kstOpenChanged");
            return false;
        }
        
        
        
        // assign newly changed keystore
        super._kstOpen_ = kstOpenChanged;
        
        // reload
        
        if (! _reloadSK())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // highlight new alias
        
        if (super._strsAliasSK_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._strsAliasSK_");
            return false;
        }
        
        
        int intIdRow = -1;
        
        for (int i=0; i<super._strsAliasSK_.length; i++)
        {
            String strCur = super._strsAliasSK_[i];
            
            if (strCur.toLowerCase().compareTo(strAliasShk.toLowerCase()) == 0)
            {
                intIdRow = i;
                break;
            }
        }
        
        if (intIdRow == -1)
        {
            MySystem.s_printOutError(this, strMethod, "intIdRow == -1");
            return false;
        }
        
        if (super._pnlTableSK_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._pnlTableSK_");
            return false;
        }
        
        if (! super._pnlTableSK_.updateSelectionRowId(intIdRow))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        // ending
        return true;
    }
    
    // a change has been made in the table, eg: alias changed, cloned entry, deleted entry, ...
    private boolean _reloadSK()
    {
        String strMethod = "_reloadSK()";
        
        // ----
        // get aliases
        
        String[] strsAlias = UtilKstAbs.s_getStrsAlias(
            (Frame) getOwner(), 
            
            super._kstOpen_);
        
        if (strsAlias == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strsAlias");
            return false;
        }
        
         // ----
        // check for existing aliases
        
        if (strsAlias.length < 1)
        {
            MySystem.s_printOutWarning(this, strMethod, "strsAlias.length < 1");
            
            if (! super._loadSK_(
                new String[0],
                new Date[0]
                ))
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }   
            
            return true;
        }
        
        
        // ----
        // get aliases
        
        String[] strsAliasSK = UtilKstAbs.s_getStrsAliasSK(
            (Frame) getOwner(), 
           
            this._kstOpen_);
        
        if (strsAliasSK == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsAliasSK");
        }
        
        Date[] dtesLastModifiedSK = UtilKstAbs.s_getDtesLastModified((Frame) getOwner(), 
         
            this._kstOpen_, strsAliasSK);

        if (dtesLastModifiedSK == null)
        {
            MySystem.s_printOutExit(strMethod, "nil dtesLastModifiedSK");
        }
        
        
        if (! super._loadSK_(
            strsAliasSK,
            dtesLastModifiedSK
                ))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }   
        
        // --
        return true;
    }
    
    
}
