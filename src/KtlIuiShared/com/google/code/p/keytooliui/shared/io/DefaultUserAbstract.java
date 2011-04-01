/*
 *
 * Copyright (c) 2001-2007 RagingCat Project.
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
 
 
 package com.google.code.p.keytooliui.shared.io;

/**
    known subclassed:
    . UserAnnotation (rcr)
    . LastUserAbstract (shared)
**/



import com.google.code.p.keytooliui.shared.lang.*;


import java.io.*;

abstract public class DefaultUserAbstract
{
    // --------------------------
    // FINAL STATIC PRIVATE STRING
    
    // ---------------
    // ABSTRACT PUBLIC
    
    
    // ---------
    // PROTECTED
    
    protected String _strPathAbsParentAppli_ = null;
    
    /**
        eg: strApplicationNameShort: RCReader ==> rcr, RCReader Generator ==> xlb
    **/
    protected DefaultUserAbstract(
        // application
        String strPathAbsHomeAppli,
        String strApplicationNameShort,
        String strVersionAppli)
    {
        this._strPathAbsParentAppli_ = strPathAbsHomeAppli;
        this._strApplicationNameShort = strApplicationNameShort;
        this._strVersionAppli = strVersionAppli;
    }
    
    protected boolean _init_()
    {
        String strMethod = "_init_()";
        
        if (this._strPathAbsParentAppli_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._strPathAbsParentAppli_");
            return false;
        }
        
        if (this._strApplicationNameShort == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._strApplicationNameShort");
            return false;
        }
        
        return true;
    }
    
    protected void _destroy_()
    {
    }
    
    // EG: linux, KTL: ~/.rcp/usr/[ktl-version]/[user.name]
    
    protected File _getFileDirUser_()
    {
        String strMethod = "_getFileDirUser_()";
        

        
        File fleDirUsers = _getFileDirUsers();
        
        if (fleDirUsers == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil fleDirUsers");
            return null;
        }
        
        if (! fleDirUsers.canWrite())
        {
            MySystem.s_printOutError(this, strMethod, "cannot write in " + fleDirUsers.toString());
            return null;
        }
        
        // ----
        
        String strUserName = null;
        
        try
        {
            strUserName = System.getProperty("user.name");
        }
        
        catch(SecurityException exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "exc caught");
            return null;
        }
        
        if (strUserName == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strUserName");
            return null;
        }
        
        if (strUserName.trim().length() < 1)
        {
            MySystem.s_printOutError(this, strMethod, "wrong strUserName: " + strUserName);
            return null;
        }
        
        File fleDirUser = new File(fleDirUsers, strUserName.trim());
        
        if (fleDirUser == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil fleDirUser");
            return null;
        }
        
        if (! fleDirUser.exists())
        {
            try
            {
                boolean blnOk = fleDirUser.mkdir();
                
                if (! blnOk)
                {
                    MySystem.s_printOutError(this, strMethod, "failed to make dir");
                    return null;
                }
            }
            
            catch(Exception exc)
            {
                exc.printStackTrace();
                MySystem.s_printOutError(this, strMethod, "exc caught");
                return null;
            }
        }
        
        else
        {
            if (! fleDirUser.isDirectory())
            {
                MySystem.s_printOutError(this, strMethod, "not a directory: " + fleDirUser.toString());
                return null;
            }
        }
        
        if (! fleDirUser.canWrite())
        {
            MySystem.s_printOutError(this, strMethod, "cannot write in " + fleDirUser.toString());
            return null;
        }
        
