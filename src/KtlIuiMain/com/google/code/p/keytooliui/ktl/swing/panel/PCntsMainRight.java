package com.google.code.p.keytooliui.ktl.swing.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import com.google.code.p.keytooliui.ktl.swing.desktoppane.DPCntsMainRight;
import com.google.code.p.keytooliui.ktl.swing.toolbar.TBSubCntKtl;

public class PCntsMainRight extends PCntsMainAbs
{
    // public
    
    public void setContextualHelpID()
    {
        if (this._dpe != null)
            this._dpe.setContextualHelpID();
    }
    
    public void setSelectedTaskCreateKst()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskCreateKst();
    }
    
    public void setSelectedTaskCreateKprV3CDsa()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskCreateKprV3CDsa();
    }
    
    public void setSelectedTaskCreateKprV1CDsa()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskCreateKprV1CDsa();
    }
    
    public void setSelectedTaskCreateKprV3CEc()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskCreateKprV3CEc();
    }
    
    public void setSelectedTaskCreateKprV1CEc()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskCreateKprV1CEc();
    }
    
    public void setSelectedTaskCreateKprV3CRsa()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskCreateKprV3CRsa();
    }
    
    public void setSelectedTaskCreateKprV1CRsa()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskCreateKprV1CRsa();
    }
    
    public void setSelectedTaskCreateShkAll()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskCreateShkAll();
    }
    
    public void setSelectedTaskKprFromKprKst()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskKprFromKprKst();
    }
    
    public void setSelectedTaskKprAnyToCsr()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskKprAnyToCsr();
    } 
    
    public void setSelectedTaskTcrToCrt()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskTcrToCrt();
    }
    
    
    
    public void setSelectedTaskKprToKpr()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskKprToKpr();
    }
    
    public void setSelectedTaskCrtToSig()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskCrtToSig();
    }
    
    public void setSelectedTaskWelcomeKtl()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskWelcomeKtl();
    }
    
    public void setSelectedTaskCmsToSig()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskCmsToSig();
    }

    public void setSelectedTaskXmlToSig()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskXmlToSig();
    }
    
    public void setSelectedTaskKprToSig()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskKprToSig();
    }
    
    public void setSelectedTaskKprToSCms()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskKprToSCms();
    }
    
    public void setSelectedTaskKprToXmlSig()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskKprToXmlSig();
    }
    
    public void setSelectedTaskKprToJarSig()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskKprToJarSig();
    }
    
    public void setSelectedTaskDirToArc()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskDirToArc();
    }
    
    public void setSelectedTaskJarToSig()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskJarToSig();
    }
    
    public void setSelectedTaskShkToCryptEnc()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskShkToCryptEnc();
    }
    
    public void setSelectedTaskTcrRsaToCryptEnc()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskTcrRsaToCryptEnc();
    }
    
    
    
    public void setSelectedTaskKprRsaToCryptEnc()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskKprRsaToCryptEnc();
    }
    
     public void setSelectedTaskIOShkOut()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskIOShkOut();
    }
    
    
    public void setSelectedTaskIOShkIn()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskIOShkIn();
    }
    
    public void setSelectedTaskShkToCryptDec()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskShkToCryptDec();
    }
    
    
    
    public void setSelectedTaskKprRsaToCryptDec()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskKprRsaToCryptDec();
    }
    
    public void setSelectedTaskKprToCrt()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskKprToCrt();
    }
    
    public void setSelectedTaskTcrFromCCa()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskTcrFromCCa();
    }
    
    public void setSelectedTaskTcrFromCrt()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskTcrFromCrt();
    }
    
    public void setSelectedTaskKprFromKprDer()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskKprFromKprDer();
    }
    
    
    
    public void setSelectedTaskKprFromKprPem()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskKprFromKprPem();
    }
    
    public void setSelectedTaskKprAnyFromCrt()
    {
        if (this._dpe != null)
            this._dpe.setSelectedTaskKprAnyFromCrt();
    }
    
    public PCntsMainRight(Frame frmParent, String strTitleAppli)
    {
        super();
        
        
        this._tbr = new  TBSubCntKtl(strTitleAppli,
            (ActionListener) this);
        
        this._dpe = new DPCntsMainRight(frmParent, strTitleAppli);
       
        
    }
    
    public boolean init()
    {
        if (! this._tbr.init())
            return false;
        
        if (! this._dpe.init())
            return false;
        
        setLayout(new BorderLayout());
        
        add(this._tbr, BorderLayout.NORTH);
        add(this._dpe, BorderLayout.CENTER);
        
        return true;
    }
    
    public void destroy()
    {
         if (this._tbr != null)
        {
            this._tbr.destroy();
            this._tbr = null;
        }
         
        if (this._dpe != null)
        {
            this._dpe.destroy();
            this._dpe = null;
        }
    }
       
    // -------
    // private
    
    private TBSubCntKtl _tbr = null;
    private DPCntsMainRight _dpe = null;
}

