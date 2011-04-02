package com.google.code.p.keytooliui.ktl.swing.tree;

import javax.swing.tree.DefaultMutableTreeNode;

public class TipOnLeafNode extends DefaultMutableTreeNode
{
    private String tip;
    
    public TipOnLeafNode(String text)
    {
        super(text);
    }

    public TipOnLeafNode(String text, String tip)
    {
        super(text);

        this.tip = tip;
    }
    
    public String getTip()
    {
        return this.tip;
    }
}
