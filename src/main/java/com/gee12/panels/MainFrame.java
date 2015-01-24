package com.gee12.panels;

import com.gee12.other.Utils;
import com.gee12.other.SwitchStageListener;
import com.gee12.other.XLSParser;
import com.gee12.structures.Project;
import com.gee12.structures.Project.Stages;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import static java.awt.EventQueue.invokeLater;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Иван
 */
public class MainFrame extends JFrame implements SwitchStageListener {
    
    public static final String TEMP_FILE_NAME = "_temp.xls";
    
    JPanel cards;
    private ChoisePanel choisePanel;
    private CooperatePanel cooperatePanel;
    private RatingPanel ratingPanel;
    
    private JButton closeButton;
    private JButton createButton;
    private JToolBar toolBar;
    private JButton openButton;
    private JButton saveButton;
    private JButton saveAsButton;
    
    private String projectFileName = null;            
    private Project curProject = null;
    
    ////////////////////////////////////////////////////////////////////////////
    public MainFrame() {
        
        initComponents();
        
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//        setLocationByPlatform(true);
        setLocation(100, 0);
        setPreferredSize(new Dimension(900,650));
        setResizable(false);

        // add toolbar and buttons
        toolBar = new javax.swing.JToolBar();
        createButton = new javax.swing.JButton();
        openButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        saveAsButton = new javax.swing.JButton();

        toolBar.setFloatable(false);
        toolBar.setRollover(true);

        createButton.setText("Создать");
        createButton.setIcon(UIManager.getIcon("FileView.fileIcon"));
        createButton.setToolTipText("Создать проект");
        createButton.setEnabled(true);
        createButton.setFocusable(false);
        createButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        createButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });
        toolBar.add(createButton);

        openButton.setText("Открыть");
        openButton.setIcon(UIManager.getIcon("FileView.directoryIcon"));
        openButton.setToolTipText("Открыть проект");
        openButton.setFocusable(false);
        openButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });
        toolBar.add(openButton);
        
        saveButton.setText("Сохранить");
        saveButton.setIcon(new ImageIcon(MainFrame.class.getResource("/images/save.jpg")));
        saveButton.setToolTipText("Сохранить текущий проект");
        saveButton.setEnabled(false);
        saveButton.setFocusable(false);
        saveButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        toolBar.add(saveButton); 
        
        saveAsButton.setText("Сохранить как");
        saveAsButton.setIcon(new ImageIcon(MainFrame.class.getResource("/images/saveas.jpg")));
        saveAsButton.setToolTipText("Сохранить проект как");
        saveAsButton.setEnabled(false);
        saveAsButton.setFocusable(false);
        saveAsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveAsButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        saveAsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsButtonActionPerformed(evt);
            }
        });
        toolBar.add(saveAsButton); 
        
        closeButton.setText("Закрыть");
        closeButton.setIcon(new ImageIcon(MainFrame.class.getResource("/images/close.jpg")));
        closeButton.setToolTipText("Закрыть проект");
        closeButton.setEnabled(false);
        closeButton.setFocusable(false);
        closeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        closeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        toolBar.add(closeButton);        

        add(toolBar, BorderLayout.PAGE_START);
        
        // create the panel that contains the "cards".
        choisePanel = new ChoisePanel(this);
        cooperatePanel = new CooperatePanel(this);
        ratingPanel = new RatingPanel(this);
        cards = new JPanel(new CardLayout());
        cards.add(new JPanel(), Project.Stages.STAGE0_START.toString());
        cards.add(choisePanel, Project.Stages.STAGE1_CHOISE.toString());
        cards.add(cooperatePanel, Project.Stages.STAGE2_COOPERATION.toString());
        cards.add(ratingPanel, Project.Stages.STAGE3_RATING.toString());
        add(cards, BorderLayout.CENTER);

        pack();        
    }

    ////////////////////////////////////////////////////////////////////////////
    @Override
    public void nextStage(Project.Stages stage) {
        switch(stage) {
            case STAGE1_CHOISE:
                choisePanel.init(curProject, projectFileName);
                break;
            case STAGE2_COOPERATION:
                cooperatePanel.init(curProject, choisePanel.getCurrentCarrier(), projectFileName);
                break;
                
            case STAGE3_RATING:
                curProject = cooperatePanel.getProject();
                curProject.defineMarks();
                ratingPanel.init(curProject, choisePanel.getCurrentCarrier(), projectFileName);
                break;
            default:
                
                break;
        }
        
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, stage.toString());
    }
    
    ////////////////////////////////////////////////////////////////////////////
    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser createShooser = new JFileChooser();
        createShooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        createShooser.setApproveButtonText("Создать");
        createShooser.setDialogTitle("Создание нового проекта");
        createShooser.setDialogType(JFileChooser.OPEN_DIALOG);
        createShooser.setMultiSelectionEnabled(false);
        int returnVal = createShooser.showOpenDialog(this);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            projectFileName = createShooser.getSelectedFile().getAbsolutePath();
            projectFileName = XLSParser.withXLSExt(projectFileName);
            curProject = new Project();
            
            // create temp file
            XLSParser.createXLSFile(TEMP_FILE_NAME);
            XLSParser.saveXLSProject(TEMP_FILE_NAME, curProject);
            
            nextStage(Project.Stages.STAGE1_CHOISE);
            
            closeButton.setEnabled(true);
            saveButton.setEnabled(true);
            saveAsButton.setEnabled(true);
        }   
    }                                            

    ////////////////////////////////////////////////////////////////////////////
    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        JFileChooser openChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Таблицы MS Office (*.xls)", "xls");
        openChooser.setFileFilter(filter);
        openChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        openChooser.setApproveButtonText("Открыть");
        openChooser.setDialogTitle("Открытие нового проекта");
        openChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        openChooser.setMultiSelectionEnabled(false);
        int returnVal = openChooser.showOpenDialog(this);
        
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            projectFileName = openChooser.getSelectedFile().getAbsolutePath();
            curProject = XLSParser.readXLSProject(projectFileName);
            
            // create temp file
            XLSParser.createXLSFile(TEMP_FILE_NAME);
            XLSParser.saveXLSProject(TEMP_FILE_NAME, curProject);
            
            nextStage(Project.Stages.STAGE1_CHOISE);
            
            closeButton.setEnabled(true);
            saveButton.setEnabled(true);
            saveAsButton.setEnabled(true);
        }
    }                                          

    ////////////////////////////////////////////////////////////////////////////
    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        nextStage(Project.Stages.STAGE0_START);
            projectFileName = null;
            curProject = null;
            
            closeButton.setEnabled(false);
            saveButton.setEnabled(false);
            saveAsButton.setEnabled(false);
    }     

    ////////////////////////////////////////////////////////////////////////////
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {  
            // write project to file
//        XLSParser.saveXLSProject(projectFileName, curProject);
        try {
            Utils.copyFile(TEMP_FILE_NAME, projectFileName);
        } catch (IOException ex) {
            Utils.onException(ex);
        }
    }      

    ////////////////////////////////////////////////////////////////////////////
    private void saveAsButtonActionPerformed(java.awt.event.ActionEvent evt) {  
        JFileChooser saveAsChooser = new JFileChooser();
        saveAsChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        saveAsChooser.setApproveButtonText("Сохранить");
        saveAsChooser.setDialogTitle("Сохранение проекта как");
        saveAsChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        saveAsChooser.setMultiSelectionEnabled(false);
        int returnVal = saveAsChooser.showSaveDialog(this);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            projectFileName = saveAsChooser.getSelectedFile().getAbsolutePath();
            projectFileName = XLSParser.withXLSExt(projectFileName);
            
//            // create file
//            XLSParser.createXLSFile(projectFileName);
//            // write project to file
//            XLSParser.saveXLSProject(projectFileName, curProject);
            try {
                Utils.copyFile(TEMP_FILE_NAME, projectFileName);
            } catch (IOException ex) {
                Utils.onException(ex);
            }            
        } 
    }      
    ////////////////////////////////////////////////////////////////////////////
    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    
}
