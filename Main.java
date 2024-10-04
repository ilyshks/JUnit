package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Main extends JFrame {

    private final JTextField inputField1;
    private final JTextField inputField2;
    private final JLabel resultLabel;
    private final JButton addButton;
    private final JButton subtractButton;
    private final JButton multiplyButton;
    private final JButton divideButton;
    private final JPanel buttonPanel;

    public Main() {
        setTitle("Калькулятор");
        int initialWidth = 680;
        int initialHeight = 300;
        setSize(initialWidth, initialHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Устанавливаем минимальный размер окна
        setMinimumSize(new Dimension(initialWidth, initialHeight));


        // Создаем панель для размещения элементов
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Создаем текстовые поля для ввода данных
        inputField1 = new JTextField(10);
        inputField2 = new JTextField(10);

        inputField1.setName("inputField1");
        inputField2.setName("inputField2");

        // Добавляем метки и текстовые поля на панель
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel first = new JLabel("Число 1:");
        panel.add(first, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(inputField1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        JLabel second = new JLabel("Число 2:");
        panel.add(second, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(inputField2, gbc);

        // Создаем панель для кнопок
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));

        // Создаем кнопки для арифметических операций
        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");

        addButton.setName("+");
        subtractButton.setName("-");
        multiplyButton.setName("*");
        divideButton.setName("/");

        // Устанавливаем размер кнопок равным 1/16 размеров фрейма
        setButtonSize();

        // Добавляем кнопки на панель
        buttonPanel.add(addButton);
        buttonPanel.add(subtractButton);
        buttonPanel.add(multiplyButton);
        buttonPanel.add(divideButton);

        // Добавляем панель с кнопками на главную панель
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        panel.add(buttonPanel, gbc);

        // Создаем метку для отображения результата
        resultLabel = new JLabel("Результат: ");
        resultLabel.setName("resultLabel");

        // Добавляем метку на главную панель
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        panel.add(resultLabel, gbc);

        // Добавляем панель на фрейм
        add(panel);

        // Добавляем слушатель для отслеживания изменений размеров фрейма
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setButtonSize();
                setButtonGaps();
                setVerticalInsets(panel);
            }
        });
        Presenter presenter = new Presenter(new MyCalculator(), new View(inputField1, inputField2, resultLabel));
        // Добавляем слушатели для кнопок
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.onPlusClicked();
                //calculate("+");
            }
        });

        subtractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.onMinusClicked();
                //calculate("-");
            }
        });

        multiplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.onMultiplyClicked();
                //calculate("*");
            }
        });

        divideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.onDivideClicked();
                //calculate("/");
            }
        });
    }

    private void setButtonSize() {
        Dimension frameSize = getSize();
        int buttonWidth = frameSize.width / 16;
        int buttonHeight = frameSize.height / 16;
        Dimension buttonSize = new Dimension(buttonWidth, buttonHeight);

        addButton.setPreferredSize(buttonSize);
        subtractButton.setPreferredSize(buttonSize);
        multiplyButton.setPreferredSize(buttonSize);
        divideButton.setPreferredSize(buttonSize);
    }

    private void setButtonGaps() {
        Dimension frameSize = getSize();
        int buttonWidth = addButton.getPreferredSize().width;
        int hgap = (frameSize.width - 4 * buttonWidth) / 10;
        ((GridLayout) buttonPanel.getLayout()).setHgap(hgap);
    }

    private void setVerticalInsets(JPanel panel) {
        int verticalGap = getHeight() / 20;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(verticalGap, 5, verticalGap, 5);

        panel.removeAll();
        panel.setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(new JLabel("Число 1:"), gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(inputField1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(new JLabel("Число 2:"), gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(inputField2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        panel.add(buttonPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        panel.add(resultLabel, gbc);

        panel.revalidate();
        panel.repaint();
    }

    private void calculate(String operation) {
        try {
            double num1 = Double.parseDouble(inputField1.getText());
            double num2 = Double.parseDouble(inputField2.getText());
            double result = 0;

            switch (operation) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        resultLabel.setText("Результат: Ошибка деления на ноль");
                        return;
                    }
                    break;
            }

            resultLabel.setText("Результат: " + result);
        } catch (NumberFormatException e) {
            resultLabel.setText("Результат: Ошибка ввода");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}