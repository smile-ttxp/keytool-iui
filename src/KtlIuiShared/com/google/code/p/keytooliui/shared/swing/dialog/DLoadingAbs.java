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


 package com.google.code.p.keytooliui.shared.swing.dialog;

/**
    known subclasses:
    . shared:            DLoadingAppli
    . doc (RCReader):    DLoadingProgressDoc
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.lang.thread.*;

import javax.swing.*;

import java.awt.*;

abstract public class DLoadingAbs extends DEscapeAbstract implements
    Runnable
{
    // -------------
    // STATIC PUBLIC

    static public String s_strLabelWait = null;
    static public ImageIcon s_iinLoading = null;


    // --------------
    // STATIC PRIVATE

    static private String _s_strTitleSuffix = null;
	static private String _s_strLabel1 = null;


    // ------------------
    // STATIC INITIALIZER

    static
    {
        String strWhere = "com.google.code.p.keytooliui.shared.swing.dialog.DLoadingAbs";

        DLoadingAbs.s_iinLoading = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(
            com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.f_s_strWait28_32);

	    if (DLoadingAbs.s_iinLoading == null)
	    {
	        MySystem.s_printOutExit(strWhere, ", nil DLoadingAbs.s_iinLoading");
	    }

        String strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".DLoadingAbs" // class name
            ;

        String strBundleFileLong = strBundleFileShort + ".properties";


        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort,
                java.util.Locale.getDefault());

            DLoadingAbs._s_strTitleSuffix = rbeResources.getString("titleSuffix");
	        DLoadingAbs._s_strLabel1 = rbeResources.getString("label1");
	        DLoadingAbs.s_strLabelWait = rbeResources.getString("labelWait");
        }

        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, strBundleFileLong + ", excMissingResource caught");
        }
        
        strBundleFileLong = null;
    }

    // ------
    // PUBLIC





    public void destroy()
    {
      stop();
      
      this._strTitleApplication = null;
      this._strLoadingWhat = null;
      this._dimSize = null;

      super.destroy();
    }

    public void setVisible(boolean bln)
    {
        if (bln)
            start();
        else
        {
            stop();
            super.setVisible(bln);
        }
    }

    public void run()
    {
      if (this._thr == null)
        return;
        //String strMethod = "run()";

        //if (! _runSession())
          //  MySystem.s_printOutError(this, strMethod, "failed, ignoring");

        super.setVisible(true);
    }

    public void start()
    {
        if (this._thr != null)
        {
            stop();
        }

        this._thr = new MyThreadShared("DLoadingAbs", this);

        /* ----
         beg modif march 19, 03

         coz bug under jvm 1.4.1-pablo-w2k
          ==> dialog remains visible!

         note: works ok under picasso-wnt & picasso-rh7.2
        */

        //this._thr.setPriority(Thread.MIN_PRIORITY);
        this._thr.setPriority(Thread.NORM_PRIORITY);

        // end modif march 19, 03
        // ----


        this._thr.start();
    }


    public synchronized void stop()
    {
        this._thr = null;
    }

    public boolean init()
    {
        String strMethod = "init()";

        if (this._strTitleApplication == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._strTitleApplication");
            return false;
        }



        setTitle(this._strTitleApplication + " - " + _s_strTitleSuffix);

        if (this._strLoadingWhat == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._strLoadingWhat");
            return false;
        }

        // ---------

        java.awt.Container cntContentPane = getContentPane();



        // ---------

        this._lbl1 = new JLabel(DLoadingAbs._s_strLabel1 + " " + this._strLoadingWhat);
        this._lbl2 = new JLabel(DLoadingAbs.s_strLabelWait);

        this._lbl1.setIcon(DLoadingAbs.s_iinLoading);

        this._lbl1.setHorizontalAlignment(JLabel.CENTER);
        this._lbl2.setHorizontalAlignment(JLabel.RIGHT);

        cntContentPane.add(this._lbl1, java.awt.BorderLayout.CENTER);
        cntContentPane.add(this._lbl2, java.awt.BorderLayout.SOUTH);

        return true;
    }

    // ---------
    // PROTECTED

    protected DLoadingAbs(
        Frame fmrOwner,
        String strTitleApplication,
        String strLoadingWhat,
        java.awt.Dimension dimSize // created by subclasses
            )
    {
        super(fmrOwner, false);
        this._strTitleApplication = strTitleApplication;
        this._strLoadingWhat = strLoadingWhat;
        this._dimSize = dimSize;

        // ----
        JPanel pnl = new JPanel();
        pnl.setLayout(new java.awt.BorderLayout());

        pnl.setPreferredSize(this._dimSize);
        pnl.setMinimumSize(this._dimSize);

        setContentPane(pnl);
    }

    // -------
    // PRIVATE

    private java.awt.Dimension _dimSize = null;
    private Thread _thr = null;

    private JLabel _lbl1 = null;
    private JLabel _lbl2 = null;
    private String _strTitleApplication = null;
    private String _strLoadingWhat = null;
}