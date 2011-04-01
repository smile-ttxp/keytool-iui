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
 
 
package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**   
    "JSR" means "JarSigner"

    Known subclasses:
    . kst: KTLKprOpenVerifyJks
    . kst: KTLKprOpenVerifyJceks
    . kst: KTLKprOpenVerifyPkcs12
**/

import java.security.cert.Certificate;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import com.google.code.p.keytooliui.shared.lang.*;
//import com.google.code.p.keytooliui.shared.util.jarsigner.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

// --
// --
import java.security.KeyStore;
// --

import java.awt.*;
import java.io.*;
import java.util.jar.*;
import java.util.*;
import java.util.zip.*;

abstract public class KTLKprOpenVerifyAbs extends KTLKprOpenAbs
{
    // --------------
    // STATIC PRIVATE
    
    static private String _s_strDlgWarnBodyFNF = null;
    static private String _s_strDlgWarnBodyIO = null;
    static private String _s_strDlgWarnBodyZip = null;
    static private String _s_strDlgWarnBodySecurity = null;
    
    
    static private String _s_strDumpCheckOk = "Signed file verified.";
    
    // ------
    // STATIC

    static
    {
        String strWhere = "com.google.code.p.keytooliui.ktl.util.jarsigner.KTLKprOpenVerifyAbs";
        
        try
        {
            String strBundleFileShort =
                com.google.code.p.keytooliui.ktl.AppMainUIAbs.f_s_strBundleDir +
                ".KTLKprOpenVerifyAbs" // class name
                ;

            ResourceBundle rbeResources = ResourceBundle.getBundle(strBundleFileShort, 
                Locale.getDefault());
                
            KTLKprOpenVerifyAbs._s_strDlgWarnBodyFNF = rbeResources.getString("dlgWarnBodyFNF");
            KTLKprOpenVerifyAbs._s_strDlgWarnBodyIO = rbeResources.getString("dlgWarnBodyIO");
            KTLKprOpenVerifyAbs._s_strDlgWarnBodyZip = rbeResources.getString("dlgWarnBodyZip");
            KTLKprOpenVerifyAbs._s_strDlgWarnBodySecurity = rbeResources.getString("dlgWarnBodySecurity");
            
        }
        
        catch (MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught");
        }
    } 
 
    
    // ---------
    // PROTECTED

    

    protected KTLKprOpenVerifyAbs(
        Frame frmOwner,
        String strTitleAppli,
        String strPathAbsOpenKst, // nil value allowed (in case of use as verifier: optional)
        char[] chrsPasswdOpenKst,
        String strProviderKst,
        String strPathAbsSignedJar,
        boolean blnEntrySize,
        boolean blnEntryDate,
        boolean blnEntryCertsType,
        boolean blnEntryCertsX509AlgoName,
        boolean blnEntryCertsX509SubjectDN
        ) 
    {        
        super(
            frmOwner, 
            strTitleAppli, 
            strPathAbsOpenKst, 
            chrsPasswdOpenKst, 
            strProviderKst
            );
            
        this._strPathAbsOpenSignedJar = strPathAbsSignedJar;
        
        this._blnEntrySize = blnEntrySize;
        this._blnEntryDate = blnEntryDate;
        
        this._blnEntryCertsType = blnEntryCertsType;
        this._blnEntryCertsX509AlgoName = blnEntryCertsX509AlgoName;
        this._blnEntryCertsX509SubjectDN = blnEntryCertsX509SubjectDN;
    }
    
    /**
        if any code error, exit
        else if any other error, show warning-error dialog, then return false
        else return true
    **/
    protected boolean _doJob_(
        KeyStore kstOpen // nil value allowed
        )
    {
        String strMethod = "_doJob_(kstOpen)";
        
        /*if (true)
        {
            MySystem.s_printOutError(this, strMethod, "PENDING ...");
            return false;
        }*/
        
        // ----
        // x) load jar
        JarFile jfe = _getJarFile();
        
        if (jfe == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil jfe");
            return false;
        }
        
        // ----
        
        // 2) check for ZipException
        if (! _checkForZipException(jfe))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // 3) get manifest
        Manifest manManifest = null;
        
        try
        {   
            manManifest = jfe.getManifest();
        }
        
        catch (IOException excIO)
        {
            excIO.printStackTrace();
            MySystem.s_printOutWarning(this, strMethod, "excIO caught");
            
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, 
                super._strTitleAppli_, 
                KTLKprOpenVerifyAbs._s_strDlgWarnBodyIO + "\n" + this._strPathAbsOpenSignedJar);
                
            return false;
        }
        
