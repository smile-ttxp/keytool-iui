package com.google.code.p.keytooliui.ktl;

/**
    contains:
    . 1 frame
**/

// Uses actions with a tool bar and a menu.

import java.security.Provider;
import com.google.code.p.keytooliui.ktl.swing.frame.*;
import com.google.code.p.keytooliui.ktl.swing.menuitem.MISelTabCreateKprV3CDsa;
import com.google.code.p.keytooliui.ktl.util.changer.*;
import com.google.code.p.keytooliui.ktl.util.jarsigner.*;
import com.google.code.p.keytooliui.ktl.swing.menuitem.*;
import com.google.code.p.keytooliui.ktl.io.*;

import com.google.code.p.keytooliui.shared.*;
import com.google.code.p.keytooliui.shared.io.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.util.eventlistener.*;
import com.google.code.p.keytooliui.shared.awt.awtevent.*;
import com.google.code.p.keytooliui.shared.awt.awteventmulticaster.*;
import com.google.code.p.keytooliui.shared.swing.dialog.*;
import com.google.code.p.keytooliui.shared.util.changer.*;
import com.google.code.p.keytooliui.shared.swing.menuitem.*;

import javax.swing.event.*;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import java.io.*;
import java.awt.event.*;
import java.awt.Font;
import java.util.*;


final public class UIKeytool extends AppMainUIAbs
{
    // --------------------
    // FINAL STATIC PRIVATE

    final static private String _f_s_strClass = "com.google.code.p.keytooliui.ktl.UIKeytool.";
    
    final static public String f_s_strAppliNameShort = "ktl";
    
    
    // -------------
    // static public
    
    
    // called by NetBeans module
    static public UIKeytool s_getInstance()
    {
        return UIKeytool.s_getInstance(
                new String[0], 
                false // don't show dialog on exit
                );
        
    }
    
    // called by UIKeytool.main(strsArg)
    static public UIKeytool s_getInstance(
            String[] strsArg,
            boolean blnShowDialogExitConfirm
            )
    {
        
        
        String strMethod = UIKeytool._f_s_strClass + "s_getInstance(strsArg)";
        
        if(UIKeytool._s_ktlInstance != null) 
            return UIKeytool._s_ktlInstance;
        
        
        
        
        /* BEG TEMPO */
        
        
        /**
         *in comments, coz tbrl with NetBeeans, with module, getting
         *java.security.AccessControlException: access denied (java.security.SecurityPermission javax.crypto.CryptoAllPermission)
        at java.security.AccessControlContext.checkPermission(AccessControlContext.java:264)
        at java.security.AccessController.checkPermission(AccessController.java:427)

         **/
        
  /*      String strPerm = "javax.crypto.CryptoAllPermission";
        
        
        SecurityManager security = System.getSecurityManager();
     
        if (security != null) 
        {
            //security.checkXXX(argument,  . . . );
            
            java.security.SecurityPermission spm = new java.security.SecurityPermission(strPerm);
            
            try
            {
                security.checkPermission(spm);
                java.security.AccessController.checkPermission(spm);
                
            }
            
            catch(SecurityException excSecurity)
            {
                excSecurity.printStackTrace();
                System.err.println("excSecurity caught");
                System.exit(1);
            }
        }
*/
        
        
        /* END TEMPO */
        
        // tempo
/**
        String[] strs = 
        {
          "deployment.user.security.trusted.cacerts",
          "deployment.system.security.trusted.cacerts"
        };

        for (int i=0; i<strs.length; i++)
        {
           String str = System.getProperty(strs[i]);

           if (str == null)
             System.out.println(strs[i] + ": nil value");
           else
             System.out.println(strs[i] + ": " + str);
        }


        System.exit(1);
       **/
      
      
      // -----------------
        // BEG look and feel
        
        // ----
        try
        {
            if (AppMainUIAbs._f_s_blnSetLAFSwing_)
            {
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                UIManager.put("TitledBorder.font", UIManager.getFont("TitledBorder.font").deriveFont(Font.BOLD));
                UIManager.put("TitledBorder.titleColor", new ColorUIResource(80, 40, 40));
            }
            else
            {
	            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
           
        }

        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutTrace(strMethod, "exc caught, ignoring");
        }
            

	    // -----------------
        // END look and feel
        
        
        /*
            in comments coz tbrl with JMF's libraries and IANow 5.5.1
            
            IMPORTANT: change boolean value of type "blnAllowedCleanUpAllManagerMedia" to "true", if uncommenting
            the line below
        */      
        MySystem.s_printOutFlagDev(strMethod, "IN COMMENTS: call to com.google.code.p.keytooliui.javax.media.MyManager.s_loadLibrary()");
        //com.google.code.p.keytooliui.javax.media.MyManager.s_loadLibrary();
        
        
        /*
         *MEMO: default providers with JRE 1.6:
         *  strProvCur=SUN
            strProvCur=SunRsaSign
            strProvCur=SunJSSE
            strProvCur=SunJCE
            strProvCur=SunJGSS
            strProvCur=SunSASL
            strProvCur=XMLDSig
            strProvCur=SunPCSC
            strProvCur=SunMSCAPI

         */
        
        

        java.security.Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        // --
        
        if (! _s_setLocale(strsArg))
        {
            MySystem.s_printOutError(strMethod, "failed");
            return null;
        }
        // ----
        
        if (! MySystem.s_checkJvmVersion(UIKeytool.s_getTitleAppli()))
        {
            MySystem.s_printOutError(strMethod, "failed");
            return null;
        }
        
        // -----
    
        UIKeytool ktl = new UIKeytool(
                blnShowDialogExitConfirm
                );
        
        if (! ktl.init())
        {
            MySystem.s_printOutError(strMethod, "failed");
            return null;
        
        }
        
        if (! ktl.start())
        {
            MySystem.s_printOutError(strMethod, "failed");
            return null;
        }
        
        UIKeytool._s_ktlInstance = ktl;
        return UIKeytool._s_ktlInstance;
   }
    
