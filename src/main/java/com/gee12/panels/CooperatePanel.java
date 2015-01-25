package com.gee12.panels;

import com.gee12.other.BubbleBorder;
import com.gee12.other.ButtonEditor;
import com.gee12.other.ButtonRenderer;
import com.gee12.other.RowListener;
import com.gee12.tablemodels.DataTableModel;
import com.gee12.other.SwitchStageListener;
import com.gee12.other.XLSParser;
import com.gee12.structures.Carrier;
import com.gee12.structures.DataField;
import com.gee12.structures.Project;
import com.gee12.structures.Project.Stages;
import java.awt.Color;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import org.apache.poi.ss.util.CellReference;

/**
 *
 * @author Иван
 */
public class CooperatePanel extends JPanel implements RowListener {
    
    public static final int NAME_CELL_WIDTH = 595;
    public static final int VALUE_CELL_WIDTH = 100;
    public static final int TYPE_CELL_WIDTH = 70;
    public static final int DELETE_CELL_WIDTH = 30;

    SwitchStageListener listener;
    private final DataTableModel dataCooperateTM;
    private final DataTableModel dataRatingTM;
//    private String projectFileName = null;            
//    private Project curProject = null;
//    private Project srcProject = null;
    private Carrier selectedCarrier = null;
            
    ////////////////////////////////////////////////////////////////////////////
    public CooperatePanel(SwitchStageListener listener) {
        this.listener = listener;
        
        dataCooperateTM = new DataTableModel();
        dataRatingTM = new DataTableModel();
        
        initComponents();
        
        initDataTable(dataCooperateTable);
        initDataTable(dataRatingTable);

    }
    
    ////////////////////////////////////////////////////////////////////////////
    public void init(Carrier selectedCarrier, String fileName) {
        if (selectedCarrier == null) return;
//        this.srcProject = srcProj;
//        this.curProject = new Project();
        this.selectedCarrier = selectedCarrier;
//        this.projectFileName = fileName;
        
        nameLabel.setText("СОТРУДНИЧЕСТВО С ПЕРЕВОЗЧИКОМ '" + selectedCarrier.getName() + "'");
        
//        if (selectedCarrier.getName().equalsIgnoreCase(selectedCarrier.getName())) {
//            curProject = srcProject;
//        } else {
//            curProject = new Project();
//        }
        initTables(selectedCarrier);
    }
        
    ////////////////////////////////////////////////////////////////////////////
    private void initDataTable(JTable table) {
        if (table == null) return;
        // Create the combo box editor
        JComboBox comboBox = new JComboBox(DataTableModel.COMBO_STATES);
        comboBox.setEditable(false);   
        // Assign the editor
        TableColumnModel colModel = table.getColumnModel();
        colModel.getColumn(2).setCellEditor(
                new DefaultCellEditor(comboBox));
        colModel.getColumn(3).setCellEditor(
            new ButtonEditor(new JCheckBox(), this, -1, 3));
        colModel.getColumn(3).setCellRenderer(new ButtonRenderer(
            new ImageIcon(MainFrame.class.getResource("/images/delete.jpg"))));
        
        table.getColumnModel().getColumn(0).setPreferredWidth(NAME_CELL_WIDTH);
        table.getColumnModel().getColumn(1).setPreferredWidth(VALUE_CELL_WIDTH);
        table.getColumnModel().getColumn(2).setPreferredWidth(TYPE_CELL_WIDTH);
        table.getColumnModel().getColumn(3).setPreferredWidth(DELETE_CELL_WIDTH);
    }

    ////////////////////////////////////////////////////////////////////////////
    public void initTables(Carrier car) {
        initTable(car.getStage(Project.Stages.STAGE2_COOPERATION).getDataFields(), dataCooperateTM);
        initTable(car.getStage(Project.Stages.STAGE3_RATING).getDataFields(), dataRatingTM);
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public void initTable(List <DataField> dataFields, DataTableModel tm) {
        // for rows sort
        dataFields.sort(DataField.COMPARATOR);
        tm.setData(dataFields);
    }

    ////////////////////////////////////////////////////////////////////////////
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameLabel = new javax.swing.JLabel();
        toRatingButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        dataCooperationScrollPane = new javax.swing.JScrollPane(dataCooperateTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        dataCooperateTable = new javax.swing.JTable();
        addDataCooperateButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        dataRatingScrollPane = new javax.swing.JScrollPane(dataCooperateTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        dataRatingTable = new javax.swing.JTable();
        addDataRatingButton = new javax.swing.JButton();
        prevButton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(900, 600));

        nameLabel.setBackground(java.awt.Color.orange);
        nameLabel.setFont(nameLabel.getFont().deriveFont(nameLabel.getFont().getStyle() | java.awt.Font.BOLD, nameLabel.getFont().getSize()+4));
        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameLabel.setText("СОТРУДНИЧЕСТВО С ПЕРЕВОЗЧИКОМ");
        nameLabel.setBorder(new BubbleBorder(Color.ORANGE,1,8,0));
        nameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nameLabel.setOpaque(true);

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

        addDataCooperateButton.setText("+");
        addDataCooperateButton.setFocusable(false);
        addDataCooperateButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addDataCooperateButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addDataCooperateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDataCooperateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(dataCooperationScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addDataCooperateButton))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(addDataCooperateButton)
                .addGap(0, 167, Short.MAX_VALUE))
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

