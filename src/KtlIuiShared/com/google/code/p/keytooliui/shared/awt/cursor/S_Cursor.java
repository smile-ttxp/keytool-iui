/*
 *
 * Copyright (c) 2001-2002 RagingCat Project.
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
 
 
 package com.google.code.p.keytooliui.shared.awt.cursor;

import com.google.code.p.keytooliui.shared.lang.*;

import java.awt.Cursor;

public class S_Cursor
{
    // -----------------------
    // FINAL STATIC PUBLIC INT
    
    final static public int f_s_intIOTypeHand = 1;
    final static public int f_s_intIOTypeCrossHair = 2;
    final static public int f_s_intIOTypeDefault = 3;
    
    
    // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.shared.awt.cursor.S_Cursor.";
    
    
    // -------------
    // STATIC PUBLIC
    
    static public boolean s_isValidType(int intType)
    {
        if (intType == Cursor.HAND_CURSOR)
            return true;
        
        if (intType == Cursor.CROSSHAIR_CURSOR)
            return true;
        
        if (intType == Cursor.DEFAULT_CURSOR)
            return true;

        return false;
    }
    
    static public boolean s_isValidTypeIO(int intIO)
    {
        if (intIO == f_s_intIOTypeHand)
            return true;
        
        if (intIO == f_s_intIOTypeCrossHair)
            return true;
        
        if (intIO == f_s_intIOTypeDefault)
            return true;

        return false;
    }
    
    // if any error, exiting
    static public int s_getIOFromType(int intType)
    {
        String f_strMethod = _f_s_strClass + "s_getIOFromType(intType)";
        
        if (intType == Cursor.HAND_CURSOR)
            return f_s_intIOTypeHand;
        
        if (intType == Cursor.CROSSHAIR_CURSOR)
            return f_s_intIOTypeCrossHair;
        
        if (intType == Cursor.DEFAULT_CURSOR)
            return f_s_intIOTypeDefault;

        MySystem.s_printOutExit(f_strMethod, "unknown value, intType=" + intType);
        
        // unreached
        return -1;
    }
    
    // if any error, exiting
    static public int s_getTypeFromIO(int intIO)
    {
        String f_strMethod = _f_s_strClass + "s_getTypeFromIO(intIO)";
        
        if (intIO == f_s_intIOTypeHand)
            return Cursor.HAND_CURSOR;
        
        if (intIO == f_s_intIOTypeCrossHair)
            return Cursor.CROSSHAIR_CURSOR;
        
        if (intIO == f_s_intIOTypeDefault)
            return Cursor.DEFAULT_CURSOR;

        MySystem.s_printOutExit(f_strMethod, "unknown value, intIO=" + intIO);
        
        
        // unreached
        return -1;
    }
}