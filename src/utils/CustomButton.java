package utils;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomButton extends JButton {
    private ImageIcon regularIcon;
    private ImageIcon hoveringIcon;
    private ImageIcon clickingIcon;

    //Construtor que está atribuindo as imagens do botão raposinha 
    public CustomButton(ImageIcon regularIcon, ImageIcon hoveringIcon, ImageIcon clickingIcon) {
        this.regularIcon = regularIcon;
        this.hoveringIcon = hoveringIcon;
        this.clickingIcon = clickingIcon;

        setIcon(regularIcon);
        
        //remove a borda padrão
        setBorderPainted(false);
        
        //desativa o preenchido automático do botçao, ele terá apenas o tamanho da imagem 
        setContentAreaFilled(false);
        
        //habilita a versão mãozinha do mouse
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        //adiciona um ouvinte de eventos para o mouse
        addMouseListener(new MouseAdapter() {
            @Override
            //define a imagem raposinha correspondente para quando estiver clicado
            public void mousePressed(MouseEvent e) {
                setIcon(clickingIcon);
            }

            @Override
            //lógica para depois que o botão é clicado
            public void mouseReleased(MouseEvent e) {
            	//se o mouse ainda estiver em cima do botão, ele continua com a imagem raposinha acesa
                if (containsMouse(e.getX(), e.getY())) {
                    setIcon(hoveringIcon);
                // senão, define a imagem da raposinha apagada
                } else {
                    setIcon(regularIcon);
                }
            }
            
            @Override
            //verifica se o mouse está em cima do botão
            public void mouseEntered(MouseEvent e) {
                setIcon(hoveringIcon);
            }

            @Override
            //define a imagem raposinha correspondente para quando nao tem cursor no botão
            public void mouseExited(MouseEvent e) {
                setIcon(regularIcon);
            }
        });
    }
    
    //verifica se o ponto definido pelas coordenadas x e y está dentro dos limites do botão.
    private boolean containsMouse(int x, int y) {
        return x >= 0 && x <= getWidth() && y >= 0 && y <= getHeight();
    }
}