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
 
 
package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

MEMO: hiding this class while printing message
      eg:
      . no message like exc.printStacjTrace()
      . replacing 
          MySystem.s_printOutError(this, strMethod, ...)
        by
          MySystem.s_printOutError(strMethod, ...)
**/

import com.google.code.p.keytooliui.shared.swing.dialog.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.lang.system.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.io.*;
import com.google.code.p.keytooliui.shared.*;

// --
import java.security.Signature;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.InvalidKeyException;
// --
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
// --
import java.security.spec.X509EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
// --


import java.io.*;
import java.util.*;
import java.awt.*;
import java.util.jar.*;

public final class ChkRegUIJsr
{
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _f_s_strKeyName = "na" + "me =";
    private static final String _f_s_strKeyOrg = "or" + "g =";
    private static final String _f_s_strKeyProdId = "pr" + "o" + "d" + "I" + "d =";
    
    // eg: _sreg_xls11.jar
    private static final String _f_s_strNameLibReg = 
        "_" + "s" + "r" + "eg" + "_" + 
        System.getProperty("_appli.name.short") +
        Shared.f_s_strPackLibVersionXP +
        "." +
        com.google.code.p.keytooliui.shared.io.S_FileExtension.f_s_strJARDocument.toLowerCase();
    
    
    private static final String _f_s_strNameLicReg = "_res.properties";
    //private static final int _f_s_intNbDayEval = 3; // + 1; // Ob fus ca ting!
    private static final int _f_s_intNbDayEval = 3 + 1; // Ob fus ca ting!
    
