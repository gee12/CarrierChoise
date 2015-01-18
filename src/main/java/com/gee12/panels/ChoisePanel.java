package com.gee12.panels;

import com.gee12.other.BubbleBorder;
import com.gee12.tableModels.CarriersTableModel;
import com.gee12.groupTableHeader.MultiLineHeaderRenderer;
import com.gee12.other.MatrixTableListener;
import com.gee12.other.SwitchStageListener;
import com.gee12.other.XLSParser;
import com.gee12.structures.Carrier;
import static com.gee12.structures.Carrier.CAPACITIES;
import static com.gee12.structures.Carrier.VOLUMES;
import com.gee12.structures.Matrix;
import com.gee12.structures.MatrixCriterion;
import com.gee12.structures.Project;
import com.gee12.tableModels.MatrixCriterionsTableModel;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import javafx.scene.control.SelectionMode;
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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Иван
 */
public class ChoisePanel extends JPanel implements MatrixTableListener {

    SwitchStageListener listener;
    private final CarriersTableModel carriersTM;
    private final MatrixCriterionsTableModel matrixCritsTM;
    private String projectFileName = null;            
    private Project curProject = null;
    private Carrier curCarrier = null;
           
    public ChoisePanel(SwitchStageListener listener) {
        this.listener = listener;
        carriersTM = new CarriersTableModel();
        matrixCritsTM = new MatrixCriterionsTableModel();
        
        initComponents();
        
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
        this.projectFileName = fileName;
        
        carriersTableSort();
        
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
                    onRowSelected(lsm.getMinSelectionIndex());
                }
            }
        });
        
        if (curProject.getCarriers().size() > 0) {
            carriersTable.setRowSelectionInterval(0, 0);
        }
    }
    
    // for rows sort
    public void carriersTableSort() {
        List <Carrier> carriers = curProject.getCarriers();
        carriers.sort(carrierComparator);
        carriersTM.setData(carriers);
    }
    
    public void onRowSelected(int selectedRow) {
//        for(Carrier car : carriersTM.getData()) {
//            if (car.getName().isEmpty()
//                    /*|| car.getMatrix() */) {
//                car.setState(Carrier.States.ERROR);
//            } else car.setState(Carrier.States.NORM);
//        }
        
        if (selectedRow != -1) {
            curCarrier = curProject.getCarriers()
                    .get(carriersTable.convertRowIndexToModel(selectedRow));
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
        int selectedRow = carriersTable.getSelectedRow();
        // make after sort
        ratingTextField.setText(String.valueOf(carriersTable.getModel().getValueAt(selectedRow, 0)));
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
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
        jLabel5 = new javax.swing.JLabel();
        ratingTextField = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        matrixCritsTable = new javax.swing.JTable();
        matrixPanel = new MatrixPanel(this, CAPACITIES, VOLUMES);
        addCarrierButton = new javax.swing.JButton();
        toCooperateButton = new javax.swing.JButton();
        refreshCarriersButton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(840, 600));

        jLabel1.setBackground(java.awt.Color.orange);
        jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getStyle() | java.awt.Font.BOLD, jLabel1.getFont().getSize()+4));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ВЫБОР ПЕРЕВОЗЧИКА");
        jLabel1.setBorder(new BubbleBorder(Color.ORANGE,1,8,0));
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setOpaque(true);

        carriersTable.setBorder(null);
        carriersTable.setModel(carriersTM);
        carriersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(carriersTable);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Подробнее", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel2.setText("Имя");

        jLabel3.setText("Наиболее повторяемая грузоподъемность");

        jLabel4.setText("Число повторений");

        capacityTextField.setForeground(new java.awt.Color(0, 0, 0));
        capacityTextField.setEnabled(false);

        repeatTextField.setEnabled(false);

        jLabel5.setText("Рейтинг в списке");

        ratingTextField.setEnabled(false);

        saveButton.setText("Сохранить");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        matrixCritsTable.setModel(matrixCritsTM);
        matrixCritsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(matrixCritsTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(matrixPanel);
        matrixPanel.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 155, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(matrixPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nameTextField))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(ratingTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(78, 78, 78)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(capacityTextField)
                            .addComponent(repeatTextField)
                            .addComponent(saveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
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
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(capacityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(repeatTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(jLabel5)
                    .addComponent(ratingTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addComponent(refreshCarriersButton))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(toCooperateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 12, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(toCooperateButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addCarrierButton)
                    .addComponent(refreshCarriersButton)))
        );
    }// </editor-fold>                        

    private void toCooperateButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        int selectedRow = carriersTable.getSelectedRow();
        if (selectedRow != -1) { 
//            curProject.setCurrentCarrier(carriersTable.convertRowIndexToModel(selectedRow));
            curCarrier = curProject.getCarriers().get(carriersTable.convertRowIndexToModel(selectedRow));
            listener.nextStage(Project.Stages.STAGE2_COOPERATION);
        }
    }                                                 

    private void addCarrierButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        int maxId = 0;
        for(Carrier car : curProject.getCarriers()) {
            if (maxId < car.getId())
                maxId = car.getId();
        }
        carriersTM.addRow(new Carrier(maxId + 1, "", 0, 0, new Matrix()));
    }                                                

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        XLSParser.saveXLSCarrier(projectFileName, curCarrier);
//        curProject = XLSParser.readXLSProject(projectFileName);
//        init(curProject, projectFileName);
    }                                          

    private void refreshCarriersButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                      
        carriersTM.fireTableDataChanged();
        carriersTableSort();
    }                                                     

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
    
    // Variables declaration - do not modify                     
    private javax.swing.JButton addCarrierButton;
    private javax.swing.JTextField capacityTextField;
    private javax.swing.JTable carriersTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    MatrixPanel matrixPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable matrixCritsTable;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTextField ratingTextField;
    private javax.swing.JButton refreshCarriersButton;
    private javax.swing.JTextField repeatTextField;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton toCooperateButton;
    // End of variables declaration                   

}
