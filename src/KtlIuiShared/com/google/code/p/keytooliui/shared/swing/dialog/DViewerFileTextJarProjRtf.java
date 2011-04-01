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

import java.awt.*;
import java.awt.event.*;
import java.net.*;

final public class DViewerFileTextJarProjRtf extends DViewerFileTextJarProjAbs 
{       
    // --------------
    // STATIC PRIVATE
    
    static private String _s_strTitleSuffix = null;
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        final String f_strWhere = "com.google.code.p.keytooliui.shared.swing.dialog.DViewerFileTextJarProjRtf";
        
        final String f_strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".DViewerFileTextJarProjRtf" // class name
            ;
        
        String strBundleFileLong = f_strBundleFileShort + ".properties";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(
                f_strBundleFileShort, 
                java.util.Locale.getDefault());
                
	        _s_strTitleSuffix = rbeResources.getString("titleSuffix");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(f_strWhere, strBundleFileLong + " not found, excMissingResource caught");
        }
    }
      
    // ------
    // PUBLIC
    
    /**
    jarConnection = (JarURLConnection)url.openConnection();
	    System.out.println("Jar connection is " +
jarConnection.getClass().getName() );

	    jarConnection.setDefaultUseCaches(false);
	    jarConnection.setUseCaches(false);

    
    **/

    
    /*
        current bug in jdk1.3/winNt: using setPage(url) in an JEditorPane
        causes a jar not be deleted!!!!!!!!
        ==> workaround: copy the jar file as a temp file (deleteOnExit)
           use the copy for the setPage(url)
           warning cannot just copy the rtf included in the jar as it may contains resources like images!
    */
    
    /*
        WARNING! quite same code as DViewerFileTextJarProjHtml.doFileOpen(...)
    */
    public boolean doFileOpen(
        String strPathAbsoluteJarSys,
        String strPathRelativeFile
        )
        throws Exception
    {
        String strMethod = "doFileOpen(strPathAbsoluteJarSys, strPathRelativeFile)";
        
        
        if (strPathAbsoluteJarSys==null || strPathRelativeFile==null)
        {
            MySystem.s_printOutError(this, strMethod, "nil arg");
            return false;
        }
        
        // ------------
        // fixing up bug ID #2: JEditorPage.setPage(urlJarred) ==> cannot then delete the jar file 
        
        // 1/3 create a temp file
        
        java.io.File fleTemp = com.google.code.p.keytooliui.shared.io.S_FileSys.s_createTemp("vhj", ".xlg");
        
        if (fleTemp == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil fleTemp");
            return false;
        }
        
        // 2/3 copy jar into temp file
        // ---
        
        java.io.File fleFrom = new java.io.File(strPathAbsoluteJarSys);
        
        if (! fleFrom.exists())
        {
            MySystem.s_printOutError(this, strMethod, "! fleFrom.exists(), strPathAbsoluteJarSys=" + strPathAbsoluteJarSys);
            return false;
        }
        
        if (fleFrom.isDirectory())
        {
            MySystem.s_printOutError(this, strMethod, "fleFrom.isDirectory(), strPathAbsoluteJarSys=" + strPathAbsoluteJarSys);
            return false;
        }
        
        if (! fleFrom.canRead())
        {
            MySystem.s_printOutError(this, strMethod, "! fleFrom.canRead(), strPathAbsoluteJarSys=" + strPathAbsoluteJarSys);
            return false;
        }
        
        if (! com.google.code.p.keytooliui.shared.io.S_FileSys.s_copyFile(fleFrom, fleTemp))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ----
        
        // 3/3 generate url from copyTempFile
        
        
        // ------------
        
        String strPathAbsoluteJarJava = com.google.code.p.keytooliui.shared.lang.system.S_SystemShared.s_systemPathToJavaPath(
            fleTemp.getAbsolutePath());
        
        
        /** ORI CODE, uncommented once bug fixed in Java's next release !!!!!!!!
        String strPathAbsoluteJarJava = com.google.code.p.keytooliui.shared.lang.system.S_SystemShared.s_systemPathToJavaPath(strPathAbsoluteJarSys);
        **/
                
        if (strPathAbsoluteJarJava == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strPathAbsoluteJarJava");
            return false;
        }
        
        
        
        URL url = null;
        
        // ----
        final String f_strProtocol = com.google.code.p.keytooliui.shared.io.S_FileExtension.f_s_strJARDocument.toLowerCase();
	    final String f_strHost = "";
	    
	    String strFile = "file:" + strPathAbsoluteJarJava + com.google.code.p.keytooliui.shared.io.FileJar.f_s_strFileSeparatorMain;
        
        // ---
	    
        String strUrl = strFile +  strPathRelativeFile;
        
        try
	    {
	        url = new URL(f_strProtocol, f_strHost, strUrl);
	    }
	    
	    catch(java.net.MalformedURLException excMalformedURL)
	    {
	        excMalformedURL.printStackTrace();
	        MySystem.s_printOutError(this, strMethod, "excMalformedURL caught");
	        return false;
	    }
        
        
        // END -----------------------------
        // ---------------------------------
        
        
        if (! ((PViewerFileTextRtfPreviewJar)super._pnd_).doFileOpen(url))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (super._strTitleApplication_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._strTitleApplication_");
            return false;
        }
        
        setTitle(super._strTitleApplication_ + " - " + url.toString());
        
        // ending
        return true;
    }
        
    
    public DViewerFileTextJarProjRtf(Component cmpFrameOwner, String strTitleApplication)
    {
        super(cmpFrameOwner, strTitleApplication, _s_strTitleSuffix);
        
        String strMethod = "DViewerFileTextJarProjRtf(cmpFrameOwner, strTitleApplication)";
        
        try
	    {
	        super._pnd_ = new PViewerFileTextRtfPreviewJar((ActionListener) this, cmpFrameOwner, strTitleApplication);
	    }
	    
	    catch(Exception exc)
	    {
	        exc.printStackTrace();
	        MySystem.s_printOutExit(this, strMethod, "exc caught"); 
	    }
    }
    
    // -------
    // PRIVATE
    
}