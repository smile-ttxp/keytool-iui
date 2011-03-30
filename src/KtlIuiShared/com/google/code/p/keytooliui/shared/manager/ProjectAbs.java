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
    
    . shared: Project2JarAbs
    . shared: ProjectMainAbs
    

**/

import com.google.code.p.keytooliui.shared.lang.*;

abstract public class ProjectAbs
{
    // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.shared.manager.ProjectAbs.";
    
    // -------------
    // STATIC PUBLIC
    
    /**
        if any error, returning nil
    **/
    static public String s_getNameFromAbsolutePath(String strAbsolutePath)
    {
        final String strWhere = _f_s_strClass + "s_getNameFromAbsolutePath(strAbsolutePath)";
        
        if (strAbsolutePath == null)
        {
            MySystem.s_printOutError(strWhere, "nil strAbsolutePath");
            return null;
        }
           
        int i = strAbsolutePath.lastIndexOf(MySystem.s_getFileSeparator());
        
        if (i < 1)
        {
            MySystem.s_printOutError(strWhere, "wrong strAbsolutePath: " + strAbsolutePath);
            return null;
        }
        
        i++;
        
        String strName = null;
        
	    try
	    {
	        strName = strAbsolutePath.substring(i);
		}
		
		catch(IndexOutOfBoundsException excIndexOutOfBounds)
		{
		    excIndexOutOfBounds.printStackTrace();
		    MySystem.s_printOutError(strWhere, "excIndexOutOfBounds caught");
		    return null;
		}
		
		return strName;
    }

    // ---------------
    // ABSTRACT PUBLIC
    
    abstract public boolean init();
    abstract public void destroy();
    
    // ----------------
    // PUBLIC ACCESSORS
    
    public String getName() { return this._strFileName_; }
    public String getAbsolutePath() { return this._strFileAbsolutePath_; }
 
    // ------
    // PUBLIC
    
    public boolean containsResource(String strPathRelative)
    {
        String strMethod = "containsResource(strPathRelative)";
        
        if (strPathRelative == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil strPathRelative");
            
        }
        
        if (this._strFileAbsolutePath_ == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil this._strFileAbsolutePath_");
            
        }
        
        return com.google.code.p.keytooliui.shared.io.FileJar.s_contains(this._strFileAbsolutePath_, strPathRelative);
    }
    
    
    // ---------
    // PROTECTED
    
    protected String _strFileAbsolutePath_ = null;
    protected String _strFileName_ = null;  
    
    
    protected ProjectAbs(String strFileAbsolutePath, String strFileName)
    {        
        this._strFileAbsolutePath_ = strFileAbsolutePath;
        this._strFileName_ = strFileName;
    }
    
    protected boolean _init_()
    {
        String strMethod = "_init_()";
        
        if (this._strFileAbsolutePath_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._strFileAbsolutePath_");
            return false;
        }
        
        if (this._strFileName_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._strFileName_");
            return false;
        }
        
        /* fixing up bug ID ?
        */
        
        if (! _checkForExceedingLength(this._strFileAbsolutePath_))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        
        return true;
    }
    
    protected void _destroy_() {}
    
    // -------
    // PRIVATE
    
    private boolean _checkForExceedingLength(String strPathAbsolute)
    {
        String strMethod = "_checkForExceedingLength(strPathAbsolute)";
        
        if (! com.google.code.p.keytooliui.shared.io.S_OperatingSystem.s_isWindows())
            return true;
            
        if (strPathAbsolute == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strPathAbsolute");
            return false;
        }
        
        final int f_intLengthMax = 260;
        
        if (strPathAbsolute.length() < f_intLengthMax + 1)
            return true;
            
        MySystem.s_printOutError(this, strMethod, "! (strPathAbsolute.length() < f_intLengthMax + 1), strPathAbsolute.length()=" + strPathAbsolute.length());
        return false;
    }
}