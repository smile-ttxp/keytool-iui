package com.google.code.p.keytooliui.ktl.swing.panel;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import com.google.code.p.keytooliui.ktl.swing.editorpane.S_EPEUI;
import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.swing.panel.*;

public class PTabHtmlWelcomeKtl extends PTabAbstract
{
    final static public String STR_TITLETASK = "Welcome";
    
    public PTabHtmlWelcomeKtl(
            Frame frmOwner
            )
    {
        // tempo
        setBackground(java.awt.Color.ORANGE);
        
        
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        
        JEditorPane epe = S_EPEUI.s_get("welcome.html");
        
        if (epe == null)
        {
            MySystem.s_printOutError(this, strMethod, "Failed to load welcome.html");
            return false;
        }
        
        epe.setEditable(false); 
         //html.addHyperlinkListener(createHyperLinkListener()); 

        JScrollPane scroller = new JScrollPane(); 
        JViewport vp = scroller.getViewport(); 
        vp.add(epe); 
        //getDemoPanel().add(scroller, BorderLayout.CENTER); 
        add(scroller, BorderLayout.CENTER); 
        
        /*try { 
 	    URL url = null; 
 	    // System.getProperty("user.dir") + 
 	    // System.getProperty("file.separator"); 
 	    String path = null; 
 	    
            try 
            { 
 		//path = "/resources/index.html"; 
                path = "com.google.code.p.keytooliui/kst/htmls/welcome.html";
 		url = getClass().getResource(path); 
             } 
            
             catch (Exception exc) 
             { 
 		//System.err.println("Failed to open " + path); 
 		//url = null; 
                exc.printStackTrace();
                MySystem.s_printOutError(this, strMethod, exc.getMessage());
                return false;
             } 
 	     
             if(url == null)
             { 
                MySystem.s_printOutError(this, strMethod, "nil url, path=" + path);
                return false;
             }
            
             JEditorPane html = new JEditorPane(url); 
             
             
             html.setEditable(false); 
             //html.addHyperlinkListener(createHyperLinkListener()); 

            JScrollPane scroller = new JScrollPane(); 
            JViewport vp = scroller.getViewport(); 
            vp.add(html); 
            //getDemoPanel().add(scroller, BorderLayout.CENTER); 
            add(scroller, BorderLayout.CENTER); 
              
         } 
        
        catch (MalformedURLException excMalformedURL) 
        { 
             excMalformedURL.printStackTrace();
             MySystem.s_printOutError(this, strMethod, excMalformedURL.getMessage());
             return false;
        } 
        
        catch (IOException excIO) 
        { 
             excIO.printStackTrace();
             MySystem.s_printOutError(this, strMethod, excIO.getMessage());
             return false;
        }
        
        */
        
        
        
        return true;
    }
    
    public void destroy()
    {
        
    }
    
    public void setContextualHelpID()
    {
        if (this._strHelpID == null)
            return;
        
        com.google.code.p.keytooliui.shared.help.MyCSH.setHelpIDString(this, this._strHelpID);
    }
    
    // -------
    // private
    
    private String _strHelpID = null;
    
}
