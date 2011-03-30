/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
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
 
 
 package com.google.code.p.keytooliui.shared.manager;

/**
    known subclasses:
    . rcr:          Project2JarNote
    . shared_gen:   Project2JarRcrAbs
**/

import com.google.code.p.keytooliui.shared.lang.*;

import java.io.*;

abstract public class Project2JarAbs extends ProjectAbs
{        
    // ---------
    // PROTECTED
    
    protected java.util.jar.JarOutputStream _jos_ = null;
    
    /**
        eg, windows:
        . strFileInAbsolute = "D:\mystuff\myproj\protected\images\plus.gif"
        . strFileInRelative = "protected\images\plus.gif"
        ==> strFileOutRelative = "res/protected/images/plus.gif"
        
        
        eg2, windows:
        . strFileInAbsolute = "D:\temp\deletedFileOnExit.tpl"
        . strFileInRelative = "public\templates\my_template.tpl"
        ==> strFileOutRelative = "res/public/templates/my_template.tpl"

    **/
    
    protected boolean _addResourceFromFileSys_(String strFileInAbsolute, String strFileInRelative)
	{
	    String strMethod = "_addResourceFromFileSys_(strFileInAbsolute, strFileInRelative)";
	    
	    if (strFileInAbsolute == null)
	    {
	        MySystem.s_printOutError(this, strMethod, "nil strFileInAbsolute");
	        return false;
	    }
	    
	    if (strFileInRelative == null)
	    {
	        MySystem.s_printOutError(this, strMethod, "nil strFileInRelative");
	        return false;
	    }
	    
	    String strFileOutRelative = new String(strFileInRelative); //strCur.substring(2);
        strFileOutRelative = strFileOutRelative.replace('\\', '/');
        strFileOutRelative = com.google.code.p.keytooliui.shared.io.FileLocation.f_strResource + "/" + strFileOutRelative;
	    
	    // ----
        File fleResourceProject = new File(strFileInAbsolute);
            
        if (fleResourceProject == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil fleResourceProject");
            return false;
        }
            
        if (! fleResourceProject.exists())
        {
            MySystem.s_printOutError(this, strMethod, "! fleResourceProject.exists():" + fleResourceProject.toString());
            return false;
        }
        
        
        
        // --    
        // change: nov 6, 2003 
        byte[] bytsResourceProject = com.google.code.p.keytooliui.shared.io.S_FileSys.s_getFileResource(fleResourceProject);
        
        if (bytsResourceProject == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil bytsResourceProject");
            return false;
        }
        
        // ----
            
        java.util.jar.JarEntry jeyResourceProject = new java.util.jar.JarEntry(strFileOutRelative);
        
        if (this._jos_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._jos_");
            return false;
        }
        
        if (! com.google.code.p.keytooliui.shared.util.jar.S_JarOutputStream.s_writeEntry(
            this._jos_, jeyResourceProject, bytsResourceProject))
	    {
	        MySystem.s_printOutError(this, strMethod, "failed");
	        return false;
	    }
		
	    return true;
	}

    protected Project2JarAbs(
        String strFileAbsolutePath,
        String strFileName)
    {
        super(strFileAbsolutePath, strFileName);  
    }
    
    protected boolean _init_()
    {
        String strMethod = "_init_()";
        
        if (! super._init_())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (! _init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
    
        return true;
    }
    
    protected void _destroy_()
    {
        String strMethod = "destroy()";
           
        // -----------------
        // -- destroying all
        
        if (! com.google.code.p.keytooliui.shared.util.jar.S_JarOutputStream.s_close(this._jos_))
        {
            MySystem.s_printOutExit(this, strMethod, "failed");
        }
        
        this._jos_ = null;
        
        super._destroy_();
    }
    
    // -------
    // PRIVATE
    
    /**
        copy the jar file
    **/
    
    private boolean _init()
    {
        String strMethod = "_init()";
        
        this._jos_ = com.google.code.p.keytooliui.shared.util.jar.S_JarOutputStream.s_createWithManifest(
            super._strFileAbsolutePath_, 
            (java.awt.Frame) null, 
            (String) null
            );

        if (this._jos_ == null)
        {
			MySystem.s_printOutError(this, strMethod, "nil this._jos_");
			return false;
		}
        
		/* MEMO
		    december 2, 2001
		    got exception with local JNLP file, while using JavaWebStart and
		    trying to save page annotation!
		        
		    ==> (The filename or extension is too long)
		        
		    MEMO: absolute path length = 333 in the following! while WinNT4.0SP6a does not support more than 260 chars
		    
		    MEMO: file name:
		    CN=John Johnson, OU=Software Dev., O=XYZ Corp., L=La Habra, ST=California, C=US_958147623594.xln
		        
		    Exception:
		    java.io.FileNotFoundException: 
		    C:\Program Files\Java Web Start\.javaws\cache\file\D\P-1\DMC&c\DMDocuments and Settings\
		        DMAdministrator\DMMy Documents\DMprod\DMrcr\DM10\DMbck3\DMtmp_testdeploy\DMtest1\
		        usr\rcr\Administrator\notes\
		        CN=John Johnson, OU=Software Dev., O=XYZ Corp., L=La Habra, ST=California, C=US_958147623594.xln (The filename or extension is too long)
		          
	        at java.io.FileOutputStream.open(Native Method)
	        at java.io.FileOutputStream.<init>(Unknown Source)
	        at java.io.FileOutputStream.<init>(Unknown Source)
	        at com.google.code.p.keytooliui.shared.manager.Project2JarAbs._init(Project2JarAbs.java:249)
		*/
		    
			
        
        return true;
    }
}