    private static final String[] _f_s_strsPubKeyReg =
    {
        "48",
        "-126",
        "1",
        "-73",
        "48",
        "-126",
        "1",
        "44",
        "6",
        "7",
        "42",
        "-122",
        "72",
        "-50",
        "56",
        "4",
        "1",
        "48",
        "-126",
        "1",
        "31",
        "2",
        "-127",
        "-127",
        "0",
        "-3",
        "127",
        "83",
        "-127",
        "29",
        "117",
        "18",
        "41",
        "82",
        "-33",
        "74",
        "-100",
        "46",
        "-20",
        "-28",
        "-25",
        "-10",
        "17",
        "-73",
        "82",
        "60",
        "-17",
        "68",
        "0",
        "-61",
        "30",
        "63",
        "-128",
        "-74",
        "81",
        "38",
        "105",
        "69",
        "93",
        "64",
        "34",
        "81",
        "-5",
        "89",
        "61",
        "-115",
        "88",
        "-6",
        "-65",
        "-59",
        "-11",
        "-70",
        "48",
        "-10",
        "-53",
        "-101",
        "85",
        "108",
        "-41",
        "-127",
        "59",
        "-128",
        "29",
        "52",
        "111",
        "-14",
        "102",
        "96",
        "-73",
        "107",
        "-103",
        "80",
        "-91",
        "-92",
        "-97",
        "-97",
        "-24",
        "4",
        "123",
        "16",
        "34",
        "-62",
        "79",
        "-69",
        "-87",
        "-41",
        "-2",
        "-73",
        "-58",
        "27",
        "-8",
        "59",
        "87",
        "-25",
        "-58",
        "-88",
        "-90",
        "21",
        "15",
        "4",
        "-5",
        "-125",
        "-10",
        "-45",
        "-59",
        "30",
        "-61",
        "2",
        "53",
        "84",
        "19",
        "90",
        "22",
        "-111",
        "50",
        "-10",
        "117",
        "-13",
        "-82",
        "43",
        "97",
        "-41",
        "42",
        "-17",
        "-14",
        "34",
        "3",
        "25",
        "-99",
        "-47",
        "72",
        "1",
        "-57",
        "2",
        "21",
        "0",
        "-105",
        "96",
        "80",
        "-113",
        "21",
        "35",
        "11",
        "-52",
        "-78",
        "-110",
        "-71",
        "-126",
        "-94",
        "-21",
        "-124",
        "11",
        "-16",
        "88",
        "28",
        "-11",
        "2",
        "-127",
        "-127",
        "0",
        "-9",
        "-31",
        "-96",
        "-123",
        "-42",
        "-101",
        "61",
        "-34",
        "-53",
        "-68",
        "-85",
        "92",
        "54",
        "-72",
        "87",
        "-71",
        "121",
        "-108",
        "-81",
        "-69",
        "-6",
        "58",
        "-22",
        "-126",
        "-7",
        "87",
        "76",
        "11",
        "61",
        "7",
        "-126",
        "103",
        "81",
        "89",
        "87",
        "-114",
        "-70",
        "-44",
        "89",
        "79",
        "-26",
        "113",
        "7",
        "16",
        "-127",
        "-128",
        "-76",
        "73",
        "22",
        "113",
        "35",
        "-24",
        "76",
        "40",
        "22",
        "19",
        "-73",
        "-49",
        "9",
        "50",
        "-116",
        "-56",
        "-90",
        "-31",
        "60",
        "22",
        "122",
        "-117",
        "84",
        "124",
        "-115",
        "40",
        "-32",
        "-93",
        "-82",
        "30",
        "43",
        "-77",
        "-90",
        "117",
        "-111",
        "110",
        "-93",
        "127",
        "11",
        "-6",
        "33",
        "53",
        "98",
        "-15",
        "-5",
        "98",
        "122",
        "1",
        "36",
        "59",
        "-52",
        "-92",
        "-15",
        "-66",
        "-88",
        "81",
        "-112",
        "-119",
        "-88",
        "-125",
        "-33",
        "-31",
        "90",
        "-27",
        "-97",
        "6",
        "-110",
        "-117",
        "102",
        "94",
        "-128",
        "123",
        "85",
        "37",
        "100",
        "1",
        "76",
        "59",
        "-2",
        "-49",
        "73",
        "42",
        "3",
        "-127",
        "-124",
        "0",
        "2",
        "-127",
        "-128",
        "108",
        "82",
        "120",
        "21",
        "-44",
        "-99",
        "33",
        "-88",
        "29",
        "24",
        "95",
        "-13",
        "75",
        "94",
        "-56",
        "119",
        "-43",
        "-110",
        "-47",
        "-88",
        "-114",
        "-66",
        "21",
        "10",
        "-14",
        "122",
        "102",
        "-67",
        "26",
        "90",
        "7",
        "28",
        "64",
        "-100",
        "107",
        "-76",
        "86",
        "94",
        "-32",
        "36",
        "27",
        "-44",
        "-90",
        "14",
        "48",
        "121",
        "-102",
        "-47",
        "117",
        "-58",
        "13",
        "30",
        "-114",
        "-48",
        "82",
        "36",
        "-84",
        "-87",
        "20",
        "-38",
        "-74",
        "39",
        "119",
        "-20",
        "114",
        "127",
        "-48",
        "-3",
        "72",
        "-102",
        "-118",
        "-90",
        "-62",
        "119",
        "-32",
        "-117",
        "70",
        "-57",
        "-1",
        "-27",
        "-28",
        "125",
        "-116",
        "-94",
        "3",
        "84",
        "-21",
        "-74",
        "-104",
        "-49",
        "-101",
        "71",
        "69",
        "82",
        "76",
        "2",
        "-91",
        "-20",
        "-23",
        "-116",
        "71",
        "-2",
        "72",
        "60",
        "-118",
        "-34",
        "-127",
        "-69",
        "79",
        "-117",
        "-125",
        "42",
        "-7",
        "-82",
        "-107",
        "29",
        "-42",
        "-122",
        "58",
        "98",
        "-22",
        "-100",
        "65",
        "-49",
        "-104",
        "65",
        "67",
        "-70"
    };
    
    // ------
    // PUBLIC
    
    public String getLic() { return this._strLic; }
    public boolean isReg() { return this._blnIsReg; }
        
    public ChkRegUIJsr()
    {
    }
    
    /**
        
        if any error, return false, calling method should exit immediately
        
        1) check for valid reg
        
        2) if not valid reg, check for valid nbDaysEvaluation
        
    **/
    
