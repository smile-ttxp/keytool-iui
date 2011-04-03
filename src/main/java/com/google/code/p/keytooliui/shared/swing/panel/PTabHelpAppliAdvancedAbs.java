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
    known subclasses:
    . PTabHelpAppliAdvancedRdr
    . PTabHelpAppliAdvancedUIKtl
 
    contains the following:
    . panelJWSOnOff (JWS means Java(TM) Web Start

 
 **/
 
 import com.google.code.p.keytooliui.shared.swing.label.*;
 import com.google.code.p.keytooliui.shared.lang.*;

 import javax.swing.border.*;
 import javax.swing.*;
 
 import java.awt.*;
 import java.util.*;
 import java.io.*;
 
 abstract public class PTabHelpAppliAdvancedAbs extends JPanel
 {
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private int _f_s_intTextFieldColumns = 340;
    final static private int _f_s_intTextFieldH = 30;
    final static private String _s_strLabelPathAbsApplicationHome = "Application's cache dir.:";
    final static private String _s_strLabelUserName = "User name:";

    // ---------------------
    // STATIC PRIVATE STRING
    
    static private String _s_strTitleMode = null;
    static private String _s_strTitleLaunchBy = null;
    
    static private String _s_strTextNBModule = null;
    static private String _s_strTextEcPlugin = null;
    static private String _s_strTextJWS = null;
    static private String _s_strTextStandalone = null;
    
    // ------------------
    // STATIC INITIALIZER
    
    static
    {
        String strWhere = "com.google.code.p.keytooliui.shared.swing.panel.PTabHelpAppliAdvancedAbs";
        
        String strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".PTabHelpAppliAdvancedAbs" // class name
            ;
        
        String strBundleFileLong = strBundleFileShort + ".properties";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            _s_strTitleLaunchBy = rbeResources.getString("titleLaunchBy");
            _s_strTextEcPlugin = rbeResources.getString("textEcPlugin");
            _s_strTextNBModule = rbeResources.getString("textNBModule");
            _s_strTextJWS = rbeResources.getString("textJWS");
            _s_strTextStandalone = rbeResources.getString("textStandalone");
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
    
    // public
    
    public void destroy()
    {
        if (this._vecLabelProp != null)
        {
            this._vecLabelProp.clear();
            this._vecLabelProp = null;
        }
    }
    
    // ---------
    // PROTECTED
    
    protected JTextField _createTextField_(String strText)
    {
        JTextField tfd = new JTextField();
        
        if (strText != null)
        {
            tfd.setText(strText);
            tfd.setToolTipText(strText);
        }
        
        
        tfd.setEditable(false);
	    
	    final Dimension dim = new Dimension(PTabHelpAppliAdvancedAbs._f_s_intTextFieldColumns, PTabHelpAppliAdvancedAbs._f_s_intTextFieldH);
        tfd.setPreferredSize(dim);
        tfd.setMaximumSize(dim);
        return tfd;
    }
    
    protected PTabHelpAppliAdvancedAbs()
    {
        super(true);
        
        String strMethod = "PTabHelpAppliAdvancedAbs()";
        
        this._vecLabelProp = new Vector<JLabel>();
        
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentY(TOP_ALIGNMENT);
        setAlignmentX(LEFT_ALIGNMENT);
        
        // --
        
        JPanel pnlLaunchedBy = _createPanelLaunchedBy();
        
        if (pnlLaunchedBy == null)
            MySystem.s_printOutExit(this, strMethod, "nil pnlLaunchedBy");
        
        // --
        
        
        JPanel pnlProperties = _createPanelProp();
        
        if (pnlProperties == null)
            MySystem.s_printOutExit(this, strMethod, "nil pnlProperties");
            
        // --
        
        add(Box.createRigidArea(new Dimension(1,30)));
        add(pnlLaunchedBy);

        add(Box.createRigidArea(new Dimension(1,30)));
        add(pnlProperties);

        // equalize
        if (! _equalizeLabelsSizeProp())
            MySystem.s_printOutExit(this, strMethod, "failed");
    }
    
    // -------
    // PRIVATE
    
    private Vector<JLabel> _vecLabelProp = null;
    
    
    
    /*
        shows JWS YES/NO
        
        // buttonGroup not needed!
        
         
    */
    private JPanel _createPanelLaunchedBy()
    {
        String strMethod = "_createPanelLaunchedBy()";
        JPanel pnl = new JPanel();
        
        
        // 
        boolean blnECP = com.google.code.p.keytooliui.shared.AppAbs.s_isDeployedWithEcp();
        boolean blnNBM = com.google.code.p.keytooliui.shared.AppAbs.s_isDeployedWithNbm();
        boolean blnJWS = com.google.code.p.keytooliui.shared.AppAbs.s_isDeployedWithJws();
        boolean blnSTD = false;
        
        if (!blnECP && !blnNBM && !blnJWS)
            blnSTD = true;
        
        if (blnNBM && blnJWS) //!!!
        {
            MySystem.s_printOutExit(this, strMethod, "Code error: blnNBM && blnJWS, exiting");
        }
        
        
        
        // TODO: assign value of "lauched by NetBeans module", and update others
        JLabel lblEcPlugin = new LabelCheck(_s_strTextEcPlugin, blnECP);
        JLabel lblNBModule = new LabelCheck(_s_strTextNBModule, blnNBM);
        JLabel lblJWS = new LabelCheck(_s_strTextJWS, blnJWS);
        JLabel lblStandalone = new LabelCheck(_s_strTextStandalone, blnSTD);
        
        // --
        pnl.setBorder(new TitledBorder( _s_strTitleLaunchBy));
        
        
        // --
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
        pnl.add(lblEcPlugin);
        pnl.add(lblNBModule);
        pnl.add(lblJWS);
        pnl.add(lblStandalone);
        

        // --
        return pnl;
    }
    
    
    private JPanel _createPanelProp()
    {
        String strMethod = "_createPanelProp()";
        
        JPanel pnl = new JPanel();
        
        // --
        
        String strApplicationHome = "File not found!";
        
        File fleApplicationHome = com.google.code.p.keytooliui.shared.io.S_FileSys.s_getPathAbsParentAppli(true);
            
        if (fleApplicationHome == null)
        {
            MySystem.s_printOutError(strMethod, "nil fleApplicationHome");
        }
        
        else
            strApplicationHome = fleApplicationHome.getAbsolutePath();
        
        // --
        
        JPanel pnlPathAbsApplicationHome = _createPanelPathAbs(PTabHelpAppliAdvancedAbs._s_strLabelPathAbsApplicationHome, strApplicationHome);
        
        // --
        
        String strUserName = "Unknown!";
        
        if (System.getProperty("user.name") != null)
            strUserName = System.getProperty("user.name");
        
        JPanel pnlUserName = _createPanelPathAbs(PTabHelpAppliAdvancedAbs._s_strLabelUserName, strUserName);

        // --
        pnl.setBorder(new TitledBorder("Properties"));
        
        
        // --
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
        pnl.add(pnlPathAbsApplicationHome);
        pnl.add(pnlUserName);
        
        // --
        return pnl;
    }
 
    
    private boolean _equalizeLabelsSizeProp()
    {
        String strMethod = "_equalizeLabelsSizeProp()"; 
        
        if (this._vecLabelProp == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._vecLabelProp");
            return false;
        }
        
        // --------------------------------------
        //    equalizing the width of the checkBoxes
        
        
        int intMin = -1;
        int intPref = -1;
        int intMax = -1;
        
        for (int i=0; i<this._vecLabelProp.size(); i++)
        {
            JLabel lblCur = (JLabel) this._vecLabelProp.elementAt(i);
            
            int intMinCur = lblCur.getMinimumSize().width;
            
            if (intMinCur > intMin)
                intMin = intMinCur;
                
            int intMaxCur = lblCur.getMaximumSize().width;
            
            if (intMaxCur > intMax)
                intMax = intMaxCur;
                
            int intPrefCur = lblCur.getPreferredSize().width;
            
            if (intPrefCur > intPref)
                intPref = intPrefCur;
        }
        
        // ----
        
        for (int i=0; i<this._vecLabelProp.size(); i++)
        {
            JLabel lblCur = (JLabel) this._vecLabelProp.elementAt(i);
        
            lblCur.setMinimumSize(new Dimension(intMin, lblCur.getMinimumSize().height));
            lblCur.setMaximumSize(new Dimension(intMax, lblCur.getMaximumSize().height));
            lblCur.setPreferredSize(new Dimension(intPref, lblCur.getPreferredSize().height));
        }
        
        return true;
    }
    
    private JPanel _createPanelPathAbs(String strLabel, String strTextField)
    {
        JPanel pnl = new JPanel();
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.X_AXIS));
        
        JLabel lbl = new JLabel(strLabel);
        this._vecLabelProp.addElement(lbl);
        
        JTextField tfd = _createTextField_(strTextField); 

        pnl.add(lbl);
        pnl.add(Box.createHorizontalStrut(10));
        
        pnl.add(tfd);
        
        return pnl;
    }
    
    
 }