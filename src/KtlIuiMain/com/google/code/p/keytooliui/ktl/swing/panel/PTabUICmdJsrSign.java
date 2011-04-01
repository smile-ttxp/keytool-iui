/*
 *
 * Copyright (c) 2001-2008 keyTool IUI Project.
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


package com.google.code.p.keytooliui.ktl.swing.panel;

/**
**/

import com.google.code.p.keytooliui.ktl.util.jarsigner.*;
import com.google.code.p.keytooliui.ktl.io.*;
import com.google.code.p.keytooliui.ktl.swing.button.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;
import com.google.code.p.keytooliui.shared.swing.button.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;


import javax.swing.event.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;

final public class PTabUICmdJsrSign extends PTabUICmdJsrAbs
{
    // --------------------------
    // final static public String
    
    final static public String STR_TITLETASK = "Sign JAR file with private key entry";
    
    
    // ---------------------
    // STATIC PRIVATE STRING

    static private String _s_strHelpID = null;


    static private String _s_strDlgInfoActionBodyOK = null;
    static private String _s_strDlgInfoActionBodyQueryRCRPrefix = null;


    static private String _s_strDlgInfoActionBodyQueryRCRSuffix =
        " " +
        com.google.code.p.keytooliui.shared.Shared.f_s_strVersionCurr +
        "?";





    // ------------------
    // STATIC INITIALIZER

    static
    {
        String strWhere = "com.google.code.p.keytooliui.ktl.swing.panel.PTabUICmdJsrSign";

        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.f_s_strBundleDir +
            ".PTabUICmdJsrSign" // class name
            ;

        String strBundleFileLong = strBundleFileShort + ".properties";

        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort,
                java.util.Locale.getDefault());


            _s_strHelpID = rbeResources.getString("helpID");
            _s_strDlgInfoActionBodyOK = rbeResources.getString("dlgInfoActionBodyOK");
            _s_strDlgInfoActionBodyQueryRCRPrefix = rbeResources.getString("dlgInfoActionBodyQueryRCR");


            if (_s_strDlgInfoActionBodyQueryRCRPrefix == null)
            {
                MySystem.s_printOutExit(strWhere, "nil _s_strDlgInfoActionBodyQueryRCRPrefix");
            }

