package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CalculatorContext{
    public static class MyCalculator implements ICalculator{

        @Override
        public double sum(double a, double b) {
            return a + b;
        }

        @Override
        public double subtract(double a, double b) {
            return a - b;
        }

        @Override
        public double multiply(double a, double b) {
            return a * b;
        }

        @Override
        public double divide(double a, double b) throws ArithmeticException{
            if (Math.abs(b) < 10e-8) throw new ArithmeticException("Zero division error");
            return a / b;
        }
    }

    public static class Presenter implements ICalculatorPresenter {
        private CalculatorContext.MyCalculator calculator;
        private CalculatorContext.View view;
        public Presenter(CalculatorContext.MyCalculator calculator, CalculatorContext.View view){
            this.calculator = calculator;
            this.view = view;
        }

        public boolean checkValues(String first, String second){
            if (first.isEmpty() || second.isEmpty()){
                view.displayError("заполните все поля!");
                return false;
            }
            try {
                double a = Double.parseDouble(first);
                double b = Double.parseDouble(second);
                return true;
            } catch (NumberFormatException exception){
                view.displayError("некорректный формат ввода!");
                return false;
            }
        }

        @Override
        public void onPlusClicked() {
            String first = view.getFirstArgumentAsString();
            String second = view.getSecondArgumentAsString();
            if (checkValues(first, second)){
                double result = calculator.sum(Double.parseDouble(first), Double.parseDouble(second));
                view.printResult(result);
            }
        }

        @Override
        public void onMinusClicked() {
            String first = view.getFirstArgumentAsString();
            String second = view.getSecondArgumentAsString();
            if (checkValues(first, second)){
                double result = calculator.subtract(Double.parseDouble(first), Double.parseDouble(second));
                view.printResult(result);
            }
        }

        @Override
        public void onDivideClicked() {
            String first = view.getFirstArgumentAsString();
            String second = view.getSecondArgumentAsString();
            if (checkValues(first, second)){
                try {
                    double result = calculator.divide(Double.parseDouble(first), Double.parseDouble(second));
                    view.printResult(result);
                } catch (ArithmeticException exception) {
                    view.displayError("ошибка деления на ноль!");
                }
            }

        }

        @Override
        public void onMultiplyClicked() {
            String first = view.getFirstArgumentAsString();
            String second = view.getSecondArgumentAsString();
            if (checkValues(first, second)){
                double result = calculator.multiply(Double.parseDouble(first), Double.parseDouble(second));
                view.printResult(result);
            }
        }

        public void runGUI(){
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    view.setVisible(true);
                }
            });
        }
    }

    public static class View extends JFrame implements ICalculatorView{
        private CalculatorContext.Presenter presenter;
        private JTextField inputField1;
        private JTextField inputField2;
        private JLabel resultLabel;
        private JButton addButton;
        private JButton subtractButton;
        private JButton multiplyButton;
        private JButton divideButton;
        private JPanel buttonPanel;

        public View() {
            presenter = new CalculatorContext.Presenter(new CalculatorContext.MyCalculator(), this);
            setConfiguration();

            JPanel panel = initComponents();

            addActions(panel);
        }

        public void setPresenter(CalculatorContext.Presenter presenter){
            this.presenter = presenter;
        }

        public void setInputField1(JTextField inputField1){
            this.inputField1 = inputField1;
        }

        public void setInputField2(JTextField inputField2){
            this.inputField2 = inputField2;
        }

        public void setResultLabel(JLabel resultLabel){
            this.resultLabel = resultLabel;
        }

        public JButton getAddButton(){ return addButton; }
        public JButton getSubtractButton(){ return subtractButton; }
        public JButton getMultiplyButton(){ return multiplyButton; }
        public JButton getDivideButton(){ return divideButton; }
        public JTextField getInputField1(){ return inputField1; }
        public JTextField getInputField2(){ return inputField2; }
        public JLabel getResultLabel(){ return resultLabel; }

        private void setConfiguration(){
            setTitle("Калькулятор");

            int initialWidth = 680;
            int initialHeight = 300;
            setSize(initialWidth, initialHeight);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            // Устанавливаем минимальный размер окна
            setMinimumSize(new Dimension(initialWidth, initialHeight));
        }

        private JPanel initComponents(){
            // Создаем панель для размещения элементов
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;

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

            // Устанавливаем размер кнопок
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
            return panel;
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

        private void addActions(JPanel panel){
            // Добавляем слушатель для отслеживания изменений размеров фрейма
            addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    setButtonSize();
                    setButtonGaps();
                    setVerticalInsets(panel);
                }
            });

            // Добавляем слушатели для кнопок
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    presenter.onPlusClicked();
                }
            });

            subtractButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    presenter.onMinusClicked();
                }
            });

            multiplyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    presenter.onMultiplyClicked();
                }
            });

            divideButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    presenter.onDivideClicked();
                }
            });
        }
        @Override
        public void printResult(double num) {
            resultLabel.setText("Результат: " + num);
        }

        @Override
        public void displayError(String message) {
            resultLabel.setText("Результат: " + message);
        }

        @Override
        public String getFirstArgumentAsString() {
            return inputField1.getText();
        }

        @Override
        public String getSecondArgumentAsString() {
            return inputField2.getText();
        }
    }
}
