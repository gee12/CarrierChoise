package com.gee12.panels;

import com.gee12.other.BubbleBorder;
import com.gee12.tableModels.CarriersTableModel;
import com.gee12.groupTableHeader.MultiLineHeaderRenderer;
import com.gee12.other.ButtonEditor;
import com.gee12.other.ButtonRenderer;
import com.gee12.other.MatrixTableListener;
import com.gee12.other.RowListener;
import com.gee12.other.SwitchStageListener;
import com.gee12.other.XLSParser;
import com.gee12.structures.Carrier;
import static com.gee12.structures.Carrier.CAPACITIES;
import static com.gee12.structures.Carrier.VOLUMES;
import com.gee12.structures.Matrix;
import com.gee12.structures.Project;
import com.gee12.tableModels.DataTableModel;
import com.gee12.tableModels.MatrixCriterionsTableModel;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Иван
 */
public class ChoisePanel extends JPanel implements MatrixTableListener, RowListener {

    SwitchStageListener listener;
    private final CarriersTableModel carriersTM;
    private final MatrixCriterionsTableModel matrixCritsTM;
//    private String projectFileName = null;            
    private Project curProject = null;
    private Carrier curCarrier = null;
    private int selectedRow = 0;
           
    public ChoisePanel(SwitchStageListener listener) {
        this.listener = listener;
        carriersTM = new CarriersTableModel();
        matrixCritsTM = new MatrixCriterionsTableModel();
        
        initComponents();
        //
        matrixPanel.addTableModelListener(this);
        
        // for multi line header
        MultiLineHeaderRenderer renderer = new MultiLineHeaderRenderer();
        TableColumnModel columnModel = carriersTable.getColumnModel();
//        curriersTable.setBorder(new BevelBorder(BevelBorder.RAISED));
        Enumeration e = columnModel.getColumns();
        while (e.hasMoreElements()) {
            ((TableColumn) e.nextElement()).setHeaderRenderer(renderer);
        }
        columnModel.getColumn(1).setPreferredWidth((int)(
            carriersTable.getPreferredScrollableViewportSize().width * 0.80));
        columnModel.getColumn(4).setPreferredWidth(30);
        columnModel.getColumn(4).setCellEditor(
            new ButtonEditor(new JCheckBox(), this, -1, 4));
        columnModel.getColumn(4).setCellRenderer(new ButtonRenderer(
            new ImageIcon(MainFrame.class.getResource("/images/delete.jpg"))));
        
        // listen for changes in the name
        nameTextField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                check();
            }

            public void removeUpdate(DocumentEvent e) {
                check();
            }

            public void insertUpdate(DocumentEvent e) {
                check();
            }

