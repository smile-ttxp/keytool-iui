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
 
 
 package com.google.code.p.keytooliui.shared.swing.dialog;

/**
    known subclasses:
    . DEditorJarProjTextHtml
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;
import com.google.code.p.keytooliui.shared.util.eventlistener.*;
import com.google.code.p.keytooliui.shared.awt.awtevent.*;
import com.google.code.p.keytooliui.shared.io.*;

import java.awt.*;

public class DEditorJarProjText extends DEditorAbstract 
{    
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _f_s_strBundleFileShort =
        com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
        ".DEditorJarProjText" // class name
        ;
    
    
    
    // --------------
    // PRIVATE STATIC
    
    private static String _s_strTitleSuffix = null;
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        final String f_strWhere = "com.google.code.p.keytooliui.shared.swing.dialog.DEditorJarProjText";
        
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
    
    public String getPathRelativeFile() { return this._strPathRelativeFile; }
    public String getPathAbsoluteJar() { return this._strPathAbsoluteJar; }
    
    // ------
    // PUBLIC
    
    

    
    public boolean doFileNew(String strPathAbsoluteJar, String strPathRelativeFile)
        throws Exception
    {
        String f_strMethod = "doFileNew(strPathAbsoluteJar, strPathRelativeFile)";
        
        if (strPathAbsoluteJar==null || strPathRelativeFile==null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil strPath[x]");
            return false;
        }
        
        this._strPathAbsoluteJar = strPathAbsoluteJar;
        this._strPathRelativeFile = strPathRelativeFile;
        
        return ((PEditorDefaultJar)super._pnd_).doFileNew(this._strPathRelativeFile);
    }
    
    public boolean doFileOpen(
        String strPathAbsoluteJar,
        String strPathRelativeFile,
        byte[] bytsBuffer)
        
        throws Exception
    {
        String f_strMethod = "doFileOpen(strPathAbsoluteJar, strPathRelativeFile, bytsBuffer)";
        
        if (strPathAbsoluteJar==null || strPathRelativeFile==null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil strPath[x]");
            return false;
        }
        
        this._strPathAbsoluteJar = strPathAbsoluteJar;
        this._strPathRelativeFile = strPathRelativeFile;
        
        return ((PEditorDefaultJar)super._pnd_).doFileOpen(bytsBuffer, this._strPathRelativeFile);
    }
    
    public boolean writeTo(FileJar fjr)
        throws Exception
    {
        String f_strMethod = "writeTo(fjr)";
        
        if (this._strPathRelativeFile == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "wrong value, nil this._strPathRelativeFile");
            return false;
        }
        
        return ((PEditorDefaultJar)super._pnd_).writeTo(fjr, this._strPathRelativeFile);
    }
    
    public void fileRecord(PEditorAbstractEvent evtPEditorAbstract)
    {
        String f_strMethod = "fileRecord(evtPEditorAbstract)";
        
        if (this._strPathRelativeFile == null)
        {
            MySystem.s_printOutExit(this, f_strMethod, "wrong value, nil this._strPathRelativeFile");
            
        }
     
        super.fileRecord(evtPEditorAbstract);   
    }
	
	public void fileDelete(PEditorAbstractEvent evtPEditorAbstract)
    {
        String f_strMethod = "fileDelete(evtPEditorAbstract)";
        
        if (this._strPathRelativeFile == null)
        {
            MySystem.s_printOutExit(this, f_strMethod, "wrong value, nil this._strPathRelativeFile");
            
        }
        
        super.fileDelete(evtPEditorAbstract);
        this._strPathRelativeFile = null;
    }
    
    public DEditorJarProjText(DEditorAbstractListener dapListenerParent, Component cmpFrameOwner)
    {
        super(dapListenerParent, cmpFrameOwner, _s_strTitleSuffix);
        
        String strMethod = "DEditorJarProjText(dapListenerParent, cmpFrameOwner)";
        
        try
	    {
	        super._pnd_ = new PEditorDefaultJar(super._pndListenerThis_, cmpFrameOwner);
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
    
    private String _strPathRelativeFile = null;
    private String _strPathAbsoluteJar = null;
}