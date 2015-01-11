package com.gee12.panels;

import com.gee12.tableModels.DataTableModel;
import com.gee12.other.SwitchStageListener;
import com.gee12.structures.Project;
import java.awt.Color;
import java.util.Enumeration;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Иван
 */
public class CooperatePanel extends JPanel {

    SwitchStageListener listener;
    private final DataTableModel dataCooperateTM;
    private final DataTableModel dataRatingTM;
    private String projectFileName = null;            
    private Project curProject = null;
            
    public CooperatePanel(SwitchStageListener listener) {
        this.listener = listener;
        
        dataCooperateTM = new DataTableModel();
        dataRatingTM = new DataTableModel();
        
        initComponents();
        
        dataCooperateTable.getColumnModel().getColumn(0).setPreferredWidth((int)(
            dataCooperateTable.getPreferredScrollableViewportSize().width * 0.88));
        dataRatingTable.getColumnModel().getColumn(0).setPreferredWidth((int)(
            dataRatingTable.getPreferredScrollableViewportSize().width * 0.88));
    }

    //
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        matrixPanel1 = new com.gee12.panels.MatrixPanel();
        jLabel1 = new javax.swing.JLabel();
        toRatingButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        baseDataScrollPane = new javax.swing.JScrollPane(dataCooperateTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        dataCooperateTable = new javax.swing.JTable();
        dataCooperateButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        baseDataScrollPane1 = new javax.swing.JScrollPane(dataCooperateTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        dataRatingTable = new javax.swing.JTable();
        dataRatingButton = new javax.swing.JButton();
        prevButton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(840, 600));

        jLabel1.setBackground(java.awt.Color.orange);
        jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getStyle() | java.awt.Font.BOLD, jLabel1.getFont().getSize()+4));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("СОТРУДНИЧЕСТВО С ПЕРЕВОЗЧИКОМ");
        jLabel1.setBorder(new BubbleBorder(Color.ORANGE,1,8,16));
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setOpaque(true);

        toRatingButton.setBackground(new java.awt.Color(170, 92, 0));
        toRatingButton.setText("Сотрудничать");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Данные этапа сотрудничества"));

        dataCooperateTable.setModel(dataCooperateTM);
        dataCooperateTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        dataCooperateTable.setName("Основные критерии"); // NOI18N
        dataCooperateTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        baseDataScrollPane.setViewportView(dataCooperateTable);

        dataCooperateButton.setText("+");
        dataCooperateButton.setEnabled(false);
        dataCooperateButton.setFocusable(false);
        dataCooperateButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        dataCooperateButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        dataCooperateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataCooperateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(baseDataScrollPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dataCooperateButton)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(dataCooperateButton)
                .addGap(0, 185, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(baseDataScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Данные после этапа сотрудничества"));

        dataRatingTable.setModel(dataRatingTM);
        dataRatingTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        dataRatingTable.setName("Основные критерии"); // NOI18N
        dataRatingTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        baseDataScrollPane1.setViewportView(dataRatingTable);

        dataRatingButton.setText("+");
        dataRatingButton.setEnabled(false);
        dataRatingButton.setFocusable(false);
        dataRatingButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        dataRatingButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        dataRatingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataRatingButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(baseDataScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 766, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dataRatingButton)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(baseDataScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataRatingButton))
                .addGap(0, 3, Short.MAX_VALUE))
        );

        prevButton.setText("Назад");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(311, 311, 311)
                .addComponent(prevButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(toRatingButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toRatingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prevButton)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void dataRatingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataRatingButtonActionPerformed
//        updateDataField(dataCooperateTable,
//            curProject.getStage(curStage).getBaseDataFileds(),
//            XLSParser.baseDataFieldsRef);
    }//GEN-LAST:event_dataRatingButtonActionPerformed

    private void dataCooperateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataCooperateButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dataCooperateButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane baseDataScrollPane;
    private javax.swing.JScrollPane baseDataScrollPane1;
    private javax.swing.JButton dataCooperateButton;
    private javax.swing.JTable dataCooperateTable;
    private javax.swing.JButton dataRatingButton;
    private javax.swing.JTable dataRatingTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private com.gee12.panels.MatrixPanel matrixPanel1;
    private javax.swing.JButton prevButton;
    private javax.swing.JButton toRatingButton;
    // End of variables declaration//GEN-END:variables
}