    public boolean doJob()
    {
        String strMethod = "doJob()";
        
        
        
        // ---
      
        File fleDirAppli = S_FileSys.s_getPathAbsParentAppli(false); // ???? false v/s true
            
        if (fleDirAppli == null)
        {
            MySystem.s_printOutError(strMethod, "nil fleDirAppli");
            return false;
        }

        String strNameFolderLib = null;
        
        if (AppAbs.s_isDeployedWithJws())
            strNameFolderLib = S_SystemShared.f_s_strPrefixFolderJWS + AppMainAbs.f_s_strNameDirLibraries;
        else
            strNameFolderLib = AppMainAbs.f_s_strNameDirLibraries;
        
        // --
        
        File fleDirLib = new File(fleDirAppli, strNameFolderLib);
        
        
        // --
        
        if (! fleDirLib.exists())
        {
            MySystem.s_printOutError(strMethod, "! fleDirLib.exists(), fleDirLib.getAbsolutePath()=" +
                fleDirLib.getAbsolutePath());
                    
            return false;
        }
            
        if (! fleDirLib.isDirectory())
        {
            MySystem.s_printOutError(strMethod, "! fleDirLib.isDirectory(), fleDirLib.getAbsolutePath()=" +
                fleDirLib.getAbsolutePath());
                    
            return false;
        }
        
        
        // --
        
        File fleJarReg = new File(fleDirLib, ChkRegUIJsr._f_s_strNameLibReg);
            
        if (! fleJarReg.exists())
        {
            boolean blnOk = _doJobEval(fleDirAppli);
            
            if (! blnOk)
            {
                return blnOk;
            }
        }
            
        else
        {
            boolean blnOk = _doJobReg(fleJarReg);
                
            if (! blnOk)
            {
                String strBody = "Fai" + "led to" + " r" + "ec" + "over " + "l" + "ice" + "nse!";
                strBody += "\n" + com.google.code.p.keytooliui.shared.swing.panel.PHelpAboutAppli.s_strContactPoints;
                
                OPAbstract.s_showDialogError((Component) null, strBody);

                System.exit(1);
                
            }
                
            return blnOk;
        }
  
        return true;
    }
    
    // -------
    // PRIVATE
        
    
    private String _strLic = null; 
    private boolean _blnIsReg = false;
    
    
    private boolean _doJobReg(File fleJarReg)
    {
        String strMethod = "_doJobReg(fleJarReg)";
        
        if (fleJarReg == null)
        {
            MySystem.s_printOutError(strMethod, "nil fleJarReg");
            return false;
        }
        
        
        JarFile jfe = _getJarFile(fleJarReg);
        
        if (jfe == null)
        {
            MySystem.s_printOutError(strMethod, "nil jfe");
            return false;
        }
        
        // --
        
        if (! _checkForZipException(jfe))
        {
            MySystem.s_printOutError(strMethod, "failed");
            return false;
        }
        
        // --
        
        JarEntry jeyScript = _getJarEntry(jfe, ChkRegUIJsr._f_s_strNameLicReg);
        
        if (jeyScript == null)
        {
            MySystem.s_printOutError(strMethod, "nil jeyScript for " + ChkRegUIJsr._f_s_strNameLicReg);
            return false;
        }
        
        if (! _readJarEntry(jfe, jeyScript))
        {
            MySystem.s_printOutError(strMethod, "failed for " + ChkRegUIJsr._f_s_strNameLicReg);
            return false;
        }
        
        // --
        
        // first check cert bundled in signed jar's entry
        
        Certificate[] cersScript = _getCertificates(jeyScript);
        
        if (cersScript != null)
        {
            if (cersScript.length == 0)
            {
                MySystem.s_printOutError(strMethod, "cersScript.length == 0");
                return false;
            }
            
            if (! _verify(cersScript[cersScript.length-1]))
            {
                MySystem.s_printOutError(strMethod, "failed");
                return false;
            }
            
   
        }
        
        // then check entry with hard-coded public key
        
        // --
        
        byte[] bytsKeyPublic = new byte[ChkRegUIJsr._f_s_strsPubKeyReg.length];
        
        for (int i=0; i<ChkRegUIJsr._f_s_strsPubKeyReg.length; i++)
        {
            String str = ChkRegUIJsr._f_s_strsPubKeyReg[i];
        
            Byte byt = null;
            
            try
            {
                byt = Byte.decode(str);
            }
            
            catch(NumberFormatException excNumberFormat)
            {
                //excNumberFormat.printStackTrace();
                MySystem.s_printOutError(strMethod, "excNumberFormat caught");
                return false;
            }
            
            bytsKeyPublic[i] = byt.byteValue();
            
        }
        
        // --
        
        X509EncodedKeySpec spcX509 = new X509EncodedKeySpec(bytsKeyPublic);
        
        KeyFactory kfy = null;
        
        try
        {
            kfy = KeyFactory.getInstance(KTLAbs.f_s_strTypeKeypairDsa, "SUN");
        }
        
        catch(NoSuchAlgorithmException excNoSuchAlgorithmException)
        {
            //excNoSuchAlgorithmException.printStackTrace();
            MySystem.s_printOutError(strMethod, "excNoSuchAlgorithmException caught");
            return false;
        }
        
        catch(NoSuchProviderException excNoSuchProviderException)
        {
            //excNoSuchProviderException.printStackTrace();
            MySystem.s_printOutError(strMethod, "excNoSuchProviderException caught");
            return false;
        }
        
        // create public key
        
        PublicKey pkyPublic = null;
        
        try
        {
            pkyPublic = kfy.generatePublic(spcX509);
        }
        
        catch(InvalidKeySpecException excInvalidKeySpec)
        {
            //excInvalidKeySpec.printStackTrace();
            MySystem.s_printOutError(strMethod, "excInvalidKeySpec caught");
            return false;
        }
        
       
        
        if (pkyPublic == null)
        {
            MySystem.s_printOutError(strMethod, "nil pkyPublic");
            return false;
        }
        
        if (! _verify(cersScript[cersScript.length-1], pkyPublic))
        {
            MySystem.s_printOutError(strMethod, "failed");
            return false;
        }
        
        // -- OK: REGISTERED
               
        this._strLic = _getLicValid(fleJarReg);
        
        if (this._strLic == null)
        {
            MySystem.s_printOutError(strMethod, "nil this._strLic");
            return false;
        }
        
        
        
        
        
        
        // ending
        return true;
    }
    
