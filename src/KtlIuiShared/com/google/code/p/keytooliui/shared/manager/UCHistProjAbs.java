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
    . rcr: UCHistProjRdr
    . shared_gen: UCHistProjGenAbs
 **/
 
 import com.google.code.p.keytooliui.shared.lang.*;
 import com.google.code.p.keytooliui.shared.io.*;
 
 import java.awt.*;
 import java.util.*;
 import java.io.*;
 
 abstract public class UCHistProjAbs extends UserChoice
 {
    // -------------------
    // FINAL STATIC PUBLIC
        
    //final static public int F_S_INT_filePreviousAbsoluteMax = 2; // TEMPO, used for testing purposes
    final static public int F_S_INT_filePreviousAbsoluteMax = 8;
    
    // -----------------------------
    // FINAL STATIC PROTECTED STRING
    
    final static protected String _f_s_str_NoProjectPath_ = "no_project_path";
    
    
    // ---------------
    // FINAL PROTECTED
    
    final protected Vector<File> _f_vecFileProject_ = new Vector<File>(F_S_INT_filePreviousAbsoluteMax + 2);
    final protected Vector<File> _f_vecFileProjectLast_ = new Vector<File>(F_S_INT_filePreviousAbsoluteMax + 2);
    
    // ---------------
    // ABSTRACT PUBLIC
    
    abstract public ProjectMainAbs get(int intIndex);    
    // ------
    // PUBLIC
    
    public File remove(int intIndex)
    {       
        String strMethod = "remove(intIndex)";
        
        if (this._f_vecFileProject_.isEmpty())
        {
            MySystem.s_printOutError(this, strMethod, "empty this._f_vecFileProject_");
            return null;
        }
        
        File fleRemoved = null;
        
        try
        {
            fleRemoved = (File) this._f_vecFileProject_.remove(intIndex);
        }
        
        catch(java.lang.ArrayIndexOutOfBoundsException excArrayIndexOutOfBounds)
        {
            excArrayIndexOutOfBounds.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excArrayIndexOutOfBounds caught");
            return null;
        }
        
        if (fleRemoved == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil fleRemoved");
            return null;
        }
        
        if (this._updatePathsList_() == -1)
        {
            MySystem.s_printOutError(this, strMethod, "this._updatePathsList_() == -1");
            return null;
        }
            
        return fleRemoved;
    }
    
    // insert at the top of the list
    // assuming prj is ok
    public boolean add(ProjectMainAbs prj)
    {
        String strMethod = "add(prj)";
            
        if (prj == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil prj");
            return false;
        }
        
        String strPathAbsolute = prj.getAbsolutePath();
        
        if (strPathAbsolute == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strPathAbsolute");
            return false;
        }
        
        File fle = new File(strPathAbsolute);
            
        if (! this._removeCopyIfAny_(fle))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._f_vecFileProject_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._f_vecFileProject_");
            return false;
        }
        
        try
        {
            this._f_vecFileProject_.add(0, fle); // warning: prj should derive from Object!!!!!!!!!!!!!! 
        }
        
        catch(ArrayIndexOutOfBoundsException excArrayIndexOutOfBounds)
        {
            excArrayIndexOutOfBounds.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excArrayIndexOutOfBounds caught");
            return false;
        }
        
        if (this._updatePathsList_() == -1)
        {
            MySystem.s_printOutError(this, strMethod, "this._updatePathsList_() == -1");
            return false;
        }
        
        return true;
    }
    
    
    /**
        called by UCManagProjRdr.requestOpenProject
        iterating throw projects vector:
            if a project contains the same string as strPathAbsolute
            then:
                . the project is removed from the list
                . return!
    **/
    public void removeFromPathsList(String strPathAbsolute)
    {
        String strMethod = "removeFromPathsList(strPathAbsolute)";
        
        if (strPathAbsolute == null)
            MySystem.s_printOutExit(this, strMethod, "nil strPathAbsolute");
                     
        for (int i=0; i<this._f_vecFileProject_.size(); i++)
        {
            try
            {
                File fleCur = (File) this._f_vecFileProject_.get(i);
                String strCur = fleCur.getAbsolutePath();
                
                if (strPathAbsolute.compareTo(strCur) == 0)
                {
                    this._f_vecFileProject_.remove(fleCur);
                    
                    if (this._updatePathsList_() == -1)
                        MySystem.s_printOutExit(this, strMethod, "this._updatePathsList_() == -1");
                    
                    return;
                }
            }
        
            catch(java.lang.ArrayIndexOutOfBoundsException excArrayIndexOutOfBounds)
            {
                excArrayIndexOutOfBounds.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, "excArrayIndexOutOfBounds caught");
            }
        }
    }
    
    //called by UCManagProjRdr.assignLast()
    // it returns true or exit!!!!!!!!!!
    public boolean assignLast()
    {            
        String strMethod = "assignLast()";
        
        if (this._f_vecFileProjectLast_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._f_vecFileProjectLast_");
            return false;
        }
        
        if (this._f_vecFileProjectLast_.size() < 1)
        {
            return true;
        }
        
        if (this._f_vecFileProject_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._f_vecFileProject_");
            return false;
        }
        
        if (this._f_vecFileProject_.size() > 0)
        {
            MySystem.s_printOutError(this, strMethod, "this._f_vecFileProject_.size() > 0, this._f_vecFileProject_.size()=" + this._f_vecFileProject_.size());
            return false;
        }

	    // ---
	    for (int i=0; i<this._f_vecFileProjectLast_.size(); i++)
	    {
	        try
            {
                File fleCur = (File) this._f_vecFileProjectLast_.get(i);
                this._f_vecFileProject_.add(fleCur);
            }
        
            catch(java.lang.ArrayIndexOutOfBoundsException excArrayIndexOutOfBounds)
            {
                excArrayIndexOutOfBounds.printStackTrace();
                MySystem.s_printOutError(this, strMethod, "excArrayIndexOutOfBounds caught");
                return false;
            }
	    }
	    
	    if (this._updatePathsList_() == -1)
        {
            MySystem.s_printOutError(this, strMethod, "this._updatePathsList_() == -1");
            return false;
        }
	    
	    // ---
        
        return true;
    }
    
    public boolean read(ObjectInputStream ois)
    {
        String strMethod = "read(ois)";
        
        int i = 0;
        
        for (; i<F_S_INT_filePreviousAbsoluteMax; i++)
        {
            String strVal = null;
        
            try
            {  
                strVal = (String) ois.readObject();
            }
        
            catch(ClassNotFoundException excClassNotFound)
            {
                excClassNotFound.printStackTrace();
                MySystem.s_printOutError(this, strMethod, "excClassNotFound caught");
                return false;
            }
            
            catch(ClassCastException excClassCast)
            {
                excClassCast.printStackTrace();
                MySystem.s_printOutError(this, strMethod, "excClassCast caught");
                return false;
            }
        
            catch(IOException excIO)
            {
                excIO.printStackTrace();
                MySystem.s_printOutError(this, strMethod, "excIO caught");
                return false;
            }
        
            if (strVal == null)
            {
                MySystem.s_printOutError(this, strMethod, "nil strVal");
                return false;
            }
        
            if (strVal.compareTo(_f_s_str_NoProjectPath_) == 0)
            {
                i++;
                break;
            }
            
            // ----
            
            File fle = new File(strVal);

            try
            {
                if(! fle.exists())
		        {
		            MySystem.s_printOutWarning(this, strMethod, "! fle.exists(), strVal=" + strVal + ", ignoring");
		            continue;
		        }
		        
		        if(fle.isDirectory())
		        {
		            MySystem.s_printOutWarning(this, strMethod, "fle.isDirectory(), strVal=" + strVal + ", ignoring");
		            continue;
		        }
		        
		        if(! fle.canRead())
		        {
		            MySystem.s_printOutWarning(this, strMethod, "! fle.canRead(), strVal=" + strVal + ", ignoring");
		            continue;
		        }
		    }
		    
		    catch(java.lang.SecurityException excSecurity)
		    {
		        excSecurity.printStackTrace();
		        MySystem.s_printOutWarning(this, strMethod, "excSecurity caught, ignoring");
		        continue;
		    }
		    
            
            this._f_vecFileProjectLast_.add(fle);
    
            // ----
            
            
        } // end of for
        
        // ----
        /*
            2nd:
            in order to get the same number of "readObject" and "writeObject"
            should only contain "_f_s_str_NoProjectPath_"
        */
        for (; i<F_S_INT_filePreviousAbsoluteMax; i++)
        {
            // do job
            String strVal = null;
        
            try
            {  
                strVal = (String) ois.readObject();
            }
        
            catch(ClassNotFoundException excClassNotFound)
            {
                excClassNotFound.printStackTrace();
                MySystem.s_printOutError(this, strMethod, "excClassNotFound caught");
                return false;
            }
            
            catch(ClassCastException excClassCast)
            {
                excClassCast.printStackTrace();
                MySystem.s_printOutError(this, strMethod, "excClassCast caught");
                return false;
            }
        
            catch(IOException excIO)
            {
                excIO.printStackTrace();
                MySystem.s_printOutError(this, strMethod, "excIO caught");
                return false;
            }
        
            if (strVal == null)
            {
                MySystem.s_printOutError(this, strMethod, "nil strVal");
                return false;
            }
        
            if (strVal.compareTo(_f_s_str_NoProjectPath_) == 0) // ok!
            {
                continue;
            }
            
            // wrong!
            MySystem.s_printOutError(this, strMethod, "got unexpected value, strVal=" + strVal);
            return false;
            
        }
        
        
        if (this._updatePathsListLast_() == -1)
        {
            MySystem.s_printOutError(this, strMethod, "this._updatePathsList_() == -1");
            return false;
        }
        
        return true;
    }
    
    public boolean write(ObjectOutputStream oos)
    {            
        String strMethod = "write(oos)";
        
        //_dump();
        
        int i=0;
        
        for (; i<this._strsFilePreviousAbsolutePath.length; i++)
        {
            String strVal = this._strsFilePreviousAbsolutePath[i];
            
            if (strVal == null)
            {
                break;
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
        }
         
        for (; i<F_S_INT_filePreviousAbsoluteMax; i++)
        {
            try
            {        
                oos.writeObject(_f_s_str_NoProjectPath_);
            }
        
            catch(IOException excIO)
            {
                excIO.printStackTrace();
                MySystem.s_printOutError(this, strMethod, "excIO caught");
                return false;
            }
        }
        
        return true;
    }
    
    
    public int getIndex(ProjectMainAbs prj)
    {
        String f_strMethod = "getIndex(prj)";
        
        if (prj == null)
            MySystem.s_printOutExit(this, f_strMethod, "nil prj");
        
        String strPathAbsolute = prj.getAbsolutePath();
        
        if (strPathAbsolute == null)
            MySystem.s_printOutExit(this, f_strMethod, "nil strPathAbsolute");
        
        
        final int intWrong = -1;
        
        
        for (int i=0; i<this._f_vecFileProject_.size(); i++)
        {
            File fleCur = null;
            
            try
            {
                 fleCur = (File) this._f_vecFileProject_.get(i);
            }
            
            catch(java.lang.ArrayIndexOutOfBoundsException excArrayIndexOutOfBounds)
            {
                excArrayIndexOutOfBounds.printStackTrace();
                MySystem.s_printOutError(this, f_strMethod, "excArrayIndexOutOfBounds caught");
                return intWrong;
            }
            
            if (fleCur == null)
                return intWrong;
                
            if (fleCur.getAbsolutePath().compareTo(strPathAbsolute) == 0)
                return i;
        }
        
        return intWrong;
    }
    
    public void destroy() {}
    
    public boolean init()
    {
        String strMethod = "init()";
        
        //_dump();
        
        try
        {
            // cleaning up
            for (int i=0; i<F_S_INT_filePreviousAbsoluteMax; i++)
            {
                this._strsFilePreviousAbsolutePath[i] = null;
                this._strsFilePreviousAbsolutePathLast[i] = null; //added nov 19, 2001
            }
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "exc caught");
            return false;
        }
        
        return true;
    }
    
    // ---------
    // PROTECTED
    
    protected String _strApplicationTitle_ = null;
    protected Component _cmpFrameParent_ = null;
    
    protected boolean _removeCopyIfAny_(File fle)
    {          
        String strMethod = "_removeCopyIfAny_(fle)";
        
        if (fle == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil fle");
            return false;
        }
        
        String strPath = fle.getAbsolutePath();
        
        if (strPath == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strPath");
            return false;
        }
        
        
        Stack<File> stkProjectToRemove = new Stack<File>();
        
        for (int i=0; i<this._f_vecFileProject_.size(); i++)
        {
            File fleCur = null;
            
            try
            {
                fleCur = (File) this._f_vecFileProject_.get(i);
            }
            
            catch(java.lang.ArrayIndexOutOfBoundsException excArrayIndexOutOfBounds)
            {
                excArrayIndexOutOfBounds.printStackTrace();
                MySystem.s_printOutError(this, strMethod, "excArrayIndexOutOfBounds caught");
                return false;
            }
            
            if (fleCur == null)
            {
                MySystem.s_printOutError(this, strMethod, "nil fleCur");
                return false;
            }
            
            String strPathCur = fleCur.getAbsolutePath();
            
            if (strPathCur == null)
            {
                MySystem.s_printOutError(this, strMethod, "nil strPathCur");
                return false;
            }
            
            if (strPath.compareTo(strPathCur) == 0)
            {
                stkProjectToRemove.push(fleCur);
            }
        }
        
            
        while (true)
        {
		    if (stkProjectToRemove.empty())
			    return true;
			    
			File fleCur = null;
            
            try // NOT REALLY NEEDED !!!!!!!!!!!
            {
			    fleCur = (File) stkProjectToRemove.pop();
			    
			    if (fleCur == null)
			    {
			        MySystem.s_printOutError(this, strMethod, "nil fleCur");
	                return false;
			    }
    	        
	            if (! this._f_vecFileProject_.remove(fleCur))
	            {
	                MySystem.s_printOutError(this, strMethod, "failed to remove element, fleCur.getAbsolutePath()=" + fleCur.getAbsolutePath());
	                return false;
	            }
	        }
	        
	        catch(EmptyStackException excEmptyStack)
	        {
	            excEmptyStack.printStackTrace();
	            MySystem.s_printOutError(this, strMethod, "excEmptyStack caught, fleCur.getAbsolutePath()=" + fleCur.getAbsolutePath());
	            return false;
	        }        
        }
    }
    
    // !!!!!!!! quite same code as _updatePathsList_ !!!!!
    // called by this.read()
    protected int _updatePathsListLast_()
    {            
        String strMethod = "_updatePathsListLast_()";
        
        
        
        // 1) clean-up
        //for (int i=0; i<F_S_INT_filePreviousAbsoluteMax; i++)
        for (int i=0; i<this._strsFilePreviousAbsolutePathLast.length; i++)
        {
            this._strsFilePreviousAbsolutePathLast[i] = null;
        }
        
        // 2) get cur paths nb
        
        int intFilePreviousAbsoluteLastNb = F_S_INT_filePreviousAbsoluteMax;
        
        if (this._f_vecFileProjectLast_.size() < intFilePreviousAbsoluteLastNb)
            intFilePreviousAbsoluteLastNb = this._f_vecFileProjectLast_.size();
        
        
        // 3) fill-up
        for (int i=0; i<intFilePreviousAbsoluteLastNb; i++)
        {
            try
            {
                File fleCur = (File) this._f_vecFileProjectLast_.get(i);
                
                if (fleCur == null)
                {
                    MySystem.s_printOutError(this, strMethod, "nil fleCur");
                    return -1;
                }
                
                String strCur = fleCur.getAbsolutePath();
                
                if (strCur == null)
                {
                    MySystem.s_printOutError(this, strMethod, "nil strCur");
                    return -1;
                }
                
                
                this._strsFilePreviousAbsolutePathLast[i] = new String(strCur);
            }
        
            catch(java.lang.ArrayIndexOutOfBoundsException excArrayIndexOutOfBounds)
            {
                excArrayIndexOutOfBounds.printStackTrace();
                MySystem.s_printOutError(this, strMethod, "excArrayIndexOutOfBounds caught");
                return -1;
            }
        }
        
        // ending
        return intFilePreviousAbsoluteLastNb;
    }
    
    /*called by add or remove or assignLast
        if any error, returns -1
    */
    protected int _updatePathsList_()
    {            
        String strMethod = "_updatePathsList_()";
        
        //_dump();
       
        
        // 1) clean-up
        for (int i=0; i<this._strsFilePreviousAbsolutePath.length; i++)
        {
            this._strsFilePreviousAbsolutePath[i] = null;
        }
        
        // 2) get cur paths nb
        
        int intFilePreviousAbsoluteNb = F_S_INT_filePreviousAbsoluteMax;
        
        if (this._f_vecFileProject_.size() < intFilePreviousAbsoluteNb)
            intFilePreviousAbsoluteNb = this._f_vecFileProject_.size();
        
        
        // 3) fill-up
        for (int i=0; i<intFilePreviousAbsoluteNb; i++)
        {
            try
            {
                File fleCur = (File) this._f_vecFileProject_.get(i);
                
                if (fleCur == null)
                {
                    MySystem.s_printOutError(this, strMethod, "nil fleCur");
                    return -1;
                }
                
                String strCur = fleCur.getAbsolutePath();
                
                if (strCur == null)
                {
                    MySystem.s_printOutError(this, strMethod, "nil strCur");
                    return -1;
                }
                
                
                this._strsFilePreviousAbsolutePath[i] = new String(strCur);
            }
        
            catch(java.lang.ArrayIndexOutOfBoundsException excArrayIndexOutOfBounds)
            {
                excArrayIndexOutOfBounds.printStackTrace();
                MySystem.s_printOutError(this, strMethod, "excArrayIndexOutOfBounds caught");
                return -1;
            }
        }
        
        // ending
        return intFilePreviousAbsoluteNb;
    }
    
    protected UCHistProjAbs(
        String strApplicationTitle, 
        Component cmpFrameParent,
        String[] strsFilePreviousAbsolutePath,
        String[] strsFilePreviousAbsolutePathLast)
    {
        this._strApplicationTitle_ = strApplicationTitle;
        this._cmpFrameParent_ = cmpFrameParent;
        this._strsFilePreviousAbsolutePath = strsFilePreviousAbsolutePath;
        this._strsFilePreviousAbsolutePathLast = strsFilePreviousAbsolutePathLast;
    }
    
    // -------
    // PRIVATE
    
    private String[] _strsFilePreviousAbsolutePath;
    private String[] _strsFilePreviousAbsolutePathLast;
    
    // tempo
    /*private void _dump()
    {
        if (true) return;
        System.out.println("_strsFilePreviousAbsolutePath.length=" + _strsFilePreviousAbsolutePath.length);
        System.out.println("_strsFilePreviousAbsolutePathLast.length=" + _strsFilePreviousAbsolutePathLast.length);
        System.out.println("_f_vecFileProject_.size()=" + _f_vecFileProject_.size());
        System.out.println("_f_vecFileProjectLast_.size()=" + _f_vecFileProjectLast_.size());
        
    }*/
 }