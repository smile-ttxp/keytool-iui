/*
 *
 * Copyright (c) 2001-2002 keyTool IUI Project.
 * LGPL License.
 * http://code.google.com/p/keytool-iui/
 *
 *
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of keyTool IUI Project's license agreement.
 *
 * THE SOFTWARE IS PROVIDED AND LICENSED "AS IS" WITHOUT WARRANTY OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. 
 *
 * LICENSE FOR THE SOFTWARE DOES NOT INCLUDE ANY CONSIDERATION FOR ASSUMPTION OF RISK
 * BY KEYTOOL IUI PROJECT, AND KEYTOOL IUI PROJECT DISCLAIMS ANY AND ALL LIABILITY FOR INCIDENTAL
 * OR CONSEQUENTIAL DAMAGES ARISING OUT OF THE USE OR OPERATION OF OR INABILITY
 * TO USE THE SOFTWARE, EVEN IF KEYTOOL IUI PROJECT HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES. 
 *
 */
 

 
 package com.google.code.p.keytooliui.shared.swing.menu;

/**
    known subclasses:
    . MHelpAllMainReader
    . MHelpAllMainGenDoc
    . MHelpAllMainGenYpl
    . MHelpAllMainUIKtl
    
    contains:
    . 1 menu helpTopics
    . 1 menu online
    
    -- BEG PENDING --
    . 1 menu ??? pointing to:
       . (if not yet registered) install reg file
       . (if not yet registered) License ==> point to the help, 2 cases: unregistered, & registered
       . ?? BouncyCastle license
       . ordering info
    -- END PENDING --
       
    . 1 menu about
    
    children are:
    . created in subclasses,
    . inited, added, and destroyed in this class
**/

 import com.google.code.p.keytooliui.shared.lang.*;

abstract public class MHelpAllMainAbs extends MAbstract
{
    // --------------
    // PRIVATE STATIC
    
    private static String _s_strText = null;
    private static char _s_chrThisMnemo;
    
    // ------------------
    // STATIC INITIALIZER
    
    static
    {
        java.util.ResourceBundle rbeResources;
    
        final String f_strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".MHelpAllMainAbs" // class name
            ;
    
        
        
        final String f_strWhere = "com.google.code.p.keytooliui.shared.swing.menu.MHelpAllMainAbs";
        
        try
        {
            rbeResources = java.util.ResourceBundle.getBundle(f_strBundleFileShort, 
                java.util.Locale.getDefault());
                
            MHelpAllMainAbs._s_strText = rbeResources.getString("text");
            String strThisMnemo = rbeResources.getString("mnemo");
            MHelpAllMainAbs._s_chrThisMnemo = strThisMnemo.trim().charAt(0);
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(f_strWhere, "excMissingResource caught");
        }
        
        catch(IndexOutOfBoundsException excIndexOutOfBounds) // for charAt(0)
	    {
	        excIndexOutOfBounds.printStackTrace();
	        MySystem.s_printOutExit(f_strWhere, "excIndexOutOfBounds caught");
	    }
    }
    
    // ------
    // PUBLIC
    
    // menubar's menuItem'
    public void setEnabledHelpSourceAndTrack(javax.help.HelpBroker hbrHelpStandard)  
    {
       if (this._matHelpOffline_ != null)
           ((MHelpOffline) this._matHelpOffline_).setEnabledHelpSourceAndTrack(hbrHelpStandard);
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (this._matHelpOffline_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._matHelpOffline_");
            return false;
        }
        
        if (! this._matHelpOffline_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // march 14, 2003: memo: subclasses: creation in comments coz not cross-platforms
        if (this._matHelpOnline_ != null)
        {
            if (! this._matHelpOnline_.init())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
        }
        
        if (this._matAbout_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._matAbout_");
            return false;
        }
        
        if (! this._matAbout_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        this.add(this._matHelpOffline_);
        
        // march 14, 2003: memo: subclasses: creation in comments coz not cross-platforms
        if (this._matHelpOnline_ != null)
            this.add(this._matHelpOnline_);
        
        this.addSeparator();
        this.add(this._matAbout_);
        
        return true;
    }
    
    public void destroy()
    {
        if (this._matHelpOffline_ != null)
        {
            this._matHelpOffline_.destroy();
            this._matHelpOffline_ = null;
        }
        
        if (this._matHelpOnline_ != null)
        {
            this._matHelpOnline_.destroy();
            this._matHelpOnline_ = null;
        }
        
        if (this._matAbout_ != null)
        {
            this._matAbout_.destroy();
            this._matAbout_ = null;
        }
        
        
    }
    
    // ---------
    // PROTECTED
    
    protected MAbstract _matHelpOffline_ = null;
    protected MAbstract _matHelpOnline_ = null;
    protected MAbstract _matAbout_ = null;
    
    protected MHelpAllMainAbs()
    {
        setText(MHelpAllMainAbs._s_strText);
        setMnemonic(MHelpAllMainAbs._s_chrThisMnemo);
    }
}