        return fleDirUser;
    }
    
    
    
    /** ####
        PRIVATE
    **/
    
    
    private String _strApplicationNameShort = null;
    private String _strVersionAppli = null;
    
    /**
        EG linux:, RCReader
        should return the directory name ~/.rcp/usr/[ktl-version]
     *  ie                               ~/.rcp/usr/ktl/20   (for version #2.0)
    **/
    private File _getFileDirUsers()
    {
        String strMethod = "_getFileDirUsers()";
        
        // -----------------------
        // 1) get ~/.rcp/usr
        
        String strPathUsr = this._strPathAbsParentAppli_ + File.separator + FileLocation.f_strUser;
        File fleDirUsr = new File(strPathUsr);
        
        if (fleDirUsr == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil fleDirUsr:" + strPathUsr);
            return null;
        }
        
        if (! fleDirUsr.exists())
        {
            try
            {
                boolean blnOk = fleDirUsr.mkdir();
                
                if (! blnOk)
                {
                    MySystem.s_printOutError(this, strMethod, "failed to make dir: " + strPathUsr);
                    return null;
                }
            }
            
            catch(Exception exc)
            {
                exc.printStackTrace();
                MySystem.s_printOutError(this, strMethod, "exc caught");
                return null;
            }
        }
        
        else
        {
            if (! fleDirUsr.isDirectory())
            {
                MySystem.s_printOutError(this, strMethod, "not a directory: " + fleDirUsr.getAbsolutePath());
                return null;
            }
        }
        
        // -----------------------
        // 2) get ~/.rcp/usr/[appli.shortName]
        // ie     ~/.rcp/usr/ktl
        
        
        String strPathUsrAppli = fleDirUsr.getAbsolutePath() + File.separator + this._strApplicationNameShort;
        File fleDirUsrAppli = new File(strPathUsrAppli);
        
        if (fleDirUsrAppli == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil fleDirUsrAppli:" + strPathUsrAppli);
            return null;
        }
        
        if (! fleDirUsrAppli.exists())
        {
            try
            {
                boolean blnOk = fleDirUsrAppli.mkdir();
                
                if (! blnOk)
                {
                    MySystem.s_printOutError(this, strMethod, "failed to make dir: " + strPathUsrAppli);
                    return null;
                }
            }
            
            catch(Exception exc)
            {
                exc.printStackTrace();
                MySystem.s_printOutError(this, strMethod, "exc caught");
                return null;
            }
        }
        
        else
        {
            if (! fleDirUsrAppli.isDirectory())
            {
                MySystem.s_printOutError(this, strMethod, "not a directory: " + fleDirUsrAppli.getAbsolutePath());
                return null;
            }
        }
        
        // -----------------------
        // 3) get ~/.rcp/usr/[appli.shortName]/[appli.version]
        //    ie. appli's version: "2.0"
        //                     ==> "20"
        //                     ==> ~/.rcp/usr/ktl/20
        
        
        String strNameVersionAppli = this._strVersionAppli;
        int intIndexOfDot = this._strVersionAppli.indexOf(".");
        
        if (intIndexOfDot != -1)
        {
            String strBeg = this._strVersionAppli.substring(0, intIndexOfDot);
            String strEnd =  this._strVersionAppli.substring(intIndexOfDot+1, this._strVersionAppli.length());
            strNameVersionAppli = new String(strBeg + strEnd);
        }
        
        
        
        File fleDirVersionAppli = new File(fleDirUsrAppli, strNameVersionAppli);
        
        if (fleDirVersionAppli == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil fleDirVersionAppli:" + fleDirVersionAppli);
            return null;
        }
        
        if (! fleDirVersionAppli.exists())
        {
            try
            {
                boolean blnOk = fleDirVersionAppli.mkdir();
                
                if (! blnOk)
                {
                    MySystem.s_printOutError(this, strMethod, "failed to make dir: " + fleDirVersionAppli.getAbsolutePath());
                    return null;
                }
            }
            
            catch(Exception exc)
            {
                exc.printStackTrace();
                MySystem.s_printOutError(this, strMethod, "exc caught");
                return null;
            }
        }
        
        else
        {
            if (! fleDirVersionAppli.isDirectory())
            {
                MySystem.s_printOutError(this, strMethod, "not a directory: " + fleDirVersionAppli.getAbsolutePath());
                return null;
            }
        }
        
        // ---------
        // 4) ending
        return fleDirVersionAppli;       
    }
    
}