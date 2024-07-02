import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ToDoListApp extends JFrame {
    private ArrayList<String> tasks;
    private DefaultListModel<String> listModel;
    private JList<String> taskList;
    private JTextField taskField;
    private JTextArea actionArea;

    public ToDoListApp() {
        tasks = new ArrayList<>();
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskField = new JTextField(20);
        actionArea = new JTextArea(10, 30);
        actionArea.setEditable(false);

        setTitle("To-Do List Application");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Title Label
        JLabel titleLabel = new JLabel("To-Do List", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLUE);
        panel.add(titleLabel, BorderLayout.NORTH);

        // Task List
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(taskList);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Task: "));
        inputPanel.add(taskField);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });
        buttonsPanel.add(addButton);

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editTask();
            }
        });
        buttonsPanel.add(editButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteTask();
            }
        });
        buttonsPanel.add(deleteButton);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetTaskField();
            }
        });
        buttonsPanel.add(resetButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonsPanel.add(cancelButton);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonsPanel, BorderLayout.SOUTH);

        // Action Area
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BorderLayout());
        actionPanel.add(new JLabel("Actions:"), BorderLayout.NORTH);
        actionPanel.add(new JScrollPane(actionArea), BorderLayout.CENTER);

        add(panel, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void addTask() {
        String task = taskField.getText();
        if (!task.isEmpty()) {
            tasks.add(task);
            listModel.addElement(task);
            taskField.setText("");
            actionArea.append("Added task: " + task + "\n");
        }
    }

    private void editTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            String newTask = taskField.getText();
            if (!newTask.isEmpty()) {
                String oldTask = tasks.set(selectedIndex, newTask);
                listModel.set(selectedIndex, newTask);
                taskField.setText("");
                actionArea.append("Edited task: " + oldTask + " to " + newTask + "\n");
            }
        }
    }

    private void deleteTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            String removedTask = tasks.remove(selectedIndex);
            listModel.remove(selectedIndex);
            actionArea.append("Deleted task: " + removedTask + "\n");
        }
    }

    private void resetTaskField() {
        taskField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ToDoListApp();
            }
        });
    }
}