/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

import bingo.network.ClientManager;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import models.Card;

/**
 *
 * @author 14023691
 */
public class TelaJogo extends bingo.MyJFrame implements Tela{
    private final ButtonWithLoader[][] btns;
    private final List<Integer> marks = new ArrayList<>();
    private ClientManager clientManager;

    /**
     * Creates new form TelaJogo
     */
    public TelaJogo(ClientManager clientManagaer) {
        this.clientManager = clientManagaer;

        initComponents();
        btns = new ButtonWithLoader[][] {
            {jButton6, jButton7, jButton8, jButton9, jButton10},
            {jButton11,jButton12,jButton13,jButton14,jButton15},
            {jButton16,jButton17,null,     jButton18,jButton19},
            {jButton20,jButton21,jButton22,jButton23,jButton24},
            {jButton25,jButton31,jButton27,jButton28,jButton29},
        };
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton6 = new bingo.ButtonWithLoader();
        jButton7 = new bingo.ButtonWithLoader();
        jButton8 = new bingo.ButtonWithLoader();
        jButton9 = new bingo.ButtonWithLoader();
        jButton10 = new bingo.ButtonWithLoader();
        jButton11 = new bingo.ButtonWithLoader();
        jButton12 = new bingo.ButtonWithLoader();
        jButton13 = new bingo.ButtonWithLoader();
        jButton14 = new bingo.ButtonWithLoader();
        jButton15 = new bingo.ButtonWithLoader();
        jButton16 = new bingo.ButtonWithLoader();
        jButton17 = new bingo.ButtonWithLoader();
        jButton18 = new bingo.ButtonWithLoader();
        jButton19 = new bingo.ButtonWithLoader();
        jButton20 = new bingo.ButtonWithLoader();
        jButton21 = new bingo.ButtonWithLoader();
        jButton22 = new bingo.ButtonWithLoader();
        jButton23 = new bingo.ButtonWithLoader();
        jButton24 = new bingo.ButtonWithLoader();
        jButton25 = new bingo.ButtonWithLoader();
        jButton26 = new bingo.ButtonWithLoader();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jButton27 = new bingo.ButtonWithLoader();
        jButton28 = new bingo.ButtonWithLoader();
        jButton29 = new bingo.ButtonWithLoader();
        jButton30 = new bingo.ButtonWithLoader();
        jButton31 = new bingo.ButtonWithLoader();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton6.setFocusPainted(false);

        jButton7.setFocusPainted(false);

        jButton8.setFocusPainted(false);

        jButton9.setFocusPainted(false);

        jButton10.setFocusPainted(false);

        jButton11.setFocusPainted(false);

        jButton12.setFocusPainted(false);

        jButton13.setFocusPainted(false);

        jButton14.setFocusPainted(false);

        jButton15.setFocusPainted(false);

        jButton16.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton16.setToolTipText("");
        jButton16.setFocusPainted(false);

        jButton17.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton17.setToolTipText("");
        jButton17.setFocusPainted(false);

        jButton18.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton18.setToolTipText("");
        jButton18.setFocusPainted(false);

        jButton19.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton19.setToolTipText("");
        jButton19.setFocusPainted(false);

        jButton20.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton20.setToolTipText("");
        jButton20.setFocusPainted(false);

        jButton21.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton21.setFocusPainted(false);

        jButton22.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton22.setToolTipText("");
        jButton22.setFocusPainted(false);

        jButton23.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton23.setFocusPainted(false);

        jButton24.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton24.setFocusPainted(false);

        jButton25.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton25.setFocusPainted(false);

        jButton26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton26.setText("BINGO!");
        jButton26.setFocusPainted(false);
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(2);
        jTextArea1.setEnabled(false);
        jScrollPane2.setViewportView(jTextArea1);

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton27.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton27.setToolTipText("");
        jButton27.setFocusPainted(false);

        jButton28.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton28.setFocusPainted(false);

        jButton29.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton29.setFocusPainted(false);

        jButton30.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton30.setFocusPainted(false);

        jButton31.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton31.setFocusPainted(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton23, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        clientManager.sendMessage(new bingo.responses.Bingo(marks));
    }//GEN-LAST:event_jButton26ActionPerformed

    /**
     * @param args the command line arguments
     */
    @Override
    public void showGenericError() {
        JOptionPane.showMessageDialog(null, "Algo muito errado deu errado!");
    }

    public void setCard(Card card) {
        jTextArea1.setText("");
        for (int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if (btns[i][j] != null) {
                    final Integer n = card.getNumber(i, j);
                    btns[i][j].setText(n.toString());

                    btns[i][j].addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (marks.contains(n)) {
                                marks.remove(n);
                            } else {
                                marks.add(n);
                            }
                        }
                    });
                }
            }
        }
    }

    public void addNextNumber(Integer n){
        String prev = jTextArea1.getText();

        if (prev.isEmpty()) {
            jTextArea1.setText(n.toString());
        } else {
            jTextArea1.setText(prev + ", " + n.toString());
        }
    }

    public void setTime(Integer time) {
        jLabel3.setText(time.toString());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private bingo.ButtonWithLoader jButton10;
    private bingo.ButtonWithLoader jButton11;
    private bingo.ButtonWithLoader jButton12;
    private bingo.ButtonWithLoader jButton13;
    private bingo.ButtonWithLoader jButton14;
    private bingo.ButtonWithLoader jButton15;
    private bingo.ButtonWithLoader jButton16;
    private bingo.ButtonWithLoader jButton17;
    private bingo.ButtonWithLoader jButton18;
    private bingo.ButtonWithLoader jButton19;
    private bingo.ButtonWithLoader jButton20;
    private bingo.ButtonWithLoader jButton21;
    private bingo.ButtonWithLoader jButton22;
    private bingo.ButtonWithLoader jButton23;
    private bingo.ButtonWithLoader jButton24;
    private bingo.ButtonWithLoader jButton25;
    private bingo.ButtonWithLoader jButton26;
    private bingo.ButtonWithLoader jButton27;
    private bingo.ButtonWithLoader jButton28;
    private bingo.ButtonWithLoader jButton29;
    private bingo.ButtonWithLoader jButton30;
    private bingo.ButtonWithLoader jButton31;
    private bingo.ButtonWithLoader jButton6;
    private bingo.ButtonWithLoader jButton7;
    private bingo.ButtonWithLoader jButton8;
    private bingo.ButtonWithLoader jButton9;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

    public void warnInvalidBingo() {
        JOptionPane.showMessageDialog(null, "Fanfarrero!");
    }
}
