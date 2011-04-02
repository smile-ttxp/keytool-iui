package com.google.code.p.keytooliui.ktl.swing.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.google.code.p.keytooliui.ktl.swing.panel.PTblEntPKTCAbs;
//import com.google.code.p.keytooliui.ktl.swing.panel.PTblEntPKTCShowAll;
import com.google.code.p.keytooliui.ktl.swing.panel.PTblEntSKAbs;
//import com.google.code.p.keytooliui.ktl.swing.panel.PTblEntSKShowAll;
import com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstPkcs12;
import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.swing.button.BESExit24;
import com.google.code.p.keytooliui.shared.swing.button.BESTip24;
import com.google.code.p.keytooliui.shared.swing.button.BEnabledState;
import com.google.code.p.keytooliui.shared.swing.dialog.DEscapeAbstract;
import com.google.code.p.keytooliui.shared.swing.dialog.DPasswordOpen;
import com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract;
import com.google.code.p.keytooliui.shared.util.jarsigner.UtilCrtX509;

abstract public class DTblsKstAbs extends DEscapeAbstract implements
    ListSelectionListener,
    ActionListener,
    MouseListener // listener for mouse event on table
{
    // --------------------
    // final static private
    
    final static private String _STR_BODYBUTTONUSAGE = "Usage:\n  Mouse-right click on entry's row to display popup menu.";
    
    // ATTN: should not have the same value!
    final static private String _STR_viewEntryTCCrt = "View certificate ...";
    final static private String _STR_viewEntryPKCrtChain = "View certificates chain ...";
    final static private String _STR_viewEntrySKInfo = "View infos ...";

    // ------
    // public

    public void mouseClicked(MouseEvent e) 
    {
        if (e.getButton() == MouseEvent.BUTTON3)
        {
            _doMouseRightClickRow(e);
        }
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    
    public void valueChanged(ListSelectionEvent evtListSelection)
    {
        String strMethod = "valueChanged(evtListSelection)";
        
        if (evtListSelection.getValueIsAdjusting())
            return;
        
        ListSelectionModel lsm = (ListSelectionModel) evtListSelection.getSource();
        
        if (lsm == this._pnlTablePKTC_.getTableSelectionModel())
        {
            _valueChangedPKTC(lsm);
            return;
        }
        
        if (lsm == this._pnlTableSK_.getTableSelectionModel())
        {
            _valueChangedSK(lsm);
            return;
        }
        
        
        //_updateButtonEntryPKTC();
        // Q: what about SK?
    }
    
    
    
    
    
    public void actionPerformed(ActionEvent evtAction) 
    {
        String strMethod = "actionPerformed(evtAction)";
        
        if (evtAction.getSource() instanceof JMenuItem)
        {
            JMenuItem mimSource = (JMenuItem) evtAction.getSource();
            
            if (mimSource.getText().compareTo(DTblsKstAbs._STR_viewEntryTCCrt) == 0)
            {
                this._viewEntryTCCrt();
                return;
            }
            
            if (mimSource.getText().compareTo(DTblsKstAbs._STR_viewEntryPKCrtChain) == 0)
            {
                this._viewEntryPKCrtsChain();
                return;
            }
            
            if (mimSource.getText().compareTo(DTblsKstAbs._STR_viewEntrySKInfo) == 0)
            {
                this._viewEntrySKInfo();
                return;
            }
            
            // ending
            MySystem.s_printOutExit(this, strMethod, "uncaught mimSource");
        }

        
        if (evtAction.getSource() instanceof JButton)
        {
            JButton btnSource = (JButton) evtAction.getSource();
        
            if (btnSource == this._btnExit_)
            {
                 super._cancel_();
                 return;
            }
            
            if (btnSource == this._btnTip)
            {
                String strBody = new String();
                
                if (this._strPathAbs_ != null)
                {
                    strBody += "Kestore location:";
                    strBody += "\n" + "  " + this._strPathAbs_;
                    strBody += "\n\n";
                }
                
                strBody += this._strBodyButtonUsage;
                
                OPAbstract.s_showDialogInfo(this, strBody);
                 return;
            }

            MySystem.s_printOutExit(this, strMethod, "uncaught btnSource");
        }
        
        MySystem.s_printOutExit(this, strMethod, "uncaught evtAction.getSource()");
    }
    
    public void destroy()
    {
        super.destroy();
        
        if (this._pnlTablePKTC_ != null)
        {
            this._pnlTablePKTC_.destroy();
            this._pnlTablePKTC_ = null;
        }
        
        if (this._pnlTableSK_ != null)
        {
            this._pnlTableSK_.destroy();
            this._pnlTableSK_ = null;
        }
        
        if (this._btnExit_ != null)
        {
            this._btnExit_.destroy();
            this._btnExit_ = null;
        }
        
        if (this._btnTip != null)
        {
            this._btnTip.destroy();
            this._btnTip = null;
        }

        this._pnlContentsAll_ = null;
        this._strsAliasPKTC_ = null;
        this._strsAliasSK_ = null;
        this._kstOpen_ = null;
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        this._pnlTables.setLayout(new BoxLayout(this._pnlTables, BoxLayout.Y_AXIS));
        this._pnlTables.setBorder(new javax.swing.border.EmptyBorder(4, 4, 4, 4));
       
        
        
        // popup menus
        _createPopupMenuPK();
        _createPopupMenuTC();
        _createPopupMenuSK();
        
        _addToolbarButtonUsage();
        
        // ----
        
        if (! this._pnlTablePKTC_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (! this._pnlTableSK_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        this._pnlTables.add(this._pnlTablePKTC_);
        this._pnlTables.add(this._pnlTableSK_);
        
        // ----
        
        
        Dimension dim = new Dimension(800, this._pnlTables.getPreferredSize().height);
        this._pnlTables.setMinimumSize(dim);
        this._pnlTables.setPreferredSize(dim);
        
        // ----
        
        this._pnlContentsAll_.setBorder(new javax.swing.border.EmptyBorder(4, 4, 4, 4));
        
        this._pnlContentsAll_.add(this._pnlTables, BorderLayout.CENTER);
        
        
       
        
        getContentPane().add(this._pnlContentsAll_, BorderLayout.CENTER);
        
        getRootPane().setDefaultButton(this._btnExit_);

        // ----
        return true;
    }
    
    // ---------
    // protected
    
    protected KeyStore _kstOpen_ = null;
    protected PTblEntPKTCAbs _pnlTablePKTC_ = null;
    protected PTblEntSKAbs _pnlTableSK_ = null;

     protected String _strPathAbs_ = null;
    
    protected Boolean[] _boosIsTCEntryPKTC_ = null;
    protected String[] _strsAliasPKTC_ = null;
    protected String[] _strsAliasSK_ = null;
    protected int _intSelectionRowCurPKTC_ = -1;
    protected int _intSelectionRowCurSK_ = -1;
    
    protected JToolBar _tbr_ = null;
    protected JPanel _pnlContentsAll_ = null;
    protected BEnabledState _btnExit_ = null;
    
    
    protected DTblsKstAbs(
        Component cmpFrameOwner, 
        String strTitleThis,
        KeyStore kseLoaded,
        String strPathAbs,
        String strBodyButtonUsage)
    {
        super(
            (Frame) cmpFrameOwner, 
            System.getProperty("_appli.title") + " - " + strTitleThis, // title
            true // true ==> modal=true
                );
        
        this._kstOpen_ = kseLoaded;
        this._strPathAbs_ = strPathAbs;
        
        if (strBodyButtonUsage != null)
            this._strBodyButtonUsage = strBodyButtonUsage;
        else
            this._strBodyButtonUsage = DTblsKstAbs._STR_BODYBUTTONUSAGE;
        
        this._pnlTables = new JPanel();
        this._btnExit_ = new BESExit24((ActionListener) this);
        this._btnTip = new BESTip24((ActionListener) this);
        this._pnlContentsAll_ = new JPanel();
        this._pnlContentsAll_.setLayout(new BorderLayout());
        
        // --
        
        this._tbr_ = new JToolBar(System.getProperty("_appli.title"));
        
        
        
        
        // ----
        // layouts
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(this._tbr_, BorderLayout.NORTH);
        
        
        
    }
    
    
    
    
    
    
    
    protected boolean _addToolbarButtonExit_()
    {
        String strMethod = "_addButtonExit_()";
        
        this._tbr_.add(Box.createHorizontalGlue());
        this._tbr_.add(Box.createVerticalGlue());
        
        if (this._btnExit_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._btnExit_");
            return false;
        }
        
        this._tbr_.add(this._btnExit_);
        //this._addSeparator_();
        return true;
    }
    
    // change in the table, no more selection
    protected void _resetPKTC_()
    {
        this._intSelectionRowCurPKTC_ = -1;
        /*_setEnabledButtonViewKprCrt(false);
        _setEnabledButtonVerifyKprCrt(false);
        _setEnabledButtonViewKprCrtsChain(false);
        _setEnabledButtonViewTcrCrt(false);
        _setEnabledButtonVerifyTcrCrt(false);*/
    }
    
    // change in the table, no more selection
    protected void _resetSK_()
    {
        this._intSelectionRowCurSK_ = -1;
    }
    
    protected String _getCurAliasSK_()
    {
        String strMethod = "_getCurAlias_()";
        
        if (this._strsAliasSK_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._strsAliasSK_");
            return null;
        }
        
        if (this._strsAliasSK_.length < 1)
        {
            MySystem.s_printOutError(this, strMethod, "this._strsAliasSK_.length < 1");
            return null;
        }
        
        if (this._intSelectionRowCurSK_<0 || this._intSelectionRowCurSK_>this._strsAliasSK_.length-1)
        {
            MySystem.s_printOutError(this, strMethod, "out of range, this._intSelectionRowCurSK_=" + this._intSelectionRowCurSK_);
            return null;
        }
        
        return this._strsAliasSK_[this._intSelectionRowCurSK_];
    }
    
    protected String _getCurAliasPKTC_()
    {
        String strMethod = "_getCurAlias_()";
        
        if (this._strsAliasPKTC_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._strsAliasPKTC_");
            return null;
        }
        
        if (this._strsAliasPKTC_.length < 1)
        {
            MySystem.s_printOutError(this, strMethod, "this._strsAliasPKTC_.length < 1");
            return null;
        }
        
        if (this._intSelectionRowCurPKTC_<0 || this._intSelectionRowCurPKTC_>this._strsAliasPKTC_.length-1)
        {
            MySystem.s_printOutError(this, strMethod, "out of range, this._intSelectionRowCurPKTC_=" + this._intSelectionRowCurPKTC_);
            return null;
        }
        
        return this._strsAliasPKTC_[this._intSelectionRowCurPKTC_];
    }
    
    // -------
    // private
    
    
    private BEnabledState _btnTip = null;
    
    
    private String _strBodyButtonUsage = null;
    
    private boolean _addToolbarButtonUsage()
    {
        String strMethod = "_addToolbarButtonUsage()";
        
        
        if (this._btnTip == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._btnTip");
            return false;
        }
        
        this._tbr_.add(this._btnTip);
        return true;
    }
    
    private boolean _viewEntryPKCrtsChain()
    {
        String strMethod = "_viewEntryPKCrtsChain()";
        
        // ---------
        // get alias
        
        
        
        String strKprAlias = this._getCurAliasPKTC_();
        
        if (strKprAlias == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strKprAlias");
            return false;
        }
        
        // ---------------
        // get certs chain
        
        X509Certificate[] crtsX509 = UtilCrtX509.s_getX509CertificateChain(
            this._kstOpen_, 
            strKprAlias, 
            false // blnOrderChain
            );
            
        if (crtsX509 == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil crtsX509");
            return false;
        }
        
        if (crtsX509.length < 1)
        {
            MySystem.s_printOutError(this, strMethod, "crtsX509.length < 1");
            return false;
        }
        
        UtilCrtX509.s_showChain((Frame) getOwner(), crtsX509);
        
        // ending
        return true;
    }
    
    private boolean _viewEntrySKInfo()
    {
        String strMethod = "_viewEntrySKInfo()";
        
        // ---------
        // get alias
        
        
        String strEntryAlias = this._getCurAliasSK_();
        
        if (strEntryAlias == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strEntryAlias");
            return false;
        }
        
        char[] chrsPasswdEntry = _getPasswordEntryPKSK(strEntryAlias);
        
        if (chrsPasswdEntry == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil chrsPasswdEntry, aborting");
            return true;
        }
        
        
        com.google.code.p.keytooliui.shared.util.jarsigner.UtilSK.s_show(
                (Frame) this.getOwner(), this._kstOpen_, strEntryAlias, chrsPasswdEntry);
        
        
        // ending
        return true;
    }
    
    private boolean _viewEntryTCCrt()
    {
        String strMethod = "_viewEntryTCCrt()";
        
        // ---------
        // get alias
        
        
        String strEntryAlias = this._getCurAliasPKTC_();
        
        if (strEntryAlias == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strEntryAlias");
            return false;
        }
        
        // ---------------
        // get certs chain
        
        X509Certificate crtX509 = UtilCrtX509.s_getX509Certificate(
            this._kstOpen_, 
            strEntryAlias
            );
            
        if (crtX509 == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil crtX509");
            return false;
        }
        
        
        UtilCrtX509.s_show((Frame) getOwner(), crtX509);
        
        // ending
        return true;
    }
    
    protected void _addItemToMenuPK_(JMenuItem mim)
    {
        mim.addActionListener(this);
        this._pmuPK.add(mim);
    }
    
    protected void _addItemToMenuTC_(JMenuItem mim)
    {
        mim.addActionListener(this);
        this._pmuTC.add(mim);
    }
    
    protected void _addItemToMenuSK_(JMenuItem mim)
    {
        mim.addActionListener(this);
        this._pmuSK.add(mim);
    }
    
    // -------
    // private
    
    // popup menus
    private JPopupMenu _pmuPK = null;
    private JPopupMenu _pmuTC = null;
    private JPopupMenu _pmuSK = null;
    
    private JPanel _pnlTables = null;
    
   
    
    private void _createPopupMenuPK()
    {
        this._pmuPK = new JPopupMenu();
        JMenuItem mim = null;
        
        // ----
        mim = new JMenuItem(DTblsKstAbs._STR_viewEntryPKCrtChain);
        mim.addActionListener(this);
        this._pmuPK.add(mim);
    }
    
    private void _createPopupMenuTC()
    {
        this._pmuTC = new JPopupMenu();
        JMenuItem mim = null;
        
        // ----
        mim = new JMenuItem(DTblsKstAbs._STR_viewEntryTCCrt);
        mim.addActionListener(this);
        this._pmuTC.add(mim);
    }
    
    private void _createPopupMenuSK()
    {
        this._pmuSK = new JPopupMenu();
        JMenuItem mim = null;
        
        // ----
        mim = new JMenuItem(DTblsKstAbs._STR_viewEntrySKInfo);
        mim.addActionListener(this);
        this._pmuSK.add(mim);
    }
    
    // used by entries of type PK or SK
    private char[] _getPasswordEntryPKSK(String strAlias)
    {
        String strMethod = "_getPasswordEntryPKSK(strAlias)";
        
        // ---
        // get password for this alias
        
        
        if (this._kstOpen_.getType().toLowerCase().compareTo(UtilKstPkcs12.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            return new char[0];
        }
            
        char[] chrsPasswdKpr = null;
    
        // open up a passwordOpen dialog
                
        DPasswordOpen dlg = new DPasswordOpen(
            (Frame) super.getOwner(), 
            false // blnNoPasswdAllowed
                );
                    
        String strTitleSuffixDlg = " ";
        strTitleSuffixDlg += "for";
        strTitleSuffixDlg += " ";
        strTitleSuffixDlg += "alias";
        strTitleSuffixDlg += " ";
        strTitleSuffixDlg += strAlias;
                
                
        dlg.setTitle(dlg.getTitle() + strTitleSuffixDlg);
                    
        if (! dlg.init())
            MySystem.s_printOutExit(this, strMethod, "failed");
                    
        dlg.setVisible(true);
                
        chrsPasswdKpr = dlg.getPassword();        
     
                
        dlg.destroy();
        dlg = null;
        
        if (chrsPasswdKpr == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil chrsPasswdKpr, user canceled");
            return null;
        }
        
        return chrsPasswdKpr;
    }
    
    private void _doMouseRightClickRow(MouseEvent evtMouse)
    {
        String strMethod = "_doMouseRightClickHighlightedRow(evtMouse)";
        
        JTable tbl = null;
        
        if (evtMouse.getSource() == this._pnlTablePKTC_.getTable())
            tbl = this._pnlTablePKTC_.getTable();
        else if (evtMouse.getSource() == this._pnlTableSK_.getTable())
            tbl = this._pnlTableSK_.getTable();
        else
            MySystem.s_printOutExit(this, strMethod, "uncaught e.getSource():" + evtMouse.getSource().getClass().toString());
        
        
        int intRow = tbl.rowAtPoint(evtMouse.getPoint());
        int intCol = tbl.columnAtPoint(evtMouse.getPoint());
        tbl.changeSelection(intRow, intCol, false, false);
        
        if (tbl == this._pnlTablePKTC_.getTable())
        {
            if (_isEntrySelectionTC())
                this._pmuTC.show(evtMouse.getComponent(), evtMouse.getX(), evtMouse.getY());
            else
                this._pmuPK.show(evtMouse.getComponent(), evtMouse.getX(), evtMouse.getY());
        }
        else
            this._pmuSK.show(evtMouse.getComponent(), evtMouse.getX(), evtMouse.getY());
            
    }
    
    private void _valueChangedPKTC(ListSelectionModel lsm)
    {
        if (lsm.isSelectionEmpty())
        {
            _resetPKTC_();
            return;
        }
        
        // clean up SK list selection
        _resetSK_();
        this._pnlTableSK_.getTableSelectionModel().clearSelection(); 
        
        // --
        this._intSelectionRowCurPKTC_ = lsm.getMinSelectionIndex();
        
         //_updateButtonEntrySK();
        // Q: what about SK?
    }
    
    private void _valueChangedSK(ListSelectionModel lsm)
    {
        if (lsm.isSelectionEmpty())
        {
            _resetSK_();
            return;
        }
        
        // clean up PKTC list selection
        _resetPKTC_();
        this._pnlTablePKTC_.getTableSelectionModel().clearSelection(); 
        
        // --
        this._intSelectionRowCurSK_ = lsm.getMinSelectionIndex();
    }
    
    private boolean _isEntrySelectionTC()
    {
        String strMethod = "_isEntrySelectionTC()";

        if (this._intSelectionRowCurPKTC_<0 || this._intSelectionRowCurPKTC_>_boosIsTCEntryPKTC_.length-1)
            MySystem.s_printOutExit(this, strMethod, 
                "this._intSelectionRowCurPKTC_<0 || this._intSelectionRowCurPKTC_>_boosIsTCEntryPKTC_.length<, _intSelectionRowCurPKTC_=" +
                    this._intSelectionRowCurPKTC_);
        
        return this._boosIsTCEntryPKTC_[this._intSelectionRowCurPKTC_].booleanValue();
    }
}
