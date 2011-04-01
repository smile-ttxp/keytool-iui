package com.google.code.p.keytooliui.ktl.swing.tree;

import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

public class TipOnLeafRenderer extends DefaultTreeCellRenderer
{    
    public Component getTreeCellRendererComponent(
        JTree tree,
        Object objValue,
        boolean sel,
        boolean expanded,
        boolean leaf,
        int row,
        boolean hasFocus) 
    {

        super.getTreeCellRendererComponent(tree, objValue, sel, expanded, leaf, row, hasFocus);

        if (leaf) 
        {
            if (objValue instanceof TipOnLeafNode)
            {
                TipOnLeafNode tln = (TipOnLeafNode) objValue;
                setToolTipText(tln.getTip());
            }
            
            else
                setToolTipText(null); //no tool tip
                
            //setIcon(myIcon);
        } 
        
        else 
        {
            setToolTipText(null); //no tool tip
        }

        return this;
    }
    
}
