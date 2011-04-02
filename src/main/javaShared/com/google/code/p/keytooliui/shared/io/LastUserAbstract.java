/*
 *
 * Copyright (c) 2001-2008 RagingCat Project.
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

    known subclasses:
    . LastUserPref,
    . LastUserProj
    
    
    IMPORTANT:
    done once a time in a session, the following methods:
    . _setSessionLog
    . _writeConfigLog
    

    MEMO: _strFileNameBak is not in use right now!
**/

import com.google.code.p.keytooliui.shared.lang.*;

import java.io.*;
import java.util.Arrays;

abstract public class LastUserAbstract extends DefaultUserAbstract
{
    // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    //final static private String _f_s_strClass = "com.google.code.p.keytooliui.shared.io.LastUserAbstract.";
    
    final static private String _f_s_strFileConfigLog = "config.prop";
    final static private String _f_s_strFileSessionLog = "session.log";
    
    final static private String _f_s_strFileConfigBak = "config.bak.prop";
    final static private String _f_s_strFileSessionBak = "session.bak.log";
    
    
    // --------------
    // STATIC PRIVATE
    
    static private boolean _S_BLN_SETSESSIONLOG = true; // default
    
    
    // ----------------------
    // STATIC PRIVATE BOOLEAN
    
    /*
        done once a time during the session, the first appli (eg: xlb from xlb v/s rcr) has priority
        in order to get all messages in synchro
    */
    static private boolean _s_blnConfigLogDone = false;
    static private boolean _s_blnSessionLogDone = false;
    
