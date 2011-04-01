package com.google.code.p.keytooliui.ktl;

/**
    contains:
    . 1 frame
**/

// Uses actions with a tool bar and a menu.

import com.google.code.p.keytooliui.ktl.swing.frame.*;
import com.google.code.p.keytooliui.ktl.util.jarsigner.*;
import com.google.code.p.keytooliui.ktl.swing.menuitem.*;
import com.google.code.p.keytooliui.ktl.swing.button.*;

import com.google.code.p.keytooliui.shared.*;
import com.google.code.p.keytooliui.shared.io.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.button.BESPrint24;
import com.google.code.p.keytooliui.shared.swing.menuitem.*;

import javax.swing.event.*;

import java.awt.event.*;
import java.util.*;


abstract public class AppMainUIAbs extends AppMainAbs implements
    ChangeListener // tabbedPane ==> tab selected
{

    final static public String F_STR_BUNDLE_DIR =
            new String("com.google.code.p.keytooliui." +
            System.getProperty("_appli.name.short") + // ie. "ktl"
            ".bundle");
    
    
    // ----------------------
    // final static protected
    final static protected boolean _F_BLN_SET_LAF_SWING_ = true;
    
    
    // ------
    // PUBLIC
    
    @Override
    public boolean start()
    {
        String strMethod = "start()";
        
        if (! super.start())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (! super._packAndShow_())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
      
        return true;
    }
    
    @Override
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";
        
        try
        { 
            // ----
            
            if (evtAction.getSource() instanceof BESPrint24)
            {
                if (! ((FMainUIAbs) super._fmaFrame_).doPrint())
                {
                    // bug or cancelled?
                }

            
                // ending
                return;
            }
            
            if (evtAction.getSource() instanceof BESView24)
            {
                UtilKstAll.s_showFile(super._fmaFrame_); 
            
                // ending
                return;
            }
            
            if (evtAction.getSource() instanceof BESTool24)
            {
                UtilKstAll.s_manageFile(super._fmaFrame_); 
            
                // ending
                return;
            }
            
            // ----
            
            if (evtAction.getSource() instanceof MIHelpAboutProjAbstract)
            {
                // the following: not done yet, menuItem should not be visible
                //if (! _aboutProject()) 
                    MySystem.s_printOutExit(this, strMethod, "DEVELOPMENT ERROR! menuItem \"aboutThisProject\" should not be visible");  
            
                // ending
                return;
            }
            
                        
            if (evtAction.getSource() instanceof MIViewCsrPkcs10)
            {                
                UtilCsrPkcs10.s_showFile(super._fmaFrame_);
            
                // ending
                return;
            }
            
            if (evtAction.getSource() instanceof MIViewCrtPem)
            {
                UtilCrtX509Pem.s_showFile(super._fmaFrame_);
            
                // ending
                return;
            }
            
            if (evtAction.getSource() instanceof MIViewCrtDer)
            {
                UtilCrtX509Der.s_showFile(super._fmaFrame_);
            
                // ending
                return;
            }
            
            if (evtAction.getSource() instanceof MIViewCrtPkcs7)
            {
                UtilCrtX509Pkcs7.s_showFile(super._fmaFrame_);
            
                // ending
                return;
            }
            
            if (evtAction.getSource() instanceof MIViewKstJks)
            {
                UtilKstJks.s_showFile(super._fmaFrame_); 
            
                // ending
                return;
            }
            
            if (evtAction.getSource() instanceof MIViewKstJceks)
            {
                UtilKstJceks.s_showFile(super._fmaFrame_); 
            
                // ending
                return;
            }
            
            if (evtAction.getSource() instanceof MIViewKstPkcs12)
            {
                UtilKstPkcs12.s_showFile(super._fmaFrame_); 
            
                // ending
                return;
            }
            
            if (evtAction.getSource() instanceof MIViewKstBks)
            {
                UtilKstBks.s_showFile(super._fmaFrame_); 
            
                // ending
                return;
            }
            
            if (evtAction.getSource() instanceof MIViewKstUber)
            {
                UtilKstUber.s_showFile(super._fmaFrame_); 
            
                // ending
                return;
            }
            
             if (evtAction.getSource() instanceof MIToolKstJksSysRootCA)
            {
                UtilKstJks.s_manageFileKstCertsTrustSys(super._fmaFrame_);  
                // ending
                return;
            }
            
            if (evtAction.getSource() instanceof MIViewKstJksSysRootCA)
            {
                UtilKstJks.s_showFileKstCertsTrustSys(super._fmaFrame_);  
                // ending
                return;
            }
            
            if (evtAction.getSource() instanceof MIViewKstJksUsrTrusSig)
            {
                UtilKstJks.s_showFileKstCertsTrustUsr(super._fmaFrame_);  
                // ending
                return;
            }
            
            if (evtAction.getSource() instanceof MIToolKstJks)
            {
                UtilKstJks.s_manageFile(super._fmaFrame_); 
            
                // ending
                return;
            }
            
            if (evtAction.getSource() instanceof MIToolKstJceks)
            {
                UtilKstJceks.s_manageFile(super._fmaFrame_); 
            
                // ending
                return;
            }
            
            
            if (evtAction.getSource() instanceof MIToolKstPkcs12)
            {
                UtilKstPkcs12.s_manageFile(super._fmaFrame_); 
            
                // ending
                return;
            }
            
            if (evtAction.getSource() instanceof MIToolKstBks)
            {
                UtilKstBks.s_manageFile(super._fmaFrame_); 
            
                // ending
                return;
            }
            
            if (evtAction.getSource() instanceof MIToolKstUber)
            {
                UtilKstUber.s_manageFile(super._fmaFrame_); 
            
                // ending
                return;
            }
            
            // --
            // redirecting to superclass
            super.actionPerformed(evtAction);
        
        }
        
        catch(Exception exc)
        {
            if (super._blnExitNow_)
            {
                MySystem.s_printOutTrace(this, strMethod, "exc caught, super._blnExitNow_, ignoring");
                return;
            }
            
            exc.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "exc caught");
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
        
        
        // --
        
        /*if (this._cltChangerLocTabs_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._cltChangerLocTabs_");
            return false;
        }
        
        if (! this._cltChangerLocTabs_.init()) // changer main toolbar orientation
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }*/
        
        /*if (this._cltSetVisibleTab_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._cltSetVisibleTab_");
            return false;
        }
        
        if (! this._cltSetVisibleTab_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }*/
        
        // if assigned in RUN command line
        _dumpSecurityProviderJvm();
        
        return true;
    }
    
    
    // missing: ??children??.destroy()
    public void destroy()
    {   
        // temp
        System.out.println("AppMainUIAbs.destroy()");
        
        
        
        //com.google.code.p.keytooliui.shared.lang.thread.MyThreadAbs.s_interruptThreadsActive();
        
        _removeListeners();
        
        super.destroy();
        
        /*if (this._cltChangerLocTabs_ != null)
        {
            this._cltChangerLocTabs_.destroy();
            this._cltChangerLocTabs_ = null;
        }*/
        
        /*if (this._cltSetVisibleTab_ != null)
        {
            this._cltSetVisibleTab_.destroy();
            this._cltSetVisibleTab_ = null;
        } */ 
        
        if (this._blnExitNormally)
            super._exitNormally_();
    }
    
    // catching main tabs selections
    public void stateChanged(ChangeEvent evtChange)
    {
    }
    
    public void itemStateChanged(ItemEvent evtItem) 
    {
        String strMethod = "itemStateChanged(evtItem)";
        
        try
        {
        
            if (evtItem.getStateChange() == ItemEvent.DESELECTED)
                return;
            
            if (! (evtItem.getSource() instanceof RBMIAlignAbstract))
                MySystem.s_printOutExit(this, strMethod, "wrong instance");
                
            RBMIAlignAbstract rbmSource = 
                (RBMIAlignAbstract) evtItem.getSource();
               
            /*
            // ------------
            // MAIN TABS
            
            
            if (rbmSource instanceof RBMIAlignTopTabsMain)
            {
	            if (! this._cltChangerLocTabs_.setTop())
	                MySystem.s_printOutExit(this, strMethod, "failed");
    	       
	            return;
            }
            
            if (rbmSource instanceof RBMIAlignBottomTabsMain)
            {
	            if (! this._cltChangerLocTabs_.setBottom())
	                MySystem.s_printOutExit(this, strMethod, "failed");
    	          
	            return;
            }
                
            if (rbmSource instanceof RBMIAlignLeftTabsMain)
            {
	            if (! this._cltChangerLocTabs_.setLeft())
	                MySystem.s_printOutExit(this, strMethod, "failed");
                
                return;
            }
            
            if (rbmSource instanceof RBMIAlignRightTabsMain)
            {
	            if (! this._cltChangerLocTabs_.setRight())
	                MySystem.s_printOutExit(this, strMethod, "failed");
                    
                return;
            }*/
            
            // ----------------------
            // redirect to superclass
            super.itemStateChanged(evtItem);
        
        }
        
        catch(Exception exc)
        {
            if (super._blnExitNow_)
            {
                MySystem.s_printOutTrace(this, strMethod, "exc caught, super._blnExitNow_, ignoring");
                return;
            }
            
            exc.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "exc caught");
        }
    }
    
    // ---------
    // PROTECTED  
    
    //protected ChgLocMainUIAbs _cltChangerLocTabs_ = null; // main
    //protected UCBooleanAbstract _cltSetVisibleTab_ = null;
    protected String _strLic_ = null;
   
    public AppMainUIAbs(
        boolean blnExitNormally, // memo: should be equals to value of "blnShowDialogExitConfirm""
        boolean blnParentDirReadOnlyAllowed,
        //String strTitleAppli,
        //String strAppliNameShort, // eg: "ktl"
        boolean blnShowDialogExitConfirm, 
        boolean blnIsHelpGettingStarted,
        boolean blnInternAllowed
        )
    {
        super(
            blnParentDirReadOnlyAllowed, // MEMO: blnParentDirReadOnlyAllowed
            //strTitleAppli,
            //strAppliNameShort, // eg: "ktl"
            blnShowDialogExitConfirm,
            blnIsHelpGettingStarted,
            AppMainUIAbs._F_BLN_SET_LAF_SWING_, // used to fix up JH's toolbar buttons
            blnInternAllowed
            );
            
        this._blnExitNormally = blnExitNormally;
        //this._blnDumpSecurityProviderJVM = blnDumpSecurityProviderJVM;
            
        String strMethod = "AppMainUIAbs(...)";
        
        if (! _addProviders())
            MySystem.s_printOutExit(this, strMethod, "failed");
        
        this._addListeners();
    }
    
    protected boolean _createLastUserPreferences_(
            //String strAppliNameShort,
            String strVersionAppli)
    {
        String strMethod = "_createLastUserPreferences_(...)";
        
        Vector<UserChoice> vecUserChoice = new Vector<UserChoice>();
	        
	    //if (this._cltChangerLocTabs_ == null)
            //MySystem.s_printOutExit(this, strMethod, "nil this._cltChangerLocTabs_");
        
        //vecUserChoice.addElement(this._cltChangerLocTabs_);
        
        /*if (this._cltSetVisibleTab_ == null)
            MySystem.s_printOutExit(this, strMethod, "nil this._cltSetVisibleTab_");
        
        vecUserChoice.addElement(this._cltSetVisibleTab_);*/
        
        if (! super._createLastUserPreferences_(strVersionAppli, vecUserChoice))
        {
	        MySystem.s_printOutExit(this, strMethod, "failed");
	        return false;
	    }
	    
	    return true;
    }
    
    /**
    1) confirm exit
    2) if (no current project) exit
       else
       {
            ask for saving
       }
    **/
    
    /**
        MEMO: called in superclass
    **/
    protected boolean _exitNow_()
    {
        if (! this._blnExitNormally) // eg: launched by NetBeans module
        {
            // TODO: "help".setVisible(false);
            if (super._fmaFrame_ != null)
                super._fmaFrame_.setVisible(false);
            
            return true;
        }
        
        super._blnExitNow_ = true;
        
        destroy();
        return true;
    }
    
    // -------
    // PRIVATE
    
    private boolean _blnExitNormally = true;
    //private boolean _blnDumpSecurityProviderJVM;
    
    // ---------
    // listeners
    //private TPMainAbstractListener _tpaListenerThis = null;
    
    
    private void _addListeners()
    {
        //this.addTPMainAbstractListener(this);
    }
    
    private void _removeListeners()
    {
        //this.removeTPMainAbstractListener(this);
    }
    
    private boolean _addProviders()
    {
        String strMethod = "_addProviders()";
        
       
        //java.security.Security.removeProvider("SunRsaJca");

        /*java.security.Security.removeProvider("SunJCE");
        java.security.Security.removeProvider("SunRsaSign");
        java.security.Security.removeProvider("SunJGSS");
        java.security.Security.removeProvider("SunJSSE");
        java.security.Security.removeProvider("SUN");
        */
        if (! _addProviderBC())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        /*if (! _addProviderSunJSSE())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }*/
            
        return true;
    }
    
    
    /* 
        if not instanciated, add BC provider
        if any error, NO ERROR/WARNING dialog, just return false
    */
    private boolean _addProviderBC()
    {
        String strMethod = "_addProviderBC()";
        
        if (java.security.Security.getProvider(KTLAbs.f_s_strProviderKstBC) != null)
        {
            MySystem.s_printOutTrace(this, strMethod, "1) java.security.Security.getProvider(KTLAbs.f_s_strProviderKstBC) != null");
            return true;
        }
        
        Class cls = null;
        
        try
        {
            cls = Class.forName("org.bouncycastle.jce.provider.BouncyCastleProvider");
        }
        
        catch(ClassNotFoundException excClassNotFound)
        {
            excClassNotFound.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excClassNotFound caught");
            return false;
        }
        
        java.security.Provider prv = null;
        
        try
        {
            prv = (java.security.Provider) cls.newInstance();
        }
        
        catch(InstantiationException excInstantiation)
        {
            excInstantiation.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excInstantiation caught");
            return false;
        }
        
        catch(IllegalAccessException excIllegalAccess)
        {
            excIllegalAccess.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excIllegalAccess caught");
            return false;
        }
                
        try
        {
            java.security.Security.addProvider(prv);
        }
        
        catch(SecurityException excSecurity)
        {
            excSecurity.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excSecurity caught");
            return false;
        }
        
        if (java.security.Security.getProvider(KTLAbs.f_s_strProviderKstBC) != null)
        {
            MySystem.s_printOutTrace(this, strMethod, "2) java.security.Security.getProvider(KTLAbs.f_s_strProviderKstBC) != null");
        }
        
        
        return true;
    }
    
    // sun.security.pkcs11.SunPKCS11
    
    
    /* 
        if not instanciated, add SunJSSE provider
        if any error, NO ERROR/WARNING dialog, just return false
    */
    private boolean _addProviderSunJSSE()
    {
        String strMethod = "_addProviderSunJSSE()";
        
      
        
        if (java.security.Security.getProvider(KTLAbs.f_s_strSecurityProviderSunRsaSign) != null)
            return true;
        
        Class cls = null;
        
        try
        {
            cls = Class.forName("com.sun.net.ssl.internal.ssl.Provider");
        }
        
        catch(ClassNotFoundException excClassNotFound)
        {
            excClassNotFound.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excClassNotFound caught");
            return false;
        }
        
        java.security.Provider prv = null;
        
        try
        {
            prv = (java.security.Provider) cls.newInstance();
        }
        
        catch(InstantiationException excInstantiation)
        {
            excInstantiation.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excInstantiation caught");
            return false;
        }
        
        catch(IllegalAccessException excIllegalAccess)
        {
            excIllegalAccess.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excIllegalAccess caught");
            return false;
        }
                
        try
        {
            java.security.Security.addProvider(prv);
        }
        
        catch(SecurityException excSecurity)
        {
            excSecurity.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excSecurity caught");
            return false;
        }
        
        return true;
    }
    
    
    private void _dumpSecurityProviderJvm()
    {
        String strVal = System.getProperty("_rcp_.dump.security.provider.jvm");
        
        if (strVal == null)
            return;
            
        if (strVal.toLowerCase().compareTo("true") != 0)
            return;
        
        System.out.println("\n\n");
        
        System.out.println("---- BEG DUMP PROVIDER JVM ----");
        
        java.security.Provider[] prvs = java.security.Security.getProviders();
        
        if (prvs == null)
        {
            System.out.println("nil prvs");
            System.exit(1);
        }
        
        System.out.println("prvs.length=" + prvs.length);
        
        for (int i=0; i<prvs.length; i++)
        {
            System.out.println("prvs[" + i + "].getName()=" + prvs[i].getName());
            System.out.println("prvs[" + i + "].getInfo()=" + prvs[i].getInfo());
            System.out.println("\n\n");
        }
        
        System.out.println("---- END DUMP PROVIDER JVM ----");
        System.out.println("\n\n");
    }
}
