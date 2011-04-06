package com.google.code.p.keytooliui.ktl.swing.desktoppane;

import java.awt.Frame;
import java.beans.PropertyVetoException;
import javax.swing.JDesktopPane;
import com.google.code.p.keytooliui.ktl.swing.internalframe.*;
import com.google.code.p.keytooliui.shared.lang.MySystem;

public class DPCntsMainRight extends JDesktopPane
{
    // ------
    // public
    
    public void setContextualHelpID()
    {
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            ifeCur.setContextualHelpID();
        }
    }
    
    public DPCntsMainRight(Frame frmParent)
    {
        setBackground(new java.awt.Color(0, 0, 153));
        
        IFAbs ife = null;
        
        ife = new IFWelcomeKtl(frmParent);
        super.add(ife);
        
        
        ife = new IFKstSave(frmParent);
        super.add(ife);
        
        // create keypair DSA, vers. #1 cert.
        ife = new IFKstOpenCrKprDsa(frmParent);
        super.add(ife);
            
        // create keypair RSA, vers. #1 cert.
        ife = new IFKstOpenCrKprRsa(frmParent);
        super.add(ife);
        
        // create keypair EC, vers. #1 cert.
        ife = new IFKstOpenCrKprEc(frmParent);
        super.add(ife);
        
        
        // create keypair DSA, vers. #3 cert.
     
        ife = new IFKstOpenCrKprV3CDsa(frmParent);
        super.add(ife);
            
        // create keypair RSA, vers. #3 cert.
        ife = new IFKstOpenCrKprV3CRsa(frmParent);
        super.add(ife);
        
        // create keypair EC, vers. #3 cert.
        ife = new IFKstOpenCrKprV3CEc(frmParent);
        super.add(ife);
        
        
        
        
        // certificate RSA-DSA
        ife = new IFKstOpenSigVerCrt(frmParent);
        super.add(ife);
        
        ife = new IFCmsSigVerify(frmParent);
        super.add(ife);
                
        // void
        ife = new IFXmlSigVerify(frmParent);
        super.add(ife);
                
        // shared key all
        ife = new IFKstOpenCrShkAll(frmParent);
        super.add(ife);
        
        ife = new IFKstOpenCryptEncShk(frmParent);
        super.add(ife);
        
        ife = new IFKstOpenCryptDecShk(frmParent);
        super.add(ife);
        
        ife = new IFKstOpenIOShkOut(frmParent);
        super.add(ife);
        
        ife = new IFKstOpenIOShkIn(frmParent);
        super.add(ife);
        
        // public key (either trusted cert or private key entry)
        
        ife = new IFKstOpenCryptEncTC(frmParent);
        super.add(ife);
        
        ife = new IFKstOpenCryptEncPK(frmParent);
        super.add(ife);
        
        ife = new IFKstOpenCryptDecPK(frmParent);
        super.add(ife);
        
        // keypair RSA-DSA
        ife = new IFKstOpenKprFromKprDer(frmParent);
        super.add(ife);
        
        ife = new IFKstOpenKprFromKprPem(frmParent);
        super.add(ife);
        
        
        ife = new IFKstOpenCrtExpKpr(frmParent);
        super.add(ife);
        
        ife = new IFKstOpenCrtSigReq(frmParent);
        super.add(ife);
        
        ife = new IFKstOpenCrtImpReply(frmParent);
        super.add(ife);
        
        ife = new IFKstOpenSigExpKpr(frmParent);
        super.add(ife);
        
        ife = new IFKstOpenSCmsExpKpr(frmParent);
        super.add(ife);
        
        
        ife = new IFKstOpenXmlSigExpKpr(frmParent);
        super.add(ife);
        
        ife = new IFKstOpenJarSigExpKpr(frmParent);
        super.add(ife);
        
        
        ife = new IFKstOpenJarVerif(frmParent);
        super.add(ife);
        
        ife = new IFKstOpenArcDir(frmParent);
        super.add(ife);
        
        
        // trusted certificate
        ife = new IFKstOpenCrtExpTcr(frmParent);
        super.add(ife);
        
        ife = new IFKstOpenCrtImpTcr(frmParent);
        super.add(ife);
        
        ife = new IFKstOpenCCaImpTcr(frmParent);
        super.add(ife);
        
        ife = new IFKstOpenKprFromKprKst(frmParent);
        super.add(ife);
        
        // keypair
         ife = new IFKstOpenKprExpKpr(frmParent);
        super.add(ife);
        
    }
    
    
    
    public boolean init()
    {
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (! ifeCur.init())
                return false;
            
            /*ifeCur.setVisible(true);
            
            try 
            {
                ifeCur.setSelected(true);
            } 
        
            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                return false;
            }*/    
        }
        
        return true;
    }
    
    public void destroy()
    {
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
        
            if (ifeCur != null)
            {
                ifeCur.destroy();
                ifeCur = null;
            }
        }
        
    }
    
    public void setSelectedTaskCreateKst()
    {
        String strMethod = "setSelectedTaskCreateKst()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFKstSave)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
    

    
    public void setSelectedTaskCreateKprV3CEc()
    {
        String strMethod = "setSelectedTaskCreateKprV3CEc()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFKstOpenCrKprV3CEc)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
    public void setSelectedTaskCreateKprV3CRsa()
    {
        String strMethod = "setSelectedTaskCreateKprV3CRsa()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFKstOpenCrKprV3CRsa)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
    public void setSelectedTaskCreateKprV3CDsa()
    {
        String strMethod = "setSelectedTaskCreateKprV3CDsa()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFKstOpenCrKprV3CDsa)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
    public void setSelectedTaskCreateKprV1CDsa()
    {
        String strMethod = "setSelectedTaskCreateKprV1CDsa()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFKstOpenCrKprDsa)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
    public void setSelectedTaskCreateKprV1CEc()
    {
        String strMethod = "setSelectedTaskCreateKprV1CEc()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFKstOpenCrKprEc)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
   
    
    public void setSelectedTaskCreateKprV1CRsa()
    {
        String strMethod = "setSelectedTaskCreateKprV1CRsa()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFKstOpenCrKprRsa)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
    public void setSelectedTaskCreateShkAll()
    {
        String strMethod = "setSelectedTaskCreateShkAll()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFKstOpenCrShkAll)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
    public void setSelectedTaskKprFromKprKst()
    {
        String strMethod = "setSelectedTaskKprFromKprKst()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFKstOpenKprFromKprKst)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
    public void setSelectedTaskKprAnyToCsr()
    {
        String strMethod = "setSelectedTaskKprAnyToCsr()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFKstOpenCrtSigReq)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }  
    
    public void setSelectedTaskTcrToCrt()
    {
        String strMethod = "setSelectedTaskTcrToCrt()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFKstOpenCrtExpTcr)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
    public void setSelectedTaskKprToKpr()
    {
        String strMethod = "setSelectedTaskKprToKpr()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFKstOpenKprExpKpr)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
    public void setSelectedTaskCrtToSig()
    {
        String strMethod = "setSelectedTaskCrtToSig()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFKstOpenSigVerCrt)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
    public void setSelectedTaskXmlToSig()
    {
        String strMethod = "setSelectedTaskXmlToSig()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFXmlSigVerify)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
    public void setSelectedTaskWelcomeKtl()
    {
        String strMethod = "setSelectedTaskWelcomeKtl()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFWelcomeKtl)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
    //

    public void setSelectedTaskCmsToSig()
    {
        String strMethod = "setSelectedTaskCmsToSig()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFCmsSigVerify)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
    public void setSelectedTaskKprToSig()
    {
        String strMethod = "setSelectedTaskKprToSig()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFKstOpenSigExpKpr)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
    public void setSelectedTaskKprToSCms()
    {
        String strMethod = "setSelectedTaskKprToSCms()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFKstOpenSCmsExpKpr)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
    public void setSelectedTaskKprToXmlSig()
    {
        String strMethod = "setSelectedTaskKprToXmlSig()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFKstOpenXmlSigExpKpr)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
    public void setSelectedTaskKprToJarSig()
    {
        String strMethod = "setSelectedTaskKprToJarSig()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFKstOpenJarSigExpKpr)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
    public void setSelectedTaskDirToArc()
    {
        String strMethod = "setSelectedTaskDirToArc()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFKstOpenArcDir)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
    public void setSelectedTaskJarToSig()
    {
        String strMethod = "setSelectedTaskJarToSig()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFKstOpenJarVerif)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
    public void setSelectedTaskShkToCryptEnc()
    {
        String strMethod = "setSelectedTaskShkToCryptEnc()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFKstOpenCryptEncShk)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
    public void setSelectedTaskTcrRsaToCryptEnc()
    {
        String strMethod = "setSelectedTaskTcrRsaToCryptEnc()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFKstOpenCryptEncTC)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
    
    
    public void setSelectedTaskKprRsaToCryptEnc()
    {
        String strMethod = "setSelectedTaskKprRsaToCryptEnc()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFKstOpenCryptEncPK)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
     public void setSelectedTaskIOShkOut()
    {
        String strMethod = "setSelectedTaskIOShkOut()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFKstOpenIOShkOut)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
    
    public void setSelectedTaskIOShkIn()
    {
        String strMethod = "setSelectedTaskIOShkIn()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFKstOpenIOShkIn)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
    public void setSelectedTaskShkToCryptDec()
    {
        String strMethod = "setSelectedTaskShkToCryptDec()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFKstOpenCryptDecShk)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
    
    
    public void setSelectedTaskKprRsaToCryptDec()
    {
        String strMethod = "setSelectedTaskKprRsaToCryptDec()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFKstOpenCryptDecPK)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
    public void setSelectedTaskKprToCrt()
    {
        String strMethod = "setSelectedTaskKprToCrt()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFKstOpenCrtExpKpr)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
    public void setSelectedTaskTcrFromCCa()
    {
        String strMethod = "setSelectedTaskTcrFromCCa()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFKstOpenCCaImpTcr)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
            
    public void setSelectedTaskTcrFromCrt()
    {
        String strMethod = "setSelectedTaskTcrFromCrt()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFKstOpenCrtImpTcr)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
    public void setSelectedTaskKprFromKprDer()
    {
        String strMethod = "setSelectedTaskKprFromKprDer()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFKstOpenKprFromKprDer)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
    
    
    public void setSelectedTaskKprFromKprPem()
    {
        String strMethod = "setSelectedTaskKprFromKprPem()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFKstOpenKprFromKprPem)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
    public void setSelectedTaskKprAnyFromCrt()
    {
        String strMethod = "setSelectedTaskKprAnyFromCrt()";
        
        IFAbs ifeTarget = null;
        
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Object objCur = super.getComponent(i);
            
            if (! (objCur instanceof IFAbs))     
                continue;
            
            IFAbs ifeCur = (IFAbs) objCur;
            
            if (ifeCur instanceof IFKstOpenCrtImpReply)
            {
                ifeTarget = ifeCur;
                continue;
            }
            
            if (ifeCur.isVisible())
                ifeCur.setVisible(false);
        }
        
        if (ifeTarget == null) // !! BUG
            return;
        
        if (ifeTarget.isIcon())
        {  
            try 
            {
                ifeTarget.setIcon(false);
            } 

            catch (PropertyVetoException ex) 
            {
                ex.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, ex.getMessage() + "\n exiting");
            }
        }

        if (! ifeTarget.isVisible())
             ifeTarget.setVisible(true);
        
        ifeTarget.toFront();
    }
    
    
    // -------
    // private
    
}
