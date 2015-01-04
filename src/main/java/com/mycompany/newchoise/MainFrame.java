package com.mycompany.newchoise;

import com.mycompany.newchoise.CriterionDialog.ActionType;
import static com.mycompany.newchoise.Project.Stages;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.util.CellReference;

/**
 *
 * @author Иван
 */
public class MainFrame extends javax.swing.JFrame {

    private Stages curStage = Stages.STAGE1_START;
    private final CriterionsTableModel baseCriterionsTM;
    private final CriterionsTableModel otherCriterionsTM;
    private final DataTableModel baseDataTM;
    private final DataTableModel otherDataTM;
    private Project curProject = null;
    private String projectFileName = null;
    
    public MainFrame() {
        
        baseCriterionsTM = new CriterionsTableModel();
        otherCriterionsTM = new CriterionsTableModel();
        baseDataTM = new DataTableModel();
        otherDataTM = new DataTableModel();
        
        initComponents();
        setLocationRelativeTo(getParent());
        baseCriteriorsTable.getColumnModel().getColumn(0).setPreferredWidth((int)(
            baseCriteriorsTable.getPreferredScrollableViewportSize().width * 0.88));
        otherCriteriorsTable.getColumnModel().getColumn(0).setPreferredWidth((int)(
            otherCriteriorsTable.getPreferredScrollableViewportSize().width * 0.88));
        baseDataTable.getColumnModel().getColumn(0).setPreferredWidth((int)(
            baseDataTable.getPreferredScrollableViewportSize().width * 0.88));
        otherDataTable.getColumnModel().getColumn(0).setPreferredWidth((int)(
            otherDataTable.getPreferredScrollableViewportSize().width * 0.88));
        
        initFields(curStage, null);
    }
    