    static public String s_getTitleAppli()
    {
        if (UIKeytool._s_strTitleAppli != null)
            return UIKeytool._s_strTitleAppli;
            
        // assign title
        
        UIKeytool._s_strTitleAppli = new String("KeyTool IUI");
        
        //if (KTLAbs.s_isPolicyExtended())
          //  UIKeytool._s_strTitleAppli += " Plus";
           
           
        return UIKeytool._s_strTitleAppli;
    }
    
    // ----
    
    static private UIKeytool _s_ktlInstance = null;
    static private String _s_strTitleAppli = null;
    //static private Boolean _s_booPolicyExtended = null;
    
    
    
    
  
    // -----------
    // INITIALIZER
    
    /*static
    {
        String strMethod = _f_s_strClass + "initializer()";
        
        // ----
        // check for specific classes in external packages or libraries
        
        try
        {
	        //  should be located in [jvm-home]/lib/tools.jar (to be verified!) for use with jarsigner tool programatically
	        Class.forName("sun.security.util.SignatureFile");
	        // sun.misc.BASE64Encoder
	    }
	    
	    catch(ClassNotFoundException excClassNotFound)
	    {
	        excClassNotFound.printStackTrace();
	        MySystem.s_printOutExit(strMethod, "excClassNotFound caught");
	    }
    }*/
    
    // -------------
    // STATIC PUBLIC
    
   // static public boolean S_BLN_DUMPSECURITYPROVIDERJVM = true; //false; // !! should be static private !!
   
    
    
    // BEG HIDDEN
    static public boolean S_BLN_FORCELOCALEEN = false; // should be private
    // END HIDDEN
    
    
    // ------------------
    // STATIC INITIALIZER
    /*
    static
    {
        String strMethod = _f_s_strClass + "initializer";
        
        PropertyResourceBundle prbMagic = null;
        
        try
        {
            prbMagic = Shared.s_getPrbMagic();
        }
        
        catch(MissingResourceException exc)
        {
            MySystem.s_printOutFlagDev(strMethod, exc.toString()); 
        }
        
        
        if (prbMagic != null)
        {
            String str = null;
            */
            // --
            /*str = null;
            try { str = prbMagic.getString(_f_s_strClass + "dumpSecurityProviderJvm"); }
            catch (java.util.MissingResourceException excMissingResource) {}
            
            if (str != null) // else ignoring
            {
                str = str.toLowerCase();
                
                if (str.compareTo("false") == 0)
                    UIKeytool.S_BLN_DUMPSECURITYPROVIDERJVM = false;
                else if (str.compareTo("true") == 0)
                    UIKeytool.S_BLN_DUMPSECURITYPROVIDERJVM = true;
                else
                    MySystem.s_printOutTrace(strMethod, "ignoring uncaught value, key=" + _f_s_strClass + "dumpSecurityProviderJvm" + ", value =" + str);

            }
            
            */
            
            
            // 
            

            
        
 
            