    /**
        four days full-features, then appli blocking
    **/
    private boolean _doJobEval(File fleDirAppli)
    {
        String strMethod = "_doJobEval(fleDirAppli)";
                
        if (fleDirAppli == null)
        {
            MySystem.s_printOutError(strMethod, "nil fleDirAppli");
            return false;
        }
        
        File fleDirUsr = new File(fleDirAppli, FileLocation.f_strUser);
        
        if (! fleDirUsr.exists())
        {
            try
            {
                boolean blnOk = fleDirUsr.mkdir();
                        
                if (! blnOk)
                {
                    MySystem.s_printOutError(strMethod, "failed to make dir: " + FileLocation.f_strUser);
                    return false;
                }
            }
                    
            catch(Exception exc)
            {
                exc.printStackTrace();
                MySystem.s_printOutError(strMethod, "exc caught");
                return false;
            }
        }
        
        if (! fleDirUsr.isDirectory())
        {
            MySystem.s_printOutError(strMethod, "! fleDirUsr.isDirectory()"); // NO MORE INFO THERE!!
            return false;
        }
        
        Date dteCur = new Date();
        
        long lngDateFirstRun = fleDirUsr.lastModified();
        
        if (dteCur.before(new Date(lngDateFirstRun))) // prohibited
        {
            //MySystem.s_printOutError(strMethod, "TEMPO! THIS MESSAGE SHOULD BE HIDDEN dteCur.before(new Date(lngDateFirstRun))");
            System.exit(1);
        }
        
        
        lngDateFirstRun += ((long) ChkRegUIJsr._f_s_intNbDayEval)*24*60*60*1000; 
        Date dteAllowedEval = new Date(lngDateFirstRun);
        
        String strAllowedEval = MySystem.s_getDateFromTime(lngDateFirstRun);
        
        String strX = "E" + "v" + "al" + "uat" + "ion" + " " + "l" + "i" + "c" + "en" + "se"; // ob fus ca tin g
        
        if (dteCur.before(dteAllowedEval))
        {
            // show eval dialog, OK
                        
            String strY = "V" + "a" + "l" + "id" + " " + "t" + "il" + "l ";
            
            this._strLic = strX + ", " + strY.toLowerCase() + strAllowedEval;
            
            if (! _askQuestion(strAllowedEval))
                System.exit(0); // normal termination
                    
            return true; // installed reg file
        }
        
        else
        {
            if (! _askWarning(strAllowedEval))
                System.exit(0); // normal termination
                    
            return true; // installed reg file
        }
    }
    
