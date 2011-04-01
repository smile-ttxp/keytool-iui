package com.google.code.p.keytooliui.ktl.swing.tree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.JTree;
import javax.swing.ToolTipManager;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import com.google.code.p.keytooliui.ktl.swing.button.BESFolderCloseAbs;
import com.google.code.p.keytooliui.ktl.swing.button.BESFolderOpenAbs;
import com.google.code.p.keytooliui.shared.lang.MySystem;

public class TipOnLeafTree extends JTree implements ActionListener
{
    // ------
    // public
    
    /**
        MEMO: if folder not already loaded (dynamic loading), bypass the respective folder (like a terminal node)
    **/
    public boolean isAllowedFolderAllExpand()
    {
        String strMethod = "isAllowedFolderAllExpand()";
        
        if (getSelectionCount() < 1)
            return false;
            
        TreePath tphSel = getSelectionPath();
        
        if (tphSel == null)
            return false;
        
        Object objSel = tphSel.getLastPathComponent();
        
        TipOnLeafNode mmtSel = (TipOnLeafNode) objSel;
        
        
        if (mmtSel == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil mmtSel");
            return false; // !!!!!
        }
        
        for (Enumeration e = mmtSel.depthFirstEnumeration() ; e.hasMoreElements() ;)
        { 
	        TipOnLeafNode tsaCur = (TipOnLeafNode) e.nextElement(); 

	        TreePath tphCur = new TreePath(tsaCur.getPath());
	        
	        if (! isVisible(tphCur)) 
                return true;
        } 
        
        return false;
    }
    
    /** ----------------------------
        memo: children & subChildren
        if tsa is a leaf, then return false
        else if tsa is open, return true,
        else if any child or subChild is open, return true;
    **/
    public boolean isAllowedFolderAllCollapse()
    {
        String strMethod = "isAllowedFolderAllCollapse()";
        
        if (getSelectionCount() < 1)
            return false;
            
        TreePath tphSel = getSelectionPath();
        
        if (tphSel == null)
            return false;
        
        Object objSel = tphSel.getLastPathComponent();
        
        TipOnLeafNode mmtSel = (TipOnLeafNode) objSel;
        
        
        if (mmtSel == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil mmtSel");
            return false; // !!!!!
        }
        
        for (Enumeration e = mmtSel.depthFirstEnumeration() ; e.hasMoreElements() ;)
        { 
	        TipOnLeafNode tsaCur = (TipOnLeafNode) e.nextElement(); 
	        
	        if (tsaCur == mmtSel)
	            continue;
	            
	        TreePath tphCur = new TreePath(tsaCur.getPath());
	        
	        if (isVisible(tphCur)) 
                return true;
        } 
        
        return false;
    }
    
    public TipOnLeafTree(
            TreeSelectionListener lstTreeSelectionParent, // used to enable/disable "expandAll/collapseAll" 
            TreeExpansionListener lstTreeExpansion,
            TipOnLeafNode mtn)
    {
        super(mtn);

        ToolTipManager.sharedInstance().registerComponent(this);
        this.setCellRenderer(new TipOnLeafRenderer());
        
        if (lstTreeSelectionParent != null)
            addTreeSelectionListener(lstTreeSelectionParent);
        
        if (lstTreeExpansion != null)
            addTreeExpansionListener(lstTreeExpansion);
    }
    
    

    public void actionPerformed(ActionEvent e) 
    {
        String strMethod = "actionPerformed(e)";
        
        if (e.getSource() instanceof BESFolderCloseAbs)
            _folderAllCollapse();
        else if (e.getSource() instanceof BESFolderOpenAbs)
            _folderAllExpand();
        else
            MySystem.s_printOutExit(this, strMethod, "uncaught source, e.getSource()=" + e.getSource());
    }
    
    // -------
    // private
    
    private void _folderAllExpand()
    {
        String strMethod = "_folderAllExpand()";
        
        if (getSelectionCount() < 1)
            return;
            
        TreePath tphSel = getSelectionPath();
        
        if (tphSel == null)
            return;
        
        Object objSel = tphSel.getLastPathComponent();
        
        TipOnLeafNode mmtSel = (TipOnLeafNode) objSel;
        
        
        if (mmtSel == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil mmtSel, aborting");
            return; // !!!!!
        }
        
        for (Enumeration e=mmtSel.depthFirstEnumeration() ; e.hasMoreElements() ;)
        { 
	        TipOnLeafNode tsaCur = (TipOnLeafNode) e.nextElement(); 
	        
	        //if (tsaCur.isFolderNotLoaded())
	          //  continue;
	        
	        TreePath tphCur = new TreePath(tsaCur.getPath());   
	        expandPath(tphCur);
        }  
    }
    
    private void _folderAllCollapse()
    {
        String strMethod = "_folderAllCollapse()";
        
        
        if (getSelectionCount() < 1)
            return;
            
        TreePath tphSel = getSelectionPath();
        
        if (tphSel == null)
            return;
        
        Object objSel = tphSel.getLastPathComponent();
        
        TipOnLeafNode mmtSel = (TipOnLeafNode) objSel;
        
        
        if (mmtSel == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil mmtSel, aborting");
            return; // !!!!!
        }
        
        for (Enumeration e=mmtSel.depthFirstEnumeration() ; e.hasMoreElements() ;)
        { 
	        TipOnLeafNode tsaCur = (TipOnLeafNode) e.nextElement(); 
	        TreePath tphCur = new TreePath(tsaCur.getPath());   
	        collapsePath(tphCur);
        }  
    }
    
}