            //
 /*
            
            // --
            str = null;
            try { str = prbMagic.getString(_f_s_strClass + "forceLocaleEn"); }
            catch (java.util.MissingResourceException excMissingResource) {}
            
            if (str != null) // else ignoring
            {
                str = str.toLowerCase();
                
                if (str.compareTo("false") == 0)
                    UIKeytool.S_BLN_FORCELOCALEEN = false;
                else if (str.compareTo("true") == 0)
                    UIKeytool.S_BLN_FORCELOCALEEN = true;
                else
                    MySystem.s_printOutTrace(strMethod, "ignoring uncaught value, key=" + _f_s_strClass + "forceLocaleEn" + ", value =" + str);
            }
            
            // --  
        }
    } 
   */     
    // -------------
    // STATIC PUBLIC

    static public void main(String[] strsArg)
    {
        String strMethod = UIKeytool._f_s_strClass + "main(strsArg)";
        
        // tempo
        //System.setProperty(KTLAbs.F_STR_propKeyPolicyExtended, "true");
        
        if (UIKeytool.s_getInstance(
                strsArg,
                true // blnShowDialogExitConfirm
                ) == null)
            MySystem.s_printOutExit(strMethod, "failed");
    }
    
    
    // --------------
    // STATIC PRIVATE
    
    
    
    static private boolean _s_setLocale(String[] strsArg)
    {
        // tempo
        if (true)
            return true;
        
        String strMethod = _f_s_strClass + "_s_setLocale(strsArg)";
        
        Locale loc = Locale.getDefault();
        
    
        
        if (UIKeytool.S_BLN_FORCELOCALEEN)
        {
            loc = new Locale("en", "en"); 
            Locale.setDefault(loc);
            return true;
        }
        
        
        
  
        
        
        // ----------
        // 1) no strsArg
        if (strsArg.length == 0)
        {
            return true;
        }
        
        // ---------------
        // 2) no pair strsArg
       
        else if ((strsArg.length %2) != 0)
        {
            MySystem.s_printOutError(strMethod, "wrong number of args, should be a a pair value:" + strsArg.length);
            return false;
        }
        
        
        else
        {
            // --------
            // 3) pairs
        
            // ----
            int intArgPairNb = strsArg.length/2;
       
            if (intArgPairNb != 1)
            {
                MySystem.s_printOutError(strMethod, "wrong number of args pairs, should be equal to 1:" + intArgPairNb);
                return false;
            }
            
            // ----------------
            // one pair of args
            
   
            String strKey = strsArg[0];
            
            if (! (strKey.startsWith("-")))
            {
                MySystem.s_printOutError(strMethod, "wrong arg, not starting with \"-\":" + strKey);
                return false;
            }
            
            if (strsArg[0].equalsIgnoreCase("-lang"))
            {
                String strLang = strsArg[1];
                
                strLang = strLang.toLowerCase();
                
                if (strLang.equalsIgnoreCase("en"))
                {
                    loc = new Locale( "en", "en" ); 
                }
                
                else if (strLang.equalsIgnoreCase("fr"))
                {
                    loc = new Locale( "fr", "FR" ); 
                }
                    
                else if (strLang.equalsIgnoreCase("it"))
                {
                    loc = new Locale( "it", "IT" ); 
                }
                    
                else if (strLang.equalsIgnoreCase("de"))
                {
                    loc = new Locale( "de", "DE" ); 
                }
                
                else
                {
                    MySystem.s_printOutError(strMethod, "unsupported lang:" + strLang + ", allowed: \"en\" - \"fr\" - \"de\" - \"it\" ");
                    return false;
                }
                
            }
                
            else
            {
                MySystem.s_printOutError(strMethod, "unknown key arg:" + strKey + ", should be: \"-lang\" [en-fr-de]");
                return false;
            }
            
            
        } // end of else args
            // ----
        
        
        // first of all: assigning locales
        Locale.setDefault(loc);
        
        // ending
        return true;
    }
    
    
    
    
    
    // ------
    // PUBLIC  
    
    public boolean start()
    {
        if (! super.start())
            return false;
        
        if (super._fmaFrame_ != null)
            ((FMainUIKtl) super._fmaFrame_).setSelectedTaskWelcomeKtl();
        
        return true;
    }
    
    
 
    // beg: used by NetBeans module
    
