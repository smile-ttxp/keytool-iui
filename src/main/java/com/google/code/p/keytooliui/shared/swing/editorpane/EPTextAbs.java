/*
 *
 * Copyright (c) 2001-2007 keyTool IUI Project.
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
 
 
package com.google.code.p.keytooliui.shared.swing.editorpane;
 
 /**
    known subclasses:
    . EPTextHTMLAbs
    . EPTextRTFAbs
 **/
 
 
import com.google.code.p.keytooliui.shared.awt.awtevent.*;
import com.google.code.p.keytooliui.shared.awt.awteventmulticaster.*;
import com.google.code.p.keytooliui.shared.util.eventlistener.*; 
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.text.*;
import com.google.code.p.keytooliui.shared.swing.dialog.*;
 
import javax.swing.*;
import javax.swing.text.*;

import java.awt.event.*;
import java.awt.print.*;
import java.awt.*;
import java.beans.*; // for PropertyChangeListener
import java.net.*;
import java.util.*;
 
abstract public class EPTextAbs extends JEditorPane implements
    // Serializable, // !!!!!!!!!!!!
    EPTextAbsListener,
    DFindPageListener,
    Printable,
    PropertyChangeListener, // test
    FocusListener, // test ==> to handle textSelection
    MouseListener
 {
        // --------------
    // PRIVATE STATIC
    
    private static String _s_strDlgPageNotFullyLoaded = null;
    
    // ------------------
    // STATIC INITIALIZER
    

    static
    {
        String strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".EPTextAbs" // class name
            ;
        
        String strWhere = "com.google.code.p.keytooliui.shared.swing.editorpane.EPTextAbs";
        
        try
        {
            ResourceBundle rbeResources = ResourceBundle.getBundle(strBundleFileShort, 
                Locale.getDefault());  
            
            _s_strDlgPageNotFullyLoaded = rbeResources.getString("dlgPageNotFullyLoaded");
        }
        
        catch (MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught");
        }
        
        strBundleFileShort = null;
        strWhere = null;
    }
    
    // ------
    // PUBLIC
    
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    
    public void mouseReleased(MouseEvent e)
    {
        _checkEnablingCopyEvent();   
    }
    
    public void scrollToReference(String str)
    {
        String strMethod = "scrollToReference(str)";
        
        if (str == null)
        {
            MySystem.s_printOutWarning(this, strMethod, "nil str, aborting");
            return;
        }
        
        try
        {
            super.scrollToReference(str);
        }
        
        catch(java.lang.NullPointerException excNullPointer)
        {
            excNullPointer.printStackTrace();
            MySystem.s_printOutWarning(this, strMethod, "excNullPointer caught, aborting");
            return;
        }
    }
    
    
    
    /*
     *still a memory leak in JEditorPane, JTextPane, tryin to fix up
     */
    public void destroy()
    {
        String strMethod = "destroy()";
        MySystem.s_printOutTrace(this, strMethod, "...");
    
        
        /*
         * cannot use this!, seems to cause java.util.EmptyStackException
        Document doc = getDocument();
        
        try
        {
           doc.remove(0,doc.getLength());
        }
        
        catch(Exception exc) 
        {
            exc.printStackTrace();
            MySystem.s_printOutWarning(this, strMethod, "exc caught, ignoring");
            
        }
        doc=null;
        */
        
        this._cmpFrameOwner = null;
    
        this._url = null;
        
        
        _removeListeners();
        
        /*if (this._tts != null)
        {
            this._tts.stop();
            this._tts = null;
        }*/

        if (this._dfp != null)
        {
            this._dfp.destroy();    
            this._dfp = null;
        }
                    
        if (this._shlCur != null)
        {
            this._shlCur.destroy();
            this._shlCur = null;
        }
        
      
       
    }
    
    public boolean doTextSearch()
    {
        String strMethod = "doTextSearch()";
        
        if (this._dfp == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._dfp");
            return false;
        }
        
                
        if (! this._blnPageLoaded)
        {
            MySystem.s_printOutTrace(this, strMethod, "! this._blnPageLoaded");
            
            if (! com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showConfirmDialog(
                this._cmpFrameOwner, _s_strDlgPageNotFullyLoaded))
	        {
	            // action cancelled
	            MySystem.s_printOutTrace(this, strMethod, "! blnPageLoaded, action cancelled");
	            return true;
	        }
             
            //return false;
        }

        Toolkit.getDefaultToolkit().beep();
        this._dfp.setVisible(true);
        return true;
    }
    
    public boolean doText2Speech()
    {
        String strMethod = "doText2Speech()";
        
        MySystem.s_printOutFlagDev(this, strMethod, "IN COMMENTS");
        
        
        /**Document doc = getDocument();
        
        MySystem.s_printOutTrace(this, strMethod, "doc.getClass().toString()=" + doc.getClass().toString());
        
        
        String strText = null;
        
        // ----
        try
        {
            strText = doc.getText(0, doc.getLength());
            System.out.println("\n\n");
            System.out.println("strText=" + strText);
            System.out.println("\n\n");
        }
        
        catch(javax.swing.text.BadLocationException excBadLocation)
        {
            excBadLocation.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excBadLocation caught");
            return false;
        }
        
        if (this._tts != null)
        {
            this._tts.stop();
            this._tts = null;
        }
        
        this._tts = new com.google.code.p.keytooliui.speech.freetts.MyFreeTTS(strText);
        
        this._tts.start();
        **/
    
        // --
        return true;
    }
    
    public boolean doTextCopy()
	{
	    String strMethod = "doTextCopy()";
	    
	    String strData = this.getSelectedText();

	    if (strData == null)
	    {
	        MySystem.s_printOutError(this, strMethod, "nil strData");
	        return false;
	    }
	    
	    java.awt.datatransfer.StringSelection ssnCur = new java.awt.datatransfer.StringSelection(strData);
	 
	    
	    java.awt.datatransfer.Clipboard cbdSystem = Toolkit.getDefaultToolkit().getSystemClipboard();
	    cbdSystem.setContents(ssnCur,ssnCur);
	    
	    return true;
	}
	
	public boolean doTextSelectAll()
	{	    
	    if (! isFocusOwner())
	    {
	        requestFocus();
	    }
	    
	    
	    this.selectAll();
	    _checkEnablingCopyEvent(); 
        return true;
	}
    
    public void updateTreeUI()
    {   
        if (this._dfp != null)
            javax.swing.SwingUtilities.updateComponentTreeUI(this._dfp);
    }
    
   
    
    public void focusGained(FocusEvent evtFocus)
    {
	    this._blnGotFocusFirst = true;
	}
	
    public void focusLost(FocusEvent evtFocus)
    {
        String strMethod = "focusLost(evtFocus)";
	    
        DefaultHighlighter dhr = (DefaultHighlighter) this.getHighlighter();
	    
        if (dhr != null)
        {
            dhr.removeAllHighlights();
            repaint();
        }	    

        if (! _saveSelectionIfAny())
        {
            MySystem.s_printOutExit(this, strMethod, "failed");
        }
    }
    
    public boolean setTextSelectionColor(Color col)
    {
        String strMethod = "setTextSelectionColor(col)";
        MySystem.s_printOutTrace(this, strMethod, "col=" + col);
        
        setSelectionColor(col);
        
        DefaultHighlighter dhr = (DefaultHighlighter) this.getHighlighter();
        boolean blnHighlight = false;
        
        if (dhr != null)
        {
            blnHighlight = true;
            Highlighter.Highlight[] hlts = dhr.getHighlights();
            IRange[] rngs = new IRange[hlts.length];
            
            for (int i=0; i<hlts.length; i++)
            {
                rngs[i] = new IRange(hlts[i].getStartOffset(), hlts[i].getEndOffset());
                
                if (! rngs[i].init())
                {
                    MySystem.s_printOutError(this, strMethod, "failed");
                    return false;
                }
            }
            
            // ----
            dhr.removeAllHighlights();
            DefaultHighlighter.DefaultHighlightPainter dhp = new DefaultHighlighter.DefaultHighlightPainter(col); 
            
            for (int i=0; i<rngs.length; i++)
            {
                try
                {
                    dhr.addHighlight(rngs[i].getStart(), rngs[i].getEnd(), dhp);
                }
                
                catch(javax.swing.text.BadLocationException excBadLocation)
                {
                    excBadLocation.printStackTrace();
                    MySystem.s_printOutError(this, strMethod, "excBadLocation caught");
                    return false;
                }
            }                    
            
        }
        
        //_updateSelection();
        String strSelection = _getSelectedText();
        
        if (strSelection==null && !blnHighlight)
            return true;
            
        repaint();
        return true;
    }
    
    /**
        should highlight all occurrences of the text inside the pane!
    **/
    
    public boolean setPageTextOccurrenceHighlighted(
        String strText, 
        boolean blnMatchCase, 
        boolean blnMatchWord, 
        int intOccurrenceExpected)
    {
        String strMethod = "setPageTextOccurrenceHighlighted(...)";
        /*MySystem.s_printOutError(this, strMethod, "IN PROGRESS ..., returning true");
        return true;
        */

        if (strText == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strText");
            return false;
        }
        
        if (intOccurrenceExpected < 1)
        {
            MySystem.s_printOutError(this, strMethod, "wrong value, intOccurrenceExpected=" + intOccurrenceExpected);
            return false;
        }
        
        // using a local boolean, in order to get (fixed) the same value in this method, during comparison
        
        boolean blnPageLoaded = this._blnPageLoaded;
        
        if (! blnPageLoaded)
        {
            MySystem.s_printOutTrace(this, strMethod, "! blnPageLoaded");
            
            if (! com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showConfirmDialog(
                this._cmpFrameOwner, _s_strDlgPageNotFullyLoaded))
	        {
	            // action cancelled
	            MySystem.s_printOutTrace(this, strMethod, "! blnPageLoaded, action cancelled");
	            return true;
	        }
             
            //return false;
        }
        
        Document doc = getDocument();
        
        if (doc == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil doc");
            return false;
        }
        
        
        JDocText2ListOccurr ohl = new JDocText2ListOccurr(doc, strText, blnMatchCase, blnMatchWord);
        
        if (! ohl.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        java.util.List<IRange> lst = ohl.getLstSelection();
        
        if (lst == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil lst");
            return false;
        }
        
        if (lst.size() < 1) // !!! rather show a warning
        {
            if (blnPageLoaded)
            {
                MySystem.s_printOutError(this, strMethod, "lst.size() < 1, blnPageLoaded");
                return false;
            }
            
            else
            {
                MySystem.s_printOutWarning(this, strMethod, "!blnPageLoaded, lst.size() < 1, intOccurrenceExpected=" + intOccurrenceExpected);
                return true;
            }
        }
        
        if (lst.size() != intOccurrenceExpected) // !!! rather show a warning
        {
            if (blnPageLoaded)
            {
                MySystem.s_printOutError(this, strMethod, "lst.size() != intOccurrenceExpected, blnPageLoaded, lst.size()=" + lst.size() + ", intOccurrenceExpected=" + intOccurrenceExpected);
                return false;
            }
            
            else
            {
                MySystem.s_printOutWarning(this, strMethod, "!blnPageLoaded, lst.size() != intOccurrenceExpected");
                System.out.println("lst.size()=" + lst.size());
                System.out.println("intOccurrenceExpected=" + intOccurrenceExpected);
                System.out.println("this._url.toString()=" + this._url.toString());
            }
        }
        
        // --
        
        DefaultHighlighter dhr = new DefaultHighlighter();   
        Color col = this.getSelectionColor();
        DefaultHighlighter.DefaultHighlightPainter dhp = new DefaultHighlighter.DefaultHighlightPainter(col);   
        this.setHighlighter(dhr);   
        
        // --
        
        Iterator itr = lst.iterator();
        
        while (itr.hasNext())
        {
            IRange rng = (IRange) itr.next();
            
            try
            {
                dhr.addHighlight(rng.getStart(), rng.getEnd(), dhp); 
            }
            
            catch(javax.swing.text.BadLocationException excBadLocation)
            {
                excBadLocation.printStackTrace();
                MySystem.s_printOutError(this, strMethod, "excBadLocation caught");
                return false;
            }
        }
        
        return true;
       
    }
    
    // trying to catch an exception in jvm 1.3.1 / WinNT
    // also with jvm 1.5.0 / Win2k
    public Dimension getPreferredSize() 
    {
        String strMethod = "getPreferredSize()";
        
        Dimension dim = null;
        
        try
        {
            dim = super.getPreferredSize();
        }
        
        catch(ArrayIndexOutOfBoundsException excArrayIndexOutOfBounds)
        {
            //excArrayIndexOutOfBounds.printStackTrace();
            MySystem.s_printOutWarning(this, strMethod, "excArrayIndexOutOfBounds caught, ignoring, returning new Dimension(400, 400)");
            return new Dimension(400, 400);
        }
        
        catch(NullPointerException excNullPointer)
        {
            MySystem.s_printOutWarning(this, strMethod, "excNullPointer caught, ignoring, returning new Dimension(400, 400)");
            return new Dimension(400, 400);
        }
        
        
        
        //if (dim.width<50 || dim.height<50)
          //  return new Dimension(400, 400);
        System.out.println("dim.width=" + dim.width + ", dim.height=" + dim.height);
        
        return dim;
    }
    
    // !! not yet in use !!
    public void propertyChange(PropertyChangeEvent evtPropertyChange)
    {
        String strMethod = "propertyChange(evtPropertyChange)";
        
        if (evtPropertyChange.getPropertyName().equals("page"))
        {
            // Now it's fully loaded
            this._blnPageLoaded = true;
            MySystem.s_printOutFlagDev(this, strMethod, ">>>> page loaded, this._url.toString()=" + this._url.toString());
            System.out.println("<< OK >>: evtPropertyChange.getPropertyName()=" + evtPropertyChange.getPropertyName());
            return;
        }
        System.out.println("!! NO !!: evtPropertyChange.getPropertyName()=" + evtPropertyChange.getPropertyName());
        //MySystem.s_printOutTrace(this, strMethod, "!!!! evtPropertyChange.getPropertyName()=" + evtPropertyChange.getPropertyName());
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (this._dfp == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._dfp");
            return false;
        }
        
        if (! this._dfp.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
   
        // -----
        
        if (this._url == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._url");
            return false;
        }
        
        
        // BEGIN -------------------
        // trying to fix up a bug, in JEditorPane$PageLoader.run:450 (jdk1.2.2)
        // NullPointerException at javax.swing.text.ParagraphView.loadChildren:129
        
        if (getParent() == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil getParent()");
            return false;
        }
        
	    
        // ORI
        
        boolean blnLoop = true;
        MySystem.s_printOutTrace(this, strMethod, "!!!! starting loop");
        
        while (blnLoop)
        {
            // !!!!!!!!!!
            //blnLoop = false;
            
            
            
            
            try
            {
                setPage(this._url); 
            }
            
            catch(UnknownHostException excUnknownHost)
            {
                excUnknownHost.printStackTrace();
                MySystem.s_printOutError(this, strMethod, "excUnknownHost caught");
                
                // tempo
                String strBody = "Failed to load page:";
                strBody += "\n";
                strBody += "  " + this._url.toString();
                strBody += "\n\n";
                strBody += "Please make sure you are connected to the Internet,";
                strBody += "\n";
                strBody += "then try again by selecting \"OK\" button below.";
                                
               
                
                if (! com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showConfirmDialog(
                    this._cmpFrameOwner, strBody))
	            {
	                // action cancelled
	                MySystem.s_printOutTrace(this, strMethod, "action cancelled");
	                return false;
	            }
                
                continue;
            }
            
            catch(Exception exc)
            {
                exc.printStackTrace();
                MySystem.s_printOutError(this, strMethod, "exc caught");
                return false;
            }
            
            blnLoop = false;
        }
       
         MySystem.s_printOutTrace(this, strMethod, "!!!! stopped loop");
        // END ---------
        
        setEditable(false);
        
        return true;
    }
    
    public synchronized void addDFindPageListener(DFindPageListener dfpListener)
	{
	    if (this._dfpListenerThis == null)
                this._dfpListenerThis = DFindPageEventMulticaster.add(this._dfpListenerThis, dfpListener);
	}
  
	public synchronized void removeDFindPageListener(DFindPageListener dfpListener)
	{
	    if (this._dfpListenerThis != null)
	    {
                this._dfpListenerThis = DFindPageEventMulticaster.remove(this._dfpListenerThis, dfpListener);
	        this._dfpListenerThis = null;
	    }
	}
	
	public void findNext(DFindPageEvent evtDFindPage)
	{	    
	    String strMethod = "findNext(evtDFindPage)";	    
	    
	    String strText = evtDFindPage.getText();
	    
	    if (strText == null)
	        MySystem.s_printOutExit(this, strMethod, "nil strText");
	    	    
	    DefaultHighlighter dhr = (DefaultHighlighter) this.getHighlighter();
	    
	    if (dhr != null)
	    {
	        dhr.removeAllHighlights();
	    }
	    
	    if (this._shlCur == null)
	    {
	        this._shlCur = new DocPageTextSearch(this._cmpFrameOwner, getDocument(), strText, evtDFindPage.getMatchWord(), evtDFindPage.getMatchCase());
	        
	        if (! this._shlCur.init())
	            MySystem.s_printOutExit(this, strMethod, "failed");
	    
	        // what about if no texts matches
	    }
	    
	    else
	    {
	        if (! this._shlCur.sameConfig(strText, evtDFindPage.getMatchWord(), evtDFindPage.getMatchCase()))
	        {
	            this._shlCur.destroy();
	            this._shlCur = new DocPageTextSearch(this._cmpFrameOwner, getDocument(), strText, evtDFindPage.getMatchWord(), evtDFindPage.getMatchCase());
	            
	            if (! this._shlCur.init())
	                MySystem.s_printOutExit(this, strMethod, "failed");
	            
	            // what about if no texts matches
	        }
                
                
	    }
	    
	    // assuming there are texts matching !!!!!!!!! to be rearranged
	    
	    MyStringSelection ssnCur = null;
	    
	    if (this.getSelectedText() != null)
	    {
	        int intStartCur = this.getSelectionStart();
	        int intEndCur = this.getSelectionEnd();
	        ssnCur = new MyStringSelection(intStartCur, intEndCur);
	        
	        if (! ssnCur.init())
	            MySystem.s_printOutExit(this, strMethod, "failed");
	    }
	    
	    MyStringSelection ssn = this._shlCur.getNext(evtDFindPage.getDirectionDown(), ssnCur);
	    
	    if (ssn == null)
	    {
	        MySystem.s_printOutTrace(this, strMethod, "nil ssn, returning");
	        return;
	    }
	    
	    // selecting!
	    
	    MySystem.s_printOutTrace(this, strMethod, "ssn.getStart()=" + ssn.getStart() + ", ssn.getEnd()=" + ssn.getEnd());
	    this.select(ssn.getStart(), ssn.getEnd()); 
	    
	    
	    
	    //    bugId # 4
	      //  !! QUICK FIX !!
	    
	    if (! this._blnGotFocusFirst)
	    {
	        MySystem.s_printOutTrace(this, strMethod, "! this._blnGotFocusFirst");
	        
	        if (dhr == null)
	        {
	            dhr = new DefaultHighlighter();
	        }
	        
	        Color col = this.getSelectionColor();
            DefaultHighlighter.DefaultHighlightPainter dhp = new DefaultHighlighter.DefaultHighlightPainter(col);   
            this.setHighlighter(dhr);   
            
            // --
                
            try
            {
                dhr.addHighlight(ssn.getStart(), ssn.getEnd(), dhp); 
            }
                
            catch(javax.swing.text.BadLocationException excBadLocation)
            {
                excBadLocation.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, "excBadLocation caught");  
            }
	    }

	    
	    _checkEnablingCopyEvent();
	   
	}
	
    
    public boolean doPageUrlTextPrint()
    {
        String strMethod = "doPageUrlTextPrint()";
        
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
        PrinterJob job = PrinterJob.getPrinterJob(); 
        job.setPrintable(this); 
        boolean blnOk = true;
        
        if (job.printDialog())
        { 
            try
            { 
                job.print(); 
            } 
            
            catch (Exception exc)
            { 
                exc.printStackTrace();
                MySystem.s_printOutError(this, strMethod, "exc caught");
                //setCursor(Cursor.getDefaultCursor());
                
                blnOk = false;
            } 
        } 
        
        // else the user has cancelled the printing (AS FOR INFO)
        
        setCursor(Cursor.getDefaultCursor()); 
        return blnOk;
    }
    
    /** 
     * The method @print@ must be implemented for @Printable@ interface. 
     * Parameters are supplied by system. 
     */ 
    public int print(Graphics g, PageFormat pft, int intPageIndex) 
        throws PrinterException
    { 
        String strMethod = "print(g, pft, int intPageIndex)";
        
        Graphics2D g2 = (Graphics2D)g; 
        g2.setColor(Color.black);    //set default foreground color to black 
        //for faster printing, turn off double buffering 
        //RepaintManager.currentManager(this).setDoubleBufferingEnabled(false); 
        Dimension d = this.getSize();    //get size of document 
        
        double dblPanelWidth  = d.width;    //width in pixels 
        double dblPanelHeight = d.height;   //height in pixels 
        double dblPageHeight = pft.getImageableHeight();   //height of printer page 
        double dblPageWidth  = pft.getImageableWidth();    //width of printer page 
        double dblScale = dblPageWidth/dblPanelWidth; 
        
        int intPageNb = (int)Math.ceil(dblScale * dblPanelHeight / dblPageHeight); 
        
        //make sure not print empty pages 
        if(intPageIndex >= intPageNb)
        { 
            return Printable.NO_SUCH_PAGE; 
        } 
        
        //shift Graphic to line up with beginning of print-imageable region 
        g2.translate(pft.getImageableX(), pft.getImageableY()); 
        //shift Graphic to line up with beginning of next page to print 
        g2.translate(0f, -intPageIndex*dblPageHeight); 
        //dblScale the page so the width fits... 
        g2.scale(dblScale, dblScale); 
        this.paint(g2);   //repaint the page for printing 
 
        return Printable.PAGE_EXISTS; 
    }
    
    public synchronized void addEPTextAbsListener(EPTextAbsListener pepListener)
	{
	    if (this._pepListenerThis == null)
            this._pepListenerThis = EPTextAbsEventMulticaster.add(this._pepListenerThis, pepListener);
	}
  
	public synchronized void removeEPTextAbsListener(EPTextAbsListener pepListener)
	{
	    if (this._pepListenerThis != null)
	    {
                this._pepListenerThis = EPTextAbsEventMulticaster.remove(this._pepListenerThis, pepListener);
	        this._pepListenerThis = null;
	    }
	}
	
	public void setEnabledCopy(EPTextAbsEvent evt)
	{	    
	    if (this._pepListenerParent != null)
                this._pepListenerParent.setEnabledCopy(evt);
	    
	}
    
    // ---------
    // PROTECTED
    
    
    
    protected EPTextAbs(EPTextAbsListener pepListenerParent, Component cmpFrameOwner, Color colPageTextSelection, URL url, StyledEditorKit sek)
    {
        String strMethod = "EPTextAbs(...)";
        
        this._pepListenerParent = pepListenerParent;
        this._cmpFrameOwner = cmpFrameOwner;

        this._url = url;
        
        // --
        
        setEditorKit(sek);
        
        
        // beg try to fix up mlk
        //Document doc = getDocument();
        
        Document doc = getEditorKit().createDefaultDocument();
        
        
        
        
        
        
        // end 
        
        
        
        
        // ----
        
        // The Document class does not yet handle charset's properly. 
        // IMPORTANT MEMO: w/o the line below, will throw an exception
        //doc.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
        
        // --
        
        if (colPageTextSelection == null)
            MySystem.s_printOutExit(this, strMethod, "nil colPageTextSelection");
        
        setSelectionColor(colPageTextSelection); 
        
        // ------------
        
        _addListeners();

        this._dfp = new DFindPage(this._dfpListenerThis, cmpFrameOwner);  
    }
    
    // -------
    // PRIVATE
    
    //private com.google.code.p.keytooliui.speech.freetts.MyFreeTTS _tts = null;
    
    // listeners
    private DFindPageListener _dfpListenerThis = null;
    private EPTextAbsListener _pepListenerThis = null;
    private EPTextAbsListener _pepListenerParent = null;
    
     /**
        trying to fix up bug in selection not hightlighted
    **/
    private boolean _blnGotFocusFirst = false;
    
    // tempo, trying to fix up bug: highlight occurrences (nbItemFounds != nbItemExpected)
    private boolean _blnPageLoaded = false;

    private Component _cmpFrameOwner;

    private URL _url = null;
    
    
    
    
    private DocPageTextSearch _shlCur = null; // !!!!!!!!!
    
    private DFindPage _dfp = null;
    
    /**
        should be called by:
        . mouseReleased
        . doSelectAll
        . ?? fin
        . ?? destroy
    **/
    private void _checkEnablingCopyEvent()
    {
        //String strMethod = "_checkEnablingCopyEvent()"; 
        
        /**boolean blnIsSelectedTextNew = false;
        
        if (this._getSelectedText() != null)
        {
            blnIsSelectedTextNew = true;
        }
        
        if (this._blnIsSelectedText && blnIsSelectedTextNew)
            return;
            
        if (!this._blnIsSelectedText && !blnIsSelectedTextNew)
            return;
            
        this._blnIsSelectedText = blnIsSelectedTextNew;
        
        
        // generate an event in order to allow/unallow copyToClipboard
        EPTextAbsEvent evt = new EPTextAbsEvent(this, EPTextAbsEvent.EPTEXTABSEVENT_SETENABLEDCOPY, this._blnIsSelectedText);
        
        if (this._pepListenerThis != null)
            this._pepListenerThis.setEnabledCopy(evt);
        **/
        
        boolean blnIsSelectedText = false;
        
        if (this._getSelectedText() != null)
        {
            blnIsSelectedText = true;
        }
        
        // generate an event in order to allow/unallow copyToClipboard
        EPTextAbsEvent evt = new EPTextAbsEvent(this, EPTextAbsEvent.EPTEXTABSEVENT_SETENABLEDCOPY, blnIsSelectedText);
        
        if (this._pepListenerThis != null)
            this._pepListenerThis.setEnabledCopy(evt);
    }
    
    private String _getSelectedText()
    {
        String strMethod = "_getSelectedText()";
        
        String strSelectedText = null;
	    
	    try
	    {
	        strSelectedText = getSelectedText();  
	    }
	    
	    catch(Exception exc)
	    {
	        exc.printStackTrace();
	        MySystem.s_printOutExit(this, strMethod, "exc caught");       
	    }
	    
	    if  (strSelectedText == null)
	    {
	        return null;    
	    }
	    
	    if (strSelectedText.trim().length() < 1)
	    {
	        return null;
	    }
	    
	    return strSelectedText;
    }
    
    private boolean _saveSelectionIfAny()
    {
        String strMethod = "_saveSelectionIfAny()";
        
        if (_getSelectedText() == null)
            return true;
        
	    
        int intStartCur = this.getSelectionStart();
	    int intEndCur = this.getSelectionEnd();
	    MyStringSelection ssnCur = new MyStringSelection(intStartCur, intEndCur);
	    
	    if (! ssnCur.init())
	    {
	        MySystem.s_printOutError(this, strMethod, "failed");
	        return false;
	    }
	    
	    ssnCur.setEditorPane(this);
	    EventQueue.invokeLater(ssnCur);
    
        return true;
    }
    
    
    private void _addListeners()
    {
        addFocusListener(this);
        addDFindPageListener(this);
        addEPTextAbsListener(this);
        addPropertyChangeListener(this);
        addMouseListener(this);
    }
    
    private void _removeListeners()
    {
        removeFocusListener(this);
        removeDFindPageListener(this);
        removeEPTextAbsListener(this);
        removePropertyChangeListener(this);
        removeMouseListener(this);
        
        this._pepListenerParent = null;
    }
    
    
 }