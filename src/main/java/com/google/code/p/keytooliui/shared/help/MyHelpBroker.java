package com.google.code.p.keytooliui.shared.help;

/**
    a convenient class to:
    1) assign Frame's icon
    2) assign/update stylesheet
    JHelpContentViewer
**/

import com.google.code.p.keytooliui.shared.lang.*;

import javax.help.*;


import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.html.*;

import java.awt.event.*;
import java.awt.*;


final public class MyHelpBroker extends DefaultHelpBroker
{
    // ------
    // PUBLIC
    
    public void destroy()
    {
        String strMethod = "destroy()";
        
        JFrame frm = _getFrameWindow();
	    
        if (frm == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil frm");
            return; // unreached
        }
        
        frm.setVisible(false);
        
        this._sst = null;
    }

    public boolean init()
    {
        String strMethod = "init()";
        
        // should be in a separate thread, see DefaultHelpBroker.initPresentation()
        // AND _assignFrameIcon() in the same thread, else window not yet accessible 
        super.initPresentation();
        
        if (! _assignFrameIcon())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (! assignStyleSheet())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._blnSetLAFSwing)
        {
            if (! _assignLAFBtnTlb())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
        }
        
        // ending
        return true;
    }

    

    /**
        memo: could be called in a separated thread, don't worry about nil fields
    **/
    public boolean assignStyleSheet()
    { 
        String strMethod = "assignStyleSheet()";
        
        JHelp hlp = _getJHelp();
        
        if (hlp == null)
	    {
	        MySystem.s_printOutTrace(this, strMethod, "nil hlp");
	        return true;
	    }
	    

        
        JHelpContentViewer hcv = hlp.getContentViewer();
        
        if (hcv == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil hcv");
            return true;
        }
        
        Component[] cmpsChild = null;
        // --
        
        cmpsChild = hcv.getComponents();
        
        if (cmpsChild == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil cmpsChild");
            return true;
        }
                
        // -------------
        // 1) scrollPane
        JScrollPane spn = null;
        
        for (int i=0; i<cmpsChild.length; i++)
        {
            Component cmpCur = cmpsChild[i];
        
            if (cmpCur instanceof JScrollPane)
            {
                spn = (JScrollPane) cmpCur;
                break;
            }
        }
        
        cmpsChild = null;
        
        if (spn == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil spn");
            return true;
        }
        
        cmpsChild = spn.getComponents();
        
        if (cmpsChild == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil cmpsChild");
            return true;
        }
        
        // -----------
        // 2) viewPort
        JViewport vpt = null;
        
        
        for (int i=0; i<cmpsChild.length; i++)
        {
            Component cmpCur = cmpsChild[i];
        
            if (cmpCur instanceof JViewport)
            {
                vpt = (JViewport) cmpCur;
                break;
            }
        }
        
        cmpsChild = null;
        
        if (vpt == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil vpt");
            return true;
        }
        
        // --------------
        // 3) JEditorPane
        
        Component cmpView = vpt.getView();
        
        if (cmpView == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil cmpView");
            return true;
        }
        
        if (! (cmpView instanceof JEditorPane))
        {
            MySystem.s_printOutError(this, strMethod, "! (cmpView instanceof JEditorPane)");
            return false;
        }
        
        JEditorPane epn = (JEditorPane) cmpView;
        
        
        
        // ----------------
        // 4) HTMLEditorKit
        
        EditorKit ekt = epn.getEditorKit();
        
        if (ekt == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil ekt");
            return true;
        }
        
        if (! (ekt instanceof HTMLEditorKit))
        {
            MySystem.s_printOutError(this, strMethod, "! (ekt instanceof HTMLEditorKit)");
            return false;
        }
        
        HTMLEditorKit ektHtml = (HTMLEditorKit) ekt;
        
        // -------------------------------------
        // 5) assign styleSheet to editorKitHtml
        
        StyleSheet sstCur = ektHtml.getStyleSheet();
        
        if (sstCur == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil sstCur");
            return true;
        }
        
        if (this._sst == sstCur)
        {
            return true;
        }
        
        if (ektHtml != null) // !!!!
            ektHtml.setStyleSheet(this._sst);
        
        return true;
    }
   
    
    public MyHelpBroker(HelpSet hst, boolean blnSetLAFSwing)
    {
        super(hst);
        this._blnSetLAFSwing = blnSetLAFSwing;
        
        String strMethod = "MyHelpBroker(...)";
        
        this._sst = com.google.code.p.keytooliui.shared.swing.text.html.stylesheet.S_StyleSheet.s_getDefault();
        
        if (this._sst == null)
            MySystem.s_printOutExit(this, strMethod, "nil this._sst");    
    }
    
    // ---------
    // PROTECTED
    
    /**
     * Returns the default DisplayHelpFromFocus listener.
     *
     * @see enableHelpKey
     */
    // OVERWRITE SUPERCLASS'S METHOD
    protected ActionListener getDisplayHelpFromFocus() 
    {
	    if (super.displayHelpFromFocus == null)
	        super.displayHelpFromFocus = new MyCSH.MyDisplayHelpFromFocus(this);
	    
	    return super.displayHelpFromFocus;
    }
    
    // TEST: april 1, 07
    // OVERWRITE SUPERCLASS'S METHOD
    protected ActionListener getDisplayHelpFromSource() 
    {
	    if (super.displayHelpFromSource == null)
	        super.displayHelpFromSource = new MyCSH.MyDisplayHelpFromSource(this);
	    
	    return super.displayHelpFromSource;
    }
    
    // -------
    // PRIVATE
    
    private StyleSheet _sst = null;
    private boolean _blnSetLAFSwing;
    
    
    private boolean _assignFrameIcon()
    {
        String strMethod = "_assignFrameIcon()";

        Image imgIcon = com.google.code.p.keytooliui.shared.awt.image.S_Image.s_get("help16.gif");
	    
	    if (imgIcon == null)
	    {
	        MySystem.s_printOutError(this, strMethod, "nil imgIcon");
	        return false;
	    }
	    
	    JFrame frm = _getFrameWindow();
	    
	    if (frm == null)
	    {
	        MySystem.s_printOutError(this, strMethod, "nil frm");
	        return false;
	    }
	    
        frm.setIconImage(imgIcon);
        
        // ending
        return true;
    }
    
    private JFrame _getFrameWindow()
    {
        String strMethod = "_getFrameWindow()";
        
        WindowPresentation pres = this.getWindowPresentation();
	    
	    if (pres == null)
	    {
	        MySystem.s_printOutError(this, strMethod, "nil pres");
	        return null;
	    }
	    
        Window win = pres.getHelpWindow(); 
        
        if (win == null)
	    {
	        MySystem.s_printOutError(this, strMethod, "nil win");
	        return null;
	    }
	    
	    if (! (win instanceof JFrame))
	    {
	        MySystem.s_printOutError(this, strMethod, "! (win instanceof JFrame)");
	        return null;
	    }
	    
        return (JFrame) win;
    }
        
    private JHelp _getJHelp()
    {
        String strMethod = "_getJHelp()";
        
        JFrame frm = _getFrameWindow();
	    
	    if (frm == null)
	    {
	        MySystem.s_printOutError(this, strMethod, "nil frm");
	        return null;
	    }
	    
	    Container cnt = frm.getContentPane();
	    
	    if (cnt == null)
	    {
	        MySystem.s_printOutError(this, strMethod, "nil cnt");
	        return null;
	    }
	    
	    int intCount = cnt.getComponentCount();
	    
	    if (intCount < 1)
	    {
	        MySystem.s_printOutError(this, strMethod, "intCount < 1");
	        return null;
	    }
	    
	    for (int i=0; i<intCount; i++)
	    {
	        Component cmpCur = cnt.getComponent(i);
	        
	        if (cmpCur instanceof JHelp)
	        {
	            return (JHelp) cmpCur;
	        }
	    }
	    
	    MySystem.s_printOutError(this, strMethod, "failed to get instanceof JHelp");
	    return null;
    }
    
    // toolbar's buttons look and feel
    private boolean _assignLAFBtnTlb()
    {
        String strMethod = "_assignLAFBtnTlb()";
        
        JHelp hlp = _getJHelp();
        
        if (hlp == null)
	    {
	        MySystem.s_printOutTrace(this, strMethod, "nil hlp");
	        return true;
	    }
	    
	    
	    Component[] cmpsChild = null;
	    // --
	    
	    
	    cmpsChild = hlp.getComponents();
        
        if (cmpsChild == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil hlp cmpsChild");
            return true;
        }
        
        // get toolbar
        
        JToolBar tbr = null;
        
        
        for (int i=0; i<cmpsChild.length; i++)
        {
            Component cmpCur = cmpsChild[i];
        
            if (cmpCur instanceof JToolBar)
            {
                tbr = (JToolBar) cmpCur;
                break;
            }
        }
        
        if (tbr == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil tbr");
            return true;
        }
        
        // get buttons
        
        cmpsChild = tbr.getComponents();
        
        if (cmpsChild == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil tbr cmpsChild");
            return true;
        }
        
        for (int i=0; i<cmpsChild.length; i++)
        {
            Component cmpCur = cmpsChild[i];
        
            if (cmpCur instanceof JButton)
            {
                final JButton btnCur = (JButton) cmpCur;
                btnCur.setContentAreaFilled(false);
                btnCur.setBorderPainted(false);
                
                btnCur.addMouseListener(new java.awt.event.MouseAdapter()
	            {
                    public void mouseEntered(java.awt.event.MouseEvent evtMouse)
                    { 
                        if (btnCur.isEnabled())
                        {
                            btnCur.setBorderPainted(true);
                            btnCur.setContentAreaFilled(true);
                        }
                    }
                    
                    public void mouseExited(java.awt.event.MouseEvent evtMouse)
                    {
                        btnCur.setBorderPainted(false);
                        btnCur.setContentAreaFilled(false);
                    }
                    
                });
            }
        }
        
        
        // ending
        return true;
    }
}