package com.google.code.p.keytooliui.ktl.io;

/**
**/


public class S_FileExtensionUI
{    
    // --------------------------
    // PUBLIC STATIC FINAL STRING
    
    // memo, the preferred one at last, used for eg filesaving, without extension name
    public static final String[] f_s_strsKstJks = { "ks", "jks" };
    public static final String[] f_s_strsKstJceks = { "jce" };
    public static final String[] f_s_strsKstPkcs12 = { "pfx", "p12" };
    public static final String[] f_s_strsKstBks = { "bks" };
    public static final String[] f_s_strsKstUber = { "ubr" };
    
    public static final String[] f_s_strsKprDer = { "der" }; // not sure about p8! 
    public static final String[] f_s_strsKprPem = { "pem" };       // ascii 
    
    public static final String[] f_s_strsShkDer = { "der" }; // not sure about p8! 
    public static final String[] f_s_strsShkPem = { "pem" };       // ascii 
    
    public static final String[] f_s_strsCsrPkcs10 = { "csr", "p10" };
    
    public static final String[] f_s_strsCrtX509Pkcs7 = { "p7b" }; // binary
    public static final String[] f_s_strsCrtX509Pem = { "pem" }; // ascii, also PKCS7
    
    public static final String[] f_s_strsCrtPkcs12 = S_FileExtensionUI.f_s_strsKstPkcs12; 
    
    public static final String[] f_s_strsCrtX509Der = { "crt", "cer" }; 
    public static final String[] f_s_strsCrtX509Base64 = { "crt", "cer" }; 
    public static final String[] f_s_strsCrtOther = { "crt", "cer" }; 
    public static final String[] f_s_strsCrtCms = { "p7c" }; 
    
    public static final String[] f_s_strsXmlXml = { "xml" }; 
    
    public static final String[] f_s_strsSigX509Der = { "der" }; 
    public static final String[] f_s_strsSigX509Pkcs7 = S_FileExtensionUI.f_s_strsCrtX509Pkcs7;
    public static final String[] f_s_strsSigX509Pem = S_FileExtensionUI.f_s_strsCrtX509Pem;
    
    // ----
    public static final String[] f_s_strsSCmsP7m = { "p7m" }; 
    public static final String[] f_s_strsSCmsP7s = { "p7s" }; 
    
    // ----
    public static final String f_s_strDirNameDefaultCsr = "mycertreqs";
    public static final String f_s_strDirNameDefaultCrt = "mycerts"; // certificate file
    public static final String f_s_strDirNameDefaultSig = "mysigs"; // digital signature file
    public static final String f_s_strDirNameDefaultSCms = "myscms"; // digital CMS signature file
    public static final String f_s_strDirNameDefaultCCms = "myccms"; // digital CMS certificate file
    public static final String f_s_strDirNameDefaultKst = "mykeystores";
    public static final String f_s_strDirNameDefaultXml = "myxmls"; // XML data file
    public static final String f_s_strDirNameDefaultKpr = "mykeypairs";
    public static final String f_s_strDirNameDefaultShk = "mysharedkeys";
    
    public static final String f_s_strDirNameDefaultFileSigned = "mysignedfiles";
    public static final String f_s_strDirNameDefaultFileUnsigned = "myunsignedfiles";
    
    
    // ----
    public static final String f_s_strFileDescKprPem = "PEM private keys";
    
    
    public static final String f_s_strFileDescCsrPkcs10 = "PKCS#10 CSR";
    public static final String f_s_strFileDescCrtX509Pkcs7 = "PKCS#7 cert.";
    public static final String f_s_strFileDescCrtX509Pem = "PEM cert.";
    public static final String f_s_strFileDescCrtX509Der = "DER cert.";
    
    public static final String f_s_strFileDescKstAll = "keystores";
    
    public static final String f_s_strFileDescKstJks = "JKS " + f_s_strFileDescKstAll;
    public static final String f_s_strFileDescKstJceks = "JCEKS " + f_s_strFileDescKstAll;
    public static final String f_s_strFileDescKstPkcs12 = "PKCS#12 " + f_s_strFileDescKstAll;
    public static final String f_s_strFileDescKstBks = "BKS " + f_s_strFileDescKstAll;
    public static final String f_s_strFileDescKstUber = "UBER " + f_s_strFileDescKstAll;
    
    // -------------
    // public static
    
    public static boolean s_isKstJks(String strPathAbs)
    {
        return S_FileExtensionUI._s_isSameExtension(strPathAbs, S_FileExtensionUI.f_s_strsKstJks);
    }
    
    public static boolean s_isKstJceks(String strPathAbs)
    {
        return S_FileExtensionUI._s_isSameExtension(strPathAbs, S_FileExtensionUI.f_s_strsKstJceks);
    }
    
    public static boolean s_isKstPkcs12(String strPathAbs)
    {
        return S_FileExtensionUI._s_isSameExtension(strPathAbs, S_FileExtensionUI.f_s_strsKstPkcs12);
    }
    
    public static boolean s_isKstBks(String strPathAbs)
    {
        return S_FileExtensionUI._s_isSameExtension(strPathAbs, S_FileExtensionUI.f_s_strsKstBks);
    }
    
    public static boolean s_isKstUber(String strPathAbs)
    {
        return S_FileExtensionUI._s_isSameExtension(strPathAbs, S_FileExtensionUI.f_s_strsKstUber);
    }

    
    
    
    
    
    public static String[] s_getKstAll()
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
    // private static
    
    
    private static boolean _s_isSameExtension(String strPathAbsCandidate, String[] strs)
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