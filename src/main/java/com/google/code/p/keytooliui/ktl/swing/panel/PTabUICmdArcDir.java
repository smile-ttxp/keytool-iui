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

package com.google.code.p.keytooliui.ktl.swing.panel;

/**
 *
 * @author bantchao
 *
 *MEMO: in comments: target:
 * . Contents description,
 * . Contents keywords.
 */


import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.io.File;
import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import com.google.code.p.keytooliui.ktl.swing.button.RBTypeJarAbs;
import com.google.code.p.keytooliui.ktl.swing.button.RBTypeJarJar;
import com.google.code.p.keytooliui.ktl.swing.button.RBTypeJarJhr;
import com.google.code.p.keytooliui.ktl.swing.button.RBTypeJarOhr;
import com.google.code.p.keytooliui.ktl.swing.button.RBTypeJarRcr;
import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract;
import com.google.code.p.keytooliui.shared.swing.panel.PSelAbs;
import net.miginfocom.swing.MigLayout;

final public class PTabUICmdArcDir extends PTabUICmdArcAbs
{
    // --------------------------
    // final static public String
    
    final static public String STR_TITLETASK = "Archive directory in JAR format";
    
    
    // ---------------------
    // STATIC PRIVATE STRING

    static private String _s_strHelpID = null;


    static private String _s_strDlgInfoActionBodyOK = null;
    //static private String _s_strDlgInfoActionBodyQueryRCRPrefix = null;


    /*static private String _s_strDlgInfoActionBodyQueryRCRSuffix =
        " " +
        com.google.code.p.keytooliui.shared.Shared.f_s_strVersionCurr +
        "?";
*/




    // ------------------
    // STATIC INITIALIZER

    static
    {
        String strWhere = "com.google.code.p.keytooliui.ktl.swing.panel.PTabUICmdArcDir";

        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.F_STR_BUNDLE_DIR +
            ".PTabUICmdArcDir" // class name
            ;

        String strBundleFileLong = strBundleFileShort + ".properties";

        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort,
                java.util.Locale.getDefault());


            _s_strHelpID = rbeResources.getString("helpID");
            _s_strDlgInfoActionBodyOK = rbeResources.getString("dlgInfoActionBodyOK");
            //_s_strDlgInfoActionBodyQueryRCRPrefix = rbeResources.getString("dlgInfoActionBodyQueryRCR");


            /*if (_s_strDlgInfoActionBodyQueryRCRPrefix == null)
            {
                MySystem.s_printOutExit(strWhere, "nil _s_strDlgInfoActionBodyQueryRCRPrefix");
            }

            _s_strDlgInfoActionBodyQueryRCRPrefix += " ";*/


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
        String strMethod = "actionPerformed";
        
        File fleDir2Open = new File(this._strPathAbsFileDir2Open);
        
        File [] flesDirChildren = fleDir2Open.listFiles();
        
        if (flesDirChildren.length < 1)
        {
            MySystem.s_printOutWarning(this, strMethod, "empty dir: " + this._strPathAbsFileDir2Open);
            
            String strBody = "Empty directory!";
            
            strBody += "\n  " + this._strPathAbsFileDir2Open;
            
            strBody += "\n\nAborting ...";
            
            OPAbstract.s_showDialogWarning(super._frmOwner_, strBody);
            return;
        }
        
        
        com.google.code.p.keytooliui.shared.io.DirToJar dtj =
            new com.google.code.p.keytooliui.shared.io.DirToJar(
            this._strPathAbsFileDir2Open,
                super._strPathAbsUnsignedJar2Save_,
                System.getProperty("_appli.title") // !!!!!!!!
                );
        
        
        
        try
        {
            dtj.doJob();
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "exc caught");
            
            String strBody = "Failed to archive directory!";
            
            strBody += "\n\n";
            strBody += "Got exception:";
            strBody += "\n";
            strBody += exc.getMessage();
            
            strBody += "\n\n";
            strBody += "more in \"session.log\" file";
            
