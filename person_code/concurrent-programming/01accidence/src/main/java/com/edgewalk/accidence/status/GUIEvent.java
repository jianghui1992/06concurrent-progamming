package com.edgewalk.accidence.status;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by: edgewalk
 * 2018-08-18 20:10
 */
@Slf4j
public class GUIEvent {
    public static void main(String[] args) {
        JFrame frame = new JFrame("简单的GUI程序");
        frame.setJMenuBar(new JMenuBar());
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.printf("[%s]事件:%s\n",Thread.currentThread().getName(), e);
            }
        });
        frame.setBounds(300,400,400,300);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
