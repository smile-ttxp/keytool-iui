package com.google.code.p.keytooliui.javax.media;

/**
    . handling tbrls about resources not freed
    . support for OggVorbis


    note: tutorial "Creating and Loading Shared Libraries"
    http://java.sun.com/docs/books/tutorial/native1.1/stepbystep/_library.html
**/


import com.google.code.p.keytooliui.shared.lang.*;




import javax.media.protocol.*;
import javax.media.*;

import java.io.*;
import java.awt.*;
import java.util.*;

final public class MyManager
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.javax.media.MyManager.";
    
    // trick to handle Exception of type java.net.UnknownHostException
    final static private String _f_s_strUnknownHost = "com.sun.media.protocol.http.DataSource";
    
    
    final static private String _f_s_strNameShortLibWindowsJmdaudc = "jmdaudc";
    
    //    see bug id: 4435414, about "ordinal 6 not located in dsound.dll", 
    //    ==> seems to be a bug in Windows NT 4.0
    //    SUN assumes that should be fixed in JMF 2.2
    final static private String[] _f_s_strsNameShortLibWindowsNtNO =
    {
        MyManager._f_s_strNameShortLibWindowsJmdaudc
    };
    
    // memo: don't prepend "lib" to "so" file names
    final static private String[] _f_s_strsNameShortLibWindows =
    {
        // FOBS4JMF
        //"fobs4jmf",
        
        // JMF 2.1.1e
        "jmutil", // OK
		"jmmpegv", // OK
		"jmddraw", // OK
		"jmam", // OK
        "jmacm", // OK
        "jmcvid", // OK
        MyManager._f_s_strNameShortLibWindowsJmdaudc, // OK        
        "jmdaud", // OK
        "jmg723", // OK
        "jmgdi", // OK
        "jmgsm", // OK
        "jmh261", // OK
        "jmh263enc", // OK
        "jmjpeg", // OK
        "jmmci", // OK
        "jmmpa", // OK
        "jmvcm", // OK
        "jmvfw", // OK
        "jmvh263", // OK            
        "jsound" // OK
    };
    
    final static private String[] _f_s_strsNameShortLibLinux =
    {
         // JMF 2.1.1e
        "libjmutil", // OK
		"libjmmpegv", // OK
		//"libjmddraw", // NO, windows only 
		//"libjmam", // NO, windows only
        //"libjmacm", // NO, windows only
        "libjmcvid", // OK 
        //"libjmdaudc", // NO, windows only
        "libjmdaud", // OK 
        "libjmg723", //  OK
        //"libjmgdi", // NO, windows only 
        "libjmgsm", // OK 
        "libjmh261", // OK
        "libjmh263enc", // OK 
        "libjmjpeg", // OK
        //"libjmmci", // NO, windows only 
        "libjmmpa", // OK
        //"libjmvcm", // NO, windows only 
        //"libjmvfw", // NO, windows only 
        //"libjmvh263", // NO, windows only                 
        //"jsound", // NO, windows only 
            
        // ????
        "libjmfjawt", 
        "libjmmpx", 
        "libjm4l", 
        "libjmxlib" 
    };
    
    
    // ------------------
    // STATIC INITIALIZER
    
    // --------------------
    // BEG OGG-VORBIS STUFF
    
    static
    {
        String strMethod = MyManager._f_s_strClass + "static";
       
        boolean blnJarnStuff;
        
        
        if (true)
            blnJarnStuff = false;
        
        if (! blnJarnStuff)
        {
            MySystem.s_printOutFlagDev(strMethod, "BY-PASS JARNBJO STUFF");
        }
        
        if (blnJarnStuff)
        {
        
            // --
            // 0)
           
            // trick to handle unchecked conversion
            Vector vecTmp = PackageManager.getProtocolPrefixList();
            Vector<String> vecProtocolPrefix = new Vector<String>(); 
            
            for (int i=0; i<vecTmp.size(); i++)
            {
                Object objCur = vecTmp.elementAt(i);
                
                if (! (objCur instanceof String))
                    MySystem.s_printOutExit(strMethod, "! (objCur instanceof String), objCur.toString()=" + objCur.toString());
                    
                String strCur = (String) objCur;
                vecProtocolPrefix.addElement(strCur);
            }
       
            
            
            for (int i=0; i<vecProtocolPrefix.size(); i++)
            {
                MySystem.s_printOutFlagDev(strMethod, "vecProtocolPrefix.elementAt(" + i + ").toString()=" + vecProtocolPrefix.elementAt(i).toString());
            }
            
            // TEMPO
            
            if (vecProtocolPrefix.contains("de.jarnbjo"))
            {
                MySystem.s_printOutFlagDev(strMethod, "vecProtocolPrefix.contains(\"de.jarnbjo\")");
            }
            else
            {
                MySystem.s_printOutFlagDev(strMethod, "! vecProtocolPrefix.contains(\"de.jarnbjo\"), adding");
                vecProtocolPrefix.addElement("de.jarnbjo");
            
                // --
                PackageManager.setProtocolPrefixList(vecProtocolPrefix);
                
                /**
                !!! TBRL:
                getting an uncaught exception:
                Could not commit protocolPrefixList
                **/
                MySystem.s_printOutFlagDev(strMethod, "CODE IN COMMMENTS coz getting Could not commit protocolPrefixList");
                
                /*try
                {
                    PackageManager.commitProtocolPrefixList();
                }
                
                catch(SecurityException excSecurity)
                {
                    excSecurity.printStackTrace();
                    MySystem.s_printOutExit(strMethod, "excSecurity caught");
                }
                */
            }
            
            
            
            
            
            
            
            
            
            
            
            //vecProtocolPrefix = PackageManager.getProtocolPrefixList();
            
             // trick to handle unchecked conversion
            vecTmp = PackageManager.getProtocolPrefixList();
            vecProtocolPrefix = new Vector<String>(); 
            
            for (int i=0; i<vecTmp.size(); i++)
            {
                Object objCur = vecTmp.elementAt(i);
                
                if (! (objCur instanceof String))
                    MySystem.s_printOutExit(strMethod, "! (objCur instanceof String), objCur.toString()=" + objCur.toString());
                    
                String strCur = (String) objCur;
                vecProtocolPrefix.addElement(strCur);
            }
            
            
            
            
            
            for (int i=0; i<vecProtocolPrefix.size(); i++)
            {
                MySystem.s_printOutFlagDev(strMethod, "vecProtocolPrefix.elementAt(" + i + ").toString()=" + vecProtocolPrefix.elementAt(i).toString());
            }
            
            MySystem.s_printOutFlagDev(strMethod, "\n");
            
            
            // -------------------------
            // 1) MIME type registration
            /*
                MIME type "application/ogg" 
                file extension "ogg"
            */
            
            // ---------------------------------
            // 2) Ogg demultiplexer registration
            // 
            
            de.jarnbjo.jmf.OggParser opr = null;

	        try 
	        {
	            opr = new de.jarnbjo.jmf.OggParser();
	        } 
    	    
	        catch (Exception exc) 
	        {
	            exc.printStackTrace();
	            MySystem.s_printOutExit(strMethod, "exc caught");
	        }
    	    
	        // ---- TEMPO IN COMMENTS JUNE 14, 2005
	        boolean blnAddedPlugInOpr = false;

            //
            
	        try 
	        {
	            blnAddedPlugInOpr = PlugInManager.addPlugIn(
	                opr.getClass().getName(),
			        opr.getSupportedInputContentDescriptors(),
			        new Format[0], 
			        PlugInManager.DEMULTIPLEXER);  
	        } 
	        catch (Exception exc) 
	        {
	            exc.printStackTrace();
	            MySystem.s_printOutExit(strMethod, "exc caught");
	        }
    	    
	        if (! blnAddedPlugInOpr)
	        {
	            MySystem.s_printOutFlagDev(strMethod, "! blnAddedPlugInOpr, opr.getClass().getName()=" + opr.getClass().getName());
	            //MySystem.s_printOutExit(strMethod, "! blnAddedPlugInOpr, opr.getClass().getName()=" + opr.getClass().getName());

	        }
    	    
     
            // Save the changes to the plug-in registry
            
            /*try
            {
                PlugInManager.commit();
            }
            
            catch(IOException excIO)
            {
                excIO.printStackTrace();
	            MySystem.s_printOutExit(strMethod, "excIO caught");
            }*/
            
            // --
            
            // 
            
            // beg vorbis
            
            
            de.jarnbjo.jmf.VorbisDecoder vdr = null;

	        try 
	        {
	            vdr = new de.jarnbjo.jmf.VorbisDecoder();
	        } 
    	    
	        catch (Exception exc) 
	        {
	            exc.printStackTrace();
	            MySystem.s_printOutExit(strMethod, "exc caught");
	        }
    	    
	        // --
    	    
	        boolean blnAddedPlugInVdr = false;

            // 
	        try 
	        {
	            blnAddedPlugInVdr = PlugInManager.addPlugIn(vdr.getClass().getName(),
			        vdr.getSupportedInputFormats(),
			        vdr.getSupportedOutputFormats(null),
			        PlugInManager.CODEC);  
	        } 
	        catch (Exception exc) 
	        {
	            exc.printStackTrace();
	            MySystem.s_printOutExit(strMethod, "exc caught");;
	        }
    	    
	        if (! blnAddedPlugInVdr)
	        {
	            MySystem.s_printOutFlagDev(strMethod, "! blnAddedPlugInVdr, vdr.getClass().getName()=" + vdr.getClass().getName());
	            //MySystem.s_printOutExit(strMethod, "! blnAddedPlugInVdr, vdr.getClass().getName()=" + vdr.getClass().getName());

	        }
    	    
	        // end vorbis
    	    
	        // beg theora
    	    
	        /**de.jarnbjo.jmf.TheoraDecoder tdr = null;

	        try 
	        {
	            tdr = new de.jarnbjo.jmf.TheoraDecoder();
	        } 
    	    
	        catch (Exception exc) 
	        {
	            exc.printStackTrace();
	            MySystem.s_printOutExit(strMethod, "exc caught");
	        }
    	    
	        // --
    	    
	        boolean blnAddedPlugInTdr = false;

            // 
	        try 
	        {
	            blnAddedPlugInTdr = PlugInManager.addPlugIn(tdr.getClass().getName(),
			        tdr.getSupportedInputFormats(),
			        tdr.getSupportedOutputFormats(null),
			        PlugInManager.CODEC);  
	        } 
	        catch (Exception exc) 
	        {
	            exc.printStackTrace();
	            MySystem.s_printOutExit(strMethod, "exc caught");;
	        }
    	    
	        if (! blnAddedPlugInTdr)
	        {
	            MySystem.s_printOutExit(strMethod, "! blnAddedPlugInTdr, tdr.getClass().getName()=" + tdr.getClass().getName());
	        }**/
    	    
	        // end theora
     
            // Save the changes to the plug-in registry
      
            /**
                !!! TBRL:
                
                getting an uncaught exception:
                javax.lang.reflect.InvocationTargetException
            **/
            MySystem.s_printOutFlagDev(strMethod, "CODE IN COMMMENTS coz getting uncaught javax.lang.reflect.InvocationTargetException");
            /**try
            {
                PlugInManager.commit();
            }
            
            catch(IOException excIO)
            {
                excIO.printStackTrace();
	            MySystem.s_printOutExit(strMethod, "excIO caught");
            }**/
        
  
        // --
        } // end of if (blnJarnStuff)
    }
    
    // --------------------
    // END OGG-VORBIS STUFF
    
    // ####
        
        
 
        
    
    // --------------
    // STATIC PRIVATE
    
    static private boolean _s_blnLoadedLibrary = false;
    static private Vector<Player> _s_vecPlayer = new Vector<Player>();
    
    static private boolean _s_gotUnknownHostException(javax.media.NoPlayerException excNoPlayer)
    {
        if (excNoPlayer == null)
            return false;
            
        String strMessage = excNoPlayer.getMessage();
        
        if (strMessage == null)
            return false;
            
        if (strMessage.indexOf(_f_s_strUnknownHost) == -1)
            return false;
            
        return true;
    }
    
    
    
    // -------------
    // STATIC PUBLIC
    
    synchronized static public void s_loadLibrary()
	{
	    String strMethod = MyManager._f_s_strClass + "s_loadLibrary()";
	    
	    
		if (MyManager._s_blnLoadedLibrary)
		    return;
		
		// dec 27, 05: code in comments for now
		// tempo, fixing up conflicts JMF v/s JavaSound, plus migration NetBeans IDE 5.0 beta + tbrl JMF v/s linux
		else
		{
		    MySystem.s_printOutFlagDev(strMethod, "TEMPO CODE, BYPASS METHOD!");
		    
		    MyManager._s_blnLoadedLibrary = true;
		    return;
		}
		
		
		
		/**
		String strJavaLibraryPath = System.getProperty("java.library.path");
		
		if (strJavaLibraryPath == null)
		{
		    MySystem.s_printOutFlagDev(strMethod, "nil strJavaLibraryPath, ignoring"); 
		}
		
		else 
		    MySystem.s_printOutFlagDev(strMethod, "strJavaLibraryPath=" + strJavaLibraryPath); 
		
		
		    
		MyManager._s_blnLoadedLibrary = true;
		    
	    // -- for info
	    
	            
        try
        {
            MySystem.s_printOutFlagDev(strMethod, 
                "\n" + "    " + 
                "javax.media.Manager.getVersion()=" + javax.media.Manager.getVersion() + 
                "\n" + "    " + 
                "javax.media.Manager.getCacheDirectory()=" + javax.media.Manager.getCacheDirectory());
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutExit(strMethod, "exc caught");
        }
        
        
	    
	    // --
		
		MySystem.s_printOutFlagDev(strMethod, "done for Windows other than NT, coz sun's bugID 4435414");
		
		// Native libs for JMF!
		String strNameOs = System.getProperty("os.name");

        if (strNameOs.toLowerCase().startsWith("windows")) 
        {
           if (! _s_loadLibraryWindows(strNameOs))
           {
                MySystem.s_printOutExit(strMethod, "failed, strNameOs=" + strNameOs);
           }
        }
        
        else if (strNameOs.toLowerCase().startsWith("linux")) 
        {
           if (! _s_loadLibraryLinux())
           {
                MySystem.s_printOutExit(strMethod, "failed, strNameOs=" + strNameOs);
           }
        }
        
        else 
        {
            MySystem.s_printOutWarning(strMethod, "Not done yet: native JMFs for this OS, strNameOs=" + strNameOs);
        }
        **/

    }
    
    static public void s_cleanUp(Player ply)
    {
        String strMethod = _f_s_strClass + "s_cleanUp(ply)";
        
        MySystem.s_printOutTrace(strMethod, "...");
        
        if (ply == null)
            return;
        
        // todo: check vector
        
        if (MyManager._s_vecPlayer != null)
        {
            if (MyManager._s_vecPlayer.contains(ply))
                MyManager._s_vecPlayer.remove(ply);
        }
        
        MyManager._s_cleanUp(ply);
        
        /*
            IMPORTANT:
            if not calling "System.gc()", and file is a temp file to be deleted at exit time
            then fails to remove the temp file
        */
        System.gc();
    }
    
    static public void s_cleanUpAll()
    {
        String strMethod = _f_s_strClass + "s_cleanUpAll()";
        
        MySystem.s_printOutTrace(strMethod, "...");
        
        if (MyManager._s_vecPlayer == null)
            return;
            
        if (MyManager._s_vecPlayer.size() < 1)
            return;
            
        //_s_stop
        
        for (int i=0; i<MyManager._s_vecPlayer.size(); i++)
        {
            Player ply = (Player) MyManager._s_vecPlayer.elementAt(i);
            
            if (ply == null)
                continue;
                
            MyManager._s_stop(ply);
        }
            
        for (int i=0; i<MyManager._s_vecPlayer.size(); i++)
        {
            Player ply = (Player) MyManager._s_vecPlayer.elementAt(i);
            
            if (ply == null)
                continue;
                
            MyManager._s_cleanUp(ply);
        }
        
        MyManager._s_vecPlayer.removeAllElements();
        MyManager._s_vecPlayer = null;
        
        
        /*
            IMPORTANT:
            if not calling "System.gc()", and file is a temp file to be deleted at exit time
            then fails to remove the temp file
        */
        System.gc();
    }
    
    static public Player s_createPlayer(DataSource dse)
    {
        String strMethod = _f_s_strClass + "s_createPlayer(dse)";
        
        if (dse == null)
        {
            MySystem.s_printOutError(strMethod, "nil dse");
            return null;
        }
        
        Player ply = null;
        
        try
        {
            ply = Manager.createPlayer(dse);
        }
        
        catch(IOException excIO)
        {
            excIO.printStackTrace();
            MySystem.s_printOutError(strMethod, "excIO caught");
            return null;
        }
        
        catch(NoPlayerException excNoPlayer)
        {
            excNoPlayer.printStackTrace();
            MySystem.s_printOutError(strMethod, "excNoPlayer caught");
            return null;
        }
        
        
        MyManager._s_vecPlayer.addElement(ply);
        
        return ply;
    }
    
    static public Player s_createPlayer(
        MediaLocator mlr,
        Window winParent,
        String strTitleAppli
        )
    {
        String strMethod = _f_s_strClass + "s_createPlayer(mlr, winParent, strTitleAppli)";
        
        MySystem.s_printOutFlagDev(strMethod, "TEMPO CODE ...");
        
        
        // --
        
        if (mlr == null)
        {
            MySystem.s_printOutError(strMethod, "nil mlr");
            return null;
        }
        
        DataSource dse = null;
        
        
        java.net.URL url = null;
        
        
        try
        {
            url = mlr.getURL();
        }
        
        catch(java.net.MalformedURLException excMalformedURL)
        {
            excMalformedURL.printStackTrace();
            MySystem.s_printOutError(strMethod, "excMalformedURL caught");
            return null;
        }

        // --
        
        // jarred
        if (url.getProtocol().toLowerCase().equals("jar"))
        {
            String urlFile = url.getFile();
        
                
            try
            {
                // oggVorbis, jarred
                if (com.google.code.p.keytooliui.javax.media.protocol.PullDataSourceJarNoOgg.s_isValidExtension(url))
                {
                    dse = new com.google.code.p.keytooliui.javax.media.protocol.PullDataSourceJarYesOgg(mlr);
                }
                
                // BEG NOT WORKING !!
                /*else if (urlFile.toLowerCase().endsWith(".mpg") ||
                  urlFile.toLowerCase().endsWith(".mpeg"))
                {
                    dse = new com.google.code.p.keytooliui.javax.media.protocol.PullDataSourceJarYesMpeg(mlr);
                }
                
                else if (urlFile.toLowerCase().endsWith(".mov"))
                {
                    dse = new com.google.code.p.keytooliui.javax.media.protocol.PullDataSourceJarYesMov(mlr);
                }
                
                else if (urlFile.toLowerCase().endsWith(".avi"))
                {
                    dse = new com.google.code.p.keytooliui.javax.media.protocol.PullDataSourceJarYesAvi(mlr);
                }*/
                    
                else
                {
                    MySystem.s_printOutError(strMethod, "not yet supported, url.toString()=" + url.toString());
                    return null;
                }
            }
            
            catch(IOException excIO)
            {
                excIO.printStackTrace();
                MySystem.s_printOutError(strMethod, "excIO caught");
                return null;
            }
                
            catch(IllegalArgumentException excIllegalArgument)
            {
                excIllegalArgument.printStackTrace();
                MySystem.s_printOutError(strMethod, "excIllegalArgument caught");
                return null;
            }
        }
        
        // --

        
        
        
        // oggVorbis, unjarred
        else if (com.google.code.p.keytooliui.javax.media.protocol.PullDataSourceJarNoOgg.s_isValidExtension(url))
        {    
            try
            {
                dse = new com.google.code.p.keytooliui.javax.media.protocol.PullDataSourceJarNoOgg(mlr);
            }
            
            catch(IOException excIO)
            {
                excIO.printStackTrace();
                MySystem.s_printOutError(strMethod, "excIO caught");
                return null;
            }
            
            catch(IllegalArgumentException excIllegalArgument)
            {
                excIllegalArgument.printStackTrace();
                MySystem.s_printOutError(strMethod, "excIllegalArgument caught");
                return null;
            }
        }
        
        else // all other than Ogg-Vorbis 
        {
            try
            {
                dse = Manager.createDataSource(mlr);
            }
            
            catch(IOException excIO)
            {
                excIO.printStackTrace();
                MySystem.s_printOutError(strMethod, "excIO caught");
                return null;
            }
            
            catch(NoDataSourceException excNoDataSource)
            {
                excNoDataSource.printStackTrace();
                MySystem.s_printOutError(strMethod, "excNoDataSource caught");
                return null;
            }
        }
        
        
        // ----
        
        if (dse == null)
        {
            MySystem.s_printOutError(strMethod, "nil dse");
            return null;
        }
        
        //MySystem.s_printOutFlagDev(strMethod, "\n");
        
        /*try
        {
            MySystem.s_printOutFlagDev(strMethod, "mlr.getURL().toString()=" + mlr.getURL().toString());
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(strMethod, "exc caught");
            return null;
        }*/
        
        
        
        //Player ply = Manager.createRealizedPlayer(dse);
        
        Player ply = null;
        
        try
        {
            ply = javax.media.Manager.createPlayer(dse); 
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(strMethod, "exc caught");
            return null;
        }
        
        // should add a catch, should be connnected prior to ask for this
        
        try
        {
            MySystem.s_printOutTrace(strMethod, "dse.getContentType()=" + dse.getContentType());
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(strMethod, "exc caught");
            return null;
        }
        
        MyManager._s_vecPlayer.addElement(ply);
        return ply;
        
        /**
        
        if (mlr == null)
        {
            MySystem.s_printOutError(strMethod, "nil mlr");
            return null;
        }
        
        Player ply = null;
        boolean blnLoop = true;
        
        while (blnLoop)
        {
            try
            {
                ply = javax.media.Manager.createPlayer(mlr); 
            }
            
            catch(javax.media.NoPlayerException excNoPlayer)
            {
                excNoPlayer.printStackTrace();
                MySystem.s_printOutError(strMethod, "excNoPlayer caught, mlr.toString()=" + mlr.toString());
                
                if (! _s_gotUnknownHostException(excNoPlayer))
                {
                    MySystem.s_printOutError(strMethod, "! _s_gotUnknownHostException(excNoPlayer)");
                    return null;
	            }
	            // ----
	            MySystem.s_printOutTrace(strMethod, "GOT UnknownHostException");
	                
	            // tempo
                String strBody = "Failed to load media:";
                strBody += "\n";
                strBody += "  " + mlr.toString();
                strBody += "\n\n";
                strBody += "Please make sure you are connected to the Internet,";
                strBody += "\n";
                strBody += "then try again by selecting \"OK\" button below.";
                                    
                String strTitle = null;
                
                if (strTitleAppli != null)
                    strTitle = strTitleAppli + " - " + "Unknown host exception"; // !!!!!!!
                else
                    strTitle = "Unknown host exception";
                    
                if (! com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showConfirmDialog(
                    winParent, strTitle, strBody))
	            {
	                // action cancelled
	                MySystem.s_printOutTrace(strMethod, "action cancelled");
	                return null;
	            }
                    
                continue;
            }
            
            catch(IOException excIO)
            {
                excIO.printStackTrace();
                MySystem.s_printOutError(strMethod, "excIO caught");
                return null;
            }
            
            blnLoop = false;
        }
        
        MyManager._s_vecPlayer.addElement(ply);
        return ply;**/    
    }
    
    // --------------
    // STATIC PRIVATE
    
    static private boolean _s_loadLibraryLinux(
        )
    {
        String strMethod = _f_s_strClass + "_s_loadLibraryLinux()";
        
        MySystem.s_printOutFlagDev(strMethod, "IN PROGRESS ...");
        
        
        for (int i=0; i<MyManager._f_s_strsNameShortLibLinux.length; i++)
        {
            try
            {
                System.loadLibrary(MyManager._f_s_strsNameShortLibLinux[i]);
            }
            
            catch(UnsatisfiedLinkError errUnsatisfiedLink)
            {
                errUnsatisfiedLink.printStackTrace();
                //MySystem.s_printOutError(strMethod, "errUnsatisfiedLink caught");
                //return false;
                
                /* 
                    could be coz, eg: "libjmutil.so already loaded in another classloader"
                    for example if JMF already installed AND in classpath
                */
                MySystem.s_printOutWarning(strMethod, "errUnsatisfiedLink caught, ignoring, MyManager._f_s_strsNameShortLibLinux[" + i + "]=" + 
                    MyManager._f_s_strsNameShortLibLinux[i]);
                
                continue;
            }
            
            catch(SecurityException excSecurity)
            {
                excSecurity.printStackTrace();
                MySystem.s_printOutError(strMethod, "excSecurity caught");
                return false;
            }
        }
        
        return true;
    }
    
    static private boolean _s_loadLibraryWindows(
        String strNameOs
        )
    {
       
        String strMethod = _f_s_strClass + "_s_loadLibraryWindows(strNameOs)";
        
        
        /*MySystem.s_printOutWarning(strMethod, "CODE IN COMMENTS");
        if (true)
            return true;
        */
        
        for (int i=0; i<MyManager._f_s_strsNameShortLibWindows.length; i++)
        {
            boolean blnJump = false;
            
            if (strNameOs.toLowerCase().startsWith("windows nt"))
            {
                for (int j=0; j<MyManager._f_s_strsNameShortLibWindowsNtNO.length; j++)
                {
                    // note: in the following, "toLowerCase()" not really needed!
                    if (MyManager._f_s_strsNameShortLibWindows[i].toLowerCase().compareTo(
                        MyManager._f_s_strsNameShortLibWindowsNtNO[j].toLowerCase()) == 0)
                    {
                        blnJump = true;
                        break;
                    }
                }
            }
            
            if (blnJump)
                continue;
            
            
            try
            {
                System.loadLibrary(MyManager._f_s_strsNameShortLibWindows[i]);
            }
            
            catch(UnsatisfiedLinkError errUnsatisfiedLink)
            {
                /*
                    if launched by JWS
                    ==> BUG
                    else
                    {
                        // launched by eg DOS command:
                        if message like eg:
                            "jmutil.dll already loaded in another classloader"
                        ==> means JMF already installed in user's system, and in the classpath
                        ==> therefore, just return true (DO_NOT_CONTINUE_INSIDE_LOOP)
                        else
                        ==> BUG
                    }
                */
             
                // WebStart
                if (com.google.code.p.keytooliui.shared.AppAbs.s_isDeployedWithJws())
                {
                    MySystem.s_printOutTrace(strMethod, "com.google.code.p.keytooliui.shared.AppAbs.s_isDeployedWithJws()");
                    errUnsatisfiedLink.printStackTrace();
                    MySystem.s_printOutError(strMethod, "errUnsatisfiedLink caught");
                    return false;
                }
                
                // Not WebStart
                MySystem.s_printOutTrace(strMethod, "! com.google.code.p.keytooliui.shared.AppAbs.s_isDeployedWithJws()");
                
                boolean blnAlreadyLoaded = false;
                String strMessage = errUnsatisfiedLink.getMessage();
                
                if (strMessage != null)
                {
                    strMessage = strMessage.trim().toLowerCase();
                    // x-fingers!
                    String strFlag = "already loaded in another classloader";
                    if (strMessage.indexOf(strFlag) != -1)
                    {
                        MySystem.s_printOutFlagDev(strMethod, "strFlag=" + strFlag);
                        blnAlreadyLoaded = true;
                    }
                }
                
                if (! blnAlreadyLoaded)
                {
                    errUnsatisfiedLink.printStackTrace();
                    MySystem.s_printOutError(strMethod, "errUnsatisfiedLink caught");
                    return false;
                }
                
                /* 
                    could be coz, eg: "libjmutil.so already loaded in another classloader"
                    for example if JMF already installed AND in classpath
                */
                
               
                
                String strFlagDev = "errUnsatisfiedLink caught";
                strFlagDev += ",\nignoring,\nreturning";
                strFlagDev +="\n" +  
                    "MyManager._f_s_strsNameShortLibWindows[" + i + "]=" + 
                    MyManager._f_s_strsNameShortLibWindows[i];
                    
               MySystem.s_printOutFlagDev(strMethod, strFlagDev);
                    
                
                /*
                    memo: don't use "continue()",
                    else tbrls, eg: for mp3s, fails to realize!
                    use "return true" instead
                */
                return true;
            }
            
            catch(SecurityException excSecurity)
            {
                excSecurity.printStackTrace();
                MySystem.s_printOutError(strMethod, "excSecurity caught");
                return false;
            }
        }
        
        return true;
    }
    
    static private void _s_cleanUp(Player ply)
    {
        String strMethod = _f_s_strClass + "_s_cleanUp(ply)";
        
        if (ply == null)
            return;
            
        try
        {                
            if (ply.getState() == javax.media.Controller.Started)
            {
                //MySystem.s_printOutTrace(this, strMethod, "intPlayerState == javax.media.Controller.Started");
                ply.stop();
            }
                
            ply.deallocate();
            ply.close();    
            ply = null;
        }
            
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(strMethod, "exc caught, ignoring");
            ply = null;
        }
    }
    
    static private void _s_stop(Player ply)
    {
        String strMethod = _f_s_strClass + "_s_stop(ply)";
        
        if (ply == null)
            return;
            
        try
        {                
            if (ply.getState() == javax.media.Controller.Started)
            {
                //MySystem.s_printOutTrace(this, strMethod, "intPlayerState == javax.media.Controller.Started");
                ply.stop();
            }
        }
            
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(strMethod, "exc caught, ignoring");
        }
    }
}