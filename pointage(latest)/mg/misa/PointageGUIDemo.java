package mg.misa;

import mg.misa.view.MainFrame;

import static javax.swing.SwingUtilities.invokeLater;

public class PointageGUIDemo {
    public static void main(String[] arg){
        invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}
