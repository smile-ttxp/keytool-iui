package com.google.code.p.keytooliui.ktl.swing.panel;

import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.google.code.p.keytooliui.ktl.swing.toolbar.TBSubTreeKtl;
import com.google.code.p.keytooliui.ktl.swing.tree.TipOnLeafNode;
import com.google.code.p.keytooliui.ktl.swing.tree.TipOnLeafTree;
import com.google.code.p.keytooliui.shared.lang.MySystem;

public class PCntsMainLeft extends PCntsMainAbs implements TreeSelectionListener, TreeExpansionListener
{   
    // -------------------
    // final static public
    
    // temp
    
    final static public String STR_NODE_KW_PKDER = "DER file format";
    final static public String STR_NODE_KW_PKPEM = "PEM file format";
    
    final static public String STR_NODE_KW_SK = "Secret key";
    final static public String STR_NODE_KW_TC = "Trusted certificate";
    final static public String STR_NODE_KW_PK = "Private key";
    final static public String STR_NODE_KW_PK_V1C = STR_NODE_KW_PK + ", with vers. #1 cert.";
    final static public String STR_NODE_KW_PK_V3C = STR_NODE_KW_PK + ", with vers. #3 cert.";
    final static public String STR_NODE_KW_ENTRY = "Keystore's entry";
    
    final static public String STR_NODE_KW_TCREGULAR = "Regular certificate";
    final static public String STR_NODE_KW_TCROOTCA = "Root CA certificate";
    
    
    final static public String STR_NODE_CREATE = "Create";
    final static public String STR_NODE_CRKST = "Keystore";
    final static public String STR_NODE_CRENTPKDSA = "DSA";
    final static public String STR_NODE_CRENTPKRSA = "RSA";
    final static public String STR_NODE_CRENTPKEC = "EC";
    
    final static public String STR_NODE_CIPHERSIGN = "Sign file with private key";
    final static public String STR_NODE_CIPHERVERIFY = "Verify signed file";
    final static public String STR_NODE_CIPHERDET = "Detached signature";
    final static public String STR_NODE_CIPHERDETOTHER = "Other";
    final static public String STR_NODE_CIPHERDETCMS = "CMS";
    final static public String STR_NODE_CIPHEREMB = "Embedded signature";
    final static public String STR_NODE_CIPHEREMBXML = "XML";
    final static public String STR_NODE_CIPHEREMBJAR = "JAR";
    
    final static public String STR_NODE_ARCDIR = "Directory"; // archiving directory
    
    
    final static public String STR_NODE_CRYPTENC = "Encrypt file with keystore's entry";
    final static public String STR_NODE_CRYPTDEC = "Decrypt file with keystore's entry";
    final static public String STR_NODE_CRYPTSK = "Use secret key";
    final static public String STR_NODE_CRYPTPK = "Use RSA private key";
    final static public String STR_NODE_CRYPTTC = "Use RSA trusted certificate";
    
    
    final static public String STR_NODE_IOIN = "Import";
    final static public String STR_NODE_IOOUT = "Export";
    
    final static public String STR_NODE_IOENTPKOTHER = "From other keystore"; // IN !
    final static public String STR_NODE_IOCACRTREPLY = "Private key's cert. from CA cert. reply"; // IN !
    final static public String STR_NODE_IOPKZCRT = "Private key's first certificate in chain"; // OUT !
    final static public String STR_NODE_IOPKZCRT2CSR = "As certificate signing request file"; // OUT !
    final static public String STR_NODE_IOPKZCRT2CRT = "As simple certificate file"; // OUT !
            
    // --------------------
    // final static private
    
    final static private String _STR_NODEMANAGEKST = "Manage keystore file";
    
    final static public String STR_NODE_WELCOME = "Welcome";

    
    
    // ------
    // public
    
    public JTree getTree() { return this._tre; }
    
    public PCntsMainLeft(
            Frame frmOwner, 
  
            TreeSelectionListener lsrTreeSelectionParent)
    {
       this._frmOwner = frmOwner;
     
       
       // -----
       
        TipOnLeafNode mtnRoot = new TipOnLeafNode("ROOT NODE");
        
        TipOnLeafNode mtnKtl = _createNodeMain();
        mtnRoot.add(mtnKtl);

        this._tre = new TipOnLeafTree(this, this, mtnRoot); 
       
       if (lsrTreeSelectionParent != null)
           this._tre.addTreeSelectionListener(lsrTreeSelectionParent);
           
       //this._tre.addTreeSelectionListener(this);

        this._tre.setRootVisible(true);
        this._tre.setShowsRootHandles(false);
        this._tre.setBorder(new EmptyBorder(2, 3, 2, 3));

        this._spe = new JScrollPane(this._tre);
       
       
       // ----
       this._tbr = new TBSubTreeKtl(
       
               this._tre, // expand/collapse folders
               this // print
               ); 
       
       
       // ----
       
       TreePath tphCur = new TreePath(mtnKtl.getPath());   
       this._tre.expandPath(tphCur);
       
    }
    
    
    /*public Insets getInsets()
    {
        return new Insets(20, 20, 20, 20);
    }*/
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! this._tbr.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        setLayout(new BorderLayout());
        super.add(this._spe, BorderLayout.CENTER);
        super.add(this._tbr, BorderLayout.NORTH);
        
