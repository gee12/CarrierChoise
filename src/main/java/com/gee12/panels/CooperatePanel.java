package com.gee12.panels;

import com.gee12.other.BubbleBorder;
import com.gee12.tableModels.DataTableModel;
import com.gee12.other.SwitchStageListener;
import com.gee12.other.XLSParser;
import com.gee12.structures.Carrier;
import com.gee12.structures.DataField;
import com.gee12.structures.Project;
import com.gee12.structures.Project.Stages;
import java.awt.Color;
import java.util.Comparator;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.apache.poi.ss.util.CellReference;

/**
 *
 * @author Иван
 */
public class CooperatePanel extends JPanel {
    
    public static final int NAME_CELL_WIDTH = 570;
    public static final int VALUE_CELL_WIDTH = 150;
    public static final int TYPE_CELL_WIDTH = 80;

    SwitchStageListener listener;
    private final DataTableModel dataCooperateTM;
    private final DataTableModel dataRatingTM;
    private String projectFileName = null;            
    private Project curProject = null;
    private Carrier curCarrier = null;
            
    public CooperatePanel(SwitchStageListener listener) {
        this.listener = listener;
        
        dataCooperateTM = new DataTableModel();
        dataRatingTM = new DataTableModel();
        
        initComponents();
        
        dataCooperateTable.getColumnModel().getColumn(0).setPreferredWidth(NAME_CELL_WIDTH);
        dataCooperateTable.getColumnModel().getColumn(1).setPreferredWidth(VALUE_CELL_WIDTH);
        dataCooperateTable.getColumnModel().getColumn(2).setPreferredWidth(TYPE_CELL_WIDTH);
        dataRatingTable.getColumnModel().getColumn(0).setPreferredWidth(NAME_CELL_WIDTH);
        dataRatingTable.getColumnModel().getColumn(1).setPreferredWidth(VALUE_CELL_WIDTH);
        dataRatingTable.getColumnModel().getColumn(2).setPreferredWidth(TYPE_CELL_WIDTH);
    }
    
    public void init(Project proj, Carrier curCar, String fileName) {
        if (proj == null || curCar == null) return;
        this.curProject = proj;
        this.curCarrier = curCar;
        this.projectFileName = fileName;
        
        if (curCar.getName().equalsIgnoreCase(curProject.getCurrentCarrier().getName())) {
            initTables(curProject);
        }
    }
    
    public void initTables(Project proj) {
        initTable(proj.getStage(Project.Stages.STAGE2_COOPERATION).getDataFields(), dataCooperateTM);
        initTable(proj.getStage(Project.Stages.STAGE3_RATING).getDataFields(), dataRatingTM);
    }
    
    public void initTable(List <DataField> dataFields, DataTableModel tm) {
        // for rows sort
        dataFields.sort(dataFieldComparator);
        tm.setData(dataFields);
    }
    
    public static Comparator<DataField> dataFieldComparator = new Comparator<DataField>() {
	@Override
	public int compare(DataField data1, DataField data2) {
            final DataField.Types type1 = data1.getType();
            final DataField.Types type2 = data2.getType();
	    return (type1 == DataField.Types.BASE && type2 == DataField.Types.OTHER) ? -1 :
		    (type2 == DataField.Types.BASE && type1 == DataField.Types.OTHER) ? 1 : 0;
	}
    };
    
    public Project getProject() {
        return curProject;
    }
    
