package com.gee12.panels;

import static com.gee12.panels.CooperatePanel.dataFieldComparator;
import com.gee12.tableModels.DataTableModel;
import com.gee12.structures.Project;
import com.gee12.structures.Criterion;
import com.gee12.structures.DataField;
import com.gee12.structures.Project.Stages;
import com.gee12.tableModels.DataSimpleTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JScrollPane;

/**
 *
 * @author Иван
 */
public class CriterionDialog extends ModalDialog {
    
    public static final int NAME_CELL_WIDTH = 550;
    public static final int VALUE_CELL_WIDTH = 100;
    public static final int TYPE_CELL_WIDTH = 80;
    public static final int REF_CELL_WIDTH = 70;
    
    private final DataSimpleTableModel dataTM;
    private Project curProject = null;
    private Stages curStage = Stages.STAGE1_CHOISE;
//    private String formula = null;
    
    public CriterionDialog(Project proj, Criterion crit, Stages stage) {
        dataTM = new DataSimpleTableModel();
        
        initComponents();
        initDialogComponents();
        
        dataTable.getColumnModel().getColumn(0).setPreferredWidth(NAME_CELL_WIDTH);
        dataTable.getColumnModel().getColumn(1).setPreferredWidth(VALUE_CELL_WIDTH);
        dataTable.getColumnModel().getColumn(2).setPreferredWidth(TYPE_CELL_WIDTH);
        dataTable.getColumnModel().getColumn(3).setPreferredWidth(REF_CELL_WIDTH);
        
        curProject = proj;
        curStage = stage;
        initTable(stage, proj);
        initFields(crit);
    }
    
    public void initFields(Criterion crit) {
        nameLabel.setText(crit.getName());
        formulaTextField.setText(crit.getFormula());
        valueTextField.setText(crit.getValue());
    }
    
    
    public void initTable(Stages stage, Project proj) {
        List <DataField> dataFields = proj.getStage(stage).getDataFields();
        // for rows sort
        dataFields.sort(dataFieldComparator);
        dataTM.setData(dataFields);
    }
    
    public String getFormula() {
        return formulaTextField.getText();
    }
    
    public void pasteStringIntoFormulaTextField(String value) {
        int caretPos = formulaTextField.getCaretPosition();
        String res =  new StringBuffer(formulaTextField.getText()).insert(caretPos, value).toString();
        formulaTextField.setText(res);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        formulaTextField = new javax.swing.JTextField();
        nameLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        valueTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        baseDataScrollPane = new javax.swing.JScrollPane(dataTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        dataTable = new javax.swing.JTable();

        setTitle("Критерий");

        cancelButton.setText("Отмена");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        okButton.setText("Готово");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Параметры"));

        formulaTextField.setAutoscrolls(false);

        nameLabel.setText("Имя");

        jLabel4.setText("Формула");

        jLabel5.setText("Значение");

        valueTextField.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(valueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(formulaTextField)))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(formulaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setText("Для добавления в формулу ссылки (на ячейку данных) кникните по ячейке в таблице");

        dataTable.setModel(dataTM);
        dataTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        dataTable.setName("Основные критерии"); // NOI18N
        dataTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        dataTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dataTableMouseClicked(evt);
            }
        });
        baseDataScrollPane.setViewportView(dataTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 183, Short.MAX_VALUE)
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(baseDataScrollPane))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(baseDataScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(okButton)
                    .addComponent(jLabel6))
                .addGap(11, 11, 11))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        result = false;
        setVisible(false);
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
//        formula = formulaTextField.getText();
//        if (formula.isEmpty())
//            return;
        
        result = true;
        setVisible(false);
        dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    private void dataTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataTableMouseClicked
        int selectedRow = dataTable.getSelectedRow();
        DataField dataField = curProject.getStage(curStage).getDataFields()
                .get(dataTable.convertRowIndexToModel(selectedRow));
        pasteStringIntoFormulaTextField(dataField.getValueCellReference());
    }//GEN-LAST:event_dataTableMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane baseDataScrollPane;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTable dataTable;
    private javax.swing.JTextField formulaTextField;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JButton okButton;
    private javax.swing.JTextField valueTextField;
    // End of variables declaration//GEN-END:variables
}
