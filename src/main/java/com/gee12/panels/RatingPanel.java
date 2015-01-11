package com.gee12.panels;

import com.gee12.other.BubbleBorder;
import com.gee12.tableModels.CriterionsTableModel;
import com.gee12.other.SwitchStageListener;
import com.gee12.other.XLSParser;
import com.gee12.panels.CriterionDialog.ActionType;
import com.gee12.structures.Carrier;
import com.gee12.structures.Criterion;
import com.gee12.structures.Criterion.Types;
import com.gee12.structures.Project;
import com.gee12.structures.Project.Stages;
import java.awt.Color;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.apache.poi.ss.util.CellReference;

/**
 *
 * @author Иван
 */
public class RatingPanel extends JPanel {
    
    public static final int NAME_CELL_WIDTH = 570;
    public static final int VALUE_CELL_WIDTH = 150;
    public static final int TYPE_CELL_WIDTH = 80;
    
    SwitchStageListener listener;
    private final CriterionsTableModel critCooperateTM;
    private final CriterionsTableModel critRatingTM;
    private String projectFileName = null;            
    private Project curProject = null;
    private Carrier curCarrier = null;
    
    public RatingPanel(SwitchStageListener listener) {
        this.listener = listener;
        
        critCooperateTM = new CriterionsTableModel();
        critRatingTM = new CriterionsTableModel();
        
        initComponents();
                
        critCooperateTable.getColumnModel().getColumn(0).setPreferredWidth(NAME_CELL_WIDTH);
        critCooperateTable.getColumnModel().getColumn(1).setPreferredWidth(VALUE_CELL_WIDTH);
        critCooperateTable.getColumnModel().getColumn(2).setPreferredWidth(TYPE_CELL_WIDTH);
        critRatingTable.getColumnModel().getColumn(0).setPreferredWidth(NAME_CELL_WIDTH);
        critRatingTable.getColumnModel().getColumn(1).setPreferredWidth(VALUE_CELL_WIDTH);
        critRatingTable.getColumnModel().getColumn(2).setPreferredWidth(TYPE_CELL_WIDTH);
    }
    
    public void init(Project proj, Carrier curCar, String fileName) {
        if (proj == null || curCar == null) return;
        this.curProject = proj;
        this.curCarrier = curCar;
        this.projectFileName = fileName;
        
        if (curCar.getName().equalsIgnoreCase(curProject.getCurrentCarrier().getName())) {
            initControls(curProject);
        }
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
    
    public static Comparator<Criterion> critComparator = new Comparator<Criterion>() {
	@Override
	public int compare(Criterion crit1, Criterion crit2) {
            final Types type1 = crit1.getType();
            final Types type2 = crit2.getType();
	    return (type1 == Types.BASE && type2 == Types.OTHER) ? -1 :
		    (type2 == Criterion.Types.BASE && type1 == Types.OTHER) ? 1 : 0;
	}
    };
    //
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        matrixPanel1 = new com.gee12.panels.MatrixPanel();
        jLabel1 = new javax.swing.JLabel();
        saveReportButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        baseDataScrollPane = new javax.swing.JScrollPane(critCooperateTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        critCooperateTable = new javax.swing.JTable();
        critCooperateButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        baseDataScrollPane1 = new javax.swing.JScrollPane(critCooperateTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        critRatingTable = new javax.swing.JTable();
        critRatingButton = new javax.swing.JButton();
        prevButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        genMarkTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        maxMarkTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        deviationTextField = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(840, 600));

        jLabel1.setBackground(java.awt.Color.orange);
        jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getStyle() | java.awt.Font.BOLD, jLabel1.getFont().getSize()+4));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ОЦЕНКА СОТРУДНИЧЕСТВА С ПЕРЕВОЗЧИКОМ");
        jLabel1.setBorder(new BubbleBorder(Color.ORANGE,1,8,16));
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setOpaque(true);

        saveReportButton.setBackground(new java.awt.Color(170, 92, 0));
        saveReportButton.setText("Сохранить отчет");
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
        baseDataScrollPane.setViewportView(critCooperateTable);

