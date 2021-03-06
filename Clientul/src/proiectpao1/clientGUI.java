package proiectpao1;

import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class clientGUI extends javax.swing.JFrame {

    String nume;
    Socket sc;
    BufferedReader bf;
    PrintWriter pw;
    ArrayList<String> userList = new ArrayList();
    Boolean isConnected = false;
    
    public clientGUI() {
        initComponents();
    }
    
    public class Client implements Runnable{

        @Override
        public synchronized void run() {
            
            String[] data;
            String msg;
                
            try {
                
                while ((msg = bf.readLine()) != null) {

                    data = msg.split("#");
                    switch (data[2]) {
                        case "Msg":
                            chatTextArea.append(data[0] + ">> " + data[1] + "\n");
                            chatTextArea.setCaretPosition(chatTextArea.getDocument().getLength());
                            break;
                        case "Conectat":
                            chatTextArea.removeAll();
                            addUser(data[0]);
                            break;
                        case "Deconectat":
                            removeUser(data[0]);
                            break;
                        case "Exista":
                            JOptionPane.showMessageDialog(null, "Porecla este deja folosita.");
                            deconecteaza();
                            isConnected = false;
                            break;
                        case "Gata":
                            //onlineUserArea.setText("");
                            onlineUserList.setListData(new Vector());//***
                            scrieUseri();
                            userList.clear();//sa reseteze lista de useri, sa nu apara lista veche + cea noua (useri care apar de 2 ori)
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Client - Eroare la decodarea datelor. \n");
                            break;
                    }
                }
            }
            catch(IOException e) {

               //JOptionPane.showMessageDialog(null, "Client - Eroare la decodarea datelor: " + e.getMessage());
           }
       }
    }
    
    
    public synchronized void addUser(String data) {
        
         userList.add(data);
    }
    
    public synchronized void removeUser(String data) {
        
        chatTextArea.append(data + " s-a deconectat.\n");
    }
    
    public synchronized void scrieUseri() {
        
         String[] tempList = new String[(userList.size())];
         userList.toArray(tempList);
         int i = 0;
         //for (String item:tempList) {
             //onlineUserArea.append(item + "\n");
         //}
         onlineUserList.setListData(tempList);//***
     }
    
    public void firClient() {
        
         Thread nouUser = new Thread(new Client());
         nouUser.start();
    }
    
    
    public synchronized void deconecteaza () {
        
        try {  
            chatTextArea.setText("");
            chatTextArea.append("Deconectat.\n");
            sc.close();
            sc = null;
            
       } catch(IOException e) {
           
            JOptionPane.showMessageDialog(null, "Client - Deconectare esuata. \n");
       }

       userNameArea.setEditable(true);
       //onlineUserArea.setText("");
       onlineUserList.setListData(new Vector());//***
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        userNameArea = new javax.swing.JTextField();
        connectButton = new javax.swing.JButton();
        disconnectButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        chatTextArea = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        inputTextArea = new javax.swing.JTextArea();
        sendButton = new javax.swing.JButton();
        sendAllButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        onlineUserList = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat");

        jLabel1.setText("Utilizator:");

        userNameArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                userNameAreaKeyPressed(evt);
            }
        });

        connectButton.setText("Conecteaza");
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        disconnectButton.setText("Deconecteaza");
        disconnectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disconnectButtonActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Utilizatori online");
        jLabel2.setToolTipText("");

        chatTextArea.setEditable(false);
        chatTextArea.setColumns(20);
        chatTextArea.setLineWrap(true);
        chatTextArea.setRows(5);
        jScrollPane2.setViewportView(chatTextArea);

        inputTextArea.setColumns(20);
        inputTextArea.setLineWrap(true);
        inputTextArea.setRows(5);
        inputTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inputTextAreaKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputTextAreaKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(inputTextArea);

        sendButton.setText("Trimite");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        sendAllButton.setText("Trimite Toti");
        sendAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendAllButtonActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jScrollPane4.setViewportView(onlineUserList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sendAllButton)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(sendButton)
                                .addGap(17, 17, 17)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(connectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                    .addComponent(disconnectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userNameArea))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userNameArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(connectButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(disconnectButton)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sendButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sendAllButton))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed

        synchronized (clientGUI.this){
            
            if (isConnected == false) {

                nume = userNameArea.getText();
                
                if(nume.isEmpty() || nume.trim().isEmpty()) {
                
                    JOptionPane.showMessageDialog(null, "Introduceti un nume valid.");
                    return;
                }
                
                userNameArea.setEditable(false);

                try {
                    sc = new Socket("127.0.0.1", 1408);
                    InputStreamReader isr = new InputStreamReader(sc.getInputStream());
                    bf = new BufferedReader(isr);
                    pw = new PrintWriter(sc.getOutputStream());
                    pw.println(nume + "#S-a conectat. :)#Conectat"); //Trimite serverului semnal ca s-a conectat
                    pw.flush();
                    isConnected = true;

                } catch (IOException e) {

                    JOptionPane.showMessageDialog(null, "Client - Conexiune imposibila! Incercati mai tarziu. \n");
                    userNameArea.setEditable(true);
                    return;
                }

                firClient();

            } else {

                JOptionPane.showMessageDialog(null, "Client - V-ati conectat deja. \n");
            }
        }
    }//GEN-LAST:event_connectButtonActionPerformed

    private void disconnectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disconnectButtonActionPerformed
        
       synchronized(clientGUI.this) {
            
           try{
               isConnected = false;
               pw.println(nume + "# #Deconectat"); //Trimite serverului semnalul pt deconectare
               pw.flush();

           } catch (Exception e) {
           
               JOptionPane.showMessageDialog(null, "Client - Nu s-a putut realiza deconectarea de la server.\n");
               isConnected = true;
           }

           deconecteaza(); 
       }
    }//GEN-LAST:event_disconnectButtonActionPerformed

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed

        String user = (String) onlineUserList.getSelectedValue();
        
        if(user == null) {
            
            JOptionPane.showMessageDialog(null, "Va rugam selectati un utilizator.");
            inputTextArea.setText("");
            inputTextArea.requestFocus();
            return;
        }
        
        if (!(inputTextArea.getText()).equals("")) {
            
            try {
                
               pw.println(nume + "#" + inputTextArea.getText() + "@" + user + "#" + "MsgUnul");
               pw.flush();
               
            } catch (Exception e) {
                
                 JOptionPane.showMessageDialog(null, "Client - Mesajul nu a fost trimis. \n");
            }
        }

        inputTextArea.setText("");
        inputTextArea.requestFocus();

    }//GEN-LAST:event_sendButtonActionPerformed

    private void inputTextAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputTextAreaKeyPressed
       
        if(evt.getKeyChar() == '\n')
            sendButtonActionPerformed(null);
    }//GEN-LAST:event_inputTextAreaKeyPressed

    private void inputTextAreaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputTextAreaKeyTyped
    
        if(evt.getKeyChar() == '\n')
            inputTextArea.setText("");
    }//GEN-LAST:event_inputTextAreaKeyTyped

    private void userNameAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userNameAreaKeyPressed

        if(evt.getKeyChar() == '\n')
            connectButtonActionPerformed(null);
    }//GEN-LAST:event_userNameAreaKeyPressed

    private void sendAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendAllButtonActionPerformed

        if (!(inputTextArea.getText()).equals("")) {
            
            try {
                
               pw.println(nume + "#" + inputTextArea.getText() + "#" + "Msg");
               pw.flush();
               
            } catch (Exception e) {
                
                 JOptionPane.showMessageDialog(null, "Client - Mesajul nu a fost trimis. \n");
            }
        }

        inputTextArea.setText("");
        inputTextArea.requestFocus();
    }//GEN-LAST:event_sendAllButtonActionPerformed

    
    /*public static void main(String args[]) {
        /* Set the Nimbus look and feel 
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(clientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new clientGUI().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea chatTextArea;
    private javax.swing.JButton connectButton;
    private javax.swing.JButton disconnectButton;
    private javax.swing.JTextArea inputTextArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JList onlineUserList;
    private javax.swing.JButton sendAllButton;
    private javax.swing.JButton sendButton;
    private javax.swing.JTextField userNameArea;
    // End of variables declaration//GEN-END:variables
}