        addDataRatingButton.setText("+");
        addDataRatingButton.setFocusable(false);
        addDataRatingButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addDataRatingButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addDataRatingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDataRatingButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(dataRatingScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addDataRatingButton))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(addDataRatingButton)
                .addGap(0, 189, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(dataRatingScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        prevButton.setText("Прекратить сотрудничество");
        prevButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevButtonActionPerformed(evt);
            }
        });

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
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(prevButton)
                        .addGap(18, 18, 18)
                        .addComponent(toRatingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    
    ////////////////////////////////////////////////////////////////////////////
    private DataField getNewDataField(Stages stage) {
        DataField res = new DataField();
        // ишем последний DataField и берем его координаты ячеек
        CellReference lastRef = DataField.lastDataFieldNameRef(
                selectedCarrier.getStage(stage).getDataFields(), 
                res.getType());
        // переходим на след.строку
//        int nextRow = lastRef.getRow() + 1;
//        CellReference ref = (res.getType() == DataField.Types.BASE)
//                ? XLSParser.baseDataFieldsRef : XLSParser.otherDataFieldsRef;
        res.setNameRef(new CellReference(lastRef.getRow() + 1, lastRef.getCol()));
        res.setValueRef(new CellReference(lastRef.getRow() + 1, lastRef.getCol() + 1));
        
        return res;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    private void addDataRatingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDataRatingButtonActionPerformed
        DataField newDF = getNewDataField(Stages.STAGE3_RATING);
        dataRatingTM.addRow(newDF);
//        selectedCarrier.getStage(Stages.STAGE3_RATING).getDataFields().add(newDF);
    }//GEN-LAST:event_addDataRatingButtonActionPerformed

    ////////////////////////////////////////////////////////////////////////////
    private void addDataCooperateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDataCooperateButtonActionPerformed
        DataField newDF = getNewDataField(Stages.STAGE2_COOPERATION);
        dataCooperateTM.addRow(newDF);
//        selectedCarrier.getStage(Stages.STAGE2_COOPERATION).getDataFields().add(newDF);
    }//GEN-LAST:event_addDataCooperateButtonActionPerformed

    ////////////////////////////////////////////////////////////////////////////
    @Override
    public Object editRow(JTable table, int row) {
        return null;
    }
        
    ////////////////////////////////////////////////////////////////////////////
    @Override
    public void deleteRow(JTable table, int row) {
        int res = JOptionPane.showConfirmDialog(null, "Удалить строку?", "Удаление",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (res == JOptionPane.YES_OPTION) {
            ((DataTableModel) table.getModel()).deleteRow(row);
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    private void prevButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevButtonActionPerformed
        listener.nextStage(Project.Stages.STAGE1_CHOISE);
    }//GEN-LAST:event_prevButtonActionPerformed

    ////////////////////////////////////////////////////////////////////////////
    private void toRatingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toRatingButtonActionPerformed
//        curProject.setCurrentCarrier(curCarrier);
//        curCarrier = curProject.getCurCarrier();
//        curCarrier
//        srcProject = curProject;
        
        listener.nextStage(Project.Stages.STAGE3_RATING);
    }//GEN-LAST:event_toRatingButtonActionPerformed

    ////////////////////////////////////////////////////////////////////////////
//    public Project getProject() {
//        return curProject;
//    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addDataCooperateButton;
    private javax.swing.JButton addDataRatingButton;
    private javax.swing.JTable dataCooperateTable;
    private javax.swing.JScrollPane dataCooperationScrollPane;
    private javax.swing.JScrollPane dataRatingScrollPane;
    private javax.swing.JTable dataRatingTable;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JButton prevButton;
    private javax.swing.JButton toRatingButton;
    // End of variables declaration//GEN-END:variables

}
