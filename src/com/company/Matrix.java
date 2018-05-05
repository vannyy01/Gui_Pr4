package com.company;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JPanel;

public class Matrix extends JFrame {
    private JTextArea textAreaTracery;
    private JSpinner spinnerSize;
    private JPanel panel;
    private JRadioButton radioButton270;
    private JRadioButton radioButton180;
    private JRadioButton radioButton90;
    private JLabel label_1;
    private JLabel label_2;
    private JLabel label_3;
    private JLabel label_4;
    private JButton reverseButton;
    private JTextArea FirstMatrix;
    private JLabel label_6;
    private JLabel lable_5;
    private ButtonGroup buttonGroup;

    private Matrix() {
        this.initialize();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Matrix window = new Matrix();
                window.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private void initialize() {
        this.setTitle("Matrix");
        setBounds(100, 100, 600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        spinnerSize.setModel(new SpinnerNumberModel(2, 2, 8, 1));

        radioButton90.setActionCommand("90");
        radioButton90.setSelected(true);
        radioButton180.setActionCommand("180");
        radioButton270.setActionCommand("270");

        reverseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = (Integer) spinnerSize.getValue();
                String first = "";
                String second = "";
                String[][] matrix = new String[size][size];
                int t = 1;

                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[i].length; j++) {
                        //Random rnd = new Random(System.currentTimeMillis() + 1000);
                        //String y = matrix[i][j] = Integer.toString(rnd.nextInt(100));
                        String y = matrix[i][j] = Integer.toString(t);
                        t++;
                    }
                }
                for (String srI[] : matrix) {
                    for (String srJ : srI) {
                        first += srJ + " ";
                    }
                    first += "\n";
                }

                String buttonValue = buttonGroup.getSelection().getActionCommand();
                try {
                    System.out.print(buttonValue);
                    if (buttonValue.isEmpty()) {
                        textAreaTracery.setText("Вибетріть кут");
                        throw new Exception("Did not select the corner");
                    }
                } catch (Exception x) {
                    x.printStackTrace();
                }
                FirstMatrix.setText(first);
                // System.out.print(Integer.valueOf(buttonGroup.getSelection().getActionCommand()));
                matrix = transMatrix(matrix, Integer.valueOf(buttonValue), true);
                for (String srI[] : matrix) {
                    for (String srJ : srI) {
                        second += srJ + " ";
                    }
                    second += "\n";
                }
                textAreaTracery.setText(second);
            }
        });
        setContentPane(panel);
    }

    public static String[][] transMatrix(String matrix[][], int corner, boolean show) {
        if (matrix.length < 1 || matrix[0].length < matrix.length) {
            throw new ArrayStoreException("Too short array");
        }
        int M_len = matrix[0].length;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i].length < M_len) {
                throw new ArrayStoreException("Doesn`t enough arguments in the matrix");
            }
        }
        String[][] retArr = new String[M_len][matrix.length];
        if (corner == 90) {
            int retArrI = 0;
            int retArrJ = matrix.length - 1;
            for (String[] srI : matrix) {
                for (String srJ : srI) {
                    retArr[retArrI++][retArrJ] = srJ;
                }
                retArrI = 0;
                retArrJ--;
            }
        }
        if (corner == 180) {
            int retArrI = matrix.length - 1;
            int retArrJ = matrix.length - 1;
            for (String[] srI : matrix) {
                for (String srJ : srI) {
                    retArr[retArrI][retArrJ--] = srJ;
                }
                retArrI--;
                retArrJ = matrix.length - 1;
            }
        }
        if (corner == 270) {
            int retArrI = matrix.length - 1;
            int retArrJ = 0;
            for (String[] srI : matrix) {
                for (String srJ : srI) {
                    retArr[retArrI--][retArrJ] = srJ;
                }
                retArrI = matrix.length - 1;
                retArrJ++;
            }
        }
        if (show) {
            for (int i = 0; i < retArr.length; i++) {
                for (int j = 0; j < retArr[i].length; j++) {
                    System.out.print(retArr[i][j] + " ");
                }
                System.out.print(System.lineSeparator());
            }
            System.out.print(System.lineSeparator());
        }
        return retArr;
    }

    private static String[][] transMatrixRec(String matrix[][], int corner, boolean show) {
        if (matrix.length < 1 || matrix[0].length < matrix.length) {
            throw new ArrayStoreException("Too short array");
        }
        int M_len = matrix[0].length;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i].length < M_len) {
                throw new ArrayStoreException("Doesn`t enough arguments in the matrix");
            }
        }
        String[][] retArr = new String[M_len][matrix.length];
        if (corner == 90) {
            retArr = transMatrix(matrix, 90, show);
        }
        if (corner == 180) {
            retArr = transMatrix(transMatrix(matrix, 90, false), 90, show);
        }
        if (corner == 270) {
            retArr = transMatrix(transMatrix(transMatrix(matrix, 90, false), 90, false), 90, show);
        }
        return retArr;
    }

}
