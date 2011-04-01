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
 
 
package com.google.code.p.keytooliui.shared.swing.button;

/**
    known subclasses:
    . BESHelpJHSource ==> JavaHelp, From Source
    . BESHelpJHTrack  ==> JavaHelp; After Tracking
**/


import com.google.code.p.keytooliui.shared.lang.*;



abstract public class BESHelpJHAbstract extends BEnabledState
{
    // ---------------
    // ABSTRACT PUBLIC
    
    abstract public void setEnabledHelp(javax.help.HelpBroker hbr);
    
    // ------
    // PUBLIC
    
    public boolean init()
    {
        return true;
    }
   
 
    // ---------
    // PROTECTED
    
    protected BESHelpJHAbstract(
            String strToolTipText, 
            String strImage//,
            //ActionListener alr // created by subclasses
            )
            throws NullPointerException
    {
        super(com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strImage)/*, alr*/);
        
        String strMethod = "BESHelpJHAbstract(strToolTipText, strImage)";
        
        
        if (strToolTipText == null)
            MySystem.s_printOutExit(this, strMethod, "nil strToolTipText");
        
        /* !!!!!! MLK !!!!!!!!
        javax.swing.ArrayTable.put(ArrayTable.java:86)
	javax.swing.JComponent.putClientProperty(JComponent.java:3821)
	javax.swing.JComponent.setToolTipText(JComponent.java:2870)
	com.google.code.p.keytooliui.shared.swing.button.BESHelpJHAbstract.<init>(BESHelpJHAbstract.java:78)
         *==> setToolTipText(strToolTipText);
         */
        
        setToolTipText(strToolTipText);
        
           
    }
 
}