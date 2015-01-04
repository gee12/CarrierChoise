package com.mycompany.newchoise;

import com.mycompany.newchoise.Project.Stages;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import org.apache.poi.ss.util.CellReference;

/**
 *
 * @author Иван
 */
public class CriterionDialog extends MyJDialog {
    
    public enum ActionType {
        Add,
        Edit,
        Delete
    }
    
    private final DataTableModel baseDataTM;
    private final DataTableModel otherDataTM;
    private Project curProject = null;
    private Criterion curCriterion = null;
    private final MyButtonGroup actionTypeGroup;
    private Stages curStage = Stages.STAGE1_START;
    private ActionType actionType = ActionType.Add;
    
    public CriterionDialog(Stages stage, Project proj, Criterion crit) {
        baseDataTM = new DataTableModel();
        otherDataTM = new DataTableModel();
        actionTypeGroup = new MyButtonGroup();
	//
	actionTypeGroup.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
                selectActionType(e.getActionCommand());
	    }
	});        
        
        initComponents();
        initDialogComponents();
        baseDataTable.getColumnModel().getColumn(1).setMaxWidth(100);
        otherDataTable.getColumnModel().getColumn(1).setMaxWidth(100);
        
        curProject = proj;
        curStage = stage;
        curCriterion = crit;
        initTables(stage, proj);
        
        String actionCommand = "";
        if (crit == null) {
            curCriterion = Criterion.EMPTY;
            actionCommand = addRadioButton.getActionCommand();
            addRadioButton.setSelected(true);
            editRadioButton.setEnabled(false);
            deleteRadioButton.setEnabled(false);
        } else {
            actionCommand = editRadioButton.getActionCommand();
        }
        selectActionType(actionCommand);
    }
    
    private void selectActionType(String actionCommand) {
        if (actionCommand.equalsIgnoreCase(addRadioButton.getActionCommand())) {
            actionType = ActionType.Add;
            initCriterionFields(Criterion.EMPTY);
        } else if (actionCommand.equalsIgnoreCase(editRadioButton.getActionCommand())) {
            actionType = ActionType.Edit;
            initCriterionFields(curCriterion);
        } else if (actionCommand.equalsIgnoreCase(deleteRadioButton.getActionCommand())) {
            actionType = ActionType.Delete;
            initCriterionFields(curCriterion);
        }
    }
    
    public void initCriterionFields(Criterion crit) {
        nameTextField.setText(crit.getName());
        formulaTextField.setText(crit.getFormula());
        valueTextField.setText(crit.getValue());
    }
    
    
    public void initTables(Stages stage, Project proj) {
        if (proj == null) return;
        
        switch (stage) {
            case STAGE2_COOPERATION:
                baseDataTM.setData(proj.getStage(Stages.STAGE2_COOPERATION).getBaseDataFileds());
                otherDataTM.setData(proj.getStage(Stages.STAGE2_COOPERATION).getOtherDataFileds());
                break;
            case STAGE3_RATING:
                baseDataTM.setData(proj.getStage(Stages.STAGE3_RATING).getBaseDataFileds());
                otherDataTM.setData(proj.getStage(Stages.STAGE3_RATING).getOtherDataFileds());
                break;
        }
    }
    
    public Criterion getCriterion() {
        return curCriterion;
    }
    
    public ActionType getActionType() {
        return actionType;
    }
    
    public void pasteStringIntoFormulaTextField(String value) {
        int caretPos = formulaTextField.getCaretPosition();
        String res =  new StringBuffer(formulaTextField.getText()).insert(caretPos, value).toString();
        formulaTextField.setText(res);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        baseDataTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        otherDataTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        formulaTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        valueTextField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        addRadioButton = new javax.swing.JRadioButton();
        editRadioButton = new javax.swing.JRadioButton();
        deleteRadioButton = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();

        setTitle("Критерий");

        baseDataTable.setModel(baseDataTM);
        baseDataTable.setName("Основные критерии"); // NOI18N
        baseDataTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        baseDataTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                baseDataTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(baseDataTable);

        otherDataTable.setModel(otherDataTM);
        otherDataTable.setName("Основные критерии"); // NOI18N
        otherDataTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        otherDataTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                otherDataTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(otherDataTable);

        jLabel1.setText("Основные");

        jLabel2.setText("Дополнительные");

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

        jLabel3.setText("Имя");

        nameTextField.setAutoscrolls(false);

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
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameTextField)
                    .addComponent(formulaTextField)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(valueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(formulaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Тип действия"));

        actionTypeGroup.add(addRadioButton);
        addRadioButton.setText("Создать новый");
        addRadioButton.setActionCommand("Создать новый");

        actionTypeGroup.add(editRadioButton);
        editRadioButton.setSelected(true);
        editRadioButton.setText("Редактировать");
        editRadioButton.setActionCommand("Редактировать");

        actionTypeGroup.add(deleteRadioButton);
        deleteRadioButton.setText("Удалить");
        deleteRadioButton.setActionCommand("Удалить");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addRadioButton)
                    .addComponent(editRadioButton)
                    .addComponent(deleteRadioButton)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(addRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(editRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deleteRadioButton)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel6.setText("Для добавления в формулу ссылки на ячейку данных кникните по ячейке в таблице");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(186, 186, 186))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cancelButton))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(14, 14, 14))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
        String name = nameTextField.getText();
        String formula = formulaTextField.getText();
        
        if (name.isEmpty() && formula.isEmpty())
            return;
        
        curCriterion.setName(name);
        curCriterion.setFormula(formula);
        
        result = true;
        setVisible(false);
        dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    private void baseDataTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_baseDataTableMouseClicked
        int selectedRow = baseDataTable.getSelectedRow();
        DataField dataField = curProject.getStage(curStage).getBaseDataFileds()
                .get(baseDataTable.convertRowIndexToModel(selectedRow));
        // paste String into cursor position in TextField
        pasteStringIntoFormulaTextField(dataField.getValueCellReference());
    }//GEN-LAST:event_baseDataTableMouseClicked

    private void otherDataTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_otherDataTableMouseClicked
        int selectedRow = otherDataTable.getSelectedRow();
        DataField dataField = curProject.getStage(curStage).getOtherDataFileds()
                .get(otherDataTable.convertRowIndexToModel(selectedRow));
        // paste String into cursor position in TextField
        pasteStringIntoFormulaTextField(dataField.getValueCellReference());
    }//GEN-LAST:event_otherDataTableMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton addRadioButton;
    private javax.swing.JTable baseDataTable;
    private javax.swing.JButton cancelButton;
    private javax.swing.JRadioButton deleteRadioButton;
    private javax.swing.JRadioButton editRadioButton;
    private javax.swing.JTextField formulaTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JButton okButton;
    private javax.swing.JTable otherDataTable;
    private javax.swing.JTextField valueTextField;
    // End of variables declaration//GEN-END:variables
}
