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
 
 
package com.google.code.p.keytooliui.shared.swing.dialog;

/**
     note: OK means "call to destroy" (dispose())

    known subclasses:
    . OK DListNoteAbstract
    . OK DViewString            !!!!!!!!!!!! no call to destroy !!!!!!!!!!
    . OK DCacheNote
    . OK DDocInfo               !!!!!!!!!!!! no call to destroy !!!!!!!!!!
    . OK DDplOnlineDocAbs    !!!!!!!!!!!! no call to destroy !!!!!!!!!!
    . OK DSearchingProgress
    . OK DEditorAbstract    
                                !!!!!!!!!!!! sub-subclass DEditorJarProjTextHtml no call to destroy !!!!!!!!!!DEditorJarProjTextHtml
    . OK DSelectString
    . OK DFindAbstract
    . OK DIImage                !!!!!!!!!!!! no call to destroy !!!!!!!!!!
    . OK DLoadingAbs
    . OK DRangeAbstract
    . OK DViewerFileTextAbs    !!!!!!!!!!!! no call to destroy !!!!!!!!!!
    . OK DHelpAboutAppli        !!!!!!!!!!!! no call to destroy !!!!!!!!!!
    
    . OK DWChoiceAbs
    
    . OK DCreateDirAbs
    . OK DSelRlAbs               !!! sub-subclasses: ?no-call-to-destroyy? DSelRlUrlRemTextHtml / DSelRlUrlSysDocRcr !!
    . OK DSelectHLocalJarAnchor
    
    . OK DViewCheckHtmlAbstract
    . OK DViewFLocalSysAbstract
    . OK DViewSourceFileTextAbs
    . OK DJarProjAbs
    

    includes the escape feature
**/


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

abstract public class DEscapeAbstract extends JDialog implements
    WindowListener
{
    // ---------------
    // ABSTRACT PUBLIC
    
    abstract public boolean init();
    
    
    // ------
    // PUBLIC
    
    public void setVisible(boolean bln)
    {
        if (bln)
            _prepare2show();
            
        super.setVisible(bln);
    }
    
    
    /**
        may be overwritten in subclasses, in case of dialog of type "choice"
        if so, disabling cancel, force to wait till user clicks on a button
    **/
    public void windowClosing(WindowEvent evt)
    {
        _cancel_();
    }
    
    public void windowActivated(WindowEvent e) {} 
    public void windowClosed(WindowEvent e) {}  
    public void windowDeactivated(WindowEvent e) {}  
    public void windowDeiconified(WindowEvent e) {}  
    public void windowIconified(WindowEvent e) {}  
    public void windowOpened(WindowEvent e) {}   

    
    // --
    
    public void destroy()
    {
        /* BEGIN bug fix: jdk1.3.1/Linux RH 7.2/KDE, december 25, 2001
           second attempt to show dialog causes the appli to hang!
           iconifying the main appli, followed by uniconifying will allow to show up the dialog and unfreeze the appli

           related javasoft's bug parade: Bug ID 4509276

           a simple call to "dispose()" allows to fix up this bug!
        */
        
       
        
        
        dispose();
        // END bug fix: jdk1.3.1/Linux RH 7.2/KDE
        
         
    }
    
    
    // ---------
    // PROTECTED
    
    protected DEscapeAbstract()
    {
        this((Frame)null, false);
    }
  
    protected DEscapeAbstract(Frame owner)
    {
        this(owner, false);
    }
    
    protected DEscapeAbstract(Frame owner, boolean modal)
    {
        this(owner, null, modal);
    }
    
    protected DEscapeAbstract(Frame owner, String title)
    {
        this(owner, title, false);     
    }
    
    
    
    protected DEscapeAbstract(Dialog owner)
    {
        this(owner, false);
    }
    
    protected DEscapeAbstract(Dialog owner, boolean modal)
    {
        this(owner, null, modal);
    }
    
    protected DEscapeAbstract(Dialog owner, String title)
    {
        this(owner, title, false);     
    }
    
    // -----
    
    protected DEscapeAbstract(Frame owner, String title, boolean modal)
    {
        super(owner, title, modal);
        _initialize();
    }
    
    protected DEscapeAbstract(Dialog owner, String title, boolean modal)
    {
        super(owner, title, modal);
        _initialize();
    }
    
    // ----

    protected JRootPane createRootPane()
    {
        ActionListener actionListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                //setVisible(false);
                _cancel_();
            }
        };
    
        JRootPane rootPane = new JRootPane();
        KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        rootPane.registerKeyboardAction(actionListener, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
        return rootPane;
    }
    
    protected void _cancel_()
    {
        Window wnd = getOwner();
        setVisible(false);
        
        if (wnd != null)
        {    
            wnd.paintComponents(wnd.getGraphics());
            //wnd.repaint();
        }
        
        // BEG MODIF: jan 29, 2001: fix up bug with Linux RH7.2 / JVM 1.3.1
        dispose();
        // END MODIF: jan 29, 2001: fix up bug with Linux RH7.2 / JVM 1.3.1
    }
    
    // -------
    // PRIVATE
    
    private void _prepare2show()
    {
        pack();
        
        if (getOwner() != null)
            setLocationRelativeTo(getOwner());
    }
    
    private void _initialize()
    {
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        
        addWindowListener(this);
        
        /*addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent evt)
            {
                _cancel_();
            }
        });
        */
    }
}

