/*
 *
 * Copyright (c)  2001-2011 keyTool IUI Project.
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
 
 
package com.google.code.p.keytooliui.ktl.swing.table;

/**
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.lang.bool.*;

import javax.swing.*;
import javax.swing.table.*;

public final class TMEntPKTCSelAll extends TMEntAbs
{
    // -------------------
    // PUBLIC STATIC FINAL
    
    public static final int f_s_intColumnIdIsCandidate = 0;
    public static final int f_s_intColumnIdIsTCEntry = 2;        // see array of integers right below
    public static final int f_s_intColumnIdIsValidDate = TMEntPKTCSelAll.f_s_intColumnIdIsTCEntry + 1;      // see array of integers right below
    public static final int f_s_intColumnIdIsSelfSignedCert = TMEntPKTCSelAll.f_s_intColumnIdIsValidDate + 1; // see array of integers right below
    public static final int f_s_intColumnIdIsTrustedCACert = TMEntPKTCSelAll.f_s_intColumnIdIsSelfSignedCert + 1;    // see array of integers right below
    
    // preferred columns width 
    public static final int[] f_s_intsColW = 
    { 
        30,  // booIsCandidate (for selection)
        TMEntAbs._INT_W_ALIAS_, // strAlias
        30,  // booIsTCEntry
        30,  // booValidDate
        30,  // booSelfSignedCert
        30,  // booTrustedCACert
        60,  // strSizeKeyPubl
        30,  // strTypeCert
        90,  // strAlgSigCert
        TMEntAbs._INT_W_MODIFIEDDATE_  // dteLastModified
    }; // sum: ?
    
    
    public static final String[] f_s_strsColumnNames =
    { 
        "Candidate ?",
        TMEntAbs._STR_ALIAS_,
        "Entry", // PK versus TC
        "Valid Date ?",
        "Self-Signed ?", // "Self-Signed Cert. ?"
        "Trusted C.A. ?", // trusted C.A. certificates
        "Key Size", // (public key size)
        "Cert. Type",
        "Cert. Sig. Algo.", // cert sig algo
        TMEntAbs._STR_MODIFIEDDATE_
    };
    

    // ------
    // PUBLIC

    public TMEntPKTCSelAll()
    {
        this(new Object[0][0]);
    }

    public TMEntPKTCSelAll(Object[][] objssData)
    {
        super(objssData);
    }
    
        
    public int getColumnCount()
    {
        return TMEntPKTCSelAll.f_s_strsColumnNames.length;
    }
        

    public String getColumnName(int col)
    {
        return TMEntPKTCSelAll.f_s_strsColumnNames[col];
    }
}
