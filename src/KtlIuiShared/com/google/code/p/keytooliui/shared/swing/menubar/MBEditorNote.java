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
 
 
 package com.google.code.p.keytooliui.shared.swing.menubar;

import com.google.code.p.keytooliui.shared.swing.menu.*;
import com.google.code.p.keytooliui.shared.util.eventlistener.*;
import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

final public class MBEditorNote extends MBEditorAbstract
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strBundleFileShort =
        com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
        ".MBEditorNote" // class name
        ;
    
    final static private String _f_s_strBundleFileLong = _f_s_strBundleFileShort + ".properties";
    
    // --------------
    // STATIC PRIVATE
    
    static private java.util.ResourceBundle _s_rbeResources;
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        final String f_strWhere = "com.google.code.p.keytooliui.shared.swing.menubar.MBEditorNote";
        
        try
        {
            _s_rbeResources = java.util.ResourceBundle.getBundle(_f_s_strBundleFileShort, 
                java.util.Locale.getDefault());
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(f_strWhere, _f_s_strBundleFileLong + " not found, excMissingResource caught");
        }
    }
    
    
    // ------
    // PUBLIC

    
    public boolean doFileNew()
        throws Exception
    {
        String f_strMethod = "doFileNew()";
        
        if (! super._doFileNew_())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }

        if (! this._ned.doFileNew())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        
        if (! this._nft.doFileNew())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }

        if (! this._nct.doFileNew())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        if (! this._nca.doFileNew())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    public boolean doFileOpen()
        throws Exception
    {
        String f_strMethod = "doFileOpen()";
        
        if (! super._doFileOpen_())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        if (! this._ned.doFileOpen())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        if (! this._nft.doFileOpen())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        if (! this._nct.doFileOpen())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        if (! this._nca.doFileOpen())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }

        return true;
    }
    
    
    public MBEditorNote(
        java.awt.Component cmpFrameOwner,
  
        java.awt.event.ActionListener actListenerParent,
        MFileAllEditorListener nfeListenerParent,
        MEditEditorTextListener netListenerParent,
        java.util.Hashtable actions,
        AbstractAction aanUndo,
        AbstractAction aanRedo)
    {
        super(actListenerParent, nfeListenerParent);
        
        this._ned = new MEditEditorText(netListenerParent, actions, aanUndo, aanRedo);
        
        this._jmnStyle = new MDefault();
        
        this._nft = new METextFontEditorText(actions);
        this._nct = new METextFgColorEditorText();
        this._nca = new METextAlignmentEditorText(actions);
        
        super._nhp_ = new MHelpAboutEditorNote(cmpFrameOwner);
    }
    
    public boolean init()
    {
        String f_strMethod = "init()";
        
        if (! super._init_())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        if (! _loadResourceBundle())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }

        // --
        
        if (this._ned == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil this._ned");
            return false;
        }
        
        if (! this._ned.init())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        // --
        
        if (this._nft == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil this._nft");
            return false;
        }
        
        if (! this._nft.init())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
      
        // --
        
        if (this._nct == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil this._nct");
            return false;
        }
        
        if (! this._nct.init())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
     
        // --
        
        if (this._nca == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil this._nca");
            return false;
        }
        
        if (! this._nca.init())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
      
        // --
        
        if (! super._insert_(this._ned))
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        
        this._jmnStyle.add(this._nft);
        this._jmnStyle.add(this._nct);
        this._jmnStyle.add(this._nca);

        if (! super._insert_(this._jmnStyle))
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
    
        // ending
        return true;
    }
    
    public void destroy()
    {
        super._destroy_();

        if (this._ned != null)
        {
            this._ned.destroy();
            this._ned = null;
        }
        
        if (this._nft != null)
        {
            this._nft.destroy();
            this._nft = null;
        }
        
        if (this._nct != null)
        {
            this._nct.destroy();
            this._nct = null;
        }
        
        if (this._nca != null)
        {
            this._nca.destroy();
            this._nca = null;
        }   
    }
    
    
    // -------
    // PRIVATE

    private MEditEditorText _ned;
    private METextFontEditorText _nft;
    private METextFgColorEditorText _nct;
    private METextAlignmentEditorText _nca;
    
    private JMenu _jmnStyle = null;
    
    private boolean _loadResourceBundle()
    {
        String f_strMethod = "_loadResourceBundle()";
        
         /* MEMO: trim() not needed
        */
        try
        {   
            String strValue = null;
            
            // TEXTS

	        strValue = _s_rbeResources.getString("text_style");
	        this._jmnStyle.setText(strValue);

	        
	        // MNEMONICS
	        
	        char chrValue;
	        
	        strValue = _s_rbeResources.getString("mnemo_style");
	        chrValue = strValue.trim().charAt(0);
	        this._jmnStyle.setMnemonic(chrValue);
	    }
	    
	    catch (java.util.MissingResourceException excMissingResource)
	    {
	        excMissingResource.printStackTrace();
	        MySystem.s_printOutError(this, f_strMethod, _f_s_strBundleFileLong + ", excMissingResource caught");
	        return false;
	    }
	    
	    catch(IndexOutOfBoundsException excIndexOutOfBounds) // for charAt(0)
	    {
	        excIndexOutOfBounds.printStackTrace();
	        MySystem.s_printOutError(this, f_strMethod, _f_s_strBundleFileLong + ", excIndexOutOfBounds caught");
	        return false;
	    }
	    
	    // ending
	    return true;
    }   
}