    public boolean isVisibleFrame()
    {
        if (super._fmaFrame_ == null) // !!! bug
            return false;
        
        return super._fmaFrame_.isVisible();
    }
    
    public void setVisibleFrame(boolean bln)
    {
        if (super._fmaFrame_ != null)
            super._fmaFrame_.setVisible(bln);
    }
    
    // !!! should be changed in getExtendedStateFrame()
    public int getStateFrame()
    {
        if (super._fmaFrame_ == null) // !!! bug
            return -1;
        
        return super._fmaFrame_.getState();
    }
    
    public void setStateFrame(int intVal)
    {
        if (super._fmaFrame_ != null)
            super._fmaFrame_.setState(intVal);
    }
    
    public void toFrontFrame()
    {
        if (super._fmaFrame_ != null)
            super._fmaFrame_.toFront();
    }
    
    // end: used by NetBeans module
    
    
    /**
    
    **/
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";
        
        try
        {                        
           
            
            // --
            // BEG setSelectedTask[x]
            
            if (evtAction.getSource() instanceof MISelTabAbs)
            {        
                MISelTabAbs itm = (MISelTabAbs) evtAction.getSource();
                
                if (itm instanceof MISelTabCreateKst)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskCreateKst();
                    
                    // ending
                    return;
                }
                
                if (itm instanceof MISelTabCreateKprV3CDsa)
                {   
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskCreateKprV3CDsa();
                    
                    // ending
                    return;
                }
                
                if (itm instanceof MISelTabCreateKprDsa)
                {   
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskCreateKprV1CDsa();
                    
                    // ending
                    return;
                }
                
                if (itm instanceof MISelTabCreateKprV3CEc)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskCreateKprV3CEc();
                    
                    // ending
                    return;
                }
                
                if (itm instanceof MISelTabCreateKprEc)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskCreateKprV1CEc();
                    
