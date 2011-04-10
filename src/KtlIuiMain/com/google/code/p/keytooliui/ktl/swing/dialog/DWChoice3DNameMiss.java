package com.google.code.p.keytooliui.ktl.swing.dialog;

/*
 *MEMO: not in use anymore! sould be removed
 *TODO: REMOVE THIS CLASS!
 * january 9, 2008
 */

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.dialog.*;

final public class DWChoice3DNameMiss extends DWChoice3Abs
{    
    // ---------------------
    // STATIC PRIVATE STRING
    
    static private String _s_strWhat = "At least one of optional Distinguished Name field is not filled.";

    // ------
    // PUBLIC

    public DWChoice3DNameMiss(
        java.awt.Frame frm)
    {
        super(frm, DWChoice3DNameMiss._s_strWhat);
    }
    
    // -------
    // PRIVATE
}