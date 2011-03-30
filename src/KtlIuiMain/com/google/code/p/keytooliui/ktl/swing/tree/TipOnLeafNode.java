package com.google.code.p.keytooliui.ktl.swing.tree;

import javax.swing.*;
import javax.swing.tree.*;

//TipOnLeafTree

public class TipOnLeafNode extends DefaultMutableTreeNode 
{
    public String getTip() { return this._strTip; }
    public TipOnLeafNode(String strText)
    {
        super(strText);
    }
    
    public TipOnLeafNode(String strText, String strTip)
    {
        super(strText);
        
        this._strTip = strTip;
    }
    
    private String _strTip = null;
}