        // -- if no manifest (JAR without manifest file), means not a signed JAR
        
        if (manManifest == null)
        {
            MySystem.s_printOutWarning(this, strMethod, "nil manManifest, this._strPathAbsOpenSignedJar=" + this._strPathAbsOpenSignedJar);
            
            String strBody = "No manifest found in:";
            strBody += "\n  ";
            strBody += this._strPathAbsOpenSignedJar;
            strBody += "\n\n";
            strBody += "Not a signed JAR file!";
            
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, 
                super._strTitleAppli_, 
                strBody);
                
            return false;
        }
        
        
        
        // 3) read all entries
        
        String strDump = _dumpIt(jfe, manManifest, kstOpen);
        
        if (strDump == null)
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // 4) show result in separate window
        
         
        com.google.code.p.keytooliui.shared.swing.dialog.DViewString vsg = new com.google.code.p.keytooliui.shared.swing.dialog.DViewString(
            super._frmOwner_,
            super._strTitleAppli_ + " - " + "verify signed file",
            strDump);
                    
        if (! vsg.init())
            MySystem.s_printOutExit(this, strMethod, "failed");
                
        vsg.setVisible(true);
        
        // ----
        
        // --
        return true;
    }
    
    // -------
    // PRIVATE
    
    private String _strPathAbsOpenSignedJar = null;
    
    private boolean _blnEntryCertsType = false;
    private boolean _blnEntryCertsX509AlgoName = false;
    private boolean _blnEntryCertsX509SubjectDN = false;
    
    private boolean _blnEntrySize = true;
    private boolean _blnEntryDate = true;
    
    private JarFile _getJarFile()
    {
        String strMethod = "_getJarFile()";
        
        if (this._strPathAbsOpenSignedJar == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._strPathAbsOpenSignedJar");
            return null;
        }
        
        // ----
        
        
        JarFile jfe = null;
        boolean blnSigned = true;
        
        try
        {
           jfe = new JarFile(this._strPathAbsOpenSignedJar, blnSigned);
        }
            
        catch(FileNotFoundException excFileNotFound)
        {
            excFileNotFound.printStackTrace();
            MySystem.s_printOutWarning(this, strMethod, "excFileNotFound caught");
            
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, 
                super._strTitleAppli_, 
                KTLKprOpenVerifyAbs._s_strDlgWarnBodyFNF + "\n" + this._strPathAbsOpenSignedJar);
            
            return null;
        }
            
        catch (IOException excIO)
        {
            excIO.printStackTrace();
            MySystem.s_printOutWarning(this, strMethod, "excIO caught");
            
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, 
                super._strTitleAppli_, 
                KTLKprOpenVerifyAbs._s_strDlgWarnBodyIO + "\n" + this._strPathAbsOpenSignedJar);
            
            return null;
        }
        
        return jfe;
    }
    
    private boolean _checkForZipException(JarFile jfe)
    {
        String strMethod = "_checkForZipException(jfe)";
        
        if (jfe == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil jfe");
        }
        
        Enumeration enuKey = jfe.entries();
        
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
		        
		        InputStream ismCur = jfe.getInputStream(jeyCur);
		        
		        if (ismCur == null)
		        {
		            MySystem.s_printOutError(this, strMethod, "nil ismCur");
    		        return false;
		        }
		    }
        }
        
        catch(ZipException excZip)
        {
            excZip.printStackTrace();
            MySystem.s_printOutWarning(this, strMethod, "excZip caught");
            
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, 
                super._strTitleAppli_, 
                KTLKprOpenVerifyAbs._s_strDlgWarnBodyZip + "\n" + this._strPathAbsOpenSignedJar);

    	    return false;
        }
        
        catch(IOException excIO)
        {
            excIO.printStackTrace();
            MySystem.s_printOutWarning(this, strMethod, "excIO caught");
            
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, 
                super._strTitleAppli_, 
                KTLKprOpenVerifyAbs._s_strDlgWarnBodyIO + "\n" + this._strPathAbsOpenSignedJar);

    	    return false;
        }
        
        catch(SecurityException excSecurity)
        {
            excSecurity.printStackTrace();
            MySystem.s_printOutWarning(this, strMethod, "excSecurity caught");
            
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, 
                super._strTitleAppli_, 
                KTLKprOpenVerifyAbs._s_strDlgWarnBodySecurity + "\n" + this._strPathAbsOpenSignedJar);

    	    return false;
        }
        
        // ------------
        
        // ending
        return true;
    }
    
    // TEMPO
    private String _dumpIt(
        JarFile jfe, 
        Manifest manManifest,
        KeyStore kstOpen // nil value allowed
        )
    { 
        String strMethod = "_dumpIt(jfe, manManifest, kstOpen)";
        StringBuffer sbr = new StringBuffer();
        
        if (jfe == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil jfe");
            return null;
        }
        
        if (manManifest == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil manManifest");
            return null;
        }
        
        boolean blnAtLeastOneWrongDate = false;
        Enumeration enuJRY = jfe.entries();
        
        while (enuJRY.hasMoreElements())
        { 
            JarEntry jeyCur = (JarEntry) enuJRY.nextElement(); 
                
            if (jeyCur == null) 
                continue;
            
            if (jeyCur.isDirectory())
            {
                if (jeyCur.getName().startsWith(com.google.code.p.keytooliui.shared.util.jar.S_Manifest.f_s_strDirParentManifest))
                    sbr.append("DIRECTORY (jarsigner's specific): " + jeyCur.getName());
                else
                    sbr.append("DIRECTORY: " + jeyCur.getName());
                
                sbr.append("\n");
                sbr.append("\n");
                continue;
            }
           
            String sEntry = jeyCur.getName();
            
            if (sEntry.startsWith(com.google.code.p.keytooliui.shared.util.jar.S_Manifest.f_s_strDirParentManifest))
            {
                sbr.append("FILE (jarsigner's specific): " + jeyCur.getName());
                sbr.append("\n");
                sbr.append("\n");
                continue;
            }
            
            // ---------
            
            if (! _readJarEntry(jfe, jeyCur))
            {
                MySystem.s_printOutError(this, strMethod, "failed to read entry, jeyCur.getName()=" + jeyCur.getName() + ", this._strPathAbsOpenSignedJar=" + this._strPathAbsOpenSignedJar);
                return null;
            }
            
            java.security.cert.Certificate[] cersCur = _getCertificates(jeyCur);
            
            
            
            //    BUG ID [X] fixed, july 10, 2003
              //  modif coz bug:
               // if entry is file of size 0 byte (eg: empty acii file),
               // then was showing  "Got an enty without certificcate ..., File is unsigned ...
            
            
            boolean blnEmptyEntry = true;
            
            
            
            if (jeyCur.getSize() > 0)
                blnEmptyEntry = false;
            
            if (cersCur==null && !blnEmptyEntry)
            {
                // unsigned
                MySystem.s_printOutWarning(
                    this, 
                    strMethod, 
                    "cersCur == null, jeyCur.getName()=" + 
                    jeyCur.getName() + 
                    ", jeyCur.getSize()=" + 
                    jeyCur.getSize() + 
                    ", this._strPathAbsOpenSignedJar=" + 
                    this._strPathAbsOpenSignedJar);
                
                String strDump = "Got an entry without certificate:";
                strDump += "\n" + "  " + jeyCur.getName();
                
                strDump += "\n\n" + "File is unsigned:";
                strDump += "\n" + "  " + this._strPathAbsOpenSignedJar;
                return strDump;
            }
            
            // 
            else
            {
                
                if (cersCur != null)
                {
                    // contains certificate(s), verifying
                    
                    if (cersCur.length == 0)
                    {
                        MySystem.s_printOutExit(this, strMethod, "cersCur!=null && cersCur.length == 0, jeyCur.getName()=" + jeyCur.getName());
                    }
                   
                    java.security.cert.Certificate cerLast = cersCur[cersCur.length-1];
                    
                    if (! _verify(cerLast))
                    {
                        MySystem.s_printOutError(
                            this, strMethod, 
                            "failed, jeyCur.getName()=" + jeyCur.getName());
                                
                        return null;
                    }
                }
               
                // ----
                if (cersCur != null)
                    sbr.append("FILE: " +  "S");
                else
                    sbr.append("FILE: " +  " ");
                
                if (_entryListedInManifest(jeyCur, manManifest))
                    sbr.append("M");
                else
                    sbr.append(" ");
                    
                if (cersCur!=null && _certInKeystore(cersCur, kstOpen))
                    sbr.append("K");
                else
                    sbr.append(" ");
                    
                if (this._blnEntrySize)
                {
                    String str = Long.toString(jeyCur.getSize());
			        
                    for (int i=6-str.length(); i>0; --i) 
                    {
                        sbr.append(" ");
                    }

                    sbr.append(str);
                    sbr.append(" ");
                }
                
                if (this._blnEntryDate)
                {
                    Date dte = new Date(jeyCur.getTime());
                    String str = dte.toString();
                    sbr.append(str);  
                    sbr.append(" ");
                }
                
                sbr.append(" " + jeyCur.getName());
                
                // ----
                
                if (cersCur != null)
                {
                    if (this._blnEntryCertsType || this._blnEntryCertsX509AlgoName || this._blnEntryCertsX509SubjectDN)
                    {
                        // -----------------------
                        // iterating through certs
                        
                        for (int i=0; i<cersCur.length; i++)
                        {
                            sbr.append("\n");
                            
                            // type
                            if (this._blnEntryCertsType)
                            {
                                sbr.append("  ");
                                sbr.append(cersCur[i].getType());
                            }
                            
                            if (this._blnEntryCertsX509AlgoName || this._blnEntryCertsX509SubjectDN)
                            {
                                if (cersCur[i] instanceof java.security.cert.X509Certificate)
                                {
                                    java.security.cert.X509Certificate crtX509 = (java.security.cert.X509Certificate) cersCur[i];
                                
                                    if (this._blnEntryCertsX509AlgoName)
                                    {
                                        // algo
                                        sbr.append("  ");
                                        sbr.append(crtX509.getSigAlgName());
                                    }
                                    
                                    if (this._blnEntryCertsX509SubjectDN)
                                    {
                                        // subjectDN, excerpt from j2sdk doc: Gets the subject (subject distinguished name) value from the certificate
                                        sbr.append("  ");
                                        sbr.append(crtX509.getSubjectDN().toString());
                                    }
                                }
                            }
                            // end if
                            
                            // !!!!!!!!!!!!!!!!!!!!!!!!
                            
                            //check for valid date
                            X509Certificate crtX509 = (X509Certificate) cersCur[i];
                            boolean blnValidDate = true;
                            String strMessage = null;
                            
                            try
                            {
                                crtX509.checkValidity();
                            }
                            
                            catch(CertificateExpiredException exc) 
                            {
                                blnValidDate = false;
                                strMessage = exc.getMessage();
                            }
                            
                            catch(CertificateNotYetValidException exc)
                            {
                                blnValidDate = false;
                                strMessage = exc.getMessage();
                            }
                            
                            if (! blnValidDate)
                            {
                                blnAtLeastOneWrongDate = true;
                                sbr.append("\n");
                                sbr.append("     ==> WARNING: not a valid date, " + strMessage);
                            }
                        
                        }
                    }
                } // END OF if (cersCur != null)
                
                sbr.append("\n\n");
                
            }
        }
        
        // --
        sbr.append("\n\n");

        sbr.append("    S = Entry contains certificate(s), signature(s) was verified");
        sbr.append("\n");
        sbr.append("    M = Entry is listed in manifest");
        
        if (kstOpen != null)
        {
            sbr.append("\n");
            sbr.append("    K = At least one entry's certificate found in keystore");
        }
         // --
        sbr.append("\n\n");
        
        if (blnAtLeastOneWrongDate)
        {
            sbr.append("!!!! AT LEAST ONE WRONG DATE FOUND IN CERTIFICATES !!!!");
            sbr.append("\n\n");
        }
        
        sbr.append(_s_strDumpCheckOk);
        
        // ending
        return sbr.toString();
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
            InputStream ism = jfe.getInputStream(jey); 
            
            int intChar; 
                        
            while ((intChar = ism.read()) != -1)
            { 
                //do nothing here, just to make sure the whole stream ism read 
            } 
        }
        
        catch(IOException excIO)
        {
            excIO.printStackTrace();
            MySystem.s_printOutWarning(this, strMethod, "excIO caught");
            
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, 
                super._strTitleAppli_, 
                KTLKprOpenVerifyAbs._s_strDlgWarnBodyIO + "\n" + this._strPathAbsOpenSignedJar);

            
            return false;
        }
        
        catch(SecurityException excSecurity)
        {
            excSecurity.printStackTrace();
            MySystem.s_printOutWarning(this, strMethod, "excSecurity caught");
            
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, 
                super._strTitleAppli_, 
                KTLKprOpenVerifyAbs._s_strDlgWarnBodySecurity + "\n" + this._strPathAbsOpenSignedJar);


            return false;
        }
        
        return true;
    }
    
    /**
        if any error, exiting
    **/
    private java.security.cert.Certificate[] _getCertificates(JarEntry jey)
    {
        String strMethod = "_getCertificates(jey)";
        
        if (jey == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil jey");
        }
               
        java.security.cert.Certificate[] cers = jey.getCertificates(); 
                        
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
    
    private boolean _verify(java.security.cert.Certificate cer)
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
        
        // beg tempo
        
        //byte[] bytsPublic = pky.getEncoded();
        /*StringBuffer sbr = new StringBuffer();
        
        for (int i=0; i<bytsPublic.length; i++)
        {
            if (sbr.length() != 0)
                sbr.append(", 0x");
            else
                sbr.append("0x");
            
            int intCur = (int) bytsPublic[i];
            String strCur = Integer.toString(intCur);
            sbr.append(strCur);
        }
        
        System.out.println("sbr.toString()=" + sbr.toString());
        */
        /*System.out.println("\nbytsPublic=" + bytsPublic);
        
        System.out.println("\nnew String(bytsPublic)=" + new String(bytsPublic));
        */
        // end tempo
        
        try
        {
            cer.verify(pky);
        }
                   
        catch(java.security.cert.CertificateException excCertificate)
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
    
    private boolean _entryListedInManifest(JarEntry jey, Manifest manManifest)
    {
        String strMethod = "_entryListedInManifest(jey, manManifest)";
        
        if (jey==null || manManifest==null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil arg");
        }
        
        if (manManifest.getEntries().size() < 1)
            return false;
     
        String strNameEntry = jey.getName();
        
        if (strNameEntry == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil strNameEntry");
        }
          

        if (manManifest.getAttributes(strNameEntry) != null)
            return true;
            
        if (manManifest.getAttributes("/" + strNameEntry) != null)
            return true;
            
        if (manManifest.getAttributes("./" + strNameEntry) != null)
            return true;
        
        // ---- 
        return false;
    }
    
    // TEMPO
    private boolean _certInKeystore(
        java.security.cert.Certificate[] cers, 
        KeyStore kst // nil value allowed
        ) 
    {
        String strMethod = "_certInKeystore(cers, kst)";
       
	    if (cers == null) // !!!!!!!!!! should not be allowed
	        return false;
	        
	    if (kst == null)
	        return false;
	        
	        
	    boolean blnResult = false;
	        
	    Hashtable<Certificate, String> storeHash = new Hashtable<Certificate, String>();

	    boolean blnFound = false;

	    for (int i=0; i<cers.length; i++) 
	    {
	        java.security.cert.Certificate cerCur = cers[i];
	        blnFound = false;
	        String strAlias = (String) storeHash.get(cerCur);

	        if (strAlias != null) 
	        {
		        if (strAlias.startsWith("("))
			        blnResult |= true; // IN_KEYSTORE;
		        //else if (strAlias.startsWith("["))
			      //  blnResult |= IN_SCOPE;
	        } 
	        
	        else 
	        {
		        if (kst != null) 
		        {
		            try 
		            {
			            strAlias = kst.getCertificateAlias(cerCur);
		            } 
		            
		            catch (java.security.KeyStoreException excKeyStore) 
		            {
			            // this never happens, because keystore has been loaded
		            }
		            
		            if (strAlias != null) 
		            {
			            storeHash.put(cerCur, "("+strAlias+")");
			            blnFound = true;
			            blnResult |= true; // IN_KEYSTORE;
		            }
		        }
		        
		        //if (!blnFound && (scope != null)) 
		        //{
		            //Identity id = scope.getIdentity(cerCur.getPublicKey());
		            
		            //if (id != null) 
		            //{
			          //  blnResult |= IN_SCOPE;
			        //    storeHash.put(cerCur, "["+id.getName()+"]");
		          //  }
		        //}
	        }
	    }
	    
	    return blnResult;
    }
    
}
