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


package com.google.code.p.keytooliui.shared.io;

/**
    MEMO: for access of a jar from URL

 URL urlSource = null;
            // Make sure you don't forget the "/" at the end of the following line:
            String sURL = "jar:http://your.domain.xx/yourdirectory/YourJar.jar!/";
            JarURLConnection jarConn = null;
            JarFile jf = null;

            try
            {
                urlSource = new URL(sURL);
            }

            catch(MalformedURLException exp)
            {
                System.out.println("Malformed URL: " + urlSource + "!");
            }

            try
            {
                jarConn = (JarURLConnection)
                urlSource.openConnection();
            }

            catch(IOException exp)
            {
                System.out.println("IOException while opening connection: return.");
                return;
            }

            try
            {
                jf = jarConn.getJarFile();
            }

            catch(IOException exp)
            {
                System.out.println("IOException while getting JarFile: return.");
                return;
            }
**/



import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;

import java.util.jar.*;
import java.io.*;
import java.util.*;
import java.security.cert.*;

final public class JarSignedSys
{
    // ---------------------
    // STATIC PRIVATE STRING

    static private String _s_strDumpCertOk = null;
    static private String _s_strDumpCertWrong = null;
    static private String _s_strDumpCheckOk = null;
    static private String _s_strDumpCheckWrong = null;

    static private String _s_strDumpMoreNotApplicable = null;
    static private String _s_strDumpMoreUnknown = null;

    static private String _s_strDialogInitFailedBodyFNF = null;
    static private String _s_strDialogInitFailedBodyIO = null;
    static private String _s_strDialogInitFailedBodyZip = null;
    static private String _s_strDialogInitFailedBodySecurity = null;

    // ------
    // STATIC

