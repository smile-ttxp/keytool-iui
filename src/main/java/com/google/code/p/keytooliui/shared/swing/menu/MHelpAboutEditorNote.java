/*
 *
 * Copyright (c)  2001-2011 keyTool IUI Project.
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

import com.google.code.p.keytooliui.shared.lang.*;

import java.awt.*;

public final class MHelpAboutEditorNote extends MHelpAboutAbstract 
{
    /** ##############
        PRIVATE STATIC
        ##############
    **/
    
    private static java.util.ResourceBundle _s_rbeResources;
    
    private static final String _f_s_strBundleFileShort =
        com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
        ".MHelpAboutEditorNote" // class name
        ;
    
    
    private static final String _f_s_strBundleFileLong = _f_s_strBundleFileShort + ".properties";

    static
    {
        final String f_strWhere = "com.google.code.p.keytooliui.shared.swing.menu.MHelpAboutEditorNote";
        
        try
        {
            _s_rbeResources = java.util.ResourceBundle.getBundle(_f_s_strBundleFileShort, 
                java.util.Locale.getDefault());
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(f_strWhere, _f_s_strBundleFileLong + " not found, excMissingResource caught");
        }
    }
    
    
    /** ######
        PUBLIC
        ######
    **/
    
    
    public boolean init()
    {
        String f_strMethod = "init()";
        
        if (! _loadResourceBundle())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        if (! super._init_())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    public MHelpAboutEditorNote(Component cmpFrameOwner)
    {
        super(cmpFrameOwner);
    }
 
   
    
    /** #######
        PRIVATE
        #######
    **/
    
   
    
    
    private boolean _loadResourceBundle()
    {
        final String strMethod = "_loadResourceBundle()";
        
         /* MEMO: trim() not necessary
        */
        try
        {   
            String strValue = null;
	        
	        //strValue = _s_rbeResources.getString("infoAboutTitle");
	        //super._strInfoAboutTitle_ = strValue;
	        
	         strValue = _s_rbeResources.getString("infoAboutBody");
	        super._strInfoAboutBody_ = strValue;
	        
	    }
	    
	    catch (java.util.MissingResourceException excMissingResource)
	    {
	        excMissingResource.printStackTrace();
	        MySystem.s_printOutError(this, strMethod, _f_s_strBundleFileLong + ", excMissingResource caught");
	        return false;
	    }
	    
	    catch(IndexOutOfBoundsException excIndexOutOfBounds) // for charAt(0)
	    {
	        excIndexOutOfBounds.printStackTrace();
	        MySystem.s_printOutError(this, strMethod, _f_s_strBundleFileLong + ", excIndexOutOfBounds caught");
	        return false;
	    }
	    
	    // ending
	    return true;
    }
}