        critCooperateButton.setText("+");
        critCooperateButton.setFocusable(false);
        critCooperateButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        critCooperateButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        critCooperateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                critCooperateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(baseDataScrollPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(critCooperateButton)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(critCooperateButton)
                .addGap(0, 149, Short.MAX_VALUE))
            .addComponent(baseDataScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Критерии после этапа сотрудничества"));

        critRatingTable.setModel(critRatingTM);
        critRatingTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        critRatingTable.setName("Основные критерии"); // NOI18N
        critRatingTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        baseDataScrollPane1.setViewportView(critRatingTable);

        critRatingButton.setText("+");
        critRatingButton.setFocusable(false);
        critRatingButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        critRatingButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        critRatingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                critRatingButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(baseDataScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 766, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(critRatingButton)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(critRatingButton)
                .addContainerGap(136, Short.MAX_VALUE))
            .addComponent(baseDataScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        prevButton.setText("Назад");
        prevButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Общая оценка");

        jLabel3.setText("Эталонное значение");

        jLabel4.setText("Отклонение, %");

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
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(genMarkTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(maxMarkTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(deviationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prevButton)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void updateCriterion(JTable table, List<Criterion> criterions, Stages stage) {
        if (table == null || criterions == null) return;
        
        int selectedRow = table.getSelectedRow();
        Criterion crit = (selectedRow == -1) ? null : 
                criterions.get(table.convertRowIndexToModel(selectedRow));
        CriterionDialog critDialog = new CriterionDialog(curProject, crit, stage);
        
        if (critDialog.showDialog()) {
            Criterion newCrit = critDialog.getCriterion();
            ActionType action = critDialog.getActionType();
            // сохранение в excel обновленных критериев
            switch(action) {
                case Add:
                    // ишем последний критерий и берем его координаты ячеек
                    //Criterion lastCrit = criterions.get(criterions.size() - 1);
                    CellReference lastRef = Criterion.lastCriterionNameRef(criterions, newCrit.getType());
                     // переходим на след.строку
                    int nextRow = lastRef.getRow() + 1;
                    CellReference ref = (newCrit.getType() == Criterion.Types.BASE) ?
                            XLSParser.baseCriterionsRef: XLSParser.otherCriterionsRef;
                    newCrit.setNameRef(new CellReference(nextRow, ref.getCol()));
                    newCrit.setValueRef(new CellReference(nextRow, ref.getCol() + 1));
                    
                    XLSParser.saveXLSCriterion(projectFileName, newCrit, stage);
                    break;
                    
                case Edit:
                    XLSParser.saveXLSCriterion(projectFileName, newCrit, stage);
                    break;
                    
                case Delete:
                    newCrit.setName("");
                    newCrit.setFormula("");
                    
                    XLSParser.saveXLSCriterion(projectFileName, newCrit, stage);
                    break;
            }
            // и повторная загрузка из excel
//            curProject.getStage(stage).setCriterions(
//                    XLSParser.readXLSCriterions(projectFileName, stage));
//            initTable(curProject.getStage(stage).getCriterions(), 
//                    (stage == Stages.STAGE2_COOPERATION) ? critCooperateTM : critRatingTM);
            curProject = XLSParser.readXLSProject(projectFileName);
            initControls(curProject);
        }
    }    
    
    private void critRatingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_critRatingButtonActionPerformed
        updateCriterion(critRatingTable,
            curProject.getStage(Stages.STAGE3_RATING).getCriterions(),
            Stages.STAGE3_RATING);
    }//GEN-LAST:event_critRatingButtonActionPerformed

    private void critCooperateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_critCooperateButtonActionPerformed
        updateCriterion(critCooperateTable,
            curProject.getStage(Stages.STAGE2_COOPERATION).getCriterions(),
            Stages.STAGE2_COOPERATION);
    }//GEN-LAST:event_critCooperateButtonActionPerformed

    private void saveReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveReportButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveReportButtonActionPerformed

    private void prevButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevButtonActionPerformed
        listener.nextStage(Project.Stages.STAGE2_COOPERATION);
    }//GEN-LAST:event_prevButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane baseDataScrollPane;
    private javax.swing.JScrollPane baseDataScrollPane1;
    private javax.swing.JButton critCooperateButton;
    private javax.swing.JTable critCooperateTable;
    private javax.swing.JButton critRatingButton;
    private javax.swing.JTable critRatingTable;
    private javax.swing.JTextField deviationTextField;
    private javax.swing.JTextField genMarkTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private com.gee12.panels.MatrixPanel matrixPanel1;
    private javax.swing.JTextField maxMarkTextField;
    private javax.swing.JButton prevButton;
    private javax.swing.JButton saveReportButton;
    // End of variables declaration//GEN-END:variables
}
