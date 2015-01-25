package com.gee12.panels;

import com.gee12.other.BubbleBorder;
import com.gee12.other.ButtonEditor;
import com.gee12.other.ButtonRenderer;
import com.gee12.other.RowListener;
import com.gee12.tablemodels.CriterionsTableModel;
import com.gee12.other.SwitchStageListener;
import com.gee12.other.XLSParser;
import com.gee12.structures.Carrier;
import com.gee12.structures.Criterion;
import com.gee12.structures.Project;
import com.gee12.structures.Project.Stages;
import java.awt.Color;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumnModel;
import org.apache.poi.ss.util.CellReference;

/**
 *
 * @author Иван
 */
public class RatingPanel extends JPanel implements RowListener {
    
    public static final int NAME_CELL_WIDTH = 545;
    public static final int VALUE_CELL_WIDTH = 70;
    public static final int MAX_CELL_WIDTH = 70;
    public static final int TYPE_CELL_WIDTH = 60;
    public static final int FORMULA_CELL_WIDTH = 30;
    public static final int DELETE_CELL_WIDTH = 30;
    
    
    SwitchStageListener listener;
    private final CriterionsTableModel critCooperateTM;
    private final CriterionsTableModel critRatingTM;
//    private Project curProject = null;
    private Carrier selectedCarrier;
    
