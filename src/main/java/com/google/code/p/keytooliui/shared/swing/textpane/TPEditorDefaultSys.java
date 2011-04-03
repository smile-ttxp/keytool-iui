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
 
 
 package com.google.code.p.keytooliui.shared.swing.textpane;


import com.google.code.p.keytooliui.shared.lang.*;

import java.io.*;

final public class TPEditorDefaultSys extends TPEditorAbstract 
{    
    // ------
    // PUBLIC
    
    public boolean writeTo(FileOutputStream fos)
        throws Exception
    {
        String f_strMethod = "writeTo(fos)";
        
        if (fos == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil fos");
            return false;
        }
        
        String strText = this.getText();
        
        if (strText == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil strText");
            return false;
        }
        
        byte[] bytsText = strText.getBytes();
        
        if (bytsText == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil bytsText");
            return false;
        }
        
        try
        {
           
            fos.write(bytsText);

            //fos.close(); // ???????
            fos.flush();
            fos.close();
            fos = null;
        }
        
        catch (java.io.IOException excIO)
        {
            excIO.printStackTrace();
            MySystem.s_printOutError(f_strMethod, "excIO caught");
            return false;
        }
        
        
        
        
        return true;
    }
    
    public boolean doFileOpen(FileInputStream fis)
        throws Exception
    {
        String f_strMethod = "doFileOpen(fis)";
        
        String strSource = _loadSource(fis);
        
        if (strSource == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil strSource");            
            return false;
        }
        
        this.setText(strSource);
        setEnabled(true);
        
        /**
        this._uan_.setEnabled(false);
        this._ran_.setEnabled(false);
        
        // initilaizing undoManager
        this._umr_ = new UndoManager();
        
        // ----
        
         // 1) destroying sure?????
        
        DefaultDefaultSysDocument dsdCurrent = (DefaultDefaultSysDocument) getDocument();
        
        if (dsdCurrent != null)
        {
            dsdCurrent.removeDocumentListener(_mdlListener);
            dsdCurrent.removeUndoableEditListener(_mueListener);
            dsdCurrent = null;
        }
        // ----
        
		DefaultDefaultSysDocument dsd = (DefaultDefaultSysDocument) ois.readObject();
		//setDocument(dsd);
		
		_setDefaultFont();
		dsd.addDocumentListener(_mdlListener);
	    dsd.addUndoableEditListener(_mueListener);
	    
	    setDefaultSysDocument(dsd); 
        setEnabled(true);
        **/
        
        return true;
    }

    /**
        todo: clean-up undo & redo
    **/
    
    public boolean doFileNew()
        throws Exception
    {
        String f_strMethod = "doFileNew()";
        
        MySystem.s_printOutWarning(this, f_strMethod, "IN PROGRESS");
        
        /**
        //_blnRecorded = false;
        
        // ------------
        // 0) disabling
        
        this._uan_.setEnabled(false);
        this._ran_.setEnabled(false);
        
        // initilaizing undoManager
        this._umr_ = new UndoManager();
        
        // 1) destroying
        
        DefaultDefaultSysDocument dsd = (DefaultDefaultSysDocument) getDocument();
        
        if (dsd == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil dsd");
            return false;
        }
        
        dsd.removeDocumentListener(_mdlListener);
        dsd.removeUndoableEditListener(_mueListener);
        
        // 2) creating
        _setDefaultFont();
            
        StyleContext sct = new StyleContext();
	    dsd = new DefaultDefaultSysDocument(sct);
	    
	    
	    dsd.addDocumentListener(_mdlListener);
	    dsd.addUndoableEditListener(_mueListener);
	    
        setDefaultSysDocument(dsd); 
        setEnabled(true);
        **/
        
        return true;
    }
    

    public TPEditorDefaultSys()
    {
        super();
        
        //_setDefaultFont();
        //_createChildren();
    }
    
    public boolean init()
    {
        String f_strMethod = "init()";
       
        if (! super._init_())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
    
        return true;
    }
    
    public void destroy()
    {
    }
    
   
    // -------
    // PRIVATE
    
    
    
    /**
    private void _setDefaultFont()
    {
        int intFontStyle = Font.PLAIN;
        
        if (METextFontStyleEditorText.F_S_BLN_BOLD && METextFontStyleEditorText.F_S_BLN_ITALIC)
        {
            intFontStyle = Font.BOLD|Font.ITALIC;
        }
        
        else  if (METextFontStyleEditorText.F_S_BLN_BOLD)
        {
            intFontStyle = Font.BOLD;
        }
        
        else  if (METextFontStyleEditorText.F_S_BLN_ITALIC)
        {
            intFontStyle = Font.ITALIC;
        }
        
        
        Font fnt = new Font(METextFontFamilyEditorText.F_S_STR_DEFAULT, intFontStyle, METextFontSizeEditorText.F_S_INT_DEFAULT);
        setFont(fnt);
    }
    **/
    
    
    
    /**
    private void _createChildren()
    {
        this._umr_ = new UndoManager();
        this._uan_ = new UndoAction();
        this._ran_ = new RedoAction();
	    this._mdlListener = new MyDocumentListener();
	    this._mueListener = new MyUndoableEditListener();
	    
	    DefaultDefaultSysDocument dsd = new DefaultDefaultSysDocument(new StyleContext());
	    
	    dsd.addDocumentListener(this._mdlListener);
	    dsd.addUndoableEditListener(this._mueListener);
	    
        setDefaultSysDocument(dsd); 
    }
    **/
    
    private String _loadSource(FileInputStream fis)
    {
	    String f_strMethod = "_loadSource(fis)";
	    
	    if (fis == null)
	    {
	        MySystem.s_printOutExit(this, f_strMethod, "nil fis");
		    //return new String("failed to open file");
		    
	    }
	    
	    String strSourceCode = new String();
	    char[] chrsBuffer = new char[50000];
	    
	    try
	    {
		    
		    InputStreamReader isr = new InputStreamReader(fis);
		   
		    int intNch;
		    
		    while ((intNch = isr.read(chrsBuffer, 0, chrsBuffer.length)) != -1)
		    {
		        strSourceCode += new String(chrsBuffer, 0, intNch);
		    }
		    
		    //fis.flush(); // ??????
	    }
	    
	    catch(Exception exc)
	    {
	        exc.printStackTrace();
		    MySystem.s_printOutError(this, f_strMethod, "exc caught");
		    return null;
		    
		    //return new String("failed to open file \n\n" + fle.getAbsolutePath());
		    
	    }
	    
	    return strSourceCode;
    }
}