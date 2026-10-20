package com.campus.lostandfound.UI;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.border.Border;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public final class UniversityTheme {

    public static final Color BLUE = new Color(43, 88, 158);
    public static final Color BLUE_DARK = new Color(27, 57, 105);
    public static final Color BLUE_SOFT = new Color(231, 239, 250);
    public static final Color GREEN = new Color(116, 157, 67);
    public static final Color GREEN_DARK = new Color(83, 123, 42);
    public static final Color BACKGROUND = new Color(244, 248, 252);
    public static final Color SURFACE = Color.WHITE;
    public static final Color TEXT = new Color(31, 48, 67);
    public static final Color MUTED = new Color(93, 111, 128);
    public static final Color BORDER = new Color(211, 222, 234);

    private static final String LOGO = "/com/campus/lostandfound/UI/Brand/universal-university-logo.png";
    private static final String SOAR_BANNER = "/com/campus/lostandfound/UI/Brand/soar-banner.png";

    private UniversityTheme() {
    }

    public static void install() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            throw new IllegalStateException("Unable to load the application theme", ex);
        }
        UIManager.put("Component.arc", 12);
        UIManager.put("Button.arc", 12);
        UIManager.put("TextComponent.arc", 10);
        UIManager.put("Component.focusColor", GREEN);
        UIManager.put("Button.focusedBorderColor", GREEN);
        UIManager.put("TextField.focusedBorderColor", BLUE);
        UIManager.put("PasswordField.focusedBorderColor", BLUE);
        UIManager.put("ComboBox.focusedBorderColor", BLUE);
        UIManager.put("TabbedPane.selectedBackground", BLUE_SOFT);
        UIManager.put("Table.selectionBackground", BLUE_SOFT);
        UIManager.put("Table.selectionForeground", TEXT);
        UIManager.put("TableHeader.background", BLUE);
        UIManager.put("TableHeader.foreground", Color.WHITE);
        UIManager.put("Panel.background", BACKGROUND);
        UIManager.put("Label.foreground", TEXT);
    }

    public static JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout(18, 8));
        header.setBackground(SURFACE);
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER),
                BorderFactory.createEmptyBorder(12, 20, 8, 20)));

        header.add(logoLabel(390, 84), BorderLayout.WEST);

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("CAMPUS LOST & FOUND");
        title.setFont(new Font("Segoe UI", Font.BOLD, 25));
        title.setForeground(BLUE_DARK);
        JLabel subtitle = new JLabel("Universal AI University • Karjat Campus Services");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(GREEN_DARK);
        titlePanel.add(Box.createVerticalGlue());
        titlePanel.add(title);
        titlePanel.add(Box.createVerticalStrut(4));
        titlePanel.add(subtitle);
        titlePanel.add(Box.createVerticalGlue());
        header.add(titlePanel, BorderLayout.CENTER);

        JLabel banner = imageLabel(SOAR_BANNER, 500, 30);
        banner.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
        header.add(banner, BorderLayout.SOUTH);
        return header;
    }

    public static JPanel createLoginBrandPanel() {
        JPanel panel = new JPanel(new BorderLayout(16, 16));
        panel.setBackground(BLUE_DARK);
        panel.setBorder(BorderFactory.createEmptyBorder(36, 34, 30, 34));

        JLabel logo = logoLabel(390, 99);
        logo.setOpaque(true);
        logo.setBackground(Color.WHITE);
        logo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(logo, BorderLayout.NORTH);

        JPanel copy = new JPanel();
        copy.setOpaque(false);
        copy.setLayout(new BoxLayout(copy, BoxLayout.Y_AXIS));
        JLabel eyebrow = new JLabel("UNIVERSAL AI UNIVERSITY");
        eyebrow.setFont(new Font("Segoe UI", Font.BOLD, 13));
        eyebrow.setForeground(new Color(178, 211, 139));
        JLabel heading = new JLabel("Lost something on campus?");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 29));
        heading.setForeground(Color.WHITE);
        JLabel body = new JLabel("<html>Report, search, claim, and return campus items through one trusted university desk.</html>");
        body.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        body.setForeground(new Color(225, 235, 247));
        copy.add(Box.createVerticalGlue());
        copy.add(eyebrow);
        copy.add(Box.createVerticalStrut(12));
        copy.add(heading);
        copy.add(Box.createVerticalStrut(12));
        copy.add(body);
        copy.add(Box.createVerticalGlue());
        panel.add(copy, BorderLayout.CENTER);

        panel.add(imageLabel(SOAR_BANNER, 440, 27), BorderLayout.SOUTH);
        return panel;
    }

    public static JPanel card(LayoutManager layout) {
        JPanel panel = new JPanel(layout);
        panel.setBackground(SURFACE);
        panel.setBorder(cardBorder());
        return panel;
    }

    public static Border cardBorder() {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(20, 22, 20, 22));
    }

    public static void stylePrimaryButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(BLUE);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BLUE_DARK),
                BorderFactory.createEmptyBorder(8, 16, 8, 16)));
        button.setFocusPainted(false);
        button.setOpaque(true);
    }

    public static void styleSecondaryButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(BLUE_DARK);
        button.setBackground(SURFACE);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(8, 14, 8, 14)));
        button.setFocusPainted(false);
        button.setOpaque(true);
    }

    public static JLabel sectionTitle(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 24));
        label.setForeground(BLUE_DARK);
        return label;
    }

    private static JLabel imageLabel(String path, int width, int height) {
        URL resource = UniversityTheme.class.getResource(path);
        if (resource == null) {
            JLabel missing = new JLabel("Universal AI University", SwingConstants.CENTER);
            missing.setFont(new Font("Segoe UI", Font.BOLD, 20));
            missing.setForeground(BLUE);
            return missing;
        }
        Image image = new ImageIcon(resource).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(image), SwingConstants.CENTER);
    }

    private static JLabel logoLabel(int width, int height) {
        URL resource = UniversityTheme.class.getResource(LOGO);
        if (resource == null) return imageLabel(LOGO, width, height);
        try {
            BufferedImage source = ImageIO.read(resource);
            int cropWidth = Math.min(535, source.getWidth());
            BufferedImage universityLogo = source.getSubimage(0, 0, cropWidth, source.getHeight());
            Image scaled = universityLogo.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new JLabel(new ImageIcon(scaled), SwingConstants.CENTER);
        } catch (IOException ex) {
            return imageLabel(LOGO, width, height);
        }
    }
}