        this._tre.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        this._tre.setRootVisible(false);
        
        return true;
    }
    
    public void destroy()
    {
        if (this._tbr != null)
        {
            this._tbr.destroy();
            this._tbr = null;
        }
    }
    
    private void _valueChangedHandleButtons(TreeSelectionEvent evtTreeSelection) 
    {
        String strMethod =  "_valueChangedHandleButtons(evtTreeSelection)";
        
        if (! evtTreeSelection.isAddedPath())
            return;
        
        if (this._tbr != null)
        {
            //this._tbr.setEnabledButtonClearSelec(true);
           
            if (this._tre == null)
                return;
            
            // folder all collapse
            if (this._tre.isAllowedFolderAllCollapse())
                this._tbr.setEnabledButtonFolderClose(true);
            else
                this._tbr.setEnabledButtonFolderClose(false);
          
            
            // folder all expand
            if (this._tre.isAllowedFolderAllExpand())
                this._tbr.setEnabledButtonFolderOpen(true);
            else
                this._tbr.setEnabledButtonFolderOpen(false);
        }
    }
     
    public void valueChanged(TreeSelectionEvent e) 
    {
         // handle toolbar's buttons
        _valueChangedHandleButtons(e);
        
         // handle leaves selection 
        // !!!!!!!!! TO REMOVE?
        TipOnLeafNode mtn = (TipOnLeafNode)
            this._tre.getLastSelectedPathComponent();

        if (mtn == null) 
            return;
        
        if (! mtn.isLeaf())
        {
            return;
        }
        
       
            
        Object obj = mtn.getUserObject();
        
        String str = (String) obj;
        
        /*if (str.compareTo(UtilKstJks.f_s_strKeystoreType + " ...") == 0)
        {
            // check whether view or manage keystore
            
            TipOnLeafNode mtnParent = (TipOnLeafNode) mtn.getParent();
            
            String strParent = (String) mtnParent.getUserObject();
            
            if (strParent.compareTo(PCntsMainLeft._STR_NODEMANAGEKST) == 0)
                UtilKstJks.s_manageFile(this._strTitleAppli, this._frmOwner);   
            else
                UtilKstJks.s_showFile(this._strTitleAppli, this._frmOwner); 
            
            this._tre.getSelectionModel().clearSelection();
            
            if (this._tphTaskLast != null)
                this._tre.setSelectionPath(this._tphTaskLast);
            
            return;
        }*/
        
        /*if (str.compareTo(UtilKstJceks.f_s_strKeystoreType + " ...") == 0)
        {
            // check whether view or manage keystore
            
            TipOnLeafNode mtnParent = (TipOnLeafNode) mtn.getParent();
            
            String strParent = (String) mtnParent.getUserObject();
            
            if (strParent.compareTo(PCntsMainLeft._STR_NODEMANAGEKST) == 0)
                UtilKstJceks.s_manageFile(this._strTitleAppli, this._frmOwner);    
            else
                UtilKstJceks.s_showFile(this._strTitleAppli, this._frmOwner); 
            
            
            this._tre.getSelectionModel().clearSelection();
            
            if (this._tphTaskLast != null)
                this._tre.setSelectionPath(this._tphTaskLast);
            
            return;
            
        }*/
        
        /*if (str.compareTo(UtilKstPkcs12.f_s_strKeystoreType + " ...") == 0)
        {
            // check whether view or manage keystore
            
            TipOnLeafNode mtnParent = (TipOnLeafNode) mtn.getParent();
            
            String strParent = (String) mtnParent.getUserObject();
            
            if (strParent.compareTo(PCntsMainLeft._STR_NODEMANAGEKST) == 0)
                UtilKstPkcs12.s_manageFile(this._strTitleAppli, this._frmOwner);   
            else
                UtilKstPkcs12.s_showFile(this._strTitleAppli, this._frmOwner); 
            
            this._tre.getSelectionModel().clearSelection();
            
            if (this._tphTaskLast != null)
                this._tre.setSelectionPath(this._tphTaskLast);
            
            return;
        }*/
        
        /*if (str.compareTo(UtilKstBks.f_s_strKeystoreType + " ...") == 0)
        {
            // check whether view or manage keystore
            
            TipOnLeafNode mtnParent = (TipOnLeafNode) mtn.getParent();
            
            String strParent = (String) mtnParent.getUserObject();
            
            if (strParent.compareTo(PCntsMainLeft._STR_NODEMANAGEKST) == 0)
                UtilKstBks.s_manageFile(this._strTitleAppli, this._frmOwner);  
            else
                UtilKstBks.s_showFile(this._strTitleAppli, this._frmOwner); 
            
            this._tre.getSelectionModel().clearSelection();
            
            if (this._tphTaskLast != null)
                this._tre.setSelectionPath(this._tphTaskLast);
            
            return;
        }*/
        
        /*if (str.compareTo(UtilKstUber.f_s_strKeystoreType + " ...") == 0)
        {
            // check whether view or manage keystore
            
            TipOnLeafNode mtnParent = (TipOnLeafNode) mtn.getParent();
            
            String strParent = (String) mtnParent.getUserObject();
            
            if (strParent.compareTo(PCntsMainLeft._STR_NODEMANAGEKST) == 0)
                UtilKstUber.s_manageFile(this._strTitleAppli, this._frmOwner);    
            else
                UtilKstUber.s_showFile(this._strTitleAppli, this._frmOwner); 
            
            this._tre.getSelectionModel().clearSelection();
            
            if (this._tphTaskLast != null)
                this._tre.setSelectionPath(this._tphTaskLast);
            
            return;
        }*/
        
        /*if (str.compareTo(MIViewKstJksSysRootCA.s_strPrefix + " JKS" + " ...") == 0)
        {
            UtilKstJks.s_showFileKstCertsTrustSys(this._strTitleAppli, this._frmOwner); 
            this._tre.getSelectionModel().clearSelection();
            
            if (this._tphTaskLast != null)
                this._tre.setSelectionPath(this._tphTaskLast);
            
            return;
        }*/
        
        /*if (str.compareTo(MIViewKstJksUsrTrusSig.s_strPrefix + " JKS" + " ...") == 0)
        {
            TipOnLeafNode mtnParent = (TipOnLeafNode) mtn.getParent();
            
            String strParent = (String) mtnParent.getUserObject();
            
            if (strParent.compareTo(PCntsMainLeft._STR_NODEMANAGEKST) == 0)
                UtilKstJks.s_manageFileKstCertsTrustUsr(this._strTitleAppli, this._frmOwner); 
            else
                UtilKstJks.s_showFileKstCertsTrustUsr(this._strTitleAppli, this._frmOwner); 
            
            
            this._tre.getSelectionModel().clearSelection();
            
            if (this._tphTaskLast != null)
                this._tre.setSelectionPath(this._tphTaskLast);
            
            return;
        }*/
        
        /*if (str.compareTo(MIViewCrtPem.STR_TEXT) == 0)
        {
            UtilCrtX509Pem.s_showFile(this._strTitleAppli, this._frmOwner); 
            this._tre.getSelectionModel().clearSelection();
            
            if (this._tphTaskLast != null)
                this._tre.setSelectionPath(this._tphTaskLast);
            
            return;
        }*/
        
        /*if (str.compareTo(MIViewCrtDer.STR_TEXT) == 0)
        {
            UtilCrtX509Der.s_showFile(this._strTitleAppli, this._frmOwner); 
            this._tre.getSelectionModel().clearSelection();
            
            if (this._tphTaskLast != null)
                this._tre.setSelectionPath(this._tphTaskLast);
            
            return;
        }*/
        
        /*if (str.compareTo(MIViewCrtPkcs7.STR_TEXT) == 0)
        {
            UtilCrtX509Pkcs7.s_showFile(this._strTitleAppli, this._frmOwner); 
            this._tre.getSelectionModel().clearSelection();
            
            if (this._tphTaskLast != null)
                this._tre.setSelectionPath(this._tphTaskLast);
            
            return;
        }*/
        
        /*if (str.compareTo(MIViewCsrPkcs10.STR_TEXT) == 0)
        {
            UtilCsrPkcs10.s_showFile(this._strTitleAppli, this._frmOwner); 
            this._tre.getSelectionModel().clearSelection();
            
            if (this._tphTaskLast != null)
                this._tre.setSelectionPath(this._tphTaskLast);
            
            return;
        }*/
        
        
        // ----
        
        TreePath tph = this._tre.getSelectionModel().getSelectionPath();
        
        if (tph != null)
            this._tphTaskLast = tph;
    }
    
    
    
    // -------
    // private
    private TreePath _tphTaskLast = null;
    private TBSubTreeKtl _tbr = null;
    
    
    private JScrollPane _spe = null;
    private TipOnLeafTree _tre = null;

  

    private Frame _frmOwner;
    
    /*private TipOnLeafNode _createNodeView() 
    {
        TipOnLeafNode mtnTop = new TipOnLeafNode("View file");
        
        // ---
        TipOnLeafNode mtnChild = null;
        // ---
        
        // keystore
        TipOnLeafNode mtnKst = new TipOnLeafNode(PCntsMainLeft.STR_NODE_CRKST);
        mtnTop.add(mtnKst);
        
        
        
        mtnChild = new TipOnLeafNode(UtilKstJks.f_s_strKeystoreType + " ...", 
                "View " + UtilKstJks.f_s_strKeystoreType + " keystore file ...");
        mtnKst.add(mtnChild);
        
        
        mtnChild = new TipOnLeafNode(UtilKstJceks.f_s_strKeystoreType + " ...",
                "View " + UtilKstJceks.f_s_strKeystoreType + " keystore file ...");
        mtnKst.add(mtnChild);
        
        //if (com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs.s_isPolicyExtended())
        //{
            mtnChild = new TipOnLeafNode(UtilKstPkcs12.f_s_strKeystoreType + " ...",
                "View " + UtilKstPkcs12.f_s_strKeystoreType + " keystore file ...");
            mtnKst.add(mtnChild);

            mtnChild = new TipOnLeafNode(UtilKstBks.f_s_strKeystoreType + " ...",
                "View " + UtilKstBks.f_s_strKeystoreType + " keystore file ...");
            mtnKst.add(mtnChild);

            mtnChild = new TipOnLeafNode(UtilKstUber.f_s_strKeystoreType + " ...",
                "View " + UtilKstUber.f_s_strKeystoreType + " keystore file ...");
            mtnKst.add(mtnChild);  
        //}
        
        mtnChild = new TipOnLeafNode(MIViewKstJksSysRootCA.s_strPrefix + " JKS" + " ...",
                "View " + MIViewKstJksSysRootCA.s_strPrefix + " JKS keystore file ...");
        mtnKst.add(mtnChild);
        
        if (_canGetUserLevelTrustedCertsKst())
        {
            mtnChild = new TipOnLeafNode(MIViewKstJksUsrTrusSig.s_strPrefix + " JKS" + " ...",
                "View " + MIViewKstJksUsrTrusSig.s_strPrefix + " JKS keystore file ...");
            mtnKst.add(mtnChild);
        }
        
        
        // certificate
        
        TipOnLeafNode mtnCrt = new TipOnLeafNode("Certificate");
        mtnTop.add(mtnCrt);
        
        mtnChild = new TipOnLeafNode(MIViewCrtPem.STR_TEXT, 
                "View " + MIViewCrtPem.STR_TEXT);
        mtnCrt.add(mtnChild);
        
        mtnChild = new TipOnLeafNode(MIViewCrtDer.STR_TEXT, 
                "View " + MIViewCrtDer.STR_TEXT);
        mtnCrt.add(mtnChild);
        
        mtnChild = new TipOnLeafNode(MIViewCrtPkcs7.STR_TEXT, 
                "View " + MIViewCrtPkcs7.STR_TEXT);
        mtnCrt.add(mtnChild);
        
        mtnChild = new TipOnLeafNode(MIViewCsrPkcs10.STR_TEXT, 
                "View " + MIViewCsrPkcs10.STR_TEXT);
        mtnCrt.add(mtnChild);
        
        // ending
        return mtnTop;
    }*/
    
    /*private TipOnLeafNode _createNodeManage() 
    {
        TipOnLeafNode mtnTop = new TipOnLeafNode(PCntsMainLeft._STR_NODEMANAGEKST);
        
        // ---
        TipOnLeafNode mtnChild = null;
        // ---
       
        
        mtnChild = new TipOnLeafNode(UtilKstJks.f_s_strKeystoreType + " ...",
                "Manage " + UtilKstJks.f_s_strKeystoreType + " keystore file ...");
        mtnTop.add(mtnChild);
        
        mtnChild = new TipOnLeafNode(UtilKstJceks.f_s_strKeystoreType + " ...",
                "Manage " + UtilKstJceks.f_s_strKeystoreType + " keystore file ...");
        mtnTop.add(mtnChild);
        
        //if (com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs.s_isPolicyExtended())
        //{
            mtnChild = new TipOnLeafNode(UtilKstPkcs12.f_s_strKeystoreType + " ...",
                "Manage " + UtilKstPkcs12.f_s_strKeystoreType + " keystore file ...");
            mtnTop.add(mtnChild);

            mtnChild = new TipOnLeafNode(UtilKstBks.f_s_strKeystoreType + " ...",
                "Manage " + UtilKstBks.f_s_strKeystoreType + " keystore file ...");
            mtnTop.add(mtnChild);

            mtnChild = new TipOnLeafNode(UtilKstUber.f_s_strKeystoreType + " ...",
                "Manage " + UtilKstUber.f_s_strKeystoreType + " keystore file ...");
            mtnTop.add(mtnChild);
        //}
        
        // TEMPO COZ possible tbrl if password changed!
        //if (_canGetUserLevelTrustedCertsKst())
        //{
          //  mtnChild = new TipOnLeafNode(MIViewKstJksUsrTrusSig.s_strPrefix + " JKS" + " ...");
            //mtnTop.add(mtnChild);
        //}
        
        
        return mtnTop;
    }*/
    
    private TipOnLeafNode _createNodeCreate() 
    {
        TipOnLeafNode mtnTop = new TipOnLeafNode(PCntsMainLeft.STR_NODE_CREATE);
        
         // ---
        TipOnLeafNode mtnChild = null;
        // ---
        
        // keystore
        mtnChild = new TipOnLeafNode(PCntsMainLeft.STR_NODE_CRKST, PTabUICmdKtlKstSave.STR_TITLETASK);
        mtnTop.add(mtnChild);
        
        // ----
        // keystore's entry
        TipOnLeafNode mtnEnt = new TipOnLeafNode(PCntsMainLeft.STR_NODE_KW_ENTRY);
        mtnTop.add(mtnEnt);
        
        
        // 1/3
        // private key, version #1 certificate
        TipOnLeafNode mtnPKV1C = new TipOnLeafNode(STR_NODE_KW_PK_V1C);
        mtnEnt.add(mtnPKV1C);
        
        // DSA
        mtnChild = new TipOnLeafNode(PCntsMainLeft.STR_NODE_CRENTPKDSA, PTabUICmdKtlKstOpenCrKprDsa.STR_TITLETASK);
        mtnPKV1C.add(mtnChild);
        
        // RSA
        mtnChild = new TipOnLeafNode(PCntsMainLeft.STR_NODE_CRENTPKRSA, PTabUICmdKtlKstOpenCrKprRsa.STR_TITLETASK);
        mtnPKV1C.add(mtnChild);
        
        // EC
        mtnChild = new TipOnLeafNode(PCntsMainLeft.STR_NODE_CRENTPKEC, PTabUICmdKtlKstOpenCrKprEc.STR_TITLETASK);
        mtnPKV1C.add(mtnChild);
        
        // 2/3
        // private key, version #3 certificate
        TipOnLeafNode mtnPKV3C = new TipOnLeafNode(STR_NODE_KW_PK_V3C);
        mtnEnt.add(mtnPKV3C);

        /* TODO in comments coz conflicts
        // DSA
        mtnChild = new TipOnLeafNode(PCntsMainLeft.STR_NODE_CRENTPKDSA, PTabUICmdKtlKstOpenCrKprV3CDsa.STR_TITLETASK);
        mtnPKV3C.add(mtnChild);
        
        // RSA
        mtnChild = new TipOnLeafNode(PCntsMainLeft.STR_NODE_CRENTPKRSA, PTabUICmdKtlKstOpenCrKprV3CRsa.STR_TITLETASK);
        mtnPKV3C.add(mtnChild);
        
        // EC
        mtnChild = new TipOnLeafNode(PCntsMainLeft.STR_NODE_CRENTPKEC, PTabUICmdKtlKstOpenCrKprV3CEc.STR_TITLETASK);
        mtnPKV3C.add(mtnChild);
        */
        
        
        
        // 3/3
        // secret key
        TipOnLeafNode mtnSK = new TipOnLeafNode(PCntsMainLeft.STR_NODE_KW_SK, PTabUICmdKtlKstOpenCrShkAll.STR_TITLETASK);
        mtnEnt.add(mtnSK);
        
        // ----
       
        
        // ----
        return mtnTop;
    }
    
    private TipOnLeafNode _createNodeArchiveFiles() 
    {
        TipOnLeafNode mtnTop = new TipOnLeafNode("Archive files");
        
         // ---
        TipOnLeafNode mtnChild = null;
        // ---

        mtnChild = new TipOnLeafNode(PCntsMainLeft.STR_NODE_ARCDIR, PTabUICmdArcDir.STR_TITLETASK);
        mtnTop.add(mtnChild);
        
        // 
 
        
        // 
        return mtnTop;
    }
    
    private TipOnLeafNode _createNodeSign() 
    {
        TipOnLeafNode mtnTop = new TipOnLeafNode(PCntsMainLeft.STR_NODE_CIPHERSIGN);
        
         // ---
        TipOnLeafNode mtnChild = null;
        // ---
        
        TipOnLeafNode mtnFolderDetached = new TipOnLeafNode(PCntsMainLeft.STR_NODE_CIPHERDET);
        mtnTop.add(mtnFolderDetached);

        mtnChild = new TipOnLeafNode(PCntsMainLeft.STR_NODE_CIPHERDETCMS, PTabUICmdKtlKstOpenSCmsExpKpr.STR_TITLETASK);
        mtnFolderDetached.add(mtnChild);
        
        mtnChild = new TipOnLeafNode(PCntsMainLeft.STR_NODE_CIPHERDETOTHER, PTabUICmdKtlKstOpenSigExpKpr.STR_TITLETASK);
        mtnFolderDetached.add(mtnChild);
        
        
        
        // 
        TipOnLeafNode mtnFolderEmbedded = new TipOnLeafNode(PCntsMainLeft.STR_NODE_CIPHEREMB);
        mtnTop.add(mtnFolderEmbedded);
        
        mtnChild = new TipOnLeafNode(PCntsMainLeft.STR_NODE_CIPHEREMBXML, PTabUICmdKtlKstOpenXmlSigExpKpr.STR_TITLETASK);
        mtnFolderEmbedded.add(mtnChild);
        
        
        mtnChild = new TipOnLeafNode(PCntsMainLeft.STR_NODE_CIPHEREMBJAR, PTabUICmdJsrSign.STR_TITLETASK);
        mtnFolderEmbedded.add(mtnChild);
        
        // 
        return mtnTop;
    }
    
    private TipOnLeafNode _createNodeWelcome() 
    {
        TipOnLeafNode mtnTop = new TipOnLeafNode(PCntsMainLeft.STR_NODE_WELCOME, PTabHtmlWelcomeKtl.STR_TITLETASK);
        
       
        
        // 
        return mtnTop;
    }
    
    private TipOnLeafNode _createNodeVerify() 
    {
        TipOnLeafNode mtnTop = new TipOnLeafNode(PCntsMainLeft.STR_NODE_CIPHERVERIFY);
        
         // ---
        TipOnLeafNode mtnChild = null;
        // ---
        
        TipOnLeafNode mtnFolderDetached = new TipOnLeafNode(PCntsMainLeft.STR_NODE_CIPHERDET);
        mtnTop.add(mtnFolderDetached);

        mtnChild = new TipOnLeafNode(PCntsMainLeft.STR_NODE_CIPHERDETCMS, PTabUICmdCmsSigVerify.STR_TITLETASK);
        mtnFolderDetached.add(mtnChild);
        
        mtnChild = new TipOnLeafNode(PCntsMainLeft.STR_NODE_CIPHERDETOTHER, PTabUICmdKtlKstOpenSigVerCrt.STR_TITLETASK);
        mtnFolderDetached.add(mtnChild);
        
        
        // 
        TipOnLeafNode mtnFolderEmbedded = new TipOnLeafNode(PCntsMainLeft.STR_NODE_CIPHEREMB);
        mtnTop.add(mtnFolderEmbedded);
        
        mtnChild = new TipOnLeafNode(PCntsMainLeft.STR_NODE_CIPHEREMBXML, PTabUICmdXmlSigVerify.STR_TITLETASK);
        mtnFolderEmbedded.add(mtnChild);
        
        
        mtnChild = new TipOnLeafNode(PCntsMainLeft.STR_NODE_CIPHEREMBJAR, PTabUICmdJsrVerify.STR_TITLETASK);
        mtnFolderEmbedded.add(mtnChild);
        
        // 
        return mtnTop;
    }
    

    
    private TipOnLeafNode _createNodeEncrypt() 
    {
        TipOnLeafNode mtnTop = new TipOnLeafNode(PCntsMainLeft.STR_NODE_CRYPTENC);
        
         // ---
        TipOnLeafNode mtnChild = null;
        // ---

        mtnChild = new TipOnLeafNode(PCntsMainLeft.STR_NODE_CRYPTSK, PTabUICmdKtlKstOpenCryptEncShk.STR_TITLETASK);
        mtnTop.add(mtnChild);
        
        mtnChild = new TipOnLeafNode(PCntsMainLeft.STR_NODE_CRYPTTC, PTabUICmdKtlKstOpenCryptEncTC.STR_TITLETASK);
        mtnTop.add(mtnChild);
        
        mtnChild = new TipOnLeafNode(PCntsMainLeft.STR_NODE_CRYPTPK, PTabUICmdKtlKstOpenCryptEncPK.STR_TITLETASK);
        mtnTop.add(mtnChild);
        
        // 
        return mtnTop;
    }
    
    private TipOnLeafNode _createNodeDecrypt() 
    {
        TipOnLeafNode mtnTop = new TipOnLeafNode(PCntsMainLeft.STR_NODE_CRYPTDEC);
        
         // ---
        TipOnLeafNode mtnChild = null;
        // ---

        mtnChild = new TipOnLeafNode(PCntsMainLeft.STR_NODE_CRYPTSK, PTabUICmdKtlKstOpenCryptDecShk.STR_TITLETASK);
        mtnTop.add(mtnChild);
        
        mtnChild = new TipOnLeafNode(PCntsMainLeft.STR_NODE_CRYPTPK, PTabUICmdKtlKstOpenCryptDecPK.STR_TITLETASK);
        mtnTop.add(mtnChild);
        
        // 
        return mtnTop;
    }
    
    private TipOnLeafNode _createNodeImport() 
    {
        TipOnLeafNode mtnTop = new TipOnLeafNode(PCntsMainLeft.STR_NODE_IOIN);
        
         // ---
        TipOnLeafNode mtnChild = null;
        // ---

        
        // keystore's entry
        TipOnLeafNode mtnKstEnt = new TipOnLeafNode(PCntsMainLeft.STR_NODE_KW_ENTRY);
        mtnTop.add(mtnKstEnt);
        
        mtnChild = new TipOnLeafNode(PCntsMainLeft.STR_NODE_KW_SK, PTabUICmdKtlKstOpenIOShkIn.STR_TITLETASK);
        mtnKstEnt.add(mtnChild);
        
        mtnChild = new TipOnLeafNode(PCntsMainLeft.STR_NODE_KW_PK);
        mtnKstEnt.add(mtnChild);
        
        // -- beg import PK from [DER-PEM] file -- other KST
        TipOnLeafNode mtnChild2 = null;
        
        mtnChild2 = new TipOnLeafNode(PCntsMainLeft.STR_NODE_KW_PKDER, PTabUICmdKtlKstOpenKprFromKprDer.STR_TITLETASK);
        mtnChild.add(mtnChild2);
        
        mtnChild2 = new TipOnLeafNode(PCntsMainLeft.STR_NODE_KW_PKPEM, PTabUICmdKtlKstOpenKprFromKprPem.STR_TITLETASK);
        mtnChild.add(mtnChild2);
        
        mtnChild2 = new TipOnLeafNode(PCntsMainLeft.STR_NODE_IOENTPKOTHER, PTabUICmdKtlKstOpenKprFromKprKst.STR_TITLETASK);
        mtnChild.add(mtnChild2);
        // -- end import PK from [DER-PEM] file -- other KST
        
        
        // -- beg import trusted certificate
        TipOnLeafNode mtnChild3 = null;
        
        mtnChild = new TipOnLeafNode(PCntsMainLeft.STR_NODE_KW_TC, "PTabUICmdKtlKstOpenCrtImpTcr.STR_TITLETASK");
        mtnKstEnt.add(mtnChild);
        
        mtnChild3 = new TipOnLeafNode(PCntsMainLeft.STR_NODE_KW_TCREGULAR, PTabUICmdKtlKstOpenCrtImpTcr.STR_TITLETASK);
        mtnChild.add(mtnChild3);
        
        mtnChild3 = new TipOnLeafNode(PCntsMainLeft.STR_NODE_KW_TCROOTCA, PTabUICmdKtlKstOpenCCaImpTcr.STR_TITLETASK);
        mtnChild.add(mtnChild3);
        
        // -- end import trusted certificate
        
        //mtnChild = new TipOnLeafNode(PCntsMainLeft.STR_NODE_IOENTPKOTHER, PTabUICmdKtlKstOpenKprFromKprKst.STR_TITLETASK);
        //mtnKstEnt.add(mtnChild);
        
        
        // ----
        TipOnLeafNode mtnPkzCrt = new TipOnLeafNode(PCntsMainLeft.STR_NODE_IOCACRTREPLY, PTabUICmdKtlKstOpenCrtImpReply.STR_TITLETASK);
        mtnTop.add(mtnPkzCrt);
        
        // 
        return mtnTop;
    }
    
    private TipOnLeafNode _createNodeExport() 
    {
        TipOnLeafNode mtnTop = new TipOnLeafNode(PCntsMainLeft.STR_NODE_IOOUT);
        
         // ---
        TipOnLeafNode mtnChild = null;
        // ---

        
        // keystore's entry
        TipOnLeafNode mtnKstEnt = new TipOnLeafNode(PCntsMainLeft.STR_NODE_KW_ENTRY);
        mtnTop.add(mtnKstEnt);
        
        
        
        mtnChild = new TipOnLeafNode(PCntsMainLeft.STR_NODE_KW_SK, PTabUICmdKtlKstOpenIOShkOut.STR_TITLETASK);
        mtnKstEnt.add(mtnChild);
        
        mtnChild = new TipOnLeafNode(PCntsMainLeft.STR_NODE_KW_PK, PTabUICmdKtlKstOpenKprExpKpr.STR_TITLETASK);
        mtnKstEnt.add(mtnChild);
        
        mtnChild = new TipOnLeafNode(PCntsMainLeft.STR_NODE_KW_TC, PTabUICmdKtlKstOpenCrtExpTcr.STR_TITLETASK);
        mtnKstEnt.add(mtnChild);
        
        
        
        // ----
        TipOnLeafNode mtnPkzCrt = new TipOnLeafNode(PCntsMainLeft.STR_NODE_IOPKZCRT);
        mtnTop.add(mtnPkzCrt);
        
        // ----
        
        mtnChild = new TipOnLeafNode(PCntsMainLeft.STR_NODE_IOPKZCRT2CSR, PTabUICmdKtlKstOpenCrtSigReq.STR_TITLETASK);
        mtnPkzCrt.add(mtnChild);
        
        mtnChild = new TipOnLeafNode(PCntsMainLeft.STR_NODE_IOPKZCRT2CRT, PTabUICmdKtlKstOpenCrtExpKpr.STR_TITLETASK);
        mtnPkzCrt.add(mtnChild);
        
        // 
        return mtnTop;
    }
    
    private TipOnLeafNode _createNodeMain() 
    {
        TipOnLeafNode mtnRoot = new TipOnLeafNode("Keytool IUI");
        
        
        TipOnLeafNode mtn = null;
        
        // verify
        mtn = _createNodeWelcome();
        mtnRoot.add(mtn);
        
        // view
        //mtn = _createNodeView();
        //mtnRoot.add(mtn);
        
        // manage
        //mtn = _createNodeManage();
        //mtnRoot.add(mtn);
        
        // create
        mtn = _createNodeCreate();
        mtnRoot.add(mtn);
        
        // archive
        mtn = _createNodeArchiveFiles();
        mtnRoot.add(mtn);
        
        
        // sign
        mtn = _createNodeSign();
        mtnRoot.add(mtn);
        
        // verify
        mtn = _createNodeVerify();
        mtnRoot.add(mtn);
        
        // encrypt
        mtn = _createNodeEncrypt();
        mtnRoot.add(mtn);
        
        // decrypt
        mtn = _createNodeDecrypt();
        mtnRoot.add(mtn);
        
        // import 
        mtn = _createNodeImport();
        mtnRoot.add(mtn);
        
        // export
        mtn = _createNodeExport();
        mtnRoot.add(mtn);
        
        
        
        /*TipOnLeafNode category = null;
        TipOnLeafNode book = null;

        category = new TipOnLeafNode("Books for Java Programmers");
        mtnRoot.add(category);

        //original Tutorial
        book = new TipOnLeafNode(new BookInfo
            ("The Java Tutorial: A Short Course on the Basics",
            "tutorial.html"));
        category.add(book);

        //Tutorial Continued
        book = new TipOnLeafNode(new BookInfo
            ("The Java Tutorial Continued: The Rest of the JDK",
            "tutorialcont.html"));
        category.add(book);

        //JFC Swing Tutorial
        book = new TipOnLeafNode(new BookInfo
            ("The JFC Swing Tutorial: A Guide to Constructing GUIs",
            "swingtutorial.html"));
        category.add(book);

        //Bloch
        book = new TipOnLeafNode(new BookInfo
            ("Effective Java Programming Language Guide",
	     "bloch.html"));
        category.add(book);

        //Arnold/Gosling
        book = new TipOnLeafNode(new BookInfo
            ("The Java Programming Language", "arnold.html"));
        category.add(book);

        //Chan
        book = new TipOnLeafNode(new BookInfo
            ("The Java Developers Almanac",
             "chan.html"));
        category.add(book);

        category = new TipOnLeafNode("Books for Java Implementers");
        mtnRoot.add(category);

        //VM
        book = new TipOnLeafNode(new BookInfo
            ("The Java Virtual Machine Specification",
             "vm.html"));
        category.add(book);

        //Language Spec
        book = new TipOnLeafNode(new BookInfo
            ("The Java Language Specification",
             "jls.html"));
        category.add(book);
        */
        
        return mtnRoot;
    }

    // !!!! for now it's OK, however, if kesytore is created during appli's session,
    // this will not be visible anyway,
    // will require to quit and restart appli to see it
    private boolean _canGetUserLevelTrustedCertsKst()
    {
        if (! (com.google.code.p.keytooliui.shared.io.S_OperatingSystem.s_isUnix() || com.google.code.p.keytooliui.shared.io.S_OperatingSystem.s_isWindows()))
            return false;
        
        java.io.File fleCertsTrustUsr = 
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJks.s_getFileKstCertsTrustUsr();
        
        if (fleCertsTrustUsr == null)
            return false;
        
        if (! fleCertsTrustUsr.exists())
            return false;
            
        return true;
    }

    public void treeExpanded(TreeExpansionEvent event) 
    {
        // folder all collapse
        if (this._tre.isAllowedFolderAllCollapse())
            this._tbr.setEnabledButtonFolderClose(true);
        else
            this._tbr.setEnabledButtonFolderClose(false);
            
        // folder all expand
        if (this._tre.isAllowedFolderAllExpand())
            this._tbr.setEnabledButtonFolderOpen(true);
        else
            this._tbr.setEnabledButtonFolderOpen(false);
    }

    public void treeCollapsed(TreeExpansionEvent event) 
    {
        // folder all expand
        if (this._tre.isAllowedFolderAllExpand())
            this._tbr.setEnabledButtonFolderOpen(true);
        else
            this._tbr.setEnabledButtonFolderOpen(false);
            
        // folder all collapse
        if (this._tre.isAllowedFolderAllCollapse())
            this._tbr.setEnabledButtonFolderClose(true);
        else
            this._tbr.setEnabledButtonFolderClose(false);
    }

    
    
    
}
