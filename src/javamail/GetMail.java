/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javamail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.activation.*;
import javax.mail.Flags.Flag;
import javax.mail.search.FlagTerm;

public class GetMail extends javax.swing.JFrame {

    /**
     * Creates new form GetMail
     */
    public GetMail() {
        initComponents();
    }

    public void getMail(String host, String storeType, String user, String password) throws NoSuchProviderException, MessagingException{
        DefaultTableModel mod = new DefaultTableModel();
        mod.addColumn("No");
        mod.addColumn("Subyek");
        mod.addColumn("Dari");
        mod.addColumn("Pesam");
        mod.addColumn("Waktu");
        try {
            Properties properties = new Properties();
            properties.put("mail.imap.host", host);
            properties.put("mail.imap.port", "993");
            properties.put("mail.imap.starttls.enable", "true");
            properties.put("mail.imap.ssl.trust", host);
            
            Session emailSession = Session.getDefaultInstance(properties);
            Store store = emailSession.getStore("imaps");
            
            store.connect(host, user, password);
            
            Folder inbox = store.getFolder("Inbox");
            inbox.open(Folder.READ_WRITE);
            
            Message[] messages = inbox.search(new FlagTerm(new Flags(Flag.SEEN), false));
            int mLength = messages.length;
            if (mLength>20) mLength=20;
            for (int i = 0, n =mLength; i < n; i++){
                Message message = messages[i];
                message.setFlag(Flag.SEEN, true);
                mod.addRow(new Object[]{
                    i+1,message.getSubject(),
                    message.getFrom()[0],message.getContent().toString(),message.getSentDate()
                            
                });
            }
            tableInbox.setModel(mod);
            inbox.close(false);
            store.close();
        }catch (NoSuchProviderException e){
            e.printStackTrace();
        }catch (MessagingException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnInbox = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableInbox = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        textMessage = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JONI PRIANTO - JAVA MAIL");
        setResizable(false);

        btnInbox.setText("Inbox");
        btnInbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInboxActionPerformed(evt);
            }
        });

        tableInbox.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No", "Subyek", "Dari", "Pesan", "Waktu"
            }
        ));
        tableInbox.setToolTipText("");
        tableInbox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableInboxMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableInbox);

        textMessage.setColumns(20);
        textMessage.setRows(5);
        jScrollPane2.setViewportView(textMessage);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnInbox)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnInbox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInboxActionPerformed
        try {
            String host = "imap.gmail.com";
            String mailStoreType = "imap";
            String username = "joniprianto60@gmail.com"; //akun gmail anda
            String password = "hhfv spmm syrg uzju";
            getMail(host, mailStoreType, username, password);
        } catch (MessagingException ex) {
            Logger.getLogger(GetMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnInboxActionPerformed

    private void tableInboxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableInboxMouseClicked
        int row = tableInbox.getSelectedRow();
        textMessage.setText("no :"+tableInbox.getModel().getValueAt(row, 0).toString()
        +"\nDari : "+tableInbox.getModel().getValueAt(row, 2).toString()
        +"\nSubyek : "+tableInbox.getModel().getValueAt(row, 1).toString()
        +"\nWaktu : "+tableInbox.getModel().getValueAt(row, 4).toString()
        +"\nIsi : "+tableInbox.getModel().getValueAt(row, 3).toString());
       
    }//GEN-LAST:event_tableInboxMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GetMail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GetMail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GetMail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GetMail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GetMail().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInbox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableInbox;
    private javax.swing.JTextArea textMessage;
    // End of variables declaration//GEN-END:variables
}