            OPAbstract.s_showDialogError(super._frmOwner_, strBody);
            
            
            return;
        }
        
        if (! _doneJobShowDialog())
                MySystem.s_printOutExit(this, strMethod, "failed");
        
        
        
        
      /*  String strMethod = "actionPerformed(evtAction)";

        KTLKprOpenArcAbs crt = null;

        String strFormatKst = ((PSelBtnTfdFileOpenKst) super._pnlSelectFileKst_).getSelectedFormatFile();

        if (strFormatKst == null)
            MySystem.s_printOutExit(this, strMethod, "nil strFormatKst");


        char[] chrsPasswdKst = null;

        if (this._strPasswdKst != null)
            chrsPasswdKst = this._strPasswdKst.toCharArray();

        if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJks.f_s_strKeystoreType.toLowerCase()) == 0)
        {

            crt = new KTLKprOpenArcDirJks(
                super._frmOwner_,
 
                // input
                super._strPathAbsKst_,
                chrsPasswdKst,
                KTLAbs.f_s_strProviderKstJks,

                this._strPathAbsFileDir2Open,

                // output
                super._strPathAbsUnsignedJar2Save_,
                this._strDescription // nil value allowed
                this._strKeywords // nil value allowed
            );
        }

        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJceks.f_s_strKeystoreType.toLowerCase()) == 0)
        {

            crt = new KTLKprOpenArcDirJceks(
                super._frmOwner_,
             
                // input
                super._strPathAbsKst_,
                chrsPasswdKst,
                KTLAbs.f_s_strProviderKstJceks,

                this._strPathAbsFileDir2Open,

                // output
                super._strPathAbsUnsignedJar2Save_,
                this._strDescription // nil value allowed
            );
        }

        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstPkcs12.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            crt = new KTLKprOpenArcDirPkcs12(
                super._frmOwner_,
             
                // input
                super._strPathAbsKst_,
                chrsPasswdKst,
                "com.google.code.p.keytooliui.ktl.UIKeytool.S_STR_PROVIDERKSTPKCS12SIGN",

                this._strPathAbsFileDir2Open,

                // output
                super._strPathAbsUnsignedJar2Save_,
                this._strDescription // nil value allowed
            );
        }

        else
        {
            MySystem.s_printOutExit(this, strMethod, "uncaught value, strFormatKst=" + strFormatKst);
        }


        if (crt.doJob())
        {
            MySystem.s_printOutTrace(this, strMethod, "OK!");

            // if extension == ".rcr", then show warning-confirm dialog
            //   or ".jhr", or ".ohr"
            

            if (! _doneJobShowDialog())
                MySystem.s_printOutExit(this, strMethod, "failed");
        }

        else
            MySystem.s_printOutTrace(this, strMethod, "either aborted by user or failed");
    */}

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
            if (super._fssSelectUnsignedJar2Save_ == null)
                MySystem.s_printOutExit(this, strMethod, "nil super._fssSelectUnsignedJar2Save_");

            if (! super._fssSelectUnsignedJar2Save_.setSelectedTypeFileProjDoc())
                MySystem.s_printOutExit(this, strMethod, "failed");

            return;
        }

        if (evtItem.getSource() instanceof RBTypeJarJhr)
        {
            if (super._fssSelectUnsignedJar2Save_ == null)
                MySystem.s_printOutExit(this, strMethod, "nil super._fssSelectUnsignedJar2Save_");

            if (! super._fssSelectUnsignedJar2Save_.setSelectedTypeFileProjHelpSun())
                MySystem.s_printOutExit(this, strMethod, "failed");

            return;
        }
        
        if (evtItem.getSource() instanceof RBTypeJarOhr)
        {
            if (super._fssSelectUnsignedJar2Save_ == null)
                MySystem.s_printOutExit(this, strMethod, "nil super._fssSelectUnsignedJar2Save_");

            if (! super._fssSelectUnsignedJar2Save_.setSelectedTypeFileProjHelpOracle())
                MySystem.s_printOutExit(this, strMethod, "failed");

            return;
        }

        if (evtItem.getSource() instanceof RBTypeJarJar)
        {
            if (super._fssSelectUnsignedJar2Save_ == null)
                MySystem.s_printOutExit(this, strMethod, "nil super._fssSelectUnsignedJar2Save_");

            if (! super._fssSelectUnsignedJar2Save_.setSelectedTypeFileJar())
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

        if (strPropVal.compareTo(PSelBtnTfdFileOpenAnyDir.f_s_strDocPropVal) == 0)
        {
            this._strPathAbsFileDir2Open = strText;
            _updateActionButtonDataChanged_(true);
            return;
        }

       


        if (strPropVal.compareTo(PSelBtnTfdStrXlsDescription.f_s_strDocPropVal) == 0)
        {
            this._strDescription = strText;
            // MEMO: NO UPDATE COZ THIS ONE IS OPTIONAL!
            return;
        }
        
        if (strPropVal.compareTo(PSelBtnTfdStrXlsKeywords.f_s_strDocPropVal) == 0)
        {
            this._strKeywords = strText;
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

        if (strPropVal.compareTo(PSelBtnTfdFileOpenAnyDir.f_s_strDocPropVal) == 0)
        {
            this._strPathAbsFileDir2Open = null;
            _updateActionButtonDataChanged_(false);
            return;
        }

        if (strPropVal.compareTo(PSelBtnTfdStrXlsDescription.f_s_strDocPropVal) == 0)
        {
            this._strDescription = null;
            // MEMO: NO UPDATE COZ THIS ONE IS OPTIONAL!
            return;
        }
        
        if (strPropVal.compareTo(PSelBtnTfdStrXlsKeywords.f_s_strDocPropVal) == 0)
        {
            this._strKeywords = null;
            // MEMO: NO UPDATE COZ THIS ONE IS OPTIONAL!
            return;
        }

        // -------
        MySystem.s_printOutExit(this, strMethod, "uncaught strPropVal, strPropVal=" + strPropVal);
    }

    public void destroy()
    {
        super.destroy();


        /*if (this._pnlSelectDescription != null)
        {
            this._pnlSelectDescription.destroy();
            this._pnlSelectDescription = null;
        }

        if (this._pnlSelectKeywords != null)
        {
            this._pnlSelectKeywords.destroy();
            this._pnlSelectKeywords = null;
        }*/

        if (this._pnlSelectFileDir2Open != null)
        {
            this._pnlSelectFileDir2Open.destroy();
            this._pnlSelectFileDir2Open = null;
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



        /*if (this._pnlSelectDescription == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectDescription");
            return false;
        }

        if (! this._pnlSelectDescription.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._pnlSelectKeywords == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectKeywords");
            return false;
        }

        if (! this._pnlSelectKeywords.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }*/



        if (this._pnlSelectFileDir2Open == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectFileDir2Open");
            return false;
        }

        if (! this._pnlSelectFileDir2Open.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }



        // --

        // alignment

        java.util.Vector<PSelAbs> vecPanel = new java.util.Vector<PSelAbs>();
        
        // --

        vecPanel.add(this._pnlSelectFileDir2Open);

    
        
        vecPanel.add(super._fssSelectUnsignedJar2Save_);
        //vecPanel.add(this._pnlSelectDescription);
        //vecPanel.add(this._pnlSelectKeywords);


        if (! PSelAbs.s_alignLabels(vecPanel))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        // add listeners



        // ---------
        return true;
    }

    public PTabUICmdArcDir(Frame frmOwner)
    {
        super(
            frmOwner,

            PTabUICmdArcDir._s_strHelpID
            );


        this._pnlSelectFileDir2Open = new PSelBtnTfdFileOpenAnyDirViewXml(
            (javax.swing.event.DocumentListener) this,
            frmOwner, 

            (java.awt.event.ItemListener) null,
            "Directory:" // strLabel
            );
        
        /*this._pnlSelectDescription = new PSelBtnTfdStrXlsDescription(
            (javax.swing.event.DocumentListener) this,
            frmOwner);
        
        this._pnlSelectKeywords = new PSelBtnTfdStrXlsKeywords(
            (javax.swing.event.DocumentListener) this,
            frmOwner);*/
    }

    // ---------
    // PROTECTED

   
    protected void _fillInPanelInput_()
    {
        super._pnlInput_.setLayout(new MigLayout("fill, wrap 1", "[left]"));
        super._pnlInput_.add(this._pnlSelectFileDir2Open);
        //super._pnlInput_.add(super._pnlSelectFileKst_);
        //super._pnlInput_.add(this._pnlSelectPasswdKst);
    }

    /**
        contains 1 panel that allow to select files:

        . 1) file of type:
          . signed JAR: extension ".jar"
          . signed RCR: extension ".rcr"
    **/
    protected void _fillInPanelOutput_()
    {
        super._pnlOutput_.setLayout(new MigLayout("fill, wrap 1", "[left]"));
        super._pnlOutput_.add(super._fssSelectUnsignedJar2Save_);
        //super._pnlOutput_.add(_pnlSelectDescription);
        //super._pnlOutput_.add(_pnlSelectKeywords);
    }

    protected void _updateActionButtonDataChanged_(boolean blnFieldInserted)
    {
        if (blnFieldInserted)
        {
            if (super._btnAction_.isEnabled())
                return;

            // output
            if (super._strPathAbsUnsignedJar2Save_ == null)
                return;

            // input
            if (this._strPathAbsFileDir2Open == null)
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
            if (super._strPathAbsUnsignedJar2Save_ == null)
            {
                super._btnAction_.setEnabled(false);
                return;
            }

            // input

            if (this._strPathAbsFileDir2Open == null)
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



    // input
    //private PSelBtnTfdStrXlsDescription _pnlSelectDescription = null;
    //private PSelBtnTfdStrXlsKeywords _pnlSelectKeywords = null;

    private PSelBtnTfdFileAbs _pnlSelectFileDir2Open = null;


    // input
    private String _strPathAbsFileDir2Open = null;
    // output
    private String _strDescription = null; // optional
    private String _strKeywords = null; // optional

    


    /**
        if signed jar is of type RCR, or of type JHR
        ask for previewing results in RCReader, or in JHReader
    **/
    private boolean _doneJobShowDialog()
    {
        String strMethod = "_doneJobShowDialog()";
        
        if (super._strPathAbsUnsignedJar2Save_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._strPathAbsUnsignedJar2Save_");
            return false;
        }

        String strBody = _s_strDlgInfoActionBodyOK;

        strBody += "\n";
        strBody += "  ";
        strBody += super._strPathAbsUnsignedJar2Save_;


        // --
        // NOT REALLY NEEDED !
        if (super._strPathAbsUnsignedJar2Save_.toLowerCase().endsWith("." +
            com.google.code.p.keytooliui.shared.io.S_FileExtension.f_s_strProjectReaderDocument.toLowerCase()))
        {
            // show info dialog
	        OPAbstract.s_showDialogInfo(
	            super._frmOwner_, strBody);

            // ending
            return true;
        }


        // statement should never be reached!
        // ERROR 
        // ending
        return false;

    }
}