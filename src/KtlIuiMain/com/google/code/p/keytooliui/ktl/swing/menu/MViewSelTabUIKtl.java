
package com.google.code.p.keytooliui.ktl.swing.menu;
 
/**

**/

import com.google.code.p.keytooliui.ktl.swing.menuitem.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.menu.*;
import com.google.code.p.keytooliui.shared.swing.menuitem.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;


final public class MViewSelTabUIKtl extends MViewSelTabUIAbs
{
    // ------
    // PUBLIC
    
    
    
    public void destroy()
    { 
        for (int i=0; i<super.getComponentCount(); i++)
        {
            Component cmpCur = super.getComponent(i);
            
            if (! (cmpCur instanceof MISelTabAbs))
                continue;
            
            MISelTabAbs mimCur = (MISelTabAbs) cmpCur;
            
            if (mimCur != null)
            {
                mimCur.destroy();
                mimCur = null;
            }
        }  
    }
    
    public boolean init()
    {
        //String strMethod = "init()";

        for (int i=0; i<super.getComponentCount(); i++)
        {
            Component cmpCur = super.getComponent(i);
            
            if (! (cmpCur instanceof MISelTabAbs))
                continue;
            
            MISelTabAbs mimCur = (MISelTabAbs) cmpCur;
            
            if (mimCur != null)
            {
                if (! mimCur.init())
                    return false;
            }
        }

        
        // --
        return true;
    }
    
   
    
