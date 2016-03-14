import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Launcher extends javax.swing.JApplet {

    public static void main(String[] args) {
        try {
            //First it downloads whatever updates are available, then it launches the client.
            updateClient();
            updateCache();
            updateLib();
            launch();
        }         
        catch(Exception exception) {
        }
    }
    
    private static String serverVer[] = new String[15]; // Leave these as they are.
    private static String clientVer[] = new String[15];
    public static byte[] createChecksum(String s) throws Exception {
        FileInputStream fileinputstream = new FileInputStream(s);
        byte abyte0[] = new byte[1024];
        MessageDigest messagedigest = MessageDigest.getInstance("MD5");
        int i;
        do {
                i = fileinputstream.read(abyte0);
                if(i >= 0)
                {
                    messagedigest.update(abyte0, 0, i);
                }
        } 
        while(i != -1);
        fileinputstream.close();
        return messagedigest.digest();
    }
    public static String getMD5Checksum(String s) throws Exception {
        byte abyte0[] = createChecksum(s);
        String s1 = "";
        for(int i = 0; i < abyte0.length; i++)
        {
            s1 = (new StringBuilder()).append(s1).append(Integer.toString((abyte0[i] & 0xff) + 256, 16).substring(1)).toString();
        }

        return s1;
    }
    /**
     * This is our update method where it downloads a copy of the client cache archive from the webserver and then checks the server's MD5 hash against it.
     */
    public static void updateClient() throws IOException {
        try {
            for(int i = 0; i < 1; i++) {
                serverVer[i] = ""; // Leave these as they are.
                clientVer[i] = "";
            }
			File baseDir = new File(System.getProperty("user.home") // user.home specifies the home directory of any OS.
			+ File.separator 
			+ "WK"); // This is the folder in your home directory it creates, saves, and extracts the cache in to.
			if (!baseDir.isDirectory()) // Checks if the directory exists or not.
			{
			baseDir.mkdir(); // Creates the directory if none exists.
			}
			if((new File(System.getProperty("user.home") // user.home specifies the home directory of any OS.
			+ File.separator 
			+ "WK" // This is the folder in your home directory it creates, saves, and extracts the cache in to.
			+ File.separator 
			+ "client.zip")).exists()) // Here is your client cache archive.
            {
                clientVer[0] = getMD5Checksum(System.getProperty("user.home") // user.home specifies the home directory of any OS.
				+ File.separator 
				+ "WK" // This is the folder in your home directory it creates, saves, and extracts the cache in to.
				+ File.separator 
				+ "client.zip"); // Here is your client cache archive.
            }
                        
            URL url = new URL("http://localhost/url.txt"); // The URL on your webserver that you can quickly update instead of 															 // updating the hash checker code each time the URL changes for the
																	 // client cache archive file and hashes.txt file.
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(url.openStream()));
            String s = bufferedreader.readLine();
            bufferedreader.close();
            URL url1 = new URL((new StringBuilder()).append(s).append("/hashes.txt").toString()); // The file that lists the MD5 hashes on your webserver.
            Properties properties = new Properties();
            InputStream inputstream = url1.openStream();
            properties.load(inputstream);
            serverVer[0] = properties.getProperty("client"); // This must match the text before the = and the MD5 hash on the hashes.txt file.
            // EX: da=63d8e501858db397ae6e0b3ff762a1e0
            //serverVer[1] = properties.getProperty("da2"); //Disabled multiple file download but worth keeping incase of future need
            String s1 = "";
            byte byte0 = 0;
            int j = 0;
            do {
                if(j >= 1) {
                    break;
                }
                switch(j) {
                case 0:
                    s1 = "client.zip"; // Here is where you want to name the cache archive that the client needs to download.
                    break;
                }
                if(!clientVer[j].equals(serverVer[j])) {
                    URL url2 = new URL((new StringBuilder()).append(s).append("/").append(s1).toString());
                    int k = url2.openConnection().getContentLength();
                    byte abyte0[] = new byte[k];
                    BufferedInputStream bufferedinputstream = new BufferedInputStream(url2.openStream());
                    s1 = (new StringBuilder()).append(System.getProperty("user.home") // user.home specifies the home directory of any OS.
                    + File.separator 
                    + "WK" // This is the folder in your home directory it creates, saves, and extracts the cache in to.
                    + File.separator).append(s1).toString();
                    int i1 = 0;
                    do {
                        if(i1 >= k) {
                            break;
                        }
                        int l = bufferedinputstream.read(abyte0, i1, abyte0.length - i1);
                        if(l == -1) {
                            break;
                        }
                        i1 += l;
                    } while(true);
                    FileOutputStream fileoutputstream = new FileOutputStream(s1);
                    fileoutputstream.write(abyte0);
                    fileoutputstream.flush();
                    fileoutputstream.close();
                    bufferedinputstream.close();
                    clientVer[j] = getMD5Checksum(s1);
                    if(!clientVer[j].equals(serverVer[j]) && byte0 < 1) {
                        j--;
                        System.out.println((new StringBuilder()).append("Hash mis-match after download. Retrying (").append(byte0).append(")...").toString());
                        System.out.println((new StringBuilder()).append(getMD5Checksum(s1)));
                        byte0++;
                    }
                    else {
                        if(byte0 >= 1) {
                            System.out.println("Error downloading file."); //You may need to verify that the client cache archive file is in the right directory.
                            j = 2;
                            break;
                        }
                        byte0 = 0;
                        System.out.println((new StringBuilder()).append(s1).append(" - Download complete.").toString()); // Appears after the .zip has been downloaded.
			zip1(); // Extracts the downloaded archive
                    }
                }
                j++;
            } while(true);
        }
        catch(Exception exception) {
            System.out.println("Error saving file. Please make sure the game client is not already open. Try again.");
            System.out.println(exception);
        }
    }
    
    public static void updateCache() throws IOException {
        try {
            for(int i = 0; i < 1; i++) {
                serverVer[i] = ""; // Leave these as they are.
                clientVer[i] = "";
            }
			File baseDir = new File(System.getProperty("user.home") // user.home specifies the home directory of any OS.
			+ File.separator 
			+ "WK"); // This is the folder in your home directory it creates, saves, and extracts the cache in to.
			if (!baseDir.isDirectory()) // Checks if the directory exists or not.
			{
			baseDir.mkdir(); // Creates the directory if none exists.
			}
			if((new File(System.getProperty("user.home") // user.home specifies the home directory of any OS.
			+ File.separator 
			+ "WK" // This is the folder in your home directory it creates, saves, and extracts the cache in to.
			+ File.separator 
			+ "cache.zip")).exists()) // Here is your client cache archive.
            {
                clientVer[0] = getMD5Checksum(System.getProperty("user.home") // user.home specifies the home directory of any OS.
				+ File.separator 
				+ "WK" // This is the folder in your home directory it creates, saves, and extracts the cache in to.
				+ File.separator 
				+ "cache.zip"); // Here is your client cache archive.
            }
                        
            URL url = new URL("http://localhost/url.txt"); // The URL on your webserver that you can quickly update instead of 															 // updating the hash checker code each time the URL changes for the
																	 // client cache archive file and hashes.txt file.
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(url.openStream()));
            String s = bufferedreader.readLine();
            bufferedreader.close();
            URL url1 = new URL((new StringBuilder()).append(s).append("/hashes.txt").toString()); // The file that lists the MD5 hashes on your webserver.
            Properties properties = new Properties();
            InputStream inputstream = url1.openStream();
            properties.load(inputstream);
            serverVer[0] = properties.getProperty("cache"); // This must match the text before the = and the MD5 hash on the hashes.txt file.
            String s1 = "";
            byte byte0 = 0;
            int j = 0;
            do {
                if(j >= 1) {
                    break;
                }
                switch(j) {
                case 0:
                    s1 = "cache.zip"; // Here is where you want to name the cache archive that the client needs to download.
                    break;
                }
                if(!clientVer[j].equals(serverVer[j])) {
                    URL url2 = new URL((new StringBuilder()).append(s).append("/").append(s1).toString());
                    int k = url2.openConnection().getContentLength();
                    byte abyte0[] = new byte[k];
                    BufferedInputStream bufferedinputstream = new BufferedInputStream(url2.openStream());
                    s1 = (new StringBuilder()).append(System.getProperty("user.home") // user.home specifies the home directory of any OS.
                    + File.separator 
                    + "WK" // This is the folder in your home directory it creates, saves, and extracts the cache in to.
                    + File.separator).append(s1).toString();
                    int i1 = 0;
                    do {
                        if(i1 >= k) {
                            break;
                        }
                        int l = bufferedinputstream.read(abyte0, i1, abyte0.length - i1);
                        if(l == -1) {
                            break;
                        }
                        i1 += l;
                    } while(true);
                    FileOutputStream fileoutputstream = new FileOutputStream(s1);
                    fileoutputstream.write(abyte0);
                    fileoutputstream.flush();
                    fileoutputstream.close();
                    bufferedinputstream.close();
                    clientVer[j] = getMD5Checksum(s1);
                    if(!clientVer[j].equals(serverVer[j]) && byte0 < 1) {
                        j--;
                        System.out.println((new StringBuilder()).append("Hash mis-match after download. Retrying (").append(byte0).append(")...").toString());
                        System.out.println((new StringBuilder()).append(getMD5Checksum(s1)));
                        byte0++;
                    }
                    else {
                        if(byte0 >= 1) {
                            System.out.println("Error downloading file."); //You may need to verify that the client cache archive file is in the right directory.
                            j = 2;
                            break;
                        }
                        byte0 = 0;
                        System.out.println((new StringBuilder()).append(s1).append(" - Download complete.").toString()); // Appears after the .zip has been downloaded.
			zip3(); // Extracts the downloaded archive
                    }
                }
                j++;
            } while(true);
        }
        catch(Exception exception) {
            System.out.println("Error saving file. Please make sure the game client is not already open. Try again.");
            System.out.println(exception);
        }
    }
    
    public static void updateLib() throws IOException {
        try {
            for(int i = 0; i < 1; i++) {
                serverVer[i] = ""; // Leave these as they are.
                clientVer[i] = "";
            }
			File baseDir = new File(System.getProperty("user.home") // user.home specifies the home directory of any OS.
			+ File.separator 
			+ "WK"
                        + File.separator 
			+ "lib");
			if (!baseDir.isDirectory()) // Checks if the directory exists or not.
			{
			baseDir.mkdir(); // Creates the directory if none exists.
			}
			if((new File(System.getProperty("user.home") // user.home specifies the home directory of any OS.
			+ File.separator 
			+ "WK"
			+ File.separator 
                        + "lib"
			+ File.separator 
			+ "lib.zip")).exists()) // Here is your client cache archive.
            {
                clientVer[0] = getMD5Checksum(System.getProperty("user.home") // user.home specifies the home directory of any OS.
				+ File.separator 
				+ "WK"
				+ File.separator 
                                + "lib"
				+ File.separator 
				+ "lib.zip"); // Here is your client cache archive.
            }
                        
            URL url = new URL("http://localhost/url.txt"); // The URL on your webserver that you can quickly update instead of 															 // updating the hash checker code each time the URL changes for the
																	 // client cache archive file and hashes.txt file.
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(url.openStream()));
            String s = bufferedreader.readLine();
            bufferedreader.close();
            URL url1 = new URL((new StringBuilder()).append(s).append("/hashes.txt").toString()); // The file that lists the MD5 hashes on your webserver.
            Properties properties = new Properties();
            InputStream inputstream = url1.openStream();
            properties.load(inputstream);
            serverVer[0] = properties.getProperty("lib"); // This must match the text before the = and the MD5 hash on the hashes.txt file.
            // EX: da=63d8e501858db397ae6e0b3ff762a1e0
            //serverVer[1] = properties.getProperty("da2"); //Disabled multiple file download but worth keeping incase of future need
            String s1 = "";
            byte byte0 = 0;
            int j = 0;
            do {
                if(j >= 1) {
                    break;
                }
                switch(j) {
                case 0:
                    s1 = "lib.zip"; // Here is where you want to name the cache archive that the client needs to download.
                    break;
                }
                if(!clientVer[j].equals(serverVer[j])) {
                    URL url2 = new URL((new StringBuilder()).append(s).append("/").append(s1).toString());
                    int k = url2.openConnection().getContentLength();
                    byte abyte0[] = new byte[k];
                    BufferedInputStream bufferedinputstream = new BufferedInputStream(url2.openStream());
                    s1 = (new StringBuilder()).append(System.getProperty("user.home") // user.home specifies the home directory of any OS.
                    + File.separator 
                    + "WK"
                    + File.separator 
                    + "lib"
                    + File.separator).append(s1).toString();
                    int i1 = 0;
                    do {
                        if(i1 >= k) {
                            break;
                        }
                        int l = bufferedinputstream.read(abyte0, i1, abyte0.length - i1);
                        if(l == -1) {
                            break;
                        }
                        i1 += l;
                    } while(true);
                    FileOutputStream fileoutputstream = new FileOutputStream(s1);
                    fileoutputstream.write(abyte0);
                    fileoutputstream.flush();
                    fileoutputstream.close();
                    bufferedinputstream.close();
                    clientVer[j] = getMD5Checksum(s1);
                    if(!clientVer[j].equals(serverVer[j]) && byte0 < 1) {
                        j--;
                        System.out.println((new StringBuilder()).append("Hash mis-match after download. Retrying (").append(byte0).append(")...").toString());
                        System.out.println((new StringBuilder()).append(getMD5Checksum(s1)));
                        byte0++;
                    }
                    else {
                        if(byte0 >= 1) {
                            System.out.println("Error downloading file."); //You may need to verify that the client cache archive file is in the right directory.
                            j = 2;
                            break;
                        }
                        byte0 = 0;
                        System.out.println((new StringBuilder()).append(s1).append(" - Download complete.").toString()); // Appears after the .zip has been downloaded.
			zip2(); // Extracts the downloaded archive
                    }
                }
                j++;
            } while(true);
        }
        catch(Exception exception) {
            System.out.println("Error saving file. Please make sure the game client is not already open. Try again.");
            System.out.println(exception);
        }
    }
    
    /**
    * This method extracts the downloaded .zip archive in to the home directory for the folder specified.
    */
    public static void zip1() throws Exception {
	String fName = System.getProperty("user.home") // user.home specifies the home directory of any OS.
	+ File.separator 
	+ "WK" // This is the folder in your home directory it creates, saves, and extracts the cache in to.
	+ File.separator 
	+ "client.zip"; // Here is your client cache archive.
	byte[] buf = new byte[1024];
	ZipInputStream zinstream = new ZipInputStream(
        new FileInputStream(fName));
	ZipEntry zentry = zinstream.getNextEntry();
	//System.out.println("Name of current Zip Entry : " + entry + "\n"); // You could have this printed out info for debugging purposes.
	System.out.println("Attempting to extract game client update...");
	while (zentry != null) {
		String entryName = zentry.getName();
		//System.out.println("Name of  Zip Entry : " + entryName); // You could have this printed out info for debugging purposes.
		FileOutputStream outstream = new FileOutputStream(System.getProperty("user.home") + File.separator + "WK" + File.separator + entryName);
		int n;
		while ((n = zinstream.read(buf, 0, 1024)) > -1) {
			outstream.write(buf, 0, n);
		}
		//System.out.println("Successfully Extracted File Name : " + entryName); // You could have this printed out info for debugging purposes.
		outstream.close();
		zinstream.closeEntry();
		zentry = zinstream.getNextEntry();
	}
	zinstream.close();
	System.out.println("Game client cache data update extracted successfully.");
    }
    
    public static void zip2() throws Exception {
	String fName = System.getProperty("user.home") // user.home specifies the home directory of any OS.
	+ File.separator 
	+ "WK" // This is the folder in your home directory it creates, saves, and extracts the cache in to.
	+ File.separator 
        + "lib" // This is the folder in your home directory it creates, saves, and extracts the cache in to.
	+ File.separator 
	+ "lib.zip"; // Here is your client cache archive.
	byte[] buf = new byte[1024];
	ZipInputStream zinstream = new ZipInputStream(
        new FileInputStream(fName));
	ZipEntry zentry = zinstream.getNextEntry();
	//System.out.println("Name of current Zip Entry : " + entry + "\n"); // You could have this printed out info for debugging purposes.
	System.out.println("Attempting to extract game client update...");
	while (zentry != null) {
		String entryName = zentry.getName();
		//System.out.println("Name of  Zip Entry : " + entryName); // You could have this printed out info for debugging purposes.
		FileOutputStream outstream = new FileOutputStream(System.getProperty("user.home") + File.separator + "WK" + File.separator + "lib" + File.separator + entryName);
		int n;
		while ((n = zinstream.read(buf, 0, 1024)) > -1) {
			outstream.write(buf, 0, n);
		}
		//System.out.println("Successfully Extracted File Name : " + entryName); // You could have this printed out info for debugging purposes.
		outstream.close();
		zinstream.closeEntry();
		zentry = zinstream.getNextEntry();
	}
	zinstream.close();
	System.out.println("Game client lib data update extracted successfully.");
    }
    
    public static void zip3() throws Exception {
	String fName = System.getProperty("user.home") // user.home specifies the home directory of any OS.
	+ File.separator 
	+ "WK" // This is the folder in your home directory it creates, saves, and extracts the cache in to.
	+ File.separator 
	+ "cache.zip"; // Here is your client cache archive.
	byte[] buf = new byte[1024];
	ZipInputStream zinstream = new ZipInputStream(
        new FileInputStream(fName));
	ZipEntry zentry = zinstream.getNextEntry();
	//System.out.println("Name of current Zip Entry : " + entry + "\n"); // You could have this printed out info for debugging purposes.
	System.out.println("Attempting to extract game client cache...");
	while (zentry != null) {
		String entryName = zentry.getName();
		//System.out.println("Name of  Zip Entry : " + entryName); // You could have this printed out info for debugging purposes.
		FileOutputStream outstream = new FileOutputStream(System.getProperty("user.home") + File.separator + "WK" + File.separator + entryName);
		int n;
		while ((n = zinstream.read(buf, 0, 1024)) > -1) {
			outstream.write(buf, 0, n);
		}
		//System.out.println("Successfully Extracted File Name : " + entryName); // You could have this printed out info for debugging purposes.
		outstream.close();
		zinstream.closeEntry();
		zentry = zinstream.getNextEntry();
	}
	zinstream.close();
	System.out.println("Game client cache data update extracted successfully.");
    }
    
    /**
    * This method launches your executable jar after it has verified the MD5 hash is up to date.
    */
    public static void launch() throws Exception {
	File f = new File(System.getProperty("user.home") // user.home specifies the home directory of any OS.
	+ File.separator 
	+ "WK" // This is the folder in your home directory it creates, saves, and extracts the cache in to.
	+ File.separator 
	+ "Wolf_Kingdom.jar"); // Here is your executable client JAR file that it launches.
        ProcessBuilder pb = new ProcessBuilder(System.getProperty("java.home") // Your PC's Java runtime environment folder
	+ File.separator 
	+ "bin" // Self explainatory
	+ File.separator 
	+ "java", "-jar", f.getAbsolutePath() ); // Launches your executable JAR with the java -jar command.
        Process p = pb.start();
    }
    
    /** Initializes the applet Launcher */
    @Override
    public void init() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Launcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Launcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Launcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Launcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the applet */
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {

                public void run() {
                    initComponents();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /** This method is called from within the init() method to
     * initialize the form.
     * WARNING: Do NOT modify the code right below this. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        launchButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();

        setName("Launcher"); // NOI18N

        launchButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        launchButton.setToolTipText("Click here to launch the game client");
        launchButton.setLabel("Play Lupus Regnum");
        launchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                launchButtonActionPerformed(evt);
            }
        });

        jTextPane1.setEditable(false);
        jTextPane1.setFont(jTextPane1.getFont());
        jTextPane1.setText("After clicking the \"Play Lupus Regnum\" button below, please wait while the game is download to your computer so that it will open immediately in the future. Whenever the game is updated, this will download the updated game client automatically. Once everything is ready, it will launch the game.");
        jTextPane1.setFocusable(false);
        jTextPane1.setOpaque(false);
        jScrollPane1.setViewportView(jTextPane1);

        jLabel1.setText("Lupus Regnum Game Launcher");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(jLabel1)))
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(182, 182, 182)
                .addComponent(launchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(185, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(launchButton)
                .addGap(19, 19, 19))
        );

        launchButton.getAccessibleContext().setAccessibleDescription("Click here to launch the game");
    }// </editor-fold>//GEN-END:initComponents

private void launchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_launchButtonActionPerformed
        try {
            //First it downloads whatever updates are available, then it launches the client.
            updateCache();
            updateLib();
            launch();
        }         
        catch(Exception exception) {
        }
}//GEN-LAST:event_launchButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JButton launchButton;
    // End of variables declaration//GEN-END:variables
}