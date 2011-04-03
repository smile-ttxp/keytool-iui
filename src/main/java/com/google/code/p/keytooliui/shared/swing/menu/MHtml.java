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


import com.google.code.p.keytooliui.shared.lang.*;


import javax.swing.*;


final public class MHtml extends MAbstract
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strIcon = "html16.gif";
    
    // --------------
    // STATIC PRIVATE
    
    static private String _s_strTextThis = null;
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        String strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".MHtml" // class name
            ;
    
        String strWhere = "com.google.code.p.keytooliui.rcr.swing.menu.MEditPage";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            _s_strTextThis = rbeResources.getString("textThis");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught");
            
        }
    }
    
    // ------
    // PUBLIC
    
    public boolean init() { return true; }
    public void destroy() {}
    
    public MHtml()
    {
        super();
        
        String strMethod = "MHtml()";
        
        ImageIcon iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(_f_s_strIcon);
	    
	    if (iin == null)
	        MySystem.s_printOutExit(this, strMethod, _f_s_strIcon + ": nil iin");
	    
	    this.setIcon(iin);
        
        setText(_s_strTextThis);
    }
}