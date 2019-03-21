/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.DAOException;
import dao.DatabaseDao;
import dao.ProductManager;
import domain.Product;
import gui.helpers.SimpleListModel;
import gui.helpers.ValidationHelper;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import javax.swing.JOptionPane;

/**
 *
 * @author shaki694
 */
public class ProductEditor extends javax.swing.JDialog {

    //private ProductEditorDAO newEditorDAO = new ProductEditorDAO();
    private final ProductManager newEditorDAO;
    private SimpleListModel myModel = new SimpleListModel();
    private Collection<String> categories = new HashSet<>();
    private Product newProduct = new Product();
    private ValidationHelper validate = new ValidationHelper();

    /**
     * Creates new form ProductEditor
     */
    public ProductEditor(java.awt.Window parent, boolean modal, ProductManager newEditorDAO) {
        this.newEditorDAO = newEditorDAO;
        super.setModal(modal);
        initComponents();
        categories = newEditorDAO.getCategories();
        myModel.updateItems(categories);
        txtCategory.setModel(myModel);
        txtCategory.setEditable(true);
        validate.addTypeFormatter(txtPrice, "#0.00", BigDecimal.class);
        validate.addTypeFormatter(txtQuantity, "#", Integer.class);
    }

    public ProductEditor(java.awt.Window parent, boolean modal, Product productToEdit, ProductManager newEditorDAO) {
        this(parent, modal, newEditorDAO);
        this.newProduct = productToEdit;
        txtID.setText(newProduct.getProductId());
        txtName.setText(newProduct.getName());
        txtDescription.setText(newProduct.getDescription());
        txtCategory.setSelectedItem(newProduct.getCategory());
        txtPrice.setValue(newProduct.getListPrice());
        txtQuantity.setValue(newProduct.getQuantity());
        txtID.setEditable(false);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSave = new javax.swing.JButton();
        txtCancel = new javax.swing.JButton();
        txtCategory = new javax.swing.JComboBox<>();
        txtPrice = new javax.swing.JFormattedTextField();
        txtQuantity = new javax.swing.JFormattedTextField();

        jTextField1.setText("jTextField1");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("productDialog"); // NOI18N

        jLabel1.setText("ID:");

        txtID.setName("txtID"); // NOI18N

        jLabel2.setText("Name:");

        txtName.setName("txtName"); // NOI18N

        jLabel3.setText("Description:");

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        txtDescription.setName("txtDescription"); // NOI18N
        jScrollPane2.setViewportView(txtDescription);

        jLabel4.setText("Category:");

        jLabel5.setText("Price:");

        jLabel6.setText("Quantity In Stock:");

        txtSave.setText("Save");
        txtSave.setName("btnSave"); // NOI18N
        txtSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSaveActionPerformed(evt);
            }
        });

        txtCancel.setText("Cancel");
        txtCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCancelActionPerformed(evt);
            }
        });

        txtCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        txtCategory.setName("txtCategory"); // NOI18N

        txtPrice.setName("txtPrice"); // NOI18N

        txtQuantity.setName("txtQuantity"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtID)
                            .addComponent(txtName)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                            .addComponent(txtCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPrice)
                            .addComponent(txtQuantity)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(txtSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCancel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(txtCancel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtSave)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSaveActionPerformed
        try {
            String id = txtID.getText();
            String name = txtName.getText();
            String description = txtDescription.getText();
            String category = (String) txtCategory.getSelectedItem();
            BigDecimal price = (BigDecimal) txtPrice.getValue();
            Integer quantity = (Integer) txtQuantity.getValue();

            newProduct.setProductId(id);
            newProduct.setName(name);
            newProduct.setDescription(description);
            newProduct.setCategory(category);
            newProduct.setListPrice(price);
            newProduct.setQuantity(quantity);

            if (validate.isObjectValid(newProduct)) {
                if (txtID.isEditable() && newEditorDAO.searchProduct(id) == null || txtID.isEditable() == false) {
                    newEditorDAO.saveProduct(newProduct);
                    dispose();
                } else {
                    if (txtID.isEditable()) {
                        JOptionPane.showMessageDialog(this, "Product ID " + id + " already exists. Please enter a new Product Id.");
                    }

                }

            }
        } catch (DAOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), ex.toString(), JOptionPane.INFORMATION_MESSAGE);
        }


    }//GEN-LAST:event_txtSaveActionPerformed

    private void txtCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCancelActionPerformed
        dispose();
    }//GEN-LAST:event_txtCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton txtCancel;
    private javax.swing.JComboBox<String> txtCategory;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JFormattedTextField txtPrice;
    private javax.swing.JFormattedTextField txtQuantity;
    private javax.swing.JButton txtSave;
    // End of variables declaration//GEN-END:variables
}
