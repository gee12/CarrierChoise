package com.gee12.panels;

import com.gee12.structures.DataField;
import com.gee12.structures.Project;
import com.gee12.panels.CriterionDialog.ActionType;
import com.gee12.structures.Project.Stages;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Иван
 */
public class DataFieldDialog extends MyodalDialog {
    
    private final MyButtonGroup actionTypeGroup;
    private ActionType actionType = ActionType.Add;
    private DataField curDataField = null;
    
    public DataFieldDialog(Stages stage, DataField dataField) {
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
        
        curDataField = dataField;
        String actionCommand = "";
        if (dataField == null) {
            curDataField = DataField.EMPTY;
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
            actionType = CriterionDialog.ActionType.Add;
            initDataFields(DataField.EMPTY);
        } else if (actionCommand.equalsIgnoreCase(editRadioButton.getActionCommand())) {
            actionType = CriterionDialog.ActionType.Edit;
            initDataFields(curDataField);
        } else if (actionCommand.equalsIgnoreCase(deleteRadioButton.getActionCommand())) {
            actionType = CriterionDialog.ActionType.Delete;
            initDataFields(curDataField);
        }
        
    }    
    
    public void initDataFields(DataField data) {
        nameTextField.setText(data.getName());
        valueTextField.setText(data.getValue());
    }
    
    public void initFields(Stages stage, Project proj) {
        if (proj == null) return;
        
        switch (stage) {
            case STAGE2_COOPERATION:
                break;
            case STAGE3_RATING:
                break;
        }
    }
    
    public DataField getDataField() {
        return curDataField;
    }
    
    public ActionType getActionType() {
        return actionType;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        addRadioButton = new javax.swing.JRadioButton();
        editRadioButton = new javax.swing.JRadioButton();
        deleteRadioButton = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        valueTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Параметры"));

        valueTextField.setAutoscrolls(false);

        jLabel4.setText("Имя");

        nameTextField.setAutoscrolls(false);

        jLabel5.setText("Значение");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(valueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(nameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(valueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(okButton)
                    .addComponent(jLabel3))
                .addGap(15, 15, 15))
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
        String formula = valueTextField.getText();
        
        if (name.isEmpty() && formula.isEmpty())
            return;
        
        curDataField.setName(name);
        curDataField.setValue(formula);
        
        result = true;
        setVisible(false);
        dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton addRadioButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JRadioButton deleteRadioButton;
    private javax.swing.JRadioButton editRadioButton;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JButton okButton;
    private javax.swing.JTextField valueTextField;
    // End of variables declaration//GEN-END:variables
}
