package com.gee12.panels;

import com.gee12.other.BubbleBorder;
import com.gee12.other.ButtonEditor;
import com.gee12.other.ButtonRenderer;
import com.gee12.other.RowListener;
import com.gee12.tableModels.CriterionsTableModel;
import com.gee12.other.SwitchStageListener;
import com.gee12.other.XLSParser;
import com.gee12.structures.Carrier;
import com.gee12.structures.Criterion;
import com.gee12.structures.Criterion.Types;
import com.gee12.structures.Project;
import com.gee12.structures.Project.Stages;
import com.gee12.tableModels.DataTableModel;
import java.awt.Color;
import java.util.Comparator;
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
import javax.swing.UIManager;
import javax.swing.table.TableColumnModel;
import org.apache.poi.ss.util.CellReference;

/**
 *
 * @author Иван
 */
public class RatingPanel extends JPanel implements RowListener {
    
    public static final int NAME_CELL_WIDTH = 545;
    public static final int VALUE_CELL_WIDTH = 80;
    public static final int FORMULA_CELL_WIDTH = 60;
    public static final int TYPE_CELL_WIDTH = 60;
    public static final int DELETE_CELL_WIDTH = 60;
    
    
    SwitchStageListener listener;
    private final CriterionsTableModel critCooperateTM;
    private final CriterionsTableModel critRatingTM;
    private String projectFileName = null;            
    private Project curProject = null;
//    private Carrier curCarrier = null;
    
    public RatingPanel(SwitchStageListener listener) {
        this.listener = listener;
        
        critCooperateTM = new CriterionsTableModel();
        critRatingTM = new CriterionsTableModel();
        
        initComponents();
                
        initDataTable(critCooperateTable);
        initDataTable(critRatingTable);
    }
    
    public void init(Project proj, Carrier curCar, String fileName) {
        if (proj == null || curCar == null) return;
        this.curProject = proj;
//        this.curCarrier = curCar;
        this.projectFileName = fileName;
        
        nameLabel.setText("ОЦЕНКА СОТРУДНИЧЕСТВА С ПЕРЕВОЗЧИКОМ '" + curCar.getName() + "'");
                
        if (curCar.getName().equalsIgnoreCase(curProject.getCurrentCarrier().getName())) {
            initControls(curProject);
        }
    }
        
    private void initDataTable(JTable table) {
        if (table == null) return;
        // Create the combo box editor
        JComboBox comboBox = new JComboBox(CriterionsTableModel.COMBO_STATES);
        comboBox.setEditable(false);   
        // Assign the editor
        TableColumnModel tcm = table.getColumnModel();
        // type
        tcm.getColumn(2).setCellEditor(
                new DefaultCellEditor(comboBox));
        // formula
        tcm.getColumn(3).setCellEditor(
            new ButtonEditor(new JCheckBox(), this, 3, 4));
        tcm.getColumn(3).setCellRenderer(new ButtonRenderer(
            new ImageIcon(MainFrame.class.getResource("/images/edit.jpg"))));
        // delete
        tcm.getColumn(4).setCellEditor(
            new ButtonEditor(new JCheckBox(), this, 3, 4));
        tcm.getColumn(4).setCellRenderer(new ButtonRenderer(
            new ImageIcon(MainFrame.class.getResource("/images/delete.jpg"))));
        
        table.getColumnModel().getColumn(0).setPreferredWidth(NAME_CELL_WIDTH);
        table.getColumnModel().getColumn(1).setPreferredWidth(VALUE_CELL_WIDTH);
        table.getColumnModel().getColumn(2).setPreferredWidth(FORMULA_CELL_WIDTH);
        table.getColumnModel().getColumn(3).setPreferredWidth(TYPE_CELL_WIDTH);
        table.getColumnModel().getColumn(4).setPreferredWidth(DELETE_CELL_WIDTH);
    }
    
    public void initControls(Project proj) {
        initTable(proj.getStage(Project.Stages.STAGE2_COOPERATION).getCriterions(), critCooperateTM);
        initTable(proj.getStage(Project.Stages.STAGE3_RATING).getCriterions(), critRatingTM);
        
        genMarkTextField.setText(String.format(Locale.US, "%.2f", proj.getGeneralMark()));
        maxMarkTextField.setText(String.format(Locale.US, "%.2f", proj.getMaxMark()));
        deviationTextField.setText(String.format(Locale.US, "%.2f", proj.getDeviation()));
    }
    
    public void initTable(List <Criterion> dataFields, CriterionsTableModel tm) {
        // for rows sort
        dataFields.sort(critComparator);
        tm.setData(dataFields);
    }

