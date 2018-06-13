import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelListener;
import java.util.*;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

class GUI {
    private JFrame frame;
    private JPanel board;
    private JLabel info;
    private JRadioButton periodical;
    private JTextField grainNumber;

    private JTextField anySizeX, anySizeY;

    private JButton[][] tab;
    private JButton show;
    private JButton reset;
    private JButton confirm;
    private JRadioButton radio1;
    private JRadioButton radio2;
    private JRadioButton radio3;
    private JRadioButton radio4;
    private Controller controller;
    private int n, m;
    private int global;
    private Map<Integer, Integer> ID = new HashMap<>();
    private Map<Integer, Color> ID_Color = new HashMap<>();
    private List<ColorRGB> colorRGBList = new LinkedList<>();

    private Random generator = new Random();

    private MouseListener mouseListener = new MouseListener() {

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub
            String command = ((JButton) e.getSource()).getActionCommand();
            Integer pom = Integer.parseInt(command);
            int a = 0, b = 0;
            int counter = 0;
            int i, j;
            for (i = 0; i < n; i++) {
                for (j = 0; j < m; j++) {
                    if (counter == pom) {
                        a = i;
                        b = j;
                    }
                    counter++;
                }
            }

            System.out.println(controller.energy[a][b] + " " + controller.moore(a, b, true));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub

        }
    };


    GUI()//konstruktor GUI
    {
        frame = new JFrame("Monte Carlo Grain Growth");
        JPanel control = new JPanel();
        board = new JPanel();

        JLabel canvasSizeInfo = new JLabel("Board size");
        radio1 = new JRadioButton("25 x 25");
        radio2 = new JRadioButton("50 x 50");
        radio3 = new JRadioButton("100 x 100");
        radio4 = new JRadioButton("150 x 150");
        anySizeX = new JTextField("50");
        anySizeY = new JTextField("50");
        ButtonGroup buttonGroup = new ButtonGroup();
        show = new JButton("Start");
        reset = new JButton("Reset");

        JLabel embryosCreationInfo = new JLabel("Embryos creation");
        grainNumber = new JTextField("10");
        JLabel textField = new JLabel("n:");
        confirm = new JButton("Draw");

        JLabel periodicityInfo = new JLabel("Periodicity");
        ButtonGroup periodicityButtonGroup = new ButtonGroup();
        periodical = new JRadioButton("Periodical");
        JRadioButton nonPeriodical = new JRadioButton("Non-periodical");

        JLabel proximityInfo = new JLabel("Proximity");
        JRadioButton moore = new JRadioButton("Moore");

        info = new JLabel("Information");

        global = 0;

        canvasSizeInfo.setSize(200, 20);
        canvasSizeInfo.setLocation(450, 20);
        canvasSizeInfo.setFont(new Font(null, Font.BOLD, 14));
        radio1.setSize(100, 20);
        radio1.setLocation(450, 40);
        radio2.setSize(100, 20);
        radio2.setLocation(450, 60);
        radio3.setSize(100, 20);
        radio3.setLocation(450, 80);
        radio4.setSize(100, 20);
        radio4.setLocation(450, 100);
        anySizeX.setSize(60, 20);
        anySizeX.setLocation(450, 120);
        anySizeY.setSize(60, 20);
        anySizeY.setLocation(510, 120);
        buttonGroup.add(radio1);
        buttonGroup.add(radio2);
        buttonGroup.add(radio3);
        buttonGroup.add(radio4);
        show.setSize(100, 50);
        show.setLocation(580, 20);

        // TODO Auto-generated method stub
        ActionListener actionStart = new ActionListener() {
            int a = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                a = Integer.parseInt(grainNumber.getText());
                for (int i = 0; i < a; i++) {
                    global++;
                    ID.put(global, global);
                    controller.ID.put(global, global);
                    Color color = randColor(colorRGBList);
                    ID_Color.put(global, color);
                }
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        controller.board[i][j] = generator.nextInt(a) + 1;
                        tab[i][j].setBackground(ID_Color.get(ID.get(controller.board[i][j])));
                    }
                }
            }
        };
        confirm.addActionListener(actionStart);
        confirm.setEnabled(false);

        // TODO Auto-generated method stub
        ActionListener actionCreate = e -> {
            // TODO Auto-generated method stub
            if (radio1.isSelected()) {
                start(25, 25);
            } else if (radio2.isSelected()) {
                start(50, 50);
            } else if (radio3.isSelected()) {
                start(100, 100);
            } else if (radio4.isSelected()) {
                start(150, 150);
            } else {
                n = Integer.parseInt(anySizeX.getText());
                m = Integer.parseInt(anySizeY.getText());
                start(n, m);
            }
            show.setEnabled(false);
            board.setVisible(true);
            radio1.setEnabled(false);
            radio2.setEnabled(false);
            radio3.setEnabled(false);
            radio4.setEnabled(false);
            reset.setEnabled(true);
            confirm.setEnabled(true);
        };
        show.addActionListener(actionCreate);
        reset.setSize(100, 50);
        reset.setLocation(580, 70);
        // TODO Auto-generated method stub
        ActionListener actionReset = e -> {
            // TODO Auto-generated method stub
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    controller.board[i][j] = 0;
                    tab[i][j].setBackground(Color.WHITE);
                }
            }
            global = 0;
            info.setText("-- Information --");
        };
        reset.addActionListener(actionReset);
        reset.setEnabled(false);

        embryosCreationInfo.setSize(200, 20);
        embryosCreationInfo.setLocation(450, 150);
        embryosCreationInfo.setFont(new Font(null, Font.BOLD, 14));
        grainNumber.setSize(60, 20);
        grainNumber.setLocation(470, 170);
        textField.setSize(60, 20);
        textField.setLocation(450, 170);
        confirm.setSize(100, 20);
        confirm.setLocation(450, 210);

        periodicityInfo.setSize(200, 20);
        periodicityInfo.setLocation(450, 280);
        periodicityInfo.setFont(new Font(null, Font.BOLD, 14));
        periodical.setSize(120, 20);
        periodical.setLocation(450, 300);
        periodical.doClick();
        nonPeriodical.setSize(120, 20);
        nonPeriodical.setLocation(450, 320);
        periodicityButtonGroup.add(periodical);
        periodicityButtonGroup.add(nonPeriodical);

        proximityInfo.setSize(200, 20);
        proximityInfo.setLocation(450, 350);
        proximityInfo.setFont(new Font(null, Font.BOLD, 14));
        moore.setSize(120, 20);
        moore.setLocation(450, 370);
        moore.doClick();

        info.setSize(200, 20);
        info.setLocation(50, 20);
        info.setFont(new Font(null, Font.BOLD, 14));

        control.add(canvasSizeInfo);
        control.add(embryosCreationInfo);
        control.add(periodicityInfo);
        control.add(proximityInfo);
        control.add(moore);
        control.add(periodical);
        control.add(nonPeriodical);
        control.add(grainNumber);
        control.add(radio1);
        control.add(radio2);
        control.add(radio3);
        control.add(radio4);
        control.add(show);
        control.add(reset);
        control.add(textField);
        control.add(confirm);
        control.add(info);
        control.add(anySizeX);
        control.add(anySizeY);
        control.setLayout(null);
        control.setSize(800, 1080);
        control.setLocation(1100, 0);
        board.setLayout(null);
        board.setSize(1000, 1000);
        board.setLocation(20, 20);
        //krecac kólkiem spowodujemy rozrost ziaren
        // TODO Auto-generated method stub
        //krecac kólkiem spowodujemy rozrost ziaren
        MouseWheelListener mouseWheelListener = e -> {
            // TODO Auto-generated method stub
            startCount();
        };
        board.addMouseWheelListener(mouseWheelListener);
        frame.setLayout(null);
        frame.add(control);
        frame.setSize(1920, 1080);
        frame.setLocation(0, 0);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void start(int a, int b) {
        if (a > 250) a = 250;
        if (b > 250) b = 250;
        n = a;
        m = b;
        double sizeX = 1000.0 / (double) n;
        double sizeY = 1000.0 / (double) m;
        tab = new JButton[n][m];
        controller = new Controller(n, m);
        controller.ID.put(global, global);
        ID.put(global, global);
        ID_Color.put(global, Color.WHITE);
        int helpVariable = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tab[i][j] = new JButton();
                tab[i][j].setSize((int) sizeX, (int) sizeY);
                tab[i][j].setBackground(Color.WHITE);
                tab[i][j].setLocation((int) sizeX * i, (int) sizeY * j);
                tab[i][j].setBorder(null);
                tab[i][j].addMouseListener(mouseListener);
                String StringCommand = Integer.toString(helpVariable);
                tab[i][j].setActionCommand(StringCommand);
                board.add(tab[i][j]);
                helpVariable++;
            }
        }
        board.setVisible(false);
        frame.add(board);
    }

    private Color randColor(List<ColorRGB> colorRGBList)//metoda losujaca kolor zarodka
    {
        int r = 0, g = 0, b = 0;
        ColorRGB testColor;
        boolean isExist = true;
        while (isExist) {
            isExist = false;
            r = generator.nextInt(256);
            g = generator.nextInt(256);
            b = generator.nextInt(256);
            testColor = new ColorRGB(r, g, b);
            for (ColorRGB aColorRGBList : colorRGBList) if (testColor.isEqual(aColorRGBList)) isExist = true;
        }
        Color col = new Color(r, g, b);
        colorRGBList.add(new ColorRGB(r, g, b));
        return col;
    }

    private int counter = 0;

    private void startCount() {
        long millisActualTime = System.currentTimeMillis(); // pocz?tkowy czas w milisekundach.
        boolean periodicity = periodical.isSelected();
        controller.count(Integer.parseInt(grainNumber.getText()), periodicity);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {                            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                tab[i][j].setBackground(ID_Color.get(ID.get(controller.board[i][j])));
            }
        }
        counter++;
        info.setText(String.valueOf(counter));
        long executionTime = System.currentTimeMillis() - millisActualTime; // czas wykonania programu w milisekundach.
        System.out.println("Czas startCount: " + executionTime);
    }

}
