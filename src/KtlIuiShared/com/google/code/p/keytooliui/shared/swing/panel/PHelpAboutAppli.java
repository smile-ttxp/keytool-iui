/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
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
 
 
 package com.google.code.p.keytooliui.shared.swing.panel;
 
 /**
 

 
 should contain:
 . centered: tabbedPane
 . south: "close" button
   
 
 TabbedPane should contain:
 . default: "general" tab
 . optional: "advanced", ..., tabs
   eg: RCReader contains an advanced tab, while UIKeytool just contains the default genral tab right now!
 
 
 "General" tab pane should contain:
 . north:
   . a label (big)
   . a label (small
 . centered:
   . a textPane
 
 **/
 
 import com.google.code.p.keytooliui.shared.lang.*;
 import com.google.code.p.keytooliui.shared.swing.button.*;
 
 import javax.swing.*;
 
 import java.awt.*;
 import java.awt.event.*;
 
 final public class PHelpAboutAppli extends JPanel 
 {
    // --------------------
    // STATIC PUBLIC STRING
    
    static public String s_strContactPoints = null;
    
    // ---------------------
    // STATIC PRIVATE STRING
    
    static private String _s_strTabTextGeneral = null;
    static private String _s_strTabTextAdvanced = null;
    
    static private String _s_strTabTipGeneral = null;
    static private String _s_strTabTipAdvanced = null;
    
    
    
    // ------------------
    // STATIC INITIALIZER
    
    static
    {
        String strWhere = "com.google.code.p.keytooliui.shared.swing.panel.PHelpAboutAppli";
        
        String strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared._F_STR_PATH_BUNDLE +
            ".PHelpAboutAppli" // class name
            ;
        
        String strBundleFileLong = strBundleFileShort + ".properties";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            PHelpAboutAppli._s_strTabTextGeneral = rbeResources.getString("tabTextGeneral");
            PHelpAboutAppli._s_strTabTextAdvanced = rbeResources.getString("tabTextAdvanced");
            
            PHelpAboutAppli._s_strTabTipGeneral = rbeResources.getString("tabTipGeneral");
            PHelpAboutAppli._s_strTabTipAdvanced = rbeResources.getString("tabTipAdvanced");
            
            PHelpAboutAppli.s_strContactPoints = rbeResources.getString("contactPoints");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught, " + strBundleFileLong + " not found");
        }
        
        strWhere = null;
        strBundleFileShort = null;
        strBundleFileLong = null;
    }
    
    // ------
    // PUBLIC
    
    /*public void showIt()
    {
        if (this._spnMid != null)
            this._spnMid.getVerticalScrollBar().setValue(0);
    }*/
    
    public PHelpAboutAppli(
        ActionListener actListenerParent, 
        // tabGeneral
 
        String strAppliCopyright,
        String strProductID,
        String strTextThirdParty,
        // additional tabs
        PTabHelpAppliAdvancedAbs pnlTabAdvanced
        )
    {
        super(true);
        
        String strMethod = "PHelpAboutAppli(...)";
        
        this._pnlTabAdvanced = pnlTabAdvanced;
        
        this._btnClose = new BClose((ActionListener) actListenerParent);
        
        com.google.code.p.keytooliui.shared.swing.border.S_Border.s_setEtched(this);
        
        this._spnMid = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        
        
        JTabbedPane tpnMid = _createTabbedPaneMid(
            strAppliCopyright, 
            PHelpAboutAppli.s_strContactPoints, 
            strProductID, 
            strTextThirdParty);
        
        if (tpnMid == null)
            MySystem.s_printOutExit(this, strMethod, "nil tpnMid");

        JPanel pnlBottom = _createPanelBottom(actListenerParent);
        
        if (pnlBottom == null)
            MySystem.s_printOutExit(this, strMethod, "nil pnlBottom");
            
        // --
        
        setLayout(new BorderLayout());
        add(tpnMid, BorderLayout.CENTER);
        add(pnlBottom, BorderLayout.SOUTH);
                
        // --
        
        Dimension dim = new Dimension(500, 500);
        setSize(dim);
        setPreferredSize(dim);
        
    }
    
    public boolean init()
    {
        return true;
    }
    
    public void destroy()
    {
        if (this._btnClose != null)
        {
            this._btnClose.destroy();
            this._btnClose = null;
        }
        
        if (this._pnlTabAdvanced != null)
        {
            this._pnlTabAdvanced.destroy();
            this._pnlTabAdvanced = null;
        }
        
        this._spnMid = null;
    }
    
    
    // -------
    // PRIVATE
    
    private JScrollPane _spnMid = null;
    private BAbs _btnClose = null;
    private PTabHelpAppliAdvancedAbs _pnlTabAdvanced = null;
    
    private boolean _fillInScrollPaneTabGeneralMid(String strTextThirdParty)
    {
        String strMethod = "_fillInScrollPaneTabGeneralMid(strTextThirdParty)";
        
        if (strTextThirdParty == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strTextThirdParty");
            return false;
        }
        
        
        JTextPane tpn = new JTextPane();
        
        
        // --
        tpn.setEditable(false);
        tpn.setText(strTextThirdParty);

        
        this._spnMid.getViewport().add(tpn); 
        return true;
    }
    
    
    
    private JTabbedPane _createTabbedPaneMid(
        String strAppliCopyright,
        String strContactPoints,
        String strProductID,
        String strTextThirdParty)
    {
        String strMethod = "_createTabbedPaneMid(...)";
        JTabbedPane tpn = new JTabbedPane();
        
        JPanel pnlTabGeneral = _createPanelTabGeneral(
            strAppliCopyright, 
            strContactPoints, strProductID, strTextThirdParty);
        
        if (pnlTabGeneral == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil pnlTabGeneral");
            return null;
        }

        // ----
        
        if (this._pnlTabAdvanced == null)
        {
            tpn.addTab(null, null, pnlTabGeneral, null);
        }
        
        else
        {
            tpn.addTab(PHelpAboutAppli._s_strTabTextGeneral, null, pnlTabGeneral, PHelpAboutAppli._s_strTabTipGeneral);
            tpn.addTab(PHelpAboutAppli._s_strTabTextAdvanced, null, this._pnlTabAdvanced, PHelpAboutAppli._s_strTabTipAdvanced);
        }
        
        
        // --
        return tpn;
    }
    
    private JPanel _createPanelBottom(ActionListener actListenerParent)
    {
        JPanel pnl = new JPanel();
       
        pnl.add(this._btnClose);
        return pnl;
    }
    
    /*
        contains:
        . top:
          . appliTitle
          . appliVersion
          . (optional) product ID
          . appliCopyright
          
        . centered:
          . third parties copyright
    */
    private JPanel _createPanelTabGeneral(
        String strAppliCopyright,
        String strContactPoints,
        String strProductID,
        String strTextThirdParty)
    {
        String strMethod = "_createPanelTabGeneral(...)";
        
        JPanel pnl = new JPanel();
        
        JPanel pnlTop = _createPanelTabGeneralTop(
            strAppliCopyright, strContactPoints, strProductID);
        
        if (pnlTop == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil pnlTop");
            return null;
        }
      
        if (! _fillInScrollPaneTabGeneralMid(strTextThirdParty))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return null;
        }
        
        // --
        pnl.setLayout(new BorderLayout());
        pnl.add(pnlTop, BorderLayout.NORTH);
        pnl.add(this._spnMid, BorderLayout.CENTER);
        
        
        // --
        return pnl;
    }
    
    /*
      contains:
      . appliTitle
      . appliVersion
      . copyright
      
      may contain:
        product ID
    */
 
    private JPanel _createPanelTabGeneralTop(

        String strAppliCopyright,
        String strContactPoints,
        String strProductID)
    {
        String strMethod = "_createPanelTabGeneralTop(...)";
        
        if (strAppliCopyright==null || 
            strContactPoints==null || strProductID==null)
        {
            MySystem.s_printOutError(this, strMethod, "nil arg");
            return null;
        }
        
        JPanel pnl = new JPanel();
        
        JPanel pnlAppliTitle = new JPanel();
        JPanel pnlAppliVersion = new JPanel();
        
        JPanel pnlProductID = null;
        if (strProductID != null)
            pnlProductID = new JPanel();
        
        JPanel pnlAppliCopyright = new JPanel();
        JPanel pnlContactPoints = new JPanel();
        
        JLabel lblAppliTitle = new JLabel();
        JLabel lblAppliVersion = new JLabel();
        
        JLabel lblProductID = null;
        if (strProductID != null)
            lblProductID = new JLabel(strProductID);
        
        
        JLabel lblAppliCopyright = new JLabel();
        JLabel lblContactPoints = new JLabel();
        
        // --
        
        
        pnlAppliTitle.setBackground(new Color(40, 40, 40));
        pnlAppliVersion.setBackground(new Color(80, 80, 80));
        
        lblAppliTitle.setForeground(new Color(220, 210, 200));
        lblAppliVersion.setForeground(new Color(240, 230, 220));
        
        lblAppliTitle.setFont(new Font("Serif", Font.PLAIN, 48));
        lblAppliVersion.setFont(new Font("Serif", Font.PLAIN, 24));
        //lblBig.setForeground(Color.green);
        
        lblAppliTitle.setText(System.getProperty("_appli.title"));
        lblAppliVersion.setText(System.getProperty("_appli.version"));
        
        lblAppliCopyright.setText(strAppliCopyright);
        lblContactPoints.setText(strContactPoints);
        
        // --
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
        
        
        // ----
        pnlAppliTitle.add(lblAppliTitle);
        pnlAppliVersion.add(lblAppliVersion);
        
        if (pnlProductID != null)
            pnlProductID.add(lblProductID);
        
        pnlAppliCopyright.add(lblAppliCopyright);
        
        pnlContactPoints.add(lblContactPoints);
        
        
        //pnl.setAlignmentX(CENTER_ALIGNMENT);
	    //pnl.setAlignmentY(TOP_ALIGNMENT);
        
        pnl.add(pnlAppliTitle);
        pnl.add(pnlAppliVersion);
        pnl.add(Box.createVerticalStrut(10));
        
        if (pnlProductID != null)
            pnl.add(pnlProductID);
        
        pnl.add(pnlAppliCopyright);
        pnl.add(pnlContactPoints);
        pnl.add(Box.createVerticalStrut(10));
       
        // --
         
        return pnl;
    }
    
 }
 