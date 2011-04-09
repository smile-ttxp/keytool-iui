package com.google.code.p.keytooliui.ktl.swing.panel;

/*
*/

import java.awt.Frame;
import java.awt.event.ItemListener;
import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import com.google.code.p.keytooliui.shared.lang.MySystem;
import net.miginfocom.swing.MigLayout;

public abstract class PTabUICmdKtlKstOpenSCmsExpAbs extends PTabUICmdKtlKstOpenSCmsAbs
{    
    // ------
    // public
    
    public void insertUpdate(DocumentEvent evtDocument)
    {
        String strMethod = "insertUpdate(evtDocument)";
        
        Document doc = evtDocument.getDocument();
        
        if (doc == null)
            MySystem.s_printOutExit(this, strMethod, "nil doc");
            
        Object objPropVal = doc.getProperty(com.google.code.p.keytooliui.shared.swing.textfield.TFAbstract.f_s_strDocPropKey);
        
        if (objPropVal == null)
            MySystem.s_printOutExit(this, strMethod, "nil objPropVal");
            
        if (! (objPropVal instanceof String))
            MySystem.s_printOutExit(this, strMethod, "! (objPropVal instanceof String)");
            
        String strPropVal = (String) objPropVal;
        
        if (strPropVal == null)
            MySystem.s_printOutExit(this, strMethod, "nil strPropVal");
        
        // ----
        
        int intLength = doc.getLength();
        
        if (intLength == 0)
            MySystem.s_printOutExit(this, strMethod, "intLength == 0");
            
        String strText = null;
        
        try
        {
            strText = doc.getText(0, intLength);
        }
            
        catch(BadLocationException excBadLocation)
        {
            excBadLocation.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "excBadLocation caught");
        }
        
        // --------
        // output, optional
        
        if (strPropVal.compareTo(PSelBtnTfdFileSaveCCms.f_s_strDocPropVal) == 0)
        {
            this._strPathAbsFileCrt_ = strText;
            
            // no need to update actionButton coz optional field
            //_updateActionButtonDataChanged(true);
            
            return;
        }
        
        super.insertUpdate(evtDocument);
    }
    
    public void removeUpdate(DocumentEvent evtDocument)
    {
        String strMethod = "removeUpdate(evtDocument)";
  
        Document doc = evtDocument.getDocument();
        
        if (doc == null)
            MySystem.s_printOutExit(this, strMethod, "nil doc");
            
        Object objPropVal = doc.getProperty(com.google.code.p.keytooliui.shared.swing.textfield.TFAbstract.f_s_strDocPropKey);
        
        if (objPropVal == null)
            MySystem.s_printOutExit(this, strMethod, "nil objPropVal");
            
        if (! (objPropVal instanceof String))
            MySystem.s_printOutExit(this, strMethod, "! (objPropVal instanceof String)");
            
        String strPropVal = (String) objPropVal;
        
        if (strPropVal == null)
            MySystem.s_printOutExit(this, strMethod, "nil strPropVal");
        
        // --------
        
        
        if (strPropVal.compareTo(PSelBtnTfdFileSaveCCms.f_s_strDocPropVal) == 0)
        {
            this._strPathAbsFileCrt_ = null;
            
            // no need to update actionButton coz optional field
            //_updateActionButtonDataChanged(false);
            
            return;
        }

        
        super.removeUpdate(evtDocument);
    }
    
    public void destroy()
    {
        super.destroy();
        
        if (this._pnlSelectFileCrt_ != null)
        {
            this._pnlSelectFileCrt_.destroy();
            this._pnlSelectFileCrt_ = null;
        }
    }

    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        if (this._pnlSelectFileCrt_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectFileCrt_");
            return false;
        }
        
        if (! this._pnlSelectFileCrt_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
    
        return true;
    }
    
    
    // ---------
    // protected
   
   
    protected String _strPathAbsFileCrt_ = null; // output, optional
    protected PSelBtnTfdFileSaveCCms _pnlSelectFileCrt_;
    
    protected PTabUICmdKtlKstOpenSCmsExpAbs(
        Frame frmOwner, 
    
        String strHelpID
        )
    {
        super(
            strHelpID, 
            frmOwner, 
     
            PSelBtnTfdFileOpenAnyFile.f_s_strDocPropVal,
            PSelBtnTfdFileSaveSCms.f_s_strDocPropVal
            );

        super._pnlSelectFileSCms_ = new PSelBtnTfdFileSaveSCms(
            (javax.swing.event.DocumentListener) this,
            frmOwner, 
      
            (ItemListener) null
            );
        
        this._pnlSelectFileCrt_ = new PSelBtnTfdFileSaveCCms(
            (javax.swing.event.DocumentListener) this,
            frmOwner, 
  
            (ItemListener) null
            );
        
        this._pnlSelectFileCrt_.setFieldRequired(false);
    }
    
    protected void _fillInPanelOutput_()
    {
        super._pnlOutput_.setLayout(new MigLayout("fill, wrap 1", "[left]"));
        super._pnlOutput_.add(super._pnlSelectFileSCms_);
        super._pnlOutput_.add(this._pnlSelectFileCrt_);
    }
}
