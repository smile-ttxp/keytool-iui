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
 
 
package com.google.code.p.keytooliui.shared.swing.dialog;


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;
import com.google.code.p.keytooliui.shared.util.eventlistener.*;
import com.google.code.p.keytooliui.shared.awt.awtevent.*;

import java.awt.*;
import java.io.*;

public final class DEditorNote extends DEditorAbstract 
{    
    
    // --------------
    // PRIVATE STATIC
    
    private static String _s_strTitleSuffix = null;
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        String strWhere = "com.google.code.p.keytooliui.shared.swing.dialog.DEditorNote";
        
        String strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".DEditorNote" // class name
            ;
        
        String strBundleFileLong = strBundleFileShort + ".properties";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
	        DEditorNote._s_strTitleSuffix = rbeResources.getString("titleSuffix");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, strBundleFileLong + " not found, excMissingResource caught");
        }
    }
    
    // -------------
    // PUBLIC ACCESS
    
    public String getFileName() { return this._strFileName; }
    public int getPageId() { return this._intPageId; }
    
    
    // ------
    // PUBLIC
    
    public boolean doFileNew(String strFileName, int intPageId)
        throws Exception
    {
        String strMethod = "doFileNew(strFileName, intPageId)";
        
        if (strFileName == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strFileName");
            return false;
        }
        
        this._strFileName = strFileName;
        
        if (intPageId < 1)
        {
            MySystem.s_printOutError(this, strMethod, "wrong value, intPageId=" + intPageId);
            return false;
        }
        
        this._intPageId = intPageId;
        return ((PEditorNote)super._pnd_).doFileNew(intPageId);
    }
    
    public boolean doFileOpen(
        String strFileName,
        ObjectInputStream ois,
        int intPageId)
        
        throws Exception
    {
        String strMethod = "doFileOpen(strFileName, ois, intPageId)";
        
        if (strFileName == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strFileName");
            return false;
        }
        
        this._strFileName = strFileName;
        
        if (intPageId < 1)
        {
            MySystem.s_printOutError(this, strMethod, "wrong value, intPageId=" + intPageId);
            return false;
        }
        
        this._intPageId = intPageId;
        return ((PEditorNote)super._pnd_).doFileOpen(ois, intPageId);
    }
    
    public boolean writeTo(ObjectOutputStream oos)
        throws Exception
    {
        return ((PEditorNote)super._pnd_).writeTo(oos);
    }
    
    public void fileRecord(PEditorAbstractEvent evtPEditorAbstract)
    {
        String strMethod = "fileRecord(evtPEditorAbstract)";
        
        if (this._intPageId < 1)
        {
            MySystem.s_printOutExit(this, strMethod, "wrong value, this._intPageId=" + this._intPageId);
        }
     
        super.fileRecord(evtPEditorAbstract);   
    }
	
	public void fileDelete(PEditorAbstractEvent evtPEditorAbstract)
    {
        String strMethod = "fileDelete(evtPEditorAbstract)";
        
        if (this._intPageId < 1)
        {
            MySystem.s_printOutExit(this, strMethod, "wrong value, this._intPageId=" + this._intPageId);
        }
        
        super.fileDelete(evtPEditorAbstract);
        this._intPageId = -1;
    }
    
    public DEditorNote(
        DEditorAbstractListener dapListenerParent, 
        Component cmpFrameOwner)
    {
        super(dapListenerParent, cmpFrameOwner, DEditorNote._s_strTitleSuffix);
        
        String strMethod = "DEditorNote(dapListenerParent, cmpFrameOwner)";
        
        try
	    {
	        super._pnd_ = new PEditorNote(super._pndListenerThis_, cmpFrameOwner);
	    }
	    
	    catch(Exception exc)
	    {
	        exc.printStackTrace();
	        MySystem.s_printOutExit(this, strMethod, "exc caught");
	    }
    }
    
    // -------
    // PRIVATE
    
    private String _strFileName = null;
    private int _intPageId = -1;
}