    public MViewSelTabUIKtl(ActionListener actListenerParent)
    {
        super();
        
        // --
        
        MISelTabAbs itmCreateKst = new MISelTabCreateKst(actListenerParent);
        MISelTabAbs itmCreateKprV1CDsa = new MISelTabCreateKprDsa(actListenerParent);
        MISelTabAbs itmCreateKprV1CRsa = new MISelTabCreateKprRsa(actListenerParent);
        MISelTabAbs itmCreateKprV1CEc = new MISelTabCreateKprEc(actListenerParent);
        
        MISelTabAbs itmCreateKprV3CDsa = new MISelTabCreateKprV3CDsa(actListenerParent);
        MISelTabAbs itmCreateKprV3CRsa = new MISelTabCreateKprV3CRsa(actListenerParent);
        MISelTabAbs itmCreateKprV3CEc = new MISelTabCreateKprV3CEc(actListenerParent);
        
        MISelTabAbs itmCreateShkAll = new MISelTabCreateShkAll(actListenerParent);
        MISelTabAbs itmKprFromKprKst = new MISelTabKprFromKprKst(actListenerParent); // in other keystore
        MISelTabAbs itmKprAnyToCsr = new MISelTabKprAnyToCsr(actListenerParent);
        MISelTabAbs itmTcrToCrt = new MISelTabTcrToCrt(actListenerParent);
        MISelTabAbs itmKprToKpr = new MISelTabKprToKpr(actListenerParent);
        
        MISelTabAbs itmTcrFromCrt = new MISelTabTcrFromCrt(actListenerParent);
        MISelTabAbs itmTcrFromCCa = new MISelTabTcrFromCCa(actListenerParent);
        MISelTabAbs itmKprFromKprDer = new MISelTabKprFromKprDer(actListenerParent);
        MISelTabAbs itmKprFromKprPem = new MISelTabKprFromKprPem(actListenerParent);
        
        MISelTabAbs itmKprToCrt = new MISelTabKprToCrt(actListenerParent);
        MISelTabAbs itmKprToSig = new MISelTabKprToSig(actListenerParent);
        MISelTabAbs itmCrtToSig = new MISelTabCrtToSig(actListenerParent);
        
        MISelTabAbs itmKprToSCms = new MISelTabKprToSCms(actListenerParent);
        
                
        MISelTabAbs itmWelcomeKtl = new MISelTabWelcomeKtl(actListenerParent);
        
        MISelTabAbs itmKprToXmlSig = new MISelTabKprToXmlSig(actListenerParent);
        MISelTabAbs itmKprToJarSig = new MISelTabJarSign(actListenerParent);
        MISelTabAbs itmCmsToSig = new MISelTabCmsToSig(actListenerParent);
        MISelTabAbs itmXmlToSig = new MISelTabXmlToSig(actListenerParent);
        MISelTabAbs itmJarToSig = new MISelTabJarVerify(actListenerParent);
        
        MISelTabAbs itmDirToArc = new MISelTabArcDir(actListenerParent);
        
        MISelTabAbs itmShkToCryptEnc = new MISelTabShkToCryptEnc(actListenerParent);
        MISelTabAbs itmShkToCryptDec = new MISelTabShkToCryptDec(actListenerParent);
        
        
        MISelTabAbs itmTcrRsaToCryptEnc = new MISelTabTcrRsaToCryptEnc(actListenerParent);
        MISelTabAbs itmKprRsaToCryptEnc = new MISelTabKprRsaToCryptEnc(actListenerParent);
        MISelTabAbs itmKprRsaToCryptDec = new MISelTabKprRsaToCryptDec(actListenerParent);
        
        MISelTabAbs itmIOShkOut = new MISelTabIOShkOut(actListenerParent);
        MISelTabAbs itmIOShkIn = new MISelTabIOShkIn(actListenerParent);
        
        MISelTabAbs itmKprAnyFromCrt = new MISelTabKprAnyFromCrt(actListenerParent);
        
        
        JMenu menCreate = new JMenu("Create");
        JMenu menArchive = new JMenu("Archive");
        JMenu menSign = new JMenu("Sign");
        JMenu menVerify = new JMenu("Verify");
        JMenu menEncrypt = new JMenu("Encrypt");
        JMenu menDecrypt = new JMenu("Decrypt");
        JMenu menImport = new JMenu("Import");
        JMenu menExport = new JMenu("Export");
        
        // welcome
        add(itmWelcomeKtl);
        
        super.addSeparator();
        add(menCreate);
        
        super.addSeparator();
        add(menArchive);
        
        super.addSeparator();
        add(menSign);
        add(menVerify);
        
        super.addSeparator();
        add(menEncrypt);
        add(menDecrypt);
        
        super.addSeparator();
        add(menImport);
        add(menExport);
        
        
        
        // create
        menCreate.add(itmCreateKst);
        
        JMenu menCreateEntry = new JMenu("Keystore's entry");
        menCreate.add(menCreateEntry);
        menCreateEntry.add(itmCreateKprV1CDsa);
        menCreateEntry.add(itmCreateKprV1CRsa);
        menCreateEntry.add(itmCreateKprV1CEc);
        
        menCreateEntry.add(itmCreateKprV3CDsa);
        menCreateEntry.add(itmCreateKprV3CRsa);
        menCreateEntry.add(itmCreateKprV3CEc);
        
        menCreateEntry.add(itmCreateShkAll);
        
        // archive
        menArchive.add(itmDirToArc);
        
        
        // sign
        menSign.add(itmKprToSCms);
        menSign.add(itmKprToSig);
        
        menSign.add(itmKprToXmlSig);
        menSign.add(itmKprToJarSig);
        
        // verify
        menVerify.add(itmCmsToSig);
        menVerify.add(itmCrtToSig);
        
        menVerify.add(itmXmlToSig);
        menVerify.add(itmJarToSig);
        
        // encrypt
        menEncrypt.add(itmShkToCryptEnc);
        menEncrypt.add(itmTcrRsaToCryptEnc);
        menEncrypt.add(itmKprRsaToCryptEnc);
        
        // decrypt
        menDecrypt.add(itmShkToCryptDec);
        menDecrypt.add(itmKprRsaToCryptDec);
        
        // import
        JMenu menInEntry = new JMenu("Keystore's entry");
        JMenu menInCrt = new JMenu("Certificate");
        menImport.add(menInEntry);
        menImport.add(menInCrt);
        
        
        menInEntry.add(itmIOShkIn);
        
        JMenu menPK = new JMenu("Private key");
        menInEntry.add(menPK);
        menPK.add(itmKprFromKprDer);
        menPK.add(itmKprFromKprPem);
        menPK.add(itmKprFromKprKst);
        
        JMenu menTC = new JMenu("Trusted certificate");
        menInEntry.add(menTC);
        menTC.add(itmTcrFromCrt);
        menTC.add(itmTcrFromCCa);
        
        
        
        
        menInCrt.add(itmKprAnyFromCrt);
        
        // export
        JMenu menOutEntry = new JMenu("Keystore's entry");
        JMenu menOutCrt = new JMenu("Certificate");
        menExport.add(menOutEntry);
        menExport.add(menOutCrt);
        
        menOutEntry.add(itmIOShkOut);
        menOutEntry.add(itmKprToKpr);
        menOutEntry.add(itmTcrToCrt);
         
        menOutCrt.add(itmKprAnyToCsr);
        menOutCrt.add(itmKprToCrt);
    }
    
    
    // -------
    // PRIVATE
    
   
    
   
    
   
}