    //
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameLabel = new javax.swing.JLabel();
        saveReportButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        critCooperateScrollPane = new javax.swing.JScrollPane(critCooperateTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        critCooperateTable = new javax.swing.JTable();
        addCritCooperateButton = new javax.swing.JButton();
        saveCooperationButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        critRatingScrollPane = new javax.swing.JScrollPane(critCooperateTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        critRatingTable = new javax.swing.JTable();
        addCritRatingButton = new javax.swing.JButton();
        saveRatingButton = new javax.swing.JButton();
        prevButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        genMarkTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        maxMarkTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        deviationTextField = new javax.swing.JTextField();
        saveRatingButton1 = new javax.swing.JButton();

        setEnabled(false);
        setPreferredSize(new java.awt.Dimension(840, 600));

        nameLabel.setBackground(java.awt.Color.orange);
        nameLabel.setFont(nameLabel.getFont().deriveFont(nameLabel.getFont().getStyle() | java.awt.Font.BOLD, nameLabel.getFont().getSize()+4));
        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameLabel.setText("ОЦЕНКА СОТРУДНИЧЕСТВА С ПЕРЕВОЗЧИКОМ");
        nameLabel.setBorder(new BubbleBorder(Color.ORANGE,1,8,0));
        nameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nameLabel.setOpaque(true);

        saveReportButton.setBackground(new java.awt.Color(170, 92, 0));
        saveReportButton.setText("Сохранить отчет");
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

        saveCooperationButton.setIcon(UIManager.getIcon("FileView.floppyDriveIcon"));
        saveCooperationButton.setFocusable(false);
        saveCooperationButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveCooperationButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        saveCooperationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveCooperationButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(critCooperateScrollPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addCritCooperateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(saveCooperationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(addCritCooperateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveCooperationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 124, Short.MAX_VALUE))
            .addComponent(critCooperateScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Критерии после этапа сотрудничества"));

        critRatingTable.setModel(critRatingTM);
        critRatingTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        critRatingTable.setName("Основные критерии"); // NOI18N
        critRatingTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
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

        saveRatingButton.setIcon(UIManager.getIcon("FileView.floppyDriveIcon"));
        saveRatingButton.setFocusable(false);
        saveRatingButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveRatingButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        saveRatingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveRatingButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(critRatingScrollPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addCritRatingButton)
                    .addComponent(saveRatingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(addCritRatingButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveRatingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(126, Short.MAX_VALUE))
            .addComponent(critRatingScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        prevButton.setText("Назад");
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
            .addGroup(layout.createSequentialGroup()
                .addGap(311, 311, 311)
                .addComponent(prevButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(saveReportButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(genMarkTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(maxMarkTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deviationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 159, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prevButton)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(saveRatingButton1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

//    private void updateCriterion(JTable table, List<Criterion> criterions, Stages stage) {
//        if (table == null || criterions == null) return;
//        
//        int selectedRow = table.getSelectedRow();
//        Criterion crit = (selectedRow == -1) ? null : 
//                criterions.get(table.convertRowIndexToModel(selectedRow));
//        CriterionDialog critDialog = new CriterionDialog(curProject, crit, stage);
//        
//        if (critDialog.showDialog()) {
//            Criterion newCrit = critDialog.getCriterion();
//            ActionType action = critDialog.getActionType();
//            // сохранение в excel обновленных критериев
//            switch(action) {
//                case Add:
//                    // ишем последний критерий и берем его координаты ячеек
//                    //Criterion lastCrit = criterions.get(criterions.size() - 1);
//                    CellReference lastRef = Criterion.lastCriterionNameRef(criterions, newCrit.getType());
//                     // переходим на след.строку
//                    int nextRow = lastRef.getRow() + 1;
//                    CellReference ref = (newCrit.getType() == Criterion.Types.BASE) ?
//                            XLSParser.baseCriterionsRef: XLSParser.otherCriterionsRef;
//                    newCrit.setNameRef(new CellReference(nextRow, ref.getCol()));
//                    newCrit.setValueRef(new CellReference(nextRow, ref.getCol() + 1));
//                    
//                    XLSParser.saveXLSCriterion(projectFileName, newCrit, stage);
//                    break;
//                    
//                case Edit:
//                    XLSParser.saveXLSCriterion(projectFileName, newCrit, stage);
//                    break;
//                    
//                case Delete:
//                    newCrit.setName("");
//                    newCrit.setFormula("");
//                    
//                    XLSParser.saveXLSCriterion(projectFileName, newCrit, stage);
//                    break;
//            }
//            // и повторная загрузка из excel
////            curProject.getStage(stage).setCriterions(
////                    XLSParser.readXLSCriterions(projectFileName, stage));
////            initTable(curProject.getStage(stage).getCriterions(), 
////                    (stage == Stages.STAGE2_COOPERATION) ? critCooperateTM : critRatingTM);
//            curProject = XLSParser.readXLSProject(projectFileName);
//            initControls(curProject);
//        }
//    }    
    
    private Criterion getNewCriterion(Stages stage) {
        Criterion res = Criterion.EMPTY;
        // ишем последний Criterion и берем его координаты ячеек
        CellReference lastRef = Criterion.lastCriterionNameRef(
                curProject.getStage(stage).getCriterions(), 
                res.getType());
        // переходим на след.строку
        int nextRow = lastRef.getRow() + 1;
        CellReference ref = (res.getType() == Criterion.Types.BASE)
                ? XLSParser.baseDataFieldsRef : XLSParser.otherDataFieldsRef;
        res.setNameRef(new CellReference(nextRow, ref.getCol()));
        res.setValueRef(new CellReference(nextRow, ref.getCol() + 1));
        
        return res;
    }
    
    private void addCritRatingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCritRatingButtonActionPerformed
//        updateCriterion(critRatingTable,
//            curProject.getStage(Stages.STAGE3_RATING).getCriterions(),
//            Stages.STAGE3_RATING);
        Criterion newCrit = getNewCriterion(Stages.STAGE3_RATING);
        critRatingTM.addRow(newCrit);
    }//GEN-LAST:event_addCritRatingButtonActionPerformed

    private void addCritCooperateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCritCooperateButtonActionPerformed
//        updateCriterion(critCooperateTable,
//            curProject.getStage(Stages.STAGE2_COOPERATION).getCriterions(),
//            Stages.STAGE2_COOPERATION);
        Criterion newCrit = getNewCriterion(Stages.STAGE2_COOPERATION);
        critCooperateTM.addRow(newCrit);
    }//GEN-LAST:event_addCritCooperateButtonActionPerformed

    @Override
    public Object editRow(JTable table, int row) {
        if (table == null) return null;
        
        Stages stage = (table.equals(critCooperateTable)) ? Stages.STAGE2_COOPERATION :
                (table.equals(critRatingTable)) ? Stages.STAGE3_RATING : Stages.STAGE0_START;
//        int selectedRow = table.getSelectedRow();
        Criterion crit = (row == -1) ? null : 
                ((CriterionsTableModel)table.getModel()).getData().get(table.convertRowIndexToModel(row));
        CriterionDialog critDialog = new CriterionDialog(curProject, crit, stage);
        if (critDialog.showDialog()) {
            String formula = critDialog.getFormula();
            ((CriterionsTableModel)table.getModel()).setValueAt(formula, row, 3);
            return formula;
        }
        return null;
    }

    @Override
    public void deleteRow(JTable table, int row) {
         if (table == null) return;
        int res = JOptionPane.showConfirmDialog(null, "Удалить строку?", "Удаление",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (res == JOptionPane.YES_OPTION) {
            ((CriterionsTableModel)table.getModel()).deleteRow(row);
        } 
    }
    
    private void saveReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveReportButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveReportButtonActionPerformed

    private void prevButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevButtonActionPerformed
        listener.nextStage(Project.Stages.STAGE2_COOPERATION);
    }//GEN-LAST:event_prevButtonActionPerformed

    private void saveCooperationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveCooperationButtonActionPerformed
        XLSParser.saveXLSCriterions(projectFileName, Stages.STAGE2_COOPERATION, critCooperateTM.getData());
    }//GEN-LAST:event_saveCooperationButtonActionPerformed

    private void saveRatingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveRatingButtonActionPerformed
        XLSParser.saveXLSCriterions(projectFileName, Stages.STAGE3_RATING, critRatingTM.getData());
    }//GEN-LAST:event_saveRatingButtonActionPerformed

    public static Comparator<Criterion> critComparator = new Comparator<Criterion>() {
	@Override
	public int compare(Criterion crit1, Criterion crit2) {
            final Types type1 = crit1.getType();
            final Types type2 = crit2.getType();
	    return (type1 == Types.BASE && type2 == Types.OTHER) ? -1 :
		    (type2 == Criterion.Types.BASE && type1 == Types.OTHER) ? 1 : 0;
	}
    };

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
    private javax.swing.JButton saveCooperationButton;
    private javax.swing.JButton saveRatingButton;
    private javax.swing.JButton saveRatingButton1;
    private javax.swing.JButton saveReportButton;
    // End of variables declaration//GEN-END:variables

}