    static
    {
        String strWhere = "com.google.code.p.keytooliui.shared.io.JarSignedSys";

        try
        {
            String strBundleFileShort =
                com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
                ".JarSignedSys" // class name
                ;

            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort,
                java.util.Locale.getDefault());

            JarSignedSys._s_strDumpCertOk = rbeResources.getString("dumpCertOk");
            JarSignedSys._s_strDumpCertWrong = rbeResources.getString("dumpCertWrong");
            JarSignedSys._s_strDumpCheckOk = rbeResources.getString("dumpCheckOk");
            JarSignedSys._s_strDumpCheckWrong = rbeResources.getString("dumpCheckWrong");

            JarSignedSys._s_strDumpMoreNotApplicable = rbeResources.getString("dumpMoreNotApplicable");
            JarSignedSys._s_strDumpMoreUnknown = rbeResources.getString("dumpMoreUnknown");

            JarSignedSys._s_strDialogInitFailedBodyFNF = rbeResources.getString("dialogInitFailedBodyFNF");
            JarSignedSys._s_strDialogInitFailedBodyIO = rbeResources.getString("dialogInitFailedBodyIO");
            JarSignedSys._s_strDialogInitFailedBodyZip = rbeResources.getString("dialogInitFailedBodyZip");
            JarSignedSys._s_strDialogInitFailedBodySecurity = rbeResources.getString("dialogInitFailedBodySecurity");
        }

        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught");
        }
    }

    // -------------
    // PUBLIC ACCESS

    public boolean isSignedX509CertificateDoc() { return this._blnSignedX509CertificateDoc; }

    public String getCertificateSubjectO()
    {
        String strMethod = "getCertificateSubjectO()";

        if (this._cersScript == null)
            return JarSignedSys._s_strDumpMoreNotApplicable;

        if (this._cersScript.length == 0)
            MySystem.s_printOutExit(this, strMethod, "this._cersScript.length == 0");

        if (! (this._cersScript[0] instanceof X509Certificate))
            return JarSignedSys._s_strDumpMoreUnknown;

        X509Certificate x5c = (X509Certificate) this._cersScript[0];
        java.security.Principal pri = x5c.getSubjectDN();

        String strCertificateSubject = pri.getName();

        String strCN = _getO(strCertificateSubject);

        if (strCN == null)
            MySystem.s_printOutExit(this, strMethod, "nil strCN");

        return strCN;
    }


    public String getCertificateIssuerO()
    {
        String strMethod = "getCertificateIssuerO()";

        if (this._cersScript == null)
            return JarSignedSys._s_strDumpMoreNotApplicable;

        if (this._cersScript.length == 0)
            MySystem.s_printOutExit(this, strMethod, "this._cersScript.length == 0");

        if (! (this._cersScript[0] instanceof X509Certificate))
            return JarSignedSys._s_strDumpMoreUnknown;

        X509Certificate x5c = (X509Certificate) this._cersScript[0];
        java.security.Principal pri = x5c.getIssuerDN();

        String strCertificateIssuer = pri.getName();

        String strCN = _getO(strCertificateIssuer);

        if (strCN == null)
            MySystem.s_printOutExit(this, strMethod, "nil strCN");

        return strCN;
    }


    // 4) issuer
    public String getCertificateIssuer()
    {
        String strMethod = "getCertificateIssuer()";

        if (this._cersScript == null)
            return JarSignedSys._s_strDumpMoreNotApplicable;

        if (this._cersScript.length == 0)
            MySystem.s_printOutExit(this, strMethod, "this._cersScript.length == 0");

        if (! (this._cersScript[0] instanceof X509Certificate))
            return JarSignedSys._s_strDumpMoreUnknown;

        X509Certificate x5c = (X509Certificate) this._cersScript[0];
        java.security.Principal pri = x5c.getIssuerDN();

        return pri.getName();
    }

    /**
        if any error, returning nil
    **/
    public String getSerialNumber()
    {
        String strMethod = "getSerialNumber()";

        if (this._cersScript == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._cersScript");
            return null;
        }

        if (this._cersScript.length == 0)
        {
            MySystem.s_printOutError(this, strMethod, "this._cersScript.length == 0");
            return null;
        }

        if (! (this._cersScript[0] instanceof X509Certificate))
        {
            MySystem.s_printOutError(this, strMethod, "! (this._cersScript[0] instanceof X509Certificate)");
            return null;
        }

        X509Certificate x5c = (X509Certificate) this._cersScript[0];

        return UtilCrtX509.s_dumpSerialNumber(x5c);

        //return x5c.getSerialNumber().toString(16).toUpperCase();
    }

    public String getCertificateSubject()
    {
        String strMethod = "getCertificateSubject()";

        if (this._cersScript == null)
            return JarSignedSys._s_strDumpMoreNotApplicable;

        if (this._cersScript.length == 0)
            MySystem.s_printOutExit(this, strMethod, "this._cersScript.length == 0");

        if (! (this._cersScript[0] instanceof X509Certificate))
            return JarSignedSys._s_strDumpMoreUnknown;

        X509Certificate x5c = (X509Certificate) this._cersScript[0];
        java.security.Principal pri = x5c.getSubjectDN();

        return pri.getName();
    }

    public String dumpMoreInfo()
    {
        String strMethod = "dumpMoreInfo()";

        if (this._cersScript == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._cersScript");
            return null;
        }

        X509Certificate[] crts = new X509Certificate[this._cersScript.length];

        try
        {
            for (int i=0; i<this._cersScript.length; i++)
                crts[i] = (X509Certificate) this._cersScript[i];
        }

        catch(ClassCastException excClassCast)
        {
            excClassCast.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excClassCast caught");
            return null;
        }

        return UtilCrtX509.s_dumpIt(crts);
    }


    /**public String dumpMoreInfo()
    {
        String strMethod = "dumpMoreInfo()";

        if (this._cersScript == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._cersScript");
            return null;
        }

        if (this._cersScript.length == 0)
        {
            MySystem.s_printOutError(this, strMethod, "this._cersScript.length == 0");
            return null;
        }

        StringBuffer sbr = new StringBuffer();

        sbr.append(JarSignedSys._s_strDumpMoreCertNb);  // ("nb certificates");

        sbr.append("=" + Integer.toString(this._cersScript.length));
        sbr.append("\n");

        for (int i=0; i<this._cersScript.length; i++)
        {
            Certificate cerCur = this._cersScript[i];

            sbr.append("\n");
            sbr.append("---- ");

            // --
            sbr.append(JarSignedSys._s_strDumpMoreCertBegin);                      // ("BEGIN Certificate #");

            sbr.append(Integer.toString(i+1) + "/" + Integer.toString(this._cersScript.length) + "----");

            sbr.append("\n");


            // ----------
            // 1) owner
            sbr.append(JarSignedSys._s_strDumpMoreOwner);                  // ("Owner=");

            sbr.append("=" + _getSubject(cerCur));

            sbr.append("\n");

            // ----------
            // 2) issuer
            sbr.append(JarSignedSys._s_strDumpMoreIssuer);

            sbr.append("=" + _getIssuer(cerCur));
            sbr.append("\n");

            // ----------
            // 3) version
            sbr.append(JarSignedSys._s_strDumpMoreVersion);

            sbr.append("=" + _getVersion(cerCur));
            sbr.append("\n");

            // ----------
            // 4) serial number
            sbr.append(JarSignedSys._s_strDumpMoreSerialNumber);

            sbr.append("=" + _getSerialNumber(cerCur));
            sbr.append("\n");

            // ----------
            // 5) start date
            sbr.append(JarSignedSys._s_strDumpMoreDateStart);

            sbr.append("=" + _getDateStart(cerCur));
            sbr.append("\n");

            // ----------
            // 6) end date
            sbr.append(JarSignedSys._s_strDumpMoreDateEnd);

            sbr.append("=" + _getDateEnd(cerCur));
            sbr.append("\n");

            // ----------
            // 7) type
            sbr.append(JarSignedSys._s_strDumpMoreType);

            sbr.append("=" + _getType(cerCur));
            sbr.append("\n");



            // ----------
            // 8) algorithm signature
            sbr.append(JarSignedSys._s_strDumpMoreSignatureAlgorithm);

            sbr.append("=" + _getSignatureAlgo(cerCur));
            sbr.append("\n");


            // ----------
            // 9) fingerprints
            sbr.append(JarSignedSys._s_strDumpMoreFingerprints);

            sbr.append("=" + _getCertFingerprints(cerCur));
            sbr.append("\n");


            // ---
            // END
            sbr.append("---- ");

            sbr.append(JarSignedSys._s_strDumpMoreCertEnd);

            sbr.append(Integer.toString(i+1) + "/" + Integer.toString(this._cersScript.length) + " ----");
            sbr.append("\n");


        }


        // ending
        return sbr.toString();
    }**/

    public String dumpCheckSignatures()
    {
        String strMethod = "dumpCheckSignatures()";

        boolean blnOk = true;

        StringBuffer sbr = new StringBuffer();

        if (this._jfe == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._jfe");
            return null;
        }

        Enumeration enu = this._jfe.entries();

        while (enu.hasMoreElements())
        {
            JarEntry jeyCur = (JarEntry) enu.nextElement();

            if (jeyCur == null)
                continue;

            if (jeyCur.isDirectory())
            {
                if (jeyCur.getName().startsWith(com.google.code.p.keytooliui.shared.util.jar.S_Manifest.f_s_strDirParentManifest))
                    sbr.append("jarsigner's specific directory: " + jeyCur.getName());
                else
                    sbr.append("directory: " + jeyCur.getName());

                sbr.append("\n");
                continue;
            }

            String sEntry = jeyCur.getName();

            if (sEntry.startsWith(com.google.code.p.keytooliui.shared.util.jar.S_Manifest.f_s_strDirParentManifest))
            {
                sbr.append("jarsigner's specific file: " + jeyCur.getName());
                sbr.append("\n");
                continue;
            }

            // ---------

            if (! _readJarEntry(this._jfe, jeyCur))
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                //return false;
                sbr.append("file: " + JarSignedSys._s_strDumpCertWrong + jeyCur.getName());
                sbr.append("\n");
                blnOk = false;
                continue;
            }

            Certificate[] cersCur = _getCertificates(jeyCur);

            if (cersCur != null)
            {
                if (cersCur.length == 0)
                {
                    MySystem.s_printOutError(this, strMethod, "cersCur.length == 0");
                    sbr.append("file: " + JarSignedSys._s_strDumpCertWrong + jeyCur.getName());
                    sbr.append("\n");
                    blnOk = false;
                    continue;
                }

                if (! (cersCur[cersCur.length-1] instanceof X509Certificate))
                {
                    MySystem.s_printOutError(this, strMethod, "failed");
                    sbr.append("file: " + JarSignedSys._s_strDumpCertWrong + jeyCur.getName());
                    sbr.append("\n");
                    blnOk = false;
                    continue;
                }

                if (! _verify(cersCur[cersCur.length-1]))
                {
                    MySystem.s_printOutError(this, strMethod, "failed");
                    sbr.append("file: " +  JarSignedSys._s_strDumpCertWrong + jeyCur.getName());
                    sbr.append("\n");
                    blnOk = false;
                    continue;
                }
            }

            else
            {
                MySystem.s_printOutError(this, strMethod, "nil cersCur");
                sbr.append("file: " + JarSignedSys._s_strDumpCertWrong + jeyCur.getName());
                sbr.append("\n");
                blnOk = false;
                continue;
            }

            // ---------


            sbr.append("file: " +  JarSignedSys._s_strDumpCertOk + jeyCur.getName());
            sbr.append("\n");
        }

        // --
        sbr.append("\n\n");

        if (blnOk)
            sbr.append(JarSignedSys._s_strDumpCheckOk);
        else
            sbr.append(JarSignedSys._s_strDumpCheckWrong);

        // ending
        return sbr.toString();
    }

    public boolean init()
    {
        String strMethod = "init()";

        // 1) load jar

        this._jfe = _getJarFile();

        if (this._jfe == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._jfe");
            return false;
        }

        // 2) check for ZipException

        if (! _checkForZipException())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        // 3) --

        JarEntry jeyScript = _getJarEntry(
          this._jfe,
          com.google.code.p.keytooliui.shared.io.FileJar.f_s_strPathRelShortDocRcrScript +
          "." +
          com.google.code.p.keytooliui.shared.io.S_FileExtension.f_s_strJarDocRcrScript
          );

        if (jeyScript == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil jeyScript");
            return false;
        }

        if (! _readJarEntry(this._jfe, jeyScript))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        this._cersScript = _getCertificates(jeyScript);

        if (this._cersScript != null)
        {
            if (this._cersScript.length == 0)
            {
                MySystem.s_printOutError(this, strMethod, "this._cersScript.length == 0");
                return false;
            }

            if (! _verify(this._cersScript[this._cersScript.length-1]))
            {
                MySystem.s_printOutWarning(strMethod, "failed");
                return false;
            }

            this._blnSignedX509CertificateDoc = true;
        }

        // ending
        return true;
    }


    public JarSignedSys(java.awt.Component cmpFrameOwner, String strPathAbsoluteJar)
    {
        this._cmpFrameOwner = cmpFrameOwner;
 
        this._strPathAbsoluteJar = strPathAbsoluteJar;
    }

    // -------
    // PRIVATE

    private java.awt.Component _cmpFrameOwner = null;
 

    private String _strPathAbsoluteJar = null;
    private JarFile _jfe = null;
    private boolean _blnSignedX509CertificateDoc = false;
    private Certificate[] _cersScript = null;


    private JarFile _getJarFile()
    {
        String strMethod = "_getJarFile()";

        if (this._strPathAbsoluteJar == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._strPathAbsoluteJar");
            return null;
        }

        // ----


        JarFile jfe = null;
        boolean blnSigned = true;

        try
        {
           jfe = new JarFile(this._strPathAbsoluteJar, blnSigned);
        }

        catch(FileNotFoundException excFileNotFound)
        {
            excFileNotFound.printStackTrace();
            MySystem.s_printOutWarning(this, strMethod, "excFileNotFound caught");

            OPAbstract.s_showDialogWarning(
                this._cmpFrameOwner,
                JarSignedSys._s_strDialogInitFailedBodyFNF + "\n" + this._strPathAbsoluteJar);

            return null;
        }

        catch (IOException excIO)
        {
            excIO.printStackTrace();
            MySystem.s_printOutWarning(this, strMethod, "excIO caught");

            OPAbstract.s_showDialogWarning(this._cmpFrameOwner,
                JarSignedSys._s_strDialogInitFailedBodyIO + "\n" + this._strPathAbsoluteJar);

            return null;
        }

        return jfe;
    }

    private JarEntry _getJarEntry(JarFile jfe, String strEntryName)
    {
        String strMethod = "_getJarEntry(jfe, strEntryName)";

        if (jfe==null || strEntryName==null)
        {
            MySystem.s_printOutError(this, strMethod, "nil arg");
            return null;
        }

        Enumeration enuKey = jfe.entries();

        if (enuKey == null) // empty hashtable
        {
            MySystem.s_printOutError(this, strMethod, "nil enuKey");
		    return null;
		}

		while (enuKey.hasMoreElements())
        {
            JarEntry jeyCur = (JarEntry)enuKey.nextElement();

            if (jeyCur == null)
                continue;

            String strEntryCur = jeyCur.getName();

            if (strEntryCur.compareTo(strEntryName) == 0)
            {
                return jeyCur;
            }
        } // end of while

        MySystem.s_printOutError(this, strMethod, "not found, strEntryName=" + strEntryName);
        return null;
    }

    private boolean _readJarEntry(JarFile jfe, JarEntry jey)
    {
        String strMethod = "_readJarEntry(jfe, jey)";

        if (jfe==null || jey==null)
        {
            MySystem.s_printOutError(this, strMethod, "nil arg");
            return false;
        }

        try
        {
            InputStream is = jfe.getInputStream(jey);

            int c;

            while ((c = is.read()) != -1)
            {
                //do nothing here, just to make sure the whole stream is read
            }
        }

        catch(IOException excIO)
        {
            excIO.printStackTrace();
            MySystem.s_printOutWarning(this, strMethod, "excIO caught");

            OPAbstract.s_showDialogWarning(
                this._cmpFrameOwner,
              
                JarSignedSys._s_strDialogInitFailedBodyIO + "\n" + this._strPathAbsoluteJar);


            return false;
        }

        catch(SecurityException excSecurity)
        {
            excSecurity.printStackTrace();
            MySystem.s_printOutWarning(this, strMethod, "excSecurity caught");

            OPAbstract.s_showDialogWarning(
                this._cmpFrameOwner,
             
                JarSignedSys._s_strDialogInitFailedBodySecurity + "\n" + this._strPathAbsoluteJar);


            return false;
        }

        return true;
    }

    /**
        if any error, exiting
    **/
    private Certificate[] _getCertificates(JarEntry jey)
    {
        String strMethod = "_getCertificates(jey)";

        if (jey == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil jey");
        }

        Certificate[] cers = jey.getCertificates();

        if(cers == null)
        {
            //MySystem.s_printOutTrace(this, strMethod, "nil cers");
            return null;
        }

        if (cers.length == 0)
        {
            MySystem.s_printOutExit(this, strMethod, "cers.length == 0");
        }



        // ending
        return cers;
    }

    /**
        extracts O from issuer or subject
    **/
    private String _getO(String str)
    {
        String strMethod = "_getO(str)";

        final String f_strKeyWordStart = "O=";
        final String f_strKeyWordDelimiter = ",";

        if (str == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil arg");
            return null;
        }

        int intIDKeyWordStart = str.indexOf(f_strKeyWordStart);

        if (intIDKeyWordStart == -1)
            return JarSignedSys._s_strDumpMoreUnknown;

        String strCN = null;

        try
        {
            strCN = str.substring(intIDKeyWordStart);
        }

        catch(IndexOutOfBoundsException excIndexOutOfBounds)
        {
            excIndexOutOfBounds.printStackTrace();
            MySystem.s_printOutWarning(this, strMethod, "excIndexOutOfBounds caught");
            return JarSignedSys._s_strDumpMoreUnknown;
        }

        if (strCN == null)
        {
            MySystem.s_printOutWarning(this, strMethod, "nil strCN");
            return JarSignedSys._s_strDumpMoreUnknown;
        }

        int intIDKeyWordDelimiter = strCN.indexOf(f_strKeyWordDelimiter);

        if (intIDKeyWordDelimiter == -1)
            return strCN;

        try
        {
            strCN = strCN.substring(f_strKeyWordStart.length(), intIDKeyWordDelimiter);
        }

        catch(IndexOutOfBoundsException excIndexOutOfBounds)
        {
            excIndexOutOfBounds.printStackTrace();
            MySystem.s_printOutWarning(this, strMethod, "excIndexOutOfBounds caught");
            return JarSignedSys._s_strDumpMoreUnknown;
        }

        if (strCN == null)
        {
            MySystem.s_printOutWarning(this, strMethod, "nil strCN");
            return JarSignedSys._s_strDumpMoreUnknown;
        }

        return strCN;
    }

    private boolean _verify(Certificate cer)
    {
        String strMethod = "_verify(cer)";

        if (cer == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil arg");
        }

        java.security.PublicKey pky = cer.getPublicKey();

        if (pky == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil arg");
        }

        try
        {
            cer.verify(pky);
        }


        catch(CertificateException excCertificate)
        {
            excCertificate.printStackTrace();
            MySystem.s_printOutWarning(this, strMethod, "excCertificate caught");
            return false;
        }

        catch(java.security.NoSuchAlgorithmException excNoSuchAlgorithm)
        {
            excNoSuchAlgorithm.printStackTrace();
            MySystem.s_printOutWarning(this, strMethod, "excNoSuchAlgorithm caught");
            return false;
        }

        catch(java.security.InvalidKeyException excInvalidKey)
        {
            excInvalidKey.printStackTrace();
            MySystem.s_printOutWarning(this, strMethod, "excInvalidKey caught");
            return false;
        }

        catch(java.security.NoSuchProviderException excNoSuchProvider)
        {
            excNoSuchProvider.printStackTrace();
            MySystem.s_printOutWarning(this, strMethod, "excNoSuchProvider caught");
            return false;
        }

        catch(java.security.SignatureException excSignature)
        {
            excSignature.printStackTrace();
            MySystem.s_printOutWarning(this, strMethod, "excSignature caught");
            return false;
        }

        //MySystem.s_printOutTrace(this, strMethod, "OK");
        // ending
        return true;
    }


    private boolean _checkForZipException()
    {
        String strMethod = "_checkForZipException()";

        if (this._jfe == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil this._jfe");
        }

        Enumeration enuKey = this._jfe.entries();

        if (enuKey == null) // empty hashtable
        {
            MySystem.s_printOutError(this, strMethod, "nil enuKey");
		    return false;
		}

		try
		{
	        while (enuKey.hasMoreElements())
		    {
	            JarEntry jeyCur = (JarEntry) enuKey.nextElement();

		        if (jeyCur == null)
		        {
		            MySystem.s_printOutError(this, strMethod, "nil jeyCur");
    		        return false;
		        }

		        InputStream ismCur = this._jfe.getInputStream(jeyCur);

		        if (ismCur == null)
		        {
		            MySystem.s_printOutError(this, strMethod, "nil ismCur");
    		        return false;
		        }
		    }
        }

        catch(java.util.zip.ZipException excZip)
        {
            excZip.printStackTrace();
            MySystem.s_printOutWarning(this, strMethod, "excZip caught");

            OPAbstract.s_showDialogWarning(this._cmpFrameOwner, JarSignedSys._s_strDialogInitFailedBodyZip + "\n" + this._strPathAbsoluteJar);

    	    return false;
        }

        catch(IOException excIO)
        {
            excIO.printStackTrace();
            MySystem.s_printOutWarning(this, strMethod, "excIO caught");

            OPAbstract.s_showDialogWarning(this._cmpFrameOwner, JarSignedSys._s_strDialogInitFailedBodyIO + "\n" + this._strPathAbsoluteJar);

    	    return false;
        }

        catch(SecurityException excSecurity)
        {
            excSecurity.printStackTrace();
            MySystem.s_printOutWarning(this, strMethod, "excSecurity caught");

            OPAbstract.s_showDialogWarning(this._cmpFrameOwner, JarSignedSys._s_strDialogInitFailedBodySecurity + "\n" + this._strPathAbsoluteJar);

    	    return false;
        }

        // ------------

        // ending
        return true;
    }
}