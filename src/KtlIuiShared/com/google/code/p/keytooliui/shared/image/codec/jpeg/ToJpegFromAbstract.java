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
 
 
 package com.google.code.p.keytooliui.shared.image.codec.jpeg;

/**

    known subclasses:
    . ToJpegFromDocReader
    . ToJpegFromDocBuilder
    . ToJpegFromTplBuilder

an abstract class that make a screen capture of a component (input),
next save it in JPEG format,
finally shows a message dialog with the reduced image displayed inside

at the first run, open up a dialog window:
. files prefix?
. parent location? (default: [appli.home]/???????????????

**/

import com.google.code.p.keytooliui.shared.lang.*;

import com.sun.image.codec.jpeg.*;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

abstract public class ToJpegFromAbstract
{
    // ----------------------
    // FINAL STATIC PROTECTED
    
    final static protected String _f_s_strFileOutSuffix_ = ".jpg";
    
    // ----------------
    // STATIC PROTECTED
    
    // used by subclasses
    static protected String _s_strDlgFileTitle_ = null;
    static protected String _s_strDlgFileButtonApproveText_ = null;
    static protected String _s_strDlgFileButtonApproveTip_ = null;
    
    // --------------
    // STATIC PRIVATE
    
    
    static private String _s_strDlgInfoBody1 = null;
    static private String _s_strDlgInfoBody2 = null;
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        final String f_strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".ToJpegFromAbstract" // class name
            ;
        
        final String f_strWhere = "com.google.code.p.keytooliui.shared.image.codec.jpeg.ToJpegFromAbstract";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(
                f_strBundleFileShort, 
                java.util.Locale.getDefault());
        
            _s_strDlgInfoBody1 = rbeResources.getString("dlgInfoBody1");
            _s_strDlgInfoBody2 = rbeResources.getString("dlgInfoBody2");
            
            _s_strDlgFileTitle_ = rbeResources.getString("dlgFileTitle");
            _s_strDlgFileButtonApproveText_ = rbeResources.getString("dlgFileButtonApproveText");
            _s_strDlgFileButtonApproveTip_ = rbeResources.getString("dlgFileButtonApproveTip");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(f_strWhere, "excMissingResource caught");
        }
    }
    
    // ---------------
    // ABSTRACT PUBLIC
    
    
    
    abstract public void destroy();
    abstract public boolean doJob();
    
    // ------
    // PUBLIC
    
    public boolean init()
	{
	    
	    String strMethod = "init()";
	    
	    if (this._cmpSource == null)
	    {
	        MySystem.s_printOutError(this, strMethod, "nil this._cmpSource");
	        return false;
	    }

        Dimension dim = this._cmpSource.getSize(null); 
        this._bieSource = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB); 
        Graphics gra = this._bieSource.getGraphics(); 
        this._cmpSource.paintAll(gra); 
        

	    return true;
	}
    

    // ---------
    // PROTECTED
    
    
    protected String _strTitleApplication_ = null;
    protected Component _cmpFrameParent_ = null;

    protected ToJpegFromAbstract(Component cmpFrameParent, String strTitleApplication, Component cmpSource)
    {
        this._cmpFrameParent_ = cmpFrameParent;
        this._strTitleApplication_ = strTitleApplication;
        this._cmpSource = cmpSource;
    }
    
    protected boolean _exportAsJPEG_(File fleOut)
    {
        String f_strMethod = "_exportAsJPEG_(fleOut)";
        
        if (fleOut==null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil arg");
            return false;
        }
        
        if (this._bieSource == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil this._bieSource");
            return false;
        }
        
        OutputStream osm = null; 
        
        try
        { 
            osm = new FileOutputStream(fleOut); 
            JPEGImageEncoder ier = JPEGCodec.createJPEGEncoder(osm); 
            JPEGEncodeParam param = ier.getDefaultJPEGEncodeParam(this._bieSource); 
            
            param.setQuality(1.0f, false); 
            // test
            //param.setDensityUnit(1000);
            
            ier.setJPEGEncodeParam(param); 
            ier.encode(this._bieSource); 
            osm.flush();
            osm.close(); 
        } 
        
        catch (Exception exc)
        { 
            exc.printStackTrace();
            MySystem.s_printOutError(this, f_strMethod, "exc caught");
            return false;
        } 
        
        // shows info dialog
        
        MySystem.s_printOutTrace(f_strMethod, "saved in " + fleOut.getAbsolutePath());
        
        
        String strDlgInfoBody = _s_strDlgInfoBody1;
        strDlgInfoBody += " " + fleOut.getName();
        strDlgInfoBody += "\n";
        strDlgInfoBody += _s_strDlgInfoBody2;
        strDlgInfoBody += "\n\n";
        strDlgInfoBody += fleOut.getAbsolutePath();    
        
        
        com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showDialogInfo(
            this._cmpFrameParent_, this._strTitleApplication_, strDlgInfoBody);

        

        return true;
    }
    
    

    // -------
    // PRIVATE
    
    private BufferedImage _bieSource = null;
    private Component _cmpSource = null;
}