    public void initFields(Stages stage, Project proj) {
        
        switch (stage) {
            case STAGE1_START:
                curProject = null;
                statusLabel.setText("Откройте или создайте проект для начала работы");
                baseCriterionsTM.setData(new ArrayList<Criterion>());
                otherCriterionsTM.setData(new ArrayList<Criterion>());
                baseDataTM.setData(new ArrayList<DataField>());
                otherDataTM.setData(new ArrayList<DataField>());
                closeButton.setEnabled(false);
                prevButton.setEnabled(false);
                nextButton.setEnabled(false);
                baseCriterionButton.setEnabled(false);
                otherCriterionButton.setEnabled(false);
                baseDataButton.setEnabled(false);
                otherDataButton.setEnabled(false);
                break;
            case STAGE2_COOPERATION:
                if (proj == null) return;
                statusLabel.setText("Этап сотрудничества с " + proj.getCarrier().getName());
                baseCriterionsTM.setData(proj.getStage(Project.Stages.STAGE2_COOPERATION).getBaseCriterions());
                otherCriterionsTM.setData(proj.getStage(Project.Stages.STAGE2_COOPERATION).getOtherCriterions());
                baseDataTM.setData(proj.getStage(Project.Stages.STAGE2_COOPERATION).getBaseDataFileds());
                otherDataTM.setData(proj.getStage(Project.Stages.STAGE2_COOPERATION).getOtherDataFileds());
                closeButton.setEnabled(true);
                prevButton.setEnabled(false);
                nextButton.setEnabled(true);
                baseCriterionButton.setEnabled(true);
                otherCriterionButton.setEnabled(true);
                baseDataButton.setEnabled(true);
                otherDataButton.setEnabled(true);
                break;
            case STAGE3_RATING:
                if (proj == null) return;
                statusLabel.setText("Оценка сотрудничества с " + proj.getCarrier().getName());
                baseCriterionsTM.setData(proj.getStage(Project.Stages.STAGE3_RATING).getBaseCriterions());
                otherCriterionsTM.setData(proj.getStage(Project.Stages.STAGE3_RATING).getOtherCriterions());
                baseDataTM.setData(proj.getStage(Project.Stages.STAGE3_RATING).getBaseDataFileds());
                otherDataTM.setData(proj.getStage(Project.Stages.STAGE3_RATING).getOtherDataFileds());
                closeButton.setEnabled(true);
                prevButton.setEnabled(true);
                nextButton.setEnabled(false);
                baseCriterionButton.setEnabled(true);
                otherCriterionButton.setEnabled(true);
                baseDataButton.setEnabled(true);
                otherDataButton.setEnabled(true);
                break;
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        jToolBar1 = new javax.swing.JToolBar();
        createButton = new javax.swing.JButton();
        openButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        optionsButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        prevButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        baseDataScrollPane = new javax.swing.JScrollPane(baseDataTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        baseDataTable = new javax.swing.JTable();
        otherDataScrollPane = new javax.swing.JScrollPane(otherDataTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        otherDataTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        baseDataButton = new javax.swing.JButton();
        otherDataButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        tableScrollPane = new javax.swing.JScrollPane(baseDataTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        baseCriteriorsTable = new javax.swing.JTable();
        otherCriteriorsScrollPane = new javax.swing.JScrollPane(otherCriteriorsTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        otherCriteriorsTable = new javax.swing.JTable();
        baseCriterionButton = new javax.swing.JButton();
        otherCriterionButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        statusPanel = new javax.swing.JPanel();
        statusLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Выбор v0.1");
        setResizable(false);

        jToolBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setName("Проект"); // NOI18N

        createButton.setText("Создать");
        createButton.setToolTipText("Создать проект");
        createButton.setEnabled(false);
        createButton.setFocusable(false);
        createButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        createButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(createButton);

        openButton.setText("Открыть");
        openButton.setToolTipText("Открыть проект");
        openButton.setFocusable(false);
        openButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(openButton);

        closeButton.setText("Закрыть");
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
        jToolBar1.add(closeButton);
        jToolBar1.add(jSeparator1);

        optionsButton.setText("Настройки");
        optionsButton.setEnabled(false);
        optionsButton.setFocusable(false);
        optionsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        optionsButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        optionsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionsButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(optionsButton);

        nextButton.setText("К следующему этапу");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        prevButton.setText("К предыдущему этапу");
        prevButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevButtonActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Входные данные", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        baseDataTable.setModel(baseDataTM);
        baseDataTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        baseDataTable.setName("Основные критерии"); // NOI18N
        baseDataTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        baseDataScrollPane.setViewportView(baseDataTable);

        otherDataTable.setModel(otherDataTM);
        otherDataTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        otherDataTable.setName("Основные критерии"); // NOI18N
        otherDataTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        otherDataScrollPane.setViewportView(otherDataTable);

        jLabel2.setText("Дополнительные");

        jLabel1.setText("Основные");

        baseDataButton.setText("+");
        baseDataButton.setEnabled(false);
        baseDataButton.setFocusable(false);
        baseDataButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        baseDataButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        baseDataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baseDataButtonActionPerformed(evt);
            }
        });

        otherDataButton.setText("+");
        otherDataButton.setEnabled(false);
        otherDataButton.setFocusable(false);
        otherDataButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        otherDataButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        otherDataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otherDataButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(190, 190, 190)
                        .addComponent(baseDataButton))
                    .addComponent(baseDataScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(otherDataScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(134, 134, 134)
                        .addComponent(otherDataButton))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(baseDataButton, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)))
                    .addComponent(otherDataButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(baseDataScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                    .addComponent(otherDataScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Критерии", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        baseCriteriorsTable.setModel(baseCriterionsTM);
        baseCriteriorsTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        baseCriteriorsTable.setAutoscrolls(false);
        baseCriteriorsTable.setName("Основные критерии"); // NOI18N
        baseCriteriorsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableScrollPane.setViewportView(baseCriteriorsTable);

        otherCriteriorsTable.setModel(otherCriterionsTM);
        otherCriteriorsTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        otherCriteriorsTable.setName("Основные критерии"); // NOI18N
        otherCriteriorsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        otherCriteriorsScrollPane.setViewportView(otherCriteriorsTable);

        baseCriterionButton.setText("+");
        baseCriterionButton.setEnabled(false);
        baseCriterionButton.setFocusable(false);
        baseCriterionButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        baseCriterionButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        baseCriterionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baseCriterionButtonActionPerformed(evt);
            }
        });

        otherCriterionButton.setText("+");
        otherCriterionButton.setEnabled(false);
        otherCriterionButton.setFocusable(false);
        otherCriterionButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        otherCriterionButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        otherCriterionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otherCriterionButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Основные");

        jLabel4.setText("Дополнительные");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(baseCriterionButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(otherCriteriorsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(128, 128, 128)
                        .addComponent(otherCriterionButton))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(baseCriterionButton)
                    .addComponent(otherCriterionButton)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(otherCriteriorsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        statusPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        statusLabel.setText("Статусная трока");

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(statusPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(prevButton)
                .addGap(27, 27, 27)
                .addComponent(nextButton)
                .addGap(340, 340, 340))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nextButton)
                    .addComponent(prevButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Таблицы MS Office (*.xls)", "xls");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            projectFileName = chooser.getSelectedFile().getAbsolutePath();
            curProject = XLSParser.readXLSProject(projectFileName);
            curStage = Stages.STAGE2_COOPERATION;
            initFields(curStage, curProject);
        }
    }//GEN-LAST:event_openButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        curStage = Stages.STAGE3_RATING;
        initFields(curStage, curProject);
    }//GEN-LAST:event_nextButtonActionPerformed

