/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gee12.panels;

import com.gee12.tableModels.CarriersTableModel;
import com.gee12.groupTableHeader.MultiLineHeaderRenderer;
import com.gee12.other.SwitchStageListener;
import com.gee12.other.XLSParser;
import com.gee12.structures.Carrier;
import com.gee12.structures.Project;
import java.awt.Color;
import java.util.Enumeration;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Иван
 */
public class ChoisePanel extends JPanel {

    SwitchStageListener listener;
    private final CarriersTableModel curriersTM;
    private String projectFileName = null;            
    private Project curProject = null;
           
    public ChoisePanel(SwitchStageListener listener) {
        this.listener = listener;
        curriersTM = new CarriersTableModel();
        
        initComponents();
        
        //
        MultiLineHeaderRenderer renderer = new MultiLineHeaderRenderer();
        TableColumnModel columnModel = carriersTable.getColumnModel();
//        curriersTable.setBorder(new BevelBorder(BevelBorder.RAISED));
        Enumeration e = columnModel.getColumns();
        while (e.hasMoreElements()) {
            ((TableColumn) e.nextElement()).setHeaderRenderer(renderer);
        }
        columnModel.getColumn(1).setPreferredWidth((int)(
            carriersTable.getPreferredScrollableViewportSize().width * 0.80));
    }
    
    public void init(Project proj, String fileName) {
        if (proj == null) return;
        this.curProject = proj;
        this.projectFileName = fileName;
        
        curriersTM.setData(curProject.getCarriers());
    }
    
    public void initFields(Carrier carrier) {
        nameTextField.setText(carrier.getName());
        capacityTextField.setText(String.valueOf(carrier.getCapacity()));
        repeatTextField.setText(String.valueOf(carrier.getRepeatNum()));
        int selectedRow = carriersTable.getSelectedRow();
        ratingTextField.setText(String.valueOf(carriersTable.getModel().getValueAt(selectedRow, 0)));
        matrixPanel.setMatrixTableModel(carrier.getMatrix());
    }

    //
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        matrixPanel1 = new com.gee12.panels.MatrixPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        carriersTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        nameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        capacityTextField = new javax.swing.JTextField();
        repeatTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ratingTextField = new javax.swing.JTextField();
        matrixPanel = new com.gee12.panels.MatrixPanel();
        saveButton = new javax.swing.JButton();
        toCooperateButton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(840, 600));

        jLabel1.setBackground(java.awt.Color.orange);
        jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getStyle() | java.awt.Font.BOLD, jLabel1.getFont().getSize()+4));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ВЫБОР ПЕРЕВОЗЧИКА");
        jLabel1.setBorder(new BubbleBorder(Color.ORANGE,1,8,16));
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setOpaque(true);

        carriersTable.setBorder(null);
        carriersTable.setModel(curriersTM);
        carriersTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                carriersTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(carriersTable);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Подробнее", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        nameTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nameTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nameTextFieldFocusLost(evt);
            }
        });

        jLabel2.setText("Имя");

        jLabel3.setText("Наиболее повторяемая грузоподъемность");

        jLabel4.setText("Число повторений");

        jLabel5.setText("Рейтинг в списке");

        matrixPanel.setBorder(null);
        matrixPanel.setPreferredSize(new java.awt.Dimension(390, 300));

        saveButton.setText("Сохранить изменения");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(nameTextField))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(capacityTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(repeatTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(saveButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(matrixPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ratingTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 3, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(capacityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(repeatTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(ratingTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(matrixPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveButton)
                .addContainerGap())
        );

        toCooperateButton.setBackground(new java.awt.Color(170, 92, 0));
        toCooperateButton.setText("Сотрудничать");
        toCooperateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toCooperateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(toCooperateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                        .addComponent(toCooperateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void carriersTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_carriersTableMouseClicked
        int selectedRow = carriersTable.getSelectedRow();
        if (selectedRow != -1) { 
            Carrier carrier = curProject.getCarriers().get(carriersTable.convertRowIndexToModel(selectedRow));
            initFields(carrier);
        }
    }//GEN-LAST:event_carriersTableMouseClicked

    private void nameTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameTextFieldFocusGained
        if (nameTextField.getText().isEmpty()) {
            nameTextField.setText("Введите имя перевозчика..");
        }
    }//GEN-LAST:event_nameTextFieldFocusGained

    private void nameTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameTextFieldFocusLost
        if (nameTextField.getText().isEmpty()) {
            nameTextField.setText("");
        }
    }//GEN-LAST:event_nameTextFieldFocusLost

    private void toCooperateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toCooperateButtonActionPerformed
        int selectedRow = carriersTable.getSelectedRow();
        if (selectedRow != -1) { 
            curProject.setCurrentCarrier(carriersTable.convertRowIndexToModel(selectedRow));
            listener.nextStage(Project.Stages.STAGE2_COOPERATION);
        }
    }//GEN-LAST:event_toCooperateButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField capacityTextField;
    private javax.swing.JTable carriersTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.gee12.panels.MatrixPanel matrixPanel;
    private com.gee12.panels.MatrixPanel matrixPanel1;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTextField ratingTextField;
    private javax.swing.JTextField repeatTextField;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton toCooperateButton;
    // End of variables declaration//GEN-END:variables
}