            public void check() {
                String name = nameTextField.getText();
                if (name.isEmpty()) {
                    nameTextField.setBackground(Color.RED);
                } else {
                    nameTextField.setBackground(UIManager.getColor("TextField.background"));
                    curCarrier.setName(name);
//                    updateCarriersTable(curCarrier);
                }
            }
        });

    }
    
    public void init(Project proj, String fileName) {
        if (proj == null) return;
        this.curProject = proj;
//        this.projectFileName = fileName;
        
        initCarriersTable();
        
        // for row selection
        ListSelectionModel rowSM = carriersTable.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                // ignore extra messages.
                if (e.getValueIsAdjusting()) {
                    return;
                }
                ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                if (!lsm.isSelectionEmpty()) {
//                    selectedRow = lsm.getMinSelectionIndex();
                    onRowSelected(lsm.getMinSelectionIndex());
                }
            }
        });
        
        if (curProject.getCarriers().size() > 0) {
            carriersTable.setRowSelectionInterval(selectedRow, selectedRow);
        }
    }
    
    // for rows sort
    public void initCarriersTable() {
        List <Carrier> carriers = curProject.getCarriers();
        carriers.sort(carrierComparator);
        carriersTM.setData(carriers);
        // select row with current carrier
//        for (int i = 0; i < carriers.size(); i++) {
//            if (curCarrier.equals(carriers.get(i))) {
//                int tableRow = carriersTable.convertRowIndexToView(i);
//                onRowSelected(tableRow);
//                return;
//            }
//        }
        if (curCarrier != null) {
            int modelRow = carriers.indexOf(curCarrier);
            int tableRow = carriersTable.convertRowIndexToView(modelRow);
//            onRowSelected(tableRow);
            carriersTable.setRowSelectionInterval(tableRow, tableRow);
        }
    }
    
    public void onRowSelected(int row) {
//        for(Carrier car : carriersTM.getData()) {
//            if (car.getName().isEmpty()
//                    /*|| car.getMatrix() */) {
//                car.setState(Carrier.States.ERROR);
//            } else car.setState(Carrier.States.NORM);
//        }
        this.selectedRow = row;
        
        if (row != -1) {
            curCarrier = curProject.getCarriers()
                    .get(carriersTable.convertRowIndexToModel(row));
            initFields(curCarrier);
        }
    }

    public void updateCarriersTable(Carrier carrier) {
        int rowTable = carriersTable.getSelectedRow();
        int rowTM = carriersTable.convertRowIndexToModel(rowTable);
        carriersTM.setValueAt(carrier.getName(), rowTM, 1);
        carriersTM.setValueAt(carrier.getCapacity(), rowTM, 2);
        carriersTM.setValueAt(carrier.getRepeatNum(), rowTM, 3);
//        carriersTM.fireTableDataChanged();
//        carriersTable.setRowSelectionInterval(rowTable, rowTable);
        
//        carriersTableSort();
    }
        
    public void initFields(Carrier carrier) {
        // changable fields
        nameTextField.setText(carrier.getName());
        matrixPanel.setMatrixTableModel(carrier.getMatrix());
        // 
        matrixCritsTM.setData(carrier.getMatrix(), CAPACITIES);
        initDependentFields(carrier);
    }
    
    public void initDependentFields(Carrier carrier) {
        capacityTextField.setText(String.valueOf(carrier.getCapacity()));
        repeatTextField.setText(String.valueOf(carrier.getRepeatNum()));
//        int selectedRow = carriersTable.getSelectedRow();
        // make after sort
//        ratingTextField.setText(String.valueOf(carriersTable.getModel().getValueAt(selectedRow, 0)));
    }
    
    public static void sortTableRows(JTable table) {
        if (table == null) return;
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        int columnIndexToSort = 3;
        sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.DESCENDING));
        sorter.setSortKeys(sortKeys);
        sorter.sort();
    }
    
    public Carrier getCurrentCarrier() {
        return curCarrier;
    }

    //
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jScrollPane2 = new javax.swing.JScrollPane();
        matrixCritsTable = new javax.swing.JTable();
        matrixPanel = new com.gee12.panels.MatrixPanel();
        addCarrierButton = new javax.swing.JButton();
        toCooperateButton = new javax.swing.JButton();
        refreshCarriersButton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(900, 600));

        jLabel1.setBackground(java.awt.Color.orange);
        jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getStyle() | java.awt.Font.BOLD, jLabel1.getFont().getSize()+4));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ВЫБОР ПЕРЕВОЗЧИКА");
        jLabel1.setBorder(new BubbleBorder(Color.ORANGE,1,8,0));
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setOpaque(true);

        carriersTable.setBorder(null);
        carriersTable.setModel(carriersTM);
        carriersTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(carriersTable);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Подробнее", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel2.setText("Имя");

        jLabel3.setText("Наиболее повторяемая грузоподъемность");

        jLabel4.setText("Число повторений");

        capacityTextField.setForeground(new java.awt.Color(0, 0, 0));
        capacityTextField.setEnabled(false);

        repeatTextField.setEnabled(false);

        matrixCritsTable.setModel(matrixCritsTM);
        matrixCritsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(matrixCritsTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(68, 68, 68)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(repeatTextField)
                            .addComponent(capacityTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nameTextField)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(matrixPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(matrixPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(capacityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(repeatTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap())
        );

        addCarrierButton.setText("Добавить");
        addCarrierButton.setFocusable(false);
        addCarrierButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addCarrierButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addCarrierButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCarrierButtonActionPerformed(evt);
            }
        });

        toCooperateButton.setBackground(new java.awt.Color(170, 92, 0));
        toCooperateButton.setText("Сотрудничать");
        toCooperateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toCooperateButtonActionPerformed(evt);
            }
        });

        refreshCarriersButton.setText("Обновить");
        refreshCarriersButton.setFocusable(false);
        refreshCarriersButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        refreshCarriersButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        refreshCarriersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshCarriersButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addCarrierButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(refreshCarriersButton)
                                .addGap(0, 249, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(toCooperateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(toCooperateButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addCarrierButton)
                    .addComponent(refreshCarriersButton)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void toCooperateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toCooperateButtonActionPerformed
        int selectedRow = carriersTable.getSelectedRow();
        if (selectedRow != -1) { 
//            curProject.setCurrentCarrier(carriersTable.convertRowIndexToModel(selectedRow));
            curCarrier = curProject.getCarriers().get(carriersTable.convertRowIndexToModel(selectedRow));
            listener.nextStage(Project.Stages.STAGE2_COOPERATION);
        }
    }//GEN-LAST:event_toCooperateButtonActionPerformed

    private void addCarrierButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCarrierButtonActionPerformed
        int maxId = -1;
        for(Carrier car : curProject.getCarriers()) {
            if (maxId < car.getId())
                maxId = car.getId();
        }
        carriersTM.addRow(new Carrier(maxId + 1, "", 0, 0, new Matrix()));
        int row = carriersTM.getRowCount() - 1;
        carriersTable.setRowSelectionInterval(row, row);
    }//GEN-LAST:event_addCarrierButtonActionPerformed

    private void refreshCarriersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshCarriersButtonActionPerformed
//        carriersTM.fireTableDataChanged();
        initCarriersTable();
    }//GEN-LAST:event_refreshCarriersButtonActionPerformed

    // when matrix table changed
    @Override
    public void tableChanged() {
        curCarrier.setMatrix(matrixPanel.getMatrix());
        matrixCritsTM.setData(curCarrier.getMatrix(), CAPACITIES);
        curCarrier.defineCapacityRepeat(matrixCritsTM.getData());
        initDependentFields(curCarrier);
        updateCarriersTable(curCarrier);
    }
    
    public static Comparator<Carrier> carrierComparator = new Comparator<Carrier>() {
	@Override
	public int compare(Carrier car1, Carrier car2) {
            final double num1 = car1.getRepeatNum();
            final double num2 = car2.getRepeatNum();
	    return (num1 < num2) ? 1 :
		    (num1 > num2) ? -1 : 0;
	}
    };
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCarrierButton;
    private javax.swing.JTextField capacityTextField;
    private javax.swing.JTable carriersTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable matrixCritsTable;
    private com.gee12.panels.MatrixPanel matrixPanel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JButton refreshCarriersButton;
    private javax.swing.JTextField repeatTextField;
    private javax.swing.JButton toCooperateButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public Object editRow(JTable table, int row) {
        return null;
    }

    @Override
    public void deleteRow(JTable table, int row) {
        int res = JOptionPane.showConfirmDialog(null, "Удалить перевозчика?", "Удаление",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (res == JOptionPane.YES_OPTION) {
            ((CarriersTableModel)table.getModel()).deleteRow(row);
        }
    }

}
