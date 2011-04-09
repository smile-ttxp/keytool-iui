

package com.google.code.p.keytooliui.ktl.swing.menu;

/**
    known subclasses:
    . MViewAllMainUIKtl
    . MViewAllMainUIJsr
**/

import com.google.code.p.keytooliui.ktl.swing.menuitem.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.menu.*;
import com.google.code.p.keytooliui.shared.swing.menuitem.*;
import com.google.code.p.keytooliui.shared.io.*;

import javax.swing.*;

import java.awt.event.*;

public abstract class MViewAllMainUIAbs extends MViewAllMainAbs
{
    // ------
    // PUBLIC
    
    public void destroy()
    {
        super.destroy();
        
        
        if (this._menSelectTab_ != null)
        {
            this._menSelectTab_.destroy();
            this._menSelectTab_ = null;
        }
        
        if (this._mimViewCsrPkcs10 != null)
        {
            this._mimViewCsrPkcs10.destroy();
            this._mimViewCsrPkcs10 = null;
        }
        
        if (this._mimViewCrtDer != null)
        {
            this._mimViewCrtDer.destroy();
            this._mimViewCrtDer = null;
        }
        
        if (this._mimViewCrtPkcs7 != null)
        {
            this._mimViewCrtPkcs7.destroy();
            this._mimViewCrtPkcs7 = null;
        }
        
        if (this._mimViewCrtPem != null)
        {
            this._mimViewCrtPem.destroy();
            this._mimViewCrtPem = null;
        }
        
        if (this._mimViewKstJks != null)
        {
            this._mimViewKstJks.destroy();
            this._mimViewKstJks = null;
        }
        
        if (this._mimViewKstJceks != null)
        {
            this._mimViewKstJceks.destroy();
            this._mimViewKstJceks = null;
        }
        
        if (this._mimViewKstPkcs12 != null)
        {
            this._mimViewKstPkcs12.destroy();
            this._mimViewKstPkcs12 = null;
        }
        
        if (this._mimViewKstBks != null)
        {
            this._mimViewKstBks.destroy();
            this._mimViewKstBks = null;
        }
        
        if (this._mimViewKstUber != null)
        {
            this._mimViewKstUber.destroy();
            this._mimViewKstUber = null;
        }
        
        if (this._mimViewKstJksTrustedSystem != null)
        {
            this._mimViewKstJksTrustedSystem.destroy();
            this._mimViewKstJksTrustedSystem = null;
        }
        
        if (this._mimViewKstJksTrustedUser != null)
        {
            this._mimViewKstJksTrustedUser.destroy();
            this._mimViewKstJksTrustedUser = null;
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
        
        
        
        if (! this._menSelectTab_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        if (! this._mimViewCsrPkcs10.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (! this._mimViewCrtPem.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (! this._mimViewCrtDer.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (! this._mimViewCrtPkcs7.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (! this._mimViewKstJks.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (! this._mimViewKstJceks.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (! this._mimViewKstPkcs12.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (! this._mimViewKstBks.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (! this._mimViewKstUber.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (! this._mimViewKstJksTrustedSystem.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._mimViewKstJksTrustedUser != null)
        {
            if (! this._mimViewKstJksTrustedUser.init())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
        }
        
        // --
        
        add(this._menSelectTab_);
        
        _addMenuFile();
        
    
        // ending
        return true;
    }
    
    // ---------
    // PROTECTED
   
    protected MAbstract _menSelectTab_ = null;
    
    protected MViewAllMainUIAbs(ActionListener actListenerParent)
    {
        this._mimViewCsrPkcs10 = new MIViewCsrPkcs10(actListenerParent);
        this._mimViewCrtDer = new MIViewCrtDer(actListenerParent);
        this._mimViewCrtPkcs7 = new MIViewCrtPkcs7(actListenerParent);
        this._mimViewCrtPem = new MIViewCrtPem(actListenerParent);
        this._mimViewKstJks = new MIViewKstJks(actListenerParent);
        this._mimViewKstJceks = new MIViewKstJceks(actListenerParent);
        this._mimViewKstPkcs12 = new MIViewKstPkcs12(actListenerParent);
        this._mimViewKstBks = new MIViewKstBks(actListenerParent);
        this._mimViewKstUber = new MIViewKstUber(actListenerParent);
        this._mimViewKstJksTrustedSystem = new MIViewKstJksSysRootCA(actListenerParent);
        
        if (S_OperatingSystem.s_isUnix() || S_OperatingSystem.s_isWindows())
            this._mimViewKstJksTrustedUser = new MIViewKstJksUsrTrusSig(actListenerParent);
    }
    
    // -------
    // PRIVATE
    
    
    
    
    private MIAbstract _mimViewCsrPkcs10 = null;
    private MIAbstract _mimViewCrtDer = null;
    private MIAbstract _mimViewCrtPkcs7 = null;
    private MIAbstract _mimViewCrtPem = null;
    private MIAbstract _mimViewKstJks = null;
    private MIAbstract _mimViewKstJceks = null;
    private MIAbstract _mimViewKstPkcs12 = null;
    private MIAbstract _mimViewKstBks = null;
    private MIAbstract _mimViewKstUber = null;
    private MIAbstract _mimViewKstJksTrustedSystem = null;
    private MIAbstract _mimViewKstJksTrustedUser = null;
    
    private void _addMenuFile()
    {
        // --
        JMenu menFile = new JMenu();
        JMenu menFileKst = new JMenu();
        JMenu menFileCrt = new JMenu();
        //JMenu menFileCsr = new JMenu();
        
        // --
        menFile.setText("File");
        menFileKst.setText("Keystore");
        menFileCrt.setText("Certificate");
        //menFileCsr.setText("CSR");
        
        
        // --
        menFileKst.add(this._mimViewKstJks);
        menFileKst.add(this._mimViewKstJceks);
        menFileKst.add(this._mimViewKstPkcs12);
        menFileKst.add(this._mimViewKstBks);
        menFileKst.add(this._mimViewKstUber);
        
        menFileKst.addSeparator();
        
        menFileKst.add(this._mimViewKstJksTrustedSystem);
        
        if (this._mimViewKstJksTrustedUser != null)
            menFileKst.add(this._mimViewKstJksTrustedUser);
        
        menFileCrt.add(this._mimViewCrtDer);
        menFileCrt.add(this._mimViewCrtPkcs7);
        menFileCrt.add(this._mimViewCrtPem);
        menFileCrt.addSeparator();
        menFileCrt.add(this._mimViewCsrPkcs10);
        
        // --
        menFile.add(menFileKst);
        menFile.add(menFileCrt);
        //menFile.add(menFileCsr);
       
        add(menFile);
    }
}