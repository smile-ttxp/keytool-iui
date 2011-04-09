
package com.google.code.p.keytooliui.ktl.swing.menu;

import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JMenu;

import com.google.code.p.keytooliui.ktl.swing.menuitem.*;

public final class MViewSelTabUIKtl extends MViewSelTabUIAbs
{
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

        return true;
    }


    public MViewSelTabUIKtl(ActionListener listener)
    {
        JMenu menuCreate = new JMenu("Create");
        JMenu menuArchive = new JMenu("Archive");
        JMenu menuSign = new JMenu("Sign");
        JMenu menuVerify = new JMenu("Verify");
        JMenu menuEncrypt = new JMenu("Encrypt");
        JMenu menuDecrypt = new JMenu("Decrypt");
        JMenu menuImport = new JMenu("Import");
        JMenu menuExport = new JMenu("Export");
        
        // welcome
        add(new MISelTabWelcomeKtl(listener));
        
        super.addSeparator();
        add(menuCreate);
        
        super.addSeparator();
        add(menuArchive);
        
        super.addSeparator();
        add(menuSign);
        add(menuVerify);
        
        super.addSeparator();
        add(menuEncrypt);
        add(menuDecrypt);
        
        super.addSeparator();
        add(menuImport);
        add(menuExport);
        
        
        
        // create
        menuCreate.add(new MISelTabCreateKst(listener));
        
        JMenu menCreateEntry = new JMenu("Keystore's entry");
        menuCreate.add(menCreateEntry);
        menCreateEntry.add(new MISelTabCreateKprDsa(listener));
        menCreateEntry.add(new MISelTabCreateKprRsa(listener));
        menCreateEntry.add(new MISelTabCreateKprEc(listener));
        
        menCreateEntry.add(new MISelTabCreateKprV3CDsa(listener));
        menCreateEntry.add(new MISelTabCreateKprV3CRsa(listener));
        menCreateEntry.add(new MISelTabCreateKprV3CEc(listener));
        
        menCreateEntry.add(new MISelTabCreateShkAll(listener));
        
        // archive
        menuArchive.add(new MISelTabArcDir(listener));
        
        // sign
        menuSign.add(new MISelTabKprToSCms(listener));
        menuSign.add(new MISelTabKprToSig(listener));
        menuSign.add(new MISelTabKprToXmlSig(listener));
        menuSign.add(new MISelTabJarSign(listener));
        
        // verify
        menuVerify.add(new MISelTabCmsToSig(listener));
        menuVerify.add(new MISelTabCrtToSig(listener));
        menuVerify.add(new MISelTabXmlToSig(listener));
        menuVerify.add(new MISelTabJarVerify(listener));
        
        // encrypt
        menuEncrypt.add(new MISelTabShkToCryptEnc(listener));
        menuEncrypt.add(new MISelTabTcrRsaToCryptEnc(listener));
        menuEncrypt.add(new MISelTabKprRsaToCryptEnc(listener));
        
        // decrypt
        menuDecrypt.add(new MISelTabShkToCryptDec(listener));
        menuDecrypt.add(new MISelTabKprRsaToCryptDec(listener));
        
        // import
        JMenu menInEntry = new JMenu("Keystore's entry");
        JMenu menInCrt = new JMenu("Certificate");
        menuImport.add(menInEntry);
        menuImport.add(menInCrt);
        
        
        menInEntry.add(new MISelTabIOShkIn(listener));
        
        JMenu menPK = new JMenu("Private key");
        menInEntry.add(menPK);
        menPK.add(new MISelTabKprFromKprDer(listener));
        menPK.add(new MISelTabKprFromKprPem(listener));
        menPK.add(new MISelTabKprFromKprKst(listener));
        
        JMenu menTC = new JMenu("Trusted certificate");
        menInEntry.add(menTC);
        menTC.add(new MISelTabTcrFromCrt(listener));
        menTC.add(new MISelTabTcrFromCCa(listener));
        
        
        
        
        menInCrt.add(new MISelTabKprAnyFromCrt(listener));
        
        // export
        JMenu menOutEntry = new JMenu("Keystore's entry");
        JMenu menOutCrt = new JMenu("Certificate");
        menuExport.add(menOutEntry);
        menuExport.add(menOutCrt);
        
        menOutEntry.add(new MISelTabIOShkOut(listener));
        menOutEntry.add(new MISelTabKprToKpr(listener));
        menOutEntry.add(new MISelTabTcrToCrt(listener));
         
        menOutCrt.add(new MISelTabKprAnyToCsr(listener));
        menOutCrt.add(new MISelTabKprToCrt(listener));
    }
}
