
package br.com.projetoorientadoobjeto.form;

import br.com.projetoorientadoobjeto.controller.PessoaController;


import br.com.projetoorientadoobjeto.dao.PessoaDao;
import br.com.projetoorientadoobjeto.modelo.Pessoa;
import java.sql.SQLException;
import java.util.Map;
import javax.swing.JOptionPane;


public class FormPessoa extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FormPessoa.class.getName());

    
    private PessoaController pessoaController;
    private PessoaDao pessoaDao;
    
    public FormPessoa() {
        initComponents();
        esconderCampoErro();
        
        this.pessoaController = new PessoaController();
        this.pessoaDao = new PessoaDao();
        
    }
    private void esconderCampoErro(){
        jLabelErroNome.setVisible(false);
        jLabelErroDataNascimento.setVisible(false);
        jLabelErroEmail.setVisible(false);
        
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        JtfNome = new javax.swing.JTextField();
        jtfEmail = new javax.swing.JTextField();
        jcbTipoCadastro = new javax.swing.JComboBox<>();
        jbtnCadastrar = new javax.swing.JButton();
        jbtnLimpar = new javax.swing.JButton();
        jLabelErroNome = new javax.swing.JLabel();
        jLabelErroDataNascimento = new javax.swing.JLabel();
        jLabelErroEmail = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jcbGenero1 = new javax.swing.JComboBox<>();
        jtfWpp = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jtfDtNascimento = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        jLabel1.setText("CADASTRO DE PESSOA");

        jLabel2.setText("NOME:");

        jLabel3.setText("DATA DE NASCIMENTO:");

        jLabel4.setText("E-MAIL:");

        jLabel5.setText("GÊNERO:");

        JtfNome.addActionListener(this::JtfNomeActionPerformed);

        jcbTipoCadastro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Aluno", "Professor" }));
        jcbTipoCadastro.addActionListener(this::jcbTipoCadastroActionPerformed);

        jbtnCadastrar.setText("CADASTRAR");
        jbtnCadastrar.addActionListener(this::jbtnCadastrarActionPerformed);

        jbtnLimpar.setText("LIMPAR");
        jbtnLimpar.addActionListener(this::jbtnLimparActionPerformed);

        jLabelErroNome.setFont(new java.awt.Font("sansserif", 1, 10)); // NOI18N
        jLabelErroNome.setForeground(new java.awt.Color(255, 51, 51));
        jLabelErroNome.setText("Por favor, preencha o campo");

        jLabelErroDataNascimento.setFont(new java.awt.Font("sansserif", 1, 10)); // NOI18N
        jLabelErroDataNascimento.setForeground(new java.awt.Color(255, 51, 51));
        jLabelErroDataNascimento.setText("Por favor, preencha o campo");

        jLabelErroEmail.setFont(new java.awt.Font("sansserif", 1, 10)); // NOI18N
        jLabelErroEmail.setForeground(new java.awt.Color(255, 51, 51));
        jLabelErroEmail.setText("Por favor, preencha o campo");

        jLabel6.setText("Tipo de Cadastro:");

        jcbGenero1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Masculino", "Feminino" }));

        jtfWpp.setText("Whatsapp");

        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        jtfDtNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbtnCadastrar)
                                .addGap(18, 18, 18)
                                .addComponent(jbtnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jcbTipoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jtfWpp))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jcbGenero1, 0, 282, Short.MAX_VALUE)
                                        .addComponent(jTextField1))))
                            .addGap(189, 189, 189))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(2, 2, 2)
                                    .addComponent(jtfDtNascimento))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(JtfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelErroNome, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelErroDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelErroEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(612, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(JtfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelErroNome))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabelErroDataNascimento)
                    .addComponent(jtfDtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelErroEmail))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfWpp)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jcbGenero1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jcbTipoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnCadastrar)
                    .addComponent(jbtnLimpar))
                .addContainerGap(403, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnLimparActionPerformed
       JtfNome.setText("");
       jtfDtNascimento.setText("");
       jtfEmail.setText("");
       jcbTipoCadastro.setSelectedIndex(0);
       jcbTipoCadastro.setSelectedIndex(0);
       JtfNome.requestFocus();
       
    }//GEN-LAST:event_jbtnLimparActionPerformed

    private void JtfNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JtfNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JtfNomeActionPerformed

    private void jbtnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCadastrarActionPerformed

        String nome = JtfNome.getText();
        String datanascimento = jtfDtNascimento.getText();
        String email = jtfEmail.getText();
        String wpp = jtfWpp.getText();
        Integer genero = jcbGenero1.getSelectedIndex();
        Integer tipoCadastro = jcbTipoCadastro.getSelectedIndex();

        Map<String, String> mapErros = pessoaController.validarCadastro(nome, datanascimento, email, wpp, genero, tipoCadastro);

        System.out.println(mapErros);
        if(mapErros.isEmpty()){

            try{
                Pessoa pessoa = new Pessoa();
                pessoa.setCpf("");
                pessoa.setNome(nome);
                pessoa.setEmail(email);

                pessoa.setGenero(genero);
                pessoa.setdtNascimento(datanascimento);
                pessoa.settipoCadastro(tipoCadastro);
                pessoa.setFoto(null);
                pessoa.setWpp(wpp);


                pessoaDao.salvar(pessoa);
                
                JOptionPane.showMessageDialog(this,"Cadastro realizado com sucesso!");
                
                JtfNome.setText("");
                jtfDtNascimento.setText("");
                jtfEmail.setText("");
                jTextField1.setText("");
                jcbGenero1.setSelectedIndex(0);
                jcbTipoCadastro.setSelectedIndex(0);
                JtfNome.requestFocus();


            } catch (SQLException sqle){
                JOptionPane.showMessageDialog(
                this, 
                "Erro de BD/SQL: " + sqle.getMessage());

            }
        } else {
            JOptionPane.showMessageDialog(
                this, 
                "Erro. Campos obrigatórios não preenchidos.");
        }
 
    }//GEN-LAST:event_jbtnCadastrarActionPerformed

    private void jcbTipoCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbTipoCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbTipoCadastroActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed


    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(() -> new FormPessoa().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField JtfNome;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelErroDataNascimento;
    private javax.swing.JLabel jLabelErroEmail;
    private javax.swing.JLabel jLabelErroNome;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton jbtnCadastrar;
    private javax.swing.JButton jbtnLimpar;
    private javax.swing.JComboBox<String> jcbGenero1;
    private javax.swing.JComboBox<String> jcbTipoCadastro;
    private javax.swing.JFormattedTextField jtfDtNascimento;
    private javax.swing.JTextField jtfEmail;
    private javax.swing.JLabel jtfWpp;
    // End of variables declaration//GEN-END:variables
}
