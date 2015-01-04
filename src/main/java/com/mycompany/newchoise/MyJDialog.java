package com.mycompany.newchoise;

//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.GroupLayout;
//import javax.swing.JButton;
import javax.swing.JDialog;

/**
 *
 * @author Иван
 */
public class MyJDialog extends JDialog {
    
//    private JButton okButton;
//    private JButton cancelButton;
    protected boolean result = false;
 
    public MyJDialog() {
        initDialogComponents();
//        initOkCancelButtons();
    }
    
    public MyJDialog(String title) {
        initDialogComponents();
        setTitle(title);
//        initOkCancelButtons();
    }
    
    public boolean getResult() {
        return result;
    }
    
//    public void initFields(Project proj) {
//    }
    
    public boolean showDialog() {
        setVisible(true);
        return result;
    }
    
//    public boolean showDialog(Project proj) {
//        initFields(proj);
//        setVisible(true);
//        return result;
//    }
    
//    private void okButtonActionPerformed(ActionEvent evt) {                                         
//        result = true;
//        setVisible(false);
//        dispose();
//    }                                        
//
//    private void cancelButtonActionPerformed(ActionEvent evt) {                                             
//        result = false;
//        setVisible(false);
//        dispose();
//    } 

    protected void initDialogComponents() {
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
        setLocationRelativeTo(getParent());
    }
    
//    protected void initOkCancelButtons() {    
//        okButton = new JButton();
//        cancelButton = new JButton();
//        
//        okButton.setText("Готово");
//        okButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                okButtonActionPerformed(evt);
//            }
//        });
//
//        cancelButton.setText("Отмена");
//        cancelButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                cancelButtonActionPerformed(evt);
//            }
//        });
//        
//        GroupLayout layout = new GroupLayout(getContentPane());
//        getContentPane().setLayout(layout);
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
//                .addContainerGap(289, Short.MAX_VALUE)
//                .addComponent(okButton)
//                .addGap(18, 18, 18)
//                .addComponent(cancelButton)
//                .addContainerGap())
//        );
//        layout.setVerticalGroup(
//            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
//                .addContainerGap(311, Short.MAX_VALUE)
//                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                    .addComponent(cancelButton)
//                    .addComponent(okButton))
//                .addContainerGap())
//        );
//        
//        pack();
//    }
}
