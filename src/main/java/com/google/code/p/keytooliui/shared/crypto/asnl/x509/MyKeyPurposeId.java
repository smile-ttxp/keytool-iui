package com.google.code.p.keytooliui.shared.crypto.asnl.x509;


/*
 *MEMO: cannot extend from KeyPurposeId coz private constructor!
 */

/*
 *the following comes from
 *http://support.microsoft.com/kb/287547
 *
 *
Crypto 2.0......................................1.3.6.1.4.1.311.10
     PKCS #7 ContentType Object Identifier for Certificate Trust List (CTL)
        szOID_CTL                               1.3.6.1.4.1.311.10.1
     Sorted CTL Extension
        szOID_SORTED_CTL                        1.3.6.1.4.1.311.10.1.1

     Next Update Location extension or attribute. Value is an encoded GeneralNames
        szOID_NEXT_UPDATE_LOCATION              1.3.6.1.4.1.311.10.2

     Enhanced Key Usage (Purpose)
        Signer of CTLs
        szOID_KP_CTL_USAGE_SIGNING              1.3.6.1.4.1.311.10.3.1

        Signer of TimeStamps
        szOID_KP_TIME_STAMP_SIGNING             1.3.6.1.4.1.311.10.3.2

     Can use strong encryption in export environment
        szOID_SERVER_GATED_CRYPTO               1.3.6.1.4.1.311.10.3.3
        szOID_SERIALIZED                        1.3.6.1.4.1.311.10.3.3.1

     Can use encrypted file systems (EFS)
        szOID_EFS_CRYPTO                        1.3.6.1.4.1.311.10.3.4
        szOID_EFS_RECOVERY                      1.3.6.1.4.1.311.10.3.4.1

     Can use Windows Hardware Compatible (WHQL)
        szOID_WHQL_CRYPTO                       1.3.6.1.4.1.311.10.3.5

     Signed by the NT5 build lab
        szOID_NT5_CRYPTO                        1.3.6.1.4.1.311.10.3.6

     Signed by and OEM of WHQL
        szOID_OEM_WHQL_CRYPTO                   1.3.6.1.4.1.311.10.3.7

     Signed by the Embedded NT
        szOID_EMBEDDED_NT_CRYPTO                1.3.6.1.4.1.311.10.3.8

     Signer of a CTL containing trusted roots
        szOID_ROOT_LIST_SIGNER                  1.3.6.1.4.1.311.10.3.9

     Can sign cross-cert and subordinate CA requests with qualified
     subordination (name constraints, policy mapping, etc.)
        szOID_KP_QUALIFIED_SUBORDINATION        1.3.6.1.4.1.311.10.3.10

     Can be used to encrypt/recover escrowed keys
        szOID_KP_KEY_RECOVERY                   1.3.6.1.4.1.311.10.3.11

     Signer of documents
        szOID_KP_DOCUMENT_SIGNING               1.3.6.1.4.1.311.10.3.12

     Limits the valid lifetime of the signature to the lifetime of the certificate.
        szOID_KP_LIFETIME_SIGNING               1.3.6.1.4.1.311.10.3.13
        szOID_KP_MOBILE_DEVICE_SOFTWARE         1.3.6.1.4.1.311.10.3.14

     Microsoft Attribute Object Identifiers
        szOID_YESNO_TRUST_ATTR                  1.3.6.1.4.1.311.10.4.1

     Microsoft Music
        szOID_DRM                               1.3.6.1.4.1.311.10.5.1

     Microsoft DRM EKU
        szOID_DRM_INDIVIDUALIZATION             1.3.6.1.4.1.311.10.5.2

     Microsoft Licenses
        szOID_LICENSES                          1.3.6.1.4.1.311.10.6.1
        szOID_LICENSE_SERVER                    1.3.6.1.4.1.311.10.6.2

     Microsoft CERT_RDN attribute Object Identifiers
        szOID_MICROSOFT_RDN_PREFIX              1.3.6.1.4.1.311.10.7
     Special RDN containing the KEY_ID. Its value type is CERT_RDN_OCTET_STRING.
        szOID_KEYID_RDN                         1.3.6.1.4.1.311.10.7.1

     Microsoft extension in a CTL to add or remove the certificates. The
     extension type is an INTEGER. 0 => add certificate, 1 => remove certificate
        szOID_REMOVE_CERTIFICATE                1.3.6.1.4.1.311.10.8.1

     Microsoft certificate extension containing cross certificate distribution
     points. ASN.1 encoded as follows:
         CrossCertDistPoints ::= SEQUENCE {
             syncDeltaTime               INTEGER (0..4294967295) OPTIONAL,
             crossCertDistPointNames     CrossCertDistPointNames
         } --#public--
              CrossCertDistPointNames ::= SEQUENCE OF GeneralNames

        szOID_CROSS_CERT_DIST_POINTS            1.3.6.1.4.1.311.10.9.1


     Microsoft CMC OIDs                         1.3.6.1.4.1.311.10.10

     Similar to szOID_CMC_ADD_EXTENSIONS. Attributes replaces Extensions.
        szOID_CMC_ADD_ATTRIBUTES                1.3.6.1.4.1.311.10.10.1

     Microsoft certificate property OIDs        1.3.6.1.4.1.311.10.11
     The OID component following the prefix contains the PROP_ID (decimal)
        szOID_CERT_PROP_ID_PREFIX               1.3.6.1.4.1.311.10.11.

     CryptUI                                    1.3.6.1.4.1.311.10.12
        szOID_ANY_APPLICATION_POLICY            1.3.6.1.4.1.311.10.12.1
                      */

