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


import com.google.code.p.keytooliui.shared.util.eventlistener.*;
import com.google.code.p.keytooliui.shared.awt.awtevent.*;
import com.google.code.p.keytooliui.shared.awt.awteventmulticaster.*;
import com.google.code.p.keytooliui.shared.io.*;
import com.google.code.p.keytooliui.shared.lang.*;

import java.awt.*;

final public class DEditorJarProjTextHtml extends DEditorJarProjText implements
    DEditorJarProjTextHtmlListener
{    
    // ------
    // PUBLIC
    
    public synchronized void addDEditorJarProjTextHtmlListener(DEditorJarProjTextHtmlListener dhpListener)
	{
	    if (this._dhpListenerThis == null)
            this._dhpListenerThis = DEditorJarProjTextHtmlEventMulticaster.add(this._dhpListenerThis, dhpListener);
	}
  
	public synchronized void removeDEditorJarProjTextHtmlListener(DEditorJarProjTextHtmlListener dhpListener)
	{
	    if (this._dhpListenerThis != null)
            this._dhpListenerThis = DEditorJarProjTextHtmlEventMulticaster.remove(this._dhpListenerThis, dhpListener);
	}
	
	public void fileModified(DEditorJarProjTextHtmlEvent evtDEditorJarProjTextHtml)
	{
	    if (this._dhpListenerParent != null)
	        this._dhpListenerParent.fileModified(evtDEditorJarProjTextHtml);
	}
    
    
    public boolean writeTo(FileJar fjr)
        throws Exception
    {
        String f_strMethod = "writeTo(fjr)";
        
        if (! super.writeTo(fjr))
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        DEditorJarProjTextHtmlEvent evtThis = new DEditorJarProjTextHtmlEvent(this, DEditorJarProjTextHtmlEvent.DEDITORJARPROJTEXTHTMLEVENT_FILEMODIFIED,
            super.getPathAbsoluteJar(), super.getPathRelativeFile());
        
        if (this._dhpListenerThis != null)
            this._dhpListenerThis.fileModified(evtThis);
        
        // ending
        return true;
    }
    
    public void destroy()
    {
        removeDEditorJarProjTextHtmlListener(this);
        super.destroy();
        
    }   
    
    public DEditorJarProjTextHtml(
        DEditorJarProjTextHtmlListener dhpListenerParent,
        DEditorAbstractListener dapListenerParent,
        Component cmpFrameOwner,
        String strTitleApplication)
    {
        super(dapListenerParent, cmpFrameOwner, strTitleApplication);    
        this._dhpListenerParent = dhpListenerParent;
        addDEditorJarProjTextHtmlListener(this);
    }
    
    // -------
    // PRIVATE
    
    private DEditorJarProjTextHtmlListener _dhpListenerParent = null;
    private DEditorJarProjTextHtmlListener _dhpListenerThis = null;
}