    ////////////////////////////////////////////////////////////////////////////
    public RatingPanel(SwitchStageListener listener) {
        this.listener = listener;
        
        critCooperateTM = new CriterionsTableModel();
        critRatingTM = new CriterionsTableModel();
        
        initComponents();
                
        initDataTable(critCooperateTable);
        initDataTable(critRatingTable);
        
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public void init(Carrier selectedCarrier, String fileName) {
//        if (proj == null) return;
//        this.curProject = proj;
        this.selectedCarrier = selectedCarrier;
        
        nameLabel.setText("ОЦЕНКА СОТРУДНИЧЕСТВА С ПЕРЕВОЗЧИКОМ '" + selectedCarrier.getName() + "'");
        
        // create temp file
        XLSParser.createXLSFile(MainFrame.TEMP_FILE_NAME);
                
        // define marks based on criterions
        selectedCarrier.defineMarks();

        // save project in temp file
        XLSParser.saveXLSCarrierProject(MainFrame.TEMP_FILE_NAME, null, selectedCarrier);

        // init controls
        initControls(selectedCarrier);
    }
        
    ////////////////////////////////////////////////////////////////////////////
    private void initDataTable(JTable table) {
        if (table == null) return;
        // Create the combo box editor
        JComboBox comboBox = new JComboBox(CriterionsTableModel.COMBO_STATES);
        comboBox.setEditable(false);   
        // Assign the editor
        TableColumnModel tcm = table.getColumnModel();
        // type
        tcm.getColumn(3).setCellEditor(
                new DefaultCellEditor(comboBox));
        // formula
        tcm.getColumn(4).setCellEditor(
            new ButtonEditor(new JCheckBox(), this, 4, 5));
        tcm.getColumn(4).setCellRenderer(new ButtonRenderer(
            new ImageIcon(MainFrame.class.getResource("/images/edit.jpg"))));
        // delete
        tcm.getColumn(5).setCellEditor(
            new ButtonEditor(new JCheckBox(), this, 4, 5));
        tcm.getColumn(5).setCellRenderer(new ButtonRenderer(
            new ImageIcon(MainFrame.class.getResource("/images/delete.jpg"))));
        
        table.getColumnModel().getColumn(0).setPreferredWidth(NAME_CELL_WIDTH);
        table.getColumnModel().getColumn(1).setPreferredWidth(VALUE_CELL_WIDTH);
        table.getColumnModel().getColumn(2).setPreferredWidth(MAX_CELL_WIDTH);
        table.getColumnModel().getColumn(3).setPreferredWidth(TYPE_CELL_WIDTH);
        table.getColumnModel().getColumn(4).setPreferredWidth(FORMULA_CELL_WIDTH);
        table.getColumnModel().getColumn(5).setPreferredWidth(DELETE_CELL_WIDTH);
        
//        // for row selection
//        ListSelectionModel rowSM = table.getSelectionModel();
//        rowSM.addListSelectionListener(new ListSelectionListener() {
//            public void valueChanged(ListSelectionEvent e) {
//                // ignore extra messages.
//                if (e.getValueIsAdjusting()) {
//                    return;
//                }
//                ListSelectionModel lsm = (ListSelectionModel) e.getSource();
//                if (!lsm.isSelectionEmpty()) {
//                    int index = lsm.getMinSelectionIndex();
//                    
//                    selectedCarrier.defineMarks();
//                    initMarksTextFields(selectedCarrier);
//                }
//            }
//        });
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public void initControls(Carrier car) {
        if (car == null) return;
        
        initTable(car.getStage(Project.Stages.STAGE2_COOPERATION).getCriterions(), critCooperateTM);
        initTable(car.getStage(Project.Stages.STAGE3_RATING).getCriterions(), critRatingTM);
        initMarksTextFields(car);
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public void initTable(List <Criterion> dataFields, CriterionsTableModel tableModel) {
        // for rows sort
        dataFields.sort(Criterion.COMPARATOR);
        tableModel.setData(dataFields);
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public void initMarksTextFields(Carrier car) {
        if (car == null) return;
        
        genMarkTextField.setText(String.format(Locale.US, "%.2f", car.getGeneralMark()));
        maxMarkTextField.setText(String.format(Locale.US, "%.2f", car.getMaxMark()));
        deviationTextField.setText(String.format(Locale.US, "%.2f", car.getDeviation()));
    }
    
    ////////////////////////////////////////////////////////////////////////////
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameLabel = new javax.swing.JLabel();
        saveReportButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        critCooperateScrollPane = new javax.swing.JScrollPane(critCooperateTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        critCooperateTable = new javax.swing.JTable();
        addCritCooperateButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        critRatingScrollPane = new javax.swing.JScrollPane(critCooperateTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        critRatingTable = new javax.swing.JTable();
        addCritRatingButton = new javax.swing.JButton();
        prevButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        genMarkTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        maxMarkTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        deviationTextField = new javax.swing.JTextField();
        saveRatingButton1 = new javax.swing.JButton();

        setEnabled(false);
        setPreferredSize(new java.awt.Dimension(900, 600));

        nameLabel.setBackground(java.awt.Color.orange);
        nameLabel.setFont(nameLabel.getFont().deriveFont(nameLabel.getFont().getStyle() | java.awt.Font.BOLD, nameLabel.getFont().getSize()+4));
        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameLabel.setText("ОЦЕНКА СОТРУДНИЧЕСТВА С ПЕРЕВОЗЧИКОМ");
        nameLabel.setBorder(new BubbleBorder(Color.ORANGE,1,8,0));
        nameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nameLabel.setOpaque(true);

        saveReportButton.setBackground(new java.awt.Color(170, 92, 0));
        saveReportButton.setEnabled(false);
        saveReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveReportButtonActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Критерии этапа сотрудничества"));

        critCooperateTable.setModel(critCooperateTM);
        critCooperateTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        critCooperateTable.setName("Основные критерии"); // NOI18N
        critCooperateTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        critCooperateTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                critTableKeyReleased(evt);
            }
        });
        critCooperateScrollPane.setViewportView(critCooperateTable);

        addCritCooperateButton.setText("+");
        addCritCooperateButton.setFocusable(false);
        addCritCooperateButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addCritCooperateButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addCritCooperateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCritCooperateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(critCooperateScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addCritCooperateButton))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(addCritCooperateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 152, Short.MAX_VALUE))
            .addComponent(critCooperateScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Критерии после этапа сотрудничества"));

        critRatingTable.setModel(critRatingTM);
        critRatingTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        critRatingTable.setName("Основные критерии"); // NOI18N
        critRatingTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        critRatingTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                critTableKeyReleased(evt);
            }
        });
        critRatingScrollPane.setViewportView(critRatingTable);

        addCritRatingButton.setText("+");
        addCritRatingButton.setFocusable(false);
        addCritRatingButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addCritRatingButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addCritRatingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCritRatingButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(critRatingScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addCritRatingButton))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(addCritRatingButton)
                .addContainerGap(142, Short.MAX_VALUE))
            .addComponent(critRatingScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        prevButton.setText("К этапу сотрудничества");
        prevButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Общая оценка");

        genMarkTextField.setEditable(false);

        jLabel3.setText("Эталонное значение");

        maxMarkTextField.setEditable(false);

        jLabel4.setText("Отклонение, %");

        deviationTextField.setEditable(false);

        saveRatingButton1.setIcon(UIManager.getIcon("FileView.floppyDriveIcon"));
        saveRatingButton1.setFocusable(false);
        saveRatingButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveRatingButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(prevButton, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(saveReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(genMarkTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(maxMarkTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deviationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(saveRatingButton1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(genMarkTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(maxMarkTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(deviationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(saveReportButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(prevButton)
                        .addContainerGap())))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 284, Short.MAX_VALUE)
                    .addComponent(saveRatingButton1)
                    .addGap(0, 284, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    ////////////////////////////////////////////////////////////////////////////
    private Criterion getNewCriterion(Stages stage) {
        Criterion res = new Criterion();
        // ишем последний Criterion и берем его координаты ячеек
        CellReference lastRef = Criterion.lastCriterionNameRef(
                selectedCarrier.getStage(stage).getCriterions(), 
                res.getType());
        // переходим на след.строку
//        int nextRow = lastRef.getRow() + 1;
//        CellReference ref = (res.getType() == Criterion.Types.BASE)
//                ? XLSParser.baseDataFieldsRef : XLSParser.otherDataFieldsRef;
        res.setNameRef(new CellReference(lastRef.getRow() + 1, lastRef.getCol()));
        
        return res;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    private void addCritRatingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCritRatingButtonActionPerformed
        Criterion newCrit = getNewCriterion(Stages.STAGE3_RATING);
        critRatingTM.addRow(newCrit);
    }//GEN-LAST:event_addCritRatingButtonActionPerformed

    ////////////////////////////////////////////////////////////////////////////
    private void addCritCooperateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCritCooperateButtonActionPerformed
        Criterion newCrit = getNewCriterion(Stages.STAGE2_COOPERATION);
        critCooperateTM.addRow(newCrit);
    }//GEN-LAST:event_addCritCooperateButtonActionPerformed

    ////////////////////////////////////////////////////////////////////////////
    @Override
    public Object editRow(JTable table, int row) {
        if (table == null) return null;
        
        // get selected criterion
        Stages stage = (table.equals(critCooperateTable)) ? Stages.STAGE2_COOPERATION :
                (table.equals(critRatingTable)) ? Stages.STAGE3_RATING : Stages.STAGE0_START;
        Criterion crit = (row == -1) ? null : 
                ((CriterionsTableModel)table.getModel()).getData().get(table.convertRowIndexToModel(row));
        // create dialog
        CriterionDialog critDialog = new CriterionDialog(selectedCarrier, crit, stage);
        if (critDialog.showDialog()) {
            // get updated criterion
            Criterion updatedCrit = critDialog.getCriterion();
            ((CriterionsTableModel)table.getModel()).setValueAt(updatedCrit.getValue(), row, 1);
            ((CriterionsTableModel)table.getModel()).setValueAt(updatedCrit.getFormula(), row, 4);
            // update criterion in project
//            curProject.getStage(stage).getCriterions().get(row)
            // update marks
            selectedCarrier.defineMarks();
            initMarksTextFields(selectedCarrier);
            return updatedCrit.getFormula();
        }
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////
    @Override
    public void deleteRow(JTable table, int row) {
         if (table == null) return;
        int res = JOptionPane.showConfirmDialog(null, "Удалить строку?", "Удаление",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (res == JOptionPane.YES_OPTION) {
            ((CriterionsTableModel)table.getModel()).deleteRow(row);
            // update marks
            selectedCarrier.defineMarks();
            initMarksTextFields(selectedCarrier);
        } 
    }
    
    ////////////////////////////////////////////////////////////////////////////
    private void saveReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveReportButtonActionPerformed

    }//GEN-LAST:event_saveReportButtonActionPerformed

    ////////////////////////////////////////////////////////////////////////////
    private void prevButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevButtonActionPerformed
        listener.nextStage(Project.Stages.STAGE2_COOPERATION);
    }//GEN-LAST:event_prevButtonActionPerformed

    private void critTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_critTableKeyReleased
        selectedCarrier.defineMarks();
        initMarksTextFields(selectedCarrier);
    }//GEN-LAST:event_critTableKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCritCooperateButton;
    private javax.swing.JButton addCritRatingButton;
    private javax.swing.JScrollPane critCooperateScrollPane;
    private javax.swing.JTable critCooperateTable;
    private javax.swing.JScrollPane critRatingScrollPane;
    private javax.swing.JTable critRatingTable;
    private javax.swing.JTextField deviationTextField;
    private javax.swing.JTextField genMarkTextField;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField maxMarkTextField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JButton prevButton;
    private javax.swing.JButton saveRatingButton1;
    private javax.swing.JButton saveReportButton;
    // End of variables declaration//GEN-END:variables

}
