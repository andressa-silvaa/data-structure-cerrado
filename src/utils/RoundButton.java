package utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JButton;

public class RoundButton extends JButton {
    private int arcRadius = 50; // Raio de curvatura do botão

    //Construtor recebe o nome do botão e o cria.
    public RoundButton(String label) {
        super(label);      
        //remove a cor de fundo da área de conteúdo, tornando a área interna transparente
        setContentAreaFilled(false);
    }

    protected void paintComponent(Graphics g) {
    	//verifica se o modelo do botão está no estado "pressionado"
        if (getModel().isArmed()) {
            g.setColor(new Color(0xFFDEAD)); // Cor de fundo #D8B872 quando pressionado
        } else {
            g.setColor(new Color(0xD8B872)); // Cor de fundo #FFDEAD padrão
        }
        //um retângulo arredondado é desenhado para representar a forma do botão
        g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, arcRadius, arcRadius); // Use g.fillRoundRect com o raio de curvatura
        //desenha o conteúdo interno do botão, como o texto e os ícones.
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
    	//Verifica se o objeto g é uma instância de Graphics2D
        if (g instanceof Graphics2D) {
        	//"g" é convertido para Graphics2D e atribuído a g2d
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.DARK_GRAY); // Define a cor da borda.
            Stroke oldStroke = g2d.getStroke(); // Salva a espessura da linha da borda atual
            g2d.setStroke(new BasicStroke(3.0f)); // Define uma nova espessura para a linha da borda(3 pixels)
            //desenha a borda
            g2d.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, arcRadius, arcRadius);
            g2d.setStroke(oldStroke); // Restaura a espessura original da linha
        }
    }

    // Usado para evitar que o botão tenha um contorno quando obtém foco
    public boolean isFocusTraversable() {
        return false;
    }
}