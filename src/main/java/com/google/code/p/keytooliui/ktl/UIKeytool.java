/*
 *  Copyright (C) 2001-2011 keyTool IUI Project
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 *
 * @author bantchao
 *
 *
 */


package com.google.code.p.keytooliui.ktl;

import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.InsetsUIResource;

import java.awt.event.*;
import java.awt.Font;
import java.util.*;

import com.google.code.p.keytooliui.ktl.swing.frame.FMainUIKtl;
import com.google.code.p.keytooliui.ktl.swing.menuitem.*;
import com.google.code.p.keytooliui.ktl.util.changer.ChgLocMainUIToolKtl;
import com.google.code.p.keytooliui.shared.lang.MySystem;
import java.awt.Color;
import javax.swing.UIManager.LookAndFeelInfo;


public final class UIKeytool extends AppMainUIAbs
{
    static
    {
        try
        {

            
            for (LookAndFeelInfo lafInfo: UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(lafInfo.getName()))
                {
                   // TODO: in comments coz troubleshooting with JCheckBoxMenuItem
                   UIManager.setLookAndFeel(lafInfo.getClassName());
                    
                   break;
                }
            }
        }
        catch (Exception exc)
        {
            //UIKeytool._LOGGER.warning(exc.getMessage() + "\n ignoring");
        }

    }
    

    private static final String[] _F_STRS_PROPS_REQUIRED =
    {
        "_appli.title",
        "_appli.version",
        "_appli.name.short",
        "_appli.cache"
    };

    private static final String _F_STR_CLASS = "com.google.code.p.keytooliui.ktl.UIKeytool.";

    static
    {
        for (int i=0; i<UIKeytool._F_STRS_PROPS_REQUIRED.length; i++)
        {
            if (System.getProperty(UIKeytool._F_STRS_PROPS_REQUIRED[i]) == null)
            {
                System.err.println("missing property: " + UIKeytool._F_STRS_PROPS_REQUIRED[i] + ", exiting");
                System.exit(1);
            }
        }
    }
    
    
    // -------------
    // public static
    
    
    // called by NetBeans module
    public static UIKeytool s_getInstance()
    {
        return UIKeytool.s_getInstance(
                new String[0], 
                false // don't show dialog on exit
                );
        
    }
    
    // called by UIKeytool.main(strsArg)
    public static UIKeytool s_getInstance(
            String[] strsArg,
            boolean blnShowDialogExitConfirm
            )
    {
        
        
        String strMethod = UIKeytool._F_STR_CLASS + "s_getInstance(strsArg)";
        
        if(UIKeytool._INSTANCE != null)
            return UIKeytool._INSTANCE;
        
      
      
      // -----------------
        // BEG look and feel
        
        // ----
        try
        {
            /*if (AppMainUIAbs._F_BLN_SET_LAF_SWING_)
            {
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                UIManager.put("TitledBorder.font", UIManager.getFont("TitledBorder.font").deriveFont(Font.BOLD));
                UIManager.put("TitledBorder.titleColor", new ColorUIResource(80, 40, 40));
                UIManager.put("ToolTip.background", new ColorUIResource(Color.YELLOW));
                UIManager.put("ToolTip.foreground", new ColorUIResource(Color.DARK_GRAY));
                UIManager.put("TextField.border", new BorderUIResource(new SoftBevelBorder(BevelBorder.LOWERED)));
                UIManager.put("PasswordField.border", UIManager.get("TextField.border"));
                UIManager.put("ComboBox.padding", new InsetsUIResource(-2, 0, -2, 0));
            }
            else
            {
	            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }*/
            
     
           
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
        
        if (! MySystem.s_checkJvmVersion())
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
        
        UIKeytool._INSTANCE = ktl;
        return UIKeytool._INSTANCE;
   }
    
    // ----
    
    private static UIKeytool _INSTANCE = null;

    
    
    // BEG HIDDEN
    public static boolean S_BLN_FORCELOCALEEN = false; // should be private
    // END HIDDEN
    
  

    public static void main(String[] strsArg)
    {
        String strMethod = UIKeytool._F_STR_CLASS + "main(strsArg)";
        
        // tempo
        //System.setProperty(KTLAbs.F_STR_propKeyPolicyExtended, "true");
        
        if (UIKeytool.s_getInstance(
                strsArg,
                true // blnShowDialogExitConfirm
                ) == null)
            MySystem.s_printOutExit(strMethod, "failed");
    }
    
    
    // --------------
    // PRIVATE STATIC
    
    
    
    private static boolean _s_setLocale(String[] strsArg)
    {
        // tempo
        if (true)
            return true;
        
        String strMethod = _F_STR_CLASS + "_s_setLocale(strsArg)";
        
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
    
    @Override
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
    
    @Override
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
            false, // MEMO: blnParentDirReadOnlyAllowed
            blnShowDialogExitConfirm,
            false, // blnIsHelpGettingStarted
            // tmp forcing default english language
            false // ! UIKeytool.S_BLN_FORCELOCALEEN // blnInternAllowed
            );
        
	
        
        // ----
        // construct children
        
        super._fmaFrame_ = new FMainUIKtl(
            (WindowListener) this,
            (ActionListener) this,
            (ItemListener) this,
            (ChangeListener) this // tabbedPane
          
            );
             
        // ----

	    String strMethod = "UIKeytool(blnShowDialogExitConfirm)";
	    
	    if (! _createChangers())
	        MySystem.s_printOutExit(this, strMethod, "failed");
	     
	    // ----
        
        if (! super._createLastUserPreferences_(
                    //UIKeytool._F_STR_NAME_SHORT_APPLI,
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
    
   
    
    
    
}