            _s_strDlgInfoActionBodyQueryRCRPrefix += " ";


        }

        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught, " + strBundleFileLong + " not found");
        }
    }

    // ------
    // PUBLIC

    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";

        KTLKprOpenSignAbs crt = null;

        String strFormatKst = ((PSelBtnTfdFileOpenKst) super._pnlSelectFileKst_).getSelectedFormatFile();

        if (strFormatKst == null)
            MySystem.s_printOutExit(this, strMethod, "nil strFormatKst");


        char[] chrsPasswdKst = null;

        if (this._strPasswdKst != null)
            chrsPasswdKst = this._strPasswdKst.toCharArray();

        if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJks.f_s_strKeystoreType.toLowerCase()) == 0)
        {

            crt = new KTLKprOpenSignJks(
                super._frmOwner_,
                super._strTitleAppli_,
                // input
                super._strPathAbsKst_,
                chrsPasswdKst,
                KTLAbs.f_s_strProviderKstJks,

                this._strPathAbsFileJarUnsigned2Open,

                // output
                super._strPathAbsJarSigned_,
                this._strSigfileBasename // nil value allowed
            );
        }

        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJceks.f_s_strKeystoreType.toLowerCase()) == 0)
        {

            crt = new KTLKprOpenSignJceks(
                super._frmOwner_,
                super._strTitleAppli_,
                // input
                super._strPathAbsKst_,
                chrsPasswdKst,
                KTLAbs.f_s_strProviderKstJceks,

                this._strPathAbsFileJarUnsigned2Open,

                // output
                super._strPathAbsJarSigned_,
                this._strSigfileBasename // nil value allowed
            );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstBks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            crt = new KTLKprOpenSignBks(
                super._frmOwner_,
                super._strTitleAppli_,
                // input
                super._strPathAbsKst_,
                chrsPasswdKst,
                KTLAbs.f_s_strProviderKstBks,

                this._strPathAbsFileJarUnsigned2Open,

                // output
                super._strPathAbsJarSigned_,
                this._strSigfileBasename // nil value allowed
            );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstUber.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            crt = new KTLKprOpenSignUber(
                super._frmOwner_,
                super._strTitleAppli_,
                // input
                super._strPathAbsKst_,
                chrsPasswdKst,
                KTLAbs.f_s_strProviderKstUber,

                this._strPathAbsFileJarUnsigned2Open,

                // output
                super._strPathAbsJarSigned_,
                this._strSigfileBasename // nil value allowed
            );
        }

        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstPkcs12.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            crt = new KTLKprOpenSignPkcs12(
                super._frmOwner_,
                super._strTitleAppli_,
                // input
                super._strPathAbsKst_,
                chrsPasswdKst,
                "com.google.code.p.keytooliui.ktl.UIKeytool.S_STR_PROVIDERKSTPKCS12SIGN",

                this._strPathAbsFileJarUnsigned2Open,

                // output
                super._strPathAbsJarSigned_,
                this._strSigfileBasename // nil value allowed
            );
        }

        else
        {
            MySystem.s_printOutExit(this, strMethod, "uncaught value, strFormatKst=" + strFormatKst);
        }

        if (crt.doJob())
        {
            MySystem.s_printOutTrace(this, strMethod, "OK!");

            /* if extension == ".rcr", then show warning-confirm dialog
               or ".jhr", or ".ohr"
            */

            if (! _doneJobQueryPreviewResults())
                MySystem.s_printOutExit(this, strMethod, "failed");
        }

        else
            MySystem.s_printOutTrace(this, strMethod, "either aborted by user or failed");
    }

    // radioButton: RCR (v/s JAR) of unsigned input file
    public void itemStateChanged(ItemEvent evtItem)
    {
        String strMethod = "itemStateChanged(evtItem)";

        if (! (evtItem.getSource() instanceof RBTypeJarAbs))
            MySystem.s_printOutExit(this, strMethod, "wrong source instance, \n evtItem.getSource().getClass().toString()=" + evtItem.getSource().getClass().toString());


        if (evtItem.getStateChange() != ItemEvent.SELECTED)
            return;

        if (evtItem.getSource() instanceof RBTypeJarRcr)
        {
            if (super._fssSelectSignedJar_ == null)
                MySystem.s_printOutExit(this, strMethod, "nil super._fssSelectSignedJar_");

            if (! super._fssSelectSignedJar_.setSelectedTypeFileProjDoc())
                MySystem.s_printOutExit(this, strMethod, "failed");

            return;
        }

        if (evtItem.getSource() instanceof RBTypeJarJhr)
        {
            if (super._fssSelectSignedJar_ == null)
                MySystem.s_printOutExit(this, strMethod, "nil super._fssSelectSignedJar_");

            if (! super._fssSelectSignedJar_.setSelectedTypeFileProjHelpSun())
                MySystem.s_printOutExit(this, strMethod, "failed");

            return;
        }
        
        if (evtItem.getSource() instanceof RBTypeJarOhr)
        {
            if (super._fssSelectSignedJar_ == null)
                MySystem.s_printOutExit(this, strMethod, "nil super._fssSelectSignedJar_");

            if (! super._fssSelectSignedJar_.setSelectedTypeFileProjHelpOracle())
                MySystem.s_printOutExit(this, strMethod, "failed");

            return;
        }
        
        if (evtItem.getSource() instanceof RBTypeJarJar)
        {
            if (super._fssSelectSignedJar_ == null)
                MySystem.s_printOutExit(this, strMethod, "nil super._fssSelectSignedJar_");

            if (! super._fssSelectSignedJar_.setSelectedTypeFileJar())
                MySystem.s_printOutExit(this, strMethod, "failed");

            return;
        }

        if (evtItem.getSource() instanceof RBTypeJarApk)
        {
            if (super._fssSelectSignedJar_ == null)
                MySystem.s_printOutExit(this, strMethod, "nil super._fssSelectSignedJar_");

            if (! super._fssSelectSignedJar_.setSelectedTypeFileApk())
                MySystem.s_printOutExit(this, strMethod, "failed");

            return;
        }

        // ----
        MySystem.s_printOutExit(this, strMethod, "uncaught source");
    }



    public void insertUpdate(DocumentEvent evtDocument)
    {
        String strMethod = "insertUpdate(evtDocument)";

        Document doc = evtDocument.getDocument();

        if (doc == null)
            MySystem.s_printOutExit(this, strMethod, "nil doc");

        Object objPropVal = doc.getProperty(com.google.code.p.keytooliui.shared.swing.textfield.TFAbstract.f_s_strDocPropKey);

        if (objPropVal == null)
            MySystem.s_printOutExit(this, strMethod, "nil objPropVal");

        if (! (objPropVal instanceof String))
            MySystem.s_printOutExit(this, strMethod, "! (objPropVal instanceof String)");

        String strPropVal = (String) objPropVal;

        if (strPropVal == null)
            MySystem.s_printOutExit(this, strMethod, "nil strPropVal");

        // ----

        int intLength = doc.getLength();

        if (intLength == 0)
            MySystem.s_printOutExit(this, strMethod, "intLength == 0");

        String strText = null;

        try
        {
            strText = doc.getText(0, intLength);
        }

        catch(BadLocationException excBadLocation)
        {
            excBadLocation.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "excBadLocation caught");
        }

        // -----

        if (super._doneInsertUpdate_(strPropVal, strText))
        {
            return;
        }

        // ------
        // memo: output, done in subclass



        // -----
        // input

        if (strPropVal.compareTo(PSelBtnTfdFileJarOpenUnsigned.f_s_strDocPropVal) == 0)
        {
            this._strPathAbsFileJarUnsigned2Open = strText;
            _updateActionButtonDataChanged_(true);
            return;
        }

        if (strPropVal.compareTo(PSelBtnTfdFileOpenKst.f_s_strDocPropVal) == 0)
        {
            super._strPathAbsKst_ = strText;
            _updateActionButtonDataChanged_(true);
            return;
        }


        if (strPropVal.compareTo(PSelBtnTfdPasswdXlsKstAny.f_s_strDocPropVal) == 0)
        {
            this._strPasswdKst = strText;
            _updateActionButtonDataChanged_(true);
            return;
        }


        if (strPropVal.compareTo(PSelBtnTfdStrXlsSigfile.f_s_strDocPropVal) == 0)
        {
            this._strSigfileBasename = strText;
            // MEMO: NO UPDATE COZ THIS ONE IS OPTIONAL!
            return;
        }

        // ------------
        MySystem.s_printOutExit(this, strMethod, "uncaught strPropVal, strPropVal=" + strPropVal);
    }

    public void removeUpdate(DocumentEvent evtDocument)
    {
        String strMethod = "removeUpdate(evtDocument)";

        Document doc = evtDocument.getDocument();

        if (doc == null)
            MySystem.s_printOutExit(this, strMethod, "nil doc");

        Object objPropVal = doc.getProperty(com.google.code.p.keytooliui.shared.swing.textfield.TFAbstract.f_s_strDocPropKey);

        if (objPropVal == null)
            MySystem.s_printOutExit(this, strMethod, "nil objPropVal");

        if (! (objPropVal instanceof String))
            MySystem.s_printOutExit(this, strMethod, "! (objPropVal instanceof String)");

        String strPropVal = (String) objPropVal;

        if (strPropVal == null)
            MySystem.s_printOutExit(this, strMethod, "nil strPropVal");


        // ----

        if (super._doneRemoveUpdate_(strPropVal))
        {
            return;
        }

        // -----
        // input

        if (strPropVal.compareTo(PSelBtnTfdFileJarOpenUnsigned.f_s_strDocPropVal) == 0)
        {
            this._strPathAbsFileJarUnsigned2Open = null;
            _updateActionButtonDataChanged_(false);
            return;
        }

        if (strPropVal.compareTo(PSelBtnTfdFileOpenKst.f_s_strDocPropVal) == 0)
        {
            super._strPathAbsKst_ = null;
            _updateActionButtonDataChanged_(false);
            return;
        }

        if (strPropVal.compareTo(PSelBtnTfdPasswdXlsKstAny.f_s_strDocPropVal) == 0)
        {
            this._strPasswdKst = null;
            _updateActionButtonDataChanged_(false);
            return;
        }

       

        if (strPropVal.compareTo(PSelBtnTfdStrXlsSigfile.f_s_strDocPropVal) == 0)
        {
            this._strSigfileBasename = null;
            // MEMO: NO UPDATE COZ THIS ONE IS OPTIONAL!
            return;
        }

        // -------
        MySystem.s_printOutExit(this, strMethod, "uncaught strPropVal, strPropVal=" + strPropVal);
    }

    public void destroy()
    {
        super.destroy();

        if (this._pnlSelectPasswdKst != null)
        {
            this._pnlSelectPasswdKst.destroy();
            this._pnlSelectPasswdKst = null;
        }

        /*if (this._pnlSelectPasswdKpr != null)
        {
            this._pnlSelectPasswdKpr.destroy();
            this._pnlSelectPasswdKpr = null;
        }*/

        /*if (this._pnlSelectAliasKpr != null)
        {
            this._pnlSelectAliasKpr.destroy();
            this._pnlSelectAliasKpr = null;
        }*/

        if (this._pnlSelectSigfileBasename != null)
        {
            this._pnlSelectSigfileBasename.destroy();
            this._pnlSelectSigfileBasename = null;
        }



        if (this._pnlSelectFileJarUnsigned2Open != null)
        {
            this._pnlSelectFileJarUnsigned2Open.destroy();
            this._pnlSelectFileJarUnsigned2Open = null;
        }


    }

    public boolean init()
    {
        String strMethod = "init()";

        if (! super.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        if (this._pnlSelectPasswdKst == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectPasswdKst");
            return false;
        }

        if (! this._pnlSelectPasswdKst.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }



        /*if (this._pnlSelectPasswdKpr == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectPasswdKpr");
            return false;
        }

        if (! this._pnlSelectPasswdKpr.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }*/

        /*if (this._pnlSelectAliasKpr == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectAliasKpr");
            return false;
        }

        if (! this._pnlSelectAliasKpr.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }*/

        if (this._pnlSelectSigfileBasename == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectSigfileBasename");
            return false;
        }

        if (! this._pnlSelectSigfileBasename.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }



        if (this._pnlSelectFileJarUnsigned2Open == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectFileJarUnsigned2Open");
            return false;
        }

        if (! this._pnlSelectFileJarUnsigned2Open.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }



        // --

        // alignment

        java.util.Vector<PSelAbs> vecPanel = new java.util.Vector<PSelAbs>();




        // --

        vecPanel.add(this._pnlSelectFileJarUnsigned2Open);
        vecPanel.add(super._pnlSelectFileKst_);
        vecPanel.add(this._pnlSelectPasswdKst);
    
        vecPanel.add(this._pnlSelectSigfileBasename);

        vecPanel.add(super._fssSelectSignedJar_);


        if (! PSelAbs.s_alignLabels(vecPanel))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        // add listeners


        /**if (! this._pnlSelectFileJarUnsigned2Open.addDocListener(this))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        if (! this._pnlSelectPasswdKst.addDocListener(this))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }**/

        /*if (! this._pnlSelectPasswdKpr.addDocListener(this))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }*/


        /*if (! this._pnlSelectAliasKpr.addDocListener(this))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }*/

        /**if (! this._pnlSelectSigfileBasename.addDocListener(this))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        **/






        // ---------
        return true;
    }

    public PTabUICmdJsrSign(Frame frmOwner, String strTitleAppli)
    {
        super(
            frmOwner,
            strTitleAppli,
            PTabUICmdJsrSign._s_strHelpID,
            true, // blnFileSignedJarSave
            true // blnFieldRequiredKeystore
            );


        this._pnlSelectPasswdKst = new PSelBtnTfdPasswdXlsKstAny(
            (javax.swing.event.DocumentListener) this,
            frmOwner,
            strTitleAppli,
            BESPasswordAbs.f_s_intModeOpen
            );


        this._pnlSelectSigfileBasename = new PSelBtnTfdStrXlsSigfile(
            (javax.swing.event.DocumentListener) this,
            frmOwner, strTitleAppli);

        this._pnlSelectFileJarUnsigned2Open = new PSelBtnTfdFileJarOpenUnsigned(
            (javax.swing.event.DocumentListener) this,
            frmOwner,
            strTitleAppli,
            (ItemListener) this,
            S_FileExtensionUI.f_s_strDirNameDefaultFileUnsigned,
            true // blnFieldRequired
            );
    }

    // ---------
    // PROTECTED

        /**
        contains 5 panels that allow to select strings or files:



        . x) file of type:
          . KeyStore: any extension

        . x) String ==> storepass
        . x) String ==> keypass
        . x) String ==> alias

        . x) file of type:
          . unsigned JAR: extension ".jar"
          . unsigned RCR: extension ".rcr"
    **/
    protected void _fillInPanelInput_()
    {
        super._pnlInput_.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        //natural height, maximum width
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.fill = GridBagConstraints.VERTICAL;
        
        gbc.anchor = GridBagConstraints.WEST; // ? left side of space
        //gbc.anchor = GridBagConstraints.NORTH; // ? left side of space
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        super._pnlInput_.add(this._pnlSelectFileJarUnsigned2Open, gbc);
        
        gbc.gridy ++;
        super._pnlInput_.add(super._pnlSelectFileKst_, gbc);
        
        gbc.gridy ++;
        super._pnlInput_.add(this._pnlSelectPasswdKst, gbc);
    }

    /**
        contains 1 panel that allow to select files:

        . 1) file of type:
          . signed JAR: extension ".jar"
          . signed RCR: extension ".rcr"
    **/
    protected void _fillInPanelOutput_()
    {
        super._pnlOutput_.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        //natural height, maximum width
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.fill = GridBagConstraints.VERTICAL;
        
        gbc.anchor = GridBagConstraints.WEST; // ? left side of space
        //gbc.anchor = GridBagConstraints.NORTH; // ? left side of space
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        super._pnlOutput_.add(super._fssSelectSignedJar_, gbc);
        
        gbc.gridy ++;
        super._pnlOutput_.add(_pnlSelectSigfileBasename, gbc);
    }

    protected void _updateActionButtonDataChanged_(boolean blnFieldInserted)
    {
        if (blnFieldInserted)
        {
            if (super._btnAction_.isEnabled())
                return;

            // output
            if (super._strPathAbsJarSigned_ == null)
                return;

            // input
            if (this._strPathAbsFileJarUnsigned2Open == null)
                return;

            if (super._strPathAbsKst_ == null)
                return;

            if (this._strPasswdKst == null)
                return;

            // --
            super._btnAction_.setEnabled(true);
            return;
        }

        else
        {
            if (! super._btnAction_.isEnabled())
                return;

            // output
            if (super._strPathAbsJarSigned_ == null)
            {
                super._btnAction_.setEnabled(false);
                return;
            }

            // input

            if (this._strPathAbsFileJarUnsigned2Open == null)
            {
                super._btnAction_.setEnabled(false);
                return;
            }

            if (super._strPathAbsKst_ == null)
            {
                super._btnAction_.setEnabled(false);
                return;
            }

            if (this._strPasswdKst == null)
            {
                super._btnAction_.setEnabled(false);
                return;
            }

            // --
            return;
        }
    }


    // -------
    // PRIVATE

    private com.google.code.p.keytooliui.shared.AppMainAbs _appReaderPreviewRcr = null;



    // input
    private PSelBtnTfdPasswdXlsAbs _pnlSelectPasswdKst = null;
    private PSelBtnTfdStrXlsSigfile _pnlSelectSigfileBasename = null;

    private PSelBtnTfdFileJarAbs _pnlSelectFileJarUnsigned2Open = null;


    // input
    private String _strPathAbsFileJarUnsigned2Open = null;

    private String _strPasswdKst = null;
    private String _strSigfileBasename = null; // optional




    /**
        if signed jar is of type RCR, or of type JHR
        ask for previewing results in RCReader, or in JHReader
    **/
    private boolean _doneJobQueryPreviewResults()
    {
        String strMethod = "_doneJobQueryPreviewResults()";

        MySystem.s_printOutFlagDev(this, strMethod, "PENDING: preview in JHReader");

        //boolean blnIsPackageReaderDoc = _isPackageReaderDoc();

        if (super._strPathAbsJarSigned_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._strPathAbsJarSigned_");
            return false;
        }

        String strBody = _s_strDlgInfoActionBodyOK;

        strBody += "\n";
        strBody += "  ";
        strBody += super._strPathAbsJarSigned_;


        // --
        // Is not RCR doc
        if (! super._strPathAbsJarSigned_.toLowerCase().endsWith("." +
            com.google.code.p.keytooliui.shared.io.S_FileExtension.f_s_strProjectReaderDocument.toLowerCase()))
        {
            // show info dialog
	        OPAbstract.s_showDialogInfo(
	            super._frmOwner_, super._strTitleAppli_, strBody);

            // ending
            return true;
        }

        // --
        // Is RCR doc

    
        // show info dialog
        OPAbstract.s_showDialogInfo(
            super._frmOwner_, super._strTitleAppli_, strBody);

        // ending
        return true;


       
        /**else // show warning confirm dialog
        {
            String strTitle = super._strTitleAppli_; // + " - " + "confirm";

            strBody += "\n\n";
            strBody += _s_strDlgInfoActionBodyQueryRCRPrefix;

            strBody += com.google.code.p.keytooliui.rcr.RCReader.f_s_strTitleAppli;

            strBody += _s_strDlgInfoActionBodyQueryRCRSuffix;

            if (! OPAbstract.s_showConfirmDialog(super._frmOwner_, strTitle, strBody))
                return true;


            // check file

            java.io.File fle = new java.io.File(super._strPathAbsJarSigned_);

            if (! fle.exists())
            {
                MySystem.s_printOutError(this, strMethod, "! fle.exists(), super._strPathAbsJarSigned_=" + super._strPathAbsJarSigned_);
                return false;
            }

            if (! fle.canRead())
            {
                MySystem.s_printOutError(this, strMethod, "! fle.canRead(), super._strPathAbsJarSigned_=" + super._strPathAbsJarSigned_);
                return false;
            }

            // launch RCReader

            if (this._appReaderPreviewRcr != null)
            {
                this._appReaderPreviewRcr.destroy();
                this._appReaderPreviewRcr = null;
            }

            try
            {
                String[] strs = { super._strPathAbsJarSigned_ };

                this._appReaderPreviewRcr = new com.google.code.p.keytooliui.rcr.RCReader(strs, com.google.code.p.keytooliui.rcr.RCReader.f_s_intModePreview);
            }

            catch(Exception exc)
            {
                exc.printStackTrace();
                MySystem.s_printOutError(this, strMethod, "exc caught");
                return false;
            }

            if (! this._appReaderPreviewRcr.init())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }

            if (! this._appReaderPreviewRcr.start())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
        }**/


        // ---

        // ending
        //return true;
    }

    /**private boolean _isPackageReaderDoc()
    {
        try
        {
            Class.forName("com.google.code.p.keytooliui.rcr.RCReader");
        }

        catch(ClassNotFoundException excClassNotFound)
        {
            return false;
        }

        return true;
    }**/
}