import org.bouncycastle.asn1.DERObjectIdentifier;
//import org.bouncycastle.asn1.x509.*;

public class MyKeyPurposeId extends DERObjectIdentifier
{
    private static final String _STR_MICROSOFT = "1.3.6.1.4.1.311";
    private static final String _STR_MS_CRYPTO20 = MyKeyPurposeId._STR_MICROSOFT + ".10";
    
    private static final String _STR_ADOBE_CDS_PKI = "1.2.840.113583.1.1.5";
    
    
    /*
    "            - Microsoft's smartcardlogon:", // 1.3.6.1.4.1.311.20.2.2
        "            - Microsoft's server gated crypto",
        "            - Microsoft's serialized",
        "            - Microsoft's EFS crypto",
        "            - Microsoft's EFS recovery",
        "            - unknown key usage"
            */
    
    // other than Microsoft
    
    public static final MyKeyPurposeId id_kp_unknownKeyUsage = 
            new MyKeyPurposeId("2.16.840.1.113730.4.1");
    
    //
    // microsoft key purpose ids
    //
    
    
    
    public static final MyKeyPurposeId szOID_SERVER_GATED_CRYPTO = 
            new MyKeyPurposeId(MyKeyPurposeId._STR_MS_CRYPTO20 + ".3.3");
    
    public static final MyKeyPurposeId szOID_SERIALIZED = 
            new MyKeyPurposeId(MyKeyPurposeId._STR_MS_CRYPTO20 + ".3.3.1");
    
    public static final MyKeyPurposeId szOID_EFS_CRYPTO = 
            new MyKeyPurposeId(MyKeyPurposeId._STR_MS_CRYPTO20 + ".3.4");
    
    public static final MyKeyPurposeId szOID_EFS_RECOVERY = 
            new MyKeyPurposeId(MyKeyPurposeId._STR_MS_CRYPTO20 + ".3.4.1");
    
    // got from http://www.adobe.com/misc/pdfs/Adobe_CDS_CPv011604clean.pdf
    // CDS: Certified Document Services
    public static final MyKeyPurposeId szOID_CDS_PKI = 
            new MyKeyPurposeId(MyKeyPurposeId._STR_ADOBE_CDS_PKI);
    
    private MyKeyPurposeId(String strId)
    {
        super(strId);
    }
}