    private void prevButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevButtonActionPerformed
        curStage = Stages.STAGE2_COOPERATION;
        initFields(curStage, curProject);
    }//GEN-LAST:event_prevButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        curStage = Stages.STAGE1_START;
        initFields(curStage, curProject);
    }//GEN-LAST:event_closeButtonActionPerformed

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
//        ProjectFrame projectFrame = new ProjectFrame("Создать проект");
//        if (projectFrame.showDialog()) {
//            curStage = Stages.RATING;
//            curProject = projectFrame.getProject();
//            initFields(curStage, curProject);
//        }
    }//GEN-LAST:event_createButtonActionPerformed

    private void optionsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionsButtonActionPerformed
        OptionsDialog projectFrame = new OptionsDialog();
        if (projectFrame.showDialog()) {
            
        }
    }//GEN-LAST:event_optionsButtonActionPerformed

    private void updateCriterion(JTable table, List<Criterion> criterions, CellReference ref) {
        if (table == null || criterions == null || ref == null) return;
        
        int selectedRow = table.getSelectedRow();
        Criterion crit = (selectedRow == -1) ? null : 
                criterions.get(table.convertRowIndexToModel(selectedRow));
        CriterionDialog critDialog = new CriterionDialog(curStage, curProject, crit);
        if (critDialog.showDialog()) {
            Criterion newCrit = critDialog.getCriterion();
            ActionType action = critDialog.getActionType();
            // сохранение в excel обновленных критериев
            switch(action) {
                case Add:
                    // ишем последний критерий и берем его координаты ячеек
                    Criterion lastCrit = criterions.get(criterions.size() - 1);
                    // переходим на след.строку
                    int nextRow = lastCrit.getNameRow() + 1;
                    newCrit.setNameRef(new CellReference(nextRow, ref.getCol()));
                    newCrit.setValueRef(new CellReference(nextRow, ref.getCol() + 1));
                    
                    XLSParser.saveXLSCriterion(projectFileName, newCrit, curStage);
                    break;
                    
                case Edit:
                    XLSParser.saveXLSCriterion(projectFileName, newCrit, curStage);
                    break;
                    
                case Delete:
                    newCrit.setName("");
                    newCrit.setFormula("");
                    
                    XLSParser.saveXLSCriterion(projectFileName, newCrit, curStage);
                    break;
            }
            // и повторная загрузка из excel
            curProject = XLSParser.readXLSProject(projectFileName);
            
            initFields(curStage, curProject);
        }
    }
    
    private void baseCriterionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baseCriterionButtonActionPerformed
        updateCriterion(baseCriteriorsTable,
                curProject.getStage(curStage).getBaseCriterions(),
                XLSParser.baseCriterionsRef);
    }//GEN-LAST:event_baseCriterionButtonActionPerformed

    
    private void otherCriterionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_otherCriterionButtonActionPerformed
        updateCriterion(otherCriteriorsTable,
                curProject.getStage(curStage).getOtherCriterions(),
                XLSParser.otherCriterionsRef);
    }//GEN-LAST:event_otherCriterionButtonActionPerformed

    private void updateDataField(JTable table, List<DataField> dataFields, CellReference ref) {
        if (table == null || dataFields == null || ref == null) return;
        
        int selectedRow = table.getSelectedRow();
        DataField dataField = (selectedRow == -1) ? null : 
                dataFields.get(table.convertRowIndexToModel(selectedRow));
        DataFieldDialog critDialog = new DataFieldDialog(curStage, dataField);
        if (critDialog.showDialog()) {
            DataField newDataField = critDialog.getDataField();
            ActionType action = critDialog.getActionType();
            
            // сохранение в excel обновленных входных данных
            switch(action) {
                case Add:
                    // ишем последний критерий и берем его координаты ячеек
                    DataField lastDataField = dataFields.get(dataFields.size() - 1);
                    // переходим на след.строку
                    int nextRow = lastDataField.getNameRow() + 1;
                    newDataField.setNameRef(new CellReference(nextRow, ref.getCol()));
                    newDataField.setValueRef(new CellReference(nextRow, ref.getCol() + 1));
                    
                    XLSParser.saveXLSDataField(projectFileName, newDataField, curStage);
                    break;
                    
                case Edit:
                    XLSParser.saveXLSDataField(projectFileName, newDataField, curStage);
                    break;
                    
                case Delete:
                    newDataField.setName("");
                    newDataField.setValue("");
                    
                    XLSParser.saveXLSDataField(projectFileName, newDataField, curStage);
                    break;
            }
            // и повторная загрузка из excel
            curProject = XLSParser.readXLSProject(projectFileName);
            initFields(curStage, curProject);
        }
    }
    
    private void baseDataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baseDataButtonActionPerformed
        updateDataField(baseDataTable,
                curProject.getStage(curStage).getBaseDataFileds(),
                XLSParser.baseDataFieldsRef);
    }//GEN-LAST:event_baseDataButtonActionPerformed

    private void otherDataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_otherDataButtonActionPerformed
        updateDataField(otherDataTable,
                curProject.getStage(curStage).getOtherDataFileds(),
                XLSParser.otherDataFieldsRef);
    }//GEN-LAST:event_otherDataButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton baseCriterionButton;
    private javax.swing.JTable baseCriteriorsTable;
    private javax.swing.JButton baseDataButton;
    private javax.swing.JScrollPane baseDataScrollPane;
    private javax.swing.JTable baseDataTable;
    private javax.swing.JButton closeButton;
    private javax.swing.JButton createButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton openButton;
    private javax.swing.JButton optionsButton;
    private javax.swing.JButton otherCriterionButton;
    private javax.swing.JScrollPane otherCriteriorsScrollPane;
    private javax.swing.JTable otherCriteriorsTable;
    private javax.swing.JButton otherDataButton;
    private javax.swing.JScrollPane otherDataScrollPane;
    private javax.swing.JTable otherDataTable;
    private javax.swing.JButton prevButton;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JScrollPane tableScrollPane;
    // End of variables declaration//GEN-END:variables
}