    // ------
    // PUBLIC
    
    
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super._init_())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._strFileNameIni == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._strFileNameIni");
            return false;
        }
        
        if (this._strFileNameBak == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._strFileNameBak");
            return false;
        }
        
        if (! _writeConfigLog())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        /*if (! AppAbs.s_isDeployedWithJws())
        {*/
            if (LastUserAbstract._S_BLN_SETSESSIONLOG)
            {
                if (! _setSessionLog())
                {
                    MySystem.s_printOutError(this, strMethod, "failed");
                    return false;
                }
            }
        //}
        
        if (this._vecUserChoice == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._vecUserChoice");
            return false;
        }
        
        for (int i=0; i<this._vecUserChoice.size(); i++)
        {
            Object obj = this._vecUserChoice.elementAt(i);
            
            if (obj == null)
            {
                MySystem.s_printOutError(this, strMethod, "nil obj");
                return false;
            }
            
            if (! (obj instanceof UserChoice))
            {
                MySystem.s_printOutError(this, strMethod, "wrong instance");
                return false;
            }     
        }
	    
	    // ----
        
        if (! _read())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    public void destroy()
    {
        String strMethod = "destroy()";
        
        super._destroy_();
        
        if (! _write())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
        }
    }
    
    public boolean assign()
    {
        String strMethod = "assign()";
        
        
        for (int i=0; i<this._vecUserChoice.size(); i++)
	    {
	        UserChoice uce = (UserChoice) this._vecUserChoice.elementAt(i);
	        
	        if (! uce.assignLast())
	        {
	            MySystem.s_printOutError(this, strMethod, "failed");
	            return false;
	        }  
	    }
        
        // ----
        
        return true;
    }
    
    // ---------
    // PROTECTED
       
    
    protected LastUserAbstract(
        // application
        String strPathAbsHomeAppli, 
        //String strApplicationNameShort,
        String strVersionAppli,
        String strFileNameIni,
        String strFileNameBak,
        java.util.Vector<UserChoice> vecUserChoice)
    {
        super(strPathAbsHomeAppli, strVersionAppli);
        this._strFileNameIni = strFileNameIni;
        this._strFileNameBak = strFileNameBak;
        this._vecUserChoice = vecUserChoice;
    }
    
    
    
    
    
    /** ####
        PRIVATE
    **/
    
    private String _strFileNameIni = null;
    private String _strFileNameBak = null;
    private java.util.Vector<UserChoice> _vecUserChoice = null;
    
    /**
        if userDir: should get it, else exit there
        if config file: may return nil
    **/
    
    private FileOutputStream _getFileOutputStream()
    {
        String strMethod = "_getFileOutputStream()";
        File fleDirUser = super._getFileDirUser_();
        
        if (fleDirUser == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil fleDirUser");
            return null;
        }
        
        // ----
        
        File fle = new File(fleDirUser, this._strFileNameIni);
        
        if (fle == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil fle");
            return null;
        }

        FileOutputStream fos = null;
          
        try
		{
			fos = new FileOutputStream(fle);
		}

		catch(IOException excIO)
		{
		    excIO.printStackTrace();
			MySystem.s_printOutError(this, strMethod, "excIO caught, fle.getAbsolutePath()=" + fle.getAbsolutePath());
			return null;
		}
		
		return fos;
    }
    
    private boolean _close(ObjectOutputStream oos, FileOutputStream fos)
    {
        String strMethod = "_close(oos, fos)";
        
        if (oos==null || fos==null)
        {
            MySystem.s_printOutError(this, strMethod, "nil arg");
			return false;
        }
        
        try
		{
			oos.flush();
			oos.close();
			//fos.close();
		}

		catch(IOException excIO)
		{
		    excIO.printStackTrace();
			MySystem.s_printOutError(this, strMethod, "excIO caught");
			return false;
		}
			
		return true;
    }
    
    /*
        if any error, exiting
    */
    private FileInputStream _getFileInputStream(String strFileName)
    {
        String strMethod = "_getFileInputStream(strFileName)";
        
        if (strFileName == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil strFileName");
        }
        
        File fleDirUser = super._getFileDirUser_();
        
        if (fleDirUser == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil fleDirUser");
        }
        
        
        File fle = new File(fleDirUser, strFileName);
        
        if (fle == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil fle");
        }
        
        if (! fle.exists())
        {
            MySystem.s_printOutTrace(this, strMethod, "! fle.exists(), fle.getAbsolutePath()=" + fle.getAbsolutePath());
            return null;
        }
        

        
        // _fleFolderAbsolute
        FileInputStream fis = null;
        
        try
		{
			//fis = new FileInputStream(strFilePath);
			fis = new FileInputStream(fle);
		}

		catch(IOException excIO)
		{
		    excIO.printStackTrace();
			MySystem.s_printOutError(this, strMethod, "excIO caught, fle.getAbsolutePath()=" + fle.getAbsolutePath());
			return null;
		}
		
		return fis;
    }
    
    
    
    private ObjectInputStream _getObjectInputStream(FileInputStream fis)
    {
        String strMethod = "_getObjectInputStream(fis)";
        
        if (fis == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil fis");
			return null;
        }
        
        ObjectInputStream ois = null;
        
        try
        {
		    ois = new ObjectInputStream(fis);
		}
		
		catch(IOException excIO)
		{
		    excIO.printStackTrace();
			MySystem.s_printOutError(this, strMethod, "excIO caught");
			return null;
		}
        
        return ois;
    }
    
    private ObjectOutputStream _getObjectOutputStream(FileOutputStream fos)
    {
        String strMethod = "_getObjectOutputStream(fos)";
        
        if (fos == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil fos");
			return null;
        }
        
        ObjectOutputStream oos = null;
        
        try
        {
		    oos = new ObjectOutputStream(fos);
		}
		
		catch(IOException excIO)
		{
			excIO.printStackTrace();
			MySystem.s_printOutError(this, strMethod, "excIO caught");
			return null;
		}
        
        return oos;
    }
    
    private boolean _close(ObjectInputStream ois)
    {
        String strMethod = "_close(ois)";
        
        if (ois == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil ois");
			return false;
        }
        
        try
		{
			ois.close();
		}

		catch(IOException excIO)
		{
		    excIO.printStackTrace();
			MySystem.s_printOutError(this, strMethod, "excIO caught");
			return false;
		}
			
		return true;
    }
    
    private boolean _read()
    {
        String strMethod = "_read()";
        
        
        // ----
        // 1) open stream
        FileInputStream fis = _getFileInputStream(this._strFileNameIni);
        
        if (fis == null)
        {
            //fis = _getFileInputStream(this._strFileNameBak);
            
            //if (fis == null)
            //{
                MySystem.s_printOutTrace(this, strMethod, "nil fis, returning");
	            return true;
            //}
        }
	    
	    ObjectInputStream ois = _getObjectInputStream(fis);

	    if (ois == null)
	    {
	        MySystem.s_printOutError(this, strMethod, "nil ois");
	        return false;
	    }
	    
	    for (int i=0; i<this._vecUserChoice.size(); i++)
	    {
	        UserChoice uce = (UserChoice) this._vecUserChoice.elementAt(i);
	        
	        if (! uce.read(ois))
	        {
	            MySystem.s_printOutError(this, strMethod, "failed");
	            return false;
	        }  
	    }
	    
	    
	    
	    
	    // ----
	    // 3) close stream
	    if (! _close(ois))
	    {
	        MySystem.s_printOutError(this, strMethod, "failed");
	        return false;
	    }
	      
        ois = null;
        fis = null;
        return true;
    }
    
    private boolean _write()
    {
        String strMethod = "_write()";
        
        // ----
        // 1) open stream
        
        FileOutputStream fos = _getFileOutputStream();
        
        if (fos == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil fos");
	        return false;
        }
	    
	    ObjectOutputStream oos = _getObjectOutputStream(fos);

	    if (oos == null)
	    {
	        MySystem.s_printOutError(this, strMethod, "nil oos");
	        return false;
	    }
        
        for (int i=0; i<this._vecUserChoice.size(); i++)
	    {
	        UserChoice uce = (UserChoice) this._vecUserChoice.elementAt(i);
	        
	        if (! uce.write(oos))
	        {
	            MySystem.s_printOutError(this, strMethod, "failed");
	            return false;
	        }  
	    }
        
        
        
        // ----
        // 3) close stream
        if (! _close(oos, fos))
	    {
	        MySystem.s_printOutError(this, strMethod, "aborting");
	        return false;
	    }
	      
        oos = null;
        fos = null;
        return true;
    }
    
    /**
        note: jdk1.3.0 bug: always getting file.exists(), before process goes to System.[setOut-setErr]
    **/
    
    private boolean _setSessionLog()
    {
        String strMethod = "_setSessionLog()";
        
        //MySystem.s_printOutFlagDev(this, strMethod, "IN COMMENTS");
        //if (true) return true;
        
        if (LastUserAbstract._s_blnSessionLogDone == true)
            return true;
        
        LastUserAbstract._s_blnSessionLogDone = true;
        
        File fleParent = super._getFileDirUser_(); // ie ~/.rcp/usr/ktl/20/john-doe
        
        if (fleParent == null)
        {
            //System.out.println(strMethod + ": nil fleParent");
            MySystem.s_printOutError(this, strMethod, "nil fleParent");
            return false;
        }
        
        //System.out.println(strMethod + ": fleParent.getAbsolutePath()=" + fleParent.getAbsolutePath());
        
        boolean blnAppend = true;
        
        File fleSessionLog = new File(fleParent, LastUserAbstract._f_s_strFileSessionLog);
        
        if (fleSessionLog == null)
        {
            //System.out.println(strMethod + ": nil fleSessionLog");
            MySystem.s_printOutError(this, strMethod, "nil fleSessionLog");
            return false;
        }
        
        //System.out.println(strMethod + ": fleSessionLog.getAbsolutePath()=" + fleSessionLog.getAbsolutePath());
        
        if (fleSessionLog.exists())
        {
            //MySystem.s_printOutTrace(this, strMethod, "fleSessionLog.exists()");
         
            File fleSessionBak = new File(fleParent, LastUserAbstract._f_s_strFileSessionBak);
        
            if (fleSessionBak.exists())
            {
                //MySystem.s_printOutWarning(this, strMethod, "fleSessionBak.exists()");
                //System.out.println("fleSessionBak.exists(), fleSessionBak.getAbsolutePath()=" + fleSessionBak.getAbsolutePath());
                
                if (! com.google.code.p.keytooliui.shared.io.S_FileSys.s_deleteFile(fleSessionBak.getAbsolutePath()))
                {
                    MySystem.s_printOutError(this, strMethod, "failed");
                    return false;
                }
            }
            
            //else
                //System.out.println("! fleSessionBak.exists(), fleSessionBak.getAbsolutePath()=" + fleSessionBak.getAbsolutePath());
            
            
            // NEW fixing bug ID 12
            
            //File fleSessionLog = new File(fleParent, LastUserAbstract._f_s_strFileSessionLog);
            
            if (! fleSessionLog.exists())
            {
                MySystem.s_printOutError(this, strMethod, "! fleSessionLog.exists(), fleSessionLog.getAbsolutePath()=" + fleSessionLog.getAbsolutePath());
                return false;
            }
            
            if (! fleSessionLog.canRead())
            {
                MySystem.s_printOutError(this, strMethod, "! fleSessionLog.canRead(), fleSessionLog.getAbsolutePath()=" + fleSessionLog.getAbsolutePath());
                return false;
            }
            
            if (! fleSessionLog.canWrite())
            {
                MySystem.s_printOutError(this, strMethod, "! fleSessionLog.canWrite(), fleSessionLog.getAbsolutePath()=" + fleSessionLog.getAbsolutePath());
                return false;
            }
            
            //File fleSessionBak = new File(fleParent, LastUserAbstract._f_s_strFileSessionBak);
            
            if (fleSessionBak.exists())
            {
                MySystem.s_printOutError(this, strMethod, "fleSessionBak.exists(), fleSessionBak.getAbsolutePath()=" + fleSessionBak.getAbsolutePath());
                return false;
            }
            
            if (! com.google.code.p.keytooliui.shared.io.S_FileSys.s_tryRename(fleParent.getAbsolutePath(), LastUserAbstract._f_s_strFileSessionLog, LastUserAbstract._f_s_strFileSessionBak))
            {
                MySystem.s_printOutTrace(this, strMethod, "not renamed, ignoring");
                //System.out.println("fleParent.getAbsolutePath()=" + fleParent.getAbsolutePath());
                //System.out.println("LastUserAbstract._f_s_strFileSessionLog=" + LastUserAbstract._f_s_strFileSessionLog);
                //System.out.println("LastUserAbstract._f_s_strFileSessionBak=" + LastUserAbstract._f_s_strFileSessionBak);
                //blnAppend = true;
            }
            
            /** OLD
            if (! com.google.code.p.keytooliui.shared.io.S_FileSys.s_rename(fleParent.getAbsolutePath(), LastUserAbstract._f_s_strFileSessionLog, LastUserAbstract._f_s_strFileSessionBak))
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                System.out.println("fleParent.getAbsolutePath()=" + fleParent.getAbsolutePath());
                System.out.println("LastUserAbstract._f_s_strFileSessionLog=" + LastUserAbstract._f_s_strFileSessionLog);
                System.out.println("LastUserAbstract._f_s_strFileSessionBak=" + LastUserAbstract._f_s_strFileSessionBak);
                return false;
            }
            **/   
        }
        
        // ---
        
        //File fleSessionLog2 = new File(fleParent, LastUserAbstract._f_s_strFileSessionLog);
        
       
        try
        { 
            //System.setOut(new PrintStream(new FileOutputStream (fleOutLog.getAbsolutePath(), false))); 
            //System.setErr(new PrintStream(new FileOutputStream (fleErrLog.getAbsolutePath(), false))); 
            
            // !!!!! where is it closed? !!!!!!!!
            FileOutputStream fos = new FileOutputStream(fleSessionLog.getAbsolutePath(), blnAppend);
            
            PrintStream psm = new PrintStream(fos);
            System.setOut(psm);
            System.setErr(psm); 
        } 
        
        catch(Exception exc) 
        {
            // should open up an error message
            exc.printStackTrace();
            System.out.println("exc caught, failed to redirect"); 
            MySystem.s_printOutError(this, strMethod, "exc caught, failed to redirect");
            return false;
        }
        
        
        // beg test
        
        MySystem.s_storePathLogSession(fleSessionLog.getAbsolutePath());
        // end test
        
        // ending
        return true;
    }
    
    private boolean _writeConfigLog()
    {
        String strMethod = "_writeConfigLog()";
        
        if (LastUserAbstract._s_blnConfigLogDone == true)
            return true;
        
        LastUserAbstract._s_blnConfigLogDone = true;
        
        File fleParent = super._getFileDirUser_();
        
        if (fleParent == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil fleParent");
            return false;
        }
        
        File fleConfigLog = new File(fleParent, LastUserAbstract._f_s_strFileConfigLog);
        
        
        // --
        
        if (fleConfigLog.exists())
        {         
            File fleConfigBak = new File(fleParent, LastUserAbstract._f_s_strFileConfigBak);
        
            if (fleConfigBak.exists())
            {                
                if (! com.google.code.p.keytooliui.shared.io.S_FileSys.s_deleteFile(fleConfigBak.getAbsolutePath()))
                {
                    MySystem.s_printOutError(this, strMethod, "failed");
                    return false;
                }
            }
            
            if (! com.google.code.p.keytooliui.shared.io.S_FileSys.s_rename(
                fleParent.getAbsolutePath(), 
                LastUserAbstract._f_s_strFileConfigLog, 
                LastUserAbstract._f_s_strFileConfigBak))
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
        }
        
        // --
        
        Runtime rte = Runtime.getRuntime();     
        long lngFreeMemory = rte.freeMemory();
        long lngTotalMemory = rte.totalMemory();
        
        StringBuffer sbr = new StringBuffer();
        
        sbr.append("---- begin memory ----");sbr.append("\n");
        sbr.append("free memory" + "=<" + lngFreeMemory + ">"); sbr.append("\n");
        sbr.append("total memory" + "=<" + lngTotalMemory + ">"); sbr.append("\n");
        sbr.append("---- end memory ----");sbr.append("\n");
        
        sbr.append("---- begin specifics ----");sbr.append("\n");
        sbr.append("javax.net.ssl.keyStore" + "=<" + System.getProperty("javax.net.ssl.keyStore") + ">"); sbr.append("\n");
        sbr.append("javax.net.ssl.trustStore" + "=<" + System.getProperty("javax.net.ssl.trustStore") + ">"); sbr.append("\n");
        sbr.append("---- end specifics ----");sbr.append("\n");
       
        java.util.Properties prps = null;
        
        try
        {
            prps = System.getProperties();
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(strMethod, "exc caught");
            return false;
        }
        
       
        
        java.util.Enumeration enu = prps.propertyNames();
        
        String[] strs = new String[prps.size()];
        int i = 0;
        
        // 
        
        while (enu.hasMoreElements())
        {
            String strKey = (String) enu.nextElement();
            strs[i] =  strKey;
            i ++;
        }
        
        Arrays.sort(strs);
        sbr.append("---- begin system's properties ----");sbr.append("\n");
        
        for (int j=0; j<strs.length; j++)
        {
            String strKey = strs[j];
            String strValue = prps.getProperty(strKey);
            sbr.append(strKey + "=<" + strValue + ">"); sbr.append("\n");
        }
        
        sbr.append("---- end system's properties ----");sbr.append("\n");
        
        
        try
        {
            FileWriter fwrConfigLog = new FileWriter(fleConfigLog);
            fwrConfigLog.write(sbr.toString());
            fwrConfigLog.close();
            fwrConfigLog = null;
        }
        
        catch(IOException excIO)
        {
            excIO.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excIO caught");
            return false;
        }
        
        // ending
        return true;
    }
    
}
