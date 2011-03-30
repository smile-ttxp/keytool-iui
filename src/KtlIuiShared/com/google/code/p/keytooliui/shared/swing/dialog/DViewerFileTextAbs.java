/*
 *
 * Copyright (c) 2001-2002 RagingCat Project.
 * LGPL License.
 * http://code.google.com/p/keytool-iui/
 *
 * This software is the confidential and proprietary information of RagingCat Project.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of RagingCat Project's license agreement.
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
 
 
 package com.google.code.p.keytooliui.shared.swing.dialog;

/**
    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    IMPORTANT:
    
    do not change extension from JDIALOG to JFRAME,
    as this window is opened thanks to a dialog window.
    ==> if extending from JFRAME, then this frame will freeze, as the appli wait for actions on the dialog window
    
    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    
    
    known subclasses:
    . DViewerFileTextSysAbs ==> preview, builders
    . DViewerFileTextJarProjAbs ==> preview, builders
    
**/


import com.google.code.p.keytooliui.shared.lang.*;

import java.awt.*;

abstract public class DViewerFileTextAbs extends DViewerFileAbs
{
    // ------
    // PUBLIC
    
    /**
        overwriting superclass's method
    **/
    public void setVisible(boolean bln)
    {
        String strMethod = "setVisible(bln)";
                
        if (bln)
        {
            this.pack(); // needed for windows, let display windows'menu bar 
            java.awt.Dimension dimScreen = null;
        
            try
            {
                java.awt.Toolkit tkt = java.awt.Toolkit.getDefaultToolkit();
                dimScreen = tkt.getScreenSize();
            }
        
            catch(java.awt.AWTError errAWT)
            {
                errAWT.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, "errAWT caught");
            }
        
            java.awt.Insets ins = getInsets();
        
            int intW = dimScreen.width - ins.left - ins.right;
            int intH = dimScreen.height - ins.top - ins.bottom;
            java.awt.Dimension dim = new java.awt.Dimension(intW, intH);
            setSize(dim);
        }
        
        
        /* BEGIN MODIF march 21, 2002
        
            bug: J2SE1.4.0/WinNT/RCR-dev1.1:
            
            	at java.awt.Window.hide(Window.java:477)
	        at java.awt.Dialog.hide(Dialog.java:575)
	        at java.awt.Component.show(Component.java:1130)
	        at java.awt.Component.setVisible(Component.java:1083)
	        at com.google.code.p.keytooliui.shared.swing.dialog.DViewerFileTextAbs.setVisible(DViewerFileTextAbs.java:95)
	        at java.awt.Window$1$DisposeAction.run(Window.java:520)
	        at java.awt.Window.dispose(Window.java:534)
	        at java.awt.Dialog.disposeImpl(Dialog.java:588)
	        at java.awt.Dialog.dispose(Dialog.java:584)
	        at com.google.code.p.keytooliui.shared.swing.dialog.DEscapeAbstract.destroy(DEscapeAbstract.java:11
        */
        
        /*
        super.setVisible(bln);
        
        if (! bln)
            destroy();
        */
        
            
        super.setVisible(bln);
        
       // END MODIF march 21, 2002

    }

    // ---------
    // PROTECTED

    protected DViewerFileTextAbs(
        Component cmpFrameOwner,
        String strTitleApplication,
        String strTitleSuffix)
    {
        super(cmpFrameOwner, strTitleApplication, strTitleSuffix);
    } 
}