package com.gee12.panels;

import com.gee12.other.SwitchStageListener;
import com.gee12.other.ExcelParser;
import com.gee12.other.Utils;
import com.gee12.structures.Carrier;
import com.gee12.structures.Project;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import static java.awt.EventQueue.invokeLater;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.UIManager;

/**
 *
 * @author Иван
 */
public class MainFrame extends JFrame implements SwitchStageListener {
    
    public static final String TITLE = "CarrierChoise 1.0";
    public static final String TEMPORARY_FILE_NAME = "temporary.xls";
    public static final String TEMPLATE_FILE_NAME = "template.xls";
    
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
    private static Carrier tempCarrier = null;
    private Carrier curCarrier = null;
    
    ////////////////////////////////////////////////////////////////////////////
    public MainFrame() {
        try {
            //
            initComponents();
            // load temporary carrier
            Project tempProject = ExcelParser.readExcelFile(TEMPLATE_FILE_NAME);
            tempCarrier = tempProject.getCarrier(TEMPLATE_FILE_NAME);
            
            setIconImage(ImageIO.read(MainFrame.class.getResource("/images/main.png")));
            
        } catch (IOException ex) {
            Utils.onException(ex);
        }
        
        if (tempCarrier == null) {
            tempCarrier = new Carrier();
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public void initComponents() throws IOException {
        setTitle(null);
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
        saveButton.setIcon(new ImageIcon(MainFrame.class.getResource("/images/save.gif")));
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
        saveAsButton.setIcon(new ImageIcon(MainFrame.class.getResource("/images/saveas.png")));
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
        closeButton.setIcon(new ImageIcon(MainFrame.class.getResource("/images/close.png")));
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
                curCarrier = choisePanel.getCurrentCarrier();
                cooperatePanel.init(curCarrier, projectFileName);
                break;
            case STAGE3_RATING:
                
                ratingPanel.init(curCarrier, projectFileName);
                break;
            default:
                break;
        }
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, stage.toString());
    }
    
    ////////////////////////////////////////////////////////////////////////////
    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (JFileChoosers.showCreateChooser(this) == JFileChooser.APPROVE_OPTION) {
            // get file name
            projectFileName = JFileChoosers.getChooser().getSelectedFile().getAbsolutePath();
            projectFileName = ExcelParser.addXLSExtension(projectFileName);
            curProject = new Project();
            // create file
            ExcelParser.createXLSFile(projectFileName);
            // switch panel
            nextStage(Project.Stages.STAGE1_CHOISE);
            // title
            setTitle(projectFileName);
            // buttons
            closeButton.setEnabled(true);
            saveButton.setEnabled(true);
            saveAsButton.setEnabled(true);
        }   
    }                                            

    ////////////////////////////////////////////////////////////////////////////
    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        if(JFileChoosers.showOpenChooser(this) == JFileChooser.APPROVE_OPTION) {
            // get file name
            projectFileName = JFileChoosers.getChooser().getSelectedFile().getAbsolutePath();
            // create current carrier from project
            curProject = ExcelParser.readExcelFile(projectFileName);
            // switch panel
            nextStage(Project.Stages.STAGE1_CHOISE);
            // title
            setTitle(projectFileName);
            // buttons
            closeButton.setEnabled(true);
            saveButton.setEnabled(true);
            saveAsButton.setEnabled(true);
        }
    }                                          

    ////////////////////////////////////////////////////////////////////////////
    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        nextStage(Project.Stages.STAGE0_START);
            projectFileName = null;
            curCarrier = null;
            // title
            setTitle(projectFileName);
            // buttons
            closeButton.setEnabled(false);
            saveButton.setEnabled(false);
            saveAsButton.setEnabled(false);
    }     

    ////////////////////////////////////////////////////////////////////////////
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {  
        // create file (for overwrite)
        ExcelParser.createXLSFile(projectFileName);
        // write project to file
        curCarrier = choisePanel.getCurrentCarrier();
        ExcelParser.saveXLSCarrierProject(projectFileName, curProject, curCarrier);
    }      

    ////////////////////////////////////////////////////////////////////////////
    private void saveAsButtonActionPerformed(java.awt.event.ActionEvent evt) {  
        if (JFileChoosers.showSaveChooser(this) == JFileChooser.APPROVE_OPTION) {
            // get file name
            projectFileName = JFileChoosers.getChooser().getSelectedFile().getAbsolutePath();
            projectFileName = ExcelParser.addXLSExtension(projectFileName);
            // create file
            ExcelParser.createXLSFile(projectFileName);
            // title
            setTitle(projectFileName);
            // write project to file
            curCarrier = choisePanel.getCurrentCarrier();
            ExcelParser.saveXLSCarrierProject(projectFileName, curProject, curCarrier);
        } 
    }      
    
    ////////////////////////////////////////////////////////////////////////////
    @Override
    public void setTitle(String fileName) {
        String title = (fileName != null) ? TITLE + " - " + fileName : TITLE;
        super.setTitle(title);
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static Carrier getTemplate() {
        return tempCarrier;
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
