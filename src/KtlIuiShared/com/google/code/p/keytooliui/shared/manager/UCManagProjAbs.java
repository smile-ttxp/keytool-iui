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
 
 
 package com.google.code.p.keytooliui.shared.manager;

/**
    known subclasses:
    . UCManagProjGenAbs
    . UCManagProjRdr
    
    
    contains:
    . project
    . projects history: may be nil
   
**/




import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.io.*;

import java.awt.*;
import java.io.*;

abstract public class UCManagProjAbs extends UserChoice
{
    // ----------------------
    // FINAL STATIC PROTECTED
    
    // used in read & write 
    final static protected String _f_s_strNoProjectPath_ = "no_project_path";
    
    // ------
    // PUBLIC
    
    public boolean write(ObjectOutputStream oos)
    {
        String strMethod = "write(oos)";
        

        
        if (! _writeCurrentProject(oos))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._pha_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pha_");
            return false;
        }
        
        if (! this._pha_.write(oos))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    
    /**
        if any error, returns nil
    **/
    
    public ProjectMainAbs getPreviousProject(int intIndex)
    {
        String strMethod = "getPreviousProject(intIndex)";
        
        
        if (this._pha_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pha_");
            return null;
        }
        
        ProjectMainAbs prj = null;
        
        
        try
        {
            prj = (ProjectMainAbs) this._pha_.get(intIndex);
        }
        
        catch(ClassCastException excClassCast)
        {
            excClassCast.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excClassCast caught");
            return null;
        }
        
        
        if (prj == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil prj");
            return null;
        }
        
        return prj;
    }
    
    
    
    public boolean doCloseProject()
    {
        String strMethod = "doCloseProject()";
        
        
        if (this._prjCurr_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._prjCurr_");
            return false;
        }
        
        // 1) todo: push "project stack", if project saved at least once a time!
        File fleProject = new File(this._prjCurr_.getAbsolutePath());
        
 
        if (fleProject.exists())
        {
            if (this._pha_ == null)
            {
                MySystem.s_printOutError(this, strMethod, "nil this._pha_");
                return false;
            }
                
            if (! this._pha_.add(this._prjCurr_))
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
        }
        
        // 2) reset this._prjCurr_
        // WARNING: don't do "destroy()"
        this._prjCurr_ = null;
        return true;
    }
    
    public void destroyProjectCurrent()
    {
        String strMethod = "destroyProjectCurrent()";
        
        if (this._prjCurr_ == null)
            MySystem.s_printOutExit(this, strMethod, "nil this._prjCurr_");
        
        this._prjCurr_.destroy();
        this._prjCurr_ = null;  
    }
    
    public boolean isSignedProject()
    {
        String strMethod = "isSignedProject()";
        
        if (this._prjCurr_ == null)
            MySystem.s_printOutExit(this, strMethod, "nil this._prjCurr_");
        
        return this._prjCurr_.isSigned();
    }
    
    /**
        if any error, returns nil
    **/
    
    public String getProjectName()
    {
        String strMethod = "getProjectName()";
        
        if (this._prjCurr_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._prjCurr_");
            return null;
        }
            
        return this._prjCurr_.getName();
    }
    
    public boolean isProjectCur()
    {
        return (this._prjCurr_ == null)? false: true;
    }
    
    public boolean isProjectLast()
    {
        return (this._prjLast_ == null)? false: true;
    }
    
    public void destroy()
    {        
        if (this._prjCurr_ != null)
        {
            this._prjCurr_.destroy();
            this._prjCurr_ = null;
        }
        
        if (this._pha_ != null)
        {
            this._pha_.destroy();
            this._pha_ = null;
        }
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (this._strApplicationTitle_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._strApplicationTitle_");
            return false;
        }
        
        if (this._cmpFrameParent_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._cmpFrameParent_");
            return false;
        }
        
        
        if (this._pha_ != null)
        {
            if (! this._pha_.init())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
        }
        
        return true;
    }
    
    // ---------
    // PROTECTED 
    
    protected UCHistProjAbs _pha_ = null;
    protected ProjectMainAbs _prjCurr_ = null;
    protected ProjectMainAbs _prjLast_ = null;
    
    protected String _strApplicationTitle_ = null;
    protected Component _cmpFrameParent_ = null;
    
    
    protected UCManagProjAbs(String strApplicationTitle, Component cmpFrameParent)
    {
        super();
        this._strApplicationTitle_ = strApplicationTitle;
        this._cmpFrameParent_ = cmpFrameParent;
    }
    
    // -------
    // PRIVATE
    
    private boolean _writeCurrentProject(ObjectOutputStream oos)
    {
        String strMethod = "_writeCurrentProject(oos)";
        
        if (oos == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil oos");
            return false;
        }
        
        String strVal = _f_s_strNoProjectPath_;
        
        if (this._prjCurr_ != null)
        {
            strVal = this._prjCurr_.getAbsolutePath();
            
            if (strVal == null)
            {
                MySystem.s_printOutWarning(this, strMethod, "nil strVal, continuing");
                strVal = _f_s_strNoProjectPath_;
            }
        }
          
        try
        {        
            oos.writeObject(strVal);
        }
        
        catch(IOException excIO)
        {
            excIO.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excIO caught");
            return false;
        }
        
        return true;
    }
    
}