    //
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        toRatingButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        dataCooperationScrollPane = new javax.swing.JScrollPane(dataCooperateTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        dataCooperateTable = new javax.swing.JTable();
        dataCooperateButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        dataRatingScrollPane = new javax.swing.JScrollPane(dataCooperateTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
        toRatingButton.setText("Оценить");
        toRatingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toRatingButtonActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Данные этапа сотрудничества"));

        dataCooperationScrollPane.setPreferredSize(new java.awt.Dimension(0, 0));

        dataCooperateTable.setModel(dataCooperateTM);
        dataCooperateTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        dataCooperateTable.setName("Основные критерии"); // NOI18N
        dataCooperateTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        dataCooperationScrollPane.setViewportView(dataCooperateTable);

        dataCooperateButton.setText("+");
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
                .addComponent(dataCooperationScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dataCooperateButton))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(dataCooperateButton)
                .addGap(0, 173, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(dataCooperationScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Данные после этапа сотрудничества"));

        dataRatingScrollPane.setPreferredSize(new java.awt.Dimension(0, 0));

        dataRatingTable.setModel(dataRatingTM);
        dataRatingTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        dataRatingTable.setName("Основные критерии"); // NOI18N
        dataRatingTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        dataRatingScrollPane.setViewportView(dataRatingTable);

        dataRatingButton.setText("+");
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
                .addComponent(dataRatingScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dataRatingButton))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(dataRatingButton)
                .addGap(0, 153, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(dataRatingScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        prevButton.setText("Назад");
        prevButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(311, 311, 311)
                .addComponent(prevButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(toRatingButton, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toRatingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prevButton)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void updateDataField(JTable table, List<DataField> dataFields, Stages stage) {
        if (table == null || dataFields == null) return;
        
        int selectedRow = table.getSelectedRow();
        DataField dataField = (selectedRow == -1) ? null : 
                dataFields.get(table.convertRowIndexToModel(selectedRow));
        DataFieldDialog dataFieldDialog = new DataFieldDialog(dataField);
        
        if (dataFieldDialog.showDialog()) {
            DataField newDataField = dataFieldDialog.getDataField();
            CriterionDialog.ActionType action = dataFieldDialog.getActionType();
            
            // сохранение в excel обновленных входных данных
            switch(action) {
                case Add:
                    // ишем последний критерий и берем его координаты ячеек
                    CellReference lastRef = DataField.lastDataFieldNameRef(dataFields, newDataField.getType());
                    // переходим на след.строку
                    int nextRow = lastRef.getRow() + 1;
                    CellReference ref = (newDataField.getType() == DataField.Types.BASE) ?
                            XLSParser.baseDataFieldsRef : XLSParser.otherDataFieldsRef;
                    newDataField.setNameRef(new CellReference(nextRow, ref.getCol()));
                    newDataField.setValueRef(new CellReference(nextRow, ref.getCol() + 1));
                    
                    XLSParser.saveXLSDataField(projectFileName, newDataField, stage);
                    break;
                    
                case Edit:
                    XLSParser.saveXLSDataField(projectFileName, newDataField, stage);
                    break;
                    
                case Delete:
                    newDataField.setName("");
                    newDataField.setValue("");
                    
                    XLSParser.saveXLSDataField(projectFileName, newDataField, stage);
                    break;
            }
            // и повторная загрузка из excel
            curProject.getStage(stage).setDataFields(
                    XLSParser.readXLSDataFields(projectFileName, stage));
            initTable(curProject.getStage(stage).getDataFields(), 
                    (stage == Stages.STAGE2_COOPERATION) ? dataCooperateTM : dataRatingTM);
        }
    }
    
    private void dataRatingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataRatingButtonActionPerformed
        updateDataField(dataRatingTable,
            curProject.getStage(Stages.STAGE3_RATING).getDataFields(),
            Stages.STAGE3_RATING);
    }//GEN-LAST:event_dataRatingButtonActionPerformed

    private void dataCooperateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataCooperateButtonActionPerformed
        updateDataField(dataCooperateTable,
            curProject.getStage(Stages.STAGE2_COOPERATION).getDataFields(),
            Stages.STAGE2_COOPERATION);
    }//GEN-LAST:event_dataCooperateButtonActionPerformed

    private void prevButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevButtonActionPerformed
        listener.nextStage(Project.Stages.STAGE1_CHOISE);
    }//GEN-LAST:event_prevButtonActionPerformed

    private void toRatingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toRatingButtonActionPerformed
        listener.nextStage(Project.Stages.STAGE3_RATING);
    }//GEN-LAST:event_toRatingButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dataCooperateButton;
    private javax.swing.JTable dataCooperateTable;
    private javax.swing.JScrollPane dataCooperationScrollPane;
    private javax.swing.JButton dataRatingButton;
    private javax.swing.JScrollPane dataRatingScrollPane;
    private javax.swing.JTable dataRatingTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton prevButton;
    private javax.swing.JButton toRatingButton;
    // End of variables declaration//GEN-END:variables
}
