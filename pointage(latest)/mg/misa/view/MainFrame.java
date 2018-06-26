package mg.misa.view;

import mg.misa.view.Pointage.PointageAutoView;
import mg.misa.view.Pointage.PointageManuelView;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JTabbedPane jTabbedPane;
    private PointageAutoView pointageAutoView;
    private PointageManuelView pointageManuelView;
    private StatistiqueView statistiqueView;
    private EmployeView employeView;
    private CongeView congeView;
    private AutorisationView autorisationView;

    public MainFrame(){
        initComponents();
    }

    private void initComponents(){
        jTabbedPane = new JTabbedPane();
        pointageAutoView = new PointageAutoView();
        pointageManuelView = new PointageManuelView();
        statistiqueView = new StatistiqueView();
        employeView = new EmployeView();
        congeView = new CongeView();
        autorisationView = new AutorisationView();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 700));
        setMinimumSize(new Dimension(800,700));
        setResizable(true);

        jTabbedPane.addTab("Pointage statique", pointageAutoView);
        jTabbedPane.addTab("Pointage Manuel", pointageManuelView);
        jTabbedPane.addTab("Statistiques", statistiqueView);
        jTabbedPane.addTab("Gestion des employes", employeView);
        jTabbedPane.addTab("Gestion des conges", congeView);
        jTabbedPane.addTab("Gestion des autorisations", autorisationView);

        createLayout();
        pack();
    }

    private void createLayout(){
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jTabbedPane, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jTabbedPane, GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
        );

    }
}
