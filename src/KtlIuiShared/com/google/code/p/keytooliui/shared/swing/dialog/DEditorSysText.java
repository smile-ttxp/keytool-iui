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
 
 
 package com.google.code.p.keytooliui.shared.swing.dialog;


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;
import com.google.code.p.keytooliui.shared.util.eventlistener.*;
import com.google.code.p.keytooliui.shared.awt.awtevent.*;

import java.awt.*;

final public class DEditorSysText extends DEditorAbstract 
{    
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strBundleFileShort =
        com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
        ".DEditorSysText" // class name
        ;
    
    
    
    // --------------
    // STATIC PRIVATE
    
    static private String _s_strTitleSuffix = null;
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        final String f_strWhere = "com.google.code.p.keytooliui.shared.swing.dialog.DEditorSysText";
        
        String strBundleFileLong = _f_s_strBundleFileShort + ".properties";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(_f_s_strBundleFileShort, 
                java.util.Locale.getDefault());
                
	        _s_strTitleSuffix = rbeResources.getString("titleSuffix");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(f_strWhere, strBundleFileLong + " not found, excMissingResource caught");
        }
    }
    
    // -------------
    // PUBLIC ACCESS
    
    public String getPathAbsoluteFile() { return this._strPathAbsolutePath; }
    
    // ------
    // PUBLIC
    
    

    
    public boolean doFileNew(String strPathAbsolutePath)
        throws Exception
    {
        String strMethod = "doFileNew(strPathAbsolutePath)";
        
        if (strPathAbsolutePath == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strPathAbsolutePath");
            return false;
        }
        
        this._strPathAbsolutePath = strPathAbsolutePath;
        
        
        return ((PEditorDefaultSys)super._pnd_).doFileNew(this._strPathAbsolutePath);
    }
    
    public boolean doFileOpen(
        String strPathAbsolutePath,
        java.io.FileInputStream fis)
        
        throws Exception
    {
        String strMethod = "doFileOpen(strPathAbsolutePath, fis)";
        
        if (strPathAbsolutePath == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strPathAbsolutePath");
            return false;
        }
        
        this._strPathAbsolutePath = strPathAbsolutePath;
        
        return ((PEditorDefaultSys)super._pnd_).doFileOpen(fis, this._strPathAbsolutePath);
    }
    
    public boolean writeTo(java.io.FileOutputStream fos)
        throws Exception
    {
        return ((PEditorDefaultSys)super._pnd_).writeTo(fos);
    }
    
    public void fileRecord(PEditorAbstractEvent evtPEditorAbstract)
    {
        String strMethod = "fileRecord(evtPEditorAbstract)";
        
        if (this._strPathAbsolutePath == null)
        {
            MySystem.s_printOutExit(this, strMethod, "wrong value, nil this._strPathAbsolutePath");
        }
     
        super.fileRecord(evtPEditorAbstract);   
    }
	
	public void fileDelete(PEditorAbstractEvent evtPEditorAbstract)
    {
        String strMethod = "fileDelete(evtPEditorAbstract)";
        
        if (this._strPathAbsolutePath == null)
        {
            MySystem.s_printOutExit(this, strMethod, "wrong value, nil this._strPathAbsolutePath");
            
        }
        
        super.fileDelete(evtPEditorAbstract);
        this._strPathAbsolutePath = null;
    }
    
    public DEditorSysText(DEditorAbstractListener dapListenerParent, Component cmpFrameOwner, String strTitleApplication)
    {
        super(dapListenerParent, cmpFrameOwner, strTitleApplication, _s_strTitleSuffix);
        
        String strMethod = "DEditorSysText(dapListenerParent, cmpFrameOwner, strTitleApplication)";
        
        try
	    {
	        super._pnd_ = new PEditorDefaultSys(super._pndListenerThis_, cmpFrameOwner, strTitleApplication);
	    }
	    
	    catch(Exception exc)
	    {
	        exc.printStackTrace();
	        MySystem.s_printOutExit(this, strMethod, "exc caught");
	        
	    }
	    
	    super._pnd_.setPreferredSize(new Dimension(500, 400));
    }
    
    // -------
    // PRIVATE
    
    private String _strPathAbsolutePath = null;
}