    private JarFile _getJarFile(File fle)
    {
        String strMethod = "_getJarFile(fle)";
        
        if (fle == null)
        {
            MySystem.s_printOutError(strMethod, "nil fle");
            return null;
        }
        
        // ----
        
        
        JarFile jfe = null;
        boolean blnSigned = true;
        
        try
        {
           jfe = new JarFile(fle, blnSigned);
        }
            
        catch(FileNotFoundException excFileNotFound)
        {
            //excFileNotFound.printStackTrace();
            MySystem.s_printOutError(strMethod, "excFileNotFound caught");
            
            return null;
        }
            
        catch (IOException excIO)
        {
            //excIO.printStackTrace();
            MySystem.s_printOutError(strMethod, "excIO caught");

            return null;
        }        
        
        return jfe;
    }
    
    private JarEntry _getJarEntry(JarFile jfe, String strEntryName)
    {
        String strMethod = "_getJarEntry(jfe, strEntryName)";
        
        if (jfe==null || strEntryName==null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return null;
        }
        
        Enumeration enuKey = jfe.entries();
        
        if (enuKey == null) // empty hashtable
        {
            MySystem.s_printOutError(strMethod, "nil enuKey for " + strEntryName);
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
        
        MySystem.s_printOutError(strMethod, "not found for " + strEntryName);
        return null;
    }
    
    private boolean _readJarEntry(JarFile jfe, JarEntry jey)
    {
        String strMethod = "_readJarEntry(jfe, jey)";
        
        if (jfe==null || jey==null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
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
            //excIO.printStackTrace();
            MySystem.s_printOutError(strMethod, "excIO caught");
            
            return false;
        }
        
        catch(SecurityException excSecurity)
        {
            //excSecurity.printStackTrace();
            MySystem.s_printOutError(strMethod, "excSecurity caught");

            return false;
        }
        
        return true;
    }
    
    private boolean _verify(Certificate cer)
    {
        String strMethod = "_verify(cer)";
        
        if (cer == null)
        {
            MySystem.s_printOutError(strMethod, "nil cer");
            return false;
        }
        
        PublicKey pky = cer.getPublicKey(); 
        
        if (pky == null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return false;
        }
        
        try
        {
            cer.verify(pky);
        }
                            
                            
        catch(CertificateException excCertificate)
        {
            //excCertificate.printStackTrace();
            MySystem.s_printOutError(strMethod, "excCertificate caught");
            return false;
        }
                            
        catch(NoSuchAlgorithmException excNoSuchAlgorithm)
        {
            //excNoSuchAlgorithm.printStackTrace();
            MySystem.s_printOutError(strMethod, "excNoSuchAlgorithm caught");
            return false;
        }
                            
        catch(InvalidKeyException excInvalidKey)
        {
            //excInvalidKey.printStackTrace();
            MySystem.s_printOutError(strMethod, "excInvalidKey caught");
            return false;
        }
                            
        catch(NoSuchProviderException excNoSuchProvider)
        {
            //excNoSuchProvider.printStackTrace();
            MySystem.s_printOutError(strMethod, "excNoSuchProvider caught");
            return false;
        }
                            
        catch(SignatureException excSignature)
        {
            //excSignature.printStackTrace();
            MySystem.s_printOutError(strMethod, "excSignature caught");
            return false;
        }

        // ending
        return true;
    }
    
    private boolean _verify(Certificate cer, PublicKey pky)
    {
        String strMethod = "_verify(cer, pky)";
        
        if (cer==null || pky==null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return false;
        }
        
        try
        {
            cer.verify(pky);
        }
                            
                            
        catch(CertificateException excCertificate)
        {
            //excCertificate.printStackTrace();
            MySystem.s_printOutError(strMethod, "excCertificate caught");
            return false;
        }
                            
        catch(NoSuchAlgorithmException excNoSuchAlgorithm)
        {
            //excNoSuchAlgorithm.printStackTrace();
            MySystem.s_printOutError(strMethod, "excNoSuchAlgorithm caught");
            return false;
        }
                            
        catch(InvalidKeyException excInvalidKey)
        {
            //excInvalidKey.printStackTrace();
            MySystem.s_printOutError(strMethod, "excInvalidKey caught");
            return false;
        }
                            
        catch(NoSuchProviderException excNoSuchProvider)
        {
            //excNoSuchProvider.printStackTrace();
            MySystem.s_printOutError(strMethod, "excNoSuchProvider caught");
            return false;
        }
                            
        catch(SignatureException excSignature)
        {
            //excSignature.printStackTrace();
            MySystem.s_printOutError(strMethod, "excSignature caught");
            return false;
        }

        // ending
        return true;
    }
    
    private Certificate[] _getCertificates(JarEntry jey)
    {
        String strMethod = "_getCertificates(jey)";
        
        if (jey == null)
        {
            MySystem.s_printOutError(strMethod, "nil jey");
            return null;
        }
               
        Certificate[] cers = jey.getCertificates(); 
                        
        if(cers == null)
        { 
            MySystem.s_printOutError(strMethod, "nil cers");
            return null;
        }    
        
        if (cers.length == 0)
        {
            MySystem.s_printOutError(strMethod, "cers.length == 0");
            return null;
        }
        
        
        
        // ending
        return cers;
    }
    
    private boolean _checkForZipException(JarFile jfe)
    {
        String strMethod = "_checkForZipException()";
        
        if (jfe == null)
        {
            MySystem.s_printOutError(strMethod, "nil jfe");
            return false;
        }
        
        Enumeration enuKey = jfe.entries();
        
        if (enuKey == null) // empty hashtable
        {
            MySystem.s_printOutError(strMethod, "nil enuKey");
		    return false;
		}   
		
		try
		{
	        while (enuKey.hasMoreElements())
		    {
	            JarEntry jeyCur = (JarEntry) enuKey.nextElement();
    		    
		        if (jeyCur == null)
		        {
		            MySystem.s_printOutError(strMethod, "nil jeyCur");
    		        return false;
		        }
		        
		        InputStream ismCur = jfe.getInputStream(jeyCur);
		        
		        if (ismCur == null)
		        {
		            MySystem.s_printOutError(strMethod, "nil ismCur");
    		        return false;
		        }
		    }
        }
        
        catch(java.util.zip.ZipException excZip)
        {
            //excZip.printStackTrace();
            MySystem.s_printOutError(strMethod, "excZip caught");
           
    	    return false;
        }
        
        catch(IOException excIO)
        {
            //excIO.printStackTrace();
            MySystem.s_printOutError(strMethod, "excIO caught");
       
    	    return false;
        }
        
        catch(SecurityException excSecurity)
        {
            //excSecurity.printStackTrace();
            MySystem.s_printOutError(strMethod, "excSecurity caught");
         
    	    return false;
        }
        
        // --
        // ending
        return true;
    }
    
    /*
        MEMO: script starts as follow:
        <_reader_> [version]
        <document>
        docTitle ["the_title"-_void_]
    */
    private String _getLicValid(File fleJar)
    {
        String strMethod = "_getLicValid(fleJar)";
        
        StringBuffer sbr = new StringBuffer();
        
        if (fleJar == null)
        {
            MySystem.s_printOutError(strMethod, "nil fleJar");
            return null;
        }
	    
        if (! fleJar.exists())
        {
                    MySystem.s_printOutError(strMethod, "! fleJar.exists()");
                    return null;
        }

        if (! fleJar.canRead())
        {
                    MySystem.s_printOutError(strMethod, "! fleJar.canRead()");
                    return null;
        }

        // ----

        byte[] bytsBuffer = com.google.code.p.keytooliui.shared.io.FileJar.s_getFileResource(
            fleJar.getAbsolutePath(),
            ChkRegUIJsr._f_s_strNameLicReg);
        
        if (bytsBuffer == null)
        {
            MySystem.s_printOutError(strMethod, "nil bytsBuffer");
            return null;
        }
        
        // ----
        
        BufferedReader brrData = null;
  
        try
        {
            brrData = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytsBuffer))); 
        }
        
        catch (Exception exc)
        {
            //exc.printStackTrace();
            MySystem.s_printOutError(strMethod, "exc caught");
            return null;
        }
        
        // --
        
        String strName = null;
        String strOrg = null;
        String strProdId = null;
        boolean blnContinue = true;
        
        while (blnContinue == true)
        {
            if (strName!=null && strOrg!=null && strProdId!=null)
                break;
                
            String strCurLine = null;
            
            try
            {
                strCurLine = brrData.readLine();
                
                if (strCurLine == null)
                {
                    break;
                }
                                
                if (strCurLine.toLowerCase().startsWith(ChkRegUIJsr._f_s_strKeyName.toLowerCase()))
                {
                    if (strCurLine.toLowerCase().endsWith(Shared.f_s_strValueNil)) // ????
                        break;
                        
                    strName = strCurLine.substring(ChkRegUIJsr._f_s_strKeyName.length(), strCurLine.length());
                    strName = strName.trim();
                    continue;
                }
                
                else if (strCurLine.toLowerCase().startsWith(ChkRegUIJsr._f_s_strKeyOrg.toLowerCase()))
                {
                    if (strCurLine.toLowerCase().endsWith(Shared.f_s_strValueNil)) // ????
                        break;
                        
                    strOrg = strCurLine.substring(ChkRegUIJsr._f_s_strKeyOrg.length(), strCurLine.length());
                    strOrg = strOrg.trim();
                    continue;
                }
                
                else if (strCurLine.toLowerCase().startsWith(ChkRegUIJsr._f_s_strKeyProdId.toLowerCase()))
                {
                    if (strCurLine.toLowerCase().endsWith(Shared.f_s_strValueNil)) // ????
                        break;
                        
                    strProdId = strCurLine.substring(ChkRegUIJsr._f_s_strKeyProdId.length(), strCurLine.length());
                    strProdId = strProdId.trim();
                    continue;
                }
                
            }
            
            catch (EOFException excEOF) 
            {
                //excEOF.printStackTrace();
                MySystem.s_printOutError(strMethod, "excEOF caught");
                return null;
            }
            
            catch (IOException excIO) 
            {
                //excIO.printStackTrace();
                MySystem.s_printOutError(strMethod, "excIO caught");
                return null;
            }
        }
        
        try
        {
            brrData.close();
        }
        
        catch(Exception exc)
        {
            //exc.printStackTrace();
            MySystem.s_printOutError(strMethod, "exc caught");
            return null;
        }
        
        // --
        
        if (strName==null || strOrg==null || strProdId==null)
        {
            MySystem.s_printOutError(strMethod, "strName==null || strOrg==null || strProdId==null");
            return null;
        }
        
        // --
        // ok!
        this._blnIsReg = true;
        // --
        
        sbr.append("Li" + "cen" + "sed to ");
        
        sbr.append(strName);
        sbr.append(", ");
        sbr.append(strOrg);
        
        sbr.append(", Pr" + "odu" + "ct " + "I" + "D" + ": ");
        sbr.append(strProdId);
        
        
        // ending
        return sbr.toString();
    }
    
    private boolean _askWarning(String strAllowedEval)
    {
        String strMethod = "_askWarning(strAllowedEval)";
        
        // ----
        // show option dialog
        
        DChoiceEvWarning chg = new DChoiceEvWarning(strAllowedEval);
                
        if (! chg.init())
            MySystem.s_printOutExit(strMethod, "failed");
            
        chg.setVisible(true);

			    
		int intReply = chg.getValue();
		chg.destroy();
		chg = null;
			    
	    if (intReply == DChoiceEvWarning.f_s_intInstall)
		{
            if (! _importAndReadReg())
            {
                //MySystem.s_printOutError(strMethod, "failed");
                return false;
            }
            
            return true;
        }
                
        else if (intReply != DChoiceEvWarning.f_s_intQuit)
		    MySystem.s_printOutExit(strMethod, "uncaught dialog answer");
        
        // quitting   
        // --
        return false;
    }
    
    private boolean _askQuestion(String strAllowedEval)
    {
        String strMethod = "_askQuestion(strAllowedEval)";
        
        // ----
        // show option dialog
        
        DChoiceEvQuestion chg = new DChoiceEvQuestion(strAllowedEval);
                
        if (! chg.init())
            MySystem.s_printOutExit(strMethod, "failed");
            
        chg.setVisible(true);

			    
		int intReply = chg.getValue();
		chg.destroy();
		chg = null;
			    
	    if (intReply == DChoiceEvQuestion.f_s_intInstall)
		{
            if (! _importAndReadReg()) // don't care
            {
                //MySystem.s_printOutTrace(strMethod, "aborted or failed");
                return true;
            }
            
             // --
            // to do: read license
            //MySystem.s_printOutWarning(this, strMethod, "to do: read license");
            //if (true) return false;
            // --
            
            return true;
        }
                
        else if (intReply != DChoiceEvQuestion.f_s_intContinue)
        {
		    MySystem.s_printOutExit(strMethod, "uncaught dialog answer");
        }
        
        // continue  
        // --
        return true;
    }
    
    /**
        get jarred file name _sreg_xls11.jar, and install a copy to [appli.home]/lib/.
        if target already exist, ask for overwrite it!
        
        short name of fileSource should be "_sreg_xls11"
        
        fileTarget should be: [appli.home]/lib/.
        
        !!!!!!!! all messages should be hidden
    **/
    private boolean _importAndReadReg()
    {
        String strMethod = "_importAndReadReg()";
        
        MySystem.s_printOutError(this, strMethod, "CODING ERROR: commented S_FileChooserAbs.s_getOpenFileRegSource(...), returning false");
        return false;
        
        /** TEMPO IN COMMENTS oct 25, 2003
        
        final String f_strNameReg = "_sreg_xls11.jar";
        
        File fleSource = null;
        
        while (true)
        {
            fleSource = S_FileChooserAbs.s_getOpenFileRegSource(this.System.getProperty("_.appli.title"));
        
            if (fleSource != null)
            {
                
                if (fleSource.getName().compareTo(f_strNameReg) == 0)
                {
                    // got it
                    break;
                }
                
                
                // --
                // show errorConfirm dialog
                
                String strWarningConfirmTitle = "wrong file name";
                String strWarningConfirmBody = "Wrong file name: " + fleSource.getName();
                
                
                strWarningConfirmBody += "\n\n";
                strWarningConfirmBody += "Retry?";
           
                boolean blnConfirm = com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showWarningConfirmDialog(
                    null, System.getProperty("_.appli.title") + " - " + strWarningConfirmTitle, strWarningConfirmBody);
                
                
                // ---
                
                if (blnConfirm)
                    continue;
                    
                // --
                return false;
                
                // --
            }
            
            else // fleSource == null
            {
                //MySystem.s_printOutTrace(this, strMethod, "fleSource == null");
                return false;
            }
   
        }

        // --
        File fleParentTarget = S_FileChooserAbs.s_getOpenParentFileRegTarget(System.getProperty("_.appli.title"));
        
        if (fleParentTarget == null) // exiting
        {
            MySystem.s_printOutExit(this, strMethod, "nil fleParentTarget");
        }
        
        // --
        File fleTarget = null;
        
        fleTarget = new File(fleParentTarget, f_strNameReg);
        
        if (fleTarget.exists())
        {
            // ask for overwrite
            MySystem.s_printOutWarning(this, strMethod, "IN PROGRESS: fleTarget.exists(), ask for overwrite, returning false");
            return false;
        }
        
        // --
        
        if (! S_FileSys.s_copyFile(fleSource, fleTarget))
        {
            MySystem.s_printOutExit(this, strMethod, "! S_FileSys.s_copyFile(fleSource, fleTarget)");
        }
        
        boolean blnOk = _doJobReg(fleTarget);
                
        if (! blnOk)
        {
            String strBody = "Fai" + "led to" + " r" + "ec" + "over " + "l" + "ice" + "nse!";
            strBody += "\n" + com.google.code.p.keytooliui.shared.swing.panel.PHelpAboutAppli.s_strContactPoints;
                
            OPAbstract.s_showDialogError((Component) null, System.getProperty("_.appli.title"), strBody);
            System.exit(1);    
        }
        
        // --
        // ending
        return true;**/
    }
}