                    // ending
                    return;
                }
                
                if (itm instanceof MISelTabCreateKprV3CRsa)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskCreateKprV3CRsa();
                    
                    // ending
                    return;
                }
                
                
                if (itm instanceof MISelTabCreateKprRsa)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskCreateKprV1CRsa();
                    
                    // ending
                    return;
                }
                
                if (itm instanceof MISelTabCreateShkAll)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskCreateShkAll();
                    
                    // ending
                    return;
                }
                
                if (itm instanceof MISelTabKprFromKprKst)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskKprFromKprKst();
                    
                    // ending
                    return;
                }
                
                
                if (itm instanceof MISelTabKprAnyToCsr)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskKprAnyToCsr();
                    
                    // ending
                    return;
                }
                
                if (itm instanceof MISelTabTcrToCrt)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskTcrToCrt();
                    
                    // ending
                    return;
                }
                
                if (itm instanceof MISelTabKprToKpr)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskKprToKpr();
                    
                    // ending
                    return;
                }
                
                if (itm instanceof MISelTabKprToCrt)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskKprToCrt();
                    
                    // ending
                    return;
                }
                
                if (itm instanceof MISelTabKprToSig)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskKprToSig();
                    
                    // ending
                    return;
                }
                
                if (itm instanceof MISelTabKprToSCms)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskKprToSCms();
                    
                    // ending
                    return;
                }
                
                if (itm instanceof MISelTabKprToXmlSig)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskKprToXmlSig();
                    
                    // ending
                    return;
                }
                
                if (itm instanceof MISelTabJarSign)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskKprToJarSig();
                    
                    // ending
                    return;
                }
                
                if (itm instanceof MISelTabArcDir)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskDirToArc();
                    
                    // ending
                    return;
                }
                
                if (itm instanceof MISelTabShkToCryptDec)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskShkToCryptDec();
                    
                    // ending
                    return;
                }
                
                
                
                if (itm instanceof MISelTabKprRsaToCryptDec)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskKprRsaToCryptDec();
                    
                    // ending
                    return;
                }
                
                if (itm instanceof MISelTabShkToCryptEnc)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskShkToCryptEnc();
                    
                    // ending
                    return;
                }
                
                if (itm instanceof MISelTabTcrRsaToCryptEnc)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskTcrRsaToCryptEnc();
                    
                    // ending
                    return;
                }
                
               
                if (itm instanceof MISelTabKprRsaToCryptEnc)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskKprRsaToCryptEnc();
                    
                    // ending
                    return;
                }
                
                
                
                if (itm instanceof MISelTabIOShkOut)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskIOShkOut();
                    
                    // ending
                    return;
                }
               
                if (itm instanceof MISelTabIOShkIn)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskIOShkIn();
                    
                    // ending
                    return;
                }
                
                if (itm instanceof MISelTabCrtToSig)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskCrtToSig();
                    
                    // ending
                    return;
                }
                
                if (itm instanceof MISelTabJarVerify)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskJarToSig();
                    
                    // ending
                    return;
                }
                
                if (itm instanceof MISelTabWelcomeKtl)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskWelcomeKtl();
                    
                    // ending
                    return;
                }
                
                if (itm instanceof MISelTabCmsToSig)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskCmsToSig();
                    
                    // ending
                    return;
                }
                
                if (itm instanceof MISelTabXmlToSig)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskXmlToSig();
                    
                    // ending
                    return;
                }
                
                if (itm instanceof MISelTabTcrFromCCa)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskTcrFromCCa();
                    
                    // ending
                    return;
                }
                
                if (itm instanceof MISelTabTcrFromCrt)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskTcrFromCrt();
                    
                    // ending
                    return;
                }
                
                if (itm instanceof MISelTabKprFromKprDer)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskKprFromKprDer();
                    
                    // ending
                    return;
                }
                
                if (itm instanceof MISelTabKprFromKprPem)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskKprFromKprPem();
                    
                    // ending
                    return;
                }
                
                
                if (itm instanceof MISelTabKprAnyFromCrt)
                {
                    if (super._fmaFrame_ != null)
                        ((FMainUIKtl) super._fmaFrame_).setSelectedTaskKprAnyFromCrt();
                    
                    // ending
                    return;
                }
                
                 
                // ending
                return;
            }

            // --
            // END setSelectedTask[x]
            
            
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
    
    
    // ---------
    // PROTECTED
    
    protected UIKeytool(boolean blnShowDialogExitConfirm)
    {
        super(
            blnShowDialogExitConfirm, // this field used for "blnExitNormally"
            false, // blnAllowedCleanUpAllManagerMedia - change to "true" if uncommenting "MyManager.s_loadLibrary()" line
            false, // MEMO: blnParentDirReadOnlyAllowed
            UIKeytool.s_getTitleAppli(), 
            UIKeytool.f_s_strAppliNameShort, // eg: "ktl"
            blnShowDialogExitConfirm,
            false, // blnIsHelpGettingStarted
            // tmp forcing default english language
            false // ! UIKeytool.S_BLN_FORCELOCALEEN // blnInternAllowed
            );
        
	    boolean blnIsReg = _chkLic();
        
        // ----
        // construct children
        
        super._fmaFrame_ = new FMainUIKtl(
            UIKeytool.s_getTitleAppli(),
            (WindowListener) this,
            (ActionListener) this,
            (ItemListener) this,
            (ChangeListener) this, // tabbedPane
            super._strLic_,
            blnIsReg
            );
             
        // ----

	    String strMethod = "UIKeytool(blnShowDialogExitConfirm)";
	    
	    if (! _createChangers())
	        MySystem.s_printOutExit(this, strMethod, "failed");
	     
	    // ----
        
        if (! super._createLastUserPreferences_(
                    UIKeytool.f_s_strAppliNameShort, 
                    MIHelpAboutAppliUIKtl.f_s_strDlgBodyVersion))
	        MySystem.s_printOutExit(this, strMethod, "failed");
    }
    
    // -------
    // PRIVATE
    
    
    
    private boolean _createChangers()
    {
        String strMethod = "_createChangers()";
        
        if (super._fmaFrame_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._fmaFrame_");
            return false;
        }
        
        super._cltChangerLocToolbar_ = new ChgLocMainUIToolKtl(super._fmaFrame_);
     
        // ending
        return true;
    }
    
    /**
        don't show this package in standard output
    **/
    private boolean _chkLic()
    {
        String strMethod = "_chkLic()";
        
        // ---
        /*
            modif june 26, 2003
            from v1.1 to v1.2: disabling all
        */
        
        if (true)
        {
            return false; // unregistered
        }
        
        // end
        
        ChkRegUIKtl crg = new ChkRegUIKtl(UIKeytool.s_getTitleAppli());
        
        if (! crg.doJob())
            MySystem.s_printOutExit(strMethod, "failed");
       
        super._strLic_ = crg.getLic();
        
        if (super._strLic_ == null)
            MySystem.s_printOutExit(strMethod, "nil super._strLic_");
        
        return crg.isReg();
    }
    
    
    
    
    
    
    
    
}
