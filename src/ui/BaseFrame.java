package ui;

import javax.swing.*;
import java.awt.*;


public abstract class BaseFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public BaseFrame() {
        super("cerrado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 800);
        
        //centraliza o frame na tela do monitor
        setLocationRelativeTo(null);
        try {
        	//cria a borda do frame
            getRootPane().setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 10));
        } catch (Exception e) {
            e.printStackTrace();
        }
        initializeComponents();
    }

   
    public BaseFrame(Image background) {
    	//chama o construtor padrão para as demais configurações do frame
        this();
        //um novo painel (JPanel) é definido como o conteúdo da janela
        setContentPane(new JPanel() {
      
			private static final long serialVersionUID = 1L;

			@Override
			//Esse método é chamado pelo sistema de gráficos Swing para desenhar o conteúdo do painel. 
			//Ele recebe um objeto Graphics chamado g, 
			//que é usado para desenhar elementos gráficos na tela.
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //desenha a imagem no fundo do frame
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        });
    }
    
    //método geral para os componentes da tela
    public abstract void initializeComponents();





}