package com.google.code.p.keytooliui.ktl.io;

/**
**/


public class S_FileExtensionUI
{    
    // --------------------------
    // FINAL STATIC PUBLIC STRING
    
    // memo, the preferred one at last, used for eg filesaving, without extension name
    final static public String[] f_s_strsKstJks = { "ks", "jks" };
    final static public String[] f_s_strsKstJceks = { "jce" };
    final static public String[] f_s_strsKstPkcs12 = { "pfx", "p12" };
    final static public String[] f_s_strsKstBks = { "bks" };
    final static public String[] f_s_strsKstUber = { "ubr" };
    
    final static public String[] f_s_strsKprDer = { "der" }; // not sure about p8! 
    final static public String[] f_s_strsKprPem = { "pem" };       // ascii 
    
    final static public String[] f_s_strsShkDer = { "der" }; // not sure about p8! 
    final static public String[] f_s_strsShkPem = { "pem" };       // ascii 
    
    final static public String[] f_s_strsCsrPkcs10 = { "csr", "p10" };
    
    final static public String[] f_s_strsCrtX509Pkcs7 = { "p7b" }; // binary
    final static public String[] f_s_strsCrtX509Pem = { "pem" }; // ascii, also PKCS7
    
    final static public String[] f_s_strsCrtPkcs12 = S_FileExtensionUI.f_s_strsKstPkcs12; 
    
    final static public String[] f_s_strsCrtX509Der = { "crt", "cer" }; 
    final static public String[] f_s_strsCrtX509Base64 = { "crt", "cer" }; 
    final static public String[] f_s_strsCrtOther = { "crt", "cer" }; 
    final static public String[] f_s_strsCrtCms = { "p7c" }; 
    
    final static public String[] f_s_strsXmlXml = { "xml" }; 
    
    final static public String[] f_s_strsSigX509Der = { "der" }; 
    final static public String[] f_s_strsSigX509Pkcs7 = S_FileExtensionUI.f_s_strsCrtX509Pkcs7;
    final static public String[] f_s_strsSigX509Pem = S_FileExtensionUI.f_s_strsCrtX509Pem;
    
    // ----
    final static public String[] f_s_strsSCmsP7m = { "p7m" }; 
    final static public String[] f_s_strsSCmsP7s = { "p7s" }; 
    
    // ----
    final static public String f_s_strDirNameDefaultCsr = "mycertreqs";
    final static public String f_s_strDirNameDefaultCrt = "mycerts"; // certificate file
    final static public String f_s_strDirNameDefaultSig = "mysigs"; // digital signature file
    final static public String f_s_strDirNameDefaultSCms = "myscms"; // digital CMS signature file
    final static public String f_s_strDirNameDefaultCCms = "myccms"; // digital CMS certificate file
    final static public String f_s_strDirNameDefaultKst = "mykeystores";
    final static public String f_s_strDirNameDefaultXml = "myxmls"; // XML data file
    final static public String f_s_strDirNameDefaultKpr = "mykeypairs";
    final static public String f_s_strDirNameDefaultShk = "mysharedkeys";
    
    final static public String f_s_strDirNameDefaultFileSigned = "mysignedfiles";
    final static public String f_s_strDirNameDefaultFileUnsigned = "myunsignedfiles";
    
    
    // ----
    final static public String f_s_strFileDescKprPem = "PEM private keys";
    
    
    final static public String f_s_strFileDescCsrPkcs10 = "PKCS#10 CSR";
    final static public String f_s_strFileDescCrtX509Pkcs7 = "PKCS#7 cert.";
    final static public String f_s_strFileDescCrtX509Pem = "PEM cert.";
    final static public String f_s_strFileDescCrtX509Der = "DER cert.";
    
    final static public String f_s_strFileDescKstAll = "keystores";
    
    final static public String f_s_strFileDescKstJks = "JKS " + f_s_strFileDescKstAll;
    final static public String f_s_strFileDescKstJceks = "JCEKS " + f_s_strFileDescKstAll;
    final static public String f_s_strFileDescKstPkcs12 = "PKCS#12 " + f_s_strFileDescKstAll;
    final static public String f_s_strFileDescKstBks = "BKS " + f_s_strFileDescKstAll;
    final static public String f_s_strFileDescKstUber = "UBER " + f_s_strFileDescKstAll;
    
    // -------------
    // static public
    
    static public boolean s_isKstJks(String strPathAbs)
    {
        return S_FileExtensionUI._s_isSameExtension(strPathAbs, S_FileExtensionUI.f_s_strsKstJks);
    }
    
    static public boolean s_isKstJceks(String strPathAbs)
    {
        return S_FileExtensionUI._s_isSameExtension(strPathAbs, S_FileExtensionUI.f_s_strsKstJceks);
    }
    
    static public boolean s_isKstPkcs12(String strPathAbs)
    {
        return S_FileExtensionUI._s_isSameExtension(strPathAbs, S_FileExtensionUI.f_s_strsKstPkcs12);
    }
    
    static public boolean s_isKstBks(String strPathAbs)
    {
        return S_FileExtensionUI._s_isSameExtension(strPathAbs, S_FileExtensionUI.f_s_strsKstBks);
    }
    
    static public boolean s_isKstUber(String strPathAbs)
    {
        return S_FileExtensionUI._s_isSameExtension(strPathAbs, S_FileExtensionUI.f_s_strsKstUber);
    }

    
    
    
    
    
    static public String[] s_getKstAll()
    {
        int intLength = f_s_strsKstJks.length;
        intLength += f_s_strsKstJceks.length;
        
        //if (com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs.s_isPolicyExtended())
        //{
            intLength += f_s_strsKstPkcs12.length;
            intLength += f_s_strsKstBks.length;
            intLength += f_s_strsKstUber.length;
        //}
        
        
        String[] strs = new String[intLength];
        
        int j = 0;
        
        for (int i=0; i<f_s_strsKstJks.length; i++)
        {
            strs[j] = f_s_strsKstJks[i];
            j++;
        }
        
        for (int i=0; i<f_s_strsKstJceks.length; i++)
        {
            strs[j] = f_s_strsKstJceks[i];
            j++;
        }
        
        //if (com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs.s_isPolicyExtended())
        //{
            for (int i=0; i<f_s_strsKstPkcs12.length; i++)
            {
                strs[j] = f_s_strsKstPkcs12[i];
                j++;
            }
            
            for (int i=0; i<f_s_strsKstBks.length; i++)
            {
                strs[j] = f_s_strsKstBks[i];
                j++;
            }
            
            for (int i=0; i<f_s_strsKstUber.length; i++)
            {
                strs[j] = f_s_strsKstUber[i];
                j++;
            }
        //}
     
        return strs;
   }
    
    // --------------
    // static private
    
    
    static private boolean _s_isSameExtension(String strPathAbsCandidate, String[] strs)
    {
        if (strPathAbsCandidate == null) // should be an error
            return false;
            
        String strExtCandidate = com.google.code.p.keytooliui.shared.lang.string.StringFilter.s_getExtension(strPathAbsCandidate);
        
        if (strExtCandidate == null)
            return false;
            
        strExtCandidate = strExtCandidate.toLowerCase();
            
        if (strs == null) // should be an error, rather exit!
            return false;
            
            
        for (int i=0; i<strs.length; i++)
        {
            if (strExtCandidate.compareTo(strs[i].toLowerCase()) == 0)
                return true;
        }
        
        return false;
    }
}