package org.jetbrains.java.decompiler.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by Jonathan Beaudoin on 7/19/2015.
 */
public class FileUtilities {

	/**
	 * Reuse the same buffer to reduce un-needed garbage
	 */
	private static byte[] buffer = new byte[1024];

	public static void extract(File jar, File out) {
		try {
			ZipInputStream zis = new ZipInputStream(new FileInputStream(jar));
			for (ZipEntry entry = zis.getNextEntry(); entry != null; entry = zis.getNextEntry()) {
				File destinationPath = new File(out, entry.getName());
				destinationPath.getParentFile().mkdirs();

				if (entry.isDirectory()) {
					continue;
				} else {
					FileOutputStream fos = new FileOutputStream(destinationPath);
					int len;
					while ((len = zis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}
					fos.close();
				}
			}
			zis.closeEntry();
			zis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
