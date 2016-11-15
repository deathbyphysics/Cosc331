/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashSet;
import javax.swing.Timer;

/**
 *
 * @author jthha
 */
public class ChatRoomHost extends javax.swing.JFrame implements ActionListener {

    /**
     * Creates new form ChatRoomHost
     */
    private DatagramSocket socket;
    private HashSet<SocketAddress> group;
    private InetAddress addr;
    private String IP;
    private boolean firstTime;
    private final Timer timer = new Timer(1000,this);
    
    public ChatRoomHost() {
        initComponents();
        run();
    }
    public void run(){
        try{
            firstTime = true;
        
            group = new HashSet<>();
            int port = 4000;
            socket = new DatagramSocket(port);
        
            addr = InetAddress.getLocalHost();
            String hostName = addr.getHostName();
            String hostAddress = addr.getHostAddress();
            lblIPAddress.setText(hostAddress);
            lblHostName.setText(hostName);
            
            txtAreaClientActivity.append("IP Address = " + hostAddress + "\n");
            txtAreaClientActivity.append("Port# = " + port + "\n");
            txtAreaClientActivity.append("Host name: " + hostName + "\n");
            txtAreaClientActivity.append("\nClient Activity\n" + "\n");
            timer.start();
            
            socket.setSoTimeout(500);
        }
        catch(SocketException e){
            System.out.println("we fucked up1");
        }
        catch(UnknownHostException e){
            System.out.println("we fucked up2");
        }
        catch(IOException e){
            System.out.println("we fucked up3");
            
        }
    }
    
    public void actionPerformed(ActionEvent e){
        sendAndReceive();
    }
    
    private void sendAndReceive(){
        try{
            while(true){
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
                socket.receive(packet);
                firstTime = group.add(packet.getSocketAddress());
                txtAreaClientActivity.append("Receiving-->" + packet.getSocketAddress() + "\n");
                for(SocketAddress s: group){
                    txtAreaClientActivity.append("Sending-->" + s + "\n");
                    DatagramPacket p = new DatagramPacket(buffer,1024,s);
                    socket.send(p);
                }
            }
        }
        catch(SocketTimeoutException e){
            
        }
        catch(SocketException e){
            System.out.println(e);
        }
        catch(IOException e){
            System.out.println(e);
            
        }
            
        
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaClientActivity = new javax.swing.JTextArea();
        lblIPLable = new javax.swing.JLabel();
        lblIPAddress = new javax.swing.JLabel();
        lblHostNameLable = new javax.swing.JLabel();
        lblHostName = new javax.swing.JLabel();
        lblClientActivity = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtAreaClientActivity.setEditable(false);
        txtAreaClientActivity.setColumns(20);
        txtAreaClientActivity.setRows(5);
        jScrollPane1.setViewportView(txtAreaClientActivity);

        lblIPLable.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        lblIPLable.setText("IP Address:");

        lblIPAddress.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        lblIPAddress.setText("jLabel2");

        lblHostNameLable.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        lblHostNameLable.setText("Host Name: ");

        lblHostName.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        lblHostName.setText("jLabel1");

        lblClientActivity.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        lblClientActivity.setText("Client Activity");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblIPLable)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblIPAddress))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblHostNameLable)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblHostName)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(lblClientActivity)
                .addContainerGap(162, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIPLable)
                    .addComponent(lblIPAddress))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHostNameLable)
                    .addComponent(lblHostName))
                .addGap(15, 15, 15)
                .addComponent(lblClientActivity)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ChatRoomHost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatRoomHost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatRoomHost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatRoomHost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatRoomHost().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblClientActivity;
    private javax.swing.JLabel lblHostName;
    private javax.swing.JLabel lblHostNameLable;
    private javax.swing.JLabel lblIPAddress;
    private javax.swing.JLabel lblIPLable;
    private javax.swing.JTextArea txtAreaClientActivity;
    // End of variables declaration//GEN-END:variables
}
