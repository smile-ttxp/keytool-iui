/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
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
 
 
package com.google.code.p.keytooliui.shared.swing.panel;

/*
    displays contents of page
    
    Known subclasses:
    . PPageSwAbs    ==> shared, secondary window, shared
    . PPageIfRdrAbs ==> rcr, internal frame, RCReader
    . PPageMediaAbs ==> beans, object inside HTML
*/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

import javax.swing.*;

import java.awt.*;

abstract public class PPageAbs extends JPanel
{
    // ---------------
    // ABSTRACT PUBLIC
    
    abstract public boolean pageReload();
    abstract public boolean pageClose();
    
    abstract public boolean init();
    abstract public void destroy();
    abstract public void showAbout();
    
    
    // ---------
    // PROTECTED
    
    protected Window _winParent_ = null; // either JFrame or JDialog
    
    protected String _strTitleAppli_ = null;
    
    protected void _showAbout_(String strWhat, String strWhere)
    {
        String strMethod = "_showAbout_(strWhat, strWhere)";
        
        if (strWhat==null || strWhere==null)
        {
            MySystem.s_printOutWarning(this, strMethod, "nil arg");
            return;
        }
        
        // fix up tbrl with spaces in url, under windows
        // not really the right place!
        
        String strCodeSpaceUrl = "%20";
        String strCodeSpaceFile = " ";
        String strWhereTbrlFixed = strWhere.replaceAll(strCodeSpaceUrl, strCodeSpaceFile);
        
        // fix up string too long ==> window width too big
        // eg: case where appli resides in JWS cache, and resource is inside jarred document (eg: ogg, html, rtf), in JWS cache too
        // especially under Linux! (coz of text font), also under Windows 2k
        
        // --
        
            
            
        com.google.code.p.keytooliui.shared.util.wrapper.Text2LinesRL ble = 
            new com.google.code.p.keytooliui.shared.util.wrapper.Text2LinesRL(strWhereTbrlFixed);
    		
		if (! ble.init())
		{
	        MySystem.s_printOutExit(this, strMethod, "failed");
	    }
	        
	    java.util.Vector vecStrLines = ble.getLines();
		
		if (vecStrLines == null)
		{
			MySystem.s_printOutExit(this, strMethod, "nil vecStrLines");
		}
		
		ble.destroy();
		ble = null;
            
        //System.out.println("strWhere=" + strWhere);
        
        final String f_strMargin = "  ";
        StringBuffer sbr = new StringBuffer();
        
        for (int i=0; i<vecStrLines.size(); i++)
        {
            sbr.append(f_strMargin);
            sbr.append((String) vecStrLines.elementAt(i));
            sbr.append("\n");
        }
        
        // --
        
        String strBody = strWhat;
        strBody += ":";
        strBody += "\n";
      
        
        // --
        strBody += sbr.toString();
        
        // --
        
        
        
        OPAbstract.s_showDialogInfo(
            this._winParent_, 
            this._strTitleAppli_, 
            strBody);
    }
    
    
    protected PPageAbs(
        String strTitleAppli,
        Window winParent
        )
    {
        super();
        
        this._strTitleAppli_ = strTitleAppli;
        this._winParent_ = winParent;
        
        setDoubleBuffered(true);
    }
}