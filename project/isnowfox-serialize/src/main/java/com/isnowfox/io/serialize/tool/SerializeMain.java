package com.isnowfox.io.serialize.tool;

import com.isnowfox.io.serialize.tool.model.Message;
import com.isnowfox.io.serialize.tool.ui.MainFrame;
import httl.spi.codecs.json.JSON;
import httl.spi.codecs.json.JSONObject;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

import javax.swing.*;
import java.io.*;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class SerializeMain {

    public static void start(Config c) {
        MainFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
//            UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        MainFrame frame = new MainFrame(c);
        frame.setVisible(true);
    }

    public static void main(String[] args) throws ParseException {
//        MessageAnalyse analyse = new MessageAnalyse(Config.load());
//        try {
//            InputStream in = SerializeMain.class.getResourceAsStream("/TestMessage.io");
//            LineIterator it = IOUtils.lineIterator(in, "utf8");
//            Message m = analyse.analyse(it, "com.xie.message");
//            m.setName(FilenameUtils.getBaseName(m.getName()));
//            System.out.println(m);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
		try {
			//UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MainFrame frame = new MainFrame(Config.load());
		frame.setVisible(true);
    }
}
